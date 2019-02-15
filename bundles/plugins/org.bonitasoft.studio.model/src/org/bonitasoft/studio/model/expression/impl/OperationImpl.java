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
package org.bonitasoft.studio.model.expression.impl;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.OperationImpl#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.OperationImpl#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.impl.OperationImpl#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationImpl extends EObjectImpl implements Operation {
	/**
     * The cached value of the '{@link #getLeftOperand() <em>Left Operand</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLeftOperand()
     * @generated
     * @ordered
     */
	protected Expression leftOperand;

	/**
     * The cached value of the '{@link #getRightOperand() <em>Right Operand</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRightOperand()
     * @generated
     * @ordered
     */
	protected Expression rightOperand;

	/**
     * The cached value of the '{@link #getOperator() <em>Operator</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOperator()
     * @generated
     * @ordered
     */
	protected Operator operator;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected OperationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ExpressionPackage.Literals.OPERATION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getLeftOperand() {
        return leftOperand;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetLeftOperand(Expression newLeftOperand, NotificationChain msgs) {
        Expression oldLeftOperand = leftOperand;
        leftOperand = newLeftOperand;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionPackage.OPERATION__LEFT_OPERAND, oldLeftOperand, newLeftOperand);
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
	public void setLeftOperand(Expression newLeftOperand) {
        if (newLeftOperand != leftOperand) {
            NotificationChain msgs = null;
            if (leftOperand != null)
                msgs = ((InternalEObject)leftOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.OPERATION__LEFT_OPERAND, null, msgs);
            if (newLeftOperand != null)
                msgs = ((InternalEObject)newLeftOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.OPERATION__LEFT_OPERAND, null, msgs);
            msgs = basicSetLeftOperand(newLeftOperand, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.OPERATION__LEFT_OPERAND, newLeftOperand, newLeftOperand));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getRightOperand() {
        return rightOperand;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetRightOperand(Expression newRightOperand, NotificationChain msgs) {
        Expression oldRightOperand = rightOperand;
        rightOperand = newRightOperand;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionPackage.OPERATION__RIGHT_OPERAND, oldRightOperand, newRightOperand);
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
	public void setRightOperand(Expression newRightOperand) {
        if (newRightOperand != rightOperand) {
            NotificationChain msgs = null;
            if (rightOperand != null)
                msgs = ((InternalEObject)rightOperand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.OPERATION__RIGHT_OPERAND, null, msgs);
            if (newRightOperand != null)
                msgs = ((InternalEObject)newRightOperand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.OPERATION__RIGHT_OPERAND, null, msgs);
            msgs = basicSetRightOperand(newRightOperand, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.OPERATION__RIGHT_OPERAND, newRightOperand, newRightOperand));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Operator getOperator() {
        return operator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetOperator(Operator newOperator, NotificationChain msgs) {
        Operator oldOperator = operator;
        operator = newOperator;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionPackage.OPERATION__OPERATOR, oldOperator, newOperator);
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
	public void setOperator(Operator newOperator) {
        if (newOperator != operator) {
            NotificationChain msgs = null;
            if (operator != null)
                msgs = ((InternalEObject)operator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.OPERATION__OPERATOR, null, msgs);
            if (newOperator != null)
                msgs = ((InternalEObject)newOperator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.OPERATION__OPERATOR, null, msgs);
            msgs = basicSetOperator(newOperator, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.OPERATION__OPERATOR, newOperator, newOperator));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ExpressionPackage.OPERATION__LEFT_OPERAND:
                return basicSetLeftOperand(null, msgs);
            case ExpressionPackage.OPERATION__RIGHT_OPERAND:
                return basicSetRightOperand(null, msgs);
            case ExpressionPackage.OPERATION__OPERATOR:
                return basicSetOperator(null, msgs);
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
            case ExpressionPackage.OPERATION__LEFT_OPERAND:
                return getLeftOperand();
            case ExpressionPackage.OPERATION__RIGHT_OPERAND:
                return getRightOperand();
            case ExpressionPackage.OPERATION__OPERATOR:
                return getOperator();
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
            case ExpressionPackage.OPERATION__LEFT_OPERAND:
                setLeftOperand((Expression)newValue);
                return;
            case ExpressionPackage.OPERATION__RIGHT_OPERAND:
                setRightOperand((Expression)newValue);
                return;
            case ExpressionPackage.OPERATION__OPERATOR:
                setOperator((Operator)newValue);
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
            case ExpressionPackage.OPERATION__LEFT_OPERAND:
                setLeftOperand((Expression)null);
                return;
            case ExpressionPackage.OPERATION__RIGHT_OPERAND:
                setRightOperand((Expression)null);
                return;
            case ExpressionPackage.OPERATION__OPERATOR:
                setOperator((Operator)null);
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
            case ExpressionPackage.OPERATION__LEFT_OPERAND:
                return leftOperand != null;
            case ExpressionPackage.OPERATION__RIGHT_OPERAND:
                return rightOperand != null;
            case ExpressionPackage.OPERATION__OPERATOR:
                return operator != null;
        }
        return super.eIsSet(featureID);
    }

} //OperationImpl
