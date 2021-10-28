/**
 */
package org.bonitasoft.studio.businessobject.editor.model.impl;

import java.util.Collection;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unique Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.UniqueConstraintImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.UniqueConstraintImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.UniqueConstraintImpl#getFieldNames <em>Field Names</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UniqueConstraintImpl extends MinimalEObjectImpl.Container implements UniqueConstraint {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getFieldNames() <em>Field Names</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFieldNames()
     * @generated
     * @ordered
     */
    protected EList<String> fieldNames;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UniqueConstraintImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BusinessDataModelPackage.Literals.UNIQUE_CONSTRAINT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.UNIQUE_CONSTRAINT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.UNIQUE_CONSTRAINT__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getFieldNames() {
        if (fieldNames == null) {
            fieldNames = new EDataTypeEList<String>(String.class, this, BusinessDataModelPackage.UNIQUE_CONSTRAINT__FIELD_NAMES);
        }
        return fieldNames;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__NAME:
                return getName();
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__DESCRIPTION:
                return getDescription();
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__FIELD_NAMES:
                return getFieldNames();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__NAME:
                setName((String)newValue);
                return;
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__FIELD_NAMES:
                getFieldNames().clear();
                getFieldNames().addAll((Collection<? extends String>)newValue);
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
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__FIELD_NAMES:
                getFieldNames().clear();
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
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT__FIELD_NAMES:
                return fieldNames != null && !fieldNames.isEmpty();
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
        result.append(" (name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(", fieldNames: ");
        result.append(fieldNames);
        result.append(')');
        return result.toString();
    }

} //UniqueConstraintImpl
