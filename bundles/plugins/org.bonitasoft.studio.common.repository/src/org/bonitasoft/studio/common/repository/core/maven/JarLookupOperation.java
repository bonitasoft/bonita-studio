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
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.maven.Maven;
import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.BuildSummary;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
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
    static final String MAVEN_CENTRAL_REPOSITORY_URL = "https://repo.maven.apache.org/maven2";

    private DependencyLookup result;
    private List<String> repositories = new ArrayList<>();
    private InputStreamSupplier fileToLookup;
    private IStatus status = Status.OK_STATUS;

    public JarLookupOperation(InputStreamSupplier fileToLookup) {
        this.fileToLookup = fileToLookup;
    }

    public JarLookupOperation addRemoteRespository(String repositoryUrl) {
        this.repositories.add(repositoryUrl);
        Collections.sort(repositories, (url1, url2) -> {
            if (url1.equals(MAVEN_CENTRAL_REPOSITORY_URL)) {
                return 1;
            }
            return -1;
        });
        return this;
    }

    public List<String> getRemoteRepositories() {
        return repositories;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(String.format(Messages.lookupDependencyFor, fileToLookup.getName()),
                IProgressMonitor.UNKNOWN);
        try {
            result = lookup();
        } catch (CoreException e) {
            status = e.getStatus();
        } catch (IOException e) {
            status = new Status(IStatus.ERROR, getClass(), "Dependency lookup fails for " + fileToLookup.getName(),
                    e);
        }
    }

    private DependencyLookup lookup() throws IOException, CoreException {
        Path tmpLookupOutputFolder = null;
        try {
            tmpLookupOutputFolder = Files.createTempDirectory("depLookup");
            MavenExecutionResult runLookupPlugin = runLookupPlugin(fileToLookup.toTempFile().toPath(),
                    tmpLookupOutputFolder);
            BuildSummary buildSummary = runLookupPlugin.getBuildSummary(runLookupPlugin.getProject());
            if (buildSummary instanceof BuildSuccess) {
                String line = "";
                try (BufferedReader br = new BufferedReader(
                        new FileReader(tmpLookupOutputFolder.resolve("dependency-status.csv").toFile()))) {
                    line = br.readLine(); // Skip header line
                    while ((line = br.readLine()) != null) {
                        String[] csvData = line.split(",");
                        var dl = DependencyLookup.fromCSV(csvData);
                        var pomProperties = DependencyLookup.readPomProperties(fileToLookup.toTempFile());
                        if (pomProperties.isPresent()) {
                            return checkGAVConsistency(pomProperties.get(), dl);
                        }
                        return dl;
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

    private DependencyLookup checkGAVConsistency(Properties properties, DependencyLookup dl) {
        var groupId = properties.get("groupId");
        var artifactId = properties.get("artifactId");
        var version = properties.get("version");
        if (!Objects.equals(dl.getGroupId(), groupId) 
                || !Objects.equals(dl.getArtifactId(), artifactId) 
                || !Objects.equals(dl.getVersion(), version)) {
            dl.setStatus(DependencyLookup.Status.NOT_FOUND);
        }
        return dl;
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
