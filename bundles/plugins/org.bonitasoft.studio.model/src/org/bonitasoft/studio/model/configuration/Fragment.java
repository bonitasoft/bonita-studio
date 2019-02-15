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
package org.bonitasoft.studio.model.configuration;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Fragment#getKey <em>Key</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Fragment#getValue <em>Value</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Fragment#isExported <em>Exported</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Fragment#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragment()
 * @model
 * @generated
 */
public interface Fragment extends EObject {
	/**
     * Returns the value of the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Key</em>' attribute.
     * @see #setKey(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragment_Key()
     * @model required="true"
     * @generated
     */
	String getKey();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Fragment#getKey <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Key</em>' attribute.
     * @see #getKey()
     * @generated
     */
	void setKey(String value);

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
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragment_Value()
     * @model
     * @generated
     */
	String getValue();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Fragment#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
	void setValue(String value);

	/**
     * Returns the value of the '<em><b>Exported</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Exported</em>' attribute.
     * @see #setExported(boolean)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragment_Exported()
     * @model default="true"
     * @generated
     */
	boolean isExported();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Fragment#isExported <em>Exported</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exported</em>' attribute.
     * @see #isExported()
     * @generated
     */
	void setExported(boolean value);

	/**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragment_Type()
     * @model required="true"
     * @generated
     */
	String getType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Fragment#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
	void setType(String value);

} // Fragment
