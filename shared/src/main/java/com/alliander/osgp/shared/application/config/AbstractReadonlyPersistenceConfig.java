/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.shared.application.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;

import com.alliander.osgp.shared.infra.db.DefaultConnectionPoolFactory;

public abstract class AbstractReadonlyPersistenceConfig extends AbstractPersistenceConfig {

    protected AbstractReadonlyPersistenceConfig(final String persistenceUnitName) {
        super(persistenceUnitName);
    }

    @Value("${db.readonly.username:osp_admin}")
    private String username;

    @Value("${db.readonly.password:1234}")
    private String password;

    @Override
    protected String getUsername() {
        return this.username;
    }

    @Override
    protected String getPassword() {
        return this.password;
    }

    /**
     * Method for creating the Data Source.
     *
     * @return DataSource
     */
    @Override
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
}
