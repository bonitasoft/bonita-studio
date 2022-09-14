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
package org.bonitasoft.studio.identity.organization.model.organization.util;

import org.bonitasoft.studio.identity.organization.model.organization.*;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage
 * @generated
 */
public class OrganizationSwitch<T> extends Switch<T> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static OrganizationPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OrganizationSwitch() {
        if (modelPackage == null) {
            modelPackage = OrganizationPackage.eINSTANCE;
        }
    }

	/**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case OrganizationPackage.CONTACT_DATA: {
                ContactData contactData = (ContactData)theEObject;
                T result = caseContactData(contactData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.CUSTOM_USER_INFO_DEFINITION: {
                CustomUserInfoDefinition customUserInfoDefinition = (CustomUserInfoDefinition)theEObject;
                T result = caseCustomUserInfoDefinition(customUserInfoDefinition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.CUSTOM_USER_INFO_DEFINITIONS: {
                CustomUserInfoDefinitions customUserInfoDefinitions = (CustomUserInfoDefinitions)theEObject;
                T result = caseCustomUserInfoDefinitions(customUserInfoDefinitions);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.CUSTOM_USER_INFO_VALUE: {
                CustomUserInfoValue customUserInfoValue = (CustomUserInfoValue)theEObject;
                T result = caseCustomUserInfoValue(customUserInfoValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.CUSTOM_USER_INFO_VALUES_TYPE: {
                CustomUserInfoValuesType customUserInfoValuesType = (CustomUserInfoValuesType)theEObject;
                T result = caseCustomUserInfoValuesType(customUserInfoValuesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.DOCUMENT_ROOT: {
                DocumentRoot documentRoot = (DocumentRoot)theEObject;
                T result = caseDocumentRoot(documentRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.GROUP: {
                Group group = (Group)theEObject;
                T result = caseGroup(group);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.GROUPS: {
                Groups groups = (Groups)theEObject;
                T result = caseGroups(groups);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.MEMBERSHIP: {
                Membership membership = (Membership)theEObject;
                T result = caseMembership(membership);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.MEMBERSHIPS: {
                Memberships memberships = (Memberships)theEObject;
                T result = caseMemberships(memberships);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.METADATA: {
                Metadata metadata = (Metadata)theEObject;
                T result = caseMetadata(metadata);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.META_DATAS_TYPE: {
                MetaDatasType metaDatasType = (MetaDatasType)theEObject;
                T result = caseMetaDatasType(metaDatasType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.ORGANIZATION: {
                Organization organization = (Organization)theEObject;
                T result = caseOrganization(organization);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.ROLE: {
                Role role = (Role)theEObject;
                T result = caseRole(role);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.ROLES: {
                Roles roles = (Roles)theEObject;
                T result = caseRoles(roles);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.USER: {
                User user = (User)theEObject;
                T result = caseUser(user);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.USERS: {
                Users users = (Users)theEObject;
                T result = caseUsers(users);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OrganizationPackage.PASSWORD_TYPE: {
                PasswordType passwordType = (PasswordType)theEObject;
                T result = casePasswordType(passwordType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Contact Data</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Contact Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseContactData(ContactData object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Custom User Info Definition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom User Info Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCustomUserInfoDefinition(CustomUserInfoDefinition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Custom User Info Definitions</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom User Info Definitions</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCustomUserInfoDefinitions(CustomUserInfoDefinitions object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Custom User Info Value</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom User Info Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCustomUserInfoValue(CustomUserInfoValue object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Custom User Info Values Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom User Info Values Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCustomUserInfoValuesType(CustomUserInfoValuesType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDocumentRoot(DocumentRoot object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseGroup(Group object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Groups</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Groups</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseGroups(Groups object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Membership</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Membership</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMembership(Membership object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Memberships</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Memberships</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMemberships(Memberships object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Metadata</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Metadata</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMetadata(Metadata object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Meta Datas Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Meta Datas Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMetaDatasType(MetaDatasType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Organization</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Organization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseOrganization(Organization object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Role</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Role</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseRole(Role object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Roles</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Roles</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseRoles(Roles object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>User</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseUser(User object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Users</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Users</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseUsers(Users object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Password Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Password Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePasswordType(PasswordType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	@Override
	public T defaultCase(EObject object) {
        return null;
    }

} //OrganizationSwitch
