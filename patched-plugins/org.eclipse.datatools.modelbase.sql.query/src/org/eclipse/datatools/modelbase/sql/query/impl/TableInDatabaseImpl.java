/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableInDatabaseImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQLRDB Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getDeleteStatement <em>Delete Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getDatabaseTable <em>Database Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl#getDerivedColumnList <em>Derived Column List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableInDatabaseImpl extends TableExpressionImpl implements TableInDatabase {
	/**
     * The cached value of the '{@link #getDatabaseTable() <em>Database Table</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDatabaseTable()
     * @generated
     * @ordered
     */
    protected Table databaseTable;

	/**
     * The cached value of the '{@link #getDerivedColumnList() <em>Derived Column List</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDerivedColumnList()
     * @generated
     * @ordered
     */
    protected EList derivedColumnList;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TableInDatabaseImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.TABLE_IN_DATABASE;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public QueryUpdateStatement getUpdateStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT) return null;
        return (QueryUpdateStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUpdateStatement(QueryUpdateStatement newUpdateStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUpdateStatement, SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setUpdateStatement(QueryUpdateStatement newUpdateStatement) {
        if (newUpdateStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT && newUpdateStatement != null)) {
            if (EcoreUtil.isAncestor(this, newUpdateStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUpdateStatement != null)
                msgs = ((InternalEObject)newUpdateStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__TARGET_TABLE, QueryUpdateStatement.class, msgs);
            msgs = basicSetUpdateStatement(newUpdateStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT, newUpdateStatement, newUpdateStatement));
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public QueryDeleteStatement getDeleteStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT) return null;
        return (QueryDeleteStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDeleteStatement(QueryDeleteStatement newDeleteStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newDeleteStatement, SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setDeleteStatement(QueryDeleteStatement newDeleteStatement) {
        if (newDeleteStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT && newDeleteStatement != null)) {
            if (EcoreUtil.isAncestor(this, newDeleteStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newDeleteStatement != null)
                msgs = ((InternalEObject)newDeleteStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE, QueryDeleteStatement.class, msgs);
            msgs = basicSetDeleteStatement(newDeleteStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT, newDeleteStatement, newDeleteStatement));
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public QueryInsertStatement getInsertStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT) return null;
        return (QueryInsertStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInsertStatement(QueryInsertStatement newInsertStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newInsertStatement, SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setInsertStatement(QueryInsertStatement newInsertStatement) {
        if (newInsertStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT && newInsertStatement != null)) {
            if (EcoreUtil.isAncestor(this, newInsertStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newInsertStatement != null)
                msgs = ((InternalEObject)newInsertStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, QueryInsertStatement.class, msgs);
            msgs = basicSetInsertStatement(newInsertStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT, newInsertStatement, newInsertStatement));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Table getDatabaseTable() {
        if (databaseTable != null && databaseTable.eIsProxy()) {
            InternalEObject oldDatabaseTable = (InternalEObject)databaseTable;
            databaseTable = (Table)eResolveProxy(oldDatabaseTable);
            if (databaseTable != oldDatabaseTable) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.TABLE_IN_DATABASE__DATABASE_TABLE, oldDatabaseTable, databaseTable));
            }
        }
        return databaseTable;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Table basicGetDatabaseTable() {
        return databaseTable;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDatabaseTable(Table newDatabaseTable) {
        Table oldDatabaseTable = databaseTable;
        databaseTable = newDatabaseTable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_IN_DATABASE__DATABASE_TABLE, oldDatabaseTable, databaseTable));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getDerivedColumnList() {
        if (derivedColumnList == null) {
            derivedColumnList = new EObjectWithInverseResolvingEList(ValueExpressionColumn.class, this, SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE);
        }
        return derivedColumnList;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUpdateStatement((QueryUpdateStatement)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetDeleteStatement((QueryDeleteStatement)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetInsertStatement((QueryInsertStatement)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
                return ((InternalEList)getDerivedColumnList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
                return basicSetUpdateStatement(null, msgs);
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
                return basicSetDeleteStatement(null, msgs);
            case SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
                return basicSetInsertStatement(null, msgs);
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
                return ((InternalEList)getDerivedColumnList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__TARGET_TABLE, QueryUpdateStatement.class, msgs);
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE, QueryDeleteStatement.class, msgs);
            case SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_TABLE, QueryInsertStatement.class, msgs);
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
            case SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
                return getUpdateStatement();
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
                return getDeleteStatement();
            case SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
                return getInsertStatement();
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DATABASE_TABLE:
                if (resolve) return getDatabaseTable();
                return basicGetDatabaseTable();
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
                return getDerivedColumnList();
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
            case SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
                setUpdateStatement((QueryUpdateStatement)newValue);
                return;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
                setDeleteStatement((QueryDeleteStatement)newValue);
                return;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
                setInsertStatement((QueryInsertStatement)newValue);
                return;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DATABASE_TABLE:
                setDatabaseTable((Table)newValue);
                return;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
                getDerivedColumnList().clear();
                getDerivedColumnList().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
                setUpdateStatement((QueryUpdateStatement)null);
                return;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
                setDeleteStatement((QueryDeleteStatement)null);
                return;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
                setInsertStatement((QueryInsertStatement)null);
                return;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DATABASE_TABLE:
                setDatabaseTable((Table)null);
                return;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
                getDerivedColumnList().clear();
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
            case SQLQueryModelPackage.TABLE_IN_DATABASE__UPDATE_STATEMENT:
                return getUpdateStatement() != null;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT:
                return getDeleteStatement() != null;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__INSERT_STATEMENT:
                return getInsertStatement() != null;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DATABASE_TABLE:
                return databaseTable != null;
            case SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST:
                return derivedColumnList != null && !derivedColumnList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SQLRDBTableImpl
