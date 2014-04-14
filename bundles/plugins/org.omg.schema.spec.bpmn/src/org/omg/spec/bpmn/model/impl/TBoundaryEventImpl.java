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
import org.omg.spec.bpmn.model.TBoundaryEvent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TBoundary Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBoundaryEventImpl#getAttachedToRef <em>Attached To Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBoundaryEventImpl#isCancelActivity <em>Cancel Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TBoundaryEventImpl extends TCatchEventImpl implements TBoundaryEvent {
	/**
	 * The default value of the '{@link #getAttachedToRef() <em>Attached To Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttachedToRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName ATTACHED_TO_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAttachedToRef() <em>Attached To Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttachedToRef()
	 * @generated
	 * @ordered
	 */
	protected QName attachedToRef = ATTACHED_TO_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #isCancelActivity() <em>Cancel Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCancelActivity()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CANCEL_ACTIVITY_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isCancelActivity() <em>Cancel Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCancelActivity()
	 * @generated
	 * @ordered
	 */
	protected boolean cancelActivity = CANCEL_ACTIVITY_EDEFAULT;

	/**
	 * This is true if the Cancel Activity attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean cancelActivityESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TBoundaryEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TBOUNDARY_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getAttachedToRef() {
		return attachedToRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttachedToRef(QName newAttachedToRef) {
		QName oldAttachedToRef = attachedToRef;
		attachedToRef = newAttachedToRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TBOUNDARY_EVENT__ATTACHED_TO_REF, oldAttachedToRef, attachedToRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCancelActivity() {
		return cancelActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCancelActivity(boolean newCancelActivity) {
		boolean oldCancelActivity = cancelActivity;
		cancelActivity = newCancelActivity;
		boolean oldCancelActivityESet = cancelActivityESet;
		cancelActivityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TBOUNDARY_EVENT__CANCEL_ACTIVITY, oldCancelActivity, cancelActivity, !oldCancelActivityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCancelActivity() {
		boolean oldCancelActivity = cancelActivity;
		boolean oldCancelActivityESet = cancelActivityESet;
		cancelActivity = CANCEL_ACTIVITY_EDEFAULT;
		cancelActivityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TBOUNDARY_EVENT__CANCEL_ACTIVITY, oldCancelActivity, CANCEL_ACTIVITY_EDEFAULT, oldCancelActivityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCancelActivity() {
		return cancelActivityESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TBOUNDARY_EVENT__ATTACHED_TO_REF:
				return getAttachedToRef();
			case ModelPackage.TBOUNDARY_EVENT__CANCEL_ACTIVITY:
				return isCancelActivity();
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
			case ModelPackage.TBOUNDARY_EVENT__ATTACHED_TO_REF:
				setAttachedToRef((QName)newValue);
				return;
			case ModelPackage.TBOUNDARY_EVENT__CANCEL_ACTIVITY:
				setCancelActivity((Boolean)newValue);
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
			case ModelPackage.TBOUNDARY_EVENT__ATTACHED_TO_REF:
				setAttachedToRef(ATTACHED_TO_REF_EDEFAULT);
				return;
			case ModelPackage.TBOUNDARY_EVENT__CANCEL_ACTIVITY:
				unsetCancelActivity();
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
			case ModelPackage.TBOUNDARY_EVENT__ATTACHED_TO_REF:
				return ATTACHED_TO_REF_EDEFAULT == null ? attachedToRef != null : !ATTACHED_TO_REF_EDEFAULT.equals(attachedToRef);
			case ModelPackage.TBOUNDARY_EVENT__CANCEL_ACTIVITY:
				return isSetCancelActivity();
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
		result.append(" (attachedToRef: ");
		result.append(attachedToRef);
		result.append(", cancelActivity: ");
		if (cancelActivityESet) result.append(cancelActivity); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TBoundaryEventImpl
