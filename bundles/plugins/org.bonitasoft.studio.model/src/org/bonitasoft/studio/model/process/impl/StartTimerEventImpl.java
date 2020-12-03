/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.impl;

import java.util.Date;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start Timer Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl#getAt <em>At</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl#getMonth <em>Month</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl#getDay <em>Day</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl#getHours <em>Hours</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl#getDayNumber <em>Day Number</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl#getMinutes <em>Minutes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl#getSeconds <em>Seconds</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.StartTimerEventImpl#getScriptType <em>Script Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StartTimerEventImpl extends TimerEventImpl implements StartTimerEvent {
	/**
     * The default value of the '{@link #getFrom() <em>From</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFrom()
     * @generated
     * @ordered
     */
	protected static final Date FROM_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getFrom() <em>From</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFrom()
     * @generated
     * @ordered
     */
	protected Date from = FROM_EDEFAULT;

	/**
     * The default value of the '{@link #getAt() <em>At</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAt()
     * @generated
     * @ordered
     */
	protected static final Date AT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getAt() <em>At</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAt()
     * @generated
     * @ordered
     */
	protected Date at = AT_EDEFAULT;

	/**
     * The default value of the '{@link #getMonth() <em>Month</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMonth()
     * @generated
     * @ordered
     */
	protected static final int MONTH_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getMonth() <em>Month</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMonth()
     * @generated
     * @ordered
     */
	protected int month = MONTH_EDEFAULT;

	/**
     * The default value of the '{@link #getDay() <em>Day</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDay()
     * @generated
     * @ordered
     */
	protected static final int DAY_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getDay() <em>Day</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDay()
     * @generated
     * @ordered
     */
	protected int day = DAY_EDEFAULT;

	/**
     * The default value of the '{@link #getHours() <em>Hours</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHours()
     * @generated
     * @ordered
     */
	protected static final int HOURS_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getHours() <em>Hours</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHours()
     * @generated
     * @ordered
     */
	protected int hours = HOURS_EDEFAULT;

	/**
     * The default value of the '{@link #getDayNumber() <em>Day Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDayNumber()
     * @generated
     * @ordered
     */
	protected static final int DAY_NUMBER_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getDayNumber() <em>Day Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDayNumber()
     * @generated
     * @ordered
     */
	protected int dayNumber = DAY_NUMBER_EDEFAULT;

	/**
     * The default value of the '{@link #getMinutes() <em>Minutes</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMinutes()
     * @generated
     * @ordered
     */
	protected static final int MINUTES_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getMinutes() <em>Minutes</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMinutes()
     * @generated
     * @ordered
     */
	protected int minutes = MINUTES_EDEFAULT;

	/**
     * The default value of the '{@link #getSeconds() <em>Seconds</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSeconds()
     * @generated
     * @ordered
     */
	protected static final int SECONDS_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getSeconds() <em>Seconds</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSeconds()
     * @generated
     * @ordered
     */
	protected int seconds = SECONDS_EDEFAULT;

	/**
     * The default value of the '{@link #getScriptType() <em>Script Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getScriptType()
     * @generated
     * @ordered
     */
	protected static final StartTimerScriptType SCRIPT_TYPE_EDEFAULT = StartTimerScriptType.GROOVY;

	/**
     * The cached value of the '{@link #getScriptType() <em>Script Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getScriptType()
     * @generated
     * @ordered
     */
	protected StartTimerScriptType scriptType = SCRIPT_TYPE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected StartTimerEventImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.START_TIMER_EVENT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Date getFrom() {
        return from;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setFrom(Date newFrom) {
        Date oldFrom = from;
        from = newFrom;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.START_TIMER_EVENT__FROM, oldFrom, from));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Date getAt() {
        return at;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAt(Date newAt) {
        Date oldAt = at;
        at = newAt;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.START_TIMER_EVENT__AT, oldAt, at));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getMonth() {
        return month;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMonth(int newMonth) {
        int oldMonth = month;
        month = newMonth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.START_TIMER_EVENT__MONTH, oldMonth, month));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getDay() {
        return day;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDay(int newDay) {
        int oldDay = day;
        day = newDay;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.START_TIMER_EVENT__DAY, oldDay, day));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getHours() {
        return hours;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setHours(int newHours) {
        int oldHours = hours;
        hours = newHours;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.START_TIMER_EVENT__HOURS, oldHours, hours));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getDayNumber() {
        return dayNumber;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDayNumber(int newDayNumber) {
        int oldDayNumber = dayNumber;
        dayNumber = newDayNumber;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.START_TIMER_EVENT__DAY_NUMBER, oldDayNumber, dayNumber));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getMinutes() {
        return minutes;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMinutes(int newMinutes) {
        int oldMinutes = minutes;
        minutes = newMinutes;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.START_TIMER_EVENT__MINUTES, oldMinutes, minutes));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getSeconds() {
        return seconds;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setSeconds(int newSeconds) {
        int oldSeconds = seconds;
        seconds = newSeconds;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.START_TIMER_EVENT__SECONDS, oldSeconds, seconds));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public StartTimerScriptType getScriptType() {
        return scriptType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setScriptType(StartTimerScriptType newScriptType) {
        StartTimerScriptType oldScriptType = scriptType;
        scriptType = newScriptType == null ? SCRIPT_TYPE_EDEFAULT : newScriptType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.START_TIMER_EVENT__SCRIPT_TYPE, oldScriptType, scriptType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProcessPackage.START_TIMER_EVENT__FROM:
                return getFrom();
            case ProcessPackage.START_TIMER_EVENT__AT:
                return getAt();
            case ProcessPackage.START_TIMER_EVENT__MONTH:
                return getMonth();
            case ProcessPackage.START_TIMER_EVENT__DAY:
                return getDay();
            case ProcessPackage.START_TIMER_EVENT__HOURS:
                return getHours();
            case ProcessPackage.START_TIMER_EVENT__DAY_NUMBER:
                return getDayNumber();
            case ProcessPackage.START_TIMER_EVENT__MINUTES:
                return getMinutes();
            case ProcessPackage.START_TIMER_EVENT__SECONDS:
                return getSeconds();
            case ProcessPackage.START_TIMER_EVENT__SCRIPT_TYPE:
                return getScriptType();
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
            case ProcessPackage.START_TIMER_EVENT__FROM:
                setFrom((Date)newValue);
                return;
            case ProcessPackage.START_TIMER_EVENT__AT:
                setAt((Date)newValue);
                return;
            case ProcessPackage.START_TIMER_EVENT__MONTH:
                setMonth((Integer)newValue);
                return;
            case ProcessPackage.START_TIMER_EVENT__DAY:
                setDay((Integer)newValue);
                return;
            case ProcessPackage.START_TIMER_EVENT__HOURS:
                setHours((Integer)newValue);
                return;
            case ProcessPackage.START_TIMER_EVENT__DAY_NUMBER:
                setDayNumber((Integer)newValue);
                return;
            case ProcessPackage.START_TIMER_EVENT__MINUTES:
                setMinutes((Integer)newValue);
                return;
            case ProcessPackage.START_TIMER_EVENT__SECONDS:
                setSeconds((Integer)newValue);
                return;
            case ProcessPackage.START_TIMER_EVENT__SCRIPT_TYPE:
                setScriptType((StartTimerScriptType)newValue);
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
            case ProcessPackage.START_TIMER_EVENT__FROM:
                setFrom(FROM_EDEFAULT);
                return;
            case ProcessPackage.START_TIMER_EVENT__AT:
                setAt(AT_EDEFAULT);
                return;
            case ProcessPackage.START_TIMER_EVENT__MONTH:
                setMonth(MONTH_EDEFAULT);
                return;
            case ProcessPackage.START_TIMER_EVENT__DAY:
                setDay(DAY_EDEFAULT);
                return;
            case ProcessPackage.START_TIMER_EVENT__HOURS:
                setHours(HOURS_EDEFAULT);
                return;
            case ProcessPackage.START_TIMER_EVENT__DAY_NUMBER:
                setDayNumber(DAY_NUMBER_EDEFAULT);
                return;
            case ProcessPackage.START_TIMER_EVENT__MINUTES:
                setMinutes(MINUTES_EDEFAULT);
                return;
            case ProcessPackage.START_TIMER_EVENT__SECONDS:
                setSeconds(SECONDS_EDEFAULT);
                return;
            case ProcessPackage.START_TIMER_EVENT__SCRIPT_TYPE:
                setScriptType(SCRIPT_TYPE_EDEFAULT);
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
            case ProcessPackage.START_TIMER_EVENT__FROM:
                return FROM_EDEFAULT == null ? from != null : !FROM_EDEFAULT.equals(from);
            case ProcessPackage.START_TIMER_EVENT__AT:
                return AT_EDEFAULT == null ? at != null : !AT_EDEFAULT.equals(at);
            case ProcessPackage.START_TIMER_EVENT__MONTH:
                return month != MONTH_EDEFAULT;
            case ProcessPackage.START_TIMER_EVENT__DAY:
                return day != DAY_EDEFAULT;
            case ProcessPackage.START_TIMER_EVENT__HOURS:
                return hours != HOURS_EDEFAULT;
            case ProcessPackage.START_TIMER_EVENT__DAY_NUMBER:
                return dayNumber != DAY_NUMBER_EDEFAULT;
            case ProcessPackage.START_TIMER_EVENT__MINUTES:
                return minutes != MINUTES_EDEFAULT;
            case ProcessPackage.START_TIMER_EVENT__SECONDS:
                return seconds != SECONDS_EDEFAULT;
            case ProcessPackage.START_TIMER_EVENT__SCRIPT_TYPE:
                return scriptType != SCRIPT_TYPE_EDEFAULT;
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (from: "); //$NON-NLS-1$
        result.append(from);
        result.append(", at: "); //$NON-NLS-1$
        result.append(at);
        result.append(", month: "); //$NON-NLS-1$
        result.append(month);
        result.append(", day: "); //$NON-NLS-1$
        result.append(day);
        result.append(", hours: "); //$NON-NLS-1$
        result.append(hours);
        result.append(", dayNumber: "); //$NON-NLS-1$
        result.append(dayNumber);
        result.append(", minutes: "); //$NON-NLS-1$
        result.append(minutes);
        result.append(", seconds: "); //$NON-NLS-1$
        result.append(seconds);
        result.append(", scriptType: "); //$NON-NLS-1$
        result.append(scriptType);
        result.append(')');
        return result.toString();
    }

} //StartTimerEventImpl
