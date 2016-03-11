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
 * request actual reads for E or GAS meters
 *
 * @author dev
 */
public class ActualMeterReadsQueryDto implements Serializable, ChannelQueryDto {
    private static final long serialVersionUID = 3751586818507193990L;

    private final ChannelDto channel;

    public ActualMeterReadsQueryDto() {
        this(ChannelDto.NONE);
    }

    public ActualMeterReadsQueryDto(final ChannelDto channel) {
        this.channel = channel;
    }

    public boolean isGas() {
        return this.channel != ChannelDto.NONE;
    }

    @Override
    public ChannelDto getChannel() {
        return this.channel;
    }

}
