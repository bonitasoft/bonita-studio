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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Creatable;

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
    public RepositoryAccessor init() {
        repositoryManagerInstance = RepositoryManager.getInstance();
        return this;
    }

    public <T extends IRepositoryStore<?>> T getRepositoryStore(final Class<T> storeClass) {
        return repositoryManagerInstance.getRepositoryStore(storeClass);
    }

    public Repository getCurrentRepository() {
        return repositoryManagerInstance.getCurrentRepository();
    }

    public IRepository start(final IProgressMonitor monitor) {
        Repository repository = getCurrentRepository();
        if (!repository.exists()) {
            repository.create(monitor);
        }
        return repository.open(monitor);
    }

    public IWorkspace getWorkspace() {
        return ResourcesPlugin.getWorkspace();
    }

    public Repository getRepository(String targetRepository) {
        return repositoryManagerInstance.getRepository(targetRepository);
    }

    public void setRepository(final String repositoryName) {
        repositoryManagerInstance.setRepository(repositoryName, false, Repository.NULL_PROGRESS_MONITOR);
    }

    public List<IRepository> getAllRepositories() {
        return repositoryManagerInstance.getAllRepositories();
    }

    public void addBonitaProjectListener(IBonitaProjectListener listener) {
        repositoryManagerInstance.addBonitaProjectListener(listener);
    }

}
