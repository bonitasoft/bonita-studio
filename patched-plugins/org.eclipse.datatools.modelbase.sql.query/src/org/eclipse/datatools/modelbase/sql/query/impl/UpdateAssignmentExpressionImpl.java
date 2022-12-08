/**
 * <copyright>
 * </copyright>
 *
 * $Id: UpdateAssignmentExpressionImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Update Assignment Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl#getTargetColumnList <em>Target Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl#getUpdateSource <em>Update Source</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl#getMergeUpdateSpec <em>Merge Update Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UpdateAssignmentExpressionImpl extends SQLQueryObjectImpl implements UpdateAssignmentExpression {
	/**
     * The cached value of the '{@link #getTargetColumnList() <em>Target Column List</em>}' reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getTargetColumnList()
     * @generated
     * @ordered
     */
  protected EList targetColumnList;

	/**
     * The cached value of the '{@link #getUpdateSource() <em>Update Source</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getUpdateSource()
     * @generated
     * @ordered
     */
  protected UpdateSource updateSource;

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected UpdateAssignmentExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.UPDATE_ASSIGNMENT_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public QueryUpdateStatement getUpdateStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT) return null;
        return (QueryUpdateStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUpdateStatement(QueryUpdateStatement newUpdateStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUpdateStatement, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setUpdateStatement(QueryUpdateStatement newUpdateStatement) {
        if (newUpdateStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT && newUpdateStatement != null)) {
            if (EcoreUtil.isAncestor(this, newUpdateStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUpdateStatement != null)
                msgs = ((InternalEObject)newUpdateStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__ASSIGNMENT_CLAUSE, QueryUpdateStatement.class, msgs);
            msgs = basicSetUpdateStatement(newUpdateStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT, newUpdateStatement, newUpdateStatement));
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList getTargetColumnList() {
        if (targetColumnList == null) {
            targetColumnList = new EObjectWithInverseResolvingEList.ManyInverse(ValueExpressionColumn.class, this, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET);
        }
        return targetColumnList;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public UpdateSource getUpdateSource() {
        return updateSource;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetUpdateSource(UpdateSource newUpdateSource, NotificationChain msgs) {
        UpdateSource oldUpdateSource = updateSource;
        updateSource = newUpdateSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, oldUpdateSource, newUpdateSource);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setUpdateSource(UpdateSource newUpdateSource) {
        if (newUpdateSource != updateSource) {
            NotificationChain msgs = null;
            if (updateSource != null)
                msgs = ((InternalEObject)updateSource).eInverseRemove(this, SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, UpdateSource.class, msgs);
            if (newUpdateSource != null)
                msgs = ((InternalEObject)newUpdateSource).eInverseAdd(this, SQLQueryModelPackage.UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR, UpdateSource.class, msgs);
            msgs = basicSetUpdateSource(newUpdateSource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, newUpdateSource, newUpdateSource));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeUpdateSpecification getMergeUpdateSpec() {
        if (eContainerFeatureID() != SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC) return null;
        return (MergeUpdateSpecification)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMergeUpdateSpec(MergeUpdateSpecification newMergeUpdateSpec, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newMergeUpdateSpec, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMergeUpdateSpec(MergeUpdateSpecification newMergeUpdateSpec) {
        if (newMergeUpdateSpec != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC && newMergeUpdateSpec != null)) {
            if (EcoreUtil.isAncestor(this, newMergeUpdateSpec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newMergeUpdateSpec != null)
                msgs = ((InternalEObject)newMergeUpdateSpec).eInverseAdd(this, SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST, MergeUpdateSpecification.class, msgs);
            msgs = basicSetMergeUpdateSpec(newMergeUpdateSpec, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC, newMergeUpdateSpec, newMergeUpdateSpec));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUpdateStatement((QueryUpdateStatement)otherEnd, msgs);
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
                return ((InternalEList)getTargetColumnList()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
                if (updateSource != null)
                    msgs = ((InternalEObject)updateSource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE, null, msgs);
                return basicSetUpdateSource((UpdateSource)otherEnd, msgs);
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetMergeUpdateSpec((MergeUpdateSpecification)otherEnd, msgs);
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
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
                return basicSetUpdateStatement(null, msgs);
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
                return ((InternalEList)getTargetColumnList()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
                return basicSetUpdateSource(null, msgs);
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC:
                return basicSetMergeUpdateSpec(null, msgs);
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
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__ASSIGNMENT_CLAUSE, QueryUpdateStatement.class, msgs);
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST, MergeUpdateSpecification.class, msgs);
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
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
                return getUpdateStatement();
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
                return getTargetColumnList();
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
                return getUpdateSource();
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC:
                return getMergeUpdateSpec();
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
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
                setUpdateStatement((QueryUpdateStatement)newValue);
                return;
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
                getTargetColumnList().clear();
                getTargetColumnList().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
                setUpdateSource((UpdateSource)newValue);
                return;
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC:
                setMergeUpdateSpec((MergeUpdateSpecification)newValue);
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
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
                setUpdateStatement((QueryUpdateStatement)null);
                return;
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
                getTargetColumnList().clear();
                return;
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
                setUpdateSource((UpdateSource)null);
                return;
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC:
                setMergeUpdateSpec((MergeUpdateSpecification)null);
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
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT:
                return getUpdateStatement() != null;
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST:
                return targetColumnList != null && !targetColumnList.isEmpty();
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE:
                return updateSource != null;
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC:
                return getMergeUpdateSpec() != null;
        }
        return super.eIsSet(featureID);
    }

} //UpdateAssignmentExpressionImpl
