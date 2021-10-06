/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableJoined.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Table Joined</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinOperator <em>Join Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinCondition <em>Join Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefRight <em>Table Ref Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefLeft <em>Table Ref Left</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableJoined()
 * @model
 * @generated
 */
public interface TableJoined extends TableReference{
	/**
     * Returns the value of the '<em><b>Join Operator</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Join Operator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Join Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator
     * @see #setJoinOperator(TableJoinedOperator)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableJoined_JoinOperator()
     * @model
     * @generated
     */
    TableJoinedOperator getJoinOperator();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinOperator <em>Join Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Join Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator
     * @see #getJoinOperator()
     * @generated
     */
    void setJoinOperator(TableJoinedOperator value);

	/**
     * Returns the value of the '<em><b>Join Condition</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getTableJoined <em>Table Joined</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Join Condition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Join Condition</em>' containment reference.
     * @see #setJoinCondition(QuerySearchCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableJoined_JoinCondition()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getTableJoined
     * @model opposite="tableJoined" containment="true"
     * @generated
     */
    QuerySearchCondition getJoinCondition();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinCondition <em>Join Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Join Condition</em>' containment reference.
     * @see #getJoinCondition()
     * @generated
     */
    void setJoinCondition(QuerySearchCondition value);

	/**
     * Returns the value of the '<em><b>Table Ref Right</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedRight <em>Table Joined Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Ref Right</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Ref Right</em>' containment reference.
     * @see #setTableRefRight(TableReference)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableJoined_TableRefRight()
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedRight
     * @model opposite="tableJoinedRight" containment="true" required="true"
     * @generated
     */
    TableReference getTableRefRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefRight <em>Table Ref Right</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Ref Right</em>' containment reference.
     * @see #getTableRefRight()
     * @generated
     */
    void setTableRefRight(TableReference value);

	/**
     * Returns the value of the '<em><b>Table Ref Left</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedLeft <em>Table Joined Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Ref Left</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Ref Left</em>' containment reference.
     * @see #setTableRefLeft(TableReference)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableJoined_TableRefLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedLeft
     * @model opposite="tableJoinedLeft" containment="true" required="true"
     * @generated
     */
    TableReference getTableRefLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefLeft <em>Table Ref Left</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Ref Left</em>' containment reference.
     * @see #getTableRefLeft()
     * @generated
     */
    void setTableRefLeft(TableReference value);

} // SQLTableJoined
