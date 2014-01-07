/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.advisor;

import org.bonitasoft.studio.application.BonitaStudioWorkbenchAdvisor;
import org.bonitasoft.studio.common.BonitaHomeUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

public abstract class InstallerApplicationWorkbenchAdvisor extends BonitaStudioWorkbenchAdvisor {

	protected static final String PRIORITY = "priority";

	@Override
	public boolean openWindows() {
		return true; //DO NOT OPEN WORKBENCH WINDOW
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#preStartup()
	 */
	@Override
	public void preStartup() {
		try {
			IProgressMonitor monitor = new NullProgressMonitor() ;
			IWorkspaceRunnable workspaceOperation = new IWorkspaceRunnable() {

				public void run(IProgressMonitor monitor) throws CoreException {
					IRepository repository = RepositoryManager.getInstance().getCurrentRepository() ; 
					if(!repository.getProject().exists()){
						repository.create() ;
					}
				}
			} ;
			workspaceOperation.run(monitor) ;

	        BOSWebServerManager.getInstance().copyTomcatBundleInWorkspace(monitor) ;
	        BonitaHomeUtil.initBonitaHome();
			
		} catch (Exception e) {
			BonitaStudioLog.error(e);
		}
	}

	@Override
	public void postStartup() {
		executePostStartupHandler() ;
	}
	
	protected abstract void executePostStartupHandler() ;


	protected void closeAllProjects(IProgressMonitor monitor,IWorkspace workspace) {
		for (IProject project : workspace.getRoot().getProjects()) {
			if (project.isOpen()) {
				try {
					project.close(monitor);
					monitor.worked(1);
				} catch (CoreException e) {
					BonitaStudioLog.error(e);
				}
			}
		}
	}
}
