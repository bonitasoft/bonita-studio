/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionAdapterFactory;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionResourceImpl;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionXMLProcessor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.osgi.framework.Bundle;

public abstract class AbstractDefinitionRepositoryStore<T extends EMFFileStore> extends AbstractEMFRepositoryStore<T> implements IDefinitionRepositoryStore {

    private final List<T> cachedFileStore = new ArrayList<T>();
    private final List<IConnectorDefinitionFilter> filters = new ArrayList<IConnectorDefinitionFilter>();

    public AbstractDefinitionRepositoryStore() {
        super();
        for (final IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.connectors.connectorDefFilter")) {
            try {
                filters.add((IConnectorDefinitionFilter) elem.createExecutableExtension("class"));
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public List<ConnectorDefinition> getDefinitions() {
        final List<ConnectorDefinition> result = new ArrayList<ConnectorDefinition>();
        for (final IRepositoryFileStore fileStore : getChildren()) {
            ConnectorDefinition def;
            try {
                def = (ConnectorDefinition) fileStore.getContent();
            } catch (final Exception e) {
                def = ConnectorDefinitionFactory.eINSTANCE.createUnloadableConnectorDefinition();
                def.setId(fileStore.getName());
                def.setVersion("");
            }
            if (def == null) {
                def = ConnectorDefinitionFactory.eINSTANCE.createUnloadableConnectorDefinition();
                def.setId(fileStore.getName());
                def.setVersion("");
            }
            result.add(def);
        }
        return result;
    }

    @Override
    public ConnectorDefinition getDefinition(final String id, final String version) {
        for (final ConnectorDefinition def : getDefinitions()) {
            if (def.getId().equals(id) && def.getVersion().equals(version)) {
                return def;
            }
        }
        return null;
    }

    @Override
    public ConnectorDefinition getDefinition(final String id, final String version, final Collection<ConnectorDefinition> existingDefinitions) {
        for (final ConnectorDefinition def : existingDefinitions) {
            if (def.getId().equals(id) && def.getVersion().equals(version)) {
                return def;
            }
        }
        return null;
    }

    @Override
    public List<T> getChildren() {
        final List<T> result = super.getChildren();
        if (cachedFileStore.isEmpty()) {
            final Enumeration<URL> connectorDefs = getBundle().findEntries(getName(), "*.def", false);
            if (connectorDefs != null) {
                while (connectorDefs.hasMoreElements()) {
                    final URL url = connectorDefs.nextElement();
                    final String[] segments = url.getFile().split("/");
                    final String fileName = segments[segments.length - 1];
                    if (fileName.lastIndexOf(".") != -1) {
                        final String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                        if (getCompatibleExtensions().contains(extension)) {
                            final T defFileStore = getDefFileStore(url);
                            boolean filtered = false;
                            for (final IConnectorDefinitionFilter filter : filters) {
                                if (filter.filter((ConnectorDefinition) defFileStore.getContent())) {
                                    filtered = true;
                                }
                            }
                            if (!filtered) {
                                cachedFileStore.add(defFileStore);
                                result.add(defFileStore);
                            }
                        }
                    }
                }
            }
        } else {
            result.addAll(cachedFileStore);
        }
        return result;
    }

    @Override
    public T getChild(final String fileName) {
        final T file = super.getChild(fileName);
        if (file == null) {
            final URL url = getBundle().getResource(getName() + "/" + fileName);
            if (url != null) {
                final T defFileStore = getDefFileStore(url);
                if (defFileStore != null) {
                    for (final IConnectorDefinitionFilter filter : filters) {
                        if (filter.filter((ConnectorDefinition) defFileStore.getContent())) {
                            return null;
                        }
                    }
                }
                return defFileStore;
            } else {
                return null;
            }
        } else {
            return file;
        }

    }

    public void clearCachedFileStore() {
        cachedFileStore.clear();
    }

    @Override
    protected void addAdapterFactory(final ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConnectorDefinitionAdapterFactory());
    }

    protected abstract T getDefFileStore(URL url);

    protected abstract Bundle getBundle();

    @Override
    protected void performMigration(final Migrator migrator, final URI resourceURI, final Release release) throws MigrationException {
        migrator.setLevel(ValidationLevel.NONE);
        final ResourceSet rSet = migrator.migrateAndLoad(
                Collections.singletonList(resourceURI), release,
                null, Repository.NULL_PROGRESS_MONITOR);
        if (!rSet.getResources().isEmpty()) {
            FileOutputStream fos = null;
            try {
                final ConnectorDefinitionResourceImpl r = (ConnectorDefinitionResourceImpl) rSet.getResources().get(0);
                final Resource resource = new XMLResourceImpl(resourceURI);
                final DocumentRoot root = ConnectorDefinitionFactory.eINSTANCE.createDocumentRoot();
                final ConnectorDefinition definition = EcoreUtil.copy(((DocumentRoot) r.getContents().get(0)).getConnectorDefinition());
                root.setConnectorDefinition(definition);
                resource.getContents().add(root);
                final Map<String, String> options = new HashMap<String, String>();
                options.put(XMLResource.OPTION_ENCODING, "UTF-8");
                options.put(XMLResource.OPTION_XML_VERSION, "1.0");
                final File target = new File(resourceURI.toFileString());
                fos = new FileOutputStream(target);
                new ConnectorDefinitionXMLProcessor().save(fos, resource, options);
            } catch (final Exception e) {
                BonitaStudioLog.error(e, "org.bonitasoft.studio.connectors.model.edit");
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e, "org.bonitasoft.studio.connectors.model.edit");
                    }
                }
            }
        }
    }

}
