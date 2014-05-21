/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.util;

import java.util.Map;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorDefinitionXMLProcessor extends XMLProcessor {

    /**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorDefinitionXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		ConnectorDefinitionPackage.eINSTANCE.eClass();
	}
    
    /**
	 * Register for "*" and "xml" file extensions the ConnectorDefinitionResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new ConnectorDefinitionResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new ConnectorDefinitionResourceFactoryImpl());
		}
		return registrations;
	}

} //ConnectorDefinitionXMLProcessor
