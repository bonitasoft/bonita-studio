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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage
 * @generated
 */
public interface SimulationFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	SimulationFactory eINSTANCE = org.bonitasoft.studio.model.simulation.impl.SimulationFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Element</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Element</em>'.
     * @generated
     */
	SimulationElement createSimulationElement();

	/**
     * Returns a new object of class '<em>Transition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Transition</em>'.
     * @generated
     */
	SimulationTransition createSimulationTransition();

	/**
     * Returns a new object of class '<em>Resource Usage</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Resource Usage</em>'.
     * @generated
     */
	ResourceUsage createResourceUsage();

	/**
     * Returns a new object of class '<em>Injection Period</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Injection Period</em>'.
     * @generated
     */
	InjectionPeriod createInjectionPeriod();

	/**
     * Returns a new object of class '<em>Boolean</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Boolean</em>'.
     * @generated
     */
	SimulationBoolean createSimulationBoolean();

	/**
     * Returns a new object of class '<em>Number Data</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Number Data</em>'.
     * @generated
     */
	SimulationNumberData createSimulationNumberData();

	/**
     * Returns a new object of class '<em>Literal Data</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Literal Data</em>'.
     * @generated
     */
	SimulationLiteralData createSimulationLiteralData();

	/**
     * Returns a new object of class '<em>Literal</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Literal</em>'.
     * @generated
     */
	SimulationLiteral createSimulationLiteral();

	/**
     * Returns a new object of class '<em>Number Range</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Number Range</em>'.
     * @generated
     */
	SimulationNumberRange createSimulationNumberRange();

	/**
     * Returns a new object of class '<em>Data Change</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Change</em>'.
     * @generated
     */
	DataChange createDataChange();

	/**
     * Returns a new object of class '<em>Calendar</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Calendar</em>'.
     * @generated
     */
	SimulationCalendar createSimulationCalendar();

	/**
     * Returns a new object of class '<em>Day Period</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Day Period</em>'.
     * @generated
     */
	DayPeriod createDayPeriod();

	/**
     * Returns a new object of class '<em>Model Version</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Model Version</em>'.
     * @generated
     */
	ModelVersion createModelVersion();

	/**
     * Returns a new object of class '<em>Load Profile</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Load Profile</em>'.
     * @generated
     */
	LoadProfile createLoadProfile();

	/**
     * Returns a new object of class '<em>Resource</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Resource</em>'.
     * @generated
     */
	Resource createResource();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	SimulationPackage getSimulationPackage();

} //SimulationFactory
