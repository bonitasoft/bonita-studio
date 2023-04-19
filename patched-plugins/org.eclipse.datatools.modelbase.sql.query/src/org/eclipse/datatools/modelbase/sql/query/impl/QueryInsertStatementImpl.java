/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryInsertStatementImpl.java,v 1.4 2007/02/08 17:00:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Insert Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl#getSourceQuery <em>Source Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl#getSourceValuesRowList <em>Source Values Row List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl#getTargetTable <em>Target Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl#getTargetColumnList <em>Target Column List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryInsertStatementImpl extends QueryChangeStatementImpl implements QueryInsertStatement {
	/**
     * The cached value of the '{@link #getSourceQuery() <em>Source Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceQuery()
     * @generated
     * @ordered
     */
    protected QueryExpressionRoot sourceQuery;

	/**
     * The cached value of the '{@link #getSourceValuesRowList() <em>Source Values Row List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceValuesRowList()
     * @generated
     * @ordered
     */
    protected EList sourceValuesRowList;

	/**
     * The cached value of the '{@link #getTargetTable() <em>Target Table</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetTable()
     * @generated
     * @ordered
     */
    protected TableInDatabase targetTable;

	/**
     * This is true if the Target Table containment reference has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean targetTableESet;

	/**
     * The cached value of the '{@link #getTargetColumnList() <em>Target Column List</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetColumnList()
     * @generated
     * @ordered
     */
    protected EList targetColumnList;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryInsertStatementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_INSERT_STATEMENT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionRoot getSourceQuery() {
        return sourceQuery;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSourceQuery(QueryExpressionRoot newSourceQuery, NotificationChain msgs) {
        QueryExpressionRoot oldSourceQuery = sourceQuery;
        sourceQuery = newSourceQuery;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, oldSourceQuery, newSourceQuery);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceQuery(QueryExpressionRoot newSourceQuery) {
        if (newSourceQuery != sourceQuery) {
            NotificationChain msgs = null;
            if (sourceQuery != null)
                msgs = ((InternalEObject)sourceQuery).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, QueryExpressionRoot.class, msgs);
            if (newSourceQuery != null)
                msgs = ((InternalEObject)newSourceQuery).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, QueryExpressionRoot.class, msgs);
            msgs = basicSetSourceQuery(newSourceQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, newSourceQuery, newSourceQuery));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSourceValuesRowList() {
        if (sourceValuesRowList == null) {
            sourceValuesRowList = new EObjectContainmentWithInverseEList(ValuesRow.class, this, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST, SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT);
        }
        return sourceValuesRowList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableInDatabase getTargetTable() {
        return targetTable;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetTargetTable(TableInDatabase newTargetTable, NotificationChain msgs) {
        TableInDatabase oldTargetTable = targetTable;
        targetTable = newTargetTable;
        boolean oldTargetTableESet = targetTableESet;
        targetTableESet = true;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, oldTargetTable, newTargetTable, !oldTargetTableESet);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setTargetTable(TableInDatabase newTargetTable) {
        if (newTargetTable != targetTable) {
            NotificationChain msgs = null;
            if (targetTable != null)
                msgs = ((InternalEObject)targetTable).eInverseRemove(this, SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, TableInDatabase.class, msgs);
            if (newTargetTable != null)
                msgs = ((InternalEObject)newTargetTable).eInverseAdd(this, SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, TableInDatabase.class, msgs);
            msgs = basicSetTargetTable(newTargetTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else {
            boolean oldTargetTableESet = targetTableESet;
            targetTableESet = true;
            if (eNotificationRequired())
                eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, newTargetTable, newTargetTable, !oldTargetTableESet));
        }
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicUnsetTargetTable(NotificationChain msgs) {
        TableInDatabase oldTargetTable = targetTable;
        targetTable = null;
        boolean oldTargetTableESet = targetTableESet;
        targetTableESet = false;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, oldTargetTable, null, oldTargetTableESet);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetTargetTable() {
        if (targetTable != null) {
            NotificationChain msgs = null;
            msgs = ((InternalEObject)targetTable).eInverseRemove(this, SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, TableInDatabase.class, msgs);
            msgs = basicUnsetTargetTable(msgs);
            if (msgs != null) msgs.dispatch();
        }
        else {
            boolean oldTargetTableESet = targetTableESet;
            targetTableESet = false;
            if (eNotificationRequired())
                eNotify(new ENotificationImpl(this, Notification.UNSET, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, null, null, oldTargetTableESet));
        }
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetTargetTable() {
        return targetTableESet;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTargetColumnList() {
        if (targetColumnList == null) {
            targetColumnList = new EObjectWithInverseResolvingEList.ManyInverse(ValueExpressionColumn.class, this, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT);
        }
        return targetColumnList;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
                if (sourceQuery != null)
                    msgs = ((InternalEObject)sourceQuery).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, null, msgs);
                return basicSetSourceQuery((QueryExpressionRoot)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
                return ((InternalEList)getSourceValuesRowList()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
                if (targetTable != null)
                    msgs = ((InternalEObject)targetTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, null, msgs);
                return basicSetTargetTable((TableInDatabase)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
                return ((InternalEList)getTargetColumnList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
                return basicSetSourceQuery(null, msgs);
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
                return ((InternalEList)getSourceValuesRowList()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
                return basicUnsetTargetTable(msgs);
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
                return ((InternalEList)getTargetColumnList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
                return getSourceQuery();
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
                return getSourceValuesRowList();
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
                return getTargetTable();
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
                return getTargetColumnList();
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
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
                setSourceQuery((QueryExpressionRoot)newValue);
                return;
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
                getSourceValuesRowList().clear();
                getSourceValuesRowList().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
                setTargetTable((TableInDatabase)newValue);
                return;
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
                getTargetColumnList().clear();
                getTargetColumnList().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
                setSourceQuery((QueryExpressionRoot)null);
                return;
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
                getSourceValuesRowList().clear();
                return;
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
                unsetTargetTable();
                return;
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
                getTargetColumnList().clear();
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
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY:
                return sourceQuery != null;
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST:
                return sourceValuesRowList != null && !sourceValuesRowList.isEmpty();
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE:
                return isSetTargetTable();
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST:
                return targetColumnList != null && !targetColumnList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SQLInsertStatementImpl
