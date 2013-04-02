/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages";//$NON-NLS-1$

    public static String overwriteTitle;
    public static String overwriteMessage;
    public static String overwriteFile_tile;
    public static String overwriteFile_message;
    public static String dateDataType;
    public static String couldNotOpenImportedConnectorTitle;
    public static String beginToAddJars;
    public static String addingJar;
    public static String removeJar;
    public static String close;
    public static String addJar;
    public static String beginToRemoveJars;
    public static String removingJar;
    public static String cannotUnmarshalOldProcessException;
    public static String importInformation;
    public static String importSucessNoGraphic;
    public static String inconsistentModel;
    public static String cannotSave;
    public static String noToAll;
    public static String yesToAll;

    public static String unsupportedResourcesTitle;
    public static String unsupportedResourcesMessage;
    public static String softTooOlfForModel_title;
    public static String softTooOlfForModel_message;
    public static String jarsAndArchives;
    public static String updatingClasspath;


    public static String artifact_configuration;
    public static String repository_configuration;
    public static String repository_myjars;
    public static String repository_providedjars;
    public static String repository_connectors;
    public static String repository_groovy;
    public static String repository_process;
    public static String repository_validators;

    public static String updateListTypeInScriptTitle;
    public static String updateListTypeInScriptMessage;
    public static String importingGroovyFiles;
    public static String processMigration;
    public static String multiInstantiationMigrationTitle;
    public static String multiInstantiationMigrationMessage;
    public static String clear;
    public static String mustBeChanged;
    public static String repository_myprocesses;
    public static String repository_myattachment;
    public static String repository_myexamples;
    public static String repository_templates;
    public static String createValidatorWizardPage_openEditor;
    public static String errorWhileImporting_title;
    public static String errorWhileImporting_message;
    public static String providedWebTemplateRepositoryName;
    public static String informationDeleteJarTitle;
    public static String informationDeleteJarMessage;


    public static String exportArtifactsWizard_title;
    public static String path;
    public static String browse;
    public static String exportArtifactsWizard_desc;
    public static String zipExtensionName;
    public static String export_defaultFileName;
    public static String exportArtifactsWizard_desc_toFile;
    public static String exportArtifactsWizard_selectAll;
    public static String exportArtifactsWizard_selectNone;
    public static String completingInstall;

    public static String initializingJarRepository;
    public static String initializingProvidedJarRepository;
    public static String initializingGroovyRepository;
    public static String initializingConnectorRepository;
    public static String initializingProcessRepository;
    public static String intializingUserWebTemplates;
    public static String intializingProvidedWebTemplates;
    public static String intializingAttachmentRepository;
    public static String initializingConnectorConfigurationsRepository;
    public static String openingStudio;
    public static String manageJarTitle;
    public static String artifactNotExported_title;
    public static String artifactExported_title;
    public static String artifactExported_msg;
    public static String exporting;
    public static String deleteConfirmationTitle;
    public static String deleteConfirmationMsg;
    public static String exportLabel;
    public static String exportRepositoryTitle;
    public static String exportRepositoryFileTitle;
    public static String importedRepository_title;
    public static String importRepositoryFailedTitle;
    public static String importingRepository;
    public static String importRepositoryFailedMsg ;

    public static String destinationPath;
    public static String selectDestinationTitle;
    public static String selectAtLeastOneArtifact;
    public static String invalidArchive;
    public static String exportDiagramWizardTitle;
    public static String invalidFileFormat;
    public static String browseRepository;
    public static String destinationPathMustBeADirectory;
    public static String exportFinishMessage;

    public static String current;

    public static String readOnlyFileTitle;
    public static String readOnlyFileWarning;

	public static String selectAll;
	public static String deselectAll;

	public static String migrationFailedTitle;
	public static String migrationFailedMessage;




    private Messages() {
        // Do not instantiate
    }



    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}