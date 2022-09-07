/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryDeleteStatement.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Delete Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereCurrentOfClause <em>Where Current Of Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereClause <em>Where Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getTargetTable <em>Target Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryDeleteStatement()
 * @model
 * @generated
 */
public interface QueryDeleteStatement extends QueryChangeStatement{
	/**
     * Returns the value of the '<em><b>Where Current Of Clause</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.CursorReference#getDeleteStatement <em>Delete Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Where Current Of Clause</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Where Current Of Clause</em>' containment reference.
     * @see #setWhereCurrentOfClause(CursorReference)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryDeleteStatement_WhereCurrentOfClause()
     * @see org.eclipse.datatools.modelbase.sql.query.CursorReference#getDeleteStatement
     * @model opposite="deleteStatement" containment="true"
     * @generated
     */
    CursorReference getWhereCurrentOfClause();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereCurrentOfClause <em>Where Current Of Clause</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Where Current Of Clause</em>' containment reference.
     * @see #getWhereCurrentOfClause()
     * @generated
     */
    void setWhereCurrentOfClause(CursorReference value);

	/**
     * Returns the value of the '<em><b>Where Clause</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getDeleteStatement <em>Delete Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Where Clause</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Where Clause</em>' containment reference.
     * @see #isSetWhereClause()
     * @see #unsetWhereClause()
     * @see #setWhereClause(QuerySearchCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryDeleteStatement_WhereClause()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getDeleteStatement
     * @model opposite="deleteStatement" containment="true" unsettable="true"
     * @generated
     */
    QuerySearchCondition getWhereClause();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereClause <em>Where Clause</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Where Clause</em>' containment reference.
     * @see #isSetWhereClause()
     * @see #unsetWhereClause()
     * @see #getWhereClause()
     * @generated
     */
    void setWhereClause(QuerySearchCondition value);

	/**
     * Unsets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereClause <em>Where Clause</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetWhereClause()
     * @see #getWhereClause()
     * @see #setWhereClause(QuerySearchCondition)
     * @generated
     */
    void unsetWhereClause();

	/**
     * Returns whether the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereClause <em>Where Clause</em>}' containment reference is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Where Clause</em>' containment reference is set.
     * @see #unsetWhereClause()
     * @see #getWhereClause()
     * @see #setWhereClause(QuerySearchCondition)
     * @generated
     */
    boolean isSetWhereClause();

	/**
     * Returns the value of the '<em><b>Target Table</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDeleteStatement <em>Delete Statement</em>}'.
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
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryDeleteStatement_TargetTable()
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDeleteStatement
     * @model opposite="deleteStatement" containment="true" unsettable="true" required="true"
     * @generated
     */
    TableInDatabase getTargetTable();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getTargetTable <em>Target Table</em>}' containment reference.
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
     * Unsets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getTargetTable <em>Target Table</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetTargetTable()
     * @see #getTargetTable()
     * @see #setTargetTable(TableInDatabase)
     * @generated
     */
    void unsetTargetTable();

	/**
     * Returns whether the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getTargetTable <em>Target Table</em>}' containment reference is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Target Table</em>' containment reference is set.
     * @see #unsetTargetTable()
     * @see #getTargetTable()
     * @see #setTargetTable(TableInDatabase)
     * @generated
     */
    boolean isSetTargetTable();

} // SQLDeleteStatement
