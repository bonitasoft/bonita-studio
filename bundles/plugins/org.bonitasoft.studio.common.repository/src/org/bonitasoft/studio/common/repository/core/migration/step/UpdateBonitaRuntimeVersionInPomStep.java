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

import java.nio.file.Path;
import java.util.Objects;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Version;

public class UpdateBonitaRuntimeVersionInPomStep implements MigrationStep {

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        var metadata = ProjectMetadata.read(project.resolve(POM_FILE_NAME).toFile());
        String bonitaRuntimeVersion = metadata.getBonitaRuntimeVersion();
        if (Strings.isNullOrEmpty(bonitaRuntimeVersion)) {
            throw new CoreException(new Status(IStatus.ERROR,
                    UpdateBonitaRuntimeVersionInPomStep.class,
                    String.format("The %s property has not been found and cannot be updated.",
                            ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION)));
        }
        var currentBonitaMinorVersion = ProductVersion.minorVersion();
        if (!Objects.equals(minorVersion(bonitaRuntimeVersion), currentBonitaMinorVersion)) {
            var report = new MigrationReport();
            var model = loadParentMavenModel(project);
            var properties = model.getProperties();
            properties.setProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION, ProductVersion.BONITA_RUNTIME_VERSION);
            saveMavenModel(model, project);
            report.updated(String.format("`%s` has been updated from `%s` to `%s`.",
                    ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION, 
                    bonitaRuntimeVersion,
                    ProductVersion.BONITA_RUNTIME_VERSION));
            return report;
        }
        return MigrationReport.emptyReport();
    }

    private static String minorVersion(String bonitaRuntimeVersion) {
        var version = new DefaultArtifactVersion(bonitaRuntimeVersion);
        return version.getMajorVersion() + "." + version.getMinorVersion();
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) >= 0;
    }

}
