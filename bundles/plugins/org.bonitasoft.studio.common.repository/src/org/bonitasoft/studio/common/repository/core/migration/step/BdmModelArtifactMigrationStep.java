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
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class BdmModelArtifactMigrationStep implements MavenModelMigration, MigrationStep {
    
    private boolean parametrizedGAV;

    /**
     * 
     * @param parametrizedGAV Whether the groupId and version of the BDM mode 
     * artifacts should be parametrized in the model with ${project.groupId} 
     * and ${project.version} or not.
     */
    public BdmModelArtifactMigrationStep(boolean parametrizedGAV) {
        this.parametrizedGAV = parametrizedGAV;
    }
    
    public BdmModelArtifactMigrationStep() {
        this(false);
    }

    @Override
    public MigrationReport migrate(Model model, ProjectMetadata metadata) {
        MigrationReport report = new MigrationReport();
        
        // Remove old bdm artifacts from dependencies
        var removed = false;
        removed = removeDependency(model, matchingBdmClient(), report);
        removed = removeDependency(model, matchingBdmDaoClient(), report) || removed;

        if (removed) {
            // Add new bdm model dependency
            var bdmModelDependency = bdmModelDependency(metadata);
            model.getDependencies().add(bdmModelDependency);
            report.added(String.format("`%s` dependency has been added.", new GAV(bdmModelDependency)));
        }
        return report;
    }

    private boolean removeDependency(Model model, Predicate<Dependency> dependencyPredicate, MigrationReport report) {
        var dependency = model.getDependencies().stream()
                .filter(dependencyPredicate)
                .findFirst()
                .orElse(null);
        if (dependency != null) {
            var removed = model.getDependencies().remove(dependency);
            if (removed) {
                report.removed(String.format("`%s` has been removed.",  new GAV(dependency)));
                return true;
            }
        }
        return false;
    }

    private Dependency bdmModelDependency(ProjectMetadata metadata) {
        return newDependency(parametrizedGAV ? "${project.groupId}" : metadata.getGroupId(), String.format("%s-bdm-model", metadata.getProjectId()),
                parametrizedGAV ? "${project.version}" : metadata.getVersion(), Artifact.SCOPE_PROVIDED);
    }

    private Predicate<Dependency> matchingBdmClient() {
        return dep -> Objects.equals(dep.getArtifactId(), "bdm-client")
                && Objects.equals(dep.getVersion(), "1.0.0");
    }

    private Predicate<Dependency> matchingBdmDaoClient() {
        return dep -> Objects.equals(dep.getArtifactId(), "bdm-dao")
                && Objects.equals(dep.getVersion(), "1.0.0");
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
        return model.getDependencies().stream().anyMatch(matchingBdmClient())
                || model.getDependencies().stream().anyMatch(matchingBdmDaoClient());
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        var pomFile = project.resolve(POM_FILE_NAME).toFile();
        var metadata = ProjectMetadata.read(pomFile);
        var model = MavenProjectHelper.readModel(pomFile);
        if (appliesTo(model, metadata)) {
            var report = migrate(model, metadata);
            MavenProjectHelper.saveModel(pomFile.toPath(), model);
            return report;
        }

        return MigrationReport.emptyReport();
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.16.0")) < 0;
    }
}
