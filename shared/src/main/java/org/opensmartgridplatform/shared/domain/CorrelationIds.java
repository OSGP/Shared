package org.opensmartgridplatform.shared.domain;

/**
 * Set of ID's used to track things like device operations passing through the platform,
 * i.e. the organisation identification, the device identification and the correlation UID.
 */
public class CorrelationIds {
    private final String organisationIdentification;
    private final String deviceIdentification;
    private final String correlationUid;

    public CorrelationIds(final String organisationIdentification, final String deviceIdentification,
            final String correlationUid) {
        this.organisationIdentification = organisationIdentification;
        this.deviceIdentification = deviceIdentification;
        this.correlationUid = correlationUid;
    }

    public String getOrganisationIdentification() {
        return organisationIdentification;
    }

    public String getDeviceIdentification() {
        return deviceIdentification;
    }

    public String getCorrelationUid() {
        return correlationUid;
    }


}
