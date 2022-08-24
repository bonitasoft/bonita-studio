/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TComplex Behavior Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getEvent <em>Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexBehaviorDefinition()
 * @model extendedMetaData="name='tComplexBehaviorDefinition' kind='elementOnly'"
 * @generated
 */
public interface TComplexBehaviorDefinition extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(TFormalExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexBehaviorDefinition_Condition()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='condition' namespace='##targetNamespace'"
	 * @generated
	 */
	TFormalExpression getCondition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(TFormalExpression value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference.
	 * @see #setEvent(TImplicitThrowEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexBehaviorDefinition_Event()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='event' namespace='##targetNamespace'"
	 * @generated
	 */
	TImplicitThrowEvent getEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(TImplicitThrowEvent value);

} // TComplexBehaviorDefinition
