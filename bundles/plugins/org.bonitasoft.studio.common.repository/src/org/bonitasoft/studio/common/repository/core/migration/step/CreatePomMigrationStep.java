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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.RedirectURLBuilder;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.CreateBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.MavenProjectModelBuilder;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Version;

public class CreatePomMigrationStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.createPomMigrationTitle, Messages.createPomMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.createPomMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", CreatePomMigrationStep.class.getName()));
        var pomFile = project.resolve(POM_FILE_NAME);
        var report = new MigrationReport();
        ProjectMetadata metadata = null;
        if (Files.exists(pomFile)) {
            metadata = ProjectMetadata.read(pomFile.toFile());
            report.removed(
                    "Existing `pom.xml` has been backed up as `pom.xml.old`. Bonita projects are now Maven project and the `pom.xml` file is *reserved for internal use*.");
        } else {
            metadata = ProjectMetadata.defaultMetadata();
            var name = project.getFileName().toString();
            metadata.setName(name);
            metadata.setArtifactId(ProjectMetadata.toArtifactId(name));
        }
        BonitaStudioLog.info(String.format("Creating a pom.xml with coordinates %s", metadata));
        var model = createDefaultPomFile(project, metadata);
        report.updated("Groovy version has been updated from `2.4.x` to `3.0.x`");
        report.updated(
                "Only Java `11` version is now supported. This might impact your existing project if you were still using Java 8. Some dependencies in your project might be incompatible with the _Java Platform Module System_ introduced in Java 9.");
        report.added(String.format(
                "Bonita projects are now Maven projects and rely on the https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html[Maven dependency mechanism] to manage their dependencies. Check the documentation for more information about %s[Project composition].",
                RedirectURLBuilder.create("727")));
        addBdmDependency(project, model);
        BonitaStudioLog.info(String.format("%s completed.", CreatePomMigrationStep.class.getName()));
        return report;
    }

    private void addBdmDependency(Path project, Model model) throws CoreException {
        var artifactDescriptor = project.resolve("bdm").resolve(".artifact-descriptor.properties");
        if (Files.exists(artifactDescriptor)) {
            var p = new Properties();
            try (var is = Files.newInputStream(artifactDescriptor)) {
                p.load(is);
            } catch (IOException e) {
                throw new CoreException(Status.error("Failed to read .artifact-descriptor.properties", e));
            }
            var bdmDependency = new Dependency();
            bdmDependency.setGroupId(p.getProperty("groupId"));
            bdmDependency.setArtifactId("bdm-client");
            bdmDependency.setVersion("1.0.0");
            bdmDependency.setScope("provided");
            model.getDependencies().add(bdmDependency);
            MavenProjectHelper.saveModel(project.resolve(POM_FILE_NAME), model);
            BonitaStudioLog.info(String.format("BDM dependency %s has been added to pom.xml", bdmDependency));
        }
    }

    private static Model createDefaultPomFile(Path project,
            ProjectMetadata metadata) throws CoreException {
        var pomFile = project.resolve("pom.xml");
        if (Files.exists(pomFile)) {
            CreateBonitaProjectOperation.backupExistingPomFile(pomFile, metadata);
        }
        var builder = CreateBonitaProjectOperation.newProjectBuilder(metadata, new MavenProjectModelBuilder());
        return MavenProjectHelper.saveModel(project.resolve("pom.xml"), builder.toMavenModel());
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) < 0;
    }

}
