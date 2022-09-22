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

import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;

import org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl;

import org.bonitasoft.studio.model.configuration.ConfigurationPackage;

import org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;

import org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl;

import org.bonitasoft.studio.model.expression.ExpressionPackage;

import org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl;

import org.bonitasoft.studio.model.form.FormPackage;

import org.bonitasoft.studio.model.form.impl.FormPackageImpl;

import org.bonitasoft.studio.model.kpi.KpiPackage;

import org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl;

import org.bonitasoft.studio.model.parameter.ParameterPackage;

import org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl;

import org.bonitasoft.studio.model.process.ProcessPackage;

import org.bonitasoft.studio.model.process.decision.DecisionPackage;

import org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl;

import org.bonitasoft.studio.model.process.decision.transitions.TransitionsPackage;

import org.bonitasoft.studio.model.process.decision.transitions.impl.TransitionsPackageImpl;

import org.bonitasoft.studio.model.process.impl.ProcessPackageImpl;

import org.bonitasoft.studio.model.simulation.DataChange;
import org.bonitasoft.studio.model.simulation.DayPeriod;
import org.bonitasoft.studio.model.simulation.InjectionPeriod;
import org.bonitasoft.studio.model.simulation.LoadProfile;
import org.bonitasoft.studio.model.simulation.ModelVersion;
import org.bonitasoft.studio.model.simulation.RepartitionType;
import org.bonitasoft.studio.model.simulation.Resource;
import org.bonitasoft.studio.model.simulation.ResourceUsage;
import org.bonitasoft.studio.model.simulation.SimulationAbstractProcess;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.model.simulation.SimulationBoolean;
import org.bonitasoft.studio.model.simulation.SimulationCalendar;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationDataContainer;
import org.bonitasoft.studio.model.simulation.SimulationElement;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.model.simulation.SimulationLiteral;
import org.bonitasoft.studio.model.simulation.SimulationLiteralData;
import org.bonitasoft.studio.model.simulation.SimulationNumberData;
import org.bonitasoft.studio.model.simulation.SimulationNumberRange;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.model.simulation.SimulationTransition;
import org.bonitasoft.studio.model.simulation.TimeUnit;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimulationPackageImpl extends EPackageImpl implements SimulationPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationElementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationDataEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationTransitionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass resourceUsageEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass injectionPeriodEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationBooleanEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationNumberDataEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationLiteralDataEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationLiteralEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationNumberRangeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationDataContainerEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationAbstractProcessEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationActivityEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass dataChangeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass simulationCalendarEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass dayPeriodEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass modelVersionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass loadProfileEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass resourceEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum timeUnitEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum repartitionTypeEEnum = null;

	/**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private SimulationPackageImpl() {
        super(eNS_URI, SimulationFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link SimulationPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static SimulationPackage init() {
        if (isInited) return (SimulationPackage)EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredSimulationPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        SimulationPackageImpl theSimulationPackage = registeredSimulationPackage instanceof SimulationPackageImpl ? (SimulationPackageImpl)registeredSimulationPackage : new SimulationPackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ActorMappingPackage.eNS_URI);
        ActorMappingPackageImpl theActorMappingPackage = (ActorMappingPackageImpl)(registeredPackage instanceof ActorMappingPackageImpl ? registeredPackage : ActorMappingPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI);
        ConfigurationPackageImpl theConfigurationPackage = (ConfigurationPackageImpl)(registeredPackage instanceof ConfigurationPackageImpl ? registeredPackage : ConfigurationPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConnectorConfigurationPackage.eNS_URI);
        ConnectorConfigurationPackageImpl theConnectorConfigurationPackage = (ConnectorConfigurationPackageImpl)(registeredPackage instanceof ConnectorConfigurationPackageImpl ? registeredPackage : ConnectorConfigurationPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);
        ExpressionPackageImpl theExpressionPackage = (ExpressionPackageImpl)(registeredPackage instanceof ExpressionPackageImpl ? registeredPackage : ExpressionPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(KpiPackage.eNS_URI);
        KpiPackageImpl theKpiPackage = (KpiPackageImpl)(registeredPackage instanceof KpiPackageImpl ? registeredPackage : KpiPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI);
        ParameterPackageImpl theParameterPackage = (ParameterPackageImpl)(registeredPackage instanceof ParameterPackageImpl ? registeredPackage : ParameterPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI);
        ProcessPackageImpl theProcessPackage = (ProcessPackageImpl)(registeredPackage instanceof ProcessPackageImpl ? registeredPackage : ProcessPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DecisionPackage.eNS_URI);
        DecisionPackageImpl theDecisionPackage = (DecisionPackageImpl)(registeredPackage instanceof DecisionPackageImpl ? registeredPackage : DecisionPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TransitionsPackage.eNS_URI);
        TransitionsPackageImpl theTransitionsPackage = (TransitionsPackageImpl)(registeredPackage instanceof TransitionsPackageImpl ? registeredPackage : TransitionsPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);
        FormPackageImpl theFormPackage = (FormPackageImpl)(registeredPackage instanceof FormPackageImpl ? registeredPackage : FormPackage.eINSTANCE);

        // Create package meta-data objects
        theSimulationPackage.createPackageContents();
        theActorMappingPackage.createPackageContents();
        theConfigurationPackage.createPackageContents();
        theConnectorConfigurationPackage.createPackageContents();
        theExpressionPackage.createPackageContents();
        theKpiPackage.createPackageContents();
        theParameterPackage.createPackageContents();
        theProcessPackage.createPackageContents();
        theDecisionPackage.createPackageContents();
        theTransitionsPackage.createPackageContents();
        theFormPackage.createPackageContents();

        // Initialize created meta-data
        theSimulationPackage.initializePackageContents();
        theActorMappingPackage.initializePackageContents();
        theConfigurationPackage.initializePackageContents();
        theConnectorConfigurationPackage.initializePackageContents();
        theExpressionPackage.initializePackageContents();
        theKpiPackage.initializePackageContents();
        theParameterPackage.initializePackageContents();
        theProcessPackage.initializePackageContents();
        theDecisionPackage.initializePackageContents();
        theTransitionsPackage.initializePackageContents();
        theFormPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSimulationPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SimulationPackage.eNS_URI, theSimulationPackage);
        return theSimulationPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationElement() {
        return simulationElementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationElement_Name() {
        return (EAttribute)simulationElementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationElement_Description() {
        return (EAttribute)simulationElementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationData() {
        return simulationDataEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationData_ExpressionBased() {
        return (EAttribute)simulationDataEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSimulationData_Expression() {
        return (EReference)simulationDataEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationTransition() {
        return simulationTransitionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationTransition_Probability() {
        return (EAttribute)simulationTransitionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationTransition_DataBased() {
        return (EAttribute)simulationTransitionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationTransition_UseExpression() {
        return (EAttribute)simulationTransitionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSimulationTransition_Expression() {
        return (EReference)simulationTransitionEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getResourceUsage() {
        return resourceUsageEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResourceUsage_Duration() {
        return (EAttribute)resourceUsageEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResourceUsage_ResourceID() {
        return (EAttribute)resourceUsageEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResourceUsage_Quantity() {
        return (EAttribute)resourceUsageEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResourceUsage_UseActivityDuration() {
        return (EAttribute)resourceUsageEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getInjectionPeriod() {
        return injectionPeriodEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getInjectionPeriod_Begin() {
        return (EAttribute)injectionPeriodEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getInjectionPeriod_End() {
        return (EAttribute)injectionPeriodEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getInjectionPeriod_NbInstances() {
        return (EAttribute)injectionPeriodEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getInjectionPeriod_Repartition() {
        return (EAttribute)injectionPeriodEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationBoolean() {
        return simulationBooleanEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationBoolean_ProbabilityOfTrue() {
        return (EAttribute)simulationBooleanEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationNumberData() {
        return simulationNumberDataEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSimulationNumberData_Ranges() {
        return (EReference)simulationNumberDataEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationLiteralData() {
        return simulationLiteralDataEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSimulationLiteralData_Literals() {
        return (EReference)simulationLiteralDataEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationLiteral() {
        return simulationLiteralEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationLiteral_Probability() {
        return (EAttribute)simulationLiteralEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationLiteral_Value() {
        return (EAttribute)simulationLiteralEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationNumberRange() {
        return simulationNumberRangeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationNumberRange_Min() {
        return (EAttribute)simulationNumberRangeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationNumberRange_Max() {
        return (EAttribute)simulationNumberRangeEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationNumberRange_Probability() {
        return (EAttribute)simulationNumberRangeEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationNumberRange_RepartitionType() {
        return (EAttribute)simulationNumberRangeEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationDataContainer() {
        return simulationDataContainerEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSimulationDataContainer_SimulationData() {
        return (EReference)simulationDataContainerEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationAbstractProcess() {
        return simulationAbstractProcessEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationAbstractProcess_LoadProfileID() {
        return (EAttribute)simulationAbstractProcessEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationAbstractProcess_MaximumTime() {
        return (EAttribute)simulationAbstractProcessEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationActivity() {
        return simulationActivityEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSimulationActivity_ResourcesUsages() {
        return (EReference)simulationActivityEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationActivity_ExecutionTime() {
        return (EAttribute)simulationActivityEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationActivity_EstimatedTime() {
        return (EAttribute)simulationActivityEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationActivity_MaximumTime() {
        return (EAttribute)simulationActivityEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationActivity_Contigous() {
        return (EAttribute)simulationActivityEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getSimulationActivity_ExclusiveOutgoingTransition() {
        return (EAttribute)simulationActivityEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSimulationActivity_LoopTransition() {
        return (EReference)simulationActivityEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSimulationActivity_DataChange() {
        return (EReference)simulationActivityEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getDataChange() {
        return dataChangeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getDataChange_Data() {
        return (EReference)dataChangeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getDataChange_Value() {
        return (EReference)dataChangeEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getSimulationCalendar() {
        return simulationCalendarEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getSimulationCalendar_DaysOfWeek() {
        return (EReference)simulationCalendarEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getDayPeriod() {
        return dayPeriodEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getDayPeriod_Day() {
        return (EAttribute)dayPeriodEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getDayPeriod_StartHour() {
        return (EAttribute)dayPeriodEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getDayPeriod_EndHour() {
        return (EAttribute)dayPeriodEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getDayPeriod_StartMinute() {
        return (EAttribute)dayPeriodEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getDayPeriod_EndMinute() {
        return (EAttribute)dayPeriodEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getModelVersion() {
        return modelVersionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getModelVersion_Version() {
        return (EAttribute)modelVersionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getLoadProfile() {
        return loadProfileEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getLoadProfile_Calendar() {
        return (EReference)loadProfileEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getLoadProfile_InjectionPeriods() {
        return (EReference)loadProfileEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getResource() {
        return resourceEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResource_Type() {
        return (EAttribute)resourceEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResource_Quantity() {
        return (EAttribute)resourceEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResource_MaximumQuantity() {
        return (EAttribute)resourceEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResource_CostUnit() {
        return (EAttribute)resourceEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResource_TimeUnit() {
        return (EAttribute)resourceEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResource_FixedCost() {
        return (EAttribute)resourceEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResource_TimeCost() {
        return (EAttribute)resourceEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getResource_Calendar() {
        return (EReference)resourceEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getResource_Unlimited() {
        return (EAttribute)resourceEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getTimeUnit() {
        return timeUnitEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EEnum getRepartitionType() {
        return repartitionTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public SimulationFactory getSimulationFactory() {
        return (SimulationFactory)getEFactoryInstance();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

	/**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        simulationElementEClass = createEClass(SIMULATION_ELEMENT);
        createEAttribute(simulationElementEClass, SIMULATION_ELEMENT__NAME);
        createEAttribute(simulationElementEClass, SIMULATION_ELEMENT__DESCRIPTION);

        simulationDataEClass = createEClass(SIMULATION_DATA);
        createEAttribute(simulationDataEClass, SIMULATION_DATA__EXPRESSION_BASED);
        createEReference(simulationDataEClass, SIMULATION_DATA__EXPRESSION);

        simulationTransitionEClass = createEClass(SIMULATION_TRANSITION);
        createEAttribute(simulationTransitionEClass, SIMULATION_TRANSITION__PROBABILITY);
        createEAttribute(simulationTransitionEClass, SIMULATION_TRANSITION__DATA_BASED);
        createEAttribute(simulationTransitionEClass, SIMULATION_TRANSITION__USE_EXPRESSION);
        createEReference(simulationTransitionEClass, SIMULATION_TRANSITION__EXPRESSION);

        resourceUsageEClass = createEClass(RESOURCE_USAGE);
        createEAttribute(resourceUsageEClass, RESOURCE_USAGE__DURATION);
        createEAttribute(resourceUsageEClass, RESOURCE_USAGE__RESOURCE_ID);
        createEAttribute(resourceUsageEClass, RESOURCE_USAGE__QUANTITY);
        createEAttribute(resourceUsageEClass, RESOURCE_USAGE__USE_ACTIVITY_DURATION);

        injectionPeriodEClass = createEClass(INJECTION_PERIOD);
        createEAttribute(injectionPeriodEClass, INJECTION_PERIOD__BEGIN);
        createEAttribute(injectionPeriodEClass, INJECTION_PERIOD__END);
        createEAttribute(injectionPeriodEClass, INJECTION_PERIOD__NB_INSTANCES);
        createEAttribute(injectionPeriodEClass, INJECTION_PERIOD__REPARTITION);

        simulationBooleanEClass = createEClass(SIMULATION_BOOLEAN);
        createEAttribute(simulationBooleanEClass, SIMULATION_BOOLEAN__PROBABILITY_OF_TRUE);

        simulationNumberDataEClass = createEClass(SIMULATION_NUMBER_DATA);
        createEReference(simulationNumberDataEClass, SIMULATION_NUMBER_DATA__RANGES);

        simulationLiteralDataEClass = createEClass(SIMULATION_LITERAL_DATA);
        createEReference(simulationLiteralDataEClass, SIMULATION_LITERAL_DATA__LITERALS);

        simulationLiteralEClass = createEClass(SIMULATION_LITERAL);
        createEAttribute(simulationLiteralEClass, SIMULATION_LITERAL__PROBABILITY);
        createEAttribute(simulationLiteralEClass, SIMULATION_LITERAL__VALUE);

        simulationNumberRangeEClass = createEClass(SIMULATION_NUMBER_RANGE);
        createEAttribute(simulationNumberRangeEClass, SIMULATION_NUMBER_RANGE__MIN);
        createEAttribute(simulationNumberRangeEClass, SIMULATION_NUMBER_RANGE__MAX);
        createEAttribute(simulationNumberRangeEClass, SIMULATION_NUMBER_RANGE__PROBABILITY);
        createEAttribute(simulationNumberRangeEClass, SIMULATION_NUMBER_RANGE__REPARTITION_TYPE);

        simulationDataContainerEClass = createEClass(SIMULATION_DATA_CONTAINER);
        createEReference(simulationDataContainerEClass, SIMULATION_DATA_CONTAINER__SIMULATION_DATA);

        simulationAbstractProcessEClass = createEClass(SIMULATION_ABSTRACT_PROCESS);
        createEAttribute(simulationAbstractProcessEClass, SIMULATION_ABSTRACT_PROCESS__LOAD_PROFILE_ID);
        createEAttribute(simulationAbstractProcessEClass, SIMULATION_ABSTRACT_PROCESS__MAXIMUM_TIME);

        simulationActivityEClass = createEClass(SIMULATION_ACTIVITY);
        createEReference(simulationActivityEClass, SIMULATION_ACTIVITY__RESOURCES_USAGES);
        createEAttribute(simulationActivityEClass, SIMULATION_ACTIVITY__EXECUTION_TIME);
        createEAttribute(simulationActivityEClass, SIMULATION_ACTIVITY__ESTIMATED_TIME);
        createEAttribute(simulationActivityEClass, SIMULATION_ACTIVITY__MAXIMUM_TIME);
        createEAttribute(simulationActivityEClass, SIMULATION_ACTIVITY__CONTIGOUS);
        createEAttribute(simulationActivityEClass, SIMULATION_ACTIVITY__EXCLUSIVE_OUTGOING_TRANSITION);
        createEReference(simulationActivityEClass, SIMULATION_ACTIVITY__LOOP_TRANSITION);
        createEReference(simulationActivityEClass, SIMULATION_ACTIVITY__DATA_CHANGE);

        dataChangeEClass = createEClass(DATA_CHANGE);
        createEReference(dataChangeEClass, DATA_CHANGE__DATA);
        createEReference(dataChangeEClass, DATA_CHANGE__VALUE);

        simulationCalendarEClass = createEClass(SIMULATION_CALENDAR);
        createEReference(simulationCalendarEClass, SIMULATION_CALENDAR__DAYS_OF_WEEK);

        dayPeriodEClass = createEClass(DAY_PERIOD);
        createEAttribute(dayPeriodEClass, DAY_PERIOD__DAY);
        createEAttribute(dayPeriodEClass, DAY_PERIOD__START_HOUR);
        createEAttribute(dayPeriodEClass, DAY_PERIOD__END_HOUR);
        createEAttribute(dayPeriodEClass, DAY_PERIOD__START_MINUTE);
        createEAttribute(dayPeriodEClass, DAY_PERIOD__END_MINUTE);

        modelVersionEClass = createEClass(MODEL_VERSION);
        createEAttribute(modelVersionEClass, MODEL_VERSION__VERSION);

        loadProfileEClass = createEClass(LOAD_PROFILE);
        createEReference(loadProfileEClass, LOAD_PROFILE__CALENDAR);
        createEReference(loadProfileEClass, LOAD_PROFILE__INJECTION_PERIODS);

        resourceEClass = createEClass(RESOURCE);
        createEAttribute(resourceEClass, RESOURCE__TYPE);
        createEAttribute(resourceEClass, RESOURCE__QUANTITY);
        createEAttribute(resourceEClass, RESOURCE__MAXIMUM_QUANTITY);
        createEAttribute(resourceEClass, RESOURCE__COST_UNIT);
        createEAttribute(resourceEClass, RESOURCE__TIME_UNIT);
        createEAttribute(resourceEClass, RESOURCE__FIXED_COST);
        createEAttribute(resourceEClass, RESOURCE__TIME_COST);
        createEReference(resourceEClass, RESOURCE__CALENDAR);
        createEAttribute(resourceEClass, RESOURCE__UNLIMITED);

        // Create enums
        timeUnitEEnum = createEEnum(TIME_UNIT);
        repartitionTypeEEnum = createEEnum(REPARTITION_TYPE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

	/**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        ExpressionPackage theExpressionPackage = (ExpressionPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        simulationDataEClass.getESuperTypes().add(this.getSimulationElement());
        simulationBooleanEClass.getESuperTypes().add(this.getSimulationData());
        simulationNumberDataEClass.getESuperTypes().add(this.getSimulationData());
        simulationLiteralDataEClass.getESuperTypes().add(this.getSimulationData());
        simulationAbstractProcessEClass.getESuperTypes().add(this.getSimulationDataContainer());
        simulationActivityEClass.getESuperTypes().add(this.getSimulationDataContainer());
        loadProfileEClass.getESuperTypes().add(this.getSimulationElement());
        loadProfileEClass.getESuperTypes().add(this.getModelVersion());
        resourceEClass.getESuperTypes().add(this.getSimulationElement());
        resourceEClass.getESuperTypes().add(this.getModelVersion());

        // Initialize classes and features; add operations and parameters
        initEClass(simulationElementEClass, SimulationElement.class, "SimulationElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSimulationElement_Name(), ecorePackage.getEString(), "name", null, 1, 1, SimulationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSimulationElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, SimulationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(simulationDataEClass, SimulationData.class, "SimulationData", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSimulationData_ExpressionBased(), ecorePackage.getEBoolean(), "expressionBased", "true", 1, 1, SimulationData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getSimulationData_Expression(), theExpressionPackage.getExpression(), null, "expression", null, 0, 1, SimulationData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(simulationTransitionEClass, SimulationTransition.class, "SimulationTransition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSimulationTransition_Probability(), ecorePackage.getEDouble(), "probability", "1", 1, 1, SimulationTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getSimulationTransition_DataBased(), ecorePackage.getEBoolean(), "dataBased", null, 0, 1, SimulationTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSimulationTransition_UseExpression(), ecorePackage.getEBoolean(), "useExpression", "false", 0, 1, SimulationTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getSimulationTransition_Expression(), theExpressionPackage.getExpression(), null, "expression", null, 0, 1, SimulationTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(resourceUsageEClass, ResourceUsage.class, "ResourceUsage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getResourceUsage_Duration(), ecorePackage.getELong(), "duration", null, 0, 1, ResourceUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getResourceUsage_ResourceID(), ecorePackage.getEString(), "resourceID", null, 0, 1, ResourceUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getResourceUsage_Quantity(), ecorePackage.getEInt(), "quantity", "1", 1, 1, ResourceUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getResourceUsage_UseActivityDuration(), ecorePackage.getEBoolean(), "useActivityDuration", "true", 1, 1, ResourceUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(injectionPeriodEClass, InjectionPeriod.class, "InjectionPeriod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getInjectionPeriod_Begin(), ecorePackage.getELong(), "begin", "0", 1, 1, InjectionPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getInjectionPeriod_End(), ecorePackage.getELong(), "end", "0", 1, 1, InjectionPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getInjectionPeriod_NbInstances(), ecorePackage.getEInt(), "nbInstances", "1", 1, 1, InjectionPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getInjectionPeriod_Repartition(), this.getRepartitionType(), "repartition", "CONSTANT", 1, 1, InjectionPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(simulationBooleanEClass, SimulationBoolean.class, "SimulationBoolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSimulationBoolean_ProbabilityOfTrue(), ecorePackage.getEDouble(), "probabilityOfTrue", "1", 1, 1, SimulationBoolean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(simulationNumberDataEClass, SimulationNumberData.class, "SimulationNumberData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getSimulationNumberData_Ranges(), this.getSimulationNumberRange(), null, "ranges", null, 0, -1, SimulationNumberData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(simulationLiteralDataEClass, SimulationLiteralData.class, "SimulationLiteralData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getSimulationLiteralData_Literals(), this.getSimulationLiteral(), null, "literals", null, 0, -1, SimulationLiteralData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(simulationLiteralEClass, SimulationLiteral.class, "SimulationLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSimulationLiteral_Probability(), ecorePackage.getEDouble(), "probability", "1", 1, 1, SimulationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getSimulationLiteral_Value(), ecorePackage.getEString(), "value", "", 1, 1, SimulationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(simulationNumberRangeEClass, SimulationNumberRange.class, "SimulationNumberRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSimulationNumberRange_Min(), ecorePackage.getELong(), "min", "0", 1, 1, SimulationNumberRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getSimulationNumberRange_Max(), ecorePackage.getELong(), "max", "0", 1, 1, SimulationNumberRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getSimulationNumberRange_Probability(), ecorePackage.getEDouble(), "probability", "1", 0, 1, SimulationNumberRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getSimulationNumberRange_RepartitionType(), this.getRepartitionType(), "repartitionType", "CONSTANT", 1, 1, SimulationNumberRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(simulationDataContainerEClass, SimulationDataContainer.class, "SimulationDataContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getSimulationDataContainer_SimulationData(), this.getSimulationData(), null, "simulationData", null, 0, -1, SimulationDataContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(simulationAbstractProcessEClass, SimulationAbstractProcess.class, "SimulationAbstractProcess", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSimulationAbstractProcess_LoadProfileID(), ecorePackage.getEString(), "loadProfileID", null, 0, 1, SimulationAbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSimulationAbstractProcess_MaximumTime(), ecorePackage.getELong(), "maximumTime", null, 0, 1, SimulationAbstractProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(simulationActivityEClass, SimulationActivity.class, "SimulationActivity", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getSimulationActivity_ResourcesUsages(), this.getResourceUsage(), null, "resourcesUsages", null, 0, -1, SimulationActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSimulationActivity_ExecutionTime(), ecorePackage.getELong(), "executionTime", null, 0, 1, SimulationActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSimulationActivity_EstimatedTime(), ecorePackage.getEDouble(), "estimatedTime", "0", 0, 1, SimulationActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getSimulationActivity_MaximumTime(), ecorePackage.getEDouble(), "maximumTime", "0", 0, 1, SimulationActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getSimulationActivity_Contigous(), ecorePackage.getEBoolean(), "contigous", null, 0, 1, SimulationActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSimulationActivity_ExclusiveOutgoingTransition(), ecorePackage.getEBoolean(), "exclusiveOutgoingTransition", null, 0, 1, SimulationActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getSimulationActivity_LoopTransition(), this.getSimulationTransition(), null, "loopTransition", null, 0, 1, SimulationActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getSimulationActivity_DataChange(), this.getDataChange(), null, "dataChange", null, 0, -1, SimulationActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(dataChangeEClass, DataChange.class, "DataChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDataChange_Data(), this.getSimulationData(), null, "data", null, 0, 1, DataChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDataChange_Value(), theExpressionPackage.getExpression(), null, "value", null, 0, 1, DataChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(simulationCalendarEClass, SimulationCalendar.class, "SimulationCalendar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getSimulationCalendar_DaysOfWeek(), this.getDayPeriod(), null, "daysOfWeek", null, 0, 7, SimulationCalendar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(dayPeriodEClass, DayPeriod.class, "DayPeriod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDayPeriod_Day(), ecorePackage.getEInt(), "day", null, 0, -1, DayPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDayPeriod_StartHour(), ecorePackage.getEInt(), "startHour", "0", 1, 1, DayPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getDayPeriod_EndHour(), ecorePackage.getEInt(), "endHour", "0", 1, 1, DayPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getDayPeriod_StartMinute(), ecorePackage.getEInt(), "startMinute", "0", 1, 1, DayPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getDayPeriod_EndMinute(), ecorePackage.getEInt(), "endMinute", "0", 1, 1, DayPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(modelVersionEClass, ModelVersion.class, "ModelVersion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getModelVersion_Version(), ecorePackage.getEString(), "version", null, 0, 1, ModelVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(loadProfileEClass, LoadProfile.class, "LoadProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getLoadProfile_Calendar(), this.getSimulationCalendar(), null, "calendar", null, 0, 1, LoadProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getLoadProfile_InjectionPeriods(), this.getInjectionPeriod(), null, "injectionPeriods", null, 0, -1, LoadProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getResource_Type(), ecorePackage.getEString(), "type", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getResource_Quantity(), ecorePackage.getEInt(), "quantity", "1", 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getResource_MaximumQuantity(), ecorePackage.getEInt(), "maximumQuantity", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getResource_CostUnit(), ecorePackage.getEString(), "costUnit", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getResource_TimeUnit(), this.getTimeUnit(), "timeUnit", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getResource_FixedCost(), ecorePackage.getEDouble(), "fixedCost", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getResource_TimeCost(), ecorePackage.getEDouble(), "timeCost", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getResource_Calendar(), this.getSimulationCalendar(), null, "calendar", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getResource_Unlimited(), ecorePackage.getEBoolean(), "unlimited", "false", 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        // Initialize enums and add enum literals
        initEEnum(timeUnitEEnum, TimeUnit.class, "TimeUnit"); //$NON-NLS-1$
        addEEnumLiteral(timeUnitEEnum, TimeUnit.MINUTE);
        addEEnumLiteral(timeUnitEEnum, TimeUnit.HOUR);
        addEEnumLiteral(timeUnitEEnum, TimeUnit.DAY);
        addEEnumLiteral(timeUnitEEnum, TimeUnit.WEEK);
        addEEnumLiteral(timeUnitEEnum, TimeUnit.MONTH);
        addEEnumLiteral(timeUnitEEnum, TimeUnit.YEAR);

        initEEnum(repartitionTypeEEnum, RepartitionType.class, "RepartitionType"); //$NON-NLS-1$
        addEEnumLiteral(repartitionTypeEEnum, RepartitionType.CONSTANT);
        addEEnumLiteral(repartitionTypeEEnum, RepartitionType.DIRECT);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http://www.eclipse.org/edapt
        createEdaptAnnotations();
    }

	/**
     * Initializes the annotations for <b>http://www.eclipse.org/edapt</b>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void createEdaptAnnotations() {
        String source = "http://www.eclipse.org/edapt"; //$NON-NLS-1$
        addAnnotation
          (this,
           source,
           new String[] {
               "historyURI", "process.history" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

} //SimulationPackageImpl
