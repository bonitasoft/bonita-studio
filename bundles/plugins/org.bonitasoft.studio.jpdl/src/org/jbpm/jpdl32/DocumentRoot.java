/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getAction <em>Action</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getCancelTimer <em>Cancel Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getController <em>Controller</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getCreateTimer <em>Create Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getDecision <em>Decision</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getEndState <em>End State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getFork <em>Fork</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getJoin <em>Join</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getMail <em>Mail</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getMailNode <em>Mail Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getNode <em>Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getProcessDefinition <em>Process Definition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getProcessState <em>Process State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getRecipients <em>Recipients</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getScript <em>Script</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getStartState <em>Start State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getState <em>State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getSuperState <em>Super State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getTask <em>Task</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getTaskNode <em>Task Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getTemplate <em>Template</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getText <em>Text</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getTo <em>To</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.DocumentRoot#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Action()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='action' namespace='##targetNamespace'"
	 * @generated
	 */
	ActionType getAction();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getAction <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(ActionType value);

	/**
	 * Returns the value of the '<em><b>Assignment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assignment</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assignment</em>' containment reference.
	 * @see #setAssignment(AssignmentType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Assignment()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='assignment' namespace='##targetNamespace'"
	 * @generated
	 */
	AssignmentType getAssignment();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getAssignment <em>Assignment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assignment</em>' containment reference.
	 * @see #getAssignment()
	 * @generated
	 */
	void setAssignment(AssignmentType value);

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_CancelTimer()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='cancel-timer' namespace='##targetNamespace'"
	 * @generated
	 */
	CancelTimerType getCancelTimer();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getCancelTimer <em>Cancel Timer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cancel Timer</em>' containment reference.
	 * @see #getCancelTimer()
	 * @generated
	 */
	void setCancelTimer(CancelTimerType value);

	/**
	 * Returns the value of the '<em><b>Controller</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controller</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Controller</em>' containment reference.
	 * @see #setController(Delegation)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Controller()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='controller' namespace='##targetNamespace'"
	 * @generated
	 */
	Delegation getController();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getController <em>Controller</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Controller</em>' containment reference.
	 * @see #getController()
	 * @generated
	 */
	void setController(Delegation value);

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_CreateTimer()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='create-timer' namespace='##targetNamespace'"
	 * @generated
	 */
	CreateTimerType getCreateTimer();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getCreateTimer <em>Create Timer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create Timer</em>' containment reference.
	 * @see #getCreateTimer()
	 * @generated
	 */
	void setCreateTimer(CreateTimerType value);

	/**
	 * Returns the value of the '<em><b>Decision</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decision</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decision</em>' containment reference.
	 * @see #setDecision(DecisionType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Decision()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='decision' namespace='##targetNamespace'"
	 * @generated
	 */
	DecisionType getDecision();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getDecision <em>Decision</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Decision</em>' containment reference.
	 * @see #getDecision()
	 * @generated
	 */
	void setDecision(DecisionType value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Description()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>End State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End State</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End State</em>' containment reference.
	 * @see #setEndState(EndStateType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_EndState()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='end-state' namespace='##targetNamespace'"
	 * @generated
	 */
	EndStateType getEndState();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getEndState <em>End State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End State</em>' containment reference.
	 * @see #getEndState()
	 * @generated
	 */
	void setEndState(EndStateType value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference.
	 * @see #setEvent(EventType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Event()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='event' namespace='##targetNamespace'"
	 * @generated
	 */
	EventType getEvent();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(EventType value);

	/**
	 * Returns the value of the '<em><b>Exception Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Handler</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Handler</em>' containment reference.
	 * @see #setExceptionHandler(ExceptionHandlerType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_ExceptionHandler()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exception-handler' namespace='##targetNamespace'"
	 * @generated
	 */
	ExceptionHandlerType getExceptionHandler();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getExceptionHandler <em>Exception Handler</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Handler</em>' containment reference.
	 * @see #getExceptionHandler()
	 * @generated
	 */
	void setExceptionHandler(ExceptionHandlerType value);

	/**
	 * Returns the value of the '<em><b>Fork</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fork</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fork</em>' containment reference.
	 * @see #setFork(ForkType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Fork()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='fork' namespace='##targetNamespace'"
	 * @generated
	 */
	ForkType getFork();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getFork <em>Fork</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fork</em>' containment reference.
	 * @see #getFork()
	 * @generated
	 */
	void setFork(ForkType value);

	/**
	 * Returns the value of the '<em><b>Join</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Join</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Join</em>' containment reference.
	 * @see #setJoin(JoinType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Join()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='join' namespace='##targetNamespace'"
	 * @generated
	 */
	JoinType getJoin();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getJoin <em>Join</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Join</em>' containment reference.
	 * @see #getJoin()
	 * @generated
	 */
	void setJoin(JoinType value);

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Mail()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='mail' namespace='##targetNamespace'"
	 * @generated
	 */
	MailType getMail();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getMail <em>Mail</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mail</em>' containment reference.
	 * @see #getMail()
	 * @generated
	 */
	void setMail(MailType value);

	/**
	 * Returns the value of the '<em><b>Mail Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mail Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mail Node</em>' containment reference.
	 * @see #setMailNode(MailNodeType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_MailNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='mail-node' namespace='##targetNamespace'"
	 * @generated
	 */
	MailNodeType getMailNode();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getMailNode <em>Mail Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mail Node</em>' containment reference.
	 * @see #getMailNode()
	 * @generated
	 */
	void setMailNode(MailNodeType value);

	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' containment reference.
	 * @see #setNode(NodeType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Node()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='node' namespace='##targetNamespace'"
	 * @generated
	 */
	NodeType getNode();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getNode <em>Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node</em>' containment reference.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(NodeType value);

	/**
	 * Returns the value of the '<em><b>Process Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Definition</em>' containment reference.
	 * @see #setProcessDefinition(ProcessDefinitionType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_ProcessDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='process-definition' namespace='##targetNamespace'"
	 * @generated
	 */
	ProcessDefinitionType getProcessDefinition();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getProcessDefinition <em>Process Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Definition</em>' containment reference.
	 * @see #getProcessDefinition()
	 * @generated
	 */
	void setProcessDefinition(ProcessDefinitionType value);

	/**
	 * Returns the value of the '<em><b>Process State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process State</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process State</em>' containment reference.
	 * @see #setProcessState(ProcessStateType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_ProcessState()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='process-state' namespace='##targetNamespace'"
	 * @generated
	 */
	ProcessStateType getProcessState();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getProcessState <em>Process State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process State</em>' containment reference.
	 * @see #getProcessState()
	 * @generated
	 */
	void setProcessState(ProcessStateType value);

	/**
	 * Returns the value of the '<em><b>Recipients</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recipients</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recipients</em>' attribute.
	 * @see #setRecipients(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Recipients()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='recipients' namespace='##targetNamespace'"
	 * @generated
	 */
	String getRecipients();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getRecipients <em>Recipients</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recipients</em>' attribute.
	 * @see #getRecipients()
	 * @generated
	 */
	void setRecipients(String value);

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Script()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace'"
	 * @generated
	 */
	ScriptType getScript();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(ScriptType value);

	/**
	 * Returns the value of the '<em><b>Start State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start State</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start State</em>' containment reference.
	 * @see #setStartState(StartStateType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_StartState()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='start-state' namespace='##targetNamespace'"
	 * @generated
	 */
	StartStateType getStartState();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getStartState <em>Start State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start State</em>' containment reference.
	 * @see #getStartState()
	 * @generated
	 */
	void setStartState(StartStateType value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' containment reference.
	 * @see #setState(StateType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_State()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='state' namespace='##targetNamespace'"
	 * @generated
	 */
	StateType getState();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getState <em>State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' containment reference.
	 * @see #getState()
	 * @generated
	 */
	void setState(StateType value);

	/**
	 * Returns the value of the '<em><b>Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject</em>' attribute.
	 * @see #setSubject(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Subject()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='subject' namespace='##targetNamespace'"
	 * @generated
	 */
	String getSubject();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getSubject <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject</em>' attribute.
	 * @see #getSubject()
	 * @generated
	 */
	void setSubject(String value);

	/**
	 * Returns the value of the '<em><b>Super State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super State</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super State</em>' containment reference.
	 * @see #setSuperState(SuperStateType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_SuperState()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='super-state' namespace='##targetNamespace'"
	 * @generated
	 */
	SuperStateType getSuperState();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getSuperState <em>Super State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super State</em>' containment reference.
	 * @see #getSuperState()
	 * @generated
	 */
	void setSuperState(SuperStateType value);

	/**
	 * Returns the value of the '<em><b>Swimlane</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swimlane</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swimlane</em>' containment reference.
	 * @see #setSwimlane(SwimlaneType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Swimlane()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='swimlane' namespace='##targetNamespace'"
	 * @generated
	 */
	SwimlaneType getSwimlane();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getSwimlane <em>Swimlane</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Swimlane</em>' containment reference.
	 * @see #getSwimlane()
	 * @generated
	 */
	void setSwimlane(SwimlaneType value);

	/**
	 * Returns the value of the '<em><b>Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' containment reference.
	 * @see #setTask(TaskType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Task()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='task' namespace='##targetNamespace'"
	 * @generated
	 */
	TaskType getTask();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getTask <em>Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task</em>' containment reference.
	 * @see #getTask()
	 * @generated
	 */
	void setTask(TaskType value);

	/**
	 * Returns the value of the '<em><b>Task Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Node</em>' containment reference.
	 * @see #setTaskNode(TaskNodeType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_TaskNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='task-node' namespace='##targetNamespace'"
	 * @generated
	 */
	TaskNodeType getTaskNode();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getTaskNode <em>Task Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task Node</em>' containment reference.
	 * @see #getTaskNode()
	 * @generated
	 */
	void setTaskNode(TaskNodeType value);

	/**
	 * Returns the value of the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' attribute.
	 * @see #setTemplate(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Template()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='template' namespace='##targetNamespace'"
	 * @generated
	 */
	String getTemplate();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getTemplate <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template</em>' attribute.
	 * @see #getTemplate()
	 * @generated
	 */
	void setTemplate(String value);

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Text()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='text' namespace='##targetNamespace'"
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer</em>' containment reference.
	 * @see #setTimer(TimerType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Timer()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timer' namespace='##targetNamespace'"
	 * @generated
	 */
	TimerType getTimer();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getTimer <em>Timer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timer</em>' containment reference.
	 * @see #getTimer()
	 * @generated
	 */
	void setTimer(TimerType value);

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_To()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='to' namespace='##targetNamespace'"
	 * @generated
	 */
	String getTo();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(String value);

	/**
	 * Returns the value of the '<em><b>Transition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition</em>' containment reference.
	 * @see #setTransition(TransitionType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Transition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='transition' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionType getTransition();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getTransition <em>Transition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition</em>' containment reference.
	 * @see #getTransition()
	 * @generated
	 */
	void setTransition(TransitionType value);

	/**
	 * Returns the value of the '<em><b>Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' containment reference.
	 * @see #setVariable(VariableType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getDocumentRoot_Variable()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='variable' namespace='##targetNamespace'"
	 * @generated
	 */
	VariableType getVariable();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.DocumentRoot#getVariable <em>Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' containment reference.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(VariableType value);

} // DocumentRoot
