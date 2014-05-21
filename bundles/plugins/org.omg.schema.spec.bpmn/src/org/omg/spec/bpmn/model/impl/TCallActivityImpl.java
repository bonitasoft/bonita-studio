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
import org.omg.spec.bpmn.model.TCallActivity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TCall Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCallActivityImpl#getCalledElement <em>Called Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TCallActivityImpl extends TActivityImpl implements TCallActivity {
	/**
	 * The default value of the '{@link #getCalledElement() <em>Called Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledElement()
	 * @generated
	 * @ordered
	 */
	protected static final QName CALLED_ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCalledElement() <em>Called Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledElement()
	 * @generated
	 * @ordered
	 */
	protected QName calledElement = CALLED_ELEMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TCallActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TCALL_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getCalledElement() {
		return calledElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalledElement(QName newCalledElement) {
		QName oldCalledElement = calledElement;
		calledElement = newCalledElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCALL_ACTIVITY__CALLED_ELEMENT, oldCalledElement, calledElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TCALL_ACTIVITY__CALLED_ELEMENT:
				return getCalledElement();
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
			case ModelPackage.TCALL_ACTIVITY__CALLED_ELEMENT:
				setCalledElement((QName)newValue);
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
			case ModelPackage.TCALL_ACTIVITY__CALLED_ELEMENT:
				setCalledElement(CALLED_ELEMENT_EDEFAULT);
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
			case ModelPackage.TCALL_ACTIVITY__CALLED_ELEMENT:
				return CALLED_ELEMENT_EDEFAULT == null ? calledElement != null : !CALLED_ELEMENT_EDEFAULT.equals(calledElement);
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
		result.append(" (calledElement: ");
		result.append(calledElement);
		result.append(')');
		return result.toString();
	}

} //TCallActivityImpl
