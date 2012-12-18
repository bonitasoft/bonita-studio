/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.antTasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Mickael Istria
 *
 */
public class RefreshWorkspaceTask extends Task {

	private String projectName;

	private boolean allWorkspace = false;

	@Override
	public void execute() throws BuildException {
		try {
			if(allWorkspace){
				for(IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()){
					if(!p.isOpen()){
						p.open(null);
					}
				}
				ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
			}else{
				IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				if (!iProject.exists()) {
					iProject.create(null);
				}
				if (!iProject.isOpen()) {
					iProject.open(null);
				}
				iProject.refreshLocal(IResource.DEPTH_INFINITE, null);
			}
		} catch (CoreException ex) {
			throw new BuildException(ex);
		}
	}

	public void setProject(String projectName) {
		this.projectName = projectName;
	}

	public void setAllWorkspace(boolean allWorkspace) {
		this.allWorkspace = allWorkspace;
	}

}
