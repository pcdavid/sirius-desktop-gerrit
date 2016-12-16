/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.properties.ext.widgets.nebula.propertiesextwidgetsnebula;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.sirius.properties.ext.widgets.nebula.propertiesextwidgetsnebula.PropertiesExtWidgetsNebulaPackage
 * @generated
 */
public interface PropertiesExtWidgetsNebulaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PropertiesExtWidgetsNebulaFactory eINSTANCE = org.eclipse.sirius.properties.ext.widgets.nebula.propertiesextwidgetsnebula.impl.PropertiesExtWidgetsNebulaFactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Ext Nebula Rich Text Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ext Nebula Rich Text Description</em>'.
	 * @generated
	 */
	ExtNebulaRichTextDescription createExtNebulaRichTextDescription();

	/**
	 * Returns a new object of class '<em>Ext Nebula Rich Text Widget Style</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ext Nebula Rich Text Widget Style</em>'.
	 * @generated
	 */
	ExtNebulaRichTextWidgetStyle createExtNebulaRichTextWidgetStyle();

	/**
	 * Returns a new object of class '<em>Ext Nebula Rich Text Widget Conditional Style</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ext Nebula Rich Text Widget Conditional Style</em>'.
	 * @generated
	 */
	ExtNebulaRichTextWidgetConditionalStyle createExtNebulaRichTextWidgetConditionalStyle();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PropertiesExtWidgetsNebulaPackage getPropertiesExtWidgetsNebulaPackage();

} //PropertiesExtWidgetsNebulaFactory
