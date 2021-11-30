/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.impl;

import java.util.Collection;
import java.util.Date;

import org.bonitasoft.studio.model.configuration.Configuration;

import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;

import org.bonitasoft.studio.model.parameter.Parameter;

import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.RecapFlow;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getKpis <em>Kpis</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getFormMapping <em>Form Mapping</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getOverviewFormMapping <em>Overview Form Mapping</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getModificationDate <em>Modification Date</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getDatatypes <em>Datatypes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getActors <em>Actors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractProcessImpl extends ContainerImpl implements AbstractProcess {
	/**
     * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getData()
     * @generated
     * @ordered
     */
	protected EList<Data> data;

	/**
     * The cached value of the '{@link #getConnectors() <em>Connectors</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConnectors()
     * @generated
     * @ordered
     */
	protected EList<Connector> connectors;

	/**
     * The cached value of the '{@link #getKpis() <em>Kpis</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getKpis()
     * @generated
     * @ordered
     */
	protected EList<AbstractKPIBinding> kpis;

	/**
     * The cached value of the '{@link #getFormMapping() <em>Form Mapping</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFormMapping()
     * @generated
     * @ordered
     */
	protected FormMapping formMapping;

	/**
     * The cached value of the '{@link #getOverviewFormMapping() <em>Overview Form Mapping</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOverviewFormMapping()
     * @generated
     * @ordered
     */
	protected FormMapping overviewFormMapping;

	/**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected static final String VERSION_EDEFAULT = "1.0"; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected String version = VERSION_EDEFAULT;

	/**
     * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAuthor()
     * @generated
     * @ordered
     */
	protected static final String AUTHOR_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAuthor()
     * @generated
     * @ordered
     */
	protected String author = AUTHOR_EDEFAULT;

	/**
     * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
	protected static final Date CREATION_DATE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
	protected Date creationDate = CREATION_DATE_EDEFAULT;

	/**
     * The default value of the '{@link #getModificationDate() <em>Modification Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getModificationDate()
     * @generated
     * @ordered
     */
	protected static final Date MODIFICATION_DATE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getModificationDate() <em>Modification Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getModificationDate()
     * @generated
     * @ordered
     */
	protected Date modificationDate = MODIFICATION_DATE_EDEFAULT;

	/**
     * The cached value of the '{@link #getDatatypes() <em>Datatypes</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDatatypes()
     * @generated
     * @ordered
     */
	protected EList<DataType> datatypes;

	/**
     * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConnections()
     * @generated
     * @ordered
     */
	protected EList<Connection> connections;

	/**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCategories()
     * @generated
     * @ordered
     */
	protected EList<String> categories;

	/**
     * The cached value of the '{@link #getActors() <em>Actors</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getActors()
     * @generated
     * @ordered
     */
	protected EList<Actor> actors;

	/**
     * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConfigurations()
     * @generated
     * @ordered
     */
	protected EList<Configuration> configurations;

	/**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
	protected EList<Parameter> parameters;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected AbstractProcessImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.ABSTRACT_PROCESS;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Data> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<Data>(Data.class, this, ProcessPackage.ABSTRACT_PROCESS__DATA);
        }
        return data;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Connector> getConnectors() {
        if (connectors == null) {
            connectors = new EObjectContainmentEList<Connector>(Connector.class, this, ProcessPackage.ABSTRACT_PROCESS__CONNECTORS);
        }
        return connectors;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<AbstractKPIBinding> getKpis() {
        if (kpis == null) {
            kpis = new EObjectContainmentEList<AbstractKPIBinding>(AbstractKPIBinding.class, this, ProcessPackage.ABSTRACT_PROCESS__KPIS);
        }
        return kpis;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public FormMapping getFormMapping() {
        return formMapping;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetFormMapping(FormMapping newFormMapping, NotificationChain msgs) {
        FormMapping oldFormMapping = formMapping;
        formMapping = newFormMapping;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING, oldFormMapping, newFormMapping);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setFormMapping(FormMapping newFormMapping) {
        if (newFormMapping != formMapping) {
            NotificationChain msgs = null;
            if (formMapping != null)
                msgs = ((InternalEObject)formMapping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING, null, msgs);
            if (newFormMapping != null)
                msgs = ((InternalEObject)newFormMapping).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING, null, msgs);
            msgs = basicSetFormMapping(newFormMapping, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING, newFormMapping, newFormMapping));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public FormMapping getOverviewFormMapping() {
        return overviewFormMapping;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetOverviewFormMapping(FormMapping newOverviewFormMapping, NotificationChain msgs) {
        FormMapping oldOverviewFormMapping = overviewFormMapping;
        overviewFormMapping = newOverviewFormMapping;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING, oldOverviewFormMapping, newOverviewFormMapping);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setOverviewFormMapping(FormMapping newOverviewFormMapping) {
        if (newOverviewFormMapping != overviewFormMapping) {
            NotificationChain msgs = null;
            if (overviewFormMapping != null)
                msgs = ((InternalEObject)overviewFormMapping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING, null, msgs);
            if (newOverviewFormMapping != null)
                msgs = ((InternalEObject)newOverviewFormMapping).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING, null, msgs);
            msgs = basicSetOverviewFormMapping(newOverviewFormMapping, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING, newOverviewFormMapping, newOverviewFormMapping));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getVersion() {
        return version;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__VERSION, oldVersion, version));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getAuthor() {
        return author;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAuthor(String newAuthor) {
        String oldAuthor = author;
        author = newAuthor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__AUTHOR, oldAuthor, author));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Date getCreationDate() {
        return creationDate;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setCreationDate(Date newCreationDate) {
        Date oldCreationDate = creationDate;
        creationDate = newCreationDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE, oldCreationDate, creationDate));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Date getModificationDate() {
        return modificationDate;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setModificationDate(Date newModificationDate) {
        Date oldModificationDate = modificationDate;
        modificationDate = newModificationDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE, oldModificationDate, modificationDate));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<DataType> getDatatypes() {
        if (datatypes == null) {
            datatypes = new EObjectContainmentEList<DataType>(DataType.class, this, ProcessPackage.ABSTRACT_PROCESS__DATATYPES);
        }
        return datatypes;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Connection> getConnections() {
        if (connections == null) {
            connections = new EObjectContainmentEList<Connection>(Connection.class, this, ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS);
        }
        return connections;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<String> getCategories() {
        if (categories == null) {
            categories = new EDataTypeUniqueEList<String>(String.class, this, ProcessPackage.ABSTRACT_PROCESS__CATEGORIES);
        }
        return categories;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Actor> getActors() {
        if (actors == null) {
            actors = new EObjectContainmentEList<Actor>(Actor.class, this, ProcessPackage.ABSTRACT_PROCESS__ACTORS);
        }
        return actors;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Configuration> getConfigurations() {
        if (configurations == null) {
            configurations = new EObjectContainmentEList<Configuration>(Configuration.class, this, ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS);
        }
        return configurations;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Parameter> getParameters() {
        if (parameters == null) {
            parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, ProcessPackage.ABSTRACT_PROCESS__PARAMETERS);
        }
        return parameters;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                return ((InternalEList<?>)getConnectors()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                return ((InternalEList<?>)getKpis()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                return basicSetFormMapping(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                return basicSetOverviewFormMapping(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                return ((InternalEList<?>)getDatatypes()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                return ((InternalEList<?>)getActors()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                return ((InternalEList<?>)getConfigurations()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                return getData();
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                return getConnectors();
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                return getKpis();
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                return getFormMapping();
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                return getOverviewFormMapping();
            case ProcessPackage.ABSTRACT_PROCESS__VERSION:
                return getVersion();
            case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
                return getAuthor();
            case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
                return getCreationDate();
            case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
                return getModificationDate();
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                return getDatatypes();
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                return getConnections();
            case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
                return getCategories();
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                return getActors();
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                return getConfigurations();
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                return getParameters();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                getData().clear();
                getData().addAll((Collection<? extends Data>)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                getConnectors().clear();
                getConnectors().addAll((Collection<? extends Connector>)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                getKpis().clear();
                getKpis().addAll((Collection<? extends AbstractKPIBinding>)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                setFormMapping((FormMapping)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                setOverviewFormMapping((FormMapping)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VERSION:
                setVersion((String)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
                setAuthor((String)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
                setCreationDate((Date)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
                setModificationDate((Date)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                getDatatypes().clear();
                getDatatypes().addAll((Collection<? extends DataType>)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                getConnections().clear();
                getConnections().addAll((Collection<? extends Connection>)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
                getCategories().clear();
                getCategories().addAll((Collection<? extends String>)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                getActors().clear();
                getActors().addAll((Collection<? extends Actor>)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                getConfigurations().clear();
                getConfigurations().addAll((Collection<? extends Configuration>)newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                getParameters().clear();
                getParameters().addAll((Collection<? extends Parameter>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                getData().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                getConnectors().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                getKpis().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                setFormMapping((FormMapping)null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                setOverviewFormMapping((FormMapping)null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
                setAuthor(AUTHOR_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
                setCreationDate(CREATION_DATE_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
                setModificationDate(MODIFICATION_DATE_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                getDatatypes().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                getConnections().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
                getCategories().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                getActors().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                getConfigurations().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                getParameters().clear();
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                return data != null && !data.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                return connectors != null && !connectors.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                return kpis != null && !kpis.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                return formMapping != null;
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                return overviewFormMapping != null;
            case ProcessPackage.ABSTRACT_PROCESS__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
                return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
            case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
                return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
            case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
                return MODIFICATION_DATE_EDEFAULT == null ? modificationDate != null : !MODIFICATION_DATE_EDEFAULT.equals(modificationDate);
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                return datatypes != null && !datatypes.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                return connections != null && !connections.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
                return categories != null && !categories.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                return actors != null && !actors.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                return configurations != null && !configurations.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                return parameters != null && !parameters.isEmpty();
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == DataAware.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__DATA: return ProcessPackage.DATA_AWARE__DATA;
                default: return -1;
            }
        }
        if (baseClass == ConnectableElement.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS: return ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS;
                case ProcessPackage.ABSTRACT_PROCESS__KPIS: return ProcessPackage.CONNECTABLE_ELEMENT__KPIS;
                default: return -1;
            }
        }
        if (baseClass == AbstractPageFlow.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == PageFlow.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING: return ProcessPackage.PAGE_FLOW__FORM_MAPPING;
                default: return -1;
            }
        }
        if (baseClass == RecapFlow.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING: return ProcessPackage.RECAP_FLOW__OVERVIEW_FORM_MAPPING;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == DataAware.class) {
            switch (baseFeatureID) {
                case ProcessPackage.DATA_AWARE__DATA: return ProcessPackage.ABSTRACT_PROCESS__DATA;
                default: return -1;
            }
        }
        if (baseClass == ConnectableElement.class) {
            switch (baseFeatureID) {
                case ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS: return ProcessPackage.ABSTRACT_PROCESS__CONNECTORS;
                case ProcessPackage.CONNECTABLE_ELEMENT__KPIS: return ProcessPackage.ABSTRACT_PROCESS__KPIS;
                default: return -1;
            }
        }
        if (baseClass == AbstractPageFlow.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == PageFlow.class) {
            switch (baseFeatureID) {
                case ProcessPackage.PAGE_FLOW__FORM_MAPPING: return ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING;
                default: return -1;
            }
        }
        if (baseClass == RecapFlow.class) {
            switch (baseFeatureID) {
                case ProcessPackage.RECAP_FLOW__OVERVIEW_FORM_MAPPING: return ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (version: "); //$NON-NLS-1$
        result.append(version);
        result.append(", author: "); //$NON-NLS-1$
        result.append(author);
        result.append(", creationDate: "); //$NON-NLS-1$
        result.append(creationDate);
        result.append(", modificationDate: "); //$NON-NLS-1$
        result.append(modificationDate);
        result.append(", categories: "); //$NON-NLS-1$
        result.append(categories);
        result.append(')');
        return result.toString();
    }

} //AbstractProcessImpl
