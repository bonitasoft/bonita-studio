/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jbpm.jpdl32.ActionType;
import org.jbpm.jpdl32.CancelTimerType;
import org.jbpm.jpdl32.CreateTimerType;
import org.jbpm.jpdl32.DecisionType;
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
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Definition Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getStartState <em>Start State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getState <em>State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getTaskNode <em>Task Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getSuperState <em>Super State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getProcessState <em>Process State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getFork <em>Fork</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getDecision <em>Decision</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getEndState <em>End State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getMailNode <em>Mail Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getAction <em>Action</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getCreateTimer <em>Create Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getCancelTimer <em>Cancel Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getMail <em>Mail</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getTask <em>Task</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessDefinitionTypeImpl extends EObjectImpl implements ProcessDefinitionType {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessDefinitionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.PROCESS_DEFINITION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, jpdl32Package.PROCESS_DEFINITION_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDescription() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__DESCRIPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SwimlaneType> getSwimlane() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__SWIMLANE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StartStateType> getStartState() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__START_STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NodeType> getNode() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StateType> getState() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskNodeType> getTaskNode() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__TASK_NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SuperStateType> getSuperState() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__SUPER_STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProcessStateType> getProcessState() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__PROCESS_STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ForkType> getFork() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__FORK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JoinType> getJoin() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__JOIN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DecisionType> getDecision() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__DECISION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EndStateType> getEndState() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__END_STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MailNodeType> getMailNode() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__MAIL_NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActionType> getAction() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__ACTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScriptType> getScript() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__SCRIPT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CreateTimerType> getCreateTimer() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__CREATE_TIMER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CancelTimerType> getCancelTimer() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__CANCEL_TIMER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MailType> getMail() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__MAIL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventType> getEvent() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__EVENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExceptionHandlerType> getExceptionHandler() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__EXCEPTION_HANDLER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskType> getTask() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_DEFINITION_TYPE__TASK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.PROCESS_DEFINITION_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.PROCESS_DEFINITION_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SWIMLANE:
				return ((InternalEList<?>)getSwimlane()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__START_STATE:
				return ((InternalEList<?>)getStartState()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__NODE:
				return ((InternalEList<?>)getNode()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__STATE:
				return ((InternalEList<?>)getState()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK_NODE:
				return ((InternalEList<?>)getTaskNode()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SUPER_STATE:
				return ((InternalEList<?>)getSuperState()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__PROCESS_STATE:
				return ((InternalEList<?>)getProcessState()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__FORK:
				return ((InternalEList<?>)getFork()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__JOIN:
				return ((InternalEList<?>)getJoin()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__DECISION:
				return ((InternalEList<?>)getDecision()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__END_STATE:
				return ((InternalEList<?>)getEndState()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL_NODE:
				return ((InternalEList<?>)getMailNode()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__ACTION:
				return ((InternalEList<?>)getAction()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SCRIPT:
				return ((InternalEList<?>)getScript()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CREATE_TIMER:
				return ((InternalEList<?>)getCreateTimer()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CANCEL_TIMER:
				return ((InternalEList<?>)getCancelTimer()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL:
				return ((InternalEList<?>)getMail()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EXCEPTION_HANDLER:
				return ((InternalEList<?>)getExceptionHandler()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK:
				return ((InternalEList<?>)getTask()).basicRemove(otherEnd, msgs);
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
			case jpdl32Package.PROCESS_DEFINITION_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__DESCRIPTION:
				return getDescription();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SWIMLANE:
				return getSwimlane();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__START_STATE:
				return getStartState();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__NODE:
				return getNode();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__STATE:
				return getState();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK_NODE:
				return getTaskNode();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SUPER_STATE:
				return getSuperState();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__PROCESS_STATE:
				return getProcessState();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__FORK:
				return getFork();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__JOIN:
				return getJoin();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__DECISION:
				return getDecision();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__END_STATE:
				return getEndState();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL_NODE:
				return getMailNode();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__ACTION:
				return getAction();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SCRIPT:
				return getScript();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CREATE_TIMER:
				return getCreateTimer();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CANCEL_TIMER:
				return getCancelTimer();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL:
				return getMail();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EVENT:
				return getEvent();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EXCEPTION_HANDLER:
				return getExceptionHandler();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK:
				return getTask();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case jpdl32Package.PROCESS_DEFINITION_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends String>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SWIMLANE:
				getSwimlane().clear();
				getSwimlane().addAll((Collection<? extends SwimlaneType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__START_STATE:
				getStartState().clear();
				getStartState().addAll((Collection<? extends StartStateType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__NODE:
				getNode().clear();
				getNode().addAll((Collection<? extends NodeType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__STATE:
				getState().clear();
				getState().addAll((Collection<? extends StateType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK_NODE:
				getTaskNode().clear();
				getTaskNode().addAll((Collection<? extends TaskNodeType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SUPER_STATE:
				getSuperState().clear();
				getSuperState().addAll((Collection<? extends SuperStateType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__PROCESS_STATE:
				getProcessState().clear();
				getProcessState().addAll((Collection<? extends ProcessStateType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__FORK:
				getFork().clear();
				getFork().addAll((Collection<? extends ForkType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__JOIN:
				getJoin().clear();
				getJoin().addAll((Collection<? extends JoinType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__DECISION:
				getDecision().clear();
				getDecision().addAll((Collection<? extends DecisionType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__END_STATE:
				getEndState().clear();
				getEndState().addAll((Collection<? extends EndStateType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL_NODE:
				getMailNode().clear();
				getMailNode().addAll((Collection<? extends MailNodeType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__ACTION:
				getAction().clear();
				getAction().addAll((Collection<? extends ActionType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SCRIPT:
				getScript().clear();
				getScript().addAll((Collection<? extends ScriptType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CREATE_TIMER:
				getCreateTimer().clear();
				getCreateTimer().addAll((Collection<? extends CreateTimerType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CANCEL_TIMER:
				getCancelTimer().clear();
				getCancelTimer().addAll((Collection<? extends CancelTimerType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL:
				getMail().clear();
				getMail().addAll((Collection<? extends MailType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends EventType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				getExceptionHandler().addAll((Collection<? extends ExceptionHandlerType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK:
				getTask().clear();
				getTask().addAll((Collection<? extends TaskType>)newValue);
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__NAME:
				setName((String)newValue);
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
			case jpdl32Package.PROCESS_DEFINITION_TYPE__GROUP:
				getGroup().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SWIMLANE:
				getSwimlane().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__START_STATE:
				getStartState().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__NODE:
				getNode().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__STATE:
				getState().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK_NODE:
				getTaskNode().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SUPER_STATE:
				getSuperState().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__PROCESS_STATE:
				getProcessState().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__FORK:
				getFork().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__JOIN:
				getJoin().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__DECISION:
				getDecision().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__END_STATE:
				getEndState().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL_NODE:
				getMailNode().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__ACTION:
				getAction().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SCRIPT:
				getScript().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CREATE_TIMER:
				getCreateTimer().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CANCEL_TIMER:
				getCancelTimer().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL:
				getMail().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EVENT:
				getEvent().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK:
				getTask().clear();
				return;
			case jpdl32Package.PROCESS_DEFINITION_TYPE__NAME:
				setName(NAME_EDEFAULT);
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
			case jpdl32Package.PROCESS_DEFINITION_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__DESCRIPTION:
				return !getDescription().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SWIMLANE:
				return !getSwimlane().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__START_STATE:
				return !getStartState().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__NODE:
				return !getNode().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__STATE:
				return !getState().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK_NODE:
				return !getTaskNode().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SUPER_STATE:
				return !getSuperState().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__PROCESS_STATE:
				return !getProcessState().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__FORK:
				return !getFork().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__JOIN:
				return !getJoin().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__DECISION:
				return !getDecision().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__END_STATE:
				return !getEndState().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL_NODE:
				return !getMailNode().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__ACTION:
				return !getAction().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__SCRIPT:
				return !getScript().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CREATE_TIMER:
				return !getCreateTimer().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__CANCEL_TIMER:
				return !getCancelTimer().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__MAIL:
				return !getMail().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EVENT:
				return !getEvent().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__EXCEPTION_HANDLER:
				return !getExceptionHandler().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__TASK:
				return !getTask().isEmpty();
			case jpdl32Package.PROCESS_DEFINITION_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (group: ");
		result.append(group);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ProcessDefinitionTypeImpl
