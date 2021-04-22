/**
 * <copyright>
 * </copyright>
 *
 * $Id: WithTableReference.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>With Table Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.WithTableReference#getWithTableSpecification <em>With Table Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getWithTableReference()
 * @model
 * @generated
 */
public interface WithTableReference extends TableExpression{
	/**
     * Returns the value of the '<em><b>With Table Specification</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableReferences <em>With Table References</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>With Table Specification</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>With Table Specification</em>' reference.
     * @see #setWithTableSpecification(WithTableSpecification)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getWithTableReference_WithTableSpecification()
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableReferences
     * @model opposite="withTableReferences" required="true"
     * @generated
     */
    WithTableSpecification getWithTableSpecification();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.WithTableReference#getWithTableSpecification <em>With Table Specification</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>With Table Specification</em>' reference.
     * @see #getWithTableSpecification()
     * @generated
     */
    void setWithTableSpecification(WithTableSpecification value);

} // WithTableReference
