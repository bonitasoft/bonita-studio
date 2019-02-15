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
package org.bonitasoft.studio.model.expression.impl;

import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;

import org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl;

import org.bonitasoft.studio.model.configuration.ConfigurationPackage;

import org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;

import org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl;

import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.expression.TableExpression;

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
 * @generated
 */
public class ExpressionPackageImpl extends EPackageImpl implements ExpressionPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass abstractExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass expressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass listExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass tableExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass operationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass operatorEClass = null;

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
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private ExpressionPackageImpl() {
        super(eNS_URI, ExpressionFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ExpressionPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static ExpressionPackage init() {
        if (isInited) return (ExpressionPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredExpressionPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        ExpressionPackageImpl theExpressionPackage = registeredExpressionPackage instanceof ExpressionPackageImpl ? (ExpressionPackageImpl)registeredExpressionPackage : new ExpressionPackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ActorMappingPackage.eNS_URI);
        ActorMappingPackageImpl theActorMappingPackage = (ActorMappingPackageImpl)(registeredPackage instanceof ActorMappingPackageImpl ? registeredPackage : ActorMappingPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI);
        ConfigurationPackageImpl theConfigurationPackage = (ConfigurationPackageImpl)(registeredPackage instanceof ConfigurationPackageImpl ? registeredPackage : ConfigurationPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConnectorConfigurationPackage.eNS_URI);
        ConnectorConfigurationPackageImpl theConnectorConfigurationPackage = (ConnectorConfigurationPackageImpl)(registeredPackage instanceof ConnectorConfigurationPackageImpl ? registeredPackage : ConnectorConfigurationPackage.eINSTANCE);
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
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI);
        SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl)(registeredPackage instanceof SimulationPackageImpl ? registeredPackage : SimulationPackage.eINSTANCE);

        // Create package meta-data objects
        theExpressionPackage.createPackageContents();
        theActorMappingPackage.createPackageContents();
        theConfigurationPackage.createPackageContents();
        theConnectorConfigurationPackage.createPackageContents();
        theKpiPackage.createPackageContents();
        theParameterPackage.createPackageContents();
        theProcessPackage.createPackageContents();
        theDecisionPackage.createPackageContents();
        theTransitionsPackage.createPackageContents();
        theFormPackage.createPackageContents();
        theSimulationPackage.createPackageContents();

        // Initialize created meta-data
        theExpressionPackage.initializePackageContents();
        theActorMappingPackage.initializePackageContents();
        theConfigurationPackage.initializePackageContents();
        theConnectorConfigurationPackage.initializePackageContents();
        theKpiPackage.initializePackageContents();
        theParameterPackage.initializePackageContents();
        theProcessPackage.initializePackageContents();
        theDecisionPackage.initializePackageContents();
        theTransitionsPackage.initializePackageContents();
        theFormPackage.initializePackageContents();
        theSimulationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theExpressionPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ExpressionPackage.eNS_URI, theExpressionPackage);
        return theExpressionPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getAbstractExpression() {
        return abstractExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getExpression() {
        return expressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getExpression_Name() {
        return (EAttribute)expressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getExpression_Content() {
        return (EAttribute)expressionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getExpression_Interpreter() {
        return (EAttribute)expressionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getExpression_Type() {
        return (EAttribute)expressionEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getExpression_ReturnType() {
        return (EAttribute)expressionEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getExpression_ReferencedElements() {
        return (EReference)expressionEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getExpression_Connectors() {
        return (EReference)expressionEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getExpression_PropagateVariableChange() {
        return (EAttribute)expressionEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getExpression_ReturnTypeFixed() {
        return (EAttribute)expressionEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getExpression_AutomaticDependencies() {
        return (EAttribute)expressionEClass.getEStructuralFeatures().get(9);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getExpression_HtmlAllowed() {
        return (EAttribute)expressionEClass.getEStructuralFeatures().get(10);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getListExpression() {
        return listExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getListExpression_Expressions() {
        return (EReference)listExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getTableExpression() {
        return tableExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getTableExpression_Expressions() {
        return (EReference)tableExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getOperation() {
        return operationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getOperation_LeftOperand() {
        return (EReference)operationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getOperation_RightOperand() {
        return (EReference)operationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getOperation_Operator() {
        return (EReference)operationEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getOperator() {
        return operatorEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getOperator_Type() {
        return (EAttribute)operatorEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getOperator_Expression() {
        return (EAttribute)operatorEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getOperator_InputTypes() {
        return (EAttribute)operatorEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ExpressionFactory getExpressionFactory() {
        return (ExpressionFactory)getEFactoryInstance();
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
        abstractExpressionEClass = createEClass(ABSTRACT_EXPRESSION);

        expressionEClass = createEClass(EXPRESSION);
        createEAttribute(expressionEClass, EXPRESSION__NAME);
        createEAttribute(expressionEClass, EXPRESSION__CONTENT);
        createEAttribute(expressionEClass, EXPRESSION__INTERPRETER);
        createEAttribute(expressionEClass, EXPRESSION__TYPE);
        createEAttribute(expressionEClass, EXPRESSION__RETURN_TYPE);
        createEReference(expressionEClass, EXPRESSION__REFERENCED_ELEMENTS);
        createEReference(expressionEClass, EXPRESSION__CONNECTORS);
        createEAttribute(expressionEClass, EXPRESSION__PROPAGATE_VARIABLE_CHANGE);
        createEAttribute(expressionEClass, EXPRESSION__RETURN_TYPE_FIXED);
        createEAttribute(expressionEClass, EXPRESSION__AUTOMATIC_DEPENDENCIES);
        createEAttribute(expressionEClass, EXPRESSION__HTML_ALLOWED);

        listExpressionEClass = createEClass(LIST_EXPRESSION);
        createEReference(listExpressionEClass, LIST_EXPRESSION__EXPRESSIONS);

        tableExpressionEClass = createEClass(TABLE_EXPRESSION);
        createEReference(tableExpressionEClass, TABLE_EXPRESSION__EXPRESSIONS);

        operationEClass = createEClass(OPERATION);
        createEReference(operationEClass, OPERATION__LEFT_OPERAND);
        createEReference(operationEClass, OPERATION__RIGHT_OPERAND);
        createEReference(operationEClass, OPERATION__OPERATOR);

        operatorEClass = createEClass(OPERATOR);
        createEAttribute(operatorEClass, OPERATOR__TYPE);
        createEAttribute(operatorEClass, OPERATOR__EXPRESSION);
        createEAttribute(operatorEClass, OPERATOR__INPUT_TYPES);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        expressionEClass.getESuperTypes().add(this.getAbstractExpression());
        listExpressionEClass.getESuperTypes().add(this.getAbstractExpression());
        tableExpressionEClass.getESuperTypes().add(this.getAbstractExpression());

        // Initialize classes and features; add operations and parameters
        initEClass(abstractExpressionEClass, AbstractExpression.class, "AbstractExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(expressionEClass, Expression.class, "Expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getExpression_Name(), ecorePackage.getEString(), "name", null, 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getExpression_Content(), ecorePackage.getEString(), "content", null, 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getExpression_Interpreter(), ecorePackage.getEString(), "interpreter", "", 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getExpression_Type(), ecorePackage.getEString(), "type", "TYPE_CONSTANT", 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getExpression_ReturnType(), ecorePackage.getEString(), "returnType", "java.lang.String", 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(getExpression_ReferencedElements(), ecorePackage.getEObject(), null, "referencedElements", null, 0, -1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getExpression_Connectors(), theProcessPackage.getConnector(), null, "connectors", null, 0, -1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getExpression_PropagateVariableChange(), ecorePackage.getEBooleanObject(), "propagateVariableChange", "false", 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getExpression_ReturnTypeFixed(), ecorePackage.getEBoolean(), "returnTypeFixed", "false", 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getExpression_AutomaticDependencies(), ecorePackage.getEBoolean(), "automaticDependencies", "true", 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getExpression_HtmlAllowed(), ecorePackage.getEBoolean(), "htmlAllowed", "false", 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        addEOperation(expressionEClass, ecorePackage.getEBoolean(), "hasContent", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

        addEOperation(expressionEClass, ecorePackage.getEBoolean(), "hasName", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

        initEClass(listExpressionEClass, ListExpression.class, "ListExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getListExpression_Expressions(), this.getExpression(), null, "expressions", null, 0, -1, ListExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(tableExpressionEClass, TableExpression.class, "TableExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getTableExpression_Expressions(), this.getListExpression(), null, "expressions", null, 0, -1, TableExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getOperation_LeftOperand(), this.getExpression(), null, "leftOperand", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getOperation_RightOperand(), this.getExpression(), null, "rightOperand", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getOperation_Operator(), this.getOperator(), null, "operator", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(operatorEClass, Operator.class, "Operator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getOperator_Type(), ecorePackage.getEString(), "type", null, 1, 1, Operator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getOperator_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, Operator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getOperator_InputTypes(), ecorePackage.getEString(), "inputTypes", null, 0, -1, Operator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

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

} //ExpressionPackageImpl
