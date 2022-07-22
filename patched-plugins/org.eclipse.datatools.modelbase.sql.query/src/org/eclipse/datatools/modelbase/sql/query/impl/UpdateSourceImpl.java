/**
 * <copyright>
 * </copyright>
 *
 * $Id: UpdateSourceImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
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
 * An implementation of the model object '<em><b>Update Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceImpl#getUpdateAssignmentExpr <em>Update Assignment Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UpdateSourceImpl extends SQLQueryObjectImpl implements UpdateSource {
	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected UpdateSourceImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.UPDATE_SOURCE;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public UpdateAssignmentExpression getUpdateAssignmentExpr() {
        if (eContainerFeatureID() != SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR) return null;
        return (UpdateAssignmentExpression)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUpdateAssignmentExpr(UpdateAssignmentExpression newUpdateAssignmentExpr, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUpdateAssignmentExpr, SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setUpdateAssignmentExpr(UpdateAssignmentExpression newUpdateAssignmentExpr) {
        if (newUpdateAssignmentExpr != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR && newUpdateAssignmentExpr != null)) {
            if (EcoreUtil.isAncestor(this, newUpdateAssignmentExpr))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUpdateAssignmentExpr != null)
                msgs = ((InternalEObject)newUpdateAssignmentExpr).eInverseAdd(this, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, UpdateAssignmentExpression.class, msgs);
            msgs = basicSetUpdateAssignmentExpr(newUpdateAssignmentExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, newUpdateAssignmentExpr, newUpdateAssignmentExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUpdateAssignmentExpr((UpdateAssignmentExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
                return basicSetUpdateAssignmentExpr(null, msgs);
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
            case SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, UpdateAssignmentExpression.class, msgs);
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
            case SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
                return getUpdateAssignmentExpr();
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
            case SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
                setUpdateAssignmentExpr((UpdateAssignmentExpression)newValue);
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
            case SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
                setUpdateAssignmentExpr((UpdateAssignmentExpression)null);
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
            case SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR:
                return getUpdateAssignmentExpr() != null;
        }
        return super.eIsSet(featureID);
    }

} //UpdateSourceImpl
