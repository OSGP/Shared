/**
 * Copyright 2014-2016 Smart Society Services B.V.
 */
package com.alliander.osgp.shared.infra.ws;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.security.support.KeyStoreFactoryBean;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.alliander.osgp.shared.exceptionhandling.WebServiceSecurityException;

public class DefaultWebServiceTemplateFactory implements WebserviceTemplateFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWebServiceTemplateFactory.class);

    private final Map<String, WebServiceTemplate> webServiceTemplates;
    private final Lock lock = new ReentrantLock();

    private static final String ORGANISATION_IDENTIFICATION_HEADER = "OrganisationIdentification";
    private static final String USER_NAME_HEADER = "UserName";
    private static final String APPLICATION_NAME_HEADER = "ApplicationName";

    private static final String NAMESPACE = "http://www.alliander.com/schemas/osp/common";

    private static final String PROXY_SERVER = "proxy-server";

    private Jaxb2Marshaller marshaller;
    private SaajSoapMessageFactory messageFactory;
    private String targetUri;
    private String keyStoreType;
    private String keyStoreLocation;
    private String keyStorePassword;
    private KeyStoreFactoryBean trustStoreFactory;
    private String applicationName;
    private int maxConnectionsPerRoute;
    private int maxConnectionsTotal;

    private DefaultWebServiceTemplateFactory() {
        this.webServiceTemplates = new HashMap<>();
    }

    public WebServiceTemplate getTemplate(final String organisationIdentification, final String userName)
            throws WebServiceSecurityException {
        return this.getTemplate(organisationIdentification, userName, this.applicationName);
    }

    @Override
    public WebServiceTemplate getTemplate(final String organisationIdentification, final String userName,
            final URL targetUri) throws WebServiceSecurityException {
        this.targetUri = targetUri.toString();
        return this.getTemplate(organisationIdentification, userName, this.applicationName);
    }

    public static class Builder {
        private String applicationName;
        private Jaxb2Marshaller marshaller;
        private SaajSoapMessageFactory messageFactory;
        private String targetUri;
        private String keyStoreType;
        private String keyStoreLocation;
        private String keyStorePassword;
        private KeyStoreFactoryBean trustStoreFactory;
        private int maxConnectionsPerRoute = 2;
        private int maxConnectionsTotal = 20;

        public Builder setApplicationName(final String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public Builder setMarshaller(final Jaxb2Marshaller marshaller) {
            this.marshaller = marshaller;
            return this;
        }

        public Builder setMessageFactory(final SaajSoapMessageFactory messageFactory) {
            this.messageFactory = messageFactory;
            return this;
        }

        public Builder setTargetUri(final String targetUri) {
            this.targetUri = targetUri;
            return this;
        }

        public Builder setKeyStoreType(final String keyStoreType) {
            this.keyStoreType = keyStoreType;
            return this;
        }

        public Builder setKeyStoreLocation(final String keyStoreLocation) {
            this.keyStoreLocation = keyStoreLocation;
            return this;
        }

        public Builder setKeyStorePassword(final String keyStorePassword) {
            this.keyStorePassword = keyStorePassword;
            return this;
        }

        public Builder setTrustStoreFactory(final KeyStoreFactoryBean trustStoreFactory) {
            this.trustStoreFactory = trustStoreFactory;
            return this;
        }

        public Builder setMaxConnectionsPerRoute(final int maxConnectionsPerRoute) {
            this.maxConnectionsPerRoute = maxConnectionsPerRoute;
            return this;
        }

        public Builder setMaxConnectionsTotal(final int maxConnectionsTotal) {
            this.maxConnectionsTotal = maxConnectionsTotal;
            return this;
        }

        public DefaultWebServiceTemplateFactory build() {
            final DefaultWebServiceTemplateFactory webServiceTemplateFactory = new DefaultWebServiceTemplateFactory();
            webServiceTemplateFactory.marshaller = this.marshaller;
            webServiceTemplateFactory.messageFactory = this.messageFactory;
            webServiceTemplateFactory.targetUri = this.targetUri;
            webServiceTemplateFactory.keyStoreType = this.keyStoreType;
            webServiceTemplateFactory.keyStoreLocation = this.keyStoreLocation;
            webServiceTemplateFactory.keyStorePassword = this.keyStorePassword;
            webServiceTemplateFactory.trustStoreFactory = this.trustStoreFactory;
            webServiceTemplateFactory.applicationName = this.applicationName;
            webServiceTemplateFactory.maxConnectionsPerRoute = this.maxConnectionsPerRoute;
            webServiceTemplateFactory.maxConnectionsTotal = this.maxConnectionsTotal;
            return webServiceTemplateFactory;
        }
    }

    @Override
    public WebServiceTemplate getTemplate(final String organisationIdentification, final String userName,
            final String applicationName) throws WebServiceSecurityException {

        if (StringUtils.isEmpty(organisationIdentification)) {
            LOGGER.error("organisationIdentification is empty or null");
        }
        if (StringUtils.isEmpty(userName)) {
            LOGGER.error("userName is empty or null");
        }
        if (StringUtils.isEmpty(applicationName)) {
            LOGGER.error("applicationName is empty or null");
        }

        WebServiceTemplate webServiceTemplate = null;
        try {
            this.lock.lock();

            // Create new webservice template, if not yet available for
            // a combination of organisation, username, applicationName and
            // targetUri
            final String url = (this.targetUri == null) ? "" : "-" + this.targetUri;
            final String key = organisationIdentification.concat("-").concat(userName).concat(applicationName)
                    .concat(url);

            if (!this.webServiceTemplates.containsKey(key)) {
                this.webServiceTemplates.put(key,
                        this.createTemplate(organisationIdentification, userName, applicationName));
            }

            webServiceTemplate = this.webServiceTemplates.get(key);
        } finally {
            this.lock.unlock();
        }

        return webServiceTemplate;
    }

    private WebServiceTemplate createTemplate(final String organisationIdentification, final String userName,
            final String applicationName) throws WebServiceSecurityException {
        final WebServiceTemplate webServiceTemplate = new WebServiceTemplate(this.messageFactory);

        webServiceTemplate.setCheckConnectionForFault(true);
        if (this.targetUri != null) {
            webServiceTemplate.setDefaultUri(this.targetUri);
            if (this.targetUri.contains(PROXY_SERVER)) {
                webServiceTemplate.setCheckConnectionForFault(false);
            }
        }

        webServiceTemplate.setMarshaller(this.marshaller);
        webServiceTemplate.setUnmarshaller(this.marshaller);
        webServiceTemplate.setInterceptors(new ClientInterceptor[] {
                new OrganisationIdentificationClientInterceptor(organisationIdentification, userName, applicationName,
                        NAMESPACE, ORGANISATION_IDENTIFICATION_HEADER, USER_NAME_HEADER, APPLICATION_NAME_HEADER) });

        try {
            webServiceTemplate.setMessageSender(this.webServiceMessageSender(organisationIdentification));
        } catch (GeneralSecurityException | IOException e) {
            LOGGER.error("Webservice exception occurred: Certificate not available", e);
            throw new WebServiceSecurityException("Certificate not available", e);
        }

        return webServiceTemplate;
    }

    private HttpComponentsMessageSender webServiceMessageSender(final String keystore)
            throws GeneralSecurityException, IOException {

        // Open keystore, assuming same identity
        final KeyStoreFactoryBean keyStoreFactory = new KeyStoreFactoryBean();
        keyStoreFactory.setType(this.keyStoreType);
        keyStoreFactory.setLocation(new FileSystemResource(this.keyStoreLocation + "/" + keystore + ".pfx"));
        keyStoreFactory.setPassword(this.keyStorePassword);
        keyStoreFactory.afterPropertiesSet();

        final KeyStore keyStore = keyStoreFactory.getObject();
        if ((keyStore == null) || (keyStore.size() == 0)) {
            throw new KeyStoreException("Key store is empty");
        }

        // Setup SSL context, load trust and keystore and build the message
        // sender
        final SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, this.keyStorePassword.toCharArray())
                .loadTrustMaterial(this.trustStoreFactory.getObject(), new TrustSelfSignedStrategy()).build();

        final HttpClientBuilder clientbuilder = HttpClientBuilder.create();
        final SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        clientbuilder.setSSLSocketFactory(connectionFactory);

        clientbuilder.setMaxConnPerRoute(this.maxConnectionsPerRoute);
        clientbuilder.setMaxConnTotal(this.maxConnectionsTotal);

        // Add intercepter to prevent issue with duplicate headers.
        // See also:
        // http://forum.spring.io/forum/spring-projects/web-services/118857-spring-ws-2-1-4-0-httpclient-proxy-content-length-header-already-present
        clientbuilder.addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor());

        return new HttpComponentsMessageSender(clientbuilder.build());
    }
}