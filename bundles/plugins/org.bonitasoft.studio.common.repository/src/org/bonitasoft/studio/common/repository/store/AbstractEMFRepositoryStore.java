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
package org.bonitasoft.studio.common.repository.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.MigratorRegistry;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractEMFRepositoryStore<T extends EMFFileStore<?>>
        extends AbstractRepositoryStore<T> {

    private AdapterFactoryLabelProvider labelProvider;

    private final ComposedAdapterFactory adapterFactory;

    private Migrator migrator;

    public AbstractEMFRepositoryStore() {
        super();
        adapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory
                .addAdapterFactory(new ResourceItemProviderAdapterFactory());
        adapterFactory
                .addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        addAdapterFactory(adapterFactory);

    }

    /**
     * public for test purpose
     *
     * @return
     */
    public Migrator initializeMigrator() {
        if (migrator == null) {
            try {
                migrator = new SingleResourceMigrator();
            } catch (final MigrationException e) {
                BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
            }
        }
        return migrator;
    }


    public AdapterFactoryLabelProvider getLabelProvider() {
        if (labelProvider != null) {
            labelProvider.dispose();
        }
        labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
        return labelProvider;
    }

    protected abstract void addAdapterFactory(
            ComposedAdapterFactory adapterFactory);

    public EditingDomain getEditingDomain(final URI uri) {
        return createAdapterFactoryEditingDomain();
    }

    public EditingDomain getEditingDomain() {
        return createAdapterFactoryEditingDomain();
    }

    protected EditingDomain createAdapterFactoryEditingDomain() {
        return new AdapterFactoryEditingDomain(adapterFactory,
                new BasicCommandStack(), new HashMap<Resource, Boolean>());
    }

    @Override
    protected InputStream handlePreImport(final String fileName,
            final InputStream inputStream) throws MigrationException,
            IOException {
        final InputStream is = super.handlePreImport(fileName, inputStream);
        if (fileName.endsWith(".properties")
                || fileName.toLowerCase().endsWith(".png")
                || fileName.toLowerCase().endsWith(".jpg")
                || fileName.toLowerCase().endsWith(".gif")
                || fileName.toLowerCase().endsWith(".jpeg")
                || fileName.toLowerCase().endsWith(".xsd")) {// not an emf
                                                                                                                                                                                                                                                                                      // resource
            return is;
        }
        final CopyInputStream copyIs = new CopyInputStream(is);
        final InputStream originalStream = copyIs.getCopy();

        final Resource resource = getTmpEMFResource(fileName, copyIs.getFile());
        if (resource == null) {
            BonitaStudioLog.debug(
                    "Failed to retrieve EMF Resource for migration",
                    CommonRepositoryPlugin.PLUGIN_ID);
            copyIs.close();
            return originalStream;
        }
        final URI resourceURI = resource.getURI();
        final File tmpFile = new File(resource.getURI().toFileString());
        final String nsURI = getNamespaceURI(fileName, copyIs, resourceURI, tmpFile);
        if (nsURI == null) {
            tmpFile.delete();
            copyIs.close();
            throw new IOException(fileName);
        }
        final Migrator targetMigrator = getMigrator(nsURI);
        if (targetMigrator != null) {
            final Release release = getRelease(targetMigrator, resource);
            if (release != null && !release.isLatestRelease()) {
                try {
                    BonitaStudioLog.debug(
                            "Performing migration on " + fileName + " from " + release.getLabel() + " to latest...",
                            CommonRepositoryPlugin.PLUGIN_ID);
                    performMigration(targetMigrator, resourceURI, release);
                } catch (final MigrationException e) {
                    if (tmpFile != null) {
                        tmpFile.delete();
                    }
                    if (copyIs != null) {
                        copyIs.close();
                    }
                    throw e;
                }
                try {
                    copyIs.close();
                    final FileInputStream newIs = new FileInputStream(tmpFile);
                    tmpFile.delete();
                    return newIs;
                } catch (final Exception e) {
                    BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
                    return null;
                } finally {
                    if (tmpFile != null) {
                        tmpFile.delete();
                    }
                    if (copyIs != null) {
                        copyIs.close();
                    }
                }
            }
        }

        tmpFile.delete();
        copyIs.close();
        return originalStream;
    }

    private String getNamespaceURI(final String fileName, final CopyInputStream copyIs, final URI resourceURI,
            final File tmpFile) throws IOException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.setErrorHandler(new ErrorHandler() {

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                }
            });
            dBuilder.parse(tmpFile);
        } catch (ParserConfigurationException | SAXException e1) {
            throw new IOException(e1);
        }

       return ReleaseUtils.getNamespaceURI(resourceURI);
    }


    public Migrator getMigrator(final String nsURI) {
        Migrator targetMigrator = initializeMigrator();
        if (!targetMigrator.getNsURIs().contains(nsURI)) {
            return MigratorRegistry.getInstance().getMigrator(nsURI);
        } 
        return targetMigrator;
    }

    protected Release getRelease(final Migrator targetMigrator,
            final Resource resource) {
        return targetMigrator.getRelease(resource.getURI()).iterator().next();
    }

    protected void performMigration(final Migrator migrator,
            final URI resourceURI, final Release release)
            throws MigrationException {
        migrator.setLevel(ValidationLevel.RELEASE);
        Map<String, Object> loadOptions = new HashMap<>();
        loadOptions.put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        try {
            migrator.migrateAndSave(Collections.singletonList(resourceURI),
                    release, null, AbstractRepository.NULL_PROGRESS_MONITOR, loadOptions);
        } catch (RuntimeException e) {
            throw new MigrationException(String.format("Failed to migrate %s", resourceURI), e);
        }

    }

    protected Resource getTmpEMFResource(final String fileName,
            final File originalFile) throws IOException {
        final EditingDomain editingDomain = getEditingDomain();
        final File tmpFile = File.createTempFile("tmp", fileName,
                ProjectUtil.getBonitaStudioWorkFolder());
        Files.copy(originalFile, tmpFile);
        return editingDomain.getResourceSet()
                .createResource(
                        URI.createFileURI(tmpFile.getAbsolutePath()));

    }

}
