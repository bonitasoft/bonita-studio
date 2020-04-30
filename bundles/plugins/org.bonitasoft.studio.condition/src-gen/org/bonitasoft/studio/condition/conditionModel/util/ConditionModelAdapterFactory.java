/**
 */
package org.bonitasoft.studio.condition.conditionModel.util;

import org.bonitasoft.studio.condition.conditionModel.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage
 * @generated
 */
public class ConditionModelAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ConditionModelPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionModelAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = ConditionModelPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConditionModelSwitch<Adapter> modelSwitch =
    new ConditionModelSwitch<Adapter>()
    {
      @Override
      public Adapter caseOperation_Compare(Operation_Compare object)
      {
        return createOperation_CompareAdapter();
      }
      @Override
      public Adapter caseUnary_Operation(Unary_Operation object)
      {
        return createUnary_OperationAdapter();
      }
      @Override
      public Adapter caseOperation(Operation object)
      {
        return createOperationAdapter();
      }
      @Override
      public Adapter caseExpression(Expression object)
      {
        return createExpressionAdapter();
      }
      @Override
      public Adapter caseExpression_Double(Expression_Double object)
      {
        return createExpression_DoubleAdapter();
      }
      @Override
      public Adapter caseExpression_Integer(Expression_Integer object)
      {
        return createExpression_IntegerAdapter();
      }
      @Override
      public Adapter caseExpression_String(Expression_String object)
      {
        return createExpression_StringAdapter();
      }
      @Override
      public Adapter caseExpression_ProcessRef(Expression_ProcessRef object)
      {
        return createExpression_ProcessRefAdapter();
      }
      @Override
      public Adapter caseExpression_Boolean(Expression_Boolean object)
      {
        return createExpression_BooleanAdapter();
      }
      @Override
      public Adapter caseOperation_Less_Equals(Operation_Less_Equals object)
      {
        return createOperation_Less_EqualsAdapter();
      }
      @Override
      public Adapter caseOperation_Less(Operation_Less object)
      {
        return createOperation_LessAdapter();
      }
      @Override
      public Adapter caseOperation_Greater_Equals(Operation_Greater_Equals object)
      {
        return createOperation_Greater_EqualsAdapter();
      }
      @Override
      public Adapter caseOperation_Greater(Operation_Greater object)
      {
        return createOperation_GreaterAdapter();
      }
      @Override
      public Adapter caseOperation_Not_Equals(Operation_Not_Equals object)
      {
        return createOperation_Not_EqualsAdapter();
      }
      @Override
      public Adapter caseOperation_Equals(Operation_Equals object)
      {
        return createOperation_EqualsAdapter();
      }
      @Override
      public Adapter caseOperation_Unary(Operation_Unary object)
      {
        return createOperation_UnaryAdapter();
      }
      @Override
      public Adapter caseOperation_NotUnary(Operation_NotUnary object)
      {
        return createOperation_NotUnaryAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Compare <em>Operation Compare</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Compare
   * @generated
   */
  public Adapter createOperation_CompareAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Unary_Operation <em>Unary Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Unary_Operation
   * @generated
   */
  public Adapter createUnary_OperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation
   * @generated
   */
  public Adapter createOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression
   * @generated
   */
  public Adapter createExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_Double <em>Expression Double</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_Double
   * @generated
   */
  public Adapter createExpression_DoubleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_Integer <em>Expression Integer</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_Integer
   * @generated
   */
  public Adapter createExpression_IntegerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_String <em>Expression String</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_String
   * @generated
   */
  public Adapter createExpression_StringAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef <em>Expression Process Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef
   * @generated
   */
  public Adapter createExpression_ProcessRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Expression_Boolean <em>Expression Boolean</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Expression_Boolean
   * @generated
   */
  public Adapter createExpression_BooleanAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Less_Equals <em>Operation Less Equals</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Less_Equals
   * @generated
   */
  public Adapter createOperation_Less_EqualsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Less <em>Operation Less</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Less
   * @generated
   */
  public Adapter createOperation_LessAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Greater_Equals <em>Operation Greater Equals</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Greater_Equals
   * @generated
   */
  public Adapter createOperation_Greater_EqualsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Greater <em>Operation Greater</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Greater
   * @generated
   */
  public Adapter createOperation_GreaterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Not_Equals <em>Operation Not Equals</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Not_Equals
   * @generated
   */
  public Adapter createOperation_Not_EqualsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Equals <em>Operation Equals</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Equals
   * @generated
   */
  public Adapter createOperation_EqualsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Unary <em>Operation Unary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_Unary
   * @generated
   */
  public Adapter createOperation_UnaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary <em>Operation Not Unary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary
   * @generated
   */
  public Adapter createOperation_NotUnaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //ConditionModelAdapterFactory
