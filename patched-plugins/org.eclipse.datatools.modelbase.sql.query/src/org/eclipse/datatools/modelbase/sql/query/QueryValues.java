/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryValues.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Values</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryValues#getValuesRowList <em>Values Row List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValues()
 * @model
 * @generated
 */
public interface QueryValues extends QueryExpressionBody{
	/**
     * Returns the value of the '<em><b>Values Row List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValuesRow}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getQueryValues <em>Query Values</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Values Row List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Values Row List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryValues_ValuesRowList()
     * @see org.eclipse.datatools.modelbase.sql.query.ValuesRow#getQueryValues
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValuesRow" opposite="queryValues" containment="true" required="true"
     * @generated
     */
    EList getValuesRowList();

} // SQLQueryValues
