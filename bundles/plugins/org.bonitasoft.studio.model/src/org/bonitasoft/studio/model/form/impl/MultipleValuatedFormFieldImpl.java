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
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Multiple Valuated Form Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.MultipleValuatedFormFieldImpl#getDefaultExpression <em>Default Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.MultipleValuatedFormFieldImpl#getDefaultExpressionAfterEvent <em>Default Expression After Event</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class MultipleValuatedFormFieldImpl extends FormFieldImpl implements MultipleValuatedFormField {
	/**
     * The cached value of the '{@link #getDefaultExpression() <em>Default Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultExpression()
     * @generated
     * @ordered
     */
	protected Expression defaultExpression;

	/**
     * The cached value of the '{@link #getDefaultExpressionAfterEvent() <em>Default Expression After Event</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultExpressionAfterEvent()
     * @generated
     * @ordered
     */
	protected Expression defaultExpressionAfterEvent;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MultipleValuatedFormFieldImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.MULTIPLE_VALUATED_FORM_FIELD;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDefaultExpression() {
        return defaultExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDefaultExpression(Expression newDefaultExpression, NotificationChain msgs) {
        Expression oldDefaultExpression = defaultExpression;
        defaultExpression = newDefaultExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION, oldDefaultExpression, newDefaultExpression);
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
	public void setDefaultExpression(Expression newDefaultExpression) {
        if (newDefaultExpression != defaultExpression) {
            NotificationChain msgs = null;
            if (defaultExpression != null)
                msgs = ((InternalEObject)defaultExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION, null, msgs);
            if (newDefaultExpression != null)
                msgs = ((InternalEObject)newDefaultExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION, null, msgs);
            msgs = basicSetDefaultExpression(newDefaultExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION, newDefaultExpression, newDefaultExpression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDefaultExpressionAfterEvent() {
        return defaultExpressionAfterEvent;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDefaultExpressionAfterEvent(Expression newDefaultExpressionAfterEvent, NotificationChain msgs) {
        Expression oldDefaultExpressionAfterEvent = defaultExpressionAfterEvent;
        defaultExpressionAfterEvent = newDefaultExpressionAfterEvent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT, oldDefaultExpressionAfterEvent, newDefaultExpressionAfterEvent);
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
	public void setDefaultExpressionAfterEvent(Expression newDefaultExpressionAfterEvent) {
        if (newDefaultExpressionAfterEvent != defaultExpressionAfterEvent) {
            NotificationChain msgs = null;
            if (defaultExpressionAfterEvent != null)
                msgs = ((InternalEObject)defaultExpressionAfterEvent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT, null, msgs);
            if (newDefaultExpressionAfterEvent != null)
                msgs = ((InternalEObject)newDefaultExpressionAfterEvent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT, null, msgs);
            msgs = basicSetDefaultExpressionAfterEvent(newDefaultExpressionAfterEvent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT, newDefaultExpressionAfterEvent, newDefaultExpressionAfterEvent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION:
                return basicSetDefaultExpression(null, msgs);
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT:
                return basicSetDefaultExpressionAfterEvent(null, msgs);
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
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION:
                return getDefaultExpression();
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT:
                return getDefaultExpressionAfterEvent();
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
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION:
                setDefaultExpression((Expression)newValue);
                return;
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT:
                setDefaultExpressionAfterEvent((Expression)newValue);
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
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION:
                setDefaultExpression((Expression)null);
                return;
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT:
                setDefaultExpressionAfterEvent((Expression)null);
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
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION:
                return defaultExpression != null;
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT:
                return defaultExpressionAfterEvent != null;
        }
        return super.eIsSet(featureID);
    }

} //MultipleValuatedFormFieldImpl
