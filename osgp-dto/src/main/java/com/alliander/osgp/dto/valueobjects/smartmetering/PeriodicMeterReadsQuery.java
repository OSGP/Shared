/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.dto.valueobjects.smartmetering;

import java.io.Serializable;
import java.util.Date;

/**
 * request periodic reads for E or GAS meter
 * 
 * @author dev
 */
public class PeriodicMeterReadsQuery implements Serializable {

    private static final long serialVersionUID = -2483665562035897062L;

    private final PeriodType periodType;
    private final Date beginDate;
    private final Date endDate;
    private final int channel;

    public PeriodicMeterReadsQuery(final PeriodType periodType, final Date beginDate, final Date endDate,
            final int channel) {
        this.periodType = periodType;
        this.beginDate = new Date(beginDate.getTime());
        this.endDate = new Date(endDate.getTime());
        this.channel = channel;
    }

    public PeriodicMeterReadsQuery(PeriodType periodType, Date beginDate, Date endDate) {
        this(periodType, beginDate, endDate, NOCHANNEL);
    }

    private static final int NOCHANNEL = -1;

    public PeriodType getPeriodType() {
        return this.periodType;
    }

    public Date getBeginDate() {
        return new Date(this.beginDate.getTime());
    }

    public Date getEndDate() {
        return new Date(this.endDate.getTime());
    }

    public boolean isGas() {
        return channel > NOCHANNEL;
    }

    public int getChannel() {
        return channel;
    }

}
