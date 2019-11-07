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

import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.Memberships;
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
 * An implementation of the model object '<em><b>Memberships</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipsImpl#getMembership <em>Membership</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MembershipsImpl extends EObjectImpl implements Memberships {
	/**
     * The cached value of the '{@link #getMembership() <em>Membership</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMembership()
     * @generated
     * @ordered
     */
	protected EList<Membership> membership;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MembershipsImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OrganizationPackage.Literals.MEMBERSHIPS;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Membership> getMembership() {
        if (membership == null) {
            membership = new EObjectContainmentEList<Membership>(Membership.class, this, OrganizationPackage.MEMBERSHIPS__MEMBERSHIP);
        }
        return membership;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OrganizationPackage.MEMBERSHIPS__MEMBERSHIP:
                return ((InternalEList<?>)getMembership()).basicRemove(otherEnd, msgs);
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
            case OrganizationPackage.MEMBERSHIPS__MEMBERSHIP:
                return getMembership();
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
            case OrganizationPackage.MEMBERSHIPS__MEMBERSHIP:
                getMembership().clear();
                getMembership().addAll((Collection<? extends Membership>)newValue);
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
            case OrganizationPackage.MEMBERSHIPS__MEMBERSHIP:
                getMembership().clear();
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
            case OrganizationPackage.MEMBERSHIPS__MEMBERSHIP:
                return membership != null && !membership.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //MembershipsImpl
