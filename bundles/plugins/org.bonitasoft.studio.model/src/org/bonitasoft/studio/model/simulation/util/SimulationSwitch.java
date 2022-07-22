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
package org.bonitasoft.studio.model.simulation.util;

import org.bonitasoft.studio.model.simulation.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage
 * @generated
 */
public class SimulationSwitch<T> extends Switch<T> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static SimulationPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SimulationSwitch() {
        if (modelPackage == null) {
            modelPackage = SimulationPackage.eINSTANCE;
        }
    }

	/**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case SimulationPackage.SIMULATION_ELEMENT: {
                SimulationElement simulationElement = (SimulationElement)theEObject;
                T result = caseSimulationElement(simulationElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_DATA: {
                SimulationData simulationData = (SimulationData)theEObject;
                T result = caseSimulationData(simulationData);
                if (result == null) result = caseSimulationElement(simulationData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_TRANSITION: {
                SimulationTransition simulationTransition = (SimulationTransition)theEObject;
                T result = caseSimulationTransition(simulationTransition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.RESOURCE_USAGE: {
                ResourceUsage resourceUsage = (ResourceUsage)theEObject;
                T result = caseResourceUsage(resourceUsage);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.INJECTION_PERIOD: {
                InjectionPeriod injectionPeriod = (InjectionPeriod)theEObject;
                T result = caseInjectionPeriod(injectionPeriod);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_BOOLEAN: {
                SimulationBoolean simulationBoolean = (SimulationBoolean)theEObject;
                T result = caseSimulationBoolean(simulationBoolean);
                if (result == null) result = caseSimulationData(simulationBoolean);
                if (result == null) result = caseSimulationElement(simulationBoolean);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_NUMBER_DATA: {
                SimulationNumberData simulationNumberData = (SimulationNumberData)theEObject;
                T result = caseSimulationNumberData(simulationNumberData);
                if (result == null) result = caseSimulationData(simulationNumberData);
                if (result == null) result = caseSimulationElement(simulationNumberData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_LITERAL_DATA: {
                SimulationLiteralData simulationLiteralData = (SimulationLiteralData)theEObject;
                T result = caseSimulationLiteralData(simulationLiteralData);
                if (result == null) result = caseSimulationData(simulationLiteralData);
                if (result == null) result = caseSimulationElement(simulationLiteralData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_LITERAL: {
                SimulationLiteral simulationLiteral = (SimulationLiteral)theEObject;
                T result = caseSimulationLiteral(simulationLiteral);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_NUMBER_RANGE: {
                SimulationNumberRange simulationNumberRange = (SimulationNumberRange)theEObject;
                T result = caseSimulationNumberRange(simulationNumberRange);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_DATA_CONTAINER: {
                SimulationDataContainer simulationDataContainer = (SimulationDataContainer)theEObject;
                T result = caseSimulationDataContainer(simulationDataContainer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_ABSTRACT_PROCESS: {
                SimulationAbstractProcess simulationAbstractProcess = (SimulationAbstractProcess)theEObject;
                T result = caseSimulationAbstractProcess(simulationAbstractProcess);
                if (result == null) result = caseSimulationDataContainer(simulationAbstractProcess);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_ACTIVITY: {
                SimulationActivity simulationActivity = (SimulationActivity)theEObject;
                T result = caseSimulationActivity(simulationActivity);
                if (result == null) result = caseSimulationDataContainer(simulationActivity);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.DATA_CHANGE: {
                DataChange dataChange = (DataChange)theEObject;
                T result = caseDataChange(dataChange);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.SIMULATION_CALENDAR: {
                SimulationCalendar simulationCalendar = (SimulationCalendar)theEObject;
                T result = caseSimulationCalendar(simulationCalendar);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.DAY_PERIOD: {
                DayPeriod dayPeriod = (DayPeriod)theEObject;
                T result = caseDayPeriod(dayPeriod);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.MODEL_VERSION: {
                ModelVersion modelVersion = (ModelVersion)theEObject;
                T result = caseModelVersion(modelVersion);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.LOAD_PROFILE: {
                LoadProfile loadProfile = (LoadProfile)theEObject;
                T result = caseLoadProfile(loadProfile);
                if (result == null) result = caseSimulationElement(loadProfile);
                if (result == null) result = caseModelVersion(loadProfile);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SimulationPackage.RESOURCE: {
                Resource resource = (Resource)theEObject;
                T result = caseResource(resource);
                if (result == null) result = caseSimulationElement(resource);
                if (result == null) result = caseModelVersion(resource);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationElement(SimulationElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Data</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationData(SimulationData object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Transition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Transition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationTransition(SimulationTransition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Resource Usage</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resource Usage</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseResourceUsage(ResourceUsage object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Injection Period</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Injection Period</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseInjectionPeriod(InjectionPeriod object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Boolean</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boolean</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationBoolean(SimulationBoolean object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Number Data</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Number Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationNumberData(SimulationNumberData object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Literal Data</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Literal Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationLiteralData(SimulationLiteralData object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Literal</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Literal</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationLiteral(SimulationLiteral object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Number Range</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Number Range</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationNumberRange(SimulationNumberRange object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Data Container</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationDataContainer(SimulationDataContainer object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Process</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Process</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationAbstractProcess(SimulationAbstractProcess object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Activity</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Activity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationActivity(SimulationActivity object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Data Change</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Change</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDataChange(DataChange object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Calendar</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Calendar</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSimulationCalendar(SimulationCalendar object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Day Period</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Day Period</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDayPeriod(DayPeriod object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Model Version</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Version</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseModelVersion(ModelVersion object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Load Profile</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Load Profile</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseLoadProfile(LoadProfile object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseResource(Resource object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	@Override
	public T defaultCase(EObject object) {
        return null;
    }

} //SimulationSwitch
