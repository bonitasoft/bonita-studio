/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TSequenceFlow;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TSequence Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSequenceFlowImpl#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSequenceFlowImpl#isIsImmediate <em>Is Immediate</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSequenceFlowImpl#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSequenceFlowImpl#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TSequenceFlowImpl extends TFlowElementImpl implements TSequenceFlow {
	/**
	 * The cached value of the '{@link #getConditionExpression() <em>Condition Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionExpression()
	 * @generated
	 * @ordered
	 */
	protected TExpression conditionExpression;

	/**
	 * The default value of the '{@link #isIsImmediate() <em>Is Immediate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImmediate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_IMMEDIATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsImmediate() <em>Is Immediate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImmediate()
	 * @generated
	 * @ordered
	 */
	protected boolean isImmediate = IS_IMMEDIATE_EDEFAULT;

	/**
	 * This is true if the Is Immediate attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isImmediateESet;

	/**
	 * The default value of the '{@link #getSourceRef() <em>Source Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRef()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceRef() <em>Source Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRef()
	 * @generated
	 * @ordered
	 */
	protected String sourceRef = SOURCE_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetRef() <em>Target Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRef()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetRef() <em>Target Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRef()
	 * @generated
	 * @ordered
	 */
	protected String targetRef = TARGET_REF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TSequenceFlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TSEQUENCE_FLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExpression getConditionExpression() {
		return conditionExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConditionExpression(TExpression newConditionExpression, NotificationChain msgs) {
		TExpression oldConditionExpression = conditionExpression;
		conditionExpression = newConditionExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TSEQUENCE_FLOW__CONDITION_EXPRESSION, oldConditionExpression, newConditionExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConditionExpression(TExpression newConditionExpression) {
		if (newConditionExpression != conditionExpression) {
			NotificationChain msgs = null;
			if (conditionExpression != null)
				msgs = ((InternalEObject)conditionExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TSEQUENCE_FLOW__CONDITION_EXPRESSION, null, msgs);
			if (newConditionExpression != null)
				msgs = ((InternalEObject)newConditionExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TSEQUENCE_FLOW__CONDITION_EXPRESSION, null, msgs);
			msgs = basicSetConditionExpression(newConditionExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSEQUENCE_FLOW__CONDITION_EXPRESSION, newConditionExpression, newConditionExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsImmediate() {
		return isImmediate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsImmediate(boolean newIsImmediate) {
		boolean oldIsImmediate = isImmediate;
		isImmediate = newIsImmediate;
		boolean oldIsImmediateESet = isImmediateESet;
		isImmediateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSEQUENCE_FLOW__IS_IMMEDIATE, oldIsImmediate, isImmediate, !oldIsImmediateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsImmediate() {
		boolean oldIsImmediate = isImmediate;
		boolean oldIsImmediateESet = isImmediateESet;
		isImmediate = IS_IMMEDIATE_EDEFAULT;
		isImmediateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TSEQUENCE_FLOW__IS_IMMEDIATE, oldIsImmediate, IS_IMMEDIATE_EDEFAULT, oldIsImmediateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsImmediate() {
		return isImmediateESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceRef() {
		return sourceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceRef(String newSourceRef) {
		String oldSourceRef = sourceRef;
		sourceRef = newSourceRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSEQUENCE_FLOW__SOURCE_REF, oldSourceRef, sourceRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetRef() {
		return targetRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetRef(String newTargetRef) {
		String oldTargetRef = targetRef;
		targetRef = newTargetRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSEQUENCE_FLOW__TARGET_REF, oldTargetRef, targetRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TSEQUENCE_FLOW__CONDITION_EXPRESSION:
				return basicSetConditionExpression(null, msgs);
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
			case ModelPackage.TSEQUENCE_FLOW__CONDITION_EXPRESSION:
				return getConditionExpression();
			case ModelPackage.TSEQUENCE_FLOW__IS_IMMEDIATE:
				return isIsImmediate();
			case ModelPackage.TSEQUENCE_FLOW__SOURCE_REF:
				return getSourceRef();
			case ModelPackage.TSEQUENCE_FLOW__TARGET_REF:
				return getTargetRef();
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
			case ModelPackage.TSEQUENCE_FLOW__CONDITION_EXPRESSION:
				setConditionExpression((TExpression)newValue);
				return;
			case ModelPackage.TSEQUENCE_FLOW__IS_IMMEDIATE:
				setIsImmediate((Boolean)newValue);
				return;
			case ModelPackage.TSEQUENCE_FLOW__SOURCE_REF:
				setSourceRef((String)newValue);
				return;
			case ModelPackage.TSEQUENCE_FLOW__TARGET_REF:
				setTargetRef((String)newValue);
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
			case ModelPackage.TSEQUENCE_FLOW__CONDITION_EXPRESSION:
				setConditionExpression((TExpression)null);
				return;
			case ModelPackage.TSEQUENCE_FLOW__IS_IMMEDIATE:
				unsetIsImmediate();
				return;
			case ModelPackage.TSEQUENCE_FLOW__SOURCE_REF:
				setSourceRef(SOURCE_REF_EDEFAULT);
				return;
			case ModelPackage.TSEQUENCE_FLOW__TARGET_REF:
				setTargetRef(TARGET_REF_EDEFAULT);
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
			case ModelPackage.TSEQUENCE_FLOW__CONDITION_EXPRESSION:
				return conditionExpression != null;
			case ModelPackage.TSEQUENCE_FLOW__IS_IMMEDIATE:
				return isSetIsImmediate();
			case ModelPackage.TSEQUENCE_FLOW__SOURCE_REF:
				return SOURCE_REF_EDEFAULT == null ? sourceRef != null : !SOURCE_REF_EDEFAULT.equals(sourceRef);
			case ModelPackage.TSEQUENCE_FLOW__TARGET_REF:
				return TARGET_REF_EDEFAULT == null ? targetRef != null : !TARGET_REF_EDEFAULT.equals(targetRef);
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
		result.append(" (isImmediate: ");
		if (isImmediateESet) result.append(isImmediate); else result.append("<unset>");
		result.append(", sourceRef: ");
		result.append(sourceRef);
		result.append(", targetRef: ");
		result.append(targetRef);
		result.append(')');
		return result.toString();
	}

} //TSequenceFlowImpl
