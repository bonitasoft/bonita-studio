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
package org.bonitasoft.studio.model.process;

import org.bonitasoft.studio.model.expression.Expression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.FormMapping#getTargetForm <em>Target Form</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.FormMapping#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.FormMapping#getUrl <em>Url</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getFormMapping()
 * @model
 * @generated
 */
public interface FormMapping extends EObject {
	/**
     * Returns the value of the '<em><b>Target Form</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Form</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Target Form</em>' containment reference.
     * @see #setTargetForm(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getFormMapping_TargetForm()
     * @model containment="true"
     * @generated
     */
	Expression getTargetForm();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.FormMapping#getTargetForm <em>Target Form</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Form</em>' containment reference.
     * @see #getTargetForm()
     * @generated
     */
	void setTargetForm(Expression value);

	/**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>"INTERNAL"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.FormMappingType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.FormMappingType
     * @see #setType(FormMappingType)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getFormMapping_Type()
     * @model default="INTERNAL"
     * @generated
     */
	FormMappingType getType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.FormMapping#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.FormMappingType
     * @see #getType()
     * @generated
     */
	void setType(FormMappingType value);

	/**
     * Returns the value of the '<em><b>Url</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Url</em>' attribute.
     * @see #setUrl(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getFormMapping_Url()
     * @model
     * @generated
     */
	String getUrl();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.FormMapping#getUrl <em>Url</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Url</em>' attribute.
     * @see #getUrl()
     * @generated
     */
	void setUrl(String value);

} // FormMapping
