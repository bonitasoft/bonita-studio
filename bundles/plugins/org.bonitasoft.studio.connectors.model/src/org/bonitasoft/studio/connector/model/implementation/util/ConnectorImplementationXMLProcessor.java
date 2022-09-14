/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation.util;

import java.util.Map;

import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorImplementationXMLProcessor extends XMLProcessor {

    /**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorImplementationXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		ConnectorImplementationPackage.eINSTANCE.eClass();
	}
    
    /**
	 * Register for "*" and "xml" file extensions the ConnectorImplementationResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new ConnectorImplementationResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new ConnectorImplementationResourceFactoryImpl());
		}
		return registrations;
	}

} //ConnectorImplementationXMLProcessor
