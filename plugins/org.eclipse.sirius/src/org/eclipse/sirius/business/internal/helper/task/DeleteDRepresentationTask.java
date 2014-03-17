/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.internal.util.GMFUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A task to delete a representation.
 * 
 * @author mchauvin
 */
public class DeleteDRepresentationTask extends AbstractCommandTask {

    /** The representation to delete. */
    protected DRepresentation representation;

    /** Also delete the dangling references. */
    protected boolean deleteReferences;

    /**
     * Default constructor.
     * 
     * @param representation
     *            the representation to delete
     */
    public DeleteDRepresentationTask(final DRepresentation representation) {
        this.representation = representation;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {

        /* only destroy attached elements */
        if ((representation != null) && (representation.eResource() != null)) {

            ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation);

            if (deleteReferences) {
                final Session session;
                if (representation instanceof DSemanticDecorator) {
                    session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) representation).getTarget());
                } else {
                    session = SessionManager.INSTANCE.getSession(representation);
                }
                accessor.eDelete(representation, session != null ? session.getSemanticCrossReferencer() : null);
            } else {
                // tear down incoming references
                GMFUtil.tearDownIncomingReferences(representation);

                // also tear down outgoing references, because we don't want
                // reverse-reference lookups to find destroyed objects
                GMFUtil.tearDownOutgoingReferences(representation);

                // remove the object from its container
                SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation).eRemove(representation);

                // in case it was cross-resource-contained
                final Resource res = representation.eResource();
                if (res != null) {
                    res.getContents().remove(representation);
                }
            }
        }
    }

    public void setDeleteIncomingReferences(final boolean value) {
        deleteReferences = value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return null;
    }
}
