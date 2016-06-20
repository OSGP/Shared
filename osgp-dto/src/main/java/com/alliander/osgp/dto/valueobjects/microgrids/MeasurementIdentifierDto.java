package com.alliander.osgp.dto.valueobjects.microgrids;

import java.io.Serializable;

public class MeasurementIdentifierDto implements Serializable {

    private static final long serialVersionUID = 5587798706867134143L;

    private int id;
    private String node;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(final String node) {
        this.node = node;
    }
}
