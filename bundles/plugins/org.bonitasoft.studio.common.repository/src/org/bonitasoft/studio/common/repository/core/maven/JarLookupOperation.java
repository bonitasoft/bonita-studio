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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.maven.Maven;
import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.BuildSummary;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;

public class JarLookupOperation implements IRunnableWithProgress {

    private static final String LOOKUP_GOAL = "lookup";
    private static final String BONITA_PROJECT_PLUGIN_ARTIFACT_ID = "bonita-project-maven-plugin";
    private static final String BONITA_PROJECT_PLUGIN_GROUP_ID = "org.bonitasoft.maven";
    public static final String MAVEN_CENTRAL_REPOSITORY_URL = "https://repo.maven.apache.org/maven2/";

    private DependencyLookup result;
    private Set<String> repositories = new HashSet<>();
    private File fileToLookup;
    private IStatus status = Status.OK_STATUS;
    private String localRepositoryUrl;

    public JarLookupOperation(File fileToLookup) {
        this.fileToLookup = fileToLookup;
    }

    public JarLookupOperation addLocalRespository(String localRepositoryUrl) {
        this.localRepositoryUrl = localRepositoryUrl;
        return this;
    }

    public JarLookupOperation addRemoteRespository(String repositoryUrl) {
        this.repositories.add(repositoryUrl);
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(String.format(Messages.lookupDependencyFor, fileToLookup.getName()),
                IProgressMonitor.UNKNOWN);
        Properties properties = DependencyLookup.readPomProperties(fileToLookup)
                .orElse(null);
        if (properties != null) {
            result = tryGetDependency(properties);
        }
        if (result == null) { // Use sha1 lookup
            try {
                result = lookup();
            } catch (CoreException e) {
                status = e.getStatus();
            } catch (IOException e) {
                status = new Status(IStatus.ERROR, getClass(), "Dependency lookup fails for " + fileToLookup.getName(),
                        e);
            }
        }
    }

    private DependencyLookup tryGetDependency(Properties properties) throws InvocationTargetException {
        String groupId = properties.getProperty("groupId");
        String artifactId = properties.getProperty("artifactId");
        String version = properties.getProperty("version");
        Path tempLocalRepo = null;
        try {
            if (localRepositoryUrl == null) {
                tempLocalRepo = Files.createTempDirectory("m2Repo");
                localRepositoryUrl = tempLocalRepo.toFile().getAbsolutePath();
            }
            File localRepository = new File(localRepositoryUrl);
            for (String repository : repositories) {
                    MavenExecutionResult executionResult = runDependencyGetPlugin(groupId, artifactId, version,
                            null,
                            localRepository, repository);
                    if (executionResult.getBuildSummary(executionResult.getProject()) instanceof BuildSuccess) {
                        return new DependencyLookup(fileToLookup.getName(),
                                null,
                                DependencyLookup.Status.FOUND,
                                groupId,
                                artifactId,
                                version,
                                null,
                                repository);
                    }
            }
        } catch (CoreException e) {
            status = e.getStatus();
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        } finally {
            if (tempLocalRepo != null && tempLocalRepo.toFile().exists()) {
                FileUtil.deleteDir(tempLocalRepo.toFile());
            }
        }
        return null;
    }

    private DependencyLookup lookup() throws IOException, CoreException {
        Path tmpLookupOutputFolder = null;
        try {
            tmpLookupOutputFolder = Files.createTempDirectory("depLookup");
            MavenExecutionResult runLookupPlugin = runLookupPlugin(fileToLookup.toPath(), tmpLookupOutputFolder);
            BuildSummary buildSummary = runLookupPlugin.getBuildSummary(runLookupPlugin.getProject());
            if (buildSummary instanceof BuildSuccess) {
                String line = "";
                try (BufferedReader br = new BufferedReader(
                        new FileReader(tmpLookupOutputFolder.resolve("dependency-status.csv").toFile()))) {
                    line = br.readLine(); // Skip header line
                    while ((line = br.readLine()) != null) {
                        String[] csvData = line.split(",");
                        return DependencyLookup.fromCSV(csvData);
                    }
                }
            } else if (!runLookupPlugin.getExceptions().isEmpty()) {
                Throwable throwable = runLookupPlugin.getExceptions().get(0);
                throw new CoreException(new org.eclipse.core.runtime.Status(IStatus.ERROR,
                        getClass(),
                        "Dependency lookup fails for " + fileToLookup.getName(),
                        throwable));
            }
        } finally {
            if (tmpLookupOutputFolder != null) {
                FileUtil.deleteDir(tmpLookupOutputFolder.toFile());
            }
        }
        return null;
    }

    private MavenExecutionResult runLookupPlugin(Path filePath, Path outputDir)
            throws CoreException {
        final IMavenExecutionContext context = maven().createExecutionContext();
        final MavenExecutionRequest request = context.getExecutionRequest();
        request.setGoals(List.of(String.format("%s:%s:%s",
                BONITA_PROJECT_PLUGIN_GROUP_ID,
                BONITA_PROJECT_PLUGIN_ARTIFACT_ID,
                LOOKUP_GOAL)));
        Properties userProperties = new Properties();
        userProperties.setProperty("artifactLocation", filePath.toFile().getAbsolutePath());
        userProperties.setProperty("outputDir", outputDir.toFile().getAbsolutePath());
        if (!repositories.isEmpty()) {
            userProperties.setProperty("repositoryUrl", repositories.stream().collect(Collectors.joining(",")));
        }
        request.setUserProperties(userProperties);
        return context.execute(new ICallable<MavenExecutionResult>() {

            @Override
            public MavenExecutionResult call(final IMavenExecutionContext context, final IProgressMonitor innerMonitor)
                    throws CoreException {
                return maven().lookup(Maven.class).execute(request);
            }
        }, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    private MavenExecutionResult runDependencyGetPlugin(String groupId,
            String artifactId,
            String version,
            String classifier,
            File localRepo,
            String remoteRepo) throws CoreException {
        final IMavenExecutionContext context = maven().createExecutionContext();
        final MavenExecutionRequest request = context.getExecutionRequest();
        request.setLocalRepository(null);
        request.setLocalRepositoryPath(localRepo);
        request.setGoals(List.of("dependency:get"));
        Properties userProperties = new Properties();
        userProperties.setProperty("groupId", groupId);
        userProperties.setProperty("artifactId", artifactId);
        userProperties.setProperty("version", version);
        userProperties.setProperty("packaging", "jar");
        if (classifier != null) {
            userProperties.setProperty("classifier", classifier);
        }
        if (remoteRepo != null) {
            userProperties.setProperty("remoteRepositories", remoteRepo);
        }
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
