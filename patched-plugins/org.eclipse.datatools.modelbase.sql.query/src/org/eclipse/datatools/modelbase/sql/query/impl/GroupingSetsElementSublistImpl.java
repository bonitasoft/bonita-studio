/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementSublistImpl.java,v 1.5 2007/02/08 17:00:32 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Grouping Sets Element Sublist</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementSublistImpl#getGroupingSetsElementExprList <em>Grouping Sets Element Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupingSetsElementSublistImpl extends GroupingSetsElementImpl implements GroupingSetsElementSublist {
	/**
     * The cached value of the '{@link #getGroupingSetsElementExprList() <em>Grouping Sets Element Expr List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroupingSetsElementExprList()
     * @generated
     * @ordered
     */
    protected EList groupingSetsElementExprList;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GroupingSetsElementSublistImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.GROUPING_SETS_ELEMENT_SUBLIST;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getGroupingSetsElementExprList() {
        if (groupingSetsElementExprList == null) {
            groupingSetsElementExprList = new EObjectContainmentWithInverseEList(GroupingSetsElementExpression.class, this, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST);
        }
        return groupingSetsElementExprList;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
                return ((InternalEList)getGroupingSetsElementExprList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
                return ((InternalEList)getGroupingSetsElementExprList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
                return getGroupingSetsElementExprList();
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
                getGroupingSetsElementExprList().clear();
                getGroupingSetsElementExprList().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
                getGroupingSetsElementExprList().clear();
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
                return groupingSetsElementExprList != null && !groupingSetsElementExprList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SQLGroupingSetsElementSublistImpl
