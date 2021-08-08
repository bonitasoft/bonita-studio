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

import java.util.Objects;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Version;

public class UpdateBonitaRuntimeVersionInPomStep implements MigrationStep {

    @Override
    public MigrationReport run(IProject project, IProgressMonitor monitor) throws CoreException {
        var model = loadMavenModel(project);
        var properties = model.getProperties();
        if (!properties.containsKey(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION)) {
            throw new CoreException(new Status(IStatus.ERROR,
                    UpdateBonitaRuntimeVersionInPomStep.class,
                    String.format("The %s property has not been found and cannot be updated.",
                            ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION)));
        }
        var oldVersion = properties.getProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION);
        var currentVersion = ProductVersion.BONITA_RUNTIME_VERSION;
        if (!Objects.equals(oldVersion, currentVersion)) {
            var report = new MigrationReport();
            properties.setProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION, ProductVersion.BONITA_RUNTIME_VERSION);
            saveMavenModel(model, project);
            report.updated(String.format("`%s` has been updated from `%s` to `%s`.",
                    ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION, 
                    oldVersion,
                    ProductVersion.BONITA_RUNTIME_VERSION));
            return report;
        }
        return MigrationReport.emptyReport();
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) >= 0;
    }

}
