/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCombinedImpl.java,v 1.5 2007/02/08 17:00:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Value Expression Combined</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl#getCombinedOperator <em>Combined Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl#getLeftValueExpr <em>Left Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl#getRightValueExpr <em>Right Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionCombinedImpl extends QueryValueExpressionImpl implements ValueExpressionCombined {
	/**
     * The default value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCombinedOperator()
     * @generated
     * @ordered
     */
    protected static final ValueExpressionCombinedOperator COMBINED_OPERATOR_EDEFAULT = ValueExpressionCombinedOperator.ADD_LITERAL;

	/**
     * The cached value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCombinedOperator()
     * @generated
     * @ordered
     */
    protected ValueExpressionCombinedOperator combinedOperator = COMBINED_OPERATOR_EDEFAULT;

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
     * The cached value of the '{@link #getRightValueExpr() <em>Right Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRightValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression rightValueExpr;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ValueExpressionCombinedImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.VALUE_EXPRESSION_COMBINED;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCombinedOperator getCombinedOperator() {
        return combinedOperator;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCombinedOperator(ValueExpressionCombinedOperator newCombinedOperator) {
        ValueExpressionCombinedOperator oldCombinedOperator = combinedOperator;
        combinedOperator = newCombinedOperator == null ? COMBINED_OPERATOR_EDEFAULT : newCombinedOperator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR, oldCombinedOperator, combinedOperator));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, oldLeftValueExpr, newLeftValueExpr);
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
                msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, QueryValueExpression.class, msgs);
            if (newLeftValueExpr != null)
                msgs = ((InternalEObject)newLeftValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, QueryValueExpression.class, msgs);
            msgs = basicSetLeftValueExpr(newLeftValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, newLeftValueExpr, newLeftValueExpr));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, oldRightValueExpr, newRightValueExpr);
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
                msgs = ((InternalEObject)rightValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, QueryValueExpression.class, msgs);
            if (newRightValueExpr != null)
                msgs = ((InternalEObject)newRightValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, QueryValueExpression.class, msgs);
            msgs = basicSetRightValueExpr(newRightValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, newRightValueExpr, newRightValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
                if (leftValueExpr != null)
                    msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, null, msgs);
                return basicSetLeftValueExpr((QueryValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
                if (rightValueExpr != null)
                    msgs = ((InternalEObject)rightValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, null, msgs);
                return basicSetRightValueExpr((QueryValueExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
                return basicSetLeftValueExpr(null, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
                return basicSetRightValueExpr(null, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR:
                return getCombinedOperator();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
                return getLeftValueExpr();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
                return getRightValueExpr();
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR:
                setCombinedOperator((ValueExpressionCombinedOperator)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
                setLeftValueExpr((QueryValueExpression)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
                setRightValueExpr((QueryValueExpression)newValue);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR:
                setCombinedOperator(COMBINED_OPERATOR_EDEFAULT);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
                setLeftValueExpr((QueryValueExpression)null);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
                setRightValueExpr((QueryValueExpression)null);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR:
                return combinedOperator != COMBINED_OPERATOR_EDEFAULT;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR:
                return leftValueExpr != null;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR:
                return rightValueExpr != null;
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
        result.append(" (combinedOperator: ");
        result.append(combinedOperator);
        result.append(')');
        return result.toString();
    }

} //SQLValueExpressionCombinedImpl
