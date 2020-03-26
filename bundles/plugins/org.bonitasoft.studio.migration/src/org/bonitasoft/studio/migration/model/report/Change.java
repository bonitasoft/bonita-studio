/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.migration.model.report;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Change#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Change#isReviewed <em>Reviewed</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Change#getStatus <em>Status</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Change#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Change#getTransformationKind <em>Transformation Kind</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Change#getElementUUID <em>Element UUID</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Change#getElementName <em>Element Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Change#getElementType <em>Element Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getChange()
 * @model
 * @generated
 */
public interface Change extends EObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getChange_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Change#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Reviewed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reviewed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reviewed</em>' attribute.
	 * @see #setReviewed(boolean)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getChange_Reviewed()
	 * @model default="false"
	 * @generated
	 */
	boolean isReviewed();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Change#isReviewed <em>Reviewed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reviewed</em>' attribute.
	 * @see #isReviewed()
	 * @generated
	 */
	void setReviewed(boolean value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(int)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getChange_Status()
	 * @model default="0"
	 * @generated
	 */
	int getStatus();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Change#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(int value);

	/**
	 * Returns the value of the '<em><b>Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Name</em>' attribute.
	 * @see #setPropertyName(String)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getChange_PropertyName()
	 * @model
	 * @generated
	 */
	String getPropertyName();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Change#getPropertyName <em>Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Name</em>' attribute.
	 * @see #getPropertyName()
	 * @generated
	 */
	void setPropertyName(String value);

	/**
	 * Returns the value of the '<em><b>Transformation Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformation Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformation Kind</em>' attribute.
	 * @see #setTransformationKind(String)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getChange_TransformationKind()
	 * @model
	 * @generated
	 */
	String getTransformationKind();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Change#getTransformationKind <em>Transformation Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformation Kind</em>' attribute.
	 * @see #getTransformationKind()
	 * @generated
	 */
	void setTransformationKind(String value);

	/**
	 * Returns the value of the '<em><b>Element UUID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element UUID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element UUID</em>' attribute.
	 * @see #setElementUUID(String)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getChange_ElementUUID()
	 * @model
	 * @generated
	 */
	String getElementUUID();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Change#getElementUUID <em>Element UUID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element UUID</em>' attribute.
	 * @see #getElementUUID()
	 * @generated
	 */
	void setElementUUID(String value);

	/**
	 * Returns the value of the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Name</em>' attribute.
	 * @see #setElementName(String)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getChange_ElementName()
	 * @model
	 * @generated
	 */
	String getElementName();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Change#getElementName <em>Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Name</em>' attribute.
	 * @see #getElementName()
	 * @generated
	 */
	void setElementName(String value);

	/**
	 * Returns the value of the '<em><b>Element Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Type</em>' attribute.
	 * @see #setElementType(String)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getChange_ElementType()
	 * @model
	 * @generated
	 */
	String getElementType();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Change#getElementType <em>Element Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Type</em>' attribute.
	 * @see #getElementType()
	 * @generated
	 */
	void setElementType(String value);

} // Change
