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

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class BdmModelArtifactMigrationStep implements MavenModelMigration, MigrationStep {

    private boolean parametrizedGAV;

    /**
     * @param parametrizedGAV Whether the groupId and version of the BDM mode
     *        artifacts should be parametrized in the model with ${project.groupId}
     *        and ${project.version} or not.
     */
    public BdmModelArtifactMigrationStep(boolean parametrizedGAV) {
        this.parametrizedGAV = parametrizedGAV;
    }

    public BdmModelArtifactMigrationStep() {
        this(false);
    }

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.bdmModelArtifactMigrationTitle,
                Messages.bdmModelArtifactMigrationDescription);
    }

    @Override
    public MigrationReport migrate(Model model, ProjectMetadata metadata) {
        MigrationReport report = new MigrationReport();

        // Remove old bdm artifacts from dependencies
        var versionResolver = versionResolver(model);
        var bdmClientDependency = removeDependency(model, matchingBdmClient(versionResolver));
        var bdmDaoDependency = removeDependency(model, matchingBdmDaoClient(versionResolver));
        String reportMessage = null;
        if (bdmClientDependency != null) {
            // Add new bdm model dependency
            var bdmModelDependency = bdmModelDependency(metadata);
            model.getDependencies().add(bdmClientDependency.getPosition(), bdmModelDependency);
            reportMessage = bdmDaoDependency != null
                    ? String.format("`%s` and `%s` dependencies have been replaced with `%s`.",
                            new GAV(bdmClientDependency.getDependency()),
                            new GAV(bdmDaoDependency.getDependency()),
                            new GAV(bdmModelDependency))
                    : String.format("`%s` dependency has been replaced with `%s`.",
                            new GAV(bdmClientDependency.getDependency()),
                            new GAV(bdmModelDependency));

        } else if (bdmDaoDependency != null) {
            var bdmModelDependency = bdmModelDependency(metadata);
            model.getDependencies().add(bdmDaoDependency.getPosition(), bdmModelDependency);
            reportMessage = String.format("`%s` dependency has been replaced with `%s`.",
                    new GAV(bdmDaoDependency.getDependency()),
                    new GAV(bdmModelDependency));
        }
        if (bdmDaoDependency != null) {
            reportMessage = addDaoImplNote(reportMessage);
        }
        if (reportMessage != null) {
            report.updated(reportMessage);
        }
        return report;
    }

    private VersionResolver versionResolver(Model model) {
        return dependency -> {
            String versionValue = dependency.getVersion();
            if (versionValue != null
                    && versionValue.startsWith("${")
                    && versionValue.endsWith("}")) {
                var property = versionValue.substring(2, versionValue.length() - 1);
                var versionFromProperty = model.getProperties().getProperty(property);
                if (versionFromProperty != null) {
                    return versionFromProperty;
                }
            }
            return versionValue;
        };
    }

    private String addDaoImplNote(String reportMessage) {
        return reportMessage + "  \n\n" +
                "[NOTE]\n"
                + "====\n"
                + "If you were referencing `DAOImpl` directly in your code you may need to update the following usage:  \n"
                + "[source,java]\n"
                + "----\n"
                + "// When a direct access to DAOImpl class is used\n"
                + "BusinessObjectDAOImpl dao = new BusinessObjectDAOImpl(context.getApiSession())\n\n"
                + "// Use the DAO interfaces and the DAO factory from the ApiClient instead\n"
                + "BusinessObjectDAO dao = context.getApiClient().getDAO(BusinessObjectDAO.class)\n"
                + "----\n"
                + "====\n";
    }

    private RemovedDependency removeDependency(Model model, Predicate<Dependency> dependencyPredicate) {
        var dependency = model.getDependencies().stream()
                .filter(dependencyPredicate)
                .findFirst()
                .orElse(null);
        if (dependency != null) {
            var position = model.getDependencies().indexOf(dependency);
            var removed = model.getDependencies().remove(dependency);
            if (removed) {
                return RemovedDependency.create(dependency, position);
            }
        }
        return null;
    }

    private Dependency bdmModelDependency(ProjectMetadata metadata) {
        return newDependency(parametrizedGAV ? "${project.groupId}" : metadata.getGroupId(),
                String.format("%s-bdm-model", metadata.getProjectId()),
                parametrizedGAV ? "${project.version}" : metadata.getVersion(), Artifact.SCOPE_PROVIDED);
    }

    private Predicate<Dependency> matchingBdmClient(VersionResolver versionResolver) {
        return dep -> Objects.equals(dep.getArtifactId(), "bdm-client")
                && Objects.equals(versionResolver.resolve(dep), "1.0.0");
    }

    private Predicate<Dependency> matchingBdmDaoClient(VersionResolver versionResolver) {
        return dep -> Objects.equals(dep.getArtifactId(), "bdm-dao")
                && Objects.equals(versionResolver.resolve(dep), "1.0.0");
    }

    private Dependency newDependency(String groupId, String artifactId, String version, String scope) {
        Dependency dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setScope(scope);
        return dependency;
    }

    @Override
    public boolean appliesTo(Model model, ProjectMetadata metadata) {
        var versionResolver = versionResolver(model);
        return model.getDependencies().stream()
                .anyMatch(matchingBdmClient(versionResolver).or(matchingBdmDaoClient(versionResolver)));
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.bdmModelArtifactMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", BdmModelArtifactMigrationStep.class.getName()));
        var pomFile = project.resolve(POM_FILE_NAME).toFile();
        var metadata = ProjectMetadata.read(pomFile);
        var model = MavenProjectHelper.readModel(pomFile);
        if (appliesTo(model, metadata)) {
            var report = migrate(model, metadata);
            MavenProjectHelper.saveModel(pomFile.toPath(), model);
            return report;
        }
        BonitaStudioLog.info(String.format("%s completed.", BdmModelArtifactMigrationStep.class.getName()));
        return MigrationReport.emptyReport();
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("8.0.0")) < 0;
    }

    static class RemovedDependency {

        private Dependency dependency;
        private int position;

        private RemovedDependency(Dependency dependency, int position) {
            this.dependency = dependency;
            this.position = position;
        }

        static RemovedDependency create(Dependency dependency, int position) {
            return new RemovedDependency(dependency, position);
        }

        public Dependency getDependency() {
            return dependency;
        }

        public int getPosition() {
            return position;
        }

    }

    @FunctionalInterface
    static interface VersionResolver {

        String resolve(Dependency dependency);

    }
}
