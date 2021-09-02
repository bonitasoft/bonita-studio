/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionSimple.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Simple</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionSimple()
 * @model
 * @generated
 */
public interface ValueExpressionSimple extends ValueExpressionAtomic{
	/**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionSimple_Value()
     * @model
     * @generated
     */
    String getValue();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // SQLValueExpressionSimple
