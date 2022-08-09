/**
 */
package org.bonitasoft.studio.businessobject.editor.model.impl;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.FetchType;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relation Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.RelationFieldImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.RelationFieldImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.RelationFieldImpl#getFetchType <em>Fetch Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationFieldImpl extends FieldImpl implements RelationField {
    /**
     * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReference()
     * @generated
     * @ordered
     */
    protected BusinessObject reference;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final RelationType TYPE_EDEFAULT = RelationType.AGGREGATION;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected RelationType type = TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getFetchType() <em>Fetch Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFetchType()
     * @generated
     * @ordered
     */
    protected static final FetchType FETCH_TYPE_EDEFAULT = FetchType.LAZY;

    /**
     * The cached value of the '{@link #getFetchType() <em>Fetch Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFetchType()
     * @generated
     * @ordered
     */
    protected FetchType fetchType = FETCH_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RelationFieldImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BusinessDataModelPackage.Literals.RELATION_FIELD;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessObject getReference() {
        if (reference != null && reference.eIsProxy()) {
            InternalEObject oldReference = (InternalEObject)reference;
            reference = (BusinessObject)eResolveProxy(oldReference);
            if (reference != oldReference) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BusinessDataModelPackage.RELATION_FIELD__REFERENCE, oldReference, reference));
            }
        }
        return reference;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessObject basicGetReference() {
        return reference;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReference(BusinessObject newReference) {
        BusinessObject oldReference = reference;
        reference = newReference;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.RELATION_FIELD__REFERENCE, oldReference, reference));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RelationType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(RelationType newType) {
        RelationType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.RELATION_FIELD__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FetchType getFetchType() {
        return fetchType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFetchType(FetchType newFetchType) {
        FetchType oldFetchType = fetchType;
        fetchType = newFetchType == null ? FETCH_TYPE_EDEFAULT : newFetchType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.RELATION_FIELD__FETCH_TYPE, oldFetchType, fetchType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BusinessDataModelPackage.RELATION_FIELD__REFERENCE:
                if (resolve) return getReference();
                return basicGetReference();
            case BusinessDataModelPackage.RELATION_FIELD__TYPE:
                return getType();
            case BusinessDataModelPackage.RELATION_FIELD__FETCH_TYPE:
                return getFetchType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case BusinessDataModelPackage.RELATION_FIELD__REFERENCE:
                setReference((BusinessObject)newValue);
                return;
            case BusinessDataModelPackage.RELATION_FIELD__TYPE:
                setType((RelationType)newValue);
                return;
            case BusinessDataModelPackage.RELATION_FIELD__FETCH_TYPE:
                setFetchType((FetchType)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case BusinessDataModelPackage.RELATION_FIELD__REFERENCE:
                setReference((BusinessObject)null);
                return;
            case BusinessDataModelPackage.RELATION_FIELD__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case BusinessDataModelPackage.RELATION_FIELD__FETCH_TYPE:
                setFetchType(FETCH_TYPE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case BusinessDataModelPackage.RELATION_FIELD__REFERENCE:
                return reference != null;
            case BusinessDataModelPackage.RELATION_FIELD__TYPE:
                return type != TYPE_EDEFAULT;
            case BusinessDataModelPackage.RELATION_FIELD__FETCH_TYPE:
                return fetchType != FETCH_TYPE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (type: ");
        result.append(type);
        result.append(", fetchType: ");
        result.append(fetchType);
        result.append(')');
        return result.toString();
    }

} //RelationFieldImpl
