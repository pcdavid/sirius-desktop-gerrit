/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.model.business.internal.query.DRepresentationInternalQuery;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>DRepresentation</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl#getEAnnotations <em>EAnnotations</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl#getOwnedRepresentationElements <em>Owned
 * Representation Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl#getRepresentationElements <em>Representation
 * Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl#getOwnedAnnotationEntries <em>Owned Annotation
 * Entries</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationImpl#getUiState <em>Ui State</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DRepresentationImpl extends IdentifiedElementImpl implements DRepresentation {
    /**
     * The cached value of the '{@link #getEAnnotations() <em>EAnnotations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEAnnotations()
     * @generated
     * @ordered
     */
    protected EList<DAnnotation> eAnnotations;

    /**
     * The cached value of the '{@link #getOwnedAnnotationEntries() <em>Owned Annotation Entries</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedAnnotationEntries()
     * @generated
     * @ordered
     */
    protected EList<AnnotationEntry> ownedAnnotationEntries;

    /**
     * The cached value of the '{@link #getUiState() <em>Ui State</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getUiState()
     * @generated
     * @ordered
     */
    protected UIState uiState;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    protected DRepresentationImpl() {
        super();
        setUiState(ViewpointFactory.eINSTANCE.createUIState());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DREPRESENTATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DAnnotation> getEAnnotations() {
        if (eAnnotations == null) {
            eAnnotations = new EObjectContainmentEList<>(DAnnotation.class, this, ViewpointPackage.DREPRESENTATION__EANNOTATIONS);
        }
        return eAnnotations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DRepresentationElement> getOwnedRepresentationElements() {
        // TODO: implement this method to return the 'Owned Representation Elements' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DRepresentationElement> getRepresentationElements() {
        // TODO: implement this method to return the 'Representation Elements' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AnnotationEntry> getOwnedAnnotationEntries() {
        if (ownedAnnotationEntries == null) {
            ownedAnnotationEntries = new EObjectContainmentEList.Resolving<>(AnnotationEntry.class, this, ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES);
        }
        return ownedAnnotationEntries;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public UIState getUiState() {
        return uiState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetUiState(UIState newUiState, NotificationChain msgs) {
        UIState oldUiState = uiState;
        uiState = newUiState;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION__UI_STATE, oldUiState, newUiState);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setUiState(UIState newUiState) {
        if (newUiState != uiState) {
            NotificationChain msgs = null;
            if (uiState != null) {
                msgs = ((InternalEObject) uiState).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DREPRESENTATION__UI_STATE, null, msgs);
            }
            if (newUiState != null) {
                msgs = ((InternalEObject) newUiState).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ViewpointPackage.DREPRESENTATION__UI_STATE, null, msgs);
            }
            msgs = basicSetUiState(newUiState, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION__UI_STATE, newUiState, newUiState));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getName() {
        DRepresentationDescriptor representationDescriptor = new DRepresentationInternalQuery(this).getRepresentationDescriptor();
        if (representationDescriptor != null) {
            return representationDescriptor.getName();
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getDocumentation() {
        DRepresentationDescriptor representationDescriptor = new DRepresentationInternalQuery(this).getRepresentationDescriptor();
        if (representationDescriptor != null) {
            return representationDescriptor.getDocumentation();
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DAnnotation getDAnnotation(String source) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.DREPRESENTATION__EANNOTATIONS:
            return ((InternalEList<?>) getEAnnotations()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES:
            return ((InternalEList<?>) getOwnedAnnotationEntries()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.DREPRESENTATION__UI_STATE:
            return basicSetUiState(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewpointPackage.DREPRESENTATION__EANNOTATIONS:
            return getEAnnotations();
        case ViewpointPackage.DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS:
            return getOwnedRepresentationElements();
        case ViewpointPackage.DREPRESENTATION__REPRESENTATION_ELEMENTS:
            return getRepresentationElements();
        case ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES:
            return getOwnedAnnotationEntries();
        case ViewpointPackage.DREPRESENTATION__UI_STATE:
            return getUiState();
        case ViewpointPackage.DREPRESENTATION__NAME:
            return getName();
        case ViewpointPackage.DREPRESENTATION__DOCUMENTATION:
            return getDocumentation();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ViewpointPackage.DREPRESENTATION__EANNOTATIONS:
            getEAnnotations().clear();
            getEAnnotations().addAll((Collection<? extends DAnnotation>) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES:
            getOwnedAnnotationEntries().clear();
            getOwnedAnnotationEntries().addAll((Collection<? extends AnnotationEntry>) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION__UI_STATE:
            setUiState((UIState) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case ViewpointPackage.DREPRESENTATION__EANNOTATIONS:
            getEAnnotations().clear();
            return;
        case ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES:
            getOwnedAnnotationEntries().clear();
            return;
        case ViewpointPackage.DREPRESENTATION__UI_STATE:
            setUiState((UIState) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case ViewpointPackage.DREPRESENTATION__EANNOTATIONS:
            return eAnnotations != null && !eAnnotations.isEmpty();
        case ViewpointPackage.DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS:
            return !getOwnedRepresentationElements().isEmpty();
        case ViewpointPackage.DREPRESENTATION__REPRESENTATION_ELEMENTS:
            return !getRepresentationElements().isEmpty();
        case ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES:
            return ownedAnnotationEntries != null && !ownedAnnotationEntries.isEmpty();
        case ViewpointPackage.DREPRESENTATION__UI_STATE:
            return uiState != null;
        case ViewpointPackage.DREPRESENTATION__NAME:
            return DRepresentationImpl.NAME_EDEFAULT == null ? getName() != null : !DRepresentationImpl.NAME_EDEFAULT.equals(getName());
        case ViewpointPackage.DREPRESENTATION__DOCUMENTATION:
            return DRepresentationImpl.DOCUMENTATION_EDEFAULT == null ? getDocumentation() != null : !DRepresentationImpl.DOCUMENTATION_EDEFAULT.equals(getDocumentation());
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == DModelElement.class) {
            switch (derivedFeatureID) {
            case ViewpointPackage.DREPRESENTATION__EANNOTATIONS:
                return DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS;
            default:
                return -1;
            }
        }
        if (baseClass == DRefreshable.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == DModelElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS:
                return ViewpointPackage.DREPRESENTATION__EANNOTATIONS;
            default:
                return -1;
            }
        }
        if (baseClass == DRefreshable.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} // DRepresentationImpl
