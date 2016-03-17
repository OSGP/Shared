/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.dto.valueobjects.smartmetering;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class WeekProfileDto implements Comparable<WeekProfileDto>, Serializable {

    private static final long serialVersionUID = 2838240604182800624L;

    private String weekProfileName;

    private DayProfileDto monday;

    private DayProfileDto tuesday;

    private DayProfileDto wednesday;

    private DayProfileDto thursday;

    private DayProfileDto friday;

    private DayProfileDto saturday;

    private DayProfileDto sunday;

    public WeekProfileDto(final String weekProfileName, final DayProfileDto monday, final DayProfileDto tuesday,
            final DayProfileDto wednesday, final DayProfileDto thursday, final DayProfileDto friday,
            final DayProfileDto saturday, final DayProfileDto sunday) {
        this.weekProfileName = weekProfileName;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public List<DayProfileDto> getAllDaysAsList() {
        return Arrays.asList(this.monday, this.tuesday, this.wednesday, this.thursday, this.friday, this.saturday,
                this.sunday);
    }

    public String getWeekProfileName() {
        return this.weekProfileName;
    }

    public DayProfileDto getMonday() {
        return this.monday;
    }

    public DayProfileDto getTuesday() {
        return this.tuesday;
    }

    public DayProfileDto getWednesday() {
        return this.wednesday;
    }

    public DayProfileDto getThursday() {
        return this.thursday;
    }

    public DayProfileDto getFriday() {
        return this.friday;
    }

    public DayProfileDto getSaturday() {
        return this.saturday;
    }

    public DayProfileDto getSunday() {
        return this.sunday;
    }

    @Override
    public String toString() {
        return "WeekProfile [\n\t\t\t\t\t weekProfileName=" + this.weekProfileName + ", \n\t\t\t\t\t monday="
                + this.monday + ", \n\t\t\t\t\t tuesday=" + this.tuesday + ", \n\t\t\t\t\t wednesday=" + this.wednesday
                + ", \n\t\t\t\t\t thursday=" + this.thursday + ", \n\t\t\t\t\t friday=" + this.friday
                + ", \n\t\t\t\t\t saturday=" + this.saturday + ", \n\t\t\t\t\t sunday=" + this.sunday + "\n\t\t\t\t]";
    }

    @Override
    public int compareTo(final WeekProfileDto other) {
        return this.weekProfileName.compareTo(other.weekProfileName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.friday.hashCode();
        result = prime * result + this.monday.hashCode();
        result = prime * result + this.saturday.hashCode();
        result = prime * result + this.sunday.hashCode();
        result = prime * result + this.thursday.hashCode();
        result = prime * result + this.tuesday.hashCode();
        result = prime * result + this.wednesday.hashCode();
        result = prime * result + this.weekProfileName.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final WeekProfileDto other = (WeekProfileDto) obj;
        if (!this.friday.equals(other.friday)) {
            return false;
        }
        if (!this.monday.equals(other.monday)) {
            return false;
        }
        if (!this.saturday.equals(other.saturday)) {
            return false;
        }
        if (!this.sunday.equals(other.sunday)) {
            return false;
        }
        if (!this.thursday.equals(other.thursday)) {
            return false;
        }
        if (!this.tuesday.equals(other.tuesday)) {
            return false;
        }
        if (!this.wednesday.equals(other.wednesday)) {
            return false;
        }
        if (!this.weekProfileName.equals(other.weekProfileName)) {
            return false;
        }
        return true;
    }
}
