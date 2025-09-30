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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.CreateBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.MavenAppModuleModelBuilder;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.osgi.framework.Version;

class UpdateProjectDescriptionMigrationStepTest {

    @TempDir
    private Path tmpDir;

    @BeforeEach
    void setup() throws Exception {
        Files.createDirectory(tmpDir.resolve("app"));
    }

    @Test
    void throwCoreExceptionWhenDescriptorNotFound() throws Exception {
        var step = new UpdateProjectDescriptionMigrationStep();

        assertThrows(CoreException.class, () -> step.run(tmpDir, new NullProgressMonitor()));
    }

    @Test
    void stepDoesNotApplyToCurrentVersion() throws Exception {
        var step = new UpdateProjectDescriptionMigrationStep();

        assertThat(step.appliesToVersion(ProductVersion.CURRENT_VERSION)).isFalse();
    }

    @Test
    void stepDoesNotApplyToNewerVersion() throws Exception {
        var step = new UpdateProjectDescriptionMigrationStep();
        var current = new Version(ProductVersion.CURRENT_VERSION);
        var nextVersion = new Version(current.getMajor(), current.getMinor(), current.getMicro() + 1);

        assertThat(step.appliesToVersion(nextVersion.toString())).isFalse();
    }

    @Test
    void stepAppliesToOtherVersion() throws Exception {
        var step = new UpdateProjectDescriptionMigrationStep();

        assertThat(step.appliesToVersion("7.12.0")).isTrue();
    }

    @Test
    void updateProjectDescriptorToCurrentVersion() throws Exception {
        var step = new UpdateProjectDescriptionMigrationStep();
        var descriptor = tmpDir.resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
        var appDescriptor = tmpDir.resolve("app").resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
        var testDescriptor = new File(FileLocator
                .toFileURL(UpdateProjectDescriptionMigrationStepTest.class.getResource("/testProjectDescriptor"))
                .getFile()).toPath();
        try (var is = Files.newInputStream(testDescriptor)) {
            Files.copy(is, descriptor);
        }
        try (var is = Files.newInputStream(testDescriptor)) {
            Files.copy(is, appDescriptor);
        }
        var defaultMetadata = ProjectMetadata.defaultMetadata();
        var builder = CreateBonitaProjectOperation.newProjectBuilder(defaultMetadata, new MavenAppModuleModelBuilder());
        MavenProjectHelper.saveModel(tmpDir.resolve("app").resolve("pom.xml"), builder.toMavenModel());

        step.run(tmpDir, new NullProgressMonitor());

        var desc = readDescriptor(descriptor);
        assertThat(desc.getName()).isEqualTo(defaultMetadata.getArtifactId());
        assertThat(desc.getComment()).isEqualTo(ProductVersion.CURRENT_VERSION);
        assertThat(desc.getNatureIds()).containsExactly(IMavenConstants.NATURE_ID);
        assertThat(desc.getBuildSpec()).extracting(ICommand::getBuilderName)
                .containsExactly(IMavenConstants.BUILDER_ID);

        var appDesc = readDescriptor(appDescriptor);
        assertThat(appDesc.getName()).isEqualTo(defaultMetadata.getArtifactId() + "-app");
        assertThat(appDesc.getComment()).isNullOrEmpty();
        assertThat(appDesc.getNatureIds()).containsExactlyInAnyOrderElementsOf(BonitaProject.NATRUES);
        assertThat(appDesc.getBuildSpec()).extracting(ICommand::getBuilderName)
                .containsExactlyInAnyOrderElementsOf(BonitaProject.BUILDERS);
    }

    private static IProjectDescription readDescriptor(Path descriptor) throws IOException, CoreException {
        try (var is = Files.newInputStream(descriptor)) {
            return ResourcesPlugin.getWorkspace().loadProjectDescription(is);
        }
    }

}
