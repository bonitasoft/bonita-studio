/**
 * <copyright>
 * </copyright>
 *
 * $Id: ColumnNameImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Column Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ColumnNameImpl#getTableCorrelation <em>Table Correlation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ColumnNameImpl#getWithTableSpecification <em>With Table Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnNameImpl extends SQLQueryObjectImpl implements ColumnName {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ColumnNameImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.COLUMN_NAME;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableCorrelation getTableCorrelation() {
        if (eContainerFeatureID() != SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION) return null;
        return (TableCorrelation)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTableCorrelation(TableCorrelation newTableCorrelation, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTableCorrelation, SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableCorrelation(TableCorrelation newTableCorrelation) {
        if (newTableCorrelation != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION && newTableCorrelation != null)) {
            if (EcoreUtil.isAncestor(this, newTableCorrelation))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTableCorrelation != null)
                msgs = ((InternalEObject)newTableCorrelation).eInverseAdd(this, SQLQueryModelPackage.TABLE_CORRELATION__COLUMN_NAME_LIST, TableCorrelation.class, msgs);
            msgs = basicSetTableCorrelation(newTableCorrelation, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION, newTableCorrelation, newTableCorrelation));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WithTableSpecification getWithTableSpecification() {
        if (eContainerFeatureID() != SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION) return null;
        return (WithTableSpecification)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetWithTableSpecification(WithTableSpecification newWithTableSpecification, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newWithTableSpecification, SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWithTableSpecification(WithTableSpecification newWithTableSpecification) {
        if (newWithTableSpecification != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION && newWithTableSpecification != null)) {
            if (EcoreUtil.isAncestor(this, newWithTableSpecification))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newWithTableSpecification != null)
                msgs = ((InternalEObject)newWithTableSpecification).eInverseAdd(this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST, WithTableSpecification.class, msgs);
            msgs = basicSetWithTableSpecification(newWithTableSpecification, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION, newWithTableSpecification, newWithTableSpecification));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
            case SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetWithTableSpecification((WithTableSpecification)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION:
                return basicSetTableCorrelation(null, msgs);
            case SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
                return basicSetWithTableSpecification(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.TABLE_CORRELATION__COLUMN_NAME_LIST, TableCorrelation.class, msgs);
            case SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST, WithTableSpecification.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION:
                return getTableCorrelation();
            case SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
                return getWithTableSpecification();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION:
                setTableCorrelation((TableCorrelation)newValue);
                return;
            case SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
                setWithTableSpecification((WithTableSpecification)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID) {
        switch (featureID) {
            case SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION:
                setTableCorrelation((TableCorrelation)null);
                return;
            case SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
                setWithTableSpecification((WithTableSpecification)null);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION:
                return getTableCorrelation() != null;
            case SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION:
                return getWithTableSpecification() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLColumnNameImpl
