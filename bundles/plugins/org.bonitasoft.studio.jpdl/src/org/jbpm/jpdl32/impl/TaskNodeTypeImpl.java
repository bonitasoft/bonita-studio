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
import org.jbpm.jpdl32.BooleanType;
import org.jbpm.jpdl32.EventType;
import org.jbpm.jpdl32.ExceptionHandlerType;
import org.jbpm.jpdl32.SignalType;
import org.jbpm.jpdl32.TaskNodeType;
import org.jbpm.jpdl32.TaskType;
import org.jbpm.jpdl32.TimerType;
import org.jbpm.jpdl32.TransitionType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Node Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getTask <em>Task</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getAsync <em>Async</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getCreateTasks <em>Create Tasks</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getEndTasks <em>End Tasks</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl#getSignal <em>Signal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskNodeTypeImpl extends EObjectImpl implements TaskNodeType {
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
	 * The default value of the '{@link #getCreateTasks() <em>Create Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateTasks()
	 * @generated
	 * @ordered
	 */
	protected static final BooleanType CREATE_TASKS_EDEFAULT = BooleanType.TRUE;

	/**
	 * The cached value of the '{@link #getCreateTasks() <em>Create Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateTasks()
	 * @generated
	 * @ordered
	 */
	protected BooleanType createTasks = CREATE_TASKS_EDEFAULT;

	/**
	 * This is true if the Create Tasks attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean createTasksESet;

	/**
	 * The default value of the '{@link #getEndTasks() <em>End Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndTasks()
	 * @generated
	 * @ordered
	 */
	protected static final BooleanType END_TASKS_EDEFAULT = BooleanType.FALSE;

	/**
	 * The cached value of the '{@link #getEndTasks() <em>End Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndTasks()
	 * @generated
	 * @ordered
	 */
	protected BooleanType endTasks = END_TASKS_EDEFAULT;

	/**
	 * This is true if the End Tasks attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean endTasksESet;

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
	 * The default value of the '{@link #getSignal() <em>Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignal()
	 * @generated
	 * @ordered
	 */
	protected static final SignalType SIGNAL_EDEFAULT = SignalType.LAST;

	/**
	 * The cached value of the '{@link #getSignal() <em>Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignal()
	 * @generated
	 * @ordered
	 */
	protected SignalType signal = SIGNAL_EDEFAULT;

	/**
	 * This is true if the Signal attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean signalESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskNodeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.TASK_NODE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, jpdl32Package.TASK_NODE_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskType> getTask() {
		return getGroup().list(jpdl32Package.Literals.TASK_NODE_TYPE__TASK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDescription() {
		return getGroup().list(jpdl32Package.Literals.TASK_NODE_TYPE__DESCRIPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventType> getEvent() {
		return getGroup().list(jpdl32Package.Literals.TASK_NODE_TYPE__EVENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExceptionHandlerType> getExceptionHandler() {
		return getGroup().list(jpdl32Package.Literals.TASK_NODE_TYPE__EXCEPTION_HANDLER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimerType> getTimer() {
		return getGroup().list(jpdl32Package.Literals.TASK_NODE_TYPE__TIMER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransitionType> getTransition() {
		return getGroup().list(jpdl32Package.Literals.TASK_NODE_TYPE__TRANSITION);
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_NODE_TYPE__ASYNC, oldAsync, async, !oldAsyncESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.TASK_NODE_TYPE__ASYNC, oldAsync, ASYNC_EDEFAULT, oldAsyncESet));
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
	public BooleanType getCreateTasks() {
		return createTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreateTasks(BooleanType newCreateTasks) {
		BooleanType oldCreateTasks = createTasks;
		createTasks = newCreateTasks == null ? CREATE_TASKS_EDEFAULT : newCreateTasks;
		boolean oldCreateTasksESet = createTasksESet;
		createTasksESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_NODE_TYPE__CREATE_TASKS, oldCreateTasks, createTasks, !oldCreateTasksESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCreateTasks() {
		BooleanType oldCreateTasks = createTasks;
		boolean oldCreateTasksESet = createTasksESet;
		createTasks = CREATE_TASKS_EDEFAULT;
		createTasksESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.TASK_NODE_TYPE__CREATE_TASKS, oldCreateTasks, CREATE_TASKS_EDEFAULT, oldCreateTasksESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCreateTasks() {
		return createTasksESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanType getEndTasks() {
		return endTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndTasks(BooleanType newEndTasks) {
		BooleanType oldEndTasks = endTasks;
		endTasks = newEndTasks == null ? END_TASKS_EDEFAULT : newEndTasks;
		boolean oldEndTasksESet = endTasksESet;
		endTasksESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_NODE_TYPE__END_TASKS, oldEndTasks, endTasks, !oldEndTasksESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEndTasks() {
		BooleanType oldEndTasks = endTasks;
		boolean oldEndTasksESet = endTasksESet;
		endTasks = END_TASKS_EDEFAULT;
		endTasksESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.TASK_NODE_TYPE__END_TASKS, oldEndTasks, END_TASKS_EDEFAULT, oldEndTasksESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEndTasks() {
		return endTasksESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_NODE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SignalType getSignal() {
		return signal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignal(SignalType newSignal) {
		SignalType oldSignal = signal;
		signal = newSignal == null ? SIGNAL_EDEFAULT : newSignal;
		boolean oldSignalESet = signalESet;
		signalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_NODE_TYPE__SIGNAL, oldSignal, signal, !oldSignalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSignal() {
		SignalType oldSignal = signal;
		boolean oldSignalESet = signalESet;
		signal = SIGNAL_EDEFAULT;
		signalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.TASK_NODE_TYPE__SIGNAL, oldSignal, SIGNAL_EDEFAULT, oldSignalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSignal() {
		return signalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.TASK_NODE_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_NODE_TYPE__TASK:
				return ((InternalEList<?>)getTask()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_NODE_TYPE__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_NODE_TYPE__EXCEPTION_HANDLER:
				return ((InternalEList<?>)getExceptionHandler()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_NODE_TYPE__TIMER:
				return ((InternalEList<?>)getTimer()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_NODE_TYPE__TRANSITION:
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
			case jpdl32Package.TASK_NODE_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case jpdl32Package.TASK_NODE_TYPE__TASK:
				return getTask();
			case jpdl32Package.TASK_NODE_TYPE__DESCRIPTION:
				return getDescription();
			case jpdl32Package.TASK_NODE_TYPE__EVENT:
				return getEvent();
			case jpdl32Package.TASK_NODE_TYPE__EXCEPTION_HANDLER:
				return getExceptionHandler();
			case jpdl32Package.TASK_NODE_TYPE__TIMER:
				return getTimer();
			case jpdl32Package.TASK_NODE_TYPE__TRANSITION:
				return getTransition();
			case jpdl32Package.TASK_NODE_TYPE__ASYNC:
				return getAsync();
			case jpdl32Package.TASK_NODE_TYPE__CREATE_TASKS:
				return getCreateTasks();
			case jpdl32Package.TASK_NODE_TYPE__END_TASKS:
				return getEndTasks();
			case jpdl32Package.TASK_NODE_TYPE__NAME:
				return getName();
			case jpdl32Package.TASK_NODE_TYPE__SIGNAL:
				return getSignal();
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
			case jpdl32Package.TASK_NODE_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__TASK:
				getTask().clear();
				getTask().addAll((Collection<? extends TaskType>)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends String>)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends EventType>)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				getExceptionHandler().addAll((Collection<? extends ExceptionHandlerType>)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__TIMER:
				getTimer().clear();
				getTimer().addAll((Collection<? extends TimerType>)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__TRANSITION:
				getTransition().clear();
				getTransition().addAll((Collection<? extends TransitionType>)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__ASYNC:
				setAsync((String)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__CREATE_TASKS:
				setCreateTasks((BooleanType)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__END_TASKS:
				setEndTasks((BooleanType)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__NAME:
				setName((String)newValue);
				return;
			case jpdl32Package.TASK_NODE_TYPE__SIGNAL:
				setSignal((SignalType)newValue);
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
			case jpdl32Package.TASK_NODE_TYPE__GROUP:
				getGroup().clear();
				return;
			case jpdl32Package.TASK_NODE_TYPE__TASK:
				getTask().clear();
				return;
			case jpdl32Package.TASK_NODE_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case jpdl32Package.TASK_NODE_TYPE__EVENT:
				getEvent().clear();
				return;
			case jpdl32Package.TASK_NODE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				return;
			case jpdl32Package.TASK_NODE_TYPE__TIMER:
				getTimer().clear();
				return;
			case jpdl32Package.TASK_NODE_TYPE__TRANSITION:
				getTransition().clear();
				return;
			case jpdl32Package.TASK_NODE_TYPE__ASYNC:
				unsetAsync();
				return;
			case jpdl32Package.TASK_NODE_TYPE__CREATE_TASKS:
				unsetCreateTasks();
				return;
			case jpdl32Package.TASK_NODE_TYPE__END_TASKS:
				unsetEndTasks();
				return;
			case jpdl32Package.TASK_NODE_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case jpdl32Package.TASK_NODE_TYPE__SIGNAL:
				unsetSignal();
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
			case jpdl32Package.TASK_NODE_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case jpdl32Package.TASK_NODE_TYPE__TASK:
				return !getTask().isEmpty();
			case jpdl32Package.TASK_NODE_TYPE__DESCRIPTION:
				return !getDescription().isEmpty();
			case jpdl32Package.TASK_NODE_TYPE__EVENT:
				return !getEvent().isEmpty();
			case jpdl32Package.TASK_NODE_TYPE__EXCEPTION_HANDLER:
				return !getExceptionHandler().isEmpty();
			case jpdl32Package.TASK_NODE_TYPE__TIMER:
				return !getTimer().isEmpty();
			case jpdl32Package.TASK_NODE_TYPE__TRANSITION:
				return !getTransition().isEmpty();
			case jpdl32Package.TASK_NODE_TYPE__ASYNC:
				return isSetAsync();
			case jpdl32Package.TASK_NODE_TYPE__CREATE_TASKS:
				return isSetCreateTasks();
			case jpdl32Package.TASK_NODE_TYPE__END_TASKS:
				return isSetEndTasks();
			case jpdl32Package.TASK_NODE_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case jpdl32Package.TASK_NODE_TYPE__SIGNAL:
				return isSetSignal();
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
		result.append(", createTasks: ");
		if (createTasksESet) result.append(createTasks); else result.append("<unset>");
		result.append(", endTasks: ");
		if (endTasksESet) result.append(endTasks); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(", signal: ");
		if (signalESet) result.append(signal); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TaskNodeTypeImpl
