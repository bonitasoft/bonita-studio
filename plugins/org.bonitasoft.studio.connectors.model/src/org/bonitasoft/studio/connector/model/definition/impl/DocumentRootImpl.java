/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.impl;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.DocumentRoot;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.DocumentRootImpl#getConnectorDefinition <em>Connector Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
    /**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
    protected FeatureMap mixed;

    /**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
    protected EMap<String, String> xMLNSPrefixMap;

    /**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
    protected EMap<String, String> xSISchemaLocation;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected DocumentRootImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConnectorDefinitionPackage.Literals.DOCUMENT_ROOT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, ConnectorDefinitionPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ConnectorDefinitionPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ConnectorDefinitionPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public ConnectorDefinition getConnectorDefinition() {
		return (ConnectorDefinition)getMixed().get(ConnectorDefinitionPackage.Literals.DOCUMENT_ROOT__CONNECTOR_DEFINITION, true);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetConnectorDefinition(ConnectorDefinition newConnectorDefinition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ConnectorDefinitionPackage.Literals.DOCUMENT_ROOT__CONNECTOR_DEFINITION, newConnectorDefinition, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setConnectorDefinition(ConnectorDefinition newConnectorDefinition) {
		((FeatureMap.Internal)getMixed()).set(ConnectorDefinitionPackage.Literals.DOCUMENT_ROOT__CONNECTOR_DEFINITION, newConnectorDefinition);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__CONNECTOR_DEFINITION:
				return basicSetConnectorDefinition(null, msgs);
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
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__CONNECTOR_DEFINITION:
				return getConnectorDefinition();
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
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__CONNECTOR_DEFINITION:
				setConnectorDefinition((ConnectorDefinition)newValue);
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
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__CONNECTOR_DEFINITION:
				setConnectorDefinition((ConnectorDefinition)null);
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
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case ConnectorDefinitionPackage.DOCUMENT_ROOT__CONNECTOR_DEFINITION:
				return getConnectorDefinition() != null;
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
