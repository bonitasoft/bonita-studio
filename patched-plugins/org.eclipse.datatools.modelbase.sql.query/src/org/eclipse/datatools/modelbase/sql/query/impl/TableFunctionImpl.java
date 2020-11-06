/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableFunctionImpl.java,v 1.6 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;

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
 * An implementation of the model object '<em><b>SQL Table Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableFunctionImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableFunctionImpl#getParameterList <em>Parameter List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableFunctionImpl extends TableExpressionImpl implements TableFunction {
	/**
     * The cached value of the '{@link #getFunction() <em>Function</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFunction()
     * @generated
     * @ordered
     */
    protected Function function;
    /**
     * The cached value of the '{@link #getParameterList() <em>Parameter List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterList()
     * @generated
     * @ordered
     */
    protected EList parameterList;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TableFunctionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.TABLE_FUNCTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Function getFunction() {
        if (function != null && function.eIsProxy()) {
            InternalEObject oldFunction = (InternalEObject)function;
            function = (Function)eResolveProxy(oldFunction);
            if (function != oldFunction) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.TABLE_FUNCTION__FUNCTION, oldFunction, function));
            }
        }
        return function;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Function basicGetFunction() {
        return function;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFunction(Function newFunction) {
        Function oldFunction = function;
        function = newFunction;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_FUNCTION__FUNCTION, oldFunction, function));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getParameterList() {
        if (parameterList == null) {
            parameterList = new EObjectContainmentWithInverseEList(QueryValueExpression.class, this, SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION);
        }
        return parameterList;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST:
                return ((InternalEList)getParameterList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST:
                return ((InternalEList)getParameterList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.TABLE_FUNCTION__FUNCTION:
                if (resolve) return getFunction();
                return basicGetFunction();
            case SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST:
                return getParameterList();
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
            case SQLQueryModelPackage.TABLE_FUNCTION__FUNCTION:
                setFunction((Function)newValue);
                return;
            case SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST:
                getParameterList().clear();
                getParameterList().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.TABLE_FUNCTION__FUNCTION:
                setFunction((Function)null);
                return;
            case SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST:
                getParameterList().clear();
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
            case SQLQueryModelPackage.TABLE_FUNCTION__FUNCTION:
                return function != null;
            case SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST:
                return parameterList != null && !parameterList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SQLTableFunctionImpl
