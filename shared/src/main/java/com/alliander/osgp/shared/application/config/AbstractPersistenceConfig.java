/**
 * Copyright 2016 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.shared.application.config;

import java.util.Properties;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.alliander.osgp.shared.infra.db.DefaultConnectionPoolFactory;
import com.zaxxer.hikari.HikariDataSource;

public abstract class AbstractPersistenceConfig extends AbstractConfig {

    @Value("${db.username:osp_admin}")
    protected String username;

    @Value("${db.password:1234}")
    protected String password;

    @Value("${db.driver:org.postgresql.Driver}")
    private String driver;

    @Value("${db.protocol:jdbc:postgresql://}")
    private String protocol;

    @Value("${db.host:localhost}")
    private String host;

    @Value("${db.port:5432}")
    private int port;

    @Value("${db.name:osgp_core}")
    private String name;

    @Value("${db.min_pool_size:1}")
    private int minPoolSize;

    @Value("${db.max_pool_size:5}")
    private int maxPoolSize;

    @Value("${db.auto_commit:false}")
    private boolean autoCommit;

    @Value("${db.idle_timeout:120000}")
    private int idleTimeout;

    @Value("${hibernate.dialect:org.hibernate.dialect.PostgreSQLDialect}")
    private String hibernateDialect;

    @Value("${hibernate.format_sql:true}")
    private boolean hibernateFormatSql;

    @Value("${hibernate.ejb.naming_strategy:org.hibernate.cfg.ImprovedNamingStrategy}")
    private String hibernateEJBNamingStrategy;

    @Value("${hibernate.show_sql:false}")
    private boolean hibernateShowSql;

    @Value("${entitymanager.packages.to.scan}")
    private String entitymanagerPackagesToScan;

    protected String getUsername() {
        return this.username;
    }

    protected String getPassword() {
        return this.password;
    }

    protected String getDriver() {
        return this.driver;
    }

    protected String getProtocol() {
        return this.protocol;
    }

    protected String getHost() {
        return this.host;
    }

    protected int getPort() {
        return this.port;
    }

    protected String getName() {
        return this.name;
    }

    protected int getMinPoolSize() {
        return this.minPoolSize;
    }

    protected int getMaxPoolSize() {
        return this.maxPoolSize;
    }

    protected boolean getAutoCommit() {
        return this.autoCommit;
    }

    protected int getIdleTimeout() {
        return this.idleTimeout;
    }

    protected String getHibernateDialect() {
        return this.hibernateDialect;
    }

    protected boolean getHibernateFormatSql() {
        return this.hibernateFormatSql;
    }

    protected String getHibernateEJBNamingStrategy() {
        return this.hibernateEJBNamingStrategy;
    }

    protected boolean getHibernateShowSql() {
        return this.hibernateShowSql;
    }

    protected String getEntitymanagerPackagesToScan() {
        return this.entitymanagerPackagesToScan;
    }

    private String persistenceUnitName = "";

    protected AbstractPersistenceConfig() {
        this("");
    }

    protected AbstractPersistenceConfig(final String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    protected AbstractPersistenceConfig(final String persistenceUnitName, final String username,
            final String password) {
        this.persistenceUnitName = persistenceUnitName;
        this.username = this.environment.getProperty(username);
        this.password = this.environment.getProperty(password);
    }

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    protected HikariDataSource dataSource;

    protected Environment environment;

    /**
     * Method for creating the Data Source.
     *
     * @return DataSource
     */
    public DataSource getDataSource() {

        if (this.dataSource == null) {
            final DefaultConnectionPoolFactory.Builder builder = new DefaultConnectionPoolFactory.Builder()
                    .withUsername(this.getUsername()).withPassword(this.getPassword())
                    .withDriverClassName(this.getDriver()).withProtocol(this.getProtocol())
                    .withDatabaseHost(this.getHost()).withDatabasePort(this.getPort()).withDatabaseName(this.getName())
                    .withMinPoolSize(this.getMinPoolSize()).withMaxPoolSize(this.getMaxPoolSize())
                    .withAutoCommit(this.getAutoCommit()).withIdleTimeout(this.getIdleTimeout());
            final DefaultConnectionPoolFactory factory = builder.build();
            this.dataSource = factory.getDefaultConnectionPool();
        }
        return this.dataSource;
    }

    /**
     * Method for creating the Transaction Manager.
     *
     * @return JpaTransactionManager
     * @throws ClassNotFoundException
     *             when class not found
     */
    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());

        return transactionManager;
    }

    /**
     * Method for creating the Entity Manager Factory Bean.
     *
     * @return LocalContainerEntityManagerFactoryBean
     * @throws ClassNotFoundException
     *             when class not found
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setPersistenceUnitName(this.persistenceUnitName);
        entityManagerFactoryBean.setDataSource(this.getDataSource());
        entityManagerFactoryBean.setPackagesToScan(this.getEntitymanagerPackagesToScan());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);

        final Properties jpaProperties = new Properties();
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT, this.getHibernateDialect());
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, this.getHibernateFormatSql());
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, this.getHibernateEJBNamingStrategy());
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, this.getHibernateShowSql());

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @PreDestroy
    public void destroyDataSource() {
        if (this.dataSource != null) {
            this.dataSource.close();
        }
    }
}
