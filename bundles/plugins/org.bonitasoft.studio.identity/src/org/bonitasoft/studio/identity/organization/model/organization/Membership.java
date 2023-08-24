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
 * A representation of the model object '<em><b>Membership</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Membership#getUserName <em>User Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Membership#getRoleName <em>Role Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Membership#getGroupName <em>Group Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Membership#getGroupParentPath <em>Group Parent Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Membership#getAssignedBy <em>Assigned By</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.Membership#getAssignedDate <em>Assigned Date</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getMembership()
 * @model extendedMetaData="name='Membership' kind='elementOnly'"
 * @generated
 */
public interface Membership extends EObject {
	/**
     * Returns the value of the '<em><b>User Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>User Name</em>' attribute.
     * @see #setUserName(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getMembership_UserName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='userName'"
     * @generated
     */
	String getUserName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Membership#getUserName <em>User Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>User Name</em>' attribute.
     * @see #getUserName()
     * @generated
     */
	void setUserName(String value);

	/**
     * Returns the value of the '<em><b>Role Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Role Name</em>' attribute.
     * @see #setRoleName(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getMembership_RoleName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='roleName'"
     * @generated
     */
	String getRoleName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Membership#getRoleName <em>Role Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Role Name</em>' attribute.
     * @see #getRoleName()
     * @generated
     */
	void setRoleName(String value);

	/**
     * Returns the value of the '<em><b>Group Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Group Name</em>' attribute.
     * @see #setGroupName(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getMembership_GroupName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='groupName'"
     * @generated
     */
	String getGroupName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Membership#getGroupName <em>Group Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Group Name</em>' attribute.
     * @see #getGroupName()
     * @generated
     */
	void setGroupName(String value);

	/**
     * Returns the value of the '<em><b>Group Parent Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Parent Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Group Parent Path</em>' attribute.
     * @see #setGroupParentPath(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getMembership_GroupParentPath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='groupParentPath'"
     * @generated
     */
	String getGroupParentPath();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Membership#getGroupParentPath <em>Group Parent Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Group Parent Path</em>' attribute.
     * @see #getGroupParentPath()
     * @generated
     */
	void setGroupParentPath(String value);

	/**
     * Returns the value of the '<em><b>Assigned By</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assigned By</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Assigned By</em>' attribute.
     * @see #setAssignedBy(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getMembership_AssignedBy()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='assignedBy'"
     * @generated
     */
	String getAssignedBy();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Membership#getAssignedBy <em>Assigned By</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Assigned By</em>' attribute.
     * @see #getAssignedBy()
     * @generated
     */
	void setAssignedBy(String value);

	/**
     * Returns the value of the '<em><b>Assigned Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assigned Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Assigned Date</em>' attribute.
     * @see #isSetAssignedDate()
     * @see #unsetAssignedDate()
     * @see #setAssignedDate(long)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getMembership_AssignedDate()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Long"
     *        extendedMetaData="kind='element' name='assignedDate'"
     * @generated
     */
	long getAssignedDate();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Membership#getAssignedDate <em>Assigned Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Assigned Date</em>' attribute.
     * @see #isSetAssignedDate()
     * @see #unsetAssignedDate()
     * @see #getAssignedDate()
     * @generated
     */
	void setAssignedDate(long value);

	/**
     * Unsets the value of the '{@link org.bonitasoft.studio.organization.model.organization.Membership#getAssignedDate <em>Assigned Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSetAssignedDate()
     * @see #getAssignedDate()
     * @see #setAssignedDate(long)
     * @generated
     */
	void unsetAssignedDate();

	/**
     * Returns whether the value of the '{@link org.bonitasoft.studio.organization.model.organization.Membership#getAssignedDate <em>Assigned Date</em>}' attribute is set.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return whether the value of the '<em>Assigned Date</em>' attribute is set.
     * @see #unsetAssignedDate()
     * @see #getAssignedDate()
     * @see #setAssignedDate(long)
     * @generated
     */
	boolean isSetAssignedDate();

} // Membership
