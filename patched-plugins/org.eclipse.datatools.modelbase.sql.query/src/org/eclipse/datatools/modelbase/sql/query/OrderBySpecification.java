/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderBySpecification.java,v 1.3 2008/01/31 02:57:16 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Order By Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#isDescending <em>Descending</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getOrderingSpecOption <em>Ordering Spec Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getNullOrderingOption <em>Null Ordering Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getSelectStatement <em>Select Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getQuery <em>Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderBySpecification()
 * @model abstract="true"
 * @generated
 */
public interface OrderBySpecification extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Descending</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Descending</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Descending</em>' attribute.
     * @see #setDescending(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderBySpecification_Descending()
     * @model
     * @generated
     */
    boolean isDescending();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#isDescending <em>Descending</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Descending</em>' attribute.
     * @see #isDescending()
     * @generated
     */
    void setDescending(boolean value);

	/**
     * Returns the value of the '<em><b>Ordering Spec Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.OrderingSpecType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ordering Spec Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ordering Spec Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderingSpecType
     * @see #setOrderingSpecOption(OrderingSpecType)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderBySpecification_OrderingSpecOption()
     * @model
     * @generated
     */
    OrderingSpecType getOrderingSpecOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getOrderingSpecOption <em>Ordering Spec Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ordering Spec Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderingSpecType
     * @see #getOrderingSpecOption()
     * @generated
     */
    void setOrderingSpecOption(OrderingSpecType value);

	/**
     * Returns the value of the '<em><b>Null Ordering Option</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.NullOrderingType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Null Ordering Option</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Null Ordering Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.NullOrderingType
     * @see #setNullOrderingOption(NullOrderingType)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderBySpecification_NullOrderingOption()
     * @model
     * @generated
     */
    NullOrderingType getNullOrderingOption();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getNullOrderingOption <em>Null Ordering Option</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Null Ordering Option</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.NullOrderingType
     * @see #getNullOrderingOption()
     * @generated
     */
    void setNullOrderingOption(NullOrderingType value);

	/**
     * Returns the value of the '<em><b>Select Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getOrderByClause <em>Order By Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Select Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Select Statement</em>' container reference.
     * @see #setSelectStatement(QuerySelectStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderBySpecification_SelectStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getOrderByClause
     * @model opposite="orderByClause"
     * @generated
     */
    QuerySelectStatement getSelectStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getSelectStatement <em>Select Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Select Statement</em>' container reference.
     * @see #getSelectStatement()
     * @generated
     */
    void setSelectStatement(QuerySelectStatement value);

    /**
     * Returns the value of the '<em><b>Query</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getSortSpecList <em>Sort Spec List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' container reference.
     * @see #setQuery(QueryExpressionBody)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderBySpecification_Query()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getSortSpecList
     * @model opposite="sortSpecList"
     * @generated
     */
    QueryExpressionBody getQuery();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getQuery <em>Query</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' container reference.
     * @see #getQuery()
     * @generated
     */
    void setQuery(QueryExpressionBody value);

} // SQLOrderBySpecification
