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
package org.bonitasoft.studio.common.repository.filestore;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * @author Romain Bioteau
 */
public abstract class EMFFileStore<T extends EObject> extends AbstractFileStore<T> {

    protected Resource eResource;

    public EMFFileStore(final String fileName, final IRepositoryStore<? extends EMFFileStore<T>> store) {
        super(fileName, store);
    }

    protected Resource doCreateEMFResource() {
        final URI uri = getResourceURI();
        try {
            final EditingDomain editingDomain = getParentStore().getEditingDomain(uri);
            final ResourceSet resourceSet = editingDomain.getResourceSet();
            if (getResource().exists()) {
                return resourceSet.getResource(uri, true);
            } else {
                return resourceSet.createResource(uri);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    protected URI getResourceURI() {
        return URI.createFileURI(getFileStorePath());
    }

    protected String getFileStorePath() {
        return getParentStore().getResource().getLocation().toFile().getAbsolutePath() + File.separatorChar + getName();
    }

    @Override
    protected T doGetContent() throws ReadFileStoreException {
        final Resource emfResource = getEMFResource();
        doLoad(emfResource);
        if (emfResource != null && !emfResource.getContents().isEmpty()) {
            return (T) emfResource.getContents().get(0);
        }
        return null;
    }

    protected void doLoad(Resource eResource) throws ReadFileStoreException {
        if (eResource != null) {
            final boolean loaded = eResource.isLoaded();
            if (!loaded) {
                try {
                    final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(eResource);
                    if (editingDomain != null) {
                        editingDomain.getResourceSet().getResource(eResource.getURI(), true);
                    } else {
                        eResource.load(Collections.emptyMap());
                    }
                } catch (final IOException | RuntimeException e) {
                    throw new ReadFileStoreException("Failed to load EMF Resource", e);
                }
            }
        }
    }

    @Override
    protected void doDelete() {
        final Resource eResource = getEMFResource();
        doClose();
        if (eResource != null) {
            try {
                eResource.delete(Collections.emptyMap());
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    protected void doClose() {
        unloadEMFResource();
    }

    @Override
    protected void partClosed() {
        unloadEMFResource();
    }

    private void unloadEMFResource() {
        if (eResource != null && eResource.isLoaded()) {
            eResource.unload();
        }
    }

    public Resource getEMFResource() {
        if (eResource == null) {
            eResource = doCreateEMFResource();
        }
        return eResource;
    }

    public AdapterFactoryLabelProvider getLabelProvider() {
        return getParentStore().getLabelProvider();
    }

    @Override
    public AbstractEMFRepositoryStore<? extends IRepositoryFileStore<T>> getParentStore() {
        return (AbstractEMFRepositoryStore<? extends IRepositoryFileStore<T>>) super.getParentStore();
    }

    @Override
    public IFile getResource() {
    	return (IFile) super.getResource();
    }

}
