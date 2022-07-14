/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.dd.dc.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.omg.spec.bpmn.di.DiPackage;

import org.omg.spec.bpmn.di.impl.DiPackageImpl;

import org.omg.spec.bpmn.model.ModelPackage;

import org.omg.spec.bpmn.model.impl.ModelPackageImpl;

import org.omg.spec.dd.dc.Bounds;
import org.omg.spec.dd.dc.DcFactory;
import org.omg.spec.dd.dc.DcPackage;
import org.omg.spec.dd.dc.DocumentRoot;
import org.omg.spec.dd.dc.Font;
import org.omg.spec.dd.dc.Point;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DcPackageImpl extends EPackageImpl implements DcPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass boundsEClass = null;

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
	private EClass fontEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pointEClass = null;

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
	 * @see org.omg.spec.dd.dc.DcPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DcPackageImpl() {
		super(eNS_URI, DcFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DcPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DcPackage init() {
		if (isInited) return (DcPackage)EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);

		// Obtain or create and register package
		DcPackageImpl theDcPackage = (DcPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DcPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DcPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		DiPackageImpl theDiPackage = (DiPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI) instanceof DiPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI) : DiPackage.eINSTANCE);
		org.omg.spec.dd.di.impl.DiPackageImpl theDiPackage_1 = (org.omg.spec.dd.di.impl.DiPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.omg.spec.dd.di.DiPackage.eNS_URI) instanceof org.omg.spec.dd.di.impl.DiPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.omg.spec.dd.di.DiPackage.eNS_URI) : org.omg.spec.dd.di.DiPackage.eINSTANCE);

		// Load packages
		theModelPackage.loadPackage();

		// Create package meta-data objects
		theDcPackage.createPackageContents();
		theDiPackage.createPackageContents();
		theDiPackage_1.createPackageContents();

		// Initialize created meta-data
		theDcPackage.initializePackageContents();
		theDiPackage.initializePackageContents();
		theDiPackage_1.initializePackageContents();

		// Fix loaded packages
		theModelPackage.fixPackageContents();

		// Mark meta-data to indicate it can't be changed
		theDcPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DcPackage.eNS_URI, theDcPackage);
		return theDcPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBounds() {
		return boundsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBounds_Height() {
		return (EAttribute)boundsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBounds_Width() {
		return (EAttribute)boundsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBounds_X() {
		return (EAttribute)boundsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBounds_Y() {
		return (EAttribute)boundsEClass.getEStructuralFeatures().get(3);
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
	public EReference getDocumentRoot_Bounds() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Font() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Point() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFont() {
		return fontEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_IsBold() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_IsItalic() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_IsStrikeThrough() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_IsUnderline() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Name() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFont_Size() {
		return (EAttribute)fontEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPoint() {
		return pointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_X() {
		return (EAttribute)pointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_Y() {
		return (EAttribute)pointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DcFactory getDcFactory() {
		return (DcFactory)getEFactoryInstance();
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
		boundsEClass = createEClass(BOUNDS);
		createEAttribute(boundsEClass, BOUNDS__HEIGHT);
		createEAttribute(boundsEClass, BOUNDS__WIDTH);
		createEAttribute(boundsEClass, BOUNDS__X);
		createEAttribute(boundsEClass, BOUNDS__Y);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__BOUNDS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FONT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__POINT);

		fontEClass = createEClass(FONT);
		createEAttribute(fontEClass, FONT__IS_BOLD);
		createEAttribute(fontEClass, FONT__IS_ITALIC);
		createEAttribute(fontEClass, FONT__IS_STRIKE_THROUGH);
		createEAttribute(fontEClass, FONT__IS_UNDERLINE);
		createEAttribute(fontEClass, FONT__NAME);
		createEAttribute(fontEClass, FONT__SIZE);

		pointEClass = createEClass(POINT);
		createEAttribute(pointEClass, POINT__X);
		createEAttribute(pointEClass, POINT__Y);
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

		// Initialize classes and features; add operations and parameters
		initEClass(boundsEClass, Bounds.class, "Bounds", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBounds_Height(), theXMLTypePackage.getDouble(), "height", null, 1, 1, Bounds.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBounds_Width(), theXMLTypePackage.getDouble(), "width", null, 1, 1, Bounds.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBounds_X(), theXMLTypePackage.getDouble(), "x", null, 1, 1, Bounds.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBounds_Y(), theXMLTypePackage.getDouble(), "y", null, 1, 1, Bounds.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Bounds(), this.getBounds(), null, "bounds", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Font(), this.getFont(), null, "font", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Point(), this.getPoint(), null, "point", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(fontEClass, Font.class, "Font", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFont_IsBold(), theXMLTypePackage.getBoolean(), "isBold", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFont_IsItalic(), theXMLTypePackage.getBoolean(), "isItalic", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFont_IsStrikeThrough(), theXMLTypePackage.getBoolean(), "isStrikeThrough", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFont_IsUnderline(), theXMLTypePackage.getBoolean(), "isUnderline", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFont_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFont_Size(), theXMLTypePackage.getDouble(), "size", null, 0, 1, Font.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pointEClass, Point.class, "Point", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPoint_X(), theXMLTypePackage.getDouble(), "x", null, 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPoint_Y(), theXMLTypePackage.getDouble(), "y", null, 1, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
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
		  (boundsEClass, 
		   source, 
		   new String[] {
			 "name", "Bounds",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getBounds_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getBounds_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getBounds_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getBounds_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
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
		  (getDocumentRoot_Bounds(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Bounds",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Font(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Font",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Point(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Point",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (fontEClass, 
		   source, 
		   new String[] {
			 "name", "Font",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getFont_IsBold(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isBold"
		   });		
		addAnnotation
		  (getFont_IsItalic(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isItalic"
		   });		
		addAnnotation
		  (getFont_IsStrikeThrough(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isStrikeThrough"
		   });		
		addAnnotation
		  (getFont_IsUnderline(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "isUnderline"
		   });		
		addAnnotation
		  (getFont_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getFont_Size(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "size"
		   });		
		addAnnotation
		  (pointEClass, 
		   source, 
		   new String[] {
			 "name", "Point",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getPoint_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getPoint_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });
	}

} //DcPackageImpl
