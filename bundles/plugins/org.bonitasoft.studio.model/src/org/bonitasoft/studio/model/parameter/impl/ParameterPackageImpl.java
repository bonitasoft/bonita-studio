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
package org.bonitasoft.studio.model.parameter.impl;

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

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterContext;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.parameter.ParameterPackage;

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
public class ParameterPackageImpl extends EPackageImpl implements ParameterPackage {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass parameterEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass parameterContextEClass = null;

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
     * @see org.bonitasoft.studio.model.parameter.ParameterPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ParameterPackageImpl() {
        super(eNS_URI, ParameterFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ParameterPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ParameterPackage init() {
        if (isInited)
            return (ParameterPackage) EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI);

        // Obtain or create and register package
        ParameterPackageImpl theParameterPackage = (ParameterPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof ParameterPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new ParameterPackageImpl());

        isInited = true;

        // Obtain or create and register interdependencies
        ActorMappingPackageImpl theActorMappingPackage = (ActorMappingPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ActorMappingPackage.eNS_URI) instanceof ActorMappingPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ActorMappingPackage.eNS_URI)
                        : ActorMappingPackage.eINSTANCE);
        ConfigurationPackageImpl theConfigurationPackage = (ConfigurationPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ConfigurationPackage.eNS_URI) instanceof ConfigurationPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI)
                        : ConfigurationPackage.eINSTANCE);
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
        theParameterPackage.createPackageContents();
        theActorMappingPackage.createPackageContents();
        theConfigurationPackage.createPackageContents();
        theConnectorConfigurationPackage.createPackageContents();
        theExpressionPackage.createPackageContents();
        theFormPackage.createPackageContents();
        theKpiPackage.createPackageContents();
        theProcessPackage.createPackageContents();
        theDecisionPackage.createPackageContents();
        theTransitionsPackage.createPackageContents();
        theSimulationPackage.createPackageContents();

        // Initialize created meta-data
        theParameterPackage.initializePackageContents();
        theActorMappingPackage.initializePackageContents();
        theConfigurationPackage.initializePackageContents();
        theConnectorConfigurationPackage.initializePackageContents();
        theExpressionPackage.initializePackageContents();
        theFormPackage.initializePackageContents();
        theKpiPackage.initializePackageContents();
        theProcessPackage.initializePackageContents();
        theDecisionPackage.initializePackageContents();
        theTransitionsPackage.initializePackageContents();
        theSimulationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theParameterPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ParameterPackage.eNS_URI, theParameterPackage);
        return theParameterPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getParameter() {
        return parameterEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getParameter_Name() {
        return (EAttribute) parameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getParameter_Value() {
        return (EAttribute) parameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getParameter_TypeClassname() {
        return (EAttribute) parameterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getParameter_Description() {
        return (EAttribute) parameterEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getParameterContext() {
        return parameterContextEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getParameterContext_Name() {
        return (EAttribute) parameterContextEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getParameterContext_Description() {
        return (EAttribute) parameterContextEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getParameterContext_Parameters() {
        return (EReference) parameterContextEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getParameterContext_DefaultContext() {
        return (EAttribute) parameterContextEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public ParameterFactory getParameterFactory() {
        return (ParameterFactory) getEFactoryInstance();
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
        parameterEClass = createEClass(PARAMETER);
        createEAttribute(parameterEClass, PARAMETER__NAME);
        createEAttribute(parameterEClass, PARAMETER__VALUE);
        createEAttribute(parameterEClass, PARAMETER__TYPE_CLASSNAME);
        createEAttribute(parameterEClass, PARAMETER__DESCRIPTION);

        parameterContextEClass = createEClass(PARAMETER_CONTEXT);
        createEAttribute(parameterContextEClass, PARAMETER_CONTEXT__NAME);
        createEAttribute(parameterContextEClass, PARAMETER_CONTEXT__DESCRIPTION);
        createEReference(parameterContextEClass, PARAMETER_CONTEXT__PARAMETERS);
        createEAttribute(parameterContextEClass, PARAMETER_CONTEXT__DEFAULT_CONTEXT);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getParameter_Name(), ecorePackage.getEString(), "name", null, 0, 1, Parameter.class, !IS_TRANSIENT, //$NON-NLS-1$
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getParameter_Value(), ecorePackage.getEString(), "value", null, 0, 1, Parameter.class, !IS_TRANSIENT, //$NON-NLS-1$
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getParameter_TypeClassname(), ecorePackage.getEString(), "typeClassname", null, 0, 1, Parameter.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getParameter_Description(), ecorePackage.getEString(), "description", null, 0, 1, Parameter.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(parameterContextEClass, ParameterContext.class, "ParameterContext", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getParameterContext_Name(), ecorePackage.getEString(), "name", null, 0, 1, ParameterContext.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getParameterContext_Description(), ecorePackage.getEString(), "description", null, 0, 1, //$NON-NLS-1$
                ParameterContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getParameterContext_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, //$NON-NLS-1$
                ParameterContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getParameterContext_DefaultContext(), ecorePackage.getEBoolean(), "defaultContext", "false", 0, 1, //$NON-NLS-1$//$NON-NLS-2$
                ParameterContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

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

} //ParameterPackageImpl
