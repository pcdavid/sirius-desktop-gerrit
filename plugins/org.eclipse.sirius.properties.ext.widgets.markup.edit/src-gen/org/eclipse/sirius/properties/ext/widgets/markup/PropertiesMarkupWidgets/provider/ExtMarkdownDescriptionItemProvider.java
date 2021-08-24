/**
 *  Copyright (c) Obeo, Eclipse contributors and others 2023.
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 * 
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Obeo and Israel Aerospace Industries - initial API and implementation
 * 
 */
package org.eclipse.sirius.properties.ext.widgets.markup.PropertiesMarkupWidgets.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.sirius.properties.PropertiesPackage;

import org.eclipse.sirius.properties.ext.widgets.markup.PropertiesMarkupWidgets.ExtMarkdownDescription;
import org.eclipse.sirius.properties.ext.widgets.markup.PropertiesMarkupWidgets.PropertiesMarkupWidgetsPackage;

import org.eclipse.sirius.properties.provider.WidgetDescriptionItemProvider;

import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.properties.ext.widgets.markup.PropertiesMarkupWidgets.ExtMarkdownDescription} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtMarkdownDescriptionItemProvider extends WidgetDescriptionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtMarkdownDescriptionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addLabelExpressionPropertyDescriptor(object);
            addHelpExpressionPropertyDescriptor(object);
            addIsEnabledExpressionPropertyDescriptor(object);
            addValueExpressionPropertyDescriptor(object);
            addNumberOfLinesPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Label Expression feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addLabelExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractWidgetDescription_labelExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractWidgetDescription_labelExpression_feature", "_UI_AbstractWidgetDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Help Expression feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addHelpExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractWidgetDescription_helpExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractWidgetDescription_helpExpression_feature", "_UI_AbstractWidgetDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Is Enabled Expression feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addIsEnabledExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_AbstractWidgetDescription_isEnabledExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_AbstractWidgetDescription_isEnabledExpression_feature", "_UI_AbstractWidgetDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesPackage.Literals.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Value Expression feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addValueExpressionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
                getString("_UI_ExtMarkdownDescription_valueExpression_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_ExtMarkdownDescription_valueExpression_feature", "_UI_ExtMarkdownDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                PropertiesMarkupWidgetsPackage.Literals.EXT_MARKDOWN_DESCRIPTION__VALUE_EXPRESSION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Number Of Lines feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addNumberOfLinesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(
                createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_ExtMarkdownDescription_numberOfLines_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_ExtMarkdownDescription_numberOfLines_feature", "_UI_ExtMarkdownDescription_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        PropertiesMarkupWidgetsPackage.Literals.EXT_MARKDOWN_DESCRIPTION__NUMBER_OF_LINES, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(PropertiesMarkupWidgetsPackage.Literals.EXT_MARKDOWN_DESCRIPTION__INITIAL_OPERATION);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns ExtMarkdownDescription.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ExtMarkdownDescription")); //$NON-NLS-1$
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected boolean shouldComposeCreationImage() {
        return true;
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((ExtMarkdownDescription) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_ExtMarkdownDescription_type") : //$NON-NLS-1$
                getString("_UI_ExtMarkdownDescription_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(ExtMarkdownDescription.class)) {
        case PropertiesMarkupWidgetsPackage.EXT_MARKDOWN_DESCRIPTION__LABEL_EXPRESSION:
        case PropertiesMarkupWidgetsPackage.EXT_MARKDOWN_DESCRIPTION__HELP_EXPRESSION:
        case PropertiesMarkupWidgetsPackage.EXT_MARKDOWN_DESCRIPTION__IS_ENABLED_EXPRESSION:
        case PropertiesMarkupWidgetsPackage.EXT_MARKDOWN_DESCRIPTION__VALUE_EXPRESSION:
        case PropertiesMarkupWidgetsPackage.EXT_MARKDOWN_DESCRIPTION__NUMBER_OF_LINES:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case PropertiesMarkupWidgetsPackage.EXT_MARKDOWN_DESCRIPTION__INITIAL_OPERATION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(PropertiesMarkupWidgetsPackage.Literals.EXT_MARKDOWN_DESCRIPTION__INITIAL_OPERATION, ToolFactory.eINSTANCE.createInitialOperation()));
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify = childFeature == PropertiesMarkupWidgetsPackage.Literals.EXT_MARKDOWN_DESCRIPTION__INITIAL_OPERATION;

        if (qualify) {
            return getString("_UI_CreateChild_text2", //$NON-NLS-1$
                    new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
