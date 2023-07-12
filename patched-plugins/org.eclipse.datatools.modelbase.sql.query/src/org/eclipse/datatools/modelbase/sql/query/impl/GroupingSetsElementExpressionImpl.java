/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementExpressionImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.Grouping;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
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
 * An implementation of the model object '<em><b>SQL Grouping Sets Element Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementExpressionImpl#getGroupingSetsElementSublist <em>Grouping Sets Element Sublist</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementExpressionImpl#getGrouping <em>Grouping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupingSetsElementExpressionImpl extends GroupingSetsElementImpl implements GroupingSetsElementExpression {
	/**
     * The cached value of the '{@link #getGrouping() <em>Grouping</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGrouping()
     * @generated
     * @ordered
     */
    protected Grouping grouping;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GroupingSetsElementExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.GROUPING_SETS_ELEMENT_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GroupingSetsElementSublist getGroupingSetsElementSublist() {
        if (eContainerFeatureID() != SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST) return null;
        return (GroupingSetsElementSublist)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetGroupingSetsElementSublist(GroupingSetsElementSublist newGroupingSetsElementSublist, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newGroupingSetsElementSublist, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroupingSetsElementSublist(GroupingSetsElementSublist newGroupingSetsElementSublist) {
        if (newGroupingSetsElementSublist != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST && newGroupingSetsElementSublist != null)) {
            if (EcoreUtil.isAncestor(this, newGroupingSetsElementSublist))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGroupingSetsElementSublist != null)
                msgs = ((InternalEObject)newGroupingSetsElementSublist).eInverseAdd(this, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST, GroupingSetsElementSublist.class, msgs);
            msgs = basicSetGroupingSetsElementSublist(newGroupingSetsElementSublist, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST, newGroupingSetsElementSublist, newGroupingSetsElementSublist));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Grouping getGrouping() {
        return grouping;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGrouping(Grouping newGrouping, NotificationChain msgs) {
        Grouping oldGrouping = grouping;
        grouping = newGrouping;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, oldGrouping, newGrouping);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrouping(Grouping newGrouping) {
        if (newGrouping != grouping) {
            NotificationChain msgs = null;
            if (grouping != null)
                msgs = ((InternalEObject)grouping).eInverseRemove(this, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, Grouping.class, msgs);
            if (newGrouping != null)
                msgs = ((InternalEObject)newGrouping).eInverseAdd(this, SQLQueryModelPackage.GROUPING__GROUPING_SETS_ELEMENT_EXPR, Grouping.class, msgs);
            msgs = basicSetGrouping(newGrouping, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, newGrouping, newGrouping));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetGroupingSetsElementSublist((GroupingSetsElementSublist)otherEnd, msgs);
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
                if (grouping != null)
                    msgs = ((InternalEObject)grouping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING, null, msgs);
                return basicSetGrouping((Grouping)otherEnd, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
                return basicSetGroupingSetsElementSublist(null, msgs);
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
                return basicSetGrouping(null, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST, GroupingSetsElementSublist.class, msgs);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
                return getGroupingSetsElementSublist();
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
                return getGrouping();
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
                setGroupingSetsElementSublist((GroupingSetsElementSublist)newValue);
                return;
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
                setGrouping((Grouping)newValue);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
                setGroupingSetsElementSublist((GroupingSetsElementSublist)null);
                return;
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
                setGrouping((Grouping)null);
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
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST:
                return getGroupingSetsElementSublist() != null;
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING:
                return grouping != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLGroupingSetsElementExpressionImpl
