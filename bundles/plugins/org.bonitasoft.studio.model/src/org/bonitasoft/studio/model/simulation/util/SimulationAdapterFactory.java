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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage
 * @generated
 */
public class SimulationAdapterFactory extends AdapterFactoryImpl {
	/**
     * The cached model package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static SimulationPackage modelPackage;

	/**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SimulationAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = SimulationPackage.eINSTANCE;
        }
    }

	/**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
	@Override
	public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

	/**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SimulationSwitch<Adapter> modelSwitch =
		new SimulationSwitch<Adapter>() {
            @Override
            public Adapter caseSimulationElement(SimulationElement object) {
                return createSimulationElementAdapter();
            }
            @Override
            public Adapter caseSimulationData(SimulationData object) {
                return createSimulationDataAdapter();
            }
            @Override
            public Adapter caseSimulationTransition(SimulationTransition object) {
                return createSimulationTransitionAdapter();
            }
            @Override
            public Adapter caseResourceUsage(ResourceUsage object) {
                return createResourceUsageAdapter();
            }
            @Override
            public Adapter caseInjectionPeriod(InjectionPeriod object) {
                return createInjectionPeriodAdapter();
            }
            @Override
            public Adapter caseSimulationBoolean(SimulationBoolean object) {
                return createSimulationBooleanAdapter();
            }
            @Override
            public Adapter caseSimulationNumberData(SimulationNumberData object) {
                return createSimulationNumberDataAdapter();
            }
            @Override
            public Adapter caseSimulationLiteralData(SimulationLiteralData object) {
                return createSimulationLiteralDataAdapter();
            }
            @Override
            public Adapter caseSimulationLiteral(SimulationLiteral object) {
                return createSimulationLiteralAdapter();
            }
            @Override
            public Adapter caseSimulationNumberRange(SimulationNumberRange object) {
                return createSimulationNumberRangeAdapter();
            }
            @Override
            public Adapter caseSimulationDataContainer(SimulationDataContainer object) {
                return createSimulationDataContainerAdapter();
            }
            @Override
            public Adapter caseSimulationAbstractProcess(SimulationAbstractProcess object) {
                return createSimulationAbstractProcessAdapter();
            }
            @Override
            public Adapter caseSimulationActivity(SimulationActivity object) {
                return createSimulationActivityAdapter();
            }
            @Override
            public Adapter caseDataChange(DataChange object) {
                return createDataChangeAdapter();
            }
            @Override
            public Adapter caseSimulationCalendar(SimulationCalendar object) {
                return createSimulationCalendarAdapter();
            }
            @Override
            public Adapter caseDayPeriod(DayPeriod object) {
                return createDayPeriodAdapter();
            }
            @Override
            public Adapter caseModelVersion(ModelVersion object) {
                return createModelVersionAdapter();
            }
            @Override
            public Adapter caseLoadProfile(LoadProfile object) {
                return createLoadProfileAdapter();
            }
            @Override
            public Adapter caseResource(Resource object) {
                return createResourceAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

	/**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
	@Override
	public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationElement <em>Element</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationElement
     * @generated
     */
	public Adapter createSimulationElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationData <em>Data</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationData
     * @generated
     */
	public Adapter createSimulationDataAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationTransition <em>Transition</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationTransition
     * @generated
     */
	public Adapter createSimulationTransitionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.ResourceUsage <em>Resource Usage</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.ResourceUsage
     * @generated
     */
	public Adapter createResourceUsageAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod <em>Injection Period</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.InjectionPeriod
     * @generated
     */
	public Adapter createInjectionPeriodAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationBoolean <em>Boolean</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationBoolean
     * @generated
     */
	public Adapter createSimulationBooleanAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationNumberData <em>Number Data</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationNumberData
     * @generated
     */
	public Adapter createSimulationNumberDataAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationLiteralData <em>Literal Data</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationLiteralData
     * @generated
     */
	public Adapter createSimulationLiteralDataAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationLiteral <em>Literal</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationLiteral
     * @generated
     */
	public Adapter createSimulationLiteralAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationNumberRange <em>Number Range</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationNumberRange
     * @generated
     */
	public Adapter createSimulationNumberRangeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationDataContainer <em>Data Container</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationDataContainer
     * @generated
     */
	public Adapter createSimulationDataContainerAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess <em>Abstract Process</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationAbstractProcess
     * @generated
     */
	public Adapter createSimulationAbstractProcessAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationActivity <em>Activity</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity
     * @generated
     */
	public Adapter createSimulationActivityAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.DataChange <em>Data Change</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.DataChange
     * @generated
     */
	public Adapter createDataChangeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.SimulationCalendar <em>Calendar</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.SimulationCalendar
     * @generated
     */
	public Adapter createSimulationCalendarAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.DayPeriod <em>Day Period</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.DayPeriod
     * @generated
     */
	public Adapter createDayPeriodAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.ModelVersion <em>Model Version</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.ModelVersion
     * @generated
     */
	public Adapter createModelVersionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.LoadProfile <em>Load Profile</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.LoadProfile
     * @generated
     */
	public Adapter createLoadProfileAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.simulation.Resource <em>Resource</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.simulation.Resource
     * @generated
     */
	public Adapter createResourceAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
	public Adapter createEObjectAdapter() {
        return null;
    }

} //SimulationAdapterFactory
