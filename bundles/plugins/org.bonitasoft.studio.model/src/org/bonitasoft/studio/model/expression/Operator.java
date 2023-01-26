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
package org.bonitasoft.studio.model.expression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.expression.Operator#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Operator#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Operator#getInputTypes <em>Input Types</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getOperator()
 * @model
 * @generated
 */
public interface Operator extends EObject {
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
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getOperator_Type()
     * @model required="true"
     * @generated
     */
	String getType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Operator#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
	void setType(String value);

	/**
     * Returns the value of the '<em><b>Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' attribute.
     * @see #setExpression(String)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getOperator_Expression()
     * @model
     * @generated
     */
	String getExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Operator#getExpression <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' attribute.
     * @see #getExpression()
     * @generated
     */
	void setExpression(String value);

	/**
     * Returns the value of the '<em><b>Input Types</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Types</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Input Types</em>' attribute list.
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getOperator_InputTypes()
     * @model
     * @generated
     */
	EList<String> getInputTypes();

} // Operator
