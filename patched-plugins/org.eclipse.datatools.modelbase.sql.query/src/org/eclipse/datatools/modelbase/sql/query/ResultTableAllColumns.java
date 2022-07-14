/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResultTableAllColumns.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Result Column All</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns#getTableExpr <em>Table Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getResultTableAllColumns()
 * @model
 * @generated
 */
public interface ResultTableAllColumns extends QueryResultSpecification{
	/**
     * Returns the value of the '<em><b>Table Expr</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getResultTableAllColumns <em>Result Table All Columns</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Table Expr</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Table Expr</em>' reference.
     * @see #setTableExpr(TableExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getResultTableAllColumns_TableExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression#getResultTableAllColumns
     * @model opposite="resultTableAllColumns" required="true"
     * @generated
     */
  TableExpression getTableExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns#getTableExpr <em>Table Expr</em>}' reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Expr</em>' reference.
     * @see #getTableExpr()
     * @generated
     */
  void setTableExpr(TableExpression value);

} // SQLResultColumnAll
