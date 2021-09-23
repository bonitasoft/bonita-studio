/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.impl;

import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Business Object Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.BusinessObjectDataImpl#getBusinessDataRepositoryId <em>Business Data Repository Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.BusinessObjectDataImpl#isCreateNewInstance <em>Create New Instance</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.BusinessObjectDataImpl#getEClassName <em>EClass Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BusinessObjectDataImpl extends JavaObjectDataImpl implements BusinessObjectData {
	/**
     * The default value of the '{@link #getBusinessDataRepositoryId() <em>Business Data Repository Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBusinessDataRepositoryId()
     * @generated
     * @ordered
     */
	protected static final String BUSINESS_DATA_REPOSITORY_ID_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getBusinessDataRepositoryId() <em>Business Data Repository Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBusinessDataRepositoryId()
     * @generated
     * @ordered
     */
	protected String businessDataRepositoryId = BUSINESS_DATA_REPOSITORY_ID_EDEFAULT;

	/**
     * The default value of the '{@link #isCreateNewInstance() <em>Create New Instance</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isCreateNewInstance()
     * @generated
     * @ordered
     */
	protected static final boolean CREATE_NEW_INSTANCE_EDEFAULT = true;

	/**
     * The cached value of the '{@link #isCreateNewInstance() <em>Create New Instance</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isCreateNewInstance()
     * @generated
     * @ordered
     */
	protected boolean createNewInstance = CREATE_NEW_INSTANCE_EDEFAULT;

	/**
     * The default value of the '{@link #getEClassName() <em>EClass Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEClassName()
     * @generated
     * @ordered
     */
	protected static final String ECLASS_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getEClassName() <em>EClass Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEClassName()
     * @generated
     * @ordered
     */
	protected String eClassName = ECLASS_NAME_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected BusinessObjectDataImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.BUSINESS_OBJECT_DATA;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getBusinessDataRepositoryId() {
        return businessDataRepositoryId;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setBusinessDataRepositoryId(String newBusinessDataRepositoryId) {
        String oldBusinessDataRepositoryId = businessDataRepositoryId;
        businessDataRepositoryId = newBusinessDataRepositoryId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.BUSINESS_OBJECT_DATA__BUSINESS_DATA_REPOSITORY_ID, oldBusinessDataRepositoryId, businessDataRepositoryId));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isCreateNewInstance() {
        return createNewInstance;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setCreateNewInstance(boolean newCreateNewInstance) {
        boolean oldCreateNewInstance = createNewInstance;
        createNewInstance = newCreateNewInstance;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.BUSINESS_OBJECT_DATA__CREATE_NEW_INSTANCE, oldCreateNewInstance, createNewInstance));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getEClassName() {
        return eClassName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setEClassName(String newEClassName) {
        String oldEClassName = eClassName;
        eClassName = newEClassName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.BUSINESS_OBJECT_DATA__ECLASS_NAME, oldEClassName, eClassName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProcessPackage.BUSINESS_OBJECT_DATA__BUSINESS_DATA_REPOSITORY_ID:
                return getBusinessDataRepositoryId();
            case ProcessPackage.BUSINESS_OBJECT_DATA__CREATE_NEW_INSTANCE:
                return isCreateNewInstance();
            case ProcessPackage.BUSINESS_OBJECT_DATA__ECLASS_NAME:
                return getEClassName();
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
            case ProcessPackage.BUSINESS_OBJECT_DATA__BUSINESS_DATA_REPOSITORY_ID:
                setBusinessDataRepositoryId((String)newValue);
                return;
            case ProcessPackage.BUSINESS_OBJECT_DATA__CREATE_NEW_INSTANCE:
                setCreateNewInstance((Boolean)newValue);
                return;
            case ProcessPackage.BUSINESS_OBJECT_DATA__ECLASS_NAME:
                setEClassName((String)newValue);
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
            case ProcessPackage.BUSINESS_OBJECT_DATA__BUSINESS_DATA_REPOSITORY_ID:
                setBusinessDataRepositoryId(BUSINESS_DATA_REPOSITORY_ID_EDEFAULT);
                return;
            case ProcessPackage.BUSINESS_OBJECT_DATA__CREATE_NEW_INSTANCE:
                setCreateNewInstance(CREATE_NEW_INSTANCE_EDEFAULT);
                return;
            case ProcessPackage.BUSINESS_OBJECT_DATA__ECLASS_NAME:
                setEClassName(ECLASS_NAME_EDEFAULT);
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
            case ProcessPackage.BUSINESS_OBJECT_DATA__BUSINESS_DATA_REPOSITORY_ID:
                return BUSINESS_DATA_REPOSITORY_ID_EDEFAULT == null ? businessDataRepositoryId != null : !BUSINESS_DATA_REPOSITORY_ID_EDEFAULT.equals(businessDataRepositoryId);
            case ProcessPackage.BUSINESS_OBJECT_DATA__CREATE_NEW_INSTANCE:
                return createNewInstance != CREATE_NEW_INSTANCE_EDEFAULT;
            case ProcessPackage.BUSINESS_OBJECT_DATA__ECLASS_NAME:
                return ECLASS_NAME_EDEFAULT == null ? eClassName != null : !ECLASS_NAME_EDEFAULT.equals(eClassName);
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
        result.append(" (businessDataRepositoryId: "); //$NON-NLS-1$
        result.append(businessDataRepositoryId);
        result.append(", createNewInstance: "); //$NON-NLS-1$
        result.append(createNewInstance);
        result.append(", eClassName: "); //$NON-NLS-1$
        result.append(eClassName);
        result.append(')');
        return result.toString();
    }

} //BusinessObjectDataImpl
