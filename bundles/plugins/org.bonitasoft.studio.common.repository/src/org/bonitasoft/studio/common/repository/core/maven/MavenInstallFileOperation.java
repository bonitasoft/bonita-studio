/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.apache.maven.Maven;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;

public class MavenInstallFileOperation {

    private final IMaven mavenEngine;
    private ArtifactRepository internalRepository;
    private ArtifactRepository localRepository;

    public MavenInstallFileOperation(final IMaven mavenEngine) {
        this.mavenEngine = mavenEngine;
    }
    
    public MavenInstallFileOperation(final IMaven mavenEngine,
            ArtifactRepository internalRepository,
            ArtifactRepository localRepository ) {
        this.mavenEngine = mavenEngine;
        this.internalRepository = internalRepository;
        this.localRepository = localRepository;
    }

    public MavenExecutionResult execute(final Properties configuration,
            final IProgressMonitor monitor)
            throws CoreException {
        final IMavenExecutionContext context = mavenEngine.createExecutionContext();
        final MavenExecutionRequest request = context.getExecutionRequest();
        if(internalRepository != null) {
            request.setLocalRepository(internalRepository);
            request.setOffline(true);
        }
        request.setGoals(List.of(String.format("org.apache.maven.plugins:maven-install-plugin:%s:install-file", DefaultPluginVersions.MAVEN_INSTALL_PLUGIN_VERSION)));
        request.setUpdateSnapshots(internalRepository == null);
        request.setInteractiveMode(false);
        request.setCacheNotFound(true);
        return context.execute(new ICallable<MavenExecutionResult>() {

            @Override
            public MavenExecutionResult call(final IMavenExecutionContext context, final IProgressMonitor innerMonitor)
                    throws CoreException {
                final Properties systemProperties = request.getSystemProperties();
                for (final Object key : configuration.keySet()) {
                    systemProperties.setProperty((String) key, configuration.getProperty((String) key));
                }
                request.setSystemProperties(systemProperties);
                Maven maven = mavenEngine.lookup(Maven.class);
                return maven.execute(request);
            }
        }, monitor);

    }

    public void installFile(final String groupId, final String artifactId, final String version, final String type,
            final String classifier, final File file)
            throws CoreException {
        installFile(groupId, artifactId, version, type, classifier, file, null);
    }

    protected void logError(final String groupId, final String artifactId, final String type, final String version,
            final Throwable exception) {
        BonitaStudioLog.error(
                String.format("Failed to install artifact %s:%s:%s:%s in the local repository", groupId, artifactId, type,
                        version),
                exception, CommonRepositoryPlugin.PLUGIN_ID);
    }

    public void installFile(final String groupId,
            final String artifactId,
            final String version,
            final String type,
            final String classifier,
            final File file,
            final File pomFile) throws CoreException {
        final Properties properties = new Properties();
        if (file != null && file.exists()) {
            properties.setProperty("file", file.getAbsolutePath());
        }
        properties.setProperty("groupId", groupId);
        properties.setProperty("artifactId", artifactId);
        properties.setProperty("version", version);
        properties.setProperty("packaging", type);
        if (classifier != null) {
            properties.setProperty("classifier", classifier);
        }
        if (!Objects.equals("sources", classifier)) {
            if (pomFile == null || !pomFile.exists()) {
                properties.setProperty("generatePom", "true");
            } else {
                properties.setProperty("pomFile", pomFile.getAbsolutePath());
            }
        }
        if(localRepository != null) {
            properties.setProperty("localRepositoryPath", localRepository.getBasedir());
        }
        final MavenExecutionResult executionResult = execute(properties,
                AbstractRepository.NULL_PROGRESS_MONITOR);
        if (executionResult.hasExceptions()) {
            final Throwable exception = executionResult.getExceptions().get(0);
            logError(groupId, artifactId, type, version, exception);
            throw new CoreException(
                    new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, exception.getMessage(), exception));
        }
    }

}
