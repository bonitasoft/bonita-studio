/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.ProcedureReference;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.CallStatementImpl#getArgumentList <em>Argument List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.CallStatementImpl#getProcedureRef <em>Procedure Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallStatementImpl extends SQLQueryObjectImpl implements CallStatement {
    /**
     * The cached value of the '{@link #getArgumentList() <em>Argument List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArgumentList()
     * @generated
     * @ordered
     */
    protected EList argumentList;

    /**
     * The cached value of the '{@link #getProcedureRef() <em>Procedure Ref</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProcedureRef()
     * @generated
     * @ordered
     */
    protected ProcedureReference procedureRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CallStatementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.CALL_STATEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getArgumentList() {
        if (argumentList == null) {
            argumentList = new EObjectContainmentWithInverseEList(QueryValueExpression.class, this, SQLQueryModelPackage.CALL_STATEMENT__ARGUMENT_LIST, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT);
        }
        return argumentList;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProcedureReference getProcedureRef() {
        return procedureRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetProcedureRef(ProcedureReference newProcedureRef, NotificationChain msgs) {
        ProcedureReference oldProcedureRef = procedureRef;
        procedureRef = newProcedureRef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF, oldProcedureRef, newProcedureRef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProcedureRef(ProcedureReference newProcedureRef) {
        if (newProcedureRef != procedureRef) {
            NotificationChain msgs = null;
            if (procedureRef != null)
                msgs = ((InternalEObject)procedureRef).eInverseRemove(this, SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT, ProcedureReference.class, msgs);
            if (newProcedureRef != null)
                msgs = ((InternalEObject)newProcedureRef).eInverseAdd(this, SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT, ProcedureReference.class, msgs);
            msgs = basicSetProcedureRef(newProcedureRef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF, newProcedureRef, newProcedureRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.CALL_STATEMENT__ARGUMENT_LIST:
                return ((InternalEList)getArgumentList()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF:
                if (procedureRef != null)
                    msgs = ((InternalEObject)procedureRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF, null, msgs);
                return basicSetProcedureRef((ProcedureReference)otherEnd, msgs);
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
            case SQLQueryModelPackage.CALL_STATEMENT__ARGUMENT_LIST:
                return ((InternalEList)getArgumentList()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF:
                return basicSetProcedureRef(null, msgs);
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
            case SQLQueryModelPackage.CALL_STATEMENT__ARGUMENT_LIST:
                return getArgumentList();
            case SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF:
                return getProcedureRef();
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
            case SQLQueryModelPackage.CALL_STATEMENT__ARGUMENT_LIST:
                getArgumentList().clear();
                getArgumentList().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF:
                setProcedureRef((ProcedureReference)newValue);
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
            case SQLQueryModelPackage.CALL_STATEMENT__ARGUMENT_LIST:
                getArgumentList().clear();
                return;
            case SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF:
                setProcedureRef((ProcedureReference)null);
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
            case SQLQueryModelPackage.CALL_STATEMENT__ARGUMENT_LIST:
                return argumentList != null && !argumentList.isEmpty();
            case SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF:
                return procedureRef != null;
        }
        return super.eIsSet(featureID);
    }

} //CallStatementImpl
