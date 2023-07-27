/**
 * <copyright>
 * </copyright>
 *
 * $Id: CursorReference.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Cursor Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.CursorReference#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.CursorReference#getDeleteStatement <em>Delete Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getCursorReference()
 * @model
 * @generated
 */
public interface CursorReference extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Update Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getWhereCurrentOfClause <em>Where Current Of Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Update Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Update Statement</em>' container reference.
     * @see #setUpdateStatement(QueryUpdateStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getCursorReference_UpdateStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getWhereCurrentOfClause
     * @model opposite="whereCurrentOfClause"
     * @generated
     */
    QueryUpdateStatement getUpdateStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.CursorReference#getUpdateStatement <em>Update Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Statement</em>' container reference.
     * @see #getUpdateStatement()
     * @generated
     */
    void setUpdateStatement(QueryUpdateStatement value);

	/**
     * Returns the value of the '<em><b>Delete Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereCurrentOfClause <em>Where Current Of Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delete Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Delete Statement</em>' container reference.
     * @see #setDeleteStatement(QueryDeleteStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getCursorReference_DeleteStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereCurrentOfClause
     * @model opposite="whereCurrentOfClause"
     * @generated
     */
    QueryDeleteStatement getDeleteStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.CursorReference#getDeleteStatement <em>Delete Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Delete Statement</em>' container reference.
     * @see #getDeleteStatement()
     * @generated
     */
    void setDeleteStatement(QueryDeleteStatement value);

} // SQLCursorReference
