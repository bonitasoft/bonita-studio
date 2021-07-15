/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryDeleteStatementImpl.java,v 1.4 2007/02/08 17:00:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.CursorReference;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Delete Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryDeleteStatementImpl#getWhereCurrentOfClause <em>Where Current Of Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryDeleteStatementImpl#getWhereClause <em>Where Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryDeleteStatementImpl#getTargetTable <em>Target Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryDeleteStatementImpl extends QueryChangeStatementImpl implements QueryDeleteStatement {
	/**
     * The cached value of the '{@link #getWhereCurrentOfClause() <em>Where Current Of Clause</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWhereCurrentOfClause()
     * @generated
     * @ordered
     */
    protected CursorReference whereCurrentOfClause;

	/**
     * The cached value of the '{@link #getWhereClause() <em>Where Clause</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWhereClause()
     * @generated
     * @ordered
     */
    protected QuerySearchCondition whereClause;

	/**
     * This is true if the Where Clause containment reference has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean whereClauseESet;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryDeleteStatementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_DELETE_STATEMENT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CursorReference getWhereCurrentOfClause() {
        return whereCurrentOfClause;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetWhereCurrentOfClause(CursorReference newWhereCurrentOfClause, NotificationChain msgs) {
        CursorReference oldWhereCurrentOfClause = whereCurrentOfClause;
        whereCurrentOfClause = newWhereCurrentOfClause;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, oldWhereCurrentOfClause, newWhereCurrentOfClause);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWhereCurrentOfClause(CursorReference newWhereCurrentOfClause) {
        if (newWhereCurrentOfClause != whereCurrentOfClause) {
            NotificationChain msgs = null;
            if (whereCurrentOfClause != null)
                msgs = ((InternalEObject)whereCurrentOfClause).eInverseRemove(this, SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT, CursorReference.class, msgs);
            if (newWhereCurrentOfClause != null)
                msgs = ((InternalEObject)newWhereCurrentOfClause).eInverseAdd(this, SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT, CursorReference.class, msgs);
            msgs = basicSetWhereCurrentOfClause(newWhereCurrentOfClause, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, newWhereCurrentOfClause, newWhereCurrentOfClause));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySearchCondition getWhereClause() {
        return whereClause;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetWhereClause(QuerySearchCondition newWhereClause, NotificationChain msgs) {
        QuerySearchCondition oldWhereClause = whereClause;
        whereClause = newWhereClause;
        boolean oldWhereClauseESet = whereClauseESet;
        whereClauseESet = true;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, oldWhereClause, newWhereClause, !oldWhereClauseESet);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWhereClause(QuerySearchCondition newWhereClause) {
        if (newWhereClause != whereClause) {
            NotificationChain msgs = null;
            if (whereClause != null)
                msgs = ((InternalEObject)whereClause).eInverseRemove(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT, QuerySearchCondition.class, msgs);
            if (newWhereClause != null)
                msgs = ((InternalEObject)newWhereClause).eInverseAdd(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT, QuerySearchCondition.class, msgs);
            msgs = basicSetWhereClause(newWhereClause, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else {
            boolean oldWhereClauseESet = whereClauseESet;
            whereClauseESet = true;
            if (eNotificationRequired())
                eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, newWhereClause, newWhereClause, !oldWhereClauseESet));
        }
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicUnsetWhereClause(NotificationChain msgs) {
        QuerySearchCondition oldWhereClause = whereClause;
        whereClause = null;
        boolean oldWhereClauseESet = whereClauseESet;
        whereClauseESet = false;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, oldWhereClause, null, oldWhereClauseESet);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetWhereClause() {
        if (whereClause != null) {
            NotificationChain msgs = null;
            msgs = ((InternalEObject)whereClause).eInverseRemove(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT, QuerySearchCondition.class, msgs);
            msgs = basicUnsetWhereClause(msgs);
            if (msgs != null) msgs.dispatch();
        }
        else {
            boolean oldWhereClauseESet = whereClauseESet;
            whereClauseESet = false;
            if (eNotificationRequired())
                eNotify(new ENotificationImpl(this, Notification.UNSET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, null, null, oldWhereClauseESet));
        }
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetWhereClause() {
        return whereClauseESet;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE, oldTargetTable, newTargetTable, !oldTargetTableESet);
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
                msgs = ((InternalEObject)targetTable).eInverseRemove(this, SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT, TableInDatabase.class, msgs);
            if (newTargetTable != null)
                msgs = ((InternalEObject)newTargetTable).eInverseAdd(this, SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT, TableInDatabase.class, msgs);
            msgs = basicSetTargetTable(newTargetTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else {
            boolean oldTargetTableESet = targetTableESet;
            targetTableESet = true;
            if (eNotificationRequired())
                eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE, newTargetTable, newTargetTable, !oldTargetTableESet));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE, oldTargetTable, null, oldTargetTableESet);
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
            msgs = ((InternalEObject)targetTable).eInverseRemove(this, SQLQueryModelPackage.TABLE_IN_DATABASE__DELETE_STATEMENT, TableInDatabase.class, msgs);
            msgs = basicUnsetTargetTable(msgs);
            if (msgs != null) msgs.dispatch();
        }
        else {
            boolean oldTargetTableESet = targetTableESet;
            targetTableESet = false;
            if (eNotificationRequired())
                eNotify(new ENotificationImpl(this, Notification.UNSET, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE, null, null, oldTargetTableESet));
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE:
                if (whereCurrentOfClause != null)
                    msgs = ((InternalEObject)whereCurrentOfClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, null, msgs);
                return basicSetWhereCurrentOfClause((CursorReference)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE:
                if (whereClause != null)
                    msgs = ((InternalEObject)whereClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, null, msgs);
                return basicSetWhereClause((QuerySearchCondition)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE:
                if (targetTable != null)
                    msgs = ((InternalEObject)targetTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE, null, msgs);
                return basicSetTargetTable((TableInDatabase)otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE:
                return basicSetWhereCurrentOfClause(null, msgs);
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE:
                return basicUnsetWhereClause(msgs);
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE:
                return basicUnsetTargetTable(msgs);
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
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE:
                return getWhereCurrentOfClause();
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE:
                return getWhereClause();
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE:
                return getTargetTable();
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
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE:
                setWhereCurrentOfClause((CursorReference)newValue);
                return;
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE:
                setWhereClause((QuerySearchCondition)newValue);
                return;
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE:
                setTargetTable((TableInDatabase)newValue);
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
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE:
                setWhereCurrentOfClause((CursorReference)null);
                return;
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE:
                unsetWhereClause();
                return;
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE:
                unsetTargetTable();
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
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE:
                return whereCurrentOfClause != null;
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE:
                return isSetWhereClause();
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT__TARGET_TABLE:
                return isSetTargetTable();
        }
        return super.eIsSet(featureID);
    }

} //SQLDeleteStatementImpl
