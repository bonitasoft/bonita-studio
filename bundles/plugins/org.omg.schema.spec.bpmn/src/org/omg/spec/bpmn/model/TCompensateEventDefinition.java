/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TCompensate Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#getActivityRef <em>Activity Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCompensateEventDefinition()
 * @model extendedMetaData="name='tCompensateEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TCompensateEventDefinition extends TEventDefinition {
	/**
	 * Returns the value of the '<em><b>Activity Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Ref</em>' attribute.
	 * @see #setActivityRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCompensateEventDefinition_ActivityRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='activityRef'"
	 * @generated
	 */
	QName getActivityRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#getActivityRef <em>Activity Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Ref</em>' attribute.
	 * @see #getActivityRef()
	 * @generated
	 */
	void setActivityRef(QName value);

	/**
	 * Returns the value of the '<em><b>Wait For Completion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wait For Completion</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wait For Completion</em>' attribute.
	 * @see #isSetWaitForCompletion()
	 * @see #unsetWaitForCompletion()
	 * @see #setWaitForCompletion(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCompensateEventDefinition_WaitForCompletion()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='waitForCompletion'"
	 * @generated
	 */
	boolean isWaitForCompletion();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wait For Completion</em>' attribute.
	 * @see #isSetWaitForCompletion()
	 * @see #unsetWaitForCompletion()
	 * @see #isWaitForCompletion()
	 * @generated
	 */
	void setWaitForCompletion(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWaitForCompletion()
	 * @see #isWaitForCompletion()
	 * @see #setWaitForCompletion(boolean)
	 * @generated
	 */
	void unsetWaitForCompletion();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Wait For Completion</em>' attribute is set.
	 * @see #unsetWaitForCompletion()
	 * @see #isWaitForCompletion()
	 * @see #setWaitForCompletion(boolean)
	 * @generated
	 */
	boolean isSetWaitForCompletion();

} // TCompensateEventDefinition
