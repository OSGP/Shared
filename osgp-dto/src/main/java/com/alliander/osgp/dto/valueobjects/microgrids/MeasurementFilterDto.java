package com.alliander.osgp.dto.valueobjects.microgrids;

import java.io.Serializable;

public class MeasurementFilterDto extends MeasurementIdentifierDto implements Serializable {

    private static final long serialVersionUID = -6058020706641320400L;

    private boolean all;

    public boolean isAll() {
        return this.all;
    }

    public void setAll(final boolean all) {
        this.all = all;
    }

}
