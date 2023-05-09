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
package org.bonitasoft.studio.model.actormapping;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.actormapping.ActorMappingPackage
 * @generated
 */
public interface ActorMappingFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ActorMappingFactory eINSTANCE = org.bonitasoft.studio.model.actormapping.impl.ActorMappingFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Actor Mapping</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Actor Mapping</em>'.
     * @generated
     */
	ActorMapping createActorMapping();

	/**
     * Returns a new object of class '<em>Actor Mappings Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Actor Mappings Type</em>'.
     * @generated
     */
	ActorMappingsType createActorMappingsType();

	/**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
	DocumentRoot createDocumentRoot();

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
     * Returns a new object of class '<em>Membership Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Membership Type</em>'.
     * @generated
     */
	MembershipType createMembershipType();

	/**
     * Returns a new object of class '<em>Roles</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Roles</em>'.
     * @generated
     */
	Roles createRoles();

	/**
     * Returns a new object of class '<em>Users</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Users</em>'.
     * @generated
     */
	Users createUsers();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	ActorMappingPackage getActorMappingPackage();

} //ActorMappingFactory
