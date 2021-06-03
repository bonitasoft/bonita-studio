/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderByResultColumnImpl.java,v 1.5 2007/02/08 17:00:30 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.NullOrderingType;
import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Order By Result Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByResultColumnImpl#getResultCol <em>Result Col</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderByResultColumnImpl extends OrderBySpecificationImpl implements OrderByResultColumn {
	/**
     * The cached value of the '{@link #getResultCol() <em>Result Col</em>}' reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getResultCol()
     * @generated
     * @ordered
     */
  protected ResultColumn resultCol;

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected OrderByResultColumnImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.ORDER_BY_RESULT_COLUMN;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ResultColumn getResultCol() {
        if (resultCol != null && resultCol.eIsProxy()) {
            InternalEObject oldResultCol = (InternalEObject)resultCol;
            resultCol = (ResultColumn)eResolveProxy(oldResultCol);
            if (resultCol != oldResultCol) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL, oldResultCol, resultCol));
            }
        }
        return resultCol;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ResultColumn basicGetResultCol() {
        return resultCol;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetResultCol(ResultColumn newResultCol, NotificationChain msgs) {
        ResultColumn oldResultCol = resultCol;
        resultCol = newResultCol;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL, oldResultCol, newResultCol);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setResultCol(ResultColumn newResultCol) {
        if (newResultCol != resultCol) {
            NotificationChain msgs = null;
            if (resultCol != null)
                msgs = ((InternalEObject)resultCol).eInverseRemove(this, SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL, ResultColumn.class, msgs);
            if (newResultCol != null)
                msgs = ((InternalEObject)newResultCol).eInverseAdd(this, SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL, ResultColumn.class, msgs);
            msgs = basicSetResultCol(newResultCol, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL, newResultCol, newResultCol));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
                if (resultCol != null)
                    msgs = ((InternalEObject)resultCol).eInverseRemove(this, SQLQueryModelPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL, ResultColumn.class, msgs);
                return basicSetResultCol((ResultColumn)otherEnd, msgs);
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
            case SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
                return basicSetResultCol(null, msgs);
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
            case SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
                if (resolve) return getResultCol();
                return basicGetResultCol();
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
            case SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
                setResultCol((ResultColumn)newValue);
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
            case SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
                setResultCol((ResultColumn)null);
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
            case SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
                return resultCol != null;
        }
        return super.eIsSet(featureID);
    }

} //OrderByResultColumnImpl
