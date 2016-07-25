/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TChoreographyTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TChoreography Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TChoreographyTaskImpl#getMessageFlowRef <em>Message Flow Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TChoreographyTaskImpl extends TChoreographyActivityImpl implements TChoreographyTask {
	/**
	 * The cached value of the '{@link #getMessageFlowRef() <em>Message Flow Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageFlowRef()
	 * @generated
	 * @ordered
	 */
	protected EList<QName> messageFlowRef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TChoreographyTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TCHOREOGRAPHY_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QName> getMessageFlowRef() {
		if (messageFlowRef == null) {
			messageFlowRef = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF);
		}
		return messageFlowRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
				return getMessageFlowRef();
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
			case ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
				getMessageFlowRef().clear();
				getMessageFlowRef().addAll((Collection<? extends QName>)newValue);
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
			case ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
				getMessageFlowRef().clear();
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
			case ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
				return messageFlowRef != null && !messageFlowRef.isEmpty();
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
		result.append(" (messageFlowRef: ");
		result.append(messageFlowRef);
		result.append(')');
		return result.toString();
	}

} //TChoreographyTaskImpl
