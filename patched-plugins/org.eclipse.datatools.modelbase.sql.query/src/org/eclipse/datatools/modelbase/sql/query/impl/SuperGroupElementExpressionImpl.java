/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupElementExpressionImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist;
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
 * An implementation of the model object '<em><b>SQL Super Group Element Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementExpressionImpl#getSuperGroupElementSublist <em>Super Group Element Sublist</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementExpressionImpl#getGroupingExpr <em>Grouping Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperGroupElementExpressionImpl extends SuperGroupElementImpl implements SuperGroupElementExpression {
	/**
     * The cached value of the '{@link #getGroupingExpr() <em>Grouping Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroupingExpr()
     * @generated
     * @ordered
     */
    protected GroupingExpression groupingExpr;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SuperGroupElementExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.SUPER_GROUP_ELEMENT_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SuperGroupElementSublist getSuperGroupElementSublist() {
        if (eContainerFeatureID() != SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST) return null;
        return (SuperGroupElementSublist)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSuperGroupElementSublist(SuperGroupElementSublist newSuperGroupElementSublist, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSuperGroupElementSublist, SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSuperGroupElementSublist(SuperGroupElementSublist newSuperGroupElementSublist) {
        if (newSuperGroupElementSublist != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST && newSuperGroupElementSublist != null)) {
            if (EcoreUtil.isAncestor(this, newSuperGroupElementSublist))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSuperGroupElementSublist != null)
                msgs = ((InternalEObject)newSuperGroupElementSublist).eInverseAdd(this, SQLQueryModelPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST, SuperGroupElementSublist.class, msgs);
            msgs = basicSetSuperGroupElementSublist(newSuperGroupElementSublist, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST, newSuperGroupElementSublist, newSuperGroupElementSublist));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GroupingExpression getGroupingExpr() {
        return groupingExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGroupingExpr(GroupingExpression newGroupingExpr, NotificationChain msgs) {
        GroupingExpression oldGroupingExpr = groupingExpr;
        groupingExpr = newGroupingExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, oldGroupingExpr, newGroupingExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroupingExpr(GroupingExpression newGroupingExpr) {
        if (newGroupingExpr != groupingExpr) {
            NotificationChain msgs = null;
            if (groupingExpr != null)
                msgs = ((InternalEObject)groupingExpr).eInverseRemove(this, SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, GroupingExpression.class, msgs);
            if (newGroupingExpr != null)
                msgs = ((InternalEObject)newGroupingExpr).eInverseAdd(this, SQLQueryModelPackage.GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR, GroupingExpression.class, msgs);
            msgs = basicSetGroupingExpr(newGroupingExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, newGroupingExpr, newGroupingExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSuperGroupElementSublist((SuperGroupElementSublist)otherEnd, msgs);
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
                if (groupingExpr != null)
                    msgs = ((InternalEObject)groupingExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR, null, msgs);
                return basicSetGroupingExpr((GroupingExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
                return basicSetSuperGroupElementSublist(null, msgs);
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
                return basicSetGroupingExpr(null, msgs);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST, SuperGroupElementSublist.class, msgs);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
                return getSuperGroupElementSublist();
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
                return getGroupingExpr();
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
                setSuperGroupElementSublist((SuperGroupElementSublist)newValue);
                return;
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
                setGroupingExpr((GroupingExpression)newValue);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
                setSuperGroupElementSublist((SuperGroupElementSublist)null);
                return;
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
                setGroupingExpr((GroupingExpression)null);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST:
                return getSuperGroupElementSublist() != null;
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR:
                return groupingExpr != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLSuperGroupElementExpressionImpl
