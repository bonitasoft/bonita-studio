/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.model.implementation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.util.ConnectorImplementationAdapterFactory;
import org.bonitasoft.studio.connector.model.implementation.util.ConnectorImplementationResourceImpl;
import org.bonitasoft.studio.connector.model.implementation.util.ConnectorImplementationXMLProcessor;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.ValidationLevel;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;

/**
 * @author Romain Bioteau
 * 
 */
public abstract class AbstractConnectorImplRepositoryStore<T extends EMFFileStore> extends AbstractEMFRepositoryStore<T> implements IRepositoryStore<T>,
        IImplementationRepositoryStore {

    @Override
    protected void addAdapterFactory(ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConnectorImplementationAdapterFactory());
    }

    @Override
    public ConnectorImplementation getImplementation(String id, String version) {
        for (ConnectorImplementation impl : getImplementations()) {
            if (impl.getImplementationId().equals(id) && impl.getImplementationVersion().equals(version)) {
                return impl;
            }
        }
        return null;
    }

    @Override
    public List<ConnectorImplementation> getImplementations() {
        List<ConnectorImplementation> result = new ArrayList<ConnectorImplementation>();
        for (IRepositoryFileStore fileStore : getChildren()) {
            ConnectorImplementation def = (ConnectorImplementation) fileStore.getContent();
            result.add(def);
        }
        return result;
    }

    @Override
    public List<ConnectorImplementation> getImplementations(String definitionId, String definitionVersion) {
        List<ConnectorImplementation> implementations = new ArrayList<ConnectorImplementation>();
        for (ConnectorImplementation impl : getImplementations()) {
            if (impl != null && definitionId.equals(impl.getDefinitionId())
                    && definitionVersion.equals(impl.getDefinitionVersion())) {
                implementations.add(impl);
            }
        }
        return implementations;

    }

    @Override
    public IRepositoryFileStore getImplementationFileStore(String implId, String implVersion) {
        for (IRepositoryFileStore implStore : getChildren()) {
            ConnectorImplementation impl = (ConnectorImplementation) implStore.getContent();
            if (impl != null && implId.equals(impl.getImplementationId())
                    && implVersion.equals(impl.getImplementationVersion())) {
                return implStore;
            }
        }
        return null;
    }

    @Override
    protected void performMigration(Migrator migrator, URI resourceURI,
            Release release) throws MigrationException {
        migrator.setLevel(ValidationLevel.NONE);
        ResourceSet rSet = migrator.migrateAndLoad(
                Collections.singletonList(resourceURI), release,
                null, Repository.NULL_PROGRESS_MONITOR);
        if (!rSet.getResources().isEmpty()) {
            FileOutputStream fos = null;
            try {
                ConnectorImplementationResourceImpl r = (ConnectorImplementationResourceImpl) rSet.getResources().get(0);
                Resource resource = new XMLResourceImpl(resourceURI);
                org.bonitasoft.studio.connector.model.implementation.DocumentRoot root = ConnectorImplementationFactory.eINSTANCE.createDocumentRoot();
                final ConnectorImplementation definition = EcoreUtil.copy(((org.bonitasoft.studio.connector.model.implementation.DocumentRoot) r.getContents()
                        .get(0)).getConnectorImplementation());
                root.setConnectorImplementation(definition);
                resource.getContents().add(root);
                Map<String, String> options = new HashMap<String, String>();
                options.put(XMLResource.OPTION_ENCODING, "UTF-8");
                options.put(XMLResource.OPTION_XML_VERSION, "1.0");
                File target = new File(resourceURI.toFileString());
                fos = new FileOutputStream(target);
                new ConnectorImplementationXMLProcessor().save(fos, resource, options);
            } catch (Exception e) {
                BonitaStudioLog.error(e);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }

    }

    @Override
    protected T doImportInputStream(String fileName, InputStream inputStream) {
        T fStore = super.doImportInputStream(fileName, inputStream);
        cleanJarDependency(fStore, RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class), getSourceRepositoryStore());
        return fStore;

    }

    protected abstract IRepositoryStore<? extends IRepositoryFileStore> getSourceRepositoryStore();

    protected void cleanJarDependency(IRepositoryFileStore fStore, IRepositoryStore<? extends IRepositoryFileStore> dependencyRepositoryStore,
            IRepositoryStore<? extends IRepositoryFileStore> sourceRepositoryStore) {
        ConnectorImplementation content = (ConnectorImplementation) fStore.getContent();
        if (hasSources(content, sourceRepositoryStore)) {
            JarDependencies jarDependencies = content.getJarDependencies();
            if (jarDependencies != null) {
                for (String dep : jarDependencies.getJarDependency()) {
                    String depJarName = NamingUtils.toConnectorImplementationFilename(content.getImplementationId(), content.getImplementationVersion(), false)
                            + ".jar";
                    if (dep.equals(depJarName)) {
                        IRepositoryFileStore dependencyFileStore = dependencyRepositoryStore.getChild(depJarName);
                        if (dependencyFileStore != null) {
                            dependencyFileStore.delete();
                        }
                    }
                }
            }
        }
    }

    private boolean hasSources(ConnectorImplementation content, IRepositoryStore<? extends IRepositoryFileStore> sourceRepositoryStore) {
        return sourceRepositoryStore.getChild(content.getImplementationClassname()) != null;
    }
}
