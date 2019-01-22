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

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.SuggestBox;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Suggest Box</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.SuggestBoxImpl#getMaxItems <em>Max Items</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.SuggestBoxImpl#isUseMaxItems <em>Use Max Items</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.SuggestBoxImpl#isAsynchronous <em>Asynchronous</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.SuggestBoxImpl#getDelay <em>Delay</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SuggestBoxImpl extends MultipleValuatedFormFieldImpl implements SuggestBox {
	/**
	 * The default value of the '{@link #getMaxItems() <em>Max Items</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxItems()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_ITEMS_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getMaxItems() <em>Max Items</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxItems()
	 * @generated
	 * @ordered
	 */
	protected int maxItems = MAX_ITEMS_EDEFAULT;

	/**
	 * The default value of the '{@link #isUseMaxItems() <em>Use Max Items</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseMaxItems()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USE_MAX_ITEMS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUseMaxItems() <em>Use Max Items</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseMaxItems()
	 * @generated
	 * @ordered
	 */
	protected boolean useMaxItems = USE_MAX_ITEMS_EDEFAULT;

	/**
	 * The default value of the '{@link #isAsynchronous() <em>Asynchronous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsynchronous()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ASYNCHRONOUS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAsynchronous() <em>Asynchronous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsynchronous()
	 * @generated
	 * @ordered
	 */
	protected boolean asynchronous = ASYNCHRONOUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected static final int DELAY_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected int delay = DELAY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SuggestBoxImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.SUGGEST_BOX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getMaxItems() {
		return maxItems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxItems(int newMaxItems) {
		int oldMaxItems = maxItems;
		maxItems = newMaxItems;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.SUGGEST_BOX__MAX_ITEMS, oldMaxItems, maxItems));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUseMaxItems() {
		return useMaxItems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUseMaxItems(boolean newUseMaxItems) {
		boolean oldUseMaxItems = useMaxItems;
		useMaxItems = newUseMaxItems;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.SUGGEST_BOX__USE_MAX_ITEMS, oldUseMaxItems, useMaxItems));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAsynchronous() {
		return asynchronous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsynchronous(boolean newAsynchronous) {
		boolean oldAsynchronous = asynchronous;
		asynchronous = newAsynchronous;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.SUGGEST_BOX__ASYNCHRONOUS, oldAsynchronous, asynchronous));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDelay() {
		return delay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDelay(int newDelay) {
		int oldDelay = delay;
		delay = newDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.SUGGEST_BOX__DELAY, oldDelay, delay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.SUGGEST_BOX__MAX_ITEMS:
				return getMaxItems();
			case FormPackage.SUGGEST_BOX__USE_MAX_ITEMS:
				return isUseMaxItems();
			case FormPackage.SUGGEST_BOX__ASYNCHRONOUS:
				return isAsynchronous();
			case FormPackage.SUGGEST_BOX__DELAY:
				return getDelay();
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
			case FormPackage.SUGGEST_BOX__MAX_ITEMS:
				setMaxItems((Integer)newValue);
				return;
			case FormPackage.SUGGEST_BOX__USE_MAX_ITEMS:
				setUseMaxItems((Boolean)newValue);
				return;
			case FormPackage.SUGGEST_BOX__ASYNCHRONOUS:
				setAsynchronous((Boolean)newValue);
				return;
			case FormPackage.SUGGEST_BOX__DELAY:
				setDelay((Integer)newValue);
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
			case FormPackage.SUGGEST_BOX__MAX_ITEMS:
				setMaxItems(MAX_ITEMS_EDEFAULT);
				return;
			case FormPackage.SUGGEST_BOX__USE_MAX_ITEMS:
				setUseMaxItems(USE_MAX_ITEMS_EDEFAULT);
				return;
			case FormPackage.SUGGEST_BOX__ASYNCHRONOUS:
				setAsynchronous(ASYNCHRONOUS_EDEFAULT);
				return;
			case FormPackage.SUGGEST_BOX__DELAY:
				setDelay(DELAY_EDEFAULT);
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
			case FormPackage.SUGGEST_BOX__MAX_ITEMS:
				return maxItems != MAX_ITEMS_EDEFAULT;
			case FormPackage.SUGGEST_BOX__USE_MAX_ITEMS:
				return useMaxItems != USE_MAX_ITEMS_EDEFAULT;
			case FormPackage.SUGGEST_BOX__ASYNCHRONOUS:
				return asynchronous != ASYNCHRONOUS_EDEFAULT;
			case FormPackage.SUGGEST_BOX__DELAY:
				return delay != DELAY_EDEFAULT;
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
		result.append(" (maxItems: "); //$NON-NLS-1$
		result.append(maxItems);
		result.append(", useMaxItems: "); //$NON-NLS-1$
		result.append(useMaxItems);
		result.append(", asynchronous: "); //$NON-NLS-1$
		result.append(asynchronous);
		result.append(", delay: "); //$NON-NLS-1$
		result.append(delay);
		result.append(')');
		return result.toString();
	}

} //SuggestBoxImpl
