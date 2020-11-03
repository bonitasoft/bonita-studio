/**
 * </copyright>
 *
 * $Id: OrderBySpecificationImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.NullOrderingType;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
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
 * An implementation of the model object '<em><b>SQL Order By Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl#isDescending <em>Descending</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl#getOrderingSpecOption <em>Ordering Spec Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl#getNullOrderingOption <em>Null Ordering Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl#getSelectStatement <em>Select Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl#getQuery <em>Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OrderBySpecificationImpl extends SQLQueryObjectImpl implements OrderBySpecification {
	/**
     * The default value of the '{@link #isDescending() <em>Descending</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDescending()
     * @generated
     * @ordered
     */
    protected static final boolean DESCENDING_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isDescending() <em>Descending</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDescending()
     * @generated
     * @ordered
     */
    protected boolean descending = DESCENDING_EDEFAULT;

	/**
     * The default value of the '{@link #getOrderingSpecOption() <em>Ordering Spec Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrderingSpecOption()
     * @generated
     * @ordered
     */
    protected static final OrderingSpecType ORDERING_SPEC_OPTION_EDEFAULT = OrderingSpecType.NONE_LITERAL;

	/**
     * The cached value of the '{@link #getOrderingSpecOption() <em>Ordering Spec Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrderingSpecOption()
     * @generated
     * @ordered
     */
    protected OrderingSpecType orderingSpecOption = ORDERING_SPEC_OPTION_EDEFAULT;

	/**
     * The default value of the '{@link #getNullOrderingOption() <em>Null Ordering Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullOrderingOption()
     * @generated
     * @ordered
     */
    protected static final NullOrderingType NULL_ORDERING_OPTION_EDEFAULT = NullOrderingType.NONE_LITERAL;

	/**
     * The cached value of the '{@link #getNullOrderingOption() <em>Null Ordering Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNullOrderingOption()
     * @generated
     * @ordered
     */
    protected NullOrderingType nullOrderingOption = NULL_ORDERING_OPTION_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OrderBySpecificationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.ORDER_BY_SPECIFICATION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDescending() {
        return descending;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescending(boolean newDescending) {
        boolean oldDescending = descending;
        descending = newDescending;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING, oldDescending, descending));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrderingSpecType getOrderingSpecOption() {
        return orderingSpecOption;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOrderingSpecOption(OrderingSpecType newOrderingSpecOption) {
        OrderingSpecType oldOrderingSpecOption = orderingSpecOption;
        orderingSpecOption = newOrderingSpecOption == null ? ORDERING_SPEC_OPTION_EDEFAULT : newOrderingSpecOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION, oldOrderingSpecOption, orderingSpecOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NullOrderingType getNullOrderingOption() {
        return nullOrderingOption;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNullOrderingOption(NullOrderingType newNullOrderingOption) {
        NullOrderingType oldNullOrderingOption = nullOrderingOption;
        nullOrderingOption = newNullOrderingOption == null ? NULL_ORDERING_OPTION_EDEFAULT : newNullOrderingOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION, oldNullOrderingOption, nullOrderingOption));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySelectStatement getSelectStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT) return null;
        return (QuerySelectStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSelectStatement(QuerySelectStatement newSelectStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSelectStatement, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSelectStatement(QuerySelectStatement newSelectStatement) {
        if (newSelectStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT && newSelectStatement != null)) {
            if (EcoreUtil.isAncestor(this, newSelectStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSelectStatement != null)
                msgs = ((InternalEObject)newSelectStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE, QuerySelectStatement.class, msgs);
            msgs = basicSetSelectStatement(newSelectStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT, newSelectStatement, newSelectStatement));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionBody getQuery() {
        if (eContainerFeatureID() != SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY) return null;
        return (QueryExpressionBody)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetQuery(QueryExpressionBody newQuery, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuery, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuery(QueryExpressionBody newQuery) {
        if (newQuery != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY && newQuery != null)) {
            if (EcoreUtil.isAncestor(this, newQuery))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuery != null)
                msgs = ((InternalEObject)newQuery).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__SORT_SPEC_LIST, QueryExpressionBody.class, msgs);
            msgs = basicSetQuery(newQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY, newQuery, newQuery));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSelectStatement((QuerySelectStatement)otherEnd, msgs);
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuery((QueryExpressionBody)otherEnd, msgs);
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
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
                return basicSetSelectStatement(null, msgs);
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY:
                return basicSetQuery(null, msgs);
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
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE, QuerySelectStatement.class, msgs);
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__SORT_SPEC_LIST, QueryExpressionBody.class, msgs);
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
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING:
                return isDescending() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION:
                return getOrderingSpecOption();
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION:
                return getNullOrderingOption();
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
                return getSelectStatement();
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY:
                return getQuery();
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
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING:
                setDescending(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION:
                setOrderingSpecOption((OrderingSpecType)newValue);
                return;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION:
                setNullOrderingOption((NullOrderingType)newValue);
                return;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
                setSelectStatement((QuerySelectStatement)newValue);
                return;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY:
                setQuery((QueryExpressionBody)newValue);
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
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING:
                setDescending(DESCENDING_EDEFAULT);
                return;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION:
                setOrderingSpecOption(ORDERING_SPEC_OPTION_EDEFAULT);
                return;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION:
                setNullOrderingOption(NULL_ORDERING_OPTION_EDEFAULT);
                return;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
                setSelectStatement((QuerySelectStatement)null);
                return;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY:
                setQuery((QueryExpressionBody)null);
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
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__DESCENDING:
                return descending != DESCENDING_EDEFAULT;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION:
                return orderingSpecOption != ORDERING_SPEC_OPTION_EDEFAULT;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION:
                return nullOrderingOption != NULL_ORDERING_OPTION_EDEFAULT;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT:
                return getSelectStatement() != null;
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY:
                return getQuery() != null;
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
        result.append(" (descending: ");
        result.append(descending);
        result.append(", OrderingSpecOption: ");
        result.append(orderingSpecOption);
        result.append(", NullOrderingOption: ");
        result.append(nullOrderingOption);
        result.append(')');
        return result.toString();
    }

} //SQLOrderBySpecificationImpl
