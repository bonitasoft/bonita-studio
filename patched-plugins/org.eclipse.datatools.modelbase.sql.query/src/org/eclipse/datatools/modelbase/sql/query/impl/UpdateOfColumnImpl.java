/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Update Of Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateOfColumnImpl#getUpdatabilityExpr <em>Updatability Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UpdateOfColumnImpl extends SQLQueryObjectImpl implements UpdateOfColumn {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UpdateOfColumnImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.UPDATE_OF_COLUMN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UpdatabilityExpression getUpdatabilityExpr() {
        if (eContainerFeatureID() != SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR) return null;
        return (UpdatabilityExpression)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUpdatabilityExpr(UpdatabilityExpression newUpdatabilityExpr, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUpdatabilityExpr, SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpdatabilityExpr(UpdatabilityExpression newUpdatabilityExpr) {
        if (newUpdatabilityExpr != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR && newUpdatabilityExpr != null)) {
            if (EcoreUtil.isAncestor(this, newUpdatabilityExpr))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUpdatabilityExpr != null)
                msgs = ((InternalEObject)newUpdatabilityExpr).eInverseAdd(this, SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST, UpdatabilityExpression.class, msgs);
            msgs = basicSetUpdatabilityExpr(newUpdatabilityExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR, newUpdatabilityExpr, newUpdatabilityExpr));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUpdatabilityExpr((UpdatabilityExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR:
                return basicSetUpdatabilityExpr(null, msgs);
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
            case SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST, UpdatabilityExpression.class, msgs);
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
            case SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR:
                return getUpdatabilityExpr();
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
            case SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR:
                setUpdatabilityExpr((UpdatabilityExpression)newValue);
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
            case SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR:
                setUpdatabilityExpr((UpdatabilityExpression)null);
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
            case SQLQueryModelPackage.UPDATE_OF_COLUMN__UPDATABILITY_EXPR:
                return getUpdatabilityExpr() != null;
        }
        return super.eIsSet(featureID);
    }

} //UpdateOfColumnImpl
