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
import org.jbpm.jpdl32.AssignmentType;
import org.jbpm.jpdl32.BooleanType;
import org.jbpm.jpdl32.Delegation;
import org.jbpm.jpdl32.EventType;
import org.jbpm.jpdl32.ReminderType;
import org.jbpm.jpdl32.TaskType;
import org.jbpm.jpdl32.TimerType;
import org.jbpm.jpdl32.jpdl32Factory;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getController <em>Controller</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getReminder <em>Reminder</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getBlocking <em>Blocking</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getDescription1 <em>Description1</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getDuedate <em>Duedate</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getNotify <em>Notify</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getSignalling <em>Signalling</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TaskTypeImpl#getSwimlane <em>Swimlane</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskTypeImpl extends EObjectImpl implements TaskType {
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
	 * The default value of the '{@link #getBlocking() <em>Blocking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlocking()
	 * @generated
	 * @ordered
	 */
	protected static final BooleanType BLOCKING_EDEFAULT = BooleanType.FALSE;

	/**
	 * The cached value of the '{@link #getBlocking() <em>Blocking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlocking()
	 * @generated
	 * @ordered
	 */
	protected BooleanType blocking = BLOCKING_EDEFAULT;

	/**
	 * This is true if the Blocking attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean blockingESet;

	/**
	 * The default value of the '{@link #getDescription1() <em>Description1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription1()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription1() <em>Description1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription1()
	 * @generated
	 * @ordered
	 */
	protected String description1 = DESCRIPTION1_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuedate() <em>Duedate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuedate()
	 * @generated
	 * @ordered
	 */
	protected static final String DUEDATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDuedate() <em>Duedate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuedate()
	 * @generated
	 * @ordered
	 */
	protected String duedate = DUEDATE_EDEFAULT;

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
	 * The default value of the '{@link #getNotify() <em>Notify</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotify()
	 * @generated
	 * @ordered
	 */
	protected static final BooleanType NOTIFY_EDEFAULT = BooleanType.FALSE;

	/**
	 * The cached value of the '{@link #getNotify() <em>Notify</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotify()
	 * @generated
	 * @ordered
	 */
	protected BooleanType notify = NOTIFY_EDEFAULT;

	/**
	 * This is true if the Notify attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean notifyESet;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final Object PRIORITY_EDEFAULT = jpdl32Factory.eINSTANCE.createFromString(jpdl32Package.eINSTANCE.getPriorityType(), "normal");

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected Object priority = PRIORITY_EDEFAULT;

	/**
	 * This is true if the Priority attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean priorityESet;

	/**
	 * The default value of the '{@link #getSignalling() <em>Signalling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignalling()
	 * @generated
	 * @ordered
	 */
	protected static final BooleanType SIGNALLING_EDEFAULT = BooleanType.TRUE;

	/**
	 * The cached value of the '{@link #getSignalling() <em>Signalling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignalling()
	 * @generated
	 * @ordered
	 */
	protected BooleanType signalling = SIGNALLING_EDEFAULT;

	/**
	 * This is true if the Signalling attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean signallingESet;

	/**
	 * The default value of the '{@link #getSwimlane() <em>Swimlane</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwimlane()
	 * @generated
	 * @ordered
	 */
	protected static final String SWIMLANE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSwimlane() <em>Swimlane</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwimlane()
	 * @generated
	 * @ordered
	 */
	protected String swimlane = SWIMLANE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.TASK_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, jpdl32Package.TASK_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDescription() {
		return getGroup().list(jpdl32Package.Literals.TASK_TYPE__DESCRIPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssignmentType> getAssignment() {
		return getGroup().list(jpdl32Package.Literals.TASK_TYPE__ASSIGNMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Delegation> getController() {
		return getGroup().list(jpdl32Package.Literals.TASK_TYPE__CONTROLLER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventType> getEvent() {
		return getGroup().list(jpdl32Package.Literals.TASK_TYPE__EVENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimerType> getTimer() {
		return getGroup().list(jpdl32Package.Literals.TASK_TYPE__TIMER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ReminderType> getReminder() {
		return getGroup().list(jpdl32Package.Literals.TASK_TYPE__REMINDER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanType getBlocking() {
		return blocking;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlocking(BooleanType newBlocking) {
		BooleanType oldBlocking = blocking;
		blocking = newBlocking == null ? BLOCKING_EDEFAULT : newBlocking;
		boolean oldBlockingESet = blockingESet;
		blockingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_TYPE__BLOCKING, oldBlocking, blocking, !oldBlockingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBlocking() {
		BooleanType oldBlocking = blocking;
		boolean oldBlockingESet = blockingESet;
		blocking = BLOCKING_EDEFAULT;
		blockingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.TASK_TYPE__BLOCKING, oldBlocking, BLOCKING_EDEFAULT, oldBlockingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBlocking() {
		return blockingESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription1() {
		return description1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription1(String newDescription1) {
		String oldDescription1 = description1;
		description1 = newDescription1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_TYPE__DESCRIPTION1, oldDescription1, description1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDuedate() {
		return duedate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuedate(String newDuedate) {
		String oldDuedate = duedate;
		duedate = newDuedate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_TYPE__DUEDATE, oldDuedate, duedate));
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanType getNotify() {
		return notify;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotify(BooleanType newNotify) {
		BooleanType oldNotify = notify;
		notify = newNotify == null ? NOTIFY_EDEFAULT : newNotify;
		boolean oldNotifyESet = notifyESet;
		notifyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_TYPE__NOTIFY, oldNotify, notify, !oldNotifyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetNotify() {
		BooleanType oldNotify = notify;
		boolean oldNotifyESet = notifyESet;
		notify = NOTIFY_EDEFAULT;
		notifyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.TASK_TYPE__NOTIFY, oldNotify, NOTIFY_EDEFAULT, oldNotifyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetNotify() {
		return notifyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(Object newPriority) {
		Object oldPriority = priority;
		priority = newPriority;
		boolean oldPriorityESet = priorityESet;
		priorityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_TYPE__PRIORITY, oldPriority, priority, !oldPriorityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPriority() {
		Object oldPriority = priority;
		boolean oldPriorityESet = priorityESet;
		priority = PRIORITY_EDEFAULT;
		priorityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.TASK_TYPE__PRIORITY, oldPriority, PRIORITY_EDEFAULT, oldPriorityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPriority() {
		return priorityESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanType getSignalling() {
		return signalling;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignalling(BooleanType newSignalling) {
		BooleanType oldSignalling = signalling;
		signalling = newSignalling == null ? SIGNALLING_EDEFAULT : newSignalling;
		boolean oldSignallingESet = signallingESet;
		signallingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_TYPE__SIGNALLING, oldSignalling, signalling, !oldSignallingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSignalling() {
		BooleanType oldSignalling = signalling;
		boolean oldSignallingESet = signallingESet;
		signalling = SIGNALLING_EDEFAULT;
		signallingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.TASK_TYPE__SIGNALLING, oldSignalling, SIGNALLING_EDEFAULT, oldSignallingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSignalling() {
		return signallingESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSwimlane() {
		return swimlane;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwimlane(String newSwimlane) {
		String oldSwimlane = swimlane;
		swimlane = newSwimlane;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TASK_TYPE__SWIMLANE, oldSwimlane, swimlane));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.TASK_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_TYPE__ASSIGNMENT:
				return ((InternalEList<?>)getAssignment()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_TYPE__CONTROLLER:
				return ((InternalEList<?>)getController()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_TYPE__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_TYPE__TIMER:
				return ((InternalEList<?>)getTimer()).basicRemove(otherEnd, msgs);
			case jpdl32Package.TASK_TYPE__REMINDER:
				return ((InternalEList<?>)getReminder()).basicRemove(otherEnd, msgs);
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
			case jpdl32Package.TASK_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case jpdl32Package.TASK_TYPE__DESCRIPTION:
				return getDescription();
			case jpdl32Package.TASK_TYPE__ASSIGNMENT:
				return getAssignment();
			case jpdl32Package.TASK_TYPE__CONTROLLER:
				return getController();
			case jpdl32Package.TASK_TYPE__EVENT:
				return getEvent();
			case jpdl32Package.TASK_TYPE__TIMER:
				return getTimer();
			case jpdl32Package.TASK_TYPE__REMINDER:
				return getReminder();
			case jpdl32Package.TASK_TYPE__BLOCKING:
				return getBlocking();
			case jpdl32Package.TASK_TYPE__DESCRIPTION1:
				return getDescription1();
			case jpdl32Package.TASK_TYPE__DUEDATE:
				return getDuedate();
			case jpdl32Package.TASK_TYPE__NAME:
				return getName();
			case jpdl32Package.TASK_TYPE__NOTIFY:
				return getNotify();
			case jpdl32Package.TASK_TYPE__PRIORITY:
				return getPriority();
			case jpdl32Package.TASK_TYPE__SIGNALLING:
				return getSignalling();
			case jpdl32Package.TASK_TYPE__SWIMLANE:
				return getSwimlane();
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
			case jpdl32Package.TASK_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case jpdl32Package.TASK_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends String>)newValue);
				return;
			case jpdl32Package.TASK_TYPE__ASSIGNMENT:
				getAssignment().clear();
				getAssignment().addAll((Collection<? extends AssignmentType>)newValue);
				return;
			case jpdl32Package.TASK_TYPE__CONTROLLER:
				getController().clear();
				getController().addAll((Collection<? extends Delegation>)newValue);
				return;
			case jpdl32Package.TASK_TYPE__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends EventType>)newValue);
				return;
			case jpdl32Package.TASK_TYPE__TIMER:
				getTimer().clear();
				getTimer().addAll((Collection<? extends TimerType>)newValue);
				return;
			case jpdl32Package.TASK_TYPE__REMINDER:
				getReminder().clear();
				getReminder().addAll((Collection<? extends ReminderType>)newValue);
				return;
			case jpdl32Package.TASK_TYPE__BLOCKING:
				setBlocking((BooleanType)newValue);
				return;
			case jpdl32Package.TASK_TYPE__DESCRIPTION1:
				setDescription1((String)newValue);
				return;
			case jpdl32Package.TASK_TYPE__DUEDATE:
				setDuedate((String)newValue);
				return;
			case jpdl32Package.TASK_TYPE__NAME:
				setName((String)newValue);
				return;
			case jpdl32Package.TASK_TYPE__NOTIFY:
				setNotify((BooleanType)newValue);
				return;
			case jpdl32Package.TASK_TYPE__PRIORITY:
				setPriority(newValue);
				return;
			case jpdl32Package.TASK_TYPE__SIGNALLING:
				setSignalling((BooleanType)newValue);
				return;
			case jpdl32Package.TASK_TYPE__SWIMLANE:
				setSwimlane((String)newValue);
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
			case jpdl32Package.TASK_TYPE__GROUP:
				getGroup().clear();
				return;
			case jpdl32Package.TASK_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case jpdl32Package.TASK_TYPE__ASSIGNMENT:
				getAssignment().clear();
				return;
			case jpdl32Package.TASK_TYPE__CONTROLLER:
				getController().clear();
				return;
			case jpdl32Package.TASK_TYPE__EVENT:
				getEvent().clear();
				return;
			case jpdl32Package.TASK_TYPE__TIMER:
				getTimer().clear();
				return;
			case jpdl32Package.TASK_TYPE__REMINDER:
				getReminder().clear();
				return;
			case jpdl32Package.TASK_TYPE__BLOCKING:
				unsetBlocking();
				return;
			case jpdl32Package.TASK_TYPE__DESCRIPTION1:
				setDescription1(DESCRIPTION1_EDEFAULT);
				return;
			case jpdl32Package.TASK_TYPE__DUEDATE:
				setDuedate(DUEDATE_EDEFAULT);
				return;
			case jpdl32Package.TASK_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case jpdl32Package.TASK_TYPE__NOTIFY:
				unsetNotify();
				return;
			case jpdl32Package.TASK_TYPE__PRIORITY:
				unsetPriority();
				return;
			case jpdl32Package.TASK_TYPE__SIGNALLING:
				unsetSignalling();
				return;
			case jpdl32Package.TASK_TYPE__SWIMLANE:
				setSwimlane(SWIMLANE_EDEFAULT);
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
			case jpdl32Package.TASK_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case jpdl32Package.TASK_TYPE__DESCRIPTION:
				return !getDescription().isEmpty();
			case jpdl32Package.TASK_TYPE__ASSIGNMENT:
				return !getAssignment().isEmpty();
			case jpdl32Package.TASK_TYPE__CONTROLLER:
				return !getController().isEmpty();
			case jpdl32Package.TASK_TYPE__EVENT:
				return !getEvent().isEmpty();
			case jpdl32Package.TASK_TYPE__TIMER:
				return !getTimer().isEmpty();
			case jpdl32Package.TASK_TYPE__REMINDER:
				return !getReminder().isEmpty();
			case jpdl32Package.TASK_TYPE__BLOCKING:
				return isSetBlocking();
			case jpdl32Package.TASK_TYPE__DESCRIPTION1:
				return DESCRIPTION1_EDEFAULT == null ? description1 != null : !DESCRIPTION1_EDEFAULT.equals(description1);
			case jpdl32Package.TASK_TYPE__DUEDATE:
				return DUEDATE_EDEFAULT == null ? duedate != null : !DUEDATE_EDEFAULT.equals(duedate);
			case jpdl32Package.TASK_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case jpdl32Package.TASK_TYPE__NOTIFY:
				return isSetNotify();
			case jpdl32Package.TASK_TYPE__PRIORITY:
				return isSetPriority();
			case jpdl32Package.TASK_TYPE__SIGNALLING:
				return isSetSignalling();
			case jpdl32Package.TASK_TYPE__SWIMLANE:
				return SWIMLANE_EDEFAULT == null ? swimlane != null : !SWIMLANE_EDEFAULT.equals(swimlane);
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
		result.append(", blocking: ");
		if (blockingESet) result.append(blocking); else result.append("<unset>");
		result.append(", description1: ");
		result.append(description1);
		result.append(", duedate: ");
		result.append(duedate);
		result.append(", name: ");
		result.append(name);
		result.append(", notify: ");
		if (notifyESet) result.append(notify); else result.append("<unset>");
		result.append(", priority: ");
		if (priorityESet) result.append(priority); else result.append("<unset>");
		result.append(", signalling: ");
		if (signallingESet) result.append(signalling); else result.append("<unset>");
		result.append(", swimlane: ");
		result.append(swimlane);
		result.append(')');
		return result.toString();
	}

} //TaskTypeImpl
