/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCaseElseImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
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
 * An implementation of the model object '<em><b>SQL Value Expression Case Else</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseElseImpl#getValueExprCase <em>Value Expr Case</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseElseImpl#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionCaseElseImpl extends SQLQueryObjectImpl implements ValueExpressionCaseElse {
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
    protected ValueExpressionCaseElseImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.VALUE_EXPRESSION_CASE_ELSE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCase getValueExprCase() {
        if (eContainerFeatureID() != SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE) return null;
        return (ValueExpressionCase)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCase(ValueExpressionCase newValueExprCase, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCase, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCase(ValueExpressionCase newValueExprCase) {
        if (newValueExprCase != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE && newValueExprCase != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCase))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCase != null)
                msgs = ((InternalEObject)newValueExprCase).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE__CASE_ELSE, ValueExpressionCase.class, msgs);
            msgs = basicSetValueExprCase(newValueExprCase, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE, newValueExprCase, newValueExprCase));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR, oldValueExpr, newValueExpr);
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
                msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE, QueryValueExpression.class, msgs);
            if (newValueExpr != null)
                msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE, QueryValueExpression.class, msgs);
            msgs = basicSetValueExpr(newValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR, newValueExpr, newValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCase((ValueExpressionCase)otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR:
                if (valueExpr != null)
                    msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR, null, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE:
                return basicSetValueExprCase(null, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR:
                return basicSetValueExpr(null, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE__CASE_ELSE, ValueExpressionCase.class, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE:
                return getValueExprCase();
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR:
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE:
                setValueExprCase((ValueExpressionCase)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR:
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE:
                setValueExprCase((ValueExpressionCase)null);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR:
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE:
                return getValueExprCase() != null;
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR:
                return valueExpr != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLValueExpressionCaseElseImpl
