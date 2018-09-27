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
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public abstract class EMFFileStore extends AbstractFileStore {

    protected Resource eResource;

    public EMFFileStore(final String fileName, final IRepositoryStore<? extends EMFFileStore> store) {
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

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getContent()
     */
    @Override
    public synchronized EObject getContent() {
        final Resource eResource = getEMFResource();
        doLoad(eResource);
        if (eResource != null && !eResource.getContents().isEmpty()) {
            return eResource.getContents().get(0);
        }
        return null;
    }

    protected void doLoad(final Resource eResource) {
        if (eResource != null) {
            final boolean loaded = eResource.isLoaded();
            if (!loaded) {
                try {
                    final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(eResource);
                    if (editingDomain != null) {
                        editingDomain.runExclusive(eResourceLoader(eResource));
                    } else {
                        eResource.load(Collections.EMPTY_MAP);
                    }
                } catch (final IOException e) {
                    BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
                } catch (final InterruptedException e) {
                    BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
                }
            }
        }
    }

    private Runnable eResourceLoader(final Resource resource) {
        return new Runnable() {

            @Override
            public void run() {
                try {
                    resource.load(Collections.EMPTY_MAP);
                } catch (final IOException e) {
                    BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
                }
            }
        };
    }

    @Override
    protected void doDelete() {
        final Resource eResource = getEMFResource();
        doClose();
        try {
            getResource().delete(true, Repository.NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        if (eResource != null) {
            final Runnable deleteRunnable = new Runnable() {

                @Override
                public void run() {
                    try {
                        eResource.delete(Collections.EMPTY_MAP);
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e);
                    }

                }
            };
            final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(eResource);
            if (editingDomain != null) {
                try {
                    editingDomain.runExclusive(deleteRunnable);
                } catch (final InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            } else {
                deleteRunnable.run();
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
    public String getDisplayName() {
        return getLabelProvider().getText(getContent());
    }

    @Override
    public AbstractEMFRepositoryStore<? extends IRepositoryFileStore> getParentStore() {
        return (AbstractEMFRepositoryStore<? extends IRepositoryFileStore>) super.getParentStore();
    }

    @Override
    public Image getIcon() {
        return getLabelProvider().getImage(getContent());
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

}
