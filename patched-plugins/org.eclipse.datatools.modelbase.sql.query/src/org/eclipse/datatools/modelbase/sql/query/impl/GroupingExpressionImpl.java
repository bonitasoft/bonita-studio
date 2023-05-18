/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingExpressionImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression;
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
 * An implementation of the model object '<em><b>SQL Grouping Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingExpressionImpl#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingExpressionImpl#getSuperGroupElementExpr <em>Super Group Element Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupingExpressionImpl extends GroupingImpl implements GroupingExpression {
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
    protected GroupingExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.GROUPING_EXPRESSION;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR, oldValueExpr, newValueExpr);
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
                msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, QueryValueExpression.class, msgs);
            if (newValueExpr != null)
                msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, QueryValueExpression.class, msgs);
            msgs = basicSetValueExpr(newValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR, newValueExpr, newValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SuperGroupElementExpression getSuperGroupElementExpr() {
        if (eContainerFeatureID() != SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR) return null;
        return (SuperGroupElementExpression)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSuperGroupElementExpr(SuperGroupElementExpression newSuperGroupElementExpr, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSuperGroupElementExpr, SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSuperGroupElementExpr(SuperGroupElementExpression newSuperGroupElementExpr) {
        if (newSuperGroupElementExpr != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR && newSuperGroupElementExpr != null)) {
            if (EcoreUtil.isAncestor(this, newSuperGroupElementExpr))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSuperGroupElementExpr != null)
                msgs = ((InternalEObject)newSuperGroupElementExpr).eInverseAdd(this, SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, SuperGroupElementExpression.class, msgs);
            msgs = basicSetSuperGroupElementExpr(newSuperGroupElementExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, newSuperGroupElementExpr, newSuperGroupElementExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR:
                if (valueExpr != null)
                    msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR, null, msgs);
                return basicSetValueExpr((QueryValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSuperGroupElementExpr((SuperGroupElementExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR:
                return basicSetValueExpr(null, msgs);
            case SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
                return basicSetSuperGroupElementExpr(null, msgs);
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
            case SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, SuperGroupElementExpression.class, msgs);
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
            case SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR:
                return getValueExpr();
            case SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
                return getSuperGroupElementExpr();
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
            case SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR:
                setValueExpr((QueryValueExpression)newValue);
                return;
            case SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
                setSuperGroupElementExpr((SuperGroupElementExpression)newValue);
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
            case SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR:
                setValueExpr((QueryValueExpression)null);
                return;
            case SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
                setSuperGroupElementExpr((SuperGroupElementExpression)null);
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
            case SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR:
                return valueExpr != null;
            case SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR:
                return getSuperGroupElementExpr() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLGroupingExpressionImpl
