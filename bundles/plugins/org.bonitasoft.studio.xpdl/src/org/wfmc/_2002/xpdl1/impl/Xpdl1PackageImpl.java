/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
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
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Xpdl1PackageImpl extends EPackageImpl implements Xpdl1Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activitiesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activitySetsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activitySetTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actualParametersTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass automaticTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass basicTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockActivityTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conformanceClassTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataFieldsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataFieldTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deadlineTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass declaredTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationValueTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAttributesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAttributeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalPackagesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalPackageTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalReferenceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass finishModeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formalParametersTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formalParameterTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass implementationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass joinTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manualTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memberTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass noTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageHeaderTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processHeaderTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass recordTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass redefinableHeaderTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass responsiblesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass routeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass schemaTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simulationInformationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass splitTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startModeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subFlowTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeEstimationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toolTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionRefsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionRefTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionRestrictionsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionRestrictionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeDeclarationsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeDeclarationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unionTypeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowProcessesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowProcessTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xpressionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum accessLevelTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum durationUnitTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum executionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum executionType1EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum graphConformanceTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum instantiationTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum isArrayTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum modeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum publicationStatusTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeType1EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeType2EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeType3EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeType4EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeType5EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType accessLevelTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType durationUnitTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType executionTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType executionTypeObject1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType graphConformanceTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType instantiationTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType isArrayTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType modeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType publicationStatusTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeObject1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeObject2EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeObject3EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeObject4EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeObject5EDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Xpdl1PackageImpl() {
		super(eNS_URI, Xpdl1Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link Xpdl1Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Xpdl1Package init() {
		if (isInited) return (Xpdl1Package)EPackage.Registry.INSTANCE.getEPackage(Xpdl1Package.eNS_URI);

		// Obtain or create and register package
		Xpdl1PackageImpl theXpdl1Package = (Xpdl1PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Xpdl1PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Xpdl1PackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theXpdl1Package.createPackageContents();

		// Initialize created meta-data
		theXpdl1Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theXpdl1Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Xpdl1Package.eNS_URI, theXpdl1Package);
		return theXpdl1Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivitiesType() {
		return activitiesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivitiesType_Activity() {
		return (EReference)activitiesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivitySetsType() {
		return activitySetsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivitySetsType_ActivitySet() {
		return (EReference)activitySetsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivitySetType() {
		return activitySetTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivitySetType_Activities() {
		return (EReference)activitySetTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivitySetType_Transitions() {
		return (EReference)activitySetTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivitySetType_Id() {
		return (EAttribute)activitySetTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityType() {
		return activityTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityType_Description() {
		return (EAttribute)activityTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityType_Limit() {
		return (EAttribute)activityTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityType_Route() {
		return (EReference)activityTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityType_Implementation() {
		return (EReference)activityTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityType_BlockActivity() {
		return (EReference)activityTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityType_Performer() {
		return (EAttribute)activityTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityType_StartMode() {
		return (EReference)activityTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityType_FinishMode() {
		return (EReference)activityTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityType_Priority() {
		return (EAttribute)activityTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityType_Deadline() {
		return (EReference)activityTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityType_SimulationInformation() {
		return (EReference)activityTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityType_Icon() {
		return (EAttribute)activityTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityType_Documentation() {
		return (EAttribute)activityTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityType_TransitionRestrictions() {
		return (EReference)activityTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityType_ExtendedAttributes() {
		return (EReference)activityTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityType_Id() {
		return (EAttribute)activityTypeEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityType_Name() {
		return (EAttribute)activityTypeEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActualParametersType() {
		return actualParametersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActualParametersType_ActualParameter() {
		return (EAttribute)actualParametersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplicationsType() {
		return applicationsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationsType_Application() {
		return (EReference)applicationsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplicationType() {
		return applicationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplicationType_Description() {
		return (EAttribute)applicationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationType_FormalParameters() {
		return (EReference)applicationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationType_ExternalReference() {
		return (EReference)applicationTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationType_ExtendedAttributes() {
		return (EReference)applicationTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplicationType_Id() {
		return (EAttribute)applicationTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplicationType_Name() {
		return (EAttribute)applicationTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayTypeType() {
		return arrayTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayTypeType_BasicType() {
		return (EReference)arrayTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayTypeType_DeclaredType() {
		return (EReference)arrayTypeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayTypeType_SchemaType() {
		return (EReference)arrayTypeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayTypeType_ExternalReference() {
		return (EReference)arrayTypeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayTypeType_RecordType() {
		return (EReference)arrayTypeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayTypeType_UnionType() {
		return (EReference)arrayTypeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayTypeType_EnumerationType() {
		return (EReference)arrayTypeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayTypeType_ArrayType() {
		return (EReference)arrayTypeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayTypeType_ListType() {
		return (EReference)arrayTypeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayTypeType_LowerIndex() {
		return (EAttribute)arrayTypeTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayTypeType_UpperIndex() {
		return (EAttribute)arrayTypeTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAutomaticType() {
		return automaticTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBasicTypeType() {
		return basicTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBasicTypeType_Type() {
		return (EAttribute)basicTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlockActivityType() {
		return blockActivityTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlockActivityType_BlockId() {
		return (EAttribute)blockActivityTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionType() {
		return conditionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Mixed() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Group() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionType_Xpression() {
		return (EReference)conditionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Type() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConformanceClassType() {
		return conformanceClassTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConformanceClassType_GraphConformance() {
		return (EAttribute)conformanceClassTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataFieldsType() {
		return dataFieldsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataFieldsType_DataField() {
		return (EReference)dataFieldsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataFieldType() {
		return dataFieldTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataFieldType_DataType() {
		return (EReference)dataFieldTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFieldType_InitialValue() {
		return (EAttribute)dataFieldTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFieldType_Length() {
		return (EAttribute)dataFieldTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFieldType_Description() {
		return (EAttribute)dataFieldTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataFieldType_ExtendedAttributes() {
		return (EReference)dataFieldTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFieldType_Id() {
		return (EAttribute)dataFieldTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFieldType_IsArray() {
		return (EAttribute)dataFieldTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFieldType_Name() {
		return (EAttribute)dataFieldTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataTypeType() {
		return dataTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeType_BasicType() {
		return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeType_DeclaredType() {
		return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeType_SchemaType() {
		return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeType_ExternalReference() {
		return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeType_RecordType() {
		return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeType_UnionType() {
		return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeType_EnumerationType() {
		return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeType_ArrayType() {
		return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTypeType_ListType() {
		return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeadlineType() {
		return deadlineTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeadlineType_DeadlineCondition() {
		return (EReference)deadlineTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeadlineType_ExceptionName() {
		return (EReference)deadlineTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeadlineType_Execution() {
		return (EAttribute)deadlineTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeclaredTypeType() {
		return declaredTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeclaredTypeType_Id() {
		return (EAttribute)declaredTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Activities() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Activity() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ActivitySet() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ActivitySets() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ActualParameter() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ActualParameters() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Application() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Applications() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ArrayType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Author() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Automatic() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_BasicType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_BlockActivity() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Codepage() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Condition() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ConformanceClass() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Cost() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_CostUnit() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Countrykey() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Created() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataField() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataFields() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Deadline() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DeclaredType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Description() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Documentation() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Duration() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EnumerationType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EnumerationValue() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExtendedAttribute() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExtendedAttributes() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExternalPackage() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExternalPackages() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExternalReference() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_FinishMode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_FormalParameter() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_FormalParameters() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Icon() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Implementation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_InitialValue() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(43);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Join() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(44);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Length() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(45);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Limit() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(46);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ListType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(47);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Manual() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(48);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Member() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(49);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_No() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(50);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Package() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(51);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_PackageHeader() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(52);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Participant() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(53);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Participants() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(54);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ParticipantType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(55);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Performer() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(56);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Priority() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(57);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_PriorityUnit() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(58);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ProcessHeader() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(59);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_RecordType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(60);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_RedefinableHeader() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(61);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Responsible() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(62);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Responsibles() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(63);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Route() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(64);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SchemaType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(65);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Script() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(66);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SimulationInformation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(67);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Split() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(68);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StartMode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(69);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SubFlow() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(70);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TimeEstimation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(71);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Tool() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(72);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Transition() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(73);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TransitionRef() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(74);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TransitionRefs() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(75);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TransitionRestriction() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(76);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TransitionRestrictions() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(77);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Transitions() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(78);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TypeDeclaration() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(79);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TypeDeclarations() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(80);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_UnionType() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(81);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ValidFrom() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(82);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ValidTo() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(83);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Vendor() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(84);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Version() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(85);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_WaitingTime() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(86);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_WorkflowProcess() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(87);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_WorkflowProcesses() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(88);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_WorkingTime() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(89);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_XPDLVersion() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(90);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Xpression() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(91);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumerationTypeType() {
		return enumerationTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumerationTypeType_EnumerationValue() {
		return (EReference)enumerationTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumerationValueType() {
		return enumerationValueTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumerationValueType_Name() {
		return (EAttribute)enumerationValueTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAttributesType() {
		return extendedAttributesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtendedAttributesType_ExtendedAttribute() {
		return (EReference)extendedAttributesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAttributeType() {
		return extendedAttributeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttributeType_Mixed() {
		return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttributeType_Group() {
		return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttributeType_Any() {
		return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttributeType_Name() {
		return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttributeType_Value() {
		return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalPackagesType() {
		return externalPackagesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalPackagesType_ExternalPackage() {
		return (EReference)externalPackagesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalPackageType() {
		return externalPackageTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalPackageType_ExtendedAttributes() {
		return (EReference)externalPackageTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalPackageType_Href() {
		return (EAttribute)externalPackageTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalReferenceType() {
		return externalReferenceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalReferenceType_Location() {
		return (EAttribute)externalReferenceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalReferenceType_Namespace() {
		return (EAttribute)externalReferenceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalReferenceType_Xref() {
		return (EAttribute)externalReferenceTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFinishModeType() {
		return finishModeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFinishModeType_Automatic() {
		return (EReference)finishModeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFinishModeType_Manual() {
		return (EReference)finishModeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormalParametersType() {
		return formalParametersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormalParametersType_FormalParameter() {
		return (EReference)formalParametersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormalParameterType() {
		return formalParameterTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormalParameterType_DataType() {
		return (EReference)formalParameterTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormalParameterType_Description() {
		return (EAttribute)formalParameterTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormalParameterType_Id() {
		return (EAttribute)formalParameterTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormalParameterType_Index() {
		return (EAttribute)formalParameterTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormalParameterType_Mode() {
		return (EAttribute)formalParameterTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImplementationType() {
		return implementationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplementationType_No() {
		return (EReference)implementationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplementationType_Tool() {
		return (EReference)implementationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplementationType_SubFlow() {
		return (EReference)implementationTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJoinType() {
		return joinTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Type() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getListTypeType() {
		return listTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListTypeType_BasicType() {
		return (EReference)listTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListTypeType_DeclaredType() {
		return (EReference)listTypeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListTypeType_SchemaType() {
		return (EReference)listTypeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListTypeType_ExternalReference() {
		return (EReference)listTypeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListTypeType_RecordType() {
		return (EReference)listTypeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListTypeType_UnionType() {
		return (EReference)listTypeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListTypeType_EnumerationType() {
		return (EReference)listTypeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListTypeType_ArrayType() {
		return (EReference)listTypeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListTypeType_ListType() {
		return (EReference)listTypeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManualType() {
		return manualTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMemberType() {
		return memberTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberType_BasicType() {
		return (EReference)memberTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberType_DeclaredType() {
		return (EReference)memberTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberType_SchemaType() {
		return (EReference)memberTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberType_ExternalReference() {
		return (EReference)memberTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberType_RecordType() {
		return (EReference)memberTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberType_UnionType() {
		return (EReference)memberTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberType_EnumerationType() {
		return (EReference)memberTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberType_ArrayType() {
		return (EReference)memberTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberType_ListType() {
		return (EReference)memberTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNoType() {
		return noTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageHeaderType() {
		return packageHeaderTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageHeaderType_XPDLVersion() {
		return (EAttribute)packageHeaderTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageHeaderType_Vendor() {
		return (EAttribute)packageHeaderTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageHeaderType_Created() {
		return (EAttribute)packageHeaderTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageHeaderType_Description() {
		return (EAttribute)packageHeaderTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageHeaderType_Documentation() {
		return (EAttribute)packageHeaderTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageHeaderType_PriorityUnit() {
		return (EAttribute)packageHeaderTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageHeaderType_CostUnit() {
		return (EAttribute)packageHeaderTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageType() {
		return packageTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_PackageHeader() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_RedefinableHeader() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_ConformanceClass() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_Script() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_ExternalPackages() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_TypeDeclarations() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_Participants() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_Applications() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_DataFields() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_WorkflowProcesses() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageType_ExtendedAttributes() {
		return (EReference)packageTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageType_Id() {
		return (EAttribute)packageTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageType_Name() {
		return (EAttribute)packageTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParticipantsType() {
		return participantsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParticipantsType_Participant() {
		return (EReference)participantsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParticipantType() {
		return participantTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParticipantType_ParticipantType() {
		return (EReference)participantTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParticipantType_Description() {
		return (EAttribute)participantTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParticipantType_ExternalReference() {
		return (EReference)participantTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParticipantType_ExtendedAttributes() {
		return (EReference)participantTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParticipantType_Id() {
		return (EAttribute)participantTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParticipantType_Name() {
		return (EAttribute)participantTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParticipantTypeType() {
		return participantTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParticipantTypeType_Type() {
		return (EAttribute)participantTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessHeaderType() {
		return processHeaderTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessHeaderType_Created() {
		return (EAttribute)processHeaderTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessHeaderType_Description() {
		return (EAttribute)processHeaderTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessHeaderType_Priority() {
		return (EAttribute)processHeaderTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessHeaderType_Limit() {
		return (EAttribute)processHeaderTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessHeaderType_ValidFrom() {
		return (EAttribute)processHeaderTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessHeaderType_ValidTo() {
		return (EAttribute)processHeaderTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessHeaderType_TimeEstimation() {
		return (EReference)processHeaderTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessHeaderType_DurationUnit() {
		return (EAttribute)processHeaderTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRecordTypeType() {
		return recordTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRecordTypeType_Member() {
		return (EReference)recordTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRedefinableHeaderType() {
		return redefinableHeaderTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRedefinableHeaderType_Author() {
		return (EAttribute)redefinableHeaderTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRedefinableHeaderType_Version() {
		return (EAttribute)redefinableHeaderTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRedefinableHeaderType_Codepage() {
		return (EAttribute)redefinableHeaderTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRedefinableHeaderType_Countrykey() {
		return (EAttribute)redefinableHeaderTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRedefinableHeaderType_Responsibles() {
		return (EReference)redefinableHeaderTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRedefinableHeaderType_PublicationStatus() {
		return (EAttribute)redefinableHeaderTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResponsiblesType() {
		return responsiblesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResponsiblesType_Responsible() {
		return (EAttribute)responsiblesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRouteType() {
		return routeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSchemaTypeType() {
		return schemaTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSchemaTypeType_Any() {
		return (EAttribute)schemaTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScriptType() {
		return scriptTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptType_Grammar() {
		return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptType_Type() {
		return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptType_Version() {
		return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimulationInformationType() {
		return simulationInformationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSimulationInformationType_Cost() {
		return (EAttribute)simulationInformationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSimulationInformationType_TimeEstimation() {
		return (EReference)simulationInformationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSimulationInformationType_Instantiation() {
		return (EAttribute)simulationInformationTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSplitType() {
		return splitTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSplitType_TransitionRefs() {
		return (EReference)splitTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSplitType_Type() {
		return (EAttribute)splitTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStartModeType() {
		return startModeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartModeType_Automatic() {
		return (EReference)startModeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartModeType_Manual() {
		return (EReference)startModeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubFlowType() {
		return subFlowTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubFlowType_ActualParameters() {
		return (EReference)subFlowTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubFlowType_Execution() {
		return (EAttribute)subFlowTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubFlowType_Id() {
		return (EAttribute)subFlowTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimeEstimationType() {
		return timeEstimationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeEstimationType_WaitingTime() {
		return (EAttribute)timeEstimationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeEstimationType_WorkingTime() {
		return (EAttribute)timeEstimationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeEstimationType_Duration() {
		return (EAttribute)timeEstimationTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getToolType() {
		return toolTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getToolType_ActualParameters() {
		return (EReference)toolTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getToolType_Description() {
		return (EAttribute)toolTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getToolType_ExtendedAttributes() {
		return (EReference)toolTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getToolType_Id() {
		return (EAttribute)toolTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getToolType_Type() {
		return (EAttribute)toolTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionRefsType() {
		return transitionRefsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionRefsType_TransitionRef() {
		return (EReference)transitionRefsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionRefType() {
		return transitionRefTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionRefType_Id() {
		return (EAttribute)transitionRefTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionRestrictionsType() {
		return transitionRestrictionsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionRestrictionsType_TransitionRestriction() {
		return (EReference)transitionRestrictionsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionRestrictionType() {
		return transitionRestrictionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionRestrictionType_Join() {
		return (EReference)transitionRestrictionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionRestrictionType_Split() {
		return (EReference)transitionRestrictionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionsType() {
		return transitionsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionsType_Transition() {
		return (EReference)transitionsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionType() {
		return transitionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionType_Condition() {
		return (EReference)transitionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionType_Description() {
		return (EAttribute)transitionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionType_ExtendedAttributes() {
		return (EReference)transitionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionType_From() {
		return (EAttribute)transitionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionType_Id() {
		return (EAttribute)transitionTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionType_Name() {
		return (EAttribute)transitionTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionType_To() {
		return (EAttribute)transitionTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDeclarationsType() {
		return typeDeclarationsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationsType_TypeDeclaration() {
		return (EReference)typeDeclarationsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDeclarationType() {
		return typeDeclarationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_BasicType() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_DeclaredType() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_SchemaType() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_ExternalReference() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_RecordType() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_UnionType() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_EnumerationType() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_ArrayType() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_ListType() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeDeclarationType_Description() {
		return (EAttribute)typeDeclarationTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationType_ExtendedAttributes() {
		return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeDeclarationType_Id() {
		return (EAttribute)typeDeclarationTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeDeclarationType_Name() {
		return (EAttribute)typeDeclarationTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnionTypeType() {
		return unionTypeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnionTypeType_Member() {
		return (EReference)unionTypeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkflowProcessesType() {
		return workflowProcessesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessesType_WorkflowProcess() {
		return (EReference)workflowProcessesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkflowProcessType() {
		return workflowProcessTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_ProcessHeader() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_RedefinableHeader() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_FormalParameters() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_DataFields() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_Participants() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_Applications() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_ActivitySets() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_Activities() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_Transitions() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowProcessType_ExtendedAttributes() {
		return (EReference)workflowProcessTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkflowProcessType_AccessLevel() {
		return (EAttribute)workflowProcessTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkflowProcessType_Id() {
		return (EAttribute)workflowProcessTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkflowProcessType_Name() {
		return (EAttribute)workflowProcessTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXpressionType() {
		return xpressionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getXpressionType_Mixed() {
		return (EAttribute)xpressionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getXpressionType_Group() {
		return (EAttribute)xpressionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getXpressionType_Any() {
		return (EAttribute)xpressionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAccessLevelType() {
		return accessLevelTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDurationUnitType() {
		return durationUnitTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getExecutionType() {
		return executionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getExecutionType1() {
		return executionType1EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGraphConformanceType() {
		return graphConformanceTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInstantiationType() {
		return instantiationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getIsArrayType() {
		return isArrayTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getModeType() {
		return modeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPublicationStatusType() {
		return publicationStatusTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeType() {
		return typeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeType1() {
		return typeType1EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeType2() {
		return typeType2EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeType3() {
		return typeType3EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeType4() {
		return typeType4EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeType5() {
		return typeType5EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getAccessLevelTypeObject() {
		return accessLevelTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDurationUnitTypeObject() {
		return durationUnitTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getExecutionTypeObject() {
		return executionTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getExecutionTypeObject1() {
		return executionTypeObject1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGraphConformanceTypeObject() {
		return graphConformanceTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getInstantiationTypeObject() {
		return instantiationTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIsArrayTypeObject() {
		return isArrayTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getModeTypeObject() {
		return modeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPublicationStatusTypeObject() {
		return publicationStatusTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeTypeObject() {
		return typeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeTypeObject1() {
		return typeTypeObject1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeTypeObject2() {
		return typeTypeObject2EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeTypeObject3() {
		return typeTypeObject3EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeTypeObject4() {
		return typeTypeObject4EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeTypeObject5() {
		return typeTypeObject5EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Xpdl1Factory getXpdl1Factory() {
		return (Xpdl1Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		activitiesTypeEClass = createEClass(ACTIVITIES_TYPE);
		createEReference(activitiesTypeEClass, ACTIVITIES_TYPE__ACTIVITY);

		activitySetsTypeEClass = createEClass(ACTIVITY_SETS_TYPE);
		createEReference(activitySetsTypeEClass, ACTIVITY_SETS_TYPE__ACTIVITY_SET);

		activitySetTypeEClass = createEClass(ACTIVITY_SET_TYPE);
		createEReference(activitySetTypeEClass, ACTIVITY_SET_TYPE__ACTIVITIES);
		createEReference(activitySetTypeEClass, ACTIVITY_SET_TYPE__TRANSITIONS);
		createEAttribute(activitySetTypeEClass, ACTIVITY_SET_TYPE__ID);

		activityTypeEClass = createEClass(ACTIVITY_TYPE);
		createEAttribute(activityTypeEClass, ACTIVITY_TYPE__DESCRIPTION);
		createEAttribute(activityTypeEClass, ACTIVITY_TYPE__LIMIT);
		createEReference(activityTypeEClass, ACTIVITY_TYPE__ROUTE);
		createEReference(activityTypeEClass, ACTIVITY_TYPE__IMPLEMENTATION);
		createEReference(activityTypeEClass, ACTIVITY_TYPE__BLOCK_ACTIVITY);
		createEAttribute(activityTypeEClass, ACTIVITY_TYPE__PERFORMER);
		createEReference(activityTypeEClass, ACTIVITY_TYPE__START_MODE);
		createEReference(activityTypeEClass, ACTIVITY_TYPE__FINISH_MODE);
		createEAttribute(activityTypeEClass, ACTIVITY_TYPE__PRIORITY);
		createEReference(activityTypeEClass, ACTIVITY_TYPE__DEADLINE);
		createEReference(activityTypeEClass, ACTIVITY_TYPE__SIMULATION_INFORMATION);
		createEAttribute(activityTypeEClass, ACTIVITY_TYPE__ICON);
		createEAttribute(activityTypeEClass, ACTIVITY_TYPE__DOCUMENTATION);
		createEReference(activityTypeEClass, ACTIVITY_TYPE__TRANSITION_RESTRICTIONS);
		createEReference(activityTypeEClass, ACTIVITY_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(activityTypeEClass, ACTIVITY_TYPE__ID);
		createEAttribute(activityTypeEClass, ACTIVITY_TYPE__NAME);

		actualParametersTypeEClass = createEClass(ACTUAL_PARAMETERS_TYPE);
		createEAttribute(actualParametersTypeEClass, ACTUAL_PARAMETERS_TYPE__ACTUAL_PARAMETER);

		applicationsTypeEClass = createEClass(APPLICATIONS_TYPE);
		createEReference(applicationsTypeEClass, APPLICATIONS_TYPE__APPLICATION);

		applicationTypeEClass = createEClass(APPLICATION_TYPE);
		createEAttribute(applicationTypeEClass, APPLICATION_TYPE__DESCRIPTION);
		createEReference(applicationTypeEClass, APPLICATION_TYPE__FORMAL_PARAMETERS);
		createEReference(applicationTypeEClass, APPLICATION_TYPE__EXTERNAL_REFERENCE);
		createEReference(applicationTypeEClass, APPLICATION_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(applicationTypeEClass, APPLICATION_TYPE__ID);
		createEAttribute(applicationTypeEClass, APPLICATION_TYPE__NAME);

		arrayTypeTypeEClass = createEClass(ARRAY_TYPE_TYPE);
		createEReference(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__BASIC_TYPE);
		createEReference(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__DECLARED_TYPE);
		createEReference(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__SCHEMA_TYPE);
		createEReference(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__EXTERNAL_REFERENCE);
		createEReference(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__RECORD_TYPE);
		createEReference(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__UNION_TYPE);
		createEReference(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__ENUMERATION_TYPE);
		createEReference(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__ARRAY_TYPE);
		createEReference(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__LIST_TYPE);
		createEAttribute(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__LOWER_INDEX);
		createEAttribute(arrayTypeTypeEClass, ARRAY_TYPE_TYPE__UPPER_INDEX);

		automaticTypeEClass = createEClass(AUTOMATIC_TYPE);

		basicTypeTypeEClass = createEClass(BASIC_TYPE_TYPE);
		createEAttribute(basicTypeTypeEClass, BASIC_TYPE_TYPE__TYPE);

		blockActivityTypeEClass = createEClass(BLOCK_ACTIVITY_TYPE);
		createEAttribute(blockActivityTypeEClass, BLOCK_ACTIVITY_TYPE__BLOCK_ID);

		conditionTypeEClass = createEClass(CONDITION_TYPE);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__MIXED);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__GROUP);
		createEReference(conditionTypeEClass, CONDITION_TYPE__XPRESSION);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__TYPE);

		conformanceClassTypeEClass = createEClass(CONFORMANCE_CLASS_TYPE);
		createEAttribute(conformanceClassTypeEClass, CONFORMANCE_CLASS_TYPE__GRAPH_CONFORMANCE);

		dataFieldsTypeEClass = createEClass(DATA_FIELDS_TYPE);
		createEReference(dataFieldsTypeEClass, DATA_FIELDS_TYPE__DATA_FIELD);

		dataFieldTypeEClass = createEClass(DATA_FIELD_TYPE);
		createEReference(dataFieldTypeEClass, DATA_FIELD_TYPE__DATA_TYPE);
		createEAttribute(dataFieldTypeEClass, DATA_FIELD_TYPE__INITIAL_VALUE);
		createEAttribute(dataFieldTypeEClass, DATA_FIELD_TYPE__LENGTH);
		createEAttribute(dataFieldTypeEClass, DATA_FIELD_TYPE__DESCRIPTION);
		createEReference(dataFieldTypeEClass, DATA_FIELD_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(dataFieldTypeEClass, DATA_FIELD_TYPE__ID);
		createEAttribute(dataFieldTypeEClass, DATA_FIELD_TYPE__IS_ARRAY);
		createEAttribute(dataFieldTypeEClass, DATA_FIELD_TYPE__NAME);

		dataTypeTypeEClass = createEClass(DATA_TYPE_TYPE);
		createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__BASIC_TYPE);
		createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__DECLARED_TYPE);
		createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__SCHEMA_TYPE);
		createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__EXTERNAL_REFERENCE);
		createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__RECORD_TYPE);
		createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__UNION_TYPE);
		createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__ENUMERATION_TYPE);
		createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__ARRAY_TYPE);
		createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__LIST_TYPE);

		deadlineTypeEClass = createEClass(DEADLINE_TYPE);
		createEReference(deadlineTypeEClass, DEADLINE_TYPE__DEADLINE_CONDITION);
		createEReference(deadlineTypeEClass, DEADLINE_TYPE__EXCEPTION_NAME);
		createEAttribute(deadlineTypeEClass, DEADLINE_TYPE__EXECUTION);

		declaredTypeTypeEClass = createEClass(DECLARED_TYPE_TYPE);
		createEAttribute(declaredTypeTypeEClass, DECLARED_TYPE_TYPE__ID);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ACTIVITIES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ACTIVITY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ACTIVITY_SET);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ACTIVITY_SETS);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__ACTUAL_PARAMETER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ACTUAL_PARAMETERS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__APPLICATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__APPLICATIONS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ARRAY_TYPE);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__AUTHOR);
		createEReference(documentRootEClass, DOCUMENT_ROOT__AUTOMATIC);
		createEReference(documentRootEClass, DOCUMENT_ROOT__BASIC_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__BLOCK_ACTIVITY);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__CODEPAGE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONDITION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONFORMANCE_CLASS);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__COST);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__COST_UNIT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__COUNTRYKEY);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__CREATED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DATA_FIELD);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DATA_FIELDS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DATA_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DEADLINE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DECLARED_TYPE);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__DESCRIPTION);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__DOCUMENTATION);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__DURATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ENUMERATION_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ENUMERATION_VALUE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EXTENDED_ATTRIBUTE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EXTENDED_ATTRIBUTES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EXTERNAL_PACKAGE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EXTERNAL_PACKAGES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EXTERNAL_REFERENCE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FINISH_MODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FORMAL_PARAMETER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FORMAL_PARAMETERS);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__ICON);
		createEReference(documentRootEClass, DOCUMENT_ROOT__IMPLEMENTATION);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__INITIAL_VALUE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__JOIN);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__LENGTH);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__LIMIT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__LIST_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MANUAL);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MEMBER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__NO);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PACKAGE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PACKAGE_HEADER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PARTICIPANT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PARTICIPANTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PARTICIPANT_TYPE);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__PERFORMER);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__PRIORITY);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__PRIORITY_UNIT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROCESS_HEADER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RECORD_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__REDEFINABLE_HEADER);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__RESPONSIBLE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RESPONSIBLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ROUTE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SCHEMA_TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SCRIPT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SIMULATION_INFORMATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SPLIT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__START_MODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SUB_FLOW);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TIME_ESTIMATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TOOL);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSITION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSITION_REF);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSITION_REFS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSITION_RESTRICTION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSITION_RESTRICTIONS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSITIONS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TYPE_DECLARATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TYPE_DECLARATIONS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__UNION_TYPE);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__VALID_FROM);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__VALID_TO);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__VENDOR);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__VERSION);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__WAITING_TIME);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WORKFLOW_PROCESS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WORKFLOW_PROCESSES);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__WORKING_TIME);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__XPDL_VERSION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XPRESSION);

		enumerationTypeTypeEClass = createEClass(ENUMERATION_TYPE_TYPE);
		createEReference(enumerationTypeTypeEClass, ENUMERATION_TYPE_TYPE__ENUMERATION_VALUE);

		enumerationValueTypeEClass = createEClass(ENUMERATION_VALUE_TYPE);
		createEAttribute(enumerationValueTypeEClass, ENUMERATION_VALUE_TYPE__NAME);

		extendedAttributesTypeEClass = createEClass(EXTENDED_ATTRIBUTES_TYPE);
		createEReference(extendedAttributesTypeEClass, EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE);

		extendedAttributeTypeEClass = createEClass(EXTENDED_ATTRIBUTE_TYPE);
		createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__MIXED);
		createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__GROUP);
		createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__ANY);
		createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__NAME);
		createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__VALUE);

		externalPackagesTypeEClass = createEClass(EXTERNAL_PACKAGES_TYPE);
		createEReference(externalPackagesTypeEClass, EXTERNAL_PACKAGES_TYPE__EXTERNAL_PACKAGE);

		externalPackageTypeEClass = createEClass(EXTERNAL_PACKAGE_TYPE);
		createEReference(externalPackageTypeEClass, EXTERNAL_PACKAGE_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(externalPackageTypeEClass, EXTERNAL_PACKAGE_TYPE__HREF);

		externalReferenceTypeEClass = createEClass(EXTERNAL_REFERENCE_TYPE);
		createEAttribute(externalReferenceTypeEClass, EXTERNAL_REFERENCE_TYPE__LOCATION);
		createEAttribute(externalReferenceTypeEClass, EXTERNAL_REFERENCE_TYPE__NAMESPACE);
		createEAttribute(externalReferenceTypeEClass, EXTERNAL_REFERENCE_TYPE__XREF);

		finishModeTypeEClass = createEClass(FINISH_MODE_TYPE);
		createEReference(finishModeTypeEClass, FINISH_MODE_TYPE__AUTOMATIC);
		createEReference(finishModeTypeEClass, FINISH_MODE_TYPE__MANUAL);

		formalParametersTypeEClass = createEClass(FORMAL_PARAMETERS_TYPE);
		createEReference(formalParametersTypeEClass, FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER);

		formalParameterTypeEClass = createEClass(FORMAL_PARAMETER_TYPE);
		createEReference(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__DATA_TYPE);
		createEAttribute(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__DESCRIPTION);
		createEAttribute(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__ID);
		createEAttribute(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__INDEX);
		createEAttribute(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__MODE);

		implementationTypeEClass = createEClass(IMPLEMENTATION_TYPE);
		createEReference(implementationTypeEClass, IMPLEMENTATION_TYPE__NO);
		createEReference(implementationTypeEClass, IMPLEMENTATION_TYPE__TOOL);
		createEReference(implementationTypeEClass, IMPLEMENTATION_TYPE__SUB_FLOW);

		joinTypeEClass = createEClass(JOIN_TYPE);
		createEAttribute(joinTypeEClass, JOIN_TYPE__TYPE);

		listTypeTypeEClass = createEClass(LIST_TYPE_TYPE);
		createEReference(listTypeTypeEClass, LIST_TYPE_TYPE__BASIC_TYPE);
		createEReference(listTypeTypeEClass, LIST_TYPE_TYPE__DECLARED_TYPE);
		createEReference(listTypeTypeEClass, LIST_TYPE_TYPE__SCHEMA_TYPE);
		createEReference(listTypeTypeEClass, LIST_TYPE_TYPE__EXTERNAL_REFERENCE);
		createEReference(listTypeTypeEClass, LIST_TYPE_TYPE__RECORD_TYPE);
		createEReference(listTypeTypeEClass, LIST_TYPE_TYPE__UNION_TYPE);
		createEReference(listTypeTypeEClass, LIST_TYPE_TYPE__ENUMERATION_TYPE);
		createEReference(listTypeTypeEClass, LIST_TYPE_TYPE__ARRAY_TYPE);
		createEReference(listTypeTypeEClass, LIST_TYPE_TYPE__LIST_TYPE);

		manualTypeEClass = createEClass(MANUAL_TYPE);

		memberTypeEClass = createEClass(MEMBER_TYPE);
		createEReference(memberTypeEClass, MEMBER_TYPE__BASIC_TYPE);
		createEReference(memberTypeEClass, MEMBER_TYPE__DECLARED_TYPE);
		createEReference(memberTypeEClass, MEMBER_TYPE__SCHEMA_TYPE);
		createEReference(memberTypeEClass, MEMBER_TYPE__EXTERNAL_REFERENCE);
		createEReference(memberTypeEClass, MEMBER_TYPE__RECORD_TYPE);
		createEReference(memberTypeEClass, MEMBER_TYPE__UNION_TYPE);
		createEReference(memberTypeEClass, MEMBER_TYPE__ENUMERATION_TYPE);
		createEReference(memberTypeEClass, MEMBER_TYPE__ARRAY_TYPE);
		createEReference(memberTypeEClass, MEMBER_TYPE__LIST_TYPE);

		noTypeEClass = createEClass(NO_TYPE);

		packageHeaderTypeEClass = createEClass(PACKAGE_HEADER_TYPE);
		createEAttribute(packageHeaderTypeEClass, PACKAGE_HEADER_TYPE__XPDL_VERSION);
		createEAttribute(packageHeaderTypeEClass, PACKAGE_HEADER_TYPE__VENDOR);
		createEAttribute(packageHeaderTypeEClass, PACKAGE_HEADER_TYPE__CREATED);
		createEAttribute(packageHeaderTypeEClass, PACKAGE_HEADER_TYPE__DESCRIPTION);
		createEAttribute(packageHeaderTypeEClass, PACKAGE_HEADER_TYPE__DOCUMENTATION);
		createEAttribute(packageHeaderTypeEClass, PACKAGE_HEADER_TYPE__PRIORITY_UNIT);
		createEAttribute(packageHeaderTypeEClass, PACKAGE_HEADER_TYPE__COST_UNIT);

		packageTypeEClass = createEClass(PACKAGE_TYPE);
		createEReference(packageTypeEClass, PACKAGE_TYPE__PACKAGE_HEADER);
		createEReference(packageTypeEClass, PACKAGE_TYPE__REDEFINABLE_HEADER);
		createEReference(packageTypeEClass, PACKAGE_TYPE__CONFORMANCE_CLASS);
		createEReference(packageTypeEClass, PACKAGE_TYPE__SCRIPT);
		createEReference(packageTypeEClass, PACKAGE_TYPE__EXTERNAL_PACKAGES);
		createEReference(packageTypeEClass, PACKAGE_TYPE__TYPE_DECLARATIONS);
		createEReference(packageTypeEClass, PACKAGE_TYPE__PARTICIPANTS);
		createEReference(packageTypeEClass, PACKAGE_TYPE__APPLICATIONS);
		createEReference(packageTypeEClass, PACKAGE_TYPE__DATA_FIELDS);
		createEReference(packageTypeEClass, PACKAGE_TYPE__WORKFLOW_PROCESSES);
		createEReference(packageTypeEClass, PACKAGE_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(packageTypeEClass, PACKAGE_TYPE__ID);
		createEAttribute(packageTypeEClass, PACKAGE_TYPE__NAME);

		participantsTypeEClass = createEClass(PARTICIPANTS_TYPE);
		createEReference(participantsTypeEClass, PARTICIPANTS_TYPE__PARTICIPANT);

		participantTypeEClass = createEClass(PARTICIPANT_TYPE);
		createEReference(participantTypeEClass, PARTICIPANT_TYPE__PARTICIPANT_TYPE);
		createEAttribute(participantTypeEClass, PARTICIPANT_TYPE__DESCRIPTION);
		createEReference(participantTypeEClass, PARTICIPANT_TYPE__EXTERNAL_REFERENCE);
		createEReference(participantTypeEClass, PARTICIPANT_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(participantTypeEClass, PARTICIPANT_TYPE__ID);
		createEAttribute(participantTypeEClass, PARTICIPANT_TYPE__NAME);

		participantTypeTypeEClass = createEClass(PARTICIPANT_TYPE_TYPE);
		createEAttribute(participantTypeTypeEClass, PARTICIPANT_TYPE_TYPE__TYPE);

		processHeaderTypeEClass = createEClass(PROCESS_HEADER_TYPE);
		createEAttribute(processHeaderTypeEClass, PROCESS_HEADER_TYPE__CREATED);
		createEAttribute(processHeaderTypeEClass, PROCESS_HEADER_TYPE__DESCRIPTION);
		createEAttribute(processHeaderTypeEClass, PROCESS_HEADER_TYPE__PRIORITY);
		createEAttribute(processHeaderTypeEClass, PROCESS_HEADER_TYPE__LIMIT);
		createEAttribute(processHeaderTypeEClass, PROCESS_HEADER_TYPE__VALID_FROM);
		createEAttribute(processHeaderTypeEClass, PROCESS_HEADER_TYPE__VALID_TO);
		createEReference(processHeaderTypeEClass, PROCESS_HEADER_TYPE__TIME_ESTIMATION);
		createEAttribute(processHeaderTypeEClass, PROCESS_HEADER_TYPE__DURATION_UNIT);

		recordTypeTypeEClass = createEClass(RECORD_TYPE_TYPE);
		createEReference(recordTypeTypeEClass, RECORD_TYPE_TYPE__MEMBER);

		redefinableHeaderTypeEClass = createEClass(REDEFINABLE_HEADER_TYPE);
		createEAttribute(redefinableHeaderTypeEClass, REDEFINABLE_HEADER_TYPE__AUTHOR);
		createEAttribute(redefinableHeaderTypeEClass, REDEFINABLE_HEADER_TYPE__VERSION);
		createEAttribute(redefinableHeaderTypeEClass, REDEFINABLE_HEADER_TYPE__CODEPAGE);
		createEAttribute(redefinableHeaderTypeEClass, REDEFINABLE_HEADER_TYPE__COUNTRYKEY);
		createEReference(redefinableHeaderTypeEClass, REDEFINABLE_HEADER_TYPE__RESPONSIBLES);
		createEAttribute(redefinableHeaderTypeEClass, REDEFINABLE_HEADER_TYPE__PUBLICATION_STATUS);

		responsiblesTypeEClass = createEClass(RESPONSIBLES_TYPE);
		createEAttribute(responsiblesTypeEClass, RESPONSIBLES_TYPE__RESPONSIBLE);

		routeTypeEClass = createEClass(ROUTE_TYPE);

		schemaTypeTypeEClass = createEClass(SCHEMA_TYPE_TYPE);
		createEAttribute(schemaTypeTypeEClass, SCHEMA_TYPE_TYPE__ANY);

		scriptTypeEClass = createEClass(SCRIPT_TYPE);
		createEAttribute(scriptTypeEClass, SCRIPT_TYPE__GRAMMAR);
		createEAttribute(scriptTypeEClass, SCRIPT_TYPE__TYPE);
		createEAttribute(scriptTypeEClass, SCRIPT_TYPE__VERSION);

		simulationInformationTypeEClass = createEClass(SIMULATION_INFORMATION_TYPE);
		createEAttribute(simulationInformationTypeEClass, SIMULATION_INFORMATION_TYPE__COST);
		createEReference(simulationInformationTypeEClass, SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION);
		createEAttribute(simulationInformationTypeEClass, SIMULATION_INFORMATION_TYPE__INSTANTIATION);

		splitTypeEClass = createEClass(SPLIT_TYPE);
		createEReference(splitTypeEClass, SPLIT_TYPE__TRANSITION_REFS);
		createEAttribute(splitTypeEClass, SPLIT_TYPE__TYPE);

		startModeTypeEClass = createEClass(START_MODE_TYPE);
		createEReference(startModeTypeEClass, START_MODE_TYPE__AUTOMATIC);
		createEReference(startModeTypeEClass, START_MODE_TYPE__MANUAL);

		subFlowTypeEClass = createEClass(SUB_FLOW_TYPE);
		createEReference(subFlowTypeEClass, SUB_FLOW_TYPE__ACTUAL_PARAMETERS);
		createEAttribute(subFlowTypeEClass, SUB_FLOW_TYPE__EXECUTION);
		createEAttribute(subFlowTypeEClass, SUB_FLOW_TYPE__ID);

		timeEstimationTypeEClass = createEClass(TIME_ESTIMATION_TYPE);
		createEAttribute(timeEstimationTypeEClass, TIME_ESTIMATION_TYPE__WAITING_TIME);
		createEAttribute(timeEstimationTypeEClass, TIME_ESTIMATION_TYPE__WORKING_TIME);
		createEAttribute(timeEstimationTypeEClass, TIME_ESTIMATION_TYPE__DURATION);

		toolTypeEClass = createEClass(TOOL_TYPE);
		createEReference(toolTypeEClass, TOOL_TYPE__ACTUAL_PARAMETERS);
		createEAttribute(toolTypeEClass, TOOL_TYPE__DESCRIPTION);
		createEReference(toolTypeEClass, TOOL_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(toolTypeEClass, TOOL_TYPE__ID);
		createEAttribute(toolTypeEClass, TOOL_TYPE__TYPE);

		transitionRefsTypeEClass = createEClass(TRANSITION_REFS_TYPE);
		createEReference(transitionRefsTypeEClass, TRANSITION_REFS_TYPE__TRANSITION_REF);

		transitionRefTypeEClass = createEClass(TRANSITION_REF_TYPE);
		createEAttribute(transitionRefTypeEClass, TRANSITION_REF_TYPE__ID);

		transitionRestrictionsTypeEClass = createEClass(TRANSITION_RESTRICTIONS_TYPE);
		createEReference(transitionRestrictionsTypeEClass, TRANSITION_RESTRICTIONS_TYPE__TRANSITION_RESTRICTION);

		transitionRestrictionTypeEClass = createEClass(TRANSITION_RESTRICTION_TYPE);
		createEReference(transitionRestrictionTypeEClass, TRANSITION_RESTRICTION_TYPE__JOIN);
		createEReference(transitionRestrictionTypeEClass, TRANSITION_RESTRICTION_TYPE__SPLIT);

		transitionsTypeEClass = createEClass(TRANSITIONS_TYPE);
		createEReference(transitionsTypeEClass, TRANSITIONS_TYPE__TRANSITION);

		transitionTypeEClass = createEClass(TRANSITION_TYPE);
		createEReference(transitionTypeEClass, TRANSITION_TYPE__CONDITION);
		createEAttribute(transitionTypeEClass, TRANSITION_TYPE__DESCRIPTION);
		createEReference(transitionTypeEClass, TRANSITION_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(transitionTypeEClass, TRANSITION_TYPE__FROM);
		createEAttribute(transitionTypeEClass, TRANSITION_TYPE__ID);
		createEAttribute(transitionTypeEClass, TRANSITION_TYPE__NAME);
		createEAttribute(transitionTypeEClass, TRANSITION_TYPE__TO);

		typeDeclarationsTypeEClass = createEClass(TYPE_DECLARATIONS_TYPE);
		createEReference(typeDeclarationsTypeEClass, TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION);

		typeDeclarationTypeEClass = createEClass(TYPE_DECLARATION_TYPE);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__BASIC_TYPE);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__DECLARED_TYPE);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__SCHEMA_TYPE);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__RECORD_TYPE);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__UNION_TYPE);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__ENUMERATION_TYPE);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__ARRAY_TYPE);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__LIST_TYPE);
		createEAttribute(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__DESCRIPTION);
		createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__ID);
		createEAttribute(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__NAME);

		unionTypeTypeEClass = createEClass(UNION_TYPE_TYPE);
		createEReference(unionTypeTypeEClass, UNION_TYPE_TYPE__MEMBER);

		workflowProcessesTypeEClass = createEClass(WORKFLOW_PROCESSES_TYPE);
		createEReference(workflowProcessesTypeEClass, WORKFLOW_PROCESSES_TYPE__WORKFLOW_PROCESS);

		workflowProcessTypeEClass = createEClass(WORKFLOW_PROCESS_TYPE);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__PROCESS_HEADER);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__DATA_FIELDS);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__PARTICIPANTS);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__APPLICATIONS);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__ACTIVITIES);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__TRANSITIONS);
		createEReference(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES);
		createEAttribute(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__ACCESS_LEVEL);
		createEAttribute(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__ID);
		createEAttribute(workflowProcessTypeEClass, WORKFLOW_PROCESS_TYPE__NAME);

		xpressionTypeEClass = createEClass(XPRESSION_TYPE);
		createEAttribute(xpressionTypeEClass, XPRESSION_TYPE__MIXED);
		createEAttribute(xpressionTypeEClass, XPRESSION_TYPE__GROUP);
		createEAttribute(xpressionTypeEClass, XPRESSION_TYPE__ANY);

		// Create enums
		accessLevelTypeEEnum = createEEnum(ACCESS_LEVEL_TYPE);
		durationUnitTypeEEnum = createEEnum(DURATION_UNIT_TYPE);
		executionTypeEEnum = createEEnum(EXECUTION_TYPE);
		executionType1EEnum = createEEnum(EXECUTION_TYPE1);
		graphConformanceTypeEEnum = createEEnum(GRAPH_CONFORMANCE_TYPE);
		instantiationTypeEEnum = createEEnum(INSTANTIATION_TYPE);
		isArrayTypeEEnum = createEEnum(IS_ARRAY_TYPE);
		modeTypeEEnum = createEEnum(MODE_TYPE);
		publicationStatusTypeEEnum = createEEnum(PUBLICATION_STATUS_TYPE);
		typeTypeEEnum = createEEnum(TYPE_TYPE);
		typeType1EEnum = createEEnum(TYPE_TYPE1);
		typeType2EEnum = createEEnum(TYPE_TYPE2);
		typeType3EEnum = createEEnum(TYPE_TYPE3);
		typeType4EEnum = createEEnum(TYPE_TYPE4);
		typeType5EEnum = createEEnum(TYPE_TYPE5);

		// Create data types
		accessLevelTypeObjectEDataType = createEDataType(ACCESS_LEVEL_TYPE_OBJECT);
		durationUnitTypeObjectEDataType = createEDataType(DURATION_UNIT_TYPE_OBJECT);
		executionTypeObjectEDataType = createEDataType(EXECUTION_TYPE_OBJECT);
		executionTypeObject1EDataType = createEDataType(EXECUTION_TYPE_OBJECT1);
		graphConformanceTypeObjectEDataType = createEDataType(GRAPH_CONFORMANCE_TYPE_OBJECT);
		instantiationTypeObjectEDataType = createEDataType(INSTANTIATION_TYPE_OBJECT);
		isArrayTypeObjectEDataType = createEDataType(IS_ARRAY_TYPE_OBJECT);
		modeTypeObjectEDataType = createEDataType(MODE_TYPE_OBJECT);
		publicationStatusTypeObjectEDataType = createEDataType(PUBLICATION_STATUS_TYPE_OBJECT);
		typeTypeObjectEDataType = createEDataType(TYPE_TYPE_OBJECT);
		typeTypeObject1EDataType = createEDataType(TYPE_TYPE_OBJECT1);
		typeTypeObject2EDataType = createEDataType(TYPE_TYPE_OBJECT2);
		typeTypeObject3EDataType = createEDataType(TYPE_TYPE_OBJECT3);
		typeTypeObject4EDataType = createEDataType(TYPE_TYPE_OBJECT4);
		typeTypeObject5EDataType = createEDataType(TYPE_TYPE_OBJECT5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(activitiesTypeEClass, ActivitiesType.class, "ActivitiesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivitiesType_Activity(), this.getActivityType(), null, "activity", null, 0, -1, ActivitiesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activitySetsTypeEClass, ActivitySetsType.class, "ActivitySetsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivitySetsType_ActivitySet(), this.getActivitySetType(), null, "activitySet", null, 0, -1, ActivitySetsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activitySetTypeEClass, ActivitySetType.class, "ActivitySetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivitySetType_Activities(), this.getActivitiesType(), null, "activities", null, 0, 1, ActivitySetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivitySetType_Transitions(), this.getTransitionsType(), null, "transitions", null, 0, 1, ActivitySetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivitySetType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, ActivitySetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityTypeEClass, ActivityType.class, "ActivityType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivityType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityType_Limit(), theXMLTypePackage.getString(), "limit", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityType_Route(), this.getRouteType(), null, "route", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityType_Implementation(), this.getImplementationType(), null, "implementation", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityType_BlockActivity(), this.getBlockActivityType(), null, "blockActivity", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityType_Performer(), theXMLTypePackage.getString(), "performer", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityType_StartMode(), this.getStartModeType(), null, "startMode", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityType_FinishMode(), this.getFinishModeType(), null, "finishMode", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityType_Priority(), theXMLTypePackage.getString(), "priority", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityType_Deadline(), this.getDeadlineType(), null, "deadline", null, 0, -1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityType_SimulationInformation(), this.getSimulationInformationType(), null, "simulationInformation", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityType_Icon(), theXMLTypePackage.getString(), "icon", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityType_Documentation(), theXMLTypePackage.getString(), "documentation", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityType_TransitionRestrictions(), this.getTransitionRestrictionsType(), null, "transitionRestrictions", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actualParametersTypeEClass, ActualParametersType.class, "ActualParametersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActualParametersType_ActualParameter(), theXMLTypePackage.getString(), "actualParameter", null, 0, -1, ActualParametersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationsTypeEClass, ApplicationsType.class, "ApplicationsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplicationsType_Application(), this.getApplicationType(), null, "application", null, 0, -1, ApplicationsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationTypeEClass, ApplicationType.class, "ApplicationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getApplicationType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationType_FormalParameters(), this.getFormalParametersType(), null, "formalParameters", null, 0, 1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationType_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, 1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getApplicationType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getApplicationType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arrayTypeTypeEClass, ArrayTypeType.class, "ArrayTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArrayTypeType_BasicType(), this.getBasicTypeType(), null, "basicType", null, 0, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayTypeType_DeclaredType(), this.getDeclaredTypeType(), null, "declaredType", null, 0, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayTypeType_SchemaType(), this.getSchemaTypeType(), null, "schemaType", null, 0, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayTypeType_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayTypeType_RecordType(), this.getRecordTypeType(), null, "recordType", null, 0, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayTypeType_UnionType(), this.getUnionTypeType(), null, "unionType", null, 0, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayTypeType_EnumerationType(), this.getEnumerationTypeType(), null, "enumerationType", null, 0, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayTypeType_ArrayType(), this.getArrayTypeType(), null, "arrayType", null, 0, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayTypeType_ListType(), this.getListTypeType(), null, "listType", null, 0, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArrayTypeType_LowerIndex(), theXMLTypePackage.getNMTOKEN(), "lowerIndex", null, 1, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArrayTypeType_UpperIndex(), theXMLTypePackage.getNMTOKEN(), "upperIndex", null, 1, 1, ArrayTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(automaticTypeEClass, AutomaticType.class, "AutomaticType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(basicTypeTypeEClass, BasicTypeType.class, "BasicTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBasicTypeType_Type(), this.getTypeType3(), "type", null, 1, 1, BasicTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(blockActivityTypeEClass, BlockActivityType.class, "BlockActivityType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBlockActivityType_BlockId(), theXMLTypePackage.getNMTOKEN(), "blockId", null, 1, 1, BlockActivityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionTypeEClass, ConditionType.class, "ConditionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConditionType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ConditionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ConditionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getConditionType_Xpression(), this.getXpressionType(), null, "xpression", null, 0, -1, ConditionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionType_Type(), this.getTypeType2(), "type", null, 0, 1, ConditionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conformanceClassTypeEClass, ConformanceClassType.class, "ConformanceClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConformanceClassType_GraphConformance(), this.getGraphConformanceType(), "graphConformance", null, 0, 1, ConformanceClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataFieldsTypeEClass, DataFieldsType.class, "DataFieldsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataFieldsType_DataField(), this.getDataFieldType(), null, "dataField", null, 0, -1, DataFieldsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataFieldTypeEClass, DataFieldType.class, "DataFieldType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataFieldType_DataType(), this.getDataTypeType(), null, "dataType", null, 1, 1, DataFieldType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFieldType_InitialValue(), theXMLTypePackage.getString(), "initialValue", null, 0, 1, DataFieldType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFieldType_Length(), theXMLTypePackage.getString(), "length", null, 0, 1, DataFieldType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFieldType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, DataFieldType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataFieldType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, DataFieldType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFieldType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, DataFieldType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFieldType_IsArray(), this.getIsArrayType(), "isArray", "FALSE", 0, 1, DataFieldType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFieldType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, DataFieldType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataTypeTypeEClass, DataTypeType.class, "DataTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataTypeType_BasicType(), this.getBasicTypeType(), null, "basicType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTypeType_DeclaredType(), this.getDeclaredTypeType(), null, "declaredType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTypeType_SchemaType(), this.getSchemaTypeType(), null, "schemaType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTypeType_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTypeType_RecordType(), this.getRecordTypeType(), null, "recordType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTypeType_UnionType(), this.getUnionTypeType(), null, "unionType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTypeType_EnumerationType(), this.getEnumerationTypeType(), null, "enumerationType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTypeType_ArrayType(), this.getArrayTypeType(), null, "arrayType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTypeType_ListType(), this.getListTypeType(), null, "listType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deadlineTypeEClass, DeadlineType.class, "DeadlineType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeadlineType_DeadlineCondition(), ecorePackage.getEObject(), null, "deadlineCondition", null, 1, 1, DeadlineType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeadlineType_ExceptionName(), ecorePackage.getEObject(), null, "exceptionName", null, 1, 1, DeadlineType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeadlineType_Execution(), this.getExecutionType(), "execution", null, 0, 1, DeadlineType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(declaredTypeTypeEClass, DeclaredTypeType.class, "DeclaredTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeclaredTypeType_Id(), theXMLTypePackage.getIDREF(), "id", null, 1, 1, DeclaredTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Activities(), this.getActivitiesType(), null, "activities", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Activity(), this.getActivityType(), null, "activity", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ActivitySet(), this.getActivitySetType(), null, "activitySet", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ActivitySets(), this.getActivitySetsType(), null, "activitySets", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_ActualParameter(), theXMLTypePackage.getString(), "actualParameter", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ActualParameters(), this.getActualParametersType(), null, "actualParameters", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Application(), this.getApplicationType(), null, "application", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Applications(), this.getApplicationsType(), null, "applications", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ArrayType(), this.getArrayTypeType(), null, "arrayType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Author(), theXMLTypePackage.getString(), "author", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Automatic(), this.getAutomaticType(), null, "automatic", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_BasicType(), this.getBasicTypeType(), null, "basicType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_BlockActivity(), this.getBlockActivityType(), null, "blockActivity", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Codepage(), theXMLTypePackage.getString(), "codepage", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Condition(), this.getConditionType(), null, "condition", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ConformanceClass(), this.getConformanceClassType(), null, "conformanceClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Cost(), theXMLTypePackage.getString(), "cost", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_CostUnit(), theXMLTypePackage.getString(), "costUnit", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Countrykey(), theXMLTypePackage.getString(), "countrykey", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Created(), theXMLTypePackage.getString(), "created", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_DataField(), this.getDataFieldType(), null, "dataField", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_DataFields(), this.getDataFieldsType(), null, "dataFields", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_DataType(), this.getDataTypeType(), null, "dataType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Deadline(), this.getDeadlineType(), null, "deadline", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_DeclaredType(), this.getDeclaredTypeType(), null, "declaredType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Description(), theXMLTypePackage.getString(), "description", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Documentation(), theXMLTypePackage.getString(), "documentation", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Duration(), theXMLTypePackage.getString(), "duration", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_EnumerationType(), this.getEnumerationTypeType(), null, "enumerationType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_EnumerationValue(), this.getEnumerationValueType(), null, "enumerationValue", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ExtendedAttribute(), this.getExtendedAttributeType(), null, "extendedAttribute", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ExternalPackage(), this.getExternalPackageType(), null, "externalPackage", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ExternalPackages(), this.getExternalPackagesType(), null, "externalPackages", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_FinishMode(), this.getFinishModeType(), null, "finishMode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_FormalParameter(), this.getFormalParameterType(), null, "formalParameter", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_FormalParameters(), this.getFormalParametersType(), null, "formalParameters", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Icon(), theXMLTypePackage.getString(), "icon", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Implementation(), this.getImplementationType(), null, "implementation", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_InitialValue(), theXMLTypePackage.getString(), "initialValue", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Join(), this.getJoinType(), null, "join", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Length(), theXMLTypePackage.getString(), "length", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Limit(), theXMLTypePackage.getString(), "limit", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ListType(), this.getListTypeType(), null, "listType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Manual(), this.getManualType(), null, "manual", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Member(), this.getMemberType(), null, "member", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_No(), this.getNoType(), null, "no", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Package(), this.getPackageType(), null, "package", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_PackageHeader(), this.getPackageHeaderType(), null, "packageHeader", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Participant(), this.getParticipantType(), null, "participant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Participants(), this.getParticipantsType(), null, "participants", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ParticipantType(), this.getParticipantTypeType(), null, "participantType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Performer(), theXMLTypePackage.getString(), "performer", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Priority(), theXMLTypePackage.getString(), "priority", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_PriorityUnit(), theXMLTypePackage.getString(), "priorityUnit", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ProcessHeader(), this.getProcessHeaderType(), null, "processHeader", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_RecordType(), this.getRecordTypeType(), null, "recordType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_RedefinableHeader(), this.getRedefinableHeaderType(), null, "redefinableHeader", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Responsible(), theXMLTypePackage.getString(), "responsible", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Responsibles(), this.getResponsiblesType(), null, "responsibles", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Route(), this.getRouteType(), null, "route", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SchemaType(), this.getSchemaTypeType(), null, "schemaType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Script(), this.getScriptType(), null, "script", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SimulationInformation(), this.getSimulationInformationType(), null, "simulationInformation", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Split(), this.getSplitType(), null, "split", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StartMode(), this.getStartModeType(), null, "startMode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SubFlow(), this.getSubFlowType(), null, "subFlow", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TimeEstimation(), this.getTimeEstimationType(), null, "timeEstimation", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Tool(), this.getToolType(), null, "tool", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Transition(), this.getTransitionType(), null, "transition", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TransitionRef(), this.getTransitionRefType(), null, "transitionRef", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TransitionRefs(), this.getTransitionRefsType(), null, "transitionRefs", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TransitionRestriction(), this.getTransitionRestrictionType(), null, "transitionRestriction", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TransitionRestrictions(), this.getTransitionRestrictionsType(), null, "transitionRestrictions", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Transitions(), this.getTransitionsType(), null, "transitions", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TypeDeclaration(), this.getTypeDeclarationType(), null, "typeDeclaration", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TypeDeclarations(), this.getTypeDeclarationsType(), null, "typeDeclarations", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_UnionType(), this.getUnionTypeType(), null, "unionType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_ValidFrom(), theXMLTypePackage.getString(), "validFrom", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_ValidTo(), theXMLTypePackage.getString(), "validTo", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Vendor(), theXMLTypePackage.getString(), "vendor", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Version(), theXMLTypePackage.getString(), "version", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_WaitingTime(), theXMLTypePackage.getString(), "waitingTime", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_WorkflowProcess(), this.getWorkflowProcessType(), null, "workflowProcess", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_WorkflowProcesses(), this.getWorkflowProcessesType(), null, "workflowProcesses", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_WorkingTime(), theXMLTypePackage.getString(), "workingTime", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_XPDLVersion(), theXMLTypePackage.getString(), "xPDLVersion", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Xpression(), this.getXpressionType(), null, "xpression", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(enumerationTypeTypeEClass, EnumerationTypeType.class, "EnumerationTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnumerationTypeType_EnumerationValue(), this.getEnumerationValueType(), null, "enumerationValue", null, 1, -1, EnumerationTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enumerationValueTypeEClass, EnumerationValueType.class, "EnumerationValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumerationValueType_Name(), theXMLTypePackage.getNMTOKEN(), "name", null, 1, 1, EnumerationValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extendedAttributesTypeEClass, ExtendedAttributesType.class, "ExtendedAttributesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExtendedAttributesType_ExtendedAttribute(), this.getExtendedAttributeType(), null, "extendedAttribute", null, 0, -1, ExtendedAttributesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extendedAttributeTypeEClass, ExtendedAttributeType.class, "ExtendedAttributeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExtendedAttributeType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ExtendedAttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtendedAttributeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ExtendedAttributeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtendedAttributeType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ExtendedAttributeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtendedAttributeType_Name(), theXMLTypePackage.getNMTOKEN(), "name", null, 1, 1, ExtendedAttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtendedAttributeType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ExtendedAttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalPackagesTypeEClass, ExternalPackagesType.class, "ExternalPackagesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalPackagesType_ExternalPackage(), this.getExternalPackageType(), null, "externalPackage", null, 0, -1, ExternalPackagesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalPackageTypeEClass, ExternalPackageType.class, "ExternalPackageType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalPackageType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, ExternalPackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalPackageType_Href(), theXMLTypePackage.getString(), "href", null, 0, 1, ExternalPackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalReferenceTypeEClass, ExternalReferenceType.class, "ExternalReferenceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExternalReferenceType_Location(), theXMLTypePackage.getAnyURI(), "location", null, 1, 1, ExternalReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalReferenceType_Namespace(), theXMLTypePackage.getAnyURI(), "namespace", null, 0, 1, ExternalReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalReferenceType_Xref(), theXMLTypePackage.getNMTOKEN(), "xref", null, 0, 1, ExternalReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(finishModeTypeEClass, FinishModeType.class, "FinishModeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFinishModeType_Automatic(), this.getAutomaticType(), null, "automatic", null, 0, 1, FinishModeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFinishModeType_Manual(), this.getManualType(), null, "manual", null, 0, 1, FinishModeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formalParametersTypeEClass, FormalParametersType.class, "FormalParametersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFormalParametersType_FormalParameter(), this.getFormalParameterType(), null, "formalParameter", null, 0, -1, FormalParametersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formalParameterTypeEClass, FormalParameterType.class, "FormalParameterType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFormalParameterType_DataType(), this.getDataTypeType(), null, "dataType", null, 1, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFormalParameterType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFormalParameterType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFormalParameterType_Index(), theXMLTypePackage.getNMTOKEN(), "index", null, 0, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFormalParameterType_Mode(), this.getModeType(), "mode", "IN", 0, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(implementationTypeEClass, ImplementationType.class, "ImplementationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImplementationType_No(), this.getNoType(), null, "no", null, 0, 1, ImplementationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImplementationType_Tool(), this.getToolType(), null, "tool", null, 0, -1, ImplementationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImplementationType_SubFlow(), this.getSubFlowType(), null, "subFlow", null, 0, 1, ImplementationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(joinTypeEClass, JoinType.class, "JoinType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJoinType_Type(), this.getTypeType(), "type", null, 0, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listTypeTypeEClass, ListTypeType.class, "ListTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getListTypeType_BasicType(), this.getBasicTypeType(), null, "basicType", null, 0, 1, ListTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListTypeType_DeclaredType(), this.getDeclaredTypeType(), null, "declaredType", null, 0, 1, ListTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListTypeType_SchemaType(), this.getSchemaTypeType(), null, "schemaType", null, 0, 1, ListTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListTypeType_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, 1, ListTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListTypeType_RecordType(), this.getRecordTypeType(), null, "recordType", null, 0, 1, ListTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListTypeType_UnionType(), this.getUnionTypeType(), null, "unionType", null, 0, 1, ListTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListTypeType_EnumerationType(), this.getEnumerationTypeType(), null, "enumerationType", null, 0, 1, ListTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListTypeType_ArrayType(), this.getArrayTypeType(), null, "arrayType", null, 0, 1, ListTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListTypeType_ListType(), this.getListTypeType(), null, "listType", null, 0, 1, ListTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manualTypeEClass, ManualType.class, "ManualType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(memberTypeEClass, MemberType.class, "MemberType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMemberType_BasicType(), this.getBasicTypeType(), null, "basicType", null, 0, 1, MemberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMemberType_DeclaredType(), this.getDeclaredTypeType(), null, "declaredType", null, 0, 1, MemberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMemberType_SchemaType(), this.getSchemaTypeType(), null, "schemaType", null, 0, 1, MemberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMemberType_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, 1, MemberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMemberType_RecordType(), this.getRecordTypeType(), null, "recordType", null, 0, 1, MemberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMemberType_UnionType(), this.getUnionTypeType(), null, "unionType", null, 0, 1, MemberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMemberType_EnumerationType(), this.getEnumerationTypeType(), null, "enumerationType", null, 0, 1, MemberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMemberType_ArrayType(), this.getArrayTypeType(), null, "arrayType", null, 0, 1, MemberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMemberType_ListType(), this.getListTypeType(), null, "listType", null, 0, 1, MemberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(noTypeEClass, NoType.class, "NoType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(packageHeaderTypeEClass, PackageHeaderType.class, "PackageHeaderType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPackageHeaderType_XPDLVersion(), theXMLTypePackage.getString(), "xPDLVersion", null, 1, 1, PackageHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageHeaderType_Vendor(), theXMLTypePackage.getString(), "vendor", null, 1, 1, PackageHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageHeaderType_Created(), theXMLTypePackage.getString(), "created", null, 1, 1, PackageHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageHeaderType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, PackageHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageHeaderType_Documentation(), theXMLTypePackage.getString(), "documentation", null, 0, 1, PackageHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageHeaderType_PriorityUnit(), theXMLTypePackage.getString(), "priorityUnit", null, 0, 1, PackageHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageHeaderType_CostUnit(), theXMLTypePackage.getString(), "costUnit", null, 0, 1, PackageHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageTypeEClass, PackageType.class, "PackageType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackageType_PackageHeader(), this.getPackageHeaderType(), null, "packageHeader", null, 1, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_RedefinableHeader(), this.getRedefinableHeaderType(), null, "redefinableHeader", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_ConformanceClass(), this.getConformanceClassType(), null, "conformanceClass", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_Script(), this.getScriptType(), null, "script", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_ExternalPackages(), this.getExternalPackagesType(), null, "externalPackages", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_TypeDeclarations(), this.getTypeDeclarationsType(), null, "typeDeclarations", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_Participants(), this.getParticipantsType(), null, "participants", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_Applications(), this.getApplicationsType(), null, "applications", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_DataFields(), this.getDataFieldsType(), null, "dataFields", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_WorkflowProcesses(), this.getWorkflowProcessesType(), null, "workflowProcesses", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, PackageType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantsTypeEClass, ParticipantsType.class, "ParticipantsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParticipantsType_Participant(), this.getParticipantType(), null, "participant", null, 0, -1, ParticipantsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantTypeEClass, ParticipantType.class, "ParticipantType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParticipantType_ParticipantType(), this.getParticipantTypeType(), null, "participantType", null, 1, 1, ParticipantType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipantType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, ParticipantType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParticipantType_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, 1, ParticipantType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParticipantType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, ParticipantType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipantType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, ParticipantType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipantType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ParticipantType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantTypeTypeEClass, ParticipantTypeType.class, "ParticipantTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParticipantTypeType_Type(), this.getTypeType1(), "type", null, 1, 1, ParticipantTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processHeaderTypeEClass, ProcessHeaderType.class, "ProcessHeaderType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessHeaderType_Created(), theXMLTypePackage.getString(), "created", null, 0, 1, ProcessHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessHeaderType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, ProcessHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessHeaderType_Priority(), theXMLTypePackage.getString(), "priority", null, 0, 1, ProcessHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessHeaderType_Limit(), theXMLTypePackage.getString(), "limit", null, 0, 1, ProcessHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessHeaderType_ValidFrom(), theXMLTypePackage.getString(), "validFrom", null, 0, 1, ProcessHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessHeaderType_ValidTo(), theXMLTypePackage.getString(), "validTo", null, 0, 1, ProcessHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessHeaderType_TimeEstimation(), this.getTimeEstimationType(), null, "timeEstimation", null, 0, 1, ProcessHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessHeaderType_DurationUnit(), this.getDurationUnitType(), "durationUnit", null, 0, 1, ProcessHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(recordTypeTypeEClass, RecordTypeType.class, "RecordTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRecordTypeType_Member(), this.getMemberType(), null, "member", null, 1, -1, RecordTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(redefinableHeaderTypeEClass, RedefinableHeaderType.class, "RedefinableHeaderType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRedefinableHeaderType_Author(), theXMLTypePackage.getString(), "author", null, 0, 1, RedefinableHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRedefinableHeaderType_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, RedefinableHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRedefinableHeaderType_Codepage(), theXMLTypePackage.getString(), "codepage", null, 0, 1, RedefinableHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRedefinableHeaderType_Countrykey(), theXMLTypePackage.getString(), "countrykey", null, 0, 1, RedefinableHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRedefinableHeaderType_Responsibles(), this.getResponsiblesType(), null, "responsibles", null, 0, 1, RedefinableHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRedefinableHeaderType_PublicationStatus(), this.getPublicationStatusType(), "publicationStatus", null, 0, 1, RedefinableHeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(responsiblesTypeEClass, ResponsiblesType.class, "ResponsiblesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResponsiblesType_Responsible(), theXMLTypePackage.getString(), "responsible", null, 0, -1, ResponsiblesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(routeTypeEClass, RouteType.class, "RouteType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(schemaTypeTypeEClass, SchemaTypeType.class, "SchemaTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSchemaTypeType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, SchemaTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptTypeEClass, ScriptType.class, "ScriptType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptType_Grammar(), theXMLTypePackage.getAnyURI(), "grammar", null, 0, 1, ScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, ScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptType_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, ScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(simulationInformationTypeEClass, SimulationInformationType.class, "SimulationInformationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSimulationInformationType_Cost(), theXMLTypePackage.getString(), "cost", null, 1, 1, SimulationInformationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSimulationInformationType_TimeEstimation(), this.getTimeEstimationType(), null, "timeEstimation", null, 1, 1, SimulationInformationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationInformationType_Instantiation(), this.getInstantiationType(), "instantiation", null, 0, 1, SimulationInformationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(splitTypeEClass, SplitType.class, "SplitType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSplitType_TransitionRefs(), this.getTransitionRefsType(), null, "transitionRefs", null, 0, 1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplitType_Type(), this.getTypeType4(), "type", null, 0, 1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(startModeTypeEClass, StartModeType.class, "StartModeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStartModeType_Automatic(), this.getAutomaticType(), null, "automatic", null, 0, 1, StartModeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStartModeType_Manual(), this.getManualType(), null, "manual", null, 0, 1, StartModeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subFlowTypeEClass, SubFlowType.class, "SubFlowType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSubFlowType_ActualParameters(), this.getActualParametersType(), null, "actualParameters", null, 0, 1, SubFlowType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubFlowType_Execution(), this.getExecutionType1(), "execution", null, 0, 1, SubFlowType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubFlowType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, SubFlowType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timeEstimationTypeEClass, TimeEstimationType.class, "TimeEstimationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimeEstimationType_WaitingTime(), theXMLTypePackage.getString(), "waitingTime", null, 0, 1, TimeEstimationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimeEstimationType_WorkingTime(), theXMLTypePackage.getString(), "workingTime", null, 0, 1, TimeEstimationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimeEstimationType_Duration(), theXMLTypePackage.getString(), "duration", null, 0, 1, TimeEstimationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(toolTypeEClass, ToolType.class, "ToolType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getToolType_ActualParameters(), this.getActualParametersType(), null, "actualParameters", null, 0, 1, ToolType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getToolType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, ToolType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getToolType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, ToolType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getToolType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, ToolType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getToolType_Type(), this.getTypeType5(), "type", null, 0, 1, ToolType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionRefsTypeEClass, TransitionRefsType.class, "TransitionRefsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransitionRefsType_TransitionRef(), this.getTransitionRefType(), null, "transitionRef", null, 0, -1, TransitionRefsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionRefTypeEClass, TransitionRefType.class, "TransitionRefType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransitionRefType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, TransitionRefType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionRestrictionsTypeEClass, TransitionRestrictionsType.class, "TransitionRestrictionsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransitionRestrictionsType_TransitionRestriction(), this.getTransitionRestrictionType(), null, "transitionRestriction", null, 0, -1, TransitionRestrictionsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionRestrictionTypeEClass, TransitionRestrictionType.class, "TransitionRestrictionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransitionRestrictionType_Join(), this.getJoinType(), null, "join", null, 0, 1, TransitionRestrictionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionRestrictionType_Split(), this.getSplitType(), null, "split", null, 0, 1, TransitionRestrictionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionsTypeEClass, TransitionsType.class, "TransitionsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransitionsType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, TransitionsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionTypeEClass, TransitionType.class, "TransitionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransitionType_Condition(), this.getConditionType(), null, "condition", null, 0, 1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransitionType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransitionType_From(), theXMLTypePackage.getNMTOKEN(), "from", null, 1, 1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransitionType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransitionType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransitionType_To(), theXMLTypePackage.getNMTOKEN(), "to", null, 1, 1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeDeclarationsTypeEClass, TypeDeclarationsType.class, "TypeDeclarationsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeDeclarationsType_TypeDeclaration(), this.getTypeDeclarationType(), null, "typeDeclaration", null, 0, -1, TypeDeclarationsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeDeclarationTypeEClass, TypeDeclarationType.class, "TypeDeclarationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeDeclarationType_BasicType(), this.getBasicTypeType(), null, "basicType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDeclarationType_DeclaredType(), this.getDeclaredTypeType(), null, "declaredType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDeclarationType_SchemaType(), this.getSchemaTypeType(), null, "schemaType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDeclarationType_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDeclarationType_RecordType(), this.getRecordTypeType(), null, "recordType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDeclarationType_UnionType(), this.getUnionTypeType(), null, "unionType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDeclarationType_EnumerationType(), this.getEnumerationTypeType(), null, "enumerationType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDeclarationType_ArrayType(), this.getArrayTypeType(), null, "arrayType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDeclarationType_ListType(), this.getListTypeType(), null, "listType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeDeclarationType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeDeclarationType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeDeclarationType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeDeclarationType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unionTypeTypeEClass, UnionTypeType.class, "UnionTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnionTypeType_Member(), this.getMemberType(), null, "member", null, 1, -1, UnionTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workflowProcessesTypeEClass, WorkflowProcessesType.class, "WorkflowProcessesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkflowProcessesType_WorkflowProcess(), this.getWorkflowProcessType(), null, "workflowProcess", null, 0, -1, WorkflowProcessesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workflowProcessTypeEClass, WorkflowProcessType.class, "WorkflowProcessType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkflowProcessType_ProcessHeader(), this.getProcessHeaderType(), null, "processHeader", null, 1, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowProcessType_RedefinableHeader(), this.getRedefinableHeaderType(), null, "redefinableHeader", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowProcessType_FormalParameters(), this.getFormalParametersType(), null, "formalParameters", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowProcessType_DataFields(), this.getDataFieldsType(), null, "dataFields", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowProcessType_Participants(), this.getParticipantsType(), null, "participants", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowProcessType_Applications(), this.getApplicationsType(), null, "applications", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowProcessType_ActivitySets(), this.getActivitySetsType(), null, "activitySets", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowProcessType_Activities(), this.getActivitiesType(), null, "activities", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowProcessType_Transitions(), this.getTransitionsType(), null, "transitions", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowProcessType_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkflowProcessType_AccessLevel(), this.getAccessLevelType(), "accessLevel", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkflowProcessType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkflowProcessType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, WorkflowProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(xpressionTypeEClass, XpressionType.class, "XpressionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getXpressionType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, XpressionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getXpressionType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, XpressionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getXpressionType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, XpressionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(accessLevelTypeEEnum, AccessLevelType.class, "AccessLevelType");
		addEEnumLiteral(accessLevelTypeEEnum, AccessLevelType.PUBLIC);
		addEEnumLiteral(accessLevelTypeEEnum, AccessLevelType.PRIVATE);

		initEEnum(durationUnitTypeEEnum, DurationUnitType.class, "DurationUnitType");
		addEEnumLiteral(durationUnitTypeEEnum, DurationUnitType.Y);
		addEEnumLiteral(durationUnitTypeEEnum, DurationUnitType.M);
		addEEnumLiteral(durationUnitTypeEEnum, DurationUnitType.D);
		addEEnumLiteral(durationUnitTypeEEnum, DurationUnitType.H);
		addEEnumLiteral(durationUnitTypeEEnum, DurationUnitType.M1);
		addEEnumLiteral(durationUnitTypeEEnum, DurationUnitType.S);

		initEEnum(executionTypeEEnum, ExecutionType.class, "ExecutionType");
		addEEnumLiteral(executionTypeEEnum, ExecutionType.ASYNCHR);
		addEEnumLiteral(executionTypeEEnum, ExecutionType.SYNCHR);

		initEEnum(executionType1EEnum, ExecutionType1.class, "ExecutionType1");
		addEEnumLiteral(executionType1EEnum, ExecutionType1.ASYNCHR);
		addEEnumLiteral(executionType1EEnum, ExecutionType1.SYNCHR);

		initEEnum(graphConformanceTypeEEnum, GraphConformanceType.class, "GraphConformanceType");
		addEEnumLiteral(graphConformanceTypeEEnum, GraphConformanceType.FULLBLOCKED);
		addEEnumLiteral(graphConformanceTypeEEnum, GraphConformanceType.LOOPBLOCKED);
		addEEnumLiteral(graphConformanceTypeEEnum, GraphConformanceType.NONBLOCKED);

		initEEnum(instantiationTypeEEnum, InstantiationType.class, "InstantiationType");
		addEEnumLiteral(instantiationTypeEEnum, InstantiationType.ONCE);
		addEEnumLiteral(instantiationTypeEEnum, InstantiationType.MULTIPLE);

		initEEnum(isArrayTypeEEnum, IsArrayType.class, "IsArrayType");
		addEEnumLiteral(isArrayTypeEEnum, IsArrayType.TRUE);
		addEEnumLiteral(isArrayTypeEEnum, IsArrayType.FALSE);

		initEEnum(modeTypeEEnum, ModeType.class, "ModeType");
		addEEnumLiteral(modeTypeEEnum, ModeType.IN);
		addEEnumLiteral(modeTypeEEnum, ModeType.OUT);
		addEEnumLiteral(modeTypeEEnum, ModeType.INOUT);

		initEEnum(publicationStatusTypeEEnum, PublicationStatusType.class, "PublicationStatusType");
		addEEnumLiteral(publicationStatusTypeEEnum, PublicationStatusType.UNDERREVISION);
		addEEnumLiteral(publicationStatusTypeEEnum, PublicationStatusType.RELEASED);
		addEEnumLiteral(publicationStatusTypeEEnum, PublicationStatusType.UNDERTEST);

		initEEnum(typeTypeEEnum, TypeType.class, "TypeType");
		addEEnumLiteral(typeTypeEEnum, TypeType.AND);
		addEEnumLiteral(typeTypeEEnum, TypeType.XOR);

		initEEnum(typeType1EEnum, TypeType1.class, "TypeType1");
		addEEnumLiteral(typeType1EEnum, TypeType1.RESOURCESET);
		addEEnumLiteral(typeType1EEnum, TypeType1.RESOURCE);
		addEEnumLiteral(typeType1EEnum, TypeType1.ROLE);
		addEEnumLiteral(typeType1EEnum, TypeType1.ORGANIZATIONALUNIT);
		addEEnumLiteral(typeType1EEnum, TypeType1.HUMAN);
		addEEnumLiteral(typeType1EEnum, TypeType1.SYSTEM);

		initEEnum(typeType2EEnum, TypeType2.class, "TypeType2");
		addEEnumLiteral(typeType2EEnum, TypeType2.CONDITION);
		addEEnumLiteral(typeType2EEnum, TypeType2.OTHERWISE);
		addEEnumLiteral(typeType2EEnum, TypeType2.EXCEPTION);
		addEEnumLiteral(typeType2EEnum, TypeType2.DEFAULTEXCEPTION);

		initEEnum(typeType3EEnum, TypeType3.class, "TypeType3");
		addEEnumLiteral(typeType3EEnum, TypeType3.STRING);
		addEEnumLiteral(typeType3EEnum, TypeType3.FLOAT);
		addEEnumLiteral(typeType3EEnum, TypeType3.INTEGER);
		addEEnumLiteral(typeType3EEnum, TypeType3.REFERENCE);
		addEEnumLiteral(typeType3EEnum, TypeType3.DATETIME);
		addEEnumLiteral(typeType3EEnum, TypeType3.BOOLEAN);
		addEEnumLiteral(typeType3EEnum, TypeType3.PERFORMER);

		initEEnum(typeType4EEnum, TypeType4.class, "TypeType4");
		addEEnumLiteral(typeType4EEnum, TypeType4.AND);
		addEEnumLiteral(typeType4EEnum, TypeType4.XOR);

		initEEnum(typeType5EEnum, TypeType5.class, "TypeType5");
		addEEnumLiteral(typeType5EEnum, TypeType5.APPLICATION);
		addEEnumLiteral(typeType5EEnum, TypeType5.PROCEDURE);

		// Initialize data types
		initEDataType(accessLevelTypeObjectEDataType, AccessLevelType.class, "AccessLevelTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(durationUnitTypeObjectEDataType, DurationUnitType.class, "DurationUnitTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(executionTypeObjectEDataType, ExecutionType.class, "ExecutionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(executionTypeObject1EDataType, ExecutionType1.class, "ExecutionTypeObject1", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(graphConformanceTypeObjectEDataType, GraphConformanceType.class, "GraphConformanceTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(instantiationTypeObjectEDataType, InstantiationType.class, "InstantiationTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(isArrayTypeObjectEDataType, IsArrayType.class, "IsArrayTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(modeTypeObjectEDataType, ModeType.class, "ModeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(publicationStatusTypeObjectEDataType, PublicationStatusType.class, "PublicationStatusTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeTypeObjectEDataType, TypeType4.class, "TypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeTypeObject1EDataType, TypeType1.class, "TypeTypeObject1", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeTypeObject2EDataType, TypeType3.class, "TypeTypeObject2", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeTypeObject3EDataType, TypeType2.class, "TypeTypeObject3", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeTypeObject4EDataType, TypeType.class, "TypeTypeObject4", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeTypeObject5EDataType, TypeType5.class, "TypeTypeObject5", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (accessLevelTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "AccessLevel_._type"
		   });		
		addAnnotation
		  (accessLevelTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "AccessLevel_._type:Object",
			 "baseType", "AccessLevel_._type"
		   });		
		addAnnotation
		  (activitiesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Activities_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getActivitiesType_Activity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Activity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (activitySetsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ActivitySets_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getActivitySetsType_ActivitySet(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActivitySet",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (activitySetTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ActivitySet_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getActivitySetType_Activities(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Activities",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivitySetType_Transitions(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Transitions",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivitySetType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (activityTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Activity_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getActivityType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_Limit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Limit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_Route(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Route",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_Implementation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Implementation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_BlockActivity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BlockActivity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_Performer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Performer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_StartMode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StartMode",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_FinishMode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FinishMode",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_Priority(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Priority",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_Deadline(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Deadline",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_SimulationInformation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SimulationInformation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_Icon(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Icon",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_Documentation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Documentation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_TransitionRestrictions(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TransitionRestrictions",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getActivityType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getActivityType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (actualParametersTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ActualParameters_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getActualParametersType_ActualParameter(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActualParameter",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (applicationsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Applications_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getApplicationsType_Application(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Application",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (applicationTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Application_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getApplicationType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getApplicationType_FormalParameters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FormalParameters",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getApplicationType_ExternalReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalReference",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getApplicationType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getApplicationType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getApplicationType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (arrayTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ArrayType_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getArrayTypeType_BasicType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BasicType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getArrayTypeType_DeclaredType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DeclaredType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getArrayTypeType_SchemaType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SchemaType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getArrayTypeType_ExternalReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalReference",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getArrayTypeType_RecordType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RecordType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getArrayTypeType_UnionType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "UnionType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getArrayTypeType_EnumerationType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EnumerationType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getArrayTypeType_ArrayType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ArrayType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getArrayTypeType_ListType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ListType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getArrayTypeType_LowerIndex(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "LowerIndex"
		   });		
		addAnnotation
		  (getArrayTypeType_UpperIndex(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "UpperIndex"
		   });		
		addAnnotation
		  (automaticTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Automatic_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (basicTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "BasicType_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getBasicTypeType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Type"
		   });		
		addAnnotation
		  (blockActivityTypeEClass, 
		   source, 
		   new String[] {
			 "name", "BlockActivity_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getBlockActivityType_BlockId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "BlockId"
		   });		
		addAnnotation
		  (conditionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Condition_._type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getConditionType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getConditionType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:1"
		   });		
		addAnnotation
		  (getConditionType_Xpression(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Xpression",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });		
		addAnnotation
		  (getConditionType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Type"
		   });		
		addAnnotation
		  (conformanceClassTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ConformanceClass_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getConformanceClassType_GraphConformance(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "GraphConformance"
		   });		
		addAnnotation
		  (dataFieldsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "DataFields_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDataFieldsType_DataField(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DataField",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (dataFieldTypeEClass, 
		   source, 
		   new String[] {
			 "name", "DataField_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDataFieldType_DataType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DataType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataFieldType_InitialValue(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "InitialValue",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataFieldType_Length(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Length",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataFieldType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataFieldType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataFieldType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getDataFieldType_IsArray(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "IsArray"
		   });		
		addAnnotation
		  (getDataFieldType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (dataTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "DataType_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDataTypeType_BasicType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BasicType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataTypeType_DeclaredType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DeclaredType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataTypeType_SchemaType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SchemaType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataTypeType_ExternalReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalReference",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataTypeType_RecordType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RecordType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataTypeType_UnionType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "UnionType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataTypeType_EnumerationType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EnumerationType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataTypeType_ArrayType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ArrayType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDataTypeType_ListType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ListType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (deadlineTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Deadline_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDeadlineType_DeadlineCondition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DeadlineCondition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDeadlineType_ExceptionName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExceptionName",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDeadlineType_Execution(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Execution"
		   });		
		addAnnotation
		  (declaredTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "DeclaredType_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getDeclaredTypeType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_Activities(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Activities",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Activity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Activity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ActivitySet(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActivitySet",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ActivitySets(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActivitySets",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ActualParameter(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActualParameter",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ActualParameters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActualParameters",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Application(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Application",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Applications(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Applications",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ArrayType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ArrayType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Author(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Author",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Automatic(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Automatic",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_BasicType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BasicType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_BlockActivity(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BlockActivity",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Codepage(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Codepage",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Condition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Condition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ConformanceClass(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConformanceClass",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Cost(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Cost",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_CostUnit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "CostUnit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Countrykey(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Countrykey",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Created(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Created",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_DataField(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DataField",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_DataFields(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DataFields",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_DataType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DataType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Deadline(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Deadline",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_DeclaredType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DeclaredType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Documentation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Documentation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Duration(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Duration",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_EnumerationType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EnumerationType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_EnumerationValue(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EnumerationValue",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ExtendedAttribute(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttribute",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ExternalPackage(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalPackage",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ExternalPackages(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalPackages",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ExternalReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalReference",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_FinishMode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FinishMode",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_FormalParameter(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FormalParameter",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_FormalParameters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FormalParameters",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Icon(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Icon",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Implementation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Implementation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_InitialValue(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "InitialValue",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Join(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Join",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Length(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Length",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Limit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Limit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ListType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ListType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Manual(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Manual",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Member(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Member",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_No(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "No",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Package(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Package",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_PackageHeader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PackageHeader",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Participant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Participant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Participants(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Participants",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ParticipantType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ParticipantType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Performer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Performer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Priority(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Priority",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_PriorityUnit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PriorityUnit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ProcessHeader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ProcessHeader",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_RecordType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RecordType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_RedefinableHeader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RedefinableHeader",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Responsible(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Responsible",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Responsibles(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Responsibles",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Route(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Route",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_SchemaType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SchemaType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Script",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_SimulationInformation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SimulationInformation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Split(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Split",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StartMode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "StartMode",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_SubFlow(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SubFlow",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TimeEstimation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TimeEstimation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Tool(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Tool",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Transition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TransitionRef(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TransitionRef",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TransitionRefs(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TransitionRefs",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TransitionRestriction(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TransitionRestriction",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TransitionRestrictions(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TransitionRestrictions",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transitions(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Transitions",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TypeDeclaration(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TypeDeclaration",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TypeDeclarations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TypeDeclarations",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_UnionType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "UnionType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ValidFrom(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ValidFrom",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ValidTo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ValidTo",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Vendor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Vendor",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Version(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Version",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WaitingTime(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WaitingTime",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WorkflowProcess(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WorkflowProcess",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WorkflowProcesses(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WorkflowProcesses",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WorkingTime(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WorkingTime",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_XPDLVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "XPDLVersion",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Xpression(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Xpression",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (durationUnitTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "DurationUnit_._type"
		   });		
		addAnnotation
		  (durationUnitTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "DurationUnit_._type:Object",
			 "baseType", "DurationUnit_._type"
		   });		
		addAnnotation
		  (enumerationTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "EnumerationType_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getEnumerationTypeType_EnumerationValue(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EnumerationValue",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (enumerationValueTypeEClass, 
		   source, 
		   new String[] {
			 "name", "EnumerationValue_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getEnumerationValueType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (executionTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "Execution_._type"
		   });		
		addAnnotation
		  (executionType1EEnum, 
		   source, 
		   new String[] {
			 "name", "Execution_._1_._type"
		   });		
		addAnnotation
		  (executionTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "Execution_._type:Object",
			 "baseType", "Execution_._type"
		   });		
		addAnnotation
		  (executionTypeObject1EDataType, 
		   source, 
		   new String[] {
			 "name", "Execution_._1_._type:Object",
			 "baseType", "Execution_._1_._type"
		   });		
		addAnnotation
		  (extendedAttributesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ExtendedAttributes_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getExtendedAttributesType_ExtendedAttribute(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttribute",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (extendedAttributeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ExtendedAttribute_._type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getExtendedAttributeType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getExtendedAttributeType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:1"
		   });		
		addAnnotation
		  (getExtendedAttributeType_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##any",
			 "name", ":2",
			 "processing", "lax",
			 "group", "#group:1"
		   });		
		addAnnotation
		  (getExtendedAttributeType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (getExtendedAttributeType_Value(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Value"
		   });		
		addAnnotation
		  (externalPackagesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ExternalPackages_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getExternalPackagesType_ExternalPackage(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalPackage",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (externalPackageTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ExternalPackage_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getExternalPackageType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getExternalPackageType_Href(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "href"
		   });		
		addAnnotation
		  (externalReferenceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ExternalReference_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getExternalReferenceType_Location(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "location"
		   });		
		addAnnotation
		  (getExternalReferenceType_Namespace(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "namespace"
		   });		
		addAnnotation
		  (getExternalReferenceType_Xref(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xref"
		   });		
		addAnnotation
		  (finishModeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "FinishMode_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getFinishModeType_Automatic(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Automatic",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getFinishModeType_Manual(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Manual",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (formalParametersTypeEClass, 
		   source, 
		   new String[] {
			 "name", "FormalParameters_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getFormalParametersType_FormalParameter(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FormalParameter",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (formalParameterTypeEClass, 
		   source, 
		   new String[] {
			 "name", "FormalParameter_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getFormalParameterType_DataType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DataType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getFormalParameterType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getFormalParameterType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getFormalParameterType_Index(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Index"
		   });		
		addAnnotation
		  (getFormalParameterType_Mode(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Mode"
		   });		
		addAnnotation
		  (graphConformanceTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "GraphConformance_._type"
		   });		
		addAnnotation
		  (graphConformanceTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "GraphConformance_._type:Object",
			 "baseType", "GraphConformance_._type"
		   });		
		addAnnotation
		  (implementationTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Implementation_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getImplementationType_No(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "No",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getImplementationType_Tool(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Tool",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getImplementationType_SubFlow(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SubFlow",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (instantiationTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "Instantiation_._type"
		   });		
		addAnnotation
		  (instantiationTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "Instantiation_._type:Object",
			 "baseType", "Instantiation_._type"
		   });		
		addAnnotation
		  (isArrayTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "IsArray_._type"
		   });		
		addAnnotation
		  (isArrayTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "IsArray_._type:Object",
			 "baseType", "IsArray_._type"
		   });		
		addAnnotation
		  (joinTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Join_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getJoinType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Type"
		   });		
		addAnnotation
		  (listTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ListType_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getListTypeType_BasicType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BasicType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getListTypeType_DeclaredType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DeclaredType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getListTypeType_SchemaType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SchemaType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getListTypeType_ExternalReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalReference",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getListTypeType_RecordType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RecordType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getListTypeType_UnionType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "UnionType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getListTypeType_EnumerationType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EnumerationType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getListTypeType_ArrayType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ArrayType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getListTypeType_ListType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ListType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (manualTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Manual_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (memberTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Member_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMemberType_BasicType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BasicType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getMemberType_DeclaredType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DeclaredType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getMemberType_SchemaType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SchemaType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getMemberType_ExternalReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalReference",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getMemberType_RecordType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RecordType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getMemberType_UnionType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "UnionType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getMemberType_EnumerationType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EnumerationType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getMemberType_ArrayType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ArrayType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getMemberType_ListType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ListType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (modeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "Mode_._type"
		   });		
		addAnnotation
		  (modeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "Mode_._type:Object",
			 "baseType", "Mode_._type"
		   });		
		addAnnotation
		  (noTypeEClass, 
		   source, 
		   new String[] {
			 "name", "No_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (packageHeaderTypeEClass, 
		   source, 
		   new String[] {
			 "name", "PackageHeader_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getPackageHeaderType_XPDLVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "XPDLVersion",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageHeaderType_Vendor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Vendor",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageHeaderType_Created(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Created",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageHeaderType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageHeaderType_Documentation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Documentation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageHeaderType_PriorityUnit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PriorityUnit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageHeaderType_CostUnit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "CostUnit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (packageTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Package_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getPackageType_PackageHeader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "PackageHeader",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_RedefinableHeader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RedefinableHeader",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_ConformanceClass(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConformanceClass",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Script",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_ExternalPackages(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalPackages",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_TypeDeclarations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TypeDeclarations",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_Participants(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Participants",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_Applications(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Applications",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_DataFields(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DataFields",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_WorkflowProcesses(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WorkflowProcesses",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPackageType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getPackageType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (participantsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Participants_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getParticipantsType_Participant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Participant",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (participantTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Participant_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getParticipantType_ParticipantType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ParticipantType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getParticipantType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getParticipantType_ExternalReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalReference",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getParticipantType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getParticipantType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getParticipantType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (participantTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ParticipantType_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getParticipantTypeType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Type"
		   });		
		addAnnotation
		  (processHeaderTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ProcessHeader_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProcessHeaderType_Created(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Created",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getProcessHeaderType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getProcessHeaderType_Priority(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Priority",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getProcessHeaderType_Limit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Limit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getProcessHeaderType_ValidFrom(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ValidFrom",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getProcessHeaderType_ValidTo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ValidTo",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getProcessHeaderType_TimeEstimation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TimeEstimation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getProcessHeaderType_DurationUnit(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "DurationUnit"
		   });		
		addAnnotation
		  (publicationStatusTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "PublicationStatus_._type"
		   });		
		addAnnotation
		  (publicationStatusTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "PublicationStatus_._type:Object",
			 "baseType", "PublicationStatus_._type"
		   });		
		addAnnotation
		  (recordTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "RecordType_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getRecordTypeType_Member(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Member",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (redefinableHeaderTypeEClass, 
		   source, 
		   new String[] {
			 "name", "RedefinableHeader_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getRedefinableHeaderType_Author(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Author",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getRedefinableHeaderType_Version(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Version",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getRedefinableHeaderType_Codepage(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Codepage",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getRedefinableHeaderType_Countrykey(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Countrykey",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getRedefinableHeaderType_Responsibles(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Responsibles",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getRedefinableHeaderType_PublicationStatus(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "PublicationStatus"
		   });		
		addAnnotation
		  (responsiblesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Responsibles_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getResponsiblesType_Responsible(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Responsible",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (routeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Route_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (schemaTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "SchemaType_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSchemaTypeType_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##other",
			 "name", ":0",
			 "processing", "lax"
		   });		
		addAnnotation
		  (scriptTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Script_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getScriptType_Grammar(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Grammar"
		   });		
		addAnnotation
		  (getScriptType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Type"
		   });		
		addAnnotation
		  (getScriptType_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Version"
		   });		
		addAnnotation
		  (simulationInformationTypeEClass, 
		   source, 
		   new String[] {
			 "name", "SimulationInformation_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSimulationInformationType_Cost(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Cost",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSimulationInformationType_TimeEstimation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TimeEstimation",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSimulationInformationType_Instantiation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Instantiation"
		   });		
		addAnnotation
		  (splitTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Split_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSplitType_TransitionRefs(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TransitionRefs",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSplitType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Type"
		   });		
		addAnnotation
		  (startModeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "StartMode_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStartModeType_Automatic(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Automatic",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getStartModeType_Manual(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Manual",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (subFlowTypeEClass, 
		   source, 
		   new String[] {
			 "name", "SubFlow_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSubFlowType_ActualParameters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActualParameters",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSubFlowType_Execution(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Execution"
		   });		
		addAnnotation
		  (getSubFlowType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (timeEstimationTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TimeEstimation_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTimeEstimationType_WaitingTime(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WaitingTime",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTimeEstimationType_WorkingTime(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WorkingTime",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTimeEstimationType_Duration(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Duration",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (toolTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Tool_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getToolType_ActualParameters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActualParameters",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getToolType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getToolType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getToolType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getToolType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Type"
		   });		
		addAnnotation
		  (transitionRefsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TransitionRefs_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTransitionRefsType_TransitionRef(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TransitionRef",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (transitionRefTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TransitionRef_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTransitionRefType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (transitionRestrictionsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TransitionRestrictions_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTransitionRestrictionsType_TransitionRestriction(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TransitionRestriction",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (transitionRestrictionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TransitionRestriction_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTransitionRestrictionType_Join(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Join",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTransitionRestrictionType_Split(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Split",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (transitionsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Transitions_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTransitionsType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Transition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (transitionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Transition_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTransitionType_Condition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Condition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTransitionType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTransitionType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTransitionType_From(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "From"
		   });		
		addAnnotation
		  (getTransitionType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getTransitionType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (getTransitionType_To(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "To"
		   });		
		addAnnotation
		  (typeDeclarationsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TypeDeclarations_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTypeDeclarationsType_TypeDeclaration(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TypeDeclaration",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (typeDeclarationTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TypeDeclaration_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTypeDeclarationType_BasicType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "BasicType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_DeclaredType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DeclaredType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_SchemaType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "SchemaType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_ExternalReference(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExternalReference",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_RecordType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RecordType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_UnionType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "UnionType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_EnumerationType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "EnumerationType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_ArrayType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ArrayType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_ListType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ListType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTypeDeclarationType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getTypeDeclarationType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (typeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "Type_._type"
		   });		
		addAnnotation
		  (typeType1EEnum, 
		   source, 
		   new String[] {
			 "name", "Type_._1_._type"
		   });		
		addAnnotation
		  (typeType2EEnum, 
		   source, 
		   new String[] {
			 "name", "Type_._2_._type"
		   });		
		addAnnotation
		  (typeType3EEnum, 
		   source, 
		   new String[] {
			 "name", "Type_._3_._type"
		   });		
		addAnnotation
		  (typeType4EEnum, 
		   source, 
		   new String[] {
			 "name", "Type_._4_._type"
		   });		
		addAnnotation
		  (typeType5EEnum, 
		   source, 
		   new String[] {
			 "name", "Type_._5_._type"
		   });		
		addAnnotation
		  (typeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "Type_._4_._type:Object",
			 "baseType", "Type_._4_._type"
		   });		
		addAnnotation
		  (typeTypeObject1EDataType, 
		   source, 
		   new String[] {
			 "name", "Type_._1_._type:Object",
			 "baseType", "Type_._1_._type"
		   });		
		addAnnotation
		  (typeTypeObject2EDataType, 
		   source, 
		   new String[] {
			 "name", "Type_._3_._type:Object",
			 "baseType", "Type_._3_._type"
		   });		
		addAnnotation
		  (typeTypeObject3EDataType, 
		   source, 
		   new String[] {
			 "name", "Type_._2_._type:Object",
			 "baseType", "Type_._2_._type"
		   });		
		addAnnotation
		  (typeTypeObject4EDataType, 
		   source, 
		   new String[] {
			 "name", "Type_._type:Object",
			 "baseType", "Type_._type"
		   });		
		addAnnotation
		  (typeTypeObject5EDataType, 
		   source, 
		   new String[] {
			 "name", "Type_._5_._type:Object",
			 "baseType", "Type_._5_._type"
		   });		
		addAnnotation
		  (unionTypeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "UnionType_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getUnionTypeType_Member(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Member",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (workflowProcessesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "WorkflowProcesses_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getWorkflowProcessesType_WorkflowProcess(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "WorkflowProcess",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (workflowProcessTypeEClass, 
		   source, 
		   new String[] {
			 "name", "WorkflowProcess_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getWorkflowProcessType_ProcessHeader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ProcessHeader",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_RedefinableHeader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RedefinableHeader",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_FormalParameters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FormalParameters",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_DataFields(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "DataFields",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_Participants(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Participants",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_Applications(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Applications",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_ActivitySets(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ActivitySets",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_Activities(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Activities",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_Transitions(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "Transitions",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_ExtendedAttributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ExtendedAttributes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getWorkflowProcessType_AccessLevel(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "AccessLevel"
		   });		
		addAnnotation
		  (getWorkflowProcessType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Id"
		   });		
		addAnnotation
		  (getWorkflowProcessType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "Name"
		   });		
		addAnnotation
		  (xpressionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "Xpression_._type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getXpressionType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getXpressionType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:1"
		   });		
		addAnnotation
		  (getXpressionType_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##any",
			 "name", ":2",
			 "processing", "lax",
			 "group", "#group:1"
		   });
	}

} //Xpdl1PackageImpl
