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
package org.bonitasoft.studio.model.actormapping.impl;

import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.actormapping.Membership;
import org.bonitasoft.studio.model.actormapping.Roles;
import org.bonitasoft.studio.model.actormapping.Users;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingImpl#getMemberships <em>Memberships</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingImpl#getUsers <em>Users</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActorMappingImpl extends EObjectImpl implements ActorMapping {
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
     * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGroups()
     * @generated
     * @ordered
     */
	protected Groups groups;

	/**
     * The cached value of the '{@link #getMemberships() <em>Memberships</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMemberships()
     * @generated
     * @ordered
     */
	protected Membership memberships;

	/**
     * The cached value of the '{@link #getRoles() <em>Roles</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRoles()
     * @generated
     * @ordered
     */
	protected Roles roles;

	/**
     * The cached value of the '{@link #getUsers() <em>Users</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUsers()
     * @generated
     * @ordered
     */
	protected Users users;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ActorMappingImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ActorMappingPackage.Literals.ACTOR_MAPPING;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ActorMappingPackage.ACTOR_MAPPING__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Groups getGroups() {
        return groups;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetGroups(Groups newGroups, NotificationChain msgs) {
        Groups oldGroups = groups;
        groups = newGroups;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActorMappingPackage.ACTOR_MAPPING__GROUPS, oldGroups, newGroups);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setGroups(Groups newGroups) {
        if (newGroups != groups) {
            NotificationChain msgs = null;
            if (groups != null)
                msgs = ((InternalEObject)groups).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActorMappingPackage.ACTOR_MAPPING__GROUPS, null, msgs);
            if (newGroups != null)
                msgs = ((InternalEObject)newGroups).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActorMappingPackage.ACTOR_MAPPING__GROUPS, null, msgs);
            msgs = basicSetGroups(newGroups, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ActorMappingPackage.ACTOR_MAPPING__GROUPS, newGroups, newGroups));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Membership getMemberships() {
        return memberships;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMemberships(Membership newMemberships, NotificationChain msgs) {
        Membership oldMemberships = memberships;
        memberships = newMemberships;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActorMappingPackage.ACTOR_MAPPING__MEMBERSHIPS, oldMemberships, newMemberships);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMemberships(Membership newMemberships) {
        if (newMemberships != memberships) {
            NotificationChain msgs = null;
            if (memberships != null)
                msgs = ((InternalEObject)memberships).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActorMappingPackage.ACTOR_MAPPING__MEMBERSHIPS, null, msgs);
            if (newMemberships != null)
                msgs = ((InternalEObject)newMemberships).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActorMappingPackage.ACTOR_MAPPING__MEMBERSHIPS, null, msgs);
            msgs = basicSetMemberships(newMemberships, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ActorMappingPackage.ACTOR_MAPPING__MEMBERSHIPS, newMemberships, newMemberships));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Roles getRoles() {
        return roles;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetRoles(Roles newRoles, NotificationChain msgs) {
        Roles oldRoles = roles;
        roles = newRoles;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActorMappingPackage.ACTOR_MAPPING__ROLES, oldRoles, newRoles);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setRoles(Roles newRoles) {
        if (newRoles != roles) {
            NotificationChain msgs = null;
            if (roles != null)
                msgs = ((InternalEObject)roles).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActorMappingPackage.ACTOR_MAPPING__ROLES, null, msgs);
            if (newRoles != null)
                msgs = ((InternalEObject)newRoles).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActorMappingPackage.ACTOR_MAPPING__ROLES, null, msgs);
            msgs = basicSetRoles(newRoles, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ActorMappingPackage.ACTOR_MAPPING__ROLES, newRoles, newRoles));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Users getUsers() {
        return users;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUsers(Users newUsers, NotificationChain msgs) {
        Users oldUsers = users;
        users = newUsers;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActorMappingPackage.ACTOR_MAPPING__USERS, oldUsers, newUsers);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUsers(Users newUsers) {
        if (newUsers != users) {
            NotificationChain msgs = null;
            if (users != null)
                msgs = ((InternalEObject)users).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActorMappingPackage.ACTOR_MAPPING__USERS, null, msgs);
            if (newUsers != null)
                msgs = ((InternalEObject)newUsers).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActorMappingPackage.ACTOR_MAPPING__USERS, null, msgs);
            msgs = basicSetUsers(newUsers, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ActorMappingPackage.ACTOR_MAPPING__USERS, newUsers, newUsers));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ActorMappingPackage.ACTOR_MAPPING__GROUPS:
                return basicSetGroups(null, msgs);
            case ActorMappingPackage.ACTOR_MAPPING__MEMBERSHIPS:
                return basicSetMemberships(null, msgs);
            case ActorMappingPackage.ACTOR_MAPPING__ROLES:
                return basicSetRoles(null, msgs);
            case ActorMappingPackage.ACTOR_MAPPING__USERS:
                return basicSetUsers(null, msgs);
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
            case ActorMappingPackage.ACTOR_MAPPING__NAME:
                return getName();
            case ActorMappingPackage.ACTOR_MAPPING__GROUPS:
                return getGroups();
            case ActorMappingPackage.ACTOR_MAPPING__MEMBERSHIPS:
                return getMemberships();
            case ActorMappingPackage.ACTOR_MAPPING__ROLES:
                return getRoles();
            case ActorMappingPackage.ACTOR_MAPPING__USERS:
                return getUsers();
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
            case ActorMappingPackage.ACTOR_MAPPING__NAME:
                setName((String)newValue);
                return;
            case ActorMappingPackage.ACTOR_MAPPING__GROUPS:
                setGroups((Groups)newValue);
                return;
            case ActorMappingPackage.ACTOR_MAPPING__MEMBERSHIPS:
                setMemberships((Membership)newValue);
                return;
            case ActorMappingPackage.ACTOR_MAPPING__ROLES:
                setRoles((Roles)newValue);
                return;
            case ActorMappingPackage.ACTOR_MAPPING__USERS:
                setUsers((Users)newValue);
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
            case ActorMappingPackage.ACTOR_MAPPING__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ActorMappingPackage.ACTOR_MAPPING__GROUPS:
                setGroups((Groups)null);
                return;
            case ActorMappingPackage.ACTOR_MAPPING__MEMBERSHIPS:
                setMemberships((Membership)null);
                return;
            case ActorMappingPackage.ACTOR_MAPPING__ROLES:
                setRoles((Roles)null);
                return;
            case ActorMappingPackage.ACTOR_MAPPING__USERS:
                setUsers((Users)null);
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
            case ActorMappingPackage.ACTOR_MAPPING__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ActorMappingPackage.ACTOR_MAPPING__GROUPS:
                return groups != null;
            case ActorMappingPackage.ACTOR_MAPPING__MEMBERSHIPS:
                return memberships != null;
            case ActorMappingPackage.ACTOR_MAPPING__ROLES:
                return roles != null;
            case ActorMappingPackage.ACTOR_MAPPING__USERS:
                return users != null;
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //ActorMappingImpl
