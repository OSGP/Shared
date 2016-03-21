package com.alliander.osgp.dto.valueobjects.smartmetering;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A metervalue with scaler applied together with its unit on the meter
 *
 *
 */
public class DlmsMeterValueDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public DlmsMeterValueDto(final BigDecimal value, final DlmsUnitDto dlmsUnit) {
        this.value = value;
        this.dlmsUnit = dlmsUnit;
    }

    private final BigDecimal value;
    private final DlmsUnitDto dlmsUnit;

    public BigDecimal getValue() {
        return this.value;
    }

    public DlmsUnitDto getDlmsUnit() {
        return this.dlmsUnit;
    }

    @Override
    public String toString() {
        return "DlmsMeterValue [value=" + this.value + ", dlmsUnit=" + this.dlmsUnit + "]";
    }

}