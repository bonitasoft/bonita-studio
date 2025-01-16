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

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.plugin.CreateExtensionsModulePlugin;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Version;

public class ExtensionsModuleMigrationStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.extensionsModuleMigrationTitle,
                Messages.extensionsModuleMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.extensionsModuleMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", ExtensionsModuleMigrationStep.class.getName()));
        var report = MigrationReport.emptyReport();
        report.updated(
                "Rest API Extensions and Themes projects have been moved in the project layout to benefit from the Maven multi module approach. It means that files location inside the project have changed.  "
                        + "It is a technical change and will not impact the design usage in Bonita Studio."
                        + "New maven modules and their respective `pom.xml` files are *reserved for internal Studio use*.");
        var extensions = project.resolve(BonitaProject.EXTENSIONS_MODULE);
        try {
            if (Files.isDirectory(extensions)) {
                FileUtil.deleteDir(extensions);
            }
            var appModel = loadMavenModel(project.resolve(BonitaProject.APP_MODULE));
            var plugin = new CreateExtensionsModulePlugin(project, appModel.getArtifactId());
            plugin.execute(new NullProgressMonitor());
            report.updated("Project's extensions are now build in their own maven module. "
                    + "While it does not impact the design usage, this internal change allow the usage of a standard Maven build lifecycle.  "
                    + "All extensions share the same `version` and `groupId` of the parent project. "
                    + "It is enforced by the format of the Bonita project and must not be changed.");

            var extensionsPomFile = extensions.resolve(POM_FILE_NAME);
            var extensionsParentModel = loadMavenModel(extensions);

            var app = project.resolve(BonitaProject.APP_MODULE);
            var restApiExtensionsFolder = app.resolve("restAPIExtensions").toFile();
            if (restApiExtensionsFolder.exists()) {
                Stream.of(restApiExtensionsFolder.listFiles())
                        .filter(File::isDirectory)
                        .filter(file -> new File(file, POM_FILE_NAME).exists())
                        .forEach(file -> moveProjects(file.toPath(), extensions, extensionsParentModel, appModel));
                deleteLegacyExtensionFolderIfEmpty(restApiExtensionsFolder, project);
            }
            var themesFolder = app.resolve("themes").toFile();
            if (themesFolder.exists()) {
                Stream.of(themesFolder.listFiles())
                        .filter(File::isDirectory)
                        .filter(file -> new File(file, POM_FILE_NAME).exists())
                        .forEach(file -> moveProjects(file.toPath(), extensions, extensionsParentModel, appModel));
                deleteLegacyExtensionFolderIfEmpty(themesFolder, project);
            }
            saveMavenModel(appModel, project.resolve(BonitaProject.APP_MODULE));
            MavenProjectHelper.saveModel(extensionsPomFile, extensionsParentModel);
        } catch (IOException | RuntimeException e) {
            throw new CoreException(Status.error("Failed to update project layout to multi-module.", e));
        }
        BonitaStudioLog.info(String.format("%s completed.", ExtensionsModuleMigrationStep.class.getName()));
        return report;
    }

    private void deleteLegacyExtensionFolderIfEmpty(File folder, Path project) throws CoreException, IOException {
        if (folder.list().length > 0) {
            Stream.of(folder.listFiles())
                    .filter(File::isDirectory)
                    .filter(file -> !new File(file, POM_FILE_NAME).exists())
                    .forEach(extensionWithoutPom -> BonitaStudioLog.error(
                            String.format("%s/%s/pom.xml not found ! Modules migration step cannot be performed.",
                                    folder.getName(),
                                    extensionWithoutPom.getName()),
                            ExtensionsModuleMigrationStep.class));
            throw new CoreException(Status.error(String.format(
                    "Failed to update project layout to multi-module: Unexpected files are still present in '%s' folder post update (%s).%nMake sure only folders containing pom.xml file are present in '%s' folder before migrating.",
                    folder.getName(),
                    Stream.of(folder.listFiles()).map(f -> project.relativize(f.toPath()).toString())
                            .collect(Collectors.joining(", ")),
                    folder.getName())));
        }
        Files.delete(folder.toPath());
    }

    private void moveProjects(Path sourceDirectory, Path extensions, Model parentModel, Model appModel) {
        try {
            Path project = extensions.resolve(sourceDirectory.getFileName());
            FileUtil.copyDirectory(sourceDirectory, project);
            parentModel.addModule(sourceDirectory.getFileName().toString());
            FileUtil.deleteDir(sourceDirectory);

            var extensionModel = loadMavenModel(project);
            var dependency = new Dependency();
            dependency.setGroupId("${project.groupId}");
            dependency.setArtifactId(extensionModel.getArtifactId());
            dependency.setVersion("${project.version}");
            dependency.setType("zip");
            appModel.getDependencies().add(dependency);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (CoreException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("9.0.0")) < 0;
    }
}
