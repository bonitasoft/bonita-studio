/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.wfmc._2002.xpdl1.Xpdl1Factory
 * @model kind="package"
 * @generated
 */
public interface Xpdl1Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "xpdl1";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.wfmc.org/2002/XPDL1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "xpdl1";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Xpdl1Package eINSTANCE = org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ActivitiesTypeImpl <em>Activities Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ActivitiesTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActivitiesType()
	 * @generated
	 */
	int ACTIVITIES_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITIES_TYPE__ACTIVITY = 0;

	/**
	 * The number of structural features of the '<em>Activities Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITIES_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ActivitySetsTypeImpl <em>Activity Sets Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ActivitySetsTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActivitySetsType()
	 * @generated
	 */
	int ACTIVITY_SETS_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Activity Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SETS_TYPE__ACTIVITY_SET = 0;

	/**
	 * The number of structural features of the '<em>Activity Sets Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SETS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ActivitySetTypeImpl <em>Activity Set Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ActivitySetTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActivitySetType()
	 * @generated
	 */
	int ACTIVITY_SET_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SET_TYPE__ACTIVITIES = 0;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SET_TYPE__TRANSITIONS = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SET_TYPE__ID = 2;

	/**
	 * The number of structural features of the '<em>Activity Set Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_SET_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl <em>Activity Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ActivityTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActivityType()
	 * @generated
	 */
	int ACTIVITY_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__LIMIT = 1;

	/**
	 * The feature id for the '<em><b>Route</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__ROUTE = 2;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__IMPLEMENTATION = 3;

	/**
	 * The feature id for the '<em><b>Block Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__BLOCK_ACTIVITY = 4;

	/**
	 * The feature id for the '<em><b>Performer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__PERFORMER = 5;

	/**
	 * The feature id for the '<em><b>Start Mode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__START_MODE = 6;

	/**
	 * The feature id for the '<em><b>Finish Mode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__FINISH_MODE = 7;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__PRIORITY = 8;

	/**
	 * The feature id for the '<em><b>Deadline</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__DEADLINE = 9;

	/**
	 * The feature id for the '<em><b>Simulation Information</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__SIMULATION_INFORMATION = 10;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__ICON = 11;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__DOCUMENTATION = 12;

	/**
	 * The feature id for the '<em><b>Transition Restrictions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__TRANSITION_RESTRICTIONS = 13;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__EXTENDED_ATTRIBUTES = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__ID = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE__NAME = 16;

	/**
	 * The number of structural features of the '<em>Activity Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_TYPE_FEATURE_COUNT = 17;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ActualParametersTypeImpl <em>Actual Parameters Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ActualParametersTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActualParametersType()
	 * @generated
	 */
	int ACTUAL_PARAMETERS_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Actual Parameter</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_PARAMETERS_TYPE__ACTUAL_PARAMETER = 0;

	/**
	 * The number of structural features of the '<em>Actual Parameters Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_PARAMETERS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ApplicationsTypeImpl <em>Applications Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ApplicationsTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getApplicationsType()
	 * @generated
	 */
	int APPLICATIONS_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Application</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONS_TYPE__APPLICATION = 0;

	/**
	 * The number of structural features of the '<em>Applications Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ApplicationTypeImpl <em>Application Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ApplicationTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getApplicationType()
	 * @generated
	 */
	int APPLICATION_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__FORMAL_PARAMETERS = 1;

	/**
	 * The feature id for the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__EXTERNAL_REFERENCE = 2;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__EXTENDED_ATTRIBUTES = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__NAME = 5;

	/**
	 * The number of structural features of the '<em>Application Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ArrayTypeTypeImpl <em>Array Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ArrayTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getArrayTypeType()
	 * @generated
	 */
	int ARRAY_TYPE_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Basic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__BASIC_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Declared Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__DECLARED_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Schema Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__SCHEMA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__EXTERNAL_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Record Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__RECORD_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Union Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__UNION_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Enumeration Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__ENUMERATION_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Array Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__ARRAY_TYPE = 7;

	/**
	 * The feature id for the '<em><b>List Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__LIST_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Lower Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__LOWER_INDEX = 9;

	/**
	 * The feature id for the '<em><b>Upper Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE__UPPER_INDEX = 10;

	/**
	 * The number of structural features of the '<em>Array Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_TYPE_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.AutomaticTypeImpl <em>Automatic Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.AutomaticTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getAutomaticType()
	 * @generated
	 */
	int AUTOMATIC_TYPE = 8;

	/**
	 * The number of structural features of the '<em>Automatic Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTOMATIC_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.BasicTypeTypeImpl <em>Basic Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.BasicTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getBasicTypeType()
	 * @generated
	 */
	int BASIC_TYPE_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_TYPE_TYPE__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Basic Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_TYPE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.BlockActivityTypeImpl <em>Block Activity Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.BlockActivityTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getBlockActivityType()
	 * @generated
	 */
	int BLOCK_ACTIVITY_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Block Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_ACTIVITY_TYPE__BLOCK_ID = 0;

	/**
	 * The number of structural features of the '<em>Block Activity Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_ACTIVITY_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ConditionTypeImpl <em>Condition Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ConditionTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getConditionType()
	 * @generated
	 */
	int CONDITION_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE__GROUP = 1;

	/**
	 * The feature id for the '<em><b>Xpression</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE__XPRESSION = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE__TYPE = 3;

	/**
	 * The number of structural features of the '<em>Condition Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ConformanceClassTypeImpl <em>Conformance Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ConformanceClassTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getConformanceClassType()
	 * @generated
	 */
	int CONFORMANCE_CLASS_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Graph Conformance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFORMANCE_CLASS_TYPE__GRAPH_CONFORMANCE = 0;

	/**
	 * The number of structural features of the '<em>Conformance Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFORMANCE_CLASS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.DataFieldsTypeImpl <em>Data Fields Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.DataFieldsTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDataFieldsType()
	 * @generated
	 */
	int DATA_FIELDS_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Data Field</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELDS_TYPE__DATA_FIELD = 0;

	/**
	 * The number of structural features of the '<em>Data Fields Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELDS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.DataFieldTypeImpl <em>Data Field Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.DataFieldTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDataFieldType()
	 * @generated
	 */
	int DATA_FIELD_TYPE = 14;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELD_TYPE__DATA_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Initial Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELD_TYPE__INITIAL_VALUE = 1;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELD_TYPE__LENGTH = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELD_TYPE__DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELD_TYPE__EXTENDED_ATTRIBUTES = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELD_TYPE__ID = 5;

	/**
	 * The feature id for the '<em><b>Is Array</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELD_TYPE__IS_ARRAY = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELD_TYPE__NAME = 7;

	/**
	 * The number of structural features of the '<em>Data Field Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FIELD_TYPE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.DataTypeTypeImpl <em>Data Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.DataTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDataTypeType()
	 * @generated
	 */
	int DATA_TYPE_TYPE = 15;

	/**
	 * The feature id for the '<em><b>Basic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__BASIC_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Declared Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__DECLARED_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Schema Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__SCHEMA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__EXTERNAL_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Record Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__RECORD_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Union Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__UNION_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Enumeration Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__ENUMERATION_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Array Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__ARRAY_TYPE = 7;

	/**
	 * The feature id for the '<em><b>List Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE__LIST_TYPE = 8;

	/**
	 * The number of structural features of the '<em>Data Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.DeadlineTypeImpl <em>Deadline Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.DeadlineTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDeadlineType()
	 * @generated
	 */
	int DEADLINE_TYPE = 16;

	/**
	 * The feature id for the '<em><b>Deadline Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEADLINE_TYPE__DEADLINE_CONDITION = 0;

	/**
	 * The feature id for the '<em><b>Exception Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEADLINE_TYPE__EXCEPTION_NAME = 1;

	/**
	 * The feature id for the '<em><b>Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEADLINE_TYPE__EXECUTION = 2;

	/**
	 * The number of structural features of the '<em>Deadline Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEADLINE_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.DeclaredTypeTypeImpl <em>Declared Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.DeclaredTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDeclaredTypeType()
	 * @generated
	 */
	int DECLARED_TYPE_TYPE = 17;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARED_TYPE_TYPE__ID = 0;

	/**
	 * The number of structural features of the '<em>Declared Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARED_TYPE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.DocumentRootImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 18;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTIVITIES = 3;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTIVITY = 4;

	/**
	 * The feature id for the '<em><b>Activity Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTIVITY_SET = 5;

	/**
	 * The feature id for the '<em><b>Activity Sets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTIVITY_SETS = 6;

	/**
	 * The feature id for the '<em><b>Actual Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTUAL_PARAMETER = 7;

	/**
	 * The feature id for the '<em><b>Actual Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTUAL_PARAMETERS = 8;

	/**
	 * The feature id for the '<em><b>Application</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__APPLICATION = 9;

	/**
	 * The feature id for the '<em><b>Applications</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__APPLICATIONS = 10;

	/**
	 * The feature id for the '<em><b>Array Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ARRAY_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__AUTHOR = 12;

	/**
	 * The feature id for the '<em><b>Automatic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__AUTOMATIC = 13;

	/**
	 * The feature id for the '<em><b>Basic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BASIC_TYPE = 14;

	/**
	 * The feature id for the '<em><b>Block Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BLOCK_ACTIVITY = 15;

	/**
	 * The feature id for the '<em><b>Codepage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CODEPAGE = 16;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONDITION = 17;

	/**
	 * The feature id for the '<em><b>Conformance Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONFORMANCE_CLASS = 18;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COST = 19;

	/**
	 * The feature id for the '<em><b>Cost Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COST_UNIT = 20;

	/**
	 * The feature id for the '<em><b>Countrykey</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COUNTRYKEY = 21;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CREATED = 22;

	/**
	 * The feature id for the '<em><b>Data Field</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_FIELD = 23;

	/**
	 * The feature id for the '<em><b>Data Fields</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_FIELDS = 24;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_TYPE = 25;

	/**
	 * The feature id for the '<em><b>Deadline</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DEADLINE = 26;

	/**
	 * The feature id for the '<em><b>Declared Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DECLARED_TYPE = 27;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DESCRIPTION = 28;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DOCUMENTATION = 29;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DURATION = 30;

	/**
	 * The feature id for the '<em><b>Enumeration Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ENUMERATION_TYPE = 31;

	/**
	 * The feature id for the '<em><b>Enumeration Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ENUMERATION_VALUE = 32;

	/**
	 * The feature id for the '<em><b>Extended Attribute</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXTENDED_ATTRIBUTE = 33;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXTENDED_ATTRIBUTES = 34;

	/**
	 * The feature id for the '<em><b>External Package</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXTERNAL_PACKAGE = 35;

	/**
	 * The feature id for the '<em><b>External Packages</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXTERNAL_PACKAGES = 36;

	/**
	 * The feature id for the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXTERNAL_REFERENCE = 37;

	/**
	 * The feature id for the '<em><b>Finish Mode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FINISH_MODE = 38;

	/**
	 * The feature id for the '<em><b>Formal Parameter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FORMAL_PARAMETER = 39;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FORMAL_PARAMETERS = 40;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ICON = 41;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IMPLEMENTATION = 42;

	/**
	 * The feature id for the '<em><b>Initial Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INITIAL_VALUE = 43;

	/**
	 * The feature id for the '<em><b>Join</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__JOIN = 44;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LENGTH = 45;

	/**
	 * The feature id for the '<em><b>Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LIMIT = 46;

	/**
	 * The feature id for the '<em><b>List Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LIST_TYPE = 47;

	/**
	 * The feature id for the '<em><b>Manual</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MANUAL = 48;

	/**
	 * The feature id for the '<em><b>Member</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MEMBER = 49;

	/**
	 * The feature id for the '<em><b>No</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NO = 50;

	/**
	 * The feature id for the '<em><b>Package</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PACKAGE = 51;

	/**
	 * The feature id for the '<em><b>Package Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PACKAGE_HEADER = 52;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARTICIPANT = 53;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARTICIPANTS = 54;

	/**
	 * The feature id for the '<em><b>Participant Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARTICIPANT_TYPE = 55;

	/**
	 * The feature id for the '<em><b>Performer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PERFORMER = 56;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PRIORITY = 57;

	/**
	 * The feature id for the '<em><b>Priority Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PRIORITY_UNIT = 58;

	/**
	 * The feature id for the '<em><b>Process Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROCESS_HEADER = 59;

	/**
	 * The feature id for the '<em><b>Record Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RECORD_TYPE = 60;

	/**
	 * The feature id for the '<em><b>Redefinable Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REDEFINABLE_HEADER = 61;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESPONSIBLE = 62;

	/**
	 * The feature id for the '<em><b>Responsibles</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESPONSIBLES = 63;

	/**
	 * The feature id for the '<em><b>Route</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ROUTE = 64;

	/**
	 * The feature id for the '<em><b>Schema Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SCHEMA_TYPE = 65;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SCRIPT = 66;

	/**
	 * The feature id for the '<em><b>Simulation Information</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SIMULATION_INFORMATION = 67;

	/**
	 * The feature id for the '<em><b>Split</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPLIT = 68;

	/**
	 * The feature id for the '<em><b>Start Mode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__START_MODE = 69;

	/**
	 * The feature id for the '<em><b>Sub Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUB_FLOW = 70;

	/**
	 * The feature id for the '<em><b>Time Estimation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TIME_ESTIMATION = 71;

	/**
	 * The feature id for the '<em><b>Tool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TOOL = 72;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRANSITION = 73;

	/**
	 * The feature id for the '<em><b>Transition Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRANSITION_REF = 74;

	/**
	 * The feature id for the '<em><b>Transition Refs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRANSITION_REFS = 75;

	/**
	 * The feature id for the '<em><b>Transition Restriction</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRANSITION_RESTRICTION = 76;

	/**
	 * The feature id for the '<em><b>Transition Restrictions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRANSITION_RESTRICTIONS = 77;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRANSITIONS = 78;

	/**
	 * The feature id for the '<em><b>Type Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TYPE_DECLARATION = 79;

	/**
	 * The feature id for the '<em><b>Type Declarations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TYPE_DECLARATIONS = 80;

	/**
	 * The feature id for the '<em><b>Union Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__UNION_TYPE = 81;

	/**
	 * The feature id for the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VALID_FROM = 82;

	/**
	 * The feature id for the '<em><b>Valid To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VALID_TO = 83;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VENDOR = 84;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VERSION = 85;

	/**
	 * The feature id for the '<em><b>Waiting Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WAITING_TIME = 86;

	/**
	 * The feature id for the '<em><b>Workflow Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WORKFLOW_PROCESS = 87;

	/**
	 * The feature id for the '<em><b>Workflow Processes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WORKFLOW_PROCESSES = 88;

	/**
	 * The feature id for the '<em><b>Working Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WORKING_TIME = 89;

	/**
	 * The feature id for the '<em><b>XPDL Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XPDL_VERSION = 90;

	/**
	 * The feature id for the '<em><b>Xpression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XPRESSION = 91;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 92;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.EnumerationTypeTypeImpl <em>Enumeration Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.EnumerationTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getEnumerationTypeType()
	 * @generated
	 */
	int ENUMERATION_TYPE_TYPE = 19;

	/**
	 * The feature id for the '<em><b>Enumeration Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE_TYPE__ENUMERATION_VALUE = 0;

	/**
	 * The number of structural features of the '<em>Enumeration Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.EnumerationValueTypeImpl <em>Enumeration Value Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.EnumerationValueTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getEnumerationValueType()
	 * @generated
	 */
	int ENUMERATION_VALUE_TYPE = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_VALUE_TYPE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Enumeration Value Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_VALUE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributesTypeImpl <em>Extended Attributes Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ExtendedAttributesTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExtendedAttributesType()
	 * @generated
	 */
	int EXTENDED_ATTRIBUTES_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Extended Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE = 0;

	/**
	 * The number of structural features of the '<em>Extended Attributes Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTES_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributeTypeImpl <em>Extended Attribute Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ExtendedAttributeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExtendedAttributeType()
	 * @generated
	 */
	int EXTENDED_ATTRIBUTE_TYPE = 22;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE_TYPE__GROUP = 1;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE_TYPE__ANY = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE_TYPE__NAME = 3;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE_TYPE__VALUE = 4;

	/**
	 * The number of structural features of the '<em>Extended Attribute Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ExternalPackagesTypeImpl <em>External Packages Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ExternalPackagesTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExternalPackagesType()
	 * @generated
	 */
	int EXTERNAL_PACKAGES_TYPE = 23;

	/**
	 * The feature id for the '<em><b>External Package</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PACKAGES_TYPE__EXTERNAL_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>External Packages Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PACKAGES_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ExternalPackageTypeImpl <em>External Package Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ExternalPackageTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExternalPackageType()
	 * @generated
	 */
	int EXTERNAL_PACKAGE_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PACKAGE_TYPE__EXTENDED_ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PACKAGE_TYPE__HREF = 1;

	/**
	 * The number of structural features of the '<em>External Package Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PACKAGE_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ExternalReferenceTypeImpl <em>External Reference Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ExternalReferenceTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExternalReferenceType()
	 * @generated
	 */
	int EXTERNAL_REFERENCE_TYPE = 25;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_REFERENCE_TYPE__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_REFERENCE_TYPE__NAMESPACE = 1;

	/**
	 * The feature id for the '<em><b>Xref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_REFERENCE_TYPE__XREF = 2;

	/**
	 * The number of structural features of the '<em>External Reference Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_REFERENCE_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.FinishModeTypeImpl <em>Finish Mode Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.FinishModeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getFinishModeType()
	 * @generated
	 */
	int FINISH_MODE_TYPE = 26;

	/**
	 * The feature id for the '<em><b>Automatic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINISH_MODE_TYPE__AUTOMATIC = 0;

	/**
	 * The feature id for the '<em><b>Manual</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINISH_MODE_TYPE__MANUAL = 1;

	/**
	 * The number of structural features of the '<em>Finish Mode Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINISH_MODE_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.FormalParametersTypeImpl <em>Formal Parameters Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.FormalParametersTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getFormalParametersType()
	 * @generated
	 */
	int FORMAL_PARAMETERS_TYPE = 27;

	/**
	 * The feature id for the '<em><b>Formal Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER = 0;

	/**
	 * The number of structural features of the '<em>Formal Parameters Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETERS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.FormalParameterTypeImpl <em>Formal Parameter Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.FormalParameterTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getFormalParameterType()
	 * @generated
	 */
	int FORMAL_PARAMETER_TYPE = 28;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER_TYPE__DATA_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER_TYPE__ID = 2;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER_TYPE__INDEX = 3;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER_TYPE__MODE = 4;

	/**
	 * The number of structural features of the '<em>Formal Parameter Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ImplementationTypeImpl <em>Implementation Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ImplementationTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getImplementationType()
	 * @generated
	 */
	int IMPLEMENTATION_TYPE = 29;

	/**
	 * The feature id for the '<em><b>No</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_TYPE__NO = 0;

	/**
	 * The feature id for the '<em><b>Tool</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_TYPE__TOOL = 1;

	/**
	 * The feature id for the '<em><b>Sub Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_TYPE__SUB_FLOW = 2;

	/**
	 * The number of structural features of the '<em>Implementation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.JoinTypeImpl <em>Join Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.JoinTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getJoinType()
	 * @generated
	 */
	int JOIN_TYPE = 30;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Join Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ListTypeTypeImpl <em>List Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ListTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getListTypeType()
	 * @generated
	 */
	int LIST_TYPE_TYPE = 31;

	/**
	 * The feature id for the '<em><b>Basic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE__BASIC_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Declared Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE__DECLARED_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Schema Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE__SCHEMA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE__EXTERNAL_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Record Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE__RECORD_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Union Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE__UNION_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Enumeration Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE__ENUMERATION_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Array Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE__ARRAY_TYPE = 7;

	/**
	 * The feature id for the '<em><b>List Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE__LIST_TYPE = 8;

	/**
	 * The number of structural features of the '<em>List Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_TYPE_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ManualTypeImpl <em>Manual Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ManualTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getManualType()
	 * @generated
	 */
	int MANUAL_TYPE = 32;

	/**
	 * The number of structural features of the '<em>Manual Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl <em>Member Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.MemberTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getMemberType()
	 * @generated
	 */
	int MEMBER_TYPE = 33;

	/**
	 * The feature id for the '<em><b>Basic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE__BASIC_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Declared Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE__DECLARED_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Schema Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE__SCHEMA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE__EXTERNAL_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Record Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE__RECORD_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Union Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE__UNION_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Enumeration Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE__ENUMERATION_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Array Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE__ARRAY_TYPE = 7;

	/**
	 * The feature id for the '<em><b>List Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE__LIST_TYPE = 8;

	/**
	 * The number of structural features of the '<em>Member Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.NoTypeImpl <em>No Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.NoTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getNoType()
	 * @generated
	 */
	int NO_TYPE = 34;

	/**
	 * The number of structural features of the '<em>No Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl <em>Package Header Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getPackageHeaderType()
	 * @generated
	 */
	int PACKAGE_HEADER_TYPE = 35;

	/**
	 * The feature id for the '<em><b>XPDL Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_HEADER_TYPE__XPDL_VERSION = 0;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_HEADER_TYPE__VENDOR = 1;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_HEADER_TYPE__CREATED = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_HEADER_TYPE__DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_HEADER_TYPE__DOCUMENTATION = 4;

	/**
	 * The feature id for the '<em><b>Priority Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_HEADER_TYPE__PRIORITY_UNIT = 5;

	/**
	 * The feature id for the '<em><b>Cost Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_HEADER_TYPE__COST_UNIT = 6;

	/**
	 * The number of structural features of the '<em>Package Header Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_HEADER_TYPE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl <em>Package Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.PackageTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getPackageType()
	 * @generated
	 */
	int PACKAGE_TYPE = 36;

	/**
	 * The feature id for the '<em><b>Package Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__PACKAGE_HEADER = 0;

	/**
	 * The feature id for the '<em><b>Redefinable Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__REDEFINABLE_HEADER = 1;

	/**
	 * The feature id for the '<em><b>Conformance Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__CONFORMANCE_CLASS = 2;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__SCRIPT = 3;

	/**
	 * The feature id for the '<em><b>External Packages</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__EXTERNAL_PACKAGES = 4;

	/**
	 * The feature id for the '<em><b>Type Declarations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__TYPE_DECLARATIONS = 5;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__PARTICIPANTS = 6;

	/**
	 * The feature id for the '<em><b>Applications</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__APPLICATIONS = 7;

	/**
	 * The feature id for the '<em><b>Data Fields</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__DATA_FIELDS = 8;

	/**
	 * The feature id for the '<em><b>Workflow Processes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__WORKFLOW_PROCESSES = 9;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__EXTENDED_ATTRIBUTES = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__ID = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE__NAME = 12;

	/**
	 * The number of structural features of the '<em>Package Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TYPE_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ParticipantsTypeImpl <em>Participants Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ParticipantsTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getParticipantsType()
	 * @generated
	 */
	int PARTICIPANTS_TYPE = 37;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANTS_TYPE__PARTICIPANT = 0;

	/**
	 * The number of structural features of the '<em>Participants Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANTS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ParticipantTypeImpl <em>Participant Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ParticipantTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getParticipantType()
	 * @generated
	 */
	int PARTICIPANT_TYPE = 38;

	/**
	 * The feature id for the '<em><b>Participant Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE__PARTICIPANT_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE__EXTERNAL_REFERENCE = 2;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE__EXTENDED_ATTRIBUTES = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE__NAME = 5;

	/**
	 * The number of structural features of the '<em>Participant Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ParticipantTypeTypeImpl <em>Participant Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ParticipantTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getParticipantTypeType()
	 * @generated
	 */
	int PARTICIPANT_TYPE_TYPE = 39;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE_TYPE__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Participant Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_TYPE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl <em>Process Header Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getProcessHeaderType()
	 * @generated
	 */
	int PROCESS_HEADER_TYPE = 40;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_HEADER_TYPE__CREATED = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_HEADER_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_HEADER_TYPE__PRIORITY = 2;

	/**
	 * The feature id for the '<em><b>Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_HEADER_TYPE__LIMIT = 3;

	/**
	 * The feature id for the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_HEADER_TYPE__VALID_FROM = 4;

	/**
	 * The feature id for the '<em><b>Valid To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_HEADER_TYPE__VALID_TO = 5;

	/**
	 * The feature id for the '<em><b>Time Estimation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_HEADER_TYPE__TIME_ESTIMATION = 6;

	/**
	 * The feature id for the '<em><b>Duration Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_HEADER_TYPE__DURATION_UNIT = 7;

	/**
	 * The number of structural features of the '<em>Process Header Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_HEADER_TYPE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.RecordTypeTypeImpl <em>Record Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.RecordTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getRecordTypeType()
	 * @generated
	 */
	int RECORD_TYPE_TYPE = 41;

	/**
	 * The feature id for the '<em><b>Member</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECORD_TYPE_TYPE__MEMBER = 0;

	/**
	 * The number of structural features of the '<em>Record Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECORD_TYPE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl <em>Redefinable Header Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getRedefinableHeaderType()
	 * @generated
	 */
	int REDEFINABLE_HEADER_TYPE = 42;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDEFINABLE_HEADER_TYPE__AUTHOR = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDEFINABLE_HEADER_TYPE__VERSION = 1;

	/**
	 * The feature id for the '<em><b>Codepage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDEFINABLE_HEADER_TYPE__CODEPAGE = 2;

	/**
	 * The feature id for the '<em><b>Countrykey</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDEFINABLE_HEADER_TYPE__COUNTRYKEY = 3;

	/**
	 * The feature id for the '<em><b>Responsibles</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDEFINABLE_HEADER_TYPE__RESPONSIBLES = 4;

	/**
	 * The feature id for the '<em><b>Publication Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDEFINABLE_HEADER_TYPE__PUBLICATION_STATUS = 5;

	/**
	 * The number of structural features of the '<em>Redefinable Header Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDEFINABLE_HEADER_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ResponsiblesTypeImpl <em>Responsibles Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ResponsiblesTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getResponsiblesType()
	 * @generated
	 */
	int RESPONSIBLES_TYPE = 43;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLES_TYPE__RESPONSIBLE = 0;

	/**
	 * The number of structural features of the '<em>Responsibles Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLES_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.RouteTypeImpl <em>Route Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.RouteTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getRouteType()
	 * @generated
	 */
	int ROUTE_TYPE = 44;

	/**
	 * The number of structural features of the '<em>Route Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.SchemaTypeTypeImpl <em>Schema Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.SchemaTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getSchemaTypeType()
	 * @generated
	 */
	int SCHEMA_TYPE_TYPE = 45;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA_TYPE_TYPE__ANY = 0;

	/**
	 * The number of structural features of the '<em>Schema Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA_TYPE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ScriptTypeImpl <em>Script Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ScriptTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getScriptType()
	 * @generated
	 */
	int SCRIPT_TYPE = 46;

	/**
	 * The feature id for the '<em><b>Grammar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TYPE__GRAMMAR = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TYPE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TYPE__VERSION = 2;

	/**
	 * The number of structural features of the '<em>Script Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.SimulationInformationTypeImpl <em>Simulation Information Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.SimulationInformationTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getSimulationInformationType()
	 * @generated
	 */
	int SIMULATION_INFORMATION_TYPE = 47;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_INFORMATION_TYPE__COST = 0;

	/**
	 * The feature id for the '<em><b>Time Estimation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION = 1;

	/**
	 * The feature id for the '<em><b>Instantiation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_INFORMATION_TYPE__INSTANTIATION = 2;

	/**
	 * The number of structural features of the '<em>Simulation Information Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_INFORMATION_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.SplitTypeImpl <em>Split Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.SplitTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getSplitType()
	 * @generated
	 */
	int SPLIT_TYPE = 48;

	/**
	 * The feature id for the '<em><b>Transition Refs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__TRANSITION_REFS = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Split Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.StartModeTypeImpl <em>Start Mode Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.StartModeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getStartModeType()
	 * @generated
	 */
	int START_MODE_TYPE = 49;

	/**
	 * The feature id for the '<em><b>Automatic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_MODE_TYPE__AUTOMATIC = 0;

	/**
	 * The feature id for the '<em><b>Manual</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_MODE_TYPE__MANUAL = 1;

	/**
	 * The number of structural features of the '<em>Start Mode Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_MODE_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.SubFlowTypeImpl <em>Sub Flow Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.SubFlowTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getSubFlowType()
	 * @generated
	 */
	int SUB_FLOW_TYPE = 50;

	/**
	 * The feature id for the '<em><b>Actual Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_FLOW_TYPE__ACTUAL_PARAMETERS = 0;

	/**
	 * The feature id for the '<em><b>Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_FLOW_TYPE__EXECUTION = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_FLOW_TYPE__ID = 2;

	/**
	 * The number of structural features of the '<em>Sub Flow Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_FLOW_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.TimeEstimationTypeImpl <em>Time Estimation Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.TimeEstimationTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTimeEstimationType()
	 * @generated
	 */
	int TIME_ESTIMATION_TYPE = 51;

	/**
	 * The feature id for the '<em><b>Waiting Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_ESTIMATION_TYPE__WAITING_TIME = 0;

	/**
	 * The feature id for the '<em><b>Working Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_ESTIMATION_TYPE__WORKING_TIME = 1;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_ESTIMATION_TYPE__DURATION = 2;

	/**
	 * The number of structural features of the '<em>Time Estimation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_ESTIMATION_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.ToolTypeImpl <em>Tool Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.ToolTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getToolType()
	 * @generated
	 */
	int TOOL_TYPE = 52;

	/**
	 * The feature id for the '<em><b>Actual Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_TYPE__ACTUAL_PARAMETERS = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_TYPE__EXTENDED_ATTRIBUTES = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_TYPE__ID = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_TYPE__TYPE = 4;

	/**
	 * The number of structural features of the '<em>Tool Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.TransitionRefsTypeImpl <em>Transition Refs Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.TransitionRefsTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionRefsType()
	 * @generated
	 */
	int TRANSITION_REFS_TYPE = 53;

	/**
	 * The feature id for the '<em><b>Transition Ref</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_REFS_TYPE__TRANSITION_REF = 0;

	/**
	 * The number of structural features of the '<em>Transition Refs Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_REFS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.TransitionRefTypeImpl <em>Transition Ref Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.TransitionRefTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionRefType()
	 * @generated
	 */
	int TRANSITION_REF_TYPE = 54;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_REF_TYPE__ID = 0;

	/**
	 * The number of structural features of the '<em>Transition Ref Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_REF_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.TransitionRestrictionsTypeImpl <em>Transition Restrictions Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.TransitionRestrictionsTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionRestrictionsType()
	 * @generated
	 */
	int TRANSITION_RESTRICTIONS_TYPE = 55;

	/**
	 * The feature id for the '<em><b>Transition Restriction</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_RESTRICTIONS_TYPE__TRANSITION_RESTRICTION = 0;

	/**
	 * The number of structural features of the '<em>Transition Restrictions Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_RESTRICTIONS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.TransitionRestrictionTypeImpl <em>Transition Restriction Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.TransitionRestrictionTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionRestrictionType()
	 * @generated
	 */
	int TRANSITION_RESTRICTION_TYPE = 56;

	/**
	 * The feature id for the '<em><b>Join</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_RESTRICTION_TYPE__JOIN = 0;

	/**
	 * The feature id for the '<em><b>Split</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_RESTRICTION_TYPE__SPLIT = 1;

	/**
	 * The number of structural features of the '<em>Transition Restriction Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_RESTRICTION_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.TransitionsTypeImpl <em>Transitions Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.TransitionsTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionsType()
	 * @generated
	 */
	int TRANSITIONS_TYPE = 57;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIONS_TYPE__TRANSITION = 0;

	/**
	 * The number of structural features of the '<em>Transitions Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIONS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.TransitionTypeImpl <em>Transition Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.TransitionTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionType()
	 * @generated
	 */
	int TRANSITION_TYPE = 58;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__CONDITION = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__EXTENDED_ATTRIBUTES = 2;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__FROM = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__TO = 6;

	/**
	 * The number of structural features of the '<em>Transition Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.TypeDeclarationsTypeImpl <em>Type Declarations Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.TypeDeclarationsTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeDeclarationsType()
	 * @generated
	 */
	int TYPE_DECLARATIONS_TYPE = 59;

	/**
	 * The feature id for the '<em><b>Type Declaration</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION = 0;

	/**
	 * The number of structural features of the '<em>Type Declarations Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATIONS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.TypeDeclarationTypeImpl <em>Type Declaration Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.TypeDeclarationTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeDeclarationType()
	 * @generated
	 */
	int TYPE_DECLARATION_TYPE = 60;

	/**
	 * The feature id for the '<em><b>Basic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__BASIC_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Declared Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__DECLARED_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Schema Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__SCHEMA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Record Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__RECORD_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Union Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__UNION_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Enumeration Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__ENUMERATION_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Array Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__ARRAY_TYPE = 7;

	/**
	 * The feature id for the '<em><b>List Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__LIST_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__DESCRIPTION = 9;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__ID = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE__NAME = 12;

	/**
	 * The number of structural features of the '<em>Type Declaration Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_TYPE_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.UnionTypeTypeImpl <em>Union Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.UnionTypeTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getUnionTypeType()
	 * @generated
	 */
	int UNION_TYPE_TYPE = 61;

	/**
	 * The feature id for the '<em><b>Member</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE_TYPE__MEMBER = 0;

	/**
	 * The number of structural features of the '<em>Union Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessesTypeImpl <em>Workflow Processes Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.WorkflowProcessesTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getWorkflowProcessesType()
	 * @generated
	 */
	int WORKFLOW_PROCESSES_TYPE = 62;

	/**
	 * The feature id for the '<em><b>Workflow Process</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESSES_TYPE__WORKFLOW_PROCESS = 0;

	/**
	 * The number of structural features of the '<em>Workflow Processes Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESSES_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl <em>Workflow Process Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getWorkflowProcessType()
	 * @generated
	 */
	int WORKFLOW_PROCESS_TYPE = 63;

	/**
	 * The feature id for the '<em><b>Process Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__PROCESS_HEADER = 0;

	/**
	 * The feature id for the '<em><b>Redefinable Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER = 1;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS = 2;

	/**
	 * The feature id for the '<em><b>Data Fields</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__DATA_FIELDS = 3;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__PARTICIPANTS = 4;

	/**
	 * The feature id for the '<em><b>Applications</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__APPLICATIONS = 5;

	/**
	 * The feature id for the '<em><b>Activity Sets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS = 6;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__ACTIVITIES = 7;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__TRANSITIONS = 8;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES = 9;

	/**
	 * The feature id for the '<em><b>Access Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__ACCESS_LEVEL = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__ID = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE__NAME = 12;

	/**
	 * The number of structural features of the '<em>Workflow Process Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_PROCESS_TYPE_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.impl.XpressionTypeImpl <em>Xpression Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.impl.XpressionTypeImpl
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getXpressionType()
	 * @generated
	 */
	int XPRESSION_TYPE = 64;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XPRESSION_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XPRESSION_TYPE__GROUP = 1;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XPRESSION_TYPE__ANY = 2;

	/**
	 * The number of structural features of the '<em>Xpression Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XPRESSION_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.AccessLevelType <em>Access Level Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.AccessLevelType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getAccessLevelType()
	 * @generated
	 */
	int ACCESS_LEVEL_TYPE = 65;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.DurationUnitType <em>Duration Unit Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.DurationUnitType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDurationUnitType()
	 * @generated
	 */
	int DURATION_UNIT_TYPE = 66;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.ExecutionType <em>Execution Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.ExecutionType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExecutionType()
	 * @generated
	 */
	int EXECUTION_TYPE = 67;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.ExecutionType1 <em>Execution Type1</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.ExecutionType1
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExecutionType1()
	 * @generated
	 */
	int EXECUTION_TYPE1 = 68;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.GraphConformanceType <em>Graph Conformance Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.GraphConformanceType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getGraphConformanceType()
	 * @generated
	 */
	int GRAPH_CONFORMANCE_TYPE = 69;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.InstantiationType <em>Instantiation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.InstantiationType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getInstantiationType()
	 * @generated
	 */
	int INSTANTIATION_TYPE = 70;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.IsArrayType <em>Is Array Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.IsArrayType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getIsArrayType()
	 * @generated
	 */
	int IS_ARRAY_TYPE = 71;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.ModeType <em>Mode Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.ModeType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getModeType()
	 * @generated
	 */
	int MODE_TYPE = 72;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.PublicationStatusType <em>Publication Status Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.PublicationStatusType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getPublicationStatusType()
	 * @generated
	 */
	int PUBLICATION_STATUS_TYPE = 73;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.TypeType <em>Type Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType()
	 * @generated
	 */
	int TYPE_TYPE = 74;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.TypeType1 <em>Type Type1</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType1
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType1()
	 * @generated
	 */
	int TYPE_TYPE1 = 75;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.TypeType2 <em>Type Type2</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType2
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType2()
	 * @generated
	 */
	int TYPE_TYPE2 = 76;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.TypeType3 <em>Type Type3</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType3
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType3()
	 * @generated
	 */
	int TYPE_TYPE3 = 77;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.TypeType4 <em>Type Type4</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType4
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType4()
	 * @generated
	 */
	int TYPE_TYPE4 = 78;

	/**
	 * The meta object id for the '{@link org.wfmc._2002.xpdl1.TypeType5 <em>Type Type5</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType5
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType5()
	 * @generated
	 */
	int TYPE_TYPE5 = 79;

	/**
	 * The meta object id for the '<em>Access Level Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.AccessLevelType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getAccessLevelTypeObject()
	 * @generated
	 */
	int ACCESS_LEVEL_TYPE_OBJECT = 80;

	/**
	 * The meta object id for the '<em>Duration Unit Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.DurationUnitType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDurationUnitTypeObject()
	 * @generated
	 */
	int DURATION_UNIT_TYPE_OBJECT = 81;

	/**
	 * The meta object id for the '<em>Execution Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.ExecutionType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExecutionTypeObject()
	 * @generated
	 */
	int EXECUTION_TYPE_OBJECT = 82;

	/**
	 * The meta object id for the '<em>Execution Type Object1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.ExecutionType1
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExecutionTypeObject1()
	 * @generated
	 */
	int EXECUTION_TYPE_OBJECT1 = 83;

	/**
	 * The meta object id for the '<em>Graph Conformance Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.GraphConformanceType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getGraphConformanceTypeObject()
	 * @generated
	 */
	int GRAPH_CONFORMANCE_TYPE_OBJECT = 84;

	/**
	 * The meta object id for the '<em>Instantiation Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.InstantiationType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getInstantiationTypeObject()
	 * @generated
	 */
	int INSTANTIATION_TYPE_OBJECT = 85;

	/**
	 * The meta object id for the '<em>Is Array Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.IsArrayType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getIsArrayTypeObject()
	 * @generated
	 */
	int IS_ARRAY_TYPE_OBJECT = 86;

	/**
	 * The meta object id for the '<em>Mode Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.ModeType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getModeTypeObject()
	 * @generated
	 */
	int MODE_TYPE_OBJECT = 87;

	/**
	 * The meta object id for the '<em>Publication Status Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.PublicationStatusType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getPublicationStatusTypeObject()
	 * @generated
	 */
	int PUBLICATION_STATUS_TYPE_OBJECT = 88;

	/**
	 * The meta object id for the '<em>Type Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType4
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject()
	 * @generated
	 */
	int TYPE_TYPE_OBJECT = 89;

	/**
	 * The meta object id for the '<em>Type Type Object1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType1
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject1()
	 * @generated
	 */
	int TYPE_TYPE_OBJECT1 = 90;

	/**
	 * The meta object id for the '<em>Type Type Object2</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType3
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject2()
	 * @generated
	 */
	int TYPE_TYPE_OBJECT2 = 91;

	/**
	 * The meta object id for the '<em>Type Type Object3</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType2
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject3()
	 * @generated
	 */
	int TYPE_TYPE_OBJECT3 = 92;

	/**
	 * The meta object id for the '<em>Type Type Object4</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject4()
	 * @generated
	 */
	int TYPE_TYPE_OBJECT4 = 93;

	/**
	 * The meta object id for the '<em>Type Type Object5</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.wfmc._2002.xpdl1.TypeType5
	 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject5()
	 * @generated
	 */
	int TYPE_TYPE_OBJECT5 = 94;


	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ActivitiesType <em>Activities Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activities Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivitiesType
	 * @generated
	 */
	EClass getActivitiesType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.ActivitiesType#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activity</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivitiesType#getActivity()
	 * @see #getActivitiesType()
	 * @generated
	 */
	EReference getActivitiesType_Activity();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ActivitySetsType <em>Activity Sets Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Sets Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivitySetsType
	 * @generated
	 */
	EClass getActivitySetsType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.ActivitySetsType#getActivitySet <em>Activity Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activity Set</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivitySetsType#getActivitySet()
	 * @see #getActivitySetsType()
	 * @generated
	 */
	EReference getActivitySetsType_ActivitySet();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ActivitySetType <em>Activity Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Set Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivitySetType
	 * @generated
	 */
	EClass getActivitySetType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivitySetType#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activities</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivitySetType#getActivities()
	 * @see #getActivitySetType()
	 * @generated
	 */
	EReference getActivitySetType_Activities();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivitySetType#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transitions</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivitySetType#getTransitions()
	 * @see #getActivitySetType()
	 * @generated
	 */
	EReference getActivitySetType_Transitions();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ActivitySetType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivitySetType#getId()
	 * @see #getActivitySetType()
	 * @generated
	 */
	EAttribute getActivitySetType_Id();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ActivityType <em>Activity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType
	 * @generated
	 */
	EClass getActivityType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ActivityType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getDescription()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ActivityType#getLimit <em>Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Limit</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getLimit()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Limit();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivityType#getRoute <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Route</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getRoute()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_Route();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivityType#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Implementation</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getImplementation()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_Implementation();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivityType#getBlockActivity <em>Block Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block Activity</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getBlockActivity()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_BlockActivity();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ActivityType#getPerformer <em>Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Performer</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getPerformer()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Performer();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivityType#getStartMode <em>Start Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start Mode</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getStartMode()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_StartMode();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivityType#getFinishMode <em>Finish Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Finish Mode</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getFinishMode()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_FinishMode();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ActivityType#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getPriority()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Priority();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.ActivityType#getDeadline <em>Deadline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Deadline</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getDeadline()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_Deadline();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivityType#getSimulationInformation <em>Simulation Information</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Simulation Information</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getSimulationInformation()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_SimulationInformation();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ActivityType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getIcon()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Icon();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ActivityType#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Documentation</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getDocumentation()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Documentation();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivityType#getTransitionRestrictions <em>Transition Restrictions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transition Restrictions</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getTransitionRestrictions()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_TransitionRestrictions();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ActivityType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getExtendedAttributes()
	 * @see #getActivityType()
	 * @generated
	 */
	EReference getActivityType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ActivityType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getId()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ActivityType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.ActivityType#getName()
	 * @see #getActivityType()
	 * @generated
	 */
	EAttribute getActivityType_Name();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ActualParametersType <em>Actual Parameters Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actual Parameters Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ActualParametersType
	 * @generated
	 */
	EClass getActualParametersType();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.ActualParametersType#getActualParameter <em>Actual Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Actual Parameter</em>'.
	 * @see org.wfmc._2002.xpdl1.ActualParametersType#getActualParameter()
	 * @see #getActualParametersType()
	 * @generated
	 */
	EAttribute getActualParametersType_ActualParameter();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ApplicationsType <em>Applications Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Applications Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ApplicationsType
	 * @generated
	 */
	EClass getApplicationsType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.ApplicationsType#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Application</em>'.
	 * @see org.wfmc._2002.xpdl1.ApplicationsType#getApplication()
	 * @see #getApplicationsType()
	 * @generated
	 */
	EReference getApplicationsType_Application();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ApplicationType <em>Application Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ApplicationType
	 * @generated
	 */
	EClass getApplicationType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ApplicationType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.ApplicationType#getDescription()
	 * @see #getApplicationType()
	 * @generated
	 */
	EAttribute getApplicationType_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ApplicationType#getFormalParameters <em>Formal Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formal Parameters</em>'.
	 * @see org.wfmc._2002.xpdl1.ApplicationType#getFormalParameters()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_FormalParameters();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ApplicationType#getExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Reference</em>'.
	 * @see org.wfmc._2002.xpdl1.ApplicationType#getExternalReference()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_ExternalReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ApplicationType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.ApplicationType#getExtendedAttributes()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ApplicationType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.ApplicationType#getId()
	 * @see #getApplicationType()
	 * @generated
	 */
	EAttribute getApplicationType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ApplicationType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.ApplicationType#getName()
	 * @see #getApplicationType()
	 * @generated
	 */
	EAttribute getApplicationType_Name();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ArrayTypeType <em>Array Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType
	 * @generated
	 */
	EClass getArrayTypeType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getBasicType <em>Basic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Basic Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getBasicType()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EReference getArrayTypeType_BasicType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getDeclaredType <em>Declared Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Declared Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getDeclaredType()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EReference getArrayTypeType_DeclaredType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getSchemaType <em>Schema Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schema Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getSchemaType()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EReference getArrayTypeType_SchemaType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Reference</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getExternalReference()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EReference getArrayTypeType_ExternalReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getRecordType <em>Record Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Record Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getRecordType()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EReference getArrayTypeType_RecordType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getUnionType <em>Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Union Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getUnionType()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EReference getArrayTypeType_UnionType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getEnumerationType <em>Enumeration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Enumeration Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getEnumerationType()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EReference getArrayTypeType_EnumerationType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getArrayType()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EReference getArrayTypeType_ArrayType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getListType <em>List Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>List Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getListType()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EReference getArrayTypeType_ListType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getLowerIndex <em>Lower Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Index</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getLowerIndex()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EAttribute getArrayTypeType_LowerIndex();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ArrayTypeType#getUpperIndex <em>Upper Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Index</em>'.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType#getUpperIndex()
	 * @see #getArrayTypeType()
	 * @generated
	 */
	EAttribute getArrayTypeType_UpperIndex();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.AutomaticType <em>Automatic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Automatic Type</em>'.
	 * @see org.wfmc._2002.xpdl1.AutomaticType
	 * @generated
	 */
	EClass getAutomaticType();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.BasicTypeType <em>Basic Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.BasicTypeType
	 * @generated
	 */
	EClass getBasicTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.BasicTypeType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.wfmc._2002.xpdl1.BasicTypeType#getType()
	 * @see #getBasicTypeType()
	 * @generated
	 */
	EAttribute getBasicTypeType_Type();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.BlockActivityType <em>Block Activity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block Activity Type</em>'.
	 * @see org.wfmc._2002.xpdl1.BlockActivityType
	 * @generated
	 */
	EClass getBlockActivityType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.BlockActivityType#getBlockId <em>Block Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Block Id</em>'.
	 * @see org.wfmc._2002.xpdl1.BlockActivityType#getBlockId()
	 * @see #getBlockActivityType()
	 * @generated
	 */
	EAttribute getBlockActivityType_BlockId();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ConditionType <em>Condition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ConditionType
	 * @generated
	 */
	EClass getConditionType();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.ConditionType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.wfmc._2002.xpdl1.ConditionType#getMixed()
	 * @see #getConditionType()
	 * @generated
	 */
	EAttribute getConditionType_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.ConditionType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.wfmc._2002.xpdl1.ConditionType#getGroup()
	 * @see #getConditionType()
	 * @generated
	 */
	EAttribute getConditionType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.ConditionType#getXpression <em>Xpression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Xpression</em>'.
	 * @see org.wfmc._2002.xpdl1.ConditionType#getXpression()
	 * @see #getConditionType()
	 * @generated
	 */
	EReference getConditionType_Xpression();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ConditionType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ConditionType#getType()
	 * @see #getConditionType()
	 * @generated
	 */
	EAttribute getConditionType_Type();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ConformanceClassType <em>Conformance Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conformance Class Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ConformanceClassType
	 * @generated
	 */
	EClass getConformanceClassType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ConformanceClassType#getGraphConformance <em>Graph Conformance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Graph Conformance</em>'.
	 * @see org.wfmc._2002.xpdl1.ConformanceClassType#getGraphConformance()
	 * @see #getConformanceClassType()
	 * @generated
	 */
	EAttribute getConformanceClassType_GraphConformance();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.DataFieldsType <em>Data Fields Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Fields Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldsType
	 * @generated
	 */
	EClass getDataFieldsType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.DataFieldsType#getDataField <em>Data Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Field</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldsType#getDataField()
	 * @see #getDataFieldsType()
	 * @generated
	 */
	EReference getDataFieldsType_DataField();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.DataFieldType <em>Data Field Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Field Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldType
	 * @generated
	 */
	EClass getDataFieldType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataFieldType#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldType#getDataType()
	 * @see #getDataFieldType()
	 * @generated
	 */
	EReference getDataFieldType_DataType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DataFieldType#getInitialValue <em>Initial Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Value</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldType#getInitialValue()
	 * @see #getDataFieldType()
	 * @generated
	 */
	EAttribute getDataFieldType_InitialValue();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DataFieldType#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldType#getLength()
	 * @see #getDataFieldType()
	 * @generated
	 */
	EAttribute getDataFieldType_Length();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DataFieldType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldType#getDescription()
	 * @see #getDataFieldType()
	 * @generated
	 */
	EAttribute getDataFieldType_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataFieldType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldType#getExtendedAttributes()
	 * @see #getDataFieldType()
	 * @generated
	 */
	EReference getDataFieldType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DataFieldType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldType#getId()
	 * @see #getDataFieldType()
	 * @generated
	 */
	EAttribute getDataFieldType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DataFieldType#getIsArray <em>Is Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Array</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldType#getIsArray()
	 * @see #getDataFieldType()
	 * @generated
	 */
	EAttribute getDataFieldType_IsArray();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DataFieldType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.DataFieldType#getName()
	 * @see #getDataFieldType()
	 * @generated
	 */
	EAttribute getDataFieldType_Name();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.DataTypeType <em>Data Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType
	 * @generated
	 */
	EClass getDataTypeType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataTypeType#getBasicType <em>Basic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Basic Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType#getBasicType()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_BasicType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataTypeType#getDeclaredType <em>Declared Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Declared Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType#getDeclaredType()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_DeclaredType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataTypeType#getSchemaType <em>Schema Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schema Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType#getSchemaType()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_SchemaType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataTypeType#getExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Reference</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType#getExternalReference()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_ExternalReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataTypeType#getRecordType <em>Record Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Record Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType#getRecordType()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_RecordType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataTypeType#getUnionType <em>Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Union Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType#getUnionType()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_UnionType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataTypeType#getEnumerationType <em>Enumeration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Enumeration Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType#getEnumerationType()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_EnumerationType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataTypeType#getArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType#getArrayType()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_ArrayType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DataTypeType#getListType <em>List Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>List Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DataTypeType#getListType()
	 * @see #getDataTypeType()
	 * @generated
	 */
	EReference getDataTypeType_ListType();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.DeadlineType <em>Deadline Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deadline Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DeadlineType
	 * @generated
	 */
	EClass getDeadlineType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DeadlineType#getDeadlineCondition <em>Deadline Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Deadline Condition</em>'.
	 * @see org.wfmc._2002.xpdl1.DeadlineType#getDeadlineCondition()
	 * @see #getDeadlineType()
	 * @generated
	 */
	EReference getDeadlineType_DeadlineCondition();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DeadlineType#getExceptionName <em>Exception Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exception Name</em>'.
	 * @see org.wfmc._2002.xpdl1.DeadlineType#getExceptionName()
	 * @see #getDeadlineType()
	 * @generated
	 */
	EReference getDeadlineType_ExceptionName();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DeadlineType#getExecution <em>Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution</em>'.
	 * @see org.wfmc._2002.xpdl1.DeadlineType#getExecution()
	 * @see #getDeadlineType()
	 * @generated
	 */
	EAttribute getDeadlineType_Execution();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.DeclaredTypeType <em>Declared Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Declared Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DeclaredTypeType
	 * @generated
	 */
	EClass getDeclaredTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DeclaredTypeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.DeclaredTypeType#getId()
	 * @see #getDeclaredTypeType()
	 * @generated
	 */
	EAttribute getDeclaredTypeType_Id();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.wfmc._2002.xpdl1.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.wfmc._2002.xpdl1.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activities</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getActivities()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Activities();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activity</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getActivity()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Activity();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivitySet <em>Activity Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activity Set</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getActivitySet()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ActivitySet();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivitySets <em>Activity Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activity Sets</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getActivitySets()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ActivitySets();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActualParameter <em>Actual Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Parameter</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getActualParameter()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ActualParameter();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActualParameters <em>Actual Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Actual Parameters</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getActualParameters()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ActualParameters();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Application</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getApplication()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Application();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getApplications <em>Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Applications</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getApplications()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Applications();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getArrayType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ArrayType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getAuthor()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Author();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getAutomatic <em>Automatic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Automatic</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getAutomatic()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Automatic();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getBasicType <em>Basic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Basic Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getBasicType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BasicType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getBlockActivity <em>Block Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block Activity</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getBlockActivity()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BlockActivity();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCodepage <em>Codepage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Codepage</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getCodepage()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Codepage();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getCondition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getConformanceClass <em>Conformance Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Conformance Class</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getConformanceClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConformanceClass();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getCost()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Cost();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCostUnit <em>Cost Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost Unit</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getCostUnit()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_CostUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCountrykey <em>Countrykey</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Countrykey</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getCountrykey()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Countrykey();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCreated <em>Created</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getCreated()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Created();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDataField <em>Data Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Field</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getDataField()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataField();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDataFields <em>Data Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Fields</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getDataFields()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataFields();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getDataType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDeadline <em>Deadline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Deadline</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getDeadline()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Deadline();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDeclaredType <em>Declared Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Declared Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getDeclaredType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DeclaredType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getDescription()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Documentation</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getDocumentation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Documentation();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getDuration()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Duration();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getEnumerationType <em>Enumeration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Enumeration Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getEnumerationType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EnumerationType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getEnumerationValue <em>Enumeration Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Enumeration Value</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getEnumerationValue()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EnumerationValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attribute</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getExtendedAttribute()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExtendedAttribute();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getExtendedAttributes()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExtendedAttributes();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExternalPackage <em>External Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Package</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getExternalPackage()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExternalPackage();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExternalPackages <em>External Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Packages</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getExternalPackages()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExternalPackages();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Reference</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getExternalReference()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExternalReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getFinishMode <em>Finish Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Finish Mode</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getFinishMode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FinishMode();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getFormalParameter <em>Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formal Parameter</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getFormalParameter()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FormalParameter();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getFormalParameters <em>Formal Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formal Parameters</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getFormalParameters()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FormalParameters();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getIcon()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Icon();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Implementation</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getImplementation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Implementation();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getInitialValue <em>Initial Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Value</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getInitialValue()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_InitialValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getJoin <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Join</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getJoin()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Join();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getLength()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Length();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getLimit <em>Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Limit</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getLimit()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Limit();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getListType <em>List Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>List Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getListType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ListType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getManual <em>Manual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Manual</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getManual()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Manual();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getMember <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Member</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getMember()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Member();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getNo <em>No</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>No</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getNo()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_No();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Package</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getPackage()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Package();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPackageHeader <em>Package Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Package Header</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getPackageHeader()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PackageHeader();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participant</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getParticipant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Participant();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participants</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getParticipants()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Participants();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getParticipantType <em>Participant Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participant Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getParticipantType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ParticipantType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPerformer <em>Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Performer</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getPerformer()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Performer();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getPriority()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Priority();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPriorityUnit <em>Priority Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority Unit</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getPriorityUnit()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_PriorityUnit();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getProcessHeader <em>Process Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process Header</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getProcessHeader()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ProcessHeader();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getRecordType <em>Record Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Record Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getRecordType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RecordType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getRedefinableHeader <em>Redefinable Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Redefinable Header</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getRedefinableHeader()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RedefinableHeader();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getResponsible <em>Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Responsible</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getResponsible()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Responsible();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getResponsibles <em>Responsibles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Responsibles</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getResponsibles()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Responsibles();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getRoute <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Route</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getRoute()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Route();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getSchemaType <em>Schema Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schema Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getSchemaType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SchemaType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getScript()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Script();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getSimulationInformation <em>Simulation Information</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Simulation Information</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getSimulationInformation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SimulationInformation();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getSplit <em>Split</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Split</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getSplit()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Split();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getStartMode <em>Start Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start Mode</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getStartMode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StartMode();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getSubFlow <em>Sub Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sub Flow</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getSubFlow()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SubFlow();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTimeEstimation <em>Time Estimation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Time Estimation</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTimeEstimation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TimeEstimation();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tool</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTool()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Tool();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transition</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTransition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Transition();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRef <em>Transition Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transition Ref</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRef()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TransitionRef();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRefs <em>Transition Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transition Refs</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRefs()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TransitionRefs();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRestriction <em>Transition Restriction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transition Restriction</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRestriction()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TransitionRestriction();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRestrictions <em>Transition Restrictions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transition Restrictions</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRestrictions()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TransitionRestrictions();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transitions</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTransitions()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Transitions();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTypeDeclaration <em>Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Declaration</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTypeDeclaration()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TypeDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTypeDeclarations <em>Type Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Declarations</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getTypeDeclarations()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TypeDeclarations();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getUnionType <em>Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Union Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getUnionType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_UnionType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getValidFrom <em>Valid From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid From</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getValidFrom()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ValidFrom();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getValidTo <em>Valid To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid To</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getValidTo()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_ValidTo();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getVendor <em>Vendor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getVendor()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Vendor();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getVersion()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getWaitingTime <em>Waiting Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Waiting Time</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getWaitingTime()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_WaitingTime();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getWorkflowProcess <em>Workflow Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Workflow Process</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getWorkflowProcess()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_WorkflowProcess();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getWorkflowProcesses <em>Workflow Processes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Workflow Processes</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getWorkflowProcesses()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_WorkflowProcesses();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getWorkingTime <em>Working Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Working Time</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getWorkingTime()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_WorkingTime();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.DocumentRoot#getXPDLVersion <em>XPDL Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XPDL Version</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getXPDLVersion()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_XPDLVersion();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.DocumentRoot#getXpression <em>Xpression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Xpression</em>'.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot#getXpression()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Xpression();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.EnumerationTypeType <em>Enumeration Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.EnumerationTypeType
	 * @generated
	 */
	EClass getEnumerationTypeType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.EnumerationTypeType#getEnumerationValue <em>Enumeration Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Enumeration Value</em>'.
	 * @see org.wfmc._2002.xpdl1.EnumerationTypeType#getEnumerationValue()
	 * @see #getEnumerationTypeType()
	 * @generated
	 */
	EReference getEnumerationTypeType_EnumerationValue();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.EnumerationValueType <em>Enumeration Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration Value Type</em>'.
	 * @see org.wfmc._2002.xpdl1.EnumerationValueType
	 * @generated
	 */
	EClass getEnumerationValueType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.EnumerationValueType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.EnumerationValueType#getName()
	 * @see #getEnumerationValueType()
	 * @generated
	 */
	EAttribute getEnumerationValueType_Name();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ExtendedAttributesType <em>Extended Attributes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Attributes Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributesType
	 * @generated
	 */
	EClass getExtendedAttributesType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.ExtendedAttributesType#getExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Attribute</em>'.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributesType#getExtendedAttribute()
	 * @see #getExtendedAttributesType()
	 * @generated
	 */
	EReference getExtendedAttributesType_ExtendedAttribute();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ExtendedAttributeType <em>Extended Attribute Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Attribute Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributeType
	 * @generated
	 */
	EClass getExtendedAttributeType();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.ExtendedAttributeType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributeType#getMixed()
	 * @see #getExtendedAttributeType()
	 * @generated
	 */
	EAttribute getExtendedAttributeType_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.ExtendedAttributeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributeType#getGroup()
	 * @see #getExtendedAttributeType()
	 * @generated
	 */
	EAttribute getExtendedAttributeType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.ExtendedAttributeType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributeType#getAny()
	 * @see #getExtendedAttributeType()
	 * @generated
	 */
	EAttribute getExtendedAttributeType_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ExtendedAttributeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributeType#getName()
	 * @see #getExtendedAttributeType()
	 * @generated
	 */
	EAttribute getExtendedAttributeType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ExtendedAttributeType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributeType#getValue()
	 * @see #getExtendedAttributeType()
	 * @generated
	 */
	EAttribute getExtendedAttributeType_Value();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ExternalPackagesType <em>External Packages Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Packages Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ExternalPackagesType
	 * @generated
	 */
	EClass getExternalPackagesType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.ExternalPackagesType#getExternalPackage <em>External Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Package</em>'.
	 * @see org.wfmc._2002.xpdl1.ExternalPackagesType#getExternalPackage()
	 * @see #getExternalPackagesType()
	 * @generated
	 */
	EReference getExternalPackagesType_ExternalPackage();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ExternalPackageType <em>External Package Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Package Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ExternalPackageType
	 * @generated
	 */
	EClass getExternalPackageType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ExternalPackageType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.ExternalPackageType#getExtendedAttributes()
	 * @see #getExternalPackageType()
	 * @generated
	 */
	EReference getExternalPackageType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ExternalPackageType#getHref <em>Href</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Href</em>'.
	 * @see org.wfmc._2002.xpdl1.ExternalPackageType#getHref()
	 * @see #getExternalPackageType()
	 * @generated
	 */
	EAttribute getExternalPackageType_Href();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ExternalReferenceType <em>External Reference Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Reference Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ExternalReferenceType
	 * @generated
	 */
	EClass getExternalReferenceType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ExternalReferenceType#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.wfmc._2002.xpdl1.ExternalReferenceType#getLocation()
	 * @see #getExternalReferenceType()
	 * @generated
	 */
	EAttribute getExternalReferenceType_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ExternalReferenceType#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Namespace</em>'.
	 * @see org.wfmc._2002.xpdl1.ExternalReferenceType#getNamespace()
	 * @see #getExternalReferenceType()
	 * @generated
	 */
	EAttribute getExternalReferenceType_Namespace();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ExternalReferenceType#getXref <em>Xref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xref</em>'.
	 * @see org.wfmc._2002.xpdl1.ExternalReferenceType#getXref()
	 * @see #getExternalReferenceType()
	 * @generated
	 */
	EAttribute getExternalReferenceType_Xref();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.FinishModeType <em>Finish Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Finish Mode Type</em>'.
	 * @see org.wfmc._2002.xpdl1.FinishModeType
	 * @generated
	 */
	EClass getFinishModeType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.FinishModeType#getAutomatic <em>Automatic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Automatic</em>'.
	 * @see org.wfmc._2002.xpdl1.FinishModeType#getAutomatic()
	 * @see #getFinishModeType()
	 * @generated
	 */
	EReference getFinishModeType_Automatic();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.FinishModeType#getManual <em>Manual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Manual</em>'.
	 * @see org.wfmc._2002.xpdl1.FinishModeType#getManual()
	 * @see #getFinishModeType()
	 * @generated
	 */
	EReference getFinishModeType_Manual();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.FormalParametersType <em>Formal Parameters Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formal Parameters Type</em>'.
	 * @see org.wfmc._2002.xpdl1.FormalParametersType
	 * @generated
	 */
	EClass getFormalParametersType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.FormalParametersType#getFormalParameter <em>Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Formal Parameter</em>'.
	 * @see org.wfmc._2002.xpdl1.FormalParametersType#getFormalParameter()
	 * @see #getFormalParametersType()
	 * @generated
	 */
	EReference getFormalParametersType_FormalParameter();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.FormalParameterType <em>Formal Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formal Parameter Type</em>'.
	 * @see org.wfmc._2002.xpdl1.FormalParameterType
	 * @generated
	 */
	EClass getFormalParameterType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.FormalParameterType#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Type</em>'.
	 * @see org.wfmc._2002.xpdl1.FormalParameterType#getDataType()
	 * @see #getFormalParameterType()
	 * @generated
	 */
	EReference getFormalParameterType_DataType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.FormalParameterType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.FormalParameterType#getDescription()
	 * @see #getFormalParameterType()
	 * @generated
	 */
	EAttribute getFormalParameterType_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.FormalParameterType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.FormalParameterType#getId()
	 * @see #getFormalParameterType()
	 * @generated
	 */
	EAttribute getFormalParameterType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.FormalParameterType#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see org.wfmc._2002.xpdl1.FormalParameterType#getIndex()
	 * @see #getFormalParameterType()
	 * @generated
	 */
	EAttribute getFormalParameterType_Index();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.FormalParameterType#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see org.wfmc._2002.xpdl1.FormalParameterType#getMode()
	 * @see #getFormalParameterType()
	 * @generated
	 */
	EAttribute getFormalParameterType_Mode();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ImplementationType <em>Implementation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implementation Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ImplementationType
	 * @generated
	 */
	EClass getImplementationType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ImplementationType#getNo <em>No</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>No</em>'.
	 * @see org.wfmc._2002.xpdl1.ImplementationType#getNo()
	 * @see #getImplementationType()
	 * @generated
	 */
	EReference getImplementationType_No();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.ImplementationType#getTool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tool</em>'.
	 * @see org.wfmc._2002.xpdl1.ImplementationType#getTool()
	 * @see #getImplementationType()
	 * @generated
	 */
	EReference getImplementationType_Tool();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ImplementationType#getSubFlow <em>Sub Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sub Flow</em>'.
	 * @see org.wfmc._2002.xpdl1.ImplementationType#getSubFlow()
	 * @see #getImplementationType()
	 * @generated
	 */
	EReference getImplementationType_SubFlow();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.JoinType <em>Join Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Type</em>'.
	 * @see org.wfmc._2002.xpdl1.JoinType
	 * @generated
	 */
	EClass getJoinType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.JoinType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.wfmc._2002.xpdl1.JoinType#getType()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Type();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ListTypeType <em>List Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType
	 * @generated
	 */
	EClass getListTypeType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ListTypeType#getBasicType <em>Basic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Basic Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType#getBasicType()
	 * @see #getListTypeType()
	 * @generated
	 */
	EReference getListTypeType_BasicType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ListTypeType#getDeclaredType <em>Declared Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Declared Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType#getDeclaredType()
	 * @see #getListTypeType()
	 * @generated
	 */
	EReference getListTypeType_DeclaredType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ListTypeType#getSchemaType <em>Schema Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schema Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType#getSchemaType()
	 * @see #getListTypeType()
	 * @generated
	 */
	EReference getListTypeType_SchemaType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ListTypeType#getExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Reference</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType#getExternalReference()
	 * @see #getListTypeType()
	 * @generated
	 */
	EReference getListTypeType_ExternalReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ListTypeType#getRecordType <em>Record Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Record Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType#getRecordType()
	 * @see #getListTypeType()
	 * @generated
	 */
	EReference getListTypeType_RecordType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ListTypeType#getUnionType <em>Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Union Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType#getUnionType()
	 * @see #getListTypeType()
	 * @generated
	 */
	EReference getListTypeType_UnionType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ListTypeType#getEnumerationType <em>Enumeration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Enumeration Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType#getEnumerationType()
	 * @see #getListTypeType()
	 * @generated
	 */
	EReference getListTypeType_EnumerationType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ListTypeType#getArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType#getArrayType()
	 * @see #getListTypeType()
	 * @generated
	 */
	EReference getListTypeType_ArrayType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ListTypeType#getListType <em>List Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>List Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ListTypeType#getListType()
	 * @see #getListTypeType()
	 * @generated
	 */
	EReference getListTypeType_ListType();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ManualType <em>Manual Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Manual Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ManualType
	 * @generated
	 */
	EClass getManualType();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.MemberType <em>Member Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member Type</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType
	 * @generated
	 */
	EClass getMemberType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.MemberType#getBasicType <em>Basic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Basic Type</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType#getBasicType()
	 * @see #getMemberType()
	 * @generated
	 */
	EReference getMemberType_BasicType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.MemberType#getDeclaredType <em>Declared Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Declared Type</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType#getDeclaredType()
	 * @see #getMemberType()
	 * @generated
	 */
	EReference getMemberType_DeclaredType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.MemberType#getSchemaType <em>Schema Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schema Type</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType#getSchemaType()
	 * @see #getMemberType()
	 * @generated
	 */
	EReference getMemberType_SchemaType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.MemberType#getExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Reference</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType#getExternalReference()
	 * @see #getMemberType()
	 * @generated
	 */
	EReference getMemberType_ExternalReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.MemberType#getRecordType <em>Record Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Record Type</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType#getRecordType()
	 * @see #getMemberType()
	 * @generated
	 */
	EReference getMemberType_RecordType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.MemberType#getUnionType <em>Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Union Type</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType#getUnionType()
	 * @see #getMemberType()
	 * @generated
	 */
	EReference getMemberType_UnionType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.MemberType#getEnumerationType <em>Enumeration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Enumeration Type</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType#getEnumerationType()
	 * @see #getMemberType()
	 * @generated
	 */
	EReference getMemberType_EnumerationType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.MemberType#getArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array Type</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType#getArrayType()
	 * @see #getMemberType()
	 * @generated
	 */
	EReference getMemberType_ArrayType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.MemberType#getListType <em>List Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>List Type</em>'.
	 * @see org.wfmc._2002.xpdl1.MemberType#getListType()
	 * @see #getMemberType()
	 * @generated
	 */
	EReference getMemberType_ListType();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.NoType <em>No Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Type</em>'.
	 * @see org.wfmc._2002.xpdl1.NoType
	 * @generated
	 */
	EClass getNoType();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.PackageHeaderType <em>Package Header Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Header Type</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageHeaderType
	 * @generated
	 */
	EClass getPackageHeaderType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getXPDLVersion <em>XPDL Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XPDL Version</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageHeaderType#getXPDLVersion()
	 * @see #getPackageHeaderType()
	 * @generated
	 */
	EAttribute getPackageHeaderType_XPDLVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getVendor <em>Vendor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageHeaderType#getVendor()
	 * @see #getPackageHeaderType()
	 * @generated
	 */
	EAttribute getPackageHeaderType_Vendor();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getCreated <em>Created</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageHeaderType#getCreated()
	 * @see #getPackageHeaderType()
	 * @generated
	 */
	EAttribute getPackageHeaderType_Created();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageHeaderType#getDescription()
	 * @see #getPackageHeaderType()
	 * @generated
	 */
	EAttribute getPackageHeaderType_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Documentation</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageHeaderType#getDocumentation()
	 * @see #getPackageHeaderType()
	 * @generated
	 */
	EAttribute getPackageHeaderType_Documentation();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getPriorityUnit <em>Priority Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority Unit</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageHeaderType#getPriorityUnit()
	 * @see #getPackageHeaderType()
	 * @generated
	 */
	EAttribute getPackageHeaderType_PriorityUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getCostUnit <em>Cost Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost Unit</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageHeaderType#getCostUnit()
	 * @see #getPackageHeaderType()
	 * @generated
	 */
	EAttribute getPackageHeaderType_CostUnit();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.PackageType <em>Package Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Type</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType
	 * @generated
	 */
	EClass getPackageType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getPackageHeader <em>Package Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Package Header</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getPackageHeader()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_PackageHeader();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getRedefinableHeader <em>Redefinable Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Redefinable Header</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getRedefinableHeader()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_RedefinableHeader();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getConformanceClass <em>Conformance Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Conformance Class</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getConformanceClass()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_ConformanceClass();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getScript()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_Script();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getExternalPackages <em>External Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Packages</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getExternalPackages()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_ExternalPackages();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getTypeDeclarations <em>Type Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Declarations</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getTypeDeclarations()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_TypeDeclarations();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participants</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getParticipants()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_Participants();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getApplications <em>Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Applications</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getApplications()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_Applications();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getDataFields <em>Data Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Fields</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getDataFields()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_DataFields();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getWorkflowProcesses <em>Workflow Processes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Workflow Processes</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getWorkflowProcesses()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_WorkflowProcesses();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.PackageType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getExtendedAttributes()
	 * @see #getPackageType()
	 * @generated
	 */
	EReference getPackageType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.PackageType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getId()
	 * @see #getPackageType()
	 * @generated
	 */
	EAttribute getPackageType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.PackageType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.PackageType#getName()
	 * @see #getPackageType()
	 * @generated
	 */
	EAttribute getPackageType_Name();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ParticipantsType <em>Participants Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participants Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantsType
	 * @generated
	 */
	EClass getParticipantsType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.ParticipantsType#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participant</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantsType#getParticipant()
	 * @see #getParticipantsType()
	 * @generated
	 */
	EReference getParticipantsType_Participant();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ParticipantType <em>Participant Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantType
	 * @generated
	 */
	EClass getParticipantType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ParticipantType#getParticipantType <em>Participant Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participant Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantType#getParticipantType()
	 * @see #getParticipantType()
	 * @generated
	 */
	EReference getParticipantType_ParticipantType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ParticipantType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantType#getDescription()
	 * @see #getParticipantType()
	 * @generated
	 */
	EAttribute getParticipantType_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ParticipantType#getExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Reference</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantType#getExternalReference()
	 * @see #getParticipantType()
	 * @generated
	 */
	EReference getParticipantType_ExternalReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ParticipantType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantType#getExtendedAttributes()
	 * @see #getParticipantType()
	 * @generated
	 */
	EReference getParticipantType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ParticipantType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantType#getId()
	 * @see #getParticipantType()
	 * @generated
	 */
	EAttribute getParticipantType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ParticipantType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantType#getName()
	 * @see #getParticipantType()
	 * @generated
	 */
	EAttribute getParticipantType_Name();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ParticipantTypeType <em>Participant Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantTypeType
	 * @generated
	 */
	EClass getParticipantTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ParticipantTypeType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ParticipantTypeType#getType()
	 * @see #getParticipantTypeType()
	 * @generated
	 */
	EAttribute getParticipantTypeType_Type();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ProcessHeaderType <em>Process Header Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Header Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType
	 * @generated
	 */
	EClass getProcessHeaderType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ProcessHeaderType#getCreated <em>Created</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created</em>'.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType#getCreated()
	 * @see #getProcessHeaderType()
	 * @generated
	 */
	EAttribute getProcessHeaderType_Created();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ProcessHeaderType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType#getDescription()
	 * @see #getProcessHeaderType()
	 * @generated
	 */
	EAttribute getProcessHeaderType_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ProcessHeaderType#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType#getPriority()
	 * @see #getProcessHeaderType()
	 * @generated
	 */
	EAttribute getProcessHeaderType_Priority();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ProcessHeaderType#getLimit <em>Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Limit</em>'.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType#getLimit()
	 * @see #getProcessHeaderType()
	 * @generated
	 */
	EAttribute getProcessHeaderType_Limit();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ProcessHeaderType#getValidFrom <em>Valid From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid From</em>'.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType#getValidFrom()
	 * @see #getProcessHeaderType()
	 * @generated
	 */
	EAttribute getProcessHeaderType_ValidFrom();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ProcessHeaderType#getValidTo <em>Valid To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid To</em>'.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType#getValidTo()
	 * @see #getProcessHeaderType()
	 * @generated
	 */
	EAttribute getProcessHeaderType_ValidTo();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ProcessHeaderType#getTimeEstimation <em>Time Estimation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Time Estimation</em>'.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType#getTimeEstimation()
	 * @see #getProcessHeaderType()
	 * @generated
	 */
	EReference getProcessHeaderType_TimeEstimation();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ProcessHeaderType#getDurationUnit <em>Duration Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration Unit</em>'.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType#getDurationUnit()
	 * @see #getProcessHeaderType()
	 * @generated
	 */
	EAttribute getProcessHeaderType_DurationUnit();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.RecordTypeType <em>Record Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Record Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.RecordTypeType
	 * @generated
	 */
	EClass getRecordTypeType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.RecordTypeType#getMember <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Member</em>'.
	 * @see org.wfmc._2002.xpdl1.RecordTypeType#getMember()
	 * @see #getRecordTypeType()
	 * @generated
	 */
	EReference getRecordTypeType_Member();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType <em>Redefinable Header Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Redefinable Header Type</em>'.
	 * @see org.wfmc._2002.xpdl1.RedefinableHeaderType
	 * @generated
	 */
	EClass getRedefinableHeaderType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see org.wfmc._2002.xpdl1.RedefinableHeaderType#getAuthor()
	 * @see #getRedefinableHeaderType()
	 * @generated
	 */
	EAttribute getRedefinableHeaderType_Author();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.wfmc._2002.xpdl1.RedefinableHeaderType#getVersion()
	 * @see #getRedefinableHeaderType()
	 * @generated
	 */
	EAttribute getRedefinableHeaderType_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getCodepage <em>Codepage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Codepage</em>'.
	 * @see org.wfmc._2002.xpdl1.RedefinableHeaderType#getCodepage()
	 * @see #getRedefinableHeaderType()
	 * @generated
	 */
	EAttribute getRedefinableHeaderType_Codepage();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getCountrykey <em>Countrykey</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Countrykey</em>'.
	 * @see org.wfmc._2002.xpdl1.RedefinableHeaderType#getCountrykey()
	 * @see #getRedefinableHeaderType()
	 * @generated
	 */
	EAttribute getRedefinableHeaderType_Countrykey();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getResponsibles <em>Responsibles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Responsibles</em>'.
	 * @see org.wfmc._2002.xpdl1.RedefinableHeaderType#getResponsibles()
	 * @see #getRedefinableHeaderType()
	 * @generated
	 */
	EReference getRedefinableHeaderType_Responsibles();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getPublicationStatus <em>Publication Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Publication Status</em>'.
	 * @see org.wfmc._2002.xpdl1.RedefinableHeaderType#getPublicationStatus()
	 * @see #getRedefinableHeaderType()
	 * @generated
	 */
	EAttribute getRedefinableHeaderType_PublicationStatus();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ResponsiblesType <em>Responsibles Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Responsibles Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ResponsiblesType
	 * @generated
	 */
	EClass getResponsiblesType();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.ResponsiblesType#getResponsible <em>Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Responsible</em>'.
	 * @see org.wfmc._2002.xpdl1.ResponsiblesType#getResponsible()
	 * @see #getResponsiblesType()
	 * @generated
	 */
	EAttribute getResponsiblesType_Responsible();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.RouteType <em>Route Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Route Type</em>'.
	 * @see org.wfmc._2002.xpdl1.RouteType
	 * @generated
	 */
	EClass getRouteType();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.SchemaTypeType <em>Schema Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schema Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.SchemaTypeType
	 * @generated
	 */
	EClass getSchemaTypeType();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.SchemaTypeType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.wfmc._2002.xpdl1.SchemaTypeType#getAny()
	 * @see #getSchemaTypeType()
	 * @generated
	 */
	EAttribute getSchemaTypeType_Any();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ScriptType <em>Script Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ScriptType
	 * @generated
	 */
	EClass getScriptType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ScriptType#getGrammar <em>Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grammar</em>'.
	 * @see org.wfmc._2002.xpdl1.ScriptType#getGrammar()
	 * @see #getScriptType()
	 * @generated
	 */
	EAttribute getScriptType_Grammar();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ScriptType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ScriptType#getType()
	 * @see #getScriptType()
	 * @generated
	 */
	EAttribute getScriptType_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ScriptType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.wfmc._2002.xpdl1.ScriptType#getVersion()
	 * @see #getScriptType()
	 * @generated
	 */
	EAttribute getScriptType_Version();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.SimulationInformationType <em>Simulation Information Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simulation Information Type</em>'.
	 * @see org.wfmc._2002.xpdl1.SimulationInformationType
	 * @generated
	 */
	EClass getSimulationInformationType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.SimulationInformationType#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost</em>'.
	 * @see org.wfmc._2002.xpdl1.SimulationInformationType#getCost()
	 * @see #getSimulationInformationType()
	 * @generated
	 */
	EAttribute getSimulationInformationType_Cost();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.SimulationInformationType#getTimeEstimation <em>Time Estimation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Time Estimation</em>'.
	 * @see org.wfmc._2002.xpdl1.SimulationInformationType#getTimeEstimation()
	 * @see #getSimulationInformationType()
	 * @generated
	 */
	EReference getSimulationInformationType_TimeEstimation();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.SimulationInformationType#getInstantiation <em>Instantiation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instantiation</em>'.
	 * @see org.wfmc._2002.xpdl1.SimulationInformationType#getInstantiation()
	 * @see #getSimulationInformationType()
	 * @generated
	 */
	EAttribute getSimulationInformationType_Instantiation();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.SplitType <em>Split Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Split Type</em>'.
	 * @see org.wfmc._2002.xpdl1.SplitType
	 * @generated
	 */
	EClass getSplitType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.SplitType#getTransitionRefs <em>Transition Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transition Refs</em>'.
	 * @see org.wfmc._2002.xpdl1.SplitType#getTransitionRefs()
	 * @see #getSplitType()
	 * @generated
	 */
	EReference getSplitType_TransitionRefs();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.SplitType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.wfmc._2002.xpdl1.SplitType#getType()
	 * @see #getSplitType()
	 * @generated
	 */
	EAttribute getSplitType_Type();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.StartModeType <em>Start Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Mode Type</em>'.
	 * @see org.wfmc._2002.xpdl1.StartModeType
	 * @generated
	 */
	EClass getStartModeType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.StartModeType#getAutomatic <em>Automatic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Automatic</em>'.
	 * @see org.wfmc._2002.xpdl1.StartModeType#getAutomatic()
	 * @see #getStartModeType()
	 * @generated
	 */
	EReference getStartModeType_Automatic();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.StartModeType#getManual <em>Manual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Manual</em>'.
	 * @see org.wfmc._2002.xpdl1.StartModeType#getManual()
	 * @see #getStartModeType()
	 * @generated
	 */
	EReference getStartModeType_Manual();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.SubFlowType <em>Sub Flow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sub Flow Type</em>'.
	 * @see org.wfmc._2002.xpdl1.SubFlowType
	 * @generated
	 */
	EClass getSubFlowType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.SubFlowType#getActualParameters <em>Actual Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Actual Parameters</em>'.
	 * @see org.wfmc._2002.xpdl1.SubFlowType#getActualParameters()
	 * @see #getSubFlowType()
	 * @generated
	 */
	EReference getSubFlowType_ActualParameters();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.SubFlowType#getExecution <em>Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution</em>'.
	 * @see org.wfmc._2002.xpdl1.SubFlowType#getExecution()
	 * @see #getSubFlowType()
	 * @generated
	 */
	EAttribute getSubFlowType_Execution();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.SubFlowType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.SubFlowType#getId()
	 * @see #getSubFlowType()
	 * @generated
	 */
	EAttribute getSubFlowType_Id();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.TimeEstimationType <em>Time Estimation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Estimation Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TimeEstimationType
	 * @generated
	 */
	EClass getTimeEstimationType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TimeEstimationType#getWaitingTime <em>Waiting Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Waiting Time</em>'.
	 * @see org.wfmc._2002.xpdl1.TimeEstimationType#getWaitingTime()
	 * @see #getTimeEstimationType()
	 * @generated
	 */
	EAttribute getTimeEstimationType_WaitingTime();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TimeEstimationType#getWorkingTime <em>Working Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Working Time</em>'.
	 * @see org.wfmc._2002.xpdl1.TimeEstimationType#getWorkingTime()
	 * @see #getTimeEstimationType()
	 * @generated
	 */
	EAttribute getTimeEstimationType_WorkingTime();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TimeEstimationType#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see org.wfmc._2002.xpdl1.TimeEstimationType#getDuration()
	 * @see #getTimeEstimationType()
	 * @generated
	 */
	EAttribute getTimeEstimationType_Duration();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.ToolType <em>Tool Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tool Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ToolType
	 * @generated
	 */
	EClass getToolType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ToolType#getActualParameters <em>Actual Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Actual Parameters</em>'.
	 * @see org.wfmc._2002.xpdl1.ToolType#getActualParameters()
	 * @see #getToolType()
	 * @generated
	 */
	EReference getToolType_ActualParameters();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ToolType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.ToolType#getDescription()
	 * @see #getToolType()
	 * @generated
	 */
	EAttribute getToolType_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.ToolType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.ToolType#getExtendedAttributes()
	 * @see #getToolType()
	 * @generated
	 */
	EReference getToolType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ToolType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.ToolType#getId()
	 * @see #getToolType()
	 * @generated
	 */
	EAttribute getToolType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.ToolType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ToolType#getType()
	 * @see #getToolType()
	 * @generated
	 */
	EAttribute getToolType_Type();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.TransitionRefsType <em>Transition Refs Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Refs Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionRefsType
	 * @generated
	 */
	EClass getTransitionRefsType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.TransitionRefsType#getTransitionRef <em>Transition Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition Ref</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionRefsType#getTransitionRef()
	 * @see #getTransitionRefsType()
	 * @generated
	 */
	EReference getTransitionRefsType_TransitionRef();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.TransitionRefType <em>Transition Ref Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Ref Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionRefType
	 * @generated
	 */
	EClass getTransitionRefType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TransitionRefType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionRefType#getId()
	 * @see #getTransitionRefType()
	 * @generated
	 */
	EAttribute getTransitionRefType_Id();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.TransitionRestrictionsType <em>Transition Restrictions Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Restrictions Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionRestrictionsType
	 * @generated
	 */
	EClass getTransitionRestrictionsType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.TransitionRestrictionsType#getTransitionRestriction <em>Transition Restriction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition Restriction</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionRestrictionsType#getTransitionRestriction()
	 * @see #getTransitionRestrictionsType()
	 * @generated
	 */
	EReference getTransitionRestrictionsType_TransitionRestriction();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.TransitionRestrictionType <em>Transition Restriction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Restriction Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionRestrictionType
	 * @generated
	 */
	EClass getTransitionRestrictionType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TransitionRestrictionType#getJoin <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Join</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionRestrictionType#getJoin()
	 * @see #getTransitionRestrictionType()
	 * @generated
	 */
	EReference getTransitionRestrictionType_Join();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TransitionRestrictionType#getSplit <em>Split</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Split</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionRestrictionType#getSplit()
	 * @see #getTransitionRestrictionType()
	 * @generated
	 */
	EReference getTransitionRestrictionType_Split();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.TransitionsType <em>Transitions Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transitions Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionsType
	 * @generated
	 */
	EClass getTransitionsType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.TransitionsType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionsType#getTransition()
	 * @see #getTransitionsType()
	 * @generated
	 */
	EReference getTransitionsType_Transition();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.TransitionType <em>Transition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionType
	 * @generated
	 */
	EClass getTransitionType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TransitionType#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionType#getCondition()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_Condition();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TransitionType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionType#getDescription()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TransitionType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionType#getExtendedAttributes()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TransitionType#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionType#getFrom()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_From();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TransitionType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionType#getId()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TransitionType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionType#getName()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TransitionType#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see org.wfmc._2002.xpdl1.TransitionType#getTo()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_To();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.TypeDeclarationsType <em>Type Declarations Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Declarations Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationsType
	 * @generated
	 */
	EClass getTypeDeclarationsType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.TypeDeclarationsType#getTypeDeclaration <em>Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Declaration</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationsType#getTypeDeclaration()
	 * @see #getTypeDeclarationsType()
	 * @generated
	 */
	EReference getTypeDeclarationsType_TypeDeclaration();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.TypeDeclarationType <em>Type Declaration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Declaration Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType
	 * @generated
	 */
	EClass getTypeDeclarationType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getBasicType <em>Basic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Basic Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getBasicType()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_BasicType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getDeclaredType <em>Declared Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Declared Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getDeclaredType()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_DeclaredType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getSchemaType <em>Schema Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schema Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getSchemaType()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_SchemaType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getExternalReference <em>External Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Reference</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getExternalReference()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_ExternalReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getRecordType <em>Record Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Record Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getRecordType()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_RecordType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getUnionType <em>Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Union Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getUnionType()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_UnionType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getEnumerationType <em>Enumeration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Enumeration Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getEnumerationType()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_EnumerationType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getArrayType()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_ArrayType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getListType <em>List Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>List Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getListType()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_ListType();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getDescription()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EAttribute getTypeDeclarationType_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getExtendedAttributes()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EReference getTypeDeclarationType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getId()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EAttribute getTypeDeclarationType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType#getName()
	 * @see #getTypeDeclarationType()
	 * @generated
	 */
	EAttribute getTypeDeclarationType_Name();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.UnionTypeType <em>Union Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.UnionTypeType
	 * @generated
	 */
	EClass getUnionTypeType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.UnionTypeType#getMember <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Member</em>'.
	 * @see org.wfmc._2002.xpdl1.UnionTypeType#getMember()
	 * @see #getUnionTypeType()
	 * @generated
	 */
	EReference getUnionTypeType_Member();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.WorkflowProcessesType <em>Workflow Processes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow Processes Type</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessesType
	 * @generated
	 */
	EClass getWorkflowProcessesType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.wfmc._2002.xpdl1.WorkflowProcessesType#getWorkflowProcess <em>Workflow Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Workflow Process</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessesType#getWorkflowProcess()
	 * @see #getWorkflowProcessesType()
	 * @generated
	 */
	EReference getWorkflowProcessesType_WorkflowProcess();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.WorkflowProcessType <em>Workflow Process Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow Process Type</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType
	 * @generated
	 */
	EClass getWorkflowProcessType();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getProcessHeader <em>Process Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process Header</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getProcessHeader()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_ProcessHeader();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getRedefinableHeader <em>Redefinable Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Redefinable Header</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getRedefinableHeader()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_RedefinableHeader();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getFormalParameters <em>Formal Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formal Parameters</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getFormalParameters()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_FormalParameters();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getDataFields <em>Data Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Fields</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getDataFields()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_DataFields();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participants</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getParticipants()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_Participants();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getApplications <em>Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Applications</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getApplications()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_Applications();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getActivitySets <em>Activity Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activity Sets</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getActivitySets()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_ActivitySets();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activities</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getActivities()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_Activities();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transitions</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getTransitions()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_Transitions();

	/**
	 * Returns the meta object for the containment reference '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getExtendedAttributes()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EReference getWorkflowProcessType_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getAccessLevel <em>Access Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Access Level</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getAccessLevel()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EAttribute getWorkflowProcessType_AccessLevel();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getId()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EAttribute getWorkflowProcessType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType#getName()
	 * @see #getWorkflowProcessType()
	 * @generated
	 */
	EAttribute getWorkflowProcessType_Name();

	/**
	 * Returns the meta object for class '{@link org.wfmc._2002.xpdl1.XpressionType <em>Xpression Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Xpression Type</em>'.
	 * @see org.wfmc._2002.xpdl1.XpressionType
	 * @generated
	 */
	EClass getXpressionType();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.XpressionType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.wfmc._2002.xpdl1.XpressionType#getMixed()
	 * @see #getXpressionType()
	 * @generated
	 */
	EAttribute getXpressionType_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.XpressionType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.wfmc._2002.xpdl1.XpressionType#getGroup()
	 * @see #getXpressionType()
	 * @generated
	 */
	EAttribute getXpressionType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.wfmc._2002.xpdl1.XpressionType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.wfmc._2002.xpdl1.XpressionType#getAny()
	 * @see #getXpressionType()
	 * @generated
	 */
	EAttribute getXpressionType_Any();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.AccessLevelType <em>Access Level Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Access Level Type</em>'.
	 * @see org.wfmc._2002.xpdl1.AccessLevelType
	 * @generated
	 */
	EEnum getAccessLevelType();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.DurationUnitType <em>Duration Unit Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Duration Unit Type</em>'.
	 * @see org.wfmc._2002.xpdl1.DurationUnitType
	 * @generated
	 */
	EEnum getDurationUnitType();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.ExecutionType <em>Execution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Execution Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ExecutionType
	 * @generated
	 */
	EEnum getExecutionType();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.ExecutionType1 <em>Execution Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Execution Type1</em>'.
	 * @see org.wfmc._2002.xpdl1.ExecutionType1
	 * @generated
	 */
	EEnum getExecutionType1();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.GraphConformanceType <em>Graph Conformance Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Graph Conformance Type</em>'.
	 * @see org.wfmc._2002.xpdl1.GraphConformanceType
	 * @generated
	 */
	EEnum getGraphConformanceType();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.InstantiationType <em>Instantiation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Instantiation Type</em>'.
	 * @see org.wfmc._2002.xpdl1.InstantiationType
	 * @generated
	 */
	EEnum getInstantiationType();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.IsArrayType <em>Is Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Is Array Type</em>'.
	 * @see org.wfmc._2002.xpdl1.IsArrayType
	 * @generated
	 */
	EEnum getIsArrayType();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.ModeType <em>Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Mode Type</em>'.
	 * @see org.wfmc._2002.xpdl1.ModeType
	 * @generated
	 */
	EEnum getModeType();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.PublicationStatusType <em>Publication Status Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Publication Status Type</em>'.
	 * @see org.wfmc._2002.xpdl1.PublicationStatusType
	 * @generated
	 */
	EEnum getPublicationStatusType();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.TypeType <em>Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Type</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType
	 * @generated
	 */
	EEnum getTypeType();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.TypeType1 <em>Type Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Type1</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType1
	 * @generated
	 */
	EEnum getTypeType1();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.TypeType2 <em>Type Type2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Type2</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType2
	 * @generated
	 */
	EEnum getTypeType2();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.TypeType3 <em>Type Type3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Type3</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType3
	 * @generated
	 */
	EEnum getTypeType3();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.TypeType4 <em>Type Type4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Type4</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType4
	 * @generated
	 */
	EEnum getTypeType4();

	/**
	 * Returns the meta object for enum '{@link org.wfmc._2002.xpdl1.TypeType5 <em>Type Type5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Type5</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType5
	 * @generated
	 */
	EEnum getTypeType5();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.AccessLevelType <em>Access Level Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Access Level Type Object</em>'.
	 * @see org.wfmc._2002.xpdl1.AccessLevelType
	 * @model instanceClass="org.wfmc._2002.xpdl1.AccessLevelType"
	 *        extendedMetaData="name='AccessLevel_._type:Object' baseType='AccessLevel_._type'"
	 * @generated
	 */
	EDataType getAccessLevelTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.DurationUnitType <em>Duration Unit Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Duration Unit Type Object</em>'.
	 * @see org.wfmc._2002.xpdl1.DurationUnitType
	 * @model instanceClass="org.wfmc._2002.xpdl1.DurationUnitType"
	 *        extendedMetaData="name='DurationUnit_._type:Object' baseType='DurationUnit_._type'"
	 * @generated
	 */
	EDataType getDurationUnitTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.ExecutionType <em>Execution Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Execution Type Object</em>'.
	 * @see org.wfmc._2002.xpdl1.ExecutionType
	 * @model instanceClass="org.wfmc._2002.xpdl1.ExecutionType"
	 *        extendedMetaData="name='Execution_._type:Object' baseType='Execution_._type'"
	 * @generated
	 */
	EDataType getExecutionTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.ExecutionType1 <em>Execution Type Object1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Execution Type Object1</em>'.
	 * @see org.wfmc._2002.xpdl1.ExecutionType1
	 * @model instanceClass="org.wfmc._2002.xpdl1.ExecutionType1"
	 *        extendedMetaData="name='Execution_._1_._type:Object' baseType='Execution_._1_._type'"
	 * @generated
	 */
	EDataType getExecutionTypeObject1();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.GraphConformanceType <em>Graph Conformance Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Graph Conformance Type Object</em>'.
	 * @see org.wfmc._2002.xpdl1.GraphConformanceType
	 * @model instanceClass="org.wfmc._2002.xpdl1.GraphConformanceType"
	 *        extendedMetaData="name='GraphConformance_._type:Object' baseType='GraphConformance_._type'"
	 * @generated
	 */
	EDataType getGraphConformanceTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.InstantiationType <em>Instantiation Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Instantiation Type Object</em>'.
	 * @see org.wfmc._2002.xpdl1.InstantiationType
	 * @model instanceClass="org.wfmc._2002.xpdl1.InstantiationType"
	 *        extendedMetaData="name='Instantiation_._type:Object' baseType='Instantiation_._type'"
	 * @generated
	 */
	EDataType getInstantiationTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.IsArrayType <em>Is Array Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Is Array Type Object</em>'.
	 * @see org.wfmc._2002.xpdl1.IsArrayType
	 * @model instanceClass="org.wfmc._2002.xpdl1.IsArrayType"
	 *        extendedMetaData="name='IsArray_._type:Object' baseType='IsArray_._type'"
	 * @generated
	 */
	EDataType getIsArrayTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.ModeType <em>Mode Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Mode Type Object</em>'.
	 * @see org.wfmc._2002.xpdl1.ModeType
	 * @model instanceClass="org.wfmc._2002.xpdl1.ModeType"
	 *        extendedMetaData="name='Mode_._type:Object' baseType='Mode_._type'"
	 * @generated
	 */
	EDataType getModeTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.PublicationStatusType <em>Publication Status Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Publication Status Type Object</em>'.
	 * @see org.wfmc._2002.xpdl1.PublicationStatusType
	 * @model instanceClass="org.wfmc._2002.xpdl1.PublicationStatusType"
	 *        extendedMetaData="name='PublicationStatus_._type:Object' baseType='PublicationStatus_._type'"
	 * @generated
	 */
	EDataType getPublicationStatusTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.TypeType4 <em>Type Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type Object</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType4
	 * @model instanceClass="org.wfmc._2002.xpdl1.TypeType4"
	 *        extendedMetaData="name='Type_._4_._type:Object' baseType='Type_._4_._type'"
	 * @generated
	 */
	EDataType getTypeTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.TypeType1 <em>Type Type Object1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type Object1</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType1
	 * @model instanceClass="org.wfmc._2002.xpdl1.TypeType1"
	 *        extendedMetaData="name='Type_._1_._type:Object' baseType='Type_._1_._type'"
	 * @generated
	 */
	EDataType getTypeTypeObject1();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.TypeType3 <em>Type Type Object2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type Object2</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType3
	 * @model instanceClass="org.wfmc._2002.xpdl1.TypeType3"
	 *        extendedMetaData="name='Type_._3_._type:Object' baseType='Type_._3_._type'"
	 * @generated
	 */
	EDataType getTypeTypeObject2();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.TypeType2 <em>Type Type Object3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type Object3</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType2
	 * @model instanceClass="org.wfmc._2002.xpdl1.TypeType2"
	 *        extendedMetaData="name='Type_._2_._type:Object' baseType='Type_._2_._type'"
	 * @generated
	 */
	EDataType getTypeTypeObject3();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.TypeType <em>Type Type Object4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type Object4</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType
	 * @model instanceClass="org.wfmc._2002.xpdl1.TypeType"
	 *        extendedMetaData="name='Type_._type:Object' baseType='Type_._type'"
	 * @generated
	 */
	EDataType getTypeTypeObject4();

	/**
	 * Returns the meta object for data type '{@link org.wfmc._2002.xpdl1.TypeType5 <em>Type Type Object5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type Object5</em>'.
	 * @see org.wfmc._2002.xpdl1.TypeType5
	 * @model instanceClass="org.wfmc._2002.xpdl1.TypeType5"
	 *        extendedMetaData="name='Type_._5_._type:Object' baseType='Type_._5_._type'"
	 * @generated
	 */
	EDataType getTypeTypeObject5();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Xpdl1Factory getXpdl1Factory();

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
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ActivitiesTypeImpl <em>Activities Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ActivitiesTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActivitiesType()
		 * @generated
		 */
		EClass ACTIVITIES_TYPE = eINSTANCE.getActivitiesType();

		/**
		 * The meta object literal for the '<em><b>Activity</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITIES_TYPE__ACTIVITY = eINSTANCE.getActivitiesType_Activity();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ActivitySetsTypeImpl <em>Activity Sets Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ActivitySetsTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActivitySetsType()
		 * @generated
		 */
		EClass ACTIVITY_SETS_TYPE = eINSTANCE.getActivitySetsType();

		/**
		 * The meta object literal for the '<em><b>Activity Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_SETS_TYPE__ACTIVITY_SET = eINSTANCE.getActivitySetsType_ActivitySet();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ActivitySetTypeImpl <em>Activity Set Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ActivitySetTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActivitySetType()
		 * @generated
		 */
		EClass ACTIVITY_SET_TYPE = eINSTANCE.getActivitySetType();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_SET_TYPE__ACTIVITIES = eINSTANCE.getActivitySetType_Activities();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_SET_TYPE__TRANSITIONS = eINSTANCE.getActivitySetType_Transitions();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_SET_TYPE__ID = eINSTANCE.getActivitySetType_Id();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl <em>Activity Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ActivityTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActivityType()
		 * @generated
		 */
		EClass ACTIVITY_TYPE = eINSTANCE.getActivityType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_TYPE__DESCRIPTION = eINSTANCE.getActivityType_Description();

		/**
		 * The meta object literal for the '<em><b>Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_TYPE__LIMIT = eINSTANCE.getActivityType_Limit();

		/**
		 * The meta object literal for the '<em><b>Route</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_TYPE__ROUTE = eINSTANCE.getActivityType_Route();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_TYPE__IMPLEMENTATION = eINSTANCE.getActivityType_Implementation();

		/**
		 * The meta object literal for the '<em><b>Block Activity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_TYPE__BLOCK_ACTIVITY = eINSTANCE.getActivityType_BlockActivity();

		/**
		 * The meta object literal for the '<em><b>Performer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_TYPE__PERFORMER = eINSTANCE.getActivityType_Performer();

		/**
		 * The meta object literal for the '<em><b>Start Mode</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_TYPE__START_MODE = eINSTANCE.getActivityType_StartMode();

		/**
		 * The meta object literal for the '<em><b>Finish Mode</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_TYPE__FINISH_MODE = eINSTANCE.getActivityType_FinishMode();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_TYPE__PRIORITY = eINSTANCE.getActivityType_Priority();

		/**
		 * The meta object literal for the '<em><b>Deadline</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_TYPE__DEADLINE = eINSTANCE.getActivityType_Deadline();

		/**
		 * The meta object literal for the '<em><b>Simulation Information</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_TYPE__SIMULATION_INFORMATION = eINSTANCE.getActivityType_SimulationInformation();

		/**
		 * The meta object literal for the '<em><b>Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_TYPE__ICON = eINSTANCE.getActivityType_Icon();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_TYPE__DOCUMENTATION = eINSTANCE.getActivityType_Documentation();

		/**
		 * The meta object literal for the '<em><b>Transition Restrictions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_TYPE__TRANSITION_RESTRICTIONS = eINSTANCE.getActivityType_TransitionRestrictions();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getActivityType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_TYPE__ID = eINSTANCE.getActivityType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_TYPE__NAME = eINSTANCE.getActivityType_Name();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ActualParametersTypeImpl <em>Actual Parameters Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ActualParametersTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getActualParametersType()
		 * @generated
		 */
		EClass ACTUAL_PARAMETERS_TYPE = eINSTANCE.getActualParametersType();

		/**
		 * The meta object literal for the '<em><b>Actual Parameter</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTUAL_PARAMETERS_TYPE__ACTUAL_PARAMETER = eINSTANCE.getActualParametersType_ActualParameter();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ApplicationsTypeImpl <em>Applications Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ApplicationsTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getApplicationsType()
		 * @generated
		 */
		EClass APPLICATIONS_TYPE = eINSTANCE.getApplicationsType();

		/**
		 * The meta object literal for the '<em><b>Application</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATIONS_TYPE__APPLICATION = eINSTANCE.getApplicationsType_Application();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ApplicationTypeImpl <em>Application Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ApplicationTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getApplicationType()
		 * @generated
		 */
		EClass APPLICATION_TYPE = eINSTANCE.getApplicationType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_TYPE__DESCRIPTION = eINSTANCE.getApplicationType_Description();

		/**
		 * The meta object literal for the '<em><b>Formal Parameters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_TYPE__FORMAL_PARAMETERS = eINSTANCE.getApplicationType_FormalParameters();

		/**
		 * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_TYPE__EXTERNAL_REFERENCE = eINSTANCE.getApplicationType_ExternalReference();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getApplicationType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_TYPE__ID = eINSTANCE.getApplicationType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_TYPE__NAME = eINSTANCE.getApplicationType_Name();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ArrayTypeTypeImpl <em>Array Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ArrayTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getArrayTypeType()
		 * @generated
		 */
		EClass ARRAY_TYPE_TYPE = eINSTANCE.getArrayTypeType();

		/**
		 * The meta object literal for the '<em><b>Basic Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE_TYPE__BASIC_TYPE = eINSTANCE.getArrayTypeType_BasicType();

		/**
		 * The meta object literal for the '<em><b>Declared Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE_TYPE__DECLARED_TYPE = eINSTANCE.getArrayTypeType_DeclaredType();

		/**
		 * The meta object literal for the '<em><b>Schema Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE_TYPE__SCHEMA_TYPE = eINSTANCE.getArrayTypeType_SchemaType();

		/**
		 * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE_TYPE__EXTERNAL_REFERENCE = eINSTANCE.getArrayTypeType_ExternalReference();

		/**
		 * The meta object literal for the '<em><b>Record Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE_TYPE__RECORD_TYPE = eINSTANCE.getArrayTypeType_RecordType();

		/**
		 * The meta object literal for the '<em><b>Union Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE_TYPE__UNION_TYPE = eINSTANCE.getArrayTypeType_UnionType();

		/**
		 * The meta object literal for the '<em><b>Enumeration Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE_TYPE__ENUMERATION_TYPE = eINSTANCE.getArrayTypeType_EnumerationType();

		/**
		 * The meta object literal for the '<em><b>Array Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE_TYPE__ARRAY_TYPE = eINSTANCE.getArrayTypeType_ArrayType();

		/**
		 * The meta object literal for the '<em><b>List Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE_TYPE__LIST_TYPE = eINSTANCE.getArrayTypeType_ListType();

		/**
		 * The meta object literal for the '<em><b>Lower Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_TYPE_TYPE__LOWER_INDEX = eINSTANCE.getArrayTypeType_LowerIndex();

		/**
		 * The meta object literal for the '<em><b>Upper Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_TYPE_TYPE__UPPER_INDEX = eINSTANCE.getArrayTypeType_UpperIndex();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.AutomaticTypeImpl <em>Automatic Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.AutomaticTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getAutomaticType()
		 * @generated
		 */
		EClass AUTOMATIC_TYPE = eINSTANCE.getAutomaticType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.BasicTypeTypeImpl <em>Basic Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.BasicTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getBasicTypeType()
		 * @generated
		 */
		EClass BASIC_TYPE_TYPE = eINSTANCE.getBasicTypeType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_TYPE_TYPE__TYPE = eINSTANCE.getBasicTypeType_Type();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.BlockActivityTypeImpl <em>Block Activity Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.BlockActivityTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getBlockActivityType()
		 * @generated
		 */
		EClass BLOCK_ACTIVITY_TYPE = eINSTANCE.getBlockActivityType();

		/**
		 * The meta object literal for the '<em><b>Block Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK_ACTIVITY_TYPE__BLOCK_ID = eINSTANCE.getBlockActivityType_BlockId();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ConditionTypeImpl <em>Condition Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ConditionTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getConditionType()
		 * @generated
		 */
		EClass CONDITION_TYPE = eINSTANCE.getConditionType();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION_TYPE__MIXED = eINSTANCE.getConditionType_Mixed();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION_TYPE__GROUP = eINSTANCE.getConditionType_Group();

		/**
		 * The meta object literal for the '<em><b>Xpression</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION_TYPE__XPRESSION = eINSTANCE.getConditionType_Xpression();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION_TYPE__TYPE = eINSTANCE.getConditionType_Type();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ConformanceClassTypeImpl <em>Conformance Class Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ConformanceClassTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getConformanceClassType()
		 * @generated
		 */
		EClass CONFORMANCE_CLASS_TYPE = eINSTANCE.getConformanceClassType();

		/**
		 * The meta object literal for the '<em><b>Graph Conformance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFORMANCE_CLASS_TYPE__GRAPH_CONFORMANCE = eINSTANCE.getConformanceClassType_GraphConformance();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.DataFieldsTypeImpl <em>Data Fields Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.DataFieldsTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDataFieldsType()
		 * @generated
		 */
		EClass DATA_FIELDS_TYPE = eINSTANCE.getDataFieldsType();

		/**
		 * The meta object literal for the '<em><b>Data Field</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_FIELDS_TYPE__DATA_FIELD = eINSTANCE.getDataFieldsType_DataField();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.DataFieldTypeImpl <em>Data Field Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.DataFieldTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDataFieldType()
		 * @generated
		 */
		EClass DATA_FIELD_TYPE = eINSTANCE.getDataFieldType();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_FIELD_TYPE__DATA_TYPE = eINSTANCE.getDataFieldType_DataType();

		/**
		 * The meta object literal for the '<em><b>Initial Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FIELD_TYPE__INITIAL_VALUE = eINSTANCE.getDataFieldType_InitialValue();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FIELD_TYPE__LENGTH = eINSTANCE.getDataFieldType_Length();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FIELD_TYPE__DESCRIPTION = eINSTANCE.getDataFieldType_Description();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_FIELD_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getDataFieldType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FIELD_TYPE__ID = eINSTANCE.getDataFieldType_Id();

		/**
		 * The meta object literal for the '<em><b>Is Array</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FIELD_TYPE__IS_ARRAY = eINSTANCE.getDataFieldType_IsArray();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FIELD_TYPE__NAME = eINSTANCE.getDataFieldType_Name();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.DataTypeTypeImpl <em>Data Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.DataTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDataTypeType()
		 * @generated
		 */
		EClass DATA_TYPE_TYPE = eINSTANCE.getDataTypeType();

		/**
		 * The meta object literal for the '<em><b>Basic Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_TYPE__BASIC_TYPE = eINSTANCE.getDataTypeType_BasicType();

		/**
		 * The meta object literal for the '<em><b>Declared Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_TYPE__DECLARED_TYPE = eINSTANCE.getDataTypeType_DeclaredType();

		/**
		 * The meta object literal for the '<em><b>Schema Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_TYPE__SCHEMA_TYPE = eINSTANCE.getDataTypeType_SchemaType();

		/**
		 * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_TYPE__EXTERNAL_REFERENCE = eINSTANCE.getDataTypeType_ExternalReference();

		/**
		 * The meta object literal for the '<em><b>Record Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_TYPE__RECORD_TYPE = eINSTANCE.getDataTypeType_RecordType();

		/**
		 * The meta object literal for the '<em><b>Union Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_TYPE__UNION_TYPE = eINSTANCE.getDataTypeType_UnionType();

		/**
		 * The meta object literal for the '<em><b>Enumeration Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_TYPE__ENUMERATION_TYPE = eINSTANCE.getDataTypeType_EnumerationType();

		/**
		 * The meta object literal for the '<em><b>Array Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_TYPE__ARRAY_TYPE = eINSTANCE.getDataTypeType_ArrayType();

		/**
		 * The meta object literal for the '<em><b>List Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_TYPE__LIST_TYPE = eINSTANCE.getDataTypeType_ListType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.DeadlineTypeImpl <em>Deadline Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.DeadlineTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDeadlineType()
		 * @generated
		 */
		EClass DEADLINE_TYPE = eINSTANCE.getDeadlineType();

		/**
		 * The meta object literal for the '<em><b>Deadline Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEADLINE_TYPE__DEADLINE_CONDITION = eINSTANCE.getDeadlineType_DeadlineCondition();

		/**
		 * The meta object literal for the '<em><b>Exception Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEADLINE_TYPE__EXCEPTION_NAME = eINSTANCE.getDeadlineType_ExceptionName();

		/**
		 * The meta object literal for the '<em><b>Execution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEADLINE_TYPE__EXECUTION = eINSTANCE.getDeadlineType_Execution();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.DeclaredTypeTypeImpl <em>Declared Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.DeclaredTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDeclaredTypeType()
		 * @generated
		 */
		EClass DECLARED_TYPE_TYPE = eINSTANCE.getDeclaredTypeType();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECLARED_TYPE_TYPE__ID = eINSTANCE.getDeclaredTypeType_Id();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.DocumentRootImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ACTIVITIES = eINSTANCE.getDocumentRoot_Activities();

		/**
		 * The meta object literal for the '<em><b>Activity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ACTIVITY = eINSTANCE.getDocumentRoot_Activity();

		/**
		 * The meta object literal for the '<em><b>Activity Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ACTIVITY_SET = eINSTANCE.getDocumentRoot_ActivitySet();

		/**
		 * The meta object literal for the '<em><b>Activity Sets</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ACTIVITY_SETS = eINSTANCE.getDocumentRoot_ActivitySets();

		/**
		 * The meta object literal for the '<em><b>Actual Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__ACTUAL_PARAMETER = eINSTANCE.getDocumentRoot_ActualParameter();

		/**
		 * The meta object literal for the '<em><b>Actual Parameters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ACTUAL_PARAMETERS = eINSTANCE.getDocumentRoot_ActualParameters();

		/**
		 * The meta object literal for the '<em><b>Application</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__APPLICATION = eINSTANCE.getDocumentRoot_Application();

		/**
		 * The meta object literal for the '<em><b>Applications</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__APPLICATIONS = eINSTANCE.getDocumentRoot_Applications();

		/**
		 * The meta object literal for the '<em><b>Array Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ARRAY_TYPE = eINSTANCE.getDocumentRoot_ArrayType();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__AUTHOR = eINSTANCE.getDocumentRoot_Author();

		/**
		 * The meta object literal for the '<em><b>Automatic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__AUTOMATIC = eINSTANCE.getDocumentRoot_Automatic();

		/**
		 * The meta object literal for the '<em><b>Basic Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BASIC_TYPE = eINSTANCE.getDocumentRoot_BasicType();

		/**
		 * The meta object literal for the '<em><b>Block Activity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BLOCK_ACTIVITY = eINSTANCE.getDocumentRoot_BlockActivity();

		/**
		 * The meta object literal for the '<em><b>Codepage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CODEPAGE = eINSTANCE.getDocumentRoot_Codepage();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONDITION = eINSTANCE.getDocumentRoot_Condition();

		/**
		 * The meta object literal for the '<em><b>Conformance Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONFORMANCE_CLASS = eINSTANCE.getDocumentRoot_ConformanceClass();

		/**
		 * The meta object literal for the '<em><b>Cost</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__COST = eINSTANCE.getDocumentRoot_Cost();

		/**
		 * The meta object literal for the '<em><b>Cost Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__COST_UNIT = eINSTANCE.getDocumentRoot_CostUnit();

		/**
		 * The meta object literal for the '<em><b>Countrykey</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__COUNTRYKEY = eINSTANCE.getDocumentRoot_Countrykey();

		/**
		 * The meta object literal for the '<em><b>Created</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CREATED = eINSTANCE.getDocumentRoot_Created();

		/**
		 * The meta object literal for the '<em><b>Data Field</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_FIELD = eINSTANCE.getDocumentRoot_DataField();

		/**
		 * The meta object literal for the '<em><b>Data Fields</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_FIELDS = eINSTANCE.getDocumentRoot_DataFields();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_TYPE = eINSTANCE.getDocumentRoot_DataType();

		/**
		 * The meta object literal for the '<em><b>Deadline</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DEADLINE = eINSTANCE.getDocumentRoot_Deadline();

		/**
		 * The meta object literal for the '<em><b>Declared Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DECLARED_TYPE = eINSTANCE.getDocumentRoot_DeclaredType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__DESCRIPTION = eINSTANCE.getDocumentRoot_Description();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__DOCUMENTATION = eINSTANCE.getDocumentRoot_Documentation();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__DURATION = eINSTANCE.getDocumentRoot_Duration();

		/**
		 * The meta object literal for the '<em><b>Enumeration Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ENUMERATION_TYPE = eINSTANCE.getDocumentRoot_EnumerationType();

		/**
		 * The meta object literal for the '<em><b>Enumeration Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ENUMERATION_VALUE = eINSTANCE.getDocumentRoot_EnumerationValue();

		/**
		 * The meta object literal for the '<em><b>Extended Attribute</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXTENDED_ATTRIBUTE = eINSTANCE.getDocumentRoot_ExtendedAttribute();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXTENDED_ATTRIBUTES = eINSTANCE.getDocumentRoot_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>External Package</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXTERNAL_PACKAGE = eINSTANCE.getDocumentRoot_ExternalPackage();

		/**
		 * The meta object literal for the '<em><b>External Packages</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXTERNAL_PACKAGES = eINSTANCE.getDocumentRoot_ExternalPackages();

		/**
		 * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXTERNAL_REFERENCE = eINSTANCE.getDocumentRoot_ExternalReference();

		/**
		 * The meta object literal for the '<em><b>Finish Mode</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FINISH_MODE = eINSTANCE.getDocumentRoot_FinishMode();

		/**
		 * The meta object literal for the '<em><b>Formal Parameter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FORMAL_PARAMETER = eINSTANCE.getDocumentRoot_FormalParameter();

		/**
		 * The meta object literal for the '<em><b>Formal Parameters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FORMAL_PARAMETERS = eINSTANCE.getDocumentRoot_FormalParameters();

		/**
		 * The meta object literal for the '<em><b>Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__ICON = eINSTANCE.getDocumentRoot_Icon();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IMPLEMENTATION = eINSTANCE.getDocumentRoot_Implementation();

		/**
		 * The meta object literal for the '<em><b>Initial Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__INITIAL_VALUE = eINSTANCE.getDocumentRoot_InitialValue();

		/**
		 * The meta object literal for the '<em><b>Join</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__JOIN = eINSTANCE.getDocumentRoot_Join();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__LENGTH = eINSTANCE.getDocumentRoot_Length();

		/**
		 * The meta object literal for the '<em><b>Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__LIMIT = eINSTANCE.getDocumentRoot_Limit();

		/**
		 * The meta object literal for the '<em><b>List Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LIST_TYPE = eINSTANCE.getDocumentRoot_ListType();

		/**
		 * The meta object literal for the '<em><b>Manual</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MANUAL = eINSTANCE.getDocumentRoot_Manual();

		/**
		 * The meta object literal for the '<em><b>Member</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MEMBER = eINSTANCE.getDocumentRoot_Member();

		/**
		 * The meta object literal for the '<em><b>No</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NO = eINSTANCE.getDocumentRoot_No();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PACKAGE = eINSTANCE.getDocumentRoot_Package();

		/**
		 * The meta object literal for the '<em><b>Package Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PACKAGE_HEADER = eINSTANCE.getDocumentRoot_PackageHeader();

		/**
		 * The meta object literal for the '<em><b>Participant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARTICIPANT = eINSTANCE.getDocumentRoot_Participant();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARTICIPANTS = eINSTANCE.getDocumentRoot_Participants();

		/**
		 * The meta object literal for the '<em><b>Participant Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARTICIPANT_TYPE = eINSTANCE.getDocumentRoot_ParticipantType();

		/**
		 * The meta object literal for the '<em><b>Performer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__PERFORMER = eINSTANCE.getDocumentRoot_Performer();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__PRIORITY = eINSTANCE.getDocumentRoot_Priority();

		/**
		 * The meta object literal for the '<em><b>Priority Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__PRIORITY_UNIT = eINSTANCE.getDocumentRoot_PriorityUnit();

		/**
		 * The meta object literal for the '<em><b>Process Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROCESS_HEADER = eINSTANCE.getDocumentRoot_ProcessHeader();

		/**
		 * The meta object literal for the '<em><b>Record Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RECORD_TYPE = eINSTANCE.getDocumentRoot_RecordType();

		/**
		 * The meta object literal for the '<em><b>Redefinable Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__REDEFINABLE_HEADER = eINSTANCE.getDocumentRoot_RedefinableHeader();

		/**
		 * The meta object literal for the '<em><b>Responsible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__RESPONSIBLE = eINSTANCE.getDocumentRoot_Responsible();

		/**
		 * The meta object literal for the '<em><b>Responsibles</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RESPONSIBLES = eINSTANCE.getDocumentRoot_Responsibles();

		/**
		 * The meta object literal for the '<em><b>Route</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ROUTE = eINSTANCE.getDocumentRoot_Route();

		/**
		 * The meta object literal for the '<em><b>Schema Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SCHEMA_TYPE = eINSTANCE.getDocumentRoot_SchemaType();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SCRIPT = eINSTANCE.getDocumentRoot_Script();

		/**
		 * The meta object literal for the '<em><b>Simulation Information</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SIMULATION_INFORMATION = eINSTANCE.getDocumentRoot_SimulationInformation();

		/**
		 * The meta object literal for the '<em><b>Split</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SPLIT = eINSTANCE.getDocumentRoot_Split();

		/**
		 * The meta object literal for the '<em><b>Start Mode</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__START_MODE = eINSTANCE.getDocumentRoot_StartMode();

		/**
		 * The meta object literal for the '<em><b>Sub Flow</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SUB_FLOW = eINSTANCE.getDocumentRoot_SubFlow();

		/**
		 * The meta object literal for the '<em><b>Time Estimation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TIME_ESTIMATION = eINSTANCE.getDocumentRoot_TimeEstimation();

		/**
		 * The meta object literal for the '<em><b>Tool</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TOOL = eINSTANCE.getDocumentRoot_Tool();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRANSITION = eINSTANCE.getDocumentRoot_Transition();

		/**
		 * The meta object literal for the '<em><b>Transition Ref</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRANSITION_REF = eINSTANCE.getDocumentRoot_TransitionRef();

		/**
		 * The meta object literal for the '<em><b>Transition Refs</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRANSITION_REFS = eINSTANCE.getDocumentRoot_TransitionRefs();

		/**
		 * The meta object literal for the '<em><b>Transition Restriction</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRANSITION_RESTRICTION = eINSTANCE.getDocumentRoot_TransitionRestriction();

		/**
		 * The meta object literal for the '<em><b>Transition Restrictions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRANSITION_RESTRICTIONS = eINSTANCE.getDocumentRoot_TransitionRestrictions();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRANSITIONS = eINSTANCE.getDocumentRoot_Transitions();

		/**
		 * The meta object literal for the '<em><b>Type Declaration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TYPE_DECLARATION = eINSTANCE.getDocumentRoot_TypeDeclaration();

		/**
		 * The meta object literal for the '<em><b>Type Declarations</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TYPE_DECLARATIONS = eINSTANCE.getDocumentRoot_TypeDeclarations();

		/**
		 * The meta object literal for the '<em><b>Union Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__UNION_TYPE = eINSTANCE.getDocumentRoot_UnionType();

		/**
		 * The meta object literal for the '<em><b>Valid From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__VALID_FROM = eINSTANCE.getDocumentRoot_ValidFrom();

		/**
		 * The meta object literal for the '<em><b>Valid To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__VALID_TO = eINSTANCE.getDocumentRoot_ValidTo();

		/**
		 * The meta object literal for the '<em><b>Vendor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__VENDOR = eINSTANCE.getDocumentRoot_Vendor();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__VERSION = eINSTANCE.getDocumentRoot_Version();

		/**
		 * The meta object literal for the '<em><b>Waiting Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__WAITING_TIME = eINSTANCE.getDocumentRoot_WaitingTime();

		/**
		 * The meta object literal for the '<em><b>Workflow Process</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WORKFLOW_PROCESS = eINSTANCE.getDocumentRoot_WorkflowProcess();

		/**
		 * The meta object literal for the '<em><b>Workflow Processes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WORKFLOW_PROCESSES = eINSTANCE.getDocumentRoot_WorkflowProcesses();

		/**
		 * The meta object literal for the '<em><b>Working Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__WORKING_TIME = eINSTANCE.getDocumentRoot_WorkingTime();

		/**
		 * The meta object literal for the '<em><b>XPDL Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__XPDL_VERSION = eINSTANCE.getDocumentRoot_XPDLVersion();

		/**
		 * The meta object literal for the '<em><b>Xpression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XPRESSION = eINSTANCE.getDocumentRoot_Xpression();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.EnumerationTypeTypeImpl <em>Enumeration Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.EnumerationTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getEnumerationTypeType()
		 * @generated
		 */
		EClass ENUMERATION_TYPE_TYPE = eINSTANCE.getEnumerationTypeType();

		/**
		 * The meta object literal for the '<em><b>Enumeration Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION_TYPE_TYPE__ENUMERATION_VALUE = eINSTANCE.getEnumerationTypeType_EnumerationValue();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.EnumerationValueTypeImpl <em>Enumeration Value Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.EnumerationValueTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getEnumerationValueType()
		 * @generated
		 */
		EClass ENUMERATION_VALUE_TYPE = eINSTANCE.getEnumerationValueType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUMERATION_VALUE_TYPE__NAME = eINSTANCE.getEnumerationValueType_Name();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributesTypeImpl <em>Extended Attributes Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ExtendedAttributesTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExtendedAttributesType()
		 * @generated
		 */
		EClass EXTENDED_ATTRIBUTES_TYPE = eINSTANCE.getExtendedAttributesType();

		/**
		 * The meta object literal for the '<em><b>Extended Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE = eINSTANCE.getExtendedAttributesType_ExtendedAttribute();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributeTypeImpl <em>Extended Attribute Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ExtendedAttributeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExtendedAttributeType()
		 * @generated
		 */
		EClass EXTENDED_ATTRIBUTE_TYPE = eINSTANCE.getExtendedAttributeType();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__MIXED = eINSTANCE.getExtendedAttributeType_Mixed();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__GROUP = eINSTANCE.getExtendedAttributeType_Group();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__ANY = eINSTANCE.getExtendedAttributeType_Any();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__NAME = eINSTANCE.getExtendedAttributeType_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__VALUE = eINSTANCE.getExtendedAttributeType_Value();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ExternalPackagesTypeImpl <em>External Packages Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ExternalPackagesTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExternalPackagesType()
		 * @generated
		 */
		EClass EXTERNAL_PACKAGES_TYPE = eINSTANCE.getExternalPackagesType();

		/**
		 * The meta object literal for the '<em><b>External Package</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_PACKAGES_TYPE__EXTERNAL_PACKAGE = eINSTANCE.getExternalPackagesType_ExternalPackage();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ExternalPackageTypeImpl <em>External Package Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ExternalPackageTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExternalPackageType()
		 * @generated
		 */
		EClass EXTERNAL_PACKAGE_TYPE = eINSTANCE.getExternalPackageType();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_PACKAGE_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getExternalPackageType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Href</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_PACKAGE_TYPE__HREF = eINSTANCE.getExternalPackageType_Href();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ExternalReferenceTypeImpl <em>External Reference Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ExternalReferenceTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExternalReferenceType()
		 * @generated
		 */
		EClass EXTERNAL_REFERENCE_TYPE = eINSTANCE.getExternalReferenceType();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_REFERENCE_TYPE__LOCATION = eINSTANCE.getExternalReferenceType_Location();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_REFERENCE_TYPE__NAMESPACE = eINSTANCE.getExternalReferenceType_Namespace();

		/**
		 * The meta object literal for the '<em><b>Xref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_REFERENCE_TYPE__XREF = eINSTANCE.getExternalReferenceType_Xref();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.FinishModeTypeImpl <em>Finish Mode Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.FinishModeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getFinishModeType()
		 * @generated
		 */
		EClass FINISH_MODE_TYPE = eINSTANCE.getFinishModeType();

		/**
		 * The meta object literal for the '<em><b>Automatic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FINISH_MODE_TYPE__AUTOMATIC = eINSTANCE.getFinishModeType_Automatic();

		/**
		 * The meta object literal for the '<em><b>Manual</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FINISH_MODE_TYPE__MANUAL = eINSTANCE.getFinishModeType_Manual();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.FormalParametersTypeImpl <em>Formal Parameters Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.FormalParametersTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getFormalParametersType()
		 * @generated
		 */
		EClass FORMAL_PARAMETERS_TYPE = eINSTANCE.getFormalParametersType();

		/**
		 * The meta object literal for the '<em><b>Formal Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER = eINSTANCE.getFormalParametersType_FormalParameter();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.FormalParameterTypeImpl <em>Formal Parameter Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.FormalParameterTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getFormalParameterType()
		 * @generated
		 */
		EClass FORMAL_PARAMETER_TYPE = eINSTANCE.getFormalParameterType();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_PARAMETER_TYPE__DATA_TYPE = eINSTANCE.getFormalParameterType_DataType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_PARAMETER_TYPE__DESCRIPTION = eINSTANCE.getFormalParameterType_Description();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_PARAMETER_TYPE__ID = eINSTANCE.getFormalParameterType_Id();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_PARAMETER_TYPE__INDEX = eINSTANCE.getFormalParameterType_Index();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_PARAMETER_TYPE__MODE = eINSTANCE.getFormalParameterType_Mode();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ImplementationTypeImpl <em>Implementation Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ImplementationTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getImplementationType()
		 * @generated
		 */
		EClass IMPLEMENTATION_TYPE = eINSTANCE.getImplementationType();

		/**
		 * The meta object literal for the '<em><b>No</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION_TYPE__NO = eINSTANCE.getImplementationType_No();

		/**
		 * The meta object literal for the '<em><b>Tool</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION_TYPE__TOOL = eINSTANCE.getImplementationType_Tool();

		/**
		 * The meta object literal for the '<em><b>Sub Flow</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION_TYPE__SUB_FLOW = eINSTANCE.getImplementationType_SubFlow();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.JoinTypeImpl <em>Join Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.JoinTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getJoinType()
		 * @generated
		 */
		EClass JOIN_TYPE = eINSTANCE.getJoinType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__TYPE = eINSTANCE.getJoinType_Type();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ListTypeTypeImpl <em>List Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ListTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getListTypeType()
		 * @generated
		 */
		EClass LIST_TYPE_TYPE = eINSTANCE.getListTypeType();

		/**
		 * The meta object literal for the '<em><b>Basic Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_TYPE_TYPE__BASIC_TYPE = eINSTANCE.getListTypeType_BasicType();

		/**
		 * The meta object literal for the '<em><b>Declared Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_TYPE_TYPE__DECLARED_TYPE = eINSTANCE.getListTypeType_DeclaredType();

		/**
		 * The meta object literal for the '<em><b>Schema Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_TYPE_TYPE__SCHEMA_TYPE = eINSTANCE.getListTypeType_SchemaType();

		/**
		 * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_TYPE_TYPE__EXTERNAL_REFERENCE = eINSTANCE.getListTypeType_ExternalReference();

		/**
		 * The meta object literal for the '<em><b>Record Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_TYPE_TYPE__RECORD_TYPE = eINSTANCE.getListTypeType_RecordType();

		/**
		 * The meta object literal for the '<em><b>Union Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_TYPE_TYPE__UNION_TYPE = eINSTANCE.getListTypeType_UnionType();

		/**
		 * The meta object literal for the '<em><b>Enumeration Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_TYPE_TYPE__ENUMERATION_TYPE = eINSTANCE.getListTypeType_EnumerationType();

		/**
		 * The meta object literal for the '<em><b>Array Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_TYPE_TYPE__ARRAY_TYPE = eINSTANCE.getListTypeType_ArrayType();

		/**
		 * The meta object literal for the '<em><b>List Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_TYPE_TYPE__LIST_TYPE = eINSTANCE.getListTypeType_ListType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ManualTypeImpl <em>Manual Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ManualTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getManualType()
		 * @generated
		 */
		EClass MANUAL_TYPE = eINSTANCE.getManualType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.MemberTypeImpl <em>Member Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.MemberTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getMemberType()
		 * @generated
		 */
		EClass MEMBER_TYPE = eINSTANCE.getMemberType();

		/**
		 * The meta object literal for the '<em><b>Basic Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_TYPE__BASIC_TYPE = eINSTANCE.getMemberType_BasicType();

		/**
		 * The meta object literal for the '<em><b>Declared Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_TYPE__DECLARED_TYPE = eINSTANCE.getMemberType_DeclaredType();

		/**
		 * The meta object literal for the '<em><b>Schema Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_TYPE__SCHEMA_TYPE = eINSTANCE.getMemberType_SchemaType();

		/**
		 * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_TYPE__EXTERNAL_REFERENCE = eINSTANCE.getMemberType_ExternalReference();

		/**
		 * The meta object literal for the '<em><b>Record Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_TYPE__RECORD_TYPE = eINSTANCE.getMemberType_RecordType();

		/**
		 * The meta object literal for the '<em><b>Union Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_TYPE__UNION_TYPE = eINSTANCE.getMemberType_UnionType();

		/**
		 * The meta object literal for the '<em><b>Enumeration Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_TYPE__ENUMERATION_TYPE = eINSTANCE.getMemberType_EnumerationType();

		/**
		 * The meta object literal for the '<em><b>Array Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_TYPE__ARRAY_TYPE = eINSTANCE.getMemberType_ArrayType();

		/**
		 * The meta object literal for the '<em><b>List Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_TYPE__LIST_TYPE = eINSTANCE.getMemberType_ListType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.NoTypeImpl <em>No Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.NoTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getNoType()
		 * @generated
		 */
		EClass NO_TYPE = eINSTANCE.getNoType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl <em>Package Header Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getPackageHeaderType()
		 * @generated
		 */
		EClass PACKAGE_HEADER_TYPE = eINSTANCE.getPackageHeaderType();

		/**
		 * The meta object literal for the '<em><b>XPDL Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_HEADER_TYPE__XPDL_VERSION = eINSTANCE.getPackageHeaderType_XPDLVersion();

		/**
		 * The meta object literal for the '<em><b>Vendor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_HEADER_TYPE__VENDOR = eINSTANCE.getPackageHeaderType_Vendor();

		/**
		 * The meta object literal for the '<em><b>Created</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_HEADER_TYPE__CREATED = eINSTANCE.getPackageHeaderType_Created();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_HEADER_TYPE__DESCRIPTION = eINSTANCE.getPackageHeaderType_Description();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_HEADER_TYPE__DOCUMENTATION = eINSTANCE.getPackageHeaderType_Documentation();

		/**
		 * The meta object literal for the '<em><b>Priority Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_HEADER_TYPE__PRIORITY_UNIT = eINSTANCE.getPackageHeaderType_PriorityUnit();

		/**
		 * The meta object literal for the '<em><b>Cost Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_HEADER_TYPE__COST_UNIT = eINSTANCE.getPackageHeaderType_CostUnit();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl <em>Package Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.PackageTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getPackageType()
		 * @generated
		 */
		EClass PACKAGE_TYPE = eINSTANCE.getPackageType();

		/**
		 * The meta object literal for the '<em><b>Package Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__PACKAGE_HEADER = eINSTANCE.getPackageType_PackageHeader();

		/**
		 * The meta object literal for the '<em><b>Redefinable Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__REDEFINABLE_HEADER = eINSTANCE.getPackageType_RedefinableHeader();

		/**
		 * The meta object literal for the '<em><b>Conformance Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__CONFORMANCE_CLASS = eINSTANCE.getPackageType_ConformanceClass();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__SCRIPT = eINSTANCE.getPackageType_Script();

		/**
		 * The meta object literal for the '<em><b>External Packages</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__EXTERNAL_PACKAGES = eINSTANCE.getPackageType_ExternalPackages();

		/**
		 * The meta object literal for the '<em><b>Type Declarations</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__TYPE_DECLARATIONS = eINSTANCE.getPackageType_TypeDeclarations();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__PARTICIPANTS = eINSTANCE.getPackageType_Participants();

		/**
		 * The meta object literal for the '<em><b>Applications</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__APPLICATIONS = eINSTANCE.getPackageType_Applications();

		/**
		 * The meta object literal for the '<em><b>Data Fields</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__DATA_FIELDS = eINSTANCE.getPackageType_DataFields();

		/**
		 * The meta object literal for the '<em><b>Workflow Processes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__WORKFLOW_PROCESSES = eINSTANCE.getPackageType_WorkflowProcesses();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getPackageType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_TYPE__ID = eINSTANCE.getPackageType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_TYPE__NAME = eINSTANCE.getPackageType_Name();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ParticipantsTypeImpl <em>Participants Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ParticipantsTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getParticipantsType()
		 * @generated
		 */
		EClass PARTICIPANTS_TYPE = eINSTANCE.getParticipantsType();

		/**
		 * The meta object literal for the '<em><b>Participant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANTS_TYPE__PARTICIPANT = eINSTANCE.getParticipantsType_Participant();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ParticipantTypeImpl <em>Participant Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ParticipantTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getParticipantType()
		 * @generated
		 */
		EClass PARTICIPANT_TYPE = eINSTANCE.getParticipantType();

		/**
		 * The meta object literal for the '<em><b>Participant Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT_TYPE__PARTICIPANT_TYPE = eINSTANCE.getParticipantType_ParticipantType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT_TYPE__DESCRIPTION = eINSTANCE.getParticipantType_Description();

		/**
		 * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT_TYPE__EXTERNAL_REFERENCE = eINSTANCE.getParticipantType_ExternalReference();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getParticipantType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT_TYPE__ID = eINSTANCE.getParticipantType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT_TYPE__NAME = eINSTANCE.getParticipantType_Name();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ParticipantTypeTypeImpl <em>Participant Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ParticipantTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getParticipantTypeType()
		 * @generated
		 */
		EClass PARTICIPANT_TYPE_TYPE = eINSTANCE.getParticipantTypeType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT_TYPE_TYPE__TYPE = eINSTANCE.getParticipantTypeType_Type();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl <em>Process Header Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ProcessHeaderTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getProcessHeaderType()
		 * @generated
		 */
		EClass PROCESS_HEADER_TYPE = eINSTANCE.getProcessHeaderType();

		/**
		 * The meta object literal for the '<em><b>Created</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_HEADER_TYPE__CREATED = eINSTANCE.getProcessHeaderType_Created();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_HEADER_TYPE__DESCRIPTION = eINSTANCE.getProcessHeaderType_Description();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_HEADER_TYPE__PRIORITY = eINSTANCE.getProcessHeaderType_Priority();

		/**
		 * The meta object literal for the '<em><b>Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_HEADER_TYPE__LIMIT = eINSTANCE.getProcessHeaderType_Limit();

		/**
		 * The meta object literal for the '<em><b>Valid From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_HEADER_TYPE__VALID_FROM = eINSTANCE.getProcessHeaderType_ValidFrom();

		/**
		 * The meta object literal for the '<em><b>Valid To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_HEADER_TYPE__VALID_TO = eINSTANCE.getProcessHeaderType_ValidTo();

		/**
		 * The meta object literal for the '<em><b>Time Estimation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_HEADER_TYPE__TIME_ESTIMATION = eINSTANCE.getProcessHeaderType_TimeEstimation();

		/**
		 * The meta object literal for the '<em><b>Duration Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_HEADER_TYPE__DURATION_UNIT = eINSTANCE.getProcessHeaderType_DurationUnit();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.RecordTypeTypeImpl <em>Record Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.RecordTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getRecordTypeType()
		 * @generated
		 */
		EClass RECORD_TYPE_TYPE = eINSTANCE.getRecordTypeType();

		/**
		 * The meta object literal for the '<em><b>Member</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECORD_TYPE_TYPE__MEMBER = eINSTANCE.getRecordTypeType_Member();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl <em>Redefinable Header Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getRedefinableHeaderType()
		 * @generated
		 */
		EClass REDEFINABLE_HEADER_TYPE = eINSTANCE.getRedefinableHeaderType();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REDEFINABLE_HEADER_TYPE__AUTHOR = eINSTANCE.getRedefinableHeaderType_Author();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REDEFINABLE_HEADER_TYPE__VERSION = eINSTANCE.getRedefinableHeaderType_Version();

		/**
		 * The meta object literal for the '<em><b>Codepage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REDEFINABLE_HEADER_TYPE__CODEPAGE = eINSTANCE.getRedefinableHeaderType_Codepage();

		/**
		 * The meta object literal for the '<em><b>Countrykey</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REDEFINABLE_HEADER_TYPE__COUNTRYKEY = eINSTANCE.getRedefinableHeaderType_Countrykey();

		/**
		 * The meta object literal for the '<em><b>Responsibles</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REDEFINABLE_HEADER_TYPE__RESPONSIBLES = eINSTANCE.getRedefinableHeaderType_Responsibles();

		/**
		 * The meta object literal for the '<em><b>Publication Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REDEFINABLE_HEADER_TYPE__PUBLICATION_STATUS = eINSTANCE.getRedefinableHeaderType_PublicationStatus();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ResponsiblesTypeImpl <em>Responsibles Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ResponsiblesTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getResponsiblesType()
		 * @generated
		 */
		EClass RESPONSIBLES_TYPE = eINSTANCE.getResponsiblesType();

		/**
		 * The meta object literal for the '<em><b>Responsible</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESPONSIBLES_TYPE__RESPONSIBLE = eINSTANCE.getResponsiblesType_Responsible();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.RouteTypeImpl <em>Route Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.RouteTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getRouteType()
		 * @generated
		 */
		EClass ROUTE_TYPE = eINSTANCE.getRouteType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.SchemaTypeTypeImpl <em>Schema Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.SchemaTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getSchemaTypeType()
		 * @generated
		 */
		EClass SCHEMA_TYPE_TYPE = eINSTANCE.getSchemaTypeType();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEMA_TYPE_TYPE__ANY = eINSTANCE.getSchemaTypeType_Any();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ScriptTypeImpl <em>Script Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ScriptTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getScriptType()
		 * @generated
		 */
		EClass SCRIPT_TYPE = eINSTANCE.getScriptType();

		/**
		 * The meta object literal for the '<em><b>Grammar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TYPE__GRAMMAR = eINSTANCE.getScriptType_Grammar();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TYPE__TYPE = eINSTANCE.getScriptType_Type();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TYPE__VERSION = eINSTANCE.getScriptType_Version();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.SimulationInformationTypeImpl <em>Simulation Information Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.SimulationInformationTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getSimulationInformationType()
		 * @generated
		 */
		EClass SIMULATION_INFORMATION_TYPE = eINSTANCE.getSimulationInformationType();

		/**
		 * The meta object literal for the '<em><b>Cost</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_INFORMATION_TYPE__COST = eINSTANCE.getSimulationInformationType_Cost();

		/**
		 * The meta object literal for the '<em><b>Time Estimation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION = eINSTANCE.getSimulationInformationType_TimeEstimation();

		/**
		 * The meta object literal for the '<em><b>Instantiation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_INFORMATION_TYPE__INSTANTIATION = eINSTANCE.getSimulationInformationType_Instantiation();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.SplitTypeImpl <em>Split Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.SplitTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getSplitType()
		 * @generated
		 */
		EClass SPLIT_TYPE = eINSTANCE.getSplitType();

		/**
		 * The meta object literal for the '<em><b>Transition Refs</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPLIT_TYPE__TRANSITION_REFS = eINSTANCE.getSplitType_TransitionRefs();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_TYPE__TYPE = eINSTANCE.getSplitType_Type();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.StartModeTypeImpl <em>Start Mode Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.StartModeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getStartModeType()
		 * @generated
		 */
		EClass START_MODE_TYPE = eINSTANCE.getStartModeType();

		/**
		 * The meta object literal for the '<em><b>Automatic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_MODE_TYPE__AUTOMATIC = eINSTANCE.getStartModeType_Automatic();

		/**
		 * The meta object literal for the '<em><b>Manual</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_MODE_TYPE__MANUAL = eINSTANCE.getStartModeType_Manual();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.SubFlowTypeImpl <em>Sub Flow Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.SubFlowTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getSubFlowType()
		 * @generated
		 */
		EClass SUB_FLOW_TYPE = eINSTANCE.getSubFlowType();

		/**
		 * The meta object literal for the '<em><b>Actual Parameters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_FLOW_TYPE__ACTUAL_PARAMETERS = eINSTANCE.getSubFlowType_ActualParameters();

		/**
		 * The meta object literal for the '<em><b>Execution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_FLOW_TYPE__EXECUTION = eINSTANCE.getSubFlowType_Execution();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_FLOW_TYPE__ID = eINSTANCE.getSubFlowType_Id();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.TimeEstimationTypeImpl <em>Time Estimation Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.TimeEstimationTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTimeEstimationType()
		 * @generated
		 */
		EClass TIME_ESTIMATION_TYPE = eINSTANCE.getTimeEstimationType();

		/**
		 * The meta object literal for the '<em><b>Waiting Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_ESTIMATION_TYPE__WAITING_TIME = eINSTANCE.getTimeEstimationType_WaitingTime();

		/**
		 * The meta object literal for the '<em><b>Working Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_ESTIMATION_TYPE__WORKING_TIME = eINSTANCE.getTimeEstimationType_WorkingTime();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_ESTIMATION_TYPE__DURATION = eINSTANCE.getTimeEstimationType_Duration();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.ToolTypeImpl <em>Tool Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.ToolTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getToolType()
		 * @generated
		 */
		EClass TOOL_TYPE = eINSTANCE.getToolType();

		/**
		 * The meta object literal for the '<em><b>Actual Parameters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOOL_TYPE__ACTUAL_PARAMETERS = eINSTANCE.getToolType_ActualParameters();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOOL_TYPE__DESCRIPTION = eINSTANCE.getToolType_Description();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOOL_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getToolType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOOL_TYPE__ID = eINSTANCE.getToolType_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOOL_TYPE__TYPE = eINSTANCE.getToolType_Type();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.TransitionRefsTypeImpl <em>Transition Refs Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.TransitionRefsTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionRefsType()
		 * @generated
		 */
		EClass TRANSITION_REFS_TYPE = eINSTANCE.getTransitionRefsType();

		/**
		 * The meta object literal for the '<em><b>Transition Ref</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_REFS_TYPE__TRANSITION_REF = eINSTANCE.getTransitionRefsType_TransitionRef();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.TransitionRefTypeImpl <em>Transition Ref Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.TransitionRefTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionRefType()
		 * @generated
		 */
		EClass TRANSITION_REF_TYPE = eINSTANCE.getTransitionRefType();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_REF_TYPE__ID = eINSTANCE.getTransitionRefType_Id();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.TransitionRestrictionsTypeImpl <em>Transition Restrictions Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.TransitionRestrictionsTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionRestrictionsType()
		 * @generated
		 */
		EClass TRANSITION_RESTRICTIONS_TYPE = eINSTANCE.getTransitionRestrictionsType();

		/**
		 * The meta object literal for the '<em><b>Transition Restriction</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_RESTRICTIONS_TYPE__TRANSITION_RESTRICTION = eINSTANCE.getTransitionRestrictionsType_TransitionRestriction();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.TransitionRestrictionTypeImpl <em>Transition Restriction Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.TransitionRestrictionTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionRestrictionType()
		 * @generated
		 */
		EClass TRANSITION_RESTRICTION_TYPE = eINSTANCE.getTransitionRestrictionType();

		/**
		 * The meta object literal for the '<em><b>Join</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_RESTRICTION_TYPE__JOIN = eINSTANCE.getTransitionRestrictionType_Join();

		/**
		 * The meta object literal for the '<em><b>Split</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_RESTRICTION_TYPE__SPLIT = eINSTANCE.getTransitionRestrictionType_Split();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.TransitionsTypeImpl <em>Transitions Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.TransitionsTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionsType()
		 * @generated
		 */
		EClass TRANSITIONS_TYPE = eINSTANCE.getTransitionsType();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITIONS_TYPE__TRANSITION = eINSTANCE.getTransitionsType_Transition();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.TransitionTypeImpl <em>Transition Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.TransitionTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTransitionType()
		 * @generated
		 */
		EClass TRANSITION_TYPE = eINSTANCE.getTransitionType();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TYPE__CONDITION = eINSTANCE.getTransitionType_Condition();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_TYPE__DESCRIPTION = eINSTANCE.getTransitionType_Description();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getTransitionType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_TYPE__FROM = eINSTANCE.getTransitionType_From();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_TYPE__ID = eINSTANCE.getTransitionType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_TYPE__NAME = eINSTANCE.getTransitionType_Name();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_TYPE__TO = eINSTANCE.getTransitionType_To();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.TypeDeclarationsTypeImpl <em>Type Declarations Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.TypeDeclarationsTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeDeclarationsType()
		 * @generated
		 */
		EClass TYPE_DECLARATIONS_TYPE = eINSTANCE.getTypeDeclarationsType();

		/**
		 * The meta object literal for the '<em><b>Type Declaration</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION = eINSTANCE.getTypeDeclarationsType_TypeDeclaration();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.TypeDeclarationTypeImpl <em>Type Declaration Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.TypeDeclarationTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeDeclarationType()
		 * @generated
		 */
		EClass TYPE_DECLARATION_TYPE = eINSTANCE.getTypeDeclarationType();

		/**
		 * The meta object literal for the '<em><b>Basic Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__BASIC_TYPE = eINSTANCE.getTypeDeclarationType_BasicType();

		/**
		 * The meta object literal for the '<em><b>Declared Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__DECLARED_TYPE = eINSTANCE.getTypeDeclarationType_DeclaredType();

		/**
		 * The meta object literal for the '<em><b>Schema Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__SCHEMA_TYPE = eINSTANCE.getTypeDeclarationType_SchemaType();

		/**
		 * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE = eINSTANCE.getTypeDeclarationType_ExternalReference();

		/**
		 * The meta object literal for the '<em><b>Record Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__RECORD_TYPE = eINSTANCE.getTypeDeclarationType_RecordType();

		/**
		 * The meta object literal for the '<em><b>Union Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__UNION_TYPE = eINSTANCE.getTypeDeclarationType_UnionType();

		/**
		 * The meta object literal for the '<em><b>Enumeration Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__ENUMERATION_TYPE = eINSTANCE.getTypeDeclarationType_EnumerationType();

		/**
		 * The meta object literal for the '<em><b>Array Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__ARRAY_TYPE = eINSTANCE.getTypeDeclarationType_ArrayType();

		/**
		 * The meta object literal for the '<em><b>List Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__LIST_TYPE = eINSTANCE.getTypeDeclarationType_ListType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_DECLARATION_TYPE__DESCRIPTION = eINSTANCE.getTypeDeclarationType_Description();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getTypeDeclarationType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_DECLARATION_TYPE__ID = eINSTANCE.getTypeDeclarationType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_DECLARATION_TYPE__NAME = eINSTANCE.getTypeDeclarationType_Name();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.UnionTypeTypeImpl <em>Union Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.UnionTypeTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getUnionTypeType()
		 * @generated
		 */
		EClass UNION_TYPE_TYPE = eINSTANCE.getUnionTypeType();

		/**
		 * The meta object literal for the '<em><b>Member</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_TYPE_TYPE__MEMBER = eINSTANCE.getUnionTypeType_Member();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessesTypeImpl <em>Workflow Processes Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.WorkflowProcessesTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getWorkflowProcessesType()
		 * @generated
		 */
		EClass WORKFLOW_PROCESSES_TYPE = eINSTANCE.getWorkflowProcessesType();

		/**
		 * The meta object literal for the '<em><b>Workflow Process</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESSES_TYPE__WORKFLOW_PROCESS = eINSTANCE.getWorkflowProcessesType_WorkflowProcess();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl <em>Workflow Process Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getWorkflowProcessType()
		 * @generated
		 */
		EClass WORKFLOW_PROCESS_TYPE = eINSTANCE.getWorkflowProcessType();

		/**
		 * The meta object literal for the '<em><b>Process Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__PROCESS_HEADER = eINSTANCE.getWorkflowProcessType_ProcessHeader();

		/**
		 * The meta object literal for the '<em><b>Redefinable Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER = eINSTANCE.getWorkflowProcessType_RedefinableHeader();

		/**
		 * The meta object literal for the '<em><b>Formal Parameters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS = eINSTANCE.getWorkflowProcessType_FormalParameters();

		/**
		 * The meta object literal for the '<em><b>Data Fields</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__DATA_FIELDS = eINSTANCE.getWorkflowProcessType_DataFields();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__PARTICIPANTS = eINSTANCE.getWorkflowProcessType_Participants();

		/**
		 * The meta object literal for the '<em><b>Applications</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__APPLICATIONS = eINSTANCE.getWorkflowProcessType_Applications();

		/**
		 * The meta object literal for the '<em><b>Activity Sets</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS = eINSTANCE.getWorkflowProcessType_ActivitySets();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__ACTIVITIES = eINSTANCE.getWorkflowProcessType_Activities();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__TRANSITIONS = eINSTANCE.getWorkflowProcessType_Transitions();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES = eINSTANCE.getWorkflowProcessType_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Access Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKFLOW_PROCESS_TYPE__ACCESS_LEVEL = eINSTANCE.getWorkflowProcessType_AccessLevel();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKFLOW_PROCESS_TYPE__ID = eINSTANCE.getWorkflowProcessType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKFLOW_PROCESS_TYPE__NAME = eINSTANCE.getWorkflowProcessType_Name();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.impl.XpressionTypeImpl <em>Xpression Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.impl.XpressionTypeImpl
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getXpressionType()
		 * @generated
		 */
		EClass XPRESSION_TYPE = eINSTANCE.getXpressionType();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XPRESSION_TYPE__MIXED = eINSTANCE.getXpressionType_Mixed();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XPRESSION_TYPE__GROUP = eINSTANCE.getXpressionType_Group();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XPRESSION_TYPE__ANY = eINSTANCE.getXpressionType_Any();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.AccessLevelType <em>Access Level Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.AccessLevelType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getAccessLevelType()
		 * @generated
		 */
		EEnum ACCESS_LEVEL_TYPE = eINSTANCE.getAccessLevelType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.DurationUnitType <em>Duration Unit Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.DurationUnitType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDurationUnitType()
		 * @generated
		 */
		EEnum DURATION_UNIT_TYPE = eINSTANCE.getDurationUnitType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.ExecutionType <em>Execution Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.ExecutionType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExecutionType()
		 * @generated
		 */
		EEnum EXECUTION_TYPE = eINSTANCE.getExecutionType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.ExecutionType1 <em>Execution Type1</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.ExecutionType1
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExecutionType1()
		 * @generated
		 */
		EEnum EXECUTION_TYPE1 = eINSTANCE.getExecutionType1();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.GraphConformanceType <em>Graph Conformance Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.GraphConformanceType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getGraphConformanceType()
		 * @generated
		 */
		EEnum GRAPH_CONFORMANCE_TYPE = eINSTANCE.getGraphConformanceType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.InstantiationType <em>Instantiation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.InstantiationType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getInstantiationType()
		 * @generated
		 */
		EEnum INSTANTIATION_TYPE = eINSTANCE.getInstantiationType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.IsArrayType <em>Is Array Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.IsArrayType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getIsArrayType()
		 * @generated
		 */
		EEnum IS_ARRAY_TYPE = eINSTANCE.getIsArrayType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.ModeType <em>Mode Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.ModeType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getModeType()
		 * @generated
		 */
		EEnum MODE_TYPE = eINSTANCE.getModeType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.PublicationStatusType <em>Publication Status Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.PublicationStatusType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getPublicationStatusType()
		 * @generated
		 */
		EEnum PUBLICATION_STATUS_TYPE = eINSTANCE.getPublicationStatusType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.TypeType <em>Type Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType()
		 * @generated
		 */
		EEnum TYPE_TYPE = eINSTANCE.getTypeType();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.TypeType1 <em>Type Type1</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType1
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType1()
		 * @generated
		 */
		EEnum TYPE_TYPE1 = eINSTANCE.getTypeType1();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.TypeType2 <em>Type Type2</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType2
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType2()
		 * @generated
		 */
		EEnum TYPE_TYPE2 = eINSTANCE.getTypeType2();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.TypeType3 <em>Type Type3</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType3
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType3()
		 * @generated
		 */
		EEnum TYPE_TYPE3 = eINSTANCE.getTypeType3();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.TypeType4 <em>Type Type4</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType4
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType4()
		 * @generated
		 */
		EEnum TYPE_TYPE4 = eINSTANCE.getTypeType4();

		/**
		 * The meta object literal for the '{@link org.wfmc._2002.xpdl1.TypeType5 <em>Type Type5</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType5
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeType5()
		 * @generated
		 */
		EEnum TYPE_TYPE5 = eINSTANCE.getTypeType5();

		/**
		 * The meta object literal for the '<em>Access Level Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.AccessLevelType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getAccessLevelTypeObject()
		 * @generated
		 */
		EDataType ACCESS_LEVEL_TYPE_OBJECT = eINSTANCE.getAccessLevelTypeObject();

		/**
		 * The meta object literal for the '<em>Duration Unit Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.DurationUnitType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getDurationUnitTypeObject()
		 * @generated
		 */
		EDataType DURATION_UNIT_TYPE_OBJECT = eINSTANCE.getDurationUnitTypeObject();

		/**
		 * The meta object literal for the '<em>Execution Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.ExecutionType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExecutionTypeObject()
		 * @generated
		 */
		EDataType EXECUTION_TYPE_OBJECT = eINSTANCE.getExecutionTypeObject();

		/**
		 * The meta object literal for the '<em>Execution Type Object1</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.ExecutionType1
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getExecutionTypeObject1()
		 * @generated
		 */
		EDataType EXECUTION_TYPE_OBJECT1 = eINSTANCE.getExecutionTypeObject1();

		/**
		 * The meta object literal for the '<em>Graph Conformance Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.GraphConformanceType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getGraphConformanceTypeObject()
		 * @generated
		 */
		EDataType GRAPH_CONFORMANCE_TYPE_OBJECT = eINSTANCE.getGraphConformanceTypeObject();

		/**
		 * The meta object literal for the '<em>Instantiation Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.InstantiationType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getInstantiationTypeObject()
		 * @generated
		 */
		EDataType INSTANTIATION_TYPE_OBJECT = eINSTANCE.getInstantiationTypeObject();

		/**
		 * The meta object literal for the '<em>Is Array Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.IsArrayType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getIsArrayTypeObject()
		 * @generated
		 */
		EDataType IS_ARRAY_TYPE_OBJECT = eINSTANCE.getIsArrayTypeObject();

		/**
		 * The meta object literal for the '<em>Mode Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.ModeType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getModeTypeObject()
		 * @generated
		 */
		EDataType MODE_TYPE_OBJECT = eINSTANCE.getModeTypeObject();

		/**
		 * The meta object literal for the '<em>Publication Status Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.PublicationStatusType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getPublicationStatusTypeObject()
		 * @generated
		 */
		EDataType PUBLICATION_STATUS_TYPE_OBJECT = eINSTANCE.getPublicationStatusTypeObject();

		/**
		 * The meta object literal for the '<em>Type Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType4
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject()
		 * @generated
		 */
		EDataType TYPE_TYPE_OBJECT = eINSTANCE.getTypeTypeObject();

		/**
		 * The meta object literal for the '<em>Type Type Object1</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType1
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject1()
		 * @generated
		 */
		EDataType TYPE_TYPE_OBJECT1 = eINSTANCE.getTypeTypeObject1();

		/**
		 * The meta object literal for the '<em>Type Type Object2</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType3
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject2()
		 * @generated
		 */
		EDataType TYPE_TYPE_OBJECT2 = eINSTANCE.getTypeTypeObject2();

		/**
		 * The meta object literal for the '<em>Type Type Object3</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType2
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject3()
		 * @generated
		 */
		EDataType TYPE_TYPE_OBJECT3 = eINSTANCE.getTypeTypeObject3();

		/**
		 * The meta object literal for the '<em>Type Type Object4</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject4()
		 * @generated
		 */
		EDataType TYPE_TYPE_OBJECT4 = eINSTANCE.getTypeTypeObject4();

		/**
		 * The meta object literal for the '<em>Type Type Object5</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.wfmc._2002.xpdl1.TypeType5
		 * @see org.wfmc._2002.xpdl1.impl.Xpdl1PackageImpl#getTypeTypeObject5()
		 * @generated
		 */
		EDataType TYPE_TYPE_OBJECT5 = eINSTANCE.getTypeTypeObject5();

	}

} //Xpdl1Package
