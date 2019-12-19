/**
 */
package org.bonitasoft.studio.businessobject.editor.model.impl;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.Field;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.FieldImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.FieldImpl#isNullable <em>Nullable</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.FieldImpl#isCollection <em>Collection</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class FieldImpl extends MinimalEObjectImpl.Container implements Field {
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
     * The default value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNullable()
     * @generated
     * @ordered
     */
    protected static final boolean NULLABLE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNullable()
     * @generated
     * @ordered
     */
    protected boolean nullable = NULLABLE_EDEFAULT;

    /**
     * The default value of the '{@link #isCollection() <em>Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCollection()
     * @generated
     * @ordered
     */
    protected static final boolean COLLECTION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isCollection() <em>Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCollection()
     * @generated
     * @ordered
     */
    protected boolean collection = COLLECTION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FieldImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BusinessDataModelPackage.Literals.FIELD;
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
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.FIELD__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNullable(boolean newNullable) {
        boolean oldNullable = nullable;
        nullable = newNullable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.FIELD__NULLABLE, oldNullable, nullable));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isCollection() {
        return collection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCollection(boolean newCollection) {
        boolean oldCollection = collection;
        collection = newCollection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.FIELD__COLLECTION, oldCollection, collection));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BusinessDataModelPackage.FIELD__NAME:
                return getName();
            case BusinessDataModelPackage.FIELD__NULLABLE:
                return isNullable();
            case BusinessDataModelPackage.FIELD__COLLECTION:
                return isCollection();
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
            case BusinessDataModelPackage.FIELD__NAME:
                setName((String)newValue);
                return;
            case BusinessDataModelPackage.FIELD__NULLABLE:
                setNullable((Boolean)newValue);
                return;
            case BusinessDataModelPackage.FIELD__COLLECTION:
                setCollection((Boolean)newValue);
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
            case BusinessDataModelPackage.FIELD__NAME:
                setName(NAME_EDEFAULT);
                return;
            case BusinessDataModelPackage.FIELD__NULLABLE:
                setNullable(NULLABLE_EDEFAULT);
                return;
            case BusinessDataModelPackage.FIELD__COLLECTION:
                setCollection(COLLECTION_EDEFAULT);
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
            case BusinessDataModelPackage.FIELD__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case BusinessDataModelPackage.FIELD__NULLABLE:
                return nullable != NULLABLE_EDEFAULT;
            case BusinessDataModelPackage.FIELD__COLLECTION:
                return collection != COLLECTION_EDEFAULT;
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
        result.append(", nullable: ");
        result.append(nullable);
        result.append(", collection: ");
        result.append(collection);
        result.append(')');
        return result.toString();
    }

} //FieldImpl
