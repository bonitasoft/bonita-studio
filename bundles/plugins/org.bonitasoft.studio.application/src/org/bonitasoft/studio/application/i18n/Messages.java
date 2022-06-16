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
    public static String cleanBDMDatabase;
    public static String deployOptions;
    public static String DeployButtonLabel;
    public static String environment;
    public static String processes;
    public static String selectLatestVersion;
    public static String cannotResolveProcess;
    public static String cannotEnableProcess;
    public static String appDescriptorUnknownAppPage;
    public static String appDescriptorUnknownPageToken;
    public static String appDescriptorUnknownLayoutPage;
    public static String appDescriptorUnknownTheme;
    public static String deploySuccessMsg;
    public static String validateProcess;
    public static String youCanOpenApp;
    public static String artifactCounter;
    public static String pagesAndLayouts;
    public static String saveOpenedEditorsTitle;
    public static String saveOpenedEditors;
    public static String savingEditors;
    public static String deploySuccessButNoAppToOpenMsg;
    public static String deploying;
    public static String deployStatusWithUnresolvedProcessesMessage;
    public static String validateHint;
    public static String deployAborted;
    public static String abort;
    public static String applicationAsDisplayName;
    public static String allArtifactSelected;
    public static String noArtifactSelected;
    public static String environmentTootltip;
    public static String incompatibleModelFoundTitle;
    public static String incompatibleModelFoundMsg;
    public static String deployArtifacts;
    public static String extendProjectTitle;
    public static String extendProjectDescription;
    public static String addToProject;
    public static String removeFromProject;
    public static String find;
    public static String type;
    public static String all;
    public static String extensionSelectedForAdd;
    public static String addDependenciesError;
    public static String noResultFound;
    public static String version;
    public static String install;
    public static String fetchingExtensions;
    public static String installingExtensions;
    public static String filteringExtensions;
    public static String incompatibleExtension;
    public static String incompatibleExtensionTitle;
    public static String dependencyUpdatable;
    public static String newVersionAvailable;
    public static String projectOverview;
    public static String openMarketplace;
    public static String openMarketplaceTooltip;
    public static String projectOverviewTitle;
    public static String removeExtensionTooltip;
    public static String details;
    public static String removeExtensionConfirmationTitle;
    public static String removeExtensionConfirmation;
    public static String extensionLoadingErrorTitle;
    public static String extensionLoadingError;
    public static String importExtensionTitle;
    public static String importExtension;
    public static String importExtensionButtonLabel;
    public static String unknownExtensionsTitle;
    public static String deleteUnknownTooltip;
    public static String delete;
    public static String importRemoteDependencyTip;
    public static String dependencyNotFoundWhenImporting;
    public static String dependencyCoordinate;
    public static String manual;
    public static String fromFile;
    public static String browse;
    public static String file;
    public static String importFromFileTip;
    public static String resolvedDependency;
    public static String cannotResolveDependencyInstalledLocally;
    public static String editProjectMetadata;
    public static String editProjectMetadataDescription;
    public static String name;
    public static String description;
    public static String updatingProjectMetadata;
    public static String addDatabaseDriverTitle;
    public static String addDatabaseDriverDesc;
    public static String addDatabaseDriverQuestion;
    public static String databaseDriver;
    public static String engineRestartWarning;
    public static String enhanceProject;
    public static String upgradeBonitaExtensionTooltip;
    public static String upgradeBonitaExtension;
    public static String removeExtension;
    public static String updateExtensionConfirmationTitle;
    public static String updateExtensionConfirmation;
    public static String dependencyAlreadyExistsInSameVersion;
    public static String dependencyAlreadyExistsInDifferentVersion;
    public static String upgradeExtension;
    public static String upgradeExtensionTooltip;

    public static String newProjectWizardDescription;
    public static String newProjectWizardTitle;
    public static String create;
    public static String addExtensionPageTitle;
    public static String addExtensionPageDescription;
    public static String extensions;
    public static String modify;

    public static String repositories;
    public static String repositoriesDescription;
    public static String servers;
    public static String proxies;
    public static String mirrors;
    public static String id;
    public static String url;
    public static String releases;
    public static String snapshots;
    public static String enabled;
    public static String updatePolicy;
    public static String checksumPolicy;
    public static String add;
    public static String addRepositoryTooltip;
    public static String deleteRepositoryTooltip;
    public static String mavenProfile;
    public static String mavenProfileHint;
    public static String encryptionMasterPassword;
    public static String updateMasterPasswordWarning;
    public static String encryptionLink;
    public static String addServerTooltip;
    public static String deleteServerTooltip;
    public static String serversLink;
    public static String removeServerConfirmationTitle;
    public static String removeServerConfirmation;
    public static String removeRepositoryConfirmationTitle;
    public static String removeRepositoryConfirmation;
    public static String serverId;
    public static String serverIdHint;
    public static String userPasswordAuthentication;
    public static String sshAuthentication;
    public static String username;
    public static String password;
    public static String authentication;
    public static String showPassword;
    public static String encryptPassword;
    public static String encryptButtonTooltip;
    public static String privateKey;
    public static String passphrase;
    public static String showPassphrase;
    public static String clearPasswordTooltip;
    public static String clearPassword;
    public static String error;
    public static String proxiesLink;
    public static String addProxyTooltip;
    public static String deleteProxyTooltip;
    public static String removeProxyConfirmationTitle;
    public static String removeProxyConfirmation;
    public static String active;
    public static String protocol;
    public static String host;
    public static String port;
    public static String intValueExpected;
    public static String nonProxyHost;
    public static String nonProxyHostsTootltip;
    public static String mirrorsLink;
    public static String addMirrorTooltip;
    public static String deleteMirrorTooltip;
    public static String removeMirrorConfirmationTitle;
    public static String removeMirrorConfirmation;
    public static String mirrorOf;
    public static String mirrorUrlTooltip;
    public static String mirrorOfTooltip;
    public static String activate;
    public static String activateProxyTooltip;
    public static String restoreDefaultConfirmationTitle;
    public static String restoreDefaultConfirmation;
    public static String updatingConfiguration;
    public static String preparingDefinitionUpdate;
    public static String updatePreview;
    public static String definitionUpateMessage;
    public static String otherDependencyUpdateMessage;
    public static String updateActionsMessage;
    public static String abortingUpdate;
    public static String definitionUpdateWithBreakingChanges;
    public static String definitionRemovedDescription;
    public static String preparingProcessConfigurationUpdate;
    public static String jarFileRemovedChangeDescription;
    public static String jarFileUpdatedChangeDescription;
    public static String jarFileAddedChangeDescription;
    public static String configurationToUpdateFound;
    public static String updateProcessesTitle;
    public static String back;
    public static String connectors;
    public static String connectorsHint;
    public static String implementations;
    public static String preparingUpdate;
    public static String computingPreview;
    public static String applyingChanges;
    public static String actorFilters;
    public static String actorFiltersHint;
    public static String restApiExtensions;
    public static String restApiDetailsHint;
    public static String httpMethodMissing;
    public static String pathMissing;
    public static String permissionsMissing;
    public static String permissions;
    public static String path;
    public static String copyToClipboard;
    public static String editPermissionsMapping;
    public static String usages;
    public static String connectorUsages;
    public static String findUsages;
    public static String findingUsages;
    public static String diagramNotFound;
    public static String diagramNotFoundMessage;
    public static String process;
    public static String connectorUsagesDescription;
    public static String noConnectorUsagesFound;
    public static String search;
    public static String actorFilterUsagesDescription;
    public static String addConnector;
    public static String addActorFilter;
    public static String addTheme;
    public static String addRestApiExtension;
    public static String addOther;
    public static String legacyArchiveFormatHint;
    public static String extension;
    public static String extensionIsNotAConnector;
    public static String extensionIsNotAnActorFilter;
    public static String extensionIsNotARestApiExtension;
    public static String extensionIsNotAThemeExtension;
    public static String cannotAccessMarketplace;
    public static String cannotLoadMarketplaceMessage;
    public static String cannotUpdateMarketplaceMessage;
    public static String bonitaMarketplace;
    public static String localDependencyTooltip;
    public static String remoteDependencyTooltip;
    public static String marketplaceDependencyTooltip;
    public static String editMavenCoordinates;
    public static String editMavenCoordinatesTooltip;
    public static String mavenPropertiesMissing;
    public static String extensionView;
    public static String extensionViewTooltip;
    public static String elementView;
    public static String elementViewTooltip;
    public static String repositoryIdMandatory;
    public static String repositoryNameMandatory;
    public static String repositoryUrlMandatory;
    public static String invalidMavenConfigurationTitle;
    public static String invalidMavenConfiguration;
    public static String proxyIdMandatory;
    public static String proxyHostMandatory;
    public static String mirrorIdMandatory;
    public static String mirrorUrlMandatory;
    public static String mavenConfigurationUpdatedTitle;
    public static String mavenConfigurationUpdated;
    public static String projectDoesntContainsElement;
    public static String newElementTitle;
    public static String noDescription;
    public static String diagramZoomHint;
    public static String cannotReachMavenCentralRepositoryTitle;
    public static String cannotReachMavenCentralRepositoryMessage;
    public static String validateExistingMavenConfigurationMessage;
    public static String configure;
    public static String retry;
    public static String returnToElementsView;
    public static String returnToExtensionView;
    public static String addExtensionMenuLabel;
    public static String artifactIdTootltip;
    public static String groupIdTootltip;
    public static String projectElements;
    public static String bonitaExtensions;
    public static String otherExtensionsTooltip;
    public static String bonitaExtensionTooltip;
    public static String projectElementsTooltip;
    public static String showMore;
    public static String viewSource;
    public static String viewSourceTooltip;
    public static String processDiagram;
    public static String refresh;
    public static String refreshTooltip;
    public static String classifierTooltip;
    public static String depArtifactIdTootltip;
    public static String targetRuntimeVersion;
    public static String studioMaintenanceUpdateMessage;
    public static String updateTargetRuntimeVersionConfirmTitle;
    public static String updateTargetRuntimeVersionConfirmMsg;
    public static String updateTo;
    public static String displayInstalledExtensions;
    public static String alreadyInstalledExtensions;
    public static String problems;
    public static String problemInExtensionsMessage;
    public static String problemsFound;
    public static String problemsFoundMessage;
    public static String undo;
    public static String RecentlyModified;
    public static String engineStartWarning;

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
