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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Day Period</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.DayPeriod#getDay <em>Day</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.DayPeriod#getStartHour <em>Start Hour</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.DayPeriod#getEndHour <em>End Hour</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.DayPeriod#getStartMinute <em>Start Minute</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.DayPeriod#getEndMinute <em>End Minute</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getDayPeriod()
 * @model
 * @generated
 */
public interface DayPeriod extends EObject {
	/**
     * Returns the value of the '<em><b>Day</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Integer}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Day</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Day</em>' attribute list.
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getDayPeriod_Day()
     * @model
     * @generated
     */
	EList<Integer> getDay();

	/**
     * Returns the value of the '<em><b>Start Hour</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Hour</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Start Hour</em>' attribute.
     * @see #setStartHour(int)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getDayPeriod_StartHour()
     * @model default="0" required="true"
     * @generated
     */
	int getStartHour();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.DayPeriod#getStartHour <em>Start Hour</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Hour</em>' attribute.
     * @see #getStartHour()
     * @generated
     */
	void setStartHour(int value);

	/**
     * Returns the value of the '<em><b>End Hour</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Hour</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>End Hour</em>' attribute.
     * @see #setEndHour(int)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getDayPeriod_EndHour()
     * @model default="0" required="true"
     * @generated
     */
	int getEndHour();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.DayPeriod#getEndHour <em>End Hour</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Hour</em>' attribute.
     * @see #getEndHour()
     * @generated
     */
	void setEndHour(int value);

	/**
     * Returns the value of the '<em><b>Start Minute</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Minute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Start Minute</em>' attribute.
     * @see #setStartMinute(int)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getDayPeriod_StartMinute()
     * @model default="0" required="true"
     * @generated
     */
	int getStartMinute();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.DayPeriod#getStartMinute <em>Start Minute</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Minute</em>' attribute.
     * @see #getStartMinute()
     * @generated
     */
	void setStartMinute(int value);

	/**
     * Returns the value of the '<em><b>End Minute</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Minute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>End Minute</em>' attribute.
     * @see #setEndMinute(int)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getDayPeriod_EndMinute()
     * @model default="0" required="true"
     * @generated
     */
	int getEndMinute();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.DayPeriod#getEndMinute <em>End Minute</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Minute</em>' attribute.
     * @see #getEndMinute()
     * @generated
     */
	void setEndMinute(int value);

} // DayPeriod
