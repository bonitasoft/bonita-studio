/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.core.maven.plugin;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.RefreshTab;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.m2e.actions.MavenLaunchConstants;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectFacade;

public class BonitaProjectPlugin {
    
    private static final String DEAULT_REPORT_OUTPUT_FILE = "target/bonita-dependencies.json";
    
    private IProject project;

    public BonitaProjectPlugin(IProject project) {
        this.project = project;
    }
    
    public IStatus execute(IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.analyzeProjectDependencies, IProgressMonitor.UNKNOWN);
        final ILaunchConfigurationType launchConfigurationType = DebugPlugin.getDefault().getLaunchManager()
                .getLaunchConfigurationType(MavenLaunchConstants.LAUNCH_CONFIGURATION_TYPE_ID);
        ILaunchConfigurationWorkingCopy wc = configureAnalyzePluginLaunchConfiguration(launchConfigurationType);
        final ILaunch launch = wc.launch(ILaunchManager.RUN_MODE,
                AbstractRepository.NULL_PROGRESS_MONITOR,
                false);
        final IProcess process = launch.getProcesses()[0];
        waitForBuildProcessTermination(launch);
        try {
            return process.getExitValue() == 0 ? Status.OK_STATUS
                    : new Status(IStatus.ERROR, getClass(), "An error occured while executing bonita project plugin");
        } catch (DebugException e) {
           return new Status(IStatus.ERROR, getClass(),"An error occured while executing bonita project plugin", e);
        }
    }
    

    private void waitForBuildProcessTermination(final ILaunch launch) {
        while (!launch.isTerminated()) {
            try {
                Thread.sleep(200);
            } catch (final InterruptedException e) {
            }
        }
    }

    private ILaunchConfigurationWorkingCopy configureAnalyzePluginLaunchConfiguration(ILaunchConfigurationType configType)
            throws CoreException {
        ILaunchConfigurationWorkingCopy workingCopy = configType.newInstance(null, "Run Bonita Project Maven plugin");
        workingCopy.setAttribute(MavenLaunchConstants.ATTR_POM_DIR,
                project.getLocation().toFile().getAbsolutePath());
        workingCopy.setAttribute(MavenLaunchConstants.ATTR_GOALS,
                "org.bonitasoft.maven:bonita-project-maven-plugin:install org.bonitasoft.maven:bonita-project-maven-plugin:analyze");
        workingCopy.setAttribute(IDebugUIConstants.ATTR_PRIVATE, true);
        workingCopy.setAttribute(RefreshTab.ATTR_REFRESH_SCOPE, "${project}");
        workingCopy.setAttribute(RefreshTab.ATTR_REFRESH_RECURSIVE, false);
        workingCopy.setAttribute(DebugPlugin.ATTR_CAPTURE_OUTPUT, false);
        workingCopy.setAttribute(DebugPlugin.ATTR_CONSOLE_ENCODING, "UTF-8");
        final IPath path = getJREContainerPath(project);
        if (path != null) {
            workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_JRE_CONTAINER_PATH,
                    path.toPortableString());
        }
        return workingCopy;
    }

    private IPath getJREContainerPath(final IProject project) throws CoreException {
        if (project != null && project.hasNature(JavaCore.NATURE_ID)) {
            final IJavaProject javaProject = JavaCore.create(project);
            return Stream.of(javaProject.getRawClasspath())
                    .filter(entry -> JavaRuntime.JRE_CONTAINER.equals(entry.getPath().segment(0)))
                    .findFirst()
                    .map(IClasspathEntry::getPath)
                    .orElse(null);

        }
        return null;
    }

    public String getReportPath() throws CoreException {
        IMavenProjectFacade mavenProjectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        MavenProject mavenProject = mavenProjectFacade.getMavenProject(AbstractRepository.NULL_PROGRESS_MONITOR);
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
    

    private String getDepenencyReportPath(Object configuration) {
        Xpp3Dom pluginConfiguration = (Xpp3Dom) configuration;
        if(pluginConfiguration != null && pluginConfiguration.getChild("outputFile") != null) {
            return pluginConfiguration.getChild("outputFile").getValue();
        }
        return DEAULT_REPORT_OUTPUT_FILE;
    }

    private boolean isBonitaProjectPlugin(Plugin plugin) {
        return ProjectDefaultConfiguration.BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID.equals(plugin.getGroupId())
                && ProjectDefaultConfiguration.BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID.equals(plugin.getArtifactId());
    }


}
