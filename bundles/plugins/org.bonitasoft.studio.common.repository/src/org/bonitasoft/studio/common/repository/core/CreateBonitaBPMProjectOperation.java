/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.Messages;
import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.launching.LaunchingPlugin;
import org.eclipse.jdt.launching.JavaRuntime;

/**
 * @author Romain Bioteau
 */
public class CreateBonitaBPMProjectOperation implements IWorkspaceRunnable {

    private IProject project;
    private final IWorkspace workspace;
    private final String projectName;
    private final Set<String> builders = new HashSet<>();
    private final List<String> natures = new ArrayList<>();

    public CreateBonitaBPMProjectOperation(final IWorkspace workspace, final String projectName) {
        this.workspace = workspace;
        this.projectName = projectName;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        project = workspace.getRoot().getProject(projectName);
        if (!project.exists()) {
            project.create(monitor);
            project.open(monitor);
            if (!project.hasNature(BonitaProjectNature.NATURE_ID)) {
                project.setDescription(
                        new ProjectDescriptionBuilder().withProjectName(project.getName())
                                .withComment(ProductVersion.CURRENT_VERSION).havingNatures(natures)
                                .havingBuilders(builders).build(),
                        monitor);
            }
            addBuildProperties(monitor);
        }
        createJavaProject(monitor);
    }

    private void addBuildProperties(IProgressMonitor monitor) throws CoreException {
        final IFile buildPropertiesFile = project.getFile("build.properties");
        if (!buildPropertiesFile.exists()) {
            try (final ByteArrayInputStream is = new ByteArrayInputStream("custom = true".getBytes());) {
                buildPropertiesFile.create(is, true, monitor);
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private void createJavaProject(final IProgressMonitor monitor) {
        monitor.subTask(Messages.initializingJavaProject);
        final IJavaProject javaProject = asJavaProject();
        javaProject.setOption(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_11);
        javaProject.setOption(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_11);
        javaProject.setOption(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_11);
        javaProject.setOption(JavaCore.CORE_JAVA_BUILD_INVALID_CLASSPATH, JavaCore.IGNORE);
        IEclipsePreferences preferences = jdtLaunchingPrefNode();
        preferences.put(JavaRuntime.PREF_STRICTLY_COMPATIBLE_JRE_NOT_AVAILABLE, JavaCore.IGNORE);
        monitor.worked(1);
    }

    protected IEclipsePreferences jdtLaunchingPrefNode() {
        return DefaultScope.INSTANCE.getNode(LaunchingPlugin.ID_PLUGIN);
    }

    protected IJavaProject asJavaProject() {
        return JavaCore.create(project);
    }

    public CreateBonitaBPMProjectOperation addBuilder(final String builderId) {
        builders.add(builderId);
        return this;
    }

    public CreateBonitaBPMProjectOperation addNature(final String natureId) {
        natures.add(natureId);
        return this;
    }

    public IProject getProject() {
        return project;
    }

}
