/**
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SelectWidgetConditionalStyle;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class SelectDescriptionItemProviderSpec extends SelectDescriptionItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public SelectDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        String label = ((SelectDescription) object).getLabelExpression();
        return label == null || label.length() == 0 ? getString("_UI_SelectDescription_type") : //$NON-NLS-1$
                label;
    }

    @Override
    protected CommandParameter createChildParameter(Object feature, Object child) {
        Utils.addNoopNavigationOperations(child);
        return super.createChildParameter(feature, child);
    }

    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.SELECT_DESCRIPTION__INITIAL_OPERATION, ToolFactory.eINSTANCE.createInitialOperation()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.SELECT_DESCRIPTION__STYLE, PropertiesFactory.eINSTANCE.createSelectWidgetStyle()));

        SelectWidgetConditionalStyle conditionalStyle = PropertiesFactory.eINSTANCE.createSelectWidgetConditionalStyle();
        conditionalStyle.setStyle(PropertiesFactory.eINSTANCE.createSelectWidgetStyle());
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.SELECT_DESCRIPTION__CONDITIONAL_STYLES, conditionalStyle));
    }

}