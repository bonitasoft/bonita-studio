/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionVariable.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable#getQuerySelect <em>Query Select</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionVariable()
 * @model
 * @generated
 */
public interface ValueExpressionVariable extends ValueExpressionAtomic{
	/**
     * Returns the value of the '<em><b>Query Select</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getIntoClause <em>Into Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Select</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Select</em>' container reference.
     * @see #setQuerySelect(QuerySelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionVariable_QuerySelect()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getIntoClause
     * @model opposite="intoClause"
     * @generated
     */
    QuerySelect getQuerySelect();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable#getQuerySelect <em>Query Select</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Select</em>' container reference.
     * @see #getQuerySelect()
     * @generated
     */
    void setQuerySelect(QuerySelect value);

} // SQLValueExpressionVariable
