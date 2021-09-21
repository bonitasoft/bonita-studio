/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionScalarSelect.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Scalar Select</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect#getQueryExpr <em>Query Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionScalarSelect()
 * @model
 * @generated
 */
public interface ValueExpressionScalarSelect extends ValueExpressionAtomic{
	/**
     * Returns the value of the '<em><b>Query Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getValExprScalarSelect <em>Val Expr Scalar Select</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Expr</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Expr</em>' containment reference.
     * @see #setQueryExpr(QueryExpressionRoot)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionScalarSelect_QueryExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getValExprScalarSelect
     * @model opposite="valExprScalarSelect" containment="true" required="true"
     * @generated
     */
    QueryExpressionRoot getQueryExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect#getQueryExpr <em>Query Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Expr</em>' containment reference.
     * @see #getQueryExpr()
     * @generated
     */
  void setQueryExpr(QueryExpressionRoot value);

} // SQLValueExpressionScalarSelect
