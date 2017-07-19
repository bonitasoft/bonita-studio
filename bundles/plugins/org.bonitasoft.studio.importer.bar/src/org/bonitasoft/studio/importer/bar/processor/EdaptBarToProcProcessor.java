/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.EMFResourceUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.importer.bar.BarImporterPlugin;
import org.bonitasoft.studio.importer.bar.exception.IncompatibleVersionException;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.migrator.BOSMigrator;
import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.model.report.MigrationReportFactory;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.migration.preferences.BarImporterPreferenceConstants;
import org.bonitasoft.studio.migration.ui.wizard.MigrationWarningWizard;
import org.bonitasoft.studio.migration.utils.DeadlineMigrationStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.internal.migration.execution.internal.BundleClassLoader;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class EdaptBarToProcProcessor extends ToProcProcessor {

    private static final String ATTACHMENTS_FOLDER = "attachments";

    private static final Set<String> SUPPORTED_VERSIONS = new HashSet<String>();
    static {
        SUPPORTED_VERSIONS.add("5.9");
        SUPPORTED_VERSIONS.add("5.10");
    }

    private static final String MIGRATION_HISTORY_PATH = "models/v60/process.history";

    private static final String FORMS_LIBS = "forms/lib";

    private static final String VALIDATORS = "validators";

    private static final String LIBS = "libs/";

    private final List<Change> additionalChanges = new ArrayList<Change>();

    private File migratedProc;

    private BOSMigrator migrator;

    private boolean continueImport = true;

    private CustomConnectorMigrator customConnectorMigrator;

    public EdaptBarToProcProcessor() {
        final URI migratorURI = URI.createPlatformPluginURI(
                "/" + BarImporterPlugin.getDefault().getBundle().getSymbolicName() + "/" + MIGRATION_HISTORY_PATH,
                true);
        try {
            migrator = new BOSMigrator(migratorURI, new BundleClassLoader(BarImporterPlugin.getDefault().getBundle()));
        } catch (final MigrationException e) {
            BonitaStudioLog.error(e, BarImporterPlugin.PLUGIN_ID);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#createDiagram(java.net.URL, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public File createDiagram(final URL sourceFileURL, final IProgressMonitor progressMonitor) throws Exception {
        if (!FileActionDialog.getDisablePopup() && displayMigrationWarningPopup()) {
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),
                            new MigrationWarningWizard());
                    final int result = dialog.open();
                    if (result != Dialog.OK) {
                        continueImport = false;
                    }
                }
            });

        }
        if (!continueImport) {
            return null;
        }
        final File archiveFile = new File(URI.decode(sourceFileURL.getFile()));
        final File barProcFile = BarReaderUtil.getProcFormBar(archiveFile);

        importAttachment(archiveFile, progressMonitor);
        importCustomConnectors(archiveFile, progressMonitor);
        importProcessJarDependencies(archiveFile, progressMonitor);
        importFormJarDependencies(archiveFile, progressMonitor);
        importApplicationResources(archiveFile, progressMonitor);

        final Resource resource = new XMLResourceFactoryImpl()
                .createResource(URI.createFileURI(barProcFile.getAbsolutePath()));
        if (resource == null) {
            throw new Exception("Failed to create an EMF resource for " + barProcFile.getName());
        }

        final EMFResourceUtil emfResourceUtil = new EMFResourceUtil(barProcFile);
        final Map<String, String[]> featureValueFromEObjectType = emfResourceUtil.getFeatureValueFromEObjectType(
                "process:MainProcess",
                ProcessPackage.Literals.MAIN_PROCESS__BONITA_MODEL_VERSION);
        if (featureValueFromEObjectType.size() != 1) {
            throw new Exception("Failed to create an EMF resource for " + barProcFile.getName());
        }
        final String[] next = featureValueFromEObjectType.values().iterator().next();
        final String sourceVersion = next[0];

        if (sourceVersion == null || !SUPPORTED_VERSIONS.contains(sourceVersion.toString())) {
            throw new IncompatibleVersionException(sourceVersion, SUPPORTED_VERSIONS.toString());
        }
        final URI resourceURI = resource.getURI();
        final Release release = migrator.getRelease(0);
        performMigration(migrator, resourceURI, release, progressMonitor);// Migrate from 5.9 to 6.0-Alpha

        // Migrate from 6.0-Alpha to current release
        final DiagramRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        final String nsURI = ReleaseUtils.getNamespaceURI(resourceURI);
        final Migrator nextMigrator = store.getMigrator(nsURI);
        nextMigrator.setLevel(ValidationLevel.RELEASE);
        nextMigrator.migrateAndSave(
                Collections.singletonList(resourceURI), getAlphaRelease(nextMigrator),
                null, Repository.NULL_PROGRESS_MONITOR);
        addMigrationReport(migrator, resourceURI, sourceVersion, progressMonitor);
        DeadlineMigrationStore.clearDeadlines();

        migratedProc = barProcFile;
        return barProcFile;
    }

    private void importCustomConnectors(final File archiveFile, final IProgressMonitor progressMonitor) throws Exception {
        customConnectorMigrator = new CustomConnectorMigrator(archiveFile, additionalChanges);
        customConnectorMigrator.migrate(progressMonitor);
    }

    private Release getAlphaRelease(final Migrator nextMigrator) {
        for (final Release r : nextMigrator.getReleases()) {
            if (r.getLabel().equals(ProductVersion.VERSION_6_0_0_ALPHA)) {
                return r;
            }
        }
        return null;
    }

    /**
     * @throws MigrationException
     */
    protected void addMigrationReport(final BOSMigrator migrator, final URI resourceURI, final String sourceRelease,
            final IProgressMonitor monitor)
            throws MigrationException {
        final TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
        final Resource resource = editingDomain.getResourceSet().createResource(resourceURI);
        try {
            resource.load(Collections.EMPTY_MAP);
            final AbstractEMFOperation emfOperation = new AbstractEMFOperation(editingDomain, "Update report") {

                @Override
                protected IStatus doExecute(final IProgressMonitor monitor, final IAdaptable info)
                        throws ExecutionException {
                    final Report report = migrator.getReport();
                    String diagramName = "";
                    for (final EObject root : resource.getContents()) {
                        if (root instanceof MainProcess) {
                            diagramName = ((MainProcess) root).getName() + "--" + ((MainProcess) root).getVersion();
                            ((MainProcess) root).setBonitaModelVersion(ModelVersion.CURRENT_VERSION);
                            ((MainProcess) root).setBonitaVersion(ProductVersion.CURRENT_VERSION);
                            ((MainProcess) root).setConfigId(ConfigurationIdProvider.getConfigurationIdProvider()
                                    .getConfigurationId((MainProcess) root));
                        }
                    }
                    report.setName(Messages.bind(Messages.migrationReportOf, diagramName));
                    report.setSourceRelease(sourceRelease);
                    report.setTargetRelease(ProductVersion.CURRENT_VERSION);
                    report.getChanges().addAll(additionalChanges);
                    additionalChanges.clear();
                    resource.getContents().add(report);
                    return Status.OK_STATUS;
                }
            };
            final IOperationHistory history = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
            try {
                history.execute(emfOperation, monitor, null);
            } catch (final ExecutionException e) {
                BonitaStudioLog.error(e);
            }
            resource.save(Collections.emptyMap());
            resource.unload();
            editingDomain.dispose();
        } catch (final IOException e) {
            throw new MigrationException("Model could not be loaded", e);
        }
    }

    protected Change addReportChange(final String elementName, final String elementType, final String elementUUID,
            final String description, final String propertyName, final int status) {
        final Change change = MigrationReportFactory.eINSTANCE.createChange();
        change.setElementName(elementName);
        change.setElementType(elementType);
        change.setElementUUID(elementUUID);
        change.setDescription(description);
        change.setPropertyName(propertyName);
        change.setStatus(status);
        additionalChanges.add(change);
        return change;
    }

    private void importApplicationResources(final File archiveFile, final IProgressMonitor progressMonitor) {
        final File tmpBar = new File(ProjectUtil.getBonitaStudioWorkFolder(), "tmpBar");
        try {
            PlatformUtil.unzipZipFiles(archiveFile, tmpBar, progressMonitor);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        final File resourceFile = FileUtil.findDirectory(tmpBar, "studio-templates");
        if (resourceFile != null) {
            final ApplicationResourceRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(
                    ApplicationResourceRepositoryStore.class);
            File toCopy = null;
            for (final File f : resourceFile.listFiles()) {
                final String webTemplateId = f.getName();
                IRepositoryFileStore webTemplateArtifact = store.getChild(webTemplateId);
                if (webTemplateArtifact == null) {
                    webTemplateArtifact = store.createRepositoryFileStore(webTemplateId);
                }

                toCopy = f;
                if (toCopy.exists()) {
                    PlatformUtil.copyResource(webTemplateArtifact.getResource().getLocation().toFile(), toCopy,
                            progressMonitor);
                }
            }
            PlatformUtil.delete(tmpBar, progressMonitor);
        }
    }

    private void importFormJarDependencies(final File archiveFile, final IProgressMonitor monitor)
            throws ZipException, IOException {
        final DependencyRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(DependencyRepositoryStore.class);
        final ZipFile zipfile = new ZipFile(archiveFile);
        final Enumeration<?> enumEntries = zipfile.entries();
        ZipEntry zipEntry = null;
        while (enumEntries.hasMoreElements()) {
            zipEntry = (ZipEntry) enumEntries.nextElement();
            final File currentFile = new File(zipEntry.getName());
            if (!zipEntry.isDirectory() && zipEntry.getName().contains(FORMS_LIBS) && zipEntry.getName().endsWith(".jar")) {
                final DependencyFileStore dependencyFileStore = store.importInputStream(currentFile.getName(),
                        zipfile.getInputStream(zipEntry));
                if (dependencyFileStore == null) {
                    BonitaStudioLog.debug("Failed to import application dependency " + currentFile.getName(),
                            BarImporterPlugin.PLUGIN_ID);
                }
            }
            if (!zipEntry.isDirectory() && zipEntry.getName().contains(VALIDATORS) && zipEntry.getName().endsWith(".jar")) {
                if (store.getChild(currentFile.getName()) == null) {
                    store.importInputStream(currentFile.getName(), zipfile.getInputStream(zipEntry));
                }
                importValidatorSource(currentFile, monitor);
            }
        }
        zipfile.close();
    }

    private void importValidatorSource(final File currentFile, final IProgressMonitor monitor) {
        // TODO Import validator sources in repository
    }

    private void importProcessJarDependencies(final File archiveFile, final IProgressMonitor monitor)
            throws ZipException, IOException {
        final DependencyRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(DependencyRepositoryStore.class);
        final ZipFile zipfile = new ZipFile(archiveFile);
        final Enumeration<?> enumEntries = zipfile.entries();
        ZipEntry zipEntry = null;
        while (enumEntries.hasMoreElements()) {
            zipEntry = (ZipEntry) enumEntries.nextElement();
            final File currentFile = new File(zipEntry.getName());
            if (!zipEntry.isDirectory() && zipEntry.getName().contains(LIBS) && zipEntry.getName().endsWith(".jar")) {
                if (!customConnectorMigrator.getImportedJarNames().contains(currentFile.getName())
                        && store.getChild(currentFile.getName()) == null) {
                    final DependencyFileStore dependencyFileStore = store.importInputStream(currentFile.getName(),
                            zipfile.getInputStream(zipEntry));
                    if (dependencyFileStore == null) {
                        BonitaStudioLog.debug("Failed to import process dependency " + currentFile.getName(),
                                BarImporterPlugin.PLUGIN_ID);
                    }
                }
            }
        }
        zipfile.close();
    }

    private boolean displayMigrationWarningPopup() {
        final IPreferenceStore store = MigrationPlugin.getDefault().getPreferenceStore();
        return store.getBoolean(BarImporterPreferenceConstants.DISPLAY_MIGRATION_WARNING);
    }

    private void importAttachment(final File archiveFile, final IProgressMonitor monitor) throws IOException {
        final DocumentRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(DocumentRepositoryStore.class);
        final ZipFile zipfile = new ZipFile(archiveFile);
        final Enumeration<?> enumEntries = zipfile.entries();
        ZipEntry zipEntry = null;
        while (enumEntries.hasMoreElements()) {
            zipEntry = (ZipEntry) enumEntries.nextElement();
            final File currentFile = new File(zipEntry.getName());
            if (!zipEntry.isDirectory() && zipEntry.getName().contains(ATTACHMENTS_FOLDER)) {
                store.importInputStream(currentFile.getName(), zipfile.getInputStream(zipEntry));
            }

        }
        zipfile.close();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#getResources()
     */
    @Override
    public List<File> getResources() {
        if (migratedProc != null) {
            return Collections.singletonList(migratedProc);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#getExtension()
     */
    @Override
    public String getExtension() {
        return ".bar";
    }

    private void performMigration(final BOSMigrator migrator, final URI resourceURI, final Release release,
            final IProgressMonitor monitor) throws MigrationException {
        migrator.setLevel(ValidationLevel.RELEASE);
        migrator.migrateAndSave(
                Collections.singletonList(resourceURI), release,
                null, monitor);
    }

}
