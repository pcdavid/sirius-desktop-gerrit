/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.model.business.internal.color;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.util.StyleSwitch;

/**
 * Class responsible for setting default color values on style descriptions.
 * 
 * @author cbrun
 * 
 */
public class DefaultColorStyleDescription extends StyleSwitch<EObject> {

    private static final String BLACK = "black"; //$NON-NLS-1$

    /**
     * Set the default color descriptions on the given EObject.
     * 
     * @param theEObject
     *            the object to update.
     */
    public void setDefaultColors(final EObject theEObject) {
        doSwitch(theEObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseLabelStyleDescription(final LabelStyleDescription object) {
        object.setLabelColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseLabelStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseBasicLabelStyleDescription(BasicLabelStyleDescription object) {
        object.setLabelColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseBasicLabelStyleDescription(object);
    }
}
