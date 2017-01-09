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

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Dynamic
 * Mapping If Override Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription#getOverrides
 * <em>Overrides</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingIfOverrideDescription()
 * @model
 * @generated
 */
public interface DynamicMappingIfOverrideDescription extends AbstractDynamicMappingIfDescription, AbstractOverrideDescription {
    /**
     * Returns the value of the '<em><b>Overrides</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' reference.
     * @see #setOverrides(DynamicMappingIfDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingIfOverrideDescription_Overrides()
     * @model
     * @generated
     */
    DynamicMappingIfDescription getOverrides();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription#getOverrides
     * <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Overrides</em>' reference.
     * @see #getOverrides()
     * @generated
     */
    void setOverrides(DynamicMappingIfDescription value);

} // DynamicMappingIfOverrideDescription
