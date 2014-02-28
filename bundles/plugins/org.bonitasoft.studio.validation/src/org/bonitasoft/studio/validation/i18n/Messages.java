/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.i18n;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
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
    public static String Validation_SimulationEmptyExpression;
    public static String Validation_SubProcMustHaveStartEvent;
    public static String Validation_SubProcMustHaveOnlyOneStartEvent;
    public static String Validation_NoMoreThan2IncomingInInclusiveGateway;
    public static String processDoesNotExist;
    public static String Validation_Validator_MissingValidatorType;
    public static String SequenceFlow_Expression_ReturnBoolean;
    public static String targetProcessNotSet;
    public static String validating;
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
    
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

}
