/**
 * 
 */
package org.bonitasoft.studio.application.advisor;

import java.io.File;
import java.io.FileFilter;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class InitWorkspaceAdvisor extends InstallerApplicationWorkbenchAdvisor {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.application.advisor.InstallerApplicationWorkbenchAdvisor#executePostStartupHandler()
	 */
	@Override
	protected void executePostStartupHandler() {
		File[] repositoryToImport = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(".bos");
			}
		}) ;

		if(repositoryToImport != null && repositoryToImport.length > 0){
			for(File workspaceArchive : repositoryToImport){
				String repositoryName = workspaceArchive.getName().substring(0,workspaceArchive.getName().lastIndexOf(".")) ;
				RepositoryManager.getInstance().setRepository(repositoryName) ;
				IRepository repository = RepositoryManager.getInstance().getRepository(repositoryName) ;
				if(repository != null){
					try{
						repository.importFromArchive(workspaceArchive,false) ;
						workspaceArchive.delete() ;
					}catch (Exception e) {
						BonitaStudioLog.error(e) ;
					}
				}
			}
			RepositoryManager.getInstance().setRepository("default") ;
		}

		PlatformUI.getWorkbench().close() ;

	}

}
