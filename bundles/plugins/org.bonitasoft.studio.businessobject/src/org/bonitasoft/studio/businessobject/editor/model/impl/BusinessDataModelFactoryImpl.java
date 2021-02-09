/**
 */
package org.bonitasoft.studio.businessobject.editor.model.impl;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.FetchType;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class BusinessDataModelFactoryImpl extends EFactoryImpl implements BusinessDataModelFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static BusinessDataModelFactory init() {
        try {
            BusinessDataModelFactory theBusinessDataModelFactory = (BusinessDataModelFactory)EPackage.Registry.INSTANCE.getEFactory(BusinessDataModelPackage.eNS_URI);
            if (theBusinessDataModelFactory != null) {
                return theBusinessDataModelFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new BusinessDataModelFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessDataModelFactoryImpl() {
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
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL: return createBusinessObjectModel();
            case BusinessDataModelPackage.PACKAGE: return createPackage();
            case BusinessDataModelPackage.BUSINESS_OBJECT: return createBusinessObject();
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT: return createUniqueConstraint();
            case BusinessDataModelPackage.INDEX: return createIndex();
            case BusinessDataModelPackage.QUERY: return createQuery();
            case BusinessDataModelPackage.QUERY_PARAMETER: return createQueryParameter();
            case BusinessDataModelPackage.SIMPLE_FIELD: return createSimpleField();
            case BusinessDataModelPackage.RELATION_FIELD: return createRelationField();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case BusinessDataModelPackage.FIELD_TYPE:
                return createFieldTypeFromString(eDataType, initialValue);
            case BusinessDataModelPackage.RELATION_TYPE:
                return createRelationTypeFromString(eDataType, initialValue);
            case BusinessDataModelPackage.FETCH_TYPE:
                return createFetchTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case BusinessDataModelPackage.FIELD_TYPE:
                return convertFieldTypeToString(eDataType, instanceValue);
            case BusinessDataModelPackage.RELATION_TYPE:
                return convertRelationTypeToString(eDataType, instanceValue);
            case BusinessDataModelPackage.FETCH_TYPE:
                return convertFetchTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessObjectModel createBusinessObjectModel() {
        BusinessObjectModelImpl businessObjectModel = new BusinessObjectModelImpl();
        return businessObjectModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.bonitasoft.studio.businessobject.editor.model.Package createPackage() {
        PackageImpl package_ = new PackageImpl();
        return package_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessObject createBusinessObject() {
        BusinessObjectImpl businessObject = new BusinessObjectImpl();
        return businessObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UniqueConstraint createUniqueConstraint() {
        UniqueConstraintImpl uniqueConstraint = new UniqueConstraintImpl();
        return uniqueConstraint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Index createIndex() {
        IndexImpl index = new IndexImpl();
        return index;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Query createQuery() {
        QueryImpl query = new QueryImpl();
        return query;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryParameter createQueryParameter() {
        QueryParameterImpl queryParameter = new QueryParameterImpl();
        return queryParameter;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SimpleField createSimpleField() {
        SimpleFieldImpl simpleField = new SimpleFieldImpl();
        return simpleField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RelationField createRelationField() {
        RelationFieldImpl relationField = new RelationFieldImpl();
        return relationField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FieldType createFieldTypeFromString(EDataType eDataType, String initialValue) {
        FieldType result = FieldType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFieldTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RelationType createRelationTypeFromString(EDataType eDataType, String initialValue) {
        RelationType result = RelationType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertRelationTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FetchType createFetchTypeFromString(EDataType eDataType, String initialValue) {
        FetchType result = FetchType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFetchTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessDataModelPackage getBusinessDataModelPackage() {
        return (BusinessDataModelPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static BusinessDataModelPackage getPackage() {
        return BusinessDataModelPackage.eINSTANCE;
    }

} //BusinessDataModelFactoryImpl
