/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.jbpm.jpdl32.ActionType;
import org.jbpm.jpdl32.CancelTimerType;
import org.jbpm.jpdl32.CreateTimerType;
import org.jbpm.jpdl32.MailType;
import org.jbpm.jpdl32.ScriptType;
import org.jbpm.jpdl32.TimerType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Timer Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.TimerTypeImpl#getAction <em>Action</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TimerTypeImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TimerTypeImpl#getCreateTimer <em>Create Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TimerTypeImpl#getCancelTimer <em>Cancel Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TimerTypeImpl#getMail <em>Mail</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TimerTypeImpl#getDuedate <em>Duedate</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TimerTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TimerTypeImpl#getRepeat <em>Repeat</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.TimerTypeImpl#getTransition <em>Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimerTypeImpl extends EObjectImpl implements TimerType {
	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected ActionType action;

	/**
	 * The cached value of the '{@link #getScript() <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected ScriptType script;

	/**
	 * The cached value of the '{@link #getCreateTimer() <em>Create Timer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateTimer()
	 * @generated
	 * @ordered
	 */
	protected CreateTimerType createTimer;

	/**
	 * The cached value of the '{@link #getCancelTimer() <em>Cancel Timer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCancelTimer()
	 * @generated
	 * @ordered
	 */
	protected CancelTimerType cancelTimer;

	/**
	 * The cached value of the '{@link #getMail() <em>Mail</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMail()
	 * @generated
	 * @ordered
	 */
	protected MailType mail;

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
	 * The default value of the '{@link #getRepeat() <em>Repeat</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepeat()
	 * @generated
	 * @ordered
	 */
	protected static final String REPEAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRepeat() <em>Repeat</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepeat()
	 * @generated
	 * @ordered
	 */
	protected String repeat = REPEAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransition() <em>Transition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransition()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransition() <em>Transition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransition()
	 * @generated
	 * @ordered
	 */
	protected String transition = TRANSITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimerTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.TIMER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionType getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAction(ActionType newAction, NotificationChain msgs) {
		ActionType oldAction = action;
		action = newAction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__ACTION, oldAction, newAction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(ActionType newAction) {
		if (newAction != action) {
			NotificationChain msgs = null;
			if (action != null)
				msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__ACTION, null, msgs);
			if (newAction != null)
				msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__ACTION, null, msgs);
			msgs = basicSetAction(newAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__ACTION, newAction, newAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptType getScript() {
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScript(ScriptType newScript, NotificationChain msgs) {
		ScriptType oldScript = script;
		script = newScript;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__SCRIPT, oldScript, newScript);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScript(ScriptType newScript) {
		if (newScript != script) {
			NotificationChain msgs = null;
			if (script != null)
				msgs = ((InternalEObject)script).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__SCRIPT, null, msgs);
			if (newScript != null)
				msgs = ((InternalEObject)newScript).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__SCRIPT, null, msgs);
			msgs = basicSetScript(newScript, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__SCRIPT, newScript, newScript));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateTimerType getCreateTimer() {
		return createTimer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCreateTimer(CreateTimerType newCreateTimer, NotificationChain msgs) {
		CreateTimerType oldCreateTimer = createTimer;
		createTimer = newCreateTimer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__CREATE_TIMER, oldCreateTimer, newCreateTimer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreateTimer(CreateTimerType newCreateTimer) {
		if (newCreateTimer != createTimer) {
			NotificationChain msgs = null;
			if (createTimer != null)
				msgs = ((InternalEObject)createTimer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__CREATE_TIMER, null, msgs);
			if (newCreateTimer != null)
				msgs = ((InternalEObject)newCreateTimer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__CREATE_TIMER, null, msgs);
			msgs = basicSetCreateTimer(newCreateTimer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__CREATE_TIMER, newCreateTimer, newCreateTimer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CancelTimerType getCancelTimer() {
		return cancelTimer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCancelTimer(CancelTimerType newCancelTimer, NotificationChain msgs) {
		CancelTimerType oldCancelTimer = cancelTimer;
		cancelTimer = newCancelTimer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__CANCEL_TIMER, oldCancelTimer, newCancelTimer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCancelTimer(CancelTimerType newCancelTimer) {
		if (newCancelTimer != cancelTimer) {
			NotificationChain msgs = null;
			if (cancelTimer != null)
				msgs = ((InternalEObject)cancelTimer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__CANCEL_TIMER, null, msgs);
			if (newCancelTimer != null)
				msgs = ((InternalEObject)newCancelTimer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__CANCEL_TIMER, null, msgs);
			msgs = basicSetCancelTimer(newCancelTimer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__CANCEL_TIMER, newCancelTimer, newCancelTimer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MailType getMail() {
		return mail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMail(MailType newMail, NotificationChain msgs) {
		MailType oldMail = mail;
		mail = newMail;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__MAIL, oldMail, newMail);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMail(MailType newMail) {
		if (newMail != mail) {
			NotificationChain msgs = null;
			if (mail != null)
				msgs = ((InternalEObject)mail).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__MAIL, null, msgs);
			if (newMail != null)
				msgs = ((InternalEObject)newMail).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.TIMER_TYPE__MAIL, null, msgs);
			msgs = basicSetMail(newMail, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__MAIL, newMail, newMail));
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__DUEDATE, oldDuedate, duedate));
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRepeat() {
		return repeat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepeat(String newRepeat) {
		String oldRepeat = repeat;
		repeat = newRepeat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__REPEAT, oldRepeat, repeat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransition() {
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransition(String newTransition) {
		String oldTransition = transition;
		transition = newTransition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.TIMER_TYPE__TRANSITION, oldTransition, transition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.TIMER_TYPE__ACTION:
				return basicSetAction(null, msgs);
			case jpdl32Package.TIMER_TYPE__SCRIPT:
				return basicSetScript(null, msgs);
			case jpdl32Package.TIMER_TYPE__CREATE_TIMER:
				return basicSetCreateTimer(null, msgs);
			case jpdl32Package.TIMER_TYPE__CANCEL_TIMER:
				return basicSetCancelTimer(null, msgs);
			case jpdl32Package.TIMER_TYPE__MAIL:
				return basicSetMail(null, msgs);
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
			case jpdl32Package.TIMER_TYPE__ACTION:
				return getAction();
			case jpdl32Package.TIMER_TYPE__SCRIPT:
				return getScript();
			case jpdl32Package.TIMER_TYPE__CREATE_TIMER:
				return getCreateTimer();
			case jpdl32Package.TIMER_TYPE__CANCEL_TIMER:
				return getCancelTimer();
			case jpdl32Package.TIMER_TYPE__MAIL:
				return getMail();
			case jpdl32Package.TIMER_TYPE__DUEDATE:
				return getDuedate();
			case jpdl32Package.TIMER_TYPE__NAME:
				return getName();
			case jpdl32Package.TIMER_TYPE__REPEAT:
				return getRepeat();
			case jpdl32Package.TIMER_TYPE__TRANSITION:
				return getTransition();
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
			case jpdl32Package.TIMER_TYPE__ACTION:
				setAction((ActionType)newValue);
				return;
			case jpdl32Package.TIMER_TYPE__SCRIPT:
				setScript((ScriptType)newValue);
				return;
			case jpdl32Package.TIMER_TYPE__CREATE_TIMER:
				setCreateTimer((CreateTimerType)newValue);
				return;
			case jpdl32Package.TIMER_TYPE__CANCEL_TIMER:
				setCancelTimer((CancelTimerType)newValue);
				return;
			case jpdl32Package.TIMER_TYPE__MAIL:
				setMail((MailType)newValue);
				return;
			case jpdl32Package.TIMER_TYPE__DUEDATE:
				setDuedate((String)newValue);
				return;
			case jpdl32Package.TIMER_TYPE__NAME:
				setName((String)newValue);
				return;
			case jpdl32Package.TIMER_TYPE__REPEAT:
				setRepeat((String)newValue);
				return;
			case jpdl32Package.TIMER_TYPE__TRANSITION:
				setTransition((String)newValue);
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
			case jpdl32Package.TIMER_TYPE__ACTION:
				setAction((ActionType)null);
				return;
			case jpdl32Package.TIMER_TYPE__SCRIPT:
				setScript((ScriptType)null);
				return;
			case jpdl32Package.TIMER_TYPE__CREATE_TIMER:
				setCreateTimer((CreateTimerType)null);
				return;
			case jpdl32Package.TIMER_TYPE__CANCEL_TIMER:
				setCancelTimer((CancelTimerType)null);
				return;
			case jpdl32Package.TIMER_TYPE__MAIL:
				setMail((MailType)null);
				return;
			case jpdl32Package.TIMER_TYPE__DUEDATE:
				setDuedate(DUEDATE_EDEFAULT);
				return;
			case jpdl32Package.TIMER_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case jpdl32Package.TIMER_TYPE__REPEAT:
				setRepeat(REPEAT_EDEFAULT);
				return;
			case jpdl32Package.TIMER_TYPE__TRANSITION:
				setTransition(TRANSITION_EDEFAULT);
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
			case jpdl32Package.TIMER_TYPE__ACTION:
				return action != null;
			case jpdl32Package.TIMER_TYPE__SCRIPT:
				return script != null;
			case jpdl32Package.TIMER_TYPE__CREATE_TIMER:
				return createTimer != null;
			case jpdl32Package.TIMER_TYPE__CANCEL_TIMER:
				return cancelTimer != null;
			case jpdl32Package.TIMER_TYPE__MAIL:
				return mail != null;
			case jpdl32Package.TIMER_TYPE__DUEDATE:
				return DUEDATE_EDEFAULT == null ? duedate != null : !DUEDATE_EDEFAULT.equals(duedate);
			case jpdl32Package.TIMER_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case jpdl32Package.TIMER_TYPE__REPEAT:
				return REPEAT_EDEFAULT == null ? repeat != null : !REPEAT_EDEFAULT.equals(repeat);
			case jpdl32Package.TIMER_TYPE__TRANSITION:
				return TRANSITION_EDEFAULT == null ? transition != null : !TRANSITION_EDEFAULT.equals(transition);
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
		result.append(" (duedate: ");
		result.append(duedate);
		result.append(", name: ");
		result.append(name);
		result.append(", repeat: ");
		result.append(repeat);
		result.append(", transition: ");
		result.append(transition);
		result.append(')');
		return result.toString();
	}

} //TimerTypeImpl
