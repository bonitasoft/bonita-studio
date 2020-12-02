/**
 * <copyright>
 * </copyright>
 *
 * $Id: SearchConditionCombined.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Search Condition Combined</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getCombinedOperator <em>Combined Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getLeftCondition <em>Left Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getRightCondition <em>Right Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSearchConditionCombined()
 * @model
 * @generated
 */
public interface SearchConditionCombined extends QuerySearchCondition{
	/**
     * Returns the value of the '<em><b>Combined Operator</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Combined Operator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Combined Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator
     * @see #setCombinedOperator(SearchConditionCombinedOperator)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSearchConditionCombined_CombinedOperator()
     * @model
     * @generated
     */
    SearchConditionCombinedOperator getCombinedOperator();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getCombinedOperator <em>Combined Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Combined Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator
     * @see #getCombinedOperator()
     * @generated
     */
    void setCombinedOperator(SearchConditionCombinedOperator value);

	/**
     * Returns the value of the '<em><b>Left Condition</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedLeft <em>Combined Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Left Condition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Left Condition</em>' containment reference.
     * @see #setLeftCondition(QuerySearchCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSearchConditionCombined_LeftCondition()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedLeft
     * @model opposite="combinedLeft" containment="true" required="true"
     * @generated
     */
    QuerySearchCondition getLeftCondition();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getLeftCondition <em>Left Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Left Condition</em>' containment reference.
     * @see #getLeftCondition()
     * @generated
     */
    void setLeftCondition(QuerySearchCondition value);

	/**
     * Returns the value of the '<em><b>Right Condition</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedRight <em>Combined Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Right Condition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Right Condition</em>' containment reference.
     * @see #setRightCondition(QuerySearchCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSearchConditionCombined_RightCondition()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedRight
     * @model opposite="combinedRight" containment="true" required="true"
     * @generated
     */
    QuerySearchCondition getRightCondition();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getRightCondition <em>Right Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right Condition</em>' containment reference.
     * @see #getRightCondition()
     * @generated
     */
    void setRightCondition(QuerySearchCondition value);

} // SQLSearchConditionCombined
