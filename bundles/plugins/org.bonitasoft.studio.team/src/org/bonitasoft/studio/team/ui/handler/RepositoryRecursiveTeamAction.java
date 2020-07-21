/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.team.svn.ui.action.AbstractRecursiveTeamAction;

/**
 * @author Romain Bioteau
 *
 */
public class RepositoryRecursiveTeamAction extends AbstractRecursiveTeamAction {


    private IRepository repository;
    private IResource[] resources;


    public RepositoryRecursiveTeamAction(IRepository repository) {
        this.repository = repository;
    }

    public RepositoryRecursiveTeamAction(IResource[] resources) {
        this.resources = resources ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.team.svn.ui.action.AbstractSVNTeamAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.team.svn.ui.action.AbstractSVNTeamAction#runImpl(org.eclipse.jface.action.IAction)
     */
    @Override
    public void runImpl(IAction action) {

    }

    /* (non-Javadoc)
     * @see org.eclipse.team.svn.ui.action.AbstractNonRecursiveTeamAction#getSelectedResources()
     */
    @Override
    public IResource[] getSelectedResources() {
        if(resources == null){
            final List<IResource> resourceList = new ArrayList<IResource>() ;
            try {
                if(repository.getProject().isOpen()){
                    repository.getProject().accept(new IResourceVisitor() {

                        @Override
                        public boolean visit(IResource resource) throws CoreException {
                            IRepositoryStore<?> repositoryStore = repository.getRepositoryStore(resource) ;
                            if(repositoryStore != null){
                                if(repositoryStore.canBeShared()){
                                    resourceList.add(resource) ;
                                    return true ;
                                }
                            }
                            IRepositoryFileStore fileStore = repository.getFileStore(resource) ;
                            if(fileStore != null){
                                if(fileStore.canBeShared() && fileStore.getParentStore().canBeShared()){
                                    resourceList.add(resource) ;
                                    return true ;
                                }
                            }

                            if(resource.getName().equals(".project")){
                                resourceList.add(resource) ;
                            }

                            return true;
                        }
                    }) ;
                }
            } catch (CoreException e) {
                BonitaStudioLog.error(e) ;
            }
            resources = resourceList.toArray(new IResource[]{}) ;
        }
        return resources;
    }

}
