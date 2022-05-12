/**
 * <copyright>
 * </copyright>
 *
 * $Id: QuerySelect.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Select</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#isDistinct <em>Distinct</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getHavingClause <em>Having Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getWhereClause <em>Where Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getGroupByClause <em>Group By Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getSelectClause <em>Select Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getFromClause <em>From Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getIntoClause <em>Into Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySelect()
 * @model
 * @generated
 */
public interface QuerySelect extends QueryExpressionBody{
	/**
     * Returns the value of the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Distinct</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Distinct</em>' attribute.
     * @see #setDistinct(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySelect_Distinct()
     * @model
     * @generated
     */
    boolean isDistinct();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#isDistinct <em>Distinct</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Distinct</em>' attribute.
     * @see #isDistinct()
     * @generated
     */
    void setDistinct(boolean value);

	/**
     * Returns the value of the '<em><b>Having Clause</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectHaving <em>Query Select Having</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Having Clause</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Having Clause</em>' containment reference.
     * @see #isSetHavingClause()
     * @see #unsetHavingClause()
     * @see #setHavingClause(QuerySearchCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySelect_HavingClause()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectHaving
     * @model opposite="querySelectHaving" containment="true" unsettable="true"
     * @generated
     */
    QuerySearchCondition getHavingClause();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getHavingClause <em>Having Clause</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Having Clause</em>' containment reference.
     * @see #isSetHavingClause()
     * @see #unsetHavingClause()
     * @see #getHavingClause()
     * @generated
     */
    void setHavingClause(QuerySearchCondition value);

	/**
     * Unsets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getHavingClause <em>Having Clause</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetHavingClause()
     * @see #getHavingClause()
     * @see #setHavingClause(QuerySearchCondition)
     * @generated
     */
    void unsetHavingClause();

	/**
     * Returns whether the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getHavingClause <em>Having Clause</em>}' containment reference is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Having Clause</em>' containment reference is set.
     * @see #unsetHavingClause()
     * @see #getHavingClause()
     * @see #setHavingClause(QuerySearchCondition)
     * @generated
     */
    boolean isSetHavingClause();

	/**
     * Returns the value of the '<em><b>Where Clause</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectWhere <em>Query Select Where</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Where Clause</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Where Clause</em>' containment reference.
     * @see #isSetWhereClause()
     * @see #unsetWhereClause()
     * @see #setWhereClause(QuerySearchCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySelect_WhereClause()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectWhere
     * @model opposite="querySelectWhere" containment="true" unsettable="true"
     * @generated
     */
    QuerySearchCondition getWhereClause();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getWhereClause <em>Where Clause</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Where Clause</em>' containment reference.
     * @see #isSetWhereClause()
     * @see #unsetWhereClause()
     * @see #getWhereClause()
     * @generated
     */
    void setWhereClause(QuerySearchCondition value);

	/**
     * Unsets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getWhereClause <em>Where Clause</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetWhereClause()
     * @see #getWhereClause()
     * @see #setWhereClause(QuerySearchCondition)
     * @generated
     */
    void unsetWhereClause();

	/**
     * Returns whether the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getWhereClause <em>Where Clause</em>}' containment reference is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Where Clause</em>' containment reference is set.
     * @see #unsetWhereClause()
     * @see #getWhereClause()
     * @see #setWhereClause(QuerySearchCondition)
     * @generated
     */
    boolean isSetWhereClause();

	/**
     * Returns the value of the '<em><b>Group By Clause</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.GroupingSpecification}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSpecification#getQuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group By Clause</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Group By Clause</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySelect_GroupByClause()
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSpecification#getQuerySelect
     * @model type="org.eclipse.datatools.modelbase.sql.query.GroupingSpecification" opposite="querySelect" containment="true"
     * @generated
     */
    EList getGroupByClause();

	/**
     * Returns the value of the '<em><b>Select Clause</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification#getQuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Select Clause</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Select Clause</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySelect_SelectClause()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification#getQuerySelect
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification" opposite="querySelect" containment="true"
     * @generated
     */
    EList getSelectClause();

	/**
     * Returns the value of the '<em><b>From Clause</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.TableReference}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getQuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>From Clause</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>From Clause</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySelect_FromClause()
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getQuerySelect
     * @model type="org.eclipse.datatools.modelbase.sql.query.TableReference" opposite="querySelect" containment="true" required="true"
     * @generated
     */
    EList getFromClause();

	/**
     * Returns the value of the '<em><b>Into Clause</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable#getQuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Into Clause</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Into Clause</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQuerySelect_IntoClause()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable#getQuerySelect
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable" opposite="querySelect" containment="true"
     * @generated
     */
    EList getIntoClause();

} // SQLQuerySelect
