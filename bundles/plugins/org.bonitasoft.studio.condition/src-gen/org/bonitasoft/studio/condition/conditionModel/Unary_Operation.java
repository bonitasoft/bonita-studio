/**
 */
package org.bonitasoft.studio.condition.conditionModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.condition.conditionModel.Unary_Operation#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage#getUnary_Operation()
 * @model
 * @generated
 */
public interface Unary_Operation extends EObject
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(Expression)
   * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage#getUnary_Operation_Value()
   * @model containment="true"
   * @generated
   */
  Expression getValue();

  /**
   * Sets the value of the '{@link org.bonitasoft.studio.condition.conditionModel.Unary_Operation#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(Expression value);

} // Unary_Operation
