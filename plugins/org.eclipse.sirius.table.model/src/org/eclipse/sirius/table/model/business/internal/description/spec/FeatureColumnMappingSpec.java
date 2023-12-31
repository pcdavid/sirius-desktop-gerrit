/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.model.business.internal.description.spec;

import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl;

/**
 * Specific implementation for model instances.
 * 
 * @author cbrun
 * 
 */
public class FeatureColumnMappingSpec extends FeatureColumnMappingImpl {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getLabelComputationExpression() {
        return getLabelExpression();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CreateCellTool getCreateCell() {
        // there is no possibility to create a cell from a column mapping.
        return null;
    }
}
