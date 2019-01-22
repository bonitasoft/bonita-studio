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

import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.FormPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Date Form Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DateFormFieldImpl#getInitialFormat <em>Initial Format</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.DateFormFieldImpl#getDisplayFormat <em>Display Format</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DateFormFieldImpl extends SingleValuatedFormFieldImpl implements DateFormField {
	/**
	 * The default value of the '{@link #getInitialFormat() <em>Initial Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialFormat()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIAL_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitialFormat() <em>Initial Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialFormat()
	 * @generated
	 * @ordered
	 */
	protected String initialFormat = INITIAL_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplayFormat() <em>Display Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayFormat()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayFormat() <em>Display Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayFormat()
	 * @generated
	 * @ordered
	 */
	protected String displayFormat = DISPLAY_FORMAT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DateFormFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.DATE_FORM_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInitialFormat() {
		return initialFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInitialFormat(String newInitialFormat) {
		String oldInitialFormat = initialFormat;
		initialFormat = newInitialFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DATE_FORM_FIELD__INITIAL_FORMAT, oldInitialFormat, initialFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDisplayFormat() {
		return displayFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisplayFormat(String newDisplayFormat) {
		String oldDisplayFormat = displayFormat;
		displayFormat = newDisplayFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.DATE_FORM_FIELD__DISPLAY_FORMAT, oldDisplayFormat, displayFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.DATE_FORM_FIELD__INITIAL_FORMAT:
				return getInitialFormat();
			case FormPackage.DATE_FORM_FIELD__DISPLAY_FORMAT:
				return getDisplayFormat();
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
			case FormPackage.DATE_FORM_FIELD__INITIAL_FORMAT:
				setInitialFormat((String)newValue);
				return;
			case FormPackage.DATE_FORM_FIELD__DISPLAY_FORMAT:
				setDisplayFormat((String)newValue);
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
			case FormPackage.DATE_FORM_FIELD__INITIAL_FORMAT:
				setInitialFormat(INITIAL_FORMAT_EDEFAULT);
				return;
			case FormPackage.DATE_FORM_FIELD__DISPLAY_FORMAT:
				setDisplayFormat(DISPLAY_FORMAT_EDEFAULT);
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
			case FormPackage.DATE_FORM_FIELD__INITIAL_FORMAT:
				return INITIAL_FORMAT_EDEFAULT == null ? initialFormat != null : !INITIAL_FORMAT_EDEFAULT.equals(initialFormat);
			case FormPackage.DATE_FORM_FIELD__DISPLAY_FORMAT:
				return DISPLAY_FORMAT_EDEFAULT == null ? displayFormat != null : !DISPLAY_FORMAT_EDEFAULT.equals(displayFormat);
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
		result.append(" (initialFormat: "); //$NON-NLS-1$
		result.append(initialFormat);
		result.append(", displayFormat: "); //$NON-NLS-1$
		result.append(displayFormat);
		result.append(')');
		return result.toString();
	}

} //DateFormFieldImpl
