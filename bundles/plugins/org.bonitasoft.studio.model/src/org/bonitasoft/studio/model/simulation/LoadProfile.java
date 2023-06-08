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
 * A representation of the model object '<em><b>Load Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.LoadProfile#getCalendar <em>Calendar</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.LoadProfile#getInjectionPeriods <em>Injection Periods</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getLoadProfile()
 * @model
 * @generated
 */
public interface LoadProfile extends SimulationElement, ModelVersion {
	/**
     * Returns the value of the '<em><b>Calendar</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calendar</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Calendar</em>' containment reference.
     * @see #setCalendar(SimulationCalendar)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getLoadProfile_Calendar()
     * @model containment="true"
     * @generated
     */
	SimulationCalendar getCalendar();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.LoadProfile#getCalendar <em>Calendar</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Calendar</em>' containment reference.
     * @see #getCalendar()
     * @generated
     */
	void setCalendar(SimulationCalendar value);

	/**
     * Returns the value of the '<em><b>Injection Periods</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.simulation.InjectionPeriod}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Injection Periods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Injection Periods</em>' containment reference list.
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getLoadProfile_InjectionPeriods()
     * @model containment="true"
     * @generated
     */
	EList<InjectionPeriod> getInjectionPeriods();

} // LoadProfile
