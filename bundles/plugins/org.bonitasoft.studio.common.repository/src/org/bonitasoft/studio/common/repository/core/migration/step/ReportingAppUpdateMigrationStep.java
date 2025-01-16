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
import java.util.function.Predicate;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class ReportingAppUpdateMigrationStep implements MigrationStep, MavenModelMigration {

    public static final String GROUP_ID = "com.bonitasoft.web.application";
    public static final String ARTIFACT_ID = "bonita-reporting-application";
    public static final String VERSION = "1.3.0";
    public static final String COMPATIBLE_VERSION = "2.0.0";

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.reportingAppUpdateMigrationTitle,
                Messages.reportingAppUpdateMigrationDescription);
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("10.2.0")) < 0;
    }

    @Override
    public MigrationReport migrate(Model model, ProjectMetadata metadata) {
        var report = new MigrationReport();
        if (appliesTo(model, metadata)) {
            model.getDependencies().stream().filter(matchingReportingApp())
                    .forEach(dep -> dep.setVersion(COMPATIBLE_VERSION));
            BonitaStudioLog.info(
                    "Bonita Reporting Application version updated to '2.0.0'. This is the minimal compatible version with Bonita 2024.3 and above.");
            report.updated(
                    "Bonita Reporting Application version updated to `2.0.0`. This is the minimal compatible version with Bonita 2024.3 and above.");
        }
        return report;
    }

    @Override
    public boolean appliesTo(Model model, ProjectMetadata metadata) {
        return model.getDependencies().stream().anyMatch(matchingReportingApp());
    }

    private Predicate<Dependency> matchingReportingApp() {
        return dep -> Objects.equals(dep.getGroupId(), GROUP_ID)
                && Objects.equals(dep.getArtifactId(), ARTIFACT_ID)
                && Objects.equals(dep.getVersion(), VERSION);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.reportingAppUpdateMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", ReportingAppUpdateMigrationStep.class.getName()));
        var pomFile = project.resolve("app").resolve(POM_FILE_NAME).toFile();
        var metadata = ProjectMetadata.read(pomFile);
        var model = loadMavenModel(project.resolve("app"));
        if (appliesTo(model, metadata)) {
            var report = migrate(model, metadata);
            saveMavenModel(model, project.resolve("app"));
            return report;
        }
        BonitaStudioLog.info(String.format("%s completed.", ReportingAppUpdateMigrationStep.class.getName()));
        return MigrationReport.emptyReport();
    }

}
