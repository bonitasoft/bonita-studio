/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation.impl;

import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.studio.connector.model.implementation.JarDependencies;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector Implementation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl#getImplementationId <em>Implementation Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl#getImplementationVersion <em>Implementation Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl#getDefinitionId <em>Definition Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl#getDefinitionVersion <em>Definition Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl#getImplementationClassname <em>Implementation Classname</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl#isHasSources <em>Has Sources</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl#getJarDependencies <em>Jar Dependencies</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectorImplementationImpl extends EObjectImpl implements ConnectorImplementation {
    /**
	 * The default value of the '{@link #getImplementationId() <em>Implementation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getImplementationId()
	 * @generated
	 * @ordered
	 */
    protected static final String IMPLEMENTATION_ID_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getImplementationId() <em>Implementation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getImplementationId()
	 * @generated
	 * @ordered
	 */
    protected String implementationId = IMPLEMENTATION_ID_EDEFAULT;

    /**
	 * The default value of the '{@link #getImplementationVersion() <em>Implementation Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getImplementationVersion()
	 * @generated
	 * @ordered
	 */
    protected static final String IMPLEMENTATION_VERSION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getImplementationVersion() <em>Implementation Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getImplementationVersion()
	 * @generated
	 * @ordered
	 */
    protected String implementationVersion = IMPLEMENTATION_VERSION_EDEFAULT;

    /**
	 * The default value of the '{@link #getDefinitionId() <em>Definition Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDefinitionId()
	 * @generated
	 * @ordered
	 */
    protected static final String DEFINITION_ID_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getDefinitionId() <em>Definition Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDefinitionId()
	 * @generated
	 * @ordered
	 */
    protected String definitionId = DEFINITION_ID_EDEFAULT;

    /**
	 * The default value of the '{@link #getDefinitionVersion() <em>Definition Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDefinitionVersion()
	 * @generated
	 * @ordered
	 */
    protected static final String DEFINITION_VERSION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getDefinitionVersion() <em>Definition Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDefinitionVersion()
	 * @generated
	 * @ordered
	 */
    protected String definitionVersion = DEFINITION_VERSION_EDEFAULT;

    /**
	 * The default value of the '{@link #getImplementationClassname() <em>Implementation Classname</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getImplementationClassname()
	 * @generated
	 * @ordered
	 */
    protected static final String IMPLEMENTATION_CLASSNAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getImplementationClassname() <em>Implementation Classname</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getImplementationClassname()
	 * @generated
	 * @ordered
	 */
    protected String implementationClassname = IMPLEMENTATION_CLASSNAME_EDEFAULT;

    /**
	 * The default value of the '{@link #isHasSources() <em>Has Sources</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHasSources()
	 * @generated
	 * @ordered
	 */
    protected static final boolean HAS_SOURCES_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isHasSources() <em>Has Sources</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHasSources()
	 * @generated
	 * @ordered
	 */
    protected boolean hasSources = HAS_SOURCES_EDEFAULT;

    /**
	 * This is true if the Has Sources attribute has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean hasSourcesESet;

    /**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
	 * The cached value of the '{@link #getJarDependencies() <em>Jar Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getJarDependencies()
	 * @generated
	 * @ordered
	 */
    protected JarDependencies jarDependencies;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ConnectorImplementationImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public JarDependencies getJarDependencies() {
		return jarDependencies;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetJarDependencies(JarDependencies newJarDependencies, NotificationChain msgs) {
		JarDependencies oldJarDependencies = jarDependencies;
		jarDependencies = newJarDependencies;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES, oldJarDependencies, newJarDependencies);
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
    public void setJarDependencies(JarDependencies newJarDependencies) {
		if (newJarDependencies != jarDependencies) {
			NotificationChain msgs = null;
			if (jarDependencies != null)
				msgs = ((InternalEObject)jarDependencies).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES, null, msgs);
			if (newJarDependencies != null)
				msgs = ((InternalEObject)newJarDependencies).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES, null, msgs);
			msgs = basicSetJarDependencies(newJarDependencies, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES, newJarDependencies, newJarDependencies));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getDescription() {
		return description;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DESCRIPTION, oldDescription, description));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getDefinitionId() {
		return definitionId;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setDefinitionId(String newDefinitionId) {
		String oldDefinitionId = definitionId;
		definitionId = newDefinitionId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_ID, oldDefinitionId, definitionId));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getDefinitionVersion() {
		return definitionVersion;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setDefinitionVersion(String newDefinitionVersion) {
		String oldDefinitionVersion = definitionVersion;
		definitionVersion = newDefinitionVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION, oldDefinitionVersion, definitionVersion));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean isHasSources() {
		return hasSources;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setHasSources(boolean newHasSources) {
		boolean oldHasSources = hasSources;
		hasSources = newHasSources;
		boolean oldHasSourcesESet = hasSourcesESet;
		hasSourcesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__HAS_SOURCES, oldHasSources, hasSources, !oldHasSourcesESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void unsetHasSources() {
		boolean oldHasSources = hasSources;
		boolean oldHasSourcesESet = hasSourcesESet;
		hasSources = HAS_SOURCES_EDEFAULT;
		hasSourcesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__HAS_SOURCES, oldHasSources, HAS_SOURCES_EDEFAULT, oldHasSourcesESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean isSetHasSources() {
		return hasSourcesESet;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getImplementationClassname() {
		return implementationClassname;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setImplementationClassname(String newImplementationClassname) {
		String oldImplementationClassname = implementationClassname;
		implementationClassname = newImplementationClassname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME, oldImplementationClassname, implementationClassname));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getImplementationId() {
		return implementationId;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setImplementationId(String newImplementationId) {
		String oldImplementationId = implementationId;
		implementationId = newImplementationId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID, oldImplementationId, implementationId));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getImplementationVersion() {
		return implementationVersion;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setImplementationVersion(String newImplementationVersion) {
		String oldImplementationVersion = implementationVersion;
		implementationVersion = newImplementationVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION, oldImplementationVersion, implementationVersion));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES:
				return basicSetJarDependencies(null, msgs);
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
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID:
				return getImplementationId();
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION:
				return getImplementationVersion();
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_ID:
				return getDefinitionId();
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION:
				return getDefinitionVersion();
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME:
				return getImplementationClassname();
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__HAS_SOURCES:
				return isHasSources();
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DESCRIPTION:
				return getDescription();
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES:
				return getJarDependencies();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID:
				setImplementationId((String)newValue);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION:
				setImplementationVersion((String)newValue);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_ID:
				setDefinitionId((String)newValue);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION:
				setDefinitionVersion((String)newValue);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME:
				setImplementationClassname((String)newValue);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__HAS_SOURCES:
				setHasSources((Boolean)newValue);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES:
				setJarDependencies((JarDependencies)newValue);
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
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID:
				setImplementationId(IMPLEMENTATION_ID_EDEFAULT);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION:
				setImplementationVersion(IMPLEMENTATION_VERSION_EDEFAULT);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_ID:
				setDefinitionId(DEFINITION_ID_EDEFAULT);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION:
				setDefinitionVersion(DEFINITION_VERSION_EDEFAULT);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME:
				setImplementationClassname(IMPLEMENTATION_CLASSNAME_EDEFAULT);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__HAS_SOURCES:
				unsetHasSources();
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES:
				setJarDependencies((JarDependencies)null);
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
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID:
				return IMPLEMENTATION_ID_EDEFAULT == null ? implementationId != null : !IMPLEMENTATION_ID_EDEFAULT.equals(implementationId);
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION:
				return IMPLEMENTATION_VERSION_EDEFAULT == null ? implementationVersion != null : !IMPLEMENTATION_VERSION_EDEFAULT.equals(implementationVersion);
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_ID:
				return DEFINITION_ID_EDEFAULT == null ? definitionId != null : !DEFINITION_ID_EDEFAULT.equals(definitionId);
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION:
				return DEFINITION_VERSION_EDEFAULT == null ? definitionVersion != null : !DEFINITION_VERSION_EDEFAULT.equals(definitionVersion);
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME:
				return IMPLEMENTATION_CLASSNAME_EDEFAULT == null ? implementationClassname != null : !IMPLEMENTATION_CLASSNAME_EDEFAULT.equals(implementationClassname);
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__HAS_SOURCES:
				return isSetHasSources();
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES:
				return jarDependencies != null;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (implementationId: ");
		result.append(implementationId);
		result.append(", implementationVersion: ");
		result.append(implementationVersion);
		result.append(", definitionId: ");
		result.append(definitionId);
		result.append(", definitionVersion: ");
		result.append(definitionVersion);
		result.append(", implementationClassname: ");
		result.append(implementationClassname);
		result.append(", hasSources: ");
		if (hasSourcesESet) result.append(hasSources); else result.append("<unset>");
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //ConnectorImplementationImpl
