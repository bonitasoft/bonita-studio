/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryInsertStatement.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Insert Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceQuery <em>Source Query</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceValuesRowList <em>Source Values Row List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetTable <em>Target Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetColumnList <em>Target Column List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryInsertStatement()
 * @model
 * @generated
 */
public interface QueryInsertStatement extends QueryChangeStatement{
	/**
     * Returns the value of the '<em><b>Source Query</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInsertStatement <em>Insert Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Query</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Query</em>' containment reference.
     * @see #setSourceQuery(QueryExpressionRoot)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryInsertStatement_SourceQuery()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInsertStatement
     * @model opposite="insertStatement" containment="true"
     * @generated
     */
    QueryExpressionRoot getSourceQuery();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceQuery <em>Source Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Query</em>' containment reference.
     * @see #getSourceQuery()
     * @generated
     */
    void setSourceQuery(QueryExpressionRoot value);

	/**
     * Returns the value of the '<em><b>Source Values Row List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValuesRow}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getInsertStatement <em>Insert Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Values Row List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Values Row List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryInsertStatement_SourceValuesRowList()
     * @see org.eclipse.datatools.modelbase.sql.query.ValuesRow#getInsertStatement
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValuesRow" opposite="insertStatement" containment="true"
     * @generated
     */
    EList getSourceValuesRowList();

	/**
     * Returns the value of the '<em><b>Target Table</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getInsertStatement <em>Insert Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Table</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Table</em>' containment reference.
     * @see #isSetTargetTable()
     * @see #unsetTargetTable()
     * @see #setTargetTable(TableInDatabase)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryInsertStatement_TargetTable()
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getInsertStatement
     * @model opposite="insertStatement" containment="true" unsettable="true" required="true"
     * @generated
     */
    TableInDatabase getTargetTable();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetTable <em>Target Table</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Table</em>' containment reference.
     * @see #isSetTargetTable()
     * @see #unsetTargetTable()
     * @see #getTargetTable()
     * @generated
     */
  void setTargetTable(TableInDatabase value);

	/**
     * Unsets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetTable <em>Target Table</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetTargetTable()
     * @see #getTargetTable()
     * @see #setTargetTable(TableInDatabase)
     * @generated
     */
    void unsetTargetTable();

	/**
     * Returns whether the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetTable <em>Target Table</em>}' containment reference is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Target Table</em>' containment reference is set.
     * @see #unsetTargetTable()
     * @see #getTargetTable()
     * @see #setTargetTable(TableInDatabase)
     * @generated
     */
    boolean isSetTargetTable();

	/**
     * Returns the value of the '<em><b>Target Column List</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getInsertStatement <em>Insert Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Column List</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Column List</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryInsertStatement_TargetColumnList()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getInsertStatement
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn" opposite="insertStatement"
     * @generated
     */
    EList getTargetColumnList();

} // SQLInsertStatement
