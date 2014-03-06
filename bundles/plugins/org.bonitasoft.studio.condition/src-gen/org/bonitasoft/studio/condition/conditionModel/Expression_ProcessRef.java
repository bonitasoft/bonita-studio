/**
 */
package org.bonitasoft.studio.condition.conditionModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression Process Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage#getExpression_ProcessRef()
 * @model
 * @generated
 */
public interface Expression_ProcessRef extends Expression
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' reference.
   * @see #setValue(EObject)
   * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage#getExpression_ProcessRef_Value()
   * @model
   * @generated
   */
  EObject getValue();

  /**
   * Sets the value of the '{@link org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef#getValue <em>Value</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' reference.
   * @see #getValue()
   * @generated
   */
  void setValue(EObject value);

} // Expression_ProcessRef
