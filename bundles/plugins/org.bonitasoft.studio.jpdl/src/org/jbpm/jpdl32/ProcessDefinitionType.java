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
 * A representation of the model object '<em><b>Process Definition Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getStartState <em>Start State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getNode <em>Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getState <em>State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getTaskNode <em>Task Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getSuperState <em>Super State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getProcessState <em>Process State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getFork <em>Fork</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getJoin <em>Join</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getDecision <em>Decision</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getEndState <em>End State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getMailNode <em>Mail Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getAction <em>Action</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getScript <em>Script</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getCreateTimer <em>Create Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getCancelTimer <em>Cancel Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getMail <em>Mail</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getTask <em>Task</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessDefinitionType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType()
 * @model extendedMetaData="name='process-definition_._type' kind='elementOnly'"
 * @generated
 */
public interface ProcessDefinitionType extends EObject {
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Group()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Description()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<String> getDescription();

	/**
	 * Returns the value of the '<em><b>Swimlane</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.SwimlaneType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swimlane</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swimlane</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Swimlane()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='swimlane' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<SwimlaneType> getSwimlane();

	/**
	 * Returns the value of the '<em><b>Start State</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.StartStateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start State</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start State</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_StartState()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='start-state' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<StartStateType> getStartState();

	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.NodeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Node()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='node' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<NodeType> getNode();

	/**
	 * Returns the value of the '<em><b>State</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.StateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_State()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='state' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<StateType> getState();

	/**
	 * Returns the value of the '<em><b>Task Node</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.TaskNodeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Node</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_TaskNode()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='task-node' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<TaskNodeType> getTaskNode();

	/**
	 * Returns the value of the '<em><b>Super State</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.SuperStateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super State</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super State</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_SuperState()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='super-state' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<SuperStateType> getSuperState();

	/**
	 * Returns the value of the '<em><b>Process State</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.ProcessStateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process State</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process State</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_ProcessState()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='process-state' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ProcessStateType> getProcessState();

	/**
	 * Returns the value of the '<em><b>Fork</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.ForkType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fork</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fork</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Fork()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='fork' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ForkType> getFork();

	/**
	 * Returns the value of the '<em><b>Join</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.JoinType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Join</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Join</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Join()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='join' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<JoinType> getJoin();

	/**
	 * Returns the value of the '<em><b>Decision</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.DecisionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decision</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decision</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Decision()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='decision' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<DecisionType> getDecision();

	/**
	 * Returns the value of the '<em><b>End State</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.EndStateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End State</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End State</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_EndState()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='end-state' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<EndStateType> getEndState();

	/**
	 * Returns the value of the '<em><b>Mail Node</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.MailNodeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mail Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mail Node</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_MailNode()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='mail-node' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<MailNodeType> getMailNode();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Action()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Script()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_CreateTimer()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_CancelTimer()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Mail()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='mail' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<MailType> getMail();

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.EventType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Event()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='event' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<EventType> getEvent();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_ExceptionHandler()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exception-handler' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ExceptionHandlerType> getExceptionHandler();

	/**
	 * Returns the value of the '<em><b>Task</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.TaskType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Task()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='task' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<TaskType> getTask();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessDefinitionType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ProcessDefinitionType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ProcessDefinitionType
