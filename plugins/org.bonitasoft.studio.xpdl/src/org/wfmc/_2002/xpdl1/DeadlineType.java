/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deadline Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.DeadlineType#getDeadlineCondition <em>Deadline Condition</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DeadlineType#getExceptionName <em>Exception Name</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DeadlineType#getExecution <em>Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDeadlineType()
 * @model extendedMetaData="name='Deadline_._type' kind='elementOnly'"
 * @generated
 */
public interface DeadlineType extends EObject {
	/**
	 * Returns the value of the '<em><b>Deadline Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deadline Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deadline Condition</em>' containment reference.
	 * @see #setDeadlineCondition(EObject)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDeadlineType_DeadlineCondition()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='DeadlineCondition' namespace='##targetNamespace'"
	 * @generated
	 */
	EObject getDeadlineCondition();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DeadlineType#getDeadlineCondition <em>Deadline Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deadline Condition</em>' containment reference.
	 * @see #getDeadlineCondition()
	 * @generated
	 */
	void setDeadlineCondition(EObject value);

	/**
	 * Returns the value of the '<em><b>Exception Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Name</em>' containment reference.
	 * @see #setExceptionName(EObject)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDeadlineType_ExceptionName()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='ExceptionName' namespace='##targetNamespace'"
	 * @generated
	 */
	EObject getExceptionName();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DeadlineType#getExceptionName <em>Exception Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Name</em>' containment reference.
	 * @see #getExceptionName()
	 * @generated
	 */
	void setExceptionName(EObject value);

	/**
	 * Returns the value of the '<em><b>Execution</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.ExecutionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.ExecutionType
	 * @see #isSetExecution()
	 * @see #unsetExecution()
	 * @see #setExecution(ExecutionType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDeadlineType_Execution()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='Execution'"
	 * @generated
	 */
	ExecutionType getExecution();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DeadlineType#getExecution <em>Execution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.ExecutionType
	 * @see #isSetExecution()
	 * @see #unsetExecution()
	 * @see #getExecution()
	 * @generated
	 */
	void setExecution(ExecutionType value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.DeadlineType#getExecution <em>Execution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetExecution()
	 * @see #getExecution()
	 * @see #setExecution(ExecutionType)
	 * @generated
	 */
	void unsetExecution();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.DeadlineType#getExecution <em>Execution</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Execution</em>' attribute is set.
	 * @see #unsetExecution()
	 * @see #getExecution()
	 * @see #setExecution(ExecutionType)
	 * @generated
	 */
	boolean isSetExecution();

} // DeadlineType
