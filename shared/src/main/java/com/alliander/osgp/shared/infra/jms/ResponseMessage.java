/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.shared.infra.jms;

import java.io.Serializable;

import com.alliander.osgp.shared.exceptionhandling.OsgpException;
import com.alliander.osgp.shared.wsheaderattribute.priority.MessagePriorityEnum;

public class ResponseMessage implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -214808702310700742L;

    protected static final boolean DEFAULT_BYPASS_RETRY = false;

    private final String correlationUid;
    private final String organisationIdentification;
    private final String deviceIdentification;
    private final ResponseMessageResultType result;
    private final OsgpException osgpException;
    private final Serializable dataObject;
    private final int messagePriority;
    private final boolean bypassRetry;

    protected ResponseMessage(final Builder builder) {
        this.correlationUid = builder.correlationUid;
        this.organisationIdentification = builder.organisationIdentification;
        this.deviceIdentification = builder.deviceIdentification;
        this.result = builder.result;
        this.osgpException = builder.osgpException;
        this.dataObject = builder.dataObject;
        this.messagePriority = builder.messagePriority;
        this.bypassRetry = builder.bypassRetry;
    }

    public static class Builder {

        private String correlationUid = null;
        private String organisationIdentification = null;
        private String deviceIdentification = null;
        private ResponseMessageResultType result = null;
        private OsgpException osgpException = null;
        private Serializable dataObject = null;
        private int messagePriority = MessagePriorityEnum.DEFAULT.getPriority();
        private boolean bypassRetry = DEFAULT_BYPASS_RETRY;

        public Builder withCorrelationUid(final String correlationUid) {
            this.correlationUid = correlationUid;
            return this;
        }

        public Builder withOrganisationIdentification(final String organisationIdentification) {
            this.organisationIdentification = organisationIdentification;
            return this;
        }

        public Builder withDeviceIdentification(final String deviceIdentification) {
            this.deviceIdentification = deviceIdentification;
            return this;
        }

        public Builder withResult(final ResponseMessageResultType result) {
            this.result = result;
            return this;
        }

        public Builder withOsgpException(final OsgpException osgpException) {
            this.osgpException = osgpException;
            return this;
        }

        public Builder withDataObject(final Serializable dataObject) {
            this.dataObject = dataObject;
            return this;
        }

        public Builder withMessagePriority(final int messagePriority) {
            this.messagePriority = messagePriority;
            return this;
        }

        public Builder withBypassRetry(final boolean bypassRetry) {
            this.bypassRetry = bypassRetry;
            return this;
        }

        public Builder withDeviceMessageMetadata(final DeviceMessageMetadata deviceMessageMetadata) {
            this.correlationUid = deviceMessageMetadata.getCorrelationUid();
            this.organisationIdentification = deviceMessageMetadata.getOrganisationIdentification();
            this.deviceIdentification = deviceMessageMetadata.getDeviceIdentification();
            this.messagePriority = deviceMessageMetadata.getMessagePriority();
            this.bypassRetry = deviceMessageMetadata.bypassRetry();
            return this;
        }

        public ResponseMessage build() {
            return new ResponseMessage(this);
        }
    }

    public static Builder newResponseMessageBuilder() {
        return new Builder();
    }

    public String getCorrelationUid() {
        return this.correlationUid;
    }

    public String getOrganisationIdentification() {
        return this.organisationIdentification;
    }

    public String getDeviceIdentification() {
        return this.deviceIdentification;
    }

    public ResponseMessageResultType getResult() {
        return this.result;
    }

    public OsgpException getOsgpException() {
        return this.osgpException;
    }

    public Serializable getDataObject() {
        return this.dataObject;
    }

    public int getMessagePriority() {
        return this.messagePriority;
    }

    public boolean bypassRetry() {
        return this.bypassRetry;
    }

}
