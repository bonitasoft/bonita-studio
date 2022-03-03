/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResultColumn.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Result Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ResultColumn#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ResultColumn#getOrderByResultCol <em>Order By Result Col</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getResultColumn()
 * @model
 * @generated
 */
public interface ResultColumn extends QueryResultSpecification{
	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getResultColumn <em>Result Column</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getResultColumn_ValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getResultColumn
     * @model opposite="resultColumn" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ResultColumn#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Order By Result Col</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn#getResultCol <em>Result Col</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Order By Result Col</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Order By Result Col</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getResultColumn_OrderByResultCol()
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn#getResultCol
     * @model type="org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn" opposite="resultCol"
     * @generated
     */
    EList getOrderByResultCol();

} // SQLResultColumn
