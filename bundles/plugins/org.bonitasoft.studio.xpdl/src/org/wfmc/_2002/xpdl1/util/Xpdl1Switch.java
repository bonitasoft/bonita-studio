/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
import org.wfmc._2002.xpdl1.EnumerationTypeType;
import org.wfmc._2002.xpdl1.EnumerationValueType;
import org.wfmc._2002.xpdl1.ExtendedAttributeType;
import org.wfmc._2002.xpdl1.ExtendedAttributesType;
import org.wfmc._2002.xpdl1.ExternalPackageType;
import org.wfmc._2002.xpdl1.ExternalPackagesType;
import org.wfmc._2002.xpdl1.ExternalReferenceType;
import org.wfmc._2002.xpdl1.FinishModeType;
import org.wfmc._2002.xpdl1.FormalParameterType;
import org.wfmc._2002.xpdl1.FormalParametersType;
import org.wfmc._2002.xpdl1.ImplementationType;
import org.wfmc._2002.xpdl1.JoinType;
import org.wfmc._2002.xpdl1.ListTypeType;
import org.wfmc._2002.xpdl1.ManualType;
import org.wfmc._2002.xpdl1.MemberType;
import org.wfmc._2002.xpdl1.NoType;
import org.wfmc._2002.xpdl1.PackageHeaderType;
import org.wfmc._2002.xpdl1.PackageType;
import org.wfmc._2002.xpdl1.ParticipantType;
import org.wfmc._2002.xpdl1.ParticipantTypeType;
import org.wfmc._2002.xpdl1.ParticipantsType;
import org.wfmc._2002.xpdl1.ProcessHeaderType;
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
import org.wfmc._2002.xpdl1.UnionTypeType;
import org.wfmc._2002.xpdl1.WorkflowProcessType;
import org.wfmc._2002.xpdl1.WorkflowProcessesType;
import org.wfmc._2002.xpdl1.Xpdl1Package;
import org.wfmc._2002.xpdl1.XpressionType;

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
 * @see org.wfmc._2002.xpdl1.Xpdl1Package
 * @generated
 */
public class Xpdl1Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Xpdl1Package modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Xpdl1Switch() {
		if (modelPackage == null) {
			modelPackage = Xpdl1Package.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case Xpdl1Package.ACTIVITIES_TYPE: {
				ActivitiesType activitiesType = (ActivitiesType)theEObject;
				T result = caseActivitiesType(activitiesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.ACTIVITY_SETS_TYPE: {
				ActivitySetsType activitySetsType = (ActivitySetsType)theEObject;
				T result = caseActivitySetsType(activitySetsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.ACTIVITY_SET_TYPE: {
				ActivitySetType activitySetType = (ActivitySetType)theEObject;
				T result = caseActivitySetType(activitySetType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.ACTIVITY_TYPE: {
				ActivityType activityType = (ActivityType)theEObject;
				T result = caseActivityType(activityType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.ACTUAL_PARAMETERS_TYPE: {
				ActualParametersType actualParametersType = (ActualParametersType)theEObject;
				T result = caseActualParametersType(actualParametersType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.APPLICATIONS_TYPE: {
				ApplicationsType applicationsType = (ApplicationsType)theEObject;
				T result = caseApplicationsType(applicationsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.APPLICATION_TYPE: {
				ApplicationType applicationType = (ApplicationType)theEObject;
				T result = caseApplicationType(applicationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.ARRAY_TYPE_TYPE: {
				ArrayTypeType arrayTypeType = (ArrayTypeType)theEObject;
				T result = caseArrayTypeType(arrayTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.AUTOMATIC_TYPE: {
				AutomaticType automaticType = (AutomaticType)theEObject;
				T result = caseAutomaticType(automaticType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.BASIC_TYPE_TYPE: {
				BasicTypeType basicTypeType = (BasicTypeType)theEObject;
				T result = caseBasicTypeType(basicTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.BLOCK_ACTIVITY_TYPE: {
				BlockActivityType blockActivityType = (BlockActivityType)theEObject;
				T result = caseBlockActivityType(blockActivityType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.CONDITION_TYPE: {
				ConditionType conditionType = (ConditionType)theEObject;
				T result = caseConditionType(conditionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.CONFORMANCE_CLASS_TYPE: {
				ConformanceClassType conformanceClassType = (ConformanceClassType)theEObject;
				T result = caseConformanceClassType(conformanceClassType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.DATA_FIELDS_TYPE: {
				DataFieldsType dataFieldsType = (DataFieldsType)theEObject;
				T result = caseDataFieldsType(dataFieldsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.DATA_FIELD_TYPE: {
				DataFieldType dataFieldType = (DataFieldType)theEObject;
				T result = caseDataFieldType(dataFieldType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.DATA_TYPE_TYPE: {
				DataTypeType dataTypeType = (DataTypeType)theEObject;
				T result = caseDataTypeType(dataTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.DEADLINE_TYPE: {
				DeadlineType deadlineType = (DeadlineType)theEObject;
				T result = caseDeadlineType(deadlineType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.DECLARED_TYPE_TYPE: {
				DeclaredTypeType declaredTypeType = (DeclaredTypeType)theEObject;
				T result = caseDeclaredTypeType(declaredTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.ENUMERATION_TYPE_TYPE: {
				EnumerationTypeType enumerationTypeType = (EnumerationTypeType)theEObject;
				T result = caseEnumerationTypeType(enumerationTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.ENUMERATION_VALUE_TYPE: {
				EnumerationValueType enumerationValueType = (EnumerationValueType)theEObject;
				T result = caseEnumerationValueType(enumerationValueType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.EXTENDED_ATTRIBUTES_TYPE: {
				ExtendedAttributesType extendedAttributesType = (ExtendedAttributesType)theEObject;
				T result = caseExtendedAttributesType(extendedAttributesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE: {
				ExtendedAttributeType extendedAttributeType = (ExtendedAttributeType)theEObject;
				T result = caseExtendedAttributeType(extendedAttributeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.EXTERNAL_PACKAGES_TYPE: {
				ExternalPackagesType externalPackagesType = (ExternalPackagesType)theEObject;
				T result = caseExternalPackagesType(externalPackagesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.EXTERNAL_PACKAGE_TYPE: {
				ExternalPackageType externalPackageType = (ExternalPackageType)theEObject;
				T result = caseExternalPackageType(externalPackageType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.EXTERNAL_REFERENCE_TYPE: {
				ExternalReferenceType externalReferenceType = (ExternalReferenceType)theEObject;
				T result = caseExternalReferenceType(externalReferenceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.FINISH_MODE_TYPE: {
				FinishModeType finishModeType = (FinishModeType)theEObject;
				T result = caseFinishModeType(finishModeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.FORMAL_PARAMETERS_TYPE: {
				FormalParametersType formalParametersType = (FormalParametersType)theEObject;
				T result = caseFormalParametersType(formalParametersType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.FORMAL_PARAMETER_TYPE: {
				FormalParameterType formalParameterType = (FormalParameterType)theEObject;
				T result = caseFormalParameterType(formalParameterType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.IMPLEMENTATION_TYPE: {
				ImplementationType implementationType = (ImplementationType)theEObject;
				T result = caseImplementationType(implementationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.JOIN_TYPE: {
				JoinType joinType = (JoinType)theEObject;
				T result = caseJoinType(joinType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.LIST_TYPE_TYPE: {
				ListTypeType listTypeType = (ListTypeType)theEObject;
				T result = caseListTypeType(listTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.MANUAL_TYPE: {
				ManualType manualType = (ManualType)theEObject;
				T result = caseManualType(manualType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.MEMBER_TYPE: {
				MemberType memberType = (MemberType)theEObject;
				T result = caseMemberType(memberType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.NO_TYPE: {
				NoType noType = (NoType)theEObject;
				T result = caseNoType(noType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.PACKAGE_HEADER_TYPE: {
				PackageHeaderType packageHeaderType = (PackageHeaderType)theEObject;
				T result = casePackageHeaderType(packageHeaderType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.PACKAGE_TYPE: {
				PackageType packageType = (PackageType)theEObject;
				T result = casePackageType(packageType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.PARTICIPANTS_TYPE: {
				ParticipantsType participantsType = (ParticipantsType)theEObject;
				T result = caseParticipantsType(participantsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.PARTICIPANT_TYPE: {
				ParticipantType participantType = (ParticipantType)theEObject;
				T result = caseParticipantType(participantType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.PARTICIPANT_TYPE_TYPE: {
				ParticipantTypeType participantTypeType = (ParticipantTypeType)theEObject;
				T result = caseParticipantTypeType(participantTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.PROCESS_HEADER_TYPE: {
				ProcessHeaderType processHeaderType = (ProcessHeaderType)theEObject;
				T result = caseProcessHeaderType(processHeaderType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.RECORD_TYPE_TYPE: {
				RecordTypeType recordTypeType = (RecordTypeType)theEObject;
				T result = caseRecordTypeType(recordTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE: {
				RedefinableHeaderType redefinableHeaderType = (RedefinableHeaderType)theEObject;
				T result = caseRedefinableHeaderType(redefinableHeaderType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.RESPONSIBLES_TYPE: {
				ResponsiblesType responsiblesType = (ResponsiblesType)theEObject;
				T result = caseResponsiblesType(responsiblesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.ROUTE_TYPE: {
				RouteType routeType = (RouteType)theEObject;
				T result = caseRouteType(routeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.SCHEMA_TYPE_TYPE: {
				SchemaTypeType schemaTypeType = (SchemaTypeType)theEObject;
				T result = caseSchemaTypeType(schemaTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.SCRIPT_TYPE: {
				ScriptType scriptType = (ScriptType)theEObject;
				T result = caseScriptType(scriptType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE: {
				SimulationInformationType simulationInformationType = (SimulationInformationType)theEObject;
				T result = caseSimulationInformationType(simulationInformationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.SPLIT_TYPE: {
				SplitType splitType = (SplitType)theEObject;
				T result = caseSplitType(splitType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.START_MODE_TYPE: {
				StartModeType startModeType = (StartModeType)theEObject;
				T result = caseStartModeType(startModeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.SUB_FLOW_TYPE: {
				SubFlowType subFlowType = (SubFlowType)theEObject;
				T result = caseSubFlowType(subFlowType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TIME_ESTIMATION_TYPE: {
				TimeEstimationType timeEstimationType = (TimeEstimationType)theEObject;
				T result = caseTimeEstimationType(timeEstimationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TOOL_TYPE: {
				ToolType toolType = (ToolType)theEObject;
				T result = caseToolType(toolType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TRANSITION_REFS_TYPE: {
				TransitionRefsType transitionRefsType = (TransitionRefsType)theEObject;
				T result = caseTransitionRefsType(transitionRefsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TRANSITION_REF_TYPE: {
				TransitionRefType transitionRefType = (TransitionRefType)theEObject;
				T result = caseTransitionRefType(transitionRefType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TRANSITION_RESTRICTIONS_TYPE: {
				TransitionRestrictionsType transitionRestrictionsType = (TransitionRestrictionsType)theEObject;
				T result = caseTransitionRestrictionsType(transitionRestrictionsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE: {
				TransitionRestrictionType transitionRestrictionType = (TransitionRestrictionType)theEObject;
				T result = caseTransitionRestrictionType(transitionRestrictionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TRANSITIONS_TYPE: {
				TransitionsType transitionsType = (TransitionsType)theEObject;
				T result = caseTransitionsType(transitionsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TRANSITION_TYPE: {
				TransitionType transitionType = (TransitionType)theEObject;
				T result = caseTransitionType(transitionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TYPE_DECLARATIONS_TYPE: {
				TypeDeclarationsType typeDeclarationsType = (TypeDeclarationsType)theEObject;
				T result = caseTypeDeclarationsType(typeDeclarationsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.TYPE_DECLARATION_TYPE: {
				TypeDeclarationType typeDeclarationType = (TypeDeclarationType)theEObject;
				T result = caseTypeDeclarationType(typeDeclarationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.UNION_TYPE_TYPE: {
				UnionTypeType unionTypeType = (UnionTypeType)theEObject;
				T result = caseUnionTypeType(unionTypeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.WORKFLOW_PROCESSES_TYPE: {
				WorkflowProcessesType workflowProcessesType = (WorkflowProcessesType)theEObject;
				T result = caseWorkflowProcessesType(workflowProcessesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE: {
				WorkflowProcessType workflowProcessType = (WorkflowProcessType)theEObject;
				T result = caseWorkflowProcessType(workflowProcessType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Xpdl1Package.XPRESSION_TYPE: {
				XpressionType xpressionType = (XpressionType)theEObject;
				T result = caseXpressionType(xpressionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activities Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activities Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivitiesType(ActivitiesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Sets Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Sets Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivitySetsType(ActivitySetsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Set Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivitySetType(ActivitySetType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityType(ActivityType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actual Parameters Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actual Parameters Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActualParametersType(ActualParametersType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Applications Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Applications Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationsType(ApplicationsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationType(ApplicationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArrayTypeType(ArrayTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Automatic Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Automatic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAutomaticType(AutomaticType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Basic Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Basic Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBasicTypeType(BasicTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Block Activity Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Block Activity Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlockActivityType(BlockActivityType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionType(ConditionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conformance Class Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conformance Class Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConformanceClassType(ConformanceClassType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Fields Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Fields Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataFieldsType(DataFieldsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Field Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Field Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataFieldType(DataFieldType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataTypeType(DataTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deadline Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deadline Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeadlineType(DeadlineType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Declared Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Declared Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeclaredTypeType(DeclaredTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumerationTypeType(EnumerationTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration Value Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration Value Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumerationValueType(EnumerationValueType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extended Attributes Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extended Attributes Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtendedAttributesType(ExtendedAttributesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extended Attribute Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extended Attribute Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtendedAttributeType(ExtendedAttributeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Packages Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Packages Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalPackagesType(ExternalPackagesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Package Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Package Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalPackageType(ExternalPackageType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Reference Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Reference Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalReferenceType(ExternalReferenceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Finish Mode Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Finish Mode Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFinishModeType(FinishModeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Parameters Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Parameters Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalParametersType(FormalParametersType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Parameter Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Parameter Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalParameterType(FormalParameterType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Implementation Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Implementation Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImplementationType(ImplementationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Join Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Join Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJoinType(JoinType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseListTypeType(ListTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Manual Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Manual Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManualType(ManualType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMemberType(MemberType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>No Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>No Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoType(NoType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Header Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Header Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageHeaderType(PackageHeaderType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageType(PackageType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Participants Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Participants Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParticipantsType(ParticipantsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Participant Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Participant Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParticipantType(ParticipantType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Participant Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Participant Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParticipantTypeType(ParticipantTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Header Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Header Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessHeaderType(ProcessHeaderType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Record Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Record Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRecordTypeType(RecordTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Redefinable Header Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Redefinable Header Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRedefinableHeaderType(RedefinableHeaderType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Responsibles Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Responsibles Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResponsiblesType(ResponsiblesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Route Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Route Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRouteType(RouteType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Schema Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Schema Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSchemaTypeType(SchemaTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScriptType(ScriptType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simulation Information Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simulation Information Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimulationInformationType(SimulationInformationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Split Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Split Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSplitType(SplitType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Start Mode Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Start Mode Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStartModeType(StartModeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sub Flow Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sub Flow Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubFlowType(SubFlowType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time Estimation Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time Estimation Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimeEstimationType(TimeEstimationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tool Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tool Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseToolType(ToolType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Refs Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Refs Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionRefsType(TransitionRefsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Ref Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Ref Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionRefType(TransitionRefType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Restrictions Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Restrictions Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionRestrictionsType(TransitionRestrictionsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Restriction Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Restriction Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionRestrictionType(TransitionRestrictionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transitions Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transitions Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionsType(TransitionsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionType(TransitionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Declarations Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Declarations Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeDeclarationsType(TypeDeclarationsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Declaration Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Declaration Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeDeclarationType(TypeDeclarationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnionTypeType(UnionTypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workflow Processes Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workflow Processes Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkflowProcessesType(WorkflowProcessesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workflow Process Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workflow Process Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkflowProcessType(WorkflowProcessType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Xpression Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Xpression Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXpressionType(XpressionType object) {
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
	public T defaultCase(EObject object) {
		return null;
	}

} //Xpdl1Switch
