/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;

/**
 * An abstract ellipse figure to handle transparency.
 * 
 * @mporhel
 */
public abstract class AbstractTransparentEllipse extends Ellipse implements StyledFigure, ITransparentFigure {

    private int viewpointAlpha = DEFAULT_ALPHA;

    private boolean transparent;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillShape(Graphics graphics) {
        TransparentFigureGraphicsModifier modifier = new TransparentFigureGraphicsModifier(this, graphics);
        modifier.pushState();
        super.fillShape(graphics);
        modifier.popState();
    }

    /**
     * {@inheritDoc}
     */
    public int getSiriusAlpha() {
        return viewpointAlpha;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTransparent() {
        return transparent;
    }

    /**
     * {@inheritDoc}
     */
    public void setSiriusAlpha(int alpha) {
        this.viewpointAlpha = alpha;
    }

    /**
     * {@inheritDoc}
     */
    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

}
