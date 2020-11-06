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
 * 			Organization Schema 1.0 for Bonita Open Solution.
 * 			Copyright (C) 2012, 2013 BonitaSoft S.A.
 * 		
 * <!-- end-model-doc -->
 * @see org.bonitasoft.studio.actors.model.organization.OrganizationFactory
 * @model kind="package"
 * @generated
 */
public interface OrganizationPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "organization";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://documentation.bonitasoft.com/organization-xml-schema/1.1";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "organization";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	OrganizationPackage eINSTANCE = org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.ContactDataImpl <em>Contact Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.ContactDataImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getContactData()
     * @generated
     */
	int CONTACT_DATA = 0;

	/**
     * The feature id for the '<em><b>Email</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__EMAIL = 0;

	/**
     * The feature id for the '<em><b>Phone Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__PHONE_NUMBER = 1;

	/**
     * The feature id for the '<em><b>Mobile Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__MOBILE_NUMBER = 2;

	/**
     * The feature id for the '<em><b>Fax Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__FAX_NUMBER = 3;

	/**
     * The feature id for the '<em><b>Building</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__BUILDING = 4;

	/**
     * The feature id for the '<em><b>Room</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__ROOM = 5;

	/**
     * The feature id for the '<em><b>Address</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__ADDRESS = 6;

	/**
     * The feature id for the '<em><b>Zip Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__ZIP_CODE = 7;

	/**
     * The feature id for the '<em><b>City</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__CITY = 8;

	/**
     * The feature id for the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__STATE = 9;

	/**
     * The feature id for the '<em><b>Country</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__COUNTRY = 10;

	/**
     * The feature id for the '<em><b>Website</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA__WEBSITE = 11;

	/**
     * The number of structural features of the '<em>Contact Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTACT_DATA_FEATURE_COUNT = 12;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoDefinitionImpl <em>Custom User Info Definition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoDefinitionImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getCustomUserInfoDefinition()
     * @generated
     */
	int CUSTOM_USER_INFO_DEFINITION = 1;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_DEFINITION__NAME = 0;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_DEFINITION__DESCRIPTION = 1;

	/**
     * The number of structural features of the '<em>Custom User Info Definition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_DEFINITION_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoDefinitionsImpl <em>Custom User Info Definitions</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoDefinitionsImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getCustomUserInfoDefinitions()
     * @generated
     */
	int CUSTOM_USER_INFO_DEFINITIONS = 2;

	/**
     * The feature id for the '<em><b>Custom User Info Definition</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION = 0;

	/**
     * The number of structural features of the '<em>Custom User Info Definitions</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_DEFINITIONS_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoValueImpl <em>Custom User Info Value</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoValueImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getCustomUserInfoValue()
     * @generated
     */
	int CUSTOM_USER_INFO_VALUE = 3;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_VALUE__NAME = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_VALUE__VALUE = 1;

	/**
     * The number of structural features of the '<em>Custom User Info Value</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_VALUE_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoValuesTypeImpl <em>Custom User Info Values Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoValuesTypeImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getCustomUserInfoValuesType()
     * @generated
     */
	int CUSTOM_USER_INFO_VALUES_TYPE = 4;

	/**
     * The feature id for the '<em><b>Custom User Info Value</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE = 0;

	/**
     * The number of structural features of the '<em>Custom User Info Values Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CUSTOM_USER_INFO_VALUES_TYPE_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.DocumentRootImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getDocumentRoot()
     * @generated
     */
	int DOCUMENT_ROOT = 5;

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
     * The feature id for the '<em><b>Organization</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_ROOT__ORGANIZATION = 3;

	/**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.GroupImpl <em>Group</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.GroupImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getGroup()
     * @generated
     */
	int GROUP = 6;

	/**
     * The feature id for the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DISPLAY_NAME = 0;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DESCRIPTION = 1;

	/**
     * The feature id for the '<em><b>Icon Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__ICON_NAME = 2;

	/**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__ICON_PATH = 3;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__NAME = 4;

	/**
     * The feature id for the '<em><b>Parent Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__PARENT_PATH = 5;

	/**
     * The number of structural features of the '<em>Group</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP_FEATURE_COUNT = 6;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.GroupsImpl <em>Groups</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.GroupsImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getGroups()
     * @generated
     */
	int GROUPS = 7;

	/**
     * The feature id for the '<em><b>Group</b></em>' containment reference list.
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
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl <em>Membership</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getMembership()
     * @generated
     */
	int MEMBERSHIP = 8;

	/**
     * The feature id for the '<em><b>User Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP__USER_NAME = 0;

	/**
     * The feature id for the '<em><b>Role Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP__ROLE_NAME = 1;

	/**
     * The feature id for the '<em><b>Group Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP__GROUP_NAME = 2;

	/**
     * The feature id for the '<em><b>Group Parent Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP__GROUP_PARENT_PATH = 3;

	/**
     * The feature id for the '<em><b>Assigned By</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP__ASSIGNED_BY = 4;

	/**
     * The feature id for the '<em><b>Assigned Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP__ASSIGNED_DATE = 5;

	/**
     * The number of structural features of the '<em>Membership</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIP_FEATURE_COUNT = 6;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipsImpl <em>Memberships</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.MembershipsImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getMemberships()
     * @generated
     */
	int MEMBERSHIPS = 9;

	/**
     * The feature id for the '<em><b>Membership</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIPS__MEMBERSHIP = 0;

	/**
     * The number of structural features of the '<em>Memberships</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MEMBERSHIPS_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.MetadataImpl <em>Metadata</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.MetadataImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getMetadata()
     * @generated
     */
	int METADATA = 10;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int METADATA__NAME = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int METADATA__VALUE = 1;

	/**
     * The number of structural features of the '<em>Metadata</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int METADATA_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.MetaDatasTypeImpl <em>Meta Datas Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.MetaDatasTypeImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getMetaDatasType()
     * @generated
     */
	int META_DATAS_TYPE = 11;

	/**
     * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int META_DATAS_TYPE__META_DATA = 0;

	/**
     * The number of structural features of the '<em>Meta Datas Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int META_DATAS_TYPE_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.OrganizationImpl <em>Organization</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getOrganization()
     * @generated
     */
	int ORGANIZATION = 12;

	/**
     * The feature id for the '<em><b>Custom User Info Definitions</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS = 0;

	/**
     * The feature id for the '<em><b>Users</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORGANIZATION__USERS = 1;

	/**
     * The feature id for the '<em><b>Roles</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORGANIZATION__ROLES = 2;

	/**
     * The feature id for the '<em><b>Groups</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORGANIZATION__GROUPS = 3;

	/**
     * The feature id for the '<em><b>Memberships</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORGANIZATION__MEMBERSHIPS = 4;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORGANIZATION__NAME = 5;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORGANIZATION__DESCRIPTION = 6;

	/**
     * The feature id for the '<em><b>Model Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORGANIZATION__MODEL_VERSION = 7;

    /**
     * The number of structural features of the '<em>Organization</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORGANIZATION_FEATURE_COUNT = 8;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.RoleImpl <em>Role</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.RoleImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getRole()
     * @generated
     */
	int ROLE = 13;

	/**
     * The feature id for the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE__DISPLAY_NAME = 0;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE__DESCRIPTION = 1;

	/**
     * The feature id for the '<em><b>Icon Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE__ICON_NAME = 2;

	/**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE__ICON_PATH = 3;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE__NAME = 4;

	/**
     * The number of structural features of the '<em>Role</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE_FEATURE_COUNT = 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.RolesImpl <em>Roles</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.RolesImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getRoles()
     * @generated
     */
	int ROLES = 14;

	/**
     * The feature id for the '<em><b>Role</b></em>' containment reference list.
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
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl <em>User</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.UserImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getUser()
     * @generated
     */
	int USER = 15;

	/**
     * The feature id for the '<em><b>First Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__FIRST_NAME = 0;

	/**
     * The feature id for the '<em><b>Last Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__LAST_NAME = 1;

	/**
     * The feature id for the '<em><b>Icon Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__ICON_NAME = 2;

	/**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__ICON_PATH = 3;

	/**
     * The feature id for the '<em><b>Title</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__TITLE = 4;

	/**
     * The feature id for the '<em><b>Job Title</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__JOB_TITLE = 5;

	/**
     * The feature id for the '<em><b>Manager</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__MANAGER = 6;

	/**
     * The feature id for the '<em><b>Personal Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__PERSONAL_DATA = 7;

	/**
     * The feature id for the '<em><b>Professional Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__PROFESSIONAL_DATA = 8;

	/**
     * The feature id for the '<em><b>Meta Datas</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__META_DATAS = 9;

	/**
     * The feature id for the '<em><b>User Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__USER_NAME = 10;

	/**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__ENABLED = 11;

	/**
     * The feature id for the '<em><b>Password</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__PASSWORD = 12;

    /**
     * The feature id for the '<em><b>Custom User Info Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER__CUSTOM_USER_INFO_VALUES = 13;

	/**
     * The number of structural features of the '<em>User</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int USER_FEATURE_COUNT = 14;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.UsersImpl <em>Users</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.UsersImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getUsers()
     * @generated
     */
	int USERS = 16;

	/**
     * The feature id for the '<em><b>User</b></em>' containment reference list.
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
     * The meta object id for the '{@link org.bonitasoft.studio.actors.model.organization.impl.PasswordTypeImpl <em>Password Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.actors.model.organization.impl.PasswordTypeImpl
     * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getPasswordType()
     * @generated
     */
	int PASSWORD_TYPE = 17;

	/**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_TYPE__VALUE = 0;

	/**
     * The feature id for the '<em><b>Encrypted</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_TYPE__ENCRYPTED = 1;

	/**
     * The number of structural features of the '<em>Password Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_TYPE_FEATURE_COUNT = 2;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.ContactData <em>Contact Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Contact Data</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData
     * @generated
     */
	EClass getContactData();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getEmail <em>Email</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Email</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getEmail()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_Email();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getPhoneNumber <em>Phone Number</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Phone Number</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getPhoneNumber()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_PhoneNumber();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getMobileNumber <em>Mobile Number</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Mobile Number</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getMobileNumber()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_MobileNumber();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getFaxNumber <em>Fax Number</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fax Number</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getFaxNumber()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_FaxNumber();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getBuilding <em>Building</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Building</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getBuilding()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_Building();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getRoom <em>Room</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Room</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getRoom()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_Room();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getAddress <em>Address</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Address</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getAddress()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_Address();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getZipCode <em>Zip Code</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Zip Code</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getZipCode()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_ZipCode();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getCity <em>City</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>City</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getCity()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_City();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getState <em>State</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>State</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getState()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_State();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getCountry <em>Country</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Country</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getCountry()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_Country();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.ContactData#getWebsite <em>Website</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Website</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData#getWebsite()
     * @see #getContactData()
     * @generated
     */
	EAttribute getContactData_Website();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition <em>Custom User Info Definition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Custom User Info Definition</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition
     * @generated
     */
	EClass getCustomUserInfoDefinition();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition#getName()
     * @see #getCustomUserInfoDefinition()
     * @generated
     */
	EAttribute getCustomUserInfoDefinition_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition#getDescription()
     * @see #getCustomUserInfoDefinition()
     * @generated
     */
	EAttribute getCustomUserInfoDefinition_Description();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinitions <em>Custom User Info Definitions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Custom User Info Definitions</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinitions
     * @generated
     */
	EClass getCustomUserInfoDefinitions();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinitions#getCustomUserInfoDefinition <em>Custom User Info Definition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Custom User Info Definition</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinitions#getCustomUserInfoDefinition()
     * @see #getCustomUserInfoDefinitions()
     * @generated
     */
	EReference getCustomUserInfoDefinitions_CustomUserInfoDefinition();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue <em>Custom User Info Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Custom User Info Value</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue
     * @generated
     */
	EClass getCustomUserInfoValue();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue#getName()
     * @see #getCustomUserInfoValue()
     * @generated
     */
	EAttribute getCustomUserInfoValue_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue#getValue()
     * @see #getCustomUserInfoValue()
     * @generated
     */
	EAttribute getCustomUserInfoValue_Value();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoValuesType <em>Custom User Info Values Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Custom User Info Values Type</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoValuesType
     * @generated
     */
	EClass getCustomUserInfoValuesType();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoValuesType#getCustomUserInfoValue <em>Custom User Info Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Custom User Info Value</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoValuesType#getCustomUserInfoValue()
     * @see #getCustomUserInfoValuesType()
     * @generated
     */
	EReference getCustomUserInfoValuesType_CustomUserInfoValue();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document Root</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.DocumentRoot
     * @generated
     */
	EClass getDocumentRoot();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.actors.model.organization.DocumentRoot#getMixed <em>Mixed</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.DocumentRoot#getMixed()
     * @see #getDocumentRoot()
     * @generated
     */
	EAttribute getDocumentRoot_Mixed();

	/**
     * Returns the meta object for the map '{@link org.bonitasoft.studio.actors.model.organization.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.DocumentRoot#getXMLNSPrefixMap()
     * @see #getDocumentRoot()
     * @generated
     */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
     * Returns the meta object for the map '{@link org.bonitasoft.studio.actors.model.organization.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XSI Schema Location</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.DocumentRoot#getXSISchemaLocation()
     * @see #getDocumentRoot()
     * @generated
     */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.DocumentRoot#getOrganization <em>Organization</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Organization</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.DocumentRoot#getOrganization()
     * @see #getDocumentRoot()
     * @generated
     */
	EReference getDocumentRoot_Organization();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.Group <em>Group</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Group</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Group
     * @generated
     */
	EClass getGroup();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Group#getDisplayName <em>Display Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Display Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Group#getDisplayName()
     * @see #getGroup()
     * @generated
     */
	EAttribute getGroup_DisplayName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Group#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Group#getDescription()
     * @see #getGroup()
     * @generated
     */
	EAttribute getGroup_Description();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Group#getIconName <em>Icon Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Icon Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Group#getIconName()
     * @see #getGroup()
     * @generated
     */
	EAttribute getGroup_IconName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Group#getIconPath <em>Icon Path</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Group#getIconPath()
     * @see #getGroup()
     * @generated
     */
	EAttribute getGroup_IconPath();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Group#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Group#getName()
     * @see #getGroup()
     * @generated
     */
	EAttribute getGroup_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Group#getParentPath <em>Parent Path</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Parent Path</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Group#getParentPath()
     * @see #getGroup()
     * @generated
     */
	EAttribute getGroup_ParentPath();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.Groups <em>Groups</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Groups</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Groups
     * @generated
     */
	EClass getGroups();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.actors.model.organization.Groups#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Group</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Groups#getGroup()
     * @see #getGroups()
     * @generated
     */
	EReference getGroups_Group();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.Membership <em>Membership</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Membership</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Membership
     * @generated
     */
	EClass getMembership();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Membership#getUserName <em>User Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>User Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Membership#getUserName()
     * @see #getMembership()
     * @generated
     */
	EAttribute getMembership_UserName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Membership#getRoleName <em>Role Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Role Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Membership#getRoleName()
     * @see #getMembership()
     * @generated
     */
	EAttribute getMembership_RoleName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Membership#getGroupName <em>Group Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Group Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Membership#getGroupName()
     * @see #getMembership()
     * @generated
     */
	EAttribute getMembership_GroupName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Membership#getGroupParentPath <em>Group Parent Path</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Group Parent Path</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Membership#getGroupParentPath()
     * @see #getMembership()
     * @generated
     */
	EAttribute getMembership_GroupParentPath();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Membership#getAssignedBy <em>Assigned By</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Assigned By</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Membership#getAssignedBy()
     * @see #getMembership()
     * @generated
     */
	EAttribute getMembership_AssignedBy();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Membership#getAssignedDate <em>Assigned Date</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Assigned Date</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Membership#getAssignedDate()
     * @see #getMembership()
     * @generated
     */
	EAttribute getMembership_AssignedDate();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.Memberships <em>Memberships</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Memberships</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Memberships
     * @generated
     */
	EClass getMemberships();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.actors.model.organization.Memberships#getMembership <em>Membership</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Membership</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Memberships#getMembership()
     * @see #getMemberships()
     * @generated
     */
	EReference getMemberships_Membership();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.Metadata <em>Metadata</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Metadata</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Metadata
     * @generated
     */
	EClass getMetadata();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Metadata#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Metadata#getName()
     * @see #getMetadata()
     * @generated
     */
	EAttribute getMetadata_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Metadata#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Metadata#getValue()
     * @see #getMetadata()
     * @generated
     */
	EAttribute getMetadata_Value();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.MetaDatasType <em>Meta Datas Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Meta Datas Type</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.MetaDatasType
     * @generated
     */
	EClass getMetaDatasType();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.actors.model.organization.MetaDatasType#getMetaData <em>Meta Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Meta Data</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.MetaDatasType#getMetaData()
     * @see #getMetaDatasType()
     * @generated
     */
	EReference getMetaDatasType_MetaData();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.Organization <em>Organization</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Organization</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Organization
     * @generated
     */
	EClass getOrganization();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.Organization#getCustomUserInfoDefinitions <em>Custom User Info Definitions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Custom User Info Definitions</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Organization#getCustomUserInfoDefinitions()
     * @see #getOrganization()
     * @generated
     */
	EReference getOrganization_CustomUserInfoDefinitions();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.Organization#getUsers <em>Users</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Users</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Organization#getUsers()
     * @see #getOrganization()
     * @generated
     */
	EReference getOrganization_Users();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.Organization#getRoles <em>Roles</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Roles</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Organization#getRoles()
     * @see #getOrganization()
     * @generated
     */
	EReference getOrganization_Roles();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.Organization#getGroups <em>Groups</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Groups</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Organization#getGroups()
     * @see #getOrganization()
     * @generated
     */
	EReference getOrganization_Groups();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.Organization#getMemberships <em>Memberships</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Memberships</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Organization#getMemberships()
     * @see #getOrganization()
     * @generated
     */
	EReference getOrganization_Memberships();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Organization#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Organization#getName()
     * @see #getOrganization()
     * @generated
     */
	EAttribute getOrganization_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Organization#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Organization#getDescription()
     * @see #getOrganization()
     * @generated
     */
	EAttribute getOrganization_Description();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Organization#getModelVersion <em>Model Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Model Version</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Organization#getModelVersion()
     * @see #getOrganization()
     * @generated
     */
    EAttribute getOrganization_ModelVersion();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.Role <em>Role</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Role</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Role
     * @generated
     */
	EClass getRole();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Role#getDisplayName <em>Display Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Display Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Role#getDisplayName()
     * @see #getRole()
     * @generated
     */
	EAttribute getRole_DisplayName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Role#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Role#getDescription()
     * @see #getRole()
     * @generated
     */
	EAttribute getRole_Description();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Role#getIconName <em>Icon Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Icon Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Role#getIconName()
     * @see #getRole()
     * @generated
     */
	EAttribute getRole_IconName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Role#getIconPath <em>Icon Path</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Role#getIconPath()
     * @see #getRole()
     * @generated
     */
	EAttribute getRole_IconPath();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.Role#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Role#getName()
     * @see #getRole()
     * @generated
     */
	EAttribute getRole_Name();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.Roles <em>Roles</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Roles</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Roles
     * @generated
     */
	EClass getRoles();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.actors.model.organization.Roles#getRole <em>Role</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Role</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Roles#getRole()
     * @see #getRoles()
     * @generated
     */
	EReference getRoles_Role();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.User <em>User</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>User</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User
     * @generated
     */
	EClass getUser();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.User#getFirstName <em>First Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>First Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getFirstName()
     * @see #getUser()
     * @generated
     */
	EAttribute getUser_FirstName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.User#getLastName <em>Last Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getLastName()
     * @see #getUser()
     * @generated
     */
	EAttribute getUser_LastName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.User#getIconName <em>Icon Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Icon Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getIconName()
     * @see #getUser()
     * @generated
     */
	EAttribute getUser_IconName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.User#getIconPath <em>Icon Path</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getIconPath()
     * @see #getUser()
     * @generated
     */
	EAttribute getUser_IconPath();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.User#getTitle <em>Title</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Title</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getTitle()
     * @see #getUser()
     * @generated
     */
	EAttribute getUser_Title();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.User#getJobTitle <em>Job Title</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Job Title</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getJobTitle()
     * @see #getUser()
     * @generated
     */
	EAttribute getUser_JobTitle();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.User#getManager <em>Manager</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Manager</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getManager()
     * @see #getUser()
     * @generated
     */
	EAttribute getUser_Manager();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.User#getPersonalData <em>Personal Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Personal Data</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getPersonalData()
     * @see #getUser()
     * @generated
     */
	EReference getUser_PersonalData();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.User#getProfessionalData <em>Professional Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Professional Data</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getProfessionalData()
     * @see #getUser()
     * @generated
     */
	EReference getUser_ProfessionalData();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.User#getMetaDatas <em>Meta Datas</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Meta Datas</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getMetaDatas()
     * @see #getUser()
     * @generated
     */
	EReference getUser_MetaDatas();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.User#getUserName <em>User Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>User Name</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getUserName()
     * @see #getUser()
     * @generated
     */
	EAttribute getUser_UserName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.User#isEnabled <em>Enabled</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enabled</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#isEnabled()
     * @see #getUser()
     * @generated
     */
	EAttribute getUser_Enabled();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.User#getCustomUserInfoValues <em>Custom User Info Values</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Custom User Info Values</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getCustomUserInfoValues()
     * @see #getUser()
     * @generated
     */
	EReference getUser_CustomUserInfoValues();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.actors.model.organization.User#getPassword <em>Password</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Password</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.User#getPassword()
     * @see #getUser()
     * @generated
     */
	EReference getUser_Password();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.Users <em>Users</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Users</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Users
     * @generated
     */
	EClass getUsers();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.actors.model.organization.Users#getUser <em>User</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>User</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.Users#getUser()
     * @see #getUsers()
     * @generated
     */
	EReference getUsers_User();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.actors.model.organization.PasswordType <em>Password Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Password Type</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.PasswordType
     * @generated
     */
	EClass getPasswordType();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.PasswordType#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.PasswordType#getValue()
     * @see #getPasswordType()
     * @generated
     */
	EAttribute getPasswordType_Value();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.actors.model.organization.PasswordType#isEncrypted <em>Encrypted</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Encrypted</em>'.
     * @see org.bonitasoft.studio.actors.model.organization.PasswordType#isEncrypted()
     * @see #getPasswordType()
     * @generated
     */
	EAttribute getPasswordType_Encrypted();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	OrganizationFactory getOrganizationFactory();

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
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.ContactDataImpl <em>Contact Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.ContactDataImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getContactData()
         * @generated
         */
		EClass CONTACT_DATA = eINSTANCE.getContactData();

		/**
         * The meta object literal for the '<em><b>Email</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__EMAIL = eINSTANCE.getContactData_Email();

		/**
         * The meta object literal for the '<em><b>Phone Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__PHONE_NUMBER = eINSTANCE.getContactData_PhoneNumber();

		/**
         * The meta object literal for the '<em><b>Mobile Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__MOBILE_NUMBER = eINSTANCE.getContactData_MobileNumber();

		/**
         * The meta object literal for the '<em><b>Fax Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__FAX_NUMBER = eINSTANCE.getContactData_FaxNumber();

		/**
         * The meta object literal for the '<em><b>Building</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__BUILDING = eINSTANCE.getContactData_Building();

		/**
         * The meta object literal for the '<em><b>Room</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__ROOM = eINSTANCE.getContactData_Room();

		/**
         * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__ADDRESS = eINSTANCE.getContactData_Address();

		/**
         * The meta object literal for the '<em><b>Zip Code</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__ZIP_CODE = eINSTANCE.getContactData_ZipCode();

		/**
         * The meta object literal for the '<em><b>City</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__CITY = eINSTANCE.getContactData_City();

		/**
         * The meta object literal for the '<em><b>State</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__STATE = eINSTANCE.getContactData_State();

		/**
         * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__COUNTRY = eINSTANCE.getContactData_Country();

		/**
         * The meta object literal for the '<em><b>Website</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONTACT_DATA__WEBSITE = eINSTANCE.getContactData_Website();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoDefinitionImpl <em>Custom User Info Definition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoDefinitionImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getCustomUserInfoDefinition()
         * @generated
         */
		EClass CUSTOM_USER_INFO_DEFINITION = eINSTANCE.getCustomUserInfoDefinition();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CUSTOM_USER_INFO_DEFINITION__NAME = eINSTANCE.getCustomUserInfoDefinition_Name();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CUSTOM_USER_INFO_DEFINITION__DESCRIPTION = eINSTANCE.getCustomUserInfoDefinition_Description();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoDefinitionsImpl <em>Custom User Info Definitions</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoDefinitionsImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getCustomUserInfoDefinitions()
         * @generated
         */
		EClass CUSTOM_USER_INFO_DEFINITIONS = eINSTANCE.getCustomUserInfoDefinitions();

		/**
         * The meta object literal for the '<em><b>Custom User Info Definition</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION = eINSTANCE.getCustomUserInfoDefinitions_CustomUserInfoDefinition();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoValueImpl <em>Custom User Info Value</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoValueImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getCustomUserInfoValue()
         * @generated
         */
		EClass CUSTOM_USER_INFO_VALUE = eINSTANCE.getCustomUserInfoValue();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CUSTOM_USER_INFO_VALUE__NAME = eINSTANCE.getCustomUserInfoValue_Name();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CUSTOM_USER_INFO_VALUE__VALUE = eINSTANCE.getCustomUserInfoValue_Value();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoValuesTypeImpl <em>Custom User Info Values Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.CustomUserInfoValuesTypeImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getCustomUserInfoValuesType()
         * @generated
         */
		EClass CUSTOM_USER_INFO_VALUES_TYPE = eINSTANCE.getCustomUserInfoValuesType();

		/**
         * The meta object literal for the '<em><b>Custom User Info Value</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE = eINSTANCE.getCustomUserInfoValuesType_CustomUserInfoValue();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.DocumentRootImpl <em>Document Root</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.DocumentRootImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getDocumentRoot()
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
         * The meta object literal for the '<em><b>Organization</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT_ROOT__ORGANIZATION = eINSTANCE.getDocumentRoot_Organization();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.GroupImpl <em>Group</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.GroupImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getGroup()
         * @generated
         */
		EClass GROUP = eINSTANCE.getGroup();

		/**
         * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUP__DISPLAY_NAME = eINSTANCE.getGroup_DisplayName();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUP__DESCRIPTION = eINSTANCE.getGroup_Description();

		/**
         * The meta object literal for the '<em><b>Icon Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUP__ICON_NAME = eINSTANCE.getGroup_IconName();

		/**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUP__ICON_PATH = eINSTANCE.getGroup_IconPath();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUP__NAME = eINSTANCE.getGroup_Name();

		/**
         * The meta object literal for the '<em><b>Parent Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUP__PARENT_PATH = eINSTANCE.getGroup_ParentPath();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.GroupsImpl <em>Groups</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.GroupsImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getGroups()
         * @generated
         */
		EClass GROUPS = eINSTANCE.getGroups();

		/**
         * The meta object literal for the '<em><b>Group</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPS__GROUP = eINSTANCE.getGroups_Group();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl <em>Membership</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getMembership()
         * @generated
         */
		EClass MEMBERSHIP = eINSTANCE.getMembership();

		/**
         * The meta object literal for the '<em><b>User Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MEMBERSHIP__USER_NAME = eINSTANCE.getMembership_UserName();

		/**
         * The meta object literal for the '<em><b>Role Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MEMBERSHIP__ROLE_NAME = eINSTANCE.getMembership_RoleName();

		/**
         * The meta object literal for the '<em><b>Group Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MEMBERSHIP__GROUP_NAME = eINSTANCE.getMembership_GroupName();

		/**
         * The meta object literal for the '<em><b>Group Parent Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MEMBERSHIP__GROUP_PARENT_PATH = eINSTANCE.getMembership_GroupParentPath();

		/**
         * The meta object literal for the '<em><b>Assigned By</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MEMBERSHIP__ASSIGNED_BY = eINSTANCE.getMembership_AssignedBy();

		/**
         * The meta object literal for the '<em><b>Assigned Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MEMBERSHIP__ASSIGNED_DATE = eINSTANCE.getMembership_AssignedDate();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipsImpl <em>Memberships</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.MembershipsImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getMemberships()
         * @generated
         */
		EClass MEMBERSHIPS = eINSTANCE.getMemberships();

		/**
         * The meta object literal for the '<em><b>Membership</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MEMBERSHIPS__MEMBERSHIP = eINSTANCE.getMemberships_Membership();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.MetadataImpl <em>Metadata</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.MetadataImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getMetadata()
         * @generated
         */
		EClass METADATA = eINSTANCE.getMetadata();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute METADATA__NAME = eINSTANCE.getMetadata_Name();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute METADATA__VALUE = eINSTANCE.getMetadata_Value();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.MetaDatasTypeImpl <em>Meta Datas Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.MetaDatasTypeImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getMetaDatasType()
         * @generated
         */
		EClass META_DATAS_TYPE = eINSTANCE.getMetaDatasType();

		/**
         * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference META_DATAS_TYPE__META_DATA = eINSTANCE.getMetaDatasType_MetaData();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.OrganizationImpl <em>Organization</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getOrganization()
         * @generated
         */
		EClass ORGANIZATION = eINSTANCE.getOrganization();

		/**
         * The meta object literal for the '<em><b>Custom User Info Definitions</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS = eINSTANCE.getOrganization_CustomUserInfoDefinitions();

		/**
         * The meta object literal for the '<em><b>Users</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ORGANIZATION__USERS = eINSTANCE.getOrganization_Users();

		/**
         * The meta object literal for the '<em><b>Roles</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ORGANIZATION__ROLES = eINSTANCE.getOrganization_Roles();

		/**
         * The meta object literal for the '<em><b>Groups</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ORGANIZATION__GROUPS = eINSTANCE.getOrganization_Groups();

		/**
         * The meta object literal for the '<em><b>Memberships</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ORGANIZATION__MEMBERSHIPS = eINSTANCE.getOrganization_Memberships();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ORGANIZATION__NAME = eINSTANCE.getOrganization_Name();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ORGANIZATION__DESCRIPTION = eINSTANCE.getOrganization_Description();

		/**
         * The meta object literal for the '<em><b>Model Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ORGANIZATION__MODEL_VERSION = eINSTANCE.getOrganization_ModelVersion();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.RoleImpl <em>Role</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.RoleImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getRole()
         * @generated
         */
		EClass ROLE = eINSTANCE.getRole();

		/**
         * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ROLE__DISPLAY_NAME = eINSTANCE.getRole_DisplayName();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ROLE__DESCRIPTION = eINSTANCE.getRole_Description();

		/**
         * The meta object literal for the '<em><b>Icon Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ROLE__ICON_NAME = eINSTANCE.getRole_IconName();

		/**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ROLE__ICON_PATH = eINSTANCE.getRole_IconPath();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ROLE__NAME = eINSTANCE.getRole_Name();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.RolesImpl <em>Roles</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.RolesImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getRoles()
         * @generated
         */
		EClass ROLES = eINSTANCE.getRoles();

		/**
         * The meta object literal for the '<em><b>Role</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ROLES__ROLE = eINSTANCE.getRoles_Role();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl <em>User</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.UserImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getUser()
         * @generated
         */
		EClass USER = eINSTANCE.getUser();

		/**
         * The meta object literal for the '<em><b>First Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USER__FIRST_NAME = eINSTANCE.getUser_FirstName();

		/**
         * The meta object literal for the '<em><b>Last Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USER__LAST_NAME = eINSTANCE.getUser_LastName();

		/**
         * The meta object literal for the '<em><b>Icon Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USER__ICON_NAME = eINSTANCE.getUser_IconName();

		/**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USER__ICON_PATH = eINSTANCE.getUser_IconPath();

		/**
         * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USER__TITLE = eINSTANCE.getUser_Title();

		/**
         * The meta object literal for the '<em><b>Job Title</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USER__JOB_TITLE = eINSTANCE.getUser_JobTitle();

		/**
         * The meta object literal for the '<em><b>Manager</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USER__MANAGER = eINSTANCE.getUser_Manager();

		/**
         * The meta object literal for the '<em><b>Personal Data</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference USER__PERSONAL_DATA = eINSTANCE.getUser_PersonalData();

		/**
         * The meta object literal for the '<em><b>Professional Data</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference USER__PROFESSIONAL_DATA = eINSTANCE.getUser_ProfessionalData();

		/**
         * The meta object literal for the '<em><b>Meta Datas</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference USER__META_DATAS = eINSTANCE.getUser_MetaDatas();

		/**
         * The meta object literal for the '<em><b>User Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USER__USER_NAME = eINSTANCE.getUser_UserName();

		/**
         * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute USER__ENABLED = eINSTANCE.getUser_Enabled();

		/**
         * The meta object literal for the '<em><b>Custom User Info Values</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference USER__CUSTOM_USER_INFO_VALUES = eINSTANCE.getUser_CustomUserInfoValues();

		/**
         * The meta object literal for the '<em><b>Password</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference USER__PASSWORD = eINSTANCE.getUser_Password();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.UsersImpl <em>Users</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.UsersImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getUsers()
         * @generated
         */
		EClass USERS = eINSTANCE.getUsers();

		/**
         * The meta object literal for the '<em><b>User</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference USERS__USER = eINSTANCE.getUsers_User();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.actors.model.organization.impl.PasswordTypeImpl <em>Password Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.actors.model.organization.impl.PasswordTypeImpl
         * @see org.bonitasoft.studio.actors.model.organization.impl.OrganizationPackageImpl#getPasswordType()
         * @generated
         */
		EClass PASSWORD_TYPE = eINSTANCE.getPasswordType();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PASSWORD_TYPE__VALUE = eINSTANCE.getPasswordType_Value();

		/**
         * The meta object literal for the '<em><b>Encrypted</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PASSWORD_TYPE__ENCRYPTED = eINSTANCE.getPasswordType_Encrypted();

	}

} //OrganizationPackage
