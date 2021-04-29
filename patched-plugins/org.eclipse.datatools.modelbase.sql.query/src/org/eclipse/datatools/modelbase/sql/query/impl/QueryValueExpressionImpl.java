/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryValueExpressionImpl.java,v 1.8 2010/02/25 01:57:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
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
 * An implementation of the model object '<em><b>SQL Value Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getUnaryOperator <em>Unary Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValuesRow <em>Values Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getOrderByValueExpr <em>Order By Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getResultColumn <em>Result Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBasicRight <em>Basic Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBasicLeft <em>Basic Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getLikePattern <em>Like Pattern</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getLikeMatching <em>Like Matching</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getPredicateNull <em>Predicate Null</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getInValueListRight <em>In Value List Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getInValueListLeft <em>In Value List Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getInValueRowSelectLeft <em>In Value Row Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getInValueSelectLeft <em>In Value Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getQuantifiedRowSelectLeft <em>Quantified Row Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getQuantifiedValueSelectLeft <em>Quantified Value Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBetweenLeft <em>Between Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBetweenRight1 <em>Between Right1</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getBetweenRight2 <em>Between Right2</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCast <em>Value Expr Cast</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprFunction <em>Value Expr Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCombinedLeft <em>Value Expr Combined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCombinedRight <em>Value Expr Combined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getGroupingExpr <em>Grouping Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseElse <em>Value Expr Case Else</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseSimple <em>Value Expr Case Simple</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseSimpleContentWhen <em>Value Expr Case Simple Content When</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseSimpleContentResult <em>Value Expr Case Simple Content Result</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getLikeEscape <em>Like Escape</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprLabeledDuration <em>Value Expr Labeled Duration</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getNest <em>Nest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getUpdateSourceExprList <em>Update Source Expr List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getTableFunction <em>Table Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getValueExprRow <em>Value Expr Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl#getCallStatement <em>Call Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QueryValueExpressionImpl extends SQLQueryObjectImpl implements QueryValueExpression {
	/**
     * The default value of the '{@link #getUnaryOperator() <em>Unary Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnaryOperator()
     * @generated
     * @ordered
     */
    protected static final ValueExpressionUnaryOperator UNARY_OPERATOR_EDEFAULT = ValueExpressionUnaryOperator.NONE_LITERAL;

	/**
     * The cached value of the '{@link #getUnaryOperator() <em>Unary Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnaryOperator()
     * @generated
     * @ordered
     */
    protected ValueExpressionUnaryOperator unaryOperator = UNARY_OPERATOR_EDEFAULT;

	/**
     * The cached value of the '{@link #getDataType() <em>Data Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataType()
     * @generated
     * @ordered
     */
    protected DataType dataType;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryValueExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_VALUE_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionUnaryOperator getUnaryOperator() {
        return unaryOperator;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUnaryOperator(ValueExpressionUnaryOperator newUnaryOperator) {
        ValueExpressionUnaryOperator oldUnaryOperator = unaryOperator;
        unaryOperator = newUnaryOperator == null ? UNARY_OPERATOR_EDEFAULT : newUnaryOperator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR, oldUnaryOperator, unaryOperator));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataType getDataType() {
        return dataType;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataType(DataType newDataType, NotificationChain msgs) {
        DataType oldDataType = dataType;
        dataType = newDataType;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE, oldDataType, newDataType);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataType(DataType newDataType) {
        if (newDataType != dataType) {
            NotificationChain msgs = null;
            if (dataType != null)
                msgs = ((InternalEObject)dataType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE, null, msgs);
            if (newDataType != null)
                msgs = ((InternalEObject)newDataType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE, null, msgs);
            msgs = basicSetDataType(newDataType, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE, newDataType, newDataType));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValuesRow getValuesRow() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW) return null;
        return (ValuesRow)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValuesRow(ValuesRow newValuesRow, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValuesRow, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValuesRow(ValuesRow newValuesRow) {
        if (newValuesRow != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW && newValuesRow != null)) {
            if (EcoreUtil.isAncestor(this, newValuesRow))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValuesRow != null)
                msgs = ((InternalEObject)newValuesRow).eInverseAdd(this, SQLQueryModelPackage.VALUES_ROW__EXPR_LIST, ValuesRow.class, msgs);
            msgs = basicSetValuesRow(newValuesRow, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW, newValuesRow, newValuesRow));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrderByValueExpression getOrderByValueExpr() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR) return null;
        return (OrderByValueExpression)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetOrderByValueExpr(OrderByValueExpression newOrderByValueExpr, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newOrderByValueExpr, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOrderByValueExpr(OrderByValueExpression newOrderByValueExpr) {
        if (newOrderByValueExpr != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR && newOrderByValueExpr != null)) {
            if (EcoreUtil.isAncestor(this, newOrderByValueExpr))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newOrderByValueExpr != null)
                msgs = ((InternalEObject)newOrderByValueExpr).eInverseAdd(this, SQLQueryModelPackage.ORDER_BY_VALUE_EXPRESSION__VALUE_EXPR, OrderByValueExpression.class, msgs);
            msgs = basicSetOrderByValueExpr(newOrderByValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR, newOrderByValueExpr, newOrderByValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResultColumn getResultColumn() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN) return null;
        return (ResultColumn)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetResultColumn(ResultColumn newResultColumn, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newResultColumn, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResultColumn(ResultColumn newResultColumn) {
        if (newResultColumn != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN && newResultColumn != null)) {
            if (EcoreUtil.isAncestor(this, newResultColumn))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newResultColumn != null)
                msgs = ((InternalEObject)newResultColumn).eInverseAdd(this, SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR, ResultColumn.class, msgs);
            msgs = basicSetResultColumn(newResultColumn, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, newResultColumn, newResultColumn));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateBasic getBasicRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT) return null;
        return (PredicateBasic)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetBasicRight(PredicateBasic newBasicRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newBasicRight, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBasicRight(PredicateBasic newBasicRight) {
        if (newBasicRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT && newBasicRight != null)) {
            if (EcoreUtil.isAncestor(this, newBasicRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newBasicRight != null)
                msgs = ((InternalEObject)newBasicRight).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR, PredicateBasic.class, msgs);
            msgs = basicSetBasicRight(newBasicRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT, newBasicRight, newBasicRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateBasic getBasicLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT) return null;
        return (PredicateBasic)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetBasicLeft(PredicateBasic newBasicLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newBasicLeft, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBasicLeft(PredicateBasic newBasicLeft) {
        if (newBasicLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT && newBasicLeft != null)) {
            if (EcoreUtil.isAncestor(this, newBasicLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newBasicLeft != null)
                msgs = ((InternalEObject)newBasicLeft).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR, PredicateBasic.class, msgs);
            msgs = basicSetBasicLeft(newBasicLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT, newBasicLeft, newBasicLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateLike getLikePattern() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN) return null;
        return (PredicateLike)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetLikePattern(PredicateLike newLikePattern, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newLikePattern, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLikePattern(PredicateLike newLikePattern) {
        if (newLikePattern != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN && newLikePattern != null)) {
            if (EcoreUtil.isAncestor(this, newLikePattern))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newLikePattern != null)
                msgs = ((InternalEObject)newLikePattern).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, PredicateLike.class, msgs);
            msgs = basicSetLikePattern(newLikePattern, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, newLikePattern, newLikePattern));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateLike getLikeMatching() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING) return null;
        return (PredicateLike)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetLikeMatching(PredicateLike newLikeMatching, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newLikeMatching, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLikeMatching(PredicateLike newLikeMatching) {
        if (newLikeMatching != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING && newLikeMatching != null)) {
            if (EcoreUtil.isAncestor(this, newLikeMatching))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newLikeMatching != null)
                msgs = ((InternalEObject)newLikeMatching).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, PredicateLike.class, msgs);
            msgs = basicSetLikeMatching(newLikeMatching, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, newLikeMatching, newLikeMatching));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateIsNull getPredicateNull() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL) return null;
        return (PredicateIsNull)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPredicateNull(PredicateIsNull newPredicateNull, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newPredicateNull, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPredicateNull(PredicateIsNull newPredicateNull) {
        if (newPredicateNull != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL && newPredicateNull != null)) {
            if (EcoreUtil.isAncestor(this, newPredicateNull))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newPredicateNull != null)
                msgs = ((InternalEObject)newPredicateNull).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR, PredicateIsNull.class, msgs);
            msgs = basicSetPredicateNull(newPredicateNull, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL, newPredicateNull, newPredicateNull));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateInValueList getInValueListRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT) return null;
        return (PredicateInValueList)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInValueListRight(PredicateInValueList newInValueListRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newInValueListRight, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInValueListRight(PredicateInValueList newInValueListRight) {
        if (newInValueListRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT && newInValueListRight != null)) {
            if (EcoreUtil.isAncestor(this, newInValueListRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newInValueListRight != null)
                msgs = ((InternalEObject)newInValueListRight).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST, PredicateInValueList.class, msgs);
            msgs = basicSetInValueListRight(newInValueListRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT, newInValueListRight, newInValueListRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateInValueList getInValueListLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT) return null;
        return (PredicateInValueList)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInValueListLeft(PredicateInValueList newInValueListLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newInValueListLeft, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInValueListLeft(PredicateInValueList newInValueListLeft) {
        if (newInValueListLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT && newInValueListLeft != null)) {
            if (EcoreUtil.isAncestor(this, newInValueListLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newInValueListLeft != null)
                msgs = ((InternalEObject)newInValueListLeft).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR, PredicateInValueList.class, msgs);
            msgs = basicSetInValueListLeft(newInValueListLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT, newInValueListLeft, newInValueListLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateInValueRowSelect getInValueRowSelectLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT) return null;
        return (PredicateInValueRowSelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInValueRowSelectLeft(PredicateInValueRowSelect newInValueRowSelectLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newInValueRowSelectLeft, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInValueRowSelectLeft(PredicateInValueRowSelect newInValueRowSelectLeft) {
        if (newInValueRowSelectLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT && newInValueRowSelectLeft != null)) {
            if (EcoreUtil.isAncestor(this, newInValueRowSelectLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newInValueRowSelectLeft != null)
                msgs = ((InternalEObject)newInValueRowSelectLeft).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST, PredicateInValueRowSelect.class, msgs);
            msgs = basicSetInValueRowSelectLeft(newInValueRowSelectLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT, newInValueRowSelectLeft, newInValueRowSelectLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateInValueSelect getInValueSelectLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT) return null;
        return (PredicateInValueSelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInValueSelectLeft(PredicateInValueSelect newInValueSelectLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newInValueSelectLeft, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInValueSelectLeft(PredicateInValueSelect newInValueSelectLeft) {
        if (newInValueSelectLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT && newInValueSelectLeft != null)) {
            if (EcoreUtil.isAncestor(this, newInValueSelectLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newInValueSelectLeft != null)
                msgs = ((InternalEObject)newInValueSelectLeft).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_SELECT__VALUE_EXPR, PredicateInValueSelect.class, msgs);
            msgs = basicSetInValueSelectLeft(newInValueSelectLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT, newInValueSelectLeft, newInValueSelectLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateQuantifiedRowSelect getQuantifiedRowSelectLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT) return null;
        return (PredicateQuantifiedRowSelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQuantifiedRowSelectLeft(PredicateQuantifiedRowSelect newQuantifiedRowSelectLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuantifiedRowSelectLeft, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuantifiedRowSelectLeft(PredicateQuantifiedRowSelect newQuantifiedRowSelectLeft) {
        if (newQuantifiedRowSelectLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT && newQuantifiedRowSelectLeft != null)) {
            if (EcoreUtil.isAncestor(this, newQuantifiedRowSelectLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuantifiedRowSelectLeft != null)
                msgs = ((InternalEObject)newQuantifiedRowSelectLeft).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST, PredicateQuantifiedRowSelect.class, msgs);
            msgs = basicSetQuantifiedRowSelectLeft(newQuantifiedRowSelectLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT, newQuantifiedRowSelectLeft, newQuantifiedRowSelectLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateQuantifiedValueSelect getQuantifiedValueSelectLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT) return null;
        return (PredicateQuantifiedValueSelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQuantifiedValueSelectLeft(PredicateQuantifiedValueSelect newQuantifiedValueSelectLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuantifiedValueSelectLeft, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuantifiedValueSelectLeft(PredicateQuantifiedValueSelect newQuantifiedValueSelectLeft) {
        if (newQuantifiedValueSelectLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT && newQuantifiedValueSelectLeft != null)) {
            if (EcoreUtil.isAncestor(this, newQuantifiedValueSelectLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuantifiedValueSelectLeft != null)
                msgs = ((InternalEObject)newQuantifiedValueSelectLeft).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, PredicateQuantifiedValueSelect.class, msgs);
            msgs = basicSetQuantifiedValueSelectLeft(newQuantifiedValueSelectLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, newQuantifiedValueSelectLeft, newQuantifiedValueSelectLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateBetween getBetweenLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT) return null;
        return (PredicateBetween)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetBetweenLeft(PredicateBetween newBetweenLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newBetweenLeft, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBetweenLeft(PredicateBetween newBetweenLeft) {
        if (newBetweenLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT && newBetweenLeft != null)) {
            if (EcoreUtil.isAncestor(this, newBetweenLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newBetweenLeft != null)
                msgs = ((InternalEObject)newBetweenLeft).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, PredicateBetween.class, msgs);
            msgs = basicSetBetweenLeft(newBetweenLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, newBetweenLeft, newBetweenLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateBetween getBetweenRight1() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1) return null;
        return (PredicateBetween)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetBetweenRight1(PredicateBetween newBetweenRight1, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newBetweenRight1, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBetweenRight1(PredicateBetween newBetweenRight1) {
        if (newBetweenRight1 != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1 && newBetweenRight1 != null)) {
            if (EcoreUtil.isAncestor(this, newBetweenRight1))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newBetweenRight1 != null)
                msgs = ((InternalEObject)newBetweenRight1).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, PredicateBetween.class, msgs);
            msgs = basicSetBetweenRight1(newBetweenRight1, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, newBetweenRight1, newBetweenRight1));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateBetween getBetweenRight2() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2) return null;
        return (PredicateBetween)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetBetweenRight2(PredicateBetween newBetweenRight2, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newBetweenRight2, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBetweenRight2(PredicateBetween newBetweenRight2) {
        if (newBetweenRight2 != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2 && newBetweenRight2 != null)) {
            if (EcoreUtil.isAncestor(this, newBetweenRight2))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newBetweenRight2 != null)
                msgs = ((InternalEObject)newBetweenRight2).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, PredicateBetween.class, msgs);
            msgs = basicSetBetweenRight2(newBetweenRight2, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, newBetweenRight2, newBetweenRight2));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCast getValueExprCast() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST) return null;
        return (ValueExpressionCast)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCast(ValueExpressionCast newValueExprCast, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCast, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCast(ValueExpressionCast newValueExprCast) {
        if (newValueExprCast != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST && newValueExprCast != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCast))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCast != null)
                msgs = ((InternalEObject)newValueExprCast).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR, ValueExpressionCast.class, msgs);
            msgs = basicSetValueExprCast(newValueExprCast, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST, newValueExprCast, newValueExprCast));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionFunction getValueExprFunction() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION) return null;
        return (ValueExpressionFunction)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprFunction(ValueExpressionFunction newValueExprFunction, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprFunction, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprFunction(ValueExpressionFunction newValueExprFunction) {
        if (newValueExprFunction != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION && newValueExprFunction != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprFunction))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprFunction != null)
                msgs = ((InternalEObject)newValueExprFunction).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST, ValueExpressionFunction.class, msgs);
            msgs = basicSetValueExprFunction(newValueExprFunction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION, newValueExprFunction, newValueExprFunction));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCombined getValueExprCombinedLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT) return null;
        return (ValueExpressionCombined)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCombinedLeft(ValueExpressionCombined newValueExprCombinedLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCombinedLeft, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCombinedLeft(ValueExpressionCombined newValueExprCombinedLeft) {
        if (newValueExprCombinedLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT && newValueExprCombinedLeft != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCombinedLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCombinedLeft != null)
                msgs = ((InternalEObject)newValueExprCombinedLeft).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
            msgs = basicSetValueExprCombinedLeft(newValueExprCombinedLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT, newValueExprCombinedLeft, newValueExprCombinedLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCombined getValueExprCombinedRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT) return null;
        return (ValueExpressionCombined)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCombinedRight(ValueExpressionCombined newValueExprCombinedRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCombinedRight, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCombinedRight(ValueExpressionCombined newValueExprCombinedRight) {
        if (newValueExprCombinedRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT && newValueExprCombinedRight != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCombinedRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCombinedRight != null)
                msgs = ((InternalEObject)newValueExprCombinedRight).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
            msgs = basicSetValueExprCombinedRight(newValueExprCombinedRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT, newValueExprCombinedRight, newValueExprCombinedRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GroupingExpression getGroupingExpr() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR) return null;
        return (GroupingExpression)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetGroupingExpr(GroupingExpression newGroupingExpr, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newGroupingExpr, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroupingExpr(GroupingExpression newGroupingExpr) {
        if (newGroupingExpr != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR && newGroupingExpr != null)) {
            if (EcoreUtil.isAncestor(this, newGroupingExpr))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGroupingExpr != null)
                msgs = ((InternalEObject)newGroupingExpr).eInverseAdd(this, SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR, GroupingExpression.class, msgs);
            msgs = basicSetGroupingExpr(newGroupingExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR, newGroupingExpr, newGroupingExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCaseElse getValueExprCaseElse() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE) return null;
        return (ValueExpressionCaseElse)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCaseElse(ValueExpressionCaseElse newValueExprCaseElse, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCaseElse, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCaseElse(ValueExpressionCaseElse newValueExprCaseElse) {
        if (newValueExprCaseElse != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE && newValueExprCaseElse != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCaseElse))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCaseElse != null)
                msgs = ((InternalEObject)newValueExprCaseElse).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR, ValueExpressionCaseElse.class, msgs);
            msgs = basicSetValueExprCaseElse(newValueExprCaseElse, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE, newValueExprCaseElse, newValueExprCaseElse));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCaseSimple getValueExprCaseSimple() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE) return null;
        return (ValueExpressionCaseSimple)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCaseSimple(ValueExpressionCaseSimple newValueExprCaseSimple, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSimple, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCaseSimple(ValueExpressionCaseSimple newValueExprCaseSimple) {
        if (newValueExprCaseSimple != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE && newValueExprCaseSimple != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCaseSimple))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCaseSimple != null)
                msgs = ((InternalEObject)newValueExprCaseSimple).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR, ValueExpressionCaseSimple.class, msgs);
            msgs = basicSetValueExprCaseSimple(newValueExprCaseSimple, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE, newValueExprCaseSimple, newValueExprCaseSimple));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCaseSimpleContent getValueExprCaseSimpleContentWhen() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN) return null;
        return (ValueExpressionCaseSimpleContent)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCaseSimpleContentWhen(ValueExpressionCaseSimpleContent newValueExprCaseSimpleContentWhen, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSimpleContentWhen, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCaseSimpleContentWhen(ValueExpressionCaseSimpleContent newValueExprCaseSimpleContentWhen) {
        if (newValueExprCaseSimpleContentWhen != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN && newValueExprCaseSimpleContentWhen != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCaseSimpleContentWhen))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCaseSimpleContentWhen != null)
                msgs = ((InternalEObject)newValueExprCaseSimpleContentWhen).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
            msgs = basicSetValueExprCaseSimpleContentWhen(newValueExprCaseSimpleContentWhen, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, newValueExprCaseSimpleContentWhen, newValueExprCaseSimpleContentWhen));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCaseSimpleContent getValueExprCaseSimpleContentResult() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT) return null;
        return (ValueExpressionCaseSimpleContent)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCaseSimpleContentResult(ValueExpressionCaseSimpleContent newValueExprCaseSimpleContentResult, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSimpleContentResult, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCaseSimpleContentResult(ValueExpressionCaseSimpleContent newValueExprCaseSimpleContentResult) {
        if (newValueExprCaseSimpleContentResult != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT && newValueExprCaseSimpleContentResult != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCaseSimpleContentResult))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCaseSimpleContentResult != null)
                msgs = ((InternalEObject)newValueExprCaseSimpleContentResult).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
            msgs = basicSetValueExprCaseSimpleContentResult(newValueExprCaseSimpleContentResult, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, newValueExprCaseSimpleContentResult, newValueExprCaseSimpleContentResult));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCaseSearchContent getValueExprCaseSearchContent() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT) return null;
        return (ValueExpressionCaseSearchContent)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCaseSearchContent(ValueExpressionCaseSearchContent newValueExprCaseSearchContent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSearchContent, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCaseSearchContent(ValueExpressionCaseSearchContent newValueExprCaseSearchContent) {
        if (newValueExprCaseSearchContent != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT && newValueExprCaseSearchContent != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCaseSearchContent))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCaseSearchContent != null)
                msgs = ((InternalEObject)newValueExprCaseSearchContent).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR, ValueExpressionCaseSearchContent.class, msgs);
            msgs = basicSetValueExprCaseSearchContent(newValueExprCaseSearchContent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT, newValueExprCaseSearchContent, newValueExprCaseSearchContent));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PredicateLike getLikeEscape() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE) return null;
        return (PredicateLike)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetLikeEscape(PredicateLike newLikeEscape, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newLikeEscape, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLikeEscape(PredicateLike newLikeEscape) {
        if (newLikeEscape != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE && newLikeEscape != null)) {
            if (EcoreUtil.isAncestor(this, newLikeEscape))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newLikeEscape != null)
                msgs = ((InternalEObject)newLikeEscape).eInverseAdd(this, SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, PredicateLike.class, msgs);
            msgs = basicSetLikeEscape(newLikeEscape, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, newLikeEscape, newLikeEscape));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionLabeledDuration getValueExprLabeledDuration() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION) return null;
        return (ValueExpressionLabeledDuration)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprLabeledDuration(ValueExpressionLabeledDuration newValueExprLabeledDuration, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprLabeledDuration, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprLabeledDuration(ValueExpressionLabeledDuration newValueExprLabeledDuration) {
        if (newValueExprLabeledDuration != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION && newValueExprLabeledDuration != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprLabeledDuration))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprLabeledDuration != null)
                msgs = ((InternalEObject)newValueExprLabeledDuration).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR, ValueExpressionLabeledDuration.class, msgs);
            msgs = basicSetValueExprLabeledDuration(newValueExprLabeledDuration, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION, newValueExprLabeledDuration, newValueExprLabeledDuration));
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ValueExpressionNested getNest() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST) return null;
        return (ValueExpressionNested)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetNest(ValueExpressionNested newNest, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newNest, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setNest(ValueExpressionNested newNest) {
        if (newNest != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST && newNest != null)) {
            if (EcoreUtil.isAncestor(this, newNest))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newNest != null)
                msgs = ((InternalEObject)newNest).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, ValueExpressionNested.class, msgs);
            msgs = basicSetNest(newNest, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST, newNest, newNest));
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public UpdateSourceExprList getUpdateSourceExprList() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST) return null;
        return (UpdateSourceExprList)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUpdateSourceExprList(UpdateSourceExprList newUpdateSourceExprList, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUpdateSourceExprList, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setUpdateSourceExprList(UpdateSourceExprList newUpdateSourceExprList) {
        if (newUpdateSourceExprList != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST && newUpdateSourceExprList != null)) {
            if (EcoreUtil.isAncestor(this, newUpdateSourceExprList))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUpdateSourceExprList != null)
                msgs = ((InternalEObject)newUpdateSourceExprList).eInverseAdd(this, SQLQueryModelPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST, UpdateSourceExprList.class, msgs);
            msgs = basicSetUpdateSourceExprList(newUpdateSourceExprList, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST, newUpdateSourceExprList, newUpdateSourceExprList));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableFunction getTableFunction() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION) return null;
        return (TableFunction)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTableFunction(TableFunction newTableFunction, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTableFunction, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableFunction(TableFunction newTableFunction) {
        if (newTableFunction != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION && newTableFunction != null)) {
            if (EcoreUtil.isAncestor(this, newTableFunction))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTableFunction != null)
                msgs = ((InternalEObject)newTableFunction).eInverseAdd(this, SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST, TableFunction.class, msgs);
            msgs = basicSetTableFunction(newTableFunction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION, newTableFunction, newTableFunction));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionRow getValueExprRow() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW) return null;
        return (ValueExpressionRow)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValueExprRow(ValueExpressionRow newValueExprRow, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprRow, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprRow(ValueExpressionRow newValueExprRow) {
        if (newValueExprRow != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW && newValueExprRow != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprRow))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprRow != null)
                msgs = ((InternalEObject)newValueExprRow).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_ROW__VALUE_EXPR_LIST, ValueExpressionRow.class, msgs);
            msgs = basicSetValueExprRow(newValueExprRow, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW, newValueExprRow, newValueExprRow));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallStatement getCallStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT) return null;
        return (CallStatement)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCallStatement(CallStatement newCallStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newCallStatement, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCallStatement(CallStatement newCallStatement) {
        if (newCallStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT && newCallStatement != null)) {
            if (EcoreUtil.isAncestor(this, newCallStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newCallStatement != null)
                msgs = ((InternalEObject)newCallStatement).eInverseAdd(this, SQLQueryModelPackage.CALL_STATEMENT__ARGUMENT_LIST, CallStatement.class, msgs);
            msgs = basicSetCallStatement(newCallStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT, newCallStatement, newCallStatement));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValuesRow((ValuesRow)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetOrderByValueExpr((OrderByValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetResultColumn((ResultColumn)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetBasicRight((PredicateBasic)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetBasicLeft((PredicateBasic)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetLikePattern((PredicateLike)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetLikeMatching((PredicateLike)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetPredicateNull((PredicateIsNull)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetInValueListRight((PredicateInValueList)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetInValueListLeft((PredicateInValueList)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetInValueRowSelectLeft((PredicateInValueRowSelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetInValueSelectLeft((PredicateInValueSelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetBetweenLeft((PredicateBetween)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetBetweenRight1((PredicateBetween)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetBetweenRight2((PredicateBetween)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCast((ValueExpressionCast)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprFunction((ValueExpressionFunction)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCombinedLeft((ValueExpressionCombined)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCombinedRight((ValueExpressionCombined)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetGroupingExpr((GroupingExpression)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCaseElse((ValueExpressionCaseElse)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCaseSimple((ValueExpressionCaseSimple)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCaseSearchContent((ValueExpressionCaseSearchContent)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetLikeEscape((PredicateLike)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprLabeledDuration((ValueExpressionLabeledDuration)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetNest((ValueExpressionNested)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUpdateSourceExprList((UpdateSourceExprList)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTableFunction((TableFunction)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprRow((ValueExpressionRow)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetCallStatement((CallStatement)otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
                return basicSetDataType(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
                return basicSetValuesRow(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
                return basicSetOrderByValueExpr(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
                return basicSetResultColumn(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
                return basicSetBasicRight(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
                return basicSetBasicLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
                return basicSetLikePattern(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
                return basicSetLikeMatching(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
                return basicSetPredicateNull(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
                return basicSetInValueListRight(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
                return basicSetInValueListLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
                return basicSetInValueRowSelectLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
                return basicSetInValueSelectLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
                return basicSetQuantifiedRowSelectLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
                return basicSetQuantifiedValueSelectLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
                return basicSetBetweenLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
                return basicSetBetweenRight1(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
                return basicSetBetweenRight2(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
                return basicSetValueExprCast(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
                return basicSetValueExprFunction(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
                return basicSetValueExprCombinedLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
                return basicSetValueExprCombinedRight(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
                return basicSetGroupingExpr(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
                return basicSetValueExprCaseElse(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
                return basicSetValueExprCaseSimple(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
                return basicSetValueExprCaseSimpleContentWhen(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
                return basicSetValueExprCaseSimpleContentResult(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                return basicSetValueExprCaseSearchContent(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
                return basicSetLikeEscape(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
                return basicSetValueExprLabeledDuration(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST:
                return basicSetNest(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
                return basicSetUpdateSourceExprList(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION:
                return basicSetTableFunction(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW:
                return basicSetValueExprRow(null, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT:
                return basicSetCallStatement(null, msgs);
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
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUES_ROW__EXPR_LIST, ValuesRow.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.ORDER_BY_VALUE_EXPRESSION__VALUE_EXPR, OrderByValueExpression.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.RESULT_COLUMN__VALUE_EXPR, ResultColumn.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BASIC__RIGHT_VALUE_EXPR, PredicateBasic.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BASIC__LEFT_VALUE_EXPR, PredicateBasic.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, PredicateLike.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, PredicateLike.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IS_NULL__VALUE_EXPR, PredicateIsNull.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST, PredicateInValueList.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_LIST__VALUE_EXPR, PredicateInValueList.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST, PredicateInValueRowSelect.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_IN_VALUE_SELECT__VALUE_EXPR, PredicateInValueSelect.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST, PredicateQuantifiedRowSelect.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, PredicateQuantifiedValueSelect.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, PredicateBetween.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, PredicateBetween.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, PredicateBetween.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR, ValueExpressionCast.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST, ValueExpressionFunction.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR, ValueExpressionCombined.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.GROUPING_EXPRESSION__VALUE_EXPR, GroupingExpression.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR, ValueExpressionCaseElse.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR, ValueExpressionCaseSimple.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR, ValueExpressionCaseSimpleContent.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR, ValueExpressionCaseSearchContent.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, PredicateLike.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR, ValueExpressionLabeledDuration.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR, ValueExpressionNested.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST, UpdateSourceExprList.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST, TableFunction.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_ROW__VALUE_EXPR_LIST, ValueExpressionRow.class, msgs);
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.CALL_STATEMENT__ARGUMENT_LIST, CallStatement.class, msgs);
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
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR:
                return getUnaryOperator();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
                return getDataType();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
                return getValuesRow();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
                return getOrderByValueExpr();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
                return getResultColumn();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
                return getBasicRight();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
                return getBasicLeft();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
                return getLikePattern();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
                return getLikeMatching();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
                return getPredicateNull();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
                return getInValueListRight();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
                return getInValueListLeft();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
                return getInValueRowSelectLeft();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
                return getInValueSelectLeft();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
                return getQuantifiedRowSelectLeft();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
                return getQuantifiedValueSelectLeft();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
                return getBetweenLeft();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
                return getBetweenRight1();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
                return getBetweenRight2();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
                return getValueExprCast();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
                return getValueExprFunction();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
                return getValueExprCombinedLeft();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
                return getValueExprCombinedRight();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
                return getGroupingExpr();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
                return getValueExprCaseElse();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
                return getValueExprCaseSimple();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
                return getValueExprCaseSimpleContentWhen();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
                return getValueExprCaseSimpleContentResult();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                return getValueExprCaseSearchContent();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
                return getLikeEscape();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
                return getValueExprLabeledDuration();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST:
                return getNest();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
                return getUpdateSourceExprList();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION:
                return getTableFunction();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW:
                return getValueExprRow();
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT:
                return getCallStatement();
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
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR:
                setUnaryOperator((ValueExpressionUnaryOperator)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
                setDataType((DataType)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
                setValuesRow((ValuesRow)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
                setOrderByValueExpr((OrderByValueExpression)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
                setResultColumn((ResultColumn)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
                setBasicRight((PredicateBasic)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
                setBasicLeft((PredicateBasic)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
                setLikePattern((PredicateLike)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
                setLikeMatching((PredicateLike)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
                setPredicateNull((PredicateIsNull)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
                setInValueListRight((PredicateInValueList)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
                setInValueListLeft((PredicateInValueList)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
                setInValueRowSelectLeft((PredicateInValueRowSelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
                setInValueSelectLeft((PredicateInValueSelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
                setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
                setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
                setBetweenLeft((PredicateBetween)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
                setBetweenRight1((PredicateBetween)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
                setBetweenRight2((PredicateBetween)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
                setValueExprCast((ValueExpressionCast)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
                setValueExprFunction((ValueExpressionFunction)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
                setValueExprCombinedLeft((ValueExpressionCombined)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
                setValueExprCombinedRight((ValueExpressionCombined)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
                setGroupingExpr((GroupingExpression)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
                setValueExprCaseElse((ValueExpressionCaseElse)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
                setValueExprCaseSimple((ValueExpressionCaseSimple)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
                setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
                setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
                setLikeEscape((PredicateLike)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
                setValueExprLabeledDuration((ValueExpressionLabeledDuration)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST:
                setNest((ValueExpressionNested)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
                setUpdateSourceExprList((UpdateSourceExprList)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION:
                setTableFunction((TableFunction)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW:
                setValueExprRow((ValueExpressionRow)newValue);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT:
                setCallStatement((CallStatement)newValue);
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
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR:
                setUnaryOperator(UNARY_OPERATOR_EDEFAULT);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
                setDataType((DataType)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
                setValuesRow((ValuesRow)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
                setOrderByValueExpr((OrderByValueExpression)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
                setResultColumn((ResultColumn)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
                setBasicRight((PredicateBasic)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
                setBasicLeft((PredicateBasic)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
                setLikePattern((PredicateLike)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
                setLikeMatching((PredicateLike)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
                setPredicateNull((PredicateIsNull)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
                setInValueListRight((PredicateInValueList)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
                setInValueListLeft((PredicateInValueList)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
                setInValueRowSelectLeft((PredicateInValueRowSelect)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
                setInValueSelectLeft((PredicateInValueSelect)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
                setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
                setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
                setBetweenLeft((PredicateBetween)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
                setBetweenRight1((PredicateBetween)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
                setBetweenRight2((PredicateBetween)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
                setValueExprCast((ValueExpressionCast)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
                setValueExprFunction((ValueExpressionFunction)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
                setValueExprCombinedLeft((ValueExpressionCombined)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
                setValueExprCombinedRight((ValueExpressionCombined)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
                setGroupingExpr((GroupingExpression)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
                setValueExprCaseElse((ValueExpressionCaseElse)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
                setValueExprCaseSimple((ValueExpressionCaseSimple)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
                setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
                setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
                setLikeEscape((PredicateLike)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
                setValueExprLabeledDuration((ValueExpressionLabeledDuration)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST:
                setNest((ValueExpressionNested)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
                setUpdateSourceExprList((UpdateSourceExprList)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION:
                setTableFunction((TableFunction)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW:
                setValueExprRow((ValueExpressionRow)null);
                return;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT:
                setCallStatement((CallStatement)null);
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
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR:
                return unaryOperator != UNARY_OPERATOR_EDEFAULT;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE:
                return dataType != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW:
                return getValuesRow() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR:
                return getOrderByValueExpr() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN:
                return getResultColumn() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT:
                return getBasicRight() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT:
                return getBasicLeft() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN:
                return getLikePattern() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING:
                return getLikeMatching() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL:
                return getPredicateNull() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT:
                return getInValueListRight() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT:
                return getInValueListLeft() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT:
                return getInValueRowSelectLeft() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT:
                return getInValueSelectLeft() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT:
                return getQuantifiedRowSelectLeft() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT:
                return getQuantifiedValueSelectLeft() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT:
                return getBetweenLeft() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1:
                return getBetweenRight1() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2:
                return getBetweenRight2() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST:
                return getValueExprCast() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION:
                return getValueExprFunction() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT:
                return getValueExprCombinedLeft() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT:
                return getValueExprCombinedRight() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR:
                return getGroupingExpr() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE:
                return getValueExprCaseElse() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE:
                return getValueExprCaseSimple() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
                return getValueExprCaseSimpleContentWhen() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
                return getValueExprCaseSimpleContentResult() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                return getValueExprCaseSearchContent() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE:
                return getLikeEscape() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION:
                return getValueExprLabeledDuration() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST:
                return getNest() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST:
                return getUpdateSourceExprList() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION:
                return getTableFunction() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW:
                return getValueExprRow() != null;
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT:
                return getCallStatement() != null;
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

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (unaryOperator: ");
        result.append(unaryOperator);
        result.append(')');
        return result.toString();
    }

} //SQLValueExpressionImpl
