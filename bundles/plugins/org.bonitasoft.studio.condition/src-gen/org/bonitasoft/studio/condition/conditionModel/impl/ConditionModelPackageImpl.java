/**
 */
package org.bonitasoft.studio.condition.conditionModel.impl;

import org.bonitasoft.studio.condition.conditionModel.ConditionModelFactory;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression;
import org.bonitasoft.studio.condition.conditionModel.Expression_Boolean;
import org.bonitasoft.studio.condition.conditionModel.Expression_Double;
import org.bonitasoft.studio.condition.conditionModel.Expression_Integer;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Expression_String;
import org.bonitasoft.studio.condition.conditionModel.Operation;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.conditionModel.Operation_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_Greater;
import org.bonitasoft.studio.condition.conditionModel.Operation_Greater_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_Less;
import org.bonitasoft.studio.condition.conditionModel.Operation_Less_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary;
import org.bonitasoft.studio.condition.conditionModel.Operation_Not_Equals;
import org.bonitasoft.studio.condition.conditionModel.Operation_Unary;
import org.bonitasoft.studio.condition.conditionModel.Unary_Operation;

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
public class ConditionModelPackageImpl extends EPackageImpl implements ConditionModelPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operation_CompareEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass unary_OperationEClass = null;

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
  private EClass expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expression_DoubleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expression_IntegerEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expression_StringEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expression_ProcessRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expression_BooleanEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operation_Less_EqualsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operation_LessEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operation_Greater_EqualsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operation_GreaterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operation_Not_EqualsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operation_EqualsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operation_UnaryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operation_NotUnaryEClass = null;

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
   * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ConditionModelPackageImpl()
  {
    super(eNS_URI, ConditionModelFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ConditionModelPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ConditionModelPackage init()
  {
    if (isInited) return (ConditionModelPackage)EPackage.Registry.INSTANCE.getEPackage(ConditionModelPackage.eNS_URI);

    // Obtain or create and register package
    ConditionModelPackageImpl theConditionModelPackage = (ConditionModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ConditionModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ConditionModelPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theConditionModelPackage.createPackageContents();

    // Initialize created meta-data
    theConditionModelPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theConditionModelPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ConditionModelPackage.eNS_URI, theConditionModelPackage);
    return theConditionModelPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation_Compare()
  {
    return operation_CompareEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOperation_Compare_Op()
  {
    return (EReference)operation_CompareEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUnary_Operation()
  {
    return unary_OperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getUnary_Operation_Value()
  {
    return (EReference)unary_OperationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation()
  {
    return operationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOperation_Left()
  {
    return (EReference)operationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOperation_Right()
  {
    return (EReference)operationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpression()
  {
    return expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpression_Double()
  {
    return expression_DoubleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExpression_Double_Value()
  {
    return (EAttribute)expression_DoubleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpression_Integer()
  {
    return expression_IntegerEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExpression_Integer_Value()
  {
    return (EAttribute)expression_IntegerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpression_String()
  {
    return expression_StringEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExpression_String_Value()
  {
    return (EAttribute)expression_StringEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpression_ProcessRef()
  {
    return expression_ProcessRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExpression_ProcessRef_Value()
  {
    return (EReference)expression_ProcessRefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpression_Boolean()
  {
    return expression_BooleanEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExpression_Boolean_Value()
  {
    return (EAttribute)expression_BooleanEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation_Less_Equals()
  {
    return operation_Less_EqualsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation_Less()
  {
    return operation_LessEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation_Greater_Equals()
  {
    return operation_Greater_EqualsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation_Greater()
  {
    return operation_GreaterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation_Not_Equals()
  {
    return operation_Not_EqualsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation_Equals()
  {
    return operation_EqualsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation_Unary()
  {
    return operation_UnaryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperation_NotUnary()
  {
    return operation_NotUnaryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionModelFactory getConditionModelFactory()
  {
    return (ConditionModelFactory)getEFactoryInstance();
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
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    operation_CompareEClass = createEClass(OPERATION_COMPARE);
    createEReference(operation_CompareEClass, OPERATION_COMPARE__OP);

    unary_OperationEClass = createEClass(UNARY_OPERATION);
    createEReference(unary_OperationEClass, UNARY_OPERATION__VALUE);

    operationEClass = createEClass(OPERATION);
    createEReference(operationEClass, OPERATION__LEFT);
    createEReference(operationEClass, OPERATION__RIGHT);

    expressionEClass = createEClass(EXPRESSION);

    expression_DoubleEClass = createEClass(EXPRESSION_DOUBLE);
    createEAttribute(expression_DoubleEClass, EXPRESSION_DOUBLE__VALUE);

    expression_IntegerEClass = createEClass(EXPRESSION_INTEGER);
    createEAttribute(expression_IntegerEClass, EXPRESSION_INTEGER__VALUE);

    expression_StringEClass = createEClass(EXPRESSION_STRING);
    createEAttribute(expression_StringEClass, EXPRESSION_STRING__VALUE);

    expression_ProcessRefEClass = createEClass(EXPRESSION_PROCESS_REF);
    createEReference(expression_ProcessRefEClass, EXPRESSION_PROCESS_REF__VALUE);

    expression_BooleanEClass = createEClass(EXPRESSION_BOOLEAN);
    createEAttribute(expression_BooleanEClass, EXPRESSION_BOOLEAN__VALUE);

    operation_Less_EqualsEClass = createEClass(OPERATION_LESS_EQUALS);

    operation_LessEClass = createEClass(OPERATION_LESS);

    operation_Greater_EqualsEClass = createEClass(OPERATION_GREATER_EQUALS);

    operation_GreaterEClass = createEClass(OPERATION_GREATER);

    operation_Not_EqualsEClass = createEClass(OPERATION_NOT_EQUALS);

    operation_EqualsEClass = createEClass(OPERATION_EQUALS);

    operation_UnaryEClass = createEClass(OPERATION_UNARY);

    operation_NotUnaryEClass = createEClass(OPERATION_NOT_UNARY);
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
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    expression_DoubleEClass.getESuperTypes().add(this.getExpression());
    expression_IntegerEClass.getESuperTypes().add(this.getExpression());
    expression_StringEClass.getESuperTypes().add(this.getExpression());
    expression_ProcessRefEClass.getESuperTypes().add(this.getExpression());
    expression_BooleanEClass.getESuperTypes().add(this.getExpression());
    operation_Less_EqualsEClass.getESuperTypes().add(this.getOperation());
    operation_LessEClass.getESuperTypes().add(this.getOperation());
    operation_Greater_EqualsEClass.getESuperTypes().add(this.getOperation());
    operation_GreaterEClass.getESuperTypes().add(this.getOperation());
    operation_Not_EqualsEClass.getESuperTypes().add(this.getOperation());
    operation_EqualsEClass.getESuperTypes().add(this.getOperation());
    operation_UnaryEClass.getESuperTypes().add(this.getUnary_Operation());
    operation_NotUnaryEClass.getESuperTypes().add(this.getUnary_Operation());

    // Initialize classes and features; add operations and parameters
    initEClass(operation_CompareEClass, Operation_Compare.class, "Operation_Compare", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOperation_Compare_Op(), ecorePackage.getEObject(), null, "op", null, 0, 1, Operation_Compare.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(unary_OperationEClass, Unary_Operation.class, "Unary_Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getUnary_Operation_Value(), this.getExpression(), null, "value", null, 0, 1, Unary_Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOperation_Left(), this.getExpression(), null, "left", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOperation_Right(), this.getExpression(), null, "right", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(expressionEClass, Expression.class, "Expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(expression_DoubleEClass, Expression_Double.class, "Expression_Double", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExpression_Double_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, Expression_Double.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(expression_IntegerEClass, Expression_Integer.class, "Expression_Integer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExpression_Integer_Value(), ecorePackage.getELong(), "value", null, 0, 1, Expression_Integer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(expression_StringEClass, Expression_String.class, "Expression_String", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExpression_String_Value(), ecorePackage.getEString(), "value", null, 0, 1, Expression_String.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(expression_ProcessRefEClass, Expression_ProcessRef.class, "Expression_ProcessRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExpression_ProcessRef_Value(), ecorePackage.getEObject(), null, "value", null, 0, 1, Expression_ProcessRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(expression_BooleanEClass, Expression_Boolean.class, "Expression_Boolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExpression_Boolean_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, Expression_Boolean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(operation_Less_EqualsEClass, Operation_Less_Equals.class, "Operation_Less_Equals", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operation_LessEClass, Operation_Less.class, "Operation_Less", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operation_Greater_EqualsEClass, Operation_Greater_Equals.class, "Operation_Greater_Equals", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operation_GreaterEClass, Operation_Greater.class, "Operation_Greater", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operation_Not_EqualsEClass, Operation_Not_Equals.class, "Operation_Not_Equals", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operation_EqualsEClass, Operation_Equals.class, "Operation_Equals", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operation_UnaryEClass, Operation_Unary.class, "Operation_Unary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operation_NotUnaryEClass, Operation_NotUnary.class, "Operation_NotUnary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //ConditionModelPackageImpl
