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

import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.identity.organization.model.organization.Groups;
import org.bonitasoft.studio.identity.organization.model.organization.Memberships;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.Roles;
import org.bonitasoft.studio.identity.organization.model.organization.Users;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Organization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.OrganizationImpl#getCustomUserInfoDefinitions <em>Custom User Info Definitions</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.OrganizationImpl#getUsers <em>Users</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.OrganizationImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.OrganizationImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.OrganizationImpl#getMemberships <em>Memberships</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.OrganizationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.OrganizationImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.OrganizationImpl#getModelVersion <em>Model Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OrganizationImpl extends EObjectImpl implements Organization {
	/**
     * The cached value of the '{@link #getCustomUserInfoDefinitions() <em>Custom User Info Definitions</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCustomUserInfoDefinitions()
     * @generated
     * @ordered
     */
	protected CustomUserInfoDefinitions customUserInfoDefinitions;

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
     * The cached value of the '{@link #getRoles() <em>Roles</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRoles()
     * @generated
     * @ordered
     */
	protected Roles roles;

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
	protected Memberships memberships;

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
     * The default value of the '{@link #getModelVersion() <em>Model Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModelVersion()
     * @generated
     * @ordered
     */
    protected static final String MODEL_VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getModelVersion() <em>Model Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModelVersion()
     * @generated
     * @ordered
     */
    protected String modelVersion = MODEL_VERSION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected OrganizationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OrganizationPackage.Literals.ORGANIZATION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public CustomUserInfoDefinitions getCustomUserInfoDefinitions() {
        return customUserInfoDefinitions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetCustomUserInfoDefinitions(CustomUserInfoDefinitions newCustomUserInfoDefinitions, NotificationChain msgs) {
        CustomUserInfoDefinitions oldCustomUserInfoDefinitions = customUserInfoDefinitions;
        customUserInfoDefinitions = newCustomUserInfoDefinitions;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS, oldCustomUserInfoDefinitions, newCustomUserInfoDefinitions);
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
    public void setCustomUserInfoDefinitions(CustomUserInfoDefinitions newCustomUserInfoDefinitions) {
        if (newCustomUserInfoDefinitions != customUserInfoDefinitions) {
            NotificationChain msgs = null;
            if (customUserInfoDefinitions != null)
                msgs = ((InternalEObject)customUserInfoDefinitions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS, null, msgs);
            if (newCustomUserInfoDefinitions != null)
                msgs = ((InternalEObject)newCustomUserInfoDefinitions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS, null, msgs);
            msgs = basicSetCustomUserInfoDefinitions(newCustomUserInfoDefinitions, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS, newCustomUserInfoDefinitions, newCustomUserInfoDefinitions));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__USERS, oldUsers, newUsers);
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
                msgs = ((InternalEObject)users).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__USERS, null, msgs);
            if (newUsers != null)
                msgs = ((InternalEObject)newUsers).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__USERS, null, msgs);
            msgs = basicSetUsers(newUsers, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__USERS, newUsers, newUsers));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__ROLES, oldRoles, newRoles);
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
                msgs = ((InternalEObject)roles).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__ROLES, null, msgs);
            if (newRoles != null)
                msgs = ((InternalEObject)newRoles).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__ROLES, null, msgs);
            msgs = basicSetRoles(newRoles, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__ROLES, newRoles, newRoles));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__GROUPS, oldGroups, newGroups);
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
                msgs = ((InternalEObject)groups).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__GROUPS, null, msgs);
            if (newGroups != null)
                msgs = ((InternalEObject)newGroups).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__GROUPS, null, msgs);
            msgs = basicSetGroups(newGroups, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__GROUPS, newGroups, newGroups));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public Memberships getMemberships() {
        return memberships;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMemberships(Memberships newMemberships, NotificationChain msgs) {
        Memberships oldMemberships = memberships;
        memberships = newMemberships;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__MEMBERSHIPS, oldMemberships, newMemberships);
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
    public void setMemberships(Memberships newMemberships) {
        if (newMemberships != memberships) {
            NotificationChain msgs = null;
            if (memberships != null)
                msgs = ((InternalEObject)memberships).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__MEMBERSHIPS, null, msgs);
            if (newMemberships != null)
                msgs = ((InternalEObject)newMemberships).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.ORGANIZATION__MEMBERSHIPS, null, msgs);
            msgs = basicSetMemberships(newMemberships, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__MEMBERSHIPS, newMemberships, newMemberships));
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
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getDescription() {
        return description;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__DESCRIPTION, oldDescription, description));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getModelVersion() {
        return modelVersion;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setModelVersion(String newModelVersion) {
        String oldModelVersion = modelVersion;
        modelVersion = newModelVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ORGANIZATION__MODEL_VERSION, oldModelVersion, modelVersion));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OrganizationPackage.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS:
                return basicSetCustomUserInfoDefinitions(null, msgs);
            case OrganizationPackage.ORGANIZATION__USERS:
                return basicSetUsers(null, msgs);
            case OrganizationPackage.ORGANIZATION__ROLES:
                return basicSetRoles(null, msgs);
            case OrganizationPackage.ORGANIZATION__GROUPS:
                return basicSetGroups(null, msgs);
            case OrganizationPackage.ORGANIZATION__MEMBERSHIPS:
                return basicSetMemberships(null, msgs);
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
            case OrganizationPackage.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS:
                return getCustomUserInfoDefinitions();
            case OrganizationPackage.ORGANIZATION__USERS:
                return getUsers();
            case OrganizationPackage.ORGANIZATION__ROLES:
                return getRoles();
            case OrganizationPackage.ORGANIZATION__GROUPS:
                return getGroups();
            case OrganizationPackage.ORGANIZATION__MEMBERSHIPS:
                return getMemberships();
            case OrganizationPackage.ORGANIZATION__NAME:
                return getName();
            case OrganizationPackage.ORGANIZATION__DESCRIPTION:
                return getDescription();
            case OrganizationPackage.ORGANIZATION__MODEL_VERSION:
                return getModelVersion();
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
            case OrganizationPackage.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS:
                setCustomUserInfoDefinitions((CustomUserInfoDefinitions)newValue);
                return;
            case OrganizationPackage.ORGANIZATION__USERS:
                setUsers((Users)newValue);
                return;
            case OrganizationPackage.ORGANIZATION__ROLES:
                setRoles((Roles)newValue);
                return;
            case OrganizationPackage.ORGANIZATION__GROUPS:
                setGroups((Groups)newValue);
                return;
            case OrganizationPackage.ORGANIZATION__MEMBERSHIPS:
                setMemberships((Memberships)newValue);
                return;
            case OrganizationPackage.ORGANIZATION__NAME:
                setName((String)newValue);
                return;
            case OrganizationPackage.ORGANIZATION__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case OrganizationPackage.ORGANIZATION__MODEL_VERSION:
                setModelVersion((String)newValue);
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
            case OrganizationPackage.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS:
                setCustomUserInfoDefinitions((CustomUserInfoDefinitions)null);
                return;
            case OrganizationPackage.ORGANIZATION__USERS:
                setUsers((Users)null);
                return;
            case OrganizationPackage.ORGANIZATION__ROLES:
                setRoles((Roles)null);
                return;
            case OrganizationPackage.ORGANIZATION__GROUPS:
                setGroups((Groups)null);
                return;
            case OrganizationPackage.ORGANIZATION__MEMBERSHIPS:
                setMemberships((Memberships)null);
                return;
            case OrganizationPackage.ORGANIZATION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case OrganizationPackage.ORGANIZATION__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case OrganizationPackage.ORGANIZATION__MODEL_VERSION:
                setModelVersion(MODEL_VERSION_EDEFAULT);
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
            case OrganizationPackage.ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS:
                return customUserInfoDefinitions != null;
            case OrganizationPackage.ORGANIZATION__USERS:
                return users != null;
            case OrganizationPackage.ORGANIZATION__ROLES:
                return roles != null;
            case OrganizationPackage.ORGANIZATION__GROUPS:
                return groups != null;
            case OrganizationPackage.ORGANIZATION__MEMBERSHIPS:
                return memberships != null;
            case OrganizationPackage.ORGANIZATION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case OrganizationPackage.ORGANIZATION__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case OrganizationPackage.ORGANIZATION__MODEL_VERSION:
                return MODEL_VERSION_EDEFAULT == null ? modelVersion != null : !MODEL_VERSION_EDEFAULT.equals(modelVersion);
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
        result.append(", modelVersion: ");
        result.append(modelVersion);
        result.append(')');
        return result.toString();
    }

} //OrganizationImpl
