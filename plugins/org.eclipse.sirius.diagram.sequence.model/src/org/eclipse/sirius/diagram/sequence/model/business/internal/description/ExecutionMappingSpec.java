/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.model.business.internal.description;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.model.business.internal.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.sequence.description.impl.ExecutionMappingImpl;

/**
 * Implementation of <code>ExecutionMapping</code>.
 * 
 * @author pcdavid
 */
public class ExecutionMappingSpec extends ExecutionMappingImpl implements INodeMappingExt {

    private final Map<EObject, EList<DDiagramElement>> viewNodesDone = new HashMap<>();

    @Override
    public Map<EObject, EList<DDiagramElement>> getViewNodesDone() {
        return viewNodesDone;
    }

    @Override
    public String toString() {
        return new StringBuffer(getClass().getName()).append(" ").append(getName()).toString(); //$NON-NLS-1$
    }

}
