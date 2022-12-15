/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableCorrelation.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Table Correlation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getTableExpr <em>Table Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getColumnNameList <em>Column Name List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableCorrelation()
 * @model
 * @generated
 */
public interface TableCorrelation extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Table Expr</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getTableCorrelation <em>Table Correlation</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Expr</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Expr</em>' container reference.
     * @see #setTableExpr(TableExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableCorrelation_TableExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression#getTableCorrelation
     * @model opposite="tableCorrelation"
     * @generated
     */
    TableExpression getTableExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getTableExpr <em>Table Expr</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Expr</em>' container reference.
     * @see #getTableExpr()
     * @generated
     */
    void setTableExpr(TableExpression value);

	/**
     * Returns the value of the '<em><b>Column Name List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ColumnName}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ColumnName#getTableCorrelation <em>Table Correlation</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column Name List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Column Name List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableCorrelation_ColumnNameList()
     * @see org.eclipse.datatools.modelbase.sql.query.ColumnName#getTableCorrelation
     * @model type="org.eclipse.datatools.modelbase.sql.query.ColumnName" opposite="tableCorrelation" containment="true"
     * @generated
     */
    EList getColumnNameList();

} // SQLTableCorrelation
