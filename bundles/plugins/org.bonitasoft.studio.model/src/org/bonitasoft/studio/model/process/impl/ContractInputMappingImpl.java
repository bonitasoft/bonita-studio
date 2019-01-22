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

import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contract Input Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputMappingImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputMappingImpl#getSetterName <em>Setter Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputMappingImpl#getSetterParamType <em>Setter Param Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContractInputMappingImpl extends EObjectImpl implements ContractInputMapping {
	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected Data data;

	/**
	 * The default value of the '{@link #getSetterName() <em>Setter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSetterName()
	 * @generated
	 * @ordered
	 */
	protected static final String SETTER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSetterName() <em>Setter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSetterName()
	 * @generated
	 * @ordered
	 */
	protected String setterName = SETTER_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSetterParamType() <em>Setter Param Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSetterParamType()
	 * @generated
	 * @ordered
	 */
	protected static final String SETTER_PARAM_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSetterParamType() <em>Setter Param Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSetterParamType()
	 * @generated
	 * @ordered
	 */
	protected String setterParamType = SETTER_PARAM_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContractInputMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.CONTRACT_INPUT_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Data getData() {
		if (data != null && data.eIsProxy()) {
			InternalEObject oldData = (InternalEObject)data;
			data = (Data)eResolveProxy(oldData);
			if (data != oldData) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.CONTRACT_INPUT_MAPPING__DATA, oldData, data));
			}
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Data basicGetData() {
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setData(Data newData) {
		Data oldData = data;
		data = newData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT_MAPPING__DATA, oldData, data));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSetterName() {
		return setterName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSetterName(String newSetterName) {
		String oldSetterName = setterName;
		setterName = newSetterName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_NAME, oldSetterName, setterName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSetterParamType() {
		return setterParamType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSetterParamType(String newSetterParamType) {
		String oldSetterParamType = setterParamType;
		setterParamType = newSetterParamType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE, oldSetterParamType, setterParamType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProcessPackage.CONTRACT_INPUT_MAPPING__DATA:
				if (resolve) return getData();
				return basicGetData();
			case ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_NAME:
				return getSetterName();
			case ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE:
				return getSetterParamType();
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
			case ProcessPackage.CONTRACT_INPUT_MAPPING__DATA:
				setData((Data)newValue);
				return;
			case ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_NAME:
				setSetterName((String)newValue);
				return;
			case ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE:
				setSetterParamType((String)newValue);
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
			case ProcessPackage.CONTRACT_INPUT_MAPPING__DATA:
				setData((Data)null);
				return;
			case ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_NAME:
				setSetterName(SETTER_NAME_EDEFAULT);
				return;
			case ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE:
				setSetterParamType(SETTER_PARAM_TYPE_EDEFAULT);
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
			case ProcessPackage.CONTRACT_INPUT_MAPPING__DATA:
				return data != null;
			case ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_NAME:
				return SETTER_NAME_EDEFAULT == null ? setterName != null : !SETTER_NAME_EDEFAULT.equals(setterName);
			case ProcessPackage.CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE:
				return SETTER_PARAM_TYPE_EDEFAULT == null ? setterParamType != null : !SETTER_PARAM_TYPE_EDEFAULT.equals(setterParamType);
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
		result.append(" (setterName: "); //$NON-NLS-1$
		result.append(setterName);
		result.append(", setterParamType: "); //$NON-NLS-1$
		result.append(setterParamType);
		result.append(')');
		return result.toString();
	}

} //ContractInputMappingImpl
