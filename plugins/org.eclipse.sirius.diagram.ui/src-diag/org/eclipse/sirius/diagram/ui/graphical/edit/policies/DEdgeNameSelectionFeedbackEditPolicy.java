/*******************************************************************************
 * Copyright (c) 2009, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.draw2d.Connection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;

/**
 * An edit policy that can show feedback for selection on a
 * {@link org.eclipse.sirius.diagram.internal.edit.parts.DEdgeNameEditPart}.
 * 
 * @author mPorhel
 */
public class DEdgeNameSelectionFeedbackEditPolicy extends AbstractEdgeSelectionFeedbackEditPolicy implements PropertyChangeListener {

    /**
     * {@inheritDoc}
     * 
     * @see AbstractEdgeSelectionFeedbackEditPolicy#getEdgeEditPart()
     */
    @Override
    protected AbstractDiagramEdgeEditPart getEdgeEditPart() {
        if (getHost() instanceof AbstractDEdgeNameEditPart && getHost().getParent() instanceof AbstractDiagramEdgeEditPart) {
            return (AbstractDiagramEdgeEditPart) getHost().getParent();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see AbstractEdgeSelectionFeedbackEditPolicy#getEdgeNameEditPart()
     */
    @Override
    protected List<AbstractDEdgeNameEditPart> getEdgeNameEditPart() {
        List<AbstractDEdgeNameEditPart> names = new ArrayList<AbstractDEdgeNameEditPart>();
        if (getHost() instanceof AbstractDEdgeNameEditPart) {
            @SuppressWarnings("unchecked")
            List<AbstractDEdgeNameEditPart> edgeNameEditParts = (List<AbstractDEdgeNameEditPart>) getEdgeEditPart().getChildren().stream().filter(AbstractDEdgeNameEditPart.class::isInstance)
                    .map(AbstractDEdgeNameEditPart.class::cast).collect(Collectors.toList());
            for (final AbstractDEdgeNameEditPart editPart : edgeNameEditParts) {
                View view = editPart.getNotationView();
                if (view != null) {
                    EObject element = view.getElement();
                    if (element instanceof DEdge && !new DDiagramElementQuery((DEdge) element).isLabelHidden(new EditPartQuery(editPart).getVisualID())) {
                        names.add(editPart);
                    }
                }
            }
        }
        return names;
    }

    @Override
    protected void showSelection() {
        if (getEdgeEditPart() != null && getEdgeEditPart().getFigure() != null) {
            // we register this policy as a property listener to clear rebuild its handles when target edge's bend
            // points change. This is needed when an edge is straighten whereas the edge part and its name part are both
            // selected. We remove it first in case of multi selection to avoid registering it two times causing
            // exceptions later. See 546298.
            ((Connection) getEdgeEditPart().getFigure()).removePropertyChangeListener(this);
            ((Connection) getEdgeEditPart().getFigure()).addPropertyChangeListener(this);
        }
        super.showSelection();
    }

    @Override
    protected void hideSelection() {
        if (getEdgeEditPart() != null && getEdgeEditPart().getFigure() != null) {
            ((Connection) getEdgeEditPart().getFigure()).removePropertyChangeListener(this);
        }
        super.hideSelection();
    }

    /**
     * Adds selection handles to the Connection, if it is selected, when the points property changes. Since we only
     * listen for changes in the points property, this method is only called when the points of the Connection have
     * changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (getHost().getSelected() != EditPart.SELECTED_NONE) {
            addSelectionHandles();
        }
    }

}
