/**
 * Copyright (C) 2016 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.rest.api.extension.core.validation;

import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_BONITA_DEPENDENCY_STATUS_CODE;

import java.util.List;
import java.util.Objects;

import org.apache.maven.artifact.versioning.ComparableVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.project.DependencyResolutionResult;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingRequest;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;
import org.eclipse.m2e.core.project.MavenProjectInfo;

public class RestAPIExtensionPomValidator {

    private static final String COM_BONITASOFT_WEB_GROUP_ID = "com.bonitasoft.web";
    public static final String BONITA_WEB_EXTENSIONS_SP_ARTIFACT_ID = "bonita-web-extensions-sp";

    RestAPIDependencyVersionToUpdateFinder dependencyVersionToUpdateFinder = new RestAPIDependencyVersionToUpdateFinder();

    public List<IStatus> validate(final RestAPIExtensionFileStore restApiFileStore) throws CoreException {
        List<IStatus> result = newArrayList();
        MavenExecutionResult mavenResult = build(restApiFileStore);
        DependencyResolutionResult dependencyResolutionResult = mavenResult.getDependencyResolutionResult();
        validateUnresolvedDependencies(restApiFileStore, result, dependencyResolutionResult);
        validateDependenciesToUpdate(result, mavenResult.getProject());
        validateResolvedDependencies(result, dependencyResolutionResult);

        return result;
    }

    private void validateDependenciesToUpdate(List<IStatus> statuses,
            MavenProject mavenProject) {
        dependencyVersionToUpdateFinder.findDependenciesToUpdate(mavenProject).stream()
                .map(dependencyToUpdate -> {
                    String message = String.format("%s %s %s", Messages.restApiExtension,
                            dependencyToUpdate.getMessage(),
                            Messages.inPomFile);
                    return createStatus(dependencyToUpdate.getSeverity(), dependencyToUpdate.getCode(), message);
                })
                .forEach(statuses::add);
    }

    private void validateResolvedDependencies(List<IStatus> result,
            DependencyResolutionResult dependencyResolutionResult) {
        for (Dependency dependency : dependencyResolutionResult.getResolvedDependencies()) {
            if (isBonitaWebExtensionsDependency(dependency) &&
                    versionNeedToBeUpdated(dependency)) {
                String message = String.format(
                        Messages.olderVersionToUpdateWarning,
                        dependency.getArtifact().getArtifactId(),
                        ProductVersion.CURRENT_VERSION);
                IStatus status = createStatus(IStatus.WARNING, REST_API_BONITA_DEPENDENCY_STATUS_CODE, message);
                result.add(status);
            }
        }
    }

    private boolean versionNeedToBeUpdated(Dependency dependency) {
        String minVersion = ProductVersion.CURRENT_VERSION;
        String artifactVersion = dependency.getArtifact().getVersion();
        ComparableVersion artifactVersionComparable = new ComparableVersion(artifactVersion);
        ComparableVersion minVersionComparable = new ComparableVersion(minVersion);
        if (artifactVersion.startsWith("[") || artifactVersion.startsWith("(")) {
            try {
                VersionRange versionSpec = VersionRange.createFromVersionSpec(artifactVersion);
                return !versionSpec.containsVersion(new DefaultArtifactVersion(minVersion));
            } catch (InvalidVersionSpecificationException e) {
                BonitaStudioLog.error(e);
            }
        }
        return artifactVersionComparable.compareTo(minVersionComparable) < 0;
    }

    protected boolean artifactVersionToOld(String artifactVersion, String minVersion) {
        ComparableVersion artifactVersionComparable = new ComparableVersion(artifactVersion);
        ComparableVersion minVersionComparable = new ComparableVersion(minVersion);
        if (artifactVersion.startsWith("[") || artifactVersion.startsWith("(")) {
            try {
                VersionRange versionSpec = VersionRange.createFromVersionSpec(artifactVersion);
                return !versionSpec.containsVersion(new DefaultArtifactVersion(minVersion));
            } catch (InvalidVersionSpecificationException e) {
                BonitaStudioLog.error(e);
            }
        }
        return artifactVersionComparable.compareTo(minVersionComparable) < 0;
    }

    private void validateUnresolvedDependencies(RestAPIExtensionFileStore restApiFileStore, List<IStatus> result,
            DependencyResolutionResult dependencyResolutionResult) {
        for (Dependency dependency : dependencyResolutionResult.getUnresolvedDependencies()) {
            result.add(ValidationStatus.error(
                    String.format(Messages.missingDependency, restApiFileStore.getName(), dependency)));
            if (isBonitaWebExtensionsDependency(dependency)) {
                String message = String.format(Messages.todoUpdateVersion, dependency.getArtifact().getArtifactId(),
                        ProductVersion.CURRENT_VERSION);
                IStatus status = createStatus(IStatus.INFO, REST_API_BONITA_DEPENDENCY_STATUS_CODE, message);
                result.add(status);
            }
        }
    }

    private IStatus createStatus(int severity, int code, String message) {
        return new Status(severity, RestAPIExtensionActivator.PLUGIN_ID,
                code, message, null);
    }

    protected MavenExecutionResult build(RestAPIExtensionFileStore restApiFileStore) throws CoreException {
        IMaven maven = MavenPlugin.getMaven();
        MavenProjectInfo info = restApiFileStore.getMavenProjectInfo();
        MavenProject project = MavenPlugin.getMavenProjectRegistry().getProject(restApiFileStore.getProject())
                .getMavenProject(AbstractRepository.NULL_PROGRESS_MONITOR);
        IMavenExecutionContext createExecutionContext = maven.createExecutionContext();
        MavenExecutionResult mavenResult = createExecutionContext.execute(new ICallable<MavenExecutionResult>() {

            @Override
            public MavenExecutionResult call(IMavenExecutionContext context, IProgressMonitor monitor)
                    throws CoreException {
                ProjectBuildingRequest newProjectBuildingRequest = context.newProjectBuildingRequest();
                newProjectBuildingRequest.setProject(project);
                newProjectBuildingRequest.setResolveDependencies(true);
                newProjectBuildingRequest.setProcessPlugins(true);
                return maven.readMavenProject(info.getPomFile(), newProjectBuildingRequest);
            }
        }, AbstractRepository.NULL_PROGRESS_MONITOR);
        return mavenResult;
    }

    private boolean isBonitaWebExtensionsDependency(Dependency dependency) {
        Artifact artifact = dependency.getArtifact();
        return artifactMatch(artifact, COM_BONITASOFT_WEB_GROUP_ID, BONITA_WEB_EXTENSIONS_SP_ARTIFACT_ID);
    }

    private boolean artifactMatch(Artifact artifact, String groupId, String artifactId) {
        return Objects.equals(artifact.getArtifactId(), artifactId) && Objects.equals(artifact.getGroupId(), groupId);
    }
}
