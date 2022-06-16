/**
 * <copyright>
 * </copyright>
 *
 * $Id: WithTableSpecification.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>With Table Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getQueryExpressionRoot <em>Query Expression Root</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableQueryExpr <em>With Table Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableReferences <em>With Table References</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getColumnNameList <em>Column Name List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getWithTableSpecification()
 * @model
 * @generated
 */
public interface WithTableSpecification extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Query Expression Root</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getWithClause <em>With Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Expression Root</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Expression Root</em>' container reference.
     * @see #setQueryExpressionRoot(QueryExpressionRoot)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getWithTableSpecification_QueryExpressionRoot()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getWithClause
     * @model opposite="withClause"
     * @generated
     */
    QueryExpressionRoot getQueryExpressionRoot();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getQueryExpressionRoot <em>Query Expression Root</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Expression Root</em>' container reference.
     * @see #getQueryExpressionRoot()
     * @generated
     */
    void setQueryExpressionRoot(QueryExpressionRoot value);

	/**
     * Returns the value of the '<em><b>With Table Query Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getWithTableSpecification <em>With Table Specification</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>With Table Query Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>With Table Query Expr</em>' containment reference.
     * @see #setWithTableQueryExpr(QueryExpressionBody)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getWithTableSpecification_WithTableQueryExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getWithTableSpecification
     * @model opposite="withTableSpecification" containment="true" required="true"
     * @generated
     */
    QueryExpressionBody getWithTableQueryExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableQueryExpr <em>With Table Query Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>With Table Query Expr</em>' containment reference.
     * @see #getWithTableQueryExpr()
     * @generated
     */
    void setWithTableQueryExpr(QueryExpressionBody value);

	/**
     * Returns the value of the '<em><b>With Table References</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.WithTableReference}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.WithTableReference#getWithTableSpecification <em>With Table Specification</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>With Table References</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>With Table References</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getWithTableSpecification_WithTableReferences()
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableReference#getWithTableSpecification
     * @model type="org.eclipse.datatools.modelbase.sql.query.WithTableReference" opposite="withTableSpecification"
     * @generated
     */
    EList getWithTableReferences();

	/**
     * Returns the value of the '<em><b>Column Name List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ColumnName}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ColumnName#getWithTableSpecification <em>With Table Specification</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column Name List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Column Name List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getWithTableSpecification_ColumnNameList()
     * @see org.eclipse.datatools.modelbase.sql.query.ColumnName#getWithTableSpecification
     * @model type="org.eclipse.datatools.modelbase.sql.query.ColumnName" opposite="withTableSpecification" containment="true"
     * @generated
     */
    EList getColumnNameList();

} // WithTableSpecification
