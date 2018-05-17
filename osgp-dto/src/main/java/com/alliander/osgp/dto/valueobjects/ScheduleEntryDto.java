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

public class ScheduleEntryDto implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 7562344341386762082L;

    private WeekDayTypeDto weekDay;

    private DateTime startDay;

    private DateTime endDay;

    private ActionTimeTypeDto actionTime;

    private String time;

    private WindowTypeDto triggerWindow;

    private List<LightValueDto> lightValue;

    private TriggerTypeDto triggerType;

    private Integer index;

    private Boolean isEnabled;

    private Integer minimumLightsOn;

    public WeekDayTypeDto getWeekDay() {
        return this.weekDay;
    }

    public void setWeekDay(final WeekDayTypeDto value) {
        this.weekDay = value;
    }

    public void setStartDay(final DateTime value) {
        this.startDay = value;
    }

    public DateTime getStartDay() {
        return this.startDay;
    }

    public void setEndDay(final DateTime value) {
        this.endDay = value;
    }

    public DateTime getEndDay() {
        return this.endDay;
    }

    public void setActionTime(final ActionTimeTypeDto value) {
        this.actionTime = value;
    }

    public ActionTimeTypeDto getActionTime() {
        return this.actionTime;
    }

    public void setTime(final String value) {
        this.time = value;
    }

    public String getTime() {
        return this.time;
    }

    public void setTriggerWindow(final WindowTypeDto value) {
        this.triggerWindow = value;
    }

    public WindowTypeDto getTriggerWindow() {
        return this.triggerWindow;
    }

    public void setLightValue(final List<LightValueDto> value) {
        this.lightValue = value;
    }

    public List<LightValueDto> getLightValue() {
        return this.lightValue;
    }

    public void setTriggerType(final TriggerTypeDto triggerType) {
        this.triggerType = triggerType;
    }

    public TriggerTypeDto getTriggerType() {
        return this.triggerType;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(final Integer index) {
        this.index = index;
    }

    public void setIsEnabled(final Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public Integer getMinimumLightsOn() {
        return this.minimumLightsOn;
    }

    public void setMinimumLightsOn(final Integer minimumLightsOn) {
        this.minimumLightsOn = minimumLightsOn;
    }
}