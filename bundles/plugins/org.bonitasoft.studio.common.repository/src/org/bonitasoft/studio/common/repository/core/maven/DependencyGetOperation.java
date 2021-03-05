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
package org.bonitasoft.studio.common.repository.core.maven;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.maven.Maven;
import org.apache.maven.artifact.repository.ArtifactRepositoryPolicy;
import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;

public class DependencyGetOperation implements IRunnableWithProgress {

    static final String MAVEN_CENTRAL_URL = "https://repo.maven.apache.org/maven2";

    private DependencyLookup result;
    private List<String> repositories = new ArrayList<>();
    private IStatus status = Status.OK_STATUS;
    private GAV gav;

    public DependencyGetOperation(GAV gav) {
        this.gav = gav;
    }

    public DependencyGetOperation addRemoteRespository(String repositoryUrl) {
        this.repositories.add(repositoryUrl);
        Collections.sort(repositories, (url1, url2) -> {
            if (url1.equals(MAVEN_CENTRAL_URL)) {
                return 1;
            }
            return -1;
        });
        return this;
    }
    
    public GAV getGav() {
        return gav;
    }
    
    public List<String> getRemoteRepositories() {
        return repositories;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(String.format(Messages.lookupDependencyFor, gav),
                IProgressMonitor.UNKNOWN);
        result = runGetDependency();
    }

    private DependencyLookup runGetDependency() throws InvocationTargetException {
        try {
            runDependencyPurgePlugin();
            for (String repository : repositories) {
                MavenExecutionResult executionResult = runDependencyGetPlugin(repository);
                if (executionResult.getBuildSummary(executionResult.getProject()) instanceof BuildSuccess) {
                    return new DependencyLookup(null,
                            null,
                            DependencyLookup.Status.FOUND,
                            gav,
                            repository);
                }
            }
        } catch (CoreException e) {
            status = e.getStatus();
        }
        return null;
    }

    private MavenExecutionResult runDependencyPurgePlugin() throws CoreException {
        final IMavenExecutionContext context = maven().createExecutionContext();
        final MavenExecutionRequest request = context.getExecutionRequest();
        request.setGoals(List.of("org.apache.maven.plugins:maven-dependency-plugin:3.1.2:purge-local-repository"));
        Properties userProperties = new Properties();
        userProperties.setProperty("manualInclude",  String.format("%s:%s:%s", gav.getGroupId(), gav.getArtifactId(), gav.getVersion()));
        userProperties.setProperty("actTransitively", "false");
        request.setUserProperties(userProperties);
        return context.execute(new ICallable<MavenExecutionResult>() {

            @Override
            public MavenExecutionResult call(final IMavenExecutionContext context, final IProgressMonitor innerMonitor)
                    throws CoreException {
                return maven().lookup(Maven.class).execute(request);
            }
        }, AbstractRepository.NULL_PROGRESS_MONITOR);
    }
    
    private MavenExecutionResult runDependencyGetPlugin(String remoteRepo) throws CoreException {
        final IMavenExecutionContext context = maven().createExecutionContext();
        final MavenExecutionRequest request = context.getExecutionRequest();
        request.setUpdateSnapshots(true);
        request.setGoals(List.of("dependency:get"));
        request.getRemoteRepositories().forEach(r -> {
            // Force remote update
            r.getReleases().setUpdatePolicy(ArtifactRepositoryPolicy.UPDATE_POLICY_ALWAYS);
            r.getSnapshots().setUpdatePolicy(ArtifactRepositoryPolicy.UPDATE_POLICY_ALWAYS);
        });

        Properties userProperties = new Properties();
        userProperties.setProperty("groupId", gav.getGroupId());
        userProperties.setProperty("artifactId", gav.getArtifactId());
        userProperties.setProperty("version", gav.getVersion());
        userProperties.setProperty("packaging", "jar");
        if (gav.getClassifier() != null) {
            userProperties.setProperty("classifier", gav.getClassifier());
        }
        if (remoteRepo != null) {
            userProperties.setProperty("remoteRepositories", remoteRepo);
        }
        userProperties.setProperty("transitive", "false");
        request.setUserProperties(userProperties);
        return context.execute(new ICallable<MavenExecutionResult>() {

            @Override
            public MavenExecutionResult call(final IMavenExecutionContext context, final IProgressMonitor innerMonitor)
                    throws CoreException {
                return maven().lookup(Maven.class).execute(request);
            }
        }, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    public DependencyLookup getResult() {
        return result;
    }

    public IStatus getStatus() {
        return status;
    }

    IMaven maven() {
        return MavenPlugin.getMaven();
    }

}
