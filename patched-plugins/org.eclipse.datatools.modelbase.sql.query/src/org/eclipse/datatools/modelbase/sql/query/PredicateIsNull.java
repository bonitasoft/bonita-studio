/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateIsNull.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate Null</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#isNotNull <em>Not Null</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateIsNull()
 * @model
 * @generated
 */
public interface PredicateIsNull extends Predicate{
	/**
     * Returns the value of the '<em><b>Not Null</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Not Null</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Not Null</em>' attribute.
     * @see #setNotNull(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateIsNull_NotNull()
     * @model
     * @generated
     */
    boolean isNotNull();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#isNotNull <em>Not Null</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Not Null</em>' attribute.
     * @see #isNotNull()
     * @generated
     */
    void setNotNull(boolean value);

	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getPredicateNull <em>Predicate Null</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateIsNull_ValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getPredicateNull
     * @model opposite="predicateNull" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // SQLPredicateNull
