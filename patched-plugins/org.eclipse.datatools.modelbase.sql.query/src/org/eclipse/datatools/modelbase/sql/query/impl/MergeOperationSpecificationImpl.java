/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification;
import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Merge Operation Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeOperationSpecificationImpl#getMergeStatement <em>Merge Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MergeOperationSpecificationImpl extends SQLQueryObjectImpl implements MergeOperationSpecification {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MergeOperationSpecificationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.MERGE_OPERATION_SPECIFICATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryMergeStatement getMergeStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT) return null;
        return (QueryMergeStatement)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMergeStatement(QueryMergeStatement newMergeStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newMergeStatement, SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMergeStatement(QueryMergeStatement newMergeStatement) {
        if (newMergeStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT && newMergeStatement != null)) {
            if (EcoreUtil.isAncestor(this, newMergeStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newMergeStatement != null)
                msgs = ((InternalEObject)newMergeStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST, QueryMergeStatement.class, msgs);
            msgs = basicSetMergeStatement(newMergeStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT, newMergeStatement, newMergeStatement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetMergeStatement((QueryMergeStatement)otherEnd, msgs);
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
            case SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT:
                return basicSetMergeStatement(null, msgs);
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
            case SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST, QueryMergeStatement.class, msgs);
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
            case SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT:
                return getMergeStatement();
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
            case SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT:
                setMergeStatement((QueryMergeStatement)newValue);
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
            case SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT:
                setMergeStatement((QueryMergeStatement)null);
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
            case SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT:
                return getMergeStatement() != null;
        }
        return super.eIsSet(featureID);
    }

} //MergeOperationSpecificationImpl
