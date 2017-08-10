/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.dto.valueobjects.microgrids;

import java.io.Serializable;

import org.joda.time.DateTime;

public class PhaseDto extends PhaseIdentifierDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5981586929069166582L;

    private final int qualifier;
    private final DateTime time;
    private final double value;

    public PhaseDto(final int id, final String name, final int qualifier, final DateTime time, final double value) {
        super(id, name);
        this.qualifier = qualifier;
        this.time = time;
        this.value = value;
    }

    public int getQualifier() {
        return this.qualifier;
    }

    public DateTime getTime() {
        return this.time;
    }

    public double getValue() {
        return this.value;
    }

}
