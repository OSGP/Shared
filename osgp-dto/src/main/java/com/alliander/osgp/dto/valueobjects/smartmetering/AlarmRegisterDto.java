/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.dto.valueobjects.smartmetering;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class AlarmRegisterDto implements Serializable {

    private static final long serialVersionUID = 2319359505656305783L;

    private final Set<AlarmTypeDto> alarmTypes;

    public AlarmRegisterDto(final Set<AlarmTypeDto> alarmTypes) {
        this.alarmTypes = new TreeSet<AlarmTypeDto>(alarmTypes);
    }

    @Override
    public String toString() {
        return "AlarmTypes[" + this.alarmTypes + "]";
    }

    public Set<AlarmTypeDto> getAlarmTypes() {
        return new TreeSet<>(this.alarmTypes);
    }
}
