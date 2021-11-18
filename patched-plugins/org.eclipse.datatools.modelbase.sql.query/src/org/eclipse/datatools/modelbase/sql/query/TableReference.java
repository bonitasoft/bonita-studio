/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableReference.java,v 1.3 2008/01/31 02:57:16 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Table Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedRight <em>Table Joined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedLeft <em>Table Joined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getQuerySelect <em>Query Select</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getNest <em>Nest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getMergeSourceTable <em>Merge Source Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableReference()
 * @model abstract="true"
 * @generated
 */
public interface TableReference extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Table Joined Right</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefRight <em>Table Ref Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Joined Right</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Joined Right</em>' container reference.
     * @see #setTableJoinedRight(TableJoined)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableReference_TableJoinedRight()
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefRight
     * @model opposite="tableRefRight" required="true"
     * @generated
     */
    TableJoined getTableJoinedRight();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedRight <em>Table Joined Right</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Joined Right</em>' container reference.
     * @see #getTableJoinedRight()
     * @generated
     */
    void setTableJoinedRight(TableJoined value);

	/**
     * Returns the value of the '<em><b>Table Joined Left</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefLeft <em>Table Ref Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Joined Left</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Joined Left</em>' container reference.
     * @see #setTableJoinedLeft(TableJoined)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableReference_TableJoinedLeft()
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefLeft
     * @model opposite="tableRefLeft" required="true"
     * @generated
     */
    TableJoined getTableJoinedLeft();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedLeft <em>Table Joined Left</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Joined Left</em>' container reference.
     * @see #getTableJoinedLeft()
     * @generated
     */
    void setTableJoinedLeft(TableJoined value);

	/**
     * Returns the value of the '<em><b>Query Select</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getFromClause <em>From Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Select</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Select</em>' container reference.
     * @see #setQuerySelect(QuerySelect)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableReference_QuerySelect()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getFromClause
     * @model opposite="fromClause"
     * @generated
     */
    QuerySelect getQuerySelect();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getQuerySelect <em>Query Select</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Select</em>' container reference.
     * @see #getQuerySelect()
     * @generated
     */
    void setQuerySelect(QuerySelect value);

	/**
     * Returns the value of the '<em><b>Nest</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableNested#getNestedTableRef <em>Nested Table Ref</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nest</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Nest</em>' container reference.
     * @see #setNest(TableNested)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableReference_Nest()
     * @see org.eclipse.datatools.modelbase.sql.query.TableNested#getNestedTableRef
     * @model opposite="nestedTableRef"
     * @generated
     */
  TableNested getNest();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getNest <em>Nest</em>}' container reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nest</em>' container reference.
     * @see #getNest()
     * @generated
     */
  void setNest(TableNested value);

    /**
     * Returns the value of the '<em><b>Merge Source Table</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getTableRef <em>Table Ref</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Merge Source Table</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Merge Source Table</em>' container reference.
     * @see #setMergeSourceTable(MergeSourceTable)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableReference_MergeSourceTable()
     * @see org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getTableRef
     * @model opposite="tableRef"
     * @generated
     */
    MergeSourceTable getMergeSourceTable();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getMergeSourceTable <em>Merge Source Table</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Merge Source Table</em>' container reference.
     * @see #getMergeSourceTable()
     * @generated
     */
    void setMergeSourceTable(MergeSourceTable value);

} // SQLTableReference
