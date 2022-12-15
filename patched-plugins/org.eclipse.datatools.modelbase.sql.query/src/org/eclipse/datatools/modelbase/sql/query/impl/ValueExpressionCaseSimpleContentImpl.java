/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCaseSimpleContentImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Value Expression Case Simple Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleContentImpl#getValueExprCaseSimple <em>Value Expr Case Simple</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleContentImpl#getWhenValueExpr <em>When Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleContentImpl#getResultValueExpr <em>Result Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionCaseSimpleContentImpl extends SQLQueryObjectImpl implements ValueExpressionCaseSimpleContent {
	/**
     * The cached value of the '{@link #getWhenValueExpr() <em>When Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWhenValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression whenValueExpr;

	/**
     * The cached value of the '{@link #getResultValueExpr() <em>Result Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResultValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression resultValueExpr;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ValueExpressionCaseSimpleContentImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCaseSimple getValueExprCaseSimple() {
        if (eContainerFeatureID() != SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE) return null;
        return (ValueExpressionCaseSimple)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCaseSimple(ValueExpressionCaseSimple newValueExprCaseSimple, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSimple, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCaseSimple(ValueExpressionCaseSimple newValueExprCaseSimple) {
        if (newValueExprCaseSimple != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE && newValueExprCaseSimple != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCaseSimple))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCaseSimple != null)
                msgs = ((InternalEObject)newValueExprCaseSimple).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE__CONTENT_LIST, ValueExpressionCaseSimple.class, msgs);
            msgs = basicSetValueExprCaseSimple(newValueExprCaseSimple, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE, newValueExprCaseSimple, newValueExprCaseSimple));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getWhenValueExpr() {
        return whenValueExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetWhenValueExpr(QueryValueExpression newWhenValueExpr, NotificationChain msgs) {
        QueryValueExpression oldWhenValueExpr = whenValueExpr;
        whenValueExpr = newWhenValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR, oldWhenValueExpr, newWhenValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWhenValueExpr(QueryValueExpression newWhenValueExpr) {
        if (newWhenValueExpr != whenValueExpr) {
            NotificationChain msgs = null;
            if (whenValueExpr != null)
                msgs = ((InternalEObject)whenValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, QueryValueExpression.class, msgs);
            if (newWhenValueExpr != null)
                msgs = ((InternalEObject)newWhenValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, QueryValueExpression.class, msgs);
            msgs = basicSetWhenValueExpr(newWhenValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR, newWhenValueExpr, newWhenValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getResultValueExpr() {
        return resultValueExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResultValueExpr(QueryValueExpression newResultValueExpr, NotificationChain msgs) {
        QueryValueExpression oldResultValueExpr = resultValueExpr;
        resultValueExpr = newResultValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR, oldResultValueExpr, newResultValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResultValueExpr(QueryValueExpression newResultValueExpr) {
        if (newResultValueExpr != resultValueExpr) {
            NotificationChain msgs = null;
            if (resultValueExpr != null)
                msgs = ((InternalEObject)resultValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, QueryValueExpression.class, msgs);
            if (newResultValueExpr != null)
                msgs = ((InternalEObject)newResultValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, QueryValueExpression.class, msgs);
            msgs = basicSetResultValueExpr(newResultValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR, newResultValueExpr, newResultValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCaseSimple((ValueExpressionCaseSimple)otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR:
                if (whenValueExpr != null)
                    msgs = ((InternalEObject)whenValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR, null, msgs);
                return basicSetWhenValueExpr((QueryValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR:
                if (resultValueExpr != null)
                    msgs = ((InternalEObject)resultValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR, null, msgs);
                return basicSetResultValueExpr((QueryValueExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE:
                return basicSetValueExprCaseSimple(null, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR:
                return basicSetWhenValueExpr(null, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR:
                return basicSetResultValueExpr(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE__CONTENT_LIST, ValueExpressionCaseSimple.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE:
                return getValueExprCaseSimple();
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR:
                return getWhenValueExpr();
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR:
                return getResultValueExpr();
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE:
                setValueExprCaseSimple((ValueExpressionCaseSimple)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR:
                setWhenValueExpr((QueryValueExpression)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR:
                setResultValueExpr((QueryValueExpression)newValue);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE:
                setValueExprCaseSimple((ValueExpressionCaseSimple)null);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR:
                setWhenValueExpr((QueryValueExpression)null);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR:
                setResultValueExpr((QueryValueExpression)null);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE:
                return getValueExprCaseSimple() != null;
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR:
                return whenValueExpr != null;
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR:
                return resultValueExpr != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLValueExpressionCaseSimpleContentImpl
