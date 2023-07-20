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
package org.bonitasoft.studio.identity.organization.model.organization;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Organization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Organization#getCustomUserInfoDefinitions <em>Custom User Info Definitions</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Organization#getUsers <em>Users</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Organization#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Organization#getGroups <em>Groups</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Organization#getMemberships <em>Memberships</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Organization#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Organization#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Organization#getModelVersion <em>Model Version</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getOrganization()
 * @model extendedMetaData="name='Organization' kind='elementOnly'"
 * @generated
 */
public interface Organization extends EObject {
	/**
     * Returns the value of the '<em><b>Custom User Info Definitions</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Custom User Info Definitions</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Custom User Info Definitions</em>' containment reference.
     * @see #setCustomUserInfoDefinitions(CustomUserInfoDefinitions)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getOrganization_CustomUserInfoDefinitions()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='customUserInfoDefinitions'"
     * @generated
     */
	CustomUserInfoDefinitions getCustomUserInfoDefinitions();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Organization#getCustomUserInfoDefinitions <em>Custom User Info Definitions</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Custom User Info Definitions</em>' containment reference.
     * @see #getCustomUserInfoDefinitions()
     * @generated
     */
	void setCustomUserInfoDefinitions(CustomUserInfoDefinitions value);

	/**
     * Returns the value of the '<em><b>Users</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Users</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Users</em>' containment reference.
     * @see #setUsers(Users)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getOrganization_Users()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='users'"
     * @generated
     */
	Users getUsers();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Organization#getUsers <em>Users</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Users</em>' containment reference.
     * @see #getUsers()
     * @generated
     */
	void setUsers(Users value);

	/**
     * Returns the value of the '<em><b>Roles</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Roles</em>' containment reference.
     * @see #setRoles(Roles)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getOrganization_Roles()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='roles'"
     * @generated
     */
	Roles getRoles();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Organization#getRoles <em>Roles</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Roles</em>' containment reference.
     * @see #getRoles()
     * @generated
     */
	void setRoles(Roles value);

	/**
     * Returns the value of the '<em><b>Groups</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groups</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Groups</em>' containment reference.
     * @see #setGroups(Groups)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getOrganization_Groups()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='groups'"
     * @generated
     */
	Groups getGroups();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Organization#getGroups <em>Groups</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Groups</em>' containment reference.
     * @see #getGroups()
     * @generated
     */
	void setGroups(Groups value);

	/**
     * Returns the value of the '<em><b>Memberships</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Memberships</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Memberships</em>' containment reference.
     * @see #setMemberships(Memberships)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getOrganization_Memberships()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='memberships'"
     * @generated
     */
	Memberships getMemberships();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Organization#getMemberships <em>Memberships</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Memberships</em>' containment reference.
     * @see #getMemberships()
     * @generated
     */
	void setMemberships(Memberships value);

	/**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getOrganization_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Organization#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getOrganization_Description()
     * @model
     * @generated
     */
	String getDescription();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Organization#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Model Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Model Version</em>' attribute.
     * @see #setModelVersion(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getOrganization_ModelVersion()
     * @model
     * @generated
     */
    String getModelVersion();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Organization#getModelVersion <em>Model Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Model Version</em>' attribute.
     * @see #getModelVersion()
     * @generated
     */
    void setModelVersion(String value);

} // Organization
