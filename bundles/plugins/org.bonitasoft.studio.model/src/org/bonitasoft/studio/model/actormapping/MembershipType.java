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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Membership Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.actormapping.MembershipType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.actormapping.MembershipType#getRole <em>Role</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.actormapping.ActorMappingPackage#getMembershipType()
 * @model extendedMetaData="name='membership_._type' kind='elementOnly'"
 * @generated
 */
public interface MembershipType extends EObject {
	/**
     * Returns the value of the '<em><b>Group</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Group</em>' attribute.
     * @see #setGroup(String)
     * @see org.bonitasoft.studio.model.actormapping.ActorMappingPackage#getMembershipType_Group()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='group'"
     * @generated
     */
	String getGroup();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.actormapping.MembershipType#getGroup <em>Group</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Group</em>' attribute.
     * @see #getGroup()
     * @generated
     */
	void setGroup(String value);

	/**
     * Returns the value of the '<em><b>Role</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Role</em>' attribute.
     * @see #setRole(String)
     * @see org.bonitasoft.studio.model.actormapping.ActorMappingPackage#getMembershipType_Role()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='role'"
     * @generated
     */
	String getRole();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.actormapping.MembershipType#getRole <em>Role</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Role</em>' attribute.
     * @see #getRole()
     * @generated
     */
	void setRole(String value);

} // MembershipType
