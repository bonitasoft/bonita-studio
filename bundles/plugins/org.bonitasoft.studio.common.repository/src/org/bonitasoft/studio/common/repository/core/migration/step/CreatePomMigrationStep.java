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

import org.bonitasoft.studio.common.RedirectURLBuilder;
import org.bonitasoft.studio.common.repository.core.CreateBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.osgi.framework.Version;

public class CreatePomMigrationStep implements MigrationStep {

    @Override
    public MigrationReport run(IProject project, IProgressMonitor monitor) throws CoreException {
        var pomFile = project.getFile(IMavenConstants.POM_FILE_NAME);
        var report = new MigrationReport();
        if (pomFile.exists()) {
            var currentMetadata = ProjectMetadata.read(project);
            CreateBonitaProjectOperation.createDefaultPomFile(project,
                    CreateBonitaProjectOperation.newProjectBuilder(currentMetadata));
            report.removed(
                    "Existing `pom.xml` has been backed up as `pom.xml.old`. Bonita projects are now Maven project and the `pom.xml` file is *reserved for internal use*.");
        } else {
            var defaultMetadata = ProjectMetadata.defaultMetadata();
            defaultMetadata.setName(project.getName());
            defaultMetadata.setArtifactId(ProjectMetadata.toArtifactId(project.getName()));
            CreateBonitaProjectOperation.createDefaultPomFile(project,
                    CreateBonitaProjectOperation.newProjectBuilder(defaultMetadata));
        }
        report.updated("Groovy version has been updated from `2.4.x` to `3.0.x`");
        report.updated(
                "Only Java `11` version is now supported. This might impact your existing project if you were still using Java 8. Some dependencies in your project might be incompatible with the _Java Platform Module System_ introduced in Java 9.");
        report.added(String.format(
                "Bonita projects are now Maven projects and rely on the https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html[Maven dependency mechanism] to manage their dependencies. Check the documentation for more information about %s[Project composition].",
                RedirectURLBuilder.create("727")));
        return report;
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) < 0;
    }

}
