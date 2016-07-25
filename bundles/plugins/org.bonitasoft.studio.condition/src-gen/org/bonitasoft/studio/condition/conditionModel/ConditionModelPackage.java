/**
 */
package org.bonitasoft.studio.condition.conditionModel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelFactory
 * @model kind="package"
 * @generated
 */
public interface ConditionModelPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "conditionModel";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.bonitasoft.org/studio/condition/ConditionModel";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "conditionModel";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ConditionModelPackage eINSTANCE = org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl.init();

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_CompareImpl <em>Operation Compare</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_CompareImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Compare()
   * @generated
   */
  int OPERATION_COMPARE = 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_COMPARE__OP = 0;

  /**
   * The number of structural features of the '<em>Operation Compare</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_COMPARE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Unary_OperationImpl <em>Unary Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Unary_OperationImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getUnary_Operation()
   * @generated
   */
  int UNARY_OPERATION = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_OPERATION__VALUE = 0;

  /**
   * The number of structural features of the '<em>Unary Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_OPERATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.OperationImpl <em>Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.OperationImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation()
   * @generated
   */
  int OPERATION = 2;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION__LEFT = 0;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION__RIGHT = 1;

  /**
   * The number of structural features of the '<em>Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ExpressionImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 3;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_DoubleImpl <em>Expression Double</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_DoubleImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_Double()
   * @generated
   */
  int EXPRESSION_DOUBLE = 4;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_DOUBLE__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expression Double</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_DOUBLE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_IntegerImpl <em>Expression Integer</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_IntegerImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_Integer()
   * @generated
   */
  int EXPRESSION_INTEGER = 5;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_INTEGER__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expression Integer</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_INTEGER_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_StringImpl <em>Expression String</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_StringImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_String()
   * @generated
   */
  int EXPRESSION_STRING = 6;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_STRING__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expression String</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_STRING_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_ProcessRefImpl <em>Expression Process Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_ProcessRefImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_ProcessRef()
   * @generated
   */
  int EXPRESSION_PROCESS_REF = 7;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_PROCESS_REF__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expression Process Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_PROCESS_REF_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_BooleanImpl <em>Expression Boolean</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_BooleanImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_Boolean()
   * @generated
   */
  int EXPRESSION_BOOLEAN = 8;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_BOOLEAN__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expression Boolean</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_BOOLEAN_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_Less_EqualsImpl <em>Operation Less Equals</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_Less_EqualsImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Less_Equals()
   * @generated
   */
  int OPERATION_LESS_EQUALS = 9;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_LESS_EQUALS__LEFT = OPERATION__LEFT;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_LESS_EQUALS__RIGHT = OPERATION__RIGHT;

  /**
   * The number of structural features of the '<em>Operation Less Equals</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_LESS_EQUALS_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_LessImpl <em>Operation Less</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_LessImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Less()
   * @generated
   */
  int OPERATION_LESS = 10;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_LESS__LEFT = OPERATION__LEFT;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_LESS__RIGHT = OPERATION__RIGHT;

  /**
   * The number of structural features of the '<em>Operation Less</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_LESS_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_Greater_EqualsImpl <em>Operation Greater Equals</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_Greater_EqualsImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Greater_Equals()
   * @generated
   */
  int OPERATION_GREATER_EQUALS = 11;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_GREATER_EQUALS__LEFT = OPERATION__LEFT;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_GREATER_EQUALS__RIGHT = OPERATION__RIGHT;

  /**
   * The number of structural features of the '<em>Operation Greater Equals</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_GREATER_EQUALS_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_GreaterImpl <em>Operation Greater</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_GreaterImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Greater()
   * @generated
   */
  int OPERATION_GREATER = 12;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_GREATER__LEFT = OPERATION__LEFT;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_GREATER__RIGHT = OPERATION__RIGHT;

  /**
   * The number of structural features of the '<em>Operation Greater</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_GREATER_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_Not_EqualsImpl <em>Operation Not Equals</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_Not_EqualsImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Not_Equals()
   * @generated
   */
  int OPERATION_NOT_EQUALS = 13;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_NOT_EQUALS__LEFT = OPERATION__LEFT;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_NOT_EQUALS__RIGHT = OPERATION__RIGHT;

  /**
   * The number of structural features of the '<em>Operation Not Equals</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_NOT_EQUALS_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_EqualsImpl <em>Operation Equals</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_EqualsImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Equals()
   * @generated
   */
  int OPERATION_EQUALS = 14;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_EQUALS__LEFT = OPERATION__LEFT;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_EQUALS__RIGHT = OPERATION__RIGHT;

  /**
   * The number of structural features of the '<em>Operation Equals</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_EQUALS_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_UnaryImpl <em>Operation Unary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_UnaryImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Unary()
   * @generated
   */
  int OPERATION_UNARY = 15;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_UNARY__VALUE = UNARY_OPERATION__VALUE;

  /**
   * The number of structural features of the '<em>Operation Unary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_UNARY_FEATURE_COUNT = UNARY_OPERATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_NotUnaryImpl <em>Operation Not Unary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_NotUnaryImpl
   * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_NotUnary()
   * @generated
   */
  int OPERATION_NOT_UNARY = 16;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_NOT_UNARY__VALUE = UNARY_OPERATION__VALUE;

  /**
   * The number of structural features of the '<em>Operation Not Unary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_NOT_UNARY_FEATURE_COUNT = UNARY_OPERATION_FEATURE_COUNT + 0;


  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Compare <em>Operation Compare</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Compare</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Compare
   * @generated
   */
  EClass getOperation_Compare();

  /**
   * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Compare#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Compare#getOp()
   * @see #getOperation_Compare()
   * @generated
   */
  EReference getOperation_Compare_Op();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Unary_Operation <em>Unary Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unary Operation</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Unary_Operation
   * @generated
   */
  EClass getUnary_Operation();

  /**
   * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.condition.conditionModel.Unary_Operation#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Unary_Operation#getValue()
   * @see #getUnary_Operation()
   * @generated
   */
  EReference getUnary_Operation_Value();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation
   * @generated
   */
  EClass getOperation();

  /**
   * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.condition.conditionModel.Operation#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation#getLeft()
   * @see #getOperation()
   * @generated
   */
  EReference getOperation_Left();

  /**
   * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.condition.conditionModel.Operation#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation#getRight()
   * @see #getOperation()
   * @generated
   */
  EReference getOperation_Right();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_Double <em>Expression Double</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression Double</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_Double
   * @generated
   */
  EClass getExpression_Double();

  /**
   * Returns the meta object for the attribute '{@link org.bonitasoft.studio.condition.conditionModel.Expression_Double#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_Double#getValue()
   * @see #getExpression_Double()
   * @generated
   */
  EAttribute getExpression_Double_Value();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_Integer <em>Expression Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression Integer</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_Integer
   * @generated
   */
  EClass getExpression_Integer();

  /**
   * Returns the meta object for the attribute '{@link org.bonitasoft.studio.condition.conditionModel.Expression_Integer#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_Integer#getValue()
   * @see #getExpression_Integer()
   * @generated
   */
  EAttribute getExpression_Integer_Value();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_String <em>Expression String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression String</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_String
   * @generated
   */
  EClass getExpression_String();

  /**
   * Returns the meta object for the attribute '{@link org.bonitasoft.studio.condition.conditionModel.Expression_String#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_String#getValue()
   * @see #getExpression_String()
   * @generated
   */
  EAttribute getExpression_String_Value();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef <em>Expression Process Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression Process Ref</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef
   * @generated
   */
  EClass getExpression_ProcessRef();

  /**
   * Returns the meta object for the reference '{@link org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef#getValue()
   * @see #getExpression_ProcessRef()
   * @generated
   */
  EReference getExpression_ProcessRef_Value();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_Boolean <em>Expression Boolean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression Boolean</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_Boolean
   * @generated
   */
  EClass getExpression_Boolean();

  /**
   * Returns the meta object for the attribute '{@link org.bonitasoft.studio.condition.conditionModel.Expression_Boolean#isValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_Boolean#isValue()
   * @see #getExpression_Boolean()
   * @generated
   */
  EAttribute getExpression_Boolean_Value();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Less_Equals <em>Operation Less Equals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Less Equals</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Less_Equals
   * @generated
   */
  EClass getOperation_Less_Equals();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Less <em>Operation Less</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Less</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Less
   * @generated
   */
  EClass getOperation_Less();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Greater_Equals <em>Operation Greater Equals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Greater Equals</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Greater_Equals
   * @generated
   */
  EClass getOperation_Greater_Equals();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Greater <em>Operation Greater</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Greater</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Greater
   * @generated
   */
  EClass getOperation_Greater();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Not_Equals <em>Operation Not Equals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Not Equals</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Not_Equals
   * @generated
   */
  EClass getOperation_Not_Equals();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Equals <em>Operation Equals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Equals</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Equals
   * @generated
   */
  EClass getOperation_Equals();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Unary <em>Operation Unary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Unary</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Unary
   * @generated
   */
  EClass getOperation_Unary();

  /**
   * Returns the meta object for class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary <em>Operation Not Unary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Not Unary</em>'.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary
   * @generated
   */
  EClass getOperation_NotUnary();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ConditionModelFactory getConditionModelFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_CompareImpl <em>Operation Compare</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_CompareImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Compare()
     * @generated
     */
    EClass OPERATION_COMPARE = eINSTANCE.getOperation_Compare();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION_COMPARE__OP = eINSTANCE.getOperation_Compare_Op();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Unary_OperationImpl <em>Unary Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Unary_OperationImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getUnary_Operation()
     * @generated
     */
    EClass UNARY_OPERATION = eINSTANCE.getUnary_Operation();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_OPERATION__VALUE = eINSTANCE.getUnary_Operation_Value();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.OperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.OperationImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation()
     * @generated
     */
    EClass OPERATION = eINSTANCE.getOperation();

    /**
     * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION__LEFT = eINSTANCE.getOperation_Left();

    /**
     * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION__RIGHT = eINSTANCE.getOperation_Right();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ExpressionImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_DoubleImpl <em>Expression Double</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_DoubleImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_Double()
     * @generated
     */
    EClass EXPRESSION_DOUBLE = eINSTANCE.getExpression_Double();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION_DOUBLE__VALUE = eINSTANCE.getExpression_Double_Value();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_IntegerImpl <em>Expression Integer</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_IntegerImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_Integer()
     * @generated
     */
    EClass EXPRESSION_INTEGER = eINSTANCE.getExpression_Integer();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION_INTEGER__VALUE = eINSTANCE.getExpression_Integer_Value();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_StringImpl <em>Expression String</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_StringImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_String()
     * @generated
     */
    EClass EXPRESSION_STRING = eINSTANCE.getExpression_String();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION_STRING__VALUE = eINSTANCE.getExpression_String_Value();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_ProcessRefImpl <em>Expression Process Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_ProcessRefImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_ProcessRef()
     * @generated
     */
    EClass EXPRESSION_PROCESS_REF = eINSTANCE.getExpression_ProcessRef();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_PROCESS_REF__VALUE = eINSTANCE.getExpression_ProcessRef_Value();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Expression_BooleanImpl <em>Expression Boolean</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Expression_BooleanImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getExpression_Boolean()
     * @generated
     */
    EClass EXPRESSION_BOOLEAN = eINSTANCE.getExpression_Boolean();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION_BOOLEAN__VALUE = eINSTANCE.getExpression_Boolean_Value();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_Less_EqualsImpl <em>Operation Less Equals</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_Less_EqualsImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Less_Equals()
     * @generated
     */
    EClass OPERATION_LESS_EQUALS = eINSTANCE.getOperation_Less_Equals();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_LessImpl <em>Operation Less</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_LessImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Less()
     * @generated
     */
    EClass OPERATION_LESS = eINSTANCE.getOperation_Less();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_Greater_EqualsImpl <em>Operation Greater Equals</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_Greater_EqualsImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Greater_Equals()
     * @generated
     */
    EClass OPERATION_GREATER_EQUALS = eINSTANCE.getOperation_Greater_Equals();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_GreaterImpl <em>Operation Greater</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_GreaterImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Greater()
     * @generated
     */
    EClass OPERATION_GREATER = eINSTANCE.getOperation_Greater();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_Not_EqualsImpl <em>Operation Not Equals</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_Not_EqualsImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Not_Equals()
     * @generated
     */
    EClass OPERATION_NOT_EQUALS = eINSTANCE.getOperation_Not_Equals();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_EqualsImpl <em>Operation Equals</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_EqualsImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Equals()
     * @generated
     */
    EClass OPERATION_EQUALS = eINSTANCE.getOperation_Equals();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_UnaryImpl <em>Operation Unary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_UnaryImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_Unary()
     * @generated
     */
    EClass OPERATION_UNARY = eINSTANCE.getOperation_Unary();

    /**
     * The meta object literal for the '{@link org.bonitasoft.studio.condition.conditionModel.impl.Operation_NotUnaryImpl <em>Operation Not Unary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.condition.conditionModel.impl.Operation_NotUnaryImpl
     * @see org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelPackageImpl#getOperation_NotUnary()
     * @generated
     */
    EClass OPERATION_NOT_UNARY = eINSTANCE.getOperation_NotUnary();

  }

} //ConditionModelPackage
