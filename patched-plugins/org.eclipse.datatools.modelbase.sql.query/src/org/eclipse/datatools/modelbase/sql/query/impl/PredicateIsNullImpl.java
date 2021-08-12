/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateIsNullImpl.java,v 1.5 2007/02/08 17:00:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
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
 * An implementation of the model object '<em><b>SQL Predicate Null</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateIsNullImpl#isNotNull <em>Not Null</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateIsNullImpl#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateIsNullImpl extends PredicateImpl implements PredicateIsNull {
	/**
     * The default value of the '{@link #isNotNull() <em>Not Null</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNotNull()
     * @generated
     * @ordered
     */
    protected static final boolean NOT_NULL_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isNotNull() <em>Not Null</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNotNull()
     * @generated
     * @ordered
     */
    protected boolean notNull = NOT_NULL_EDEFAULT;

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
    protected PredicateIsNullImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.PREDICATE_IS_NULL;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isNotNull() {
        return notNull;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNotNull(boolean newNotNull) {
        boolean oldNotNull = notNull;
        notNull = newNotNull;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_IS_NULL__NOT_NULL, oldNotNull, notNull));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR, oldValueExpr, newValueExpr);
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
                msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL, QueryValueExpression.class, msgs);
            if (newValueExpr != null)
                msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL, QueryValueExpression.class, msgs);
            msgs = basicSetValueExpr(newValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR, newValueExpr, newValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR:
                if (valueExpr != null)
                    msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR, null, msgs);
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
            case SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR:
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
            case SQLQueryModelPackage.PREDICATE_IS_NULL__NOT_NULL:
                return isNotNull() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR:
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
            case SQLQueryModelPackage.PREDICATE_IS_NULL__NOT_NULL:
                setNotNull(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR:
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
            case SQLQueryModelPackage.PREDICATE_IS_NULL__NOT_NULL:
                setNotNull(NOT_NULL_EDEFAULT);
                return;
            case SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR:
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
            case SQLQueryModelPackage.PREDICATE_IS_NULL__NOT_NULL:
                return notNull != NOT_NULL_EDEFAULT;
            case SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR:
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
        result.append(" (notNull: ");
        result.append(notNull);
        result.append(')');
        return result.toString();
    }

} //SQLPredicateNullImpl
