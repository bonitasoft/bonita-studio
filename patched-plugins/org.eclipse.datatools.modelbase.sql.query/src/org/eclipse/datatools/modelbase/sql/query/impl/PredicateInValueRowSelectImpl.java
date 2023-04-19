/**
 * </copyright>
 *
 * $Id: PredicateInValueRowSelectImpl.java,v 1.5 2007/02/08 17:00:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Predicate In Value Row Select</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueRowSelectImpl#getValueExprList <em>Value Expr List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueRowSelectImpl#getQueryExpr <em>Query Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateInValueRowSelectImpl extends PredicateInImpl implements PredicateInValueRowSelect {
	/**
     * The cached value of the '{@link #getValueExprList() <em>Value Expr List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueExprList()
     * @generated
     * @ordered
     */
    protected EList valueExprList;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PredicateInValueRowSelectImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.PREDICATE_IN_VALUE_ROW_SELECT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getValueExprList() {
        if (valueExprList == null) {
            valueExprList = new EObjectContainmentWithInverseEList(QueryValueExpression.class, this, SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT);
        }
        return valueExprList;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR, oldQueryExpr, newQueryExpr);
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
                msgs = ((InternalEObject)queryExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT, QueryExpressionRoot.class, msgs);
            if (newQueryExpr != null)
                msgs = ((InternalEObject)newQueryExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT, QueryExpressionRoot.class, msgs);
            msgs = basicSetQueryExpr(newQueryExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR, newQueryExpr, newQueryExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST:
                return ((InternalEList)getValueExprList()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR:
                if (queryExpr != null)
                    msgs = ((InternalEObject)queryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR, null, msgs);
                return basicSetQueryExpr((QueryExpressionRoot)otherEnd, msgs);
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
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST:
                return ((InternalEList)getValueExprList()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR:
                return basicSetQueryExpr(null, msgs);
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
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST:
                return getValueExprList();
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR:
                return getQueryExpr();
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
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST:
                getValueExprList().clear();
                getValueExprList().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR:
                setQueryExpr((QueryExpressionRoot)newValue);
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
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST:
                getValueExprList().clear();
                return;
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR:
                setQueryExpr((QueryExpressionRoot)null);
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
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST:
                return valueExprList != null && !valueExprList.isEmpty();
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR:
                return queryExpr != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLPredicateInValueRowSelectImpl
