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

import org.bonitasoft.studio.model.process.decision.DecisionPackage;
import org.bonitasoft.studio.model.process.decision.DecisionTable;
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
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableImpl#getLines <em>Lines</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableImpl#getDefaultAction <em>Default Action</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DecisionTableImpl extends EObjectImpl implements DecisionTable {
	/**
     * The cached value of the '{@link #getLines() <em>Lines</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLines()
     * @generated
     * @ordered
     */
	protected EList<DecisionTableLine> lines;

	/**
     * The cached value of the '{@link #getDefaultAction() <em>Default Action</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultAction()
     * @generated
     * @ordered
     */
	protected DecisionTableAction defaultAction;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected DecisionTableImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return DecisionPackage.Literals.DECISION_TABLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<DecisionTableLine> getLines() {
        if (lines == null) {
            lines = new EObjectContainmentEList<DecisionTableLine>(DecisionTableLine.class, this, DecisionPackage.DECISION_TABLE__LINES);
        }
        return lines;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public DecisionTableAction getDefaultAction() {
        return defaultAction;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDefaultAction(DecisionTableAction newDefaultAction, NotificationChain msgs) {
        DecisionTableAction oldDefaultAction = defaultAction;
        defaultAction = newDefaultAction;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DecisionPackage.DECISION_TABLE__DEFAULT_ACTION, oldDefaultAction, newDefaultAction);
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
	public void setDefaultAction(DecisionTableAction newDefaultAction) {
        if (newDefaultAction != defaultAction) {
            NotificationChain msgs = null;
            if (defaultAction != null)
                msgs = ((InternalEObject)defaultAction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DecisionPackage.DECISION_TABLE__DEFAULT_ACTION, null, msgs);
            if (newDefaultAction != null)
                msgs = ((InternalEObject)newDefaultAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DecisionPackage.DECISION_TABLE__DEFAULT_ACTION, null, msgs);
            msgs = basicSetDefaultAction(newDefaultAction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DecisionPackage.DECISION_TABLE__DEFAULT_ACTION, newDefaultAction, newDefaultAction));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DecisionPackage.DECISION_TABLE__LINES:
                return ((InternalEList<?>)getLines()).basicRemove(otherEnd, msgs);
            case DecisionPackage.DECISION_TABLE__DEFAULT_ACTION:
                return basicSetDefaultAction(null, msgs);
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
            case DecisionPackage.DECISION_TABLE__LINES:
                return getLines();
            case DecisionPackage.DECISION_TABLE__DEFAULT_ACTION:
                return getDefaultAction();
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
            case DecisionPackage.DECISION_TABLE__LINES:
                getLines().clear();
                getLines().addAll((Collection<? extends DecisionTableLine>)newValue);
                return;
            case DecisionPackage.DECISION_TABLE__DEFAULT_ACTION:
                setDefaultAction((DecisionTableAction)newValue);
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
            case DecisionPackage.DECISION_TABLE__LINES:
                getLines().clear();
                return;
            case DecisionPackage.DECISION_TABLE__DEFAULT_ACTION:
                setDefaultAction((DecisionTableAction)null);
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
            case DecisionPackage.DECISION_TABLE__LINES:
                return lines != null && !lines.isEmpty();
            case DecisionPackage.DECISION_TABLE__DEFAULT_ACTION:
                return defaultAction != null;
        }
        return super.eIsSet(featureID);
    }

} //DecisionTableImpl
