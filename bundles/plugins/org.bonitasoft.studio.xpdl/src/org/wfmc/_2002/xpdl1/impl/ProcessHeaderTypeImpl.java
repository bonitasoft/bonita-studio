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
import org.wfmc._2002.xpdl1.DurationUnitType;
import org.wfmc._2002.xpdl1.ProcessHeaderType;
import org.wfmc._2002.xpdl1.TimeEstimationType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Header Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl#getLimit <em>Limit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl#getValidTo <em>Valid To</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl#getTimeEstimation <em>Time Estimation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl#getDurationUnit <em>Duration Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessHeaderTypeImpl extends EObjectImpl implements ProcessHeaderType {
	/**
	 * The default value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected String created = CREATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIORITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected String priority = PRIORITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getLimit() <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected static final String LIMIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLimit() <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected String limit = LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidFrom() <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidFrom()
	 * @generated
	 * @ordered
	 */
	protected static final String VALID_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidFrom() <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidFrom()
	 * @generated
	 * @ordered
	 */
	protected String validFrom = VALID_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidTo() <em>Valid To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidTo()
	 * @generated
	 * @ordered
	 */
	protected static final String VALID_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidTo() <em>Valid To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidTo()
	 * @generated
	 * @ordered
	 */
	protected String validTo = VALID_TO_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTimeEstimation() <em>Time Estimation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeEstimation()
	 * @generated
	 * @ordered
	 */
	protected TimeEstimationType timeEstimation;

	/**
	 * The default value of the '{@link #getDurationUnit() <em>Duration Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDurationUnit()
	 * @generated
	 * @ordered
	 */
	protected static final DurationUnitType DURATION_UNIT_EDEFAULT = DurationUnitType.Y;

	/**
	 * The cached value of the '{@link #getDurationUnit() <em>Duration Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDurationUnit()
	 * @generated
	 * @ordered
	 */
	protected DurationUnitType durationUnit = DURATION_UNIT_EDEFAULT;

	/**
	 * This is true if the Duration Unit attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean durationUnitESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessHeaderTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.PROCESS_HEADER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreated(String newCreated) {
		String oldCreated = created;
		created = newCreated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PROCESS_HEADER_TYPE__CREATED, oldCreated, created));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PROCESS_HEADER_TYPE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(String newPriority) {
		String oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PROCESS_HEADER_TYPE__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLimit() {
		return limit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLimit(String newLimit) {
		String oldLimit = limit;
		limit = newLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PROCESS_HEADER_TYPE__LIMIT, oldLimit, limit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValidFrom() {
		return validFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidFrom(String newValidFrom) {
		String oldValidFrom = validFrom;
		validFrom = newValidFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PROCESS_HEADER_TYPE__VALID_FROM, oldValidFrom, validFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValidTo() {
		return validTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidTo(String newValidTo) {
		String oldValidTo = validTo;
		validTo = newValidTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PROCESS_HEADER_TYPE__VALID_TO, oldValidTo, validTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeEstimationType getTimeEstimation() {
		return timeEstimation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeEstimation(TimeEstimationType newTimeEstimation, NotificationChain msgs) {
		TimeEstimationType oldTimeEstimation = timeEstimation;
		timeEstimation = newTimeEstimation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PROCESS_HEADER_TYPE__TIME_ESTIMATION, oldTimeEstimation, newTimeEstimation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeEstimation(TimeEstimationType newTimeEstimation) {
		if (newTimeEstimation != timeEstimation) {
			NotificationChain msgs = null;
			if (timeEstimation != null)
				msgs = ((InternalEObject)timeEstimation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PROCESS_HEADER_TYPE__TIME_ESTIMATION, null, msgs);
			if (newTimeEstimation != null)
				msgs = ((InternalEObject)newTimeEstimation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PROCESS_HEADER_TYPE__TIME_ESTIMATION, null, msgs);
			msgs = basicSetTimeEstimation(newTimeEstimation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PROCESS_HEADER_TYPE__TIME_ESTIMATION, newTimeEstimation, newTimeEstimation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DurationUnitType getDurationUnit() {
		return durationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDurationUnit(DurationUnitType newDurationUnit) {
		DurationUnitType oldDurationUnit = durationUnit;
		durationUnit = newDurationUnit == null ? DURATION_UNIT_EDEFAULT : newDurationUnit;
		boolean oldDurationUnitESet = durationUnitESet;
		durationUnitESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PROCESS_HEADER_TYPE__DURATION_UNIT, oldDurationUnit, durationUnit, !oldDurationUnitESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDurationUnit() {
		DurationUnitType oldDurationUnit = durationUnit;
		boolean oldDurationUnitESet = durationUnitESet;
		durationUnit = DURATION_UNIT_EDEFAULT;
		durationUnitESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Xpdl1Package.PROCESS_HEADER_TYPE__DURATION_UNIT, oldDurationUnit, DURATION_UNIT_EDEFAULT, oldDurationUnitESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDurationUnit() {
		return durationUnitESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.PROCESS_HEADER_TYPE__TIME_ESTIMATION:
				return basicSetTimeEstimation(null, msgs);
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
			case Xpdl1Package.PROCESS_HEADER_TYPE__CREATED:
				return getCreated();
			case Xpdl1Package.PROCESS_HEADER_TYPE__DESCRIPTION:
				return getDescription();
			case Xpdl1Package.PROCESS_HEADER_TYPE__PRIORITY:
				return getPriority();
			case Xpdl1Package.PROCESS_HEADER_TYPE__LIMIT:
				return getLimit();
			case Xpdl1Package.PROCESS_HEADER_TYPE__VALID_FROM:
				return getValidFrom();
			case Xpdl1Package.PROCESS_HEADER_TYPE__VALID_TO:
				return getValidTo();
			case Xpdl1Package.PROCESS_HEADER_TYPE__TIME_ESTIMATION:
				return getTimeEstimation();
			case Xpdl1Package.PROCESS_HEADER_TYPE__DURATION_UNIT:
				return getDurationUnit();
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
			case Xpdl1Package.PROCESS_HEADER_TYPE__CREATED:
				setCreated((String)newValue);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__PRIORITY:
				setPriority((String)newValue);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__LIMIT:
				setLimit((String)newValue);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__VALID_FROM:
				setValidFrom((String)newValue);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__VALID_TO:
				setValidTo((String)newValue);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__TIME_ESTIMATION:
				setTimeEstimation((TimeEstimationType)newValue);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__DURATION_UNIT:
				setDurationUnit((DurationUnitType)newValue);
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
			case Xpdl1Package.PROCESS_HEADER_TYPE__CREATED:
				setCreated(CREATED_EDEFAULT);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__LIMIT:
				setLimit(LIMIT_EDEFAULT);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__VALID_FROM:
				setValidFrom(VALID_FROM_EDEFAULT);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__VALID_TO:
				setValidTo(VALID_TO_EDEFAULT);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__TIME_ESTIMATION:
				setTimeEstimation((TimeEstimationType)null);
				return;
			case Xpdl1Package.PROCESS_HEADER_TYPE__DURATION_UNIT:
				unsetDurationUnit();
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
			case Xpdl1Package.PROCESS_HEADER_TYPE__CREATED:
				return CREATED_EDEFAULT == null ? created != null : !CREATED_EDEFAULT.equals(created);
			case Xpdl1Package.PROCESS_HEADER_TYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case Xpdl1Package.PROCESS_HEADER_TYPE__PRIORITY:
				return PRIORITY_EDEFAULT == null ? priority != null : !PRIORITY_EDEFAULT.equals(priority);
			case Xpdl1Package.PROCESS_HEADER_TYPE__LIMIT:
				return LIMIT_EDEFAULT == null ? limit != null : !LIMIT_EDEFAULT.equals(limit);
			case Xpdl1Package.PROCESS_HEADER_TYPE__VALID_FROM:
				return VALID_FROM_EDEFAULT == null ? validFrom != null : !VALID_FROM_EDEFAULT.equals(validFrom);
			case Xpdl1Package.PROCESS_HEADER_TYPE__VALID_TO:
				return VALID_TO_EDEFAULT == null ? validTo != null : !VALID_TO_EDEFAULT.equals(validTo);
			case Xpdl1Package.PROCESS_HEADER_TYPE__TIME_ESTIMATION:
				return timeEstimation != null;
			case Xpdl1Package.PROCESS_HEADER_TYPE__DURATION_UNIT:
				return isSetDurationUnit();
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
		result.append(" (created: ");
		result.append(created);
		result.append(", description: ");
		result.append(description);
		result.append(", priority: ");
		result.append(priority);
		result.append(", limit: ");
		result.append(limit);
		result.append(", validFrom: ");
		result.append(validFrom);
		result.append(", validTo: ");
		result.append(validTo);
		result.append(", durationUnit: ");
		if (durationUnitESet) result.append(durationUnit); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ProcessHeaderTypeImpl
