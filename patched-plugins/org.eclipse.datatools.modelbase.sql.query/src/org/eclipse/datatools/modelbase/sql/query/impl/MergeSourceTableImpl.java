/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.MergeSourceTable;
import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableReference;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Merge Source Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeSourceTableImpl#getQueryMergeStatement <em>Query Merge Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeSourceTableImpl#getMergeStatement <em>Merge Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeSourceTableImpl#getTableRef <em>Table Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MergeSourceTableImpl extends SQLQueryObjectImpl implements MergeSourceTable {
    /**
     * The cached value of the '{@link #getQueryMergeStatement() <em>Query Merge Statement</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueryMergeStatement()
     * @generated
     * @ordered
     */
    protected EList queryMergeStatement;

    /**
     * The cached value of the '{@link #getTableRef() <em>Table Ref</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableRef()
     * @generated
     * @ordered
     */
    protected TableReference tableRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MergeSourceTableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.MERGE_SOURCE_TABLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getQueryMergeStatement() {
        if (queryMergeStatement == null) {
            queryMergeStatement = new EObjectResolvingEList(QueryMergeStatement.class, this, SQLQueryModelPackage.MERGE_SOURCE_TABLE__QUERY_MERGE_STATEMENT);
        }
        return queryMergeStatement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryMergeStatement getMergeStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT) return null;
        return (QueryMergeStatement)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMergeStatement(QueryMergeStatement newMergeStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newMergeStatement, SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMergeStatement(QueryMergeStatement newMergeStatement) {
        if (newMergeStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT && newMergeStatement != null)) {
            if (EcoreUtil.isAncestor(this, newMergeStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newMergeStatement != null)
                msgs = ((InternalEObject)newMergeStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE, QueryMergeStatement.class, msgs);
            msgs = basicSetMergeStatement(newMergeStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT, newMergeStatement, newMergeStatement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableReference getTableRef() {
        return tableRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTableRef(TableReference newTableRef, NotificationChain msgs) {
        TableReference oldTableRef = tableRef;
        tableRef = newTableRef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF, oldTableRef, newTableRef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableRef(TableReference newTableRef) {
        if (newTableRef != tableRef) {
            NotificationChain msgs = null;
            if (tableRef != null)
                msgs = ((InternalEObject)tableRef).eInverseRemove(this, SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE, TableReference.class, msgs);
            if (newTableRef != null)
                msgs = ((InternalEObject)newTableRef).eInverseAdd(this, SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE, TableReference.class, msgs);
            msgs = basicSetTableRef(newTableRef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF, newTableRef, newTableRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetMergeStatement((QueryMergeStatement)otherEnd, msgs);
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF:
                if (tableRef != null)
                    msgs = ((InternalEObject)tableRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF, null, msgs);
                return basicSetTableRef((TableReference)otherEnd, msgs);
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
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT:
                return basicSetMergeStatement(null, msgs);
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF:
                return basicSetTableRef(null, msgs);
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
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE, QueryMergeStatement.class, msgs);
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
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__QUERY_MERGE_STATEMENT:
                return getQueryMergeStatement();
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT:
                return getMergeStatement();
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF:
                return getTableRef();
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
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__QUERY_MERGE_STATEMENT:
                getQueryMergeStatement().clear();
                getQueryMergeStatement().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT:
                setMergeStatement((QueryMergeStatement)newValue);
                return;
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF:
                setTableRef((TableReference)newValue);
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
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__QUERY_MERGE_STATEMENT:
                getQueryMergeStatement().clear();
                return;
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT:
                setMergeStatement((QueryMergeStatement)null);
                return;
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF:
                setTableRef((TableReference)null);
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
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__QUERY_MERGE_STATEMENT:
                return queryMergeStatement != null && !queryMergeStatement.isEmpty();
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT:
                return getMergeStatement() != null;
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF:
                return tableRef != null;
        }
        return super.eIsSet(featureID);
    }

} //MergeSourceTableImpl
