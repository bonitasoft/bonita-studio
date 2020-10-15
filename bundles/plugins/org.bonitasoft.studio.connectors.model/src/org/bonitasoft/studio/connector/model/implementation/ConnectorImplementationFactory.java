/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage
 * @generated
 */
public interface ConnectorImplementationFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ConnectorImplementationFactory eINSTANCE = org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>Connector Implementation</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector Implementation</em>'.
	 * @generated
	 */
    ConnectorImplementation createConnectorImplementation();

    /**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
    DocumentRoot createDocumentRoot();

    /**
	 * Returns a new object of class '<em>Jar Dependencies</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Jar Dependencies</em>'.
	 * @generated
	 */
    JarDependencies createJarDependencies();

    /**
	 * Returns a new object of class '<em>Unloadable Connector Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unloadable Connector Implementation</em>'.
	 * @generated
	 */
	UnloadableConnectorImplementation createUnloadableConnectorImplementation();

				/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    ConnectorImplementationPackage getConnectorImplementationPackage();

} //ConnectorImplementationFactory
