/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import java.util.BitSet;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.ui.graphical.figures.OverlayLabel;
import org.eclipse.sirius.diagram.ui.graphical.figures.OverlayLabelsDrawerFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;

/**
 * Specific {@link GradientRoundedRectangle} able to use a different dimension for chosen corners.
 * 
 * This is currently used to render region, and will always be configured to have outline == false.
 * 
 * fillShape(Graphics) draws the chosen corners with the additional dimension and the other ones with the main corner
 * dimension.
 * 
 * outlineShape(Graphics) currently calls super.outlineShape() and will draw the outline corresponding to a standard
 * RoundedCorner with the same corner dimension.
 * 
 * outlineShape(Graphics) will never be called as setOutline will do nothing and the outline is set to false
 * 
 * @author mporhel
 */
public class RegionRoundedGradientRectangle extends GradientRoundedRectangle {

    private final Dimension secondaryCorner = new Dimension(0, 0);

    private BitSet positions = new BitSet(PositionConstants.NSEW);

    /**
     * Create a new {@link RegionRoundedGradientRectangle}.
     * 
     * @param dimension
     *            dimension of the corner (with radius, height radius)
     * @param backgroundStyle
     *            style of the wanted gradient
     * @param view
     *            the model view of the part showing the figure.
     * @param useOverlayLabel
     *            true to use an {@link OverlayLabel} as label, false otherwise (see javadoc of
     *            {@link OverlayLabelsDrawerFigure} to see how this kind of figure is managed).
     */
    public RegionRoundedGradientRectangle(final Dimension dimension, final BackgroundStyle backgroundStyle, View view, boolean useOverlayLabel) {
        super(dimension, backgroundStyle.getValue(), view, useOverlayLabel);
        setOutline(false);
    }

    /**
     * Create a new {@link RegionRoundedGradientRectangle}.
     * 
     * @param dimension
     *            dimension of the corner (with radius, height radius)
     * @param backgroundStyle
     *            style of the wanted gradient
     * @param view
     *            the model view of the part showing the figure.
     */
    public RegionRoundedGradientRectangle(final Dimension dimension, final BackgroundStyle backgroundStyle, View view) {
        this(dimension, backgroundStyle, view, false);
    }

    /**
     * The outline of the shape is disabled for regions which have a specific border figure.
     */
    @Override
    public void setOutline(boolean b) {
        super.setOutline(false);
    }

    /**
     * Sets the dimensions of each corner. This will form the radii of the arcs which form the additional corners.
     * 
     * @param d
     *            the dimensions of the corner, it should be smaller than the main corner dimension to be visible.
     * 
     * @param chosenCorners
     *            the chosen corners, see values of the BitSet for the following indexes:
     *            {@link PositionConstants.NORTH_WEST}, {@link PositionConstants.NORTH_EAST},
     *            {@link PositionConstants.SOUTH_EAST}, {@link PositionConstants.SOUTH_WEST}.
     */
    public void setAdditionalCornerDimensions(Dimension d, BitSet chosenCorners) {
        positions.clear();
        if (chosenCorners != null) {
            positions.or(chosenCorners);
        }
        if (d == null) {
            secondaryCorner.setWidth(0);
            secondaryCorner.setHeight(0);
        } else {
            secondaryCorner.setWidth(d.width);
            secondaryCorner.setHeight(d.height);
        }
    }

    /**
     * Get the additional corner dimension.
     * 
     * @return the additional corner dimension
     */
    public Dimension getAdditionalCornerDimensions() {
        return secondaryCorner.getCopy();
    }

    /**
     * Return a {@link BitSet} knowing the chosen corner, see values of the BitSet for the following indexes:
     * {@link PositionConstants.NORTH_WEST}, {@link PositionConstants.NORTH_EAST}, {@link PositionConstants.SOUTH_EAST},
     * {@link PositionConstants.SOUTH_WEST}.
     * 
     * @return the positions of the corner to draw with the additional corner dimension.
     */
    public BitSet getAdditionalDimensionCorners() {
        return positions;
    }

    @Override
    protected void fillShape(Graphics graphics) {
        super.fillShape(graphics);

        if (positions.isEmpty()) {
            return;
        }

        if (positions.get(PositionConstants.NORTH_WEST)) {
            Rectangle corner = new Rectangle(bounds.getCenter(), bounds.getTopLeft());
            graphics.fillRoundRectangle(corner, secondaryCorner.width, secondaryCorner.height);
        }

        if (positions.get(PositionConstants.NORTH_EAST)) {
            Rectangle corner = new Rectangle(bounds.getCenter(), bounds.getTopRight());
            graphics.fillRoundRectangle(corner, secondaryCorner.width, secondaryCorner.height);
        }

        if (positions.get(PositionConstants.SOUTH_EAST)) {
            Rectangle corner = new Rectangle(bounds.getCenter(), bounds.getBottomRight());
            graphics.fillRoundRectangle(corner, secondaryCorner.width, secondaryCorner.height);
        }

        if (positions.get(PositionConstants.SOUTH_WEST)) {
            Rectangle corner = new Rectangle(bounds.getCenter(), bounds.getBottomLeft());
            graphics.fillRoundRectangle(corner, secondaryCorner.width, secondaryCorner.height);
        }
    }
}
