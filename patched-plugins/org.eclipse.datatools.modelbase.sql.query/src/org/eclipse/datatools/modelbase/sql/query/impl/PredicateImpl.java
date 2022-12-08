/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateImpl.java,v 1.5 2007/02/08 17:00:30 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Predicate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl#isNegatedPredicate <em>Negated Predicate</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl#isHasSelectivity <em>Has Selectivity</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl#getSelectivityValue <em>Selectivity Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PredicateImpl extends QuerySearchConditionImpl implements Predicate {
	/**
     * The default value of the '{@link #isNegatedPredicate() <em>Negated Predicate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNegatedPredicate()
     * @generated
     * @ordered
     */
    protected static final boolean NEGATED_PREDICATE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isNegatedPredicate() <em>Negated Predicate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNegatedPredicate()
     * @generated
     * @ordered
     */
    protected boolean negatedPredicate = NEGATED_PREDICATE_EDEFAULT;

	/**
     * The default value of the '{@link #isHasSelectivity() <em>Has Selectivity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isHasSelectivity()
     * @generated
     * @ordered
     */
    protected static final boolean HAS_SELECTIVITY_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isHasSelectivity() <em>Has Selectivity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isHasSelectivity()
     * @generated
     * @ordered
     */
    protected boolean hasSelectivity = HAS_SELECTIVITY_EDEFAULT;

	/**
     * The default value of the '{@link #getSelectivityValue() <em>Selectivity Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSelectivityValue()
     * @generated
     * @ordered
     */
    protected static final Integer SELECTIVITY_VALUE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getSelectivityValue() <em>Selectivity Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSelectivityValue()
     * @generated
     * @ordered
     */
    protected Integer selectivityValue = SELECTIVITY_VALUE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PredicateImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.PREDICATE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isNegatedPredicate() {
        return negatedPredicate;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNegatedPredicate(boolean newNegatedPredicate) {
        boolean oldNegatedPredicate = negatedPredicate;
        negatedPredicate = newNegatedPredicate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE__NEGATED_PREDICATE, oldNegatedPredicate, negatedPredicate));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isHasSelectivity() {
        return hasSelectivity;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHasSelectivity(boolean newHasSelectivity) {
        boolean oldHasSelectivity = hasSelectivity;
        hasSelectivity = newHasSelectivity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE__HAS_SELECTIVITY, oldHasSelectivity, hasSelectivity));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Integer getSelectivityValue() {
        return selectivityValue;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSelectivityValue(Integer newSelectivityValue) {
        Integer oldSelectivityValue = selectivityValue;
        selectivityValue = newSelectivityValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE__SELECTIVITY_VALUE, oldSelectivityValue, selectivityValue));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE__NEGATED_PREDICATE:
                return isNegatedPredicate() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.PREDICATE__HAS_SELECTIVITY:
                return isHasSelectivity() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.PREDICATE__SELECTIVITY_VALUE:
                return getSelectivityValue();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE__NEGATED_PREDICATE:
                setNegatedPredicate(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.PREDICATE__HAS_SELECTIVITY:
                setHasSelectivity(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.PREDICATE__SELECTIVITY_VALUE:
                setSelectivityValue((Integer)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE__NEGATED_PREDICATE:
                setNegatedPredicate(NEGATED_PREDICATE_EDEFAULT);
                return;
            case SQLQueryModelPackage.PREDICATE__HAS_SELECTIVITY:
                setHasSelectivity(HAS_SELECTIVITY_EDEFAULT);
                return;
            case SQLQueryModelPackage.PREDICATE__SELECTIVITY_VALUE:
                setSelectivityValue(SELECTIVITY_VALUE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE__NEGATED_PREDICATE:
                return negatedPredicate != NEGATED_PREDICATE_EDEFAULT;
            case SQLQueryModelPackage.PREDICATE__HAS_SELECTIVITY:
                return hasSelectivity != HAS_SELECTIVITY_EDEFAULT;
            case SQLQueryModelPackage.PREDICATE__SELECTIVITY_VALUE:
                return SELECTIVITY_VALUE_EDEFAULT == null ? selectivityValue != null : !SELECTIVITY_VALUE_EDEFAULT.equals(selectivityValue);
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (negatedPredicate: ");
        result.append(negatedPredicate);
        result.append(", hasSelectivity: ");
        result.append(hasSelectivity);
        result.append(", selectivityValue: ");
        result.append(selectivityValue);
        result.append(')');
        return result.toString();
    }

} //SQLPredicateImpl
