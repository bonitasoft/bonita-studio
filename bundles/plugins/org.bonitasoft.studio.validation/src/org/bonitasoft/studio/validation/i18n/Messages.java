/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.i18n;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    public static String DuplicatedFileWidgetMultipleDocumentConsistencyError;

    public static String Validation_CorrelationKeyNotUsed;

    public static String Validation_CorrelationKeyNotDefine;

    public static String Validation_Duplicate_Process;

    public static String Validation_SeveralDefaultTransitionsAfterXORGateway;

    public static String Validation_NotConditionalTransitionAfterXORGateway;

    public static String Validation_Validator_EmptyErrorMessage;

    public static String Validation_Validator_EmptyValidatorType;

    public static String Validation_Widget_SameName;

    public static String Validation_Element_SameName;

    public static String Validation_Form_NoButtons;

    public static String Validation_TimerEvent_MissingCondition;

    public static String Validation_NoEndEvent;

    public static String Validation_CatchEventNoMessage;

    public static String Validation_NoIncomingLink;

    public static String Validation_NoStartEvent;

    public static String Validation_NoSubProcess;

    public static String Validation_LinkEvent_MissingTarget;

    public static String Validation_LinkEvent_MissingSource;

    public static String Validation_Subprocess_Not_Found;

    public static String Validation_Subprocess_InputMapping_TargetData_Not_Found;

    public static String Validation_Subprocess_OutputMapping_SourceData_Not_Found;

    public static String validation_errorEvent_codeNotSet;

    public static String validation_exceptionTransitionError_noTransition;

    public static String validation_exceptionTransitionError_tooMuchTransition;

    public static String validation_signalEvent_codeNotSet;

    public static String Validation_SubProcMustHaveStartEvent;

    public static String Validation_SubProcMustHaveOnlyOneStartEvent;

    public static String Validation_NoMoreThan2IncomingInInclusiveGateway;

    public static String processDoesNotExist;

    public static String Validation_Validator_MissingValidatorType;

    public static String SequenceFlow_Expression_ReturnBoolean;

    public static String targetProcessNotSet;

    public static String pageTemplateNotValid;

    public static String incompatilbeOperationReturnType;

    public static String validationFailedTitle;

    public static String validationViewElementColumnName;

    public static String validationViewSeverityColumnName;

    public static String validationViewDescriptionColumnName;

    public static String validationViewValidateButtonLabel;

    public static String validationErrorFoundMessage;

    public static String groovyCompilationProblem;

    public static String validation_errorEndingPageflow;

    public static String connectableElementIsNotAScriptorServiceTask;

    public static String tooManyConnectorsDefined;

    public static String missingDefaultSequenceFlow;

    public static String missingDocumentFile;

    public static String Validation_MultiInstantiationOutputData;

    public static String Validation_MultiInstantiationInputData;

    public static String leftOperandMissing;

    public static String errorTooltip;
    public static String warningTooltip;
    public static String infoTooltip;

    public static String invalidInclusiveMergeConstraints;

    public static String validation_messageContentWithEmptyFields;

    public static String validationTaskOperationWithEmptyFields;

    public static String targetCatchMessageNotExists;

    public static String vaidation_TimerEvent_WrongReturnType;

    public static String unresolvedDependenciesFor;

    public static String unresolvedPatternDependenciesFor;

    public static String unsupportedReturnTypeForAvailableValuesOf;

    public static String incomingTransitionNotSupported;

    public static String outgoingTransitionNotSupported;

    public static String Validation_StartMessageEventWithCorrelation;

    public static String Validation_noConnectorDefFound;

    public static String invalidParalellMergeConstraints;

    public static String Validation_noActorFilterDefFound;

    public static String Validation_InconsistentConnectorDefAndConfigurationInput;

    public static String Validation_InconsistentActorDefAndConfigurationInput;

    public static String Validation_InconsistentConnectorDefAndConfigurationOutput;

    public static String Validation_InconsistentActorDefAndConfigurationOutput;

    public static String removed;

    public static String mandatory;

    public static String emptynameMessage;

    public static String elementName;

    public static String invalidConditionExpression;

    public static String overviewFormCantHaveSubmitButton;

    public static String inconsistentLeftOperand;

    public static String defaultGroovyMethodImportMissing;

    public static String invalidDependencyAssignement;
    public static String validation_OperatorConsistency;

    public static String cmisConnectorAtomPubConfigMissingUrl;

    public static String cmisConnectorWebserviceConfigMissingUrl;

    public static String queryReturnTypeIsInvalid;

    public static String queryNotExistsInBusinessObject;

    public static String queryReferencedAnUnexistingBusinessObject;

    public static String invalidQueryExpression;

    public static String queryParameterDoesNotExistInQueryDefinition;

    public static String queryParameterReturnTypeIsInvalid;

    public static String queryParameterDoesNotExistInExpression;

    public static String businessObjectDefinitionNotFound;
    public static String invalidContractConstraintDefinition;

    public static String invalidInternalFormMapping;

    public static String entryFormMapping;

    public static String overviewFormMapping;

    public static String invalidURLFormMapping;

    public static String duplicatedFormName;

    public static String sequenceFlow_Without_Target_Element;
    public static String sequenceFlow_Without_Source_Element;
    public static String sequenceFlow_Without_Container;
    public static String sequenceFlow_And_SourceElement_Not_In_The_Same_Container;
    public static String sequenceFlow_And_TargetElement_Not_In_The_Same_Container;

    public static String invalidMultipleFileContractInput;
    public static String invalidFileContractInputType;
    public static String invalidSingleFileContractInput;
    public static String missingFileContractInput;
    public static String emptyFormMappingWarning;
    public static String cannotUseThisStartEventTypeWithAContract;
    public static String failedToRetrieveLeftOperandType;
    public static String methodDoesnotExistInLeftOperandType;
    public static String failedToRetrieveExpressionType;
    public static String methodDoesnotExist;
    public static String nameUnicityConstraintsDuplicateNameMessage;
    public static String nameUnicityConstraintsGenericMessage;
    public static String nameUnicityConstraintDocument;
    public static String nameUnicityConstraintParameter;
    public static String nameUnicityConstraintContractInput;
    public static String nameUnicityConstraintVariable;
    public static String formMappingAtDiagramLevel_ModelInconsistency;
    public static String instantiationFormMapping;
    public static String conflictingQueryNamesInBusinessObject;
    public static String Validation_jasperConnectorRemoved;
    public static String invalidColumnUsageInTaskName;

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

}
