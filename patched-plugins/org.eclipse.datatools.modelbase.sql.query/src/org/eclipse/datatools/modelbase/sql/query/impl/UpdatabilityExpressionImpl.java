/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdatabilityType;
import org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Updatability Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdatabilityExpressionImpl#getUpdatabilityType <em>Updatability Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdatabilityExpressionImpl#getUpdateOfColumnList <em>Update Of Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdatabilityExpressionImpl#getSelectStatement <em>Select Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UpdatabilityExpressionImpl extends SQLQueryObjectImpl implements UpdatabilityExpression {
    /**
     * The default value of the '{@link #getUpdatabilityType() <em>Updatability Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpdatabilityType()
     * @generated
     * @ordered
     */
    protected static final UpdatabilityType UPDATABILITY_TYPE_EDEFAULT = UpdatabilityType.READ_ONLY_LITERAL;

    /**
     * The cached value of the '{@link #getUpdatabilityType() <em>Updatability Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpdatabilityType()
     * @generated
     * @ordered
     */
    protected UpdatabilityType updatabilityType = UPDATABILITY_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getUpdateOfColumnList() <em>Update Of Column List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpdateOfColumnList()
     * @generated
     * @ordered
     */
    protected EList updateOfColumnList;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UpdatabilityExpressionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.UPDATABILITY_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UpdatabilityType getUpdatabilityType() {
        return updatabilityType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpdatabilityType(UpdatabilityType newUpdatabilityType) {
        UpdatabilityType oldUpdatabilityType = updatabilityType;
        updatabilityType = newUpdatabilityType == null ? UPDATABILITY_TYPE_EDEFAULT : newUpdatabilityType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATABILITY_TYPE, oldUpdatabilityType, updatabilityType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getUpdateOfColumnList() {
        if (updateOfColumnList == null) {
            updateOfColumnList = new EObjectContainmentWithInverseEList(UpdateOfColumn.class, this, SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST, SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR);
        }
        return updateOfColumnList;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySelectStatement getSelectStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT) return null;
        return (QuerySelectStatement)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSelectStatement(QuerySelectStatement newSelectStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSelectStatement, SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSelectStatement(QuerySelectStatement newSelectStatement) {
        if (newSelectStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT && newSelectStatement != null)) {
            if (EcoreUtil.isAncestor(this, newSelectStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSelectStatement != null)
                msgs = ((InternalEObject)newSelectStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR, QuerySelectStatement.class, msgs);
            msgs = basicSetSelectStatement(newSelectStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT, newSelectStatement, newSelectStatement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST:
                return ((InternalEList)getUpdateOfColumnList()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSelectStatement((QuerySelectStatement)otherEnd, msgs);
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
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST:
                return ((InternalEList)getUpdateOfColumnList()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT:
                return basicSetSelectStatement(null, msgs);
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
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR, QuerySelectStatement.class, msgs);
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
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATABILITY_TYPE:
                return getUpdatabilityType();
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST:
                return getUpdateOfColumnList();
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT:
                return getSelectStatement();
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
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATABILITY_TYPE:
                setUpdatabilityType((UpdatabilityType)newValue);
                return;
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST:
                getUpdateOfColumnList().clear();
                getUpdateOfColumnList().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT:
                setSelectStatement((QuerySelectStatement)newValue);
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
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATABILITY_TYPE:
                setUpdatabilityType(UPDATABILITY_TYPE_EDEFAULT);
                return;
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST:
                getUpdateOfColumnList().clear();
                return;
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT:
                setSelectStatement((QuerySelectStatement)null);
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
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATABILITY_TYPE:
                return updatabilityType != UPDATABILITY_TYPE_EDEFAULT;
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST:
                return updateOfColumnList != null && !updateOfColumnList.isEmpty();
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION__SELECT_STATEMENT:
                return getSelectStatement() != null;
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
        result.append(" (updatabilityType: ");
        result.append(updatabilityType);
        result.append(')');
        return result.toString();
    }

} //UpdatabilityExpressionImpl
