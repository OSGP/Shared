/**
 * Copyright 2016 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.ws.loadmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.wsdl.WsdlDefinition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@Configuration
public class LoadManagementWebServiceConfig {

    private static final String COMMON_XSD_PATH = "schemas/common.xsd";
    private static final String LM_ADHOCMANAGEMENT_XSD_PATH = "schemas/lm-adhocmanagement.xsd";
    private static final String LM_ADHOC_MANAGEMENT_WSDL_PATH = "LoadManagementAdHocManagement.wsdl";

    @Bean
    public PayloadValidatingInterceptor payloadValidatingInterceptor() {
        final PayloadValidatingInterceptor payloadValidatingInterceptor = new PayloadValidatingInterceptor();
        final Resource[] resources = new Resource[] { new ClassPathResource(COMMON_XSD_PATH),
                new ClassPathResource(LM_ADHOCMANAGEMENT_XSD_PATH) };
        payloadValidatingInterceptor.setSchemas(resources);
        return payloadValidatingInterceptor;
    }

    @Bean(name = "common")
    public SimpleXsdSchema commonXsd() {
        return new SimpleXsdSchema(new ClassPathResource(COMMON_XSD_PATH));
    }

    @Bean(name = "LoadManagementAdHocManagement")
    public WsdlDefinition loadManagementAdHocManagementWsdl() {
        return new SimpleWsdl11Definition(new ClassPathResource(LM_ADHOC_MANAGEMENT_WSDL_PATH));
    }

    @Bean(name = "lm-adhocmanagement")
    public SimpleXsdSchema loadManagementAdHocManagementXsd() {
        return new SimpleXsdSchema(new ClassPathResource(LM_ADHOCMANAGEMENT_XSD_PATH));
    }
}
