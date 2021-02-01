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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;

public abstract class MavenModelOperation implements IWorkspaceRunnable {

    
    private MavenXpp3Writer pomWriter = new MavenXpp3Writer();

    protected void saveModel(IProject project, Model model, IProgressMonitor monitor) throws CoreException {
        var pomFile = project.getFile(IMavenConstants.POM_FILE_NAME);
        try (OutputStream stream = new FileOutputStream(pomFile.getLocation().toFile())) {
            pomWriter.write(stream, model);
        } catch (IOException e) {
            throw new CoreException(
                    new Status(IStatus.ERROR, getClass(), "Failed to write maven model in pom.xml file.", e));
        }
        pomFile.refreshLocal(IResource.DEPTH_ONE, monitor);
    }

    protected IProject getCurrentProject() {
        return RepositoryManager.getInstance().getCurrentRepository().getProject();
    }
    
    protected Model getMavenModel(IProject project, IProgressMonitor monitor) throws CoreException {
        return getMavenProject(project, monitor)
                .getModel();
    }

    private MavenProject getMavenProject(IProject project, IProgressMonitor monitor) throws CoreException {
        return MavenPlugin.getMavenProjectRegistry().getProject(project)
                .getMavenProject(monitor);
    }
    
  
}
