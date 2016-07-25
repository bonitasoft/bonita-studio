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
import org.omg.spec.bpmn.model.TMessageFlowAssociation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TMessage Flow Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TMessageFlowAssociationImpl#getInnerMessageFlowRef <em>Inner Message Flow Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TMessageFlowAssociationImpl#getOuterMessageFlowRef <em>Outer Message Flow Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TMessageFlowAssociationImpl extends TBaseElementImpl implements TMessageFlowAssociation {
	/**
	 * The default value of the '{@link #getInnerMessageFlowRef() <em>Inner Message Flow Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInnerMessageFlowRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName INNER_MESSAGE_FLOW_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInnerMessageFlowRef() <em>Inner Message Flow Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInnerMessageFlowRef()
	 * @generated
	 * @ordered
	 */
	protected QName innerMessageFlowRef = INNER_MESSAGE_FLOW_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #getOuterMessageFlowRef() <em>Outer Message Flow Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOuterMessageFlowRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName OUTER_MESSAGE_FLOW_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOuterMessageFlowRef() <em>Outer Message Flow Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOuterMessageFlowRef()
	 * @generated
	 * @ordered
	 */
	protected QName outerMessageFlowRef = OUTER_MESSAGE_FLOW_REF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TMessageFlowAssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TMESSAGE_FLOW_ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getInnerMessageFlowRef() {
		return innerMessageFlowRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInnerMessageFlowRef(QName newInnerMessageFlowRef) {
		QName oldInnerMessageFlowRef = innerMessageFlowRef;
		innerMessageFlowRef = newInnerMessageFlowRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TMESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF, oldInnerMessageFlowRef, innerMessageFlowRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getOuterMessageFlowRef() {
		return outerMessageFlowRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOuterMessageFlowRef(QName newOuterMessageFlowRef) {
		QName oldOuterMessageFlowRef = outerMessageFlowRef;
		outerMessageFlowRef = newOuterMessageFlowRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TMESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF, oldOuterMessageFlowRef, outerMessageFlowRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF:
				return getInnerMessageFlowRef();
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF:
				return getOuterMessageFlowRef();
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
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF:
				setInnerMessageFlowRef((QName)newValue);
				return;
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF:
				setOuterMessageFlowRef((QName)newValue);
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
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF:
				setInnerMessageFlowRef(INNER_MESSAGE_FLOW_REF_EDEFAULT);
				return;
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF:
				setOuterMessageFlowRef(OUTER_MESSAGE_FLOW_REF_EDEFAULT);
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
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF:
				return INNER_MESSAGE_FLOW_REF_EDEFAULT == null ? innerMessageFlowRef != null : !INNER_MESSAGE_FLOW_REF_EDEFAULT.equals(innerMessageFlowRef);
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF:
				return OUTER_MESSAGE_FLOW_REF_EDEFAULT == null ? outerMessageFlowRef != null : !OUTER_MESSAGE_FLOW_REF_EDEFAULT.equals(outerMessageFlowRef);
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
		result.append(" (innerMessageFlowRef: ");
		result.append(innerMessageFlowRef);
		result.append(", outerMessageFlowRef: ");
		result.append(outerMessageFlowRef);
		result.append(')');
		return result.toString();
	}

} //TMessageFlowAssociationImpl
