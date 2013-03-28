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
package org.bonitasoft.studio.engine.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 *
 */
public class Messages extends NLS {

    public static String errorMissingGroup;
    public static String errorMissingSubprocess;
    public static String errorMissingName;
    public static String errorMissingMultiInstanciation;
    public static String errorMissingTimerCondition;
    public static String errorMissingEvent;
    public static String errorMissingConditiononDeadline;
    public static String initializingProcessEngine;
    public static String errorIndividualNotSet;
    public static String errorTooManyIncomingInInclusiveGateway;
    public static String linkGoToIsNull;
    public static String errorMissingConditiononLoop;
    public static String errorMaximumConditionLoopNotValid ;
    public static String evaluationInProgress;
    public static String addLabel;
    public static String removeLabel;
    public static String provideaUserList;
    public static String userLabel;
    public static String backLabel;
    public static String resultTitleLabel;
    public static String exceptionFound;
    public static String dataBaseError_Msg;
    public static String dataBaseError_Title;
    public static String successMessage;
    public static String testConnectorPOJOWarning;
    public static String endOfDeploySuccessful;
    public static String undeploying;
    public static String deployingWar;
    public static String deployingUserXP;
    public static String resetEngine;
    public static String generatingWar;
    public static String deploymentFailedMessage;
    public static String deploySucceedMessage;
    public static String deploy;
    public static String overrideFileTitle;
    public static String deployingProcess;
    public static String exportAsProcTitle;
    public static String overrideFileDefinition;
    public static String exportAsProcValidation;
    public static String exportErrorOccured;
    public static String exportAsProcMessage;
    public static String exportProcessMessage;
    public static String exportProcessTitle;
    public static String couldNotExport_title;
    public static String couldNotExportAsProc_desc;
    public static String cantDeployEmptyPool;
    public static String confirmSaveTitle;
    public static String confirmSaveMessage;
    public static String incorrectUserNamePassword;
    public static String RunButtonLabel;
    public static String OpenUserXPButtonLabel;
    public static String defaultConfiguration;
    public static String engineConfigurations;
    public static String exporting;
    public static String exportSuccessTitle;
    public static String exportSuccessMsg;
    public static String initializingUserXP;
    public static String openingBonitaFormsInWebBrowser;
    public static String startBonitaEngine;
    public static String deployTestEnvironment;
    public static String waitingForEngineToStart;
    public static String enablingProcess;
    public static String run;
    public static String selectProcessToExport;
    public static String noProcessAvailable;
    public static String buildTitle;
    public static String buildDesc;
    public static String configuration;
    public static String destinationPath;
    public static String selectDestinationTitle;
    public static String browse;
    public static String process;
    public static String selectAtLeastOneProcess;
    public static String none;
    public static String searchProcess;
    public static String embbedConfigurationInBar;
    public static String embbedConfigurationInBarHint;
    public static String exportToBos;
    public static String exportToBOSTooltip;
    public static String validationFailedTitle;
    public static String validationFailedMessage;
    public static String crashRecoveryTitle;
    public static String crashRecoveryMsg;
    public static String stoppingWebServer;
    public static String startingWebServer;
    public static String resetServer;
    public static String BonitaPreferenceDialog_UserXP_Settings;
    public static String consolePreferencePortLabel;
    public static String userNameLLabel;
    public static String userPasswordLabel;
    public static String restartQuestion_title;
    public static String restartQuestion_msg;
    public static String consolePreferenceHostLabel;
    public static String loginAs;
    public static String defaultUserXPThemeLabel;
    public static String applyingLooknFeel;
    public static String portAlreadyUseTitle;
    public static String portAlreadyUseMsg;
    public static String updatingServerPort;
    public static String exportErrorOccuredMsg;
    public static String errorValidationMessage;
    public static String errorValidationContinueAnywayMessage;
    public static String errorValidationInDiagramToExport;
	public static String loginFailed;
	public static String errorActorMappingGroup;
	public static String running;
	public static String restartingWebServer;
	public static String resetingEngine;
    
    static {
        NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
    }
}
