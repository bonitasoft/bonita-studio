/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.impl;

import java.util.Collection;

import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.Page;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl#getInput <em>Input</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl#getPage <em>Page</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl#getJarDependency <em>Jar Dependency</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectorDefinitionImpl extends EObjectImpl implements ConnectorDefinition {
    /**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
    protected static final String ID_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
    protected String id = ID_EDEFAULT;

    /**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
    protected static final String VERSION_EDEFAULT = null;

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
	 * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
    protected static final String ICON_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
    protected String icon = ICON_EDEFAULT;

    /**
	 * The cached value of the '{@link #getCategory() <em>Category</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
    protected EList<Category> category;

    /**
	 * The cached value of the '{@link #getInput() <em>Input</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInput()
	 * @generated
	 * @ordered
	 */
    protected EList<Input> input;

    /**
	 * The cached value of the '{@link #getOutput() <em>Output</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOutput()
	 * @generated
	 * @ordered
	 */
    protected EList<Output> output;

    /**
	 * The cached value of the '{@link #getPage() <em>Page</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPage()
	 * @generated
	 * @ordered
	 */
    protected EList<Page> page;

    /**
	 * The cached value of the '{@link #getJarDependency() <em>Jar Dependency</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getJarDependency()
	 * @generated
	 * @ordered
	 */
    protected EList<String> jarDependency;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ConnectorDefinitionImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Category> getCategory() {
		if (category == null) {
			category = new EObjectContainmentEList<Category>(Category.class, this, ConnectorDefinitionPackage.CONNECTOR_DEFINITION__CATEGORY);
		}
		return category;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Input> getInput() {
		if (input == null) {
			input = new EObjectContainmentEList<Input>(Input.class, this, ConnectorDefinitionPackage.CONNECTOR_DEFINITION__INPUT);
		}
		return input;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Output> getOutput() {
		if (output == null) {
			output = new EObjectContainmentEList<Output>(Output.class, this, ConnectorDefinitionPackage.CONNECTOR_DEFINITION__OUTPUT);
		}
		return output;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Page> getPage() {
		if (page == null) {
			page = new EObjectContainmentEList<Page>(Page.class, this, ConnectorDefinitionPackage.CONNECTOR_DEFINITION__PAGE);
		}
		return page;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<String> getJarDependency() {
		if (jarDependency == null) {
			jarDependency = new EDataTypeEList<String>(String.class, this, ConnectorDefinitionPackage.CONNECTOR_DEFINITION__JAR_DEPENDENCY);
		}
		return jarDependency;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getIcon() {
		return icon;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setIcon(String newIcon) {
		String oldIcon = icon;
		icon = newIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ICON, oldIcon, icon));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getId() {
		return id;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getVersion() {
		return version;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.CONNECTOR_DEFINITION__VERSION, oldVersion, version));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__CATEGORY:
				return ((InternalEList<?>)getCategory()).basicRemove(otherEnd, msgs);
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__INPUT:
				return ((InternalEList<?>)getInput()).basicRemove(otherEnd, msgs);
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__OUTPUT:
				return ((InternalEList<?>)getOutput()).basicRemove(otherEnd, msgs);
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__PAGE:
				return ((InternalEList<?>)getPage()).basicRemove(otherEnd, msgs);
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
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ID:
				return getId();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__VERSION:
				return getVersion();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ICON:
				return getIcon();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__CATEGORY:
				return getCategory();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__INPUT:
				return getInput();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__OUTPUT:
				return getOutput();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__PAGE:
				return getPage();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__JAR_DEPENDENCY:
				return getJarDependency();
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
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ID:
				setId((String)newValue);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__VERSION:
				setVersion((String)newValue);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ICON:
				setIcon((String)newValue);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__CATEGORY:
				getCategory().clear();
				getCategory().addAll((Collection<? extends Category>)newValue);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__INPUT:
				getInput().clear();
				getInput().addAll((Collection<? extends Input>)newValue);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__OUTPUT:
				getOutput().clear();
				getOutput().addAll((Collection<? extends Output>)newValue);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__PAGE:
				getPage().clear();
				getPage().addAll((Collection<? extends Page>)newValue);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__JAR_DEPENDENCY:
				getJarDependency().clear();
				getJarDependency().addAll((Collection<? extends String>)newValue);
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
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ID:
				setId(ID_EDEFAULT);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ICON:
				setIcon(ICON_EDEFAULT);
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__CATEGORY:
				getCategory().clear();
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__INPUT:
				getInput().clear();
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__OUTPUT:
				getOutput().clear();
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__PAGE:
				getPage().clear();
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__JAR_DEPENDENCY:
				getJarDependency().clear();
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
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ICON:
				return ICON_EDEFAULT == null ? icon != null : !ICON_EDEFAULT.equals(icon);
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__CATEGORY:
				return category != null && !category.isEmpty();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__INPUT:
				return input != null && !input.isEmpty();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__OUTPUT:
				return output != null && !output.isEmpty();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__PAGE:
				return page != null && !page.isEmpty();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__JAR_DEPENDENCY:
				return jarDependency != null && !jarDependency.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(", version: ");
		result.append(version);
		result.append(", icon: ");
		result.append(icon);
		result.append(", jarDependency: ");
		result.append(jarDependency);
		result.append(')');
		return result.toString();
	}

} //ConnectorDefinitionImpl
