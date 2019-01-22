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
package org.bonitasoft.studio.model.process.decision.transitions.impl;

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

import org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction;
import org.bonitasoft.studio.model.process.decision.transitions.TransitionsFactory;
import org.bonitasoft.studio.model.process.decision.transitions.TransitionsPackage;

import org.bonitasoft.studio.model.process.impl.ProcessPackageImpl;

import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TransitionsPackageImpl extends EPackageImpl implements TransitionsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass takeTransitionActionEClass = null;

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
	 * @see org.bonitasoft.studio.model.process.decision.transitions.TransitionsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TransitionsPackageImpl() {
		super(eNS_URI, TransitionsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link TransitionsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TransitionsPackage init() {
		if (isInited) return (TransitionsPackage)EPackage.Registry.INSTANCE.getEPackage(TransitionsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredTransitionsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		TransitionsPackageImpl theTransitionsPackage = registeredTransitionsPackage instanceof TransitionsPackageImpl ? (TransitionsPackageImpl)registeredTransitionsPackage : new TransitionsPackageImpl();

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
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);
		FormPackageImpl theFormPackage = (FormPackageImpl)(registeredPackage instanceof FormPackageImpl ? registeredPackage : FormPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI);
		SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl)(registeredPackage instanceof SimulationPackageImpl ? registeredPackage : SimulationPackage.eINSTANCE);

		// Create package meta-data objects
		theTransitionsPackage.createPackageContents();
		theActorMappingPackage.createPackageContents();
		theConfigurationPackage.createPackageContents();
		theConnectorConfigurationPackage.createPackageContents();
		theExpressionPackage.createPackageContents();
		theKpiPackage.createPackageContents();
		theParameterPackage.createPackageContents();
		theProcessPackage.createPackageContents();
		theDecisionPackage.createPackageContents();
		theFormPackage.createPackageContents();
		theSimulationPackage.createPackageContents();

		// Initialize created meta-data
		theTransitionsPackage.initializePackageContents();
		theActorMappingPackage.initializePackageContents();
		theConfigurationPackage.initializePackageContents();
		theConnectorConfigurationPackage.initializePackageContents();
		theExpressionPackage.initializePackageContents();
		theKpiPackage.initializePackageContents();
		theParameterPackage.initializePackageContents();
		theProcessPackage.initializePackageContents();
		theDecisionPackage.initializePackageContents();
		theFormPackage.initializePackageContents();
		theSimulationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTransitionsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TransitionsPackage.eNS_URI, theTransitionsPackage);
		return theTransitionsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTakeTransitionAction() {
		return takeTransitionActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTakeTransitionAction_TakeTransition() {
		return (EAttribute)takeTransitionActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TransitionsFactory getTransitionsFactory() {
		return (TransitionsFactory)getEFactoryInstance();
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
		takeTransitionActionEClass = createEClass(TAKE_TRANSITION_ACTION);
		createEAttribute(takeTransitionActionEClass, TAKE_TRANSITION_ACTION__TAKE_TRANSITION);
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
		DecisionPackage theDecisionPackage = (DecisionPackage)EPackage.Registry.INSTANCE.getEPackage(DecisionPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		takeTransitionActionEClass.getESuperTypes().add(theDecisionPackage.getDecisionTableAction());

		// Initialize classes and features; add operations and parameters
		initEClass(takeTransitionActionEClass, TakeTransitionAction.class, "TakeTransitionAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTakeTransitionAction_TakeTransition(), ecorePackage.getEBoolean(), "takeTransition", null, 0, 1, TakeTransitionAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	}

} //TransitionsPackageImpl
