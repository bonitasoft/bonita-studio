/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getConnectorImplementation <em>Connector Implementation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
    FeatureMap getMixed();

    /**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
    EMap<String, String> getXMLNSPrefixMap();

    /**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
    EMap<String, String> getXSISchemaLocation();

    /**
	 * Returns the value of the '<em><b>Connector Implementation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connector Implementation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Implementation</em>' containment reference.
	 * @see #setConnectorImplementation(ConnectorImplementation)
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage#getDocumentRoot_ConnectorImplementation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='connectorImplementation' namespace='##targetNamespace'"
	 * @generated
	 */
    ConnectorImplementation getConnectorImplementation();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getConnectorImplementation <em>Connector Implementation</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Implementation</em>' containment reference.
	 * @see #getConnectorImplementation()
	 * @generated
	 */
    void setConnectorImplementation(ConnectorImplementation value);

} // DocumentRoot
