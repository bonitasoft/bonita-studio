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

import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.TextInfo;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Text Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl#isDuplicate <em>Duplicate</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl#isLimitNumberOfDuplication <em>Limit Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl#isLimitMinNumberOfDuplication <em>Limit Min Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl#getMaxNumberOfDuplication <em>Max Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl#getMinNumberOfDuplication <em>Min Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl#getDisplayLabelForAdd <em>Display Label For Add</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl#getTooltipForAdd <em>Tooltip For Add</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl#getDisplayLabelForRemove <em>Display Label For Remove</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl#getTooltipForRemove <em>Tooltip For Remove</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TextInfoImpl extends InfoImpl implements TextInfo {
	/**
     * The default value of the '{@link #isDuplicate() <em>Duplicate</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDuplicate()
     * @generated
     * @ordered
     */
	protected static final boolean DUPLICATE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isDuplicate() <em>Duplicate</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDuplicate()
     * @generated
     * @ordered
     */
	protected boolean duplicate = DUPLICATE_EDEFAULT;

	/**
     * The default value of the '{@link #isLimitNumberOfDuplication() <em>Limit Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLimitNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected static final boolean LIMIT_NUMBER_OF_DUPLICATION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isLimitNumberOfDuplication() <em>Limit Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLimitNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected boolean limitNumberOfDuplication = LIMIT_NUMBER_OF_DUPLICATION_EDEFAULT;

	/**
     * The default value of the '{@link #isLimitMinNumberOfDuplication() <em>Limit Min Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLimitMinNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected static final boolean LIMIT_MIN_NUMBER_OF_DUPLICATION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isLimitMinNumberOfDuplication() <em>Limit Min Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLimitMinNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected boolean limitMinNumberOfDuplication = LIMIT_MIN_NUMBER_OF_DUPLICATION_EDEFAULT;

	/**
     * The cached value of the '{@link #getMaxNumberOfDuplication() <em>Max Number Of Duplication</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMaxNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected Expression maxNumberOfDuplication;

	/**
     * The cached value of the '{@link #getMinNumberOfDuplication() <em>Min Number Of Duplication</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMinNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected Expression minNumberOfDuplication;

	/**
     * The cached value of the '{@link #getDisplayLabelForAdd() <em>Display Label For Add</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayLabelForAdd()
     * @generated
     * @ordered
     */
	protected Expression displayLabelForAdd;

	/**
     * The cached value of the '{@link #getTooltipForAdd() <em>Tooltip For Add</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTooltipForAdd()
     * @generated
     * @ordered
     */
	protected Expression tooltipForAdd;

	/**
     * The cached value of the '{@link #getDisplayLabelForRemove() <em>Display Label For Remove</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayLabelForRemove()
     * @generated
     * @ordered
     */
	protected Expression displayLabelForRemove;

	/**
     * The cached value of the '{@link #getTooltipForRemove() <em>Tooltip For Remove</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTooltipForRemove()
     * @generated
     * @ordered
     */
	protected Expression tooltipForRemove;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected TextInfoImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.TEXT_INFO;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isDuplicate() {
        return duplicate;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDuplicate(boolean newDuplicate) {
        boolean oldDuplicate = duplicate;
        duplicate = newDuplicate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__DUPLICATE, oldDuplicate, duplicate));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isLimitNumberOfDuplication() {
        return limitNumberOfDuplication;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setLimitNumberOfDuplication(boolean newLimitNumberOfDuplication) {
        boolean oldLimitNumberOfDuplication = limitNumberOfDuplication;
        limitNumberOfDuplication = newLimitNumberOfDuplication;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__LIMIT_NUMBER_OF_DUPLICATION, oldLimitNumberOfDuplication, limitNumberOfDuplication));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isLimitMinNumberOfDuplication() {
        return limitMinNumberOfDuplication;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setLimitMinNumberOfDuplication(boolean newLimitMinNumberOfDuplication) {
        boolean oldLimitMinNumberOfDuplication = limitMinNumberOfDuplication;
        limitMinNumberOfDuplication = newLimitMinNumberOfDuplication;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__LIMIT_MIN_NUMBER_OF_DUPLICATION, oldLimitMinNumberOfDuplication, limitMinNumberOfDuplication));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getMaxNumberOfDuplication() {
        return maxNumberOfDuplication;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMaxNumberOfDuplication(Expression newMaxNumberOfDuplication, NotificationChain msgs) {
        Expression oldMaxNumberOfDuplication = maxNumberOfDuplication;
        maxNumberOfDuplication = newMaxNumberOfDuplication;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION, oldMaxNumberOfDuplication, newMaxNumberOfDuplication);
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
	public void setMaxNumberOfDuplication(Expression newMaxNumberOfDuplication) {
        if (newMaxNumberOfDuplication != maxNumberOfDuplication) {
            NotificationChain msgs = null;
            if (maxNumberOfDuplication != null)
                msgs = ((InternalEObject)maxNumberOfDuplication).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION, null, msgs);
            if (newMaxNumberOfDuplication != null)
                msgs = ((InternalEObject)newMaxNumberOfDuplication).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION, null, msgs);
            msgs = basicSetMaxNumberOfDuplication(newMaxNumberOfDuplication, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION, newMaxNumberOfDuplication, newMaxNumberOfDuplication));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getMinNumberOfDuplication() {
        return minNumberOfDuplication;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMinNumberOfDuplication(Expression newMinNumberOfDuplication, NotificationChain msgs) {
        Expression oldMinNumberOfDuplication = minNumberOfDuplication;
        minNumberOfDuplication = newMinNumberOfDuplication;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION, oldMinNumberOfDuplication, newMinNumberOfDuplication);
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
	public void setMinNumberOfDuplication(Expression newMinNumberOfDuplication) {
        if (newMinNumberOfDuplication != minNumberOfDuplication) {
            NotificationChain msgs = null;
            if (minNumberOfDuplication != null)
                msgs = ((InternalEObject)minNumberOfDuplication).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION, null, msgs);
            if (newMinNumberOfDuplication != null)
                msgs = ((InternalEObject)newMinNumberOfDuplication).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION, null, msgs);
            msgs = basicSetMinNumberOfDuplication(newMinNumberOfDuplication, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION, newMinNumberOfDuplication, newMinNumberOfDuplication));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDisplayLabelForAdd() {
        return displayLabelForAdd;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDisplayLabelForAdd(Expression newDisplayLabelForAdd, NotificationChain msgs) {
        Expression oldDisplayLabelForAdd = displayLabelForAdd;
        displayLabelForAdd = newDisplayLabelForAdd;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD, oldDisplayLabelForAdd, newDisplayLabelForAdd);
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
	public void setDisplayLabelForAdd(Expression newDisplayLabelForAdd) {
        if (newDisplayLabelForAdd != displayLabelForAdd) {
            NotificationChain msgs = null;
            if (displayLabelForAdd != null)
                msgs = ((InternalEObject)displayLabelForAdd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD, null, msgs);
            if (newDisplayLabelForAdd != null)
                msgs = ((InternalEObject)newDisplayLabelForAdd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD, null, msgs);
            msgs = basicSetDisplayLabelForAdd(newDisplayLabelForAdd, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD, newDisplayLabelForAdd, newDisplayLabelForAdd));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getTooltipForAdd() {
        return tooltipForAdd;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTooltipForAdd(Expression newTooltipForAdd, NotificationChain msgs) {
        Expression oldTooltipForAdd = tooltipForAdd;
        tooltipForAdd = newTooltipForAdd;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD, oldTooltipForAdd, newTooltipForAdd);
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
	public void setTooltipForAdd(Expression newTooltipForAdd) {
        if (newTooltipForAdd != tooltipForAdd) {
            NotificationChain msgs = null;
            if (tooltipForAdd != null)
                msgs = ((InternalEObject)tooltipForAdd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD, null, msgs);
            if (newTooltipForAdd != null)
                msgs = ((InternalEObject)newTooltipForAdd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD, null, msgs);
            msgs = basicSetTooltipForAdd(newTooltipForAdd, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD, newTooltipForAdd, newTooltipForAdd));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDisplayLabelForRemove() {
        return displayLabelForRemove;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDisplayLabelForRemove(Expression newDisplayLabelForRemove, NotificationChain msgs) {
        Expression oldDisplayLabelForRemove = displayLabelForRemove;
        displayLabelForRemove = newDisplayLabelForRemove;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE, oldDisplayLabelForRemove, newDisplayLabelForRemove);
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
	public void setDisplayLabelForRemove(Expression newDisplayLabelForRemove) {
        if (newDisplayLabelForRemove != displayLabelForRemove) {
            NotificationChain msgs = null;
            if (displayLabelForRemove != null)
                msgs = ((InternalEObject)displayLabelForRemove).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE, null, msgs);
            if (newDisplayLabelForRemove != null)
                msgs = ((InternalEObject)newDisplayLabelForRemove).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE, null, msgs);
            msgs = basicSetDisplayLabelForRemove(newDisplayLabelForRemove, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE, newDisplayLabelForRemove, newDisplayLabelForRemove));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getTooltipForRemove() {
        return tooltipForRemove;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTooltipForRemove(Expression newTooltipForRemove, NotificationChain msgs) {
        Expression oldTooltipForRemove = tooltipForRemove;
        tooltipForRemove = newTooltipForRemove;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE, oldTooltipForRemove, newTooltipForRemove);
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
	public void setTooltipForRemove(Expression newTooltipForRemove) {
        if (newTooltipForRemove != tooltipForRemove) {
            NotificationChain msgs = null;
            if (tooltipForRemove != null)
                msgs = ((InternalEObject)tooltipForRemove).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE, null, msgs);
            if (newTooltipForRemove != null)
                msgs = ((InternalEObject)newTooltipForRemove).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE, null, msgs);
            msgs = basicSetTooltipForRemove(newTooltipForRemove, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE, newTooltipForRemove, newTooltipForRemove));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION:
                return basicSetMaxNumberOfDuplication(null, msgs);
            case FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION:
                return basicSetMinNumberOfDuplication(null, msgs);
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD:
                return basicSetDisplayLabelForAdd(null, msgs);
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD:
                return basicSetTooltipForAdd(null, msgs);
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE:
                return basicSetDisplayLabelForRemove(null, msgs);
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE:
                return basicSetTooltipForRemove(null, msgs);
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
            case FormPackage.TEXT_INFO__DUPLICATE:
                return isDuplicate();
            case FormPackage.TEXT_INFO__LIMIT_NUMBER_OF_DUPLICATION:
                return isLimitNumberOfDuplication();
            case FormPackage.TEXT_INFO__LIMIT_MIN_NUMBER_OF_DUPLICATION:
                return isLimitMinNumberOfDuplication();
            case FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION:
                return getMaxNumberOfDuplication();
            case FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION:
                return getMinNumberOfDuplication();
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD:
                return getDisplayLabelForAdd();
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD:
                return getTooltipForAdd();
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE:
                return getDisplayLabelForRemove();
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE:
                return getTooltipForRemove();
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
            case FormPackage.TEXT_INFO__DUPLICATE:
                setDuplicate((Boolean)newValue);
                return;
            case FormPackage.TEXT_INFO__LIMIT_NUMBER_OF_DUPLICATION:
                setLimitNumberOfDuplication((Boolean)newValue);
                return;
            case FormPackage.TEXT_INFO__LIMIT_MIN_NUMBER_OF_DUPLICATION:
                setLimitMinNumberOfDuplication((Boolean)newValue);
                return;
            case FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION:
                setMaxNumberOfDuplication((Expression)newValue);
                return;
            case FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION:
                setMinNumberOfDuplication((Expression)newValue);
                return;
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD:
                setDisplayLabelForAdd((Expression)newValue);
                return;
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD:
                setTooltipForAdd((Expression)newValue);
                return;
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE:
                setDisplayLabelForRemove((Expression)newValue);
                return;
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE:
                setTooltipForRemove((Expression)newValue);
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
            case FormPackage.TEXT_INFO__DUPLICATE:
                setDuplicate(DUPLICATE_EDEFAULT);
                return;
            case FormPackage.TEXT_INFO__LIMIT_NUMBER_OF_DUPLICATION:
                setLimitNumberOfDuplication(LIMIT_NUMBER_OF_DUPLICATION_EDEFAULT);
                return;
            case FormPackage.TEXT_INFO__LIMIT_MIN_NUMBER_OF_DUPLICATION:
                setLimitMinNumberOfDuplication(LIMIT_MIN_NUMBER_OF_DUPLICATION_EDEFAULT);
                return;
            case FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION:
                setMaxNumberOfDuplication((Expression)null);
                return;
            case FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION:
                setMinNumberOfDuplication((Expression)null);
                return;
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD:
                setDisplayLabelForAdd((Expression)null);
                return;
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD:
                setTooltipForAdd((Expression)null);
                return;
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE:
                setDisplayLabelForRemove((Expression)null);
                return;
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE:
                setTooltipForRemove((Expression)null);
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
            case FormPackage.TEXT_INFO__DUPLICATE:
                return duplicate != DUPLICATE_EDEFAULT;
            case FormPackage.TEXT_INFO__LIMIT_NUMBER_OF_DUPLICATION:
                return limitNumberOfDuplication != LIMIT_NUMBER_OF_DUPLICATION_EDEFAULT;
            case FormPackage.TEXT_INFO__LIMIT_MIN_NUMBER_OF_DUPLICATION:
                return limitMinNumberOfDuplication != LIMIT_MIN_NUMBER_OF_DUPLICATION_EDEFAULT;
            case FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION:
                return maxNumberOfDuplication != null;
            case FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION:
                return minNumberOfDuplication != null;
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD:
                return displayLabelForAdd != null;
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD:
                return tooltipForAdd != null;
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE:
                return displayLabelForRemove != null;
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE:
                return tooltipForRemove != null;
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
        if (baseClass == Duplicable.class) {
            switch (derivedFeatureID) {
                case FormPackage.TEXT_INFO__DUPLICATE: return FormPackage.DUPLICABLE__DUPLICATE;
                case FormPackage.TEXT_INFO__LIMIT_NUMBER_OF_DUPLICATION: return FormPackage.DUPLICABLE__LIMIT_NUMBER_OF_DUPLICATION;
                case FormPackage.TEXT_INFO__LIMIT_MIN_NUMBER_OF_DUPLICATION: return FormPackage.DUPLICABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION;
                case FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION: return FormPackage.DUPLICABLE__MAX_NUMBER_OF_DUPLICATION;
                case FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION: return FormPackage.DUPLICABLE__MIN_NUMBER_OF_DUPLICATION;
                case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD: return FormPackage.DUPLICABLE__DISPLAY_LABEL_FOR_ADD;
                case FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD: return FormPackage.DUPLICABLE__TOOLTIP_FOR_ADD;
                case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE: return FormPackage.DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE;
                case FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE: return FormPackage.DUPLICABLE__TOOLTIP_FOR_REMOVE;
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
        if (baseClass == Duplicable.class) {
            switch (baseFeatureID) {
                case FormPackage.DUPLICABLE__DUPLICATE: return FormPackage.TEXT_INFO__DUPLICATE;
                case FormPackage.DUPLICABLE__LIMIT_NUMBER_OF_DUPLICATION: return FormPackage.TEXT_INFO__LIMIT_NUMBER_OF_DUPLICATION;
                case FormPackage.DUPLICABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION: return FormPackage.TEXT_INFO__LIMIT_MIN_NUMBER_OF_DUPLICATION;
                case FormPackage.DUPLICABLE__MAX_NUMBER_OF_DUPLICATION: return FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION;
                case FormPackage.DUPLICABLE__MIN_NUMBER_OF_DUPLICATION: return FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION;
                case FormPackage.DUPLICABLE__DISPLAY_LABEL_FOR_ADD: return FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD;
                case FormPackage.DUPLICABLE__TOOLTIP_FOR_ADD: return FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD;
                case FormPackage.DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE: return FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE;
                case FormPackage.DUPLICABLE__TOOLTIP_FOR_REMOVE: return FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE;
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
        result.append(" (duplicate: "); //$NON-NLS-1$
        result.append(duplicate);
        result.append(", limitNumberOfDuplication: "); //$NON-NLS-1$
        result.append(limitNumberOfDuplication);
        result.append(", limitMinNumberOfDuplication: "); //$NON-NLS-1$
        result.append(limitMinNumberOfDuplication);
        result.append(')');
        return result.toString();
    }

} //TextInfoImpl
