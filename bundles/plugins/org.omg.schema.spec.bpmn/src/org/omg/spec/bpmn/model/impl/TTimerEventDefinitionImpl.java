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
import org.omg.spec.bpmn.model.TTimerEventDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TTimer Event Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TTimerEventDefinitionImpl#getTimeDate <em>Time Date</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TTimerEventDefinitionImpl#getTimeDuration <em>Time Duration</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TTimerEventDefinitionImpl#getTimeCycle <em>Time Cycle</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TTimerEventDefinitionImpl extends TEventDefinitionImpl implements TTimerEventDefinition {
	/**
	 * The cached value of the '{@link #getTimeDate() <em>Time Date</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeDate()
	 * @generated
	 * @ordered
	 */
	protected TExpression timeDate;

	/**
	 * The cached value of the '{@link #getTimeDuration() <em>Time Duration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeDuration()
	 * @generated
	 * @ordered
	 */
	protected TExpression timeDuration;

	/**
	 * The cached value of the '{@link #getTimeCycle() <em>Time Cycle</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeCycle()
	 * @generated
	 * @ordered
	 */
	protected TExpression timeCycle;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TTimerEventDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TTIMER_EVENT_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExpression getTimeDate() {
		return timeDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeDate(TExpression newTimeDate, NotificationChain msgs) {
		TExpression oldTimeDate = timeDate;
		timeDate = newTimeDate;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DATE, oldTimeDate, newTimeDate);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeDate(TExpression newTimeDate) {
		if (newTimeDate != timeDate) {
			NotificationChain msgs = null;
			if (timeDate != null)
				msgs = ((InternalEObject)timeDate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DATE, null, msgs);
			if (newTimeDate != null)
				msgs = ((InternalEObject)newTimeDate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DATE, null, msgs);
			msgs = basicSetTimeDate(newTimeDate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DATE, newTimeDate, newTimeDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExpression getTimeDuration() {
		return timeDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeDuration(TExpression newTimeDuration, NotificationChain msgs) {
		TExpression oldTimeDuration = timeDuration;
		timeDuration = newTimeDuration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DURATION, oldTimeDuration, newTimeDuration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeDuration(TExpression newTimeDuration) {
		if (newTimeDuration != timeDuration) {
			NotificationChain msgs = null;
			if (timeDuration != null)
				msgs = ((InternalEObject)timeDuration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DURATION, null, msgs);
			if (newTimeDuration != null)
				msgs = ((InternalEObject)newTimeDuration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DURATION, null, msgs);
			msgs = basicSetTimeDuration(newTimeDuration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DURATION, newTimeDuration, newTimeDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExpression getTimeCycle() {
		return timeCycle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeCycle(TExpression newTimeCycle, NotificationChain msgs) {
		TExpression oldTimeCycle = timeCycle;
		timeCycle = newTimeCycle;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TTIMER_EVENT_DEFINITION__TIME_CYCLE, oldTimeCycle, newTimeCycle);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeCycle(TExpression newTimeCycle) {
		if (newTimeCycle != timeCycle) {
			NotificationChain msgs = null;
			if (timeCycle != null)
				msgs = ((InternalEObject)timeCycle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TTIMER_EVENT_DEFINITION__TIME_CYCLE, null, msgs);
			if (newTimeCycle != null)
				msgs = ((InternalEObject)newTimeCycle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TTIMER_EVENT_DEFINITION__TIME_CYCLE, null, msgs);
			msgs = basicSetTimeCycle(newTimeCycle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TTIMER_EVENT_DEFINITION__TIME_CYCLE, newTimeCycle, newTimeCycle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DATE:
				return basicSetTimeDate(null, msgs);
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DURATION:
				return basicSetTimeDuration(null, msgs);
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_CYCLE:
				return basicSetTimeCycle(null, msgs);
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
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DATE:
				return getTimeDate();
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DURATION:
				return getTimeDuration();
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_CYCLE:
				return getTimeCycle();
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
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DATE:
				setTimeDate((TExpression)newValue);
				return;
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DURATION:
				setTimeDuration((TExpression)newValue);
				return;
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_CYCLE:
				setTimeCycle((TExpression)newValue);
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
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DATE:
				setTimeDate((TExpression)null);
				return;
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DURATION:
				setTimeDuration((TExpression)null);
				return;
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_CYCLE:
				setTimeCycle((TExpression)null);
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
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DATE:
				return timeDate != null;
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_DURATION:
				return timeDuration != null;
			case ModelPackage.TTIMER_EVENT_DEFINITION__TIME_CYCLE:
				return timeCycle != null;
		}
		return super.eIsSet(featureID);
	}

} //TTimerEventDefinitionImpl
