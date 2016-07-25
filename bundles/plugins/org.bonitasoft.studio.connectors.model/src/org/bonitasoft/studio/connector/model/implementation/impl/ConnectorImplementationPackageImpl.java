/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation.impl;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;

import org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl;

import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.studio.connector.model.implementation.DocumentRoot;
import org.bonitasoft.studio.connector.model.implementation.JarDependencies;

import org.bonitasoft.studio.connector.model.implementation.UnloadableConnectorImplementation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorImplementationPackageImpl extends EPackageImpl implements ConnectorImplementationPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass connectorImplementationEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass documentRootEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass jarDependenciesEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unloadableConnectorImplementationEClass = null;

				/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private ConnectorImplementationPackageImpl() {
		super(eNS_URI, ConnectorImplementationFactory.eINSTANCE);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static boolean isInited = false;

    /**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ConnectorImplementationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static ConnectorImplementationPackage init() {
		if (isInited) return (ConnectorImplementationPackage)EPackage.Registry.INSTANCE.getEPackage(ConnectorImplementationPackage.eNS_URI);

		// Obtain or create and register package
		ConnectorImplementationPackageImpl theConnectorImplementationPackage = (ConnectorImplementationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ConnectorImplementationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ConnectorImplementationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		ConnectorDefinitionPackageImpl theConnectorDefinitionPackage = (ConnectorDefinitionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConnectorDefinitionPackage.eNS_URI) instanceof ConnectorDefinitionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConnectorDefinitionPackage.eNS_URI) : ConnectorDefinitionPackage.eINSTANCE);

		// Create package meta-data objects
		theConnectorImplementationPackage.createPackageContents();
		theConnectorDefinitionPackage.createPackageContents();

		// Initialize created meta-data
		theConnectorImplementationPackage.initializePackageContents();
		theConnectorDefinitionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theConnectorImplementationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ConnectorImplementationPackage.eNS_URI, theConnectorImplementationPackage);
		return theConnectorImplementationPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getConnectorImplementation() {
		return connectorImplementationEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getConnectorImplementation_JarDependencies() {
		return (EReference)connectorImplementationEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorImplementation_Description() {
		return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorImplementation_DefinitionId() {
		return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorImplementation_DefinitionVersion() {
		return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorImplementation_HasSources() {
		return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorImplementation_ImplementationClassname() {
		return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorImplementation_ImplementationId() {
		return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorImplementation_ImplementationVersion() {
		return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDocumentRoot() {
		return documentRootEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDocumentRoot_ConnectorImplementation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getJarDependencies() {
		return jarDependenciesEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getJarDependencies_JarDependency() {
		return (EAttribute)jarDependenciesEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnloadableConnectorImplementation() {
		return unloadableConnectorImplementationEClass;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorImplementationFactory getConnectorImplementationFactory() {
		return (ConnectorImplementationFactory)getEFactoryInstance();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isCreated = false;

    /**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		connectorImplementationEClass = createEClass(CONNECTOR_IMPLEMENTATION);
		createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID);
		createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION);
		createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__DEFINITION_ID);
		createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION);
		createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME);
		createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__HAS_SOURCES);
		createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__DESCRIPTION);
		createEReference(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONNECTOR_IMPLEMENTATION);

		jarDependenciesEClass = createEClass(JAR_DEPENDENCIES);
		createEAttribute(jarDependenciesEClass, JAR_DEPENDENCIES__JAR_DEPENDENCY);

		unloadableConnectorImplementationEClass = createEClass(UNLOADABLE_CONNECTOR_IMPLEMENTATION);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isInitialized = false;

    /**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		unloadableConnectorImplementationEClass.getESuperTypes().add(this.getConnectorImplementation());

		// Initialize classes and features; add operations and parameters
		initEClass(connectorImplementationEClass, ConnectorImplementation.class, "ConnectorImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConnectorImplementation_ImplementationId(), theXMLTypePackage.getString(), "implementationId", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorImplementation_ImplementationVersion(), theXMLTypePackage.getString(), "implementationVersion", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorImplementation_DefinitionId(), theXMLTypePackage.getString(), "definitionId", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorImplementation_DefinitionVersion(), theXMLTypePackage.getString(), "definitionVersion", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorImplementation_ImplementationClassname(), theXMLTypePackage.getString(), "implementationClassname", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorImplementation_HasSources(), theXMLTypePackage.getBoolean(), "hasSources", "false", 0, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorImplementation_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnectorImplementation_JarDependencies(), this.getJarDependencies(), null, "jarDependencies", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ConnectorImplementation(), this.getConnectorImplementation(), null, "connectorImplementation", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(jarDependenciesEClass, JarDependencies.class, "JarDependencies", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJarDependencies_JarDependency(), theXMLTypePackage.getString(), "jarDependency", null, 0, -1, JarDependencies.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unloadableConnectorImplementationEClass, UnloadableConnectorImplementation.class, "UnloadableConnectorImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/edapt
		createEdaptAnnotations();
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

    /**
	 * Initializes the annotations for <b>http://www.eclipse.org/edapt</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEdaptAnnotations() {
		String source = "http://www.eclipse.org/edapt";		
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "historyURI", "connector.history"
		   });																
	}

				/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";			
		addAnnotation
		  (connectorImplementationEClass, 
		   source, 
		   new String[] {
			 "name", "ConnectorImplementation",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getConnectorImplementation_ImplementationId(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "implementationId"
		   });		
		addAnnotation
		  (getConnectorImplementation_ImplementationVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "implementationVersion"
		   });		
		addAnnotation
		  (getConnectorImplementation_DefinitionId(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "definitionId"
		   });		
		addAnnotation
		  (getConnectorImplementation_DefinitionVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "definitionVersion"
		   });		
		addAnnotation
		  (getConnectorImplementation_ImplementationClassname(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "implementationClassname"
		   });		
		addAnnotation
		  (getConnectorImplementation_HasSources(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "hasSources"
		   });		
		addAnnotation
		  (getConnectorImplementation_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description"
		   });		
		addAnnotation
		  (getConnectorImplementation_JarDependencies(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "jarDependencies"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_ConnectorImplementation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connectorImplementation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (jarDependenciesEClass, 
		   source, 
		   new String[] {
			 "name", "jarDependencies",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getJarDependencies_JarDependency(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "jarDependency"
		   });
	}

} //ConnectorImplementationPackageImpl
