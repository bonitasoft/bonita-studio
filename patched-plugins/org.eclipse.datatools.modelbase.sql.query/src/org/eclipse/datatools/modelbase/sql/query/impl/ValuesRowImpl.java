/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValuesRowImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Values Row</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl#getExprList <em>Expr List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl#getQueryValues <em>Query Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValuesRowImpl extends SQLQueryObjectImpl implements ValuesRow {
	/**
     * The cached value of the '{@link #getExprList() <em>Expr List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExprList()
     * @generated
     * @ordered
     */
    protected EList exprList;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ValuesRowImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.VALUES_ROW;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryInsertStatement getInsertStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT) return null;
        return (QueryInsertStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInsertStatement(QueryInsertStatement newInsertStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newInsertStatement, SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInsertStatement(QueryInsertStatement newInsertStatement) {
        if (newInsertStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT && newInsertStatement != null)) {
            if (EcoreUtil.isAncestor(this, newInsertStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newInsertStatement != null)
                msgs = ((InternalEObject)newInsertStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST, QueryInsertStatement.class, msgs);
            msgs = basicSetInsertStatement(newInsertStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT, newInsertStatement, newInsertStatement));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getExprList() {
        if (exprList == null) {
            exprList = new EObjectContainmentWithInverseEList(QueryValueExpression.class, this, SQLQueryModelPackage.VALUES_ROW__EXPR_LIST, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW);
        }
        return exprList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValues getQueryValues() {
        if (eContainerFeatureID() != SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES) return null;
        return (QueryValues)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQueryValues(QueryValues newQueryValues, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQueryValues, SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueryValues(QueryValues newQueryValues) {
        if (newQueryValues != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES && newQueryValues != null)) {
            if (EcoreUtil.isAncestor(this, newQueryValues))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQueryValues != null)
                msgs = ((InternalEObject)newQueryValues).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUES__VALUES_ROW_LIST, QueryValues.class, msgs);
            msgs = basicSetQueryValues(newQueryValues, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES, newQueryValues, newQueryValues));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetInsertStatement((QueryInsertStatement)otherEnd, msgs);
            case SQLQueryModelPackage.VALUES_ROW__EXPR_LIST:
                return ((InternalEList)getExprList()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQueryValues((QueryValues)otherEnd, msgs);
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
            case SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT:
                return basicSetInsertStatement(null, msgs);
            case SQLQueryModelPackage.VALUES_ROW__EXPR_LIST:
                return ((InternalEList)getExprList()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES:
                return basicSetQueryValues(null, msgs);
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
            case SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST, QueryInsertStatement.class, msgs);
            case SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUES__VALUES_ROW_LIST, QueryValues.class, msgs);
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
            case SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT:
                return getInsertStatement();
            case SQLQueryModelPackage.VALUES_ROW__EXPR_LIST:
                return getExprList();
            case SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES:
                return getQueryValues();
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
            case SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT:
                setInsertStatement((QueryInsertStatement)newValue);
                return;
            case SQLQueryModelPackage.VALUES_ROW__EXPR_LIST:
                getExprList().clear();
                getExprList().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES:
                setQueryValues((QueryValues)newValue);
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
            case SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT:
                setInsertStatement((QueryInsertStatement)null);
                return;
            case SQLQueryModelPackage.VALUES_ROW__EXPR_LIST:
                getExprList().clear();
                return;
            case SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES:
                setQueryValues((QueryValues)null);
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
            case SQLQueryModelPackage.VALUES_ROW__INSERT_STATEMENT:
                return getInsertStatement() != null;
            case SQLQueryModelPackage.VALUES_ROW__EXPR_LIST:
                return exprList != null && !exprList.isEmpty();
            case SQLQueryModelPackage.VALUES_ROW__QUERY_VALUES:
                return getQueryValues() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLValuesRowImpl
