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
package org.eclipse.sirius.properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.viewpoint.description.Extension;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>View
 * Extension Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.ViewExtensionDescription#getLabel
 * <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ViewExtensionDescription#getMetamodels
 * <em>Metamodels</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ViewExtensionDescription#getCategories
 * <em>Categories</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getViewExtensionDescription()
 * @model
 * @generated
 */
public interface ViewExtensionDescription extends Extension, DocumentedElementDescription {
    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getViewExtensionDescription_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.ViewExtensionDescription#getLabel
     * <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Metamodels</b></em>' reference list. The
     * list contents are of type {@link org.eclipse.emf.ecore.EPackage}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Metamodels</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Metamodels</em>' reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getViewExtensionDescription_Metamodels()
     * @model
     * @generated
     */
    EList<EPackage> getMetamodels();

    /**
     * Returns the value of the '<em><b>Categories</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.Category}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Categories</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Categories</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getViewExtensionDescription_Categories()
     * @model containment="true"
     * @generated
     */
    EList<Category> getCategories();

} // ViewExtensionDescription
