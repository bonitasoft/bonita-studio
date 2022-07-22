/**
 * <copyright>
 * </copyright>
 *
 * $Id: QuerySearchCondition.java,v 1.3 2008/01/31 02:57:16 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Search Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#isNegatedCondition <em>Negated Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getDeleteStatement <em>Delete Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getTableJoined <em>Table Joined</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedLeft <em>Combined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedRight <em>Combined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectHaving <em>Query Select Having</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectWhere <em>Query Select Where</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getNest <em>Nest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getMergeOnCondition <em>Merge On Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition()
 * @model abstract="true"
 * @generated
 */
public interface QuerySearchCondition extends SQLQueryObject, SearchCondition{
	/**
     * Returns the value of the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * This represents NOT ( <searchCondition> ).  This attribute and paren can not both be true at the same time.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Negated Condition</em>' attribute.
     * @see #setNegatedCondition(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_NegatedCondition()
     * @model
     * @generated
     */
    boolean isNegatedCondition();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#isNegatedCondition <em>Negated Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Negated Condition</em>' attribute.
     * @see #isNegatedCondition()
     * @generated
     */
    void setNegatedCondition(boolean value);

	/**
     * Returns the value of the '<em><b>Update Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getWhereClause <em>Where Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Update Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Update Statement</em>' container reference.
     * @see #setUpdateStatement(QueryUpdateStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_UpdateStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getWhereClause
     * @model opposite="whereClause"
     * @generated
     */
    QueryUpdateStatement getUpdateStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getUpdateStatement <em>Update Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Statement</em>' container reference.
     * @see #getUpdateStatement()
     * @generated
     */
    void setUpdateStatement(QueryUpdateStatement value);

	/**
     * Returns the value of the '<em><b>Delete Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereClause <em>Where Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delete Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Delete Statement</em>' container reference.
     * @see #setDeleteStatement(QueryDeleteStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_DeleteStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereClause
     * @model opposite="whereClause"
     * @generated
     */
    QueryDeleteStatement getDeleteStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getDeleteStatement <em>Delete Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Delete Statement</em>' container reference.
     * @see #getDeleteStatement()
     * @generated
     */
    void setDeleteStatement(QueryDeleteStatement value);

	/**
     * Returns the value of the '<em><b>Table Joined</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinCondition <em>Join Condition</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Joined</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Joined</em>' container reference.
     * @see #setTableJoined(TableJoined)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_TableJoined()
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinCondition
     * @model opposite="joinCondition"
     * @generated
     */
    TableJoined getTableJoined();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getTableJoined <em>Table Joined</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Joined</em>' container reference.
     * @see #getTableJoined()
     * @generated
     */
    void setTableJoined(TableJoined value);

	/**
     * Returns the value of the '<em><b>Combined Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getLeftCondition <em>Left Condition</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Combined Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Combined Left</em>' container reference.
     * @see #setCombinedLeft(SearchConditionCombined)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_CombinedLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getLeftCondition
     * @model opposite="leftCondition"
     * @generated
     */
    SearchConditionCombined getCombinedLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedLeft <em>Combined Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Combined Left</em>' container reference.
     * @see #getCombinedLeft()
     * @generated
     */
    void setCombinedLeft(SearchConditionCombined value);

	/**
     * Returns the value of the '<em><b>Combined Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getRightCondition <em>Right Condition</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Combined Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Combined Right</em>' container reference.
     * @see #setCombinedRight(SearchConditionCombined)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_CombinedRight()
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getRightCondition
     * @model opposite="rightCondition"
     * @generated
     */
    SearchConditionCombined getCombinedRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedRight <em>Combined Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Combined Right</em>' container reference.
     * @see #getCombinedRight()
     * @generated
     */
    void setCombinedRight(SearchConditionCombined value);

	/**
     * Returns the value of the '<em><b>Query Select Having</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getHavingClause <em>Having Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Select Having</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Select Having</em>' container reference.
     * @see #setQuerySelectHaving(QuerySelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_QuerySelectHaving()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getHavingClause
     * @model opposite="havingClause"
     * @generated
     */
    QuerySelect getQuerySelectHaving();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectHaving <em>Query Select Having</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Select Having</em>' container reference.
     * @see #getQuerySelectHaving()
     * @generated
     */
    void setQuerySelectHaving(QuerySelect value);

	/**
     * Returns the value of the '<em><b>Query Select Where</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getWhereClause <em>Where Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Select Where</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Select Where</em>' container reference.
     * @see #setQuerySelectWhere(QuerySelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_QuerySelectWhere()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getWhereClause
     * @model opposite="whereClause"
     * @generated
     */
    QuerySelect getQuerySelectWhere();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectWhere <em>Query Select Where</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Select Where</em>' container reference.
     * @see #getQuerySelectWhere()
     * @generated
     */
    void setQuerySelectWhere(QuerySelect value);

	/**
     * Returns the value of the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getSearchCondition <em>Search Condition</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Case Search Content</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Case Search Content</em>' container reference.
     * @see #setValueExprCaseSearchContent(ValueExpressionCaseSearchContent)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_ValueExprCaseSearchContent()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getSearchCondition
     * @model opposite="searchCondition"
     * @generated
     */
    ValueExpressionCaseSearchContent getValueExprCaseSearchContent();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Case Search Content</em>' container reference.
     * @see #getValueExprCaseSearchContent()
     * @generated
     */
    void setValueExprCaseSearchContent(ValueExpressionCaseSearchContent value);

	/**
     * Returns the value of the '<em><b>Nest</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionNested#getNestedCondition <em>Nested Condition</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nest</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Nest</em>' container reference.
     * @see #setNest(SearchConditionNested)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_Nest()
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionNested#getNestedCondition
     * @model opposite="nestedCondition"
     * @generated
     */
  SearchConditionNested getNest();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getNest <em>Nest</em>}' container reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nest</em>' container reference.
     * @see #getNest()
     * @generated
     */
  void setNest(SearchConditionNested value);

    /**
     * Returns the value of the '<em><b>Merge On Condition</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getSearchCondition <em>Search Condition</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Merge On Condition</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Merge On Condition</em>' container reference.
     * @see #setMergeOnCondition(MergeOnCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySearchCondition_MergeOnCondition()
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getSearchCondition
     * @model opposite="searchCondition"
     * @generated
     */
    MergeOnCondition getMergeOnCondition();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getMergeOnCondition <em>Merge On Condition</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Merge On Condition</em>' container reference.
     * @see #getMergeOnCondition()
     * @generated
     */
    void setMergeOnCondition(MergeOnCondition value);

} // SQLSearchCondition
