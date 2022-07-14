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
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.Task#isOverrideActorsOfTheLane <em>Override Actors Of The Lane</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Task#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Task#getExpectedDuration <em>Expected Duration</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getTask()
 * @model
 * @generated
 */
public interface Task extends Activity, PageFlow, Assignable, ContractContainer {
	/**
     * Returns the value of the '<em><b>Override Actors Of The Lane</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Override Actors Of The Lane</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Override Actors Of The Lane</em>' attribute.
     * @see #setOverrideActorsOfTheLane(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getTask_OverrideActorsOfTheLane()
     * @model default="true"
     * @generated
     */
	boolean isOverrideActorsOfTheLane();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Task#isOverrideActorsOfTheLane <em>Override Actors Of The Lane</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Override Actors Of The Lane</em>' attribute.
     * @see #isOverrideActorsOfTheLane()
     * @generated
     */
	void setOverrideActorsOfTheLane(boolean value);

	/**
     * Returns the value of the '<em><b>Priority</b></em>' attribute.
     * The default value is <code>"2"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Priority</em>' attribute.
     * @see #setPriority(int)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getTask_Priority()
     * @model default="2"
     * @generated
     */
	int getPriority();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Task#getPriority <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Priority</em>' attribute.
     * @see #getPriority()
     * @generated
     */
	void setPriority(int value);

	/**
     * Returns the value of the '<em><b>Expected Duration</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expected Duration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Expected Duration</em>' containment reference.
     * @see #setExpectedDuration(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getTask_ExpectedDuration()
     * @model containment="true"
     * @generated
     */
	Expression getExpectedDuration();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Task#getExpectedDuration <em>Expected Duration</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expected Duration</em>' containment reference.
     * @see #getExpectedDuration()
     * @generated
     */
	void setExpectedDuration(Expression value);

} // Task
