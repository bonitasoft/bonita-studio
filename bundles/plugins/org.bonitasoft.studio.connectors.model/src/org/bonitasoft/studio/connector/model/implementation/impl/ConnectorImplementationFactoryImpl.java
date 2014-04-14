/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation.impl;

import org.bonitasoft.studio.connector.model.implementation.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorImplementationFactoryImpl extends EFactoryImpl implements ConnectorImplementationFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static ConnectorImplementationFactory init() {
		try {
			ConnectorImplementationFactory theConnectorImplementationFactory = (ConnectorImplementationFactory)EPackage.Registry.INSTANCE.getEFactory(ConnectorImplementationPackage.eNS_URI);
			if (theConnectorImplementationFactory != null) {
				return theConnectorImplementationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ConnectorImplementationFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorImplementationFactoryImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION: return createConnectorImplementation();
			case ConnectorImplementationPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case ConnectorImplementationPackage.JAR_DEPENDENCIES: return createJarDependencies();
			case ConnectorImplementationPackage.UNLOADABLE_CONNECTOR_IMPLEMENTATION: return createUnloadableConnectorImplementation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorImplementation createConnectorImplementation() {
		ConnectorImplementationImpl connectorImplementation = new ConnectorImplementationImpl();
		return connectorImplementation;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public JarDependencies createJarDependencies() {
		JarDependenciesImpl jarDependencies = new JarDependenciesImpl();
		return jarDependencies;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnloadableConnectorImplementation createUnloadableConnectorImplementation() {
		UnloadableConnectorImplementationImpl unloadableConnectorImplementation = new UnloadableConnectorImplementationImpl();
		return unloadableConnectorImplementation;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorImplementationPackage getConnectorImplementationPackage() {
		return (ConnectorImplementationPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    @Deprecated
    public static ConnectorImplementationPackage getPackage() {
		return ConnectorImplementationPackage.eINSTANCE;
	}

} //ConnectorImplementationFactoryImpl
