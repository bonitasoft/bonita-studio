/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.TimeEstimationType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time Estimation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.TimeEstimationTypeImpl#getWaitingTime <em>Waiting Time</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.TimeEstimationTypeImpl#getWorkingTime <em>Working Time</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.TimeEstimationTypeImpl#getDuration <em>Duration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeEstimationTypeImpl extends EObjectImpl implements TimeEstimationType {
	/**
	 * The default value of the '{@link #getWaitingTime() <em>Waiting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaitingTime()
	 * @generated
	 * @ordered
	 */
	protected static final String WAITING_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWaitingTime() <em>Waiting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaitingTime()
	 * @generated
	 * @ordered
	 */
	protected String waitingTime = WAITING_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getWorkingTime() <em>Working Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkingTime()
	 * @generated
	 * @ordered
	 */
	protected static final String WORKING_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkingTime() <em>Working Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkingTime()
	 * @generated
	 * @ordered
	 */
	protected String workingTime = WORKING_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final String DURATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected String duration = DURATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeEstimationTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.TIME_ESTIMATION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWaitingTime() {
		return waitingTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaitingTime(String newWaitingTime) {
		String oldWaitingTime = waitingTime;
		waitingTime = newWaitingTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.TIME_ESTIMATION_TYPE__WAITING_TIME, oldWaitingTime, waitingTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkingTime() {
		return workingTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingTime(String newWorkingTime) {
		String oldWorkingTime = workingTime;
		workingTime = newWorkingTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.TIME_ESTIMATION_TYPE__WORKING_TIME, oldWorkingTime, workingTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(String newDuration) {
		String oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.TIME_ESTIMATION_TYPE__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xpdl1Package.TIME_ESTIMATION_TYPE__WAITING_TIME:
				return getWaitingTime();
			case Xpdl1Package.TIME_ESTIMATION_TYPE__WORKING_TIME:
				return getWorkingTime();
			case Xpdl1Package.TIME_ESTIMATION_TYPE__DURATION:
				return getDuration();
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
			case Xpdl1Package.TIME_ESTIMATION_TYPE__WAITING_TIME:
				setWaitingTime((String)newValue);
				return;
			case Xpdl1Package.TIME_ESTIMATION_TYPE__WORKING_TIME:
				setWorkingTime((String)newValue);
				return;
			case Xpdl1Package.TIME_ESTIMATION_TYPE__DURATION:
				setDuration((String)newValue);
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
			case Xpdl1Package.TIME_ESTIMATION_TYPE__WAITING_TIME:
				setWaitingTime(WAITING_TIME_EDEFAULT);
				return;
			case Xpdl1Package.TIME_ESTIMATION_TYPE__WORKING_TIME:
				setWorkingTime(WORKING_TIME_EDEFAULT);
				return;
			case Xpdl1Package.TIME_ESTIMATION_TYPE__DURATION:
				setDuration(DURATION_EDEFAULT);
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
			case Xpdl1Package.TIME_ESTIMATION_TYPE__WAITING_TIME:
				return WAITING_TIME_EDEFAULT == null ? waitingTime != null : !WAITING_TIME_EDEFAULT.equals(waitingTime);
			case Xpdl1Package.TIME_ESTIMATION_TYPE__WORKING_TIME:
				return WORKING_TIME_EDEFAULT == null ? workingTime != null : !WORKING_TIME_EDEFAULT.equals(workingTime);
			case Xpdl1Package.TIME_ESTIMATION_TYPE__DURATION:
				return DURATION_EDEFAULT == null ? duration != null : !DURATION_EDEFAULT.equals(duration);
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
		result.append(" (waitingTime: ");
		result.append(waitingTime);
		result.append(", workingTime: ");
		result.append(workingTime);
		result.append(", duration: ");
		result.append(duration);
		result.append(')');
		return result.toString();
	}

} //TimeEstimationTypeImpl
