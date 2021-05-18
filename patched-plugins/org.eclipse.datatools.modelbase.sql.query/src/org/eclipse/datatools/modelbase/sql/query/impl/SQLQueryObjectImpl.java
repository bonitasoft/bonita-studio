/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLQueryObjectImpl.java,v 1.5 2007/02/08 17:00:30 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceWriter;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class SQLQueryObjectImpl extends SQLObjectImpl implements SQLQueryObject {
    private SQLQuerySourceInfo sourceInfo = null;
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SQLQueryObjectImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.SQL_QUERY_OBJECT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getSQL() {
        SQLQuerySourceWriter sw = new SQLQuerySourceWriter();
        return sw.getSQL(this);
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setSQL(String sqlText) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * @inheritDoc org.eclipse.datatools.modelbase.sql.query.SQLQueryObject#getSourceInfo()
     * @generated NOT
     */
    public SQLQuerySourceInfo getSourceInfo()
    {
        if (this.sourceInfo == null)
        {
            this.sourceInfo = new SQLQuerySourceInfo(this);
        } 
        else if (this.sourceInfo.getQueryObjectBackReference() != this)
        {
            // the weak reference might have been garbage collected, but it might be needed
            this.sourceInfo.setQueryObjectBackReference(this);
        }
        
        return this.sourceInfo;
    }


    /**
     * @param sourceInfo The sourceInfo to set.
     * @generated NOT
     */
    public void setSourceInfo(SQLQuerySourceInfo sourceInfo)
    {
        this.sourceInfo = sourceInfo;
        if (sourceInfo != null)
        {
            this.sourceInfo.setQueryObjectBackReference(this);
        }
    }
    
} //SQLQueryObjectImpl
