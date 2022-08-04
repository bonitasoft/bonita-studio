/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderByOrdinalImpl.java,v 1.5 2007/02/08 17:00:29 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.NullOrderingType;
import org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
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
 * An implementation of the model object '<em><b>SQL Order By Ordinal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByOrdinalImpl#getOrdinalValue <em>Ordinal Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderByOrdinalImpl extends OrderBySpecificationImpl implements OrderByOrdinal {
	/**
     * The default value of the '{@link #getOrdinalValue() <em>Ordinal Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrdinalValue()
     * @generated
     * @ordered
     */
    protected static final int ORDINAL_VALUE_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getOrdinalValue() <em>Ordinal Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrdinalValue()
     * @generated
     * @ordered
     */
    protected int ordinalValue = ORDINAL_VALUE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OrderByOrdinalImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.ORDER_BY_ORDINAL;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getOrdinalValue() {
        return ordinalValue;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setOrdinalValue(int newOrdinalValue) {
        int oldOrdinalValue = ordinalValue;
        ordinalValue = newOrdinalValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE, oldOrdinalValue, ordinalValue));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE:
                return new Integer(getOrdinalValue());
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
            case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE:
                setOrdinalValue(((Integer)newValue).intValue());
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
            case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE:
                setOrdinalValue(ORDINAL_VALUE_EDEFAULT);
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
            case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE:
                return ordinalValue != ORDINAL_VALUE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (ordinalValue: ");
        result.append(ordinalValue);
        result.append(')');
        return result.toString();
    }

} //SQLOrderByOrdinalImpl
