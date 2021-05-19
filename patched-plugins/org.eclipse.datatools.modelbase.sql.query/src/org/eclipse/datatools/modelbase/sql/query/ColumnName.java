/**
 * <copyright>
 * </copyright>
 *
 * $Id: ColumnName.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Column Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ColumnName#getTableCorrelation <em>Table Correlation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ColumnName#getWithTableSpecification <em>With Table Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getColumnName()
 * @model
 * @generated
 */
public interface ColumnName extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Table Correlation</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getColumnNameList <em>Column Name List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Correlation</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Correlation</em>' container reference.
     * @see #setTableCorrelation(TableCorrelation)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getColumnName_TableCorrelation()
     * @see org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getColumnNameList
     * @model opposite="columnNameList"
     * @generated
     */
    TableCorrelation getTableCorrelation();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ColumnName#getTableCorrelation <em>Table Correlation</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Correlation</em>' container reference.
     * @see #getTableCorrelation()
     * @generated
     */
    void setTableCorrelation(TableCorrelation value);

	/**
     * Returns the value of the '<em><b>With Table Specification</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getColumnNameList <em>Column Name List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>With Table Specification</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>With Table Specification</em>' container reference.
     * @see #setWithTableSpecification(WithTableSpecification)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getColumnName_WithTableSpecification()
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getColumnNameList
     * @model opposite="columnNameList" required="true"
     * @generated
     */
    WithTableSpecification getWithTableSpecification();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ColumnName#getWithTableSpecification <em>With Table Specification</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>With Table Specification</em>' container reference.
     * @see #getWithTableSpecification()
     * @generated
     */
    void setWithTableSpecification(WithTableSpecification value);

} // SQLColumnName
