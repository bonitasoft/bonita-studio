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
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.Group#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.Group#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.Group#getIconName <em>Icon Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.Group#getIconPath <em>Icon Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.Group#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.Group#getParentPath <em>Parent Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getGroup()
 * @model extendedMetaData="name='Group' kind='elementOnly'"
 * @generated
 */
public interface Group extends EObject {
	/**
     * Returns the value of the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getGroup_DisplayName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='displayName'"
     * @generated
     */
	String getDisplayName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.Group#getDisplayName <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Name</em>' attribute.
     * @see #getDisplayName()
     * @generated
     */
	void setDisplayName(String value);

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
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getGroup_Description()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='description'"
     * @generated
     */
	String getDescription();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.Group#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

	/**
     * Returns the value of the '<em><b>Icon Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Icon Name</em>' attribute.
     * @see #setIconName(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getGroup_IconName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='iconName'"
     * @generated
     */
	String getIconName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.Group#getIconName <em>Icon Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Icon Name</em>' attribute.
     * @see #getIconName()
     * @generated
     */
	void setIconName(String value);

	/**
     * Returns the value of the '<em><b>Icon Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getGroup_IconPath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='iconPath'"
     * @generated
     */
	String getIconPath();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.Group#getIconPath <em>Icon Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Icon Path</em>' attribute.
     * @see #getIconPath()
     * @generated
     */
	void setIconPath(String value);

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
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getGroup_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.Group#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Parent Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parent Path</em>' attribute.
     * @see #setParentPath(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getGroup_ParentPath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='parentPath'"
     * @generated
     */
	String getParentPath();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.Group#getParentPath <em>Parent Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent Path</em>' attribute.
     * @see #getParentPath()
     * @generated
     */
	void setParentPath(String value);

} // Group
