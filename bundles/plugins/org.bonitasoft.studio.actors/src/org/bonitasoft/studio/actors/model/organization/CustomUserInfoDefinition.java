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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom User Info Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getCustomUserInfoDefinition()
 * @model extendedMetaData="name='CustomUserInfoDefinition' kind='elementOnly'"
 * @generated
 */
public interface CustomUserInfoDefinition extends EObject {
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
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getCustomUserInfoDefinition_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='name'"
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition#getName <em>Name</em>}' attribute.
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
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getCustomUserInfoDefinition_Description()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='description'"
     * @generated
     */
	String getDescription();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

} // CustomUserInfoDefinition
