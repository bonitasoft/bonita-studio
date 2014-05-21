/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.DeadlineType;
import org.wfmc._2002.xpdl1.ExecutionType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deadline Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DeadlineTypeImpl#getDeadlineCondition <em>Deadline Condition</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DeadlineTypeImpl#getExceptionName <em>Exception Name</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DeadlineTypeImpl#getExecution <em>Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeadlineTypeImpl extends EObjectImpl implements DeadlineType {
	/**
	 * The cached value of the '{@link #getDeadlineCondition() <em>Deadline Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeadlineCondition()
	 * @generated
	 * @ordered
	 */
	protected EObject deadlineCondition;

	/**
	 * The cached value of the '{@link #getExceptionName() <em>Exception Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionName()
	 * @generated
	 * @ordered
	 */
	protected EObject exceptionName;

	/**
	 * The default value of the '{@link #getExecution() <em>Execution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecution()
	 * @generated
	 * @ordered
	 */
	protected static final ExecutionType EXECUTION_EDEFAULT = ExecutionType.ASYNCHR;

	/**
	 * The cached value of the '{@link #getExecution() <em>Execution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecution()
	 * @generated
	 * @ordered
	 */
	protected ExecutionType execution = EXECUTION_EDEFAULT;

	/**
	 * This is true if the Execution attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean executionESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeadlineTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.DEADLINE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getDeadlineCondition() {
		return deadlineCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeadlineCondition(EObject newDeadlineCondition, NotificationChain msgs) {
		EObject oldDeadlineCondition = deadlineCondition;
		deadlineCondition = newDeadlineCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.DEADLINE_TYPE__DEADLINE_CONDITION, oldDeadlineCondition, newDeadlineCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeadlineCondition(EObject newDeadlineCondition) {
		if (newDeadlineCondition != deadlineCondition) {
			NotificationChain msgs = null;
			if (deadlineCondition != null)
				msgs = ((InternalEObject)deadlineCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.DEADLINE_TYPE__DEADLINE_CONDITION, null, msgs);
			if (newDeadlineCondition != null)
				msgs = ((InternalEObject)newDeadlineCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.DEADLINE_TYPE__DEADLINE_CONDITION, null, msgs);
			msgs = basicSetDeadlineCondition(newDeadlineCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.DEADLINE_TYPE__DEADLINE_CONDITION, newDeadlineCondition, newDeadlineCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getExceptionName() {
		return exceptionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExceptionName(EObject newExceptionName, NotificationChain msgs) {
		EObject oldExceptionName = exceptionName;
		exceptionName = newExceptionName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.DEADLINE_TYPE__EXCEPTION_NAME, oldExceptionName, newExceptionName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExceptionName(EObject newExceptionName) {
		if (newExceptionName != exceptionName) {
			NotificationChain msgs = null;
			if (exceptionName != null)
				msgs = ((InternalEObject)exceptionName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.DEADLINE_TYPE__EXCEPTION_NAME, null, msgs);
			if (newExceptionName != null)
				msgs = ((InternalEObject)newExceptionName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.DEADLINE_TYPE__EXCEPTION_NAME, null, msgs);
			msgs = basicSetExceptionName(newExceptionName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.DEADLINE_TYPE__EXCEPTION_NAME, newExceptionName, newExceptionName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionType getExecution() {
		return execution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecution(ExecutionType newExecution) {
		ExecutionType oldExecution = execution;
		execution = newExecution == null ? EXECUTION_EDEFAULT : newExecution;
		boolean oldExecutionESet = executionESet;
		executionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.DEADLINE_TYPE__EXECUTION, oldExecution, execution, !oldExecutionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetExecution() {
		ExecutionType oldExecution = execution;
		boolean oldExecutionESet = executionESet;
		execution = EXECUTION_EDEFAULT;
		executionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Xpdl1Package.DEADLINE_TYPE__EXECUTION, oldExecution, EXECUTION_EDEFAULT, oldExecutionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetExecution() {
		return executionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.DEADLINE_TYPE__DEADLINE_CONDITION:
				return basicSetDeadlineCondition(null, msgs);
			case Xpdl1Package.DEADLINE_TYPE__EXCEPTION_NAME:
				return basicSetExceptionName(null, msgs);
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
			case Xpdl1Package.DEADLINE_TYPE__DEADLINE_CONDITION:
				return getDeadlineCondition();
			case Xpdl1Package.DEADLINE_TYPE__EXCEPTION_NAME:
				return getExceptionName();
			case Xpdl1Package.DEADLINE_TYPE__EXECUTION:
				return getExecution();
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
			case Xpdl1Package.DEADLINE_TYPE__DEADLINE_CONDITION:
				setDeadlineCondition((EObject)newValue);
				return;
			case Xpdl1Package.DEADLINE_TYPE__EXCEPTION_NAME:
				setExceptionName((EObject)newValue);
				return;
			case Xpdl1Package.DEADLINE_TYPE__EXECUTION:
				setExecution((ExecutionType)newValue);
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
			case Xpdl1Package.DEADLINE_TYPE__DEADLINE_CONDITION:
				setDeadlineCondition((EObject)null);
				return;
			case Xpdl1Package.DEADLINE_TYPE__EXCEPTION_NAME:
				setExceptionName((EObject)null);
				return;
			case Xpdl1Package.DEADLINE_TYPE__EXECUTION:
				unsetExecution();
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
			case Xpdl1Package.DEADLINE_TYPE__DEADLINE_CONDITION:
				return deadlineCondition != null;
			case Xpdl1Package.DEADLINE_TYPE__EXCEPTION_NAME:
				return exceptionName != null;
			case Xpdl1Package.DEADLINE_TYPE__EXECUTION:
				return isSetExecution();
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
		result.append(" (execution: ");
		if (executionESet) result.append(execution); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //DeadlineTypeImpl
