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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.util.ConfigurationAdapterFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
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

/**
 * @author Romain Bioteau
 */
public class ProcessConfigurationRepositoryStore extends AbstractEMFRepositoryStore<ProcessConfigurationFileStore> {

    public static final String STORE_NAME = "process_configurations";
    private static final Set<String> extensions = new HashSet<String>();
    public static final String CONF_EXT = "conf";
    private Synchronizer synchronizer;

    static {
        extensions.add(CONF_EXT);
    }

    @Override
    public void createRepositoryStore(IRepository repository) {
        super.createRepositoryStore(repository);
        synchronizer = loadSynchronizer();
    }
    
    private Synchronizer loadSynchronizer() {
        for (IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements("org.bonitasoft.studio.diagram.custom.configurationSynchronizer")) {
            try {
                return (Synchronizer) elem.createExecutableExtension("class");
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
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
    public String getDisplayName() {
        return Messages.configurations;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.configuration);
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
                String fileNameLabel = fileName;
                final String processUUID = fileName.substring(0, fileName.lastIndexOf("."));
                final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                        .getRepositoryStore(DiagramRepositoryStore.class);
                final AbstractProcess process = diagramStore.getProcessByUUID(processUUID);
                if (process != null) {
                    fileNameLabel = Messages.bind(Messages.localConfigurationFor,
                            process.getName() + " (" + process.getVersion() + ")");
                }
                if (FileActionDialog.overwriteQuestion(fileNameLabel)) {
                    file.setContents(inputStream, true, false, Repository.NULL_PROGRESS_MONITOR);
                } else {
                    return createRepositoryFileStore(fileName);
                }
            } else {
                final File f = file.getLocation().toFile();
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                    refresh();
                }
                file.create(inputStream, true, Repository.NULL_PROGRESS_MONITOR);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return createRepositoryFileStore(fileName);
    }

    @Override
    protected Release getRelease(final Migrator targetMigrator, final Resource resource) {
        final Map<Object, Object> loadOptions = new HashMap<Object, Object>();
        //Ignore unknown features
        loadOptions.put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        final XMLOptions options = new XMLOptionsImpl();
        options.setProcessAnyXML(true);
        loadOptions.put(XMLResource.OPTION_XML_OPTIONS, options);
        String modelVersion = null;
        try {
            resource.load(loadOptions);
            modelVersion = getModelVersion(resource);
        } catch (final IOException e) {
            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        } finally {
            resource.unload();
        }
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
            if (root instanceof Configuration) {
                final String version = ((Configuration) root).getVersion();
                if (version != null) {
                    return version;
                }
            }
        }
        return modelVersion;
    }

    @Override
    protected InputStream handlePreImport(final String fileName, final InputStream inputStream)
            throws MigrationException, IOException {
        CopyInputStream copyIs = null;
        try {
            final InputStream is = super.handlePreImport(fileName, inputStream);
            copyIs = new CopyInputStream(is);
            final Resource r = getTmpEMFResource("beforeImport.conf",
                    copyIs.getFile());
            try {
                r.load(Collections.EMPTY_MAP);
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
            if (!r.getContents().isEmpty()) {
                final Configuration configuration = (Configuration) r.getContents()
                        .get(0);
                if (configuration != null) {
                    final String mVersion = configuration.getVersion();
                    if (!ModelVersion.CURRENT_VERSION.equals(mVersion)) {
                        configuration.setVersion(ModelVersion.CURRENT_VERSION);
                    }
                    DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
                    AbstractProcess process = diagramStore.getProcessByUUID(fileName.substring(0, fileName.length() - 5));
                    if(process != null) {
                        synchronizer.synchronize((Pool) process, configuration);
                    }
                    try {
                        r.save(Collections.EMPTY_MAP);
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

    @Override
    protected void addAdapterFactory(final ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory());
    }
}
