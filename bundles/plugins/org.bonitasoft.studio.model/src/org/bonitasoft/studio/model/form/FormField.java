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
package org.bonitasoft.studio.model.form;

import org.bonitasoft.studio.model.expression.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.FormField#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FormField#getExampleMessagePosition <em>Example Message Position</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FormField#getExampleMessage <em>Example Message</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getFormField()
 * @model abstract="true"
 * @generated
 */
public interface FormField extends Widget, Validable, Duplicable {
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
     * @see org.bonitasoft.studio.model.form.FormPackage#getFormField_Description()
     * @model
     * @generated
     */
	String getDescription();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FormField#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

	/**
     * Returns the value of the '<em><b>Example Message Position</b></em>' attribute.
     * The default value is <code>"Down"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.form.LabelPosition}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Example Message Position</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Example Message Position</em>' attribute.
     * @see org.bonitasoft.studio.model.form.LabelPosition
     * @see #setExampleMessagePosition(LabelPosition)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFormField_ExampleMessagePosition()
     * @model default="Down"
     * @generated
     */
	LabelPosition getExampleMessagePosition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FormField#getExampleMessagePosition <em>Example Message Position</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Example Message Position</em>' attribute.
     * @see org.bonitasoft.studio.model.form.LabelPosition
     * @see #getExampleMessagePosition()
     * @generated
     */
	void setExampleMessagePosition(LabelPosition value);

	/**
     * Returns the value of the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Example Message</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Example Message</em>' containment reference.
     * @see #setExampleMessage(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFormField_ExampleMessage()
     * @model containment="true"
     * @generated
     */
	Expression getExampleMessage();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FormField#getExampleMessage <em>Example Message</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Example Message</em>' containment reference.
     * @see #getExampleMessage()
     * @generated
     */
	void setExampleMessage(Expression value);

} // FormField
