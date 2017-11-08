/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.dto.valueobjects;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;

public class ConfigurationDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 8359276160483972289L;

    private final LightTypeDto lightType;

    private final DaliConfigurationDto daliConfiguration;

    private final RelayConfigurationDto relayConfiguration;

    private final Integer shortTermHistoryIntervalMinutes;

    private final Integer longTermHistoryInterval;

    private final LongTermIntervalTypeDto longTermHistoryIntervalType;

    private final LinkTypeDto preferredLinkType;

    private final MeterTypeDto meterType;

    private Integer timeSyncFrequency;

    private DeviceFixedIpDto deviceFixedIp;

    private Boolean dhcpEnabled;

    private Boolean tlsEnabled;

    private Integer tlsPortNumber;

    private String commonNameString;

    private Integer communicationTimeout;

    private Integer communicationNumberOfRetries;

    private Integer communicationPauseTimeBetweenConnectionTrials;

    private String osgpIpAddress;

    private Integer osgpPortNumber;

    private String ntpIpAddress;

    private Boolean ntpEnabled;

    private Integer ntpSyncInterval;

    private Boolean testButtonEnabled;

    private Boolean automaticSummerTimingEnabled;

    private Integer astroGateSunRiseOffset;

    private Integer astroGateSunSetOffset;

    private List<Integer> switchingDelays;

    private List<RelayMatrixDto> relayLinking;

    private Boolean relayRefreshing;

    private DateTime summerTimeDetails;

    private DateTime winterTimeDetails;

    public ConfigurationDto(final LightTypeDto lightType, final DaliConfigurationDto daliConfiguration,
            final RelayConfigurationDto relayConfiguration, final Integer shortTermHistoryIntervalMinutes,
            final LinkTypeDto preferredLinkType, final MeterTypeDto meterType, final Integer longTermHistoryInterval,
            final LongTermIntervalTypeDto longTermHistoryIntervalType) {
        this.lightType = lightType;
        this.daliConfiguration = daliConfiguration;
        this.relayConfiguration = relayConfiguration;
        this.shortTermHistoryIntervalMinutes = shortTermHistoryIntervalMinutes;
        this.preferredLinkType = preferredLinkType;
        this.meterType = meterType;
        this.longTermHistoryInterval = longTermHistoryInterval;
        this.longTermHistoryIntervalType = longTermHistoryIntervalType;
    }

    public MeterTypeDto getMeterType() {
        return this.meterType;
    }

    public LightTypeDto getLightType() {
        return this.lightType;
    }

    public DaliConfigurationDto getDaliConfiguration() {
        return this.daliConfiguration;
    }

    public RelayConfigurationDto getRelayConfiguration() {
        return this.relayConfiguration;
    }

    public Integer getShortTermHistoryIntervalMinutes() {
        return this.shortTermHistoryIntervalMinutes;
    }

    public LinkTypeDto getPreferredLinkType() {
        return this.preferredLinkType;
    }

    public Integer getLongTermHistoryInterval() {
        return this.longTermHistoryInterval;
    }

    public LongTermIntervalTypeDto getLongTermHistoryIntervalType() {
        return this.longTermHistoryIntervalType;
    }

    public Integer getTimeSyncFrequency() {
        return this.timeSyncFrequency;
    }

    public void setTimeSyncFrequency(final Integer timeSyncFrequency) {
        this.timeSyncFrequency = timeSyncFrequency;
    }

    public DeviceFixedIpDto getDeviceFixedIp() {
        return this.deviceFixedIp;
    }

    public void setDeviceFixedIp(final DeviceFixedIpDto deviceFixedIp) {
        this.deviceFixedIp = deviceFixedIp;
    }

    public Boolean isDhcpEnabled() {
        return this.dhcpEnabled;
    }

    public void setDhcpEnabled(final Boolean dhcpEnabled) {
        this.dhcpEnabled = dhcpEnabled;
    }

    public Boolean isTlsEnabled() {
        return this.tlsEnabled;
    }

    public void setTlsEnabled(final Boolean tlsEnabled) {
        this.tlsEnabled = tlsEnabled;
    }

    public Integer getTlsPortNumber() {
        return this.tlsPortNumber;
    }

    public void setTlsPortNumber(final Integer tlsPortNumber) {
        this.tlsPortNumber = tlsPortNumber;
    }

    public String getCommonNameString() {
        return this.commonNameString;
    }

    public void setCommonNameString(final String commonNameString) {
        this.commonNameString = commonNameString;
    }

    public Integer getCommunicationTimeout() {
        return this.communicationTimeout;
    }

    public void setCommunicationTimeout(final Integer communicationTimeout) {
        this.communicationTimeout = communicationTimeout;
    }

    public Integer getCommunicationNumberOfRetries() {
        return this.communicationNumberOfRetries;
    }

    public void setCommunicationNumberOfRetries(final Integer communicationNumberOfRetries) {
        this.communicationNumberOfRetries = communicationNumberOfRetries;
    }

    public Integer getCommunicationPauseTimeBetweenConnectionTrials() {
        return this.communicationPauseTimeBetweenConnectionTrials;
    }

    public void setCommunicationPauseTimeBetweenConnectionTrials(
            final Integer communicationPauseTimeBetweenConnectionTrials) {
        this.communicationPauseTimeBetweenConnectionTrials = communicationPauseTimeBetweenConnectionTrials;
    }

    public String getOsgpIpAddres() {
        return this.osgpIpAddress;
    }

    public void setOsgpIpAddress(final String osgpIpAddress) {
        this.osgpIpAddress = osgpIpAddress;
    }

    public Integer getOsgpPortNumber() {
        return this.osgpPortNumber;
    }

    public void setOsgpPortNumber(final Integer osgpPortNumber) {
        this.osgpPortNumber = osgpPortNumber;
    }

    public String getNtpIpAddress() {
        return this.ntpIpAddress;
    }

    public void setNtpIpAddress(final String ntpIpAddress) {
        this.ntpIpAddress = ntpIpAddress;
    }

    public Boolean getNtpEnabled() {
        return this.ntpEnabled;
    }

    public void setNtpEnabled(final Boolean ntpEnabled) {
        this.ntpEnabled = ntpEnabled;
    }

    public Integer getNtpSyncInterval() {
        return this.ntpSyncInterval;
    }

    public void setNtpSyncInterval(final Integer ntpSyncInterval) {
        this.ntpSyncInterval = ntpSyncInterval;
    }

    public Boolean isTestButtonEnabled() {
        return this.testButtonEnabled;
    }

    public void setTestButtonEnabled(final Boolean testButtonEnabled) {
        this.testButtonEnabled = testButtonEnabled;
    }

    public Boolean isAutomaticSummerTimingEnabled() {
        return this.automaticSummerTimingEnabled;
    }

    public void setAutomaticSummerTimingEnabled(final Boolean automaticSummerTimingEnabled) {
        this.automaticSummerTimingEnabled = automaticSummerTimingEnabled;
    }

    public void setRelayLinking(final List<RelayMatrixDto> relayLinking) {
        this.relayLinking = relayLinking;
    }

    public Integer getAstroGateSunRiseOffset() {
        return this.astroGateSunRiseOffset;
    }

    public void setAstroGateSunRiseOffset(final Integer astroGateSunRiseOffset) {
        this.astroGateSunRiseOffset = astroGateSunRiseOffset;
    }

    public Integer getAstroGateSunSetOffset() {
        return this.astroGateSunSetOffset;
    }

    public void setAstroGateSunSetOffset(final Integer astroGateSunSetOffset) {
        this.astroGateSunSetOffset = astroGateSunSetOffset;
    }

    public Boolean isRelayRefreshing() {
        return this.relayRefreshing;
    }

    public void setRelayRefreshing(final Boolean relayRefreshing) {
        this.relayRefreshing = relayRefreshing;
    }

    public List<Integer> getSwitchingDelays() {
        return this.switchingDelays;
    }

    public void setSwitchingDelays(final List<Integer> switchingDelays) {
        this.switchingDelays = switchingDelays;
    }

    public List<RelayMatrixDto> getRelayLinking() {
        return this.relayLinking;
    }

    public DateTime getSummerTimeDetails() {
        return this.summerTimeDetails;
    }

    public void setSummerTimeDetails(final DateTime summerTimeDetails) {
        this.summerTimeDetails = summerTimeDetails;
    }

    public DateTime getWinterTimeDetails() {
        return this.winterTimeDetails;
    }

    public void setWinterTimeDetails(final DateTime winterTimeDetails) {
        this.winterTimeDetails = winterTimeDetails;
    }
}
