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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Injection Period</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getBegin <em>Begin</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getEnd <em>End</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getNbInstances <em>Nb Instances</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getRepartition <em>Repartition</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getInjectionPeriod()
 * @model
 * @generated
 */
public interface InjectionPeriod extends EObject {
	/**
     * Returns the value of the '<em><b>Begin</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Begin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Begin</em>' attribute.
     * @see #setBegin(long)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getInjectionPeriod_Begin()
     * @model default="0" required="true"
     * @generated
     */
	long getBegin();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getBegin <em>Begin</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Begin</em>' attribute.
     * @see #getBegin()
     * @generated
     */
	void setBegin(long value);

	/**
     * Returns the value of the '<em><b>End</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>End</em>' attribute.
     * @see #setEnd(long)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getInjectionPeriod_End()
     * @model default="0" required="true"
     * @generated
     */
	long getEnd();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getEnd <em>End</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>End</em>' attribute.
     * @see #getEnd()
     * @generated
     */
	void setEnd(long value);

	/**
     * Returns the value of the '<em><b>Nb Instances</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nb Instances</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Nb Instances</em>' attribute.
     * @see #setNbInstances(int)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getInjectionPeriod_NbInstances()
     * @model default="1" required="true"
     * @generated
     */
	int getNbInstances();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getNbInstances <em>Nb Instances</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nb Instances</em>' attribute.
     * @see #getNbInstances()
     * @generated
     */
	void setNbInstances(int value);

	/**
     * Returns the value of the '<em><b>Repartition</b></em>' attribute.
     * The default value is <code>"CONSTANT"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.simulation.RepartitionType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repartition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Repartition</em>' attribute.
     * @see org.bonitasoft.studio.model.simulation.RepartitionType
     * @see #setRepartition(RepartitionType)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getInjectionPeriod_Repartition()
     * @model default="CONSTANT" required="true"
     * @generated
     */
	RepartitionType getRepartition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getRepartition <em>Repartition</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Repartition</em>' attribute.
     * @see org.bonitasoft.studio.model.simulation.RepartitionType
     * @see #getRepartition()
     * @generated
     */
	void setRepartition(RepartitionType value);

} // InjectionPeriod
