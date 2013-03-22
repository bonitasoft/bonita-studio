/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @author Romain Bioteau
 *
 */
public class RepositoryManager {

    private static final String REPOSITORY_FACTORY_IMPLEMENTATION_ID ="org.bonitasodt.studio.repositoryImplementation" ;
    private static final String PRIORITY = "priority";
    private static final String CLASS = "class";

    private static RepositoryManager INSTANCE ;

    private IRepository repository;
    private final IPreferenceStore preferenceStore;
    private final IConfigurationElement repositoryImplementationElement;

    private RepositoryManager(){
        IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(REPOSITORY_FACTORY_IMPLEMENTATION_ID) ;
        List<IConfigurationElement> sortedElems = sortByPriority(elements) ;
        repositoryImplementationElement =  sortedElems.get(0) ; //Higher element
        preferenceStore = CommonRepositoryPlugin.getDefault().getPreferenceStore() ;
        String currentRepository = preferenceStore.getString(RepositoryPreferenceConstant.CURRENT_REPOSITORY) ;
        repository = createRepository(currentRepository) ;
    }

    private List<IConfigurationElement> sortByPriority(IConfigurationElement[] elements) {
        List<IConfigurationElement> sortedConfigElems = new ArrayList<IConfigurationElement>() ;
        for(IConfigurationElement elem : elements){
            sortedConfigElems.add(elem) ;
        }

        Collections.sort(sortedConfigElems, new Comparator<IConfigurationElement>() {

            @Override
            public int compare(IConfigurationElement e1, IConfigurationElement e2) {
                int	p1 = 0;
                int p2 = 0 ;
                try{
                    p1 = Integer.parseInt(e1.getAttribute(PRIORITY));
                }catch (NumberFormatException e) {
                    p1 = 0 ;
                }
                try{
                    p2 = Integer.parseInt(e2.getAttribute(PRIORITY));
                }catch (NumberFormatException e) {
                    p2 = 0 ;
                }
                return  p2 -p1 ; //Highest Priority first
            }

        }) ;
        return sortedConfigElems ;

    }

    private IRepository createRepository(String name) {
        try {
            IRepository repository = (IRepository) repositoryImplementationElement.createExecutableExtension(CLASS) ;
            repository.createRepository(name) ;
            return repository;
        } catch (CoreException e) {
            BonitaStudioLog.error(e) ;
        }
        return null ;
    }

    public static RepositoryManager getInstance() {
        if (RepositoryManager.INSTANCE == null) {
            synchronized(RepositoryManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RepositoryManager();
                }
            }
        }
        return INSTANCE ;
    }

    public IPreferenceStore getPreferenceStore() {
        return preferenceStore;
    }

    public IRepository getCurrentRepository(){
        return repository ;
    }

    public IRepositoryStore<?> getRepositoryStore(Class<?> storeClass) {
        return getCurrentRepository().getRepositoryStore(storeClass);
    }

    public IRepository getRepository(String repositoryName) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject project = workspace.getRoot().getProject(repositoryName);
        if(project == null || !project.exists()){
            return null;
        }
        return createRepository(repositoryName) ;
    }

    public List<IRepository> getAllRepositories() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final List<IRepository> result = new ArrayList<IRepository>() ;
        try {
			workspace.run(new IWorkspaceRunnable() {
				
				@Override
				public void run(IProgressMonitor monitor) throws CoreException {
			        result.add(repository) ;
			        for(IProject p : workspace.getRoot().getProjects()){
			            try {
			            	boolean close = false;
			                if(!p.isOpen()){
			                    p.open(Repository.NULL_PROGRESS_MONITOR);
			                    close = true;
			                }
			                if(p.getDescription().hasNature(BonitaProjectNature.NATURE_ID)){
			                    if(!p.getName().equals(repository.getName())){
			                        result.add(createRepository(p.getName())) ;
			                    }
			                }
			                if(close){
			                	 p.close(Repository.NULL_PROGRESS_MONITOR);
			                }
			            } catch (CoreException e) {
			                BonitaStudioLog.error(e);
			            }
			        }
				}
			}, Repository.NULL_PROGRESS_MONITOR);
		} catch (CoreException e) {
			BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
		}

        return result;
    }

    public void setRepository(String repositoryName) {
        if(repository != null && repository.getName().equals(repositoryName)){
            return;
        }else{
        	repository.close();
        }
        repository = getRepository(repositoryName) ;
        if(repository == null){
            repository = createRepository(repositoryName) ;
        }
        repository.create() ;
        repository.open();
        preferenceStore.setValue(RepositoryPreferenceConstant.CURRENT_REPOSITORY,repositoryName) ;
    }
}
