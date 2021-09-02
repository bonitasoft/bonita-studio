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
package org.bonitasoft.studio.model.form.impl;

import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ItemContainer;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Duration Form Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DurationFormFieldImpl#getItemClass <em>Item Class</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DurationFormFieldImpl#getDay <em>Day</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DurationFormFieldImpl#getHour <em>Hour</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DurationFormFieldImpl#getMin <em>Min</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DurationFormFieldImpl#getSec <em>Sec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DurationFormFieldImpl extends SingleValuatedFormFieldImpl implements DurationFormField {
	/**
     * The default value of the '{@link #getItemClass() <em>Item Class</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getItemClass()
     * @generated
     * @ordered
     */
	protected static final String ITEM_CLASS_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getItemClass() <em>Item Class</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getItemClass()
     * @generated
     * @ordered
     */
	protected String itemClass = ITEM_CLASS_EDEFAULT;

	/**
     * The default value of the '{@link #getDay() <em>Day</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDay()
     * @generated
     * @ordered
     */
	protected static final Boolean DAY_EDEFAULT = Boolean.TRUE;

	/**
     * The cached value of the '{@link #getDay() <em>Day</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDay()
     * @generated
     * @ordered
     */
	protected Boolean day = DAY_EDEFAULT;

	/**
     * The default value of the '{@link #getHour() <em>Hour</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHour()
     * @generated
     * @ordered
     */
	protected static final Boolean HOUR_EDEFAULT = Boolean.TRUE;

	/**
     * The cached value of the '{@link #getHour() <em>Hour</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHour()
     * @generated
     * @ordered
     */
	protected Boolean hour = HOUR_EDEFAULT;

	/**
     * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
	protected static final Boolean MIN_EDEFAULT = Boolean.TRUE;

	/**
     * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
	protected Boolean min = MIN_EDEFAULT;

	/**
     * The default value of the '{@link #getSec() <em>Sec</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSec()
     * @generated
     * @ordered
     */
	protected static final Boolean SEC_EDEFAULT = Boolean.TRUE;

	/**
     * The cached value of the '{@link #getSec() <em>Sec</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSec()
     * @generated
     * @ordered
     */
	protected Boolean sec = SEC_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected DurationFormFieldImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.DURATION_FORM_FIELD;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getItemClass() {
        return itemClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setItemClass(String newItemClass) {
        String oldItemClass = itemClass;
        itemClass = newItemClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DURATION_FORM_FIELD__ITEM_CLASS, oldItemClass, itemClass));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Boolean getDay() {
        return day;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDay(Boolean newDay) {
        Boolean oldDay = day;
        day = newDay;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DURATION_FORM_FIELD__DAY, oldDay, day));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Boolean getHour() {
        return hour;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setHour(Boolean newHour) {
        Boolean oldHour = hour;
        hour = newHour;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DURATION_FORM_FIELD__HOUR, oldHour, hour));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Boolean getMin() {
        return min;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMin(Boolean newMin) {
        Boolean oldMin = min;
        min = newMin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DURATION_FORM_FIELD__MIN, oldMin, min));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Boolean getSec() {
        return sec;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setSec(Boolean newSec) {
        Boolean oldSec = sec;
        sec = newSec;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DURATION_FORM_FIELD__SEC, oldSec, sec));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FormPackage.DURATION_FORM_FIELD__ITEM_CLASS:
                return getItemClass();
            case FormPackage.DURATION_FORM_FIELD__DAY:
                return getDay();
            case FormPackage.DURATION_FORM_FIELD__HOUR:
                return getHour();
            case FormPackage.DURATION_FORM_FIELD__MIN:
                return getMin();
            case FormPackage.DURATION_FORM_FIELD__SEC:
                return getSec();
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
            case FormPackage.DURATION_FORM_FIELD__ITEM_CLASS:
                setItemClass((String)newValue);
                return;
            case FormPackage.DURATION_FORM_FIELD__DAY:
                setDay((Boolean)newValue);
                return;
            case FormPackage.DURATION_FORM_FIELD__HOUR:
                setHour((Boolean)newValue);
                return;
            case FormPackage.DURATION_FORM_FIELD__MIN:
                setMin((Boolean)newValue);
                return;
            case FormPackage.DURATION_FORM_FIELD__SEC:
                setSec((Boolean)newValue);
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
            case FormPackage.DURATION_FORM_FIELD__ITEM_CLASS:
                setItemClass(ITEM_CLASS_EDEFAULT);
                return;
            case FormPackage.DURATION_FORM_FIELD__DAY:
                setDay(DAY_EDEFAULT);
                return;
            case FormPackage.DURATION_FORM_FIELD__HOUR:
                setHour(HOUR_EDEFAULT);
                return;
            case FormPackage.DURATION_FORM_FIELD__MIN:
                setMin(MIN_EDEFAULT);
                return;
            case FormPackage.DURATION_FORM_FIELD__SEC:
                setSec(SEC_EDEFAULT);
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
            case FormPackage.DURATION_FORM_FIELD__ITEM_CLASS:
                return ITEM_CLASS_EDEFAULT == null ? itemClass != null : !ITEM_CLASS_EDEFAULT.equals(itemClass);
            case FormPackage.DURATION_FORM_FIELD__DAY:
                return DAY_EDEFAULT == null ? day != null : !DAY_EDEFAULT.equals(day);
            case FormPackage.DURATION_FORM_FIELD__HOUR:
                return HOUR_EDEFAULT == null ? hour != null : !HOUR_EDEFAULT.equals(hour);
            case FormPackage.DURATION_FORM_FIELD__MIN:
                return MIN_EDEFAULT == null ? min != null : !MIN_EDEFAULT.equals(min);
            case FormPackage.DURATION_FORM_FIELD__SEC:
                return SEC_EDEFAULT == null ? sec != null : !SEC_EDEFAULT.equals(sec);
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == ItemContainer.class) {
            switch (derivedFeatureID) {
                case FormPackage.DURATION_FORM_FIELD__ITEM_CLASS: return FormPackage.ITEM_CONTAINER__ITEM_CLASS;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == ItemContainer.class) {
            switch (baseFeatureID) {
                case FormPackage.ITEM_CONTAINER__ITEM_CLASS: return FormPackage.DURATION_FORM_FIELD__ITEM_CLASS;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (itemClass: "); //$NON-NLS-1$
        result.append(itemClass);
        result.append(", day: "); //$NON-NLS-1$
        result.append(day);
        result.append(", hour: "); //$NON-NLS-1$
        result.append(hour);
        result.append(", min: "); //$NON-NLS-1$
        result.append(min);
        result.append(", sec: "); //$NON-NLS-1$
        result.append(sec);
        result.append(')');
        return result.toString();
    }

} //DurationFormFieldImpl
