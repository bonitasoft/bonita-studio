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
package org.bonitasoft.studio.actors.model.organization;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage
 * @generated
 */
public interface OrganizationFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	OrganizationFactory eINSTANCE = org.bonitasoft.studio.actors.model.organization.impl.OrganizationFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Contact Data</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Contact Data</em>'.
     * @generated
     */
	ContactData createContactData();

	/**
     * Returns a new object of class '<em>Custom User Info Definition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Custom User Info Definition</em>'.
     * @generated
     */
	CustomUserInfoDefinition createCustomUserInfoDefinition();

	/**
     * Returns a new object of class '<em>Custom User Info Definitions</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Custom User Info Definitions</em>'.
     * @generated
     */
	CustomUserInfoDefinitions createCustomUserInfoDefinitions();

	/**
     * Returns a new object of class '<em>Custom User Info Value</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Custom User Info Value</em>'.
     * @generated
     */
	CustomUserInfoValue createCustomUserInfoValue();

	/**
     * Returns a new object of class '<em>Custom User Info Values Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Custom User Info Values Type</em>'.
     * @generated
     */
	CustomUserInfoValuesType createCustomUserInfoValuesType();

	/**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
	DocumentRoot createDocumentRoot();

	/**
     * Returns a new object of class '<em>Group</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Group</em>'.
     * @generated
     */
	Group createGroup();

	/**
     * Returns a new object of class '<em>Groups</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Groups</em>'.
     * @generated
     */
	Groups createGroups();

	/**
     * Returns a new object of class '<em>Membership</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Membership</em>'.
     * @generated
     */
	Membership createMembership();

	/**
     * Returns a new object of class '<em>Memberships</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Memberships</em>'.
     * @generated
     */
	Memberships createMemberships();

	/**
     * Returns a new object of class '<em>Metadata</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Metadata</em>'.
     * @generated
     */
	Metadata createMetadata();

	/**
     * Returns a new object of class '<em>Meta Datas Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Meta Datas Type</em>'.
     * @generated
     */
	MetaDatasType createMetaDatasType();

	/**
     * Returns a new object of class '<em>Organization</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Organization</em>'.
     * @generated
     */
	Organization createOrganization();

	/**
     * Returns a new object of class '<em>Role</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Role</em>'.
     * @generated
     */
	Role createRole();

	/**
     * Returns a new object of class '<em>Roles</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Roles</em>'.
     * @generated
     */
	Roles createRoles();

	/**
     * Returns a new object of class '<em>User</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>User</em>'.
     * @generated
     */
	User createUser();

	/**
     * Returns a new object of class '<em>Users</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Users</em>'.
     * @generated
     */
	Users createUsers();

	/**
     * Returns a new object of class '<em>Password Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Password Type</em>'.
     * @generated
     */
	PasswordType createPasswordType();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	OrganizationPackage getOrganizationPackage();

} //OrganizationFactory
