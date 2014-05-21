/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.wfmc._2002.xpdl1.Xpdl1Package
 * @generated
 */
public class Xpdl1AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Xpdl1Package modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Xpdl1AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Xpdl1Package.eINSTANCE;
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
	protected Xpdl1Switch<Adapter> modelSwitch =
		new Xpdl1Switch<Adapter>() {
			@Override
			public Adapter caseActivitiesType(ActivitiesType object) {
				return createActivitiesTypeAdapter();
			}
			@Override
			public Adapter caseActivitySetsType(ActivitySetsType object) {
				return createActivitySetsTypeAdapter();
			}
			@Override
			public Adapter caseActivitySetType(ActivitySetType object) {
				return createActivitySetTypeAdapter();
			}
			@Override
			public Adapter caseActivityType(ActivityType object) {
				return createActivityTypeAdapter();
			}
			@Override
			public Adapter caseActualParametersType(ActualParametersType object) {
				return createActualParametersTypeAdapter();
			}
			@Override
			public Adapter caseApplicationsType(ApplicationsType object) {
				return createApplicationsTypeAdapter();
			}
			@Override
			public Adapter caseApplicationType(ApplicationType object) {
				return createApplicationTypeAdapter();
			}
			@Override
			public Adapter caseArrayTypeType(ArrayTypeType object) {
				return createArrayTypeTypeAdapter();
			}
			@Override
			public Adapter caseAutomaticType(AutomaticType object) {
				return createAutomaticTypeAdapter();
			}
			@Override
			public Adapter caseBasicTypeType(BasicTypeType object) {
				return createBasicTypeTypeAdapter();
			}
			@Override
			public Adapter caseBlockActivityType(BlockActivityType object) {
				return createBlockActivityTypeAdapter();
			}
			@Override
			public Adapter caseConditionType(ConditionType object) {
				return createConditionTypeAdapter();
			}
			@Override
			public Adapter caseConformanceClassType(ConformanceClassType object) {
				return createConformanceClassTypeAdapter();
			}
			@Override
			public Adapter caseDataFieldsType(DataFieldsType object) {
				return createDataFieldsTypeAdapter();
			}
			@Override
			public Adapter caseDataFieldType(DataFieldType object) {
				return createDataFieldTypeAdapter();
			}
			@Override
			public Adapter caseDataTypeType(DataTypeType object) {
				return createDataTypeTypeAdapter();
			}
			@Override
			public Adapter caseDeadlineType(DeadlineType object) {
				return createDeadlineTypeAdapter();
			}
			@Override
			public Adapter caseDeclaredTypeType(DeclaredTypeType object) {
				return createDeclaredTypeTypeAdapter();
			}
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseEnumerationTypeType(EnumerationTypeType object) {
				return createEnumerationTypeTypeAdapter();
			}
			@Override
			public Adapter caseEnumerationValueType(EnumerationValueType object) {
				return createEnumerationValueTypeAdapter();
			}
			@Override
			public Adapter caseExtendedAttributesType(ExtendedAttributesType object) {
				return createExtendedAttributesTypeAdapter();
			}
			@Override
			public Adapter caseExtendedAttributeType(ExtendedAttributeType object) {
				return createExtendedAttributeTypeAdapter();
			}
			@Override
			public Adapter caseExternalPackagesType(ExternalPackagesType object) {
				return createExternalPackagesTypeAdapter();
			}
			@Override
			public Adapter caseExternalPackageType(ExternalPackageType object) {
				return createExternalPackageTypeAdapter();
			}
			@Override
			public Adapter caseExternalReferenceType(ExternalReferenceType object) {
				return createExternalReferenceTypeAdapter();
			}
			@Override
			public Adapter caseFinishModeType(FinishModeType object) {
				return createFinishModeTypeAdapter();
			}
			@Override
			public Adapter caseFormalParametersType(FormalParametersType object) {
				return createFormalParametersTypeAdapter();
			}
			@Override
			public Adapter caseFormalParameterType(FormalParameterType object) {
				return createFormalParameterTypeAdapter();
			}
			@Override
			public Adapter caseImplementationType(ImplementationType object) {
				return createImplementationTypeAdapter();
			}
			@Override
			public Adapter caseJoinType(JoinType object) {
				return createJoinTypeAdapter();
			}
			@Override
			public Adapter caseListTypeType(ListTypeType object) {
				return createListTypeTypeAdapter();
			}
			@Override
			public Adapter caseManualType(ManualType object) {
				return createManualTypeAdapter();
			}
			@Override
			public Adapter caseMemberType(MemberType object) {
				return createMemberTypeAdapter();
			}
			@Override
			public Adapter caseNoType(NoType object) {
				return createNoTypeAdapter();
			}
			@Override
			public Adapter casePackageHeaderType(PackageHeaderType object) {
				return createPackageHeaderTypeAdapter();
			}
			@Override
			public Adapter casePackageType(PackageType object) {
				return createPackageTypeAdapter();
			}
			@Override
			public Adapter caseParticipantsType(ParticipantsType object) {
				return createParticipantsTypeAdapter();
			}
			@Override
			public Adapter caseParticipantType(ParticipantType object) {
				return createParticipantTypeAdapter();
			}
			@Override
			public Adapter caseParticipantTypeType(ParticipantTypeType object) {
				return createParticipantTypeTypeAdapter();
			}
			@Override
			public Adapter caseProcessHeaderType(ProcessHeaderType object) {
				return createProcessHeaderTypeAdapter();
			}
			@Override
			public Adapter caseRecordTypeType(RecordTypeType object) {
				return createRecordTypeTypeAdapter();
			}
			@Override
			public Adapter caseRedefinableHeaderType(RedefinableHeaderType object) {
				return createRedefinableHeaderTypeAdapter();
			}
			@Override
			public Adapter caseResponsiblesType(ResponsiblesType object) {
				return createResponsiblesTypeAdapter();
			}
			@Override
			public Adapter caseRouteType(RouteType object) {
				return createRouteTypeAdapter();
			}
			@Override
			public Adapter caseSchemaTypeType(SchemaTypeType object) {
				return createSchemaTypeTypeAdapter();
			}
			@Override
			public Adapter caseScriptType(ScriptType object) {
				return createScriptTypeAdapter();
			}
			@Override
			public Adapter caseSimulationInformationType(SimulationInformationType object) {
				return createSimulationInformationTypeAdapter();
			}
			@Override
			public Adapter caseSplitType(SplitType object) {
				return createSplitTypeAdapter();
			}
			@Override
			public Adapter caseStartModeType(StartModeType object) {
				return createStartModeTypeAdapter();
			}
			@Override
			public Adapter caseSubFlowType(SubFlowType object) {
				return createSubFlowTypeAdapter();
			}
			@Override
			public Adapter caseTimeEstimationType(TimeEstimationType object) {
				return createTimeEstimationTypeAdapter();
			}
			@Override
			public Adapter caseToolType(ToolType object) {
				return createToolTypeAdapter();
			}
			@Override
			public Adapter caseTransitionRefsType(TransitionRefsType object) {
				return createTransitionRefsTypeAdapter();
			}
			@Override
			public Adapter caseTransitionRefType(TransitionRefType object) {
				return createTransitionRefTypeAdapter();
			}
			@Override
			public Adapter caseTransitionRestrictionsType(TransitionRestrictionsType object) {
				return createTransitionRestrictionsTypeAdapter();
			}
			@Override
			public Adapter caseTransitionRestrictionType(TransitionRestrictionType object) {
				return createTransitionRestrictionTypeAdapter();
			}
			@Override
			public Adapter caseTransitionsType(TransitionsType object) {
				return createTransitionsTypeAdapter();
			}
			@Override
			public Adapter caseTransitionType(TransitionType object) {
				return createTransitionTypeAdapter();
			}
			@Override
			public Adapter caseTypeDeclarationsType(TypeDeclarationsType object) {
				return createTypeDeclarationsTypeAdapter();
			}
			@Override
			public Adapter caseTypeDeclarationType(TypeDeclarationType object) {
				return createTypeDeclarationTypeAdapter();
			}
			@Override
			public Adapter caseUnionTypeType(UnionTypeType object) {
				return createUnionTypeTypeAdapter();
			}
			@Override
			public Adapter caseWorkflowProcessesType(WorkflowProcessesType object) {
				return createWorkflowProcessesTypeAdapter();
			}
			@Override
			public Adapter caseWorkflowProcessType(WorkflowProcessType object) {
				return createWorkflowProcessTypeAdapter();
			}
			@Override
			public Adapter caseXpressionType(XpressionType object) {
				return createXpressionTypeAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ActivitiesType <em>Activities Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ActivitiesType
	 * @generated
	 */
	public Adapter createActivitiesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ActivitySetsType <em>Activity Sets Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ActivitySetsType
	 * @generated
	 */
	public Adapter createActivitySetsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ActivitySetType <em>Activity Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ActivitySetType
	 * @generated
	 */
	public Adapter createActivitySetTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ActivityType <em>Activity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ActivityType
	 * @generated
	 */
	public Adapter createActivityTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ActualParametersType <em>Actual Parameters Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ActualParametersType
	 * @generated
	 */
	public Adapter createActualParametersTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ApplicationsType <em>Applications Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ApplicationsType
	 * @generated
	 */
	public Adapter createApplicationsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ApplicationType <em>Application Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ApplicationType
	 * @generated
	 */
	public Adapter createApplicationTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ArrayTypeType <em>Array Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ArrayTypeType
	 * @generated
	 */
	public Adapter createArrayTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.AutomaticType <em>Automatic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.AutomaticType
	 * @generated
	 */
	public Adapter createAutomaticTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.BasicTypeType <em>Basic Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.BasicTypeType
	 * @generated
	 */
	public Adapter createBasicTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.BlockActivityType <em>Block Activity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.BlockActivityType
	 * @generated
	 */
	public Adapter createBlockActivityTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ConditionType <em>Condition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ConditionType
	 * @generated
	 */
	public Adapter createConditionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ConformanceClassType <em>Conformance Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ConformanceClassType
	 * @generated
	 */
	public Adapter createConformanceClassTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.DataFieldsType <em>Data Fields Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.DataFieldsType
	 * @generated
	 */
	public Adapter createDataFieldsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.DataFieldType <em>Data Field Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.DataFieldType
	 * @generated
	 */
	public Adapter createDataFieldTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.DataTypeType <em>Data Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.DataTypeType
	 * @generated
	 */
	public Adapter createDataTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.DeadlineType <em>Deadline Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.DeadlineType
	 * @generated
	 */
	public Adapter createDeadlineTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.DeclaredTypeType <em>Declared Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.DeclaredTypeType
	 * @generated
	 */
	public Adapter createDeclaredTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.EnumerationTypeType <em>Enumeration Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.EnumerationTypeType
	 * @generated
	 */
	public Adapter createEnumerationTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.EnumerationValueType <em>Enumeration Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.EnumerationValueType
	 * @generated
	 */
	public Adapter createEnumerationValueTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ExtendedAttributesType <em>Extended Attributes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributesType
	 * @generated
	 */
	public Adapter createExtendedAttributesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ExtendedAttributeType <em>Extended Attribute Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ExtendedAttributeType
	 * @generated
	 */
	public Adapter createExtendedAttributeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ExternalPackagesType <em>External Packages Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ExternalPackagesType
	 * @generated
	 */
	public Adapter createExternalPackagesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ExternalPackageType <em>External Package Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ExternalPackageType
	 * @generated
	 */
	public Adapter createExternalPackageTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ExternalReferenceType <em>External Reference Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ExternalReferenceType
	 * @generated
	 */
	public Adapter createExternalReferenceTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.FinishModeType <em>Finish Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.FinishModeType
	 * @generated
	 */
	public Adapter createFinishModeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.FormalParametersType <em>Formal Parameters Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.FormalParametersType
	 * @generated
	 */
	public Adapter createFormalParametersTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.FormalParameterType <em>Formal Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.FormalParameterType
	 * @generated
	 */
	public Adapter createFormalParameterTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ImplementationType <em>Implementation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ImplementationType
	 * @generated
	 */
	public Adapter createImplementationTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.JoinType <em>Join Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.JoinType
	 * @generated
	 */
	public Adapter createJoinTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ListTypeType <em>List Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ListTypeType
	 * @generated
	 */
	public Adapter createListTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ManualType <em>Manual Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ManualType
	 * @generated
	 */
	public Adapter createManualTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.MemberType <em>Member Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.MemberType
	 * @generated
	 */
	public Adapter createMemberTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.NoType <em>No Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.NoType
	 * @generated
	 */
	public Adapter createNoTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.PackageHeaderType <em>Package Header Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.PackageHeaderType
	 * @generated
	 */
	public Adapter createPackageHeaderTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.PackageType <em>Package Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.PackageType
	 * @generated
	 */
	public Adapter createPackageTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ParticipantsType <em>Participants Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ParticipantsType
	 * @generated
	 */
	public Adapter createParticipantsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ParticipantType <em>Participant Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ParticipantType
	 * @generated
	 */
	public Adapter createParticipantTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ParticipantTypeType <em>Participant Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ParticipantTypeType
	 * @generated
	 */
	public Adapter createParticipantTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ProcessHeaderType <em>Process Header Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ProcessHeaderType
	 * @generated
	 */
	public Adapter createProcessHeaderTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.RecordTypeType <em>Record Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.RecordTypeType
	 * @generated
	 */
	public Adapter createRecordTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType <em>Redefinable Header Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.RedefinableHeaderType
	 * @generated
	 */
	public Adapter createRedefinableHeaderTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ResponsiblesType <em>Responsibles Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ResponsiblesType
	 * @generated
	 */
	public Adapter createResponsiblesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.RouteType <em>Route Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.RouteType
	 * @generated
	 */
	public Adapter createRouteTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.SchemaTypeType <em>Schema Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.SchemaTypeType
	 * @generated
	 */
	public Adapter createSchemaTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ScriptType <em>Script Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ScriptType
	 * @generated
	 */
	public Adapter createScriptTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.SimulationInformationType <em>Simulation Information Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.SimulationInformationType
	 * @generated
	 */
	public Adapter createSimulationInformationTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.SplitType <em>Split Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.SplitType
	 * @generated
	 */
	public Adapter createSplitTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.StartModeType <em>Start Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.StartModeType
	 * @generated
	 */
	public Adapter createStartModeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.SubFlowType <em>Sub Flow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.SubFlowType
	 * @generated
	 */
	public Adapter createSubFlowTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.TimeEstimationType <em>Time Estimation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.TimeEstimationType
	 * @generated
	 */
	public Adapter createTimeEstimationTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.ToolType <em>Tool Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.ToolType
	 * @generated
	 */
	public Adapter createToolTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.TransitionRefsType <em>Transition Refs Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.TransitionRefsType
	 * @generated
	 */
	public Adapter createTransitionRefsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.TransitionRefType <em>Transition Ref Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.TransitionRefType
	 * @generated
	 */
	public Adapter createTransitionRefTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.TransitionRestrictionsType <em>Transition Restrictions Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.TransitionRestrictionsType
	 * @generated
	 */
	public Adapter createTransitionRestrictionsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.TransitionRestrictionType <em>Transition Restriction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.TransitionRestrictionType
	 * @generated
	 */
	public Adapter createTransitionRestrictionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.TransitionsType <em>Transitions Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.TransitionsType
	 * @generated
	 */
	public Adapter createTransitionsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.TransitionType <em>Transition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.TransitionType
	 * @generated
	 */
	public Adapter createTransitionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.TypeDeclarationsType <em>Type Declarations Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationsType
	 * @generated
	 */
	public Adapter createTypeDeclarationsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.TypeDeclarationType <em>Type Declaration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.TypeDeclarationType
	 * @generated
	 */
	public Adapter createTypeDeclarationTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.UnionTypeType <em>Union Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.UnionTypeType
	 * @generated
	 */
	public Adapter createUnionTypeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.WorkflowProcessesType <em>Workflow Processes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessesType
	 * @generated
	 */
	public Adapter createWorkflowProcessesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.WorkflowProcessType <em>Workflow Process Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.WorkflowProcessType
	 * @generated
	 */
	public Adapter createWorkflowProcessTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.wfmc._2002.xpdl1.XpressionType <em>Xpression Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.wfmc._2002.xpdl1.XpressionType
	 * @generated
	 */
	public Adapter createXpressionTypeAdapter() {
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

} //Xpdl1AdapterFactory
