/**
 * <copyright>
 * </copyright>
 *
 * $Id: WithTableReferenceImpl.java,v 1.5 2007/02/08 17:00:27 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>With Table Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableReferenceImpl#getWithTableSpecification <em>With Table Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WithTableReferenceImpl extends TableExpressionImpl implements WithTableReference {
	/**
     * The cached value of the '{@link #getWithTableSpecification() <em>With Table Specification</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWithTableSpecification()
     * @generated
     * @ordered
     */
    protected WithTableSpecification withTableSpecification;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected WithTableReferenceImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.WITH_TABLE_REFERENCE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WithTableSpecification getWithTableSpecification() {
        if (withTableSpecification != null && withTableSpecification.eIsProxy()) {
            InternalEObject oldWithTableSpecification = (InternalEObject)withTableSpecification;
            withTableSpecification = (WithTableSpecification)eResolveProxy(oldWithTableSpecification);
            if (withTableSpecification != oldWithTableSpecification) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION, oldWithTableSpecification, withTableSpecification));
            }
        }
        return withTableSpecification;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WithTableSpecification basicGetWithTableSpecification() {
        return withTableSpecification;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetWithTableSpecification(WithTableSpecification newWithTableSpecification, NotificationChain msgs) {
        WithTableSpecification oldWithTableSpecification = withTableSpecification;
        withTableSpecification = newWithTableSpecification;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION, oldWithTableSpecification, newWithTableSpecification);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWithTableSpecification(WithTableSpecification newWithTableSpecification) {
        if (newWithTableSpecification != withTableSpecification) {
            NotificationChain msgs = null;
            if (withTableSpecification != null)
                msgs = ((InternalEObject)withTableSpecification).eInverseRemove(this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES, WithTableSpecification.class, msgs);
            if (newWithTableSpecification != null)
                msgs = ((InternalEObject)newWithTableSpecification).eInverseAdd(this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES, WithTableSpecification.class, msgs);
            msgs = basicSetWithTableSpecification(newWithTableSpecification, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION, newWithTableSpecification, newWithTableSpecification));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
                if (withTableSpecification != null)
                    msgs = ((InternalEObject)withTableSpecification).eInverseRemove(this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES, WithTableSpecification.class, msgs);
                return basicSetWithTableSpecification((WithTableSpecification)otherEnd, msgs);
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
            case SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
                return basicSetWithTableSpecification(null, msgs);
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
            case SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
                if (resolve) return getWithTableSpecification();
                return basicGetWithTableSpecification();
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
            case SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
                setWithTableSpecification((WithTableSpecification)newValue);
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
            case SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
                setWithTableSpecification((WithTableSpecification)null);
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
            case SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION:
                return withTableSpecification != null;
        }
        return super.eIsSet(featureID);
    }

} //WithTableReferenceImpl
