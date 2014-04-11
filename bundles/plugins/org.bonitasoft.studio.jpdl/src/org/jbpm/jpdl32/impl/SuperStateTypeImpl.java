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
import org.jbpm.jpdl32.DecisionType;
import org.jbpm.jpdl32.EndStateType;
import org.jbpm.jpdl32.EventType;
import org.jbpm.jpdl32.ExceptionHandlerType;
import org.jbpm.jpdl32.ForkType;
import org.jbpm.jpdl32.JoinType;
import org.jbpm.jpdl32.MailNodeType;
import org.jbpm.jpdl32.NodeType;
import org.jbpm.jpdl32.ProcessStateType;
import org.jbpm.jpdl32.StateType;
import org.jbpm.jpdl32.SuperStateType;
import org.jbpm.jpdl32.TaskNodeType;
import org.jbpm.jpdl32.TimerType;
import org.jbpm.jpdl32.TransitionType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Super State Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getState <em>State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getTaskNode <em>Task Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getSuperState <em>Super State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getProcessState <em>Process State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getFork <em>Fork</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getDecision <em>Decision</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getEndState <em>End State</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getMailNode <em>Mail Node</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getAsync <em>Async</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperStateTypeImpl extends EObjectImpl implements SuperStateType {
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
	 * The default value of the '{@link #getAsync() <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsync()
	 * @generated
	 * @ordered
	 */
	protected static final String ASYNC_EDEFAULT = "false";

	/**
	 * The cached value of the '{@link #getAsync() <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsync()
	 * @generated
	 * @ordered
	 */
	protected String async = ASYNC_EDEFAULT;

	/**
	 * This is true if the Async attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean asyncESet;

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
	protected SuperStateTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.SUPER_STATE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, jpdl32Package.SUPER_STATE_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NodeType> getNode() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StateType> getState() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskNodeType> getTaskNode() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__TASK_NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SuperStateType> getSuperState() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__SUPER_STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProcessStateType> getProcessState() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__PROCESS_STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ForkType> getFork() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__FORK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JoinType> getJoin() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__JOIN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DecisionType> getDecision() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__DECISION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EndStateType> getEndState() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__END_STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MailNodeType> getMailNode() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__MAIL_NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDescription() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__DESCRIPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventType> getEvent() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__EVENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExceptionHandlerType> getExceptionHandler() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__EXCEPTION_HANDLER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimerType> getTimer() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__TIMER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransitionType> getTransition() {
		return getGroup().list(jpdl32Package.Literals.SUPER_STATE_TYPE__TRANSITION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAsync() {
		return async;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsync(String newAsync) {
		String oldAsync = async;
		async = newAsync;
		boolean oldAsyncESet = asyncESet;
		asyncESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.SUPER_STATE_TYPE__ASYNC, oldAsync, async, !oldAsyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAsync() {
		String oldAsync = async;
		boolean oldAsyncESet = asyncESet;
		async = ASYNC_EDEFAULT;
		asyncESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.SUPER_STATE_TYPE__ASYNC, oldAsync, ASYNC_EDEFAULT, oldAsyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAsync() {
		return asyncESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.SUPER_STATE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.SUPER_STATE_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__NODE:
				return ((InternalEList<?>)getNode()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__STATE:
				return ((InternalEList<?>)getState()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__TASK_NODE:
				return ((InternalEList<?>)getTaskNode()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__SUPER_STATE:
				return ((InternalEList<?>)getSuperState()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__PROCESS_STATE:
				return ((InternalEList<?>)getProcessState()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__FORK:
				return ((InternalEList<?>)getFork()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__JOIN:
				return ((InternalEList<?>)getJoin()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__DECISION:
				return ((InternalEList<?>)getDecision()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__END_STATE:
				return ((InternalEList<?>)getEndState()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__MAIL_NODE:
				return ((InternalEList<?>)getMailNode()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__EXCEPTION_HANDLER:
				return ((InternalEList<?>)getExceptionHandler()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__TIMER:
				return ((InternalEList<?>)getTimer()).basicRemove(otherEnd, msgs);
			case jpdl32Package.SUPER_STATE_TYPE__TRANSITION:
				return ((InternalEList<?>)getTransition()).basicRemove(otherEnd, msgs);
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
			case jpdl32Package.SUPER_STATE_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case jpdl32Package.SUPER_STATE_TYPE__NODE:
				return getNode();
			case jpdl32Package.SUPER_STATE_TYPE__STATE:
				return getState();
			case jpdl32Package.SUPER_STATE_TYPE__TASK_NODE:
				return getTaskNode();
			case jpdl32Package.SUPER_STATE_TYPE__SUPER_STATE:
				return getSuperState();
			case jpdl32Package.SUPER_STATE_TYPE__PROCESS_STATE:
				return getProcessState();
			case jpdl32Package.SUPER_STATE_TYPE__FORK:
				return getFork();
			case jpdl32Package.SUPER_STATE_TYPE__JOIN:
				return getJoin();
			case jpdl32Package.SUPER_STATE_TYPE__DECISION:
				return getDecision();
			case jpdl32Package.SUPER_STATE_TYPE__END_STATE:
				return getEndState();
			case jpdl32Package.SUPER_STATE_TYPE__MAIL_NODE:
				return getMailNode();
			case jpdl32Package.SUPER_STATE_TYPE__DESCRIPTION:
				return getDescription();
			case jpdl32Package.SUPER_STATE_TYPE__EVENT:
				return getEvent();
			case jpdl32Package.SUPER_STATE_TYPE__EXCEPTION_HANDLER:
				return getExceptionHandler();
			case jpdl32Package.SUPER_STATE_TYPE__TIMER:
				return getTimer();
			case jpdl32Package.SUPER_STATE_TYPE__TRANSITION:
				return getTransition();
			case jpdl32Package.SUPER_STATE_TYPE__ASYNC:
				return getAsync();
			case jpdl32Package.SUPER_STATE_TYPE__NAME:
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
			case jpdl32Package.SUPER_STATE_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__NODE:
				getNode().clear();
				getNode().addAll((Collection<? extends NodeType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__STATE:
				getState().clear();
				getState().addAll((Collection<? extends StateType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__TASK_NODE:
				getTaskNode().clear();
				getTaskNode().addAll((Collection<? extends TaskNodeType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__SUPER_STATE:
				getSuperState().clear();
				getSuperState().addAll((Collection<? extends SuperStateType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__PROCESS_STATE:
				getProcessState().clear();
				getProcessState().addAll((Collection<? extends ProcessStateType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__FORK:
				getFork().clear();
				getFork().addAll((Collection<? extends ForkType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__JOIN:
				getJoin().clear();
				getJoin().addAll((Collection<? extends JoinType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__DECISION:
				getDecision().clear();
				getDecision().addAll((Collection<? extends DecisionType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__END_STATE:
				getEndState().clear();
				getEndState().addAll((Collection<? extends EndStateType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__MAIL_NODE:
				getMailNode().clear();
				getMailNode().addAll((Collection<? extends MailNodeType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends String>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends EventType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				getExceptionHandler().addAll((Collection<? extends ExceptionHandlerType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__TIMER:
				getTimer().clear();
				getTimer().addAll((Collection<? extends TimerType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__TRANSITION:
				getTransition().clear();
				getTransition().addAll((Collection<? extends TransitionType>)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__ASYNC:
				setAsync((String)newValue);
				return;
			case jpdl32Package.SUPER_STATE_TYPE__NAME:
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
			case jpdl32Package.SUPER_STATE_TYPE__GROUP:
				getGroup().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__NODE:
				getNode().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__STATE:
				getState().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__TASK_NODE:
				getTaskNode().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__SUPER_STATE:
				getSuperState().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__PROCESS_STATE:
				getProcessState().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__FORK:
				getFork().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__JOIN:
				getJoin().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__DECISION:
				getDecision().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__END_STATE:
				getEndState().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__MAIL_NODE:
				getMailNode().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__EVENT:
				getEvent().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__TIMER:
				getTimer().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__TRANSITION:
				getTransition().clear();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__ASYNC:
				unsetAsync();
				return;
			case jpdl32Package.SUPER_STATE_TYPE__NAME:
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
			case jpdl32Package.SUPER_STATE_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__NODE:
				return !getNode().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__STATE:
				return !getState().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__TASK_NODE:
				return !getTaskNode().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__SUPER_STATE:
				return !getSuperState().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__PROCESS_STATE:
				return !getProcessState().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__FORK:
				return !getFork().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__JOIN:
				return !getJoin().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__DECISION:
				return !getDecision().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__END_STATE:
				return !getEndState().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__MAIL_NODE:
				return !getMailNode().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__DESCRIPTION:
				return !getDescription().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__EVENT:
				return !getEvent().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__EXCEPTION_HANDLER:
				return !getExceptionHandler().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__TIMER:
				return !getTimer().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__TRANSITION:
				return !getTransition().isEmpty();
			case jpdl32Package.SUPER_STATE_TYPE__ASYNC:
				return isSetAsync();
			case jpdl32Package.SUPER_STATE_TYPE__NAME:
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
		result.append(", async: ");
		if (asyncESet) result.append(async); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //SuperStateTypeImpl
