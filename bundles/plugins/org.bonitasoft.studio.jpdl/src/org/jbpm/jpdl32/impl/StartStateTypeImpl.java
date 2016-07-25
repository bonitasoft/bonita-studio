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
import org.jbpm.jpdl32.EventType;
import org.jbpm.jpdl32.ExceptionHandlerType;
import org.jbpm.jpdl32.StartStateType;
import org.jbpm.jpdl32.TaskType;
import org.jbpm.jpdl32.TransitionType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start State Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.StartStateTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.StartStateTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.StartStateTypeImpl#getTask <em>Task</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.StartStateTypeImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.StartStateTypeImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.StartStateTypeImpl#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.StartStateTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StartStateTypeImpl extends EObjectImpl implements StartStateType {
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
	protected StartStateTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.START_STATE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, jpdl32Package.START_STATE_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDescription() {
		return getGroup().list(jpdl32Package.Literals.START_STATE_TYPE__DESCRIPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaskType> getTask() {
		return getGroup().list(jpdl32Package.Literals.START_STATE_TYPE__TASK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransitionType> getTransition() {
		return getGroup().list(jpdl32Package.Literals.START_STATE_TYPE__TRANSITION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventType> getEvent() {
		return getGroup().list(jpdl32Package.Literals.START_STATE_TYPE__EVENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExceptionHandlerType> getExceptionHandler() {
		return getGroup().list(jpdl32Package.Literals.START_STATE_TYPE__EXCEPTION_HANDLER);
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.START_STATE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.START_STATE_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case jpdl32Package.START_STATE_TYPE__TASK:
				return ((InternalEList<?>)getTask()).basicRemove(otherEnd, msgs);
			case jpdl32Package.START_STATE_TYPE__TRANSITION:
				return ((InternalEList<?>)getTransition()).basicRemove(otherEnd, msgs);
			case jpdl32Package.START_STATE_TYPE__EVENT:
				return ((InternalEList<?>)getEvent()).basicRemove(otherEnd, msgs);
			case jpdl32Package.START_STATE_TYPE__EXCEPTION_HANDLER:
				return ((InternalEList<?>)getExceptionHandler()).basicRemove(otherEnd, msgs);
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
			case jpdl32Package.START_STATE_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case jpdl32Package.START_STATE_TYPE__DESCRIPTION:
				return getDescription();
			case jpdl32Package.START_STATE_TYPE__TASK:
				return getTask();
			case jpdl32Package.START_STATE_TYPE__TRANSITION:
				return getTransition();
			case jpdl32Package.START_STATE_TYPE__EVENT:
				return getEvent();
			case jpdl32Package.START_STATE_TYPE__EXCEPTION_HANDLER:
				return getExceptionHandler();
			case jpdl32Package.START_STATE_TYPE__NAME:
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
			case jpdl32Package.START_STATE_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case jpdl32Package.START_STATE_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends String>)newValue);
				return;
			case jpdl32Package.START_STATE_TYPE__TASK:
				getTask().clear();
				getTask().addAll((Collection<? extends TaskType>)newValue);
				return;
			case jpdl32Package.START_STATE_TYPE__TRANSITION:
				getTransition().clear();
				getTransition().addAll((Collection<? extends TransitionType>)newValue);
				return;
			case jpdl32Package.START_STATE_TYPE__EVENT:
				getEvent().clear();
				getEvent().addAll((Collection<? extends EventType>)newValue);
				return;
			case jpdl32Package.START_STATE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				getExceptionHandler().addAll((Collection<? extends ExceptionHandlerType>)newValue);
				return;
			case jpdl32Package.START_STATE_TYPE__NAME:
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
			case jpdl32Package.START_STATE_TYPE__GROUP:
				getGroup().clear();
				return;
			case jpdl32Package.START_STATE_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case jpdl32Package.START_STATE_TYPE__TASK:
				getTask().clear();
				return;
			case jpdl32Package.START_STATE_TYPE__TRANSITION:
				getTransition().clear();
				return;
			case jpdl32Package.START_STATE_TYPE__EVENT:
				getEvent().clear();
				return;
			case jpdl32Package.START_STATE_TYPE__EXCEPTION_HANDLER:
				getExceptionHandler().clear();
				return;
			case jpdl32Package.START_STATE_TYPE__NAME:
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
			case jpdl32Package.START_STATE_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case jpdl32Package.START_STATE_TYPE__DESCRIPTION:
				return !getDescription().isEmpty();
			case jpdl32Package.START_STATE_TYPE__TASK:
				return !getTask().isEmpty();
			case jpdl32Package.START_STATE_TYPE__TRANSITION:
				return !getTransition().isEmpty();
			case jpdl32Package.START_STATE_TYPE__EVENT:
				return !getEvent().isEmpty();
			case jpdl32Package.START_STATE_TYPE__EXCEPTION_HANDLER:
				return !getExceptionHandler().isEmpty();
			case jpdl32Package.START_STATE_TYPE__NAME:
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

} //StartStateTypeImpl
