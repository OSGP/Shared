/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.dto.valueobjects.smartmetering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PeriodicMeterReadsContainerDto implements Serializable, ScalerUnitResponseDto {

    private static final long serialVersionUID = -156966569210717654L;

    private final List<PeriodicMeterReadsDto> meterReads;
    private final PeriodTypeDto periodType;
    private final ScalerUnitDto scalerUnit;

    public PeriodicMeterReadsContainerDto(final PeriodTypeDto periodType, final List<PeriodicMeterReadsDto> meterReads,
            final ScalerUnitDto scalerUnit) {
        this.meterReads = new ArrayList<PeriodicMeterReadsDto>(meterReads);
        this.periodType = periodType;
        this.scalerUnit = new ScalerUnitDto(scalerUnit.getDlmsUnit(), scalerUnit.getScaler());
    }

    public List<PeriodicMeterReadsDto> getMeterReads() {
        return Collections.unmodifiableList(this.meterReads);
    }

    public PeriodTypeDto getPeriodType() {
        return this.periodType;
    }

    @Override
    public ScalerUnitDto getScalerUnit() {
        return new ScalerUnitDto(this.scalerUnit.getDlmsUnit(), this.scalerUnit.getScaler());
    }

}
