/*******************************************************************************
 * Copyright (c) 2007-2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.metamodel.table.description.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.ui.tools.api.provider.ItemProviderHelper;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.provider.AbstractToolDescriptionItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.table.metamodel.table.description.CreateTool}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class CreateToolItemProvider extends AbstractToolDescriptionItemProvider {
    private static final Collection<EClass> TYPES_TO_HIDE = new ArrayList<>(Arrays.asList(ToolPackage.Literals.DELETE_VIEW));

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public CreateToolItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

        }
        return itemPropertyDescriptors;
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(DescriptionPackage.Literals.TABLE_TOOL__VARIABLES);
            childrenFeatures.add(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns CreateTool.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/CreateTool")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String getText(Object object) {
        String label = new IdentifiedElementQuery((IdentifiedElement) object).getLabel();
        return StringUtil.isEmpty(label) ? getString("_UI_CreateTool_type") : getString("_UI_CreateTool_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
     * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(CreateTool.class)) {
        case DescriptionPackage.CREATE_TOOL__VARIABLES:
        case DescriptionPackage.CREATE_TOOL__FIRST_MODEL_OPERATION:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        collectNewChildDescriptorsGen(newChildDescriptors, object);
        /* remove tool filters as they are not yet supported for table */
        CommandParameter firstDescriptor = (CommandParameter) newChildDescriptors.toArray()[0];
        if (firstDescriptor.feature == ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__FILTERS) {
            newChildDescriptors.remove(firstDescriptor);
        }
        ItemProviderHelper.filterChildDescriptorsByType(newChildDescriptors, CreateToolItemProvider.TYPES_TO_HIDE);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void collectNewChildDescriptorsGen(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createExternalJavaAction()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createExternalJavaActionCall()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createCreateInstance()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createChangeContext()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createSetValue()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createSetObject()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createUnset()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createMoveElement()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createRemoveElement()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createFor()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createIf()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createDeleteView()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createSwitch()));

        newChildDescriptors.add(createChildParameter(DescriptionPackage.Literals.TABLE_TOOL__FIRST_MODEL_OPERATION, ToolFactory.eINSTANCE.createLet()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return TableUIPlugin.INSTANCE;
    }

}
