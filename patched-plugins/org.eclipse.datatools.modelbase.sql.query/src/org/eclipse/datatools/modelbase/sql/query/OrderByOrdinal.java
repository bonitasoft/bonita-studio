/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderByOrdinal.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Order By Ordinal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal#getOrdinalValue <em>Ordinal Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderByOrdinal()
 * @model
 * @generated
 */
public interface OrderByOrdinal extends OrderBySpecification{
	/**
     * Returns the value of the '<em><b>Ordinal Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ordinal Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ordinal Value</em>' attribute.
     * @see #setOrdinalValue(int)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderByOrdinal_OrdinalValue()
     * @model
     * @generated
     */
    int getOrdinalValue();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal#getOrdinalValue <em>Ordinal Value</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ordinal Value</em>' attribute.
     * @see #getOrdinalValue()
     * @generated
     */
  void setOrdinalValue(int value);

} // SQLOrderByOrdinal
