/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLQueryObject.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSQLQueryObject()
 * @model abstract="true"
 * @generated
 */
public interface SQLQueryObject extends SQLObject{
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    String getSQL();

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @model
     * @generated
     */
  void setSQL(String sqlText);

    /** Returns the associated {@link SQLQuerySourceInfo} */
    SQLQuerySourceInfo getSourceInfo(); 
    
    /** Sets the {@link SQLQuerySourceInfo} */
    void setSourceInfo(SQLQuerySourceInfo sourceInfo); 
    
} // SQLQueryObject
