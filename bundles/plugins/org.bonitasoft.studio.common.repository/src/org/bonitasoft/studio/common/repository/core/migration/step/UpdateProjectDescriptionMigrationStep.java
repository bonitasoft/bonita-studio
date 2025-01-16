/**
 * Copyright (C) 2022 BonitaSoft S.A.
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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.ProjectDescriptionBuilder;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.internal.resources.ModelObjectWriter;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.internal.IMavenConstants;

public class UpdateProjectDescriptionMigrationStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.updateProjectDescriptionMigrationTitle,
                Messages.updateProjectDescriptionMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.updateProjectDescriptionMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", UpdateProjectDescriptionMigrationStep.class.getName()));
        var descriptor = project.resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
        if (!Files.exists(descriptor)) {
            throw new CoreException(Status.error("Project descriptor not found."));
        }
        var parentModel = loadMavenModel(project.resolve(BonitaProject.APP_MODULE));
        var description = BonitaProjectMigrator.readDescriptor(descriptor);
        var newParentDescription = new ProjectDescriptionBuilder()
                .withProjectName(parentModel.getArtifactId())
                .withComment(ProductVersion.CURRENT_VERSION)
                .havingNatures(List.of(IMavenConstants.NATURE_ID))
                .havingBuilders(List.of(IMavenConstants.BUILDER_ID))
                .build(description);
        writeDescriptor(descriptor, newParentDescription);

        var appDescriptor = project.resolve(BonitaProject.APP_MODULE)
                .resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
        if (!Files.exists(appDescriptor)) {
            throw new CoreException(Status.error("App project descriptor not found."));
        }
        var newAppDescription = new ProjectDescriptionBuilder()
                .withProjectName(parentModel.getArtifactId() + "-app")
                .havingNatures(BonitaProject.NATRUES)
                .havingBuilders(BonitaProject.BUILDERS)
                .build(description);
        writeDescriptor(appDescriptor, newAppDescription);
        BonitaStudioLog.info(String.format("%s completed.", UpdateProjectDescriptionMigrationStep.class.getName()));
        return MigrationReport.emptyReport();
    }

    private void writeDescriptor(Path descriptor, IProjectDescription description) throws CoreException {
        var objectWriter = new ModelObjectWriter();
        try (var out = Files.newOutputStream(descriptor)) {
            objectWriter.write(description, out, getLineSeparator(descriptor));
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed to update project descriptor"));
        }
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return ProductVersion.canBeMigrated(sourceVersion);
    }

    private static String getLineSeparator(Path file) {
        if (Files.exists(file)) {
            try (
                    // for performance reasons the buffer size should
                    // reflect the average length of the first Line:
                    InputStream input = new BufferedInputStream(Files.newInputStream(file), 128);) {
                int c = input.read();
                while (c != -1 && c != '\r' && c != '\n')
                    c = input.read();
                if (c == '\n')
                    return "\n"; //$NON-NLS-1$
                if (c == '\r') {
                    if (input.read() == '\n')
                        return "\r\n"; //$NON-NLS-1$
                    return "\r"; //$NON-NLS-1$
                }
            } catch (IOException e) {
                // ignore
            }
        }
        // if there is no preference set, fall back to OS default value
        return System.lineSeparator();
    }

}
