/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TAd Hoc Sub Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TAdHocSubProcess#getCompletionCondition <em>Completion Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TAdHocSubProcess#isCancelRemainingInstances <em>Cancel Remaining Instances</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TAdHocSubProcess#getOrdering <em>Ordering</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTAdHocSubProcess()
 * @model extendedMetaData="name='tAdHocSubProcess' kind='elementOnly'"
 * @generated
 */
public interface TAdHocSubProcess extends TSubProcess {
	/**
	 * Returns the value of the '<em><b>Completion Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completion Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completion Condition</em>' containment reference.
	 * @see #setCompletionCondition(TExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTAdHocSubProcess_CompletionCondition()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='completionCondition' namespace='##targetNamespace'"
	 * @generated
	 */
	TExpression getCompletionCondition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#getCompletionCondition <em>Completion Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completion Condition</em>' containment reference.
	 * @see #getCompletionCondition()
	 * @generated
	 */
	void setCompletionCondition(TExpression value);

	/**
	 * Returns the value of the '<em><b>Cancel Remaining Instances</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cancel Remaining Instances</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cancel Remaining Instances</em>' attribute.
	 * @see #isSetCancelRemainingInstances()
	 * @see #unsetCancelRemainingInstances()
	 * @see #setCancelRemainingInstances(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTAdHocSubProcess_CancelRemainingInstances()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='cancelRemainingInstances'"
	 * @generated
	 */
	boolean isCancelRemainingInstances();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#isCancelRemainingInstances <em>Cancel Remaining Instances</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cancel Remaining Instances</em>' attribute.
	 * @see #isSetCancelRemainingInstances()
	 * @see #unsetCancelRemainingInstances()
	 * @see #isCancelRemainingInstances()
	 * @generated
	 */
	void setCancelRemainingInstances(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#isCancelRemainingInstances <em>Cancel Remaining Instances</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCancelRemainingInstances()
	 * @see #isCancelRemainingInstances()
	 * @see #setCancelRemainingInstances(boolean)
	 * @generated
	 */
	void unsetCancelRemainingInstances();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#isCancelRemainingInstances <em>Cancel Remaining Instances</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cancel Remaining Instances</em>' attribute is set.
	 * @see #unsetCancelRemainingInstances()
	 * @see #isCancelRemainingInstances()
	 * @see #setCancelRemainingInstances(boolean)
	 * @generated
	 */
	boolean isSetCancelRemainingInstances();

	/**
	 * Returns the value of the '<em><b>Ordering</b></em>' attribute.
	 * The literals are from the enumeration {@link org.omg.spec.bpmn.model.TAdHocOrdering}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordering</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordering</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TAdHocOrdering
	 * @see #isSetOrdering()
	 * @see #unsetOrdering()
	 * @see #setOrdering(TAdHocOrdering)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTAdHocSubProcess_Ordering()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='ordering'"
	 * @generated
	 */
	TAdHocOrdering getOrdering();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#getOrdering <em>Ordering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TAdHocOrdering
	 * @see #isSetOrdering()
	 * @see #unsetOrdering()
	 * @see #getOrdering()
	 * @generated
	 */
	void setOrdering(TAdHocOrdering value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#getOrdering <em>Ordering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOrdering()
	 * @see #getOrdering()
	 * @see #setOrdering(TAdHocOrdering)
	 * @generated
	 */
	void unsetOrdering();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#getOrdering <em>Ordering</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ordering</em>' attribute is set.
	 * @see #unsetOrdering()
	 * @see #getOrdering()
	 * @see #setOrdering(TAdHocOrdering)
	 * @generated
	 */
	boolean isSetOrdering();

} // TAdHocSubProcess
