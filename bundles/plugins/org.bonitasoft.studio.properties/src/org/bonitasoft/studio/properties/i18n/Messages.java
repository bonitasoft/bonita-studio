/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.i18n;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    // GeneralSection
    public static String GeneralSection_Name;
    public static String GeneralSection_Description;
    public static String GeneralSection_Version;
    // Add data Wizard
    public static String dataNameLabel;
    public static String dataDescriptionLabel;
    public static String datatypeLabel;
    public static String defaultValueLabel;

    // Connector section
    public static String updateConnector;
    public static String removeConnector;
    public static String addConnector;
    public static String removeConnectorConfirmTitle;
    public static String removeConnectorConfirmMessage;
    public static String connectorAdd_tooltip;

    // Activity type selection
    public static String activityType;
    public static String activityType_task;
    public static String activityType_callActivity;
    public static String activityType_receiveTask;
    public static String activityType_sendTask;
    public static String activityType_serviceTask;
    public static String activityType_scriptTask;

    // Form Section
    public static String createForm_title;
    public static String createForm_desc;
    public static String createForm_defaultName;
    public static String createForm_noData;
    public static String error_allreadyExists;
    public static String error_empty;
    public static String duplicateForm_desc;
    public static String duplicateForm_title;
    public static String duplicateForm_radioCreate;
    public static String duplicateForm_radioDuplicate;
    public static String subprocess;
    public static String connectionConnection;
    public static String gatwetypeAnd;
    public static String gatwetypeXor;
    public static String gatwetypeInclusive;
    public static String gatewayType;
    public static String addForm;
    public static String removeForm;
    public static String editForm;
    public static String formUp;
    public static String formDown;
    public static String selectAll;
    public static String FormsSection_ConfirmationTemplate;
    public static String formRemoveFormCommand;
    public static String FormsSection_wizardVarsGroup_Title;
    public static String FormsSection_wizardVarsGroup_Tooltip;

    // resource section
    public static String Browse;
    public static String Remove;
    public static String File;
    public static String Folder;
    public static String Add;
    public static String Clear;
    public static String Download;
    public static String Error;
    public static String ResourceSection_ErrorTemplate;
    public static String ResourceSection_ProcessTemplate;
    public static String ResourceSection_LoginPage;
    public static String ResourceSection_AutoLogin;
    public static String ResourceSection_AutoLoginTooltip;

    //
    public static String up;
    public static String down;

    public static String priority;

    public static String durationLabel;
    public static String estimatadExecutionTimeLabel;

    public static String assignTo;
    public static String sourceParameter;

    public static String removeEventConfirmMessage;

    public static String removeEventConfirmTitle;

    public static String messageEventAddWizardPageName;

    public static String messageEventAddWizardPageTitle;

    public static String messageEventAddWizardPageDesc;

    public static String selectMessageEventLabel;

    public static String messageFlowEventChoice;

    public static String eventType_messageStart;

    public static String eventType_messageEnd;

    public static String eventType_intermediateCatch;

    public static String eventType_intermediateThrow;

    public static String messageEventType;

    public static String timerConditionWizardTitle;

    public static String timerCondition;

    public static String editCondition;

    public static String addTargetEventDialogTitle;

    public static String processNameLabel;

    public static String eventNameLabel;

    public static String gotoLabel;

    public static String fromLinksLabel;

    public static String linkType;

    public static String catchLink;

    public static String throwLink;

    public static String selectSignalEventLabel;
    public static String defaultFlowLabel;

    public static String testBeforeLabel;
    public static String loopConditionLabel;
    public static String maximumLoopLabel;

    public static String noneLabel;
    public static String optionalLabel;

    public static String confirmationMessage;

    public static String version;
    public static String name;
    public static String localTemplate;

    public static String selectLocalTemplateWizardPageDescription;
    public static String selectLocalTemplateWizardPageTitle;

    public static String webTemplates;

    public static String ResourceSection_PageTemplate;

    public static String dynamicLabelLabel;
    public static String testAfterLabel;

    public static String dynamicDescriptionLabel;

    public static String saveAsTemplate_templateLabel;
    public static String saveAsTemplate_previewPathLabel;

    public static String ResourceSection_SaveAsTemplate;

    public static String Edit;

    public static String saveAsTemplate_title;

    public static String newCategory;
    public static String ResourceSection_importTemplate;
    public static String ResourceSection_export;

    public static String ResourceSection_ViewTemplate;

    public static String StepSummarySectionContribution_title;
    public static String save;
    public static String showLabel;
    public static String errorEvent_error;
    public static String errorEvent_errorCodeHint;
    public static String errorEvent_errorCodeCatchHint;
    public static String mustBeSet;
    public static String dynamicDescriptionHint;
    public static String dynamicLabelHint;
    public static String stepSummaryHint;
    public static String executionTimeHint;

    public static String january;
    public static String february;
    public static String march;
    public static String april;
    public static String may;
    public static String june;
    public static String july;
    public static String august;
    public static String september;
    public static String october;
    public static String november;
    public static String december;

    public static String monday;
    public static String tuesday;
    public static String wednesday;
    public static String thursday;
    public static String friday;
    public static String saturday;
    public static String sunday;

    public static String targetProcessMessageHint;

    public static String targetEventMessageHint;
    public static String signalEvent_catchHint;
    public static String signalEvent_throwHint;
    public static String endErrorEvent_error;

    public static String confirmDeleteFile_title;
    public static String confirmDeleteFile_msg;

    public static String pageFlowTransientDataConnectors;
    public static String pageFlowTransientData;
    public static String formsTabLabel;
    public static String transientDatTabLabel;
    public static String pageFlow;
    public static String skip;
    public static String skipPageFlowTooltip;
    public static String messageEventAddWizardNameAlreadyExists;

    public static String chooseFont;

    public static String ChooseFigure;

    public static String applyStyle;

    public static String activityType_abstractTask;

    public static String GeneralSection_ActivateValidation;

    public static String ResourceSection_OverWrite_title;

    public static String ResourceSection_OverWrite_msg;

    public static String saveAsTemplate_error;
    public static String saveAsTemplate_import_error;

    public static String confirmationPathTooltip;
    public static String confirmationMessageTooltip;

    public static String ResourceSection_BasedOnLookAndFeel;

    public static String widgetRemovedWarning_title;
    public static String widgetRemovedWarning_msg;

    public static String ResourceSection_ProcessTemplate_hint;
    public static String ResourceSection_PageTemplate_hint;
    public static String ResourceSection_ErrorTemplate_hint;
    public static String ResourceSection_ViewTemplate_hint;
    public static String autoMap;
    public static String autoMap_description;

    public static String addMessageContent;
    public static String correlation;
    public static String correlationDescription;
    public static String correlationKeyHelp;
    public static String useCorrelationkeys;
    public static String AddCorrelation;
    public static String removeCorrelation;
    public static String removeMessageContent;
    public static String addMessageContentButton;
    public static String addMessageContentDescription;
    public static String autoFillMessageContent;
    public static String useExpression;
    public static String useDecisionTable;
    public static String editDecisionTable;
    public static String and;
    public static String dontTakeTransition;
    public static String takeTransition;
    public static String emptyName;
    public static String operationExplanation;
    public static String dublicateIdErrorMessage;

    public static String highestPriority;
    public static String lowestPriority;
    public static String underNormalPriority;
    public static String aboveNormalPriority;
    public static String normalPriority;
    public static String transitionOrderingExplanation;
    public static String transitionOrderingExplanation_Short;

    public static String AddSimple;
    public static String description;

    public static String calledProcessVersionHint;
    public static String avoidElement;
    public static String avoidElementHint;

    public static String clearSelection;
    public static String multiInstance_completionConditionLabel;
    public static String multiInstance_completionConditionDescription;
    public static String addFormTitle;
    public static String correlationKey;
    public static String correlationValue;
    public static String messageContentID;
    public static String expressionName;
    public static String messageContentIdExistenceWarning;
    public static String NoIncomingMessageWarning;
    public static String generalPropertiesSectionDescription;
    public static String diagramSectionDescription;
    public static String catchMessageContentEventSectionDescription;
    public static String catchMessageEventCorrelationSectionDescription;
    public static String laneSectionDescription;
    public static String looknfeelPropertySectionDescription;
    public static String resourcePropertySectionDescription;
    public static String userXPSectionDescription;
    public static String confirmationPropertySectionDescription;
    public static String loopSectionDescription;
    public static String exportSuccessfullMsg;
    public static String exportSuccessfullTitle;
    public static String exporting;
    public static String importResultTitle;
    public static String importSuccessMsg;
    public static String oneCorrelationAtLeastNeeded;
    public static String valueShouldBeDefined;
    public static String idShouldBeDefined;
    public static String conditionExpresssionHelpMessage;
    public static String isMandatory;
    public static String messagesSectionDescription;
    public static String minutes;
    public static String every;
    public static String minuteLabel;
    public static String hourly;
    public static String daily;
    public static String weekly;
    public static String monthly;
    public static String yearly;
    public static String generateCronButtonLabel;
    public static String at;
    public static String hourLabel;
    public static String startTime;
    public static String everyWeekDay;
    public static String dayLabel;
    public static String day;
    public static String ofEvery;
    public static String monthLabel;
    public static String the;
    public static String first;
    public static String second;
    public static String third;
    public static String fourth;
    public static String fifth;
    public static String of;
    public static String timerConditionDescription;
    public static String timerConditionHint;
    public static String invalidCronExpression;
    public static String mustBeAnValueBetween;
    public static String notAValidNumber;
    public static String mustBeGreaterThan;
    public static String cantBeGreaterThan;
    public static String cycle;
    public static String fixedDate;
    public static String selectDateLabel;
    public static String basedOn;
    public static String generateFixedDateLabel;
    public static String selectDurationLabel;
    public static String generateDurationLabel;
    public static String startTimerConditionDescription;
    public static String startTimerConditionHint;
    public static String cronShortDescription;
    public static String notAValidInput;

    public static String errorDisplayLabelMaxLength;

    public static String warningDisplayLabelMaxLength;

    public static String expressionCantBeEmpty;

    public static String wiget;

    public static String mandatory;

    public static String readOnly;

    public static String renamingDiagram;

    public static String standardLoop;

    public static String parallelMultinstantition;

    public static String sequentialMultinstantition;

    public static String dataBasedInstanceLabel;

    public static String definedNumberOfInstanceLabel;

    public static String input;

    public static String output;

    public static String numberOfInstancesToCreate;

    public static String inputList;

    public static String inputListHint;

    public static String outputList;

    public static String iterator;

    public static String iteratorHint;

    public static String outputData;

    public static String storeOutputResult;

    public static String type;

    public static String typeHint;

    public static String processData;

    public static String noProcessDataAvailable;

    public static String businessData;

    public static String generateInitialValueForBusinessData;

    public static String generateInitialValueForBusinessDataHint;

    public static String noBusinessDataAvailable;
    public static String assignToContractInput;
    public static String assignToData;
    public static String targetParameterForOutput;
    public static String targetParameterForInput;
    public static String sectionDescriptionOverviewForm;
    public static String sectionDescriptionEntryForm;
    public static String sectionDescriptionProcessEntryForm;

    public static String processScopeVariableWarning;
    public static String dataToSendSectionDescription;
    public static String dataToRecieveSectionDescription;
    public static String dataFromRootProcess;
    public static String dataInCalledProcess;
    public static String dataFromCalledProcess;
    public static String dataInRootProcess;
    public static String configureDataToSend;
    public static String fetchContract;
    public static String processNotFoundExcpetion;
    public static String latest;
    public static String noProcessToCallDefined;
    public static String noContractDefinedException;
    public static String dueDateCalculation;
    public static String dueDateCalculationHint;
    public static String displayName;
    public static String displayNameCaption;
    public static String displayNameTooltip;
    public static String poolSectionDescription;
    public static String poolNameHint;
    public static String aNameMustBeSet;
    public static String maxNameLength;
    public static String maxDescriptionLength;
    public static String aVersionMustBeSet;
    public static String maxVersionLength;
    public static String technicalNameTooltip;
    public static String maxDisplayNameLength;
    
    public static String searchIndexTypeLabel;
    public static String indexName;
    public static String indexValue;
    public static String indexSearchDescription;
    public static String updatingSearchIndexReferences;
    public static String searchIndexUnicityError;


    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

    /**
     * @param event
     * @return
     */
    public static String getValue(final String event) {
        try {
            return (String) Messages.class.getField(event).get(null);
        } catch (final Exception ex) {
            return "Field [" + event + "] does not exist in Messages";
        }
    }
}
