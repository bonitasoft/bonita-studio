/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 */
public class Messages extends NLS {

    private static String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    public static String openProcessWizardPage_title;
    public static String NewProcessButtonLabel;
    public static String OpenProcessButtonLabel;
    public static String SaveProcessButtonLabel;
    public static String PrintProcessButtonLabel;
    public static String CopyButtonLabel;
    public static String PasteButtonLabel;
    public static String HelpButtonLabel;
    public static String WelcomeButtonLabel;

    public static String unableTofindLogTitle;
    public static String unableTofindLogMessage;

    public static String badWorkspaceVersionTitle;
    public static String badWorkspaceVersionMessage;
    public static String PreferencesButtonLabel;
    public static String resfreshConnectors;
    public static String aboutText;
    public static String reduceCoolbarTooltip;
    public static String maximizeCoolbarTooltip;
    public static String initializingCurrentRepository;
    public static String shuttingDown;
    public static String jreNotFoundTitle;

    public static String jreNotFoundMessage;

    public static String invalidWorkspaceTitle;
    public static String invalidWorkspace;
    public static String failedToOpenLogTitle;
    public static String failedToOpenLogMessage;
    public static String doNotDisplayForOtherDiagrams;
    public static String noProcessAvailable;

    public static String offlineRepositoryTitle;
    public static String offlineRepositoryMessage;

    public static String incompatibleJavaVersionTitle;
    public static String incompatibleJavaVersionMessage;

    public static String exitWarningMessage;

    public static String startDialogTitle;
    public static String doNotShowMeAgain;
    public static String startDialogMsg;
    public static String startDialogDetails;
    public static String releaseNote;
    public static String letsStart;
    public static String _6xFormsDontWorkAnymore;

    public static String NewButtonTooltip;
    public static String NewButtonLabel;

    public static String organization;
    public static String businessDataModel;
    public static String bdmAccessControl;
    public static String profile;
    public static String applicationDescriptor;
    public static String applicationPage;
    public static String layout;
    public static String customWidget;
    public static String fragment;
    public static String restAPIExtension;
    public static String connectorDef;
    public static String connectorImpl;
    public static String actorFilterDef;
    public static String actorFilterImpl;
    public static String newGroovy;
    public static String theme;

    public static String importDragDropInfoTitle;
    public static String importDragDropInfo;

    public static String creatingArchive;
    public static String buildError;
    public static String deployErrorTitle;
    public static String buildErrorHelp;
    public static String validationErrorTitle;
    public static String validationError;
    public static String bdmGenerationError;
    public static String selectArtifactToDeployTitle;
    public static String selectArtifactToDeploy;
    public static String warningMissingDependency;
    public static String deploy;
    public static String selectAll;
    public static String selectNone;
    public static String searchArtifact;
    public static String expandAll;
    public static String collapseAll;
    public static String buildErrorTitle;
    public static String build;
    public static String deployStatus;
    public static String deployStatusMessage;

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
