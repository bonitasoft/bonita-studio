/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupElementImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElement;
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
 * An implementation of the model object '<em><b>SQL Super Group Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementImpl#getSuperGroup <em>Super Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SuperGroupElementImpl extends SQLQueryObjectImpl implements SuperGroupElement {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SuperGroupElementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.SUPER_GROUP_ELEMENT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SuperGroup getSuperGroup() {
        if (eContainerFeatureID() != SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP) return null;
        return (SuperGroup)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSuperGroup(SuperGroup newSuperGroup, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSuperGroup, SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSuperGroup(SuperGroup newSuperGroup) {
        if (newSuperGroup != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP && newSuperGroup != null)) {
            if (EcoreUtil.isAncestor(this, newSuperGroup))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSuperGroup != null)
                msgs = ((InternalEObject)newSuperGroup).eInverseAdd(this, SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST, SuperGroup.class, msgs);
            msgs = basicSetSuperGroup(newSuperGroup, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP, newSuperGroup, newSuperGroup));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSuperGroup((SuperGroup)otherEnd, msgs);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
                return basicSetSuperGroup(null, msgs);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST, SuperGroup.class, msgs);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
                return getSuperGroup();
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
                setSuperGroup((SuperGroup)newValue);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
                setSuperGroup((SuperGroup)null);
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
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
                return getSuperGroup() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLSuperGroupElementImpl
