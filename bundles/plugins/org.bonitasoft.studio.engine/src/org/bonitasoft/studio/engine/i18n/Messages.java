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
package org.bonitasoft.studio.engine.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 */
public class Messages extends NLS {

    public static String initializingProcessEngine;

    public static String linkGoToIsNull;

    public static String undeploying;

    public static String deploymentFailedMessage;

    public static String deployingProcess;

    public static String exportErrorOccured;

    public static String exportProcessMessage;

    public static String exportProcessTitle;

    public static String cantDeployEmptyPool;

    public static String confirmSaveTitle;

    public static String confirmSaveMessage;

    public static String RunButtonLabel;

    public static String OpenUserXPButtonLabel;

    public static String defaultConfiguration;

    public static String engineConfigurations;

    public static String exporting;

    public static String exportSuccessTitle;

    public static String exportSuccessMsg;

    public static String initializingUserXP;

    public static String waitingForEngineToStart;

    public static String enablingProcess;

    public static String selectProcessToExport;

    public static String buildTitle;

    public static String buildDesc;

    public static String destinationPath;

    public static String selectDestinationTitle;

    public static String browse;

    public static String selectAtLeastOneProcess;

    public static String searchProcess;

    public static String validationFailedTitle;

    public static String stoppingWebServer;

    public static String startingWebServer;

    public static String BonitaPreferenceDialog_UserXP_Settings;

    public static String consolePreferencePortLabel;

    public static String userNameLLabel;

    public static String userPasswordLabel;

    public static String consolePreferenceHostLabel;

    public static String loginAs;

    public static String defaultUserXPThemeLabel;

    public static String applyingLooknFeel;

    public static String portAlreadyUseTitle;

    public static String portAlreadyUseMsg;

    public static String updatingServerPort;

    public static String exportErrorOccuredMsg;

    public static String errorValidationContinueAnywayMessage;

    public static String errorValidationInDiagramToExport;

    public static String loginFailed;

    public static String errorActorMappingGroup;

    public static String running;

    public static String restartingWebServer;

    public static String resetingEngine;

    public static String undeploymentFailedMessage;

    public static String cannotStartTomcatTitle;

    public static String cannotStartTomcatMessage;

    public static String noInitiatorDefinedTitle;

    public static String noInitiatorDefinedMessage;

    public static String dontaskagain;

    public static String configure;

    public static String processEnableFailedTitle;

    public static String processEnableFailedMessage;

    public static String disablingProcessDefinition;

    public static String deletingProcessInstances;

    public static String deletingProcessDefinition;
    public static String updatePortWarningMessage;
    public static String updatePortWarningTitle;

    public static String buildingBar;
    public static String contractButNoFormTitle;
    public static String contractButNoFormMessage;

    public static String resetingEngineFailed;
    public static String resetEngine;
    public static String resetEngineSuccess;

    public static String engineLazyLoad;

    public static String diagramDoesntExist;
    public static String deploymentFailed;
    public static String deployDoneTitle;
    public static String deployDoneMessage;
    public static String deployFailedMessage;
    public static String deploySuccessMessage;
    public static String deployingPage;
    public static String deployFailedTitle;
    public static String tomcatXmxOption;
    public static String restartServer;
    public static String restartServerConfirmationMsg;
    public static String tomcatExtraParams;
    public static String noProcessToRunTitle;
    public static String noProcessToRun;
    public static String buildDoneTitle;
    public static String buildDoneMessage;
    public static String buildFailedTitle;
    public static String startingEngineServer;


    static {
        NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
    }
}
