/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateBetweenImpl.java,v 1.5 2007/02/08 17:00:32 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
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
 * An implementation of the model object '<em><b>SQL Predicate Between</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl#isNotBetween <em>Not Between</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl#getLeftValueExpr <em>Left Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl#getRightValueExpr1 <em>Right Value Expr1</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl#getRightValueExpr2 <em>Right Value Expr2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateBetweenImpl extends PredicateImpl implements PredicateBetween {
	/**
     * The default value of the '{@link #isNotBetween() <em>Not Between</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNotBetween()
     * @generated
     * @ordered
     */
    protected static final boolean NOT_BETWEEN_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isNotBetween() <em>Not Between</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNotBetween()
     * @generated
     * @ordered
     */
    protected boolean notBetween = NOT_BETWEEN_EDEFAULT;

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
     * The cached value of the '{@link #getRightValueExpr1() <em>Right Value Expr1</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRightValueExpr1()
     * @generated
     * @ordered
     */
    protected QueryValueExpression rightValueExpr1;

	/**
     * The cached value of the '{@link #getRightValueExpr2() <em>Right Value Expr2</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRightValueExpr2()
     * @generated
     * @ordered
     */
    protected QueryValueExpression rightValueExpr2;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PredicateBetweenImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.PREDICATE_BETWEEN;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isNotBetween() {
        return notBetween;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNotBetween(boolean newNotBetween) {
        boolean oldNotBetween = notBetween;
        notBetween = newNotBetween;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BETWEEN__NOT_BETWEEN, oldNotBetween, notBetween));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, oldLeftValueExpr, newLeftValueExpr);
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
                msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, QueryValueExpression.class, msgs);
            if (newLeftValueExpr != null)
                msgs = ((InternalEObject)newLeftValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, QueryValueExpression.class, msgs);
            msgs = basicSetLeftValueExpr(newLeftValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, newLeftValueExpr, newLeftValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getRightValueExpr1() {
        return rightValueExpr1;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRightValueExpr1(QueryValueExpression newRightValueExpr1, NotificationChain msgs) {
        QueryValueExpression oldRightValueExpr1 = rightValueExpr1;
        rightValueExpr1 = newRightValueExpr1;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, oldRightValueExpr1, newRightValueExpr1);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRightValueExpr1(QueryValueExpression newRightValueExpr1) {
        if (newRightValueExpr1 != rightValueExpr1) {
            NotificationChain msgs = null;
            if (rightValueExpr1 != null)
                msgs = ((InternalEObject)rightValueExpr1).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, QueryValueExpression.class, msgs);
            if (newRightValueExpr1 != null)
                msgs = ((InternalEObject)newRightValueExpr1).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, QueryValueExpression.class, msgs);
            msgs = basicSetRightValueExpr1(newRightValueExpr1, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, newRightValueExpr1, newRightValueExpr1));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getRightValueExpr2() {
        return rightValueExpr2;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRightValueExpr2(QueryValueExpression newRightValueExpr2, NotificationChain msgs) {
        QueryValueExpression oldRightValueExpr2 = rightValueExpr2;
        rightValueExpr2 = newRightValueExpr2;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, oldRightValueExpr2, newRightValueExpr2);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRightValueExpr2(QueryValueExpression newRightValueExpr2) {
        if (newRightValueExpr2 != rightValueExpr2) {
            NotificationChain msgs = null;
            if (rightValueExpr2 != null)
                msgs = ((InternalEObject)rightValueExpr2).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, QueryValueExpression.class, msgs);
            if (newRightValueExpr2 != null)
                msgs = ((InternalEObject)newRightValueExpr2).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, QueryValueExpression.class, msgs);
            msgs = basicSetRightValueExpr2(newRightValueExpr2, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, newRightValueExpr2, newRightValueExpr2));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
                if (leftValueExpr != null)
                    msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, null, msgs);
                return basicSetLeftValueExpr((QueryValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
                if (rightValueExpr1 != null)
                    msgs = ((InternalEObject)rightValueExpr1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, null, msgs);
                return basicSetRightValueExpr1((QueryValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
                if (rightValueExpr2 != null)
                    msgs = ((InternalEObject)rightValueExpr2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, null, msgs);
                return basicSetRightValueExpr2((QueryValueExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
                return basicSetLeftValueExpr(null, msgs);
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
                return basicSetRightValueExpr1(null, msgs);
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
                return basicSetRightValueExpr2(null, msgs);
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
            case SQLQueryModelPackage.PREDICATE_BETWEEN__NOT_BETWEEN:
                return isNotBetween() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
                return getLeftValueExpr();
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
                return getRightValueExpr1();
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
                return getRightValueExpr2();
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
            case SQLQueryModelPackage.PREDICATE_BETWEEN__NOT_BETWEEN:
                setNotBetween(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
                setLeftValueExpr((QueryValueExpression)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
                setRightValueExpr1((QueryValueExpression)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
                setRightValueExpr2((QueryValueExpression)newValue);
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
            case SQLQueryModelPackage.PREDICATE_BETWEEN__NOT_BETWEEN:
                setNotBetween(NOT_BETWEEN_EDEFAULT);
                return;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
                setLeftValueExpr((QueryValueExpression)null);
                return;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
                setRightValueExpr1((QueryValueExpression)null);
                return;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
                setRightValueExpr2((QueryValueExpression)null);
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
            case SQLQueryModelPackage.PREDICATE_BETWEEN__NOT_BETWEEN:
                return notBetween != NOT_BETWEEN_EDEFAULT;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
                return leftValueExpr != null;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
                return rightValueExpr1 != null;
            case SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
                return rightValueExpr2 != null;
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
        result.append(" (notBetween: ");
        result.append(notBetween);
        result.append(')');
        return result.toString();
    }

} //SQLPredicateBetweenImpl
