/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Merge Update Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeUpdateSpecificationImpl#getAssignementExprList <em>Assignement Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MergeUpdateSpecificationImpl extends MergeOperationSpecificationImpl implements MergeUpdateSpecification {
    /**
     * The cached value of the '{@link #getAssignementExprList() <em>Assignement Expr List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAssignementExprList()
     * @generated
     * @ordered
     */
    protected EList assignementExprList;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MergeUpdateSpecificationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.MERGE_UPDATE_SPECIFICATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getAssignementExprList() {
        if (assignementExprList == null) {
            assignementExprList = new EObjectContainmentWithInverseEList(UpdateAssignmentExpression.class, this, SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC);
        }
        return assignementExprList;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST:
                return ((InternalEList)getAssignementExprList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST:
                return ((InternalEList)getAssignementExprList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST:
                return getAssignementExprList();
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
            case SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST:
                getAssignementExprList().clear();
                getAssignementExprList().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST:
                getAssignementExprList().clear();
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
            case SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST:
                return assignementExprList != null && !assignementExprList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //MergeUpdateSpecificationImpl
