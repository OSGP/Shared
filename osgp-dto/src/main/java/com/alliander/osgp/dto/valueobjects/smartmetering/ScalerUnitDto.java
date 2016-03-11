/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.dto.valueobjects.smartmetering;

import java.io.Serializable;

/**
 * response for {@link ScalerUnitQuery scaler and unit query} for E or GAS
 * meters. Note that this is the scaler and unit as used on the device, not the
 * standardized unit for the outside world. For the latter the Platform knows an
 * OsgpUnit.
 *
 */
public class ScalerUnitDto implements Serializable {
    private static final long serialVersionUID = 3751586818507193990L;

    private final DlmsUnitDto dlmsUnit;
    private final int scaler;

    public ScalerUnitDto(final DlmsUnitDto dlmsUnit, final int scaler) {
        this.dlmsUnit = dlmsUnit;
        this.scaler = scaler;
    }

    public DlmsUnitDto getDlmsUnit() {
        return this.dlmsUnit;
    }

    public int getScaler() {
        return this.scaler;
    }

    @Override
    public String toString() {
        return "ScalerUnit [dlmsUnit=" + this.dlmsUnit + ", scaler=" + this.scaler + "]";
    }

}
