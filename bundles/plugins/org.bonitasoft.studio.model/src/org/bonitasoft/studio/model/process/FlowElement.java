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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.FlowElement#getDynamicLabel <em>Dynamic Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.FlowElement#getDynamicDescription <em>Dynamic Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.FlowElement#getStepSummary <em>Step Summary</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getFlowElement()
 * @model
 * @generated
 */
public interface FlowElement extends Element, SourceElement, TargetElement {
	/**
     * Returns the value of the '<em><b>Dynamic Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Label</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Dynamic Label</em>' containment reference.
     * @see #setDynamicLabel(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getFlowElement_DynamicLabel()
     * @model containment="true"
     * @generated
     */
	Expression getDynamicLabel();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.FlowElement#getDynamicLabel <em>Dynamic Label</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dynamic Label</em>' containment reference.
     * @see #getDynamicLabel()
     * @generated
     */
	void setDynamicLabel(Expression value);

	/**
     * Returns the value of the '<em><b>Dynamic Description</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Description</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Dynamic Description</em>' containment reference.
     * @see #setDynamicDescription(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getFlowElement_DynamicDescription()
     * @model containment="true"
     * @generated
     */
	Expression getDynamicDescription();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.FlowElement#getDynamicDescription <em>Dynamic Description</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dynamic Description</em>' containment reference.
     * @see #getDynamicDescription()
     * @generated
     */
	void setDynamicDescription(Expression value);

	/**
     * Returns the value of the '<em><b>Step Summary</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Step Summary</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Step Summary</em>' containment reference.
     * @see #setStepSummary(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getFlowElement_StepSummary()
     * @model containment="true"
     * @generated
     */
	Expression getStepSummary();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.FlowElement#getStepSummary <em>Step Summary</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Step Summary</em>' containment reference.
     * @see #getStepSummary()
     * @generated
     */
	void setStepSummary(Expression value);

} // FlowElement
