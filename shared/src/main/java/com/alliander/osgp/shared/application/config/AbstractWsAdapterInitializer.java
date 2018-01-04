/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.shared.application.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

/**
 * Web application Java configuration class.
 */
public abstract class AbstractWsAdapterInitializer extends AbstractApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "spring-ws";
    private static final String DISPATCHER_SERVLET_MAPPING = "/*";

    /**
     * Constructs instance of ApplicationInitializer specific for webservice
     * adapters.
     *
     * @param contextClass
     *            the class holding application specific Spring
     *            ApplicationContext
     * @param logConfig
     *            jndi property which points to logback configuration
     */
    public AbstractWsAdapterInitializer(final Class<?> contextClass, final String logConfig) {
        super(contextClass, logConfig);
    }

    /**
     * Default startup of application context for webservice adapters which: -
     * Performs default startup - Setup WSDL handling - Initialize
     * DispatchServlet
     *
     * @param servletContext
     *            Java servlet context as supplied by application server
     * @throws ServletException
     *             thrown when a servlet encounters difficulty
     */
    @Override
    protected void startUp(final ServletContext servletContext) throws ServletException {
        super.startUp(servletContext);

        final MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setContextClass(AnnotationConfigWebApplicationContext.class);
        servlet.setTransformWsdlLocations(true);

        final ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, servlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);
    }
}