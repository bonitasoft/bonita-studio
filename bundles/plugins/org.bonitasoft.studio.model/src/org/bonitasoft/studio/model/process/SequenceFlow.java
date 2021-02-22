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

import org.bonitasoft.studio.model.process.decision.DecisionTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.SequenceFlow#isIsDefault <em>Is Default</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.SequenceFlow#getConditionType <em>Condition Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.SequenceFlow#getDecisionTable <em>Decision Table</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.SequenceFlow#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.SequenceFlow#getPathToken <em>Path Token</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getSequenceFlow()
 * @model
 * @generated
 */
public interface SequenceFlow extends Connection {
	/**
     * Returns the value of the '<em><b>Is Default</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Default</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Is Default</em>' attribute.
     * @see #setIsDefault(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getSequenceFlow_IsDefault()
     * @model default="false"
     * @generated
     */
	boolean isIsDefault();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.SequenceFlow#isIsDefault <em>Is Default</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Default</em>' attribute.
     * @see #isIsDefault()
     * @generated
     */
	void setIsDefault(boolean value);

	/**
     * Returns the value of the '<em><b>Condition Type</b></em>' attribute.
     * The default value is <code>"EXPRESSION"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.SequenceFlowConditionType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Condition Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.SequenceFlowConditionType
     * @see #setConditionType(SequenceFlowConditionType)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getSequenceFlow_ConditionType()
     * @model default="EXPRESSION"
     * @generated
     */
	SequenceFlowConditionType getConditionType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.SequenceFlow#getConditionType <em>Condition Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.SequenceFlowConditionType
     * @see #getConditionType()
     * @generated
     */
	void setConditionType(SequenceFlowConditionType value);

	/**
     * Returns the value of the '<em><b>Decision Table</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decision Table</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Decision Table</em>' containment reference.
     * @see #setDecisionTable(DecisionTable)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getSequenceFlow_DecisionTable()
     * @model containment="true" required="true"
     * @generated
     */
	DecisionTable getDecisionTable();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.SequenceFlow#getDecisionTable <em>Decision Table</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Decision Table</em>' containment reference.
     * @see #getDecisionTable()
     * @generated
     */
	void setDecisionTable(DecisionTable value);

	/**
     * Returns the value of the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Condition</em>' containment reference.
     * @see #setCondition(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getSequenceFlow_Condition()
     * @model containment="true"
     * @generated
     */
	Expression getCondition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.SequenceFlow#getCondition <em>Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition</em>' containment reference.
     * @see #getCondition()
     * @generated
     */
	void setCondition(Expression value);

	/**
     * Returns the value of the '<em><b>Path Token</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Token</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Path Token</em>' attribute.
     * @see #setPathToken(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getSequenceFlow_PathToken()
     * @model
     * @generated
     */
	String getPathToken();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.SequenceFlow#getPathToken <em>Path Token</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Path Token</em>' attribute.
     * @see #getPathToken()
     * @generated
     */
	void setPathToken(String value);

} // SequenceFlow
