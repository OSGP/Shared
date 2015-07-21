/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.shared.usermanagement;

public class RemoveOrganisationRequest {

    private String organisationIdentification;

    public RemoveOrganisationRequest() {

    }

    public RemoveOrganisationRequest(final String organisationIdentification) {
        this.organisationIdentification = organisationIdentification;
    }

    public String getOrganisationIdentification() {
        return this.organisationIdentification;
    }
}
