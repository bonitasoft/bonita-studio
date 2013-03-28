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
    public static String Validation_SeveralDefaultTransitionsAfterXORGateway;
    public static String Validation_NotConditionalTransitionAfterXORGateway;
    public static String Validation_loopMissingCondition;
    public static String Validation_Validator_EmptyErrorMessage;
    public static String Validation_Validator_EmptyValidatorType;
    public static String Validation_Task_NoGroup;
    public static String Validation_Widget_SameName;
    public static String Validation_Element_SameName;
    public static String Validation_Form_NoButtons;
    public static String Validation_StartElementBadConnection;
    public static String Validation_EventObject_TargetsEmpty;
    public static String Validation_TimerEvent_MissingCondition;
    public static String Validation_NoEndEvent;
    public static String Validation_CatchEventNoMessage;
    public static String Validation_NoIncomingLink;
    public static String Validation_NoStartEvent;
    public static String Validation_NoMultiInst;
    public static String Validation_NoSubProcess;
    public static String Validation_NoMultiInstVar;
    public static String Validation_LinkEvent_MissingTarget;
    public static String Validation_LinkEvent_MissingSource;
    public static String Validation_Subprocess_Not_Found;
    public static String Validation_Subprocess_InputMapping_TargetData_Not_Found;
    public static String Validation_Subprocess_OutputMapping_SourceData_Not_Found;
    public static String Validation_Lane_NoActors;
    public static String validation_errorEvent_codeNotSet;
    public static String validation_exceptionTransitionError_noTransition;
    public static String validation_exceptionTransitionError_tooMuchTransition;
    public static String validation_event_missingIncomming;
    public static String validation_event_missingoutgoing;
    public static String validation_signalEvent_codeNotSet;
    public static String Validation_SimulationEmptyExpression;
    public static String Validation_noSubProcPoolTarget;
    public static String Validation_SubProcMustHaveStartEvent;
    public static String Validation_SubProcMustHaveOnlyOneStartEvent;
    public static String Validation_SubProcEventMustHaveAParent;
    public static String Validation_NoMoreThan2IncomingInInclusiveGateway;
    public static String processDoesNotExist;
    public static String elementNotCatchMessage;
    public static String duplicateMessageFlowError;
    public static String Validation_DuplicateID_Subprocess;
    public static String missingMessageEvent;
    public static String Validation_Validator_MissingValidatorType;
    public static String SequenceFlow_Expression_ReturnBoolean;
    public static String Connector_Definition_Missing_Validation;
    public static String Filter_Definition_Missing_Validation;
    public static String targetProcessNotSet;
    public static String validating;
    public static String pageTemplateNotValid;
    public static String Validation_multiInstanceCardinalityNotWellConfigured;
    public static String Validation_multiInstanceCollectionNotWellConfigured;
    public static String incompatilbeOperationReturnType;
    public static String validationFailedTitle;
    public static String validationFailedMessage;
    
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

    
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

}
