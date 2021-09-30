/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.MergeOnCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Merge On Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeOnConditionImpl#getMergeStatement <em>Merge Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeOnConditionImpl#getSearchCondition <em>Search Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MergeOnConditionImpl extends SQLQueryObjectImpl implements MergeOnCondition {
    /**
     * The cached value of the '{@link #getSearchCondition() <em>Search Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSearchCondition()
     * @generated
     * @ordered
     */
    protected QuerySearchCondition searchCondition;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MergeOnConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.MERGE_ON_CONDITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryMergeStatement getMergeStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT) return null;
        return (QueryMergeStatement)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMergeStatement(QueryMergeStatement newMergeStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newMergeStatement, SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMergeStatement(QueryMergeStatement newMergeStatement) {
        if (newMergeStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT && newMergeStatement != null)) {
            if (EcoreUtil.isAncestor(this, newMergeStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newMergeStatement != null)
                msgs = ((InternalEObject)newMergeStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION, QueryMergeStatement.class, msgs);
            msgs = basicSetMergeStatement(newMergeStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT, newMergeStatement, newMergeStatement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySearchCondition getSearchCondition() {
        return searchCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSearchCondition(QuerySearchCondition newSearchCondition, NotificationChain msgs) {
        QuerySearchCondition oldSearchCondition = searchCondition;
        searchCondition = newSearchCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION, oldSearchCondition, newSearchCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSearchCondition(QuerySearchCondition newSearchCondition) {
        if (newSearchCondition != searchCondition) {
            NotificationChain msgs = null;
            if (searchCondition != null)
                msgs = ((InternalEObject)searchCondition).eInverseRemove(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION, QuerySearchCondition.class, msgs);
            if (newSearchCondition != null)
                msgs = ((InternalEObject)newSearchCondition).eInverseAdd(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION, QuerySearchCondition.class, msgs);
            msgs = basicSetSearchCondition(newSearchCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION, newSearchCondition, newSearchCondition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetMergeStatement((QueryMergeStatement)otherEnd, msgs);
            case SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION:
                if (searchCondition != null)
                    msgs = ((InternalEObject)searchCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION, null, msgs);
                return basicSetSearchCondition((QuerySearchCondition)otherEnd, msgs);
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
            case SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT:
                return basicSetMergeStatement(null, msgs);
            case SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION:
                return basicSetSearchCondition(null, msgs);
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
            case SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_MERGE_STATEMENT__ON_CONDITION, QueryMergeStatement.class, msgs);
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
            case SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT:
                return getMergeStatement();
            case SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION:
                return getSearchCondition();
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
            case SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT:
                setMergeStatement((QueryMergeStatement)newValue);
                return;
            case SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION:
                setSearchCondition((QuerySearchCondition)newValue);
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
            case SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT:
                setMergeStatement((QueryMergeStatement)null);
                return;
            case SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION:
                setSearchCondition((QuerySearchCondition)null);
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
            case SQLQueryModelPackage.MERGE_ON_CONDITION__MERGE_STATEMENT:
                return getMergeStatement() != null;
            case SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION:
                return searchCondition != null;
        }
        return super.eIsSet(featureID);
    }

} //MergeOnConditionImpl
