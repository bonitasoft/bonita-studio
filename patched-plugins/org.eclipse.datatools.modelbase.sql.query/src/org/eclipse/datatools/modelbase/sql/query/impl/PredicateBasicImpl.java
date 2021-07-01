/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateBasicImpl.java,v 1.5 2007/02/08 17:00:32 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
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
 * An implementation of the model object '<em><b>SQL Predicate Basic</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBasicImpl#getComparisonOperator <em>Comparison Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBasicImpl#getRightValueExpr <em>Right Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBasicImpl#getLeftValueExpr <em>Left Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateBasicImpl extends PredicateImpl implements PredicateBasic {
	/**
     * The default value of the '{@link #getComparisonOperator() <em>Comparison Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComparisonOperator()
     * @generated
     * @ordered
     */
    protected static final PredicateComparisonOperator COMPARISON_OPERATOR_EDEFAULT = PredicateComparisonOperator.EQUAL_LITERAL;

	/**
     * The cached value of the '{@link #getComparisonOperator() <em>Comparison Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComparisonOperator()
     * @generated
     * @ordered
     */
    protected PredicateComparisonOperator comparisonOperator = COMPARISON_OPERATOR_EDEFAULT;

	/**
     * The cached value of the '{@link #getRightValueExpr() <em>Right Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRightValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression rightValueExpr;

	/**
     * The cached value of the '{@link #getLeftValueExpr() <em>Left Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLeftValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression leftValueExpr;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PredicateBasicImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.PREDICATE_BASIC;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComparisonOperator(PredicateComparisonOperator newComparisonOperator) {
        PredicateComparisonOperator oldComparisonOperator = comparisonOperator;
        comparisonOperator = newComparisonOperator == null ? COMPARISON_OPERATOR_EDEFAULT : newComparisonOperator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BASIC__COMPARISON_OPERATOR, oldComparisonOperator, comparisonOperator));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getRightValueExpr() {
        return rightValueExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRightValueExpr(QueryValueExpression newRightValueExpr, NotificationChain msgs) {
        QueryValueExpression oldRightValueExpr = rightValueExpr;
        rightValueExpr = newRightValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR, oldRightValueExpr, newRightValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRightValueExpr(QueryValueExpression newRightValueExpr) {
        if (newRightValueExpr != rightValueExpr) {
            NotificationChain msgs = null;
            if (rightValueExpr != null)
                msgs = ((InternalEObject)rightValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT, QueryValueExpression.class, msgs);
            if (newRightValueExpr != null)
                msgs = ((InternalEObject)newRightValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT, QueryValueExpression.class, msgs);
            msgs = basicSetRightValueExpr(newRightValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR, newRightValueExpr, newRightValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getLeftValueExpr() {
        return leftValueExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLeftValueExpr(QueryValueExpression newLeftValueExpr, NotificationChain msgs) {
        QueryValueExpression oldLeftValueExpr = leftValueExpr;
        leftValueExpr = newLeftValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR, oldLeftValueExpr, newLeftValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLeftValueExpr(QueryValueExpression newLeftValueExpr) {
        if (newLeftValueExpr != leftValueExpr) {
            NotificationChain msgs = null;
            if (leftValueExpr != null)
                msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT, QueryValueExpression.class, msgs);
            if (newLeftValueExpr != null)
                msgs = ((InternalEObject)newLeftValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT, QueryValueExpression.class, msgs);
            msgs = basicSetLeftValueExpr(newLeftValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR, newLeftValueExpr, newLeftValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR:
                if (rightValueExpr != null)
                    msgs = ((InternalEObject)rightValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR, null, msgs);
                return basicSetRightValueExpr((QueryValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR:
                if (leftValueExpr != null)
                    msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR, null, msgs);
                return basicSetLeftValueExpr((QueryValueExpression)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR:
                return basicSetRightValueExpr(null, msgs);
            case SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR:
                return basicSetLeftValueExpr(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE_BASIC__COMPARISON_OPERATOR:
                return getComparisonOperator();
            case SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR:
                return getRightValueExpr();
            case SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR:
                return getLeftValueExpr();
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
            case SQLQueryModelPackage.PREDICATE_BASIC__COMPARISON_OPERATOR:
                setComparisonOperator((PredicateComparisonOperator)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR:
                setRightValueExpr((QueryValueExpression)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR:
                setLeftValueExpr((QueryValueExpression)newValue);
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
            case SQLQueryModelPackage.PREDICATE_BASIC__COMPARISON_OPERATOR:
                setComparisonOperator(COMPARISON_OPERATOR_EDEFAULT);
                return;
            case SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR:
                setRightValueExpr((QueryValueExpression)null);
                return;
            case SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR:
                setLeftValueExpr((QueryValueExpression)null);
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
            case SQLQueryModelPackage.PREDICATE_BASIC__COMPARISON_OPERATOR:
                return comparisonOperator != COMPARISON_OPERATOR_EDEFAULT;
            case SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR:
                return rightValueExpr != null;
            case SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR:
                return leftValueExpr != null;
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
        result.append(" (comparisonOperator: ");
        result.append(comparisonOperator);
        result.append(')');
        return result.toString();
    }

} //SQLPredicateBasicImpl
