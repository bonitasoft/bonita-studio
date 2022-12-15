/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableExpressionImpl.java,v 1.6 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.MergeTargetTable;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Table Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl#getColumnList <em>Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl#getTableCorrelation <em>Table Correlation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl#getResultTableAllColumns <em>Result Table All Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl#getValueExprColumns <em>Value Expr Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl#getMergeTargetTable <em>Merge Target Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TableExpressionImpl extends TableReferenceImpl implements TableExpression {
	/**
     * The cached value of the '{@link #getColumnList() <em>Column List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumnList()
     * @generated
     * @ordered
     */
    protected EList columnList;

	/**
     * The cached value of the '{@link #getTableCorrelation() <em>Table Correlation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableCorrelation()
     * @generated
     * @ordered
     */
    protected TableCorrelation tableCorrelation;

	/**
     * The cached value of the '{@link #getResultTableAllColumns() <em>Result Table All Columns</em>}' reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getResultTableAllColumns()
     * @generated
     * @ordered
     */
  protected EList resultTableAllColumns;

	/**
     * The cached value of the '{@link #getValueExprColumns() <em>Value Expr Columns</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueExprColumns()
     * @generated
     * @ordered
     */
    protected EList valueExprColumns;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TableExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.TABLE_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getColumnList() {
        if (columnList == null) {
            columnList = new EObjectContainmentWithInverseEList(ValueExpressionColumn.class, this, SQLQueryModelPackage.TABLE_EXPRESSION__COLUMN_LIST, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR);
        }
        return columnList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableCorrelation getTableCorrelation() {
        return tableCorrelation;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTableCorrelation(TableCorrelation newTableCorrelation, NotificationChain msgs) {
        TableCorrelation oldTableCorrelation = tableCorrelation;
        tableCorrelation = newTableCorrelation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION, oldTableCorrelation, newTableCorrelation);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableCorrelation(TableCorrelation newTableCorrelation) {
        if (newTableCorrelation != tableCorrelation) {
            NotificationChain msgs = null;
            if (tableCorrelation != null)
                msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR, TableCorrelation.class, msgs);
            if (newTableCorrelation != null)
                msgs = ((InternalEObject)newTableCorrelation).eInverseAdd(this, SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR, TableCorrelation.class, msgs);
            msgs = basicSetTableCorrelation(newTableCorrelation, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION, newTableCorrelation, newTableCorrelation));
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList getResultTableAllColumns() {
        if (resultTableAllColumns == null) {
            resultTableAllColumns = new EObjectWithInverseResolvingEList(ResultTableAllColumns.class, this, SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS, SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR);
        }
        return resultTableAllColumns;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getValueExprColumns() {
        if (valueExprColumns == null) {
            valueExprColumns = new EObjectWithInverseResolvingEList(ValueExpressionColumn.class, this, SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR);
        }
        return valueExprColumns;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeTargetTable getMergeTargetTable() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE) return null;
        return (MergeTargetTable)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMergeTargetTable(MergeTargetTable newMergeTargetTable, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newMergeTargetTable, SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMergeTargetTable(MergeTargetTable newMergeTargetTable) {
        if (newMergeTargetTable != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE && newMergeTargetTable != null)) {
            if (EcoreUtil.isAncestor(this, newMergeTargetTable))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newMergeTargetTable != null)
                msgs = ((InternalEObject)newMergeTargetTable).eInverseAdd(this, SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR, MergeTargetTable.class, msgs);
            msgs = basicSetMergeTargetTable(newMergeTargetTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE, newMergeTargetTable, newMergeTargetTable));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.TABLE_EXPRESSION__COLUMN_LIST:
                return ((InternalEList)getColumnList()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
                if (tableCorrelation != null)
                    msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION, null, msgs);
                return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
                return ((InternalEList)getResultTableAllColumns()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
                return ((InternalEList)getValueExprColumns()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetMergeTargetTable((MergeTargetTable)otherEnd, msgs);
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
            case SQLQueryModelPackage.TABLE_EXPRESSION__COLUMN_LIST:
                return ((InternalEList)getColumnList()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
                return basicSetTableCorrelation(null, msgs);
            case SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
                return ((InternalEList)getResultTableAllColumns()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
                return ((InternalEList)getValueExprColumns()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE:
                return basicSetMergeTargetTable(null, msgs);
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
            case SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.MERGE_TARGET_TABLE__TABLE_EXPR, MergeTargetTable.class, msgs);
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
            case SQLQueryModelPackage.TABLE_EXPRESSION__COLUMN_LIST:
                return getColumnList();
            case SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
                return getTableCorrelation();
            case SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
                return getResultTableAllColumns();
            case SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
                return getValueExprColumns();
            case SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE:
                return getMergeTargetTable();
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
            case SQLQueryModelPackage.TABLE_EXPRESSION__COLUMN_LIST:
                getColumnList().clear();
                getColumnList().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
                setTableCorrelation((TableCorrelation)newValue);
                return;
            case SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
                getResultTableAllColumns().clear();
                getResultTableAllColumns().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
                getValueExprColumns().clear();
                getValueExprColumns().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE:
                setMergeTargetTable((MergeTargetTable)newValue);
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
            case SQLQueryModelPackage.TABLE_EXPRESSION__COLUMN_LIST:
                getColumnList().clear();
                return;
            case SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
                setTableCorrelation((TableCorrelation)null);
                return;
            case SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
                getResultTableAllColumns().clear();
                return;
            case SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
                getValueExprColumns().clear();
                return;
            case SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE:
                setMergeTargetTable((MergeTargetTable)null);
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
            case SQLQueryModelPackage.TABLE_EXPRESSION__COLUMN_LIST:
                return columnList != null && !columnList.isEmpty();
            case SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION:
                return tableCorrelation != null;
            case SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS:
                return resultTableAllColumns != null && !resultTableAllColumns.isEmpty();
            case SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS:
                return valueExprColumns != null && !valueExprColumns.isEmpty();
            case SQLQueryModelPackage.TABLE_EXPRESSION__MERGE_TARGET_TABLE:
                return getMergeTargetTable() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLTableExpressionImpl
