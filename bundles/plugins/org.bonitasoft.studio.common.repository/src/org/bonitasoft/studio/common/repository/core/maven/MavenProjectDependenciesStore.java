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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.apache.maven.model.Dependency;
import org.bonitasoft.plugin.analyze.report.model.ActorFilterImplementation;
import org.bonitasoft.plugin.analyze.report.model.ConnectorImplementation;
import org.bonitasoft.plugin.analyze.report.model.Definition;
import org.bonitasoft.plugin.analyze.report.model.DependencyReport;
import org.bonitasoft.plugin.analyze.report.model.Form;
import org.bonitasoft.plugin.analyze.report.model.Issue;
import org.bonitasoft.plugin.analyze.report.model.Issue.Severity;
import org.bonitasoft.plugin.analyze.report.model.Issue.Type;
import org.bonitasoft.plugin.analyze.report.model.Page;
import org.bonitasoft.plugin.analyze.report.model.RestAPIExtension;
import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.plugin.AnalyzeBonitaProjectDependenciesPlugin;
import org.bonitasoft.studio.common.repository.core.maven.plugin.InstallLocalDependenciesPlugin;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.common.ui.jface.databinding.StatusToMarkerSeverity;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MavenProjectDependenciesStore implements ProjectDependenciesStore {

    public static final String PROJECT_DEPENDENCIES_ANALYZED_TOPIC = "bonita/project/analyzed";
    private static final String ANALYZE_PLUGIN_MARKER_TYPE = CommonRepositoryPlugin.PLUGIN_ID
            + ".analyzePluginMarkerType";

    private BonitaProject project;
    private DependencyReport dependencyReport;
    private ObjectMapper mapper = new ObjectMapper().findAndRegisterModules()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .setSerializationInclusion(Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private IEventBroker eventBroker;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public MavenProjectDependenciesStore(BonitaProject project, IEventBroker eventBroker) {
        this.project = project;
        this.eventBroker = eventBroker;
    }

    @Override
    public Optional<DependencyReport> analyze(IProgressMonitor monitor) {
        ISchedulingRule rule = ResourcesPlugin.getWorkspace().getRoot();
        Job.getJobManager().beginRule(rule, monitor);
        LOCK.lock();
        try {
            var appProject = project.getAppProject();
            appProject.deleteMarkers(ANALYZE_PLUGIN_MARKER_TYPE, true, IResource.DEPTH_INFINITE);
            boolean updateMavenProject = false;
            if(project.getAppProject().getFolder(LocalDependenciesStore.NAME).exists()) {
            	var installPlugin = new InstallLocalDependenciesPlugin(project);
            	var result = installPlugin.execute(monitor);
            	if(!result.isOK()) {
            	  throw new CoreException(result);
            	}
            	updateMavenProject = true;
            }
            var bonitaProjectPlugin = new AnalyzeBonitaProjectDependenciesPlugin(project);
            var result = bonitaProjectPlugin.execute(monitor);
            if (!result.isOK()) {
            	 throw new CoreException(result);
            }
            String reportPath = bonitaProjectPlugin.getReportPath();
            var path = Paths.get(reportPath);
            File reportFile = Paths.get(reportPath).isAbsolute() ? path.toFile()
                    : appProject.getLocation().toFile().toPath().resolve(reportPath).toFile();
            if (reportFile.isFile()) {
                dependencyReport = mapper.readValue(reportFile, DependencyReport.class);
                eventBroker.send(PROJECT_DEPENDENCIES_ANALYZED_TOPIC, Map.of());
                dependencyReport.getIssues().stream()
                        .map(this::getArtifactId)
                        .filter(Objects::nonNull)
                        .distinct()
                        .map(artifactId -> createMultiStatus(artifactId, dependencyReport.getIssues()))
                        .filter(Objects::nonNull)
                        .forEach(this::addMarker);
            }
           if(updateMavenProject) {
        	    new UpdateMavenProjectJob(List.of(project.getAppProject()), false, false,
                        false,
                        true, true).schedule();
           }
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        } catch (CoreException ce) {
            addMarker(ce.getStatus());
            if (ce.getCause() != null) {
                BonitaStudioLog.error(ce.getCause());
            } else {
                BonitaStudioLog.error(ce, CommonRepositoryPlugin.PLUGIN_ID);
            }
        } finally {
            Job.getJobManager().endRule(rule);
            LOCK.unlock();
        }
        return Optional.ofNullable(dependencyReport);
    }

    private MultiStatus createMultiStatus(String artifactId, List<Issue> issues) {
        if (artifactId != null) {
            String artifact = artifactId.split(":")[1];
            var status = new MultiStatus(getClass(), 0,
                    String.format("%s: Open Overview > Extension for more details. ", artifact));
            issues.stream()
                    .filter(issue -> Objects.equals(artifactId, getArtifactId(issue)))
                    .map(MavenProjectDependenciesStore::toStatus)
                    .forEach(status::add);
            return status;
        }
        return new MultiStatus(MavenProjectDependenciesStore.class, 0, null);
    }

    private String getArtifactId(Issue issue) {
        if (!issue.getContext().isEmpty()) {
            return issue.getContext().get(0);
        }
        return null;
    }

    public static IStatus toStatus(Issue issue) {
        Severity severity = Issue.Severity.valueOf(issue.getSeverity());
        var message = toMessage(issue);
        switch (severity) {
            case ERROR:
                return Status.error(message);
            case WARNING:
                return Status.warning(message);
            default:
                return Status.info(message);
        }
    }

    private static String toMessage(Issue issue) {
        Type issueType = null;
        try {
            issueType = Issue.Type.valueOf(issue.getType());
        } catch (IllegalArgumentException e) {
            // Unknown issue type
            return issue.getMessage();
        }
        switch (issueType) {
            case INCOMPATIBLE_DEPENDENCY:
                return issue.getContext().size() == 2
                        ? String.format(Messages.incompatibleTransitiveDependencyErrorMessage,
                                issue.getContext().get(0),
                                issue.getContext().get(1))
                        : String.format(Messages.incompatibleDependencyErrorMessage, issue.getContext().get(0));
            case UNKNOWN_DEFINITION_TYPE:
            case INVALID_DESCRIPTOR_FILE:
            default:
                return issue.getMessage();
        }
    }

    private void addMarker(IStatus status) {
        try {
            var eclipseProject = project.getAdapter(IProject.class);
            if (eclipseProject.isAccessible()) {
                IMarker marker = eclipseProject.createMarker(ANALYZE_PLUGIN_MARKER_TYPE);
                marker.setAttribute(IMarker.SEVERITY, new StatusToMarkerSeverity(status).toMarkerSeverity());
                marker.setAttribute(IMarker.MESSAGE, status.getException() != null
                        ? status.getMessage() + ". See details in Studio logs." : status.getMessage());
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
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

    @Override
    public List<Issue> getIssues() {
        if (dependencyReport == null) {
            return Collections.emptyList();
        }
        return dependencyReport.getIssues();
    }

    @Override
    public List<Issue> findIssues(Dependency dependency) {
        String artifactId = toArtifactId(dependency);
        if (artifactId != null) {
            return getIssues().stream()
                    .filter(issue -> issue.getContext().stream().anyMatch(artifactId::equals))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public MultiStatus getStatus(Dependency dependency) {
        if (dependencyReport == null) {
            return new MultiStatus(getClass(), 0, "");
        }
        return createMultiStatus(toArtifactId(dependency), dependencyReport.getIssues());
    }
    
    @Override
    public Optional<DependencyReport> getReport() {
    	return Optional.ofNullable(dependencyReport);
    }

    private static String toArtifactId(Dependency dependency) {
        if (dependency.getVersion() != null) {
            return new DefaultArtifact(dependency.getGroupId(), dependency.getArtifactId(), dependency.getVersion(),
                    "compile", dependency.getType(),
                    dependency.getClassifier(), new DefaultArtifactHandler()).getId();
        }
        return null;
    }
    
}
