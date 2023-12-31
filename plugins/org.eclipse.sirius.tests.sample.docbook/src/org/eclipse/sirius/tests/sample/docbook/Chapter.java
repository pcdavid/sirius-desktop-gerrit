/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.sample.docbook;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Chapter</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Chapter#getTitle <em>Title
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Chapter#getPara <em>Para
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Chapter#getSect1 <em>Sect1
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.docbook.Chapter#getId <em>Id</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getChapter()
 * @model
 * @generated
 */
public interface Chapter extends EObject {
    /**
     * Returns the value of the '<em><b>Title</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Title</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Title</em>' containment reference.
     * @see #setTitle(Title)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getChapter_Title()
     * @model containment="true"
     * @generated
     */
    Title getTitle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Chapter#getTitle
     * <em>Title</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Title</em>' containment reference.
     * @see #getTitle()
     * @generated
     */
    void setTitle(Title value);

    /**
     * Returns the value of the '<em><b>Para</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.docbook.Para}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Para</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Para</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getChapter_Para()
     * @model containment="true"
     * @generated
     */
    EList<Para> getPara();

    /**
     * Returns the value of the '<em><b>Sect1</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.docbook.Sect1}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sect1</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sect1</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getChapter_Sect1()
     * @model containment="true"
     * @generated
     */
    EList<Sect1> getSect1();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage#getChapter_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.docbook.Chapter#getId <em>Id</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // Chapter
