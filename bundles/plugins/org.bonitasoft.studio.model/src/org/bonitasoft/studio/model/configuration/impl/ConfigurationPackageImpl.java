/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.configuration.impl;

import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;

import org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl;

import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;

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
 * 
 * @generated
 */
public class ConfigurationPackageImpl extends EPackageImpl implements ConfigurationPackage {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass configurationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass fragmentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass definitionMappingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass fragmentContainerEClass = null;

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
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ConfigurationPackageImpl() {
        super(eNS_URI, ConfigurationFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>This method is used to initialize {@link ConfigurationPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ConfigurationPackage init() {
        if (isInited)
            return (ConfigurationPackage) EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI);

        // Obtain or create and register package
        ConfigurationPackageImpl theConfigurationPackage = (ConfigurationPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof ConfigurationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new ConfigurationPackageImpl());

        isInited = true;

        // Obtain or create and register interdependencies
        ActorMappingPackageImpl theActorMappingPackage = (ActorMappingPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ActorMappingPackage.eNS_URI) instanceof ActorMappingPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ActorMappingPackage.eNS_URI)
                        : ActorMappingPackage.eINSTANCE);
        ConnectorConfigurationPackageImpl theConnectorConfigurationPackage = (ConnectorConfigurationPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ConnectorConfigurationPackage.eNS_URI) instanceof ConnectorConfigurationPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ConnectorConfigurationPackage.eNS_URI)
                        : ConnectorConfigurationPackage.eINSTANCE);
        ExpressionPackageImpl theExpressionPackage = (ExpressionPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ExpressionPackage.eNS_URI) instanceof ExpressionPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI) : ExpressionPackage.eINSTANCE);
        FormPackageImpl theFormPackage = (FormPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(FormPackage.eNS_URI) instanceof FormPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) : FormPackage.eINSTANCE);
        KpiPackageImpl theKpiPackage = (KpiPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(KpiPackage.eNS_URI) instanceof KpiPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(KpiPackage.eNS_URI) : KpiPackage.eINSTANCE);
        ParameterPackageImpl theParameterPackage = (ParameterPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ParameterPackage.eNS_URI) instanceof ParameterPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI) : ParameterPackage.eINSTANCE);
        ProcessPackageImpl theProcessPackage = (ProcessPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ProcessPackage.eNS_URI) instanceof ProcessPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI) : ProcessPackage.eINSTANCE);
        DecisionPackageImpl theDecisionPackage = (DecisionPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(DecisionPackage.eNS_URI) instanceof DecisionPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(DecisionPackage.eNS_URI) : DecisionPackage.eINSTANCE);
        TransitionsPackageImpl theTransitionsPackage = (TransitionsPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(TransitionsPackage.eNS_URI) instanceof TransitionsPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(TransitionsPackage.eNS_URI) : TransitionsPackage.eINSTANCE);
        SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(SimulationPackage.eNS_URI) instanceof SimulationPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI) : SimulationPackage.eINSTANCE);

        // Create package meta-data objects
        theConfigurationPackage.createPackageContents();
        theActorMappingPackage.createPackageContents();
        theConnectorConfigurationPackage.createPackageContents();
        theExpressionPackage.createPackageContents();
        theFormPackage.createPackageContents();
        theKpiPackage.createPackageContents();
        theParameterPackage.createPackageContents();
        theProcessPackage.createPackageContents();
        theDecisionPackage.createPackageContents();
        theTransitionsPackage.createPackageContents();
        theSimulationPackage.createPackageContents();

        // Initialize created meta-data
        theConfigurationPackage.initializePackageContents();
        theActorMappingPackage.initializePackageContents();
        theConnectorConfigurationPackage.initializePackageContents();
        theExpressionPackage.initializePackageContents();
        theFormPackage.initializePackageContents();
        theKpiPackage.initializePackageContents();
        theParameterPackage.initializePackageContents();
        theProcessPackage.initializePackageContents();
        theDecisionPackage.initializePackageContents();
        theTransitionsPackage.initializePackageContents();
        theSimulationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theConfigurationPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ConfigurationPackage.eNS_URI, theConfigurationPackage);
        return theConfigurationPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getConfiguration() {
        return configurationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConfiguration_Name() {
        return (EAttribute) configurationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConfiguration_Description() {
        return (EAttribute) configurationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConfiguration_ActorMappings() {
        return (EReference) configurationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConfiguration_AnonymousUserName() {
        return (EAttribute) configurationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConfiguration_AnonymousPassword() {
        return (EAttribute) configurationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConfiguration_DefinitionMappings() {
        return (EReference) configurationEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConfiguration_ProcessDependencies() {
        return (EReference) configurationEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConfiguration_ApplicationDependencies() {
        return (EReference) configurationEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConfiguration_Parameters() {
        return (EReference) configurationEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConfiguration_Version() {
        return (EAttribute) configurationEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConfiguration_Username() {
        return (EAttribute) configurationEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConfiguration_Password() {
        return (EAttribute) configurationEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFragment() {
        return fragmentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFragment_Key() {
        return (EAttribute) fragmentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFragment_Value() {
        return (EAttribute) fragmentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFragment_Exported() {
        return (EAttribute) fragmentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFragment_Type() {
        return (EAttribute) fragmentEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDefinitionMapping() {
        return definitionMappingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDefinitionMapping_Type() {
        return (EAttribute) definitionMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDefinitionMapping_DefinitionId() {
        return (EAttribute) definitionMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDefinitionMapping_DefinitionVersion() {
        return (EAttribute) definitionMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDefinitionMapping_ImplementationId() {
        return (EAttribute) definitionMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDefinitionMapping_ImplementationVersion() {
        return (EAttribute) definitionMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFragmentContainer() {
        return fragmentContainerEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFragmentContainer_Children() {
        return (EReference) fragmentContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFragmentContainer_Parent() {
        return (EReference) fragmentContainerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFragmentContainer_Fragments() {
        return (EReference) fragmentContainerEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFragmentContainer_Id() {
        return (EAttribute) fragmentContainerEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public ConfigurationFactory getConfigurationFactory() {
        return (ConfigurationFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        configurationEClass = createEClass(CONFIGURATION);
        createEAttribute(configurationEClass, CONFIGURATION__NAME);
        createEAttribute(configurationEClass, CONFIGURATION__DESCRIPTION);
        createEReference(configurationEClass, CONFIGURATION__ACTOR_MAPPINGS);
        createEAttribute(configurationEClass, CONFIGURATION__ANONYMOUS_USER_NAME);
        createEAttribute(configurationEClass, CONFIGURATION__ANONYMOUS_PASSWORD);
        createEReference(configurationEClass, CONFIGURATION__DEFINITION_MAPPINGS);
        createEReference(configurationEClass, CONFIGURATION__PROCESS_DEPENDENCIES);
        createEReference(configurationEClass, CONFIGURATION__APPLICATION_DEPENDENCIES);
        createEReference(configurationEClass, CONFIGURATION__PARAMETERS);
        createEAttribute(configurationEClass, CONFIGURATION__VERSION);
        createEAttribute(configurationEClass, CONFIGURATION__USERNAME);
        createEAttribute(configurationEClass, CONFIGURATION__PASSWORD);

        fragmentEClass = createEClass(FRAGMENT);
        createEAttribute(fragmentEClass, FRAGMENT__KEY);
        createEAttribute(fragmentEClass, FRAGMENT__VALUE);
        createEAttribute(fragmentEClass, FRAGMENT__EXPORTED);
        createEAttribute(fragmentEClass, FRAGMENT__TYPE);

        definitionMappingEClass = createEClass(DEFINITION_MAPPING);
        createEAttribute(definitionMappingEClass, DEFINITION_MAPPING__TYPE);
        createEAttribute(definitionMappingEClass, DEFINITION_MAPPING__DEFINITION_ID);
        createEAttribute(definitionMappingEClass, DEFINITION_MAPPING__DEFINITION_VERSION);
        createEAttribute(definitionMappingEClass, DEFINITION_MAPPING__IMPLEMENTATION_ID);
        createEAttribute(definitionMappingEClass, DEFINITION_MAPPING__IMPLEMENTATION_VERSION);

        fragmentContainerEClass = createEClass(FRAGMENT_CONTAINER);
        createEReference(fragmentContainerEClass, FRAGMENT_CONTAINER__CHILDREN);
        createEReference(fragmentContainerEClass, FRAGMENT_CONTAINER__PARENT);
        createEReference(fragmentContainerEClass, FRAGMENT_CONTAINER__FRAGMENTS);
        createEAttribute(fragmentContainerEClass, FRAGMENT_CONTAINER__ID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        ActorMappingPackage theActorMappingPackage = (ActorMappingPackage) EPackage.Registry.INSTANCE
                .getEPackage(ActorMappingPackage.eNS_URI);
        ParameterPackage theParameterPackage = (ParameterPackage) EPackage.Registry.INSTANCE
                .getEPackage(ParameterPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(configurationEClass, Configuration.class, "Configuration", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConfiguration_Name(), ecorePackage.getEString(), "name", null, 1, 1, Configuration.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConfiguration_Description(), ecorePackage.getEString(), "description", null, 0, 1, //$NON-NLS-1$
                Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getConfiguration_ActorMappings(), theActorMappingPackage.getActorMappingsType(), null,
                "actorMappings", null, 0, 1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConfiguration_AnonymousUserName(), ecorePackage.getEString(), "anonymousUserName", null, 0, 1, //$NON-NLS-1$
                Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConfiguration_AnonymousPassword(), ecorePackage.getEString(), "anonymousPassword", null, 0, 1, //$NON-NLS-1$
                Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getConfiguration_DefinitionMappings(), this.getDefinitionMapping(), null, "definitionMappings", null, //$NON-NLS-1$
                0, -1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConfiguration_ProcessDependencies(), this.getFragmentContainer(), null, "processDependencies", //$NON-NLS-1$
                null, 0, -1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConfiguration_ApplicationDependencies(), this.getFragmentContainer(), null,
                "applicationDependencies", null, 0, -1, Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConfiguration_Parameters(), theParameterPackage.getParameter(), null, "parameters", null, 0, -1, //$NON-NLS-1$
                Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConfiguration_Version(), ecorePackage.getEString(), "version", null, 0, 1, Configuration.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConfiguration_Username(), ecorePackage.getEString(), "username", null, 0, 1, Configuration.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConfiguration_Password(), ecorePackage.getEString(), "password", null, 0, 1, Configuration.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fragmentEClass, Fragment.class, "Fragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFragment_Key(), ecorePackage.getEString(), "key", null, 1, 1, Fragment.class, !IS_TRANSIENT, //$NON-NLS-1$
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFragment_Value(), ecorePackage.getEString(), "value", null, 0, 1, Fragment.class, !IS_TRANSIENT, //$NON-NLS-1$
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFragment_Exported(), ecorePackage.getEBoolean(), "exported", "true", 0, 1, Fragment.class, //$NON-NLS-1$//$NON-NLS-2$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFragment_Type(), ecorePackage.getEString(), "type", null, 1, 1, Fragment.class, !IS_TRANSIENT, //$NON-NLS-1$
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(definitionMappingEClass, DefinitionMapping.class, "DefinitionMapping", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDefinitionMapping_Type(), ecorePackage.getEString(), "type", "", 1, 1, DefinitionMapping.class, //$NON-NLS-1$//$NON-NLS-2$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitionMapping_DefinitionId(), ecorePackage.getEString(), "definitionId", null, 1, 1, //$NON-NLS-1$
                DefinitionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitionMapping_DefinitionVersion(), ecorePackage.getEString(), "definitionVersion", null, 1, 1, //$NON-NLS-1$
                DefinitionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitionMapping_ImplementationId(), ecorePackage.getEString(), "implementationId", null, 0, 1, //$NON-NLS-1$
                DefinitionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDefinitionMapping_ImplementationVersion(), ecorePackage.getEString(), "implementationVersion", //$NON-NLS-1$
                null, 0, 1, DefinitionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fragmentContainerEClass, FragmentContainer.class, "FragmentContainer", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFragmentContainer_Children(), this.getFragmentContainer(), this.getFragmentContainer_Parent(),
                "children", null, 0, -1, FragmentContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFragmentContainer_Parent(), this.getFragmentContainer(), this.getFragmentContainer_Children(),
                "parent", null, 0, 1, FragmentContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFragmentContainer_Fragments(), this.getFragment(), null, "fragments", null, 0, -1, //$NON-NLS-1$
                FragmentContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFragmentContainer_Id(), ecorePackage.getEString(), "id", null, 1, 1, FragmentContainer.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
     * 
     * @generated
     */
    protected void createEdaptAnnotations() {
        String source = "http://www.eclipse.org/edapt"; //$NON-NLS-1$	
        addAnnotation(this,
                source,
                new String[] {
                        "historyURI", "process.history" //$NON-NLS-1$ //$NON-NLS-2$
                });
    }

} //ConfigurationPackageImpl
