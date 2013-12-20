/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.layout.flag;

import java.util.Collection;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Helper to compute and attach absolute bounds flag for sequence events.
 * 
 * @author mporhel
 */
public abstract class AbstractSequenceAbsoluteBoundsFlagger {

    /**
     * Compute absolute bounds flags for each delimited sequence events of the
     * current diagram.
     */
    public final void flag() {
        for (ISequenceElement ise : getEventsToFlag()) {
            flag(ise);
        }
    }

    private void flag(ISequenceElement ise) {
        // add flag
        EObject element = ise.getNotationView().getElement();
        if (element instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) element;
            Rectangle absBounds = ise.getProperLogicalBounds();

            AbsoluteBoundsFilter flag = getOrCreateFlag(dde);

            if (ise instanceof LostMessageEnd && !((LostMessageEnd) ise).getMessage().some()) {
                flag.setX(LayoutConstants.TOOL_CREATION_FLAG_FROM_SEMANTIC.x);
            }

            // Update flag
            if (flag != null && absBounds != null) {
                if (!(ise instanceof Message)) {
                    flag.setX(absBounds.x);
                    flag.setWidth(absBounds.width);
                }
                flag.setY(absBounds.y);
                flag.setHeight(absBounds.height);
            }
        }
    }

    /**
     * Gets events to flag.
     * 
     * @return a collection of events to flag.
     */
    protected abstract Collection<ISequenceElement> getEventsToFlag();

    private AbsoluteBoundsFilter getOrCreateFlag(DDiagramElement dde) {
        AbsoluteBoundsFilter flag = null;
        Collection<AbsoluteBoundsFilter> flags = Lists.newArrayList(Iterables.filter(dde.getGraphicalFilters(), AbsoluteBoundsFilter.class));
        for (AbsoluteBoundsFilter prevFlag : flags) {
            flag = prevFlag;
            break;
        }

        if (flag == null) {
            flag = DiagramFactory.eINSTANCE.createAbsoluteBoundsFilter();
            dde.getGraphicalFilters().add(flag);
        }
        return flag;
    }

}
