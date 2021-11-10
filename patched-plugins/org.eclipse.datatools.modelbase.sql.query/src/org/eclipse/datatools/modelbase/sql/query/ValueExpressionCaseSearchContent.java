/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCaseSearchContent.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Case Search Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getSearchCondition <em>Search Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExprCaseSearch <em>Value Expr Case Search</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSearchContent()
 * @model
 * @generated
 */
public interface ValueExpressionCaseSearchContent extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSearchContent_ValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSearchContent
     * @model opposite="valueExprCaseSearchContent" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Search Condition</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Search Condition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Search Condition</em>' containment reference.
     * @see #setSearchCondition(QuerySearchCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSearchContent_SearchCondition()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getValueExprCaseSearchContent
     * @model opposite="valueExprCaseSearchContent" containment="true" required="true"
     * @generated
     */
    QuerySearchCondition getSearchCondition();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getSearchCondition <em>Search Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Search Condition</em>' containment reference.
     * @see #getSearchCondition()
     * @generated
     */
    void setSearchCondition(QuerySearchCondition value);

	/**
     * Returns the value of the '<em><b>Value Expr Case Search</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch#getSearchContentList <em>Search Content List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Case Search</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Case Search</em>' container reference.
     * @see #setValueExprCaseSearch(ValueExpressionCaseSearch)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSearchContent_ValueExprCaseSearch()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch#getSearchContentList
     * @model opposite="searchContentList"
     * @generated
     */
    ValueExpressionCaseSearch getValueExprCaseSearch();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExprCaseSearch <em>Value Expr Case Search</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Case Search</em>' container reference.
     * @see #getValueExprCaseSearch()
     * @generated
     */
    void setValueExprCaseSearch(ValueExpressionCaseSearch value);

} // SQLValueExpressionCaseSearchContent
