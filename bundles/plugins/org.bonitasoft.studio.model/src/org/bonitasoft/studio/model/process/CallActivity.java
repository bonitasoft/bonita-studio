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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.CallActivity#getInputMappings <em>Input Mappings</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.CallActivity#getOutputMappings <em>Output Mappings</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.CallActivity#getCalledActivityName <em>Called Activity Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.CallActivity#getCalledActivityVersion <em>Called Activity Version</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getCallActivity()
 * @model
 * @generated
 */
public interface CallActivity extends Activity {
	/**
     * Returns the value of the '<em><b>Input Mappings</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.InputMapping}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Input Mappings</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getCallActivity_InputMappings()
     * @model containment="true"
     * @generated
     */
	EList<InputMapping> getInputMappings();

	/**
     * Returns the value of the '<em><b>Output Mappings</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.OutputMapping}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Output Mappings</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getCallActivity_OutputMappings()
     * @model containment="true"
     * @generated
     */
	EList<OutputMapping> getOutputMappings();

	/**
     * Returns the value of the '<em><b>Called Activity Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Activity Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Called Activity Name</em>' containment reference.
     * @see #setCalledActivityName(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getCallActivity_CalledActivityName()
     * @model containment="true"
     * @generated
     */
	Expression getCalledActivityName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.CallActivity#getCalledActivityName <em>Called Activity Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Called Activity Name</em>' containment reference.
     * @see #getCalledActivityName()
     * @generated
     */
	void setCalledActivityName(Expression value);

	/**
     * Returns the value of the '<em><b>Called Activity Version</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Activity Version</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Called Activity Version</em>' containment reference.
     * @see #setCalledActivityVersion(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getCallActivity_CalledActivityVersion()
     * @model containment="true"
     * @generated
     */
	Expression getCalledActivityVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.CallActivity#getCalledActivityVersion <em>Called Activity Version</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Called Activity Version</em>' containment reference.
     * @see #getCalledActivityVersion()
     * @generated
     */
	void setCalledActivityVersion(Expression value);

} // CallActivity
