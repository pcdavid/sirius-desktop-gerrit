/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.editor.tools.internal.menu.child;

import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;

/**
 * The extensions menu.
 * 
 * @author cbrun
 * 
 */
public class ExtensionsMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Build the menu.
     */
    public ExtensionsMenuBuilder() {
        super();
        addValidType(DescriptionPackage.eINSTANCE.getDiagramExtensionDescription());
        addValidType(ToolPackage.eINSTANCE.getRequestDescription());
        addValidType(ToolPackage.eINSTANCE.getToolGroupExtension());

    }

    @Override
    public String getLabel() {
        return "New Extension";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.EXTENSION;
    }
}
