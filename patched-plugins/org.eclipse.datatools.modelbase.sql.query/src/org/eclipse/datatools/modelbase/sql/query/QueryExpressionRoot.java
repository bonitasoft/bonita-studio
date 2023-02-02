/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryExpressionRoot.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getSelectStatement <em>Select Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getWithClause <em>With Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuery <em>Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueRowSelectRight <em>In Value Row Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueSelectRight <em>In Value Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedRowSelectRight <em>Quantified Row Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedValueSelectRight <em>Quantified Value Select Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getValExprScalarSelect <em>Val Expr Scalar Select</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot()
 * @model
 * @generated
 */
public interface QueryExpressionRoot extends SQLQueryObject, QueryExpression{
	/**
     * Returns the value of the '<em><b>Insert Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceQuery <em>Source Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Insert Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Insert Statement</em>' container reference.
     * @see #setInsertStatement(QueryInsertStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_InsertStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceQuery
     * @model opposite="sourceQuery"
     * @generated
     */
    QueryInsertStatement getInsertStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInsertStatement <em>Insert Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Insert Statement</em>' container reference.
     * @see #getInsertStatement()
     * @generated
     */
    void setInsertStatement(QueryInsertStatement value);

	/**
     * Returns the value of the '<em><b>Select Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Select Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Select Statement</em>' container reference.
     * @see #setSelectStatement(QuerySelectStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_SelectStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getQueryExpr
     * @model opposite="queryExpr"
     * @generated
     */
    QuerySelectStatement getSelectStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getSelectStatement <em>Select Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Select Statement</em>' container reference.
     * @see #getSelectStatement()
     * @generated
     */
    void setSelectStatement(QuerySelectStatement value);

	/**
     * Returns the value of the '<em><b>With Clause</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getQueryExpressionRoot <em>Query Expression Root</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>With Clause</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>With Clause</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_WithClause()
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getQueryExpressionRoot
     * @model type="org.eclipse.datatools.modelbase.sql.query.WithTableSpecification" opposite="queryExpressionRoot" containment="true"
     * @generated
     */
    EList getWithClause();

	/**
     * Returns the value of the '<em><b>Query</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryExpression <em>Query Expression</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' containment reference.
     * @see #setQuery(QueryExpressionBody)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_Query()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryExpression
     * @model opposite="queryExpression" containment="true" required="true"
     * @generated
     */
    QueryExpressionBody getQuery();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuery <em>Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' containment reference.
     * @see #getQuery()
     * @generated
     */
    void setQuery(QueryExpressionBody value);

	/**
     * Returns the value of the '<em><b>In Value Row Select Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In Value Row Select Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>In Value Row Select Right</em>' container reference.
     * @see #setInValueRowSelectRight(PredicateInValueRowSelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_InValueRowSelectRight()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getQueryExpr
     * @model opposite="queryExpr"
     * @generated
     */
    PredicateInValueRowSelect getInValueRowSelectRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueRowSelectRight <em>In Value Row Select Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>In Value Row Select Right</em>' container reference.
     * @see #getInValueRowSelectRight()
     * @generated
     */
    void setInValueRowSelectRight(PredicateInValueRowSelect value);

	/**
     * Returns the value of the '<em><b>In Value Select Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In Value Select Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>In Value Select Right</em>' container reference.
     * @see #setInValueSelectRight(PredicateInValueSelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_InValueSelectRight()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect#getQueryExpr
     * @model opposite="queryExpr"
     * @generated
     */
    PredicateInValueSelect getInValueSelectRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueSelectRight <em>In Value Select Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>In Value Select Right</em>' container reference.
     * @see #getInValueSelectRight()
     * @generated
     */
    void setInValueSelectRight(PredicateInValueSelect value);

	/**
     * Returns the value of the '<em><b>Quantified Row Select Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Quantified Row Select Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Quantified Row Select Right</em>' container reference.
     * @see #setQuantifiedRowSelectRight(PredicateQuantifiedRowSelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_QuantifiedRowSelectRight()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQueryExpr
     * @model opposite="queryExpr"
     * @generated
     */
    PredicateQuantifiedRowSelect getQuantifiedRowSelectRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedRowSelectRight <em>Quantified Row Select Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Quantified Row Select Right</em>' container reference.
     * @see #getQuantifiedRowSelectRight()
     * @generated
     */
    void setQuantifiedRowSelectRight(PredicateQuantifiedRowSelect value);

	/**
     * Returns the value of the '<em><b>Quantified Value Select Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Quantified Value Select Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Quantified Value Select Right</em>' container reference.
     * @see #setQuantifiedValueSelectRight(PredicateQuantifiedValueSelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_QuantifiedValueSelectRight()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQueryExpr
     * @model opposite="queryExpr"
     * @generated
     */
    PredicateQuantifiedValueSelect getQuantifiedValueSelectRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedValueSelectRight <em>Quantified Value Select Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Quantified Value Select Right</em>' container reference.
     * @see #getQuantifiedValueSelectRight()
     * @generated
     */
    void setQuantifiedValueSelectRight(PredicateQuantifiedValueSelect value);

	/**
     * Returns the value of the '<em><b>Val Expr Scalar Select</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Val Expr Scalar Select</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Val Expr Scalar Select</em>' container reference.
     * @see #setValExprScalarSelect(ValueExpressionScalarSelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_ValExprScalarSelect()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect#getQueryExpr
     * @model opposite="queryExpr"
     * @generated
     */
    ValueExpressionScalarSelect getValExprScalarSelect();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getValExprScalarSelect <em>Val Expr Scalar Select</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Val Expr Scalar Select</em>' container reference.
     * @see #getValExprScalarSelect()
     * @generated
     */
    void setValExprScalarSelect(ValueExpressionScalarSelect value);

    /**
     * Returns the value of the '<em><b>Value Expr Scalar Selects</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Scalar Selects</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Scalar Selects</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionRoot_ValueExprScalarSelects()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect#getQueryExpr
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect" opposite="queryExpr"
     * @generated NOT
     * @deprecated Use getValExprScalarSelect instead, which returns a single element instead of a list
     */
    EList getValueExprScalarSelects();
} // SQLQueryExpression
