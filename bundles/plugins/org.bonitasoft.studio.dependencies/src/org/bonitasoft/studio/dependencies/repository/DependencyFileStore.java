/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.dependencies.repository;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.dependencies.DependenciesPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 */
public class DependencyFileStore extends AbstractFileStore<InputStream> {

    public DependencyFileStore(final String fileName, final DependencyRepositoryStore parentStore) {
        super(fileName, parentStore);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("jar.gif", DependenciesPlugin.getDefault());
    }

    @Override
    protected InputStream doGetContent() throws ReadFileStoreException {
        try {
            return getResource().getContents();
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(final Object content) {
        if (content instanceof InputStream) {
            try {
                if (getResource().exists()) {
                    getResource().setContents((InputStream) content, IResource.FORCE, AbstractRepository.NULL_PROGRESS_MONITOR);
                } else {
                    getResource().create((InputStream) content, IResource.FORCE, AbstractRepository.NULL_PROGRESS_MONITOR);
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
     */
    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
     */
    @Override
    protected void doClose() {

    }

    @Override
    protected void doDelete() {
        try {
            final IResource r = getResource();
            if (r != null && r.exists()) {
                r.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
                final AbstractRepository repository = getRepository();
                final IProject project = repository.getProject();
                project.refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
                if (repository.isBuildEnable()) {
                    project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, AbstractRepository.NULL_PROGRESS_MONITOR);
                }

            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

    @Override
    public DependencyRepositoryStore getParentStore() {
        return (DependencyRepositoryStore) super.getParentStore();
    }

    public boolean existsInRuntimeContainer() {
        final DependencyRepositoryStore depRepositoryStore = getParentStore();
        final Map<String, String> runtimeDependencies = depRepositoryStore.getRuntimeDependencies();
        final String libName = depRepositoryStore.getLibName(getName());
        final String libVersion = depRepositoryStore.getLibVersion(getName());
        if (runtimeDependencies.containsKey(libName)) {
            return runtimeDependencies.get(libName).equals(libVersion); // same libname & same version
        }
        return false;
    }

    public boolean existsInRuntimeContainerWithAnotherVersion() {
        final DependencyRepositoryStore depRepositoryStore = getParentStore();
        final Map<String, String> runtimeDependencies = depRepositoryStore.getRuntimeDependencies();
        final String libName = depRepositoryStore.getLibName(getName());
        final String libVersion = depRepositoryStore.getLibVersion(getName());
        if (runtimeDependencies.containsKey(libName)) {
            return !runtimeDependencies.get(libName).equals(libVersion); // same libname & !same version
        }
        return false;
    }

    public File getFile() {
        return getResource().getLocation().toFile();
    }
    
    public List<File> getTransitiveDependencies(){
        return Collections.emptyList();
    }

}
