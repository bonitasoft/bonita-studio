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
import org.omg.spec.bpmn.model.TCompensateEventDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TCompensate Event Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCompensateEventDefinitionImpl#getActivityRef <em>Activity Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCompensateEventDefinitionImpl#isWaitForCompletion <em>Wait For Completion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TCompensateEventDefinitionImpl extends TEventDefinitionImpl implements TCompensateEventDefinition {
	/**
	 * The default value of the '{@link #getActivityRef() <em>Activity Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName ACTIVITY_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivityRef() <em>Activity Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityRef()
	 * @generated
	 * @ordered
	 */
	protected QName activityRef = ACTIVITY_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #isWaitForCompletion() <em>Wait For Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWaitForCompletion()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WAIT_FOR_COMPLETION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isWaitForCompletion() <em>Wait For Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWaitForCompletion()
	 * @generated
	 * @ordered
	 */
	protected boolean waitForCompletion = WAIT_FOR_COMPLETION_EDEFAULT;

	/**
	 * This is true if the Wait For Completion attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean waitForCompletionESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TCompensateEventDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TCOMPENSATE_EVENT_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getActivityRef() {
		return activityRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivityRef(QName newActivityRef) {
		QName oldActivityRef = activityRef;
		activityRef = newActivityRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCOMPENSATE_EVENT_DEFINITION__ACTIVITY_REF, oldActivityRef, activityRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWaitForCompletion() {
		return waitForCompletion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaitForCompletion(boolean newWaitForCompletion) {
		boolean oldWaitForCompletion = waitForCompletion;
		waitForCompletion = newWaitForCompletion;
		boolean oldWaitForCompletionESet = waitForCompletionESet;
		waitForCompletionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCOMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION, oldWaitForCompletion, waitForCompletion, !oldWaitForCompletionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetWaitForCompletion() {
		boolean oldWaitForCompletion = waitForCompletion;
		boolean oldWaitForCompletionESet = waitForCompletionESet;
		waitForCompletion = WAIT_FOR_COMPLETION_EDEFAULT;
		waitForCompletionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TCOMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION, oldWaitForCompletion, WAIT_FOR_COMPLETION_EDEFAULT, oldWaitForCompletionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetWaitForCompletion() {
		return waitForCompletionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION__ACTIVITY_REF:
				return getActivityRef();
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION:
				return isWaitForCompletion();
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
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION__ACTIVITY_REF:
				setActivityRef((QName)newValue);
				return;
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION:
				setWaitForCompletion((Boolean)newValue);
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
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION__ACTIVITY_REF:
				setActivityRef(ACTIVITY_REF_EDEFAULT);
				return;
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION:
				unsetWaitForCompletion();
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
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION__ACTIVITY_REF:
				return ACTIVITY_REF_EDEFAULT == null ? activityRef != null : !ACTIVITY_REF_EDEFAULT.equals(activityRef);
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION:
				return isSetWaitForCompletion();
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
		result.append(" (activityRef: ");
		result.append(activityRef);
		result.append(", waitForCompletion: ");
		if (waitForCompletionESet) result.append(waitForCompletion); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TCompensateEventDefinitionImpl
