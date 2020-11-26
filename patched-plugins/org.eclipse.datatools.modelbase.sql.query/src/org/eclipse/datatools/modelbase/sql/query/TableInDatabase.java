/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableInDatabase.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


import org.eclipse.emf.common.util.EList;

import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQLRDB Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDeleteStatement <em>Delete Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDatabaseTable <em>Database Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDerivedColumnList <em>Derived Column List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableInDatabase()
 * @model
 * @generated
 */
public interface TableInDatabase extends TableExpression{
	/**
     * Returns the value of the '<em><b>Update Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getTargetTable <em>Target Table</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Update Statement</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Update Statement</em>' container reference.
     * @see #setUpdateStatement(QueryUpdateStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableInDatabase_UpdateStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getTargetTable
     * @model opposite="targetTable"
     * @generated
     */
  QueryUpdateStatement getUpdateStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getUpdateStatement <em>Update Statement</em>}' container reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Statement</em>' container reference.
     * @see #getUpdateStatement()
     * @generated
     */
  void setUpdateStatement(QueryUpdateStatement value);

	/**
     * Returns the value of the '<em><b>Delete Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getTargetTable <em>Target Table</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Delete Statement</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Delete Statement</em>' container reference.
     * @see #setDeleteStatement(QueryDeleteStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableInDatabase_DeleteStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getTargetTable
     * @model opposite="targetTable"
     * @generated
     */
  QueryDeleteStatement getDeleteStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDeleteStatement <em>Delete Statement</em>}' container reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Delete Statement</em>' container reference.
     * @see #getDeleteStatement()
     * @generated
     */
  void setDeleteStatement(QueryDeleteStatement value);

	/**
     * Returns the value of the '<em><b>Insert Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetTable <em>Target Table</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Insert Statement</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Insert Statement</em>' container reference.
     * @see #setInsertStatement(QueryInsertStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableInDatabase_InsertStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetTable
     * @model opposite="targetTable"
     * @generated
     */
  QueryInsertStatement getInsertStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getInsertStatement <em>Insert Statement</em>}' container reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Insert Statement</em>' container reference.
     * @see #getInsertStatement()
     * @generated
     */
  void setInsertStatement(QueryInsertStatement value);

	/**
     * Returns the value of the '<em><b>Database Table</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Database Table</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Database Table</em>' reference.
     * @see #setDatabaseTable(Table)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableInDatabase_DatabaseTable()
     * @model required="true"
     * @generated
     */
    Table getDatabaseTable();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDatabaseTable <em>Database Table</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Database Table</em>' reference.
     * @see #getDatabaseTable()
     * @generated
     */
    void setDatabaseTable(Table value);

	/**
     * Returns the value of the '<em><b>Derived Column List</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getTableInDatabase <em>Table In Database</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Gets the list of columns used anywhere in the statement that are derived from this table.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Derived Column List</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableInDatabase_DerivedColumnList()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getTableInDatabase
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn" opposite="tableInDatabase"
     * @generated
     */
    EList getDerivedColumnList();

} // SQLRDBTable
