/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupImpl.java,v 1.5 2007/02/08 17:00:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElement;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Super Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupImpl#getSuperGroupType <em>Super Group Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupImpl#getSuperGroupElementList <em>Super Group Element List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperGroupImpl extends GroupingImpl implements SuperGroup {
	/**
     * The default value of the '{@link #getSuperGroupType() <em>Super Group Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperGroupType()
     * @generated
     * @ordered
     */
    protected static final SuperGroupType SUPER_GROUP_TYPE_EDEFAULT = SuperGroupType.CUBE_LITERAL;

	/**
     * The cached value of the '{@link #getSuperGroupType() <em>Super Group Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperGroupType()
     * @generated
     * @ordered
     */
    protected SuperGroupType superGroupType = SUPER_GROUP_TYPE_EDEFAULT;

	/**
     * The cached value of the '{@link #getSuperGroupElementList() <em>Super Group Element List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperGroupElementList()
     * @generated
     * @ordered
     */
    protected EList superGroupElementList;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SuperGroupImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.SUPER_GROUP;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SuperGroupType getSuperGroupType() {
        return superGroupType;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSuperGroupType(SuperGroupType newSuperGroupType) {
        SuperGroupType oldSuperGroupType = superGroupType;
        superGroupType = newSuperGroupType == null ? SUPER_GROUP_TYPE_EDEFAULT : newSuperGroupType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_TYPE, oldSuperGroupType, superGroupType));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSuperGroupElementList() {
        if (superGroupElementList == null) {
            superGroupElementList = new EObjectContainmentWithInverseEList(SuperGroupElement.class, this, SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST, SQLQueryModelPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP);
        }
        return superGroupElementList;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
                return ((InternalEList)getSuperGroupElementList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
                return ((InternalEList)getSuperGroupElementList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_TYPE:
                return getSuperGroupType();
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
                return getSuperGroupElementList();
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
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_TYPE:
                setSuperGroupType((SuperGroupType)newValue);
                return;
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
                getSuperGroupElementList().clear();
                getSuperGroupElementList().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_TYPE:
                setSuperGroupType(SUPER_GROUP_TYPE_EDEFAULT);
                return;
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
                getSuperGroupElementList().clear();
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
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_TYPE:
                return superGroupType != SUPER_GROUP_TYPE_EDEFAULT;
            case SQLQueryModelPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST:
                return superGroupElementList != null && !superGroupElementList.isEmpty();
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
        result.append(" (superGroupType: ");
        result.append(superGroupType);
        result.append(')');
        return result.toString();
    }

} //SQLSuperGroupImpl
