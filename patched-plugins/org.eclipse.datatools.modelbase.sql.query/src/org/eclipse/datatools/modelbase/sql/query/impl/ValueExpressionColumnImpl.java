/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionColumnImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
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
 * An implementation of the model object '<em><b>SQL Value Expression Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl#getAssignmentExprTarget <em>Assignment Expr Target</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl#getParentTableExpr <em>Parent Table Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl#getTableExpr <em>Table Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl#getTableInDatabase <em>Table In Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl#getMergeInsertSpec <em>Merge Insert Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionColumnImpl extends ValueExpressionAtomicImpl implements ValueExpressionColumn {
	/**
     * The cached value of the '{@link #getAssignmentExprTarget() <em>Assignment Expr Target</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAssignmentExprTarget()
     * @generated
     * @ordered
     */
    protected EList assignmentExprTarget;

	/**
     * The cached value of the '{@link #getInsertStatement() <em>Insert Statement</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInsertStatement()
     * @generated
     * @ordered
     */
    protected EList insertStatement;

	/**
     * The cached value of the '{@link #getTableExpr() <em>Table Expr</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableExpr()
     * @generated
     * @ordered
     */
    protected TableExpression tableExpr;

	/**
     * The cached value of the '{@link #getTableInDatabase() <em>Table In Database</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableInDatabase()
     * @generated
     * @ordered
     */
    protected TableInDatabase tableInDatabase;

	/**
     * The cached value of the '{@link #getMergeInsertSpec() <em>Merge Insert Spec</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMergeInsertSpec()
     * @generated
     * @ordered
     */
    protected EList mergeInsertSpec;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ValueExpressionColumnImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.VALUE_EXPRESSION_COLUMN;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getAssignmentExprTarget() {
        if (assignmentExprTarget == null) {
            assignmentExprTarget = new EObjectWithInverseResolvingEList.ManyInverse(UpdateAssignmentExpression.class, this, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST);
        }
        return assignmentExprTarget;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableExpression getParentTableExpr() {
        if (eContainerFeatureID() != SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR) return null;
        return (TableExpression)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetParentTableExpr(TableExpression newParentTableExpr, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newParentTableExpr, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParentTableExpr(TableExpression newParentTableExpr) {
        if (newParentTableExpr != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR && newParentTableExpr != null)) {
            if (EcoreUtil.isAncestor(this, newParentTableExpr))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParentTableExpr != null)
                msgs = ((InternalEObject)newParentTableExpr).eInverseAdd(this, SQLQueryModelPackage.TABLE_EXPRESSION__COLUMN_LIST, TableExpression.class, msgs);
            msgs = basicSetParentTableExpr(newParentTableExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR, newParentTableExpr, newParentTableExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableExpression getTableExpr() {
        if (tableExpr != null && tableExpr.eIsProxy()) {
            InternalEObject oldTableExpr = (InternalEObject)tableExpr;
            tableExpr = (TableExpression)eResolveProxy(oldTableExpr);
            if (tableExpr != oldTableExpr) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR, oldTableExpr, tableExpr));
            }
        }
        return tableExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableExpression basicGetTableExpr() {
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR, oldTableExpr, newTableExpr);
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
                msgs = ((InternalEObject)tableExpr).eInverseRemove(this, SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS, TableExpression.class, msgs);
            if (newTableExpr != null)
                msgs = ((InternalEObject)newTableExpr).eInverseAdd(this, SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS, TableExpression.class, msgs);
            msgs = basicSetTableExpr(newTableExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR, newTableExpr, newTableExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableInDatabase getTableInDatabase() {
        if (tableInDatabase != null && tableInDatabase.eIsProxy()) {
            InternalEObject oldTableInDatabase = (InternalEObject)tableInDatabase;
            tableInDatabase = (TableInDatabase)eResolveProxy(oldTableInDatabase);
            if (tableInDatabase != oldTableInDatabase) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE, oldTableInDatabase, tableInDatabase));
            }
        }
        return tableInDatabase;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableInDatabase basicGetTableInDatabase() {
        return tableInDatabase;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTableInDatabase(TableInDatabase newTableInDatabase, NotificationChain msgs) {
        TableInDatabase oldTableInDatabase = tableInDatabase;
        tableInDatabase = newTableInDatabase;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE, oldTableInDatabase, newTableInDatabase);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableInDatabase(TableInDatabase newTableInDatabase) {
        if (newTableInDatabase != tableInDatabase) {
            NotificationChain msgs = null;
            if (tableInDatabase != null)
                msgs = ((InternalEObject)tableInDatabase).eInverseRemove(this, SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST, TableInDatabase.class, msgs);
            if (newTableInDatabase != null)
                msgs = ((InternalEObject)newTableInDatabase).eInverseAdd(this, SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST, TableInDatabase.class, msgs);
            msgs = basicSetTableInDatabase(newTableInDatabase, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE, newTableInDatabase, newTableInDatabase));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getMergeInsertSpec() {
        if (mergeInsertSpec == null) {
            mergeInsertSpec = new EObjectWithInverseResolvingEList.ManyInverse(MergeInsertSpecification.class, this, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC, SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST);
        }
        return mergeInsertSpec;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET:
                return ((InternalEList)getAssignmentExprTarget()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetParentTableExpr((TableExpression)otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT:
                return ((InternalEList)getInsertStatement()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR:
                if (tableExpr != null)
                    msgs = ((InternalEObject)tableExpr).eInverseRemove(this, SQLQueryModelPackage.TABLE_EXPRESSION__VALUE_EXPR_COLUMNS, TableExpression.class, msgs);
                return basicSetTableExpr((TableExpression)otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE:
                if (tableInDatabase != null)
                    msgs = ((InternalEObject)tableInDatabase).eInverseRemove(this, SQLQueryModelPackage.TABLE_IN_DATABASE__DERIVED_COLUMN_LIST, TableInDatabase.class, msgs);
                return basicSetTableInDatabase((TableInDatabase)otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC:
                return ((InternalEList)getMergeInsertSpec()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET:
                return ((InternalEList)getAssignmentExprTarget()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR:
                return basicSetParentTableExpr(null, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT:
                return ((InternalEList)getInsertStatement()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR:
                return basicSetTableExpr(null, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE:
                return basicSetTableInDatabase(null, msgs);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC:
                return ((InternalEList)getMergeInsertSpec()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.TABLE_EXPRESSION__COLUMN_LIST, TableExpression.class, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET:
                return getAssignmentExprTarget();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR:
                return getParentTableExpr();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT:
                return getInsertStatement();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR:
                if (resolve) return getTableExpr();
                return basicGetTableExpr();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE:
                if (resolve) return getTableInDatabase();
                return basicGetTableInDatabase();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC:
                return getMergeInsertSpec();
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET:
                getAssignmentExprTarget().clear();
                getAssignmentExprTarget().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR:
                setParentTableExpr((TableExpression)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT:
                getInsertStatement().clear();
                getInsertStatement().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR:
                setTableExpr((TableExpression)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE:
                setTableInDatabase((TableInDatabase)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC:
                getMergeInsertSpec().clear();
                getMergeInsertSpec().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET:
                getAssignmentExprTarget().clear();
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR:
                setParentTableExpr((TableExpression)null);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT:
                getInsertStatement().clear();
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR:
                setTableExpr((TableExpression)null);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE:
                setTableInDatabase((TableInDatabase)null);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC:
                getMergeInsertSpec().clear();
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET:
                return assignmentExprTarget != null && !assignmentExprTarget.isEmpty();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR:
                return getParentTableExpr() != null;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT:
                return insertStatement != null && !insertStatement.isEmpty();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_EXPR:
                return tableExpr != null;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE:
                return tableInDatabase != null;
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC:
                return mergeInsertSpec != null && !mergeInsertSpec.isEmpty();
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getInsertStatement() {
        if (insertStatement == null) {
            insertStatement = new EObjectWithInverseResolvingEList.ManyInverse(QueryInsertStatement.class, this, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST);
        }
        return insertStatement;
    }

} //SQLValueExpressionColumnImpl
