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

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;

/**
 * Step required to recreate proper project settings from the pom.xml file.
 */
public class DeleteProjectSettingsMigrationStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.deleteProjectSettingsMigrationTitle,
                Messages.deleteProjectSettingsMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.deleteProjectSettingsMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", DeleteProjectSettingsMigrationStep.class.getName()));
        var report = MigrationReport.emptyReport();
        try {
            var groovyPrefs = groovyPrefs(project);
            Files.deleteIfExists(groovyPrefs);
            var jdtPrefs = jdtPrefs(project);
            Files.deleteIfExists(jdtPrefs);
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed to delete project settings.", e));
        }
        BonitaStudioLog.info(String.format("%s completed.", DeleteProjectSettingsMigrationStep.class.getName()));
        return report;
    }

    private Path jdtPrefs(Path project) {
        return project.resolve(BonitaProject.APP_MODULE).resolve(".settings").resolve("org.eclipse.jdt.core.prefs");
    }

    private Path groovyPrefs(Path project) {
        return project.resolve(BonitaProject.APP_MODULE).resolve(".settings")
                .resolve("org.eclipse.jdt.groovy.core.prefs");
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return !ProductVersion.sameMinorVersion(sourceVersion) && ProductVersion.canBeMigrated(sourceVersion);
    }

    @Override
    public boolean appliesToProject(Path projectRoot) throws CoreException {
        return Files.exists(groovyPrefs(projectRoot)) || Files.exists(jdtPrefs(projectRoot));
    }
}
