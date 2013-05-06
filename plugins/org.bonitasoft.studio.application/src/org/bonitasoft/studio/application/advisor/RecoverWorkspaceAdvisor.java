/**
 * 
 */
package org.bonitasoft.studio.application.advisor;

import java.io.File;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
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
	 * @see org.bonitasoft.studio.application.advisor.InstallerApplicationWorkbenchAdvisor#executePostStartupHandler()
	 */
	@Override
	protected void executePostStartupHandler() {
		for(IRepository repo : RepositoryManager.getInstance().getAllRepositories()){
			if(!repo.isShared()){
				repo.exportToArchive(targetPath+File.separatorChar+repo.getName()+".bos") ;
			}
		}
		PlatformUI.getWorkbench().close() ; //close application
	}

}


