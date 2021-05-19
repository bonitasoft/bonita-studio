/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.ProcedureReference;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;

import org.eclipse.datatools.modelbase.sql.routines.Procedure;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Procedure Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ProcedureReferenceImpl#getCallStatement <em>Call Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ProcedureReferenceImpl#getProcedure <em>Procedure</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcedureReferenceImpl extends SQLQueryObjectImpl implements ProcedureReference {
    /**
     * The cached value of the '{@link #getProcedure() <em>Procedure</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProcedure()
     * @generated
     * @ordered
     */
    protected Procedure procedure;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ProcedureReferenceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.PROCEDURE_REFERENCE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallStatement getCallStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT) return null;
        return (CallStatement)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCallStatement(CallStatement newCallStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newCallStatement, SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCallStatement(CallStatement newCallStatement) {
        if (newCallStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT && newCallStatement != null)) {
            if (EcoreUtil.isAncestor(this, newCallStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newCallStatement != null)
                msgs = ((InternalEObject)newCallStatement).eInverseAdd(this, SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF, CallStatement.class, msgs);
            msgs = basicSetCallStatement(newCallStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT, newCallStatement, newCallStatement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Procedure getProcedure() {
        if (procedure != null && procedure.eIsProxy()) {
            InternalEObject oldProcedure = (InternalEObject)procedure;
            procedure = (Procedure)eResolveProxy(oldProcedure);
            if (procedure != oldProcedure) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.PROCEDURE_REFERENCE__PROCEDURE, oldProcedure, procedure));
            }
        }
        return procedure;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Procedure basicGetProcedure() {
        return procedure;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProcedure(Procedure newProcedure) {
        Procedure oldProcedure = procedure;
        procedure = newProcedure;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PROCEDURE_REFERENCE__PROCEDURE, oldProcedure, procedure));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetCallStatement((CallStatement)otherEnd, msgs);
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
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT:
                return basicSetCallStatement(null, msgs);
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
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.CALL_STATEMENT__PROCEDURE_REF, CallStatement.class, msgs);
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
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT:
                return getCallStatement();
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__PROCEDURE:
                if (resolve) return getProcedure();
                return basicGetProcedure();
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
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT:
                setCallStatement((CallStatement)newValue);
                return;
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__PROCEDURE:
                setProcedure((Procedure)newValue);
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
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT:
                setCallStatement((CallStatement)null);
                return;
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__PROCEDURE:
                setProcedure((Procedure)null);
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
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__CALL_STATEMENT:
                return getCallStatement() != null;
            case SQLQueryModelPackage.PROCEDURE_REFERENCE__PROCEDURE:
                return procedure != null;
        }
        return super.eIsSet(featureID);
    }

} //ProcedureReferenceImpl
