/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.wfmc._2002.xpdl1.WorkflowProcessType;
import org.wfmc._2002.xpdl1.WorkflowProcessesType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Workflow Processes Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessesTypeImpl#getWorkflowProcess <em>Workflow Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkflowProcessesTypeImpl extends EObjectImpl implements WorkflowProcessesType {
	/**
	 * The cached value of the '{@link #getWorkflowProcess() <em>Workflow Process</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkflowProcess()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkflowProcessType> workflowProcess;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkflowProcessesTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.WORKFLOW_PROCESSES_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WorkflowProcessType> getWorkflowProcess() {
		if (workflowProcess == null) {
			workflowProcess = new EObjectContainmentEList<WorkflowProcessType>(WorkflowProcessType.class, this, Xpdl1Package.WORKFLOW_PROCESSES_TYPE__WORKFLOW_PROCESS);
		}
		return workflowProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.WORKFLOW_PROCESSES_TYPE__WORKFLOW_PROCESS:
				return ((InternalEList<?>)getWorkflowProcess()).basicRemove(otherEnd, msgs);
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
			case Xpdl1Package.WORKFLOW_PROCESSES_TYPE__WORKFLOW_PROCESS:
				return getWorkflowProcess();
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
			case Xpdl1Package.WORKFLOW_PROCESSES_TYPE__WORKFLOW_PROCESS:
				getWorkflowProcess().clear();
				getWorkflowProcess().addAll((Collection<? extends WorkflowProcessType>)newValue);
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
			case Xpdl1Package.WORKFLOW_PROCESSES_TYPE__WORKFLOW_PROCESS:
				getWorkflowProcess().clear();
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
			case Xpdl1Package.WORKFLOW_PROCESSES_TYPE__WORKFLOW_PROCESS:
				return workflowProcess != null && !workflowProcess.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //WorkflowProcessesTypeImpl
