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

    public static String skippedValidationMessage;
    public static String importButtonLabel;
    public static String errorWhileImporting_message;
    public static String importFileDescription;
    public static String selectLocation;
    public static String browseButton_label;
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
    public static String bdmImportedInfo;
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
    public static String extensionsPreviewTitle;
    public static String extensionsPreviewDescription;
    public static String groupId;
    public static String artifactId;
    public static String version;
    public static String resolvedFromRepository;
    public static String cannotBeResolvedAgainstProvidedRepository;
    public static String unresolvedLocalDependenciesMessage;
    public static String unusedDependenciesWarning;
    public static String errorOccuredWhileLookingUpDependencies;
    public static String cannotBeResolvedAgainstProvidedRepositoryInstalledLocally;
    public static String unresolvedDependenciesMessage;
    public static String remoteRepositories;
    public static String configuredRemoteRepositories;
    public static String description;
    public static String bosArchiveContentTitle;
    public static String bosArchiveContentDescription;
    public static String detailsPageButtonLabel;
    public static String extensionPageLabel;
    public static String validatingDiagram;
    public static String validatingDiagramWithProgess;
    public static String anotherVersionAlreadyExists;
    public static String dependencyUpdatedStatus;
    public static String dependencyAddedStatus;
    public static String conflictingDependenciesMessage;

}
