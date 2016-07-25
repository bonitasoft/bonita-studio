/**
 */
package org.bonitasoft.studio.condition.conditionModel.util;

import org.bonitasoft.studio.condition.conditionModel.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage
 * @generated
 */
public class ConditionModelSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ConditionModelPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionModelSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = ConditionModelPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case ConditionModelPackage.OPERATION_COMPARE:
      {
        Operation_Compare operation_Compare = (Operation_Compare)theEObject;
        T result = caseOperation_Compare(operation_Compare);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.UNARY_OPERATION:
      {
        Unary_Operation unary_Operation = (Unary_Operation)theEObject;
        T result = caseUnary_Operation(unary_Operation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.OPERATION:
      {
        Operation operation = (Operation)theEObject;
        T result = caseOperation(operation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.EXPRESSION:
      {
        Expression expression = (Expression)theEObject;
        T result = caseExpression(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.EXPRESSION_DOUBLE:
      {
        Expression_Double expression_Double = (Expression_Double)theEObject;
        T result = caseExpression_Double(expression_Double);
        if (result == null) result = caseExpression(expression_Double);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.EXPRESSION_INTEGER:
      {
        Expression_Integer expression_Integer = (Expression_Integer)theEObject;
        T result = caseExpression_Integer(expression_Integer);
        if (result == null) result = caseExpression(expression_Integer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.EXPRESSION_STRING:
      {
        Expression_String expression_String = (Expression_String)theEObject;
        T result = caseExpression_String(expression_String);
        if (result == null) result = caseExpression(expression_String);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.EXPRESSION_PROCESS_REF:
      {
        Expression_ProcessRef expression_ProcessRef = (Expression_ProcessRef)theEObject;
        T result = caseExpression_ProcessRef(expression_ProcessRef);
        if (result == null) result = caseExpression(expression_ProcessRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.EXPRESSION_BOOLEAN:
      {
        Expression_Boolean expression_Boolean = (Expression_Boolean)theEObject;
        T result = caseExpression_Boolean(expression_Boolean);
        if (result == null) result = caseExpression(expression_Boolean);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.OPERATION_LESS_EQUALS:
      {
        Operation_Less_Equals operation_Less_Equals = (Operation_Less_Equals)theEObject;
        T result = caseOperation_Less_Equals(operation_Less_Equals);
        if (result == null) result = caseOperation(operation_Less_Equals);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.OPERATION_LESS:
      {
        Operation_Less operation_Less = (Operation_Less)theEObject;
        T result = caseOperation_Less(operation_Less);
        if (result == null) result = caseOperation(operation_Less);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.OPERATION_GREATER_EQUALS:
      {
        Operation_Greater_Equals operation_Greater_Equals = (Operation_Greater_Equals)theEObject;
        T result = caseOperation_Greater_Equals(operation_Greater_Equals);
        if (result == null) result = caseOperation(operation_Greater_Equals);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.OPERATION_GREATER:
      {
        Operation_Greater operation_Greater = (Operation_Greater)theEObject;
        T result = caseOperation_Greater(operation_Greater);
        if (result == null) result = caseOperation(operation_Greater);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.OPERATION_NOT_EQUALS:
      {
        Operation_Not_Equals operation_Not_Equals = (Operation_Not_Equals)theEObject;
        T result = caseOperation_Not_Equals(operation_Not_Equals);
        if (result == null) result = caseOperation(operation_Not_Equals);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.OPERATION_EQUALS:
      {
        Operation_Equals operation_Equals = (Operation_Equals)theEObject;
        T result = caseOperation_Equals(operation_Equals);
        if (result == null) result = caseOperation(operation_Equals);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.OPERATION_UNARY:
      {
        Operation_Unary operation_Unary = (Operation_Unary)theEObject;
        T result = caseOperation_Unary(operation_Unary);
        if (result == null) result = caseUnary_Operation(operation_Unary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ConditionModelPackage.OPERATION_NOT_UNARY:
      {
        Operation_NotUnary operation_NotUnary = (Operation_NotUnary)theEObject;
        T result = caseOperation_NotUnary(operation_NotUnary);
        if (result == null) result = caseUnary_Operation(operation_NotUnary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Compare</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Compare</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation_Compare(Operation_Compare object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unary Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unary Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnary_Operation(Unary_Operation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation(Operation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression(Expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression Double</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression Double</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression_Double(Expression_Double object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression Integer</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression Integer</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression_Integer(Expression_Integer object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression String</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression String</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression_String(Expression_String object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression Process Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression Process Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression_ProcessRef(Expression_ProcessRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression Boolean</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression Boolean</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression_Boolean(Expression_Boolean object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Less Equals</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Less Equals</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation_Less_Equals(Operation_Less_Equals object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Less</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Less</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation_Less(Operation_Less object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Greater Equals</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Greater Equals</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation_Greater_Equals(Operation_Greater_Equals object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Greater</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Greater</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation_Greater(Operation_Greater object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Not Equals</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Not Equals</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation_Not_Equals(Operation_Not_Equals object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Equals</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Equals</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation_Equals(Operation_Equals object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Unary</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Unary</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation_Unary(Operation_Unary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Not Unary</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Not Unary</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperation_NotUnary(Operation_NotUnary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //ConditionModelSwitch
