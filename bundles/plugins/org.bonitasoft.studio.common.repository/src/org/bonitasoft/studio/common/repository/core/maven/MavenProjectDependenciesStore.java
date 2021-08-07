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
import org.bonitasoft.studio.common.jface.databinding.StatusToMarkerSeverity;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.plugin.BonitaProjectPlugin;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.services.events.IEventBroker;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MavenProjectDependenciesStore implements ProjectDependenciesStore {

    public static final String PROJECT_DEPENDENCIES_ANALYZED_TOPIC = "bonita/project/analyzed";
    private static final String ANALYZE_PLUGIN_MARKER_TYPE = CommonRepositoryPlugin.PLUGIN_ID
            + ".analyzePluginMarkerType";

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
            BonitaProjectPlugin bonitaProjectPlugin = new BonitaProjectPlugin(project);
            IStatus status = bonitaProjectPlugin.execute(project, monitor);
            if (!status.isOK()) {
                throw new CoreException(status);
            }

            String reportPath = bonitaProjectPlugin.getReportPath();
            var path = Paths.get(reportPath);
            File reportFile = Paths.get(reportPath).isAbsolute() ? path.toFile()
                    : project.getLocation().toFile().toPath().resolve(reportPath).toFile();
            if (reportFile.isFile()) {
                dependencyReport = mapper.readValue(reportFile, DependencyReport.class);
                eventBroker.send(PROJECT_DEPENDENCIES_ANALYZED_TOPIC, Map.of());

                dependencyReport.getIssues().stream()
                        .map(MavenProjectDependenciesStore::toStatus)
                        .filter(Objects::nonNull)
                        .forEach(this::addMarker);
            }

            return dependencyReport;
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return null;
        } catch (CoreException ce) {
            addMarker(ce.getStatus());
            if (ce.getCause() != null) {
                BonitaStudioLog.error(ce.getCause());
            }
            return null;
        }
    }

    public static IStatus toStatus(Issue issue) {
        if (Issue.Type.valueOf(issue.getType()) == Type.INCOMPATIBLE_DEPENDENCY) {
            String message = issue.getContext().size() == 2
                    ? String.format(Messages.incompatibleTransitiveDependencyErrorMessage, issue.getContext().get(0),
                            issue.getContext().get(1))
                    : String.format(Messages.incompatibleDependencyErrorMessage, issue.getContext().get(0));
            return ValidationStatus.error(message);
        } else {
            Severity severity = Issue.Severity.valueOf(issue.getSeverity());
            switch (severity) {
                case ERROR:
                    return ValidationStatus.error(issue.getMessage());
                case WARNING:
                    return ValidationStatus.warning(issue.getMessage());
                default:
                    return ValidationStatus.info(issue.getMessage());
            }
        }
    }

    private void addMarker(IStatus status) {
        try {
            if (project.isAccessible()) {
                IMarker marker = project.createMarker(ANALYZE_PLUGIN_MARKER_TYPE);
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

}
