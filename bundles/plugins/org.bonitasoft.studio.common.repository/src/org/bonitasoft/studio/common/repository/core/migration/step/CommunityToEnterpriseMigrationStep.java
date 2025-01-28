/**
 * Copyright (C) 2024 BonitaSoft S.A.
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
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.maven.model.BuildBase;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.PluginExecution;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.BonitaCommonDependency;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.google.common.base.Objects;

public class CommunityToEnterpriseMigrationStep implements MigrationStep {

    private static final String DOCKER_BASE_IMAGE_REPOSITORY_PROPERTY = "docker.baseImageRepository";
    private static final String DOCKER_PROFILE_ID = "docker";
    private static final String BUNDLE_PROFILE_ID = "bundle";
    private static final String ENTERPRISE_DOCKER_IMAGE_REPOSITORY = "bonitasoft.jfrog.io/docker/bonita-subscription";
    private static final String ADMIN_APP_GROUP_ID = "org.bonitasoft.web.application";
    private static final String ADMIN_APP_ARTIFACT_ID = "bonita-admin-application";
    private static final String ADMIN_APP_EE_GROUP_ID = "com.bonitasoft.web.application";
    private static final String ADMIN_APP_EE_ARTIFACT_ID = "bonita-admin-application-sp";

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.communityToEnterpriseMigrationTitle,
                Messages.communityToEnterpriseMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.communityToEnterpriseMigrationTitle);
        var model = loadMavenModel(project.resolve(BonitaProject.APP_MODULE));
        var report = migrate(model);
        MavenProjectHelper.saveModel(project.resolve(BonitaProject.APP_MODULE).resolve(POM_FILE_NAME), model);
        return report;
    }

    MigrationReport migrate(Model model) {
        MigrationReport report = new MigrationReport();
        BonitaCommonDependency.updgrade(model);
        model.getDependencies().stream()
                .filter(isAdminApp())
                .findFirst()
                .ifPresent(d -> {
                    d.setGroupId(ADMIN_APP_EE_GROUP_ID);
                    d.setArtifactId(ADMIN_APP_EE_ARTIFACT_ID);
                    report.updated("Bonita Admin Application has been upgraded to Enterprise edition.");
                });
        model.getProfiles().stream()
                .filter(p -> DOCKER_PROFILE_ID.equals(p.getId()))
                .findFirst()
                .ifPresent(
                        p -> p.getProperties().put(DOCKER_BASE_IMAGE_REPOSITORY_PROPERTY,
                                ENTERPRISE_DOCKER_IMAGE_REPOSITORY));
        model.getProfiles().stream()
                .filter(p -> BUNDLE_PROFILE_ID.equals(p.getId()))
                .findFirst()
                .flatMap(p -> findPluginExecution(p.getBuild(), DefaultPluginVersions.MAVEN_DEPENDENCY_PLUGIN,
                        "prepare-bundle"))
                .ifPresent(exec -> exec.setId("prepare-bundle-enterprise"));
        model.getProfiles().stream()
                .filter(p -> BUNDLE_PROFILE_ID.equals(p.getId()))
                .findFirst()
                .flatMap(p -> findPluginExecution(p.getBuild(), DefaultPluginVersions.MAVEN_ASSEMBLY_PLUGIN,
                        "bundle-archive"))
                .ifPresent(exec -> exec.setId("bundle-archive-enterprise"));

        return report;
    }

    private Predicate<Dependency> isAdminApp() {
        return d -> ADMIN_APP_GROUP_ID.equals(d.getGroupId())
                && ADMIN_APP_ARTIFACT_ID.equals(d.getArtifactId());
    }

    private Optional<PluginExecution> findPluginExecution(BuildBase build, String artifactId, String executionId) {
        return build.getPlugins().stream()
                .filter(p -> Objects.equal(artifactId, p.getArtifactId()))
                .flatMap(p -> p.getExecutions().stream())
                .filter(exec -> executionId.equals(exec.getId()))
                .findFirst();
    }

    @Override
    public boolean appliesToProject(Path projectRoot) throws CoreException {
        var model = loadMavenModel(projectRoot.resolve(BonitaProject.APP_MODULE));
        return BonitaCommonDependency.shouldUpgade(model);
    }

}
