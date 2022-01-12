/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupElement.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Super Group Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElement#getSuperGroup <em>Super Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroupElement()
 * @model abstract="true"
 * @generated
 */
public interface SuperGroupElement extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Super Group</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSuperGroupElementList <em>Super Group Element List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Super Group</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Super Group</em>' container reference.
     * @see #setSuperGroup(SuperGroup)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroupElement_SuperGroup()
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSuperGroupElementList
     * @model opposite="superGroupElementList"
     * @generated
     */
    SuperGroup getSuperGroup();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElement#getSuperGroup <em>Super Group</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Super Group</em>' container reference.
     * @see #getSuperGroup()
     * @generated
     */
    void setSuperGroup(SuperGroup value);

} // SQLSuperGroupElement
