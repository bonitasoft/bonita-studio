/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TStandardLoopCharacteristics;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TStandard Loop Characteristics</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TStandardLoopCharacteristicsImpl#getLoopCondition <em>Loop Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TStandardLoopCharacteristicsImpl#getLoopMaximum <em>Loop Maximum</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TStandardLoopCharacteristicsImpl#isTestBefore <em>Test Before</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TStandardLoopCharacteristicsImpl extends TLoopCharacteristicsImpl implements TStandardLoopCharacteristics {
	/**
	 * The cached value of the '{@link #getLoopCondition() <em>Loop Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopCondition()
	 * @generated
	 * @ordered
	 */
	protected TExpression loopCondition;

	/**
	 * The default value of the '{@link #getLoopMaximum() <em>Loop Maximum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopMaximum()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger LOOP_MAXIMUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLoopMaximum() <em>Loop Maximum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopMaximum()
	 * @generated
	 * @ordered
	 */
	protected BigInteger loopMaximum = LOOP_MAXIMUM_EDEFAULT;

	/**
	 * The default value of the '{@link #isTestBefore() <em>Test Before</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTestBefore()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TEST_BEFORE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTestBefore() <em>Test Before</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTestBefore()
	 * @generated
	 * @ordered
	 */
	protected boolean testBefore = TEST_BEFORE_EDEFAULT;

	/**
	 * This is true if the Test Before attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean testBeforeESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TStandardLoopCharacteristicsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TSTANDARD_LOOP_CHARACTERISTICS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExpression getLoopCondition() {
		return loopCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoopCondition(TExpression newLoopCondition, NotificationChain msgs) {
		TExpression oldLoopCondition = loopCondition;
		loopCondition = newLoopCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION, oldLoopCondition, newLoopCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopCondition(TExpression newLoopCondition) {
		if (newLoopCondition != loopCondition) {
			NotificationChain msgs = null;
			if (loopCondition != null)
				msgs = ((InternalEObject)loopCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION, null, msgs);
			if (newLoopCondition != null)
				msgs = ((InternalEObject)newLoopCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION, null, msgs);
			msgs = basicSetLoopCondition(newLoopCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION, newLoopCondition, newLoopCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getLoopMaximum() {
		return loopMaximum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopMaximum(BigInteger newLoopMaximum) {
		BigInteger oldLoopMaximum = loopMaximum;
		loopMaximum = newLoopMaximum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM, oldLoopMaximum, loopMaximum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTestBefore() {
		return testBefore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestBefore(boolean newTestBefore) {
		boolean oldTestBefore = testBefore;
		testBefore = newTestBefore;
		boolean oldTestBeforeESet = testBeforeESet;
		testBeforeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE, oldTestBefore, testBefore, !oldTestBeforeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTestBefore() {
		boolean oldTestBefore = testBefore;
		boolean oldTestBeforeESet = testBeforeESet;
		testBefore = TEST_BEFORE_EDEFAULT;
		testBeforeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE, oldTestBefore, TEST_BEFORE_EDEFAULT, oldTestBeforeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTestBefore() {
		return testBeforeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
				return basicSetLoopCondition(null, msgs);
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
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
				return getLoopCondition();
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM:
				return getLoopMaximum();
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE:
				return isTestBefore();
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
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
				setLoopCondition((TExpression)newValue);
				return;
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM:
				setLoopMaximum((BigInteger)newValue);
				return;
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE:
				setTestBefore((Boolean)newValue);
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
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
				setLoopCondition((TExpression)null);
				return;
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM:
				setLoopMaximum(LOOP_MAXIMUM_EDEFAULT);
				return;
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE:
				unsetTestBefore();
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
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION:
				return loopCondition != null;
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM:
				return LOOP_MAXIMUM_EDEFAULT == null ? loopMaximum != null : !LOOP_MAXIMUM_EDEFAULT.equals(loopMaximum);
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE:
				return isSetTestBefore();
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
		result.append(" (loopMaximum: ");
		result.append(loopMaximum);
		result.append(", testBefore: ");
		if (testBeforeESet) result.append(testBefore); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TStandardLoopCharacteristicsImpl
