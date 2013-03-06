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
package org.bonitasoft.studio.application.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 *
 */
public class Messages extends NLS {

    private static String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    public static String UserGuidance ;
    public static String UserProfile ;
    public static String openProcessWizardPage_title;
    public static String openProcessWizardPage_desc;
    public static String openFromRepository_radioLabel;
    public static String openFromFilesystem_radioLabel;
    public static String processLocation_label;
    public static String browseButton_label;
    public static String NewProcessButtonLabel;
    public static String OpenProcessButtonLabel;
    public static String newFilePrefix;
    public static String SaveProcessButtonLabel;
    public static String PrintProcessButtonLabel;
    public static String ImportProcessButtonLabel;
    public static String ExportProcessButtonLabel;
    public static String CopyButtonLabel;
    public static String PasteButtonLabel;
    public static String RunProcessButtonLabel;
    public static String UserExperienceButtonLabel;
    public static String DeployProcessButtonLabel;
    public static String ShowDetailsButtonLabel;
    public static String HelpButtonLabel;
    public static String WelcomeButtonLabel;
    public static String confirmSaveMessage;
    public static String confirmSaveTitle;
    public static String exportProcessTitle;
    public static String importProcessTitle;
    public static String overrideCurrentProcessTitle;
    public static String overrideCurrentProcessDefinition;
    public static String overrideFileTitle;
    public static String overrideFileDefinition;
    public static String couldNotExport_title;
    public static String exportResourcesTitle;
    public static String exportResourcesDescription;

    //Data Types
    public static String initiator;
    public static String name;
    public static String version;
    public static String specifyNewNameAndVersion;
    public static String endOfDeploySuccessful;
    public static String endOfOpenConsole;
    public static String endOfOpenConsoleFailed;
    public static String removeProcessLabel;
    public static String confirmProcessDeleteTitle;
    public static String confirmProcessDeleteMessage;
    public static String openExampleProcessWizardPage_desc;
    public static String openExampleProcessWizardPage_title;
    public static String initiatorDescription;
    public static String targetFolderLabel;
    public static String browseLabel;

    public static String exportWarWizardTitle;
    public static String exportWarWizardDescription;
    public static String exportWarWizardMessage;
    public static String wrongPathTitle;
    public static String wrongPathMessage;

    public static String deploymentFailedMessage ;
    public static String wrongCredentialsMessages ;
    public static String chooseWarLabel;

    public static String exportApp;

    public static String exportWarInProgress;

    public static String exportLabel;

    public static String defaultExportFolder;

    public static String exportProcessAsProcTitle;

    public static String couldNotExportAsBar_message;

    public static String cantDeploy;

    public static String formPreview;
    public static String generatePreview;
    public static String openBrowser;

    public static String errorDialogTitle;

    public static String deployingProcess;
    public static String undeploying;
    public static String creatingServer;
    public static String deployingUserXP;
    public static String startingServer;
    public static String generatingWarFor;
    public static String PreviewFormLabel;


    public static String noStartEventFound;

    public static String noProcessAvailable;
    public static String importProcessWithName;
    public static String errorMissingSubprocess;
    public static String ImportFailed;
    public static String targetProcessAlreadyExists;
    public static String cannotSave_title;
    public static String newProcessPrefix;
    public static String importProcessProgressDialog;
    public static String unexpectedError;
    public static String unableTofindLogTitle;
    public static String unableTofindLogMessage;
    public static String exportUserXP;
    public static String exportRuntime;
    public static String includeLibInWar;
    public static String exportProcessAsProcAnyway;
    public static String cleanTaskName;
    public static String exportDefaultApp;
    public static String confirmContributionInstall_message;
    public static String confirmContributionInstall_title;
    public static String error;
    public static String contributionSuccessfullyInstalled;
    public static String success;
    public static String notLoggedIn;
    public static String errorWhileDownloadingContrib;
    public static String exportAllUserXP;
    public static String exportUserXPGroup ;
    public static String endOfDeploySuccessfulTitle;
    public static String deploymentFailedTitle;
    public static String deploySucceedMessage;
    public static String cannotOverrideTitle;
    public static String cannotOverride;
    public static String deployLabel;
    public static String deployWarWizardTitle;
    public static String deployWarWizardDescription;
    public static String deployWarWizardMessage;
    public static String deployApplicationHelpLabel;
    public static String buildingDefaultDiagram;
    public static String newProcess;
    public static String initRepositoryJob;
    public static String warningDialogTitle;
    public static String missingIncludedEntriesMessage;
    public static String importFileTitle;
    public static String importFileDescription;
    public static String selectImportLabel;
    public static String importDescriptionLabel;
    public static String selectFileToImport;
    public static String importFromBonitaName;
    public static String importFromBonitaDescription;
    public static String runtimeLabel;
    public static String processesLabel;
    public static String lightModeLabel;
    public static String EmbeddedMode;
    public static String jeeMode;
    public static String libExportMode;
    public static String lightModeTooltip;
    public static String embeddedModeTooltip;
    public static String jeeModeTooltip;
    public static String Both;
    public static String WAR;
    public static String BAR;
    public static String exportFormat;
    public static String exportProcessesAsFiles;
    public static String PROCFiles;
    public static String BARFiles;
    public static String couldNotExportAsProc_desc;
    public static String errorWhileComputingSubProcesses;
    public static String cantDeployEmptyPool;
    public static String myProcessCategory;
    public static String userXP;
    public static String processLabel;
    public static String exportRuntimeTooltip;
    public static String exportAsBAR;
    public static String exportAsWAR;
    public static String yes;
    public static String no;
    public static String exporting;
    public static String exportErrorOccured;
    public static String errorMessageBAR;
    public static String errorMessageWAR;
    public static String errorMessageUserXP;
    public static String errorRuntime;
    public static String errorWhileExportingTitle;
    public static String exportingBar;
    public static String exportingWar;
    public static String exportingUserXP;
    public static String exportingRuntime;
    public static String configuringAutoLogin;
    public static String installingFromContribution;
    public static String downloading;
    public static String installingContribution;
    public static String refresh;
    public static String openContributionForMoreExamples;
    public static String EmbeddedAppInBar;
    public static String questionSameAppAndEngine;
    public static String questionManyAppOnServer ;
    public static String skip ;
    public static String chooseApplicationToExport;
    public static String exportProcessMessage;
    public static String badWorkspaceVersionTitle;
    public static String badWorkspaceVersionMessage;
    public static String restWar;
    public static String PreferencesButtonLabel;
    public static String DebugProcessButtonLabel;
    public static String selectConnectorTitle;
    public static String selectConnectorMessage;
    public static String exportAsProcMessage;
    public static String selectAll;
    public static String unSelectAll;
    public static String exportAsProcTitle;
    public static String exportAsProcValidation;
    public static String resfreshConnectors;
    public static String questionExportEngine;
    public static String advancedExportInfo;
    public static String aboutText;
    public static String ConfigurationButtonLabel;
    public static String reduceCoolbarTooltip;
    public static String maximizeCoolbarTooltip;
    public static String errorWhileImporting_message;
    public static String errorWhileImporting_title;
    public static String initializingCurrentRepository;
    public static String shuttingDown;
    public static String startBonitaEngine;

    public static String duplicatingDiagram;

	public static String jreNotFoundTitle;

	public static String jreNotFoundMessage;



    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
