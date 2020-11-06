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
package org.bonitasoft.studio.actors.model.organization.util;

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
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.bonitasoft.studio.actors.model.organization.PasswordType;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.Roles;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.model.organization.Users;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage
 * @generated
 */
public class OrganizationAdapterFactory extends AdapterFactoryImpl {
	/**
     * The cached model package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static OrganizationPackage modelPackage;

	/**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OrganizationAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = OrganizationPackage.eINSTANCE;
        }
    }

	/**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
	@Override
	public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

	/**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected OrganizationSwitch<Adapter> modelSwitch =
		new OrganizationSwitch<Adapter>() {
            @Override
            public Adapter caseContactData(ContactData object) {
                return createContactDataAdapter();
            }
            @Override
            public Adapter caseCustomUserInfoDefinition(CustomUserInfoDefinition object) {
                return createCustomUserInfoDefinitionAdapter();
            }
            @Override
            public Adapter caseCustomUserInfoDefinitions(CustomUserInfoDefinitions object) {
                return createCustomUserInfoDefinitionsAdapter();
            }
            @Override
            public Adapter caseCustomUserInfoValue(CustomUserInfoValue object) {
                return createCustomUserInfoValueAdapter();
            }
            @Override
            public Adapter caseCustomUserInfoValuesType(CustomUserInfoValuesType object) {
                return createCustomUserInfoValuesTypeAdapter();
            }
            @Override
            public Adapter caseDocumentRoot(DocumentRoot object) {
                return createDocumentRootAdapter();
            }
            @Override
            public Adapter caseGroup(Group object) {
                return createGroupAdapter();
            }
            @Override
            public Adapter caseGroups(Groups object) {
                return createGroupsAdapter();
            }
            @Override
            public Adapter caseMembership(Membership object) {
                return createMembershipAdapter();
            }
            @Override
            public Adapter caseMemberships(Memberships object) {
                return createMembershipsAdapter();
            }
            @Override
            public Adapter caseMetadata(Metadata object) {
                return createMetadataAdapter();
            }
            @Override
            public Adapter caseMetaDatasType(MetaDatasType object) {
                return createMetaDatasTypeAdapter();
            }
            @Override
            public Adapter caseOrganization(Organization object) {
                return createOrganizationAdapter();
            }
            @Override
            public Adapter caseRole(Role object) {
                return createRoleAdapter();
            }
            @Override
            public Adapter caseRoles(Roles object) {
                return createRolesAdapter();
            }
            @Override
            public Adapter caseUser(User object) {
                return createUserAdapter();
            }
            @Override
            public Adapter caseUsers(Users object) {
                return createUsersAdapter();
            }
            @Override
            public Adapter casePasswordType(PasswordType object) {
                return createPasswordTypeAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

	/**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
	@Override
	public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.ContactData <em>Contact Data</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.ContactData
     * @generated
     */
	public Adapter createContactDataAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition <em>Custom User Info Definition</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition
     * @generated
     */
	public Adapter createCustomUserInfoDefinitionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinitions <em>Custom User Info Definitions</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinitions
     * @generated
     */
	public Adapter createCustomUserInfoDefinitionsAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue <em>Custom User Info Value</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue
     * @generated
     */
	public Adapter createCustomUserInfoValueAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoValuesType <em>Custom User Info Values Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.CustomUserInfoValuesType
     * @generated
     */
	public Adapter createCustomUserInfoValuesTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.DocumentRoot
     * @generated
     */
	public Adapter createDocumentRootAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.Group <em>Group</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.Group
     * @generated
     */
	public Adapter createGroupAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.Groups <em>Groups</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.Groups
     * @generated
     */
	public Adapter createGroupsAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.Membership <em>Membership</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.Membership
     * @generated
     */
	public Adapter createMembershipAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.Memberships <em>Memberships</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.Memberships
     * @generated
     */
	public Adapter createMembershipsAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.Metadata <em>Metadata</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.Metadata
     * @generated
     */
	public Adapter createMetadataAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.MetaDatasType <em>Meta Datas Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.MetaDatasType
     * @generated
     */
	public Adapter createMetaDatasTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.Organization <em>Organization</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.Organization
     * @generated
     */
	public Adapter createOrganizationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.Role <em>Role</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.Role
     * @generated
     */
	public Adapter createRoleAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.Roles <em>Roles</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.Roles
     * @generated
     */
	public Adapter createRolesAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.User <em>User</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.User
     * @generated
     */
	public Adapter createUserAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.Users <em>Users</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.Users
     * @generated
     */
	public Adapter createUsersAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.actors.model.organization.PasswordType <em>Password Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.actors.model.organization.PasswordType
     * @generated
     */
	public Adapter createPasswordTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
	public Adapter createEObjectAdapter() {
        return null;
    }

} //OrganizationAdapterFactory
