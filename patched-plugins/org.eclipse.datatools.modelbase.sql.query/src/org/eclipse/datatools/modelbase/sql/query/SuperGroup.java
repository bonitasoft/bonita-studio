/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroup.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Super Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSuperGroupType <em>Super Group Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSuperGroupElementList <em>Super Group Element List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroup()
 * @model
 * @generated
 */
public interface SuperGroup extends Grouping{
	/**
     * Returns the value of the '<em><b>Super Group Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.SuperGroupType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Super Group Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Super Group Type</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupType
     * @see #setSuperGroupType(SuperGroupType)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroup_SuperGroupType()
     * @model
     * @generated
     */
    SuperGroupType getSuperGroupType();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSuperGroupType <em>Super Group Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Super Group Type</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupType
     * @see #getSuperGroupType()
     * @generated
     */
    void setSuperGroupType(SuperGroupType value);

	/**
     * Returns the value of the '<em><b>Super Group Element List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElement}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElement#getSuperGroup <em>Super Group</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Super Group Element List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Super Group Element List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroup_SuperGroupElementList()
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElement#getSuperGroup
     * @model type="org.eclipse.datatools.modelbase.sql.query.SuperGroupElement" opposite="superGroup" containment="true"
     * @generated
     */
    EList getSuperGroupElementList();

} // SQLSuperGroup
