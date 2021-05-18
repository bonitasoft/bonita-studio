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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage
 * @generated
 */
public class BusinessDataModelSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static BusinessDataModelPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessDataModelSwitch() {
        if (modelPackage == null) {
            modelPackage = BusinessDataModelPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case BusinessDataModelPackage.BUSINESS_OBJECT_MODEL: {
                BusinessObjectModel businessObjectModel = (BusinessObjectModel)theEObject;
                T result = caseBusinessObjectModel(businessObjectModel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BusinessDataModelPackage.PACKAGE: {
                org.bonitasoft.studio.businessobject.editor.model.Package package_ = (org.bonitasoft.studio.businessobject.editor.model.Package)theEObject;
                T result = casePackage(package_);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BusinessDataModelPackage.BUSINESS_OBJECT: {
                BusinessObject businessObject = (BusinessObject)theEObject;
                T result = caseBusinessObject(businessObject);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BusinessDataModelPackage.FIELD: {
                Field field = (Field)theEObject;
                T result = caseField(field);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BusinessDataModelPackage.UNIQUE_CONSTRAINT: {
                UniqueConstraint uniqueConstraint = (UniqueConstraint)theEObject;
                T result = caseUniqueConstraint(uniqueConstraint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BusinessDataModelPackage.INDEX: {
                Index index = (Index)theEObject;
                T result = caseIndex(index);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BusinessDataModelPackage.QUERY: {
                Query query = (Query)theEObject;
                T result = caseQuery(query);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BusinessDataModelPackage.QUERY_PARAMETER: {
                QueryParameter queryParameter = (QueryParameter)theEObject;
                T result = caseQueryParameter(queryParameter);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BusinessDataModelPackage.SIMPLE_FIELD: {
                SimpleField simpleField = (SimpleField)theEObject;
                T result = caseSimpleField(simpleField);
                if (result == null) result = caseField(simpleField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case BusinessDataModelPackage.RELATION_FIELD: {
                RelationField relationField = (RelationField)theEObject;
                T result = caseRelationField(relationField);
                if (result == null) result = caseField(relationField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Business Object Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Business Object Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBusinessObjectModel(BusinessObjectModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Package</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePackage(org.bonitasoft.studio.businessobject.editor.model.Package object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Business Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Business Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBusinessObject(BusinessObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Field</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseField(Field object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Unique Constraint</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Unique Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUniqueConstraint(UniqueConstraint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Index</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Index</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIndex(Index object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseQuery(Query object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Query Parameter</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseQueryParameter(QueryParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Simple Field</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Simple Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSimpleField(SimpleField object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Relation Field</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Relation Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRelationField(RelationField object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //BusinessDataModelSwitch
