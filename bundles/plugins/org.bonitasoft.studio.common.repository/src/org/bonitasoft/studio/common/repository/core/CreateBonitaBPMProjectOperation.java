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

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.osgi.framework.BundleReference;

/**
 * @author Romain Bioteau
 */
public class CreateBonitaBPMProjectOperation implements IWorkspaceRunnable {

    private static final String META_INF_FOLDER_NAME = "META-INF";
    private IProject project;
    private final IWorkspace workspace;
    private final String projectName;
    private final Set<String> builders = new HashSet<String>();
    private final Set<String> natures = new HashSet<String>();

    public CreateBonitaBPMProjectOperation(final IWorkspace workspace, final String projectName) {
        this.workspace = workspace;
        this.projectName = projectName;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws CoreException {
        project = workspace.getRoot().getProject(projectName);
        if (project.exists()) {
            throw new CoreException(new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, String.format("Project %s already exists.", projectName)));
        }
        project.create(monitor);
        project.open(monitor);
        project.setDescription(
                new ProjectDescriptionBuilder().withProjectName(project.getName()).withComment(ProductVersion.CURRENT_VERSION).havingNatures(natures)
                        .havingBuilders(builders).build(), monitor);
        createJavaProject(monitor);
        createProjectManifest(monitor);
    }

    private void createJavaProject(final IProgressMonitor monitor) {
        monitor.subTask(Messages.initializingJavaProject);
        final IJavaProject javaProject = asJavaProject();
        javaProject.setOption(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7);
        javaProject.setOption(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);
        javaProject.setOption(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
        javaProject.setOption(JavaCore.CORE_JAVA_BUILD_INVALID_CLASSPATH, "ignore");
        monitor.worked(1);
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

    private void createProjectManifest(final IProgressMonitor monitor) throws CoreException {
        final IFolder metaInf = project.getFolder(META_INF_FOLDER_NAME);
        metaInf.create(false, true, monitor);
        final IFile projectManifest = project.getFolder(META_INF_FOLDER_NAME).getFile("MANIFEST.MF");
        final InputStream is = Repository.class.getResourceAsStream("MANIFEST.MF.template");
        final InputStream is2 = FileUtil.replaceStringInFile(is, "XXX_ENGINE_BUNDLE_XXX", engineBundleSymbolicName());
        projectManifest.create(is2, false, monitor);

    }

    protected String engineBundleSymbolicName() {
        final BundleReference cl = (BundleReference) BusinessArchive.class.getClassLoader();
        final String symbolicName = cl.getBundle().getSymbolicName();
        return symbolicName;
    }

    public IProject getProject() {
        return project;
    }

}
