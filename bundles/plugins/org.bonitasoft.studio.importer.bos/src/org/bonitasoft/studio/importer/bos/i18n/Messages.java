/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bos.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 */
public class Messages extends NLS {

    static {
        NLS.initializeMessages("messages", Messages.class);
    }

    public static String processesWithErrorAfterImport;
    public static String openDiagramWithErrors;
    public static String skippedValidationMessage;
    public static String importButtonLabel;
    public static String errorWhileImporting_message;
    public static String errorWhileImporting_title;
    public static String importFileTitle;
    public static String importFileDescription;
    public static String selectLocation;
    public static String browseButton_label;
    public static String invalidFilePath;
    public static String importProcessTitle;
    public static String importDetails;
    public static String overwriteAll;
    public static String keepAll;
    public static String archiveColumn;
    public static String actionColumn;
    public static String conflictMessage;
    public static String noConflictMessage;
    public static String analyseBosArchive;
    public static String importBosArchive;
    public static String importBosArchiveTitle;
    public static String parsingArchive;
    public static String retrivingDataToImport;
    public static String importing;
    public static String bosArchiveName;
    public static String skipped;
    public static String alreadyPresent;
    public static String errorOccuredWhileParsingBosArchive;
    public static String importWorkspaceTitle;
    public static String importWorkspaceDescription;
    public static String selectABonitaStudioWorkspace;
    public static String cannotImportWorkspaceWithVersion;
    public static String scanningWorkspace;
    public static String cannotImportWorkspaceWithEdition;
    public static String noRepositoryFoundAtLocation;
    public static String validRepository;
    public static String validRepositoryOverwritten;
    public static String noValidRepositoryFoundAtLocation;
    public static String importingWorkspace;
    public static String repositoryImported;
    public static String exportingWorkspace;
    public static String workspaceTips;
    public static String importWorkspaceOverwriteBehavior;
    public static String moreInfo;
    public static String bdmImportedInfo;
    public static String legacyFormsNotImported;
    public static String formsRemovedFromStudio;
    public static String legacyFormsNotImportedFromWorkspace;
    public static String projectConnectorToVCS;
    public static String occurrences;
    public static String downloadingRemoteBosArchive;
    public static String cannotImportRemoteArchive;
    public static String conflictingArtifactTooltip;
    public static String identicalArtifactTooltip;
    public static String importedArtifactTooltip;
    public static String errorReadArchive;
    public static String failSwitchRepository;
    public static String importWithSwitchRepositorySuccessful;
    public static String importWithSwitchRepositoryError;
    public static String currentRepoinfo;
    public static String updateProjectDependencies;
    public static String dependenciesUsageAnalysis;
    public static String definitionUsageAnalysis;

}
