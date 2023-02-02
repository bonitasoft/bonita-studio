/**
 * <copyright>
 * </copyright>
 *
 * $Id: QuerySelectStatementImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression;
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
 * An implementation of the model object '<em><b>SQL Select Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectStatementImpl#getQueryExpr <em>Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectStatementImpl#getOrderByClause <em>Order By Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectStatementImpl#getUpdatabilityExpr <em>Updatability Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QuerySelectStatementImpl extends QueryStatementImpl implements QuerySelectStatement {
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
     * The cached value of the '{@link #getOrderByClause() <em>Order By Clause</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrderByClause()
     * @generated
     * @ordered
     */
    protected EList orderByClause;

	/**
     * The cached value of the '{@link #getUpdatabilityExpr() <em>Updatability Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpdatabilityExpr()
     * @generated
     * @ordered
     */
    protected UpdatabilityExpression updatabilityExpr;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QuerySelectStatementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_SELECT_STATEMENT;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, oldQueryExpr, newQueryExpr);
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
                msgs = ((InternalEObject)queryExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, QueryExpressionRoot.class, msgs);
            if (newQueryExpr != null)
                msgs = ((InternalEObject)newQueryExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, QueryExpressionRoot.class, msgs);
            msgs = basicSetQueryExpr(newQueryExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, newQueryExpr, newQueryExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getOrderByClause() {
        if (orderByClause == null) {
            orderByClause = new EObjectContainmentWithInverseEList(OrderBySpecification.class, this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT);
        }
        return orderByClause;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UpdatabilityExpression getUpdatabilityExpr() {
        return updatabilityExpr;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUpdatabilityExpr(UpdatabilityExpression newUpdatabilityExpr, NotificationChain msgs) {
        UpdatabilityExpression oldUpdatabilityExpr = updatabilityExpr;
        updatabilityExpr = newUpdatabilityExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR, oldUpdatabilityExpr, newUpdatabilityExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpdatabilityExpr(UpdatabilityExpression newUpdatabilityExpr) {
        if (newUpdatabilityExpr != updatabilityExpr) {
            NotificationChain msgs = null;
            if (updatabilityExpr != null)
                msgs = ((InternalEObject)updatabilityExpr).eInverseRemove(this, SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT, UpdatabilityExpression.class, msgs);
            if (newUpdatabilityExpr != null)
                msgs = ((InternalEObject)newUpdatabilityExpr).eInverseAdd(this, SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT, UpdatabilityExpression.class, msgs);
            msgs = basicSetUpdatabilityExpr(newUpdatabilityExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR, newUpdatabilityExpr, newUpdatabilityExpr));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
                if (queryExpr != null)
                    msgs = ((InternalEObject)queryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, null, msgs);
                return basicSetQueryExpr((QueryExpressionRoot)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
                return ((InternalEList)getOrderByClause()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR:
                if (updatabilityExpr != null)
                    msgs = ((InternalEObject)updatabilityExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR, null, msgs);
                return basicSetUpdatabilityExpr((UpdatabilityExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
                return basicSetQueryExpr(null, msgs);
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
                return ((InternalEList)getOrderByClause()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR:
                return basicSetUpdatabilityExpr(null, msgs);
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
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
                return getQueryExpr();
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
                return getOrderByClause();
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR:
                return getUpdatabilityExpr();
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
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
                setQueryExpr((QueryExpressionRoot)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
                getOrderByClause().clear();
                getOrderByClause().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR:
                setUpdatabilityExpr((UpdatabilityExpression)newValue);
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
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
                setQueryExpr((QueryExpressionRoot)null);
                return;
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
                getOrderByClause().clear();
                return;
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR:
                setUpdatabilityExpr((UpdatabilityExpression)null);
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
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
                return queryExpr != null;
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
                return orderByClause != null && !orderByClause.isEmpty();
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR:
                return updatabilityExpr != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLSelectStatementImpl
