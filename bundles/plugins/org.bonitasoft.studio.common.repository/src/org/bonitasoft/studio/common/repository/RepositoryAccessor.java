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
package org.bonitasoft.studio.common.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @author Romain Bioteau
 */
@Creatable
@Singleton
public class RepositoryAccessor {

    private RepositoryManager repositoryManagerInstance;

    public RepositoryAccessor() {
        //KEEP ME
    }

    RepositoryAccessor(final RepositoryManager manager) {
        repositoryManagerInstance = manager;
    }

    @PostConstruct
    RepositoryAccessor init() {
        repositoryManagerInstance = RepositoryManager.getInstance();
        repositoryManagerInstance.setRepositoryAccessor(this);
        return this;
    }

    public <T extends IRepositoryStore<?>> T getRepositoryStore(final Class<T> storeClass) {
        return repositoryManagerInstance.getRepositoryStore(storeClass);
    }

    public AbstractRepository getCurrentRepository() {
        return repositoryManagerInstance.getCurrentRepository();
    }

    public IRepository start(final IProgressMonitor monitor) {
        monitor.beginTask(Messages.loadingCurrentProject, IProgressMonitor.UNKNOWN);
        AbstractRepository repository = getCurrentRepository();
        if (!repository.exists()) {
            repository.create(ProjectMetadata.defaultMetadata(), AbstractRepository.NULL_PROGRESS_MONITOR);
        }
        return repository.open(AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    public IWorkspace getWorkspace() {
        return ResourcesPlugin.getWorkspace();
    }

    public AbstractRepository getRepository(String targetRepository) {
        return repositoryManagerInstance.getRepository(targetRepository);
    }

    public void setRepository(final String repositoryName) {
        repositoryManagerInstance.setRepository(repositoryName, false, AbstractRepository.NULL_PROGRESS_MONITOR);
    }
    
    public boolean hasActiveRepository() {
        return repositoryManagerInstance.hasActiveRepository();
    }

    public List<IRepository> getAllRepositories() {
        return repositoryManagerInstance.getAllRepositories();
    }

    public void addBonitaProjectListener(IBonitaProjectListener listener) {
        repositoryManagerInstance.addBonitaProjectListener(listener);
    }

    public IRepository createNewRepository(ProjectMetadata metadata,
            IProgressMonitor monitor) {
        monitor.beginTask(Messages.creatingNewProject, IProgressMonitor.UNKNOWN);
        BonitaStudioLog.info(String.format("Creating new project %s...", metadata.getName()), CommonRepositoryPlugin.PLUGIN_ID);
        getCurrentRepository().close();
        try {
            WorkspaceModifyOperation workspaceModifyOperation = new WorkspaceModifyOperation() {

                @Override
                protected void execute(final IProgressMonitor monitor)
                        throws CoreException, InvocationTargetException, InterruptedException {
                    AbstractRepository currentRepository = getCurrentRepository();
                    if (currentRepository != null && currentRepository.getName().equals(metadata.getName())) {
                        return;
                    } else if (currentRepository != null) {
                        currentRepository.close();
                    }
                    currentRepository =  RepositoryManager.getInstance().getRepository(metadata.getName(), false);
                    if (currentRepository == null) {
                        currentRepository = RepositoryManager.getInstance().createRepository(metadata.getName(), false);
                    }
                    currentRepository.create(metadata, AbstractRepository.NULL_PROGRESS_MONITOR);
                    currentRepository.open(AbstractRepository.NULL_PROGRESS_MONITOR);
                    RepositoryManager.getInstance().setCurrentRepository(currentRepository);
                    final AbstractRepository currentRepo = RepositoryManager.getInstance().getCurrentRepository();
                    BonitaStudioLog.info(String.format("%s project created.", currentRepo.getName()),
                            CommonRepositoryPlugin.PLUGIN_ID);
                }
            };
            workspaceModifyOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
            Display.getDefault().asyncExec(
                    () -> {
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().resetPerspective();
                        PlatformUtil.openIntroIfNoOtherEditorOpen();
                    });
            return getCurrentRepository();
        } catch (final InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return null;
        } 
    }

}
