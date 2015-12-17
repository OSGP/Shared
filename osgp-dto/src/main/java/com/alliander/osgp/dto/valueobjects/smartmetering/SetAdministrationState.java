/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.dto.valueobjects.smartmetering;

import java.io.Serializable;

public class SetAdministrationState implements Serializable {

    private static final long serialVersionUID = 9204064540343962380L;

    private AdministrativeStatusType status;

    private final String deviceIdentification;

    public SetAdministrationState(final AdministrativeStatusType status, final String deviceIdentification) {
        this.status = status;
        this.deviceIdentification = deviceIdentification;
    }

    public AdministrativeStatusType getStatus() {
        return this.status;
    }

    public String getDeviceIdentification() {
        return this.deviceIdentification;
    }

}