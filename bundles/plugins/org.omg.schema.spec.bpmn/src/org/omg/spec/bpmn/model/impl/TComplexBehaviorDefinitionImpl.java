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
import org.omg.spec.bpmn.model.TComplexBehaviorDefinition;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TImplicitThrowEvent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TComplex Behavior Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TComplexBehaviorDefinitionImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TComplexBehaviorDefinitionImpl#getEvent <em>Event</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TComplexBehaviorDefinitionImpl extends TBaseElementImpl implements TComplexBehaviorDefinition {
	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected TFormalExpression condition;

	/**
	 * The cached value of the '{@link #getEvent() <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvent()
	 * @generated
	 * @ordered
	 */
	protected TImplicitThrowEvent event;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TComplexBehaviorDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TCOMPLEX_BEHAVIOR_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFormalExpression getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(TFormalExpression newCondition, NotificationChain msgs) {
		TFormalExpression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION, oldCondition, newCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(TFormalExpression newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TImplicitThrowEvent getEvent() {
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEvent(TImplicitThrowEvent newEvent, NotificationChain msgs) {
		TImplicitThrowEvent oldEvent = event;
		event = newEvent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__EVENT, oldEvent, newEvent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvent(TImplicitThrowEvent newEvent) {
		if (newEvent != event) {
			NotificationChain msgs = null;
			if (event != null)
				msgs = ((InternalEObject)event).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__EVENT, null, msgs);
			if (newEvent != null)
				msgs = ((InternalEObject)newEvent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__EVENT, null, msgs);
			msgs = basicSetEvent(newEvent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__EVENT, newEvent, newEvent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION:
				return basicSetCondition(null, msgs);
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__EVENT:
				return basicSetEvent(null, msgs);
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
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION:
				return getCondition();
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__EVENT:
				return getEvent();
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
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION:
				setCondition((TFormalExpression)newValue);
				return;
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__EVENT:
				setEvent((TImplicitThrowEvent)newValue);
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
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION:
				setCondition((TFormalExpression)null);
				return;
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__EVENT:
				setEvent((TImplicitThrowEvent)null);
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
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION:
				return condition != null;
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION__EVENT:
				return event != null;
		}
		return super.eIsSet(featureID);
	}

} //TComplexBehaviorDefinitionImpl
