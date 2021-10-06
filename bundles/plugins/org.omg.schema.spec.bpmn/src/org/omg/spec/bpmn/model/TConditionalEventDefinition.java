/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TConditional Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TConditionalEventDefinition#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTConditionalEventDefinition()
 * @model extendedMetaData="name='tConditionalEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TConditionalEventDefinition extends TEventDefinition {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(TExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTConditionalEventDefinition_Condition()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='condition' namespace='##targetNamespace'"
	 * @generated
	 */
	TExpression getCondition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TConditionalEventDefinition#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(TExpression value);

} // TConditionalEventDefinition
