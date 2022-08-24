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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Date Form Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.DateFormField#getInitialFormat <em>Initial Format</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DateFormField#getDisplayFormat <em>Display Format</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getDateFormField()
 * @model
 * @generated
 */
public interface DateFormField extends SingleValuatedFormField {
	/**
     * Returns the value of the '<em><b>Initial Format</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Format</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Initial Format</em>' attribute.
     * @see #setInitialFormat(String)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDateFormField_InitialFormat()
     * @model
     * @generated
     */
	String getInitialFormat();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DateFormField#getInitialFormat <em>Initial Format</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Initial Format</em>' attribute.
     * @see #getInitialFormat()
     * @generated
     */
	void setInitialFormat(String value);

	/**
     * Returns the value of the '<em><b>Display Format</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Format</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Display Format</em>' attribute.
     * @see #setDisplayFormat(String)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDateFormField_DisplayFormat()
     * @model
     * @generated
     */
	String getDisplayFormat();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DateFormField#getDisplayFormat <em>Display Format</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Format</em>' attribute.
     * @see #getDisplayFormat()
     * @generated
     */
	void setDisplayFormat(String value);

} // DateFormField
