/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryNested;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query Nested</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryNestedImpl#getNestedQuery <em>Nested Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryNestedImpl extends QueryExpressionBodyImpl implements QueryNested {
    /**
     * The cached value of the '{@link #getNestedQuery() <em>Nested Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNestedQuery()
     * @generated
     * @ordered
     */
    protected QueryExpressionBody nestedQuery;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryNestedImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_NESTED;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionBody getNestedQuery() {
        return nestedQuery;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNestedQuery(QueryExpressionBody newNestedQuery, NotificationChain msgs) {
        QueryExpressionBody oldNestedQuery = nestedQuery;
        nestedQuery = newNestedQuery;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY, oldNestedQuery, newNestedQuery);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNestedQuery(QueryExpressionBody newNestedQuery) {
        if (newNestedQuery != nestedQuery) {
            NotificationChain msgs = null;
            if (nestedQuery != null)
                msgs = ((InternalEObject)nestedQuery).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST, QueryExpressionBody.class, msgs);
            if (newNestedQuery != null)
                msgs = ((InternalEObject)newNestedQuery).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST, QueryExpressionBody.class, msgs);
            msgs = basicSetNestedQuery(newNestedQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY, newNestedQuery, newNestedQuery));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY:
                if (nestedQuery != null)
                    msgs = ((InternalEObject)nestedQuery).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY, null, msgs);
                return basicSetNestedQuery((QueryExpressionBody)otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY:
                return basicSetNestedQuery(null, msgs);
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
            case SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY:
                return getNestedQuery();
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
            case SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY:
                setNestedQuery((QueryExpressionBody)newValue);
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
            case SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY:
                setNestedQuery((QueryExpressionBody)null);
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
            case SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY:
                return nestedQuery != null;
        }
        return super.eIsSet(featureID);
    }

} //QueryNestedImpl
