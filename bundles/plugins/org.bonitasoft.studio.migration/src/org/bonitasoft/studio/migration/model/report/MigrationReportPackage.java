/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.migration.model.report;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.migration.model.report.MigrationReportFactory
 * @model kind="package"
 * @generated
 */
public interface MigrationReportPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "report";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/migration/report";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "report";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MigrationReportPackage eINSTANCE = org.bonitasoft.studio.migration.model.report.impl.MigrationReportPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl <em>Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.migration.model.report.impl.ChangeImpl
	 * @see org.bonitasoft.studio.migration.model.report.impl.MigrationReportPackageImpl#getChange()
	 * @generated
	 */
	int CHANGE = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Reviewed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__REVIEWED = 1;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__STATUS = 2;

	/**
	 * The feature id for the '<em><b>Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__PROPERTY_NAME = 3;

	/**
	 * The feature id for the '<em><b>Transformation Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__TRANSFORMATION_KIND = 4;

	/**
	 * The feature id for the '<em><b>Element UUID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__ELEMENT_UUID = 5;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__ELEMENT_NAME = 6;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__ELEMENT_TYPE = 7;

	/**
	 * The number of structural features of the '<em>Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.bonitasoft.studio.migration.model.report.impl.ReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.migration.model.report.impl.ReportImpl
	 * @see org.bonitasoft.studio.migration.model.report.impl.MigrationReportPackageImpl#getReport()
	 * @generated
	 */
	int REPORT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__CHANGES = 1;

	/**
	 * The feature id for the '<em><b>Completion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__COMPLETION = 2;

	/**
	 * The feature id for the '<em><b>Source Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__SOURCE_RELEASE = 3;

	/**
	 * The feature id for the '<em><b>Target Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__TARGET_RELEASE = 4;

	/**
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_FEATURE_COUNT = 5;


	/**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.migration.model.report.Change <em>Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Change
	 * @generated
	 */
	EClass getChange();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Change#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Change#getDescription()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Change#isReviewed <em>Reviewed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reviewed</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Change#isReviewed()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_Reviewed();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Change#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Change#getStatus()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_Status();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Change#getPropertyName <em>Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Property Name</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Change#getPropertyName()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_PropertyName();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Change#getTransformationKind <em>Transformation Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transformation Kind</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Change#getTransformationKind()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_TransformationKind();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Change#getElementUUID <em>Element UUID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element UUID</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Change#getElementUUID()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_ElementUUID();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Change#getElementName <em>Element Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Name</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Change#getElementName()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_ElementName();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Change#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Type</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Change#getElementType()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_ElementType();

	/**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.migration.model.report.Report <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Report
	 * @generated
	 */
	EClass getReport();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Report#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Report#getName()
	 * @see #getReport()
	 * @generated
	 */
	EAttribute getReport_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.migration.model.report.Report#getChanges <em>Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Changes</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Report#getChanges()
	 * @see #getReport()
	 * @generated
	 */
	EReference getReport_Changes();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Report#getCompletion <em>Completion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Completion</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Report#getCompletion()
	 * @see #getReport()
	 * @generated
	 */
	EAttribute getReport_Completion();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Report#getSourceRelease <em>Source Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Release</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Report#getSourceRelease()
	 * @see #getReport()
	 * @generated
	 */
	EAttribute getReport_SourceRelease();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.migration.model.report.Report#getTargetRelease <em>Target Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Release</em>'.
	 * @see org.bonitasoft.studio.migration.model.report.Report#getTargetRelease()
	 * @see #getReport()
	 * @generated
	 */
	EAttribute getReport_TargetRelease();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MigrationReportFactory getMigrationReportFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl <em>Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.migration.model.report.impl.ChangeImpl
		 * @see org.bonitasoft.studio.migration.model.report.impl.MigrationReportPackageImpl#getChange()
		 * @generated
		 */
		EClass CHANGE = eINSTANCE.getChange();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__DESCRIPTION = eINSTANCE.getChange_Description();

		/**
		 * The meta object literal for the '<em><b>Reviewed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__REVIEWED = eINSTANCE.getChange_Reviewed();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__STATUS = eINSTANCE.getChange_Status();

		/**
		 * The meta object literal for the '<em><b>Property Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__PROPERTY_NAME = eINSTANCE.getChange_PropertyName();

		/**
		 * The meta object literal for the '<em><b>Transformation Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__TRANSFORMATION_KIND = eINSTANCE.getChange_TransformationKind();

		/**
		 * The meta object literal for the '<em><b>Element UUID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__ELEMENT_UUID = eINSTANCE.getChange_ElementUUID();

		/**
		 * The meta object literal for the '<em><b>Element Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__ELEMENT_NAME = eINSTANCE.getChange_ElementName();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__ELEMENT_TYPE = eINSTANCE.getChange_ElementType();

		/**
		 * The meta object literal for the '{@link org.bonitasoft.studio.migration.model.report.impl.ReportImpl <em>Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.migration.model.report.impl.ReportImpl
		 * @see org.bonitasoft.studio.migration.model.report.impl.MigrationReportPackageImpl#getReport()
		 * @generated
		 */
		EClass REPORT = eINSTANCE.getReport();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT__NAME = eINSTANCE.getReport_Name();

		/**
		 * The meta object literal for the '<em><b>Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPORT__CHANGES = eINSTANCE.getReport_Changes();

		/**
		 * The meta object literal for the '<em><b>Completion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT__COMPLETION = eINSTANCE.getReport_Completion();

		/**
		 * The meta object literal for the '<em><b>Source Release</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT__SOURCE_RELEASE = eINSTANCE.getReport_SourceRelease();

		/**
		 * The meta object literal for the '<em><b>Target Release</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT__TARGET_RELEASE = eINSTANCE.getReport_TargetRelease();

	}

} //MigrationReportPackage
