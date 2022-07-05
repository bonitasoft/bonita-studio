/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryValueExpression.java,v 1.4 2008/07/07 19:53:17 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


import org.eclipse.datatools.modelbase.sql.datatypes.DataType;

import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUnaryOperator <em>Unary Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValuesRow <em>Values Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getOrderByValueExpr <em>Order By Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getResultColumn <em>Result Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicRight <em>Basic Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicLeft <em>Basic Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikePattern <em>Like Pattern</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeMatching <em>Like Matching</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getPredicateNull <em>Predicate Null</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListRight <em>In Value List Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListLeft <em>In Value List Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueRowSelectLeft <em>In Value Row Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueSelectLeft <em>In Value Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedRowSelectLeft <em>Quantified Row Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedValueSelectLeft <em>Quantified Value Select Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenLeft <em>Between Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight1 <em>Between Right1</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight2 <em>Between Right2</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCast <em>Value Expr Cast</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprFunction <em>Value Expr Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedLeft <em>Value Expr Combined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedRight <em>Value Expr Combined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getGroupingExpr <em>Grouping Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseElse <em>Value Expr Case Else</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimple <em>Value Expr Case Simple</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentWhen <em>Value Expr Case Simple Content When</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentResult <em>Value Expr Case Simple Content Result</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeEscape <em>Like Escape</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprLabeledDuration <em>Value Expr Labeled Duration</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getNest <em>Nest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUpdateSourceExprList <em>Update Source Expr List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getTableFunction <em>Table Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprRow <em>Value Expr Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getCallStatement <em>Call Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression()
 * @model abstract="true"
 * @generated
 */
public interface QueryValueExpression extends SQLQueryObject, ValueExpression{
	/**
     * Returns the value of the '<em><b>Unary Operator</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Unary Operator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Unary Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator
     * @see #setUnaryOperator(ValueExpressionUnaryOperator)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_UnaryOperator()
     * @model
     * @generated
     */
    ValueExpressionUnaryOperator getUnaryOperator();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUnaryOperator <em>Unary Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unary Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator
     * @see #getUnaryOperator()
     * @generated
     */
    void setUnaryOperator(ValueExpressionUnaryOperator value);

	/**
     * Returns the value of the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Type</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Type</em>' containment reference.
     * @see #setDataType(DataType)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_DataType()
     * @model containment="true"
     * @generated
     */
    DataType getDataType();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getDataType <em>Data Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Type</em>' containment reference.
     * @see #getDataType()
     * @generated
     */
    void setDataType(DataType value);

	/**
     * Returns the value of the '<em><b>Values Row</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getExprList <em>Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Values Row</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Values Row</em>' container reference.
     * @see #setValuesRow(ValuesRow)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValuesRow()
     * @see org.eclipse.datatools.modelbase.sql.query.ValuesRow#getExprList
     * @model opposite="exprList"
     * @generated
     */
    ValuesRow getValuesRow();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValuesRow <em>Values Row</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Values Row</em>' container reference.
     * @see #getValuesRow()
     * @generated
     */
    void setValuesRow(ValuesRow value);

	/**
     * Returns the value of the '<em><b>Order By Value Expr</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Order By Value Expr</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Order By Value Expr</em>' container reference.
     * @see #setOrderByValueExpr(OrderByValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_OrderByValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    OrderByValueExpression getOrderByValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getOrderByValueExpr <em>Order By Value Expr</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Order By Value Expr</em>' container reference.
     * @see #getOrderByValueExpr()
     * @generated
     */
    void setOrderByValueExpr(OrderByValueExpression value);

	/**
     * Returns the value of the '<em><b>Result Column</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ResultColumn#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Result Column</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Result Column</em>' container reference.
     * @see #setResultColumn(ResultColumn)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ResultColumn()
     * @see org.eclipse.datatools.modelbase.sql.query.ResultColumn#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    ResultColumn getResultColumn();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getResultColumn <em>Result Column</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Result Column</em>' container reference.
     * @see #getResultColumn()
     * @generated
     */
    void setResultColumn(ResultColumn value);

	/**
     * Returns the value of the '<em><b>Basic Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getRightValueExpr <em>Right Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Basic Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Basic Right</em>' container reference.
     * @see #setBasicRight(PredicateBasic)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_BasicRight()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getRightValueExpr
     * @model opposite="rightValueExpr"
     * @generated
     */
    PredicateBasic getBasicRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicRight <em>Basic Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Basic Right</em>' container reference.
     * @see #getBasicRight()
     * @generated
     */
    void setBasicRight(PredicateBasic value);

	/**
     * Returns the value of the '<em><b>Basic Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getLeftValueExpr <em>Left Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Basic Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Basic Left</em>' container reference.
     * @see #setBasicLeft(PredicateBasic)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_BasicLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getLeftValueExpr
     * @model opposite="leftValueExpr"
     * @generated
     */
    PredicateBasic getBasicLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicLeft <em>Basic Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Basic Left</em>' container reference.
     * @see #getBasicLeft()
     * @generated
     */
    void setBasicLeft(PredicateBasic value);

	/**
     * Returns the value of the '<em><b>Like Pattern</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getPatternValueExpr <em>Pattern Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Like Pattern</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Like Pattern</em>' container reference.
     * @see #setLikePattern(PredicateLike)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_LikePattern()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike#getPatternValueExpr
     * @model opposite="patternValueExpr"
     * @generated
     */
    PredicateLike getLikePattern();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikePattern <em>Like Pattern</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Like Pattern</em>' container reference.
     * @see #getLikePattern()
     * @generated
     */
    void setLikePattern(PredicateLike value);

	/**
     * Returns the value of the '<em><b>Like Matching</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getMatchingValueExpr <em>Matching Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Like Matching</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Like Matching</em>' container reference.
     * @see #setLikeMatching(PredicateLike)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_LikeMatching()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike#getMatchingValueExpr
     * @model opposite="matchingValueExpr"
     * @generated
     */
    PredicateLike getLikeMatching();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeMatching <em>Like Matching</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Like Matching</em>' container reference.
     * @see #getLikeMatching()
     * @generated
     */
    void setLikeMatching(PredicateLike value);

	/**
     * Returns the value of the '<em><b>Predicate Null</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predicate Null</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Predicate Null</em>' container reference.
     * @see #setPredicateNull(PredicateIsNull)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_PredicateNull()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    PredicateIsNull getPredicateNull();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getPredicateNull <em>Predicate Null</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Predicate Null</em>' container reference.
     * @see #getPredicateNull()
     * @generated
     */
    void setPredicateNull(PredicateIsNull value);

	/**
     * Returns the value of the '<em><b>In Value List Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In Value List Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>In Value List Right</em>' container reference.
     * @see #setInValueListRight(PredicateInValueList)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_InValueListRight()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExprList
     * @model opposite="valueExprList"
     * @generated
     */
    PredicateInValueList getInValueListRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListRight <em>In Value List Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>In Value List Right</em>' container reference.
     * @see #getInValueListRight()
     * @generated
     */
    void setInValueListRight(PredicateInValueList value);

	/**
     * Returns the value of the '<em><b>In Value List Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In Value List Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>In Value List Left</em>' container reference.
     * @see #setInValueListLeft(PredicateInValueList)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_InValueListLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    PredicateInValueList getInValueListLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListLeft <em>In Value List Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>In Value List Left</em>' container reference.
     * @see #getInValueListLeft()
     * @generated
     */
    void setInValueListLeft(PredicateInValueList value);

	/**
     * Returns the value of the '<em><b>In Value Row Select Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In Value Row Select Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>In Value Row Select Left</em>' container reference.
     * @see #setInValueRowSelectLeft(PredicateInValueRowSelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_InValueRowSelectLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getValueExprList
     * @model opposite="valueExprList"
     * @generated
     */
    PredicateInValueRowSelect getInValueRowSelectLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueRowSelectLeft <em>In Value Row Select Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>In Value Row Select Left</em>' container reference.
     * @see #getInValueRowSelectLeft()
     * @generated
     */
    void setInValueRowSelectLeft(PredicateInValueRowSelect value);

	/**
     * Returns the value of the '<em><b>In Value Select Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In Value Select Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>In Value Select Left</em>' container reference.
     * @see #setInValueSelectLeft(PredicateInValueSelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_InValueSelectLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    PredicateInValueSelect getInValueSelectLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueSelectLeft <em>In Value Select Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>In Value Select Left</em>' container reference.
     * @see #getInValueSelectLeft()
     * @generated
     */
    void setInValueSelectLeft(PredicateInValueSelect value);

	/**
     * Returns the value of the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Quantified Row Select Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Quantified Row Select Left</em>' container reference.
     * @see #setQuantifiedRowSelectLeft(PredicateQuantifiedRowSelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_QuantifiedRowSelectLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getValueExprList
     * @model opposite="valueExprList"
     * @generated
     */
    PredicateQuantifiedRowSelect getQuantifiedRowSelectLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedRowSelectLeft <em>Quantified Row Select Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Quantified Row Select Left</em>' container reference.
     * @see #getQuantifiedRowSelectLeft()
     * @generated
     */
    void setQuantifiedRowSelectLeft(PredicateQuantifiedRowSelect value);

	/**
     * Returns the value of the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Quantified Value Select Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Quantified Value Select Left</em>' container reference.
     * @see #setQuantifiedValueSelectLeft(PredicateQuantifiedValueSelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_QuantifiedValueSelectLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    PredicateQuantifiedValueSelect getQuantifiedValueSelectLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedValueSelectLeft <em>Quantified Value Select Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Quantified Value Select Left</em>' container reference.
     * @see #getQuantifiedValueSelectLeft()
     * @generated
     */
    void setQuantifiedValueSelectLeft(PredicateQuantifiedValueSelect value);

	/**
     * Returns the value of the '<em><b>Between Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getLeftValueExpr <em>Left Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Between Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Between Left</em>' container reference.
     * @see #setBetweenLeft(PredicateBetween)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_BetweenLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getLeftValueExpr
     * @model opposite="leftValueExpr"
     * @generated
     */
    PredicateBetween getBetweenLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenLeft <em>Between Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Between Left</em>' container reference.
     * @see #getBetweenLeft()
     * @generated
     */
    void setBetweenLeft(PredicateBetween value);

	/**
     * Returns the value of the '<em><b>Between Right1</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr1 <em>Right Value Expr1</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Between Right1</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Between Right1</em>' container reference.
     * @see #setBetweenRight1(PredicateBetween)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_BetweenRight1()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr1
     * @model opposite="rightValueExpr1"
     * @generated
     */
    PredicateBetween getBetweenRight1();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight1 <em>Between Right1</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Between Right1</em>' container reference.
     * @see #getBetweenRight1()
     * @generated
     */
    void setBetweenRight1(PredicateBetween value);

	/**
     * Returns the value of the '<em><b>Between Right2</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr2 <em>Right Value Expr2</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Between Right2</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Between Right2</em>' container reference.
     * @see #setBetweenRight2(PredicateBetween)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_BetweenRight2()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr2
     * @model opposite="rightValueExpr2"
     * @generated
     */
    PredicateBetween getBetweenRight2();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight2 <em>Between Right2</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Between Right2</em>' container reference.
     * @see #getBetweenRight2()
     * @generated
     */
    void setBetweenRight2(PredicateBetween value);

	/**
     * Returns the value of the '<em><b>Value Expr Cast</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Cast</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Cast</em>' container reference.
     * @see #setValueExprCast(ValueExpressionCast)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprCast()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    ValueExpressionCast getValueExprCast();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCast <em>Value Expr Cast</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Cast</em>' container reference.
     * @see #getValueExprCast()
     * @generated
     */
    void setValueExprCast(ValueExpressionCast value);

	/**
     * Returns the value of the '<em><b>Value Expr Function</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getParameterList <em>Parameter List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Function</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Function</em>' container reference.
     * @see #setValueExprFunction(ValueExpressionFunction)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprFunction()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getParameterList
     * @model opposite="parameterList"
     * @generated
     */
    ValueExpressionFunction getValueExprFunction();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprFunction <em>Value Expr Function</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Function</em>' container reference.
     * @see #getValueExprFunction()
     * @generated
     */
    void setValueExprFunction(ValueExpressionFunction value);

	/**
     * Returns the value of the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getLeftValueExpr <em>Left Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Combined Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Combined Left</em>' container reference.
     * @see #setValueExprCombinedLeft(ValueExpressionCombined)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprCombinedLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getLeftValueExpr
     * @model opposite="leftValueExpr"
     * @generated
     */
    ValueExpressionCombined getValueExprCombinedLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedLeft <em>Value Expr Combined Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Combined Left</em>' container reference.
     * @see #getValueExprCombinedLeft()
     * @generated
     */
    void setValueExprCombinedLeft(ValueExpressionCombined value);

	/**
     * Returns the value of the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getRightValueExpr <em>Right Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Combined Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Combined Right</em>' container reference.
     * @see #setValueExprCombinedRight(ValueExpressionCombined)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprCombinedRight()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getRightValueExpr
     * @model opposite="rightValueExpr"
     * @generated
     */
    ValueExpressionCombined getValueExprCombinedRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedRight <em>Value Expr Combined Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Combined Right</em>' container reference.
     * @see #getValueExprCombinedRight()
     * @generated
     */
    void setValueExprCombinedRight(ValueExpressionCombined value);

	/**
     * Returns the value of the '<em><b>Grouping Expr</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grouping Expr</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grouping Expr</em>' container reference.
     * @see #setGroupingExpr(GroupingExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_GroupingExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    GroupingExpression getGroupingExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getGroupingExpr <em>Grouping Expr</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Grouping Expr</em>' container reference.
     * @see #getGroupingExpr()
     * @generated
     */
    void setGroupingExpr(GroupingExpression value);

	/**
     * Returns the value of the '<em><b>Value Expr Case Else</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Case Else</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Case Else</em>' container reference.
     * @see #setValueExprCaseElse(ValueExpressionCaseElse)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprCaseElse()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    ValueExpressionCaseElse getValueExprCaseElse();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseElse <em>Value Expr Case Else</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Case Else</em>' container reference.
     * @see #getValueExprCaseElse()
     * @generated
     */
    void setValueExprCaseElse(ValueExpressionCaseElse value);

	/**
     * Returns the value of the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Case Simple</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Case Simple</em>' container reference.
     * @see #setValueExprCaseSimple(ValueExpressionCaseSimple)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprCaseSimple()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    ValueExpressionCaseSimple getValueExprCaseSimple();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimple <em>Value Expr Case Simple</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Case Simple</em>' container reference.
     * @see #getValueExprCaseSimple()
     * @generated
     */
    void setValueExprCaseSimple(ValueExpressionCaseSimple value);

	/**
     * Returns the value of the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getWhenValueExpr <em>When Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Case Simple Content When</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Case Simple Content When</em>' container reference.
     * @see #setValueExprCaseSimpleContentWhen(ValueExpressionCaseSimpleContent)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprCaseSimpleContentWhen()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getWhenValueExpr
     * @model opposite="whenValueExpr"
     * @generated
     */
    ValueExpressionCaseSimpleContent getValueExprCaseSimpleContentWhen();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentWhen <em>Value Expr Case Simple Content When</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Case Simple Content When</em>' container reference.
     * @see #getValueExprCaseSimpleContentWhen()
     * @generated
     */
    void setValueExprCaseSimpleContentWhen(ValueExpressionCaseSimpleContent value);

	/**
     * Returns the value of the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getResultValueExpr <em>Result Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Case Simple Content Result</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Case Simple Content Result</em>' container reference.
     * @see #setValueExprCaseSimpleContentResult(ValueExpressionCaseSimpleContent)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprCaseSimpleContentResult()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getResultValueExpr
     * @model opposite="resultValueExpr"
     * @generated
     */
    ValueExpressionCaseSimpleContent getValueExprCaseSimpleContentResult();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentResult <em>Value Expr Case Simple Content Result</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Case Simple Content Result</em>' container reference.
     * @see #getValueExprCaseSimpleContentResult()
     * @generated
     */
    void setValueExprCaseSimpleContentResult(ValueExpressionCaseSimpleContent value);

	/**
     * Returns the value of the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Case Search Content</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Case Search Content</em>' container reference.
     * @see #setValueExprCaseSearchContent(ValueExpressionCaseSearchContent)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprCaseSearchContent()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    ValueExpressionCaseSearchContent getValueExprCaseSearchContent();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Case Search Content</em>' container reference.
     * @see #getValueExprCaseSearchContent()
     * @generated
     */
    void setValueExprCaseSearchContent(ValueExpressionCaseSearchContent value);

	/**
     * Returns the value of the '<em><b>Like Escape</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getEscapeValueExpr <em>Escape Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Like Escape</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Like Escape</em>' container reference.
     * @see #setLikeEscape(PredicateLike)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_LikeEscape()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike#getEscapeValueExpr
     * @model opposite="escapeValueExpr"
     * @generated
     */
    PredicateLike getLikeEscape();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeEscape <em>Like Escape</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Like Escape</em>' container reference.
     * @see #getLikeEscape()
     * @generated
     */
    void setLikeEscape(PredicateLike value);

	/**
     * Returns the value of the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Labeled Duration</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Labeled Duration</em>' container reference.
     * @see #setValueExprLabeledDuration(ValueExpressionLabeledDuration)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprLabeledDuration()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getValueExpr
     * @model opposite="valueExpr"
     * @generated
     */
    ValueExpressionLabeledDuration getValueExprLabeledDuration();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprLabeledDuration <em>Value Expr Labeled Duration</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Labeled Duration</em>' container reference.
     * @see #getValueExprLabeledDuration()
     * @generated
     */
    void setValueExprLabeledDuration(ValueExpressionLabeledDuration value);

	/**
     * Returns the value of the '<em><b>Nest</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested#getNestedValueExpr <em>Nested Value Expr</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nest</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Nest</em>' container reference.
     * @see #setNest(ValueExpressionNested)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_Nest()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested#getNestedValueExpr
     * @model opposite="nestedValueExpr"
     * @generated
     */
  ValueExpressionNested getNest();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getNest <em>Nest</em>}' container reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nest</em>' container reference.
     * @see #getNest()
     * @generated
     */
  void setNest(ValueExpressionNested value);

	/**
     * Returns the value of the '<em><b>Update Source Expr List</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Update Source Expr List</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Update Source Expr List</em>' container reference.
     * @see #setUpdateSourceExprList(UpdateSourceExprList)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_UpdateSourceExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList#getValueExprList
     * @model opposite="valueExprList"
     * @generated
     */
  UpdateSourceExprList getUpdateSourceExprList();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUpdateSourceExprList <em>Update Source Expr List</em>}' container reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Source Expr List</em>' container reference.
     * @see #getUpdateSourceExprList()
     * @generated
     */
  void setUpdateSourceExprList(UpdateSourceExprList value);

    /**
     * Returns the value of the '<em><b>Table Function</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableFunction#getParameterList <em>Parameter List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Function</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Function</em>' container reference.
     * @see #setTableFunction(TableFunction)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_TableFunction()
     * @see org.eclipse.datatools.modelbase.sql.query.TableFunction#getParameterList
     * @model opposite="parameterList" required="true"
     * @generated
     */
    TableFunction getTableFunction();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getTableFunction <em>Table Function</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Function</em>' container reference.
     * @see #getTableFunction()
     * @generated
     */
    void setTableFunction(TableFunction value);

    /**
     * Returns the value of the '<em><b>Value Expr Row</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Row</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Row</em>' container reference.
     * @see #setValueExprRow(ValueExpressionRow)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_ValueExprRow()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow#getValueExprList
     * @model opposite="valueExprList" required="true"
     * @generated
     */
    ValueExpressionRow getValueExprRow();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprRow <em>Value Expr Row</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Row</em>' container reference.
     * @see #getValueExprRow()
     * @generated
     */
    void setValueExprRow(ValueExpressionRow value);

    /**
     * Returns the value of the '<em><b>Call Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.CallStatement#getArgumentList <em>Argument List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Call Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Call Statement</em>' container reference.
     * @see #setCallStatement(CallStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValueExpression_CallStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.CallStatement#getArgumentList
     * @model opposite="argumentList" required="true"
     * @generated
     */
    CallStatement getCallStatement();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getCallStatement <em>Call Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Call Statement</em>' container reference.
     * @see #getCallStatement()
     * @generated
     */
    void setCallStatement(CallStatement value);

} // SQLValueExpression
