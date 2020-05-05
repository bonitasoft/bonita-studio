/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.actors.model.organization.impl;

import java.util.Collection;

import org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoValuesType;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom User Info Values Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoValuesTypeImpl#getCustomUserInfoValue <em>Custom User Info Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomUserInfoValuesTypeImpl extends EObjectImpl implements CustomUserInfoValuesType {
	/**
     * The cached value of the '{@link #getCustomUserInfoValue() <em>Custom User Info Value</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCustomUserInfoValue()
     * @generated
     * @ordered
     */
	protected EList<CustomUserInfoValue> customUserInfoValue;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected CustomUserInfoValuesTypeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OrganizationPackage.Literals.CUSTOM_USER_INFO_VALUES_TYPE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<CustomUserInfoValue> getCustomUserInfoValue() {
        if (customUserInfoValue == null) {
            customUserInfoValue = new EObjectContainmentEList<CustomUserInfoValue>(CustomUserInfoValue.class, this, OrganizationPackage.CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE);
        }
        return customUserInfoValue;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OrganizationPackage.CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE:
                return ((InternalEList<?>)getCustomUserInfoValue()).basicRemove(otherEnd, msgs);
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
            case OrganizationPackage.CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE:
                return getCustomUserInfoValue();
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
            case OrganizationPackage.CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE:
                getCustomUserInfoValue().clear();
                getCustomUserInfoValue().addAll((Collection<? extends CustomUserInfoValue>)newValue);
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
            case OrganizationPackage.CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE:
                getCustomUserInfoValue().clear();
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
            case OrganizationPackage.CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE:
                return customUserInfoValue != null && !customUserInfoValue.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //CustomUserInfoValuesTypeImpl
