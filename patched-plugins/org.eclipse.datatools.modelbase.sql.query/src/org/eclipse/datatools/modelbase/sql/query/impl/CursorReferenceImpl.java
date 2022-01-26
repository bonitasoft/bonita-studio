/**
 * <copyright>
 * </copyright>
 *
 * $Id: CursorReferenceImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.CursorReference;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Cursor Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.CursorReferenceImpl#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.CursorReferenceImpl#getDeleteStatement <em>Delete Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CursorReferenceImpl extends SQLQueryObjectImpl implements CursorReference {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CursorReferenceImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.CURSOR_REFERENCE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryUpdateStatement getUpdateStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT) return null;
        return (QueryUpdateStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUpdateStatement(QueryUpdateStatement newUpdateStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUpdateStatement, SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpdateStatement(QueryUpdateStatement newUpdateStatement) {
        if (newUpdateStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT && newUpdateStatement != null)) {
            if (EcoreUtil.isAncestor(this, newUpdateStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUpdateStatement != null)
                msgs = ((InternalEObject)newUpdateStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, QueryUpdateStatement.class, msgs);
            msgs = basicSetUpdateStatement(newUpdateStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT, newUpdateStatement, newUpdateStatement));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryDeleteStatement getDeleteStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT) return null;
        return (QueryDeleteStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDeleteStatement(QueryDeleteStatement newDeleteStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newDeleteStatement, SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDeleteStatement(QueryDeleteStatement newDeleteStatement) {
        if (newDeleteStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT && newDeleteStatement != null)) {
            if (EcoreUtil.isAncestor(this, newDeleteStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newDeleteStatement != null)
                msgs = ((InternalEObject)newDeleteStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, QueryDeleteStatement.class, msgs);
            msgs = basicSetDeleteStatement(newDeleteStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT, newDeleteStatement, newDeleteStatement));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUpdateStatement((QueryUpdateStatement)otherEnd, msgs);
            case SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetDeleteStatement((QueryDeleteStatement)otherEnd, msgs);
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
            case SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
                return basicSetUpdateStatement(null, msgs);
            case SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
                return basicSetDeleteStatement(null, msgs);
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
            case SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, QueryUpdateStatement.class, msgs);
            case SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE, QueryDeleteStatement.class, msgs);
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
            case SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
                return getUpdateStatement();
            case SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
                return getDeleteStatement();
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
            case SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
                setUpdateStatement((QueryUpdateStatement)newValue);
                return;
            case SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
                setDeleteStatement((QueryDeleteStatement)newValue);
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
            case SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
                setUpdateStatement((QueryUpdateStatement)null);
                return;
            case SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
                setDeleteStatement((QueryDeleteStatement)null);
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
            case SQLQueryModelPackage.CURSOR_REFERENCE__UPDATE_STATEMENT:
                return getUpdateStatement() != null;
            case SQLQueryModelPackage.CURSOR_REFERENCE__DELETE_STATEMENT:
                return getDeleteStatement() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLCursorReferenceImpl
