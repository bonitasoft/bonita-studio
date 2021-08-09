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
package org.bonitasoft.studio.common.repository.core.maven.plugin;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.Maven;
import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.MavenUpdateRequest;

public class BonitaProjectPlugin {

    private static final String DEAULT_REPORT_OUTPUT_FILE = "target/bonita-dependencies.json";

    private IProject project;

    public BonitaProjectPlugin(IProject project) {
        this.project = project;
    }

    public synchronized IStatus execute(IProject project, IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.analyzeProjectDependencies, IProgressMonitor.UNKNOWN);

        IMaven maven = maven();
        var mavenProject = getMavenProject(project, monitor);
        if (mavenProject == null) {
            return new Status(IStatus.ERROR, getClass(),
                    "An error occured while executing bonita project plugin. Cannot resolve the Maven project.");
        }
        List<String> goals = List.of(String.format("%s:%s:%s",
                DefaultPluginVersions.BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID,
                DefaultPluginVersions.BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID,
                "install"),
                        String.format("%s:%s:%s",
                                DefaultPluginVersions.BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID,
                                DefaultPluginVersions.BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID,
                                "analyze")
        );
        var ctx = maven.createExecutionContext();
        var request = ctx.getExecutionRequest();
        request.setGoals(goals);
        request.setPom(mavenProject.getFile());
        MavenExecutionResult executionResult = ctx.execute(mavenProject, new ICallable<MavenExecutionResult>() {

            @Override
            public MavenExecutionResult call(IMavenExecutionContext context, IProgressMonitor monitor)
                    throws CoreException {
                return maven.lookup(Maven.class).execute(request);
            }

        }, monitor);
        
        if(executionResult.getBuildSummary(executionResult.getProject()) instanceof BuildSuccess) {
            MavenPlugin.getMavenProjectRegistry().refresh(new MavenUpdateRequest(project, false, false), monitor);
            return Status.OK_STATUS;
        }else {
            throw new CoreException(new Status(IStatus.ERROR, getClass(),"Failed to execute bonita-project-maven-plugin", executionResult.hasExceptions() ? executionResult.getExceptions().get(0) : null));
        }
    }

    private MavenProject getMavenProject(IProject project, IProgressMonitor monitor) throws CoreException {
        IMavenProjectFacade projectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        if (projectFacade == null) {
            return null;
        }
        return projectFacade.getMavenProject(monitor);
    }

    IMaven maven() {
        return MavenPlugin.getMaven();
    }

    public String getReportPath() throws CoreException {
        var mavenProjectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        if (mavenProjectFacade != null) {
            var mavenProject = mavenProjectFacade.getMavenProject(AbstractRepository.NULL_PROGRESS_MONITOR);
            Optional<Plugin> plugin = mavenProject.getBuildPlugins().stream()
                    .filter(this::isBonitaProjectPlugin)
                    .findFirst();
            return plugin.map(p -> p.getExecutions().stream()
                    .filter(exec -> exec.getGoals().contains("analyze"))
                    .map(exec -> getDepenencyReportPath(exec.getConfiguration()))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElseGet(() -> getDepenencyReportPath(plugin.get().getConfiguration())))
                    .orElse(DEAULT_REPORT_OUTPUT_FILE);
        }
        return DEAULT_REPORT_OUTPUT_FILE;
    }

    private String getDepenencyReportPath(Object configuration) {
        Xpp3Dom pluginConfiguration = (Xpp3Dom) configuration;
        if (pluginConfiguration != null && pluginConfiguration.getChild("outputFile") != null) {
            return pluginConfiguration.getChild("outputFile").getValue();
        }
        return DEAULT_REPORT_OUTPUT_FILE;
    }

    private boolean isBonitaProjectPlugin(Plugin plugin) {
        return DefaultPluginVersions.BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID.equals(plugin.getGroupId())
                && DefaultPluginVersions.BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID.equals(plugin.getArtifactId());
    }

}
