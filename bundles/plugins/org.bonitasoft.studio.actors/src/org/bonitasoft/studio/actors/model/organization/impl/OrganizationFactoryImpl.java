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

import org.bonitasoft.studio.actors.model.organization.ContactData;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoValuesType;
import org.bonitasoft.studio.actors.model.organization.DocumentRoot;
import org.bonitasoft.studio.actors.model.organization.Group;
import org.bonitasoft.studio.actors.model.organization.Groups;
import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.Memberships;
import org.bonitasoft.studio.actors.model.organization.MetaDatasType;
import org.bonitasoft.studio.actors.model.organization.Metadata;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.bonitasoft.studio.actors.model.organization.PasswordType;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.Roles;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.model.organization.Users;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OrganizationFactoryImpl extends EFactoryImpl implements OrganizationFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static OrganizationFactory init() {
        try {
            OrganizationFactory theOrganizationFactory = (OrganizationFactory)EPackage.Registry.INSTANCE.getEFactory(OrganizationPackage.eNS_URI);
            if (theOrganizationFactory != null) {
                return theOrganizationFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new OrganizationFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OrganizationFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case OrganizationPackage.CONTACT_DATA: return createContactData();
            case OrganizationPackage.CUSTOM_USER_INFO_DEFINITION: return createCustomUserInfoDefinition();
            case OrganizationPackage.CUSTOM_USER_INFO_DEFINITIONS: return createCustomUserInfoDefinitions();
            case OrganizationPackage.CUSTOM_USER_INFO_VALUE: return createCustomUserInfoValue();
            case OrganizationPackage.CUSTOM_USER_INFO_VALUES_TYPE: return createCustomUserInfoValuesType();
            case OrganizationPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case OrganizationPackage.GROUP: return createGroup();
            case OrganizationPackage.GROUPS: return createGroups();
            case OrganizationPackage.MEMBERSHIP: return createMembership();
            case OrganizationPackage.MEMBERSHIPS: return createMemberships();
            case OrganizationPackage.METADATA: return createMetadata();
            case OrganizationPackage.META_DATAS_TYPE: return createMetaDatasType();
            case OrganizationPackage.ORGANIZATION: return createOrganization();
            case OrganizationPackage.ROLE: return createRole();
            case OrganizationPackage.ROLES: return createRoles();
            case OrganizationPackage.USER: return createUser();
            case OrganizationPackage.USERS: return createUsers();
            case OrganizationPackage.PASSWORD_TYPE: return createPasswordType();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ContactData createContactData() {
        ContactDataImpl contactData = new ContactDataImpl();
        return contactData;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CustomUserInfoDefinition createCustomUserInfoDefinition() {
        CustomUserInfoDefinitionImpl customUserInfoDefinition = new CustomUserInfoDefinitionImpl();
        return customUserInfoDefinition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CustomUserInfoDefinitions createCustomUserInfoDefinitions() {
        CustomUserInfoDefinitionsImpl customUserInfoDefinitions = new CustomUserInfoDefinitionsImpl();
        return customUserInfoDefinitions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CustomUserInfoValue createCustomUserInfoValue() {
        CustomUserInfoValueImpl customUserInfoValue = new CustomUserInfoValueImpl();
        return customUserInfoValue;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CustomUserInfoValuesType createCustomUserInfoValuesType() {
        CustomUserInfoValuesTypeImpl customUserInfoValuesType = new CustomUserInfoValuesTypeImpl();
        return customUserInfoValuesType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Group createGroup() {
        GroupImpl group = new GroupImpl();
        return group;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Groups createGroups() {
        GroupsImpl groups = new GroupsImpl();
        return groups;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Membership createMembership() {
        MembershipImpl membership = new MembershipImpl();
        return membership;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Memberships createMemberships() {
        MembershipsImpl memberships = new MembershipsImpl();
        return memberships;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Metadata createMetadata() {
        MetadataImpl metadata = new MetadataImpl();
        return metadata;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public MetaDatasType createMetaDatasType() {
        MetaDatasTypeImpl metaDatasType = new MetaDatasTypeImpl();
        return metaDatasType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Organization createOrganization() {
        OrganizationImpl organization = new OrganizationImpl();
        return organization;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Role createRole() {
        RoleImpl role = new RoleImpl();
        return role;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Roles createRoles() {
        RolesImpl roles = new RolesImpl();
        return roles;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public User createUser() {
        UserImpl user = new UserImpl();
        return user;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Users createUsers() {
        UsersImpl users = new UsersImpl();
        return users;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PasswordType createPasswordType() {
        PasswordTypeImpl passwordType = new PasswordTypeImpl();
        return passwordType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OrganizationPackage getOrganizationPackage() {
        return (OrganizationPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static OrganizationPackage getPackage() {
        return OrganizationPackage.eINSTANCE;
    }

} //OrganizationFactoryImpl
