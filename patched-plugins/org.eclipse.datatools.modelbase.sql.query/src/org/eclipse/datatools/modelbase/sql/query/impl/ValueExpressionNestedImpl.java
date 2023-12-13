/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionNestedImpl.java,v 1.5 2007/02/08 17:00:25 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Value Expression Nested</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNestedImpl#getNestedValueExpr <em>Nested Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionNestedImpl extends QueryValueExpressionImpl implements ValueExpressionNested {
	/**
     * The cached value of the '{@link #getNestedValueExpr() <em>Nested Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getNestedValueExpr()
     * @generated
     * @ordered
     */
  protected QueryValueExpression nestedValueExpr;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ValueExpressionNestedImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.VALUE_EXPRESSION_NESTED;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public QueryValueExpression getNestedValueExpr() {
        return nestedValueExpr;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetNestedValueExpr(QueryValueExpression newNestedValueExpr, NotificationChain msgs) {
        QueryValueExpression oldNestedValueExpr = nestedValueExpr;
        nestedValueExpr = newNestedValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, oldNestedValueExpr, newNestedValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setNestedValueExpr(QueryValueExpression newNestedValueExpr) {
        if (newNestedValueExpr != nestedValueExpr) {
            NotificationChain msgs = null;
            if (nestedValueExpr != null)
                msgs = ((InternalEObject)nestedValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST, QueryValueExpression.class, msgs);
            if (newNestedValueExpr != null)
                msgs = ((InternalEObject)newNestedValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST, QueryValueExpression.class, msgs);
            msgs = basicSetNestedValueExpr(newNestedValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, newNestedValueExpr, newNestedValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
                if (nestedValueExpr != null)
                    msgs = ((InternalEObject)nestedValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, null, msgs);
                return basicSetNestedValueExpr((QueryValueExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
                return basicSetNestedValueExpr(null, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
                return getNestedValueExpr();
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
                setNestedValueExpr((QueryValueExpression)newValue);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
                setNestedValueExpr((QueryValueExpression)null);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR:
                return nestedValueExpr != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLValueExpressionNestedImpl
