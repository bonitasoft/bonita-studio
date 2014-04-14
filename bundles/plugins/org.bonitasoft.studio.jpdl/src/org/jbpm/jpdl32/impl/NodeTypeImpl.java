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
import org.jbpm.jpdl32.EventType;
import org.jbpm.jpdl32.ExceptionHandlerType;
import org.jbpm.jpdl32.MailType;
import org.jbpm.jpdl32.NodeType;
import org.jbpm.jpdl32.ScriptType;
import org.jbpm.jpdl32.TimerType;
import org.jbpm.jpdl32.TransitionType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getAction <em>Action</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getCreateTimer <em>Create Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getCancelTimer <em>Cancel Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getMail <em>Mail</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getNodeContentElements <em>Node Content Elements</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getAsync <em>Async</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.NodeTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeTypeImpl extends EObjectImpl implements NodeType {
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
	 * The cached value of the '{@link #getNodeContentElements() <em>Node Content Elements</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeContentElements()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap nodeContentElements;

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
	protected NodeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.NODE_TYPE;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__ACTION, oldAction, newAction);
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
				msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__ACTION, null, msgs);
			if (newAction != null)
				msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__ACTION, null, msgs);
			msgs = basicSetAction(newAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__ACTION, newAction, newAction));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__SCRIPT, oldScript, newScript);
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
				msgs = ((InternalEObject)script).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__SCRIPT, null, msgs);
			if (newScript != null)
				msgs = ((InternalEObject)newScript).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__SCRIPT, null, msgs);
			msgs = basicSetScript(newScript, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__SCRIPT, newScript, newScript));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__CREATE_TIMER, oldCreateTimer, newCreateTimer);
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
				msgs = ((InternalEObject)createTimer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__CREATE_TIMER, null, msgs);
			if (newCreateTimer != null)
				msgs = ((InternalEObject)newCreateTimer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__CREATE_TIMER, null, msgs);
			msgs = basicSetCreateTimer(newCreateTimer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__CREATE_TIMER, newCreateTimer, newCreateTimer));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__CANCEL_TIMER, oldCancelTimer, newCancelTimer);
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
				msgs = ((InternalEObject)cancelTimer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__CANCEL_TIMER, null, msgs);
			if (newCancelTimer != null)
				msgs = ((InternalEObject)newCancelTimer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__CANCEL_TIMER, null, msgs);
			msgs = basicSetCancelTimer(newCancelTimer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__CANCEL_TIMER, newCancelTimer, newCancelTimer));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__MAIL, oldMail, newMail);
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
				msgs = ((InternalEObject)mail).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__MAIL, null, msgs);
			if (newMail != null)
				msgs = ((InternalEObject)newMail).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.NODE_TYPE__MAIL, null, msgs);
			msgs = basicSetMail(newMail, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__MAIL, newMail, newMail));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getNodeContentElements() {
		if (nodeContentElements == null) {
			nodeContentElements = new BasicFeatureMap(this, jpdl32Package.NODE_TYPE__NODE_CONTENT_ELEMENTS);
		}
		return nodeContentElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDescription() {
		return getNodeContentElements().list(jpdl32Package.Literals.NODE_TYPE__DESCRIPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventType> getEvent() {
		return getNodeContentElements().list(jpdl32Package.Literals.NODE_TYPE__EVENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExceptionHandlerType> getExceptionHandler() {
		return getNodeContentElements().list(jpdl32Package.Literals.NODE_TYPE__EXCEPTION_HANDLER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimerType> getTimer() {
		return getNodeContentElements().list(jpdl32Package.Literals.NODE_TYPE__TIMER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransitionType> getTransition() {
		return getNodeContentElements().list(jpdl32Package.Literals.NODE_TYPE__TRANSITION);
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__ASYNC, oldAsync, async, !oldAsyncESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.NODE_TYPE__ASYNC, oldAsync, ASYNC_EDEFAULT, oldAsyncESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.NODE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.NODE_TYPE__ACTION:
				return basicSetAction(null, msgs);
			case jpdl32Package.NODE_TYPE__SCRIPT:
				return basicSetScript(null, msgs);
			case jpdl32Package.NODE_TYPE__CREATE_TIMER:
				return basicSetCreateTimer(null, msgs);
			case jpdl32Package.NODE_TYPE__CANCEL_TIMER:
				return basicSetCancelTimer(null, msgs);
			case jpdl32Package.NODE_TYPE__MAIL:
				return basicSetMail(null, msgs);
			case jpdl32Package.NODE_TYPE__NODE_CONTENT_ELEMENTS:
				return ((InternalEList<?>)getNodeContentElements()).basicRemove(otherEnd, msgs);
			case jpdl32Package.NODE_TYPE__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
			case jpdl32Package.NODE_TYPE__EXCEPTION_HANDLER:
				return ((InternalEList<?>)getExceptionHandler()).basicRemove(otherEnd, msgs);
			case jpdl32Package.NODE_TYPE__TIMER:
				return ((InternalEList<?>)getTimer()).basicRemove(otherEnd, msgs);
			case jpdl32Package.NODE_TYPE__TRANSITION:
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
			case jpdl32Package.NODE_TYPE__ACTION:
				return getAction();
			case jpdl32Package.NODE_TYPE__SCRIPT:
				return getScript();
			case jpdl32Package.NODE_TYPE__CREATE_TIMER:
				return getCreateTimer();
			case jpdl32Package.NODE_TYPE__CANCEL_TIMER:
				return getCancelTimer();
			case jpdl32Package.NODE_TYPE__MAIL:
				return getMail();
			case jpdl32Package.NODE_TYPE__NODE_CONTENT_ELEMENTS:
				if (coreType) return getNodeContentElements();
				return ((FeatureMap.Internal)getNodeContentElements()).getWrapper();
			case jpdl32Package.NODE_TYPE__DESCRIPTION:
				return getDescription();
			case jpdl32Package.NODE_TYPE__EVENT:
				return getEvent();
			case jpdl32Package.NODE_TYPE__EXCEPTION_HANDLER:
				return getExceptionHandler();
			case jpdl32Package.NODE_TYPE__TIMER:
				return getTimer();
			case jpdl32Package.NODE_TYPE__TRANSITION:
				return getTransition();
			case jpdl32Package.NODE_TYPE__ASYNC:
				return getAsync();
			case jpdl32Package.NODE_TYPE__NAME:
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
			case jpdl32Package.NODE_TYPE__ACTION:
				setAction((ActionType)newValue);
				return;
			case jpdl32Package.NODE_TYPE__SCRIPT:
				setScript((ScriptType)newValue);
				return;
			case jpdl32Package.NODE_TYPE__CREATE_TIMER:
				setCreateTimer((CreateTimerType)newValue);
				return;
			case jpdl32Package.NODE_TYPE__CANCEL_TIMER:
				setCancelTimer((CancelTimerType)newValue);
				return;
			case jpdl32Package.NODE_TYPE__MAIL:
				setMail((MailType)newValue);
				return;
			case jpdl32Package.NODE_TYPE__NODE_CONTENT_ELEMENTS:
				((FeatureMap.Internal)getNodeContentElements()).set(newValue);
				return;
			case jpdl32Package.NODE_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends String>)newValue);
				return;
			case jpdl32Package.NODE_TYPE__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends EventType>)newValue);
				return;
			case jpdl32Package.NODE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				getExceptionHandler().addAll((Collection<? extends ExceptionHandlerType>)newValue);
				return;
			case jpdl32Package.NODE_TYPE__TIMER:
				getTimer().clear();
				getTimer().addAll((Collection<? extends TimerType>)newValue);
				return;
			case jpdl32Package.NODE_TYPE__TRANSITION:
				getTransition().clear();
				getTransition().addAll((Collection<? extends TransitionType>)newValue);
				return;
			case jpdl32Package.NODE_TYPE__ASYNC:
				setAsync((String)newValue);
				return;
			case jpdl32Package.NODE_TYPE__NAME:
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
			case jpdl32Package.NODE_TYPE__ACTION:
				setAction((ActionType)null);
				return;
			case jpdl32Package.NODE_TYPE__SCRIPT:
				setScript((ScriptType)null);
				return;
			case jpdl32Package.NODE_TYPE__CREATE_TIMER:
				setCreateTimer((CreateTimerType)null);
				return;
			case jpdl32Package.NODE_TYPE__CANCEL_TIMER:
				setCancelTimer((CancelTimerType)null);
				return;
			case jpdl32Package.NODE_TYPE__MAIL:
				setMail((MailType)null);
				return;
			case jpdl32Package.NODE_TYPE__NODE_CONTENT_ELEMENTS:
				getNodeContentElements().clear();
				return;
			case jpdl32Package.NODE_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case jpdl32Package.NODE_TYPE__EVENT:
				getEvent().clear();
				return;
			case jpdl32Package.NODE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				return;
			case jpdl32Package.NODE_TYPE__TIMER:
				getTimer().clear();
				return;
			case jpdl32Package.NODE_TYPE__TRANSITION:
				getTransition().clear();
				return;
			case jpdl32Package.NODE_TYPE__ASYNC:
				unsetAsync();
				return;
			case jpdl32Package.NODE_TYPE__NAME:
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
			case jpdl32Package.NODE_TYPE__ACTION:
				return action != null;
			case jpdl32Package.NODE_TYPE__SCRIPT:
				return script != null;
			case jpdl32Package.NODE_TYPE__CREATE_TIMER:
				return createTimer != null;
			case jpdl32Package.NODE_TYPE__CANCEL_TIMER:
				return cancelTimer != null;
			case jpdl32Package.NODE_TYPE__MAIL:
				return mail != null;
			case jpdl32Package.NODE_TYPE__NODE_CONTENT_ELEMENTS:
				return nodeContentElements != null && !nodeContentElements.isEmpty();
			case jpdl32Package.NODE_TYPE__DESCRIPTION:
				return !getDescription().isEmpty();
			case jpdl32Package.NODE_TYPE__EVENT:
				return !getEvent().isEmpty();
			case jpdl32Package.NODE_TYPE__EXCEPTION_HANDLER:
				return !getExceptionHandler().isEmpty();
			case jpdl32Package.NODE_TYPE__TIMER:
				return !getTimer().isEmpty();
			case jpdl32Package.NODE_TYPE__TRANSITION:
				return !getTransition().isEmpty();
			case jpdl32Package.NODE_TYPE__ASYNC:
				return isSetAsync();
			case jpdl32Package.NODE_TYPE__NAME:
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
		result.append(" (nodeContentElements: ");
		result.append(nodeContentElements);
		result.append(", async: ");
		if (asyncESet) result.append(async); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //NodeTypeImpl
