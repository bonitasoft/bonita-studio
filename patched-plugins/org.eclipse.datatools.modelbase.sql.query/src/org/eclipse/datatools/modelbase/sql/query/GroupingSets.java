/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSets.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Grouping Sets</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.GroupingSets#getGroupingSetsElementList <em>Grouping Sets Element List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingSets()
 * @model
 * @generated
 */
public interface GroupingSets extends GroupingSpecification{
	/**
     * Returns the value of the '<em><b>Grouping Sets Element List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement#getGroupingSets <em>Grouping Sets</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grouping Sets Element List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grouping Sets Element List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingSets_GroupingSetsElementList()
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement#getGroupingSets
     * @model type="org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement" opposite="groupingSets" containment="true" required="true"
     * @generated
     */
    EList getGroupingSetsElementList();

} // SQLGroupingSets
