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

public class MeterReads implements Serializable {
    private static final long serialVersionUID = -297320204916085999L;

    private final Date logTime;

    private final DlmsMeterValue activeEnergyImport;
    private final DlmsMeterValue activeEnergyExport;
    private final DlmsMeterValue activeEnergyImportTariffOne;
    // may be null
    private final DlmsMeterValue activeEnergyImportTariffTwo;
    private final DlmsMeterValue activeEnergyExportTariffOne;
    // may be null
    private final DlmsMeterValue activeEnergyExportTariffTwo;

    public MeterReads(final Date logTime, final DlmsMeterValue activeEnergyImport,
            final DlmsMeterValue activeEnergyExport, final DlmsMeterValue activeEnergyImportTariffOne,
            final DlmsMeterValue activeEnergyImportTariffTwo, final DlmsMeterValue activeEnergyExportTariffOne,
            final DlmsMeterValue activeEnergyExportTariffTwo) {
        super();
        this.logTime = new Date(logTime.getTime());
        this.activeEnergyImportTariffOne = activeEnergyImportTariffOne;
        this.activeEnergyImportTariffTwo = activeEnergyImportTariffTwo;
        this.activeEnergyExportTariffOne = activeEnergyExportTariffOne;
        this.activeEnergyExportTariffTwo = activeEnergyExportTariffTwo;
        this.activeEnergyImport = activeEnergyImport;
        this.activeEnergyExport = activeEnergyExport;
    }

    public Date getLogTime() {
        return new Date(this.logTime.getTime());
    }

    public DlmsMeterValue getActiveEnergyImportTariffOne() {
        return this.activeEnergyImportTariffOne;
    }

    public DlmsMeterValue getActiveEnergyImportTariffTwo() {
        return this.activeEnergyImportTariffTwo;
    }

    public DlmsMeterValue getActiveEnergyExportTariffOne() {
        return this.activeEnergyExportTariffOne;
    }

    public DlmsMeterValue getActiveEnergyExportTariffTwo() {
        return this.activeEnergyExportTariffTwo;
    }

    public DlmsMeterValue getActiveEnergyImport() {
        return this.activeEnergyImport;
    }

    public DlmsMeterValue getActiveEnergyExport() {
        return this.activeEnergyExport;
    }

    @Override
    public String toString() {
        return "MeterReads [logTime=" + this.logTime + ", activeEnergyImport=" + this.activeEnergyImport
                + ", activeEnergyExport=" + this.activeEnergyExport + ", activeEnergyImportTariffOne="
                + this.activeEnergyImportTariffOne + ", activeEnergyImportTariffTwo="
                + this.activeEnergyImportTariffTwo + ", activeEnergyExportTariffOne="
                + this.activeEnergyExportTariffOne + ", activeEnergyExportTariffTwo="
                + this.activeEnergyExportTariffTwo + "]";
    }

}
