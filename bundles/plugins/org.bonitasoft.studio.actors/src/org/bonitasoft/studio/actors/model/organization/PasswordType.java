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
 * A representation of the model object '<em><b>Password Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.PasswordType#getValue <em>Value</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.PasswordType#isEncrypted <em>Encrypted</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getPasswordType()
 * @model extendedMetaData="name='password_._type' kind='simple'"
 * @generated
 */
public interface PasswordType extends EObject {
	/**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getPasswordType_Value()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='simple' name=':0'"
     * @generated
     */
	String getValue();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.PasswordType#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
	void setValue(String value);

	/**
     * Returns the value of the '<em><b>Encrypted</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encrypted</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Encrypted</em>' attribute.
     * @see #isSetEncrypted()
     * @see #unsetEncrypted()
     * @see #setEncrypted(boolean)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getPasswordType_Encrypted()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true"
     *        extendedMetaData="kind='attribute' name='encrypted'"
     * @generated
     */
	boolean isEncrypted();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.PasswordType#isEncrypted <em>Encrypted</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Encrypted</em>' attribute.
     * @see #isSetEncrypted()
     * @see #unsetEncrypted()
     * @see #isEncrypted()
     * @generated
     */
	void setEncrypted(boolean value);

    /**
     * Unsets the value of the '{@link org.bonitasoft.studio.actors.model.organization.PasswordType#isEncrypted <em>Encrypted</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetEncrypted()
     * @see #isEncrypted()
     * @see #setEncrypted(boolean)
     * @generated
     */
    void unsetEncrypted();

    /**
     * Returns whether the value of the '{@link org.bonitasoft.studio.actors.model.organization.PasswordType#isEncrypted <em>Encrypted</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Encrypted</em>' attribute is set.
     * @see #unsetEncrypted()
     * @see #isEncrypted()
     * @see #setEncrypted(boolean)
     * @generated
     */
    boolean isSetEncrypted();

} // PasswordType
