/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableNested.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Table Nested</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableNested#getNestedTableRef <em>Nested Table Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableNested()
 * @model
 * @generated
 */
public interface TableNested extends TableReference{
	/**
     * Returns the value of the '<em><b>Nested Table Ref</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getNest <em>Nest</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nested Table Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Nested Table Ref</em>' containment reference.
     * @see #setNestedTableRef(TableReference)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableNested_NestedTableRef()
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getNest
     * @model opposite="nest" containment="true" required="true"
     * @generated
     */
  TableReference getNestedTableRef();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableNested#getNestedTableRef <em>Nested Table Ref</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nested Table Ref</em>' containment reference.
     * @see #getNestedTableRef()
     * @generated
     */
  void setNestedTableRef(TableReference value);

} // SQLTableNested
