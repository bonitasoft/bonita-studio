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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionAdapterFactory;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionResourceImpl;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionXMLProcessor;
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

public abstract class AbstractDefinitionRepositoryStore<T extends EMFFileStore> extends AbstractEMFRepositoryStore<T>
        implements IDefinitionRepositoryStore<T> {


    @Override
    public List<ConnectorDefinition> getDefinitions() {
        return getChildren().stream()
                .map(this::toConnectorDefinition)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<T> find(ConnectorDefinition definition) {
        return getChildren().stream()
                .filter(fStore -> {
                    try {
                        ConnectorDefinition def = (ConnectorDefinition) fStore.getContent();
                        return Objects.equals(def.getId(), definition.getId())
                                && Objects.equals(def.getVersion(), definition.getVersion());
                    } catch (ReadFileStoreException e) {
                        return false;
                    }
                })
                .findFirst();
    }

    @Override
    public Optional<T> findFirst(Category category) {
        return getChildren().stream()
                .filter(fStore -> {
                    try {
                        ConnectorDefinition def = (ConnectorDefinition) fStore.getContent();
                        return def.getCategory().stream()
                                .anyMatch(c -> Objects.equals(c.getId(), category.getId()));
                    } catch (ReadFileStoreException e) {
                        return false;
                    }
                })
                .findFirst();
    }

    private ConnectorDefinition toConnectorDefinition(IRepositoryFileStore fStore) {
        ConnectorDefinition def;
        try {
            def = (ConnectorDefinition) fStore.getContent();
        } catch (Exception e) {
            def = ConnectorDefinitionFactory.eINSTANCE.createUnloadableConnectorDefinition();
            def.setId(fStore.getName());
            def.setVersion("");
        }
        if (def == null) {
            def = ConnectorDefinitionFactory.eINSTANCE.createUnloadableConnectorDefinition();
            def.setId(fStore.getName());
            def.setVersion("");
        }
        return def;
    }

    @Override
    public ConnectorDefinition getDefinition(final String id, final String version) {
        return getDefinitions().stream()
                .filter(definition -> Objects.equals(definition.getId(), id))
                .filter(definition -> Objects.equals(definition.getVersion(), version))
                .findFirst()
                .orElse(null);
    }

    @Override
    protected void addAdapterFactory(final ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConnectorDefinitionAdapterFactory());
    }

    protected abstract T getDefFileStore(URL url);

    protected abstract Bundle getBundle();

    @Override
    protected void performMigration(final Migrator migrator, final URI resourceURI, final Release release)
            throws MigrationException {
        migrator.setLevel(ValidationLevel.NONE);
        final ResourceSet rSet = migrator.migrateAndLoad(
                Collections.singletonList(resourceURI), release,
                null, AbstractRepository.NULL_PROGRESS_MONITOR);
        if (!rSet.getResources().isEmpty()) {
            FileOutputStream fos = null;
            try {
                final ConnectorDefinitionResourceImpl r = (ConnectorDefinitionResourceImpl) rSet.getResources().get(0);
                final Resource resource = new XMLResourceImpl(resourceURI);
                final DocumentRoot root = ConnectorDefinitionFactory.eINSTANCE.createDocumentRoot();
                final ConnectorDefinition definition = EcoreUtil
                        .copy(((DocumentRoot) r.getContents().get(0)).getConnectorDefinition());
                root.setConnectorDefinition(definition);
                resource.getContents().add(root);
                final Map<String, String> options = new HashMap<>();
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
