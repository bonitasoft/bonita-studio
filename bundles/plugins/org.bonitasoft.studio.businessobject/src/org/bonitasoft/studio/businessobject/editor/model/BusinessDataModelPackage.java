/**
 */
package org.bonitasoft.studio.businessobject.editor.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory
 * @model kind="package"
 * @generated
 */
public interface BusinessDataModelPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "model";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.bonitasoft.org/studio/bdm";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "businessDataModel";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    BusinessDataModelPackage eINSTANCE = org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl.init();

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectModelImpl <em>Business Object Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectModelImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getBusinessObjectModel()
     * @generated
     */
    int BUSINESS_OBJECT_MODEL = 0;

    /**
     * The feature id for the '<em><b>Packages</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT_MODEL__PACKAGES = 0;

    /**
     * The feature id for the '<em><b>Group Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT_MODEL__GROUP_ID = 1;

    /**
     * The number of structural features of the '<em>Business Object Model</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT_MODEL_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Business Object Model</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT_MODEL_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.PackageImpl <em>Package</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.PackageImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getPackage()
     * @generated
     */
    int PACKAGE = 1;

    /**
     * The feature id for the '<em><b>Business Objects</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE__BUSINESS_OBJECTS = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE__NAME = 1;

    /**
     * The number of structural features of the '<em>Package</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Package</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PACKAGE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl <em>Business Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getBusinessObject()
     * @generated
     */
    int BUSINESS_OBJECT = 2;

    /**
     * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT__QUALIFIED_NAME = 0;

    /**
     * The feature id for the '<em><b>Simple Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT__SIMPLE_NAME = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Fields</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT__FIELDS = 3;

    /**
     * The feature id for the '<em><b>Unique Constraints</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT__UNIQUE_CONSTRAINTS = 4;

    /**
     * The feature id for the '<em><b>Indexes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT__INDEXES = 5;

    /**
     * The feature id for the '<em><b>Queries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT__QUERIES = 6;

    /**
     * The feature id for the '<em><b>Default Queries</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT__DEFAULT_QUERIES = 7;

    /**
     * The number of structural features of the '<em>Business Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT_FEATURE_COUNT = 8;

    /**
     * The number of operations of the '<em>Business Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BUSINESS_OBJECT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.FieldImpl <em>Field</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.FieldImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getField()
     * @generated
     */
    int FIELD = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD__NAME = 0;

    /**
     * The feature id for the '<em><b>Nullable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD__NULLABLE = 1;

    /**
     * The feature id for the '<em><b>Collection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD__COLLECTION = 2;

    /**
     * The number of structural features of the '<em>Field</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Field</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIELD_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.UniqueConstraintImpl <em>Unique Constraint</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.UniqueConstraintImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getUniqueConstraint()
     * @generated
     */
    int UNIQUE_CONSTRAINT = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIQUE_CONSTRAINT__NAME = 0;

    /**
     * The feature id for the '<em><b>Field Names</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIQUE_CONSTRAINT__FIELD_NAMES = 1;

    /**
     * The number of structural features of the '<em>Unique Constraint</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIQUE_CONSTRAINT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Unique Constraint</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIQUE_CONSTRAINT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.IndexImpl <em>Index</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.IndexImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getIndex()
     * @generated
     */
    int INDEX = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDEX__NAME = 0;

    /**
     * The feature id for the '<em><b>Field Names</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDEX__FIELD_NAMES = 1;

    /**
     * The number of structural features of the '<em>Index</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDEX_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Index</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INDEX_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.QueryImpl <em>Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.QueryImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getQuery()
     * @generated
     */
    int QUERY = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__NAME = 0;

    /**
     * The feature id for the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__CONTENT = 1;

    /**
     * The feature id for the '<em><b>Return Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__RETURN_TYPE = 2;

    /**
     * The feature id for the '<em><b>Query Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__QUERY_PARAMETERS = 3;

    /**
     * The number of structural features of the '<em>Query</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Query</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.QueryParameterImpl <em>Query Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.QueryParameterImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getQueryParameter()
     * @generated
     */
    int QUERY_PARAMETER = 7;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_PARAMETER__NAME = 0;

    /**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_PARAMETER__CLASS_NAME = 1;

    /**
     * The number of structural features of the '<em>Query Parameter</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_PARAMETER_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Query Parameter</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_PARAMETER_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.SimpleFieldImpl <em>Simple Field</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.SimpleFieldImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getSimpleField()
     * @generated
     */
    int SIMPLE_FIELD = 8;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_FIELD__NAME = FIELD__NAME;

    /**
     * The feature id for the '<em><b>Nullable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_FIELD__NULLABLE = FIELD__NULLABLE;

    /**
     * The feature id for the '<em><b>Collection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_FIELD__COLLECTION = FIELD__COLLECTION;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_FIELD__TYPE = FIELD_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Length</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_FIELD__LENGTH = FIELD_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Simple Field</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Simple Field</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_FIELD_OPERATION_COUNT = FIELD_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.RelationFieldImpl <em>Relation Field</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.RelationFieldImpl
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getRelationField()
     * @generated
     */
    int RELATION_FIELD = 9;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FIELD__NAME = FIELD__NAME;

    /**
     * The feature id for the '<em><b>Nullable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FIELD__NULLABLE = FIELD__NULLABLE;

    /**
     * The feature id for the '<em><b>Collection</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FIELD__COLLECTION = FIELD__COLLECTION;

    /**
     * The feature id for the '<em><b>Reference</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FIELD__REFERENCE = FIELD_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FIELD__TYPE = FIELD_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Fetch Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FIELD__FETCH_TYPE = FIELD_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Relation Field</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Relation Field</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FIELD_OPERATION_COUNT = FIELD_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.FieldType <em>Field Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.FieldType
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getFieldType()
     * @generated
     */
    int FIELD_TYPE = 10;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.RelationType <em>Relation Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.RelationType
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getRelationType()
     * @generated
     */
    int RELATION_TYPE = 11;

    /**
     * The meta object id for the '{@link org.bonitasoft.studio.businessobject.editor.model.FetchType <em>Fetch Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.businessobject.editor.model.FetchType
     * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getFetchType()
     * @generated
     */
    int FETCH_TYPE = 12;


    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel <em>Business Object Model</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Business Object Model</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel
     * @generated
     */
    EClass getBusinessObjectModel();

    /**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel#getPackages <em>Packages</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Packages</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel#getPackages()
     * @see #getBusinessObjectModel()
     * @generated
     */
    EReference getBusinessObjectModel_Packages();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel#getGroupId <em>Group Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Group Id</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel#getGroupId()
     * @see #getBusinessObjectModel()
     * @generated
     */
    EAttribute getBusinessObjectModel_GroupId();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.Package <em>Package</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Package</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Package
     * @generated
     */
    EClass getPackage();

    /**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.businessobject.editor.model.Package#getBusinessObjects <em>Business Objects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Business Objects</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Package#getBusinessObjects()
     * @see #getPackage()
     * @generated
     */
    EReference getPackage_BusinessObjects();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.Package#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Package#getName()
     * @see #getPackage()
     * @generated
     */
    EAttribute getPackage_Name();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject <em>Business Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Business Object</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject
     * @generated
     */
    EClass getBusinessObject();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getQualifiedName <em>Qualified Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Qualified Name</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getQualifiedName()
     * @see #getBusinessObject()
     * @generated
     */
    EAttribute getBusinessObject_QualifiedName();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getSimpleName <em>Simple Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Simple Name</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getSimpleName()
     * @see #getBusinessObject()
     * @generated
     */
    EAttribute getBusinessObject_SimpleName();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getDescription()
     * @see #getBusinessObject()
     * @generated
     */
    EAttribute getBusinessObject_Description();

    /**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getFields <em>Fields</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Fields</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getFields()
     * @see #getBusinessObject()
     * @generated
     */
    EReference getBusinessObject_Fields();

    /**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getUniqueConstraints <em>Unique Constraints</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Unique Constraints</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getUniqueConstraints()
     * @see #getBusinessObject()
     * @generated
     */
    EReference getBusinessObject_UniqueConstraints();

    /**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getIndexes <em>Indexes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Indexes</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getIndexes()
     * @see #getBusinessObject()
     * @generated
     */
    EReference getBusinessObject_Indexes();

    /**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getQueries <em>Queries</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Queries</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getQueries()
     * @see #getBusinessObject()
     * @generated
     */
    EReference getBusinessObject_Queries();

    /**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getDefaultQueries <em>Default Queries</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Default Queries</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getDefaultQueries()
     * @see #getBusinessObject()
     * @generated
     */
    EReference getBusinessObject_DefaultQueries();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.Field <em>Field</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Field
     * @generated
     */
    EClass getField();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.Field#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Field#getName()
     * @see #getField()
     * @generated
     */
    EAttribute getField_Name();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.Field#isNullable <em>Nullable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Nullable</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Field#isNullable()
     * @see #getField()
     * @generated
     */
    EAttribute getField_Nullable();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.Field#isCollection <em>Collection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Collection</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Field#isCollection()
     * @see #getField()
     * @generated
     */
    EAttribute getField_Collection();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint <em>Unique Constraint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Unique Constraint</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint
     * @generated
     */
    EClass getUniqueConstraint();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint#getName()
     * @see #getUniqueConstraint()
     * @generated
     */
    EAttribute getUniqueConstraint_Name();

    /**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint#getFieldNames <em>Field Names</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Field Names</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint#getFieldNames()
     * @see #getUniqueConstraint()
     * @generated
     */
    EAttribute getUniqueConstraint_FieldNames();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.Index <em>Index</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Index</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Index
     * @generated
     */
    EClass getIndex();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.Index#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Index#getName()
     * @see #getIndex()
     * @generated
     */
    EAttribute getIndex_Name();

    /**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.businessobject.editor.model.Index#getFieldNames <em>Field Names</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Field Names</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Index#getFieldNames()
     * @see #getIndex()
     * @generated
     */
    EAttribute getIndex_FieldNames();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Query
     * @generated
     */
    EClass getQuery();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.Query#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Query#getName()
     * @see #getQuery()
     * @generated
     */
    EAttribute getQuery_Name();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.Query#getContent <em>Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Query#getContent()
     * @see #getQuery()
     * @generated
     */
    EAttribute getQuery_Content();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.Query#getReturnType <em>Return Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Return Type</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Query#getReturnType()
     * @see #getQuery()
     * @generated
     */
    EAttribute getQuery_ReturnType();

    /**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.businessobject.editor.model.Query#getQueryParameters <em>Query Parameters</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Query Parameters</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.Query#getQueryParameters()
     * @see #getQuery()
     * @generated
     */
    EReference getQuery_QueryParameters();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.QueryParameter <em>Query Parameter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Parameter</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.QueryParameter
     * @generated
     */
    EClass getQueryParameter();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.QueryParameter#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.QueryParameter#getName()
     * @see #getQueryParameter()
     * @generated
     */
    EAttribute getQueryParameter_Name();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.QueryParameter#getClassName <em>Class Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class Name</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.QueryParameter#getClassName()
     * @see #getQueryParameter()
     * @generated
     */
    EAttribute getQueryParameter_ClassName();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.SimpleField <em>Simple Field</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Simple Field</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.SimpleField
     * @generated
     */
    EClass getSimpleField();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.SimpleField#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.SimpleField#getType()
     * @see #getSimpleField()
     * @generated
     */
    EAttribute getSimpleField_Type();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.SimpleField#getLength <em>Length</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Length</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.SimpleField#getLength()
     * @see #getSimpleField()
     * @generated
     */
    EAttribute getSimpleField_Length();

    /**
     * Returns the meta object for class '{@link org.bonitasoft.studio.businessobject.editor.model.RelationField <em>Relation Field</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Relation Field</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.RelationField
     * @generated
     */
    EClass getRelationField();

    /**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.businessobject.editor.model.RelationField#getReference <em>Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Reference</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.RelationField#getReference()
     * @see #getRelationField()
     * @generated
     */
    EReference getRelationField_Reference();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.RelationField#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.RelationField#getType()
     * @see #getRelationField()
     * @generated
     */
    EAttribute getRelationField_Type();

    /**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.businessobject.editor.model.RelationField#getFetchType <em>Fetch Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fetch Type</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.RelationField#getFetchType()
     * @see #getRelationField()
     * @generated
     */
    EAttribute getRelationField_FetchType();

    /**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.businessobject.editor.model.FieldType <em>Field Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Field Type</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.FieldType
     * @generated
     */
    EEnum getFieldType();

    /**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.businessobject.editor.model.RelationType <em>Relation Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Relation Type</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.RelationType
     * @generated
     */
    EEnum getRelationType();

    /**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.businessobject.editor.model.FetchType <em>Fetch Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Fetch Type</em>'.
     * @see org.bonitasoft.studio.businessobject.editor.model.FetchType
     * @generated
     */
    EEnum getFetchType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    BusinessDataModelFactory getBusinessDataModelFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectModelImpl <em>Business Object Model</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectModelImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getBusinessObjectModel()
         * @generated
         */
        EClass BUSINESS_OBJECT_MODEL = eINSTANCE.getBusinessObjectModel();

        /**
         * The meta object literal for the '<em><b>Packages</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BUSINESS_OBJECT_MODEL__PACKAGES = eINSTANCE.getBusinessObjectModel_Packages();

        /**
         * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BUSINESS_OBJECT_MODEL__GROUP_ID = eINSTANCE.getBusinessObjectModel_GroupId();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.PackageImpl <em>Package</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.PackageImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getPackage()
         * @generated
         */
        EClass PACKAGE = eINSTANCE.getPackage();

        /**
         * The meta object literal for the '<em><b>Business Objects</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PACKAGE__BUSINESS_OBJECTS = eINSTANCE.getPackage_BusinessObjects();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PACKAGE__NAME = eINSTANCE.getPackage_Name();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl <em>Business Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getBusinessObject()
         * @generated
         */
        EClass BUSINESS_OBJECT = eINSTANCE.getBusinessObject();

        /**
         * The meta object literal for the '<em><b>Qualified Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BUSINESS_OBJECT__QUALIFIED_NAME = eINSTANCE.getBusinessObject_QualifiedName();

        /**
         * The meta object literal for the '<em><b>Simple Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BUSINESS_OBJECT__SIMPLE_NAME = eINSTANCE.getBusinessObject_SimpleName();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BUSINESS_OBJECT__DESCRIPTION = eINSTANCE.getBusinessObject_Description();

        /**
         * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BUSINESS_OBJECT__FIELDS = eINSTANCE.getBusinessObject_Fields();

        /**
         * The meta object literal for the '<em><b>Unique Constraints</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BUSINESS_OBJECT__UNIQUE_CONSTRAINTS = eINSTANCE.getBusinessObject_UniqueConstraints();

        /**
         * The meta object literal for the '<em><b>Indexes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BUSINESS_OBJECT__INDEXES = eINSTANCE.getBusinessObject_Indexes();

        /**
         * The meta object literal for the '<em><b>Queries</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BUSINESS_OBJECT__QUERIES = eINSTANCE.getBusinessObject_Queries();

        /**
         * The meta object literal for the '<em><b>Default Queries</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BUSINESS_OBJECT__DEFAULT_QUERIES = eINSTANCE.getBusinessObject_DefaultQueries();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.FieldImpl <em>Field</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.FieldImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getField()
         * @generated
         */
        EClass FIELD = eINSTANCE.getField();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FIELD__NAME = eINSTANCE.getField_Name();

        /**
         * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FIELD__NULLABLE = eINSTANCE.getField_Nullable();

        /**
         * The meta object literal for the '<em><b>Collection</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FIELD__COLLECTION = eINSTANCE.getField_Collection();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.UniqueConstraintImpl <em>Unique Constraint</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.UniqueConstraintImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getUniqueConstraint()
         * @generated
         */
        EClass UNIQUE_CONSTRAINT = eINSTANCE.getUniqueConstraint();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute UNIQUE_CONSTRAINT__NAME = eINSTANCE.getUniqueConstraint_Name();

        /**
         * The meta object literal for the '<em><b>Field Names</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute UNIQUE_CONSTRAINT__FIELD_NAMES = eINSTANCE.getUniqueConstraint_FieldNames();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.IndexImpl <em>Index</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.IndexImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getIndex()
         * @generated
         */
        EClass INDEX = eINSTANCE.getIndex();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INDEX__NAME = eINSTANCE.getIndex_Name();

        /**
         * The meta object literal for the '<em><b>Field Names</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INDEX__FIELD_NAMES = eINSTANCE.getIndex_FieldNames();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.QueryImpl <em>Query</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.QueryImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getQuery()
         * @generated
         */
        EClass QUERY = eINSTANCE.getQuery();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY__NAME = eINSTANCE.getQuery_Name();

        /**
         * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY__CONTENT = eINSTANCE.getQuery_Content();

        /**
         * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY__RETURN_TYPE = eINSTANCE.getQuery_ReturnType();

        /**
         * The meta object literal for the '<em><b>Query Parameters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY__QUERY_PARAMETERS = eINSTANCE.getQuery_QueryParameters();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.QueryParameterImpl <em>Query Parameter</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.QueryParameterImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getQueryParameter()
         * @generated
         */
        EClass QUERY_PARAMETER = eINSTANCE.getQueryParameter();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY_PARAMETER__NAME = eINSTANCE.getQueryParameter_Name();

        /**
         * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY_PARAMETER__CLASS_NAME = eINSTANCE.getQueryParameter_ClassName();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.SimpleFieldImpl <em>Simple Field</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.SimpleFieldImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getSimpleField()
         * @generated
         */
        EClass SIMPLE_FIELD = eINSTANCE.getSimpleField();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMPLE_FIELD__TYPE = eINSTANCE.getSimpleField_Type();

        /**
         * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMPLE_FIELD__LENGTH = eINSTANCE.getSimpleField_Length();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.impl.RelationFieldImpl <em>Relation Field</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.RelationFieldImpl
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getRelationField()
         * @generated
         */
        EClass RELATION_FIELD = eINSTANCE.getRelationField();

        /**
         * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATION_FIELD__REFERENCE = eINSTANCE.getRelationField_Reference();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATION_FIELD__TYPE = eINSTANCE.getRelationField_Type();

        /**
         * The meta object literal for the '<em><b>Fetch Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATION_FIELD__FETCH_TYPE = eINSTANCE.getRelationField_FetchType();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.FieldType <em>Field Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.FieldType
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getFieldType()
         * @generated
         */
        EEnum FIELD_TYPE = eINSTANCE.getFieldType();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.RelationType <em>Relation Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.RelationType
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getRelationType()
         * @generated
         */
        EEnum RELATION_TYPE = eINSTANCE.getRelationType();

        /**
         * The meta object literal for the '{@link org.bonitasoft.studio.businessobject.editor.model.FetchType <em>Fetch Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.businessobject.editor.model.FetchType
         * @see org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelPackageImpl#getFetchType()
         * @generated
         */
        EEnum FETCH_TYPE = eINSTANCE.getFetchType();

    }

} //BusinessDataModelPackage
