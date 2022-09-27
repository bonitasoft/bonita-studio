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
package org.bonitasoft.studio.model.process.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contract Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractConstraintImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractConstraintImpl#getErrorMessage <em>Error Message</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractConstraintImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractConstraintImpl#getInputNames <em>Input Names</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractConstraintImpl#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContractConstraintImpl extends EObjectImpl implements ContractConstraint {
	/**
     * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
	protected static final String EXPRESSION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
	protected String expression = EXPRESSION_EDEFAULT;

	/**
     * The default value of the '{@link #getErrorMessage() <em>Error Message</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getErrorMessage()
     * @generated
     * @ordered
     */
	protected static final String ERROR_MESSAGE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getErrorMessage() <em>Error Message</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getErrorMessage()
     * @generated
     * @ordered
     */
	protected String errorMessage = ERROR_MESSAGE_EDEFAULT;

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
     * The cached value of the '{@link #getInputNames() <em>Input Names</em>}' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInputNames()
     * @generated
     * @ordered
     */
	protected EList<String> inputNames;

	/**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ContractConstraintImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.CONTRACT_CONSTRAINT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getExpression() {
        return expression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setExpression(String newExpression) {
        String oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_CONSTRAINT__EXPRESSION, oldExpression, expression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getErrorMessage() {
        return errorMessage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setErrorMessage(String newErrorMessage) {
        String oldErrorMessage = errorMessage;
        errorMessage = newErrorMessage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_CONSTRAINT__ERROR_MESSAGE, oldErrorMessage, errorMessage));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_CONSTRAINT__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<String> getInputNames() {
        if (inputNames == null) {
            inputNames = new EDataTypeUniqueEList<String>(String.class, this, ProcessPackage.CONTRACT_CONSTRAINT__INPUT_NAMES);
        }
        return inputNames;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_CONSTRAINT__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProcessPackage.CONTRACT_CONSTRAINT__EXPRESSION:
                return getExpression();
            case ProcessPackage.CONTRACT_CONSTRAINT__ERROR_MESSAGE:
                return getErrorMessage();
            case ProcessPackage.CONTRACT_CONSTRAINT__NAME:
                return getName();
            case ProcessPackage.CONTRACT_CONSTRAINT__INPUT_NAMES:
                return getInputNames();
            case ProcessPackage.CONTRACT_CONSTRAINT__DESCRIPTION:
                return getDescription();
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
            case ProcessPackage.CONTRACT_CONSTRAINT__EXPRESSION:
                setExpression((String)newValue);
                return;
            case ProcessPackage.CONTRACT_CONSTRAINT__ERROR_MESSAGE:
                setErrorMessage((String)newValue);
                return;
            case ProcessPackage.CONTRACT_CONSTRAINT__NAME:
                setName((String)newValue);
                return;
            case ProcessPackage.CONTRACT_CONSTRAINT__INPUT_NAMES:
                getInputNames().clear();
                getInputNames().addAll((Collection<? extends String>)newValue);
                return;
            case ProcessPackage.CONTRACT_CONSTRAINT__DESCRIPTION:
                setDescription((String)newValue);
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
            case ProcessPackage.CONTRACT_CONSTRAINT__EXPRESSION:
                setExpression(EXPRESSION_EDEFAULT);
                return;
            case ProcessPackage.CONTRACT_CONSTRAINT__ERROR_MESSAGE:
                setErrorMessage(ERROR_MESSAGE_EDEFAULT);
                return;
            case ProcessPackage.CONTRACT_CONSTRAINT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ProcessPackage.CONTRACT_CONSTRAINT__INPUT_NAMES:
                getInputNames().clear();
                return;
            case ProcessPackage.CONTRACT_CONSTRAINT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
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
            case ProcessPackage.CONTRACT_CONSTRAINT__EXPRESSION:
                return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
            case ProcessPackage.CONTRACT_CONSTRAINT__ERROR_MESSAGE:
                return ERROR_MESSAGE_EDEFAULT == null ? errorMessage != null : !ERROR_MESSAGE_EDEFAULT.equals(errorMessage);
            case ProcessPackage.CONTRACT_CONSTRAINT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ProcessPackage.CONTRACT_CONSTRAINT__INPUT_NAMES:
                return inputNames != null && !inputNames.isEmpty();
            case ProcessPackage.CONTRACT_CONSTRAINT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
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
        result.append(" (expression: "); //$NON-NLS-1$
        result.append(expression);
        result.append(", errorMessage: "); //$NON-NLS-1$
        result.append(errorMessage);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", inputNames: "); //$NON-NLS-1$
        result.append(inputNames);
        result.append(", description: "); //$NON-NLS-1$
        result.append(description);
        result.append(')');
        return result.toString();
    }

} //ContractConstraintImpl
