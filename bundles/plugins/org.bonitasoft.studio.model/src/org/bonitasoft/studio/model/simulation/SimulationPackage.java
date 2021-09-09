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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.simulation.SimulationFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='process.history'"
 * @generated
 */
public interface SimulationPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "simulation"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/process/simulation"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "simulation"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	SimulationPackage eINSTANCE = org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationElementImpl <em>Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationElementImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationElement()
     * @generated
     */
	int SIMULATION_ELEMENT = 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ELEMENT__NAME = 0;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ELEMENT__DESCRIPTION = 1;

	/**
     * The number of structural features of the '<em>Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ELEMENT_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.SimulationData <em>Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.SimulationData
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationData()
     * @generated
     */
	int SIMULATION_DATA = 1;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_DATA__NAME = SIMULATION_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_DATA__DESCRIPTION = SIMULATION_ELEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Expression Based</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_DATA__EXPRESSION_BASED = SIMULATION_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_DATA__EXPRESSION = SIMULATION_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_DATA_FEATURE_COUNT = SIMULATION_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationTransitionImpl <em>Transition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationTransitionImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationTransition()
     * @generated
     */
	int SIMULATION_TRANSITION = 2;

	/**
     * The feature id for the '<em><b>Probability</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_TRANSITION__PROBABILITY = 0;

	/**
     * The feature id for the '<em><b>Data Based</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_TRANSITION__DATA_BASED = 1;

	/**
     * The feature id for the '<em><b>Use Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_TRANSITION__USE_EXPRESSION = 2;

	/**
     * The feature id for the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_TRANSITION__EXPRESSION = 3;

	/**
     * The number of structural features of the '<em>Transition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_TRANSITION_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.ResourceUsageImpl <em>Resource Usage</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.ResourceUsageImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getResourceUsage()
     * @generated
     */
	int RESOURCE_USAGE = 3;

	/**
     * The feature id for the '<em><b>Duration</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_USAGE__DURATION = 0;

	/**
     * The feature id for the '<em><b>Resource ID</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_USAGE__RESOURCE_ID = 1;

	/**
     * The feature id for the '<em><b>Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_USAGE__QUANTITY = 2;

	/**
     * The feature id for the '<em><b>Use Activity Duration</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_USAGE__USE_ACTIVITY_DURATION = 3;

	/**
     * The number of structural features of the '<em>Resource Usage</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_USAGE_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.InjectionPeriodImpl <em>Injection Period</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.InjectionPeriodImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getInjectionPeriod()
     * @generated
     */
	int INJECTION_PERIOD = 4;

	/**
     * The feature id for the '<em><b>Begin</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INJECTION_PERIOD__BEGIN = 0;

	/**
     * The feature id for the '<em><b>End</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INJECTION_PERIOD__END = 1;

	/**
     * The feature id for the '<em><b>Nb Instances</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INJECTION_PERIOD__NB_INSTANCES = 2;

	/**
     * The feature id for the '<em><b>Repartition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INJECTION_PERIOD__REPARTITION = 3;

	/**
     * The number of structural features of the '<em>Injection Period</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INJECTION_PERIOD_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationBooleanImpl <em>Boolean</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationBooleanImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationBoolean()
     * @generated
     */
	int SIMULATION_BOOLEAN = 5;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_BOOLEAN__NAME = SIMULATION_DATA__NAME;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_BOOLEAN__DESCRIPTION = SIMULATION_DATA__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Expression Based</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_BOOLEAN__EXPRESSION_BASED = SIMULATION_DATA__EXPRESSION_BASED;

	/**
     * The feature id for the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_BOOLEAN__EXPRESSION = SIMULATION_DATA__EXPRESSION;

	/**
     * The feature id for the '<em><b>Probability Of True</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_BOOLEAN__PROBABILITY_OF_TRUE = SIMULATION_DATA_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Boolean</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_BOOLEAN_FEATURE_COUNT = SIMULATION_DATA_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberDataImpl <em>Number Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationNumberDataImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationNumberData()
     * @generated
     */
	int SIMULATION_NUMBER_DATA = 6;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_DATA__NAME = SIMULATION_DATA__NAME;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_DATA__DESCRIPTION = SIMULATION_DATA__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Expression Based</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_DATA__EXPRESSION_BASED = SIMULATION_DATA__EXPRESSION_BASED;

	/**
     * The feature id for the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_DATA__EXPRESSION = SIMULATION_DATA__EXPRESSION;

	/**
     * The feature id for the '<em><b>Ranges</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_DATA__RANGES = SIMULATION_DATA_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Number Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_DATA_FEATURE_COUNT = SIMULATION_DATA_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationLiteralDataImpl <em>Literal Data</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationLiteralDataImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationLiteralData()
     * @generated
     */
	int SIMULATION_LITERAL_DATA = 7;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_LITERAL_DATA__NAME = SIMULATION_DATA__NAME;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_LITERAL_DATA__DESCRIPTION = SIMULATION_DATA__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Expression Based</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_LITERAL_DATA__EXPRESSION_BASED = SIMULATION_DATA__EXPRESSION_BASED;

	/**
     * The feature id for the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_LITERAL_DATA__EXPRESSION = SIMULATION_DATA__EXPRESSION;

	/**
     * The feature id for the '<em><b>Literals</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_LITERAL_DATA__LITERALS = SIMULATION_DATA_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Literal Data</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_LITERAL_DATA_FEATURE_COUNT = SIMULATION_DATA_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationLiteralImpl <em>Literal</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationLiteralImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationLiteral()
     * @generated
     */
	int SIMULATION_LITERAL = 8;

	/**
     * The feature id for the '<em><b>Probability</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_LITERAL__PROBABILITY = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_LITERAL__VALUE = 1;

	/**
     * The number of structural features of the '<em>Literal</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_LITERAL_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberRangeImpl <em>Number Range</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationNumberRangeImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationNumberRange()
     * @generated
     */
	int SIMULATION_NUMBER_RANGE = 9;

	/**
     * The feature id for the '<em><b>Min</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_RANGE__MIN = 0;

	/**
     * The feature id for the '<em><b>Max</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_RANGE__MAX = 1;

	/**
     * The feature id for the '<em><b>Probability</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_RANGE__PROBABILITY = 2;

	/**
     * The feature id for the '<em><b>Repartition Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_RANGE__REPARTITION_TYPE = 3;

	/**
     * The number of structural features of the '<em>Number Range</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_NUMBER_RANGE_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.SimulationDataContainer <em>Data Container</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.SimulationDataContainer
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationDataContainer()
     * @generated
     */
	int SIMULATION_DATA_CONTAINER = 10;

	/**
     * The feature id for the '<em><b>Simulation Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_DATA_CONTAINER__SIMULATION_DATA = 0;

	/**
     * The number of structural features of the '<em>Data Container</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_DATA_CONTAINER_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess <em>Abstract Process</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.SimulationAbstractProcess
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationAbstractProcess()
     * @generated
     */
	int SIMULATION_ABSTRACT_PROCESS = 11;

	/**
     * The feature id for the '<em><b>Simulation Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ABSTRACT_PROCESS__SIMULATION_DATA = SIMULATION_DATA_CONTAINER__SIMULATION_DATA;

	/**
     * The feature id for the '<em><b>Load Profile ID</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ABSTRACT_PROCESS__LOAD_PROFILE_ID = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Maximum Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ABSTRACT_PROCESS__MAXIMUM_TIME = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Abstract Process</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ABSTRACT_PROCESS_FEATURE_COUNT = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.SimulationActivity <em>Activity</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationActivity()
     * @generated
     */
	int SIMULATION_ACTIVITY = 12;

	/**
     * The feature id for the '<em><b>Simulation Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY__SIMULATION_DATA = SIMULATION_DATA_CONTAINER__SIMULATION_DATA;

	/**
     * The feature id for the '<em><b>Resources Usages</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY__RESOURCES_USAGES = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Execution Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY__EXECUTION_TIME = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Estimated Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY__ESTIMATED_TIME = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Maximum Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY__MAXIMUM_TIME = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Contigous</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY__CONTIGOUS = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Exclusive Outgoing Transition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY__EXCLUSIVE_OUTGOING_TRANSITION = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Loop Transition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY__LOOP_TRANSITION = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Data Change</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY__DATA_CHANGE = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 7;

	/**
     * The number of structural features of the '<em>Activity</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_ACTIVITY_FEATURE_COUNT = SIMULATION_DATA_CONTAINER_FEATURE_COUNT + 8;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.DataChangeImpl <em>Data Change</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.DataChangeImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getDataChange()
     * @generated
     */
	int DATA_CHANGE = 13;

	/**
     * The feature id for the '<em><b>Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_CHANGE__DATA = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_CHANGE__VALUE = 1;

	/**
     * The number of structural features of the '<em>Data Change</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATA_CHANGE_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationCalendarImpl <em>Calendar</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationCalendarImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationCalendar()
     * @generated
     */
	int SIMULATION_CALENDAR = 14;

	/**
     * The feature id for the '<em><b>Days Of Week</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_CALENDAR__DAYS_OF_WEEK = 0;

	/**
     * The number of structural features of the '<em>Calendar</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SIMULATION_CALENDAR_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.DayPeriodImpl <em>Day Period</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.DayPeriodImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getDayPeriod()
     * @generated
     */
	int DAY_PERIOD = 15;

	/**
     * The feature id for the '<em><b>Day</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DAY_PERIOD__DAY = 0;

	/**
     * The feature id for the '<em><b>Start Hour</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DAY_PERIOD__START_HOUR = 1;

	/**
     * The feature id for the '<em><b>End Hour</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DAY_PERIOD__END_HOUR = 2;

	/**
     * The feature id for the '<em><b>Start Minute</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DAY_PERIOD__START_MINUTE = 3;

	/**
     * The feature id for the '<em><b>End Minute</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DAY_PERIOD__END_MINUTE = 4;

	/**
     * The number of structural features of the '<em>Day Period</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DAY_PERIOD_FEATURE_COUNT = 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.ModelVersionImpl <em>Model Version</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.ModelVersionImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getModelVersion()
     * @generated
     */
	int MODEL_VERSION = 16;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MODEL_VERSION__VERSION = 0;

	/**
     * The number of structural features of the '<em>Model Version</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MODEL_VERSION_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.LoadProfileImpl <em>Load Profile</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.LoadProfileImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getLoadProfile()
     * @generated
     */
	int LOAD_PROFILE = 17;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOAD_PROFILE__NAME = SIMULATION_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOAD_PROFILE__DESCRIPTION = SIMULATION_ELEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOAD_PROFILE__VERSION = SIMULATION_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Calendar</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOAD_PROFILE__CALENDAR = SIMULATION_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Injection Periods</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOAD_PROFILE__INJECTION_PERIODS = SIMULATION_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Load Profile</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOAD_PROFILE_FEATURE_COUNT = SIMULATION_ELEMENT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl <em>Resource</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.impl.ResourceImpl
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getResource()
     * @generated
     */
	int RESOURCE = 18;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__NAME = SIMULATION_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__DESCRIPTION = SIMULATION_ELEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__VERSION = SIMULATION_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__TYPE = SIMULATION_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__QUANTITY = SIMULATION_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Maximum Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__MAXIMUM_QUANTITY = SIMULATION_ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Cost Unit</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__COST_UNIT = SIMULATION_ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Time Unit</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__TIME_UNIT = SIMULATION_ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Fixed Cost</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__FIXED_COST = SIMULATION_ELEMENT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Time Cost</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__TIME_COST = SIMULATION_ELEMENT_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Calendar</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__CALENDAR = SIMULATION_ELEMENT_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Unlimited</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE__UNLIMITED = SIMULATION_ELEMENT_FEATURE_COUNT + 9;

	/**
     * The number of structural features of the '<em>Resource</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_FEATURE_COUNT = SIMULATION_ELEMENT_FEATURE_COUNT + 10;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.TimeUnit <em>Time Unit</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.TimeUnit
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getTimeUnit()
     * @generated
     */
	int TIME_UNIT = 19;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.simulation.RepartitionType <em>Repartition Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.simulation.RepartitionType
     * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getRepartitionType()
     * @generated
     */
	int REPARTITION_TYPE = 20;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationElement <em>Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Element</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationElement
     * @generated
     */
	EClass getSimulationElement();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationElement#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationElement#getName()
     * @see #getSimulationElement()
     * @generated
     */
	EAttribute getSimulationElement_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationElement#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationElement#getDescription()
     * @see #getSimulationElement()
     * @generated
     */
	EAttribute getSimulationElement_Description();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationData <em>Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationData
     * @generated
     */
	EClass getSimulationData();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationData#isExpressionBased <em>Expression Based</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Expression Based</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationData#isExpressionBased()
     * @see #getSimulationData()
     * @generated
     */
	EAttribute getSimulationData_ExpressionBased();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.simulation.SimulationData#getExpression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Expression</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationData#getExpression()
     * @see #getSimulationData()
     * @generated
     */
	EReference getSimulationData_Expression();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationTransition <em>Transition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Transition</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationTransition
     * @generated
     */
	EClass getSimulationTransition();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationTransition#getProbability <em>Probability</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Probability</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationTransition#getProbability()
     * @see #getSimulationTransition()
     * @generated
     */
	EAttribute getSimulationTransition_Probability();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationTransition#isDataBased <em>Data Based</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Data Based</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationTransition#isDataBased()
     * @see #getSimulationTransition()
     * @generated
     */
	EAttribute getSimulationTransition_DataBased();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationTransition#isUseExpression <em>Use Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Expression</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationTransition#isUseExpression()
     * @see #getSimulationTransition()
     * @generated
     */
	EAttribute getSimulationTransition_UseExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.simulation.SimulationTransition#getExpression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Expression</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationTransition#getExpression()
     * @see #getSimulationTransition()
     * @generated
     */
	EReference getSimulationTransition_Expression();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.ResourceUsage <em>Resource Usage</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Resource Usage</em>'.
     * @see org.bonitasoft.studio.model.simulation.ResourceUsage
     * @generated
     */
	EClass getResourceUsage();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.ResourceUsage#getDuration <em>Duration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Duration</em>'.
     * @see org.bonitasoft.studio.model.simulation.ResourceUsage#getDuration()
     * @see #getResourceUsage()
     * @generated
     */
	EAttribute getResourceUsage_Duration();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.ResourceUsage#getResourceID <em>Resource ID</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Resource ID</em>'.
     * @see org.bonitasoft.studio.model.simulation.ResourceUsage#getResourceID()
     * @see #getResourceUsage()
     * @generated
     */
	EAttribute getResourceUsage_ResourceID();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.ResourceUsage#getQuantity <em>Quantity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Quantity</em>'.
     * @see org.bonitasoft.studio.model.simulation.ResourceUsage#getQuantity()
     * @see #getResourceUsage()
     * @generated
     */
	EAttribute getResourceUsage_Quantity();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.ResourceUsage#isUseActivityDuration <em>Use Activity Duration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Activity Duration</em>'.
     * @see org.bonitasoft.studio.model.simulation.ResourceUsage#isUseActivityDuration()
     * @see #getResourceUsage()
     * @generated
     */
	EAttribute getResourceUsage_UseActivityDuration();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod <em>Injection Period</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Injection Period</em>'.
     * @see org.bonitasoft.studio.model.simulation.InjectionPeriod
     * @generated
     */
	EClass getInjectionPeriod();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getBegin <em>Begin</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Begin</em>'.
     * @see org.bonitasoft.studio.model.simulation.InjectionPeriod#getBegin()
     * @see #getInjectionPeriod()
     * @generated
     */
	EAttribute getInjectionPeriod_Begin();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getEnd <em>End</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>End</em>'.
     * @see org.bonitasoft.studio.model.simulation.InjectionPeriod#getEnd()
     * @see #getInjectionPeriod()
     * @generated
     */
	EAttribute getInjectionPeriod_End();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getNbInstances <em>Nb Instances</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Nb Instances</em>'.
     * @see org.bonitasoft.studio.model.simulation.InjectionPeriod#getNbInstances()
     * @see #getInjectionPeriod()
     * @generated
     */
	EAttribute getInjectionPeriod_NbInstances();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.InjectionPeriod#getRepartition <em>Repartition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Repartition</em>'.
     * @see org.bonitasoft.studio.model.simulation.InjectionPeriod#getRepartition()
     * @see #getInjectionPeriod()
     * @generated
     */
	EAttribute getInjectionPeriod_Repartition();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationBoolean <em>Boolean</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boolean</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationBoolean
     * @generated
     */
	EClass getSimulationBoolean();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationBoolean#getProbabilityOfTrue <em>Probability Of True</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Probability Of True</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationBoolean#getProbabilityOfTrue()
     * @see #getSimulationBoolean()
     * @generated
     */
	EAttribute getSimulationBoolean_ProbabilityOfTrue();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationNumberData <em>Number Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Number Data</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationNumberData
     * @generated
     */
	EClass getSimulationNumberData();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.simulation.SimulationNumberData#getRanges <em>Ranges</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Ranges</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationNumberData#getRanges()
     * @see #getSimulationNumberData()
     * @generated
     */
	EReference getSimulationNumberData_Ranges();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationLiteralData <em>Literal Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Literal Data</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationLiteralData
     * @generated
     */
	EClass getSimulationLiteralData();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.simulation.SimulationLiteralData#getLiterals <em>Literals</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Literals</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationLiteralData#getLiterals()
     * @see #getSimulationLiteralData()
     * @generated
     */
	EReference getSimulationLiteralData_Literals();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationLiteral <em>Literal</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Literal</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationLiteral
     * @generated
     */
	EClass getSimulationLiteral();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationLiteral#getProbability <em>Probability</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Probability</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationLiteral#getProbability()
     * @see #getSimulationLiteral()
     * @generated
     */
	EAttribute getSimulationLiteral_Probability();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationLiteral#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationLiteral#getValue()
     * @see #getSimulationLiteral()
     * @generated
     */
	EAttribute getSimulationLiteral_Value();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationNumberRange <em>Number Range</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Number Range</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationNumberRange
     * @generated
     */
	EClass getSimulationNumberRange();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationNumberRange#getMin <em>Min</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationNumberRange#getMin()
     * @see #getSimulationNumberRange()
     * @generated
     */
	EAttribute getSimulationNumberRange_Min();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationNumberRange#getMax <em>Max</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationNumberRange#getMax()
     * @see #getSimulationNumberRange()
     * @generated
     */
	EAttribute getSimulationNumberRange_Max();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationNumberRange#getProbability <em>Probability</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Probability</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationNumberRange#getProbability()
     * @see #getSimulationNumberRange()
     * @generated
     */
	EAttribute getSimulationNumberRange_Probability();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationNumberRange#getRepartitionType <em>Repartition Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Repartition Type</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationNumberRange#getRepartitionType()
     * @see #getSimulationNumberRange()
     * @generated
     */
	EAttribute getSimulationNumberRange_RepartitionType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationDataContainer <em>Data Container</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Container</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationDataContainer
     * @generated
     */
	EClass getSimulationDataContainer();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.simulation.SimulationDataContainer#getSimulationData <em>Simulation Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Simulation Data</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationDataContainer#getSimulationData()
     * @see #getSimulationDataContainer()
     * @generated
     */
	EReference getSimulationDataContainer_SimulationData();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess <em>Abstract Process</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Process</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationAbstractProcess
     * @generated
     */
	EClass getSimulationAbstractProcess();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess#getLoadProfileID <em>Load Profile ID</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Load Profile ID</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationAbstractProcess#getLoadProfileID()
     * @see #getSimulationAbstractProcess()
     * @generated
     */
	EAttribute getSimulationAbstractProcess_LoadProfileID();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess#getMaximumTime <em>Maximum Time</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Maximum Time</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationAbstractProcess#getMaximumTime()
     * @see #getSimulationAbstractProcess()
     * @generated
     */
	EAttribute getSimulationAbstractProcess_MaximumTime();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationActivity <em>Activity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Activity</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity
     * @generated
     */
	EClass getSimulationActivity();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getResourcesUsages <em>Resources Usages</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Resources Usages</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity#getResourcesUsages()
     * @see #getSimulationActivity()
     * @generated
     */
	EReference getSimulationActivity_ResourcesUsages();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getExecutionTime <em>Execution Time</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Execution Time</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity#getExecutionTime()
     * @see #getSimulationActivity()
     * @generated
     */
	EAttribute getSimulationActivity_ExecutionTime();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getEstimatedTime <em>Estimated Time</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Estimated Time</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity#getEstimatedTime()
     * @see #getSimulationActivity()
     * @generated
     */
	EAttribute getSimulationActivity_EstimatedTime();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getMaximumTime <em>Maximum Time</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Maximum Time</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity#getMaximumTime()
     * @see #getSimulationActivity()
     * @generated
     */
	EAttribute getSimulationActivity_MaximumTime();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#isContigous <em>Contigous</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Contigous</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity#isContigous()
     * @see #getSimulationActivity()
     * @generated
     */
	EAttribute getSimulationActivity_Contigous();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#isExclusiveOutgoingTransition <em>Exclusive Outgoing Transition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exclusive Outgoing Transition</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity#isExclusiveOutgoingTransition()
     * @see #getSimulationActivity()
     * @generated
     */
	EAttribute getSimulationActivity_ExclusiveOutgoingTransition();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getLoopTransition <em>Loop Transition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Loop Transition</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity#getLoopTransition()
     * @see #getSimulationActivity()
     * @generated
     */
	EReference getSimulationActivity_LoopTransition();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.simulation.SimulationActivity#getDataChange <em>Data Change</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data Change</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationActivity#getDataChange()
     * @see #getSimulationActivity()
     * @generated
     */
	EReference getSimulationActivity_DataChange();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.DataChange <em>Data Change</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Change</em>'.
     * @see org.bonitasoft.studio.model.simulation.DataChange
     * @generated
     */
	EClass getDataChange();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.simulation.DataChange#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Data</em>'.
     * @see org.bonitasoft.studio.model.simulation.DataChange#getData()
     * @see #getDataChange()
     * @generated
     */
	EReference getDataChange_Data();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.simulation.DataChange#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see org.bonitasoft.studio.model.simulation.DataChange#getValue()
     * @see #getDataChange()
     * @generated
     */
	EReference getDataChange_Value();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.SimulationCalendar <em>Calendar</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Calendar</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationCalendar
     * @generated
     */
	EClass getSimulationCalendar();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.simulation.SimulationCalendar#getDaysOfWeek <em>Days Of Week</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Days Of Week</em>'.
     * @see org.bonitasoft.studio.model.simulation.SimulationCalendar#getDaysOfWeek()
     * @see #getSimulationCalendar()
     * @generated
     */
	EReference getSimulationCalendar_DaysOfWeek();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.DayPeriod <em>Day Period</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Day Period</em>'.
     * @see org.bonitasoft.studio.model.simulation.DayPeriod
     * @generated
     */
	EClass getDayPeriod();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.simulation.DayPeriod#getDay <em>Day</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Day</em>'.
     * @see org.bonitasoft.studio.model.simulation.DayPeriod#getDay()
     * @see #getDayPeriod()
     * @generated
     */
	EAttribute getDayPeriod_Day();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.DayPeriod#getStartHour <em>Start Hour</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Start Hour</em>'.
     * @see org.bonitasoft.studio.model.simulation.DayPeriod#getStartHour()
     * @see #getDayPeriod()
     * @generated
     */
	EAttribute getDayPeriod_StartHour();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.DayPeriod#getEndHour <em>End Hour</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>End Hour</em>'.
     * @see org.bonitasoft.studio.model.simulation.DayPeriod#getEndHour()
     * @see #getDayPeriod()
     * @generated
     */
	EAttribute getDayPeriod_EndHour();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.DayPeriod#getStartMinute <em>Start Minute</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Start Minute</em>'.
     * @see org.bonitasoft.studio.model.simulation.DayPeriod#getStartMinute()
     * @see #getDayPeriod()
     * @generated
     */
	EAttribute getDayPeriod_StartMinute();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.DayPeriod#getEndMinute <em>End Minute</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>End Minute</em>'.
     * @see org.bonitasoft.studio.model.simulation.DayPeriod#getEndMinute()
     * @see #getDayPeriod()
     * @generated
     */
	EAttribute getDayPeriod_EndMinute();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.ModelVersion <em>Model Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Model Version</em>'.
     * @see org.bonitasoft.studio.model.simulation.ModelVersion
     * @generated
     */
	EClass getModelVersion();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.ModelVersion#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.bonitasoft.studio.model.simulation.ModelVersion#getVersion()
     * @see #getModelVersion()
     * @generated
     */
	EAttribute getModelVersion_Version();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.LoadProfile <em>Load Profile</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Load Profile</em>'.
     * @see org.bonitasoft.studio.model.simulation.LoadProfile
     * @generated
     */
	EClass getLoadProfile();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.simulation.LoadProfile#getCalendar <em>Calendar</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Calendar</em>'.
     * @see org.bonitasoft.studio.model.simulation.LoadProfile#getCalendar()
     * @see #getLoadProfile()
     * @generated
     */
	EReference getLoadProfile_Calendar();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.simulation.LoadProfile#getInjectionPeriods <em>Injection Periods</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Injection Periods</em>'.
     * @see org.bonitasoft.studio.model.simulation.LoadProfile#getInjectionPeriods()
     * @see #getLoadProfile()
     * @generated
     */
	EReference getLoadProfile_InjectionPeriods();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.simulation.Resource <em>Resource</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Resource</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource
     * @generated
     */
	EClass getResource();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.Resource#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource#getType()
     * @see #getResource()
     * @generated
     */
	EAttribute getResource_Type();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.Resource#getQuantity <em>Quantity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Quantity</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource#getQuantity()
     * @see #getResource()
     * @generated
     */
	EAttribute getResource_Quantity();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.Resource#getMaximumQuantity <em>Maximum Quantity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Maximum Quantity</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource#getMaximumQuantity()
     * @see #getResource()
     * @generated
     */
	EAttribute getResource_MaximumQuantity();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.Resource#getCostUnit <em>Cost Unit</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Cost Unit</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource#getCostUnit()
     * @see #getResource()
     * @generated
     */
	EAttribute getResource_CostUnit();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.Resource#getTimeUnit <em>Time Unit</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Time Unit</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource#getTimeUnit()
     * @see #getResource()
     * @generated
     */
	EAttribute getResource_TimeUnit();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.Resource#getFixedCost <em>Fixed Cost</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fixed Cost</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource#getFixedCost()
     * @see #getResource()
     * @generated
     */
	EAttribute getResource_FixedCost();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.Resource#getTimeCost <em>Time Cost</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Time Cost</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource#getTimeCost()
     * @see #getResource()
     * @generated
     */
	EAttribute getResource_TimeCost();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.simulation.Resource#getCalendar <em>Calendar</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Calendar</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource#getCalendar()
     * @see #getResource()
     * @generated
     */
	EReference getResource_Calendar();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.simulation.Resource#isUnlimited <em>Unlimited</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Unlimited</em>'.
     * @see org.bonitasoft.studio.model.simulation.Resource#isUnlimited()
     * @see #getResource()
     * @generated
     */
	EAttribute getResource_Unlimited();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.simulation.TimeUnit <em>Time Unit</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Time Unit</em>'.
     * @see org.bonitasoft.studio.model.simulation.TimeUnit
     * @generated
     */
	EEnum getTimeUnit();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.simulation.RepartitionType <em>Repartition Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Repartition Type</em>'.
     * @see org.bonitasoft.studio.model.simulation.RepartitionType
     * @generated
     */
	EEnum getRepartitionType();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	SimulationFactory getSimulationFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationElementImpl <em>Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationElementImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationElement()
         * @generated
         */
		EClass SIMULATION_ELEMENT = eINSTANCE.getSimulationElement();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_ELEMENT__NAME = eINSTANCE.getSimulationElement_Name();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_ELEMENT__DESCRIPTION = eINSTANCE.getSimulationElement_Description();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.SimulationData <em>Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.SimulationData
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationData()
         * @generated
         */
		EClass SIMULATION_DATA = eINSTANCE.getSimulationData();

		/**
         * The meta object literal for the '<em><b>Expression Based</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_DATA__EXPRESSION_BASED = eINSTANCE.getSimulationData_ExpressionBased();

		/**
         * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SIMULATION_DATA__EXPRESSION = eINSTANCE.getSimulationData_Expression();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationTransitionImpl <em>Transition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationTransitionImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationTransition()
         * @generated
         */
		EClass SIMULATION_TRANSITION = eINSTANCE.getSimulationTransition();

		/**
         * The meta object literal for the '<em><b>Probability</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_TRANSITION__PROBABILITY = eINSTANCE.getSimulationTransition_Probability();

		/**
         * The meta object literal for the '<em><b>Data Based</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_TRANSITION__DATA_BASED = eINSTANCE.getSimulationTransition_DataBased();

		/**
         * The meta object literal for the '<em><b>Use Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_TRANSITION__USE_EXPRESSION = eINSTANCE.getSimulationTransition_UseExpression();

		/**
         * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SIMULATION_TRANSITION__EXPRESSION = eINSTANCE.getSimulationTransition_Expression();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.ResourceUsageImpl <em>Resource Usage</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.ResourceUsageImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getResourceUsage()
         * @generated
         */
		EClass RESOURCE_USAGE = eINSTANCE.getResourceUsage();

		/**
         * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE_USAGE__DURATION = eINSTANCE.getResourceUsage_Duration();

		/**
         * The meta object literal for the '<em><b>Resource ID</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE_USAGE__RESOURCE_ID = eINSTANCE.getResourceUsage_ResourceID();

		/**
         * The meta object literal for the '<em><b>Quantity</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE_USAGE__QUANTITY = eINSTANCE.getResourceUsage_Quantity();

		/**
         * The meta object literal for the '<em><b>Use Activity Duration</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE_USAGE__USE_ACTIVITY_DURATION = eINSTANCE.getResourceUsage_UseActivityDuration();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.InjectionPeriodImpl <em>Injection Period</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.InjectionPeriodImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getInjectionPeriod()
         * @generated
         */
		EClass INJECTION_PERIOD = eINSTANCE.getInjectionPeriod();

		/**
         * The meta object literal for the '<em><b>Begin</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute INJECTION_PERIOD__BEGIN = eINSTANCE.getInjectionPeriod_Begin();

		/**
         * The meta object literal for the '<em><b>End</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute INJECTION_PERIOD__END = eINSTANCE.getInjectionPeriod_End();

		/**
         * The meta object literal for the '<em><b>Nb Instances</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute INJECTION_PERIOD__NB_INSTANCES = eINSTANCE.getInjectionPeriod_NbInstances();

		/**
         * The meta object literal for the '<em><b>Repartition</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute INJECTION_PERIOD__REPARTITION = eINSTANCE.getInjectionPeriod_Repartition();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationBooleanImpl <em>Boolean</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationBooleanImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationBoolean()
         * @generated
         */
		EClass SIMULATION_BOOLEAN = eINSTANCE.getSimulationBoolean();

		/**
         * The meta object literal for the '<em><b>Probability Of True</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_BOOLEAN__PROBABILITY_OF_TRUE = eINSTANCE.getSimulationBoolean_ProbabilityOfTrue();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberDataImpl <em>Number Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationNumberDataImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationNumberData()
         * @generated
         */
		EClass SIMULATION_NUMBER_DATA = eINSTANCE.getSimulationNumberData();

		/**
         * The meta object literal for the '<em><b>Ranges</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SIMULATION_NUMBER_DATA__RANGES = eINSTANCE.getSimulationNumberData_Ranges();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationLiteralDataImpl <em>Literal Data</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationLiteralDataImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationLiteralData()
         * @generated
         */
		EClass SIMULATION_LITERAL_DATA = eINSTANCE.getSimulationLiteralData();

		/**
         * The meta object literal for the '<em><b>Literals</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SIMULATION_LITERAL_DATA__LITERALS = eINSTANCE.getSimulationLiteralData_Literals();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationLiteralImpl <em>Literal</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationLiteralImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationLiteral()
         * @generated
         */
		EClass SIMULATION_LITERAL = eINSTANCE.getSimulationLiteral();

		/**
         * The meta object literal for the '<em><b>Probability</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_LITERAL__PROBABILITY = eINSTANCE.getSimulationLiteral_Probability();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_LITERAL__VALUE = eINSTANCE.getSimulationLiteral_Value();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberRangeImpl <em>Number Range</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationNumberRangeImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationNumberRange()
         * @generated
         */
		EClass SIMULATION_NUMBER_RANGE = eINSTANCE.getSimulationNumberRange();

		/**
         * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_NUMBER_RANGE__MIN = eINSTANCE.getSimulationNumberRange_Min();

		/**
         * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_NUMBER_RANGE__MAX = eINSTANCE.getSimulationNumberRange_Max();

		/**
         * The meta object literal for the '<em><b>Probability</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_NUMBER_RANGE__PROBABILITY = eINSTANCE.getSimulationNumberRange_Probability();

		/**
         * The meta object literal for the '<em><b>Repartition Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_NUMBER_RANGE__REPARTITION_TYPE = eINSTANCE.getSimulationNumberRange_RepartitionType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.SimulationDataContainer <em>Data Container</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.SimulationDataContainer
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationDataContainer()
         * @generated
         */
		EClass SIMULATION_DATA_CONTAINER = eINSTANCE.getSimulationDataContainer();

		/**
         * The meta object literal for the '<em><b>Simulation Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SIMULATION_DATA_CONTAINER__SIMULATION_DATA = eINSTANCE.getSimulationDataContainer_SimulationData();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.SimulationAbstractProcess <em>Abstract Process</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.SimulationAbstractProcess
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationAbstractProcess()
         * @generated
         */
		EClass SIMULATION_ABSTRACT_PROCESS = eINSTANCE.getSimulationAbstractProcess();

		/**
         * The meta object literal for the '<em><b>Load Profile ID</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_ABSTRACT_PROCESS__LOAD_PROFILE_ID = eINSTANCE.getSimulationAbstractProcess_LoadProfileID();

		/**
         * The meta object literal for the '<em><b>Maximum Time</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_ABSTRACT_PROCESS__MAXIMUM_TIME = eINSTANCE.getSimulationAbstractProcess_MaximumTime();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.SimulationActivity <em>Activity</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.SimulationActivity
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationActivity()
         * @generated
         */
		EClass SIMULATION_ACTIVITY = eINSTANCE.getSimulationActivity();

		/**
         * The meta object literal for the '<em><b>Resources Usages</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SIMULATION_ACTIVITY__RESOURCES_USAGES = eINSTANCE.getSimulationActivity_ResourcesUsages();

		/**
         * The meta object literal for the '<em><b>Execution Time</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_ACTIVITY__EXECUTION_TIME = eINSTANCE.getSimulationActivity_ExecutionTime();

		/**
         * The meta object literal for the '<em><b>Estimated Time</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_ACTIVITY__ESTIMATED_TIME = eINSTANCE.getSimulationActivity_EstimatedTime();

		/**
         * The meta object literal for the '<em><b>Maximum Time</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_ACTIVITY__MAXIMUM_TIME = eINSTANCE.getSimulationActivity_MaximumTime();

		/**
         * The meta object literal for the '<em><b>Contigous</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_ACTIVITY__CONTIGOUS = eINSTANCE.getSimulationActivity_Contigous();

		/**
         * The meta object literal for the '<em><b>Exclusive Outgoing Transition</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SIMULATION_ACTIVITY__EXCLUSIVE_OUTGOING_TRANSITION = eINSTANCE.getSimulationActivity_ExclusiveOutgoingTransition();

		/**
         * The meta object literal for the '<em><b>Loop Transition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SIMULATION_ACTIVITY__LOOP_TRANSITION = eINSTANCE.getSimulationActivity_LoopTransition();

		/**
         * The meta object literal for the '<em><b>Data Change</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SIMULATION_ACTIVITY__DATA_CHANGE = eINSTANCE.getSimulationActivity_DataChange();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.DataChangeImpl <em>Data Change</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.DataChangeImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getDataChange()
         * @generated
         */
		EClass DATA_CHANGE = eINSTANCE.getDataChange();

		/**
         * The meta object literal for the '<em><b>Data</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATA_CHANGE__DATA = eINSTANCE.getDataChange_Data();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATA_CHANGE__VALUE = eINSTANCE.getDataChange_Value();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.SimulationCalendarImpl <em>Calendar</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationCalendarImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getSimulationCalendar()
         * @generated
         */
		EClass SIMULATION_CALENDAR = eINSTANCE.getSimulationCalendar();

		/**
         * The meta object literal for the '<em><b>Days Of Week</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SIMULATION_CALENDAR__DAYS_OF_WEEK = eINSTANCE.getSimulationCalendar_DaysOfWeek();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.DayPeriodImpl <em>Day Period</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.DayPeriodImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getDayPeriod()
         * @generated
         */
		EClass DAY_PERIOD = eINSTANCE.getDayPeriod();

		/**
         * The meta object literal for the '<em><b>Day</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DAY_PERIOD__DAY = eINSTANCE.getDayPeriod_Day();

		/**
         * The meta object literal for the '<em><b>Start Hour</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DAY_PERIOD__START_HOUR = eINSTANCE.getDayPeriod_StartHour();

		/**
         * The meta object literal for the '<em><b>End Hour</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DAY_PERIOD__END_HOUR = eINSTANCE.getDayPeriod_EndHour();

		/**
         * The meta object literal for the '<em><b>Start Minute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DAY_PERIOD__START_MINUTE = eINSTANCE.getDayPeriod_StartMinute();

		/**
         * The meta object literal for the '<em><b>End Minute</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DAY_PERIOD__END_MINUTE = eINSTANCE.getDayPeriod_EndMinute();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.ModelVersionImpl <em>Model Version</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.ModelVersionImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getModelVersion()
         * @generated
         */
		EClass MODEL_VERSION = eINSTANCE.getModelVersion();

		/**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MODEL_VERSION__VERSION = eINSTANCE.getModelVersion_Version();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.LoadProfileImpl <em>Load Profile</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.LoadProfileImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getLoadProfile()
         * @generated
         */
		EClass LOAD_PROFILE = eINSTANCE.getLoadProfile();

		/**
         * The meta object literal for the '<em><b>Calendar</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference LOAD_PROFILE__CALENDAR = eINSTANCE.getLoadProfile_Calendar();

		/**
         * The meta object literal for the '<em><b>Injection Periods</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference LOAD_PROFILE__INJECTION_PERIODS = eINSTANCE.getLoadProfile_InjectionPeriods();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl <em>Resource</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.impl.ResourceImpl
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getResource()
         * @generated
         */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE__TYPE = eINSTANCE.getResource_Type();

		/**
         * The meta object literal for the '<em><b>Quantity</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE__QUANTITY = eINSTANCE.getResource_Quantity();

		/**
         * The meta object literal for the '<em><b>Maximum Quantity</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE__MAXIMUM_QUANTITY = eINSTANCE.getResource_MaximumQuantity();

		/**
         * The meta object literal for the '<em><b>Cost Unit</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE__COST_UNIT = eINSTANCE.getResource_CostUnit();

		/**
         * The meta object literal for the '<em><b>Time Unit</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE__TIME_UNIT = eINSTANCE.getResource_TimeUnit();

		/**
         * The meta object literal for the '<em><b>Fixed Cost</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE__FIXED_COST = eINSTANCE.getResource_FixedCost();

		/**
         * The meta object literal for the '<em><b>Time Cost</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE__TIME_COST = eINSTANCE.getResource_TimeCost();

		/**
         * The meta object literal for the '<em><b>Calendar</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference RESOURCE__CALENDAR = eINSTANCE.getResource_Calendar();

		/**
         * The meta object literal for the '<em><b>Unlimited</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute RESOURCE__UNLIMITED = eINSTANCE.getResource_Unlimited();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.TimeUnit <em>Time Unit</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.TimeUnit
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getTimeUnit()
         * @generated
         */
		EEnum TIME_UNIT = eINSTANCE.getTimeUnit();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.simulation.RepartitionType <em>Repartition Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.simulation.RepartitionType
         * @see org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl#getRepartitionType()
         * @generated
         */
		EEnum REPARTITION_TYPE = eINSTANCE.getRepartitionType();

	}

} //SimulationPackage
