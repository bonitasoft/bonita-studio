/**
 * 
 */
package org.bonitasoft.studio.application.advisor;

import java.io.File;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain
 *
 */
public class RecoverWorkspaceAdvisor extends InstallerApplicationWorkbenchAdvisor {

	private String targetPath;


	public RecoverWorkspaceAdvisor(String targetPath){
		super() ;
		this.targetPath = targetPath ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.application.advisor.InstallerApplicationWorkbenchAdvisor#preStartup()
	 */
	@Override
	public void preStartup() {
		try {
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE,new NullProgressMonitor());
		} catch (CoreException e) {
			BonitaStudioLog.error(e);
		}
		IProject defaultDir = ResourcesPlugin.getWorkspace().getRoot().getProject("default");
		if (defaultDir != null && defaultDir.exists()){
			super.preStartup();
		} else {
			MessageDialog.openError(Display.getCurrent().getActiveShell(),Messages.invalidWorkspaceTitle, Messages.invalidWorkspace);
			PlatformUI.getWorkbench().close() ; 
		}

	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.application.advisor.InstallerApplicationWorkbenchAdvisor#executePostStartupHandler()
	 */
	@Override
	protected void executePostStartupHandler() {
		for(IRepository repo : RepositoryManager.getInstance().getAllRepositories()){
			if(!repo.isShared()){
				RepositoryManager.getInstance().setRepository(repo.getName());
				repo.exportToArchive(targetPath+File.separatorChar+repo.getName()+".bos") ;
				RepositoryManager.getInstance().getCurrentRepository().close();
			}
		}
		RepositoryManager.getInstance().setRepository("default");
		PlatformUI.getWorkbench().close() ; //close application
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.application.advisor.InstallerApplicationWorkbenchAdvisor#postStartup()
	 */
	@Override
	public void postStartup() {
		IProject defaultDir = ResourcesPlugin.getWorkspace().getRoot().getProject("default");
		if (defaultDir != null && defaultDir.exists()){
			super.postStartup();
		}
	}

}


