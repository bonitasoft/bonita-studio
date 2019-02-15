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
 * A representation of the model object '<em><b>Multiple Valuated Form Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.MultipleValuatedFormField#getDefaultExpression <em>Default Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.MultipleValuatedFormField#getDefaultExpressionAfterEvent <em>Default Expression After Event</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getMultipleValuatedFormField()
 * @model abstract="true"
 * @generated
 */
public interface MultipleValuatedFormField extends FormField {
	/**
     * Returns the value of the '<em><b>Default Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Expression</em>' containment reference.
     * @see #setDefaultExpression(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getMultipleValuatedFormField_DefaultExpression()
     * @model containment="true"
     * @generated
     */
	Expression getDefaultExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.MultipleValuatedFormField#getDefaultExpression <em>Default Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Expression</em>' containment reference.
     * @see #getDefaultExpression()
     * @generated
     */
	void setDefaultExpression(Expression value);

	/**
     * Returns the value of the '<em><b>Default Expression After Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Expression After Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Expression After Event</em>' containment reference.
     * @see #setDefaultExpressionAfterEvent(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getMultipleValuatedFormField_DefaultExpressionAfterEvent()
     * @model containment="true"
     * @generated
     */
	Expression getDefaultExpressionAfterEvent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.MultipleValuatedFormField#getDefaultExpressionAfterEvent <em>Default Expression After Event</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Expression After Event</em>' containment reference.
     * @see #getDefaultExpressionAfterEvent()
     * @generated
     */
	void setDefaultExpressionAfterEvent(Expression value);

} // MultipleValuatedFormField
