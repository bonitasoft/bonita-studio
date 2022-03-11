/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResultTableAllColumnsImpl.java,v 1.5 2007/02/08 17:00:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Result Column All</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultTableAllColumnsImpl#getTableExpr <em>Table Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultTableAllColumnsImpl extends QueryResultSpecificationImpl implements ResultTableAllColumns {
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResultTableAllColumnsImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.RESULT_TABLE_ALL_COLUMNS;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR, oldTableExpr, tableExpr));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR, oldTableExpr, newTableExpr);
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
                msgs = ((InternalEObject)tableExpr).eInverseRemove(this, SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS, TableExpression.class, msgs);
            if (newTableExpr != null)
                msgs = ((InternalEObject)newTableExpr).eInverseAdd(this, SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS, TableExpression.class, msgs);
            msgs = basicSetTableExpr(newTableExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR, newTableExpr, newTableExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
                if (tableExpr != null)
                    msgs = ((InternalEObject)tableExpr).eInverseRemove(this, SQLQueryModelPackage.TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS, TableExpression.class, msgs);
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
            case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
                return basicSetTableExpr(null, msgs);
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
            case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
                if (resolve) return getTableExpr();
                return basicGetTableExpr();
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
            case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
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
            case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
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
            case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR:
                return tableExpr != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLResultColumnAllImpl
