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
package org.bonitasoft.studio.model.simulation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getResourcesUsages <em>Resources Usages</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getExecutionTime <em>Execution Time</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getEstimatedTime <em>Estimated Time</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getMaximumTime <em>Maximum Time</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationActivity#isContigous <em>Contigous</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationActivity#isExclusiveOutgoingTransition <em>Exclusive Outgoing Transition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getLoopTransition <em>Loop Transition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getDataChange <em>Data Change</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationActivity()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SimulationActivity extends SimulationDataContainer {
	/**
     * Returns the value of the '<em><b>Resources Usages</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.simulation.ResourceUsage}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources Usages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Resources Usages</em>' containment reference list.
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationActivity_ResourcesUsages()
     * @model containment="true"
     * @generated
     */
	EList<ResourceUsage> getResourcesUsages();

	/**
     * Returns the value of the '<em><b>Execution Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Execution Time</em>' attribute.
     * @see #setExecutionTime(long)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationActivity_ExecutionTime()
     * @model
     * @generated
     */
	long getExecutionTime();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getExecutionTime <em>Execution Time</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Execution Time</em>' attribute.
     * @see #getExecutionTime()
     * @generated
     */
	void setExecutionTime(long value);

	/**
     * Returns the value of the '<em><b>Estimated Time</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Estimated Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Estimated Time</em>' attribute.
     * @see #setEstimatedTime(double)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationActivity_EstimatedTime()
     * @model default="0"
     * @generated
     */
	double getEstimatedTime();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getEstimatedTime <em>Estimated Time</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Estimated Time</em>' attribute.
     * @see #getEstimatedTime()
     * @generated
     */
	void setEstimatedTime(double value);

	/**
     * Returns the value of the '<em><b>Maximum Time</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Maximum Time</em>' attribute.
     * @see #setMaximumTime(double)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationActivity_MaximumTime()
     * @model default="0"
     * @generated
     */
	double getMaximumTime();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getMaximumTime <em>Maximum Time</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Maximum Time</em>' attribute.
     * @see #getMaximumTime()
     * @generated
     */
	void setMaximumTime(double value);

	/**
     * Returns the value of the '<em><b>Contigous</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contigous</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Contigous</em>' attribute.
     * @see #setContigous(boolean)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationActivity_Contigous()
     * @model
     * @generated
     */
	boolean isContigous();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#isContigous <em>Contigous</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Contigous</em>' attribute.
     * @see #isContigous()
     * @generated
     */
	void setContigous(boolean value);

	/**
     * Returns the value of the '<em><b>Exclusive Outgoing Transition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclusive Outgoing Transition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Exclusive Outgoing Transition</em>' attribute.
     * @see #setExclusiveOutgoingTransition(boolean)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationActivity_ExclusiveOutgoingTransition()
     * @model
     * @generated
     */
	boolean isExclusiveOutgoingTransition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#isExclusiveOutgoingTransition <em>Exclusive Outgoing Transition</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exclusive Outgoing Transition</em>' attribute.
     * @see #isExclusiveOutgoingTransition()
     * @generated
     */
	void setExclusiveOutgoingTransition(boolean value);

	/**
     * Returns the value of the '<em><b>Loop Transition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Transition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Transition</em>' containment reference.
     * @see #setLoopTransition(SimulationTransition)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationActivity_LoopTransition()
     * @model containment="true"
     * @generated
     */
	SimulationTransition getLoopTransition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getLoopTransition <em>Loop Transition</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Transition</em>' containment reference.
     * @see #getLoopTransition()
     * @generated
     */
	void setLoopTransition(SimulationTransition value);

	/**
     * Returns the value of the '<em><b>Data Change</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.simulation.DataChange}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Change</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Data Change</em>' containment reference list.
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationActivity_DataChange()
     * @model containment="true"
     * @generated
     */
	EList<DataChange> getDataChange();

} // SimulationActivity
