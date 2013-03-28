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
package org.bonitasoft.studio.properties.i18n;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$





	





//IndexSection
	

	
	//GeneralSection
    public static String GeneralSection_Name;
    public static String GeneralSection_Description;
    public static String GeneralSection_Version;
    public static String GeneralSection_Id;


    //TransitionCondition
    public static String setVisibilityOnDiagram;

    // Data Section
    public static String filterLabel;
    public static String addData;
    public static String removeData;
    public static String updateData;
    public static String createDataType;
    public static String promoteData;
    public static String promoteData_tooltip;
    // Add data Wizard
    public static String dataNameLabel;
    public static String dataDescriptionLabel;
    public static String datatypeLabel;
    public static String addDataWizardDescription;
    public static String addDataWizardTitle;
    public static String addDataWizardMessage;
    public static String addDataCommandLabel;
    public static String defaultValueLabel;
    // Update data
    public static String updateDataCommandLabel;
    public static String updateDataWizardTitle;
    public static String updateDataWizardDescription;
    public static String updateDataWizardMessage;
    public static String selectOnlyOneElementMessage;
    public static String selectOnlyOneElementTitle;
    //AddData Type Wizard
    public static String datatypeLiteralsLabel ;
    public static String addLiteralLabel ;
    public static String removeLiteralLabel ;
    public static String createLiteralTitle ;
    public static String literalLabel;
    public static String createNewTypeLabel;
    public static String addBasicDataTypeCommandLabel;
    public static String editTypeButtonLabel;
    //Data Types
    public static String dateFormatLabel;


    // Deletion
    public static String deleteDataDialogTitle;
    public static String deleteDialogConfirmMessage;
    public static String removeDataCommandLabel;
    public static String clear;

    // Actor section
    public static String actorSystem;
    public static String actorUser;
    public static String actorGroup;
    public static String userNameLabel;
    public static String groupsTableLabel;
    public static String addGroup;
    public static String removeGroup;
    public static String updateGroup;
    public static String createGroup;
    public static String addFilter;

    public static String addGroupCommandLabel;
    public static String addGroupCommandDescription;
    public static String addGroupWizardPage_title;
    public static String addGroupWizardPage_desc;

    public static String changeActorTypeCommandDesc;
    public static String changeActorTypeCommandLabel;

    public static String changeUserNameCommandDesc;
    public static String changeUserNameCommandLabel;


    public static String groupNameLabel;
    public static String chooseGroupNameWizardPageTitle;

    public static String removeGroupsAndFiltersCommandLabel;

    // Connector section
    public static String createConnector;
    public static String updateConnector;
    public static String removeConnector;
    public static String addConnector;
    public static String selectConnectorPageName;
    public static String selectConnectorPageDesc;
    public static String addConnectorCommandDesc;
    public static String addConnectorCommandLabel;
    public static String removeConnectorConfirmTitle;
    public static String removeConnectorConfirmMessage;
    public static String removeConnectorCommandDescription;
    public static String removeConnectorCommandLabel;
    public static String updateConnectorCommandDescription;
    public static String updateConnectorCommandLabel;
    public static String connectorAdd_tooltip;
    public static String connectorCreate_tooltip;

    public static String outputMapping;
    public static String outputMappingLabel;
    public static String destinationVariableLabel;
    public static String connectorOutputTitle;

    // Activity type selection
    public static String activityType;
    public static String activityType_task;
    public static String activityType_automatic;
    public static String activityType_callActivity;
    public static String activityType_receiveTask;
    public static String activityType_sendTask;
    public static String activityType_serviceTask;
    public static String activityType_scriptTask;

    public static String changeActivityTypeCommandDesc;
    public static String changeActivityTypeCommandLabel;

    // Configuration
    public static String selectConfigurationPageName;
    public static String selectConfigurationPageDesc;
    public static String saveConfigurationButton;
    public static String saveConfigurationButton_tooltip;
    public static String useInheritanceRadioLabel;
    public static String dontUseInheritanceRadioLabel;
    public static String saveConfigurationWizardPageName;
    public static String saveConfigurationWizardPageTitle;
    public static String saveConfigurationWizardPageDesc;
    public static String specifyConfigurationName;
    public static String startFromNewConfiguration_Radio;
    public static String startFromExistingConfiguration_Radio;
    public static String startFromCurrentConfiguration_Radio;

    public static String textAreaSelectDataLabel;

    public static String filtersTableLabel;
    public static String confirmDeleteFiltersTitle;
    public static String confirmDeleteFiltersDesc;
    public static String removeFiltersCommandLabel;

    public static String specifyConnnectorName_wizardTitle;
    public static String specifyConnnectorName_wizardDesc;

    public static String specifyFilterName_wizardTitle;
    public static String selectFilterPageDesc;
    public static String selectFilterPageName;
    public static String specifyFilterName_wizardDesc;

    public static String selectGroupPageDesc;
    public static String specifyGroupName_wizardDesc;
    public static String selectGroupPageName;
    public static String specifyGroupName_wizardTitle;

    //Form Section
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
    public static String selectActivityEvent;
    public static String connectorCrashLabel;
    public static String connectorFails_ignore;
    public static String connectorFails_crash;
    public static String connectionConnection;
    public static String conditionExamples;
    public static String configurationNameLabel;

    public static String showPassword;
    public static String deleteEnum;
    public static String okEnum;
    public static String cancelEnum;
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
    public static String unselectAll;
    public static String newEnumDescription ;
    public static String validatorDefaultName;
    public static String byPassForms;
    public static String byPassProcessForms;
    public static String FormsSection_ConfirmationTemplate;
    public static String formAddFormCommandDescription;
    public static String formAddFormCommandLabel;
    public static String formDuplicateFormCommandDescription;
    public static String formRemoveFormCommand;
    public static String FormsSection_DefaultSubmitName;
    public static String FormsSection_DefaultPreviousName;
    public static String formPreview;
    public static String FormsSection_widgetTypeDate;
    public static String FormsSection_widgetTypeText;
    public static String FormsSection_widgetTypeFile;
    public static String FormsSection_widgetTypeList;
    public static String FormsSection_widgetTypeSelect;
    public static String FormsSection_widgetTypeCheckBox;
    public static String FormsSection_widgetTypeRadio;
    public static String FormsSection_wizardVarsGroup_Title;
    public static String FormsSection_wizardVarsGroup_Tooltip;
    public static String multipleWidget;

    //Deadline Section
    public static String addDeadlineWizardDescription;
    public static String addDeadlineWizardTitle;
    public static String addDeadlineWizardMessage;




    public static String deadlineDateNameLabel;
    public static String deadlineTimeNameLabel;
    public static String deadlineValueLabel;

    public static String deleteDeadlineDialogTitle;
    public static String deleteDeadlineDialogConfirmMessage;



    public static String classLabel;



    public static String selectActivityEventDeadline;
    public static String deadlineTypeLabel;
    public static String deadlineDurationNameLabel;

    public static String javaConnectorTitle;
    public static String javaConnectorDesc;
    public static String methodsLabel;

    public static String browseClasses;
    public static String chooseGroup;

    public static String automaticOnEnter;
    public static String automaticOnExit;
    public static String taskOnCancel;
    public static String taskOnFinish;
    public static String taskOnReady;
    public static String taskOnResume;
    public static String taskOnStart;
    public static String taskOnSuspend;
    public static String instanceOnStart;
    public static String instanceOnCancel;
    public static String instanceOnAbort;
    public static String instanceOnFinish;

    public static String noConnectorFieldMessage;



    //resource section
    public static String Browse;
    public static String Remove;
    public static String File;
    public static String Folder;
    public static String Add;
    public static String Clear;
    public static String Download;
    public static String Upload;
    public static String Error;
    public static String Error_FileNotFound;

    public static String ResourceSection_HTMLTemplate;
    public static String ResourceSection_CSS;
    public static String ResourceSection_JavaScript;
    public static String ResourceSection_ResourceFile;
    public static String ResourceSection_ErrorTemplate;
    public static String ResourceSection_ProcessTemplate;
    public static String ResourceSection_ProcessTemplate_Tooltip;
    public static String ResourceSection_LoginPage;
    public static String ResourceSection_WelcomePage;
    public static String ResourceSection_AutoLogin;
    public static String ResourceSection_loginAs;
    public static String ResourceSection_AutoLoginTooltip;
    public static String ResourceSection_loginAsTooltip;
    public static String ResourceSection_HostPageTemplate;

    // application section
    public static String Application_FormsLabel;
    public static String Application_ResourcesLabel;

    public static String removeClassPathEntryTitle;
    public static String removeClassPathEntryMessage;

    public static String editorButtonLabel;

    //
    public static String up;
    public static String down;

    public static String priority;

    public static String attachementLabel;

    public static String multiInstantiationLabel;
    public static String isMultiInstantiated;
    public static String createMultiInstantiationClass;

    //Deadlines
    //	public static String specifyDeadlineName_wizardTitle;
    //	public static String specifyDeadlineName_wizardDesc;
    //	public static String selectDeadlinePageDesc;
    //	public static String selectDeadlinePageName;

    public static String durationLabel;
    public static String dateDataType;

    public static String joinNumber;
    public static String multiInstantiationClassPackage;
    public static String multiInstantiationClassName;
    public static String createMultiInstantiatorTitle;
    public static String createMultiInstantiatorDesc;
    public static String browsePackages;

    public static String invalidClassName;
    public static String invalidPackageName;

    public static String estimatadExecutionTimeLabel;

    public static String assignTo;
    public static String sourceParameter;
    public static String targetParameter;

    public static String refresh;
    public static String addJar;

    public static String dataAlreadyExist;

    public static String addColumn;
    public static String addRow;
    public static String removeRow;

    public static String varDataType;
    public static String deadlineVarNameLabel;
    public static String messageFlowThrowState;

    public static String connectionRoutingChoice;

    public static String synchronousLabel;

    public static String removeEventConfirmMessage;

    public static String removeEventConfirmTitle;

    public static String messageEventAddWizardPageName;

    public static String messageEventAddWizardPageTitle;

    public static String messageEventAddWizardPageDesc;

    public static String selectMessageEventLabel;

    public static String addDataLabel;

    public static String messageFlowEventChoice;

    public static String eventType_messageStart;

    public static String eventType_messageEnd;

    public static String eventType_intermediateCatch;

    public static String eventType_intermediateThrow;

    public static String messageEventType;

    public static String timerIntermediate;

    public static String timerSater;

    public static String timerType;

    public static String timerConditionWizardTitle;

    public static String timerCondition;

    public static String editCondition;

    public static String selectTargetEventLabel;

    public static String addTargetEventDialogTitle;

    public static String processNameLabel;

    public static String eventNameLabel;

    public static String literalAlreadyExistsTitle;

    public static String literalAlreadyExistsMessage;

    public static String noConnectorFieldMessageButOutpuFieldInnextPage;

    public static String External;

    public static String Internal;

    public static String setMatcherLabel;

    public static String ttlLabel;

    public static String Actors_removeGroupFromTask_message;
    public static String Actors_removeGroupFromTask_title;

    public static String dataNameIsnotAvailable;

    public static String Application_MyLibs;
    public static String Application_Validators;
    public static String Application_ProvidedLibs;

    public static String myLibs;
    public static String providedLibs;
    public static String connectors;
    public static String unresolvedDependencies;
    public static String validators;

    public static String switchContainerConnector;

    public static String switchContainerConnectorMessage;

    public static String switchContainerConnectorTitle;

    public static String copyConnectorCheckBoxLabel;

    public static String warningLocalVariableinConnector;

    public static String chooseConnectorToMove;



    public static String connectorDoesntExists;

    public static String gotoLabel;

    public static String fromLinksLabel;

    public static String linkType;

    public static String catchLink;

    public static String throwLink;

    public static String selectSignalEventLabel;

    public static String signalEventAddWizardPageDesc;

    public static String signalEventAddWizardPageName;

    public static String signalEventAddWizardPageTitle;

    public static String signalttlLabel;

    public static String defaultFlowLabel;

    public static String instantiatorName;
    public static String joinName;
    public static String groovyScript;




    public static String confirmRemoveMissingDepTitle;
    public static String confirmRemoveMissingDepMessage;

    public static String isLoopLabel;
    public static String testBeforeLabel;
    public static String loopConditionLabel;
    public static String maximumLoopLabel;

    public static String editListInGroovy;
    public static String editUsingTableView;
    public static String expectedMatrix;
    public static String editUsingListView;
    public static String expectedList;


    public static String noneLabel;
    public static String optionalLabel;

    public static String confirmationMessage;

    public static String latestLabel;
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

    public static String testConnectorDescription;
    public static String testConnectorTitle;

    public static String testConnector;
    public static String testConnector_tooltip;
    public static String saveAsTemplate_title;

    public static String newCategory;
    public static String categoriesTitle;

    public static String unableToExportTemplate;
    public static String ResourceSection_importTemplate;
    public static String ResourceSection_export;

    public static String keepLaneDefinition;
    public static String overrideLaneDefinition;

    public static String FormsSection_ViewList;
    public static String FormsSection_EditList;

    public static String ResourceSection_ViewTemplate;
    public static String Application_TemplatesTab;
    public static String Application_ResourcesTab;

    public static String FormsSection_ViewHelp;
    public static String FormsSection_EntryHelp;

    public static String selectElementLabel;
    public static String xpath;
    public static String selectXSDToImport;

    public static String StepSummarySectionContribution_title;

    public static String FormsSection_RecapHelp;
    public static String FormsSection_RecapList;
    public static String save;
    public static String showLabel;
    public static String errorEvent_error;
    public static String errorEvent_errorCodeHint;
    public static String errorEvent_errorCodeCatchHint;
    public static String mustBeSet;
    public static String dynamicDescriptionHint;
    public static String dynamicLabelHint;
    public static String stepSummaryHint;
    public static String categoriesHint;
    public static String executionTimeHint;

    public static String StartTimerCondition_everyYear;
    public static String StartTimerCondition_everyMonth;
    public static String StartTimerCondition_everyDay;
    public static String StartTimerCondition_everyWeek;
    public static String StartTimerCondition_everyHour;
    public static String StartTimerCondition_everyMinutes;
    public static String StartTimerCondition_script;
    public static String StartTimerCondition_dayOfMonth;
    public static String StartTimerCondition_dayOfYear;
    public static String StartTimerCondition_dayOfMonthInWeek;
    public static String StartTimerCondition_from;
    public static String StartTimerCondition_at;
    public static String StartTimerCondition_every;
    public static String StartTimerCondition_hours;
    public static String StartTimerCondition_minutes;
    public static String StartTimerCondition_everyDayAt;
    public static String StartTimerCondition_ofEachWeek;
    public static String StartTimerCondition_ofEachMonth;
    public static String StartTimerCondition_dayOfTheYear;
    public static String StartTimerCondition_of;

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

    public static String StartTimerCondition_hint_monthlyDayOfTheWeek;
    public static String StartTimerCondition_customize;

    public static String loopLabel;

    public static String confirmDeleteFile_title;
    public static String confirmDeleteFile_msg;

    public static String deadlines;


    public static String subProcEvent;
    public static String pageFlowTransientDataConnectors;
    public static String pageFlowTransientData;
    public static String formsTabLabel;
    public static String transientDatTabLabel;
    public static String pageFlow;
    public static String skip;
    public static String skipPageFlowTooltip;
    public static String messageEventAddWizardNameAlreadyExists;

    public static String restoreDefault;
    public static String showCondition;

    public static String hideAllLabels;

    public static String labelVisibility;

    public static String showNameLabel;

    public static String chooseFont;

    public static String ChooseFigure;

    public static String applyStyle;

    public static String StartTimerCondition_fixedDate;

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
    public static String ResourceSection_HostPageTemplate_hint;
    public static String ResourceSection_ViewTemplate_hint;
    public static String updateReferencesTitle;
    public static String updateReferencesMsg;
    public static String updatingReferences;
    public static String autoMap;
    public static String autoMap_description;

    public static String moveData;
    public static String moveData_tooltip;

    public static String key;
    public static String data;
    public static String addMessageContent;
    public static String correlation;
    public static String noCorrelation;
    public static String useCorrelationkeys;
    public static String AddCorrelation;
    public static String removeCorrelation;
    public static String removeMessageContent;
    public static String addMessageContentButton;
    public static String autoFillMessageContent;
    public static String diagramAlreadyExistsTitle;
    public static String diagramAlreadyExistsMsg;
    public static String useExpression;
    public static String useDecisionTable ;
    public static String editDecisionTable;
    public static String and;
    public static String dontTakeTransition;
    public static String takeTransition;
    public static String emptyName;
    public static String operationExplanation;
    public static String dublicateIdErrorMessage;


    public static String missingTargetStep;
    public static String missingTargetProcess;
    public static String highestPriority;
    public static String lowestPriority;
    public static String underNormalPriority;
    public static String aboveNormalPriority;
    public static String normalPriority;
    public static String transitionOrderingExplanation;
    public static String transitionOrderingExplanation_Short;

    public static String documentDefaultName;

    public static String remove;
    public static String selectDocumentDescription;
    public static String explainDocumentPropertySection;
    public static String explanationExternalDocument;
    public static String explanationInternalDocument;
    public static String selectDocumentDialogTitle;
    public static String importEtc;
    public static String AddSimple;
    public static String description;
    public static String mimeType;
    public static String hintExternalUrl;

    public static String selectApplicationResourceFileWizardDescription;
    public static String selectApplicationResourceFileWizardTitle;
    public static String explanationMimeTypeDocument;
    public static String hintMimeTypeDocument;
    public static String calledProcessVersionHint;
    public static String avoidElement;
    public static String avoidElementHint;

    public static String multiInstance_useCollection;
    public static String multiInstance_inputData;
    public static String multiInstance_outputData;
    public static String multiInstance_useCardinality;
    public static String clearSelection;
    public static String multiInstance_inputDataDescription;
    public static String multiInstance_sequentialDescription;
    public static String multiInstance_completionConditionLabel;
    public static String multiInstance_completionConditionDescription;
    public static String multiInstance_sequentialButton;
    public static String multiInstance_useCollectionDescription;
    public static String multiInstance_outputDataDescription;
    public static String multiInstance_useCardinalityDescription;
    public static String multiInstance_listOutputDataDescription;
    public static String multiInstance_listOutputDataLabel;
    public static String addFormTitle;
    public static String correlationKey;
    public static String correlationValue;
    public static String messageContentID;
    public static String expressionName;
    public static String messageContentIdExistenceWarning;
    public static String throwMessageExpressionTypeWarning;
    public static String NoIncomingMessageWarning;
    public static String generalPropertiesSectionDescription;
    public static String diagramSectionDescription;
    public static String catchMessageContentEventSectionDescription;
    public static String catchMessageEventCorrelationSectionDescription;
    public static String documentPropertySectionDescription;
    public static String laneSectionDescription;
    public static String looknfeelPropertySectionDescription;
    public static String resourcePropertySectionDescription;
    public static String parametersMappingSectionDescription;
    public static String userXPSectionDescription;
    public static String confirmationPropertySectionDescription;
    public static String loopSectionDescription;
    public static String correlationHelp;
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
	public static String errorOutputDataMultiInstanceCollection;
	public static String errorInputDataMultiInstanceCollection;
	
	
	
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
    public static String getValue(String event) {
        try {
            return (String) Messages.class.getField(event).get(null);
        } catch (Exception ex) {
            return "Field [" + event + "] does not exist in Messages";
        }
    }
}
