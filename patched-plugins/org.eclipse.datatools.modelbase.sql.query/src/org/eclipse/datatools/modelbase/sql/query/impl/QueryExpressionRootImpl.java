/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryExpressionRootImpl.java,v 1.6 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getSelectStatement <em>Select Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getWithClause <em>With Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getQuery <em>Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getInValueRowSelectRight <em>In Value Row Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getInValueSelectRight <em>In Value Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getQuantifiedRowSelectRight <em>Quantified Row Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getQuantifiedValueSelectRight <em>Quantified Value Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl#getValExprScalarSelect <em>Val Expr Scalar Select</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryExpressionRootImpl extends SQLQueryObjectImpl implements QueryExpressionRoot {
	/**
     * The cached value of the '{@link #getWithClause() <em>With Clause</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWithClause()
     * @generated
     * @ordered
     */
    protected EList withClause;

	/**
     * The cached value of the '{@link #getQuery() <em>Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQuery()
     * @generated
     * @ordered
     */
    protected QueryExpressionBody query;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryExpressionRootImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_EXPRESSION_ROOT;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryInsertStatement getInsertStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT) return null;
        return (QueryInsertStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInsertStatement(QueryInsertStatement newInsertStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newInsertStatement, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInsertStatement(QueryInsertStatement newInsertStatement) {
        if (newInsertStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT && newInsertStatement != null)) {
            if (EcoreUtil.isAncestor(this, newInsertStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newInsertStatement != null)
                msgs = ((InternalEObject)newInsertStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, QueryInsertStatement.class, msgs);
            msgs = basicSetInsertStatement(newInsertStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT, newInsertStatement, newInsertStatement));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySelectStatement getSelectStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT) return null;
        return (QuerySelectStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSelectStatement(QuerySelectStatement newSelectStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSelectStatement, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSelectStatement(QuerySelectStatement newSelectStatement) {
        if (newSelectStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT && newSelectStatement != null)) {
            if (EcoreUtil.isAncestor(this, newSelectStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSelectStatement != null)
                msgs = ((InternalEObject)newSelectStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, QuerySelectStatement.class, msgs);
            msgs = basicSetSelectStatement(newSelectStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, newSelectStatement, newSelectStatement));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getWithClause() {
        if (withClause == null) {
            withClause = new EObjectContainmentWithInverseEList(WithTableSpecification.class, this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT);
        }
        return withClause;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionBody getQuery() {
        return query;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetQuery(QueryExpressionBody newQuery, NotificationChain msgs) {
        QueryExpressionBody oldQuery = query;
        query = newQuery;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY, oldQuery, newQuery);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuery(QueryExpressionBody newQuery) {
        if (newQuery != query) {
            NotificationChain msgs = null;
            if (query != null)
                msgs = ((InternalEObject)query).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, QueryExpressionBody.class, msgs);
            if (newQuery != null)
                msgs = ((InternalEObject)newQuery).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__QUERY_EXPRESSION, QueryExpressionBody.class, msgs);
            msgs = basicSetQuery(newQuery, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY, newQuery, newQuery));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateInValueRowSelect getInValueRowSelectRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT) return null;
        return (PredicateInValueRowSelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInValueRowSelectRight(PredicateInValueRowSelect newInValueRowSelectRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newInValueRowSelectRight, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInValueRowSelectRight(PredicateInValueRowSelect newInValueRowSelectRight) {
        if (newInValueRowSelectRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT && newInValueRowSelectRight != null)) {
            if (EcoreUtil.isAncestor(this, newInValueRowSelectRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newInValueRowSelectRight != null)
                msgs = ((InternalEObject)newInValueRowSelectRight).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR, PredicateInValueRowSelect.class, msgs);
            msgs = basicSetInValueRowSelectRight(newInValueRowSelectRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT, newInValueRowSelectRight, newInValueRowSelectRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateInValueSelect getInValueSelectRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT) return null;
        return (PredicateInValueSelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInValueSelectRight(PredicateInValueSelect newInValueSelectRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newInValueSelectRight, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInValueSelectRight(PredicateInValueSelect newInValueSelectRight) {
        if (newInValueSelectRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT && newInValueSelectRight != null)) {
            if (EcoreUtil.isAncestor(this, newInValueSelectRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newInValueSelectRight != null)
                msgs = ((InternalEObject)newInValueSelectRight).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_SELECT__QUERY_EXPR, PredicateInValueSelect.class, msgs);
            msgs = basicSetInValueSelectRight(newInValueSelectRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT, newInValueSelectRight, newInValueSelectRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateQuantifiedRowSelect getQuantifiedRowSelectRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT) return null;
        return (PredicateQuantifiedRowSelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQuantifiedRowSelectRight(PredicateQuantifiedRowSelect newQuantifiedRowSelectRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuantifiedRowSelectRight, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuantifiedRowSelectRight(PredicateQuantifiedRowSelect newQuantifiedRowSelectRight) {
        if (newQuantifiedRowSelectRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT && newQuantifiedRowSelectRight != null)) {
            if (EcoreUtil.isAncestor(this, newQuantifiedRowSelectRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuantifiedRowSelectRight != null)
                msgs = ((InternalEObject)newQuantifiedRowSelectRight).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR, PredicateQuantifiedRowSelect.class, msgs);
            msgs = basicSetQuantifiedRowSelectRight(newQuantifiedRowSelectRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT, newQuantifiedRowSelectRight, newQuantifiedRowSelectRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateQuantifiedValueSelect getQuantifiedValueSelectRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT) return null;
        return (PredicateQuantifiedValueSelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQuantifiedValueSelectRight(PredicateQuantifiedValueSelect newQuantifiedValueSelectRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuantifiedValueSelectRight, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuantifiedValueSelectRight(PredicateQuantifiedValueSelect newQuantifiedValueSelectRight) {
        if (newQuantifiedValueSelectRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT && newQuantifiedValueSelectRight != null)) {
            if (EcoreUtil.isAncestor(this, newQuantifiedValueSelectRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuantifiedValueSelectRight != null)
                msgs = ((InternalEObject)newQuantifiedValueSelectRight).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, PredicateQuantifiedValueSelect.class, msgs);
            msgs = basicSetQuantifiedValueSelectRight(newQuantifiedValueSelectRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, newQuantifiedValueSelectRight, newQuantifiedValueSelectRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionScalarSelect getValExprScalarSelect() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT) return null;
        return (ValueExpressionScalarSelect)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValExprScalarSelect(ValueExpressionScalarSelect newValExprScalarSelect, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValExprScalarSelect, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValExprScalarSelect(ValueExpressionScalarSelect newValExprScalarSelect) {
        if (newValExprScalarSelect != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT && newValExprScalarSelect != null)) {
            if (EcoreUtil.isAncestor(this, newValExprScalarSelect))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValExprScalarSelect != null)
                msgs = ((InternalEObject)newValExprScalarSelect).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_SCALAR_SELECT__QUERY_EXPR, ValueExpressionScalarSelect.class, msgs);
            msgs = basicSetValExprScalarSelect(newValExprScalarSelect, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT, newValExprScalarSelect, newValExprScalarSelect));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     * @deprecated the ValueExprScalarSelect object is now associated with QueryExpressionBody
     */
    public EList getValueExprScalarSelects() {
//        if (valueExprScalarSelects == null) {
//            valueExprScalarSelects = new EObjectWithInverseResolvingEList(ValueExpressionScalarSelect.class, this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VALUE_EXPR_SCALAR_SELECTS, SQLQueryModelPackage.VALUE_EXPRESSION_SCALAR_SELECT__QUERY_EXPR);
//        }
//        return valueExprScalarSelects;
        EList scalarSelectList = new BasicEList();
        ValueExpressionScalarSelect scalarSelect = getValExprScalarSelect();
        scalarSelectList.add(scalarSelect);
        return scalarSelectList;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetInsertStatement((QueryInsertStatement)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSelectStatement((QuerySelectStatement)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
                return ((InternalEList)getWithClause()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY:
                if (query != null)
                    msgs = ((InternalEObject)query).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY, null, msgs);
                return basicSetQuery((QueryExpressionBody)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetInValueRowSelectRight((PredicateInValueRowSelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetInValueSelectRight((PredicateInValueSelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuantifiedRowSelectRight((PredicateQuantifiedRowSelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuantifiedValueSelectRight((PredicateQuantifiedValueSelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValExprScalarSelect((ValueExpressionScalarSelect)otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
                return basicSetInsertStatement(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
                return basicSetSelectStatement(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
                return ((InternalEList)getWithClause()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY:
                return basicSetQuery(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
                return basicSetInValueRowSelectRight(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
                return basicSetInValueSelectRight(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
                return basicSetQuantifiedRowSelectRight(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
                return basicSetQuantifiedValueSelectRight(null, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT:
                return basicSetValExprScalarSelect(null, msgs);
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_INSERT_STATEMENT__SOURCE_QUERY, QueryInsertStatement.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, QuerySelectStatement.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR, PredicateInValueRowSelect.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_SELECT__QUERY_EXPR, PredicateInValueSelect.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR, PredicateQuantifiedRowSelect.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, PredicateQuantifiedValueSelect.class, msgs);
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_SCALAR_SELECT__QUERY_EXPR, ValueExpressionScalarSelect.class, msgs);
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
                return getInsertStatement();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
                return getSelectStatement();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
                return getWithClause();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY:
                return getQuery();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
                return getInValueRowSelectRight();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
                return getInValueSelectRight();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
                return getQuantifiedRowSelectRight();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
                return getQuantifiedValueSelectRight();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT:
                return getValExprScalarSelect();
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
                setInsertStatement((QueryInsertStatement)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
                setSelectStatement((QuerySelectStatement)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
                getWithClause().clear();
                getWithClause().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY:
                setQuery((QueryExpressionBody)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
                setInValueRowSelectRight((PredicateInValueRowSelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
                setInValueSelectRight((PredicateInValueSelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
                setQuantifiedRowSelectRight((PredicateQuantifiedRowSelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
                setQuantifiedValueSelectRight((PredicateQuantifiedValueSelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT:
                setValExprScalarSelect((ValueExpressionScalarSelect)newValue);
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
                setInsertStatement((QueryInsertStatement)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
                setSelectStatement((QuerySelectStatement)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
                getWithClause().clear();
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY:
                setQuery((QueryExpressionBody)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
                setInValueRowSelectRight((PredicateInValueRowSelect)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
                setInValueSelectRight((PredicateInValueSelect)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
                setQuantifiedRowSelectRight((PredicateQuantifiedRowSelect)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
                setQuantifiedValueSelectRight((PredicateQuantifiedValueSelect)null);
                return;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT:
                setValExprScalarSelect((ValueExpressionScalarSelect)null);
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
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__INSERT_STATEMENT:
                return getInsertStatement() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT:
                return getSelectStatement() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE:
                return withClause != null && !withClause.isEmpty();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUERY:
                return query != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT:
                return getInValueRowSelectRight() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT:
                return getInValueSelectRight() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT:
                return getQuantifiedRowSelectRight() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT:
                return getQuantifiedValueSelectRight() != null;
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT:
                return getValExprScalarSelect() != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getSQL() {
        return super.getSQL();
    }

} //SQLQueryExpressionImpl
