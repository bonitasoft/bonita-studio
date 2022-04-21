/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValuesRow.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Values Row</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getInsertStatement <em>Insert Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getExprList <em>Expr List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getQueryValues <em>Query Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValuesRow()
 * @model
 * @generated
 */
public interface ValuesRow extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Insert Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceValuesRowList <em>Source Values Row List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Insert Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Insert Statement</em>' container reference.
     * @see #setInsertStatement(QueryInsertStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValuesRow_InsertStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceValuesRowList
     * @model opposite="sourceValuesRowList"
     * @generated
     */
    QueryInsertStatement getInsertStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getInsertStatement <em>Insert Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Insert Statement</em>' container reference.
     * @see #getInsertStatement()
     * @generated
     */
    void setInsertStatement(QueryInsertStatement value);

	/**
     * Returns the value of the '<em><b>Expr List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValuesRow <em>Values Row</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expr List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expr List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValuesRow_ExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValuesRow
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryValueExpression" opposite="valuesRow" containment="true" required="true"
     * @generated
     */
    EList getExprList();

	/**
     * Returns the value of the '<em><b>Query Values</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValues#getValuesRowList <em>Values Row List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Values</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Values</em>' container reference.
     * @see #setQueryValues(QueryValues)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValuesRow_QueryValues()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValues#getValuesRowList
     * @model opposite="valuesRowList" required="true"
     * @generated
     */
    QueryValues getQueryValues();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getQueryValues <em>Query Values</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Values</em>' container reference.
     * @see #getQueryValues()
     * @generated
     */
    void setQueryValues(QueryValues value);

} // SQLValuesRow
