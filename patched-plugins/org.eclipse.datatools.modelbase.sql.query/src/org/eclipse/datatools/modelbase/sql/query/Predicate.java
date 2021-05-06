/**
 * <copyright>
 * </copyright>
 *
 * $Id: Predicate.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.Predicate#isNegatedPredicate <em>Negated Predicate</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.Predicate#isHasSelectivity <em>Has Selectivity</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.Predicate#getSelectivityValue <em>Selectivity Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicate()
 * @model abstract="true"
 * @generated
 */
public interface Predicate extends QuerySearchCondition{
	/**
     * Returns the value of the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * This attribute adds the NOT keyword in front of a predicate, so when true you have NOT <predicate>.  If attribute "paren" in the superclass is also true, you get ( NOT <predicate> ).  If attribute "notParen" in the superclass is true, you get NOT ( NOT <predicate> ).
     * <!-- end-model-doc -->
     * @return the value of the '<em>Negated Predicate</em>' attribute.
     * @see #setNegatedPredicate(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicate_NegatedPredicate()
     * @model
     * @generated
     */
    boolean isNegatedPredicate();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.Predicate#isNegatedPredicate <em>Negated Predicate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Negated Predicate</em>' attribute.
     * @see #isNegatedPredicate()
     * @generated
     */
    void setNegatedPredicate(boolean value);

	/**
     * Returns the value of the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Has Selectivity</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Has Selectivity</em>' attribute.
     * @see #setHasSelectivity(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicate_HasSelectivity()
     * @model
     * @generated
     */
    boolean isHasSelectivity();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.Predicate#isHasSelectivity <em>Has Selectivity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Has Selectivity</em>' attribute.
     * @see #isHasSelectivity()
     * @generated
     */
    void setHasSelectivity(boolean value);

	/**
     * Returns the value of the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Selectivity Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Selectivity Value</em>' attribute.
     * @see #setSelectivityValue(Integer)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicate_SelectivityValue()
     * @model
     * @generated
     */
    Integer getSelectivityValue();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.Predicate#getSelectivityValue <em>Selectivity Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Selectivity Value</em>' attribute.
     * @see #getSelectivityValue()
     * @generated
     */
    void setSelectivityValue(Integer value);

} // SQLPredicate
