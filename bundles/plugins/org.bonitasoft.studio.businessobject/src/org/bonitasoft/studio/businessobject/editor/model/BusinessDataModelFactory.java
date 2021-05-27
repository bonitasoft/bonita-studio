/**
 */
package org.bonitasoft.studio.businessobject.editor.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage
 * @generated
 */
public interface BusinessDataModelFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    BusinessDataModelFactory eINSTANCE = org.bonitasoft.studio.businessobject.editor.model.impl.BusinessDataModelFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Business Object Model</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Business Object Model</em>'.
     * @generated
     */
    BusinessObjectModel createBusinessObjectModel();

    /**
     * Returns a new object of class '<em>Package</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Package</em>'.
     * @generated
     */
    Package createPackage();

    /**
     * Returns a new object of class '<em>Business Object</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Business Object</em>'.
     * @generated
     */
    BusinessObject createBusinessObject();

    /**
     * Returns a new object of class '<em>Unique Constraint</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Unique Constraint</em>'.
     * @generated
     */
    UniqueConstraint createUniqueConstraint();

    /**
     * Returns a new object of class '<em>Index</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Index</em>'.
     * @generated
     */
    Index createIndex();

    /**
     * Returns a new object of class '<em>Query</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Query</em>'.
     * @generated
     */
    Query createQuery();

    /**
     * Returns a new object of class '<em>Query Parameter</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Parameter</em>'.
     * @generated
     */
    QueryParameter createQueryParameter();

    /**
     * Returns a new object of class '<em>Simple Field</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Simple Field</em>'.
     * @generated
     */
    SimpleField createSimpleField();

    /**
     * Returns a new object of class '<em>Relation Field</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Relation Field</em>'.
     * @generated
     */
    RelationField createRelationField();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    BusinessDataModelPackage getBusinessDataModelPackage();

} //BusinessDataModelFactory
