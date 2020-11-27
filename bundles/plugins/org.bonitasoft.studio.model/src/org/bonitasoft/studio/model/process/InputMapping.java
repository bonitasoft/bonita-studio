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
 * A representation of the model object '<em><b>Input Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.InputMapping#getProcessSource <em>Process Source</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.InputMapping#getSubprocessTarget <em>Subprocess Target</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.InputMapping#getAssignationType <em>Assignation Type</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getInputMapping()
 * @model
 * @generated
 */
public interface InputMapping extends EObject {
	/**
     * Returns the value of the '<em><b>Process Source</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Process Source</em>' containment reference.
     * @see #setProcessSource(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getInputMapping_ProcessSource()
     * @model containment="true"
     * @generated
     */
	Expression getProcessSource();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.InputMapping#getProcessSource <em>Process Source</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Process Source</em>' containment reference.
     * @see #getProcessSource()
     * @generated
     */
	void setProcessSource(Expression value);

	/**
     * Returns the value of the '<em><b>Subprocess Target</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subprocess Target</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Subprocess Target</em>' attribute.
     * @see #setSubprocessTarget(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getInputMapping_SubprocessTarget()
     * @model
     * @generated
     */
	String getSubprocessTarget();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.InputMapping#getSubprocessTarget <em>Subprocess Target</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Subprocess Target</em>' attribute.
     * @see #getSubprocessTarget()
     * @generated
     */
	void setSubprocessTarget(String value);

	/**
     * Returns the value of the '<em><b>Assignation Type</b></em>' attribute.
     * The default value is <code>"ContractInput"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.InputMappingAssignationType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assignation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Assignation Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.InputMappingAssignationType
     * @see #setAssignationType(InputMappingAssignationType)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getInputMapping_AssignationType()
     * @model default="ContractInput" required="true"
     * @generated
     */
	InputMappingAssignationType getAssignationType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.InputMapping#getAssignationType <em>Assignation Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Assignation Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.InputMappingAssignationType
     * @see #getAssignationType()
     * @generated
     */
	void setAssignationType(InputMappingAssignationType value);

} // InputMapping
