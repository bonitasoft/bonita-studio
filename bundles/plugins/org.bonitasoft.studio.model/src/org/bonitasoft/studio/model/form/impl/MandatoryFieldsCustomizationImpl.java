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

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.MandatoryFieldsCustomization;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mandatory Fields Customization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.MandatoryFieldsCustomizationImpl#getMandatorySymbol <em>Mandatory Symbol</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.MandatoryFieldsCustomizationImpl#getMandatoryLabel <em>Mandatory Label</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class MandatoryFieldsCustomizationImpl extends CSSCustomizableImpl implements MandatoryFieldsCustomization {
	/**
	 * The cached value of the '{@link #getMandatorySymbol() <em>Mandatory Symbol</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMandatorySymbol()
	 * @generated
	 * @ordered
	 */
	protected Expression mandatorySymbol;

	/**
	 * The cached value of the '{@link #getMandatoryLabel() <em>Mandatory Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMandatoryLabel()
	 * @generated
	 * @ordered
	 */
	protected Expression mandatoryLabel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MandatoryFieldsCustomizationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getMandatorySymbol() {
		return mandatorySymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMandatorySymbol(Expression newMandatorySymbol, NotificationChain msgs) {
		Expression oldMandatorySymbol = mandatorySymbol;
		mandatorySymbol = newMandatorySymbol;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL, oldMandatorySymbol, newMandatorySymbol);
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
	public void setMandatorySymbol(Expression newMandatorySymbol) {
		if (newMandatorySymbol != mandatorySymbol) {
			NotificationChain msgs = null;
			if (mandatorySymbol != null)
				msgs = ((InternalEObject)mandatorySymbol).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL, null, msgs);
			if (newMandatorySymbol != null)
				msgs = ((InternalEObject)newMandatorySymbol).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL, null, msgs);
			msgs = basicSetMandatorySymbol(newMandatorySymbol, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL, newMandatorySymbol, newMandatorySymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getMandatoryLabel() {
		return mandatoryLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMandatoryLabel(Expression newMandatoryLabel, NotificationChain msgs) {
		Expression oldMandatoryLabel = mandatoryLabel;
		mandatoryLabel = newMandatoryLabel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL, oldMandatoryLabel, newMandatoryLabel);
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
	public void setMandatoryLabel(Expression newMandatoryLabel) {
		if (newMandatoryLabel != mandatoryLabel) {
			NotificationChain msgs = null;
			if (mandatoryLabel != null)
				msgs = ((InternalEObject)mandatoryLabel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL, null, msgs);
			if (newMandatoryLabel != null)
				msgs = ((InternalEObject)newMandatoryLabel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL, null, msgs);
			msgs = basicSetMandatoryLabel(newMandatoryLabel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL, newMandatoryLabel, newMandatoryLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL:
				return basicSetMandatorySymbol(null, msgs);
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL:
				return basicSetMandatoryLabel(null, msgs);
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
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL:
				return getMandatorySymbol();
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL:
				return getMandatoryLabel();
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
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL:
				setMandatorySymbol((Expression)newValue);
				return;
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL:
				setMandatoryLabel((Expression)newValue);
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
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL:
				setMandatorySymbol((Expression)null);
				return;
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL:
				setMandatoryLabel((Expression)null);
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
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL:
				return mandatorySymbol != null;
			case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL:
				return mandatoryLabel != null;
		}
		return super.eIsSet(featureID);
	}

} //MandatoryFieldsCustomizationImpl
