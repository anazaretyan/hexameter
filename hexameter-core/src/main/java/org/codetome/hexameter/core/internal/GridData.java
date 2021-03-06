package org.codetome.hexameter.core.internal;

import lombok.Getter;
import org.codetome.hexameter.core.api.Hexagon;
import org.codetome.hexameter.core.api.HexagonOrientation;
import org.codetome.hexameter.core.api.HexagonalGrid;
import org.codetome.hexameter.core.api.HexagonalGridLayout;

import static java.lang.Math.sqrt;
import static org.codetome.hexameter.core.api.HexagonOrientation.FLAT_TOP;

/**
 * Immutable class which holds the shared data between the {@link Hexagon}s of a {@link HexagonalGrid}
 * and the HexagonalGrid's own immutable properties.
 */
@Getter
@SuppressWarnings("PMD.UnusedPrivateField")
public final class GridData {

    private final HexagonOrientation orientation;
    private final HexagonalGridLayout gridLayout;
    private final double radius;
    private final double hexagonHeight;
    private final double hexagonWidth;
    private final int gridWidth;
    private final int gridHeight;

    /**
     * Creates a new GridData based on <code>orientation</code> and <code>radius</code>.
     *
     * @param orientation orientation
     * @param gridLayout grid layout
     * @param radius radius
     * @param gridWidth grid width (units)
     * @param gridHeight grid height (units)
     */
    public GridData(final HexagonOrientation orientation, final HexagonalGridLayout gridLayout,
                    final double radius, int gridWidth, int gridHeight) {
        this.orientation = orientation;
        this.gridLayout = gridLayout;
        this.radius = radius;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.hexagonHeight = FLAT_TOP.equals(orientation) ? calculateHeight(radius) : calculateWidth(radius);
        this.hexagonWidth = FLAT_TOP.equals(orientation) ? calculateWidth(radius) : calculateHeight(radius);
    }

    private static double calculateHeight(final double radius) {
        return sqrt(3) * radius;
    }

    private static double calculateWidth(final double radius) {
        return radius * 3 / 2;
    }
}
