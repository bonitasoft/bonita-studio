/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateQuantifiedValueSelectImpl.java,v 1.5 2007/02/08 17:00:32 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
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
 * An implementation of the model object '<em><b>SQL Predicate Quantified Value Select</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl#getQuantifiedType <em>Quantified Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl#getComparisonOperator <em>Comparison Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl#getQueryExpr <em>Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateQuantifiedValueSelectImpl extends PredicateQuantifiedImpl implements PredicateQuantifiedValueSelect {
	/**
     * The default value of the '{@link #getQuantifiedType() <em>Quantified Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQuantifiedType()
     * @generated
     * @ordered
     */
    protected static final PredicateQuantifiedType QUANTIFIED_TYPE_EDEFAULT = PredicateQuantifiedType.SOME_LITERAL;

	/**
     * The cached value of the '{@link #getQuantifiedType() <em>Quantified Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQuantifiedType()
     * @generated
     * @ordered
     */
    protected PredicateQuantifiedType quantifiedType = QUANTIFIED_TYPE_EDEFAULT;

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
     * The cached value of the '{@link #getQueryExpr() <em>Query Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueryExpr()
     * @generated
     * @ordered
     */
    protected QueryExpressionRoot queryExpr;

	/**
     * The cached value of the '{@link #getValueExpr() <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression valueExpr;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PredicateQuantifiedValueSelectImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.PREDICATE_QUANTIFIED_VALUE_SELECT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateQuantifiedType getQuantifiedType() {
        return quantifiedType;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuantifiedType(PredicateQuantifiedType newQuantifiedType) {
        PredicateQuantifiedType oldQuantifiedType = quantifiedType;
        quantifiedType = newQuantifiedType == null ? QUANTIFIED_TYPE_EDEFAULT : newQuantifiedType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE, oldQuantifiedType, quantifiedType));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR, oldComparisonOperator, comparisonOperator));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionRoot getQueryExpr() {
        return queryExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetQueryExpr(QueryExpressionRoot newQueryExpr, NotificationChain msgs) {
        QueryExpressionRoot oldQueryExpr = queryExpr;
        queryExpr = newQueryExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, oldQueryExpr, newQueryExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueryExpr(QueryExpressionRoot newQueryExpr) {
        if (newQueryExpr != queryExpr) {
            NotificationChain msgs = null;
            if (queryExpr != null)
                msgs = ((InternalEObject)queryExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, QueryExpressionRoot.class, msgs);
            if (newQueryExpr != null)
                msgs = ((InternalEObject)newQueryExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, QueryExpressionRoot.class, msgs);
            msgs = basicSetQueryExpr(newQueryExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, newQueryExpr, newQueryExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getValueExpr() {
        return valueExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValueExpr(QueryValueExpression newValueExpr, NotificationChain msgs) {
        QueryValueExpression oldValueExpr = valueExpr;
        valueExpr = newValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, oldValueExpr, newValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExpr(QueryValueExpression newValueExpr) {
        if (newValueExpr != valueExpr) {
            NotificationChain msgs = null;
            if (valueExpr != null)
                msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, QueryValueExpression.class, msgs);
            if (newValueExpr != null)
                msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, QueryValueExpression.class, msgs);
            msgs = basicSetValueExpr(newValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, newValueExpr, newValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
                if (queryExpr != null)
                    msgs = ((InternalEObject)queryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, null, msgs);
                return basicSetQueryExpr((QueryExpressionRoot)otherEnd, msgs);
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
                if (valueExpr != null)
                    msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, null, msgs);
                return basicSetValueExpr((QueryValueExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
                return basicSetQueryExpr(null, msgs);
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
                return basicSetValueExpr(null, msgs);
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
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE:
                return getQuantifiedType();
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR:
                return getComparisonOperator();
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
                return getQueryExpr();
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
                return getValueExpr();
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
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE:
                setQuantifiedType((PredicateQuantifiedType)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR:
                setComparisonOperator((PredicateComparisonOperator)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
                setQueryExpr((QueryExpressionRoot)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
                setValueExpr((QueryValueExpression)newValue);
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
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE:
                setQuantifiedType(QUANTIFIED_TYPE_EDEFAULT);
                return;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR:
                setComparisonOperator(COMPARISON_OPERATOR_EDEFAULT);
                return;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
                setQueryExpr((QueryExpressionRoot)null);
                return;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
                setValueExpr((QueryValueExpression)null);
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
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE:
                return quantifiedType != QUANTIFIED_TYPE_EDEFAULT;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR:
                return comparisonOperator != COMPARISON_OPERATOR_EDEFAULT;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
                return queryExpr != null;
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
                return valueExpr != null;
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
        result.append(" (quantifiedType: ");
        result.append(quantifiedType);
        result.append(", comparisonOperator: ");
        result.append(comparisonOperator);
        result.append(')');
        return result.toString();
    }

} //SQLPredicateQuantifiedValueSelectImpl
