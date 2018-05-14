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
package org.bonitasoft.studio.model.process.decision.impl;

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

import org.bonitasoft.studio.model.process.decision.DecisionFactory;
import org.bonitasoft.studio.model.process.decision.DecisionPackage;
import org.bonitasoft.studio.model.process.decision.DecisionTable;
import org.bonitasoft.studio.model.process.decision.DecisionTableAction;
import org.bonitasoft.studio.model.process.decision.DecisionTableLine;

import org.bonitasoft.studio.model.process.decision.transitions.TransitionsPackage;

import org.bonitasoft.studio.model.process.decision.transitions.impl.TransitionsPackageImpl;

import org.bonitasoft.studio.model.process.impl.ProcessPackageImpl;

import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl;

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
public class DecisionPackageImpl extends EPackageImpl implements DecisionPackage {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass decisionTableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass decisionTableLineEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass decisionTableActionEClass = null;

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
     * @see org.bonitasoft.studio.model.process.decision.DecisionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DecisionPackageImpl() {
        super(eNS_URI, DecisionFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link DecisionPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DecisionPackage init() {
        if (isInited)
            return (DecisionPackage) EPackage.Registry.INSTANCE.getEPackage(DecisionPackage.eNS_URI);

        // Obtain or create and register package
        DecisionPackageImpl theDecisionPackage = (DecisionPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof DecisionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new DecisionPackageImpl());

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
        ParameterPackageImpl theParameterPackage = (ParameterPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ParameterPackage.eNS_URI) instanceof ParameterPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI) : ParameterPackage.eINSTANCE);
        ProcessPackageImpl theProcessPackage = (ProcessPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(ProcessPackage.eNS_URI) instanceof ProcessPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI) : ProcessPackage.eINSTANCE);
        TransitionsPackageImpl theTransitionsPackage = (TransitionsPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(TransitionsPackage.eNS_URI) instanceof TransitionsPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(TransitionsPackage.eNS_URI) : TransitionsPackage.eINSTANCE);
        SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(SimulationPackage.eNS_URI) instanceof SimulationPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI) : SimulationPackage.eINSTANCE);

        // Create package meta-data objects
        theDecisionPackage.createPackageContents();
        theActorMappingPackage.createPackageContents();
        theConfigurationPackage.createPackageContents();
        theConnectorConfigurationPackage.createPackageContents();
        theExpressionPackage.createPackageContents();
        theFormPackage.createPackageContents();
        theKpiPackage.createPackageContents();
        theParameterPackage.createPackageContents();
        theProcessPackage.createPackageContents();
        theTransitionsPackage.createPackageContents();
        theSimulationPackage.createPackageContents();

        // Initialize created meta-data
        theDecisionPackage.initializePackageContents();
        theActorMappingPackage.initializePackageContents();
        theConfigurationPackage.initializePackageContents();
        theConnectorConfigurationPackage.initializePackageContents();
        theExpressionPackage.initializePackageContents();
        theFormPackage.initializePackageContents();
        theKpiPackage.initializePackageContents();
        theParameterPackage.initializePackageContents();
        theProcessPackage.initializePackageContents();
        theTransitionsPackage.initializePackageContents();
        theSimulationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theDecisionPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DecisionPackage.eNS_URI, theDecisionPackage);
        return theDecisionPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDecisionTable() {
        return decisionTableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDecisionTable_Lines() {
        return (EReference) decisionTableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDecisionTable_DefaultAction() {
        return (EReference) decisionTableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDecisionTableLine() {
        return decisionTableLineEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDecisionTableLine_Conditions() {
        return (EReference) decisionTableLineEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDecisionTableLine_Action() {
        return (EReference) decisionTableLineEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDecisionTableAction() {
        return decisionTableActionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public DecisionFactory getDecisionFactory() {
        return (DecisionFactory) getEFactoryInstance();
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
        decisionTableEClass = createEClass(DECISION_TABLE);
        createEReference(decisionTableEClass, DECISION_TABLE__LINES);
        createEReference(decisionTableEClass, DECISION_TABLE__DEFAULT_ACTION);

        decisionTableLineEClass = createEClass(DECISION_TABLE_LINE);
        createEReference(decisionTableLineEClass, DECISION_TABLE_LINE__CONDITIONS);
        createEReference(decisionTableLineEClass, DECISION_TABLE_LINE__ACTION);

        decisionTableActionEClass = createEClass(DECISION_TABLE_ACTION);
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
        TransitionsPackage theTransitionsPackage = (TransitionsPackage) EPackage.Registry.INSTANCE
                .getEPackage(TransitionsPackage.eNS_URI);
        ExpressionPackage theExpressionPackage = (ExpressionPackage) EPackage.Registry.INSTANCE
                .getEPackage(ExpressionPackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theTransitionsPackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(decisionTableEClass, DecisionTable.class, "DecisionTable", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDecisionTable_Lines(), this.getDecisionTableLine(), null, "lines", null, 0, -1, //$NON-NLS-1$
                DecisionTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDecisionTable_DefaultAction(), this.getDecisionTableAction(), null, "defaultAction", null, 0, 1, //$NON-NLS-1$
                DecisionTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(decisionTableLineEClass, DecisionTableLine.class, "DecisionTableLine", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDecisionTableLine_Conditions(), theExpressionPackage.getExpression(), null, "conditions", null, 0, //$NON-NLS-1$
                -1, DecisionTableLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDecisionTableLine_Action(), this.getDecisionTableAction(), null, "action", null, 0, 1, //$NON-NLS-1$
                DecisionTableLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(decisionTableActionEClass, DecisionTableAction.class, "DecisionTableAction", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
    }

} //DecisionPackageImpl
