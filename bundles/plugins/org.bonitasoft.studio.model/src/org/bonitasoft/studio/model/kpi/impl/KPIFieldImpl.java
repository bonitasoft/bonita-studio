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

import org.bonitasoft.studio.model.kpi.KPIField;
import org.bonitasoft.studio.model.kpi.KpiPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPI Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.KPIFieldImpl#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.KPIFieldImpl#getFieldType <em>Field Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.KPIFieldImpl#isUseQuotes <em>Use Quotes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KPIFieldImpl extends EObjectImpl implements KPIField {
	/**
     * The default value of the '{@link #getFieldName() <em>Field Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFieldName()
     * @generated
     * @ordered
     */
	protected static final String FIELD_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getFieldName() <em>Field Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFieldName()
     * @generated
     * @ordered
     */
	protected String fieldName = FIELD_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getFieldType() <em>Field Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFieldType()
     * @generated
     * @ordered
     */
	protected static final String FIELD_TYPE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getFieldType() <em>Field Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFieldType()
     * @generated
     * @ordered
     */
	protected String fieldType = FIELD_TYPE_EDEFAULT;

	/**
     * The default value of the '{@link #isUseQuotes() <em>Use Quotes</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUseQuotes()
     * @generated
     * @ordered
     */
	protected static final boolean USE_QUOTES_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isUseQuotes() <em>Use Quotes</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUseQuotes()
     * @generated
     * @ordered
     */
	protected boolean useQuotes = USE_QUOTES_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected KPIFieldImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return KpiPackage.Literals.KPI_FIELD;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getFieldName() {
        return fieldName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setFieldName(String newFieldName) {
        String oldFieldName = fieldName;
        fieldName = newFieldName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.KPI_FIELD__FIELD_NAME, oldFieldName, fieldName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getFieldType() {
        return fieldType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setFieldType(String newFieldType) {
        String oldFieldType = fieldType;
        fieldType = newFieldType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.KPI_FIELD__FIELD_TYPE, oldFieldType, fieldType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isUseQuotes() {
        return useQuotes;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUseQuotes(boolean newUseQuotes) {
        boolean oldUseQuotes = useQuotes;
        useQuotes = newUseQuotes;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.KPI_FIELD__USE_QUOTES, oldUseQuotes, useQuotes));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KpiPackage.KPI_FIELD__FIELD_NAME:
                return getFieldName();
            case KpiPackage.KPI_FIELD__FIELD_TYPE:
                return getFieldType();
            case KpiPackage.KPI_FIELD__USE_QUOTES:
                return isUseQuotes();
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
            case KpiPackage.KPI_FIELD__FIELD_NAME:
                setFieldName((String)newValue);
                return;
            case KpiPackage.KPI_FIELD__FIELD_TYPE:
                setFieldType((String)newValue);
                return;
            case KpiPackage.KPI_FIELD__USE_QUOTES:
                setUseQuotes((Boolean)newValue);
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
            case KpiPackage.KPI_FIELD__FIELD_NAME:
                setFieldName(FIELD_NAME_EDEFAULT);
                return;
            case KpiPackage.KPI_FIELD__FIELD_TYPE:
                setFieldType(FIELD_TYPE_EDEFAULT);
                return;
            case KpiPackage.KPI_FIELD__USE_QUOTES:
                setUseQuotes(USE_QUOTES_EDEFAULT);
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
            case KpiPackage.KPI_FIELD__FIELD_NAME:
                return FIELD_NAME_EDEFAULT == null ? fieldName != null : !FIELD_NAME_EDEFAULT.equals(fieldName);
            case KpiPackage.KPI_FIELD__FIELD_TYPE:
                return FIELD_TYPE_EDEFAULT == null ? fieldType != null : !FIELD_TYPE_EDEFAULT.equals(fieldType);
            case KpiPackage.KPI_FIELD__USE_QUOTES:
                return useQuotes != USE_QUOTES_EDEFAULT;
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
        result.append(" (fieldName: "); //$NON-NLS-1$
        result.append(fieldName);
        result.append(", fieldType: "); //$NON-NLS-1$
        result.append(fieldType);
        result.append(", useQuotes: "); //$NON-NLS-1$
        result.append(useQuotes);
        result.append(')');
        return result.toString();
    }

} //KPIFieldImpl
