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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess#getLoadProfileID <em>Load Profile ID</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess#getMaximumTime <em>Maximum Time</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationAbstractProcess()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SimulationAbstractProcess extends SimulationDataContainer {
	/**
     * Returns the value of the '<em><b>Load Profile ID</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Load Profile ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Load Profile ID</em>' attribute.
     * @see #setLoadProfileID(String)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationAbstractProcess_LoadProfileID()
     * @model
     * @generated
     */
	String getLoadProfileID();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess#getLoadProfileID <em>Load Profile ID</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Load Profile ID</em>' attribute.
     * @see #getLoadProfileID()
     * @generated
     */
	void setLoadProfileID(String value);

	/**
     * Returns the value of the '<em><b>Maximum Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Maximum Time</em>' attribute.
     * @see #setMaximumTime(long)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationAbstractProcess_MaximumTime()
     * @model
     * @generated
     */
	long getMaximumTime();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess#getMaximumTime <em>Maximum Time</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Maximum Time</em>' attribute.
     * @see #getMaximumTime()
     * @generated
     */
	void setMaximumTime(long value);

} // SimulationAbstractProcess
