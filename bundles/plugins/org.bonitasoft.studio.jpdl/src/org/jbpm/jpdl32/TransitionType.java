/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getAction <em>Action</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getScript <em>Script</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getCreateTimer <em>Create Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getCancelTimer <em>Cancel Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getMail <em>Mail</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getName <em>Name</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TransitionType#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType()
 * @model extendedMetaData="name='transition_._type' kind='elementOnly'"
 * @generated
 */
public interface TransitionType extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_Description()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<String> getDescription();

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.ConditionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_Condition()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='condition' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ConditionType> getCondition();

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.ActionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_Action()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='action' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ActionType> getAction();

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.ScriptType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_Script()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ScriptType> getScript();

	/**
	 * Returns the value of the '<em><b>Create Timer</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.CreateTimerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Create Timer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create Timer</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_CreateTimer()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='create-timer' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<CreateTimerType> getCreateTimer();

	/**
	 * Returns the value of the '<em><b>Cancel Timer</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.CancelTimerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cancel Timer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cancel Timer</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_CancelTimer()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='cancel-timer' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<CancelTimerType> getCancelTimer();

	/**
	 * Returns the value of the '<em><b>Mail</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.MailType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mail</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mail</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_Mail()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='mail' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<MailType> getMail();

	/**
	 * Returns the value of the '<em><b>Exception Handler</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.ExceptionHandlerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Handler</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Handler</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_ExceptionHandler()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exception-handler' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ExceptionHandlerType> getExceptionHandler();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TransitionType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' attribute.
	 * @see #setTo(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTransitionType_To()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='to'"
	 * @generated
	 */
	String getTo();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TransitionType#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(String value);

} // TransitionType
