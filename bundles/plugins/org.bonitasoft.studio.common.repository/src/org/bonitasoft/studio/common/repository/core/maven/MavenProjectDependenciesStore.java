/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.Maven;
import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.plugin.analyze.report.model.ActorFilterImplementation;
import org.bonitasoft.plugin.analyze.report.model.ConnectorImplementation;
import org.bonitasoft.plugin.analyze.report.model.Definition;
import org.bonitasoft.plugin.analyze.report.model.DependencyReport;
import org.bonitasoft.plugin.analyze.report.model.Form;
import org.bonitasoft.plugin.analyze.report.model.Page;
import org.bonitasoft.plugin.analyze.report.model.RestAPIExtension;
import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.common.jface.databinding.StatusToMarkerSeverity;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.MavenProjectModelBuilder;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;
import org.eclipse.m2e.core.project.IMavenProjectFacade;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MavenProjectDependenciesStore implements ProjectDependenciesStore {

    private static final String DEAULT_REPORT_OUTPUT_FILE = "target/bonita-dependencies.json";
    public static final String PROJECT_DEPENDENCIES_ANALYZED_TOPIC = "bonita/project/analyzed";
    private static final String ANALYZE_PLUGIN_MARKER_TYPE =  CommonRepositoryPlugin.PLUGIN_ID + ".analyzePluginMarkerType";
    
    private IProject project;
    private DependencyReport dependencyReport;
    private ObjectMapper mapper = new ObjectMapper().findAndRegisterModules()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .setSerializationInclusion(Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private IEventBroker eventBroker;

    public MavenProjectDependenciesStore(IProject project, IEventBroker eventBroker) {
        this.project = project;
        this.eventBroker = eventBroker;
    }

    @Override
    public DependencyReport analyze(IProgressMonitor monitor) {
        try {
            project.deleteMarkers(ANALYZE_PLUGIN_MARKER_TYPE, true, IResource.DEPTH_INFINITE);
            IStatus installStatus = getLocalDependencyStore().runBonitaProjectStoreInstall(monitor);
            if (!installStatus.isOK()) {
                throw new CoreException(installStatus);
            }
            IStatus status = runAnalyzePlugin(monitor);
            if (!status.isOK()) {
                // There is classloader issues that disappears on re run (ClassnotFound exception thrown)
                // TODO investigate classloading issues, maybe due to m2e ?
                // To reproduce: create a new project, install rest connector. First analysis will failed.
                status = runAnalyzePlugin(monitor);
                if (!status.isOK()) {
                    throw new CoreException(status);
                 }
            }

            IMavenProjectFacade mavenProjectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
            MavenProject mavenProject = mavenProjectFacade.getMavenProject(monitor);
            Optional<Plugin> plugin = mavenProject.getBuildPlugins().stream()
                    .filter(this::isBonitaProjectPlugin)
                    .findFirst();

            String reportPath = plugin.map(p -> p.getExecutions().stream()
                    .filter(exec -> exec.getGoals().contains("analyze"))
                    .map(exec -> getDepenencyReportPath(exec.getConfiguration()))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElseGet(() -> getDepenencyReportPath(plugin.get().getConfiguration())))
                    .orElse(DEAULT_REPORT_OUTPUT_FILE);
            var path = Paths.get(reportPath);
            File reportFile = Paths.get(reportPath).isAbsolute() ? path.toFile()
                    : project.getLocation().toFile().toPath().resolve(reportPath).toFile();
            if(reportFile.isFile()) {
                dependencyReport = mapper.readValue(reportFile, DependencyReport.class);
                eventBroker.send(PROJECT_DEPENDENCIES_ANALYZED_TOPIC, new HashMap<>());
            }
            return dependencyReport;
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return null;
        }catch(CoreException ce) {
            addMarker(ce.getStatus());
            if(ce.getCause() != null) {
                BonitaStudioLog.error(ce.getCause());
            }
            return null;
        }
    }


    private LocalDependenciesStore getLocalDependencyStore() {
        return RepositoryManager.getInstance().getRepository(project.getName()).getLocalDependencyStore();
    }

    private void addMarker(IStatus status) {
        try {
            IMarker marker = project.createMarker(ANALYZE_PLUGIN_MARKER_TYPE);
            marker.setAttribute(IMarker.SEVERITY, new StatusToMarkerSeverity(status).toMarkerSeverity());
            marker.setAttribute(IMarker.MESSAGE, status.getException() != null ? status.getMessage() + ". See details in Studio logs." : status.getMessage());
        } catch (CoreException e) {
           BonitaStudioLog.error(e);
        }
    }

    private String getDepenencyReportPath(Object configuration) {
        Xpp3Dom pluginConfiguration = (Xpp3Dom) configuration;
        return pluginConfiguration.getChild("outputFile").getValue();
    }

    private boolean isBonitaProjectPlugin(Plugin plugin) {
        return MavenProjectModelBuilder.BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID.equals(plugin.getGroupId())
                && MavenProjectModelBuilder.BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID.equals(plugin.getArtifactId());
    }

    public IStatus runAnalyzePlugin(IProgressMonitor monitor)
            throws CoreException {
        monitor.beginTask(Messages.analyzeProjectDependencies, IProgressMonitor.UNKNOWN);
        BonitaStudioLog.info(Messages.analyzeProjectDependencies, CommonRepositoryPlugin.PLUGIN_ID);
        IMaven maven = MavenPlugin.getMaven();
        final IMavenExecutionContext context = maven.createExecutionContext();
        final MavenExecutionRequest request = context.getExecutionRequest();
        request.setGoals(List.of("org.bonitasoft.maven:bonita-project-maven-plugin:analyze"));
        request.setPom(project.getFile("pom.xml").getLocation().toFile());
        MavenExecutionResult executionResult = context.execute(new ICallable<MavenExecutionResult>() {

            @Override
            public MavenExecutionResult call(final IMavenExecutionContext context, final IProgressMonitor innerMonitor)
                    throws CoreException {
                return maven.lookup(Maven.class).execute(request);
            }
        }, monitor);
        if (executionResult.getBuildSummary(executionResult.getProject()) instanceof BuildSuccess) {
            return Status.OK_STATUS;
        } else {
            return new Status(IStatus.ERROR,
                    MavenProjectDependenciesStore.class,
                    "An error occured while analyzing project dependencies",
                    executionResult.getExceptions().get(0));
        }
    }

    @Override
    public List<Definition> getConnectorDefinitions() {
        if (dependencyReport == null) {
            return Collections.emptyList();
        }
        return dependencyReport.getConnectorDefinitions();
    }

    @Override
    public List<ConnectorImplementation> getConnectorImplementations() {
        if (dependencyReport == null) {
            return Collections.emptyList();
        }
        return dependencyReport.getConnectorImplementations();
    }

    @Override
    public List<Definition> getActorFilterDefinitions() {
        if (dependencyReport == null) {
            return Collections.emptyList();
        }
        return dependencyReport.getFilterDefinitions();
    }

    @Override
    public List<ActorFilterImplementation> getActorFilterImplementations() {
        if (dependencyReport == null) {
            return Collections.emptyList();
        }
        return dependencyReport.getFilterImplementations();
    }

    @Override
    public List<Page> getPages() {
        if (dependencyReport == null) {
            return Collections.emptyList();
        }
        return dependencyReport.getPages();
    }

    @Override
    public List<RestAPIExtension> getRestAPIExtensions() {
        if (dependencyReport == null) {
            return Collections.emptyList();
        }
        return dependencyReport.getRestApiExtensions();
    }

    @Override
    public List<Form> getForms() {
        if (dependencyReport == null) {
            return Collections.emptyList();
        }
        return dependencyReport.getForms();
    }

    @Override
    public List<Theme> getThemes() {
        if (dependencyReport == null) {
            return Collections.emptyList();
        }
        return dependencyReport.getThemes();
    }

}
