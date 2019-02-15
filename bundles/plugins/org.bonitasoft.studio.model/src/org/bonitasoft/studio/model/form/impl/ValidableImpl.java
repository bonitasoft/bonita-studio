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

import java.util.Collection;

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Validable;
import org.bonitasoft.studio.model.form.Validator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Validable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.ValidableImpl#getValidators <em>Validators</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.ValidableImpl#getUseDefaultValidator <em>Use Default Validator</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.ValidableImpl#isBelow <em>Below</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValidableImpl extends EObjectImpl implements Validable {
	/**
     * The cached value of the '{@link #getValidators() <em>Validators</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValidators()
     * @generated
     * @ordered
     */
	protected EList<Validator> validators;

	/**
     * The default value of the '{@link #getUseDefaultValidator() <em>Use Default Validator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUseDefaultValidator()
     * @generated
     * @ordered
     */
	protected static final Boolean USE_DEFAULT_VALIDATOR_EDEFAULT = Boolean.TRUE;

	/**
     * The cached value of the '{@link #getUseDefaultValidator() <em>Use Default Validator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUseDefaultValidator()
     * @generated
     * @ordered
     */
	protected Boolean useDefaultValidator = USE_DEFAULT_VALIDATOR_EDEFAULT;

	/**
     * The default value of the '{@link #isBelow() <em>Below</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isBelow()
     * @generated
     * @ordered
     */
	protected static final boolean BELOW_EDEFAULT = true;

	/**
     * The cached value of the '{@link #isBelow() <em>Below</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isBelow()
     * @generated
     * @ordered
     */
	protected boolean below = BELOW_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ValidableImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.VALIDABLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Validator> getValidators() {
        if (validators == null) {
            validators = new EObjectContainmentEList<Validator>(Validator.class, this, FormPackage.VALIDABLE__VALIDATORS);
        }
        return validators;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Boolean getUseDefaultValidator() {
        return useDefaultValidator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUseDefaultValidator(Boolean newUseDefaultValidator) {
        Boolean oldUseDefaultValidator = useDefaultValidator;
        useDefaultValidator = newUseDefaultValidator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR, oldUseDefaultValidator, useDefaultValidator));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isBelow() {
        return below;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setBelow(boolean newBelow) {
        boolean oldBelow = below;
        below = newBelow;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.VALIDABLE__BELOW, oldBelow, below));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FormPackage.VALIDABLE__VALIDATORS:
                return ((InternalEList<?>)getValidators()).basicRemove(otherEnd, msgs);
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
            case FormPackage.VALIDABLE__VALIDATORS:
                return getValidators();
            case FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR:
                return getUseDefaultValidator();
            case FormPackage.VALIDABLE__BELOW:
                return isBelow();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FormPackage.VALIDABLE__VALIDATORS:
                getValidators().clear();
                getValidators().addAll((Collection<? extends Validator>)newValue);
                return;
            case FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR:
                setUseDefaultValidator((Boolean)newValue);
                return;
            case FormPackage.VALIDABLE__BELOW:
                setBelow((Boolean)newValue);
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
            case FormPackage.VALIDABLE__VALIDATORS:
                getValidators().clear();
                return;
            case FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR:
                setUseDefaultValidator(USE_DEFAULT_VALIDATOR_EDEFAULT);
                return;
            case FormPackage.VALIDABLE__BELOW:
                setBelow(BELOW_EDEFAULT);
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
            case FormPackage.VALIDABLE__VALIDATORS:
                return validators != null && !validators.isEmpty();
            case FormPackage.VALIDABLE__USE_DEFAULT_VALIDATOR:
                return USE_DEFAULT_VALIDATOR_EDEFAULT == null ? useDefaultValidator != null : !USE_DEFAULT_VALIDATOR_EDEFAULT.equals(useDefaultValidator);
            case FormPackage.VALIDABLE__BELOW:
                return below != BELOW_EDEFAULT;
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
        result.append(" (useDefaultValidator: "); //$NON-NLS-1$
        result.append(useDefaultValidator);
        result.append(", below: "); //$NON-NLS-1$
        result.append(below);
        result.append(')');
        return result.toString();
    }

} //ValidableImpl
