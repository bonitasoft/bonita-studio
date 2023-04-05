/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryCombined.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Combined</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getCombinedOperator <em>Combined Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getLeftQuery <em>Left Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getRightQuery <em>Right Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryCombined()
 * @model
 * @generated
 */
public interface QueryCombined extends QueryExpressionBody{
	/**
     * Returns the value of the '<em><b>Combined Operator</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Combined Operator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Combined Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator
     * @see #setCombinedOperator(QueryCombinedOperator)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryCombined_CombinedOperator()
     * @model
     * @generated
     */
    QueryCombinedOperator getCombinedOperator();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getCombinedOperator <em>Combined Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Combined Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator
     * @see #getCombinedOperator()
     * @generated
     */
    void setCombinedOperator(QueryCombinedOperator value);

	/**
     * Returns the value of the '<em><b>Left Query</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedLeft <em>Combined Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Left Query</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Left Query</em>' containment reference.
     * @see #setLeftQuery(QueryExpressionBody)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryCombined_LeftQuery()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedLeft
     * @model opposite="combinedLeft" containment="true" required="true"
     * @generated
     */
    QueryExpressionBody getLeftQuery();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getLeftQuery <em>Left Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Left Query</em>' containment reference.
     * @see #getLeftQuery()
     * @generated
     */
    void setLeftQuery(QueryExpressionBody value);

	/**
     * Returns the value of the '<em><b>Right Query</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedRight <em>Combined Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Right Query</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Right Query</em>' containment reference.
     * @see #setRightQuery(QueryExpressionBody)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryCombined_RightQuery()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedRight
     * @model opposite="combinedRight" containment="true" required="true"
     * @generated
     */
    QueryExpressionBody getRightQuery();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getRightQuery <em>Right Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right Query</em>' containment reference.
     * @see #getRightQuery()
     * @generated
     */
    void setRightQuery(QueryExpressionBody value);

} // SQLQueryCombined
