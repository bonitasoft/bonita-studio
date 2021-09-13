/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResultColumnImpl.java,v 1.5 2007/02/08 17:00:27 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Result Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultColumnImpl#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultColumnImpl#getOrderByResultCol <em>Order By Result Col</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultColumnImpl extends QueryResultSpecificationImpl implements ResultColumn {
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
     * The cached value of the '{@link #getOrderByResultCol() <em>Order By Result Col</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrderByResultCol()
     * @generated
     * @ordered
     */
    protected EList orderByResultCol;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResultColumnImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.RESULT_COLUMN;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR, oldValueExpr, newValueExpr);
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
                msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, QueryValueExpression.class, msgs);
            if (newValueExpr != null)
                msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, QueryValueExpression.class, msgs);
            msgs = basicSetValueExpr(newValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR, newValueExpr, newValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getOrderByResultCol() {
        if (orderByResultCol == null) {
            orderByResultCol = new EObjectWithInverseResolvingEList(OrderByResultColumn.class, this, SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL, SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL);
        }
        return orderByResultCol;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR:
                if (valueExpr != null)
                    msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR, null, msgs);
                return basicSetValueExpr((QueryValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
                return ((InternalEList)getOrderByResultCol()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR:
                return basicSetValueExpr(null, msgs);
            case SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
                return ((InternalEList)getOrderByResultCol()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR:
                return getValueExpr();
            case SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
                return getOrderByResultCol();
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
            case SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR:
                setValueExpr((QueryValueExpression)newValue);
                return;
            case SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
                getOrderByResultCol().clear();
                getOrderByResultCol().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR:
                setValueExpr((QueryValueExpression)null);
                return;
            case SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
                getOrderByResultCol().clear();
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
            case SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR:
                return valueExpr != null;
            case SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
                return orderByResultCol != null && !orderByResultCol.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SQLResultColumnImpl
