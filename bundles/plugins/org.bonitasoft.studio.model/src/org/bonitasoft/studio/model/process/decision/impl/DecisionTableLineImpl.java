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
package org.bonitasoft.studio.model.process.decision.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.process.decision.DecisionPackage;
import org.bonitasoft.studio.model.process.decision.DecisionTableAction;
import org.bonitasoft.studio.model.process.decision.DecisionTableLine;

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
 * An implementation of the model object '<em><b>Table Line</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableLineImpl#getConditions <em>Conditions</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableLineImpl#getAction <em>Action</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DecisionTableLineImpl extends EObjectImpl implements DecisionTableLine {
	/**
     * The cached value of the '{@link #getConditions() <em>Conditions</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConditions()
     * @generated
     * @ordered
     */
	protected EList<Expression> conditions;

	/**
     * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAction()
     * @generated
     * @ordered
     */
	protected DecisionTableAction action;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected DecisionTableLineImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return DecisionPackage.Literals.DECISION_TABLE_LINE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Expression> getConditions() {
        if (conditions == null) {
            conditions = new EObjectContainmentEList<Expression>(Expression.class, this, DecisionPackage.DECISION_TABLE_LINE__CONDITIONS);
        }
        return conditions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public DecisionTableAction getAction() {
        return action;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetAction(DecisionTableAction newAction, NotificationChain msgs) {
        DecisionTableAction oldAction = action;
        action = newAction;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DecisionPackage.DECISION_TABLE_LINE__ACTION, oldAction, newAction);
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
	public void setAction(DecisionTableAction newAction) {
        if (newAction != action) {
            NotificationChain msgs = null;
            if (action != null)
                msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DecisionPackage.DECISION_TABLE_LINE__ACTION, null, msgs);
            if (newAction != null)
                msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DecisionPackage.DECISION_TABLE_LINE__ACTION, null, msgs);
            msgs = basicSetAction(newAction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DecisionPackage.DECISION_TABLE_LINE__ACTION, newAction, newAction));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DecisionPackage.DECISION_TABLE_LINE__CONDITIONS:
                return ((InternalEList<?>)getConditions()).basicRemove(otherEnd, msgs);
            case DecisionPackage.DECISION_TABLE_LINE__ACTION:
                return basicSetAction(null, msgs);
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
            case DecisionPackage.DECISION_TABLE_LINE__CONDITIONS:
                return getConditions();
            case DecisionPackage.DECISION_TABLE_LINE__ACTION:
                return getAction();
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
            case DecisionPackage.DECISION_TABLE_LINE__CONDITIONS:
                getConditions().clear();
                getConditions().addAll((Collection<? extends Expression>)newValue);
                return;
            case DecisionPackage.DECISION_TABLE_LINE__ACTION:
                setAction((DecisionTableAction)newValue);
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
            case DecisionPackage.DECISION_TABLE_LINE__CONDITIONS:
                getConditions().clear();
                return;
            case DecisionPackage.DECISION_TABLE_LINE__ACTION:
                setAction((DecisionTableAction)null);
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
            case DecisionPackage.DECISION_TABLE_LINE__CONDITIONS:
                return conditions != null && !conditions.isEmpty();
            case DecisionPackage.DECISION_TABLE_LINE__ACTION:
                return action != null;
        }
        return super.eIsSet(featureID);
    }

} //DecisionTableLineImpl
