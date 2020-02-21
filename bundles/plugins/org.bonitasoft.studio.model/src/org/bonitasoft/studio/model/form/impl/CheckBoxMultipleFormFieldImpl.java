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

import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ItemContainer;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Check Box Multiple Form Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.CheckBoxMultipleFormFieldImpl#getItemClass <em>Item Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CheckBoxMultipleFormFieldImpl extends MultipleValuatedFormFieldImpl implements CheckBoxMultipleFormField {
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
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected CheckBoxMultipleFormFieldImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD__ITEM_CLASS, oldItemClass, itemClass));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD__ITEM_CLASS:
                return getItemClass();
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
            case FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD__ITEM_CLASS:
                setItemClass((String)newValue);
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
            case FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD__ITEM_CLASS:
                setItemClass(ITEM_CLASS_EDEFAULT);
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
            case FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD__ITEM_CLASS:
                return ITEM_CLASS_EDEFAULT == null ? itemClass != null : !ITEM_CLASS_EDEFAULT.equals(itemClass);
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
                case FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD__ITEM_CLASS: return FormPackage.ITEM_CONTAINER__ITEM_CLASS;
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
                case FormPackage.ITEM_CONTAINER__ITEM_CLASS: return FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD__ITEM_CLASS;
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
        result.append(')');
        return result.toString();
    }

} //CheckBoxMultipleFormFieldImpl
