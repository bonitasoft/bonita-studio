/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.wfmc._2002.xpdl1.AccessLevelType;
import org.wfmc._2002.xpdl1.ActivitiesType;
import org.wfmc._2002.xpdl1.ActivitySetType;
import org.wfmc._2002.xpdl1.ActivitySetsType;
import org.wfmc._2002.xpdl1.ActivityType;
import org.wfmc._2002.xpdl1.ActualParametersType;
import org.wfmc._2002.xpdl1.ApplicationType;
import org.wfmc._2002.xpdl1.ApplicationsType;
import org.wfmc._2002.xpdl1.ArrayTypeType;
import org.wfmc._2002.xpdl1.AutomaticType;
import org.wfmc._2002.xpdl1.BasicTypeType;
import org.wfmc._2002.xpdl1.BlockActivityType;
import org.wfmc._2002.xpdl1.ConditionType;
import org.wfmc._2002.xpdl1.ConformanceClassType;
import org.wfmc._2002.xpdl1.DataFieldType;
import org.wfmc._2002.xpdl1.DataFieldsType;
import org.wfmc._2002.xpdl1.DataTypeType;
import org.wfmc._2002.xpdl1.DeadlineType;
import org.wfmc._2002.xpdl1.DeclaredTypeType;
import org.wfmc._2002.xpdl1.DocumentRoot;
import org.wfmc._2002.xpdl1.DurationUnitType;
import org.wfmc._2002.xpdl1.EnumerationTypeType;
import org.wfmc._2002.xpdl1.EnumerationValueType;
import org.wfmc._2002.xpdl1.ExecutionType;
import org.wfmc._2002.xpdl1.ExecutionType1;
import org.wfmc._2002.xpdl1.ExtendedAttributeType;
import org.wfmc._2002.xpdl1.ExtendedAttributesType;
import org.wfmc._2002.xpdl1.ExternalPackageType;
import org.wfmc._2002.xpdl1.ExternalPackagesType;
import org.wfmc._2002.xpdl1.ExternalReferenceType;
import org.wfmc._2002.xpdl1.FinishModeType;
import org.wfmc._2002.xpdl1.FormalParameterType;
import org.wfmc._2002.xpdl1.FormalParametersType;
import org.wfmc._2002.xpdl1.GraphConformanceType;
import org.wfmc._2002.xpdl1.ImplementationType;
import org.wfmc._2002.xpdl1.InstantiationType;
import org.wfmc._2002.xpdl1.IsArrayType;
import org.wfmc._2002.xpdl1.JoinType;
import org.wfmc._2002.xpdl1.ListTypeType;
import org.wfmc._2002.xpdl1.ManualType;
import org.wfmc._2002.xpdl1.MemberType;
import org.wfmc._2002.xpdl1.ModeType;
import org.wfmc._2002.xpdl1.NoType;
import org.wfmc._2002.xpdl1.PackageHeaderType;
import org.wfmc._2002.xpdl1.PackageType;
import org.wfmc._2002.xpdl1.ParticipantType;
import org.wfmc._2002.xpdl1.ParticipantTypeType;
import org.wfmc._2002.xpdl1.ParticipantsType;
import org.wfmc._2002.xpdl1.ProcessHeaderType;
import org.wfmc._2002.xpdl1.PublicationStatusType;
import org.wfmc._2002.xpdl1.RecordTypeType;
import org.wfmc._2002.xpdl1.RedefinableHeaderType;
import org.wfmc._2002.xpdl1.ResponsiblesType;
import org.wfmc._2002.xpdl1.RouteType;
import org.wfmc._2002.xpdl1.SchemaTypeType;
import org.wfmc._2002.xpdl1.ScriptType;
import org.wfmc._2002.xpdl1.SimulationInformationType;
import org.wfmc._2002.xpdl1.SplitType;
import org.wfmc._2002.xpdl1.StartModeType;
import org.wfmc._2002.xpdl1.SubFlowType;
import org.wfmc._2002.xpdl1.TimeEstimationType;
import org.wfmc._2002.xpdl1.ToolType;
import org.wfmc._2002.xpdl1.TransitionRefType;
import org.wfmc._2002.xpdl1.TransitionRefsType;
import org.wfmc._2002.xpdl1.TransitionRestrictionType;
import org.wfmc._2002.xpdl1.TransitionRestrictionsType;
import org.wfmc._2002.xpdl1.TransitionType;
import org.wfmc._2002.xpdl1.TransitionsType;
import org.wfmc._2002.xpdl1.TypeDeclarationType;
import org.wfmc._2002.xpdl1.TypeDeclarationsType;
import org.wfmc._2002.xpdl1.TypeType;
import org.wfmc._2002.xpdl1.TypeType1;
import org.wfmc._2002.xpdl1.TypeType2;
import org.wfmc._2002.xpdl1.TypeType3;
import org.wfmc._2002.xpdl1.TypeType4;
import org.wfmc._2002.xpdl1.TypeType5;
import org.wfmc._2002.xpdl1.UnionTypeType;
import org.wfmc._2002.xpdl1.WorkflowProcessType;
import org.wfmc._2002.xpdl1.WorkflowProcessesType;
import org.wfmc._2002.xpdl1.Xpdl1Factory;
import org.wfmc._2002.xpdl1.Xpdl1Package;
import org.wfmc._2002.xpdl1.XpressionType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Xpdl1FactoryImpl extends EFactoryImpl implements Xpdl1Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Xpdl1Factory init() {
		try {
			Xpdl1Factory theXpdl1Factory = (Xpdl1Factory)EPackage.Registry.INSTANCE.getEFactory("http://www.wfmc.org/2002/XPDL1.0"); 
			if (theXpdl1Factory != null) {
				return theXpdl1Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Xpdl1FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Xpdl1FactoryImpl() {
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
			case Xpdl1Package.ACTIVITIES_TYPE: return createActivitiesType();
			case Xpdl1Package.ACTIVITY_SETS_TYPE: return createActivitySetsType();
			case Xpdl1Package.ACTIVITY_SET_TYPE: return createActivitySetType();
			case Xpdl1Package.ACTIVITY_TYPE: return createActivityType();
			case Xpdl1Package.ACTUAL_PARAMETERS_TYPE: return createActualParametersType();
			case Xpdl1Package.APPLICATIONS_TYPE: return createApplicationsType();
			case Xpdl1Package.APPLICATION_TYPE: return createApplicationType();
			case Xpdl1Package.ARRAY_TYPE_TYPE: return createArrayTypeType();
			case Xpdl1Package.AUTOMATIC_TYPE: return createAutomaticType();
			case Xpdl1Package.BASIC_TYPE_TYPE: return createBasicTypeType();
			case Xpdl1Package.BLOCK_ACTIVITY_TYPE: return createBlockActivityType();
			case Xpdl1Package.CONDITION_TYPE: return createConditionType();
			case Xpdl1Package.CONFORMANCE_CLASS_TYPE: return createConformanceClassType();
			case Xpdl1Package.DATA_FIELDS_TYPE: return createDataFieldsType();
			case Xpdl1Package.DATA_FIELD_TYPE: return createDataFieldType();
			case Xpdl1Package.DATA_TYPE_TYPE: return createDataTypeType();
			case Xpdl1Package.DEADLINE_TYPE: return createDeadlineType();
			case Xpdl1Package.DECLARED_TYPE_TYPE: return createDeclaredTypeType();
			case Xpdl1Package.DOCUMENT_ROOT: return createDocumentRoot();
			case Xpdl1Package.ENUMERATION_TYPE_TYPE: return createEnumerationTypeType();
			case Xpdl1Package.ENUMERATION_VALUE_TYPE: return createEnumerationValueType();
			case Xpdl1Package.EXTENDED_ATTRIBUTES_TYPE: return createExtendedAttributesType();
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE: return createExtendedAttributeType();
			case Xpdl1Package.EXTERNAL_PACKAGES_TYPE: return createExternalPackagesType();
			case Xpdl1Package.EXTERNAL_PACKAGE_TYPE: return createExternalPackageType();
			case Xpdl1Package.EXTERNAL_REFERENCE_TYPE: return createExternalReferenceType();
			case Xpdl1Package.FINISH_MODE_TYPE: return createFinishModeType();
			case Xpdl1Package.FORMAL_PARAMETERS_TYPE: return createFormalParametersType();
			case Xpdl1Package.FORMAL_PARAMETER_TYPE: return createFormalParameterType();
			case Xpdl1Package.IMPLEMENTATION_TYPE: return createImplementationType();
			case Xpdl1Package.JOIN_TYPE: return createJoinType();
			case Xpdl1Package.LIST_TYPE_TYPE: return createListTypeType();
			case Xpdl1Package.MANUAL_TYPE: return createManualType();
			case Xpdl1Package.MEMBER_TYPE: return createMemberType();
			case Xpdl1Package.NO_TYPE: return createNoType();
			case Xpdl1Package.PACKAGE_HEADER_TYPE: return createPackageHeaderType();
			case Xpdl1Package.PACKAGE_TYPE: return createPackageType();
			case Xpdl1Package.PARTICIPANTS_TYPE: return createParticipantsType();
			case Xpdl1Package.PARTICIPANT_TYPE: return createParticipantType();
			case Xpdl1Package.PARTICIPANT_TYPE_TYPE: return createParticipantTypeType();
			case Xpdl1Package.PROCESS_HEADER_TYPE: return createProcessHeaderType();
			case Xpdl1Package.RECORD_TYPE_TYPE: return createRecordTypeType();
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE: return createRedefinableHeaderType();
			case Xpdl1Package.RESPONSIBLES_TYPE: return createResponsiblesType();
			case Xpdl1Package.ROUTE_TYPE: return createRouteType();
			case Xpdl1Package.SCHEMA_TYPE_TYPE: return createSchemaTypeType();
			case Xpdl1Package.SCRIPT_TYPE: return createScriptType();
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE: return createSimulationInformationType();
			case Xpdl1Package.SPLIT_TYPE: return createSplitType();
			case Xpdl1Package.START_MODE_TYPE: return createStartModeType();
			case Xpdl1Package.SUB_FLOW_TYPE: return createSubFlowType();
			case Xpdl1Package.TIME_ESTIMATION_TYPE: return createTimeEstimationType();
			case Xpdl1Package.TOOL_TYPE: return createToolType();
			case Xpdl1Package.TRANSITION_REFS_TYPE: return createTransitionRefsType();
			case Xpdl1Package.TRANSITION_REF_TYPE: return createTransitionRefType();
			case Xpdl1Package.TRANSITION_RESTRICTIONS_TYPE: return createTransitionRestrictionsType();
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE: return createTransitionRestrictionType();
			case Xpdl1Package.TRANSITIONS_TYPE: return createTransitionsType();
			case Xpdl1Package.TRANSITION_TYPE: return createTransitionType();
			case Xpdl1Package.TYPE_DECLARATIONS_TYPE: return createTypeDeclarationsType();
			case Xpdl1Package.TYPE_DECLARATION_TYPE: return createTypeDeclarationType();
			case Xpdl1Package.UNION_TYPE_TYPE: return createUnionTypeType();
			case Xpdl1Package.WORKFLOW_PROCESSES_TYPE: return createWorkflowProcessesType();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE: return createWorkflowProcessType();
			case Xpdl1Package.XPRESSION_TYPE: return createXpressionType();
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
			case Xpdl1Package.ACCESS_LEVEL_TYPE:
				return createAccessLevelTypeFromString(eDataType, initialValue);
			case Xpdl1Package.DURATION_UNIT_TYPE:
				return createDurationUnitTypeFromString(eDataType, initialValue);
			case Xpdl1Package.EXECUTION_TYPE:
				return createExecutionTypeFromString(eDataType, initialValue);
			case Xpdl1Package.EXECUTION_TYPE1:
				return createExecutionType1FromString(eDataType, initialValue);
			case Xpdl1Package.GRAPH_CONFORMANCE_TYPE:
				return createGraphConformanceTypeFromString(eDataType, initialValue);
			case Xpdl1Package.INSTANTIATION_TYPE:
				return createInstantiationTypeFromString(eDataType, initialValue);
			case Xpdl1Package.IS_ARRAY_TYPE:
				return createIsArrayTypeFromString(eDataType, initialValue);
			case Xpdl1Package.MODE_TYPE:
				return createModeTypeFromString(eDataType, initialValue);
			case Xpdl1Package.PUBLICATION_STATUS_TYPE:
				return createPublicationStatusTypeFromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE:
				return createTypeTypeFromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE1:
				return createTypeType1FromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE2:
				return createTypeType2FromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE3:
				return createTypeType3FromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE4:
				return createTypeType4FromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE5:
				return createTypeType5FromString(eDataType, initialValue);
			case Xpdl1Package.ACCESS_LEVEL_TYPE_OBJECT:
				return createAccessLevelTypeObjectFromString(eDataType, initialValue);
			case Xpdl1Package.DURATION_UNIT_TYPE_OBJECT:
				return createDurationUnitTypeObjectFromString(eDataType, initialValue);
			case Xpdl1Package.EXECUTION_TYPE_OBJECT:
				return createExecutionTypeObjectFromString(eDataType, initialValue);
			case Xpdl1Package.EXECUTION_TYPE_OBJECT1:
				return createExecutionTypeObject1FromString(eDataType, initialValue);
			case Xpdl1Package.GRAPH_CONFORMANCE_TYPE_OBJECT:
				return createGraphConformanceTypeObjectFromString(eDataType, initialValue);
			case Xpdl1Package.INSTANTIATION_TYPE_OBJECT:
				return createInstantiationTypeObjectFromString(eDataType, initialValue);
			case Xpdl1Package.IS_ARRAY_TYPE_OBJECT:
				return createIsArrayTypeObjectFromString(eDataType, initialValue);
			case Xpdl1Package.MODE_TYPE_OBJECT:
				return createModeTypeObjectFromString(eDataType, initialValue);
			case Xpdl1Package.PUBLICATION_STATUS_TYPE_OBJECT:
				return createPublicationStatusTypeObjectFromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT:
				return createTypeTypeObjectFromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT1:
				return createTypeTypeObject1FromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT2:
				return createTypeTypeObject2FromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT3:
				return createTypeTypeObject3FromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT4:
				return createTypeTypeObject4FromString(eDataType, initialValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT5:
				return createTypeTypeObject5FromString(eDataType, initialValue);
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
			case Xpdl1Package.ACCESS_LEVEL_TYPE:
				return convertAccessLevelTypeToString(eDataType, instanceValue);
			case Xpdl1Package.DURATION_UNIT_TYPE:
				return convertDurationUnitTypeToString(eDataType, instanceValue);
			case Xpdl1Package.EXECUTION_TYPE:
				return convertExecutionTypeToString(eDataType, instanceValue);
			case Xpdl1Package.EXECUTION_TYPE1:
				return convertExecutionType1ToString(eDataType, instanceValue);
			case Xpdl1Package.GRAPH_CONFORMANCE_TYPE:
				return convertGraphConformanceTypeToString(eDataType, instanceValue);
			case Xpdl1Package.INSTANTIATION_TYPE:
				return convertInstantiationTypeToString(eDataType, instanceValue);
			case Xpdl1Package.IS_ARRAY_TYPE:
				return convertIsArrayTypeToString(eDataType, instanceValue);
			case Xpdl1Package.MODE_TYPE:
				return convertModeTypeToString(eDataType, instanceValue);
			case Xpdl1Package.PUBLICATION_STATUS_TYPE:
				return convertPublicationStatusTypeToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE:
				return convertTypeTypeToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE1:
				return convertTypeType1ToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE2:
				return convertTypeType2ToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE3:
				return convertTypeType3ToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE4:
				return convertTypeType4ToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE5:
				return convertTypeType5ToString(eDataType, instanceValue);
			case Xpdl1Package.ACCESS_LEVEL_TYPE_OBJECT:
				return convertAccessLevelTypeObjectToString(eDataType, instanceValue);
			case Xpdl1Package.DURATION_UNIT_TYPE_OBJECT:
				return convertDurationUnitTypeObjectToString(eDataType, instanceValue);
			case Xpdl1Package.EXECUTION_TYPE_OBJECT:
				return convertExecutionTypeObjectToString(eDataType, instanceValue);
			case Xpdl1Package.EXECUTION_TYPE_OBJECT1:
				return convertExecutionTypeObject1ToString(eDataType, instanceValue);
			case Xpdl1Package.GRAPH_CONFORMANCE_TYPE_OBJECT:
				return convertGraphConformanceTypeObjectToString(eDataType, instanceValue);
			case Xpdl1Package.INSTANTIATION_TYPE_OBJECT:
				return convertInstantiationTypeObjectToString(eDataType, instanceValue);
			case Xpdl1Package.IS_ARRAY_TYPE_OBJECT:
				return convertIsArrayTypeObjectToString(eDataType, instanceValue);
			case Xpdl1Package.MODE_TYPE_OBJECT:
				return convertModeTypeObjectToString(eDataType, instanceValue);
			case Xpdl1Package.PUBLICATION_STATUS_TYPE_OBJECT:
				return convertPublicationStatusTypeObjectToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT:
				return convertTypeTypeObjectToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT1:
				return convertTypeTypeObject1ToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT2:
				return convertTypeTypeObject2ToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT3:
				return convertTypeTypeObject3ToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT4:
				return convertTypeTypeObject4ToString(eDataType, instanceValue);
			case Xpdl1Package.TYPE_TYPE_OBJECT5:
				return convertTypeTypeObject5ToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitiesType createActivitiesType() {
		ActivitiesTypeImpl activitiesType = new ActivitiesTypeImpl();
		return activitiesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitySetsType createActivitySetsType() {
		ActivitySetsTypeImpl activitySetsType = new ActivitySetsTypeImpl();
		return activitySetsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitySetType createActivitySetType() {
		ActivitySetTypeImpl activitySetType = new ActivitySetTypeImpl();
		return activitySetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityType createActivityType() {
		ActivityTypeImpl activityType = new ActivityTypeImpl();
		return activityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActualParametersType createActualParametersType() {
		ActualParametersTypeImpl actualParametersType = new ActualParametersTypeImpl();
		return actualParametersType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationsType createApplicationsType() {
		ApplicationsTypeImpl applicationsType = new ApplicationsTypeImpl();
		return applicationsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationType createApplicationType() {
		ApplicationTypeImpl applicationType = new ApplicationTypeImpl();
		return applicationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayTypeType createArrayTypeType() {
		ArrayTypeTypeImpl arrayTypeType = new ArrayTypeTypeImpl();
		return arrayTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AutomaticType createAutomaticType() {
		AutomaticTypeImpl automaticType = new AutomaticTypeImpl();
		return automaticType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicTypeType createBasicTypeType() {
		BasicTypeTypeImpl basicTypeType = new BasicTypeTypeImpl();
		return basicTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockActivityType createBlockActivityType() {
		BlockActivityTypeImpl blockActivityType = new BlockActivityTypeImpl();
		return blockActivityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionType createConditionType() {
		ConditionTypeImpl conditionType = new ConditionTypeImpl();
		return conditionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConformanceClassType createConformanceClassType() {
		ConformanceClassTypeImpl conformanceClassType = new ConformanceClassTypeImpl();
		return conformanceClassType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataFieldsType createDataFieldsType() {
		DataFieldsTypeImpl dataFieldsType = new DataFieldsTypeImpl();
		return dataFieldsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataFieldType createDataFieldType() {
		DataFieldTypeImpl dataFieldType = new DataFieldTypeImpl();
		return dataFieldType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeType createDataTypeType() {
		DataTypeTypeImpl dataTypeType = new DataTypeTypeImpl();
		return dataTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeadlineType createDeadlineType() {
		DeadlineTypeImpl deadlineType = new DeadlineTypeImpl();
		return deadlineType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeclaredTypeType createDeclaredTypeType() {
		DeclaredTypeTypeImpl declaredTypeType = new DeclaredTypeTypeImpl();
		return declaredTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationTypeType createEnumerationTypeType() {
		EnumerationTypeTypeImpl enumerationTypeType = new EnumerationTypeTypeImpl();
		return enumerationTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationValueType createEnumerationValueType() {
		EnumerationValueTypeImpl enumerationValueType = new EnumerationValueTypeImpl();
		return enumerationValueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttributesType createExtendedAttributesType() {
		ExtendedAttributesTypeImpl extendedAttributesType = new ExtendedAttributesTypeImpl();
		return extendedAttributesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttributeType createExtendedAttributeType() {
		ExtendedAttributeTypeImpl extendedAttributeType = new ExtendedAttributeTypeImpl();
		return extendedAttributeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPackagesType createExternalPackagesType() {
		ExternalPackagesTypeImpl externalPackagesType = new ExternalPackagesTypeImpl();
		return externalPackagesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPackageType createExternalPackageType() {
		ExternalPackageTypeImpl externalPackageType = new ExternalPackageTypeImpl();
		return externalPackageType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalReferenceType createExternalReferenceType() {
		ExternalReferenceTypeImpl externalReferenceType = new ExternalReferenceTypeImpl();
		return externalReferenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FinishModeType createFinishModeType() {
		FinishModeTypeImpl finishModeType = new FinishModeTypeImpl();
		return finishModeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParametersType createFormalParametersType() {
		FormalParametersTypeImpl formalParametersType = new FormalParametersTypeImpl();
		return formalParametersType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParameterType createFormalParameterType() {
		FormalParameterTypeImpl formalParameterType = new FormalParameterTypeImpl();
		return formalParameterType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplementationType createImplementationType() {
		ImplementationTypeImpl implementationType = new ImplementationTypeImpl();
		return implementationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinType createJoinType() {
		JoinTypeImpl joinType = new JoinTypeImpl();
		return joinType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListTypeType createListTypeType() {
		ListTypeTypeImpl listTypeType = new ListTypeTypeImpl();
		return listTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManualType createManualType() {
		ManualTypeImpl manualType = new ManualTypeImpl();
		return manualType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemberType createMemberType() {
		MemberTypeImpl memberType = new MemberTypeImpl();
		return memberType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoType createNoType() {
		NoTypeImpl noType = new NoTypeImpl();
		return noType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageHeaderType createPackageHeaderType() {
		PackageHeaderTypeImpl packageHeaderType = new PackageHeaderTypeImpl();
		return packageHeaderType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageType createPackageType() {
		PackageTypeImpl packageType = new PackageTypeImpl();
		return packageType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParticipantsType createParticipantsType() {
		ParticipantsTypeImpl participantsType = new ParticipantsTypeImpl();
		return participantsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParticipantType createParticipantType() {
		ParticipantTypeImpl participantType = new ParticipantTypeImpl();
		return participantType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParticipantTypeType createParticipantTypeType() {
		ParticipantTypeTypeImpl participantTypeType = new ParticipantTypeTypeImpl();
		return participantTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessHeaderType createProcessHeaderType() {
		ProcessHeaderTypeImpl processHeaderType = new ProcessHeaderTypeImpl();
		return processHeaderType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordTypeType createRecordTypeType() {
		RecordTypeTypeImpl recordTypeType = new RecordTypeTypeImpl();
		return recordTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RedefinableHeaderType createRedefinableHeaderType() {
		RedefinableHeaderTypeImpl redefinableHeaderType = new RedefinableHeaderTypeImpl();
		return redefinableHeaderType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsiblesType createResponsiblesType() {
		ResponsiblesTypeImpl responsiblesType = new ResponsiblesTypeImpl();
		return responsiblesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RouteType createRouteType() {
		RouteTypeImpl routeType = new RouteTypeImpl();
		return routeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchemaTypeType createSchemaTypeType() {
		SchemaTypeTypeImpl schemaTypeType = new SchemaTypeTypeImpl();
		return schemaTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptType createScriptType() {
		ScriptTypeImpl scriptType = new ScriptTypeImpl();
		return scriptType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationInformationType createSimulationInformationType() {
		SimulationInformationTypeImpl simulationInformationType = new SimulationInformationTypeImpl();
		return simulationInformationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SplitType createSplitType() {
		SplitTypeImpl splitType = new SplitTypeImpl();
		return splitType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartModeType createStartModeType() {
		StartModeTypeImpl startModeType = new StartModeTypeImpl();
		return startModeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubFlowType createSubFlowType() {
		SubFlowTypeImpl subFlowType = new SubFlowTypeImpl();
		return subFlowType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeEstimationType createTimeEstimationType() {
		TimeEstimationTypeImpl timeEstimationType = new TimeEstimationTypeImpl();
		return timeEstimationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToolType createToolType() {
		ToolTypeImpl toolType = new ToolTypeImpl();
		return toolType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionRefsType createTransitionRefsType() {
		TransitionRefsTypeImpl transitionRefsType = new TransitionRefsTypeImpl();
		return transitionRefsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionRefType createTransitionRefType() {
		TransitionRefTypeImpl transitionRefType = new TransitionRefTypeImpl();
		return transitionRefType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionRestrictionsType createTransitionRestrictionsType() {
		TransitionRestrictionsTypeImpl transitionRestrictionsType = new TransitionRestrictionsTypeImpl();
		return transitionRestrictionsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionRestrictionType createTransitionRestrictionType() {
		TransitionRestrictionTypeImpl transitionRestrictionType = new TransitionRestrictionTypeImpl();
		return transitionRestrictionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionsType createTransitionsType() {
		TransitionsTypeImpl transitionsType = new TransitionsTypeImpl();
		return transitionsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionType createTransitionType() {
		TransitionTypeImpl transitionType = new TransitionTypeImpl();
		return transitionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationsType createTypeDeclarationsType() {
		TypeDeclarationsTypeImpl typeDeclarationsType = new TypeDeclarationsTypeImpl();
		return typeDeclarationsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationType createTypeDeclarationType() {
		TypeDeclarationTypeImpl typeDeclarationType = new TypeDeclarationTypeImpl();
		return typeDeclarationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionTypeType createUnionTypeType() {
		UnionTypeTypeImpl unionTypeType = new UnionTypeTypeImpl();
		return unionTypeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowProcessesType createWorkflowProcessesType() {
		WorkflowProcessesTypeImpl workflowProcessesType = new WorkflowProcessesTypeImpl();
		return workflowProcessesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowProcessType createWorkflowProcessType() {
		WorkflowProcessTypeImpl workflowProcessType = new WorkflowProcessTypeImpl();
		return workflowProcessType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XpressionType createXpressionType() {
		XpressionTypeImpl xpressionType = new XpressionTypeImpl();
		return xpressionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccessLevelType createAccessLevelTypeFromString(EDataType eDataType, String initialValue) {
		AccessLevelType result = AccessLevelType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAccessLevelTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DurationUnitType createDurationUnitTypeFromString(EDataType eDataType, String initialValue) {
		DurationUnitType result = DurationUnitType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationUnitTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionType createExecutionTypeFromString(EDataType eDataType, String initialValue) {
		ExecutionType result = ExecutionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExecutionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionType1 createExecutionType1FromString(EDataType eDataType, String initialValue) {
		ExecutionType1 result = ExecutionType1.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExecutionType1ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphConformanceType createGraphConformanceTypeFromString(EDataType eDataType, String initialValue) {
		GraphConformanceType result = GraphConformanceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGraphConformanceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstantiationType createInstantiationTypeFromString(EDataType eDataType, String initialValue) {
		InstantiationType result = InstantiationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInstantiationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsArrayType createIsArrayTypeFromString(EDataType eDataType, String initialValue) {
		IsArrayType result = IsArrayType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIsArrayTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeType createModeTypeFromString(EDataType eDataType, String initialValue) {
		ModeType result = ModeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertModeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PublicationStatusType createPublicationStatusTypeFromString(EDataType eDataType, String initialValue) {
		PublicationStatusType result = PublicationStatusType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPublicationStatusTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType createTypeTypeFromString(EDataType eDataType, String initialValue) {
		TypeType result = TypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType1 createTypeType1FromString(EDataType eDataType, String initialValue) {
		TypeType1 result = TypeType1.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType1ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType2 createTypeType2FromString(EDataType eDataType, String initialValue) {
		TypeType2 result = TypeType2.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType2ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType3 createTypeType3FromString(EDataType eDataType, String initialValue) {
		TypeType3 result = TypeType3.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType3ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType4 createTypeType4FromString(EDataType eDataType, String initialValue) {
		TypeType4 result = TypeType4.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType4ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType5 createTypeType5FromString(EDataType eDataType, String initialValue) {
		TypeType5 result = TypeType5.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType5ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccessLevelType createAccessLevelTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createAccessLevelTypeFromString(Xpdl1Package.Literals.ACCESS_LEVEL_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAccessLevelTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertAccessLevelTypeToString(Xpdl1Package.Literals.ACCESS_LEVEL_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DurationUnitType createDurationUnitTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDurationUnitTypeFromString(Xpdl1Package.Literals.DURATION_UNIT_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationUnitTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDurationUnitTypeToString(Xpdl1Package.Literals.DURATION_UNIT_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionType createExecutionTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createExecutionTypeFromString(Xpdl1Package.Literals.EXECUTION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExecutionTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertExecutionTypeToString(Xpdl1Package.Literals.EXECUTION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionType1 createExecutionTypeObject1FromString(EDataType eDataType, String initialValue) {
		return createExecutionType1FromString(Xpdl1Package.Literals.EXECUTION_TYPE1, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExecutionTypeObject1ToString(EDataType eDataType, Object instanceValue) {
		return convertExecutionType1ToString(Xpdl1Package.Literals.EXECUTION_TYPE1, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphConformanceType createGraphConformanceTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createGraphConformanceTypeFromString(Xpdl1Package.Literals.GRAPH_CONFORMANCE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGraphConformanceTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertGraphConformanceTypeToString(Xpdl1Package.Literals.GRAPH_CONFORMANCE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstantiationType createInstantiationTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createInstantiationTypeFromString(Xpdl1Package.Literals.INSTANTIATION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInstantiationTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertInstantiationTypeToString(Xpdl1Package.Literals.INSTANTIATION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsArrayType createIsArrayTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createIsArrayTypeFromString(Xpdl1Package.Literals.IS_ARRAY_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIsArrayTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertIsArrayTypeToString(Xpdl1Package.Literals.IS_ARRAY_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeType createModeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createModeTypeFromString(Xpdl1Package.Literals.MODE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertModeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertModeTypeToString(Xpdl1Package.Literals.MODE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PublicationStatusType createPublicationStatusTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createPublicationStatusTypeFromString(Xpdl1Package.Literals.PUBLICATION_STATUS_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPublicationStatusTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPublicationStatusTypeToString(Xpdl1Package.Literals.PUBLICATION_STATUS_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType4 createTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTypeType4FromString(Xpdl1Package.Literals.TYPE_TYPE4, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTypeType4ToString(Xpdl1Package.Literals.TYPE_TYPE4, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType1 createTypeTypeObject1FromString(EDataType eDataType, String initialValue) {
		return createTypeType1FromString(Xpdl1Package.Literals.TYPE_TYPE1, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeObject1ToString(EDataType eDataType, Object instanceValue) {
		return convertTypeType1ToString(Xpdl1Package.Literals.TYPE_TYPE1, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType3 createTypeTypeObject2FromString(EDataType eDataType, String initialValue) {
		return createTypeType3FromString(Xpdl1Package.Literals.TYPE_TYPE3, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeObject2ToString(EDataType eDataType, Object instanceValue) {
		return convertTypeType3ToString(Xpdl1Package.Literals.TYPE_TYPE3, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType2 createTypeTypeObject3FromString(EDataType eDataType, String initialValue) {
		return createTypeType2FromString(Xpdl1Package.Literals.TYPE_TYPE2, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeObject3ToString(EDataType eDataType, Object instanceValue) {
		return convertTypeType2ToString(Xpdl1Package.Literals.TYPE_TYPE2, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType createTypeTypeObject4FromString(EDataType eDataType, String initialValue) {
		return createTypeTypeFromString(Xpdl1Package.Literals.TYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeObject4ToString(EDataType eDataType, Object instanceValue) {
		return convertTypeTypeToString(Xpdl1Package.Literals.TYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType5 createTypeTypeObject5FromString(EDataType eDataType, String initialValue) {
		return createTypeType5FromString(Xpdl1Package.Literals.TYPE_TYPE5, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeObject5ToString(EDataType eDataType, Object instanceValue) {
		return convertTypeType5ToString(Xpdl1Package.Literals.TYPE_TYPE5, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Xpdl1Package getXpdl1Package() {
		return (Xpdl1Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Xpdl1Package getPackage() {
		return Xpdl1Package.eINSTANCE;
	}

} //Xpdl1FactoryImpl
