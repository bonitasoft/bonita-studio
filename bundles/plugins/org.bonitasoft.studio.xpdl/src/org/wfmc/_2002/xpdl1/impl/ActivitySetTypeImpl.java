/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.ActivitiesType;
import org.wfmc._2002.xpdl1.ActivitySetType;
import org.wfmc._2002.xpdl1.TransitionsType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Set Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivitySetTypeImpl#getActivities <em>Activities</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivitySetTypeImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivitySetTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivitySetTypeImpl extends EObjectImpl implements ActivitySetType {
	/**
	 * The cached value of the '{@link #getActivities() <em>Activities</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivities()
	 * @generated
	 * @ordered
	 */
	protected ActivitiesType activities;

	/**
	 * The cached value of the '{@link #getTransitions() <em>Transitions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitions()
	 * @generated
	 * @ordered
	 */
	protected TransitionsType transitions;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivitySetTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.ACTIVITY_SET_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitiesType getActivities() {
		return activities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivities(ActivitiesType newActivities, NotificationChain msgs) {
		ActivitiesType oldActivities = activities;
		activities = newActivities;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_SET_TYPE__ACTIVITIES, oldActivities, newActivities);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivities(ActivitiesType newActivities) {
		if (newActivities != activities) {
			NotificationChain msgs = null;
			if (activities != null)
				msgs = ((InternalEObject)activities).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_SET_TYPE__ACTIVITIES, null, msgs);
			if (newActivities != null)
				msgs = ((InternalEObject)newActivities).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_SET_TYPE__ACTIVITIES, null, msgs);
			msgs = basicSetActivities(newActivities, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_SET_TYPE__ACTIVITIES, newActivities, newActivities));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionsType getTransitions() {
		return transitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransitions(TransitionsType newTransitions, NotificationChain msgs) {
		TransitionsType oldTransitions = transitions;
		transitions = newTransitions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_SET_TYPE__TRANSITIONS, oldTransitions, newTransitions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitions(TransitionsType newTransitions) {
		if (newTransitions != transitions) {
			NotificationChain msgs = null;
			if (transitions != null)
				msgs = ((InternalEObject)transitions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_SET_TYPE__TRANSITIONS, null, msgs);
			if (newTransitions != null)
				msgs = ((InternalEObject)newTransitions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_SET_TYPE__TRANSITIONS, null, msgs);
			msgs = basicSetTransitions(newTransitions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_SET_TYPE__TRANSITIONS, newTransitions, newTransitions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_SET_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.ACTIVITY_SET_TYPE__ACTIVITIES:
				return basicSetActivities(null, msgs);
			case Xpdl1Package.ACTIVITY_SET_TYPE__TRANSITIONS:
				return basicSetTransitions(null, msgs);
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
			case Xpdl1Package.ACTIVITY_SET_TYPE__ACTIVITIES:
				return getActivities();
			case Xpdl1Package.ACTIVITY_SET_TYPE__TRANSITIONS:
				return getTransitions();
			case Xpdl1Package.ACTIVITY_SET_TYPE__ID:
				return getId();
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
			case Xpdl1Package.ACTIVITY_SET_TYPE__ACTIVITIES:
				setActivities((ActivitiesType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_SET_TYPE__TRANSITIONS:
				setTransitions((TransitionsType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_SET_TYPE__ID:
				setId((String)newValue);
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
			case Xpdl1Package.ACTIVITY_SET_TYPE__ACTIVITIES:
				setActivities((ActivitiesType)null);
				return;
			case Xpdl1Package.ACTIVITY_SET_TYPE__TRANSITIONS:
				setTransitions((TransitionsType)null);
				return;
			case Xpdl1Package.ACTIVITY_SET_TYPE__ID:
				setId(ID_EDEFAULT);
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
			case Xpdl1Package.ACTIVITY_SET_TYPE__ACTIVITIES:
				return activities != null;
			case Xpdl1Package.ACTIVITY_SET_TYPE__TRANSITIONS:
				return transitions != null;
			case Xpdl1Package.ACTIVITY_SET_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ActivitySetTypeImpl
