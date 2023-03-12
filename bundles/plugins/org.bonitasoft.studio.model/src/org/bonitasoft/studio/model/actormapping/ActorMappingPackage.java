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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 			Actor Mapping Schema 1.0 for Bonita Open Solution.
 * 			Copyright (C) 2011 BonitaSoft S.A.
 * 		
 * <!-- end-model-doc -->
 * @see org.bonitasoft.studio.model.actormapping.ActorMappingFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='process.history'"
 * @generated
 */
public interface ActorMappingPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "actormapping"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/actormapping/6.0"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "actormapping"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ActorMappingPackage eINSTANCE = org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingImpl <em>Actor Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingImpl
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getActorMapping()
     * @generated
     */
	int ACTOR_MAPPING = 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_MAPPING__NAME = 0;

	/**
     * The feature id for the '<em><b>Groups</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_MAPPING__GROUPS = 1;

	/**
     * The feature id for the '<em><b>Memberships</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_MAPPING__MEMBERSHIPS = 2;

	/**
     * The feature id for the '<em><b>Roles</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_MAPPING__ROLES = 3;

	/**
     * The feature id for the '<em><b>Users</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_MAPPING__USERS = 4;

	/**
     * The number of structural features of the '<em>Actor Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_MAPPING_FEATURE_COUNT = 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingsTypeImpl <em>Actor Mappings Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingsTypeImpl
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getActorMappingsType()
     * @generated
     */
	int ACTOR_MAPPINGS_TYPE = 1;

	/**
     * The feature id for the '<em><b>Actor Mapping</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING = 0;

	/**
     * The number of structural features of the '<em>Actor Mappings Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR_MAPPINGS_TYPE_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.actormapping.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.actormapping.impl.DocumentRootImpl
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getDocumentRoot()
     * @generated
     */
	int DOCUMENT_ROOT = 2;

	/**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
     * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
     * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
     * The feature id for the '<em><b>Actor Mappings</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_ROOT__ACTOR_MAPPINGS = 3;

	/**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.actormapping.impl.GroupsImpl <em>Groups</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.actormapping.impl.GroupsImpl
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getGroups()
     * @generated
     */
	int GROUPS = 3;

	/**
     * The feature id for the '<em><b>Group</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPS__GROUP = 0;

	/**
     * The number of structural features of the '<em>Groups</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPS_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.actormapping.impl.MembershipImpl <em>Membership</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.actormapping.impl.MembershipImpl
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getMembership()
     * @generated
     */
	int MEMBERSHIP = 4;

	/**
     * The feature id for the '<em><b>Membership</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP__MEMBERSHIP = 0;

	/**
     * The number of structural features of the '<em>Membership</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.actormapping.impl.MembershipTypeImpl <em>Membership Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.actormapping.impl.MembershipTypeImpl
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getMembershipType()
     * @generated
     */
	int MEMBERSHIP_TYPE = 5;

	/**
     * The feature id for the '<em><b>Group</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP_TYPE__GROUP = 0;

	/**
     * The feature id for the '<em><b>Role</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP_TYPE__ROLE = 1;

	/**
     * The number of structural features of the '<em>Membership Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP_TYPE_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.actormapping.impl.RolesImpl <em>Roles</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.actormapping.impl.RolesImpl
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getRoles()
     * @generated
     */
	int ROLES = 6;

	/**
     * The feature id for the '<em><b>Role</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLES__ROLE = 0;

	/**
     * The number of structural features of the '<em>Roles</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLES_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.actormapping.impl.UsersImpl <em>Users</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.actormapping.impl.UsersImpl
     * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getUsers()
     * @generated
     */
	int USERS = 7;

	/**
     * The feature id for the '<em><b>User</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USERS__USER = 0;

	/**
     * The number of structural features of the '<em>Users</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USERS_FEATURE_COUNT = 1;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.actormapping.ActorMapping <em>Actor Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Actor Mapping</em>'.
     * @see org.bonitasoft.studio.model.actormapping.ActorMapping
     * @generated
     */
	EClass getActorMapping();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.actormapping.ActorMapping#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.actormapping.ActorMapping#getName()
     * @see #getActorMapping()
     * @generated
     */
	EAttribute getActorMapping_Name();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.actormapping.ActorMapping#getGroups <em>Groups</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Groups</em>'.
     * @see org.bonitasoft.studio.model.actormapping.ActorMapping#getGroups()
     * @see #getActorMapping()
     * @generated
     */
	EReference getActorMapping_Groups();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.actormapping.ActorMapping#getMemberships <em>Memberships</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Memberships</em>'.
     * @see org.bonitasoft.studio.model.actormapping.ActorMapping#getMemberships()
     * @see #getActorMapping()
     * @generated
     */
	EReference getActorMapping_Memberships();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.actormapping.ActorMapping#getRoles <em>Roles</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Roles</em>'.
     * @see org.bonitasoft.studio.model.actormapping.ActorMapping#getRoles()
     * @see #getActorMapping()
     * @generated
     */
	EReference getActorMapping_Roles();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.actormapping.ActorMapping#getUsers <em>Users</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Users</em>'.
     * @see org.bonitasoft.studio.model.actormapping.ActorMapping#getUsers()
     * @see #getActorMapping()
     * @generated
     */
	EReference getActorMapping_Users();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.actormapping.ActorMappingsType <em>Actor Mappings Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Actor Mappings Type</em>'.
     * @see org.bonitasoft.studio.model.actormapping.ActorMappingsType
     * @generated
     */
	EClass getActorMappingsType();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.actormapping.ActorMappingsType#getActorMapping <em>Actor Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Actor Mapping</em>'.
     * @see org.bonitasoft.studio.model.actormapping.ActorMappingsType#getActorMapping()
     * @see #getActorMappingsType()
     * @generated
     */
	EReference getActorMappingsType_ActorMapping();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.actormapping.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document Root</em>'.
     * @see org.bonitasoft.studio.model.actormapping.DocumentRoot
     * @generated
     */
	EClass getDocumentRoot();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.actormapping.DocumentRoot#getMixed <em>Mixed</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.bonitasoft.studio.model.actormapping.DocumentRoot#getMixed()
     * @see #getDocumentRoot()
     * @generated
     */
	EAttribute getDocumentRoot_Mixed();

	/**
     * Returns the meta object for the map '{@link org.bonitasoft.studio.model.actormapping.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
     * @see org.bonitasoft.studio.model.actormapping.DocumentRoot#getXMLNSPrefixMap()
     * @see #getDocumentRoot()
     * @generated
     */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
     * Returns the meta object for the map '{@link org.bonitasoft.studio.model.actormapping.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XSI Schema Location</em>'.
     * @see org.bonitasoft.studio.model.actormapping.DocumentRoot#getXSISchemaLocation()
     * @see #getDocumentRoot()
     * @generated
     */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.actormapping.DocumentRoot#getActorMappings <em>Actor Mappings</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Actor Mappings</em>'.
     * @see org.bonitasoft.studio.model.actormapping.DocumentRoot#getActorMappings()
     * @see #getDocumentRoot()
     * @generated
     */
	EReference getDocumentRoot_ActorMappings();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.actormapping.Groups <em>Groups</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Groups</em>'.
     * @see org.bonitasoft.studio.model.actormapping.Groups
     * @generated
     */
	EClass getGroups();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.actormapping.Groups#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Group</em>'.
     * @see org.bonitasoft.studio.model.actormapping.Groups#getGroup()
     * @see #getGroups()
     * @generated
     */
	EAttribute getGroups_Group();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.actormapping.Membership <em>Membership</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Membership</em>'.
     * @see org.bonitasoft.studio.model.actormapping.Membership
     * @generated
     */
	EClass getMembership();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.actormapping.Membership#getMembership <em>Membership</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Membership</em>'.
     * @see org.bonitasoft.studio.model.actormapping.Membership#getMembership()
     * @see #getMembership()
     * @generated
     */
	EReference getMembership_Membership();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.actormapping.MembershipType <em>Membership Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Membership Type</em>'.
     * @see org.bonitasoft.studio.model.actormapping.MembershipType
     * @generated
     */
	EClass getMembershipType();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.actormapping.MembershipType#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Group</em>'.
     * @see org.bonitasoft.studio.model.actormapping.MembershipType#getGroup()
     * @see #getMembershipType()
     * @generated
     */
	EAttribute getMembershipType_Group();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.actormapping.MembershipType#getRole <em>Role</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Role</em>'.
     * @see org.bonitasoft.studio.model.actormapping.MembershipType#getRole()
     * @see #getMembershipType()
     * @generated
     */
	EAttribute getMembershipType_Role();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.actormapping.Roles <em>Roles</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Roles</em>'.
     * @see org.bonitasoft.studio.model.actormapping.Roles
     * @generated
     */
	EClass getRoles();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.actormapping.Roles#getRole <em>Role</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Role</em>'.
     * @see org.bonitasoft.studio.model.actormapping.Roles#getRole()
     * @see #getRoles()
     * @generated
     */
	EAttribute getRoles_Role();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.actormapping.Users <em>Users</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Users</em>'.
     * @see org.bonitasoft.studio.model.actormapping.Users
     * @generated
     */
	EClass getUsers();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.actormapping.Users#getUser <em>User</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>User</em>'.
     * @see org.bonitasoft.studio.model.actormapping.Users#getUser()
     * @see #getUsers()
     * @generated
     */
	EAttribute getUsers_User();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	ActorMappingFactory getActorMappingFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingImpl <em>Actor Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingImpl
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getActorMapping()
         * @generated
         */
		EClass ACTOR_MAPPING = eINSTANCE.getActorMapping();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ACTOR_MAPPING__NAME = eINSTANCE.getActorMapping_Name();

		/**
         * The meta object literal for the '<em><b>Groups</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTOR_MAPPING__GROUPS = eINSTANCE.getActorMapping_Groups();

		/**
         * The meta object literal for the '<em><b>Memberships</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTOR_MAPPING__MEMBERSHIPS = eINSTANCE.getActorMapping_Memberships();

		/**
         * The meta object literal for the '<em><b>Roles</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTOR_MAPPING__ROLES = eINSTANCE.getActorMapping_Roles();

		/**
         * The meta object literal for the '<em><b>Users</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTOR_MAPPING__USERS = eINSTANCE.getActorMapping_Users();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingsTypeImpl <em>Actor Mappings Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingsTypeImpl
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getActorMappingsType()
         * @generated
         */
		EClass ACTOR_MAPPINGS_TYPE = eINSTANCE.getActorMappingsType();

		/**
         * The meta object literal for the '<em><b>Actor Mapping</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING = eINSTANCE.getActorMappingsType_ActorMapping();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.actormapping.impl.DocumentRootImpl <em>Document Root</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.actormapping.impl.DocumentRootImpl
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getDocumentRoot()
         * @generated
         */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
         * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
         * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
         * The meta object literal for the '<em><b>Actor Mappings</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT_ROOT__ACTOR_MAPPINGS = eINSTANCE.getDocumentRoot_ActorMappings();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.actormapping.impl.GroupsImpl <em>Groups</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.actormapping.impl.GroupsImpl
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getGroups()
         * @generated
         */
		EClass GROUPS = eINSTANCE.getGroups();

		/**
         * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUPS__GROUP = eINSTANCE.getGroups_Group();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.actormapping.impl.MembershipImpl <em>Membership</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.actormapping.impl.MembershipImpl
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getMembership()
         * @generated
         */
		EClass MEMBERSHIP = eINSTANCE.getMembership();

		/**
         * The meta object literal for the '<em><b>Membership</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MEMBERSHIP__MEMBERSHIP = eINSTANCE.getMembership_Membership();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.actormapping.impl.MembershipTypeImpl <em>Membership Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.actormapping.impl.MembershipTypeImpl
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getMembershipType()
         * @generated
         */
		EClass MEMBERSHIP_TYPE = eINSTANCE.getMembershipType();

		/**
         * The meta object literal for the '<em><b>Group</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MEMBERSHIP_TYPE__GROUP = eINSTANCE.getMembershipType_Group();

		/**
         * The meta object literal for the '<em><b>Role</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MEMBERSHIP_TYPE__ROLE = eINSTANCE.getMembershipType_Role();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.actormapping.impl.RolesImpl <em>Roles</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.actormapping.impl.RolesImpl
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getRoles()
         * @generated
         */
		EClass ROLES = eINSTANCE.getRoles();

		/**
         * The meta object literal for the '<em><b>Role</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ROLES__ROLE = eINSTANCE.getRoles_Role();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.actormapping.impl.UsersImpl <em>Users</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.actormapping.impl.UsersImpl
         * @see org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl#getUsers()
         * @generated
         */
		EClass USERS = eINSTANCE.getUsers();

		/**
         * The meta object literal for the '<em><b>User</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USERS__USER = eINSTANCE.getUsers_User();

	}

} //ActorMappingPackage
