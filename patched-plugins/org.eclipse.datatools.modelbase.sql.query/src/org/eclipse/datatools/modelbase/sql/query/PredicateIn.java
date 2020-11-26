/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateIn.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate In</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateIn#isNotIn <em>Not In</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateIn()
 * @model abstract="true"
 * @generated
 */
public interface PredicateIn extends Predicate{
	/**
     * Returns the value of the '<em><b>Not In</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Not In</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Not In</em>' attribute.
     * @see #setNotIn(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateIn_NotIn()
     * @model
     * @generated
     */
    boolean isNotIn();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIn#isNotIn <em>Not In</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Not In</em>' attribute.
     * @see #isNotIn()
     * @generated
     */
    void setNotIn(boolean value);

} // SQLPredicateIn
