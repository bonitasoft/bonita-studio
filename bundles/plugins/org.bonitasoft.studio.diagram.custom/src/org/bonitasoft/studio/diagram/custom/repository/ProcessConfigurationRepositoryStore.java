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
package org.bonitasoft.studio.diagram.custom.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.util.ConfigurationAdapterFactory;
import org.bonitasoft.bpm.model.process.util.migration.MigrationHelper;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.studio.common.editingdomain.BonitaEditingDomainUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLOptions;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLOptionsImpl;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;

/**
 * @author Romain Bioteau
 */
public class ProcessConfigurationRepositoryStore extends AbstractEMFRepositoryStore<ProcessConfigurationFileStore> {

    public static final String STORE_NAME = "process_configurations";
    private static final Set<String> extensions = new HashSet<>();
    public static final String CONF_EXT = "conf";

    static {
        extensions.add(CONF_EXT);
    }

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public boolean canBeExported() {
        return false;
    }

    @Override
    public ProcessConfigurationFileStore createRepositoryFileStore(final String fileName) {
        return new ProcessConfigurationFileStore(fileName, this);
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    public boolean canBeShared() {
        return false;
    }

    @Override
    protected ProcessConfigurationFileStore doImportInputStream(final String fileName, final InputStream inputStream) {
        final IFile file = getResource().getFile(fileName);
        try {
            if (file.exists()) {
                file.setContents(inputStream, true, false, AbstractRepository.NULL_PROGRESS_MONITOR);
            } else {
                final File f = file.getLocation().toFile();
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                    refresh();
                }
                file.create(inputStream, true, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return createRepositoryFileStore(fileName);
    }

    @Override
    protected String getModelVersion(final Resource resource) {
        // first load the resource
        final Map<Object, Object> loadOptions = new HashMap<>();
        //Ignore unknown features
        loadOptions.put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        final XMLOptions options = new XMLOptionsImpl();
        options.setProcessAnyXML(true);
        loadOptions.put(XMLResource.OPTION_XML_OPTIONS, options);
        try {
            resource.load(loadOptions);
            for (final EObject root : resource.getContents()) {
                if (root instanceof Configuration) {
                    final String version = ((Configuration) root).getVersion();
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
            confResource = getTmpEMFResource("beforeImport.conf", copyIs.getFile());

            // force migration
            Map<String, MigrationPolicy> migrateOptions = Map.of(MigrationHelper.OPTION_MIGRATION_POLICY,
                    MigrationPolicy.ALWAYS_MIGRATE_POLICY);
            confResource.load(migrateOptions);
            if (!confResource.getContents().isEmpty()) {
                final Optional<Configuration> configuration = confResource.getContents().stream()
                        .filter(Configuration.class::isInstance)
                        .map(Configuration.class::cast)
                        .findFirst();
                if (!configuration.isPresent()) {
                    throw new IOException(
                            "Resource content is invalid. There should be only one Configuration per .conf file.");
                }
                return new FileInputStream(new File(confResource.getURI()
                        .toFileString()));
            }
            return copyIs.getCopy();
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
            return null;
        } finally {
            BonitaEditingDomainUtil.cleanEditingDomainRegistry();
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
        adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory());
    }

    @Override
    public int getImportOrder() {
        return 10;
    }
}
