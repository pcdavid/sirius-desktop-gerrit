/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.template;

import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>TMessage Style</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getStrokeColor
 * <em>Stroke Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getLineStyle
 * <em>Line Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getSourceArrow
 * <em>Source Arrow</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getTargetArrow
 * <em>Target Arrow</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getLabelExpression
 * <em>Label Expression</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageStyle()
 * @model
 * @generated
 */
public interface TMessageStyle extends TTransformer {
    /**
     * Returns the value of the '<em><b>Stroke Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * color of the edge. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Stroke Color</em>' reference.
     * @see #setStrokeColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageStyle_StrokeColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getStrokeColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getStrokeColor
     * <em>Stroke Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Stroke Color</em>' reference.
     * @see #getStrokeColor()
     * @generated
     */
    void setStrokeColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Line Style</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.viewpoint.LineStyle} . <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The style of the line.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Line Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.LineStyle
     * @see #setLineStyle(LineStyle)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageStyle_LineStyle()
     * @model
     * @generated
     */
    org.eclipse.sirius.diagram.LineStyle getLineStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getLineStyle
     * <em>Line Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Line Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.LineStyle
     * @see #getLineStyle()
     * @generated
     */
    void setLineStyle(org.eclipse.sirius.diagram.LineStyle value);

    /**
     * Returns the value of the '<em><b>Source Arrow</b></em>' attribute. The
     * default value is <code>"NoDecoration"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.viewpoint.EdgeArrows}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * source decoration. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Source Arrow</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeArrows
     * @see #setSourceArrow(EdgeArrows)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageStyle_SourceArrow()
     * @model default="NoDecoration" required="true"
     * @generated
     */
    org.eclipse.sirius.diagram.EdgeArrows getSourceArrow();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getSourceArrow
     * <em>Source Arrow</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Arrow</em>' attribute.
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @see #getSourceArrow()
     * @generated
     */
    void setSourceArrow(org.eclipse.sirius.diagram.EdgeArrows value);

    /**
     * Returns the value of the '<em><b>Target Arrow</b></em>' attribute. The
     * default value is <code>"InputArrow"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.viewpoint.EdgeArrows}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * target decoration. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Target Arrow</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.EdgeArrows
     * @see #setTargetArrow(EdgeArrows)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageStyle_TargetArrow()
     * @model default="InputArrow" required="true"
     * @generated
     */
    org.eclipse.sirius.diagram.EdgeArrows getTargetArrow();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getTargetArrow
     * <em>Target Arrow</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Arrow</em>' attribute.
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @see #getTargetArrow()
     * @generated
     */
    void setTargetArrow(org.eclipse.sirius.diagram.EdgeArrows value);

    /**
     * Returns the value of the '<em><b>Label Expression</b></em>' attribute.
     * The default value is <code>"<%name%>"</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> Expression that computes
     * the name of a node. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Label Expression</em>' attribute.
     * @see #setLabelExpression(String)
     * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage#getTMessageStyle_LabelExpression()
     * @model default="<%name%>"
     *        dataType="org.eclipse.sirius.description.InterpretedExpression"
     *        annotation
     *        ="http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='the current DSemanticDiagram.' view='the current view for which the label is calculated.'"
     * @generated
     */
    String getLabelExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.sequence.template.TMessageStyle#getLabelExpression
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Label Expression</em>' attribute.
     * @see #getLabelExpression()
     * @generated
     */
    void setLabelExpression(String value);

} // TMessageStyle
