/**
 * <copyright>
 * </copyright>
 *
 * $Id: SearchConditionCombinedImpl.java,v 1.5 2007/02/08 17:00:29 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator;
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
 * An implementation of the model object '<em><b>SQL Search Condition Combined</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionCombinedImpl#getCombinedOperator <em>Combined Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionCombinedImpl#getLeftCondition <em>Left Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionCombinedImpl#getRightCondition <em>Right Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SearchConditionCombinedImpl extends QuerySearchConditionImpl implements SearchConditionCombined {
	/**
     * The default value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCombinedOperator()
     * @generated
     * @ordered
     */
    protected static final SearchConditionCombinedOperator COMBINED_OPERATOR_EDEFAULT = SearchConditionCombinedOperator.AND_LITERAL;

	/**
     * The cached value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCombinedOperator()
     * @generated
     * @ordered
     */
    protected SearchConditionCombinedOperator combinedOperator = COMBINED_OPERATOR_EDEFAULT;

	/**
     * The cached value of the '{@link #getLeftCondition() <em>Left Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLeftCondition()
     * @generated
     * @ordered
     */
    protected QuerySearchCondition leftCondition;

	/**
     * The cached value of the '{@link #getRightCondition() <em>Right Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRightCondition()
     * @generated
     * @ordered
     */
    protected QuerySearchCondition rightCondition;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SearchConditionCombinedImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.SEARCH_CONDITION_COMBINED;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SearchConditionCombinedOperator getCombinedOperator() {
        return combinedOperator;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCombinedOperator(SearchConditionCombinedOperator newCombinedOperator) {
        SearchConditionCombinedOperator oldCombinedOperator = combinedOperator;
        combinedOperator = newCombinedOperator == null ? COMBINED_OPERATOR_EDEFAULT : newCombinedOperator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__COMBINED_OPERATOR, oldCombinedOperator, combinedOperator));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySearchCondition getLeftCondition() {
        return leftCondition;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLeftCondition(QuerySearchCondition newLeftCondition, NotificationChain msgs) {
        QuerySearchCondition oldLeftCondition = leftCondition;
        leftCondition = newLeftCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, oldLeftCondition, newLeftCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLeftCondition(QuerySearchCondition newLeftCondition) {
        if (newLeftCondition != leftCondition) {
            NotificationChain msgs = null;
            if (leftCondition != null)
                msgs = ((InternalEObject)leftCondition).eInverseRemove(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT, QuerySearchCondition.class, msgs);
            if (newLeftCondition != null)
                msgs = ((InternalEObject)newLeftCondition).eInverseAdd(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT, QuerySearchCondition.class, msgs);
            msgs = basicSetLeftCondition(newLeftCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, newLeftCondition, newLeftCondition));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySearchCondition getRightCondition() {
        return rightCondition;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRightCondition(QuerySearchCondition newRightCondition, NotificationChain msgs) {
        QuerySearchCondition oldRightCondition = rightCondition;
        rightCondition = newRightCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, oldRightCondition, newRightCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRightCondition(QuerySearchCondition newRightCondition) {
        if (newRightCondition != rightCondition) {
            NotificationChain msgs = null;
            if (rightCondition != null)
                msgs = ((InternalEObject)rightCondition).eInverseRemove(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT, QuerySearchCondition.class, msgs);
            if (newRightCondition != null)
                msgs = ((InternalEObject)newRightCondition).eInverseAdd(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT, QuerySearchCondition.class, msgs);
            msgs = basicSetRightCondition(newRightCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, newRightCondition, newRightCondition));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION:
                if (leftCondition != null)
                    msgs = ((InternalEObject)leftCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, null, msgs);
                return basicSetLeftCondition((QuerySearchCondition)otherEnd, msgs);
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION:
                if (rightCondition != null)
                    msgs = ((InternalEObject)rightCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, null, msgs);
                return basicSetRightCondition((QuerySearchCondition)otherEnd, msgs);
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
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION:
                return basicSetLeftCondition(null, msgs);
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION:
                return basicSetRightCondition(null, msgs);
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
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__COMBINED_OPERATOR:
                return getCombinedOperator();
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION:
                return getLeftCondition();
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION:
                return getRightCondition();
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
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__COMBINED_OPERATOR:
                setCombinedOperator((SearchConditionCombinedOperator)newValue);
                return;
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION:
                setLeftCondition((QuerySearchCondition)newValue);
                return;
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION:
                setRightCondition((QuerySearchCondition)newValue);
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
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__COMBINED_OPERATOR:
                setCombinedOperator(COMBINED_OPERATOR_EDEFAULT);
                return;
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION:
                setLeftCondition((QuerySearchCondition)null);
                return;
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION:
                setRightCondition((QuerySearchCondition)null);
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
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__COMBINED_OPERATOR:
                return combinedOperator != COMBINED_OPERATOR_EDEFAULT;
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION:
                return leftCondition != null;
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION:
                return rightCondition != null;
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
        result.append(" (combinedOperator: ");
        result.append(combinedOperator);
        result.append(')');
        return result.toString();
    }

} //SQLSearchConditionCombinedImpl
