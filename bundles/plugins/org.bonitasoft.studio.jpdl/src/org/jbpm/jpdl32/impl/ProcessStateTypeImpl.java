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
import org.jbpm.jpdl32.BindingType;
import org.jbpm.jpdl32.EventType;
import org.jbpm.jpdl32.ExceptionHandlerType;
import org.jbpm.jpdl32.ProcessStateType;
import org.jbpm.jpdl32.SubProcessType;
import org.jbpm.jpdl32.TimerType;
import org.jbpm.jpdl32.TransitionType;
import org.jbpm.jpdl32.VariableType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process State Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getAsync <em>Async</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessStateTypeImpl extends EObjectImpl implements ProcessStateType {
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
	 * The default value of the '{@link #getBinding() <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected static final BindingType BINDING_EDEFAULT = BindingType.LATE;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected BindingType binding = BINDING_EDEFAULT;

	/**
	 * This is true if the Binding attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean bindingESet;

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
	protected ProcessStateTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.PROCESS_STATE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, jpdl32Package.PROCESS_STATE_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SubProcessType> getSubProcess() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_STATE_TYPE__SUB_PROCESS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableType> getVariable() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_STATE_TYPE__VARIABLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDescription() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_STATE_TYPE__DESCRIPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventType> getEvent() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_STATE_TYPE__EVENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExceptionHandlerType> getExceptionHandler() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_STATE_TYPE__EXCEPTION_HANDLER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimerType> getTimer() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_STATE_TYPE__TIMER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransitionType> getTransition() {
		return getGroup().list(jpdl32Package.Literals.PROCESS_STATE_TYPE__TRANSITION);
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.PROCESS_STATE_TYPE__ASYNC, oldAsync, async, !oldAsyncESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.PROCESS_STATE_TYPE__ASYNC, oldAsync, ASYNC_EDEFAULT, oldAsyncESet));
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
	public BindingType getBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(BindingType newBinding) {
		BindingType oldBinding = binding;
		binding = newBinding == null ? BINDING_EDEFAULT : newBinding;
		boolean oldBindingESet = bindingESet;
		bindingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.PROCESS_STATE_TYPE__BINDING, oldBinding, binding, !oldBindingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBinding() {
		BindingType oldBinding = binding;
		boolean oldBindingESet = bindingESet;
		binding = BINDING_EDEFAULT;
		bindingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.PROCESS_STATE_TYPE__BINDING, oldBinding, BINDING_EDEFAULT, oldBindingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBinding() {
		return bindingESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.PROCESS_STATE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.PROCESS_STATE_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_STATE_TYPE__SUB_PROCESS:
				return ((InternalEList<?>)getSubProcess()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_STATE_TYPE__VARIABLE:
				return ((InternalEList<?>)getVariable()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_STATE_TYPE__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_STATE_TYPE__EXCEPTION_HANDLER:
				return ((InternalEList<?>)getExceptionHandler()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_STATE_TYPE__TIMER:
				return ((InternalEList<?>)getTimer()).basicRemove(otherEnd, msgs);
			case jpdl32Package.PROCESS_STATE_TYPE__TRANSITION:
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
			case jpdl32Package.PROCESS_STATE_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case jpdl32Package.PROCESS_STATE_TYPE__SUB_PROCESS:
				return getSubProcess();
			case jpdl32Package.PROCESS_STATE_TYPE__VARIABLE:
				return getVariable();
			case jpdl32Package.PROCESS_STATE_TYPE__DESCRIPTION:
				return getDescription();
			case jpdl32Package.PROCESS_STATE_TYPE__EVENT:
				return getEvent();
			case jpdl32Package.PROCESS_STATE_TYPE__EXCEPTION_HANDLER:
				return getExceptionHandler();
			case jpdl32Package.PROCESS_STATE_TYPE__TIMER:
				return getTimer();
			case jpdl32Package.PROCESS_STATE_TYPE__TRANSITION:
				return getTransition();
			case jpdl32Package.PROCESS_STATE_TYPE__ASYNC:
				return getAsync();
			case jpdl32Package.PROCESS_STATE_TYPE__BINDING:
				return getBinding();
			case jpdl32Package.PROCESS_STATE_TYPE__NAME:
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
			case jpdl32Package.PROCESS_STATE_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__SUB_PROCESS:
				getSubProcess().clear();
				getSubProcess().addAll((Collection<? extends SubProcessType>)newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__VARIABLE:
				getVariable().clear();
				getVariable().addAll((Collection<? extends VariableType>)newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends String>)newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends EventType>)newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				getExceptionHandler().addAll((Collection<? extends ExceptionHandlerType>)newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__TIMER:
				getTimer().clear();
				getTimer().addAll((Collection<? extends TimerType>)newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__TRANSITION:
				getTransition().clear();
				getTransition().addAll((Collection<? extends TransitionType>)newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__ASYNC:
				setAsync((String)newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__BINDING:
				setBinding((BindingType)newValue);
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__NAME:
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
			case jpdl32Package.PROCESS_STATE_TYPE__GROUP:
				getGroup().clear();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__SUB_PROCESS:
				getSubProcess().clear();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__VARIABLE:
				getVariable().clear();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__EVENT:
				getEvent().clear();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__TIMER:
				getTimer().clear();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__TRANSITION:
				getTransition().clear();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__ASYNC:
				unsetAsync();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__BINDING:
				unsetBinding();
				return;
			case jpdl32Package.PROCESS_STATE_TYPE__NAME:
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
			case jpdl32Package.PROCESS_STATE_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case jpdl32Package.PROCESS_STATE_TYPE__SUB_PROCESS:
				return !getSubProcess().isEmpty();
			case jpdl32Package.PROCESS_STATE_TYPE__VARIABLE:
				return !getVariable().isEmpty();
			case jpdl32Package.PROCESS_STATE_TYPE__DESCRIPTION:
				return !getDescription().isEmpty();
			case jpdl32Package.PROCESS_STATE_TYPE__EVENT:
				return !getEvent().isEmpty();
			case jpdl32Package.PROCESS_STATE_TYPE__EXCEPTION_HANDLER:
				return !getExceptionHandler().isEmpty();
			case jpdl32Package.PROCESS_STATE_TYPE__TIMER:
				return !getTimer().isEmpty();
			case jpdl32Package.PROCESS_STATE_TYPE__TRANSITION:
				return !getTransition().isEmpty();
			case jpdl32Package.PROCESS_STATE_TYPE__ASYNC:
				return isSetAsync();
			case jpdl32Package.PROCESS_STATE_TYPE__BINDING:
				return isSetBinding();
			case jpdl32Package.PROCESS_STATE_TYPE__NAME:
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
		result.append(", binding: ");
		if (bindingESet) result.append(binding); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ProcessStateTypeImpl
