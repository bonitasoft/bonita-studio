/**
 */
package org.bonitasoft.studio.condition.conditionModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Compare</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.condition.conditionModel.Operation_Compare#getOp <em>Op</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage#getOperation_Compare()
 * @model
 * @generated
 */
public interface Operation_Compare extends EObject
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' containment reference.
   * @see #setOp(EObject)
   * @see org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage#getOperation_Compare_Op()
   * @model containment="true"
   * @generated
   */
  EObject getOp();

  /**
   * Sets the value of the '{@link org.bonitasoft.studio.condition.conditionModel.Operation_Compare#getOp <em>Op</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' containment reference.
   * @see #getOp()
   * @generated
   */
  void setOp(EObject value);

} // Operation_Compare
