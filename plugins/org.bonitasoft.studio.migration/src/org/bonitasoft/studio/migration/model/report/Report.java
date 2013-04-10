/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.migration.model.report;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Report#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Report#getChanges <em>Changes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Report#getCompletion <em>Completion</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Report#getSourceRelease <em>Source Release</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.Report#getTargetRelease <em>Target Release</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getReport()
 * @model
 * @generated
 */
public interface Report extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getReport_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Report#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Changes</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.migration.model.report.Change}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changes</em>' containment reference list.
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getReport_Changes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Change> getChanges();

	/**
	 * Returns the value of the '<em><b>Completion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completion</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completion</em>' attribute.
	 * @see #setCompletion(double)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getReport_Completion()
	 * @model
	 * @generated
	 */
	double getCompletion();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Report#getCompletion <em>Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completion</em>' attribute.
	 * @see #getCompletion()
	 * @generated
	 */
	void setCompletion(double value);

	/**
	 * Returns the value of the '<em><b>Source Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Release</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Release</em>' attribute.
	 * @see #setSourceRelease(String)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getReport_SourceRelease()
	 * @model
	 * @generated
	 */
	String getSourceRelease();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Report#getSourceRelease <em>Source Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Release</em>' attribute.
	 * @see #getSourceRelease()
	 * @generated
	 */
	void setSourceRelease(String value);

	/**
	 * Returns the value of the '<em><b>Target Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Release</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Release</em>' attribute.
	 * @see #setTargetRelease(String)
	 * @see org.bonitasoft.studio.migration.model.report.MigrationReportPackage#getReport_TargetRelease()
	 * @model
	 * @generated
	 */
	String getTargetRelease();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.migration.model.report.Report#getTargetRelease <em>Target Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Release</em>' attribute.
	 * @see #getTargetRelease()
	 * @generated
	 */
	void setTargetRelease(String value);

} // Report
