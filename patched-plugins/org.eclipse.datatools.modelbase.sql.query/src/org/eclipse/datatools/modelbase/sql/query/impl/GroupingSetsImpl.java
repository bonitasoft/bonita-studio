/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsImpl.java,v 1.5 2007/02/08 17:00:27 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
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
 * An implementation of the model object '<em><b>SQL Grouping Sets</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsImpl#getGroupingSetsElementList <em>Grouping Sets Element List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupingSetsImpl extends GroupingSpecificationImpl implements GroupingSets {
	/**
     * The cached value of the '{@link #getGroupingSetsElementList() <em>Grouping Sets Element List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroupingSetsElementList()
     * @generated
     * @ordered
     */
    protected EList groupingSetsElementList;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GroupingSetsImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.GROUPING_SETS;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getGroupingSetsElementList() {
        if (groupingSetsElementList == null) {
            groupingSetsElementList = new EObjectContainmentWithInverseEList(GroupingSetsElement.class, this, SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST, SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS);
        }
        return groupingSetsElementList;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
                return ((InternalEList)getGroupingSetsElementList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
                return ((InternalEList)getGroupingSetsElementList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
                return getGroupingSetsElementList();
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
            case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
                getGroupingSetsElementList().clear();
                getGroupingSetsElementList().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
                getGroupingSetsElementList().clear();
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
            case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
                return groupingSetsElementList != null && !groupingSetsElementList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SQLGroupingSetsImpl
