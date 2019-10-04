/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TTimer Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDate <em>Time Date</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDuration <em>Time Duration</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeCycle <em>Time Cycle</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTTimerEventDefinition()
 * @model extendedMetaData="name='tTimerEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TTimerEventDefinition extends TEventDefinition {
	/**
	 * Returns the value of the '<em><b>Time Date</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Date</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Date</em>' containment reference.
	 * @see #setTimeDate(TExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTTimerEventDefinition_TimeDate()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='timeDate' namespace='##targetNamespace'"
	 * @generated
	 */
	TExpression getTimeDate();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDate <em>Time Date</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Date</em>' containment reference.
	 * @see #getTimeDate()
	 * @generated
	 */
	void setTimeDate(TExpression value);

	/**
	 * Returns the value of the '<em><b>Time Duration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Duration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Duration</em>' containment reference.
	 * @see #setTimeDuration(TExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTTimerEventDefinition_TimeDuration()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='timeDuration' namespace='##targetNamespace'"
	 * @generated
	 */
	TExpression getTimeDuration();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDuration <em>Time Duration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Duration</em>' containment reference.
	 * @see #getTimeDuration()
	 * @generated
	 */
	void setTimeDuration(TExpression value);

	/**
	 * Returns the value of the '<em><b>Time Cycle</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Cycle</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Cycle</em>' containment reference.
	 * @see #setTimeCycle(TExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTTimerEventDefinition_TimeCycle()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='timeCycle' namespace='##targetNamespace'"
	 * @generated
	 */
	TExpression getTimeCycle();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeCycle <em>Time Cycle</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Cycle</em>' containment reference.
	 * @see #getTimeCycle()
	 * @generated
	 */
	void setTimeCycle(TExpression value);

} // TTimerEventDefinition
