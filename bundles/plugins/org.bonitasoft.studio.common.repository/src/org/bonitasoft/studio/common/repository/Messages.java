/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages";//$NON-NLS-1$

    public static String exportArtifactsWizard_title;
    public static String browse;
    public static String exportArtifactsWizard_desc;
    public static String exportArtifactsWizard_desc_toFile;
    public static String exportLabel;
    public static String exportRepositoryTitle;
    public static String exportRepositoryFileTitle;
    public static String importedRepository_title;
    public static String importRepositoryFailedTitle;
    public static String importRepositoryFailedMsg;
    public static String destinationPath;
    public static String selectDestinationTitle;
    public static String selectAtLeastOneArtifact;
    public static String invalidArchive;
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
    public static String exporting;
    public static String importBonita6xTitle;
    public static String importBonita6xError;

    public static String importedFileIsInvalidTitle;
    public static String importedFileIsInvalid;

    public static String importErrorTitle;
    public static String incompatibleProductVersion;
    public static String initializingJavaProject;
    public static String creatingStore;
    public static String initializingProjectClasspath;

    public static String writePermission;
    public static String exportFailed;

    public static String issueFoundIn;
    public static String issuesFoundIn;

    public static String unvalidBossArchive;

    public static String repositoryError;
    public static String repositoryVersionErrorMsg;

    public static String sharedWithSvn;
    public static String sharedWithGit;
    public static String localRepository;
    public static String migrate;
    public static String mustMigrationMsg;
    public static String migrationTitle;

    public static String errorTitle;

    public static String failedToRetrieveResourceToExport;

    public static String migratingRepository;

    public static String defaultRepositoryName;

    public static String createNewProject_emptyText;
    public static String createNewProject_invalidCharacter;
    public static String projectAlreadyExist;

    public static String rename;
    public static String renameProject;

    public static String unloadable;
    public static String uncategorized;

    public static String invalidFile;

    public static String migrationError;
    public static String migrationConfirmationMsg;
    public static String migrationConfirmationTitle;
    public static String continueLabel;
    public static String updateAllModels;
    public static String prepareExport;

    public static String convertingDependencies;
    public static String lookupDependencyFor;
    public static String analyzeProjectDependencies;
    public static String dependenciesUsageAnalysis;
    public static String definitionUsageAnalysis;

    public static String connector;
    public static String actorFilter;
    public static String theme;
    public static String page;
    public static String form;
    public static String restApi;
    public static String other;

    public static String dependenciesInstallationMsg;
    public static String dependenciesInstallation;

    public static String creatingNewProject;
    public static String invalidFormat;
    public static String loadingCurrentProject;
    public static String conflicting;
    public static String ourVersion;
    public static String theirVersion;

    private Messages() {
        // Do not instantiate
    }

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}
