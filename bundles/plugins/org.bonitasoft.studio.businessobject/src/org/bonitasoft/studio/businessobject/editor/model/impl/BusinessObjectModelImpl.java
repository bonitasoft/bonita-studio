/**
 */
package org.bonitasoft.studio.businessobject.editor.model.impl;

import java.util.Collection;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Business Object Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectModelImpl#getPackages <em>Packages</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectModelImpl#getGroupId <em>Group Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BusinessObjectModelImpl extends MinimalEObjectImpl.Container implements BusinessObjectModel {
    /**
     * The cached value of the '{@link #getPackages() <em>Packages</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPackages()
     * @generated
     * @ordered
     */
    protected EList<org.bonitasoft.studio.businessobject.editor.model.Package> packages;
    /**
     * The default value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroupId()
     * @generated
     * @ordered
     */
    protected static final String GROUP_ID_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroupId()
     * @generated
     * @ordered
     */
    protected String groupId = GROUP_ID_EDEFAULT;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BusinessObjectModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BusinessDataModelPackage.Literals.BUSINESS_OBJECT_MODEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<org.bonitasoft.studio.businessobject.editor.model.Package> getPackages() {
        if (packages == null) {
            packages = new EObjectContainmentEList<org.bonitasoft.studio.businessobject.editor.model.Package>(org.bonitasoft.studio.businessobject.editor.model.Package.class, this, BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__PACKAGES);
        }
        return packages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroupId(String newGroupId) {
        String oldGroupId = groupId;
        groupId = newGroupId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__GROUP_ID, oldGroupId, groupId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__PACKAGES:
                return ((InternalEList<?>)getPackages()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__PACKAGES:
                return getPackages();
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__GROUP_ID:
                return getGroupId();
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
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__PACKAGES:
                getPackages().clear();
                getPackages().addAll((Collection<? extends org.bonitasoft.studio.businessobject.editor.model.Package>)newValue);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__GROUP_ID:
                setGroupId((String)newValue);
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
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__PACKAGES:
                getPackages().clear();
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__GROUP_ID:
                setGroupId(GROUP_ID_EDEFAULT);
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
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__PACKAGES:
                return packages != null && !packages.isEmpty();
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL__GROUP_ID:
                return GROUP_ID_EDEFAULT == null ? groupId != null : !GROUP_ID_EDEFAULT.equals(groupId);
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
        result.append(" (groupId: ");
        result.append(groupId);
        result.append(')');
        return result.toString();
    }

} //BusinessObjectModelImpl
