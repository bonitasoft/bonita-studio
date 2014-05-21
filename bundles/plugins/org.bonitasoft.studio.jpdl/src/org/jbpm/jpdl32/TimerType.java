/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.TimerType#getAction <em>Action</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TimerType#getScript <em>Script</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TimerType#getCreateTimer <em>Create Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TimerType#getCancelTimer <em>Cancel Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TimerType#getMail <em>Mail</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TimerType#getDuedate <em>Duedate</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TimerType#getName <em>Name</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TimerType#getRepeat <em>Repeat</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TimerType#getTransition <em>Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType()
 * @model extendedMetaData="name='timer_._type' kind='elementOnly'"
 * @generated
 */
public interface TimerType extends EObject {
	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference.
	 * @see #setAction(ActionType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType_Action()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='action' namespace='##targetNamespace'"
	 * @generated
	 */
	ActionType getAction();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TimerType#getAction <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(ActionType value);

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' containment reference.
	 * @see #setScript(ScriptType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType_Script()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace'"
	 * @generated
	 */
	ScriptType getScript();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TimerType#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(ScriptType value);

	/**
	 * Returns the value of the '<em><b>Create Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Create Timer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create Timer</em>' containment reference.
	 * @see #setCreateTimer(CreateTimerType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType_CreateTimer()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='create-timer' namespace='##targetNamespace'"
	 * @generated
	 */
	CreateTimerType getCreateTimer();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TimerType#getCreateTimer <em>Create Timer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create Timer</em>' containment reference.
	 * @see #getCreateTimer()
	 * @generated
	 */
	void setCreateTimer(CreateTimerType value);

	/**
	 * Returns the value of the '<em><b>Cancel Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cancel Timer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cancel Timer</em>' containment reference.
	 * @see #setCancelTimer(CancelTimerType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType_CancelTimer()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='cancel-timer' namespace='##targetNamespace'"
	 * @generated
	 */
	CancelTimerType getCancelTimer();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TimerType#getCancelTimer <em>Cancel Timer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cancel Timer</em>' containment reference.
	 * @see #getCancelTimer()
	 * @generated
	 */
	void setCancelTimer(CancelTimerType value);

	/**
	 * Returns the value of the '<em><b>Mail</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mail</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mail</em>' containment reference.
	 * @see #setMail(MailType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType_Mail()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='mail' namespace='##targetNamespace'"
	 * @generated
	 */
	MailType getMail();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TimerType#getMail <em>Mail</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mail</em>' containment reference.
	 * @see #getMail()
	 * @generated
	 */
	void setMail(MailType value);

	/**
	 * Returns the value of the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duedate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duedate</em>' attribute.
	 * @see #setDuedate(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType_Duedate()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='duedate'"
	 * @generated
	 */
	String getDuedate();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TimerType#getDuedate <em>Duedate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duedate</em>' attribute.
	 * @see #getDuedate()
	 * @generated
	 */
	void setDuedate(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TimerType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Repeat</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repeat</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repeat</em>' attribute.
	 * @see #setRepeat(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType_Repeat()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='repeat'"
	 * @generated
	 */
	String getRepeat();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TimerType#getRepeat <em>Repeat</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repeat</em>' attribute.
	 * @see #getRepeat()
	 * @generated
	 */
	void setRepeat(String value);

	/**
	 * Returns the value of the '<em><b>Transition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition</em>' attribute.
	 * @see #setTransition(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTimerType_Transition()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='transition'"
	 * @generated
	 */
	String getTransition();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TimerType#getTransition <em>Transition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition</em>' attribute.
	 * @see #getTransition()
	 * @generated
	 */
	void setTransition(String value);

} // TimerType
