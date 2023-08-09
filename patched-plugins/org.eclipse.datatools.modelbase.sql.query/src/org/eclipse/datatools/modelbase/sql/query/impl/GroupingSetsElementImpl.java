/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement;
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
 * An implementation of the model object '<em><b>SQL Grouping Sets Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementImpl#getGroupingSets <em>Grouping Sets</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GroupingSetsElementImpl extends SQLQueryObjectImpl implements GroupingSetsElement {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GroupingSetsElementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.GROUPING_SETS_ELEMENT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GroupingSets getGroupingSets() {
        if (eContainerFeatureID() != SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS) return null;
        return (GroupingSets)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetGroupingSets(GroupingSets newGroupingSets, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newGroupingSets, SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroupingSets(GroupingSets newGroupingSets) {
        if (newGroupingSets != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS && newGroupingSets != null)) {
            if (EcoreUtil.isAncestor(this, newGroupingSets))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGroupingSets != null)
                msgs = ((InternalEObject)newGroupingSets).eInverseAdd(this, SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST, GroupingSets.class, msgs);
            msgs = basicSetGroupingSets(newGroupingSets, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS, newGroupingSets, newGroupingSets));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetGroupingSets((GroupingSets)otherEnd, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
                return basicSetGroupingSets(null, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST, GroupingSets.class, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
                return getGroupingSets();
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
                setGroupingSets((GroupingSets)newValue);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
                setGroupingSets((GroupingSets)null);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
                return getGroupingSets() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLGroupingSetsElementImpl
