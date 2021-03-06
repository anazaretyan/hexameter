package org.codetome.hexameter.core.api;

import static org.codetome.hexameter.core.api.HexagonOrientation.FLAT_TOP;

/**
 * Utility class for converting coordinated from the offset system to
 * the axial one (the library uses the latter).
 */
public final class CoordinateConverter {

    public CoordinateConverter() {
        throw new UnsupportedOperationException("This utility class is not meant to be instantiated.");
    }

    /**
     * Calculates the axial X coordinate based on an offset coordinate pair.
     * @param offsetX offset x
     * @param offsetY offset y
     * @param orientation orientation
     * @return axial x
     */
    public static int convertOffsetCoordinatesToAxialX(final int offsetX, final int offsetY, final HexagonOrientation orientation) {
        return FLAT_TOP.equals(orientation) ? offsetX : offsetX - offsetY / 2;
    }

    /**
     * Calculates the axial Z coordinate based on an offset coordinate pair.
     * @param offsetX offset x
     * @param offsetY offset y
     * @param orientation orientation
     * @return axial z
     */
    public static int convertOffsetCoordinatesToAxialZ(final int offsetX, final int offsetY, final HexagonOrientation orientation) {
        return FLAT_TOP.equals(orientation) ? offsetY - offsetX / 2 : offsetY;
    }

}
