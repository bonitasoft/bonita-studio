/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jbpm.jpdl32.ActionType;
import org.jbpm.jpdl32.AssignmentType;
import org.jbpm.jpdl32.CancelTimerType;
import org.jbpm.jpdl32.CreateTimerType;
import org.jbpm.jpdl32.DecisionType;
import org.jbpm.jpdl32.Delegation;
import org.jbpm.jpdl32.DocumentRoot;
import org.jbpm.jpdl32.EndStateType;
import org.jbpm.jpdl32.EventType;
import org.jbpm.jpdl32.ExceptionHandlerType;
import org.jbpm.jpdl32.ForkType;
import org.jbpm.jpdl32.JoinType;
import org.jbpm.jpdl32.MailNodeType;
import org.jbpm.jpdl32.MailType;
import org.jbpm.jpdl32.NodeType;
import org.jbpm.jpdl32.ProcessDefinitionType;
import org.jbpm.jpdl32.ProcessStateType;
import org.jbpm.jpdl32.ScriptType;
import org.jbpm.jpdl32.StartStateType;
import org.jbpm.jpdl32.StateType;
import org.jbpm.jpdl32.SuperStateType;
import org.jbpm.jpdl32.SwimlaneType;
import org.jbpm.jpdl32.TaskNodeType;
import org.jbpm.jpdl32.TaskType;
import org.jbpm.jpdl32.TimerType;
import org.jbpm.jpdl32.TransitionType;
import org.jbpm.jpdl32.VariableType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getAction <em>Action</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getCancelTimer <em>Cancel Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getController <em>Controller</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getCreateTimer <em>Create Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getDecision <em>Decision</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getEndState <em>End State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getFork <em>Fork</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getMail <em>Mail</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getMailNode <em>Mail Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getProcessDefinition <em>Process Definition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getProcessState <em>Process State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getRecipients <em>Recipients</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getStartState <em>Start State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getState <em>State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getSuperState <em>Super State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getTask <em>Task</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getTaskNode <em>Task Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getTemplate <em>Template</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getTo <em>To</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.DocumentRootImpl#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getRecipients() <em>Recipients</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipients()
	 * @generated
	 * @ordered
	 */
	protected static final String RECIPIENTS_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getSubject() <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBJECT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTemplate() <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplate()
	 * @generated
	 * @ordered
	 */
	protected static final String TEMPLATE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, jpdl32Package.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, jpdl32Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, jpdl32Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionType getAction() {
		return (ActionType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__ACTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAction(ActionType newAction, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__ACTION, newAction, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(ActionType newAction) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__ACTION, newAction);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentType getAssignment() {
		return (AssignmentType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__ASSIGNMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssignment(AssignmentType newAssignment, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__ASSIGNMENT, newAssignment, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignment(AssignmentType newAssignment) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__ASSIGNMENT, newAssignment);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CancelTimerType getCancelTimer() {
		return (CancelTimerType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__CANCEL_TIMER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCancelTimer(CancelTimerType newCancelTimer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__CANCEL_TIMER, newCancelTimer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCancelTimer(CancelTimerType newCancelTimer) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__CANCEL_TIMER, newCancelTimer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Delegation getController() {
		return (Delegation)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__CONTROLLER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetController(Delegation newController, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__CONTROLLER, newController, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setController(Delegation newController) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__CONTROLLER, newController);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateTimerType getCreateTimer() {
		return (CreateTimerType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__CREATE_TIMER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCreateTimer(CreateTimerType newCreateTimer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__CREATE_TIMER, newCreateTimer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreateTimer(CreateTimerType newCreateTimer) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__CREATE_TIMER, newCreateTimer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecisionType getDecision() {
		return (DecisionType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__DECISION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDecision(DecisionType newDecision, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__DECISION, newDecision, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDecision(DecisionType newDecision) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__DECISION, newDecision);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return (String)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__DESCRIPTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndStateType getEndState() {
		return (EndStateType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__END_STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndState(EndStateType newEndState, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__END_STATE, newEndState, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndState(EndStateType newEndState) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__END_STATE, newEndState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventType getEvent() {
		return (EventType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__EVENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEvent(EventType newEvent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__EVENT, newEvent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvent(EventType newEvent) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__EVENT, newEvent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExceptionHandlerType getExceptionHandler() {
		return (ExceptionHandlerType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExceptionHandler(ExceptionHandlerType newExceptionHandler, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLER, newExceptionHandler, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExceptionHandler(ExceptionHandlerType newExceptionHandler) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLER, newExceptionHandler);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForkType getFork() {
		return (ForkType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__FORK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFork(ForkType newFork, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__FORK, newFork, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFork(ForkType newFork) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__FORK, newFork);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinType getJoin() {
		return (JoinType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__JOIN, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJoin(JoinType newJoin, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__JOIN, newJoin, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJoin(JoinType newJoin) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__JOIN, newJoin);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MailType getMail() {
		return (MailType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__MAIL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMail(MailType newMail, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__MAIL, newMail, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMail(MailType newMail) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__MAIL, newMail);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MailNodeType getMailNode() {
		return (MailNodeType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__MAIL_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMailNode(MailNodeType newMailNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__MAIL_NODE, newMailNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMailNode(MailNodeType newMailNode) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__MAIL_NODE, newMailNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeType getNode() {
		return (NodeType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNode(NodeType newNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__NODE, newNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNode(NodeType newNode) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__NODE, newNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessDefinitionType getProcessDefinition() {
		return (ProcessDefinitionType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__PROCESS_DEFINITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcessDefinition(ProcessDefinitionType newProcessDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__PROCESS_DEFINITION, newProcessDefinition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessDefinition(ProcessDefinitionType newProcessDefinition) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__PROCESS_DEFINITION, newProcessDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessStateType getProcessState() {
		return (ProcessStateType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__PROCESS_STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcessState(ProcessStateType newProcessState, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__PROCESS_STATE, newProcessState, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessState(ProcessStateType newProcessState) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__PROCESS_STATE, newProcessState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRecipients() {
		return (String)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__RECIPIENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecipients(String newRecipients) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__RECIPIENTS, newRecipients);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptType getScript() {
		return (ScriptType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__SCRIPT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScript(ScriptType newScript, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__SCRIPT, newScript, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScript(ScriptType newScript) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__SCRIPT, newScript);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartStateType getStartState() {
		return (StartStateType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__START_STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartState(StartStateType newStartState, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__START_STATE, newStartState, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartState(StartStateType newStartState) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__START_STATE, newStartState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateType getState() {
		return (StateType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetState(StateType newState, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__STATE, newState, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(StateType newState) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__STATE, newState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubject() {
		return (String)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__SUBJECT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubject(String newSubject) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__SUBJECT, newSubject);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperStateType getSuperState() {
		return (SuperStateType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__SUPER_STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuperState(SuperStateType newSuperState, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__SUPER_STATE, newSuperState, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuperState(SuperStateType newSuperState) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__SUPER_STATE, newSuperState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwimlaneType getSwimlane() {
		return (SwimlaneType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__SWIMLANE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSwimlane(SwimlaneType newSwimlane, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__SWIMLANE, newSwimlane, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwimlane(SwimlaneType newSwimlane) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__SWIMLANE, newSwimlane);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskType getTask() {
		return (TaskType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTask(TaskType newTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__TASK, newTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTask(TaskType newTask) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__TASK, newTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskNodeType getTaskNode() {
		return (TaskNodeType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__TASK_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTaskNode(TaskNodeType newTaskNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__TASK_NODE, newTaskNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskNode(TaskNodeType newTaskNode) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__TASK_NODE, newTaskNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTemplate() {
		return (String)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__TEMPLATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemplate(String newTemplate) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__TEMPLATE, newTemplate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText() {
		return (String)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__TEXT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setText(String newText) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__TEXT, newText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimerType getTimer() {
		return (TimerType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__TIMER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimer(TimerType newTimer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__TIMER, newTimer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimer(TimerType newTimer) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__TIMER, newTimer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTo() {
		return (String)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__TO, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(String newTo) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__TO, newTo);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionType getTransition() {
		return (TransitionType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__TRANSITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransition(TransitionType newTransition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__TRANSITION, newTransition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransition(TransitionType newTransition) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__TRANSITION, newTransition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableType getVariable() {
		return (VariableType)getMixed().get(jpdl32Package.Literals.DOCUMENT_ROOT__VARIABLE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariable(VariableType newVariable, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(jpdl32Package.Literals.DOCUMENT_ROOT__VARIABLE, newVariable, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariable(VariableType newVariable) {
		((FeatureMap.Internal)getMixed()).set(jpdl32Package.Literals.DOCUMENT_ROOT__VARIABLE, newVariable);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case jpdl32Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case jpdl32Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case jpdl32Package.DOCUMENT_ROOT__ACTION:
				return basicSetAction(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__ASSIGNMENT:
				return basicSetAssignment(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__CANCEL_TIMER:
				return basicSetCancelTimer(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__CONTROLLER:
				return basicSetController(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__CREATE_TIMER:
				return basicSetCreateTimer(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__DECISION:
				return basicSetDecision(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__END_STATE:
				return basicSetEndState(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__EVENT:
				return basicSetEvent(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				return basicSetExceptionHandler(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__FORK:
				return basicSetFork(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__JOIN:
				return basicSetJoin(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__MAIL:
				return basicSetMail(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__MAIL_NODE:
				return basicSetMailNode(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__NODE:
				return basicSetNode(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_DEFINITION:
				return basicSetProcessDefinition(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_STATE:
				return basicSetProcessState(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__SCRIPT:
				return basicSetScript(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__START_STATE:
				return basicSetStartState(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__STATE:
				return basicSetState(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__SUPER_STATE:
				return basicSetSuperState(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__SWIMLANE:
				return basicSetSwimlane(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__TASK:
				return basicSetTask(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__TASK_NODE:
				return basicSetTaskNode(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__TIMER:
				return basicSetTimer(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__TRANSITION:
				return basicSetTransition(null, msgs);
			case jpdl32Package.DOCUMENT_ROOT__VARIABLE:
				return basicSetVariable(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case jpdl32Package.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case jpdl32Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case jpdl32Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case jpdl32Package.DOCUMENT_ROOT__ACTION:
				return getAction();
			case jpdl32Package.DOCUMENT_ROOT__ASSIGNMENT:
				return getAssignment();
			case jpdl32Package.DOCUMENT_ROOT__CANCEL_TIMER:
				return getCancelTimer();
			case jpdl32Package.DOCUMENT_ROOT__CONTROLLER:
				return getController();
			case jpdl32Package.DOCUMENT_ROOT__CREATE_TIMER:
				return getCreateTimer();
			case jpdl32Package.DOCUMENT_ROOT__DECISION:
				return getDecision();
			case jpdl32Package.DOCUMENT_ROOT__DESCRIPTION:
				return getDescription();
			case jpdl32Package.DOCUMENT_ROOT__END_STATE:
				return getEndState();
			case jpdl32Package.DOCUMENT_ROOT__EVENT:
				return getEvent();
			case jpdl32Package.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				return getExceptionHandler();
			case jpdl32Package.DOCUMENT_ROOT__FORK:
				return getFork();
			case jpdl32Package.DOCUMENT_ROOT__JOIN:
				return getJoin();
			case jpdl32Package.DOCUMENT_ROOT__MAIL:
				return getMail();
			case jpdl32Package.DOCUMENT_ROOT__MAIL_NODE:
				return getMailNode();
			case jpdl32Package.DOCUMENT_ROOT__NODE:
				return getNode();
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_DEFINITION:
				return getProcessDefinition();
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_STATE:
				return getProcessState();
			case jpdl32Package.DOCUMENT_ROOT__RECIPIENTS:
				return getRecipients();
			case jpdl32Package.DOCUMENT_ROOT__SCRIPT:
				return getScript();
			case jpdl32Package.DOCUMENT_ROOT__START_STATE:
				return getStartState();
			case jpdl32Package.DOCUMENT_ROOT__STATE:
				return getState();
			case jpdl32Package.DOCUMENT_ROOT__SUBJECT:
				return getSubject();
			case jpdl32Package.DOCUMENT_ROOT__SUPER_STATE:
				return getSuperState();
			case jpdl32Package.DOCUMENT_ROOT__SWIMLANE:
				return getSwimlane();
			case jpdl32Package.DOCUMENT_ROOT__TASK:
				return getTask();
			case jpdl32Package.DOCUMENT_ROOT__TASK_NODE:
				return getTaskNode();
			case jpdl32Package.DOCUMENT_ROOT__TEMPLATE:
				return getTemplate();
			case jpdl32Package.DOCUMENT_ROOT__TEXT:
				return getText();
			case jpdl32Package.DOCUMENT_ROOT__TIMER:
				return getTimer();
			case jpdl32Package.DOCUMENT_ROOT__TO:
				return getTo();
			case jpdl32Package.DOCUMENT_ROOT__TRANSITION:
				return getTransition();
			case jpdl32Package.DOCUMENT_ROOT__VARIABLE:
				return getVariable();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case jpdl32Package.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__ACTION:
				setAction((ActionType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__ASSIGNMENT:
				setAssignment((AssignmentType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__CANCEL_TIMER:
				setCancelTimer((CancelTimerType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__CONTROLLER:
				setController((Delegation)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__CREATE_TIMER:
				setCreateTimer((CreateTimerType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__DECISION:
				setDecision((DecisionType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__END_STATE:
				setEndState((EndStateType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__EVENT:
				setEvent((EventType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				setExceptionHandler((ExceptionHandlerType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__FORK:
				setFork((ForkType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__JOIN:
				setJoin((JoinType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__MAIL:
				setMail((MailType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__MAIL_NODE:
				setMailNode((MailNodeType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__NODE:
				setNode((NodeType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_DEFINITION:
				setProcessDefinition((ProcessDefinitionType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_STATE:
				setProcessState((ProcessStateType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__RECIPIENTS:
				setRecipients((String)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__SCRIPT:
				setScript((ScriptType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__START_STATE:
				setStartState((StartStateType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__STATE:
				setState((StateType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__SUBJECT:
				setSubject((String)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__SUPER_STATE:
				setSuperState((SuperStateType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__SWIMLANE:
				setSwimlane((SwimlaneType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TASK:
				setTask((TaskType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TASK_NODE:
				setTaskNode((TaskNodeType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TEMPLATE:
				setTemplate((String)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TEXT:
				setText((String)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TIMER:
				setTimer((TimerType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TO:
				setTo((String)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TRANSITION:
				setTransition((TransitionType)newValue);
				return;
			case jpdl32Package.DOCUMENT_ROOT__VARIABLE:
				setVariable((VariableType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case jpdl32Package.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case jpdl32Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case jpdl32Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case jpdl32Package.DOCUMENT_ROOT__ACTION:
				setAction((ActionType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__ASSIGNMENT:
				setAssignment((AssignmentType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__CANCEL_TIMER:
				setCancelTimer((CancelTimerType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__CONTROLLER:
				setController((Delegation)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__CREATE_TIMER:
				setCreateTimer((CreateTimerType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__DECISION:
				setDecision((DecisionType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case jpdl32Package.DOCUMENT_ROOT__END_STATE:
				setEndState((EndStateType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__EVENT:
				setEvent((EventType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				setExceptionHandler((ExceptionHandlerType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__FORK:
				setFork((ForkType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__JOIN:
				setJoin((JoinType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__MAIL:
				setMail((MailType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__MAIL_NODE:
				setMailNode((MailNodeType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__NODE:
				setNode((NodeType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_DEFINITION:
				setProcessDefinition((ProcessDefinitionType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_STATE:
				setProcessState((ProcessStateType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__RECIPIENTS:
				setRecipients(RECIPIENTS_EDEFAULT);
				return;
			case jpdl32Package.DOCUMENT_ROOT__SCRIPT:
				setScript((ScriptType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__START_STATE:
				setStartState((StartStateType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__STATE:
				setState((StateType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__SUBJECT:
				setSubject(SUBJECT_EDEFAULT);
				return;
			case jpdl32Package.DOCUMENT_ROOT__SUPER_STATE:
				setSuperState((SuperStateType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__SWIMLANE:
				setSwimlane((SwimlaneType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TASK:
				setTask((TaskType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TASK_NODE:
				setTaskNode((TaskNodeType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TEMPLATE:
				setTemplate(TEMPLATE_EDEFAULT);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TEXT:
				setText(TEXT_EDEFAULT);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TIMER:
				setTimer((TimerType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TO:
				setTo(TO_EDEFAULT);
				return;
			case jpdl32Package.DOCUMENT_ROOT__TRANSITION:
				setTransition((TransitionType)null);
				return;
			case jpdl32Package.DOCUMENT_ROOT__VARIABLE:
				setVariable((VariableType)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case jpdl32Package.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case jpdl32Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case jpdl32Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case jpdl32Package.DOCUMENT_ROOT__ACTION:
				return getAction() != null;
			case jpdl32Package.DOCUMENT_ROOT__ASSIGNMENT:
				return getAssignment() != null;
			case jpdl32Package.DOCUMENT_ROOT__CANCEL_TIMER:
				return getCancelTimer() != null;
			case jpdl32Package.DOCUMENT_ROOT__CONTROLLER:
				return getController() != null;
			case jpdl32Package.DOCUMENT_ROOT__CREATE_TIMER:
				return getCreateTimer() != null;
			case jpdl32Package.DOCUMENT_ROOT__DECISION:
				return getDecision() != null;
			case jpdl32Package.DOCUMENT_ROOT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case jpdl32Package.DOCUMENT_ROOT__END_STATE:
				return getEndState() != null;
			case jpdl32Package.DOCUMENT_ROOT__EVENT:
				return getEvent() != null;
			case jpdl32Package.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				return getExceptionHandler() != null;
			case jpdl32Package.DOCUMENT_ROOT__FORK:
				return getFork() != null;
			case jpdl32Package.DOCUMENT_ROOT__JOIN:
				return getJoin() != null;
			case jpdl32Package.DOCUMENT_ROOT__MAIL:
				return getMail() != null;
			case jpdl32Package.DOCUMENT_ROOT__MAIL_NODE:
				return getMailNode() != null;
			case jpdl32Package.DOCUMENT_ROOT__NODE:
				return getNode() != null;
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_DEFINITION:
				return getProcessDefinition() != null;
			case jpdl32Package.DOCUMENT_ROOT__PROCESS_STATE:
				return getProcessState() != null;
			case jpdl32Package.DOCUMENT_ROOT__RECIPIENTS:
				return RECIPIENTS_EDEFAULT == null ? getRecipients() != null : !RECIPIENTS_EDEFAULT.equals(getRecipients());
			case jpdl32Package.DOCUMENT_ROOT__SCRIPT:
				return getScript() != null;
			case jpdl32Package.DOCUMENT_ROOT__START_STATE:
				return getStartState() != null;
			case jpdl32Package.DOCUMENT_ROOT__STATE:
				return getState() != null;
			case jpdl32Package.DOCUMENT_ROOT__SUBJECT:
				return SUBJECT_EDEFAULT == null ? getSubject() != null : !SUBJECT_EDEFAULT.equals(getSubject());
			case jpdl32Package.DOCUMENT_ROOT__SUPER_STATE:
				return getSuperState() != null;
			case jpdl32Package.DOCUMENT_ROOT__SWIMLANE:
				return getSwimlane() != null;
			case jpdl32Package.DOCUMENT_ROOT__TASK:
				return getTask() != null;
			case jpdl32Package.DOCUMENT_ROOT__TASK_NODE:
				return getTaskNode() != null;
			case jpdl32Package.DOCUMENT_ROOT__TEMPLATE:
				return TEMPLATE_EDEFAULT == null ? getTemplate() != null : !TEMPLATE_EDEFAULT.equals(getTemplate());
			case jpdl32Package.DOCUMENT_ROOT__TEXT:
				return TEXT_EDEFAULT == null ? getText() != null : !TEXT_EDEFAULT.equals(getText());
			case jpdl32Package.DOCUMENT_ROOT__TIMER:
				return getTimer() != null;
			case jpdl32Package.DOCUMENT_ROOT__TO:
				return TO_EDEFAULT == null ? getTo() != null : !TO_EDEFAULT.equals(getTo());
			case jpdl32Package.DOCUMENT_ROOT__TRANSITION:
				return getTransition() != null;
			case jpdl32Package.DOCUMENT_ROOT__VARIABLE:
				return getVariable() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
