/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableReferenceImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.MergeSourceTable;
import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
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
 * An implementation of the model object '<em><b>SQL Table Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl#getTableJoinedRight <em>Table Joined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl#getTableJoinedLeft <em>Table Joined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl#getQuerySelect <em>Query Select</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl#getNest <em>Nest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl#getMergeSourceTable <em>Merge Source Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TableReferenceImpl extends SQLQueryObjectImpl implements TableReference {
	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TableReferenceImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.TABLE_REFERENCE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableJoined getTableJoinedRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT) return null;
        return (TableJoined)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTableJoinedRight(TableJoined newTableJoinedRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTableJoinedRight, SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableJoinedRight(TableJoined newTableJoinedRight) {
        if (newTableJoinedRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT && newTableJoinedRight != null)) {
            if (EcoreUtil.isAncestor(this, newTableJoinedRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTableJoinedRight != null)
                msgs = ((InternalEObject)newTableJoinedRight).eInverseAdd(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
            msgs = basicSetTableJoinedRight(newTableJoinedRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, newTableJoinedRight, newTableJoinedRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableJoined getTableJoinedLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT) return null;
        return (TableJoined)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTableJoinedLeft(TableJoined newTableJoinedLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTableJoinedLeft, SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableJoinedLeft(TableJoined newTableJoinedLeft) {
        if (newTableJoinedLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT && newTableJoinedLeft != null)) {
            if (EcoreUtil.isAncestor(this, newTableJoinedLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTableJoinedLeft != null)
                msgs = ((InternalEObject)newTableJoinedLeft).eInverseAdd(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
            msgs = basicSetTableJoinedLeft(newTableJoinedLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, newTableJoinedLeft, newTableJoinedLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySelect getQuerySelect() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT) return null;
        return (QuerySelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQuerySelect(QuerySelect newQuerySelect, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuerySelect, SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuerySelect(QuerySelect newQuerySelect) {
        if (newQuerySelect != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT && newQuerySelect != null)) {
            if (EcoreUtil.isAncestor(this, newQuerySelect))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuerySelect != null)
                msgs = ((InternalEObject)newQuerySelect).eInverseAdd(this, SQLQueryModelPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
            msgs = basicSetQuerySelect(newQuerySelect, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT, newQuerySelect, newQuerySelect));
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public TableNested getNest() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__NEST) return null;
        return (TableNested)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetNest(TableNested newNest, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newNest, SQLQueryModelPackage.TABLE_REFERENCE__NEST, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setNest(TableNested newNest) {
        if (newNest != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__NEST && newNest != null)) {
            if (EcoreUtil.isAncestor(this, newNest))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newNest != null)
                msgs = ((InternalEObject)newNest).eInverseAdd(this, SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, TableNested.class, msgs);
            msgs = basicSetNest(newNest, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_REFERENCE__NEST, newNest, newNest));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeSourceTable getMergeSourceTable() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE) return null;
        return (MergeSourceTable)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMergeSourceTable(MergeSourceTable newMergeSourceTable, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newMergeSourceTable, SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMergeSourceTable(MergeSourceTable newMergeSourceTable) {
        if (newMergeSourceTable != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE && newMergeSourceTable != null)) {
            if (EcoreUtil.isAncestor(this, newMergeSourceTable))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newMergeSourceTable != null)
                msgs = ((InternalEObject)newMergeSourceTable).eInverseAdd(this, SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF, MergeSourceTable.class, msgs);
            msgs = basicSetMergeSourceTable(newMergeSourceTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE, newMergeSourceTable, newMergeSourceTable));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTableJoinedRight((TableJoined)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTableJoinedLeft((TableJoined)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuerySelect((QuerySelect)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__NEST:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetNest((TableNested)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetMergeSourceTable((MergeSourceTable)otherEnd, msgs);
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
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
                return basicSetTableJoinedRight(null, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
                return basicSetTableJoinedLeft(null, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT:
                return basicSetQuerySelect(null, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__NEST:
                return basicSetNest(null, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE:
                return basicSetMergeSourceTable(null, msgs);
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
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT, TableJoined.class, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT, TableJoined.class, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__FROM_CLAUSE, QuerySelect.class, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__NEST:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.TABLE_NESTED__NESTED_TABLE_REF, TableNested.class, msgs);
            case SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.MERGE_SOURCE_TABLE__TABLE_REF, MergeSourceTable.class, msgs);
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
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
                return getTableJoinedRight();
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
                return getTableJoinedLeft();
            case SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT:
                return getQuerySelect();
            case SQLQueryModelPackage.TABLE_REFERENCE__NEST:
                return getNest();
            case SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE:
                return getMergeSourceTable();
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
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
                setTableJoinedRight((TableJoined)newValue);
                return;
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
                setTableJoinedLeft((TableJoined)newValue);
                return;
            case SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT:
                setQuerySelect((QuerySelect)newValue);
                return;
            case SQLQueryModelPackage.TABLE_REFERENCE__NEST:
                setNest((TableNested)newValue);
                return;
            case SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE:
                setMergeSourceTable((MergeSourceTable)newValue);
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
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
                setTableJoinedRight((TableJoined)null);
                return;
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
                setTableJoinedLeft((TableJoined)null);
                return;
            case SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT:
                setQuerySelect((QuerySelect)null);
                return;
            case SQLQueryModelPackage.TABLE_REFERENCE__NEST:
                setNest((TableNested)null);
                return;
            case SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE:
                setMergeSourceTable((MergeSourceTable)null);
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
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT:
                return getTableJoinedRight() != null;
            case SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT:
                return getTableJoinedLeft() != null;
            case SQLQueryModelPackage.TABLE_REFERENCE__QUERY_SELECT:
                return getQuerySelect() != null;
            case SQLQueryModelPackage.TABLE_REFERENCE__NEST:
                return getNest() != null;
            case SQLQueryModelPackage.TABLE_REFERENCE__MERGE_SOURCE_TABLE:
                return getMergeSourceTable() != null;
        }
        return super.eIsSet(featureID);
    }

} //SQLTableReferenceImpl
