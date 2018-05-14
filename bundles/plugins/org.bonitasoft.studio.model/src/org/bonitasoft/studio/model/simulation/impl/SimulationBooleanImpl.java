/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.simulation.impl;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.simulation.SimulationBoolean;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationBooleanImpl#isExpressionBased <em>Expression
 * Based</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationBooleanImpl#getExpression <em>Expression</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationBooleanImpl#getProbabilityOfTrue <em>Probability Of
 * True</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SimulationBooleanImpl extends SimulationElementImpl implements SimulationBoolean {

    /**
     * The default value of the '{@link #isExpressionBased() <em>Expression Based</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isExpressionBased()
     * @generated
     * @ordered
     */
    protected static final boolean EXPRESSION_BASED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isExpressionBased() <em>Expression Based</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isExpressionBased()
     * @generated
     * @ordered
     */
    protected boolean expressionBased = EXPRESSION_BASED_EDEFAULT;

    /**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected Expression expression;

    /**
     * The default value of the '{@link #getProbabilityOfTrue() <em>Probability Of True</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getProbabilityOfTrue()
     * @generated
     * @ordered
     */
    protected static final double PROBABILITY_OF_TRUE_EDEFAULT = 1.0;

    /**
     * The cached value of the '{@link #getProbabilityOfTrue() <em>Probability Of True</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getProbabilityOfTrue()
     * @generated
     * @ordered
     */
    protected double probabilityOfTrue = PROBABILITY_OF_TRUE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SimulationBooleanImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SimulationPackage.Literals.SIMULATION_BOOLEAN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isExpressionBased() {
        return expressionBased;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setExpressionBased(boolean newExpressionBased) {
        boolean oldExpressionBased = expressionBased;
        expressionBased = newExpressionBased;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION_BASED,
                    oldExpressionBased, expressionBased));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
        Expression oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION, oldExpression, newExpression);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setExpression(Expression newExpression) {
        if (newExpression != expression) {
            NotificationChain msgs = null;
            if (expression != null)
                msgs = ((InternalEObject) expression).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION, null, msgs);
            if (newExpression != null)
                msgs = ((InternalEObject) newExpression).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION, null, msgs);
            msgs = basicSetExpression(newExpression, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION,
                    newExpression, newExpression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public double getProbabilityOfTrue() {
        return probabilityOfTrue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setProbabilityOfTrue(double newProbabilityOfTrue) {
        double oldProbabilityOfTrue = probabilityOfTrue;
        probabilityOfTrue = newProbabilityOfTrue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_BOOLEAN__PROBABILITY_OF_TRUE,
                    oldProbabilityOfTrue, probabilityOfTrue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION:
                return basicSetExpression(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION_BASED:
                return isExpressionBased();
            case SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION:
                return getExpression();
            case SimulationPackage.SIMULATION_BOOLEAN__PROBABILITY_OF_TRUE:
                return getProbabilityOfTrue();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION_BASED:
                setExpressionBased((Boolean) newValue);
                return;
            case SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION:
                setExpression((Expression) newValue);
                return;
            case SimulationPackage.SIMULATION_BOOLEAN__PROBABILITY_OF_TRUE:
                setProbabilityOfTrue((Double) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION_BASED:
                setExpressionBased(EXPRESSION_BASED_EDEFAULT);
                return;
            case SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION:
                setExpression((Expression) null);
                return;
            case SimulationPackage.SIMULATION_BOOLEAN__PROBABILITY_OF_TRUE:
                setProbabilityOfTrue(PROBABILITY_OF_TRUE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION_BASED:
                return expressionBased != EXPRESSION_BASED_EDEFAULT;
            case SimulationPackage.SIMULATION_BOOLEAN__EXPRESSION:
                return expression != null;
            case SimulationPackage.SIMULATION_BOOLEAN__PROBABILITY_OF_TRUE:
                return probabilityOfTrue != PROBABILITY_OF_TRUE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (expressionBased: "); //$NON-NLS-1$
        result.append(expressionBased);
        result.append(", probabilityOfTrue: "); //$NON-NLS-1$
        result.append(probabilityOfTrue);
        result.append(')');
        return result.toString();
    }

} //SimulationBooleanImpl
