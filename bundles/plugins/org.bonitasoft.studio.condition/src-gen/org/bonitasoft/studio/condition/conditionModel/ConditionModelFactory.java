/**
 */
package org.bonitasoft.studio.condition.conditionModel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage
 * @generated
 */
public interface ConditionModelFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ConditionModelFactory eINSTANCE = org.bonitasoft.studio.condition.conditionModel.impl.ConditionModelFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Operation Compare</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Compare</em>'.
   * @generated
   */
  Operation_Compare createOperation_Compare();

  /**
   * Returns a new object of class '<em>Unary Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unary Operation</em>'.
   * @generated
   */
  Unary_Operation createUnary_Operation();

  /**
   * Returns a new object of class '<em>Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation</em>'.
   * @generated
   */
  Operation createOperation();

  /**
   * Returns a new object of class '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression</em>'.
   * @generated
   */
  Expression createExpression();

  /**
   * Returns a new object of class '<em>Expression Double</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression Double</em>'.
   * @generated
   */
  Expression_Double createExpression_Double();

  /**
   * Returns a new object of class '<em>Expression Integer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression Integer</em>'.
   * @generated
   */
  Expression_Integer createExpression_Integer();

  /**
   * Returns a new object of class '<em>Expression String</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression String</em>'.
   * @generated
   */
  Expression_String createExpression_String();

  /**
   * Returns a new object of class '<em>Expression Process Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression Process Ref</em>'.
   * @generated
   */
  Expression_ProcessRef createExpression_ProcessRef();

  /**
   * Returns a new object of class '<em>Expression Boolean</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression Boolean</em>'.
   * @generated
   */
  Expression_Boolean createExpression_Boolean();

  /**
   * Returns a new object of class '<em>Operation Less Equals</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Less Equals</em>'.
   * @generated
   */
  Operation_Less_Equals createOperation_Less_Equals();

  /**
   * Returns a new object of class '<em>Operation Less</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Less</em>'.
   * @generated
   */
  Operation_Less createOperation_Less();

  /**
   * Returns a new object of class '<em>Operation Greater Equals</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Greater Equals</em>'.
   * @generated
   */
  Operation_Greater_Equals createOperation_Greater_Equals();

  /**
   * Returns a new object of class '<em>Operation Greater</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Greater</em>'.
   * @generated
   */
  Operation_Greater createOperation_Greater();

  /**
   * Returns a new object of class '<em>Operation Not Equals</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Not Equals</em>'.
   * @generated
   */
  Operation_Not_Equals createOperation_Not_Equals();

  /**
   * Returns a new object of class '<em>Operation Equals</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Equals</em>'.
   * @generated
   */
  Operation_Equals createOperation_Equals();

  /**
   * Returns a new object of class '<em>Operation Unary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Unary</em>'.
   * @generated
   */
  Operation_Unary createOperation_Unary();

  /**
   * Returns a new object of class '<em>Operation Not Unary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Not Unary</em>'.
   * @generated
   */
  Operation_NotUnary createOperation_NotUnary();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ConditionModelPackage getConditionModelPackage();

} //ConditionModelFactory
