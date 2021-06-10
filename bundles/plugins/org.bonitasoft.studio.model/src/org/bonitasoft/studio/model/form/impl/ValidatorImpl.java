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
import org.bonitasoft.studio.model.form.Validator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Validator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.ValidatorImpl#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.ValidatorImpl#getHtmlClass <em>Html Class</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.ValidatorImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.ValidatorImpl#isBelowField <em>Below Field</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.ValidatorImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.ValidatorImpl#getDisplayName <em>Display Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValidatorImpl extends EObjectImpl implements Validator {
	/**
     * The default value of the '{@link #getValidatorClass() <em>Validator Class</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValidatorClass()
     * @generated
     * @ordered
     */
	protected static final String VALIDATOR_CLASS_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getValidatorClass() <em>Validator Class</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValidatorClass()
     * @generated
     * @ordered
     */
	protected String validatorClass = VALIDATOR_CLASS_EDEFAULT;

	/**
     * The default value of the '{@link #getHtmlClass() <em>Html Class</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHtmlClass()
     * @generated
     * @ordered
     */
	protected static final String HTML_CLASS_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getHtmlClass() <em>Html Class</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHtmlClass()
     * @generated
     * @ordered
     */
	protected String htmlClass = HTML_CLASS_EDEFAULT;

	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The default value of the '{@link #isBelowField() <em>Below Field</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isBelowField()
     * @generated
     * @ordered
     */
	protected static final boolean BELOW_FIELD_EDEFAULT = true;

	/**
     * The cached value of the '{@link #isBelowField() <em>Below Field</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isBelowField()
     * @generated
     * @ordered
     */
	protected boolean belowField = BELOW_FIELD_EDEFAULT;

	/**
     * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParameter()
     * @generated
     * @ordered
     */
	protected Expression parameter;

	/**
     * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
	protected Expression displayName;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ValidatorImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.VALIDATOR;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getValidatorClass() {
        return validatorClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setValidatorClass(String newValidatorClass) {
        String oldValidatorClass = validatorClass;
        validatorClass = newValidatorClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.VALIDATOR__VALIDATOR_CLASS, oldValidatorClass, validatorClass));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getHtmlClass() {
        return htmlClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setHtmlClass(String newHtmlClass) {
        String oldHtmlClass = htmlClass;
        htmlClass = newHtmlClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.VALIDATOR__HTML_CLASS, oldHtmlClass, htmlClass));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.VALIDATOR__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isBelowField() {
        return belowField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setBelowField(boolean newBelowField) {
        boolean oldBelowField = belowField;
        belowField = newBelowField;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.VALIDATOR__BELOW_FIELD, oldBelowField, belowField));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getParameter() {
        return parameter;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetParameter(Expression newParameter, NotificationChain msgs) {
        Expression oldParameter = parameter;
        parameter = newParameter;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.VALIDATOR__PARAMETER, oldParameter, newParameter);
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
	public void setParameter(Expression newParameter) {
        if (newParameter != parameter) {
            NotificationChain msgs = null;
            if (parameter != null)
                msgs = ((InternalEObject)parameter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.VALIDATOR__PARAMETER, null, msgs);
            if (newParameter != null)
                msgs = ((InternalEObject)newParameter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.VALIDATOR__PARAMETER, null, msgs);
            msgs = basicSetParameter(newParameter, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.VALIDATOR__PARAMETER, newParameter, newParameter));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDisplayName() {
        return displayName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDisplayName(Expression newDisplayName, NotificationChain msgs) {
        Expression oldDisplayName = displayName;
        displayName = newDisplayName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.VALIDATOR__DISPLAY_NAME, oldDisplayName, newDisplayName);
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
	public void setDisplayName(Expression newDisplayName) {
        if (newDisplayName != displayName) {
            NotificationChain msgs = null;
            if (displayName != null)
                msgs = ((InternalEObject)displayName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.VALIDATOR__DISPLAY_NAME, null, msgs);
            if (newDisplayName != null)
                msgs = ((InternalEObject)newDisplayName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.VALIDATOR__DISPLAY_NAME, null, msgs);
            msgs = basicSetDisplayName(newDisplayName, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.VALIDATOR__DISPLAY_NAME, newDisplayName, newDisplayName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FormPackage.VALIDATOR__PARAMETER:
                return basicSetParameter(null, msgs);
            case FormPackage.VALIDATOR__DISPLAY_NAME:
                return basicSetDisplayName(null, msgs);
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
            case FormPackage.VALIDATOR__VALIDATOR_CLASS:
                return getValidatorClass();
            case FormPackage.VALIDATOR__HTML_CLASS:
                return getHtmlClass();
            case FormPackage.VALIDATOR__NAME:
                return getName();
            case FormPackage.VALIDATOR__BELOW_FIELD:
                return isBelowField();
            case FormPackage.VALIDATOR__PARAMETER:
                return getParameter();
            case FormPackage.VALIDATOR__DISPLAY_NAME:
                return getDisplayName();
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
            case FormPackage.VALIDATOR__VALIDATOR_CLASS:
                setValidatorClass((String)newValue);
                return;
            case FormPackage.VALIDATOR__HTML_CLASS:
                setHtmlClass((String)newValue);
                return;
            case FormPackage.VALIDATOR__NAME:
                setName((String)newValue);
                return;
            case FormPackage.VALIDATOR__BELOW_FIELD:
                setBelowField((Boolean)newValue);
                return;
            case FormPackage.VALIDATOR__PARAMETER:
                setParameter((Expression)newValue);
                return;
            case FormPackage.VALIDATOR__DISPLAY_NAME:
                setDisplayName((Expression)newValue);
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
            case FormPackage.VALIDATOR__VALIDATOR_CLASS:
                setValidatorClass(VALIDATOR_CLASS_EDEFAULT);
                return;
            case FormPackage.VALIDATOR__HTML_CLASS:
                setHtmlClass(HTML_CLASS_EDEFAULT);
                return;
            case FormPackage.VALIDATOR__NAME:
                setName(NAME_EDEFAULT);
                return;
            case FormPackage.VALIDATOR__BELOW_FIELD:
                setBelowField(BELOW_FIELD_EDEFAULT);
                return;
            case FormPackage.VALIDATOR__PARAMETER:
                setParameter((Expression)null);
                return;
            case FormPackage.VALIDATOR__DISPLAY_NAME:
                setDisplayName((Expression)null);
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
            case FormPackage.VALIDATOR__VALIDATOR_CLASS:
                return VALIDATOR_CLASS_EDEFAULT == null ? validatorClass != null : !VALIDATOR_CLASS_EDEFAULT.equals(validatorClass);
            case FormPackage.VALIDATOR__HTML_CLASS:
                return HTML_CLASS_EDEFAULT == null ? htmlClass != null : !HTML_CLASS_EDEFAULT.equals(htmlClass);
            case FormPackage.VALIDATOR__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case FormPackage.VALIDATOR__BELOW_FIELD:
                return belowField != BELOW_FIELD_EDEFAULT;
            case FormPackage.VALIDATOR__PARAMETER:
                return parameter != null;
            case FormPackage.VALIDATOR__DISPLAY_NAME:
                return displayName != null;
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
        result.append(" (validatorClass: "); //$NON-NLS-1$
        result.append(validatorClass);
        result.append(", htmlClass: "); //$NON-NLS-1$
        result.append(htmlClass);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", belowField: "); //$NON-NLS-1$
        result.append(belowField);
        result.append(')');
        return result.toString();
    }

} //ValidatorImpl
