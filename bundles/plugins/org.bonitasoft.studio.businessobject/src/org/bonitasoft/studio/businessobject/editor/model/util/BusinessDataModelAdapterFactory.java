/**
 */
package org.bonitasoft.studio.businessobject.editor.model.util;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage
 * @generated
 */
public class BusinessDataModelAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static BusinessDataModelPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessDataModelAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = BusinessDataModelPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BusinessDataModelSwitch<Adapter> modelSwitch =
        new BusinessDataModelSwitch<Adapter>() {
            @Override
            public Adapter caseBusinessObjectModel(BusinessObjectModel object) {
                return createBusinessObjectModelAdapter();
            }
            @Override
            public Adapter casePackage(org.bonitasoft.studio.businessobject.editor.model.Package object) {
                return createPackageAdapter();
            }
            @Override
            public Adapter caseBusinessObject(BusinessObject object) {
                return createBusinessObjectAdapter();
            }
            @Override
            public Adapter caseField(Field object) {
                return createFieldAdapter();
            }
            @Override
            public Adapter caseUniqueConstraint(UniqueConstraint object) {
                return createUniqueConstraintAdapter();
            }
            @Override
            public Adapter caseIndex(Index object) {
                return createIndexAdapter();
            }
            @Override
            public Adapter caseQuery(Query object) {
                return createQueryAdapter();
            }
            @Override
            public Adapter caseQueryParameter(QueryParameter object) {
                return createQueryParameterAdapter();
            }
            @Override
            public Adapter caseSimpleField(SimpleField object) {
                return createSimpleFieldAdapter();
            }
            @Override
            public Adapter caseRelationField(RelationField object) {
                return createRelationFieldAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel <em>Business Object Model</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel
     * @generated
     */
    public Adapter createBusinessObjectModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.Package <em>Package</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.Package
     * @generated
     */
    public Adapter createPackageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject <em>Business Object</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessObject
     * @generated
     */
    public Adapter createBusinessObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.Field <em>Field</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.Field
     * @generated
     */
    public Adapter createFieldAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint <em>Unique Constraint</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint
     * @generated
     */
    public Adapter createUniqueConstraintAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.Index <em>Index</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.Index
     * @generated
     */
    public Adapter createIndexAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.Query
     * @generated
     */
    public Adapter createQueryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.QueryParameter <em>Query Parameter</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.QueryParameter
     * @generated
     */
    public Adapter createQueryParameterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.SimpleField <em>Simple Field</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.SimpleField
     * @generated
     */
    public Adapter createSimpleFieldAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.businessobject.editor.model.RelationField <em>Relation Field</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.businessobject.editor.model.RelationField
     * @generated
     */
    public Adapter createRelationFieldAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //BusinessDataModelAdapterFactory
