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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.bpm.connector.model.definition.DocumentRoot;
import org.bonitasoft.bpm.connector.model.definition.UnloadableConnectorDefinition;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.provider.BundleDefinitionImageResourceLoader;
import org.bonitasoft.studio.common.repository.provider.BundleResourceLoader;
import org.bonitasoft.studio.common.repository.provider.DefinitionImageResourceLoader;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceLoaderProvider;
import org.bonitasoft.studio.common.repository.provider.OSGIBundleResourceLoader;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.ui.IWorkbenchPart;
import org.osgi.framework.Bundle;

public abstract class AbstractDefFileStore extends EMFFileStore<ConnectorDefinition>
        implements DefinitionResourceLoaderProvider {

    protected AbstractDefFileStore(final String fileName,
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
                final Map<String, Object> options = new HashMap<>();
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
    	
    }

    private void rebuildConnectorRegistry() {
        ((IDefinitionRepositoryStore<IRepositoryFileStore<?>>) store).getResourceProvider()
                .loadDefinitionsCategories(null);
    }

    @Override
    public BundleResourceLoader getBundleResourceLoader() {
        return new OSGIBundleResourceLoader(getEMFResource(), getBundle(), store);
    }

    @Override
    public DefinitionImageResourceLoader getDefinitionImageResourceLoader() {
        return new BundleDefinitionImageResourceLoader(getBundle(), getParentStore());
    }

    protected abstract Bundle getBundle();


}
