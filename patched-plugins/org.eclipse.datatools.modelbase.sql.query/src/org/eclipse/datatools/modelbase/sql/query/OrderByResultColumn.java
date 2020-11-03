/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderByResultColumn.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Order By Result Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn#getResultCol <em>Result Col</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderByResultColumn()
 * @model
 * @generated
 */
public interface OrderByResultColumn extends OrderBySpecification{
	/**
     * Returns the value of the '<em><b>Result Col</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ResultColumn#getOrderByResultCol <em>Order By Result Col</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Result Col</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Result Col</em>' reference.
     * @see #setResultCol(ResultColumn)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderByResultColumn_ResultCol()
     * @see org.eclipse.datatools.modelbase.sql.query.ResultColumn#getOrderByResultCol
     * @model opposite="orderByResultCol" required="true"
     * @generated
     */
  ResultColumn getResultCol();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn#getResultCol <em>Result Col</em>}' reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Result Col</em>' reference.
     * @see #getResultCol()
     * @generated
     */
  void setResultCol(ResultColumn value);

} // OrderByResultColumn
