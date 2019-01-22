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
package org.bonitasoft.studio.model.simulation.impl;

import org.bonitasoft.studio.model.simulation.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimulationFactoryImpl extends EFactoryImpl implements SimulationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SimulationFactory init() {
		try {
			SimulationFactory theSimulationFactory = (SimulationFactory)EPackage.Registry.INSTANCE.getEFactory(SimulationPackage.eNS_URI);
			if (theSimulationFactory != null) {
				return theSimulationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SimulationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SimulationPackage.SIMULATION_ELEMENT: return createSimulationElement();
			case SimulationPackage.SIMULATION_TRANSITION: return createSimulationTransition();
			case SimulationPackage.RESOURCE_USAGE: return createResourceUsage();
			case SimulationPackage.INJECTION_PERIOD: return createInjectionPeriod();
			case SimulationPackage.SIMULATION_BOOLEAN: return createSimulationBoolean();
			case SimulationPackage.SIMULATION_NUMBER_DATA: return createSimulationNumberData();
			case SimulationPackage.SIMULATION_LITERAL_DATA: return createSimulationLiteralData();
			case SimulationPackage.SIMULATION_LITERAL: return createSimulationLiteral();
			case SimulationPackage.SIMULATION_NUMBER_RANGE: return createSimulationNumberRange();
			case SimulationPackage.DATA_CHANGE: return createDataChange();
			case SimulationPackage.SIMULATION_CALENDAR: return createSimulationCalendar();
			case SimulationPackage.DAY_PERIOD: return createDayPeriod();
			case SimulationPackage.MODEL_VERSION: return createModelVersion();
			case SimulationPackage.LOAD_PROFILE: return createLoadProfile();
			case SimulationPackage.RESOURCE: return createResource();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SimulationPackage.TIME_UNIT:
				return createTimeUnitFromString(eDataType, initialValue);
			case SimulationPackage.REPARTITION_TYPE:
				return createRepartitionTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SimulationPackage.TIME_UNIT:
				return convertTimeUnitToString(eDataType, instanceValue);
			case SimulationPackage.REPARTITION_TYPE:
				return convertRepartitionTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationElement createSimulationElement() {
		SimulationElementImpl simulationElement = new SimulationElementImpl();
		return simulationElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationTransition createSimulationTransition() {
		SimulationTransitionImpl simulationTransition = new SimulationTransitionImpl();
		return simulationTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceUsage createResourceUsage() {
		ResourceUsageImpl resourceUsage = new ResourceUsageImpl();
		return resourceUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InjectionPeriod createInjectionPeriod() {
		InjectionPeriodImpl injectionPeriod = new InjectionPeriodImpl();
		return injectionPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationBoolean createSimulationBoolean() {
		SimulationBooleanImpl simulationBoolean = new SimulationBooleanImpl();
		return simulationBoolean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationNumberData createSimulationNumberData() {
		SimulationNumberDataImpl simulationNumberData = new SimulationNumberDataImpl();
		return simulationNumberData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationLiteralData createSimulationLiteralData() {
		SimulationLiteralDataImpl simulationLiteralData = new SimulationLiteralDataImpl();
		return simulationLiteralData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationLiteral createSimulationLiteral() {
		SimulationLiteralImpl simulationLiteral = new SimulationLiteralImpl();
		return simulationLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationNumberRange createSimulationNumberRange() {
		SimulationNumberRangeImpl simulationNumberRange = new SimulationNumberRangeImpl();
		return simulationNumberRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataChange createDataChange() {
		DataChangeImpl dataChange = new DataChangeImpl();
		return dataChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationCalendar createSimulationCalendar() {
		SimulationCalendarImpl simulationCalendar = new SimulationCalendarImpl();
		return simulationCalendar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DayPeriod createDayPeriod() {
		DayPeriodImpl dayPeriod = new DayPeriodImpl();
		return dayPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ModelVersion createModelVersion() {
		ModelVersionImpl modelVersion = new ModelVersionImpl();
		return modelVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LoadProfile createLoadProfile() {
		LoadProfileImpl loadProfile = new LoadProfileImpl();
		return loadProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Resource createResource() {
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeUnit createTimeUnitFromString(EDataType eDataType, String initialValue) {
		TimeUnit result = TimeUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTimeUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepartitionType createRepartitionTypeFromString(EDataType eDataType, String initialValue) {
		RepartitionType result = RepartitionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRepartitionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationPackage getSimulationPackage() {
		return (SimulationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SimulationPackage getPackage() {
		return SimulationPackage.eINSTANCE;
	}

} //SimulationFactoryImpl
