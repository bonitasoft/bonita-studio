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
package org.bonitasoft.studio.model.connectorconfiguration.impl;

import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;

import org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl;

import org.bonitasoft.studio.model.configuration.ConfigurationPackage;

import org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;

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

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ConnectorConfigurationPackageImpl extends EPackageImpl implements ConnectorConfigurationPackage {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass connectorConfigurationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass connectorParameterEClass = null;

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
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ConnectorConfigurationPackageImpl() {
        super(eNS_URI, ConnectorConfigurationFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ConnectorConfigurationPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ConnectorConfigurationPackage init() {
        if (isInited)
            return (ConnectorConfigurationPackage) EPackage.Registry.INSTANCE
                    .getEPackage(ConnectorConfigurationPackage.eNS_URI);

        // Obtain or create and register package
        ConnectorConfigurationPackageImpl theConnectorConfigurationPackage = (ConnectorConfigurationPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof ConnectorConfigurationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new ConnectorConfigurationPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        ActorMappingPackageImpl theActorMappingPackage = (ActorMappingPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ActorMappingPackage.eNS_URI) instanceof ActorMappingPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ActorMappingPackage.eNS_URI)
                        : ActorMappingPackage.eINSTANCE);
        ConfigurationPackageImpl theConfigurationPackage = (ConfigurationPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ConfigurationPackage.eNS_URI) instanceof ConfigurationPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI)
                        : ConfigurationPackage.eINSTANCE);
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
        theConnectorConfigurationPackage.createPackageContents();
        theActorMappingPackage.createPackageContents();
        theConfigurationPackage.createPackageContents();
        theExpressionPackage.createPackageContents();
        theFormPackage.createPackageContents();
        theKpiPackage.createPackageContents();
        theParameterPackage.createPackageContents();
        theProcessPackage.createPackageContents();
        theDecisionPackage.createPackageContents();
        theTransitionsPackage.createPackageContents();
        theSimulationPackage.createPackageContents();

        // Initialize created meta-data
        theConnectorConfigurationPackage.initializePackageContents();
        theActorMappingPackage.initializePackageContents();
        theConfigurationPackage.initializePackageContents();
        theExpressionPackage.initializePackageContents();
        theFormPackage.initializePackageContents();
        theKpiPackage.initializePackageContents();
        theParameterPackage.initializePackageContents();
        theProcessPackage.initializePackageContents();
        theDecisionPackage.initializePackageContents();
        theTransitionsPackage.initializePackageContents();
        theSimulationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theConnectorConfigurationPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ConnectorConfigurationPackage.eNS_URI, theConnectorConfigurationPackage);
        return theConnectorConfigurationPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getConnectorConfiguration() {
        return connectorConfigurationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConnectorConfiguration_DefinitionId() {
        return (EAttribute) connectorConfigurationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConnectorConfiguration_Version() {
        return (EAttribute) connectorConfigurationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConnectorConfiguration_Name() {
        return (EAttribute) connectorConfigurationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConnectorConfiguration_Parameters() {
        return (EReference) connectorConfigurationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConnectorConfiguration_ModelVersion() {
        return (EAttribute) connectorConfigurationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getConnectorParameter() {
        return connectorParameterEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getConnectorParameter_Key() {
        return (EAttribute) connectorParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getConnectorParameter_Expression() {
        return (EReference) connectorParameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public ConnectorConfigurationFactory getConnectorConfigurationFactory() {
        return (ConnectorConfigurationFactory) getEFactoryInstance();
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
        connectorConfigurationEClass = createEClass(CONNECTOR_CONFIGURATION);
        createEAttribute(connectorConfigurationEClass, CONNECTOR_CONFIGURATION__DEFINITION_ID);
        createEAttribute(connectorConfigurationEClass, CONNECTOR_CONFIGURATION__VERSION);
        createEAttribute(connectorConfigurationEClass, CONNECTOR_CONFIGURATION__NAME);
        createEReference(connectorConfigurationEClass, CONNECTOR_CONFIGURATION__PARAMETERS);
        createEAttribute(connectorConfigurationEClass, CONNECTOR_CONFIGURATION__MODEL_VERSION);

        connectorParameterEClass = createEClass(CONNECTOR_PARAMETER);
        createEAttribute(connectorParameterEClass, CONNECTOR_PARAMETER__KEY);
        createEReference(connectorParameterEClass, CONNECTOR_PARAMETER__EXPRESSION);
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
        XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
        ExpressionPackage theExpressionPackage = (ExpressionPackage) EPackage.Registry.INSTANCE
                .getEPackage(ExpressionPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(connectorConfigurationEClass, ConnectorConfiguration.class, "ConnectorConfiguration", !IS_ABSTRACT, //$NON-NLS-1$
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConnectorConfiguration_DefinitionId(), theXMLTypePackage.getString(), "definitionId", null, 1, 1, //$NON-NLS-1$
                ConnectorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConnectorConfiguration_Version(), ecorePackage.getEString(), "version", null, 1, 1, //$NON-NLS-1$
                ConnectorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConnectorConfiguration_Name(), ecorePackage.getEString(), "name", null, 0, 1, //$NON-NLS-1$
                ConnectorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getConnectorConfiguration_Parameters(), this.getConnectorParameter(), null, "parameters", null, 0, -1, //$NON-NLS-1$
                ConnectorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConnectorConfiguration_ModelVersion(), ecorePackage.getEString(), "modelVersion", "", 0, 1, //$NON-NLS-1$//$NON-NLS-2$
                ConnectorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(connectorParameterEClass, ConnectorParameter.class, "ConnectorParameter", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConnectorParameter_Key(), ecorePackage.getEString(), "key", null, 1, 1, ConnectorParameter.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConnectorParameter_Expression(), theExpressionPackage.getAbstractExpression(), null, "expression", //$NON-NLS-1$
                null, 0, 1, ConnectorParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

} //ConnectorConfigurationPackageImpl
