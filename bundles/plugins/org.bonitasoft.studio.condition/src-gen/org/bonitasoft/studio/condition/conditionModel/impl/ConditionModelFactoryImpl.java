/**
 */
package org.bonitasoft.studio.condition.conditionModel.impl;

import org.bonitasoft.studio.condition.conditionModel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConditionModelFactoryImpl extends EFactoryImpl implements ConditionModelFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConditionModelFactory init()
  {
    try
    {
      ConditionModelFactory theConditionModelFactory = (ConditionModelFactory)EPackage.Registry.INSTANCE.getEFactory(ConditionModelPackage.eNS_URI);
      if (theConditionModelFactory != null)
      {
        return theConditionModelFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ConditionModelFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionModelFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ConditionModelPackage.OPERATION_COMPARE: return createOperation_Compare();
      case ConditionModelPackage.UNARY_OPERATION: return createUnary_Operation();
      case ConditionModelPackage.OPERATION: return createOperation();
      case ConditionModelPackage.EXPRESSION: return createExpression();
      case ConditionModelPackage.EXPRESSION_DOUBLE: return createExpression_Double();
      case ConditionModelPackage.EXPRESSION_INTEGER: return createExpression_Integer();
      case ConditionModelPackage.EXPRESSION_STRING: return createExpression_String();
      case ConditionModelPackage.EXPRESSION_PROCESS_REF: return createExpression_ProcessRef();
      case ConditionModelPackage.EXPRESSION_BOOLEAN: return createExpression_Boolean();
      case ConditionModelPackage.OPERATION_LESS_EQUALS: return createOperation_Less_Equals();
      case ConditionModelPackage.OPERATION_LESS: return createOperation_Less();
      case ConditionModelPackage.OPERATION_GREATER_EQUALS: return createOperation_Greater_Equals();
      case ConditionModelPackage.OPERATION_GREATER: return createOperation_Greater();
      case ConditionModelPackage.OPERATION_NOT_EQUALS: return createOperation_Not_Equals();
      case ConditionModelPackage.OPERATION_EQUALS: return createOperation_Equals();
      case ConditionModelPackage.OPERATION_UNARY: return createOperation_Unary();
      case ConditionModelPackage.OPERATION_NOT_UNARY: return createOperation_NotUnary();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation_Compare createOperation_Compare()
  {
    Operation_CompareImpl operation_Compare = new Operation_CompareImpl();
    return operation_Compare;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Unary_Operation createUnary_Operation()
  {
    Unary_OperationImpl unary_Operation = new Unary_OperationImpl();
    return unary_Operation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation createOperation()
  {
    OperationImpl operation = new OperationImpl();
    return operation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression createExpression()
  {
    ExpressionImpl expression = new ExpressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression_Double createExpression_Double()
  {
    Expression_DoubleImpl expression_Double = new Expression_DoubleImpl();
    return expression_Double;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression_Integer createExpression_Integer()
  {
    Expression_IntegerImpl expression_Integer = new Expression_IntegerImpl();
    return expression_Integer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression_String createExpression_String()
  {
    Expression_StringImpl expression_String = new Expression_StringImpl();
    return expression_String;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression_ProcessRef createExpression_ProcessRef()
  {
    Expression_ProcessRefImpl expression_ProcessRef = new Expression_ProcessRefImpl();
    return expression_ProcessRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression_Boolean createExpression_Boolean()
  {
    Expression_BooleanImpl expression_Boolean = new Expression_BooleanImpl();
    return expression_Boolean;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation_Less_Equals createOperation_Less_Equals()
  {
    Operation_Less_EqualsImpl operation_Less_Equals = new Operation_Less_EqualsImpl();
    return operation_Less_Equals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation_Less createOperation_Less()
  {
    Operation_LessImpl operation_Less = new Operation_LessImpl();
    return operation_Less;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation_Greater_Equals createOperation_Greater_Equals()
  {
    Operation_Greater_EqualsImpl operation_Greater_Equals = new Operation_Greater_EqualsImpl();
    return operation_Greater_Equals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation_Greater createOperation_Greater()
  {
    Operation_GreaterImpl operation_Greater = new Operation_GreaterImpl();
    return operation_Greater;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation_Not_Equals createOperation_Not_Equals()
  {
    Operation_Not_EqualsImpl operation_Not_Equals = new Operation_Not_EqualsImpl();
    return operation_Not_Equals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation_Equals createOperation_Equals()
  {
    Operation_EqualsImpl operation_Equals = new Operation_EqualsImpl();
    return operation_Equals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation_Unary createOperation_Unary()
  {
    Operation_UnaryImpl operation_Unary = new Operation_UnaryImpl();
    return operation_Unary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operation_NotUnary createOperation_NotUnary()
  {
    Operation_NotUnaryImpl operation_NotUnary = new Operation_NotUnaryImpl();
    return operation_NotUnary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionModelPackage getConditionModelPackage()
  {
    return (ConditionModelPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ConditionModelPackage getPackage()
  {
    return ConditionModelPackage.eINSTANCE;
  }

} //ConditionModelFactoryImpl
