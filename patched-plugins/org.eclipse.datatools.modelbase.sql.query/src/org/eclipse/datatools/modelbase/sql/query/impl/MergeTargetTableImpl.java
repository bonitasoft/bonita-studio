/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.MergeTargetTable;
import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Merge Target Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeTargetTableImpl#getMergeStatement <em>Merge Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeTargetTableImpl#getTableExpr <em>Table Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MergeTargetTableImpl extends SQLQueryObjectImpl implements MergeTargetTable {
    /**
     * The cached value of the '{@link #getTableExpr() <em>Table Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableExpr()
     * @generated
     * @ordered
     */
    protected TableExpression tableExpr;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MergeTargetTableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.MERGE_TARGET_TABLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryMergeStatement getMergeStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT) return null;
        return (QueryMergeStatement)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMergeStatement(QueryMergeStatement newMergeStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newMergeStatement, SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMergeStatement(QueryMergeStatement newMergeStatement) {
        if (newMergeStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT && newMergeStatement != null)) {
            if (EcoreUtil.isAncestor(this, newMergeStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newMergeStatement != null)
                msgs = ((InternalEObject)newMergeStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE, QueryMergeStatement.class, msgs);
            msgs = basicSetMergeStatement(newMergeStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT, newMergeStatement, newMergeStatement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableExpression getTableExpr() {
        return tableExpr;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTableExpr(TableExpression newTableExpr, NotificationChain msgs) {
        TableExpression oldTableExpr = tableExpr;
        tableExpr = newTableExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR, oldTableExpr, newTableExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableExpr(TableExpression newTableExpr) {
        if (newTableExpr != tableExpr) {
            NotificationChain msgs = null;
            if (tableExpr != null)
                msgs = ((InternalEObject)tableExpr).eInverseRemove(this, SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE, TableExpression.class, msgs);
            if (newTableExpr != null)
                msgs = ((InternalEObject)newTableExpr).eInverseAdd(this, SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE, TableExpression.class, msgs);
            msgs = basicSetTableExpr(newTableExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR, newTableExpr, newTableExpr));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetMergeStatement((QueryMergeStatement)otherEnd, msgs);
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR:
                if (tableExpr != null)
                    msgs = ((InternalEObject)tableExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR, null, msgs);
                return basicSetTableExpr((TableExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT:
                return basicSetMergeStatement(null, msgs);
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR:
                return basicSetTableExpr(null, msgs);
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
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__TARGET_TABLE, QueryMergeStatement.class, msgs);
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
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT:
                return getMergeStatement();
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR:
                return getTableExpr();
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
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT:
                setMergeStatement((QueryMergeStatement)newValue);
                return;
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR:
                setTableExpr((TableExpression)newValue);
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
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT:
                setMergeStatement((QueryMergeStatement)null);
                return;
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR:
                setTableExpr((TableExpression)null);
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
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__MERGE_STATEMENT:
                return getMergeStatement() != null;
            case SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR:
                return tableExpr != null;
        }
        return super.eIsSet(featureID);
    }

} //MergeTargetTableImpl
