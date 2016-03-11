/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.dto.valueobjects.smartmetering;

import java.util.Date;

public class PeriodicMeterReadsDto extends MeterReadsDto {

    private static final long serialVersionUID = 2123390296585369208L;

    final AmrProfileStatusCodeDto amrProfileStatusCode;

    /**
     * Constructor taking all data. Use for conversion purposes, when all fields
     * need to be copied.
     *
     * @param logTime
     * @param activeEnergyImport
     * @param activeEnergyExport
     * @param activeEnergyImportTariffOne
     * @param activeEnergyImportTariffTwo
     * @param activeEnergyExportTariffOne
     * @param activeEnergyExportTariffTwo
     * @param amrProfileStatusCode
     */
    public PeriodicMeterReadsDto(final Date logTime, final Long activeEnergyImport, final Long activeEnergyExport,
            final Long activeEnergyImportTariffOne, final Long activeEnergyImportTariffTwo,
            final Long activeEnergyExportTariffOne, final Long activeEnergyExportTariffTwo,
            final AmrProfileStatusCodeDto amrProfileStatusCode) {
        super(logTime, activeEnergyImport, activeEnergyExport, activeEnergyImportTariffOne, activeEnergyImportTariffTwo,
                activeEnergyExportTariffOne, activeEnergyExportTariffTwo);

        this.amrProfileStatusCode = amrProfileStatusCode;
    }

    /**
     * Constructor for monthly reads. Does not hold a AMR profile status.
     *
     * @param logTime
     * @param activeEnergyImportTariffOne
     * @param activeEnergyImportTariffTwo
     * @param activeEnergyExportTariffOne
     * @param activeEnergyExportTariffTwo
     */
    public PeriodicMeterReadsDto(final Date logTime, final long activeEnergyImportTariffOne,
            final Long activeEnergyImportTariffTwo, final long activeEnergyExportTariffOne,
            final Long activeEnergyExportTariffTwo) {

        this(logTime, null, null, activeEnergyImportTariffOne, activeEnergyImportTariffTwo, activeEnergyExportTariffOne,
                activeEnergyExportTariffTwo, null);
    }

    /**
     * Constructor for daily reads. Holds tariff values and AMR profile status.
     *
     * @param logTime
     * @param activeEnergyImportTariffOne
     * @param activeEnergyImportTariffTwo
     * @param activeEnergyExportTariffOne
     * @param activeEnergyExportTariffTwo
     * @param amrProfileStatusCode
     */
    public PeriodicMeterReadsDto(final Date logTime, final long activeEnergyImportTariffOne,
            final long activeEnergyImportTariffTwo, final long activeEnergyExportTariffOne,
            final long activeEnergyExportTariffTwo, final AmrProfileStatusCodeDto amrProfileStatusCode) {
        this(logTime, null, null, activeEnergyImportTariffOne, activeEnergyImportTariffTwo, activeEnergyExportTariffOne,
                activeEnergyExportTariffTwo, amrProfileStatusCode);
    }

    /**
     * Constructor for interval reads.
     *
     * @param logTime
     * @param activeEnergyImport
     * @param activeEnergyExport
     * @param amrProfileStatusCode
     */
    public PeriodicMeterReadsDto(final Date logTime, final long activeEnergyImport, final long activeEnergyExport,
            final AmrProfileStatusCodeDto amrProfileStatusCode) {
        super(logTime, activeEnergyImport, activeEnergyExport, null, null, null, null);

        this.amrProfileStatusCode = amrProfileStatusCode;
    }

    public AmrProfileStatusCodeDto getAmrProfileStatusCode() {
        return this.amrProfileStatusCode;
    }

}
