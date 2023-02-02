/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryCombinedImpl.java,v 1.5 2007/02/08 17:00:25 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
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
 * An implementation of the model object '<em><b>Combined</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl#getCombinedOperator <em>Combined Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl#getLeftQuery <em>Left Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl#getRightQuery <em>Right Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryCombinedImpl extends QueryExpressionBodyImpl implements QueryCombined {
	/**
     * The default value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCombinedOperator()
     * @generated
     * @ordered
     */
    protected static final QueryCombinedOperator COMBINED_OPERATOR_EDEFAULT = QueryCombinedOperator.UNION_LITERAL;

	/**
     * The cached value of the '{@link #getCombinedOperator() <em>Combined Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCombinedOperator()
     * @generated
     * @ordered
     */
    protected QueryCombinedOperator combinedOperator = COMBINED_OPERATOR_EDEFAULT;

	/**
     * The cached value of the '{@link #getLeftQuery() <em>Left Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLeftQuery()
     * @generated
     * @ordered
     */
    protected QueryExpressionBody leftQuery;

	/**
     * The cached value of the '{@link #getRightQuery() <em>Right Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRightQuery()
     * @generated
     * @ordered
     */
    protected QueryExpressionBody rightQuery;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryCombinedImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_COMBINED;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryCombinedOperator getCombinedOperator() {
        return combinedOperator;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCombinedOperator(QueryCombinedOperator newCombinedOperator) {
        QueryCombinedOperator oldCombinedOperator = combinedOperator;
        combinedOperator = newCombinedOperator == null ? COMBINED_OPERATOR_EDEFAULT : newCombinedOperator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_COMBINED__COMBINED_OPERATOR, oldCombinedOperator, combinedOperator));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionBody getLeftQuery() {
        return leftQuery;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLeftQuery(QueryExpressionBody newLeftQuery, NotificationChain msgs) {
        QueryExpressionBody oldLeftQuery = leftQuery;
        leftQuery = newLeftQuery;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY, oldLeftQuery, newLeftQuery);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLeftQuery(QueryExpressionBody newLeftQuery) {
        if (newLeftQuery != leftQuery) {
            NotificationChain msgs = null;
            if (leftQuery != null)
                msgs = ((InternalEObject)leftQuery).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, QueryExpressionBody.class, msgs);
            if (newLeftQuery != null)
                msgs = ((InternalEObject)newLeftQuery).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, QueryExpressionBody.class, msgs);
            msgs = basicSetLeftQuery(newLeftQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY, newLeftQuery, newLeftQuery));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionBody getRightQuery() {
        return rightQuery;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRightQuery(QueryExpressionBody newRightQuery, NotificationChain msgs) {
        QueryExpressionBody oldRightQuery = rightQuery;
        rightQuery = newRightQuery;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY, oldRightQuery, newRightQuery);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRightQuery(QueryExpressionBody newRightQuery) {
        if (newRightQuery != rightQuery) {
            NotificationChain msgs = null;
            if (rightQuery != null)
                msgs = ((InternalEObject)rightQuery).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, QueryExpressionBody.class, msgs);
            if (newRightQuery != null)
                msgs = ((InternalEObject)newRightQuery).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, QueryExpressionBody.class, msgs);
            msgs = basicSetRightQuery(newRightQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY, newRightQuery, newRightQuery));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY:
                if (leftQuery != null)
                    msgs = ((InternalEObject)leftQuery).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY, null, msgs);
                return basicSetLeftQuery((QueryExpressionBody)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY:
                if (rightQuery != null)
                    msgs = ((InternalEObject)rightQuery).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY, null, msgs);
                return basicSetRightQuery((QueryExpressionBody)otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY:
                return basicSetLeftQuery(null, msgs);
            case SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY:
                return basicSetRightQuery(null, msgs);
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
            case SQLQueryModelPackage.QUERY_COMBINED__COMBINED_OPERATOR:
                return getCombinedOperator();
            case SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY:
                return getLeftQuery();
            case SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY:
                return getRightQuery();
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
            case SQLQueryModelPackage.QUERY_COMBINED__COMBINED_OPERATOR:
                setCombinedOperator((QueryCombinedOperator)newValue);
                return;
            case SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY:
                setLeftQuery((QueryExpressionBody)newValue);
                return;
            case SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY:
                setRightQuery((QueryExpressionBody)newValue);
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
            case SQLQueryModelPackage.QUERY_COMBINED__COMBINED_OPERATOR:
                setCombinedOperator(COMBINED_OPERATOR_EDEFAULT);
                return;
            case SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY:
                setLeftQuery((QueryExpressionBody)null);
                return;
            case SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY:
                setRightQuery((QueryExpressionBody)null);
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
            case SQLQueryModelPackage.QUERY_COMBINED__COMBINED_OPERATOR:
                return combinedOperator != COMBINED_OPERATOR_EDEFAULT;
            case SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY:
                return leftQuery != null;
            case SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY:
                return rightQuery != null;
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

} //SQLQueryCombinedImpl
