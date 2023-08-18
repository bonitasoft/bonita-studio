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
package org.bonitasoft.studio.identity.actors.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.bpm.model.connectorconfiguration.util.ConnectorConfigurationAdapterFactory;
import org.bonitasoft.bpm.model.process.util.migration.MigrationHelper;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.filestore.DefinitionConfigurationFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLOptions;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLOptionsImpl;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;

/**
 * @author Romain Bioteau
 */
public class ActorFilterConfRepositoryStore extends AbstractEMFRepositoryStore<DefinitionConfigurationFileStore> {

    private static final String STORE_NAME = "filters-conf";
    private static final Set<String> extensions = new HashSet<String>();
    public static final String CONF_EXT = "connectorconfig";
    static {
        extensions.add(CONF_EXT);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public DefinitionConfigurationFileStore createRepositoryFileStore(final String fileName) {
        return new DefinitionConfigurationFileStore(fileName, this);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    public List<ConnectorConfiguration> getFilterConfigurations() {
        final List<ConnectorConfiguration> result = new ArrayList<ConnectorConfiguration>();
        for (final IRepositoryFileStore child : getChildren()) {
            try {
                result.add((ConnectorConfiguration) child.getContent());
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to add filter configuration", e);
            }
        }
        return result;
    }

    public List<ConnectorConfiguration> getFilterConfigurationsFor(final String defintionId,
            final String definitionVersion) {
        final List<ConnectorConfiguration> result = new ArrayList<ConnectorConfiguration>();
        for (final ConnectorConfiguration child : getFilterConfigurations()) {
            if (child.getDefinitionId().equals(defintionId) && child.getVersion().equals(definitionVersion)) {
                result.add(child);
            }
        }
        return result;
    }

    @Override
    protected String getModelVersion(final Resource resource) {
        // first load the resource
        final Map<Object, Object> loadOptions = new HashMap<>();
        //Ignore unknown features
        loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        final XMLOptions options = new XMLOptionsImpl();
        options.setProcessAnyXML(true);
        loadOptions.put(XMLResource.OPTION_XML_OPTIONS, options);
        try {
            resource.load(loadOptions);
            for (final EObject root : resource.getContents()) {
                if (root instanceof ConnectorConfiguration) {
                    final String version = ((ConnectorConfiguration) root).getModelVersion();
                    if (version != null) {
                        return version;
                    }
                }
            }
        } catch (final IOException e) {
            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        } finally {
            resource.unload();
        }
        return null;
    }

    @Override
    protected InputStream handlePreImport(final String fileName, final InputStream inputStream)
            throws MigrationException, IOException {
        CopyInputStream copyIs = null;
        Resource confResource = null;
        try {
            final InputStream is = super.handlePreImport(fileName, inputStream);
            copyIs = new CopyInputStream(is);
            confResource = getTmpEMFResource("beforeImport.connectorconfig",
                    copyIs.getFile());

            // force migration
            Map<String, MigrationPolicy> migrateOptions = Map.of(MigrationHelper.OPTION_MIGRATION_POLICY,
                    MigrationPolicy.ALWAYS_MIGRATE_POLICY);
            confResource.load(migrateOptions);
            if (!confResource.getContents().isEmpty()) {
                final ConnectorConfiguration configuration = confResource.getContents().stream()
                        .filter(ConnectorConfiguration.class::isInstance)
                        .map(ConnectorConfiguration.class::cast)
                        .findFirst()
                        .orElseThrow(() -> new IOException(
                                "Resource content is invalid. There should be only one ConnectorConfiguration per .connectorconfig file."));

                final String mVersion = configuration.getVersion();
                if (!ModelVersion.CURRENT_DIAGRAM_VERSION.equals(mVersion)) {
                    configuration.setModelVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
                }
                try {
                    confResource.save(Collections.EMPTY_MAP);
                } catch (final IOException e) {
                    BonitaStudioLog.error(e);
                }
                return new FileInputStream(new File(confResource.getURI()
                        .toFileString()));
            }
            return copyIs.getCopy();
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
            return null;
        } finally {
            if (copyIs != null) {
                copyIs.close();
            }
            if (confResource != null) {
                confResource.delete(Collections.emptyMap());
            }
        }
    }

    @Override
    protected void addAdapterFactory(final ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConnectorConfigurationAdapterFactory());
    }
}
