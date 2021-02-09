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
package org.bonitasoft.studio.connector.model.definition;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.provider.BundleDefinitionImageResourceLoader;
import org.bonitasoft.studio.common.repository.provider.BundleResourceLoader;
import org.bonitasoft.studio.common.repository.provider.DefinitionImageResourceLoader;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceLoaderProvider;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.common.repository.provider.OSGIBundleResourceLoader;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 */
public abstract class AbstractDefFileStore extends EMFFileStore<ConnectorDefinition> implements DefinitionResourceLoaderProvider {

    public AbstractDefFileStore(final String fileName,
            final AbstractEMFRepositoryStore<? extends AbstractDefFileStore> store) {
        super(fileName, store);
    }

    @Override
    protected ConnectorDefinition doGetContent() throws ReadFileStoreException {
        try {
            final DocumentRoot root = (DocumentRoot) super.doGetContent();
            if (root == null) {
                return null;
            }
            return root.getConnectorDefinition();
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            final UnloadableConnectorDefinition connectorDefinition = ConnectorDefinitionFactory.eINSTANCE
                    .createUnloadableConnectorDefinition();
            connectorDefinition.setId(getName());
            connectorDefinition.setVersion("");
            return connectorDefinition;
        }
    }

    @Override
    protected void doSave(final Object content) {
        if (content instanceof ConnectorDefinition) {
            final Resource emfResource = getEMFResource();
            emfResource.getContents().clear();
            final DocumentRoot root = ConnectorDefinitionFactory.eINSTANCE.createDocumentRoot();
            root.setConnectorDefinition((ConnectorDefinition) EcoreUtil.copy((EObject) content));
            emfResource.getContents().add(root);
            try {
                final Map<String, Object> options = new HashMap<String, Object>();
                options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
                options.put(XMLResource.OPTION_ENCODING, "UTF-8");
                emfResource.save(options);
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
            rebuildConnectorRegistry();
        }
    }

    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    @Override
    public void delete() {
        try {
            for (IResource localeResource : retrieveLocaleResources(getContent())) {
                try {
                    localeResource.delete(true, new NullProgressMonitor());
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.warning(e.getMessage(), "org.bonitasoft.studio.connectors.model.edit");
        }
        super.delete();
        rebuildConnectorRegistry();
    }

    private void rebuildConnectorRegistry() {
        ((IDefinitionRepositoryStore<IRepositoryFileStore<?>>) store).getResourceProvider().loadDefinitionsCategories(null);
    }
    
    @Override
    public BundleResourceLoader getBundleResourceLoader() {
        return new OSGIBundleResourceLoader(getEMFResource(), getBundle(), store);
    }
    
    @Override
    public DefinitionImageResourceLoader getDefinitionImageResourceLoader() {
         return new BundleDefinitionImageResourceLoader(getBundle(), getParentStore());
    }
    
    @Override
    public Image getIcon() {
        try {
            return getDefinitionImageResourceLoader().getIcon(getContent());
        } catch (ReadFileStoreException e) {
            return null;
        }
    }

    protected abstract Bundle getBundle();

    @Override
    public Set<IResource> getRelatedResources() {
        final Set<IResource> result = super.getRelatedResources();
        ConnectorDefinition def;
        try {
            def = getContent();
        } catch (ReadFileStoreException e) {
            return result;
        }
        if (def.getIcon() != null) {
            final IFile iconFile = getParentStore().getResource().getFile(Path.fromOSString(def.getIcon()));
            if (iconFile != null && iconFile.exists()) {
                result.add(iconFile);
            }
        }
        for (final Category c : def.getCategory()) {
            if (c.getIcon() != null) {
                final IFile iconFile = getParentStore().getResource().getFile(Path.fromOSString(c.getIcon()));
                if (iconFile != null && iconFile.exists()) {
                    if (!result.contains(iconFile)) {
                        result.add(iconFile);
                    }
                }
            }
        }
        result.addAll(retrieveLocaleResources(def));
        return result;
    }

    private Set<IResource> retrieveLocaleResources(final ConnectorDefinition def) {
        final Set<IResource> localeResources = new HashSet<IResource>();
        final DefinitionResourceProvider resourceProvider = DefinitionResourceProvider.getInstance(getParentStore(),
                getBundle());
        final List<File> propertiesFile = resourceProvider.getExistingLocalesResource(def);
        for (final File propertyFile : propertiesFile) {
            final String newFilename = propertyFile.getName();
            try {
                final IFile f = getParentStore().getResource().getFile(newFilename);
                if (f != null && f.exists()) {
                    localeResources.add(f);
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        return localeResources;
    }

}
