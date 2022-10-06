/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateLike.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate Like</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#isNotLike <em>Not Like</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getPatternValueExpr <em>Pattern Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getMatchingValueExpr <em>Matching Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getEscapeValueExpr <em>Escape Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateLike()
 * @model
 * @generated
 */
public interface PredicateLike extends Predicate{
	/**
     * Returns the value of the '<em><b>Not Like</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Not Like</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Not Like</em>' attribute.
     * @see #setNotLike(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateLike_NotLike()
     * @model
     * @generated
     */
    boolean isNotLike();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#isNotLike <em>Not Like</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Not Like</em>' attribute.
     * @see #isNotLike()
     * @generated
     */
    void setNotLike(boolean value);

	/**
     * Returns the value of the '<em><b>Pattern Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikePattern <em>Like Pattern</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Value Expr</em>' containment reference.
     * @see #setPatternValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateLike_PatternValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikePattern
     * @model opposite="likePattern" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getPatternValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getPatternValueExpr <em>Pattern Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Value Expr</em>' containment reference.
     * @see #getPatternValueExpr()
     * @generated
     */
    void setPatternValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Matching Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeMatching <em>Like Matching</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Matching Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Matching Value Expr</em>' containment reference.
     * @see #setMatchingValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateLike_MatchingValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeMatching
     * @model opposite="likeMatching" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getMatchingValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getMatchingValueExpr <em>Matching Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Matching Value Expr</em>' containment reference.
     * @see #getMatchingValueExpr()
     * @generated
     */
    void setMatchingValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Escape Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeEscape <em>Like Escape</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Escape Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Escape Value Expr</em>' containment reference.
     * @see #setEscapeValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateLike_EscapeValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeEscape
     * @model opposite="likeEscape" containment="true"
     * @generated
     */
    QueryValueExpression getEscapeValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getEscapeValueExpr <em>Escape Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Escape Value Expr</em>' containment reference.
     * @see #getEscapeValueExpr()
     * @generated
     */
    void setEscapeValueExpr(QueryValueExpression value);

} // SQLPredicateLike
