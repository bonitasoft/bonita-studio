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

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.bpm.connector.model.definition.DocumentRoot;
import org.bonitasoft.bpm.connector.model.definition.UnloadableConnectorDefinition;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.BundleDefinitionImageResourceLoader;
import org.bonitasoft.studio.common.repository.provider.BundleResourceLoader;
import org.bonitasoft.studio.common.repository.provider.DefinitionImageResourceLoader;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceLoaderProvider;
import org.bonitasoft.studio.common.repository.provider.OSGIBundleResourceLoader;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osgi.framework.Bundle;

public abstract class AbstractDefFileStore extends AbstractFromArtifactFileStore<ConnectorDefinition>
        implements DefinitionResourceLoaderProvider {

    protected AbstractDefFileStore(final String fileName,
            final AbstractEMFRepositoryStore<? extends AbstractDefFileStore> store) {
        super(fileName, store, ConnectorDefinition.class);
    }

    protected AbstractDefFileStore(final String projectName, final String entryInOutputDirectory,
            final AbstractEMFRepositoryStore<? extends AbstractDefFileStore> store) {
        super(projectName, entryInOutputDirectory, store, ConnectorDefinition.class);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.model.definition.AbstractFromArtifactFileStore#extractRootContent(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected ConnectorDefinition extractRootContent(EObject documentRoot) {
        return ((DocumentRoot) documentRoot).getConnectorDefinition();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.model.definition.AbstractFromArtifactFileStore#wrapContentWithRoot(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected EObject wrapContentWithRoot(ConnectorDefinition content) {
        final DocumentRoot root = ConnectorDefinitionFactory.eINSTANCE.createDocumentRoot();
        root.setConnectorDefinition(EcoreUtil.copy(content));
        return root;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.model.definition.AbstractFromArtifactFileStore#makeContentTemplate()
     */
    @Override
    protected ConnectorDefinition makeUnloadableContent() {
        final UnloadableConnectorDefinition connectorDefinition = ConnectorDefinitionFactory.eINSTANCE
                .createUnloadableConnectorDefinition();
        connectorDefinition.setId(getName());
        connectorDefinition.setVersion("");
        return connectorDefinition;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.model.definition.AbstractFromArtifactFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(Object content) {
        super.doSave(content);
        rebuildConnectorRegistry();
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
