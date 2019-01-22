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
package org.bonitasoft.studio.model.kpi.impl;

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

import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;
import org.bonitasoft.studio.model.kpi.AbstractKPIDefinition;
import org.bonitasoft.studio.model.kpi.DatabaseKPIBinding;
import org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition;
import org.bonitasoft.studio.model.kpi.KPIField;
import org.bonitasoft.studio.model.kpi.KPIParameterMapping;
import org.bonitasoft.studio.model.kpi.KpiFactory;
import org.bonitasoft.studio.model.kpi.KpiPackage;

import org.bonitasoft.studio.model.parameter.ParameterPackage;

import org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl;

import org.bonitasoft.studio.model.process.ProcessPackage;

import org.bonitasoft.studio.model.process.decision.DecisionPackage;

import org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl;

import org.bonitasoft.studio.model.process.decision.transitions.TransitionsPackage;

import org.bonitasoft.studio.model.process.decision.transitions.impl.TransitionsPackageImpl;

import org.bonitasoft.studio.model.process.impl.ProcessPackageImpl;

import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KpiPackageImpl extends EPackageImpl implements KpiPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractKPIDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractKPIBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kpiParameterMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass databaseKPIBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass databaseKPIDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kpiFieldEClass = null;

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
	 * @see org.bonitasoft.studio.model.kpi.KpiPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private KpiPackageImpl() {
		super(eNS_URI, KpiFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link KpiPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static KpiPackage init() {
		if (isInited) return (KpiPackage)EPackage.Registry.INSTANCE.getEPackage(KpiPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredKpiPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		KpiPackageImpl theKpiPackage = registeredKpiPackage instanceof KpiPackageImpl ? (KpiPackageImpl)registeredKpiPackage : new KpiPackageImpl();

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
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI);
		SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl)(registeredPackage instanceof SimulationPackageImpl ? registeredPackage : SimulationPackage.eINSTANCE);

		// Create package meta-data objects
		theKpiPackage.createPackageContents();
		theActorMappingPackage.createPackageContents();
		theConfigurationPackage.createPackageContents();
		theConnectorConfigurationPackage.createPackageContents();
		theExpressionPackage.createPackageContents();
		theParameterPackage.createPackageContents();
		theProcessPackage.createPackageContents();
		theDecisionPackage.createPackageContents();
		theTransitionsPackage.createPackageContents();
		theFormPackage.createPackageContents();
		theSimulationPackage.createPackageContents();

		// Initialize created meta-data
		theKpiPackage.initializePackageContents();
		theActorMappingPackage.initializePackageContents();
		theConfigurationPackage.initializePackageContents();
		theConnectorConfigurationPackage.initializePackageContents();
		theExpressionPackage.initializePackageContents();
		theParameterPackage.initializePackageContents();
		theProcessPackage.initializePackageContents();
		theDecisionPackage.initializePackageContents();
		theTransitionsPackage.initializePackageContents();
		theFormPackage.initializePackageContents();
		theSimulationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theKpiPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(KpiPackage.eNS_URI, theKpiPackage);
		return theKpiPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractKPIDefinition() {
		return abstractKPIDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractKPIDefinition_Version() {
		return (EAttribute)abstractKPIDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractKPIDefinition_Fields() {
		return (EReference)abstractKPIDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractKPIBinding() {
		return abstractKPIBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractKPIBinding_KpiDefinitionName() {
		return (EAttribute)abstractKPIBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractKPIBinding_Event() {
		return (EAttribute)abstractKPIBindingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractKPIBinding_IgnoreError() {
		return (EAttribute)abstractKPIBindingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractKPIBinding_UseGraphicalEditor() {
		return (EAttribute)abstractKPIBindingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractKPIBinding_Request() {
		return (EReference)abstractKPIBindingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractKPIBinding_Parameters() {
		return (EReference)abstractKPIBindingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getKPIParameterMapping() {
		return kpiParameterMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKPIParameterMapping_KpiFieldName() {
		return (EAttribute)kpiParameterMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getKPIParameterMapping_Value() {
		return (EReference)kpiParameterMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDatabaseKPIBinding() {
		return databaseKPIBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIBinding_DriverclassName() {
		return (EReference)databaseKPIBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIBinding_JdbcUrl() {
		return (EReference)databaseKPIBindingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIBinding_User() {
		return (EReference)databaseKPIBindingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIBinding_Password() {
		return (EReference)databaseKPIBindingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIBinding_JndiUrl() {
		return (EReference)databaseKPIBindingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDatabaseKPIBinding_TableName() {
		return (EAttribute)databaseKPIBindingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDatabaseKPIDefinition() {
		return databaseKPIDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIDefinition_DefaultDriverclassName() {
		return (EReference)databaseKPIDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIDefinition_DefaultJdbcUrl() {
		return (EReference)databaseKPIDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIDefinition_DefaultUser() {
		return (EReference)databaseKPIDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIDefinition_DefaultPassword() {
		return (EReference)databaseKPIDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIDefinition_DefaultJNDIUrl() {
		return (EReference)databaseKPIDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDatabaseKPIDefinition_DefaultDBName() {
		return (EReference)databaseKPIDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDatabaseKPIDefinition_DefaultTableName() {
		return (EAttribute)databaseKPIDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getKPIField() {
		return kpiFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKPIField_FieldName() {
		return (EAttribute)kpiFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKPIField_FieldType() {
		return (EAttribute)kpiFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKPIField_UseQuotes() {
		return (EAttribute)kpiFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KpiFactory getKpiFactory() {
		return (KpiFactory)getEFactoryInstance();
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
		abstractKPIDefinitionEClass = createEClass(ABSTRACT_KPI_DEFINITION);
		createEAttribute(abstractKPIDefinitionEClass, ABSTRACT_KPI_DEFINITION__VERSION);
		createEReference(abstractKPIDefinitionEClass, ABSTRACT_KPI_DEFINITION__FIELDS);

		abstractKPIBindingEClass = createEClass(ABSTRACT_KPI_BINDING);
		createEAttribute(abstractKPIBindingEClass, ABSTRACT_KPI_BINDING__KPI_DEFINITION_NAME);
		createEAttribute(abstractKPIBindingEClass, ABSTRACT_KPI_BINDING__EVENT);
		createEAttribute(abstractKPIBindingEClass, ABSTRACT_KPI_BINDING__IGNORE_ERROR);
		createEAttribute(abstractKPIBindingEClass, ABSTRACT_KPI_BINDING__USE_GRAPHICAL_EDITOR);
		createEReference(abstractKPIBindingEClass, ABSTRACT_KPI_BINDING__REQUEST);
		createEReference(abstractKPIBindingEClass, ABSTRACT_KPI_BINDING__PARAMETERS);

		kpiParameterMappingEClass = createEClass(KPI_PARAMETER_MAPPING);
		createEAttribute(kpiParameterMappingEClass, KPI_PARAMETER_MAPPING__KPI_FIELD_NAME);
		createEReference(kpiParameterMappingEClass, KPI_PARAMETER_MAPPING__VALUE);

		databaseKPIBindingEClass = createEClass(DATABASE_KPI_BINDING);
		createEReference(databaseKPIBindingEClass, DATABASE_KPI_BINDING__DRIVERCLASS_NAME);
		createEReference(databaseKPIBindingEClass, DATABASE_KPI_BINDING__JDBC_URL);
		createEReference(databaseKPIBindingEClass, DATABASE_KPI_BINDING__USER);
		createEReference(databaseKPIBindingEClass, DATABASE_KPI_BINDING__PASSWORD);
		createEReference(databaseKPIBindingEClass, DATABASE_KPI_BINDING__JNDI_URL);
		createEAttribute(databaseKPIBindingEClass, DATABASE_KPI_BINDING__TABLE_NAME);

		databaseKPIDefinitionEClass = createEClass(DATABASE_KPI_DEFINITION);
		createEReference(databaseKPIDefinitionEClass, DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME);
		createEReference(databaseKPIDefinitionEClass, DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL);
		createEReference(databaseKPIDefinitionEClass, DATABASE_KPI_DEFINITION__DEFAULT_USER);
		createEReference(databaseKPIDefinitionEClass, DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD);
		createEReference(databaseKPIDefinitionEClass, DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL);
		createEReference(databaseKPIDefinitionEClass, DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME);
		createEAttribute(databaseKPIDefinitionEClass, DATABASE_KPI_DEFINITION__DEFAULT_TABLE_NAME);

		kpiFieldEClass = createEClass(KPI_FIELD);
		createEAttribute(kpiFieldEClass, KPI_FIELD__FIELD_NAME);
		createEAttribute(kpiFieldEClass, KPI_FIELD__FIELD_TYPE);
		createEAttribute(kpiFieldEClass, KPI_FIELD__USE_QUOTES);
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
		ProcessPackage theProcessPackage = (ProcessPackage)EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI);
		ExpressionPackage theExpressionPackage = (ExpressionPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractKPIDefinitionEClass.getESuperTypes().add(theProcessPackage.getElement());
		abstractKPIBindingEClass.getESuperTypes().add(theProcessPackage.getElement());
		databaseKPIBindingEClass.getESuperTypes().add(this.getAbstractKPIBinding());
		databaseKPIDefinitionEClass.getESuperTypes().add(this.getAbstractKPIDefinition());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractKPIDefinitionEClass, AbstractKPIDefinition.class, "AbstractKPIDefinition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAbstractKPIDefinition_Version(), ecorePackage.getEString(), "version", null, 0, 1, AbstractKPIDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAbstractKPIDefinition_Fields(), this.getKPIField(), null, "fields", null, 0, -1, AbstractKPIDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(abstractKPIBindingEClass, AbstractKPIBinding.class, "AbstractKPIBinding", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAbstractKPIBinding_KpiDefinitionName(), ecorePackage.getEString(), "kpiDefinitionName", null, 0, 1, AbstractKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAbstractKPIBinding_Event(), ecorePackage.getEString(), "event", null, 0, 1, AbstractKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAbstractKPIBinding_IgnoreError(), ecorePackage.getEBoolean(), "ignoreError", null, 0, 1, AbstractKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAbstractKPIBinding_UseGraphicalEditor(), ecorePackage.getEBoolean(), "useGraphicalEditor", "true", 0, 1, AbstractKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getAbstractKPIBinding_Request(), theExpressionPackage.getExpression(), null, "request", null, 0, 1, AbstractKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAbstractKPIBinding_Parameters(), this.getKPIParameterMapping(), null, "parameters", null, 0, -1, AbstractKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(kpiParameterMappingEClass, KPIParameterMapping.class, "KPIParameterMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getKPIParameterMapping_KpiFieldName(), ecorePackage.getEString(), "kpiFieldName", null, 0, 1, KPIParameterMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getKPIParameterMapping_Value(), theExpressionPackage.getExpression(), null, "value", null, 0, 1, KPIParameterMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(databaseKPIBindingEClass, DatabaseKPIBinding.class, "DatabaseKPIBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDatabaseKPIBinding_DriverclassName(), theExpressionPackage.getExpression(), null, "driverclassName", null, 0, 1, DatabaseKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseKPIBinding_JdbcUrl(), theExpressionPackage.getExpression(), null, "jdbcUrl", null, 0, 1, DatabaseKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseKPIBinding_User(), theExpressionPackage.getExpression(), null, "user", null, 0, 1, DatabaseKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseKPIBinding_Password(), theExpressionPackage.getExpression(), null, "password", null, 0, 1, DatabaseKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseKPIBinding_JndiUrl(), theExpressionPackage.getExpression(), null, "jndiUrl", null, 0, 1, DatabaseKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseKPIBinding_TableName(), ecorePackage.getEString(), "tableName", null, 0, 1, DatabaseKPIBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(databaseKPIDefinitionEClass, DatabaseKPIDefinition.class, "DatabaseKPIDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDatabaseKPIDefinition_DefaultDriverclassName(), theExpressionPackage.getExpression(), null, "defaultDriverclassName", null, 0, 1, DatabaseKPIDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseKPIDefinition_DefaultJdbcUrl(), theExpressionPackage.getExpression(), null, "defaultJdbcUrl", null, 0, 1, DatabaseKPIDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseKPIDefinition_DefaultUser(), theExpressionPackage.getExpression(), null, "defaultUser", null, 0, 1, DatabaseKPIDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseKPIDefinition_DefaultPassword(), theExpressionPackage.getExpression(), null, "defaultPassword", null, 0, 1, DatabaseKPIDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseKPIDefinition_DefaultJNDIUrl(), theExpressionPackage.getExpression(), null, "defaultJNDIUrl", null, 0, 1, DatabaseKPIDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseKPIDefinition_DefaultDBName(), theExpressionPackage.getExpression(), null, "defaultDBName", null, 0, 1, DatabaseKPIDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseKPIDefinition_DefaultTableName(), ecorePackage.getEString(), "defaultTableName", null, 0, 1, DatabaseKPIDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(kpiFieldEClass, KPIField.class, "KPIField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getKPIField_FieldName(), ecorePackage.getEString(), "fieldName", null, 0, 1, KPIField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getKPIField_FieldType(), ecorePackage.getEString(), "fieldType", null, 0, 1, KPIField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getKPIField_UseQuotes(), ecorePackage.getEBoolean(), "useQuotes", null, 0, 1, KPIField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

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

} //KpiPackageImpl
