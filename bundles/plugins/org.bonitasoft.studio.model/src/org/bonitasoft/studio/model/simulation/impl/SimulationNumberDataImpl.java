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
package org.bonitasoft.studio.model.simulation.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.simulation.SimulationNumberData;
import org.bonitasoft.studio.model.simulation.SimulationNumberRange;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Number Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberDataImpl#isExpressionBased <em>Expression Based</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberDataImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberDataImpl#getRanges <em>Ranges</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SimulationNumberDataImpl extends SimulationElementImpl implements SimulationNumberData {
	/**
     * The default value of the '{@link #isExpressionBased() <em>Expression Based</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isExpressionBased()
     * @generated
     * @ordered
     */
	protected static final boolean EXPRESSION_BASED_EDEFAULT = true;

	/**
     * The cached value of the '{@link #isExpressionBased() <em>Expression Based</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isExpressionBased()
     * @generated
     * @ordered
     */
	protected boolean expressionBased = EXPRESSION_BASED_EDEFAULT;

	/**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
	protected Expression expression;

	/**
     * The cached value of the '{@link #getRanges() <em>Ranges</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRanges()
     * @generated
     * @ordered
     */
	protected EList<SimulationNumberRange> ranges;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SimulationNumberDataImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SimulationPackage.Literals.SIMULATION_NUMBER_DATA;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isExpressionBased() {
        return expressionBased;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setExpressionBased(boolean newExpressionBased) {
        boolean oldExpressionBased = expressionBased;
        expressionBased = newExpressionBased;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION_BASED, oldExpressionBased, expressionBased));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getExpression() {
        return expression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
        Expression oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION, oldExpression, newExpression);
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
	public void setExpression(Expression newExpression) {
        if (newExpression != expression) {
            NotificationChain msgs = null;
            if (expression != null)
                msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION, null, msgs);
            if (newExpression != null)
                msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION, null, msgs);
            msgs = basicSetExpression(newExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION, newExpression, newExpression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<SimulationNumberRange> getRanges() {
        if (ranges == null) {
            ranges = new EObjectContainmentEList<SimulationNumberRange>(SimulationNumberRange.class, this, SimulationPackage.SIMULATION_NUMBER_DATA__RANGES);
        }
        return ranges;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION:
                return basicSetExpression(null, msgs);
            case SimulationPackage.SIMULATION_NUMBER_DATA__RANGES:
                return ((InternalEList<?>)getRanges()).basicRemove(otherEnd, msgs);
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
            case SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION_BASED:
                return isExpressionBased();
            case SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION:
                return getExpression();
            case SimulationPackage.SIMULATION_NUMBER_DATA__RANGES:
                return getRanges();
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
            case SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION_BASED:
                setExpressionBased((Boolean)newValue);
                return;
            case SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION:
                setExpression((Expression)newValue);
                return;
            case SimulationPackage.SIMULATION_NUMBER_DATA__RANGES:
                getRanges().clear();
                getRanges().addAll((Collection<? extends SimulationNumberRange>)newValue);
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
            case SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION_BASED:
                setExpressionBased(EXPRESSION_BASED_EDEFAULT);
                return;
            case SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION:
                setExpression((Expression)null);
                return;
            case SimulationPackage.SIMULATION_NUMBER_DATA__RANGES:
                getRanges().clear();
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
            case SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION_BASED:
                return expressionBased != EXPRESSION_BASED_EDEFAULT;
            case SimulationPackage.SIMULATION_NUMBER_DATA__EXPRESSION:
                return expression != null;
            case SimulationPackage.SIMULATION_NUMBER_DATA__RANGES:
                return ranges != null && !ranges.isEmpty();
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
        result.append(" (expressionBased: "); //$NON-NLS-1$
        result.append(expressionBased);
        result.append(')');
        return result.toString();
    }

} //SimulationNumberDataImpl
