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
package org.bonitasoft.studio.identity.organization.model.organization.impl;

import java.util.Collection;

import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom User Info Definitions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.CustomUserInfoDefinitionsImpl#getCustomUserInfoDefinition <em>Custom User Info Definition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomUserInfoDefinitionsImpl extends EObjectImpl implements CustomUserInfoDefinitions {
	/**
     * The cached value of the '{@link #getCustomUserInfoDefinition() <em>Custom User Info Definition</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCustomUserInfoDefinition()
     * @generated
     * @ordered
     */
	protected EList<CustomUserInfoDefinition> customUserInfoDefinition;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected CustomUserInfoDefinitionsImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OrganizationPackage.Literals.CUSTOM_USER_INFO_DEFINITIONS;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EList<CustomUserInfoDefinition> getCustomUserInfoDefinition() {
        if (customUserInfoDefinition == null) {
            customUserInfoDefinition = new EObjectContainmentEList<CustomUserInfoDefinition>(CustomUserInfoDefinition.class, this, OrganizationPackage.CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION);
        }
        return customUserInfoDefinition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OrganizationPackage.CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION:
                return ((InternalEList<?>)getCustomUserInfoDefinition()).basicRemove(otherEnd, msgs);
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
            case OrganizationPackage.CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION:
                return getCustomUserInfoDefinition();
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
            case OrganizationPackage.CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION:
                getCustomUserInfoDefinition().clear();
                getCustomUserInfoDefinition().addAll((Collection<? extends CustomUserInfoDefinition>)newValue);
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
            case OrganizationPackage.CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION:
                getCustomUserInfoDefinition().clear();
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
            case OrganizationPackage.CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION:
                return customUserInfoDefinition != null && !customUserInfoDefinition.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //CustomUserInfoDefinitionsImpl
