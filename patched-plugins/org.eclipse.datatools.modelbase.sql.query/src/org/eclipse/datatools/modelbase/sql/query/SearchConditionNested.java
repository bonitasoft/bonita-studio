/**
 * <copyright>
 * </copyright>
 *
 * $Id: SearchConditionNested.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Search Condition Nested</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionNested#getNestedCondition <em>Nested Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSearchConditionNested()
 * @model
 * @generated
 */
public interface SearchConditionNested extends QuerySearchCondition{
	/**
     * Returns the value of the '<em><b>Nested Condition</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getNest <em>Nest</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nested Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Nested Condition</em>' containment reference.
     * @see #setNestedCondition(QuerySearchCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSearchConditionNested_NestedCondition()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getNest
     * @model opposite="nest" containment="true" required="true"
     * @generated
     */
  QuerySearchCondition getNestedCondition();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionNested#getNestedCondition <em>Nested Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nested Condition</em>' containment reference.
     * @see #getNestedCondition()
     * @generated
     */
  void setNestedCondition(QuerySearchCondition value);

} // SQLSearchConditionNested
