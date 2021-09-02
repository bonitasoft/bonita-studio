/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCaseSearch.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Case Search</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch#getSearchContentList <em>Search Content List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSearch()
 * @model
 * @generated
 */
public interface ValueExpressionCaseSearch extends ValueExpressionCase{
	/**
     * Returns the value of the '<em><b>Search Content List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExprCaseSearch <em>Value Expr Case Search</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Search Content List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Search Content List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSearch_SearchContentList()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExprCaseSearch
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent" opposite="valueExprCaseSearch" containment="true" required="true"
     * @generated
     */
    EList getSearchContentList();

} // SQLValueExpressionCaseSearch
