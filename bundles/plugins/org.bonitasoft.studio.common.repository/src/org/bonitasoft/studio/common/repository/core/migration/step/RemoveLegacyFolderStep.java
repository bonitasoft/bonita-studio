/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration.step;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class RemoveLegacyFolderStep implements MigrationStep {

    private static final Set<FolderBackupHandler> HANDLERS = Set.of(
            FolderBackupHandler.of(Paths.get("application_resources")),
            FolderBackupHandler.of(Paths.get("forms")),
            FolderBackupHandler.of(Paths.get("looknfeels")),
            FolderBackupHandler.of(Paths.get("validators")),
            FolderBackupHandler.of(Paths.get("src-validators")),
            FolderBackupHandler.of(Paths.get("simulation")),
            FolderBackupHandler.of(Paths.get("customTypes")),
            FolderBackupHandler.of(Paths.get("src-customTypes")),
            FolderBackupHandler.of(Paths.get("src-connectors")),
            FolderBackupHandler.of(Paths.get("connectors-def")),
            FolderBackupHandler.of(Paths.get("connectors-impl")),
            FolderBackupHandler.of(Paths.get("src-filters"), LegacyConnectorBackupHandler.of()),
            FolderBackupHandler.of(Paths.get("filters-impl"), LegacyConnectorBackupHandler.of()),
            FolderBackupHandler.of(Paths.get("filters-def"), LegacyConnectorBackupHandler.of()),
            FolderBackupHandler.of(Paths.get("app/src-connectors"), LegacyConnectorBackupHandler.of()),
            FolderBackupHandler.of(Paths.get("app/connectors-def"), LegacyConnectorBackupHandler.of()),
            FolderBackupHandler.of(Paths.get("app/connectors-impl"), LegacyConnectorBackupHandler.of()),
            FolderBackupHandler.of(Paths.get("app/src-filters"), LegacyConnectorBackupHandler.of()),
            FolderBackupHandler.of(Paths.get("app/filters-impl"), LegacyConnectorBackupHandler.of()),
            FolderBackupHandler.of(Paths.get("app/filters-def"), LegacyConnectorBackupHandler.of()));

    private static Set<String> legacyRepositoryNames = HANDLERS.stream()
            .map(FolderBackupHandler::getFolder)
            .map(folder -> folder.getFileName().toString())
            .collect(Collectors.toSet());

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.removeLegacyFolderMigrationTitle,
                Messages.removeLegacyFolderMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.removeLegacyFolderMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", RemoveLegacyFolderStep.class.getName()));
        var result = MigrationReport.emptyReport();
        HANDLERS.stream().map(folderHandler -> folderHandler.backup(project)).forEach(report -> report.merge(result));
        BonitaStudioLog.info(String.format("%s completed.", RemoveLegacyFolderStep.class.getName()));
        return result;
    }

    public static Set<String> legacyRepositories() {
        return legacyRepositoryNames;
    }

    @Override
    public boolean appliesToProject(Path projectRoot) throws CoreException {
        return HANDLERS.stream().anyMatch(handler -> handler.isHandled(projectRoot));
    }

    static class FolderBackupHandler {

        private Path folder;
        private Function<Path, MigrationReport> backupHandler;

        static FolderBackupHandler of(Path folder) {
            return new FolderBackupHandler(folder, NoBackupHandler.of());
        }

        static FolderBackupHandler of(Path folder, Function<Path, MigrationReport> backupHandler) {
            return new FolderBackupHandler(folder, backupHandler);
        }

        FolderBackupHandler(Path folder, Function<Path, MigrationReport> backupHandler) {
            this.folder = folder;
            this.backupHandler = backupHandler;
        }

        Path getFolder() {
            return folder;
        }

        MigrationReport backup(Path projectRoot) {
            return backupHandler.apply(projectRoot.resolve(getFolder()));
        }

        public boolean isHandled(Path projectRoot) {
            return Files.exists(projectRoot.resolve(getFolder()));
        }

    }

    static class NoBackupHandler implements Function<Path, MigrationReport> {

        static NoBackupHandler of() {
            return new NoBackupHandler();
        }

        @Override
        public MigrationReport apply(Path folder) {
            if (Files.exists(folder)) {
                try {
                    FileUtil.deleteDir(folder);
                } catch (IOException e) {
                    BonitaStudioLog.error(
                            String.format("Failed to delete folder %s during migration", folder.getFileName()),
                            CommonRepositoryPlugin.PLUGIN_ID);
                }
                BonitaStudioLog.info(String.format("Folder %s has been removed during migration", folder.getFileName()),
                        CommonRepositoryPlugin.PLUGIN_ID);
            }
            return MigrationReport.emptyReport();
        }
    }

    static class LegacyConnectorBackupHandler implements Function<Path, MigrationReport> {

        private static final String BACKUP_FOLDER = "backup";

        static LegacyConnectorBackupHandler of() {
            return new LegacyConnectorBackupHandler();
        }

        private static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation)
                throws IOException {
            try (var files = Files.walk(Paths.get(sourceDirectoryLocation))) {
                files.forEach(source -> {
                    Path destination = Paths.get(destinationDirectoryLocation,
                            source.toString().substring(sourceDirectoryLocation.length()));
                    try {
                        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
            }
        }

        @Override
        public MigrationReport apply(Path folder) {
            var report = MigrationReport.emptyReport();
            if (Files.exists(folder)) {
                var backupFolder = folder.getParent().resolve(BACKUP_FOLDER).resolve(folder.getFileName());
                try {
                    if (!Files.exists(backupFolder)) {
                        Files.createDirectories(backupFolder);
                    }
                    copyDirectory(folder.toFile().getAbsolutePath(), backupFolder.toFile().getAbsolutePath());
                    BonitaStudioLog.info(
                            String.format("Folder %s has been backup to %s during migration", folder.getFileName(),
                                    backupFolder.toString()),
                            CommonRepositoryPlugin.PLUGIN_ID);
                    report.removed(String.format(
                            "Legacy connector and actor filters implementations and definitions have been removed. All related files have been backup in %s folder. Only connectors and actor filters used in the project extensions are supported. Consult the connector and actor filter maven archetype documentation to migrate to the supported format.",
                            backupFolder.getParent()));
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
                try {
                    FileUtil.deleteDir(folder);
                } catch (IOException e) {
                    BonitaStudioLog.error(
                            String.format("Failed to delete folder %s during migration", folder.getFileName()),
                            CommonRepositoryPlugin.PLUGIN_ID);
                }
                BonitaStudioLog.info(String.format("Folder %s has been removed during migration", folder.getFileName()),
                        CommonRepositoryPlugin.PLUGIN_ID);
            }
            return report;
        }
    }

}
