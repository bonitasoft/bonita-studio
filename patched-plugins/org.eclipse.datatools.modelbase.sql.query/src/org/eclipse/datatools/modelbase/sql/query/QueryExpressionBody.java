/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryExpressionBody.java,v 1.3 2008/01/31 02:57:16 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression Body</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getRowFetchLimit <em>Row Fetch Limit</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryExpression <em>Query Expression</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedLeft <em>Combined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedRight <em>Combined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getPredicateExists <em>Predicate Exists</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getUpdateSourceQuery <em>Update Source Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getWithTableSpecification <em>With Table Specification</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryNest <em>Query Nest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getSortSpecList <em>Sort Spec List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody()
 * @model abstract="true"
 * @generated
 */
public interface QueryExpressionBody extends TableExpression{
	/**
     * Returns the value of the '<em><b>Row Fetch Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Row Fetch Limit</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Row Fetch Limit</em>' attribute.
     * @see #setRowFetchLimit(int)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody_RowFetchLimit()
     * @model
     * @generated
     */
    int getRowFetchLimit();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getRowFetchLimit <em>Row Fetch Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Row Fetch Limit</em>' attribute.
     * @see #getRowFetchLimit()
     * @generated
     */
    void setRowFetchLimit(int value);

    /**
     * Returns the value of the '<em><b>Query Expression</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Expression</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Expression</em>' container reference.
     * @see #setQueryExpression(QueryExpressionRoot)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody_QueryExpression()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuery
     * @model opposite="query"
     * @generated
     */
    QueryExpressionRoot getQueryExpression();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryExpression <em>Query Expression</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Expression</em>' container reference.
     * @see #getQueryExpression()
     * @generated
     */
    void setQueryExpression(QueryExpressionRoot value);

	/**
     * Returns the value of the '<em><b>Combined Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getLeftQuery <em>Left Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Combined Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Combined Left</em>' container reference.
     * @see #setCombinedLeft(QueryCombined)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody_CombinedLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombined#getLeftQuery
     * @model opposite="leftQuery"
     * @generated
     */
    QueryCombined getCombinedLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedLeft <em>Combined Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Combined Left</em>' container reference.
     * @see #getCombinedLeft()
     * @generated
     */
    void setCombinedLeft(QueryCombined value);

	/**
     * Returns the value of the '<em><b>Combined Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getRightQuery <em>Right Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Combined Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Combined Right</em>' container reference.
     * @see #setCombinedRight(QueryCombined)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody_CombinedRight()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombined#getRightQuery
     * @model opposite="rightQuery"
     * @generated
     */
    QueryCombined getCombinedRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedRight <em>Combined Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Combined Right</em>' container reference.
     * @see #getCombinedRight()
     * @generated
     */
    void setCombinedRight(QueryCombined value);

	/**
     * Returns the value of the '<em><b>Predicate Exists</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.PredicateExists#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predicate Exists</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Predicate Exists</em>' container reference.
     * @see #setPredicateExists(PredicateExists)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody_PredicateExists()
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateExists#getQueryExpr
     * @model opposite="queryExpr"
     * @generated
     */
    PredicateExists getPredicateExists();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getPredicateExists <em>Predicate Exists</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Predicate Exists</em>' container reference.
     * @see #getPredicateExists()
     * @generated
     */
    void setPredicateExists(PredicateExists value);

	/**
     * Returns the value of the '<em><b>Update Source Query</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Update Source Query</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Update Source Query</em>' container reference.
     * @see #setUpdateSourceQuery(UpdateSourceQuery)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody_UpdateSourceQuery()
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery#getQueryExpr
     * @model opposite="queryExpr" required="true"
     * @generated
     */
  UpdateSourceQuery getUpdateSourceQuery();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getUpdateSourceQuery <em>Update Source Query</em>}' container reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Source Query</em>' container reference.
     * @see #getUpdateSourceQuery()
     * @generated
     */
  void setUpdateSourceQuery(UpdateSourceQuery value);

	/**
     * Returns the value of the '<em><b>With Table Specification</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableQueryExpr <em>With Table Query Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>With Table Specification</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>With Table Specification</em>' container reference.
     * @see #setWithTableSpecification(WithTableSpecification)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody_WithTableSpecification()
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableQueryExpr
     * @model opposite="withTableQueryExpr" required="true"
     * @generated
     */
    WithTableSpecification getWithTableSpecification();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getWithTableSpecification <em>With Table Specification</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>With Table Specification</em>' container reference.
     * @see #getWithTableSpecification()
     * @generated
     */
    void setWithTableSpecification(WithTableSpecification value);

    /**
     * Returns the value of the '<em><b>Query Nest</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryNested#getNestedQuery <em>Nested Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Nest</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Nest</em>' container reference.
     * @see #setQueryNest(QueryNested)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody_QueryNest()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryNested#getNestedQuery
     * @model opposite="nestedQuery"
     * @generated
     */
    QueryNested getQueryNest();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryNest <em>Query Nest</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Nest</em>' container reference.
     * @see #getQueryNest()
     * @generated
     */
    void setQueryNest(QueryNested value);

    /**
     * Returns the value of the '<em><b>Sort Spec List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sort Spec List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sort Spec List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryExpressionBody_SortSpecList()
     * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getQuery
     * @model type="org.eclipse.datatools.modelbase.sql.query.OrderBySpecification" opposite="query" containment="true"
     * @generated
     */
    EList getSortSpecList();

} // SQLQueryExpressionBody
