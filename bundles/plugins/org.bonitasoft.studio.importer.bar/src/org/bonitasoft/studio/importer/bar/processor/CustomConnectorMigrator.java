/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.importer.bar.BarImporterPlugin;
import org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper.ConnectorDescriptorToConnectorDefinition;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.model.report.MigrationReportFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.ow2.bonita.connector.core.Connector;
import org.ow2.bonita.connector.core.ConnectorDescription;

/**
 * @author Romain Bioteau
 */
public class CustomConnectorMigrator {

    private static final String CONNECTORS = "connectors/";

    private static final String LIBS = "libs/";

    private final List<File> toDelete = new ArrayList<File>();

    private final Set<String> connectorsJars = new HashSet<String>();

    private final File businessArchiveFile;

    private final List<Change> additionalChanges;

    public CustomConnectorMigrator(final File businessArchiveFile, final List<Change> additionalChanges) {
        this.businessArchiveFile = businessArchiveFile;
        this.additionalChanges = additionalChanges;
    }

    public void migrate(final IProgressMonitor progressMonitor) throws Exception {
        ZipFile zipfile = null;
        try {
            connectorsJars.clear();
            zipfile = new ZipFile(businessArchiveFile);
            final Enumeration<?> enumEntries = zipfile.entries();
            ZipEntry zipEntry = null;
            FileActionDialog.activateYesNoToAll();
            while (enumEntries.hasMoreElements()) {
                zipEntry = (ZipEntry) enumEntries.nextElement();
                final File currentFile = new File(zipEntry.getName());
                if (!zipEntry.isDirectory()
                        && (zipEntry.getName().startsWith(CONNECTORS) || zipEntry.getName().startsWith(LIBS))
                        && zipEntry.getName().endsWith(".jar")) {
                    try {
                        proceedCustomConnector(currentFile.getName(), zipfile.getInputStream(zipEntry), businessArchiveFile,
                                progressMonitor);
                    } finally {
                    }
                }
            }
            RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class).getResourceProvider()
                    .loadDefinitionsCategories(progressMonitor);
            RepositoryManager.getInstance().getCurrentRepository().build(progressMonitor);
        } finally {
            FileActionDialog.deactivateYesNoToAll();
            if (zipfile != null) {
                zipfile.close();
            }
        }

    }

    private void proceedCustomConnector(final String fileName, final InputStream inputStream, final File archiveFile,
            final IProgressMonitor progressMonitor) throws Exception {
        final File tmpConnectorJarFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), fileName);
        if (tmpConnectorJarFile.exists()) {
            tmpConnectorJarFile.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(tmpConnectorJarFile);
            FileUtil.copy(inputStream, fos);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

        BonitaStudioLog.debug("Searching for custom connector in " + tmpConnectorJarFile.getName() + "...",
                BarImporterPlugin.PLUGIN_ID);
        if (tmpConnectorJarFile.length() == 0) {
            BonitaStudioLog.debug(tmpConnectorJarFile.getName() + " is empty.", BarImporterPlugin.PLUGIN_ID);
            return;
        }
        final URLClassLoader customURLClassLoader = createBarClassloader(archiveFile, tmpConnectorJarFile);
        final List<String> connectorClassnames = BarReaderUtil.findCustomConnectorClassName(tmpConnectorJarFile);
        if (connectorClassnames.isEmpty()) {
            BonitaStudioLog.debug("No custom connector found in:" + tmpConnectorJarFile.getName(),
                    BarImporterPlugin.PLUGIN_ID);
        } else {
            for (final String connectorClassname : connectorClassnames) {
                BonitaStudioLog.debug(
                        "Custom connector " + connectorClassname + " has been found in " + tmpConnectorJarFile.getName(),
                        BarImporterPlugin.PLUGIN_ID);
                connectorsJars.add(tmpConnectorJarFile.getName());
                final Class<? extends Connector> instanceClass = (Class<? extends Connector>) customURLClassLoader
                        .loadClass(connectorClassname);
                try {
                    final ConnectorDescription descriptor = new ConnectorDescription(instanceClass, Locale.ENGLISH);
                    final ConnectorDescriptorToConnectorDefinition cdtocd = new ConnectorDescriptorToConnectorDefinition(
                            descriptor, tmpConnectorJarFile,
                            progressMonitor);
                    cdtocd.importConnectorDefinitionResources();
                    cdtocd.createConnectorDefinition();
                    cdtocd.createConnectorImplementation();
                    additionalChanges.add(cdtocd.createReportChange());
                } catch (final NoClassDefFoundError e) {
                    additionalChanges.add(createImportFailureReport(connectorClassname, e));
                }
            }
        }
        if (!toDelete.isEmpty()) {
            for (final File f : toDelete) {
                f.delete();
            }
        }

    }

    /***
     * Create an URLClassloader with all jar inside the archive file
     *
     * @param archiveFile
     * @param tmpConnectorJarFile
     * @return
     * @throws MalformedURLException
     * @throws ZipException
     * @throws IOException
     * @throws FileNotFoundException
     */
    protected URLClassLoader createBarClassloader(final File archiveFile, final File tmpConnectorJarFile)
            throws MalformedURLException,
            ZipException, IOException, FileNotFoundException {
        final List<URL> urls = new ArrayList<URL>();
        urls.add(tmpConnectorJarFile.toURI().toURL());
        final Enumeration<URL> urlEnum = BarImporterPlugin.getDefault().getBundle().findEntries("lib/", "*.jar", true);
        while (urlEnum.hasMoreElements()) {
            final URL type = urlEnum.nextElement();
            urls.add(type);
        }
        try (final ZipFile zipfile = new ZipFile(archiveFile);) {
            final Enumeration<?> enumEntries = zipfile.entries();
            ZipEntry zipEntry = null;
            while (enumEntries.hasMoreElements()) {
                zipEntry = (ZipEntry) enumEntries.nextElement();
                if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".jar")) {
                    final InputStream is = zipfile.getInputStream(zipEntry);
                    final File tmpJar = new File(ProjectUtil.getBonitaStudioWorkFolder(),
                            System.currentTimeMillis() + ".jar");
                    tmpJar.createNewFile();
                    final FileOutputStream os = new FileOutputStream(tmpJar);
                    FileUtil.copy(is, os);
                    is.close();
                    os.close();
                    urls.add(tmpJar.toURI().toURL());
                    toDelete.add(tmpJar);
                }
            }
        }
        final URLClassLoader customURLClassLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]),
                getClass().getClassLoader());
        return customURLClassLoader;
    }

    private Change createImportFailureReport(final String connectorClassname, final Throwable e) {
        final Change change = MigrationReportFactory.eINSTANCE.createChange();
        change.setElementName(connectorClassname);
        change.setElementType(Messages.customConnector);
        change.setElementUUID("");
        change.setDescription(Messages.bind(Messages.customConnectorMigrationFailureDescription, e.getMessage()));
        change.setPropertyName(Messages.development);
        change.setStatus(IStatus.ERROR);
        return change;
    }

    public Set<String> getImportedJarNames() {
        return connectorsJars;
    }
}
