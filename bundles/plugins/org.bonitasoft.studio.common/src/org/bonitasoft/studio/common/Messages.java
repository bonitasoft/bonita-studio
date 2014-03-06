/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.common;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 * 
 */
public class Messages extends NLS {

    static {
        NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
    }

    public static String GatewayDefaultName;
    public static String StepDefaultName;
    public static String SequenceFlowDefaultName;
    public static String WidgetDefaultLabel;
    public static String SubmitFormButtonDefaultLabel;
    public static String PreviousFormButtonDefaultLabel;
    public static String NextFormButtonDefaultLabel;
    public static String startEventDefaultName;
    public static String endEventDefaultName;
    public static String GeneralSection_Description;
    public static String GeneralSection_TextAnnotation;
    public static String CustomizedFormButtonDefaultLabel;
    public static String PoolDefaultName;
    public static String setConnectionEndCommandLabel;
    public static String setConnectionsCommandLabel;
    public static String TextFormFieldDefaultLabel;
    public static String TextAreaFormFieldDefaultLabel;
    public static String CheckBoxSingleFormFieldDefaultLabel;
    public static String CheckBoxMultipleFormFieldDefaultLabel;
    public static String ListFormFieldDefaultLabel;
    public static String PasswordFormFieldDefaultLabel;
    public static String RadioFormFieldDefaultLabel;
    public static String SelectFormFieldDefaultLabel;
    public static String MessageInfoDefaultLabel;
    public static String TextInfoDefaultLabel;
    public static String DateFormFieldDefaultLabel;
    public static String FileWidgetDefaultLabel;
    public static String identifierTootltip;
    public static String daysLabel;
    public static String hoursLabel;
    public static String minutesLabel;
    public static String secondsLabel;
    public static String intermediateMessageEventDefaultName;
    public static String MessageFlowDefaultName;
    public static String DurationFormFieldDefaultLabel;
    public static String intermeiateTimerEventDefaultName;
    public static String handleShellCloseEventTitle;
    public static String handleShellCloseEventMessage;
    public static String monthsLabel;
    public static String yearsLabel;
    public static String GroupDefaultLabel;

    public static String StringType;
    public static String IntegerType;
    public static String FloatType;
    public static String AttachmentType;
    public static String BooleanType;
    public static String DataType;
    public static String JavaType;

    public static String autoGenerateIdsLabel;
    public static String linkEventDefaultName;
    public static String ImageWidgetDefaultLabel;
    public static String HiddenWidgetDefaultLabel;
    public static String FormButtonDefaultLabel;
    public static String TableDefaultLabel;
    public static String RichTextAreaFormFieldDefaultLabel;
    public static String IntermediateErrorCatchEventLabel;
    public static String IntermediateErrorCatchEventName;
    public static String switchPaletteAction;
    public static String paletteSimple;
    public static String paletteAdvanced;
    public static String SpreadsheetDefaultLabel;
    public static String signalEventDefaultName;
    public static String paletteTooltipOnlyIcone;
    public static String paletteTooltipFull;
    public static String paletteTooltipSimple;

    public static String Step_title;
    public static String Step_desc;
    public static String Human_title;
    public static String Human_desc;
    public static String Subprocess_title;
    public static String Subprocess_desc;
    public static String Gate_title;
    public static String Gate_desc;
    public static String XORGate_title;
    public static String XORGate_desc;
    public static String InclusiveGate_title;
    public static String InclusiveGate_desc;
    public static String Transition_title;
    public static String Transition_desc;
    public static String ThrowLink_title;
    public static String ThrowLink_desc;
    public static String CatchLink_title;
    public static String CatchLink_desc;
    public static String Pool_title;
    public static String Pool_desc;
    public static String Lane_title;
    public static String Lane_desc;
    public static String TextAnnotation_title;
    public static String TextAnnotation_desc;
    public static String Start_title;
    public static String Start_desc;
    public static String StartMessage_title;
    public static String StartMessage_desc;
    public static String StartTimer_title;
    public static String StartTimer_desc;
    public static String StartSignal_title;
    public static String StartSignal_desc;
    public static String End_title;
    public static String End_desc;
    public static String EndMessage_title;
    public static String EndMessage_desc;
    public static String EndSignal_title;
    public static String EndSignal_desc;
    public static String EndError_title;
    public static String EndError_desc;
    public static String Timer_title;
    public static String Timer_desc;
    public static String ThrowMessage_title;
    public static String ThrowMessage_desc;
    public static String CatchMessage_title;
    public static String CatchMessage_desc;
    public static String ThrowSignal_title;
    public static String ThrowSignal_desc;
    public static String CatchSignal_title;
    public static String CatchSignal_desc;
    public static String CatchError_title;
    public static String CatchError_desc;
    public static String Event_title;
    public static String Event_desc;
    public static String ServiceTask_title;
    public static String ServiceTask_desc;
    public static String ScriptTask_title;
    public static String ScriptTask_desc;
    public static String ReceiveTask_title;
    public static String ReceiveTask_desc;
    public static String SendTask_title;
    public static String SendTask_desc;

    public static String Checkbox_title;
    public static String Checkbox_desc;
    public static String CheckboxList_title;
    public static String CheckboxList_desc;
    public static String Date_title;
    public static String Date_desc;
    public static String Duration_title;
    public static String Duration_desc;
    public static String Password_title;
    public static String Password_desc;
    public static String List_title;
    public static String List_desc;
    public static String Radio_title;
    public static String Radio_desc;
    public static String Select_title;
    public static String Select_desc;
    public static String TextBox_title;
    public static String TextBox_desc;
    public static String TextArea_title;
    public static String TextArea_desc;
    public static String RichTextArea_title;
    public static String RichTextArea_desc;
    public static String Text_title;
    public static String Text_desc;
    public static String Message_title;
    public static String Message_desc;
    public static String Submit_title;
    public static String Submit_desc;
    public static String Previous_title;
    public static String Previous_desc;
    public static String Next_title;
    public static String Next_desc;
    public static String SimpleButton_title;
    public static String SimpleButton_desc;
    public static String File_title;
    public static String File_desc;
    public static String Image_title;
    public static String Image_desc;
    public static String Table_title;
    public static String Table_desc;
    public static String EditableGrid_title;
    public static String EditableGrid_desc;
    public static String Hidden_title;
    public static String Hidden_desc;
    public static String Group_desc;
    public static String Group_title;
    public static String IFrame_title;
    public static String IFrame_desc;
    public static String confirmDelete_title;
    public static String confirmDelete_message;
    public static String StartError_desc;
    public static String StartError_title;
    public static String SuggestBox_desc;
    public static String SuggestBox_title;
    public static String HTML_desc;
    public static String HTML_title;
    public static String SpacingHorizontalDesc;
    public static String SpacingVerticalTitle;
    public static String SpacingHorizontalTitle;
    public static String SpacingVerticalDesc;
    public static String zoomInLabel;
    public static String zoomOutLabel;
    public static String TerminateEnd_title;
    public static String TerminateEnd_desc;
    public static String name;
    public static String version;
    public static String SubprocessEventDefaultName;
    public static String SubprocessEvent_desc;
    public static String SubprocessEvent_title;
    public static String CallActivity_desc ;
    public static String CallActivity_title ;
    public static String edit;
    public static String openNameAndVersionDialogTitle;

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
    public static String StartTimerCondition_fixedDate;
    public static String StartTimerCondition_hint_monthlyDayOfTheWeek;
    public static String StartTimerCondition_customize;

    public static String timerConditionWizardTitle;

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

    public static String nameNeedToBeSpecified;
    public static String versionNeedToBeSpecified;
    public static String sameIDIssue;
    public static String defaultValue;
    public static String mandatoryNotSet;
    public static String emptyField;
    public static String fieldIsTooLong;
    public static String containsNonUTF8Characters;
    public static String containsInvalidCharacters;
    public static String diagram;
    public static String pools;
    public static String differentCaseSameNameError;
    public static String diagramAlreadyExists;
    public static String descriptionTitle;
	public static String noToAll;
	public static String yesToAll;
	public static String overwriteTitle;
	public static String overwriteMessage;
	public static String deleteConfirmationTitle;
	public static String deleteConfirmationMsg;

	public static String seeDetailsButtonLabel;
	public static String diagramNameOrVersionMustBeChanged;
	public static String bonitaName;
	public static String bosProductName;
	public static String bosSPProductName;
	public static String bonitaStudioModuleName;
	public static String bonitaPortalModuleName;
	public static String bonitaEngineModuleName;
	public static String bonitaBPMCommunity;
	public static String bonitaBPMTeamworkSubscription;
	public static String bonitaBPMEfficiencySubscription;
	public static String bonitaBPMPerformanceSubscription;
	public static String corporateName;
    public static String nameMustStartWithLowerCase;
	public static String reservedKeyword;
	public static String nameFieldIssue;
	public static String filterLabel;
	public static String InvalidCharacterError;
	public static String toolAlignHorizontal;
	public static String toolAlignHorizontalDesc;
	public static String toolAlignVertical;
	public static String toolAlignVerticalDesc;
	public static String fieldIsTooShort;
	public static String nonInterruptingTimerEvent_desc;
	public static String nonInterruptingTimerEvent_title;
	public static String currentScript;
	public static String refactoredScript;
	public static String refactorTitle;
	public static String reviewChangesMessageRefactoring;
	public static String reviewChangesMessageRemoving;
	public static String deleteFormFieldDialogTitle;
	public static String askConfirmationForDeleting;
	public static String removingWidgetReferences;
	public static String removeTitle;
	public static String delete;
	public static String nameCantHaveAWhitespace;
    public static String emptyListExpressionName;
}
