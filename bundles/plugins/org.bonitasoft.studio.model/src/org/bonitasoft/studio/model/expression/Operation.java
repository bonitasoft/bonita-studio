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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.expression.Operation#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Operation#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Operation#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends EObject {
	/**
     * Returns the value of the '<em><b>Left Operand</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Left Operand</em>' containment reference.
     * @see #setLeftOperand(Expression)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getOperation_LeftOperand()
     * @model containment="true" required="true"
     * @generated
     */
	Expression getLeftOperand();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Operation#getLeftOperand <em>Left Operand</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Left Operand</em>' containment reference.
     * @see #getLeftOperand()
     * @generated
     */
	void setLeftOperand(Expression value);

	/**
     * Returns the value of the '<em><b>Right Operand</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Right Operand</em>' containment reference.
     * @see #setRightOperand(Expression)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getOperation_RightOperand()
     * @model containment="true" required="true"
     * @generated
     */
	Expression getRightOperand();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Operation#getRightOperand <em>Right Operand</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right Operand</em>' containment reference.
     * @see #getRightOperand()
     * @generated
     */
	void setRightOperand(Expression value);

	/**
     * Returns the value of the '<em><b>Operator</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Operator</em>' containment reference.
     * @see #setOperator(Operator)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getOperation_Operator()
     * @model containment="true" required="true"
     * @generated
     */
	Operator getOperator();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Operation#getOperator <em>Operator</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operator</em>' containment reference.
     * @see #getOperator()
     * @generated
     */
	void setOperator(Operator value);

} // Operation
