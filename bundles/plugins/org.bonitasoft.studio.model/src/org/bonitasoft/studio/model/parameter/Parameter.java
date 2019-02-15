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
package org.bonitasoft.studio.model.parameter;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.parameter.Parameter#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.parameter.Parameter#getValue <em>Value</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.parameter.Parameter#getTypeClassname <em>Type Classname</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.parameter.Parameter#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.parameter.ParameterPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends EObject {
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
     * @see org.bonitasoft.studio.model.parameter.ParameterPackage#getParameter_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.parameter.Parameter#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

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
     * @see org.bonitasoft.studio.model.parameter.ParameterPackage#getParameter_Value()
     * @model
     * @generated
     */
	String getValue();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.parameter.Parameter#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
	void setValue(String value);

	/**
     * Returns the value of the '<em><b>Type Classname</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Classname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type Classname</em>' attribute.
     * @see #setTypeClassname(String)
     * @see org.bonitasoft.studio.model.parameter.ParameterPackage#getParameter_TypeClassname()
     * @model
     * @generated
     */
	String getTypeClassname();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.parameter.Parameter#getTypeClassname <em>Type Classname</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Classname</em>' attribute.
     * @see #getTypeClassname()
     * @generated
     */
	void setTypeClassname(String value);

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
     * @see org.bonitasoft.studio.model.parameter.ParameterPackage#getParameter_Description()
     * @model
     * @generated
     */
	String getDescription();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.parameter.Parameter#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

} // Parameter
