/*******************************************************************************
 * Copyright (c) 2016, 2018 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.editor.properties.filters.properties.abstractlistdescription;

// Start of user code specific imports

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;
import org.eclipse.sirius.properties.PropertiesPackage;

// End of user code specific imports

/**
 * A filter for the extends property section.
 */
public class AbstractListDescriptionExtendsFilter extends ViewpointPropertyFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected EStructuralFeature getFeature() {
        return PropertiesPackage.eINSTANCE.getAbstractListDescription_Extends();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.properties.AbstractListDescription;
    }

    // Start of user code user methods

    // End of user code user methods

}
