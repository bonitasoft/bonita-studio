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
package org.bonitasoft.studio.connectors.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.filestore.DefinitionConfigurationFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.util.ConnectorConfigurationAdapterFactory;
import org.bonitasoft.studio.model.connectorconfiguration.util.ConnectorConfigurationResourceFactoryImpl;
import org.bonitasoft.studio.model.connectorconfiguration.util.ConnectorConfigurationResourceImpl;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLOptions;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLOptionsImpl;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;

import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class ConnectorConfRepositoryStore extends AbstractEMFRepositoryStore<DefinitionConfigurationFileStore> {

    private static final String STORE_NAME = "connectors-conf";
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
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.connectorConfRepositoryName;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("conf.png", ConnectorPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    public List<ConnectorConfiguration> getConnectorConfigurations() {
        final List<ConnectorConfiguration> result = new ArrayList<ConnectorConfiguration>();
        for (final IRepositoryFileStore child : getChildren()) {
            try {
                result.add((ConnectorConfiguration) child.getContent());
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed read connector configuration content", e);
            }
        }
        return result;
    }

    public List<ConnectorConfiguration> getConnectorConfigurationsFor(final String defintionId, final String definitionVersion) {
        final List<ConnectorConfiguration> result = new ArrayList<ConnectorConfiguration>();
        for (final ConnectorConfiguration child : getConnectorConfigurations()) {
            if (child.getDefinitionId().equals(defintionId) && child.getVersion().equals(definitionVersion)) {
                result.add(child);
            }
        }
        return result;
    }

    @Override
    protected void addAdapterFactory(final ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConnectorConfigurationAdapterFactory());
    }

    @Override
    protected Release getRelease(final Migrator targetMigrator, final Resource resource) {
        final Map<Object, Object> loadOptions = new HashMap<Object, Object>();
        //Ignore unknown features
        loadOptions.put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        final XMLOptions options = new XMLOptionsImpl();
        options.setProcessAnyXML(true);
        loadOptions.put(XMLResource.OPTION_XML_OPTIONS, options);
        try {
            resource.load(loadOptions);
        } catch (final IOException e) {
            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        }
        final String modelVersion = getModelVersion(resource);
        for (final Release release : targetMigrator.getReleases()) {
            if (release.getLabel().equals(modelVersion)) {
                return release;
            }
        }
        return targetMigrator.getReleases().iterator().next(); //First release of all time
    }

    protected String getModelVersion(final Resource resource) {
        final String modelVersion = ModelVersion.VERSION_6_0_0_ALPHA;
        for (final EObject root : resource.getContents()) {
            if (root instanceof ConnectorConfiguration) {
                final String version = ((ConnectorConfiguration) root).getModelVersion();
                if (version != null) {
                    return version;
                }
            }
        }
        return modelVersion;
    }

    @Override
    protected Resource getTmpEMFResource(final String fileName, final File originalFile) throws IOException {
        final File tmpFile = File.createTempFile("tmp", fileName,
                ProjectUtil.getBonitaStudioWorkFolder());
        Files.copy(originalFile, tmpFile);
        return new ConnectorConfigurationResourceFactoryImpl()
                .createResource(URI.createFileURI(tmpFile.getAbsolutePath()));
    }

    @Override
    protected InputStream handlePreImport(final String fileName, final InputStream inputStream) throws MigrationException, IOException {
        CopyInputStream copyIs = null;
        try {
            final InputStream is = super.handlePreImport(fileName, inputStream);
            copyIs = new CopyInputStream(is);
            final ConnectorConfigurationResourceImpl r = (ConnectorConfigurationResourceImpl) getTmpEMFResource("beforeImport",
                    copyIs.getFile());
            try {
                r.load(r.getDefaultLoadOptions());
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
            if (!r.getContents().isEmpty()) {
                final ConnectorConfiguration configuration = (ConnectorConfiguration) r.getContents()
                        .get(0);
                if (configuration != null) {
                    final String mVersion = configuration.getVersion();
                    if (!ModelVersion.CURRENT_DIAGRAM_VERSION.equals(mVersion)) {
                        configuration.setModelVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
                    }
                    try {
                        r.save(r.getDefaultSaveOptions());
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e);
                    }
                    try {
                        return new FileInputStream(new File(r.getURI()
                                .toFileString()));
                    } catch (final FileNotFoundException e) {
                        BonitaStudioLog.error(e);
                    } finally {
                        copyIs.close();
                        try {
                            r.delete(Collections.EMPTY_MAP);
                        } catch (final IOException e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                } else {
                    return null;
                }
            }
            return copyIs.getCopy();
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
            return null;
        } finally {
            if (copyIs != null) {
                copyIs.close();
            }
        }
    }

}
