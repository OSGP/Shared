/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.shared.application.mapping;

import java.awt.Color;

public enum ColorConverter {
    NULL(0, 0, 0),
    AMBER(255, 215, 36),
    BLUE(4, 25, 184),
    GREEN(111, 240, 5),
    ORANGE(255, 145, 36),
    PURPLE(115, 4, 184),
    RED(245, 5, 5),
    TEAL(8, 156, 138),
    VIOLET(185, 5, 245),
    YELLOW(255, 255, 0),
    YELLOWGREEN(148, 191, 113);

    private final int red, green, blue;

    ColorConverter(final int red, final int green, final int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public Color getColor() {
        return new Color(this.red, this.green, this.blue);
    }

    public Color getColorByName(final String name) {
        for (final ColorConverter converter : values()) {
            if (name.equalsIgnoreCase(converter.name())) {
                return converter.getColor();
            }
        }
        return null;
    }
}
