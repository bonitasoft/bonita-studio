/**
 * <copyright>
 * </copyright>
 *
 * $Id: SearchConditionNestedImpl.java,v 1.5 2007/02/08 17:00:32 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Search Condition Nested</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionNestedImpl#getNestedCondition <em>Nested Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SearchConditionNestedImpl extends QuerySearchConditionImpl implements SearchConditionNested {
	/**
     * The cached value of the '{@link #getNestedCondition() <em>Nested Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getNestedCondition()
     * @generated
     * @ordered
     */
  protected QuerySearchCondition nestedCondition;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SearchConditionNestedImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.SEARCH_CONDITION_NESTED;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public QuerySearchCondition getNestedCondition() {
        return nestedCondition;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetNestedCondition(QuerySearchCondition newNestedCondition, NotificationChain msgs) {
        QuerySearchCondition oldNestedCondition = nestedCondition;
        nestedCondition = newNestedCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, oldNestedCondition, newNestedCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setNestedCondition(QuerySearchCondition newNestedCondition) {
        if (newNestedCondition != nestedCondition) {
            NotificationChain msgs = null;
            if (nestedCondition != null)
                msgs = ((InternalEObject)nestedCondition).eInverseRemove(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST, QuerySearchCondition.class, msgs);
            if (newNestedCondition != null)
                msgs = ((InternalEObject)newNestedCondition).eInverseAdd(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST, QuerySearchCondition.class, msgs);
            msgs = basicSetNestedCondition(newNestedCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, newNestedCondition, newNestedCondition));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
                if (nestedCondition != null)
                    msgs = ((InternalEObject)nestedCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, null, msgs);
                return basicSetNestedCondition((QuerySearchCondition)otherEnd, msgs);
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
            case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
                return basicSetNestedCondition(null, msgs);
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
            case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
                return getNestedCondition();
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
            case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
                setNestedCondition((QuerySearchCondition)newValue);
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
            case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
                setNestedCondition((QuerySearchCondition)null);
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
            case SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION:
                return nestedCondition != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLSearchConditionNestedImpl
