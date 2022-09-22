/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryMergeStatementImpl.java,v 1.6 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.MergeOnCondition;
import org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeSourceTable;
import org.eclipse.datatools.modelbase.sql.query.MergeTargetTable;
import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Merge Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryMergeStatementImpl#getTargetTable <em>Target Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryMergeStatementImpl#getSourceTable <em>Source Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryMergeStatementImpl#getOnCondition <em>On Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryMergeStatementImpl#getOperationSpecList <em>Operation Spec List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryMergeStatementImpl extends QueryChangeStatementImpl implements QueryMergeStatement {
	/**
     * The cached value of the '{@link #getTargetTable() <em>Target Table</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetTable()
     * @generated
     * @ordered
     */
    protected MergeTargetTable targetTable;
    /**
     * The cached value of the '{@link #getSourceTable() <em>Source Table</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceTable()
     * @generated
     * @ordered
     */
    protected MergeSourceTable sourceTable;
    /**
     * The cached value of the '{@link #getOnCondition() <em>On Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOnCondition()
     * @generated
     * @ordered
     */
    protected MergeOnCondition onCondition;
    /**
     * The cached value of the '{@link #getOperationSpecList() <em>Operation Spec List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationSpecList()
     * @generated
     * @ordered
     */
    protected EList operationSpecList;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryMergeStatementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_MERGE_STATEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeTargetTable getTargetTable() {
        return targetTable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTargetTable(MergeTargetTable newTargetTable, NotificationChain msgs) {
        MergeTargetTable oldTargetTable = targetTable;
        targetTable = newTargetTable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE, oldTargetTable, newTargetTable);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetTable(MergeTargetTable newTargetTable) {
        if (newTargetTable != targetTable) {
            NotificationChain msgs = null;
            if (targetTable != null)
                msgs = ((InternalEObject)targetTable).eInverseRemove(this, SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT, MergeTargetTable.class, msgs);
            if (newTargetTable != null)
                msgs = ((InternalEObject)newTargetTable).eInverseAdd(this, SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT, MergeTargetTable.class, msgs);
            msgs = basicSetTargetTable(newTargetTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE, newTargetTable, newTargetTable));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeSourceTable getSourceTable() {
        return sourceTable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSourceTable(MergeSourceTable newSourceTable, NotificationChain msgs) {
        MergeSourceTable oldSourceTable = sourceTable;
        sourceTable = newSourceTable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE, oldSourceTable, newSourceTable);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceTable(MergeSourceTable newSourceTable) {
        if (newSourceTable != sourceTable) {
            NotificationChain msgs = null;
            if (sourceTable != null)
                msgs = ((InternalEObject)sourceTable).eInverseRemove(this, SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT, MergeSourceTable.class, msgs);
            if (newSourceTable != null)
                msgs = ((InternalEObject)newSourceTable).eInverseAdd(this, SQLQueryModelPackage.MERGE_SOURCE_TABLE__MERGE_STATEMENT, MergeSourceTable.class, msgs);
            msgs = basicSetSourceTable(newSourceTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE, newSourceTable, newSourceTable));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeOnCondition getOnCondition() {
        return onCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOnCondition(MergeOnCondition newOnCondition, NotificationChain msgs) {
        MergeOnCondition oldOnCondition = onCondition;
        onCondition = newOnCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION, oldOnCondition, newOnCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOnCondition(MergeOnCondition newOnCondition) {
        if (newOnCondition != onCondition) {
            NotificationChain msgs = null;
            if (onCondition != null)
                msgs = ((InternalEObject)onCondition).eInverseRemove(this, SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT, MergeOnCondition.class, msgs);
            if (newOnCondition != null)
                msgs = ((InternalEObject)newOnCondition).eInverseAdd(this, SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT, MergeOnCondition.class, msgs);
            msgs = basicSetOnCondition(newOnCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION, newOnCondition, newOnCondition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getOperationSpecList() {
        if (operationSpecList == null) {
            operationSpecList = new EObjectContainmentWithInverseEList(MergeOperationSpecification.class, this, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST, SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT);
        }
        return operationSpecList;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE:
                if (targetTable != null)
                    msgs = ((InternalEObject)targetTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE, null, msgs);
                return basicSetTargetTable((MergeTargetTable)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE:
                if (sourceTable != null)
                    msgs = ((InternalEObject)sourceTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE, null, msgs);
                return basicSetSourceTable((MergeSourceTable)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION:
                if (onCondition != null)
                    msgs = ((InternalEObject)onCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION, null, msgs);
                return basicSetOnCondition((MergeOnCondition)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST:
                return ((InternalEList)getOperationSpecList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE:
                return basicSetTargetTable(null, msgs);
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE:
                return basicSetSourceTable(null, msgs);
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION:
                return basicSetOnCondition(null, msgs);
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST:
                return ((InternalEList)getOperationSpecList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE:
                return getTargetTable();
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE:
                return getSourceTable();
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION:
                return getOnCondition();
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST:
                return getOperationSpecList();
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
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE:
                setTargetTable((MergeTargetTable)newValue);
                return;
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE:
                setSourceTable((MergeSourceTable)newValue);
                return;
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION:
                setOnCondition((MergeOnCondition)newValue);
                return;
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST:
                getOperationSpecList().clear();
                getOperationSpecList().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE:
                setTargetTable((MergeTargetTable)null);
                return;
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE:
                setSourceTable((MergeSourceTable)null);
                return;
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION:
                setOnCondition((MergeOnCondition)null);
                return;
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST:
                getOperationSpecList().clear();
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
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE:
                return targetTable != null;
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__SOURCE_TABLE:
                return sourceTable != null;
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION:
                return onCondition != null;
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST:
                return operationSpecList != null && !operationSpecList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SQLMergeStatementImpl
