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
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Version;

public class BonitaProjectParentVersionStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.bonitaProjectParentVersionMigrationTitle, String.format(
                Messages.bonitaProjectParentVersionMigrationDescription, ProductVersion.BONITA_RUNTIME_VERSION));
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.bonitaProjectParentVersionMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", BonitaProjectParentVersionStep.class.getName()));
        var parentModel = loadParentMavenModel(project);
        if (parentModel.getParent() == null) {
            throw new CoreException(new Status(IStatus.ERROR,
                    BonitaProjectParentVersionStep.class,
                    "The Bonita project parent has not been found and cannot be updated."));
        }
        String bonitaRuntimeVersion = parentModel.getParent().getVersion();
        var currentBonitaMinorVersion = ProductVersion.minorVersion();
        if (!Objects.equals(minorVersion(bonitaRuntimeVersion), currentBonitaMinorVersion)) {
            var report = new MigrationReport();
            parentModel.getParent().setVersion(ProductVersion.BONITA_RUNTIME_VERSION);
            saveMavenModel(parentModel, project);
            report.updated(String.format("Bonita project parent has been updated from `%s` to `%s`.",
                    bonitaRuntimeVersion,
                    ProductVersion.BONITA_RUNTIME_VERSION));
            return report;
        }
        BonitaStudioLog.info(String.format("%s completed.", BonitaProjectParentVersionStep.class.getName()));
        return MigrationReport.emptyReport();
    }

    private static String minorVersion(String bonitaRuntimeVersion) {
        var version = new DefaultArtifactVersion(bonitaRuntimeVersion);
        return version.getMajorVersion() + "." + version.getMinorVersion();
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("9.0.0")) >= 0;
    }

}
