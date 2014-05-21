/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='connector.history'"
 * @generated
 */
public interface ConnectorImplementationPackage extends EPackage {
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "implementation";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http://www.bonitasoft.org/ns/connector/implementation/6.0";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "implementation";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ConnectorImplementationPackage eINSTANCE = org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl.init();

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl <em>Connector Implementation</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl
	 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl#getConnectorImplementation()
	 * @generated
	 */
    int CONNECTOR_IMPLEMENTATION = 0;

    /**
	 * The feature id for the '<em><b>Implementation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID = 0;

    /**
	 * The feature id for the '<em><b>Implementation Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION = 1;

    /**
	 * The feature id for the '<em><b>Definition Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_IMPLEMENTATION__DEFINITION_ID = 2;

    /**
	 * The feature id for the '<em><b>Definition Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION = 3;

    /**
	 * The feature id for the '<em><b>Implementation Classname</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME = 4;

    /**
	 * The feature id for the '<em><b>Has Sources</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_IMPLEMENTATION__HAS_SOURCES = 5;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_IMPLEMENTATION__DESCRIPTION = 6;

    /**
	 * The feature id for the '<em><b>Jar Dependencies</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES = 7;

    /**
	 * The number of structural features of the '<em>Connector Implementation</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_IMPLEMENTATION_FEATURE_COUNT = 8;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.implementation.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.implementation.impl.DocumentRootImpl
	 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl#getDocumentRoot()
	 * @generated
	 */
    int DOCUMENT_ROOT = 1;

    /**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__MIXED = 0;

    /**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

    /**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

    /**
	 * The feature id for the '<em><b>Connector Implementation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__CONNECTOR_IMPLEMENTATION = 3;

    /**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT_FEATURE_COUNT = 4;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.implementation.impl.JarDependenciesImpl <em>Jar Dependencies</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.implementation.impl.JarDependenciesImpl
	 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl#getJarDependencies()
	 * @generated
	 */
    int JAR_DEPENDENCIES = 2;

    /**
	 * The feature id for the '<em><b>Jar Dependency</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int JAR_DEPENDENCIES__JAR_DEPENDENCY = 0;

    /**
	 * The number of structural features of the '<em>Jar Dependencies</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int JAR_DEPENDENCIES_FEATURE_COUNT = 1;


    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.implementation.impl.UnloadableConnectorImplementationImpl <em>Unloadable Connector Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.implementation.impl.UnloadableConnectorImplementationImpl
	 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl#getUnloadableConnectorImplementation()
	 * @generated
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION = 3;

				/**
	 * The feature id for the '<em><b>Implementation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID = CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID;

				/**
	 * The feature id for the '<em><b>Implementation Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION = CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION;

				/**
	 * The feature id for the '<em><b>Definition Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION__DEFINITION_ID = CONNECTOR_IMPLEMENTATION__DEFINITION_ID;

				/**
	 * The feature id for the '<em><b>Definition Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION = CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION;

				/**
	 * The feature id for the '<em><b>Implementation Classname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME = CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME;

				/**
	 * The feature id for the '<em><b>Has Sources</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION__HAS_SOURCES = CONNECTOR_IMPLEMENTATION__HAS_SOURCES;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION__DESCRIPTION = CONNECTOR_IMPLEMENTATION__DESCRIPTION;

				/**
	 * The feature id for the '<em><b>Jar Dependencies</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES = CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES;

				/**
	 * The number of structural features of the '<em>Unloadable Connector Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_IMPLEMENTATION_FEATURE_COUNT = CONNECTOR_IMPLEMENTATION_FEATURE_COUNT + 0;


				/**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation <em>Connector Implementation</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Implementation</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation
	 * @generated
	 */
    EClass getConnectorImplementation();

    /**
	 * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getJarDependencies <em>Jar Dependencies</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Jar Dependencies</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getJarDependencies()
	 * @see #getConnectorImplementation()
	 * @generated
	 */
    EReference getConnectorImplementation_JarDependencies();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getDescription()
	 * @see #getConnectorImplementation()
	 * @generated
	 */
    EAttribute getConnectorImplementation_Description();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getDefinitionId <em>Definition Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition Id</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getDefinitionId()
	 * @see #getConnectorImplementation()
	 * @generated
	 */
    EAttribute getConnectorImplementation_DefinitionId();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getDefinitionVersion <em>Definition Version</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition Version</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getDefinitionVersion()
	 * @see #getConnectorImplementation()
	 * @generated
	 */
    EAttribute getConnectorImplementation_DefinitionVersion();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#isHasSources <em>Has Sources</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Sources</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#isHasSources()
	 * @see #getConnectorImplementation()
	 * @generated
	 */
    EAttribute getConnectorImplementation_HasSources();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getImplementationClassname <em>Implementation Classname</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Classname</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getImplementationClassname()
	 * @see #getConnectorImplementation()
	 * @generated
	 */
    EAttribute getConnectorImplementation_ImplementationClassname();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getImplementationId <em>Implementation Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Id</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getImplementationId()
	 * @see #getConnectorImplementation()
	 * @generated
	 */
    EAttribute getConnectorImplementation_ImplementationId();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getImplementationVersion <em>Implementation Version</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Version</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation#getImplementationVersion()
	 * @see #getConnectorImplementation()
	 * @generated
	 */
    EAttribute getConnectorImplementation_ImplementationVersion();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.DocumentRoot
	 * @generated
	 */
    EClass getDocumentRoot();

    /**
	 * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EAttribute getDocumentRoot_Mixed();

    /**
	 * Returns the meta object for the map '{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EReference getDocumentRoot_XMLNSPrefixMap();

    /**
	 * Returns the meta object for the map '{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EReference getDocumentRoot_XSISchemaLocation();

    /**
	 * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getConnectorImplementation <em>Connector Implementation</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Connector Implementation</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.DocumentRoot#getConnectorImplementation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EReference getDocumentRoot_ConnectorImplementation();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.implementation.JarDependencies <em>Jar Dependencies</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Jar Dependencies</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.JarDependencies
	 * @generated
	 */
    EClass getJarDependencies();

    /**
	 * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.connector.model.implementation.JarDependencies#getJarDependency <em>Jar Dependency</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Jar Dependency</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.JarDependencies#getJarDependency()
	 * @see #getJarDependencies()
	 * @generated
	 */
    EAttribute getJarDependencies_JarDependency();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.implementation.UnloadableConnectorImplementation <em>Unloadable Connector Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unloadable Connector Implementation</em>'.
	 * @see org.bonitasoft.studio.connector.model.implementation.UnloadableConnectorImplementation
	 * @generated
	 */
	EClass getUnloadableConnectorImplementation();

				/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    ConnectorImplementationFactory getConnectorImplementationFactory();

    /**
	 * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
	 * @generated
	 */
    interface Literals {
        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl <em>Connector Implementation</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationImpl
		 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl#getConnectorImplementation()
		 * @generated
		 */
        EClass CONNECTOR_IMPLEMENTATION = eINSTANCE.getConnectorImplementation();

        /**
		 * The meta object literal for the '<em><b>Jar Dependencies</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES = eINSTANCE.getConnectorImplementation_JarDependencies();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_IMPLEMENTATION__DESCRIPTION = eINSTANCE.getConnectorImplementation_Description();

        /**
		 * The meta object literal for the '<em><b>Definition Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_IMPLEMENTATION__DEFINITION_ID = eINSTANCE.getConnectorImplementation_DefinitionId();

        /**
		 * The meta object literal for the '<em><b>Definition Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION = eINSTANCE.getConnectorImplementation_DefinitionVersion();

        /**
		 * The meta object literal for the '<em><b>Has Sources</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_IMPLEMENTATION__HAS_SOURCES = eINSTANCE.getConnectorImplementation_HasSources();

        /**
		 * The meta object literal for the '<em><b>Implementation Classname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME = eINSTANCE.getConnectorImplementation_ImplementationClassname();

        /**
		 * The meta object literal for the '<em><b>Implementation Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID = eINSTANCE.getConnectorImplementation_ImplementationId();

        /**
		 * The meta object literal for the '<em><b>Implementation Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION = eINSTANCE.getConnectorImplementation_ImplementationVersion();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.implementation.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.implementation.impl.DocumentRootImpl
		 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl#getDocumentRoot()
		 * @generated
		 */
        EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

        /**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

        /**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

        /**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

        /**
		 * The meta object literal for the '<em><b>Connector Implementation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__CONNECTOR_IMPLEMENTATION = eINSTANCE.getDocumentRoot_ConnectorImplementation();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.implementation.impl.JarDependenciesImpl <em>Jar Dependencies</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.implementation.impl.JarDependenciesImpl
		 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl#getJarDependencies()
		 * @generated
		 */
        EClass JAR_DEPENDENCIES = eINSTANCE.getJarDependencies();

        /**
		 * The meta object literal for the '<em><b>Jar Dependency</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute JAR_DEPENDENCIES__JAR_DEPENDENCY = eINSTANCE.getJarDependencies_JarDependency();

								/**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.implementation.impl.UnloadableConnectorImplementationImpl <em>Unloadable Connector Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.implementation.impl.UnloadableConnectorImplementationImpl
		 * @see org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl#getUnloadableConnectorImplementation()
		 * @generated
		 */
		EClass UNLOADABLE_CONNECTOR_IMPLEMENTATION = eINSTANCE.getUnloadableConnectorImplementation();

    }

} //ConnectorImplementationPackage
