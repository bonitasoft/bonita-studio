/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TGlobalChoreographyTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TGlobal Choreography Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TGlobalChoreographyTaskImpl#getInitiatingParticipantRef <em>Initiating Participant Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TGlobalChoreographyTaskImpl extends TChoreographyImpl implements TGlobalChoreographyTask {
	/**
	 * The default value of the '{@link #getInitiatingParticipantRef() <em>Initiating Participant Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitiatingParticipantRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName INITIATING_PARTICIPANT_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitiatingParticipantRef() <em>Initiating Participant Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitiatingParticipantRef()
	 * @generated
	 * @ordered
	 */
	protected QName initiatingParticipantRef = INITIATING_PARTICIPANT_REF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TGlobalChoreographyTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TGLOBAL_CHOREOGRAPHY_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getInitiatingParticipantRef() {
		return initiatingParticipantRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitiatingParticipantRef(QName newInitiatingParticipantRef) {
		QName oldInitiatingParticipantRef = initiatingParticipantRef;
		initiatingParticipantRef = newInitiatingParticipantRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TGLOBAL_CHOREOGRAPHY_TASK__INITIATING_PARTICIPANT_REF, oldInitiatingParticipantRef, initiatingParticipantRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TGLOBAL_CHOREOGRAPHY_TASK__INITIATING_PARTICIPANT_REF:
				return getInitiatingParticipantRef();
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
			case ModelPackage.TGLOBAL_CHOREOGRAPHY_TASK__INITIATING_PARTICIPANT_REF:
				setInitiatingParticipantRef((QName)newValue);
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
			case ModelPackage.TGLOBAL_CHOREOGRAPHY_TASK__INITIATING_PARTICIPANT_REF:
				setInitiatingParticipantRef(INITIATING_PARTICIPANT_REF_EDEFAULT);
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
			case ModelPackage.TGLOBAL_CHOREOGRAPHY_TASK__INITIATING_PARTICIPANT_REF:
				return INITIATING_PARTICIPANT_REF_EDEFAULT == null ? initiatingParticipantRef != null : !INITIATING_PARTICIPANT_REF_EDEFAULT.equals(initiatingParticipantRef);
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
		result.append(" (initiatingParticipantRef: ");
		result.append(initiatingParticipantRef);
		result.append(')');
		return result.toString();
	}

} //TGlobalChoreographyTaskImpl
