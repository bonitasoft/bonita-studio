/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableNestedImpl.java,v 1.5 2007/02/08 17:00:27 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Table Nested</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableNestedImpl#getNestedTableRef <em>Nested Table Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableNestedImpl extends TableReferenceImpl implements TableNested {
	/**
     * The cached value of the '{@link #getNestedTableRef() <em>Nested Table Ref</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getNestedTableRef()
     * @generated
     * @ordered
     */
  protected TableReference nestedTableRef;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TableNestedImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.TABLE_NESTED;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public TableReference getNestedTableRef() {
        return nestedTableRef;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetNestedTableRef(TableReference newNestedTableRef, NotificationChain msgs) {
        TableReference oldNestedTableRef = nestedTableRef;
        nestedTableRef = newNestedTableRef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, oldNestedTableRef, newNestedTableRef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setNestedTableRef(TableReference newNestedTableRef) {
        if (newNestedTableRef != nestedTableRef) {
            NotificationChain msgs = null;
            if (nestedTableRef != null)
                msgs = ((InternalEObject)nestedTableRef).eInverseRemove(this, SQLQueryModelPackage.TABLE_REFERENCE__NEST, TableReference.class, msgs);
            if (newNestedTableRef != null)
                msgs = ((InternalEObject)newNestedTableRef).eInverseAdd(this, SQLQueryModelPackage.TABLE_REFERENCE__NEST, TableReference.class, msgs);
            msgs = basicSetNestedTableRef(newNestedTableRef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, newNestedTableRef, newNestedTableRef));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
                if (nestedTableRef != null)
                    msgs = ((InternalEObject)nestedTableRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, null, msgs);
                return basicSetNestedTableRef((TableReference)otherEnd, msgs);
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
            case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
                return basicSetNestedTableRef(null, msgs);
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
            case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
                return getNestedTableRef();
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
            case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
                setNestedTableRef((TableReference)newValue);
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
            case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
                setNestedTableRef((TableReference)null);
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
            case SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF:
                return nestedTableRef != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLTableNestedImpl
