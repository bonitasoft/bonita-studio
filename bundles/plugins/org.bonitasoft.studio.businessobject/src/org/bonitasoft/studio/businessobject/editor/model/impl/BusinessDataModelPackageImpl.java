/**
 */
package org.bonitasoft.studio.businessobject.editor.model.impl;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.FetchType;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BusinessDataModelPackageImpl extends EPackageImpl implements BusinessDataModelPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass businessObjectModelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass packageEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass businessObjectEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass fieldEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass uniqueConstraintEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass indexEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass queryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass queryParameterEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass simpleFieldEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass relationFieldEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum fieldTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum relationTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum fetchTypeEEnum = null;

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
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private BusinessDataModelPackageImpl() {
        super(eNS_URI, BusinessDataModelFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link BusinessDataModelPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static BusinessDataModelPackage init() {
        if (isInited) return (BusinessDataModelPackage)EPackage.Registry.INSTANCE.getEPackage(BusinessDataModelPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredBusinessDataModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        BusinessDataModelPackageImpl theBusinessDataModelPackage = registeredBusinessDataModelPackage instanceof BusinessDataModelPackageImpl ? (BusinessDataModelPackageImpl)registeredBusinessDataModelPackage : new BusinessDataModelPackageImpl();

        isInited = true;

        // Create package meta-data objects
        theBusinessDataModelPackage.createPackageContents();

        // Initialize created meta-data
        theBusinessDataModelPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theBusinessDataModelPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(BusinessDataModelPackage.eNS_URI, theBusinessDataModelPackage);
        return theBusinessDataModelPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBusinessObjectModel() {
        return businessObjectModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBusinessObjectModel_Packages() {
        return (EReference)businessObjectModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBusinessObjectModel_GroupId() {
        return (EAttribute)businessObjectModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPackage() {
        return packageEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPackage_BusinessObjects() {
        return (EReference)packageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPackage_Name() {
        return (EAttribute)packageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBusinessObject() {
        return businessObjectEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBusinessObject_QualifiedName() {
        return (EAttribute)businessObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBusinessObject_SimpleName() {
        return (EAttribute)businessObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBusinessObject_Description() {
        return (EAttribute)businessObjectEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBusinessObject_Fields() {
        return (EReference)businessObjectEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBusinessObject_UniqueConstraints() {
        return (EReference)businessObjectEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBusinessObject_Indexes() {
        return (EReference)businessObjectEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBusinessObject_Queries() {
        return (EReference)businessObjectEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBusinessObject_DefaultQueries() {
        return (EReference)businessObjectEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getField() {
        return fieldEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getField_Name() {
        return (EAttribute)fieldEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getField_Nullable() {
        return (EAttribute)fieldEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getField_Collection() {
        return (EAttribute)fieldEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUniqueConstraint() {
        return uniqueConstraintEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUniqueConstraint_Name() {
        return (EAttribute)uniqueConstraintEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUniqueConstraint_FieldNames() {
        return (EAttribute)uniqueConstraintEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIndex() {
        return indexEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIndex_Name() {
        return (EAttribute)indexEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIndex_FieldNames() {
        return (EAttribute)indexEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getQuery() {
        return queryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQuery_Name() {
        return (EAttribute)queryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQuery_Content() {
        return (EAttribute)queryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQuery_ReturnType() {
        return (EAttribute)queryEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQuery_QueryParameters() {
        return (EReference)queryEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getQueryParameter() {
        return queryParameterEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQueryParameter_Name() {
        return (EAttribute)queryParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQueryParameter_ClassName() {
        return (EAttribute)queryParameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSimpleField() {
        return simpleFieldEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimpleField_Type() {
        return (EAttribute)simpleFieldEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimpleField_Length() {
        return (EAttribute)simpleFieldEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRelationField() {
        return relationFieldEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRelationField_Reference() {
        return (EReference)relationFieldEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRelationField_Type() {
        return (EAttribute)relationFieldEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRelationField_FetchType() {
        return (EAttribute)relationFieldEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFieldType() {
        return fieldTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getRelationType() {
        return relationTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFetchType() {
        return fetchTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessDataModelFactory getBusinessDataModelFactory() {
        return (BusinessDataModelFactory)getEFactoryInstance();
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
        businessObjectModelEClass = createEClass(BUSINESS_OBJECT_MODEL);
        createEReference(businessObjectModelEClass, BUSINESS_OBJECT_MODEL__PACKAGES);
        createEAttribute(businessObjectModelEClass, BUSINESS_OBJECT_MODEL__GROUP_ID);

        packageEClass = createEClass(PACKAGE);
        createEReference(packageEClass, PACKAGE__BUSINESS_OBJECTS);
        createEAttribute(packageEClass, PACKAGE__NAME);

        businessObjectEClass = createEClass(BUSINESS_OBJECT);
        createEAttribute(businessObjectEClass, BUSINESS_OBJECT__QUALIFIED_NAME);
        createEAttribute(businessObjectEClass, BUSINESS_OBJECT__SIMPLE_NAME);
        createEAttribute(businessObjectEClass, BUSINESS_OBJECT__DESCRIPTION);
        createEReference(businessObjectEClass, BUSINESS_OBJECT__FIELDS);
        createEReference(businessObjectEClass, BUSINESS_OBJECT__UNIQUE_CONSTRAINTS);
        createEReference(businessObjectEClass, BUSINESS_OBJECT__INDEXES);
        createEReference(businessObjectEClass, BUSINESS_OBJECT__QUERIES);
        createEReference(businessObjectEClass, BUSINESS_OBJECT__DEFAULT_QUERIES);

        fieldEClass = createEClass(FIELD);
        createEAttribute(fieldEClass, FIELD__NAME);
        createEAttribute(fieldEClass, FIELD__NULLABLE);
        createEAttribute(fieldEClass, FIELD__COLLECTION);

        uniqueConstraintEClass = createEClass(UNIQUE_CONSTRAINT);
        createEAttribute(uniqueConstraintEClass, UNIQUE_CONSTRAINT__NAME);
        createEAttribute(uniqueConstraintEClass, UNIQUE_CONSTRAINT__FIELD_NAMES);

        indexEClass = createEClass(INDEX);
        createEAttribute(indexEClass, INDEX__NAME);
        createEAttribute(indexEClass, INDEX__FIELD_NAMES);

        queryEClass = createEClass(QUERY);
        createEAttribute(queryEClass, QUERY__NAME);
        createEAttribute(queryEClass, QUERY__CONTENT);
        createEAttribute(queryEClass, QUERY__RETURN_TYPE);
        createEReference(queryEClass, QUERY__QUERY_PARAMETERS);

        queryParameterEClass = createEClass(QUERY_PARAMETER);
        createEAttribute(queryParameterEClass, QUERY_PARAMETER__NAME);
        createEAttribute(queryParameterEClass, QUERY_PARAMETER__CLASS_NAME);

        simpleFieldEClass = createEClass(SIMPLE_FIELD);
        createEAttribute(simpleFieldEClass, SIMPLE_FIELD__TYPE);
        createEAttribute(simpleFieldEClass, SIMPLE_FIELD__LENGTH);

        relationFieldEClass = createEClass(RELATION_FIELD);
        createEReference(relationFieldEClass, RELATION_FIELD__REFERENCE);
        createEAttribute(relationFieldEClass, RELATION_FIELD__TYPE);
        createEAttribute(relationFieldEClass, RELATION_FIELD__FETCH_TYPE);

        // Create enums
        fieldTypeEEnum = createEEnum(FIELD_TYPE);
        relationTypeEEnum = createEEnum(RELATION_TYPE);
        fetchTypeEEnum = createEEnum(FETCH_TYPE);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        simpleFieldEClass.getESuperTypes().add(this.getField());
        relationFieldEClass.getESuperTypes().add(this.getField());

        // Initialize classes, features, and operations; add parameters
        initEClass(businessObjectModelEClass, BusinessObjectModel.class, "BusinessObjectModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBusinessObjectModel_Packages(), this.getPackage(), null, "packages", null, 0, -1, BusinessObjectModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBusinessObjectModel_GroupId(), ecorePackage.getEString(), "groupId", null, 0, 1, BusinessObjectModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(packageEClass, org.bonitasoft.studio.businessobject.editor.model.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPackage_BusinessObjects(), this.getBusinessObject(), null, "businessObjects", null, 0, -1, org.bonitasoft.studio.businessobject.editor.model.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPackage_Name(), ecorePackage.getEString(), "name", null, 0, 1, org.bonitasoft.studio.businessobject.editor.model.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(businessObjectEClass, BusinessObject.class, "BusinessObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBusinessObject_QualifiedName(), ecorePackage.getEString(), "qualifiedName", null, 0, 1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBusinessObject_SimpleName(), ecorePackage.getEString(), "simpleName", null, 0, 1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBusinessObject_Description(), ecorePackage.getEString(), "description", null, 0, 1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBusinessObject_Fields(), this.getField(), null, "fields", null, 0, -1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBusinessObject_UniqueConstraints(), this.getUniqueConstraint(), null, "uniqueConstraints", null, 0, -1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBusinessObject_Indexes(), this.getIndex(), null, "indexes", null, 0, -1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBusinessObject_Queries(), this.getQuery(), null, "queries", null, 0, -1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBusinessObject_DefaultQueries(), this.getQuery(), null, "defaultQueries", null, 0, -1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fieldEClass, Field.class, "Field", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getField_Name(), ecorePackage.getEString(), "name", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getField_Nullable(), ecorePackage.getEBoolean(), "nullable", "true", 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getField_Collection(), ecorePackage.getEBoolean(), "collection", "false", 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(uniqueConstraintEClass, UniqueConstraint.class, "UniqueConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUniqueConstraint_Name(), ecorePackage.getEString(), "name", null, 0, 1, UniqueConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUniqueConstraint_FieldNames(), ecorePackage.getEString(), "fieldNames", null, 0, -1, UniqueConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(indexEClass, Index.class, "Index", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIndex_Name(), ecorePackage.getEString(), "name", null, 0, 1, Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIndex_FieldNames(), ecorePackage.getEString(), "fieldNames", null, 0, -1, Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryEClass, Query.class, "Query", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQuery_Name(), ecorePackage.getEString(), "name", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getQuery_Content(), ecorePackage.getEString(), "content", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getQuery_ReturnType(), ecorePackage.getEString(), "returnType", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuery_QueryParameters(), this.getQueryParameter(), null, "queryParameters", null, 0, -1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryParameterEClass, QueryParameter.class, "QueryParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQueryParameter_Name(), ecorePackage.getEString(), "name", null, 0, 1, QueryParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getQueryParameter_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, QueryParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(simpleFieldEClass, SimpleField.class, "SimpleField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSimpleField_Type(), this.getFieldType(), "type", "STRING", 0, 1, SimpleField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimpleField_Length(), ecorePackage.getEInt(), "length", null, 0, 1, SimpleField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(relationFieldEClass, RelationField.class, "RelationField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRelationField_Reference(), this.getBusinessObject(), null, "reference", null, 0, 1, RelationField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelationField_Type(), this.getRelationType(), "type", "AGGREGATION", 0, 1, RelationField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelationField_FetchType(), this.getFetchType(), "fetchType", "LAZY", 0, 1, RelationField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(fieldTypeEEnum, FieldType.class, "FieldType");
        addEEnumLiteral(fieldTypeEEnum, FieldType.STRING);
        addEEnumLiteral(fieldTypeEEnum, FieldType.TEXT);
        addEEnumLiteral(fieldTypeEEnum, FieldType.INTEGER);
        addEEnumLiteral(fieldTypeEEnum, FieldType.DOUBLE);
        addEEnumLiteral(fieldTypeEEnum, FieldType.LONG);
        addEEnumLiteral(fieldTypeEEnum, FieldType.FLOAT);
        addEEnumLiteral(fieldTypeEEnum, FieldType.DATE);
        addEEnumLiteral(fieldTypeEEnum, FieldType.BOOLEAN);
        addEEnumLiteral(fieldTypeEEnum, FieldType.BYTE);
        addEEnumLiteral(fieldTypeEEnum, FieldType.SHORT);
        addEEnumLiteral(fieldTypeEEnum, FieldType.CHAR);
        addEEnumLiteral(fieldTypeEEnum, FieldType.LOCALDATETIME);
        addEEnumLiteral(fieldTypeEEnum, FieldType.LOCALDATE);
        addEEnumLiteral(fieldTypeEEnum, FieldType.OFFSETDATETIME);

        initEEnum(relationTypeEEnum, RelationType.class, "RelationType");
        addEEnumLiteral(relationTypeEEnum, RelationType.AGGREGATION);
        addEEnumLiteral(relationTypeEEnum, RelationType.COMPOSITION);

        initEEnum(fetchTypeEEnum, FetchType.class, "FetchType");
        addEEnumLiteral(fetchTypeEEnum, FetchType.EAGER);
        addEEnumLiteral(fetchTypeEEnum, FetchType.LAZY);

        // Create resource
        createResource(eNS_URI);
    }

} //BusinessDataModelPackageImpl
