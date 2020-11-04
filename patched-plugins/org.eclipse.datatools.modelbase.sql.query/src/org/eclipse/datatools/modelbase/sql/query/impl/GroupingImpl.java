/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.Grouping;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
 * An implementation of the model object '<em><b>SQL Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingImpl#getGroupingSetsElementExpr <em>Grouping Sets Element Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GroupingImpl extends GroupingSpecificationImpl implements Grouping {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GroupingImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.GROUPING;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GroupingSetsElementExpression getGroupingSetsElementExpr() {
        if (eContainerFeatureID() != SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR) return null;
        return (GroupingSetsElementExpression)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetGroupingSetsElementExpr(GroupingSetsElementExpression newGroupingSetsElementExpr, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newGroupingSetsElementExpr, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroupingSetsElementExpr(GroupingSetsElementExpression newGroupingSetsElementExpr) {
        if (newGroupingSetsElementExpr != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR && newGroupingSetsElementExpr != null)) {
            if (EcoreUtil.isAncestor(this, newGroupingSetsElementExpr))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGroupingSetsElementExpr != null)
                msgs = ((InternalEObject)newGroupingSetsElementExpr).eInverseAdd(this, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, GroupingSetsElementExpression.class, msgs);
            msgs = basicSetGroupingSetsElementExpr(newGroupingSetsElementExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, newGroupingSetsElementExpr, newGroupingSetsElementExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetGroupingSetsElementExpr((GroupingSetsElementExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
                return basicSetGroupingSetsElementExpr(null, msgs);
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
            case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, GroupingSetsElementExpression.class, msgs);
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
            case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
                return getGroupingSetsElementExpr();
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
            case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
                setGroupingSetsElementExpr((GroupingSetsElementExpression)newValue);
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
            case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
                setGroupingSetsElementExpr((GroupingSetsElementExpression)null);
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
            case SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR:
                return getGroupingSetsElementExpr() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLGroupImpl
