/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.command;

import java.io.File;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.ui.editor.MavenDependencyTreeEditor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;

public class ShowMavenDependencyTreeHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            Object firstElem = ((IStructuredSelection) selection).getFirstElement();
            if (firstElem instanceof IAdaptable) {
                var project = RepositoryManager.getInstance().getCurrentProject().orElseThrow();
                if (project != null && project.exists()) {
                    MavenProject mavenProject = null;
                    try {
                        mavenProject = getMavenProject(project.getAppProject(), new NullProgressMonitor());
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                    if (mavenProject != null) {
                        try {
                            IWorkbenchPage page = PlatformUI.getWorkbench()
                                    .getActiveWorkbenchWindow()
                                    .getActivePage();

                            page.openEditor(new FileEditorInput(getPomFile(mavenProject.getFile())),
                                    MavenDependencyTreeEditor.EDITOR_ID);

                        } catch (PartInitException e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                }
            }
        }

        return null;
    }

    private MavenProject getMavenProject(IProject project, IProgressMonitor monitor) throws CoreException {
        IMavenProjectFacade projectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        if (projectFacade == null) {
            throw new CoreException(Status.error("Cannot find Maven project for " + project));
        }
        return projectFacade.getMavenProject(monitor);
    }

    private IFile getPomFile(File pomJavaFile) {
        return ResourcesPlugin.getWorkspace()
                .getRoot()
                .getFileForLocation(Path.fromOSString(pomJavaFile.getAbsolutePath()));
    }
}
