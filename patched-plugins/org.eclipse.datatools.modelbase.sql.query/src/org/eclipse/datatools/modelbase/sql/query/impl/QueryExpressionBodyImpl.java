/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryExpressionBodyImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryNested;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression Body</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getRowFetchLimit <em>Row Fetch Limit</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getQueryExpression <em>Query Expression</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getCombinedLeft <em>Combined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getCombinedRight <em>Combined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getPredicateExists <em>Predicate Exists</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getUpdateSourceQuery <em>Update Source Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getWithTableSpecification <em>With Table Specification</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getQueryNest <em>Query Nest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl#getSortSpecList <em>Sort Spec List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QueryExpressionBodyImpl extends TableExpressionImpl implements QueryExpressionBody {
	/**
     * The default value of the '{@link #getRowFetchLimit() <em>Row Fetch Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRowFetchLimit()
     * @generated
     * @ordered
     */
    protected static final int ROW_FETCH_LIMIT_EDEFAULT = 0;
    /**
     * The cached value of the '{@link #getRowFetchLimit() <em>Row Fetch Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRowFetchLimit()
     * @generated
     * @ordered
     */
    protected int rowFetchLimit = ROW_FETCH_LIMIT_EDEFAULT;
    /**
     * The cached value of the '{@link #getSortSpecList() <em>Sort Spec List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSortSpecList()
     * @generated
     * @ordered
     */
    protected EList sortSpecList;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryExpressionBodyImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_EXPRESSION_BODY;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getRowFetchLimit() {
        return rowFetchLimit;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRowFetchLimit(int newRowFetchLimit) {
        int oldRowFetchLimit = rowFetchLimit;
        rowFetchLimit = newRowFetchLimit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT, oldRowFetchLimit, rowFetchLimit));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionRoot getQueryExpression() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION) return null;
        return (QueryExpressionRoot)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQueryExpression(QueryExpressionRoot newQueryExpression, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQueryExpression, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueryExpression(QueryExpressionRoot newQueryExpression) {
        if (newQueryExpression != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION && newQueryExpression != null)) {
            if (EcoreUtil.isAncestor(this, newQueryExpression))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQueryExpression != null)
                msgs = ((InternalEObject)newQueryExpression).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY, QueryExpressionRoot.class, msgs);
            msgs = basicSetQueryExpression(newQueryExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, newQueryExpression, newQueryExpression));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryCombined getCombinedLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT) return null;
        return (QueryCombined)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetCombinedLeft(QueryCombined newCombinedLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newCombinedLeft, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCombinedLeft(QueryCombined newCombinedLeft) {
        if (newCombinedLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT && newCombinedLeft != null)) {
            if (EcoreUtil.isAncestor(this, newCombinedLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newCombinedLeft != null)
                msgs = ((InternalEObject)newCombinedLeft).eInverseAdd(this, SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY, QueryCombined.class, msgs);
            msgs = basicSetCombinedLeft(newCombinedLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT, newCombinedLeft, newCombinedLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryCombined getCombinedRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT) return null;
        return (QueryCombined)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetCombinedRight(QueryCombined newCombinedRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newCombinedRight, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCombinedRight(QueryCombined newCombinedRight) {
        if (newCombinedRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT && newCombinedRight != null)) {
            if (EcoreUtil.isAncestor(this, newCombinedRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newCombinedRight != null)
                msgs = ((InternalEObject)newCombinedRight).eInverseAdd(this, SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY, QueryCombined.class, msgs);
            msgs = basicSetCombinedRight(newCombinedRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT, newCombinedRight, newCombinedRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateExists getPredicateExists() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS) return null;
        return (PredicateExists)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPredicateExists(PredicateExists newPredicateExists, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newPredicateExists, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPredicateExists(PredicateExists newPredicateExists) {
        if (newPredicateExists != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS && newPredicateExists != null)) {
            if (EcoreUtil.isAncestor(this, newPredicateExists))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newPredicateExists != null)
                msgs = ((InternalEObject)newPredicateExists).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_EXISTS__QUERY_EXPR, PredicateExists.class, msgs);
            msgs = basicSetPredicateExists(newPredicateExists, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS, newPredicateExists, newPredicateExists));
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public UpdateSourceQuery getUpdateSourceQuery() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY) return null;
        return (UpdateSourceQuery)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUpdateSourceQuery(UpdateSourceQuery newUpdateSourceQuery, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUpdateSourceQuery, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setUpdateSourceQuery(UpdateSourceQuery newUpdateSourceQuery) {
        if (newUpdateSourceQuery != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY && newUpdateSourceQuery != null)) {
            if (EcoreUtil.isAncestor(this, newUpdateSourceQuery))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUpdateSourceQuery != null)
                msgs = ((InternalEObject)newUpdateSourceQuery).eInverseAdd(this, SQLQueryModelPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR, UpdateSourceQuery.class, msgs);
            msgs = basicSetUpdateSourceQuery(newUpdateSourceQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY, newUpdateSourceQuery, newUpdateSourceQuery));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WithTableSpecification getWithTableSpecification() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION) return null;
        return (WithTableSpecification)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetWithTableSpecification(WithTableSpecification newWithTableSpecification, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newWithTableSpecification, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWithTableSpecification(WithTableSpecification newWithTableSpecification) {
        if (newWithTableSpecification != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION && newWithTableSpecification != null)) {
            if (EcoreUtil.isAncestor(this, newWithTableSpecification))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newWithTableSpecification != null)
                msgs = ((InternalEObject)newWithTableSpecification).eInverseAdd(this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, WithTableSpecification.class, msgs);
            msgs = basicSetWithTableSpecification(newWithTableSpecification, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, newWithTableSpecification, newWithTableSpecification));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryNested getQueryNest() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST) return null;
        return (QueryNested)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetQueryNest(QueryNested newQueryNest, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQueryNest, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueryNest(QueryNested newQueryNest) {
        if (newQueryNest != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST && newQueryNest != null)) {
            if (EcoreUtil.isAncestor(this, newQueryNest))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQueryNest != null)
                msgs = ((InternalEObject)newQueryNest).eInverseAdd(this, SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY, QueryNested.class, msgs);
            msgs = basicSetQueryNest(newQueryNest, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST, newQueryNest, newQueryNest));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSortSpecList() {
        if (sortSpecList == null) {
            sortSpecList = new EObjectContainmentWithInverseEList(OrderBySpecification.class, this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__SORT_SPEC_LIST, SQLQueryModelPackage.ORDER_BY_SPECIFICATION__QUERY);
        }
        return sortSpecList;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQueryExpression((QueryExpressionRoot)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetCombinedLeft((QueryCombined)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetCombinedRight((QueryCombined)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetPredicateExists((PredicateExists)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUpdateSourceQuery((UpdateSourceQuery)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetWithTableSpecification((WithTableSpecification)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQueryNest((QueryNested)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__SORT_SPEC_LIST:
                return ((InternalEList)getSortSpecList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
                return basicSetQueryExpression(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
                return basicSetCombinedLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
                return basicSetCombinedRight(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
                return basicSetPredicateExists(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
                return basicSetUpdateSourceQuery(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
                return basicSetWithTableSpecification(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST:
                return basicSetQueryNest(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__SORT_SPEC_LIST:
                return ((InternalEList)getSortSpecList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY, QueryExpressionRoot.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_COMBINED__LEFT_QUERY, QueryCombined.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_COMBINED__RIGHT_QUERY, QueryCombined.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_EXISTS__QUERY_EXPR, PredicateExists.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR, UpdateSourceQuery.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, WithTableSpecification.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_NESTED__NESTED_QUERY, QueryNested.class, msgs);
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT:
                return new Integer(getRowFetchLimit());
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
                return getQueryExpression();
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
                return getCombinedLeft();
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
                return getCombinedRight();
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
                return getPredicateExists();
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
                return getUpdateSourceQuery();
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
                return getWithTableSpecification();
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST:
                return getQueryNest();
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__SORT_SPEC_LIST:
                return getSortSpecList();
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT:
                setRowFetchLimit(((Integer)newValue).intValue());
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
                setQueryExpression((QueryExpressionRoot)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
                setCombinedLeft((QueryCombined)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
                setCombinedRight((QueryCombined)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
                setPredicateExists((PredicateExists)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
                setUpdateSourceQuery((UpdateSourceQuery)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
                setWithTableSpecification((WithTableSpecification)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST:
                setQueryNest((QueryNested)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__SORT_SPEC_LIST:
                getSortSpecList().clear();
                getSortSpecList().addAll((Collection)newValue);
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT:
                setRowFetchLimit(ROW_FETCH_LIMIT_EDEFAULT);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
                setQueryExpression((QueryExpressionRoot)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
                setCombinedLeft((QueryCombined)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
                setCombinedRight((QueryCombined)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
                setPredicateExists((PredicateExists)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
                setUpdateSourceQuery((UpdateSourceQuery)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
                setWithTableSpecification((WithTableSpecification)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST:
                setQueryNest((QueryNested)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__SORT_SPEC_LIST:
                getSortSpecList().clear();
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT:
                return rowFetchLimit != ROW_FETCH_LIMIT_EDEFAULT;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION:
                return getQueryExpression() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_LEFT:
                return getCombinedLeft() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__COMBINED_RIGHT:
                return getCombinedRight() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__PREDICATE_EXISTS:
                return getPredicateExists() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY:
                return getUpdateSourceQuery() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION:
                return getWithTableSpecification() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_NEST:
                return getQueryNest() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY__SORT_SPEC_LIST:
                return sortSpecList != null && !sortSpecList.isEmpty();
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
        result.append(" (rowFetchLimit: ");
        result.append(rowFetchLimit);
        result.append(')');
        return result.toString();
    }

} //SQLQueryExpressionBodyImpl
