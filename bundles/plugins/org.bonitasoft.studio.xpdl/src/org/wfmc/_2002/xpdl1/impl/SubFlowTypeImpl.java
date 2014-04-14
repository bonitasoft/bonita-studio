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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.ActualParametersType;
import org.wfmc._2002.xpdl1.ExecutionType1;
import org.wfmc._2002.xpdl1.SubFlowType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sub Flow Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.SubFlowTypeImpl#getActualParameters <em>Actual Parameters</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.SubFlowTypeImpl#getExecution <em>Execution</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.SubFlowTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubFlowTypeImpl extends EObjectImpl implements SubFlowType {
	/**
	 * The cached value of the '{@link #getActualParameters() <em>Actual Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualParameters()
	 * @generated
	 * @ordered
	 */
	protected ActualParametersType actualParameters;

	/**
	 * The default value of the '{@link #getExecution() <em>Execution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecution()
	 * @generated
	 * @ordered
	 */
	protected static final ExecutionType1 EXECUTION_EDEFAULT = ExecutionType1.ASYNCHR;

	/**
	 * The cached value of the '{@link #getExecution() <em>Execution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecution()
	 * @generated
	 * @ordered
	 */
	protected ExecutionType1 execution = EXECUTION_EDEFAULT;

	/**
	 * This is true if the Execution attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean executionESet;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubFlowTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.SUB_FLOW_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActualParametersType getActualParameters() {
		return actualParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActualParameters(ActualParametersType newActualParameters, NotificationChain msgs) {
		ActualParametersType oldActualParameters = actualParameters;
		actualParameters = newActualParameters;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.SUB_FLOW_TYPE__ACTUAL_PARAMETERS, oldActualParameters, newActualParameters);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualParameters(ActualParametersType newActualParameters) {
		if (newActualParameters != actualParameters) {
			NotificationChain msgs = null;
			if (actualParameters != null)
				msgs = ((InternalEObject)actualParameters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.SUB_FLOW_TYPE__ACTUAL_PARAMETERS, null, msgs);
			if (newActualParameters != null)
				msgs = ((InternalEObject)newActualParameters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.SUB_FLOW_TYPE__ACTUAL_PARAMETERS, null, msgs);
			msgs = basicSetActualParameters(newActualParameters, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.SUB_FLOW_TYPE__ACTUAL_PARAMETERS, newActualParameters, newActualParameters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionType1 getExecution() {
		return execution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecution(ExecutionType1 newExecution) {
		ExecutionType1 oldExecution = execution;
		execution = newExecution == null ? EXECUTION_EDEFAULT : newExecution;
		boolean oldExecutionESet = executionESet;
		executionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.SUB_FLOW_TYPE__EXECUTION, oldExecution, execution, !oldExecutionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetExecution() {
		ExecutionType1 oldExecution = execution;
		boolean oldExecutionESet = executionESet;
		execution = EXECUTION_EDEFAULT;
		executionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Xpdl1Package.SUB_FLOW_TYPE__EXECUTION, oldExecution, EXECUTION_EDEFAULT, oldExecutionESet));
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
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.SUB_FLOW_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.SUB_FLOW_TYPE__ACTUAL_PARAMETERS:
				return basicSetActualParameters(null, msgs);
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
			case Xpdl1Package.SUB_FLOW_TYPE__ACTUAL_PARAMETERS:
				return getActualParameters();
			case Xpdl1Package.SUB_FLOW_TYPE__EXECUTION:
				return getExecution();
			case Xpdl1Package.SUB_FLOW_TYPE__ID:
				return getId();
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
			case Xpdl1Package.SUB_FLOW_TYPE__ACTUAL_PARAMETERS:
				setActualParameters((ActualParametersType)newValue);
				return;
			case Xpdl1Package.SUB_FLOW_TYPE__EXECUTION:
				setExecution((ExecutionType1)newValue);
				return;
			case Xpdl1Package.SUB_FLOW_TYPE__ID:
				setId((String)newValue);
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
			case Xpdl1Package.SUB_FLOW_TYPE__ACTUAL_PARAMETERS:
				setActualParameters((ActualParametersType)null);
				return;
			case Xpdl1Package.SUB_FLOW_TYPE__EXECUTION:
				unsetExecution();
				return;
			case Xpdl1Package.SUB_FLOW_TYPE__ID:
				setId(ID_EDEFAULT);
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
			case Xpdl1Package.SUB_FLOW_TYPE__ACTUAL_PARAMETERS:
				return actualParameters != null;
			case Xpdl1Package.SUB_FLOW_TYPE__EXECUTION:
				return isSetExecution();
			case Xpdl1Package.SUB_FLOW_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //SubFlowTypeImpl
