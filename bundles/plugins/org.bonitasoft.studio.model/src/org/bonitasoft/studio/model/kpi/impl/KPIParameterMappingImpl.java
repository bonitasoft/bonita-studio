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
package org.bonitasoft.studio.model.kpi.impl;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.kpi.KPIParameterMapping;
import org.bonitasoft.studio.model.kpi.KpiPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPI Parameter Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.KPIParameterMappingImpl#getKpiFieldName <em>Kpi Field Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.KPIParameterMappingImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KPIParameterMappingImpl extends EObjectImpl implements KPIParameterMapping {
	/**
     * The default value of the '{@link #getKpiFieldName() <em>Kpi Field Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getKpiFieldName()
     * @generated
     * @ordered
     */
	protected static final String KPI_FIELD_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getKpiFieldName() <em>Kpi Field Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getKpiFieldName()
     * @generated
     * @ordered
     */
	protected String kpiFieldName = KPI_FIELD_NAME_EDEFAULT;

	/**
     * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
	protected Expression value;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected KPIParameterMappingImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return KpiPackage.Literals.KPI_PARAMETER_MAPPING;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getKpiFieldName() {
        return kpiFieldName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setKpiFieldName(String newKpiFieldName) {
        String oldKpiFieldName = kpiFieldName;
        kpiFieldName = newKpiFieldName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.KPI_PARAMETER_MAPPING__KPI_FIELD_NAME, oldKpiFieldName, kpiFieldName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getValue() {
        return value;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValue(Expression newValue, NotificationChain msgs) {
        Expression oldValue = value;
        value = newValue;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.KPI_PARAMETER_MAPPING__VALUE, oldValue, newValue);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setValue(Expression newValue) {
        if (newValue != value) {
            NotificationChain msgs = null;
            if (value != null)
                msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.KPI_PARAMETER_MAPPING__VALUE, null, msgs);
            if (newValue != null)
                msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.KPI_PARAMETER_MAPPING__VALUE, null, msgs);
            msgs = basicSetValue(newValue, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.KPI_PARAMETER_MAPPING__VALUE, newValue, newValue));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KpiPackage.KPI_PARAMETER_MAPPING__VALUE:
                return basicSetValue(null, msgs);
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
            case KpiPackage.KPI_PARAMETER_MAPPING__KPI_FIELD_NAME:
                return getKpiFieldName();
            case KpiPackage.KPI_PARAMETER_MAPPING__VALUE:
                return getValue();
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
            case KpiPackage.KPI_PARAMETER_MAPPING__KPI_FIELD_NAME:
                setKpiFieldName((String)newValue);
                return;
            case KpiPackage.KPI_PARAMETER_MAPPING__VALUE:
                setValue((Expression)newValue);
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
            case KpiPackage.KPI_PARAMETER_MAPPING__KPI_FIELD_NAME:
                setKpiFieldName(KPI_FIELD_NAME_EDEFAULT);
                return;
            case KpiPackage.KPI_PARAMETER_MAPPING__VALUE:
                setValue((Expression)null);
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
            case KpiPackage.KPI_PARAMETER_MAPPING__KPI_FIELD_NAME:
                return KPI_FIELD_NAME_EDEFAULT == null ? kpiFieldName != null : !KPI_FIELD_NAME_EDEFAULT.equals(kpiFieldName);
            case KpiPackage.KPI_PARAMETER_MAPPING__VALUE:
                return value != null;
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
        result.append(" (kpiFieldName: "); //$NON-NLS-1$
        result.append(kpiFieldName);
        result.append(')');
        return result.toString();
    }

} //KPIParameterMappingImpl
