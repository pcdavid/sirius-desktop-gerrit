/**
 * Copyright (c) 2017 Obeo.
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

import org.eclipse.emf.common.notify.Adapter;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author arichard
 */
public class PropertiesItemProviderAdapterFactorySpec extends PropertiesItemProviderAdapterFactory {

    @Override
    public Adapter createViewExtensionDescriptionAdapter() {
        if (viewExtensionDescriptionItemProvider == null) {
            viewExtensionDescriptionItemProvider = new ViewExtensionDescriptionItemProviderSpec(this);
        }

        return viewExtensionDescriptionItemProvider;
    }

    @Override
    public Adapter createPageDescriptionAdapter() {
        if (pageDescriptionItemProvider == null) {
            pageDescriptionItemProvider = new PageDescriptionItemProviderSpec(this);
        }

        return pageDescriptionItemProvider;
    }

    @Override
    public Adapter createPageValidationSetDescriptionAdapter() {
        if (pageValidationSetDescriptionItemProvider == null) {
            pageValidationSetDescriptionItemProvider = new PageValidationSetDescriptionItemProviderSpec(this);
        }

        return pageValidationSetDescriptionItemProvider;
    }

    @Override
    public Adapter createPropertyValidationRuleAdapter() {
        if (propertyValidationRuleItemProvider == null) {
            propertyValidationRuleItemProvider = new PropertyValidationRuleItemProviderSpec(this);
        }

        return propertyValidationRuleItemProvider;
    }

    @Override
    public Adapter createGroupDescriptionAdapter() {
        if (groupDescriptionItemProvider == null) {
            groupDescriptionItemProvider = new GroupDescriptionItemProviderSpec(this);
        }

        return groupDescriptionItemProvider;
    }

    @Override
    public Adapter createToolbarActionAdapter() {
        if (toolbarActionItemProvider == null) {
            toolbarActionItemProvider = new ToolbarActionItemProviderSpec(this);
        }

        return toolbarActionItemProvider;
    }

    @Override
    public Adapter createGroupValidationSetDescriptionAdapter() {
        if (groupValidationSetDescriptionItemProvider == null) {
            groupValidationSetDescriptionItemProvider = new GroupValidationSetDescriptionItemProviderSpec(this);
        }

        return groupValidationSetDescriptionItemProvider;
    }

    @Override
    public Adapter createContainerDescriptionAdapter() {
        if (containerDescriptionItemProvider == null) {
            containerDescriptionItemProvider = new ContainerDescriptionItemProviderSpec(this);
        }

        return containerDescriptionItemProvider;
    }

    @Override
    public Adapter createFillLayoutDescriptionAdapter() {
        if (fillLayoutDescriptionItemProvider == null) {
            fillLayoutDescriptionItemProvider = new FillLayoutDescriptionItemProviderSpec(this);
        }

        return fillLayoutDescriptionItemProvider;
    }

    @Override
    public Adapter createGridLayoutDescriptionAdapter() {
        if (gridLayoutDescriptionItemProvider == null) {
            gridLayoutDescriptionItemProvider = new GridLayoutDescriptionItemProviderSpec(this);
        }

        return gridLayoutDescriptionItemProvider;
    }

    @Override
    public Adapter createTextDescriptionAdapter() {
        if (textDescriptionItemProvider == null) {
            textDescriptionItemProvider = new TextDescriptionItemProviderSpec(this);
        }

        return textDescriptionItemProvider;
    }

    @Override
    public Adapter createButtonDescriptionAdapter() {
        if (buttonDescriptionItemProvider == null) {
            buttonDescriptionItemProvider = new ButtonDescriptionItemProviderSpec(this);
        }

        return buttonDescriptionItemProvider;
    }

    @Override
    public Adapter createLabelDescriptionAdapter() {
        if (labelDescriptionItemProvider == null) {
            labelDescriptionItemProvider = new LabelDescriptionItemProviderSpec(this);
        }

        return labelDescriptionItemProvider;
    }

    @Override
    public Adapter createCheckboxDescriptionAdapter() {
        if (checkboxDescriptionItemProvider == null) {
            checkboxDescriptionItemProvider = new CheckboxDescriptionItemProviderSpec(this);
        }

        return checkboxDescriptionItemProvider;
    }

    @Override
    public Adapter createSelectDescriptionAdapter() {
        if (selectDescriptionItemProvider == null) {
            selectDescriptionItemProvider = new SelectDescriptionItemProviderSpec(this);
        }

        return selectDescriptionItemProvider;
    }

    @Override
    public Adapter createDynamicMappingForAdapter() {
        if (dynamicMappingForItemProvider == null) {
            dynamicMappingForItemProvider = new DynamicMappingForItemProviderSpec(this);
        }

        return dynamicMappingForItemProvider;
    }

    @Override
    public Adapter createDynamicMappingIfAdapter() {
        if (dynamicMappingIfItemProvider == null) {
            dynamicMappingIfItemProvider = new DynamicMappingIfItemProviderSpec(this);
        }

        return dynamicMappingIfItemProvider;
    }

    @Override
    public Adapter createTextAreaDescriptionAdapter() {
        if (textAreaDescriptionItemProvider == null) {
            textAreaDescriptionItemProvider = new TextAreaDescriptionItemProviderSpec(this);
        }

        return textAreaDescriptionItemProvider;
    }

    @Override
    public Adapter createRadioDescriptionAdapter() {
        if (radioDescriptionItemProvider == null) {
            radioDescriptionItemProvider = new RadioDescriptionItemProviderSpec(this);
        }

        return radioDescriptionItemProvider;
    }

    @Override
    public Adapter createListDescriptionAdapter() {
        if (listDescriptionItemProvider == null) {
            listDescriptionItemProvider = new ListDescriptionItemProviderSpec(this);
        }

        return listDescriptionItemProvider;
    }

    @Override
    public Adapter createOperationDescriptionAdapter() {
        if (operationDescriptionItemProvider == null) {
            operationDescriptionItemProvider = new OperationDescriptionItemProviderSpec(this);
        }

        return operationDescriptionItemProvider;
    }

    @Override
    public Adapter createCustomDescriptionAdapter() {
        if (customDescriptionItemProvider == null) {
            customDescriptionItemProvider = new CustomDescriptionItemProviderSpec(this);
        }

        return customDescriptionItemProvider;
    }

    @Override
    public Adapter createCustomExpressionAdapter() {
        if (customExpressionItemProvider == null) {
            customExpressionItemProvider = new CustomExpressionItemProviderSpec(this);
        }

        return customExpressionItemProvider;
    }

    @Override
    public Adapter createCustomOperationAdapter() {
        if (customOperationItemProvider == null) {
            customOperationItemProvider = new CustomOperationItemProviderSpec(this);
        }

        return customOperationItemProvider;
    }

    @Override
    public Adapter createHyperlinkDescriptionAdapter() {
        if (hyperlinkDescriptionItemProvider == null) {
            hyperlinkDescriptionItemProvider = new HyperlinkDescriptionItemProviderSpec(this);
        }

        return hyperlinkDescriptionItemProvider;
    }

    @Override
    public Adapter createWidgetStyleAdapter() {
        if (widgetStyleItemProvider == null) {
            widgetStyleItemProvider = new WidgetStyleItemProviderSpec(this);
        }

        return widgetStyleItemProvider;
    }

    @Override
    public Adapter createTextWidgetStyleAdapter() {
        if (textWidgetStyleItemProvider == null) {
            textWidgetStyleItemProvider = new TextWidgetStyleItemProviderSpec(this);
        }

        return textWidgetStyleItemProvider;
    }

    @Override
    public Adapter createLabelWidgetStyleAdapter() {
        if (labelWidgetStyleItemProvider == null) {
            labelWidgetStyleItemProvider = new LabelWidgetStyleItemProviderSpec(this);
        }

        return labelWidgetStyleItemProvider;
    }

    @Override
    public Adapter createCheckboxWidgetStyleAdapter() {
        if (checkboxWidgetStyleItemProvider == null) {
            checkboxWidgetStyleItemProvider = new CheckboxWidgetStyleItemProviderSpec(this);
        }

        return checkboxWidgetStyleItemProvider;
    }

    @Override
    public Adapter createRadioWidgetStyleAdapter() {
        if (radioWidgetStyleItemProvider == null) {
            radioWidgetStyleItemProvider = new RadioWidgetStyleItemProviderSpec(this);
        }

        return radioWidgetStyleItemProvider;
    }

    @Override
    public Adapter createButtonWidgetStyleAdapter() {
        if (buttonWidgetStyleItemProvider == null) {
            buttonWidgetStyleItemProvider = new ButtonWidgetStyleItemProviderSpec(this);
        }

        return buttonWidgetStyleItemProvider;
    }

    @Override
    public Adapter createSelectWidgetStyleAdapter() {
        if (selectWidgetStyleItemProvider == null) {
            selectWidgetStyleItemProvider = new SelectWidgetStyleItemProviderSpec(this);
        }

        return selectWidgetStyleItemProvider;
    }

    @Override
    public Adapter createCustomWidgetStyleAdapter() {
        if (customWidgetStyleItemProvider == null) {
            customWidgetStyleItemProvider = new CustomWidgetStyleItemProviderSpec(this);
        }

        return customWidgetStyleItemProvider;
    }

    @Override
    public Adapter createListWidgetStyleAdapter() {
        if (listWidgetStyleItemProvider == null) {
            listWidgetStyleItemProvider = new ListWidgetStyleItemProviderSpec(this);
        }

        return listWidgetStyleItemProvider;
    }

    @Override
    public Adapter createHyperlinkWidgetStyleAdapter() {
        if (hyperlinkWidgetStyleItemProvider == null) {
            hyperlinkWidgetStyleItemProvider = new HyperlinkWidgetStyleItemProviderSpec(this);
        }

        return hyperlinkWidgetStyleItemProvider;
    }

    @Override
    public Adapter createGroupStyleAdapter() {
        if (groupStyleItemProvider == null) {
            groupStyleItemProvider = new GroupStyleItemProviderSpec(this);
        }

        return groupStyleItemProvider;
    }

    @Override
    public Adapter createTextWidgetConditionalStyleAdapter() {
        if (textWidgetConditionalStyleItemProvider == null) {
            textWidgetConditionalStyleItemProvider = new TextWidgetConditionalStyleItemProviderSpec(this);
        }

        return textWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createLabelWidgetConditionalStyleAdapter() {
        if (labelWidgetConditionalStyleItemProvider == null) {
            labelWidgetConditionalStyleItemProvider = new LabelWidgetConditionalStyleItemProviderSpec(this);
        }

        return labelWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createCheckboxWidgetConditionalStyleAdapter() {
        if (checkboxWidgetConditionalStyleItemProvider == null) {
            checkboxWidgetConditionalStyleItemProvider = new CheckboxWidgetConditionalStyleItemProviderSpec(this);
        }

        return checkboxWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createRadioWidgetConditionalStyleAdapter() {
        if (radioWidgetConditionalStyleItemProvider == null) {
            radioWidgetConditionalStyleItemProvider = new RadioWidgetConditionalStyleItemProviderSpec(this);
        }

        return radioWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createButtonWidgetConditionalStyleAdapter() {
        if (buttonWidgetConditionalStyleItemProvider == null) {
            buttonWidgetConditionalStyleItemProvider = new ButtonWidgetConditionalStyleItemProviderSpec(this);
        }

        return buttonWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createSelectWidgetConditionalStyleAdapter() {
        if (selectWidgetConditionalStyleItemProvider == null) {
            selectWidgetConditionalStyleItemProvider = new SelectWidgetConditionalStyleItemProviderSpec(this);
        }

        return selectWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createCustomWidgetConditionalStyleAdapter() {
        if (customWidgetConditionalStyleItemProvider == null) {
            customWidgetConditionalStyleItemProvider = new CustomWidgetConditionalStyleItemProviderSpec(this);
        }

        return customWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createListWidgetConditionalStyleAdapter() {
        if (listWidgetConditionalStyleItemProvider == null) {
            listWidgetConditionalStyleItemProvider = new ListWidgetConditionalStyleItemProviderSpec(this);
        }

        return listWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createWidgetActionAdapter() {
        if (widgetActionItemProvider == null) {
            widgetActionItemProvider = new WidgetActionItemProviderSpec(this);
        }

        return widgetActionItemProvider;
    }

    @Override
    public Adapter createHyperlinkWidgetConditionalStyleAdapter() {
        if (hyperlinkWidgetConditionalStyleItemProvider == null) {
            hyperlinkWidgetConditionalStyleItemProvider = new HyperlinkWidgetConditionalStyleItemProviderSpec(this);
        }

        return hyperlinkWidgetConditionalStyleItemProvider;
    }

    @Override
    public Adapter createGroupConditionalStyleAdapter() {
        if (groupConditionalStyleItemProvider == null) {
            groupConditionalStyleItemProvider = new GroupConditionalStyleItemProviderSpec(this);
        }

        return groupConditionalStyleItemProvider;
    }

    @Override
    public Adapter createEditSupportAdapter() {
        if (editSupportItemProvider == null) {
            editSupportItemProvider = new EditSupportItemProviderSpec(this);
        }

        return editSupportItemProvider;
    }
}
