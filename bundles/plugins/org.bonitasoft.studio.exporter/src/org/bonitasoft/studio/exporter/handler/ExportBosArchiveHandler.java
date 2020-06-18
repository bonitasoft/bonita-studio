/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter.handler;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.common.repository.ui.wizard.ExportRepositoryWizard;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.exporter.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class ExportBosArchiveHandler {

    private static final String BOS_ARCHIVE_PROVIDERS_EXTENSION_POINT = "org.bonitasoft.studio.bosArchiveProvider";
    private static ArrayList<IBOSArchiveFileStoreProvider> providers;

    @Execute
    public void execute(RepositoryAccessor repositoryAccessor,
            @org.eclipse.e4.core.di.annotations.Optional @Named("diagram") String diagramToExport) {
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveAllEditors(true)) {
            Set<Object> selectedFiles = new HashSet<>();
          
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
                    final MainProcess diagram = diagramToExport != null
                            ? getDiagram(repositoryAccessor, diagramToExport)
                            : getDiagramInEditor();
                    final List<IRepositoryStore<? extends IRepositoryFileStore>> exportableStores = RepositoryManager
                            .getInstance()
                            .getCurrentRepository()
                            .getAllExportableStores();
                    if (diagram != null) {
                        //Use a progress monitor as it can takes a few seconds for big diagrams
                        monitor.beginTask(String.format(Messages.resolvingDiagramDependencies, 
                                diagram.getName(),
                                diagram.getVersion()), 
                                IProgressMonitor.UNKNOWN);
                        selectedFiles.addAll(getAllDiagramRelatedFiles(diagram));
                    } else {
                        for (final IRepositoryStore<? extends IRepositoryFileStore> store : exportableStores) {
                            final List<? extends IRepositoryFileStore> files = store.getChildren();
                            if (files != null) {
                                for (final IRepositoryFileStore fStore : files) {
                                    if (fStore != null && fStore.canBeExported()) {
                                        selectedFiles.add(fStore);
                                    }
                                }
                            }
                        }
                    }
                });
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
            
            
            if (selectedFiles != null) {
                final ExportRepositoryWizard wizard = new ExportRepositoryWizard(
                        RepositoryManager.getInstance().getCurrentRepository()
                                .getAllExportableStores(),
                        true, selectedFiles, getDefaultName(), Messages.ExportButtonLabel);
                final WizardDialog dialog = new WizardDialog(
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                        wizard) {

                    @Override
                    protected void initializeBounds() {
                        super.initializeBounds();
                        getShell().setSize(600, 600);
                    }
                };
                dialog.setTitle(Messages.ExportButtonLabel);
                dialog.open();
            }
        }
    }

    private MainProcess getDiagram(RepositoryAccessor repositoryAccessor, String diagramToExport) {
        DiagramFileStore fileStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class)
                .getChild(diagramToExport, true);
        if (fileStore != null) {
            try {
                return fileStore.getContent();
            } catch (ReadFileStoreException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        throw new IllegalArgumentException(String.format("The diagram '%s' doesn't exist", diagramToExport));
    }

    private String getDefaultName() {
        final MainProcess diagram = getDiagramInEditor();
        if (diagram == null) {
            return RepositoryManager.getInstance().getCurrentRepository().getName() + "_"
                    + new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date()) + ".bos";
        }
        return NamingUtils.toDiagramFilename(diagram).replace(".proc", ".bos");
    }

    public static Set<Object> getAllDiagramRelatedFiles(final MainProcess diagram) {
        final Set<Object> result = new HashSet<>();
        final List<Pool> processes = ModelHelper.getAllItemsOfType(diagram, ProcessPackage.Literals.POOL);
        final List<IBOSArchiveFileStoreProvider> fileStoreProvider = getFileStoreProviders();

        for (final Pool p : processes) {
            final Configuration conf = getConfiguration(p, ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON);
            for (final IBOSArchiveFileStoreProvider provider : fileStoreProvider) {
                result.addAll(provider.getFileStoreForConfiguration(p, conf));
                if(provider.distinctByConfiguration()) {
                    for (final Configuration config : p.getConfigurations()) {
                        result.addAll(provider.getFileStoreForConfiguration(p, config));
                    }
                }
            }
        }

        if (processes.isEmpty()) {
            final DiagramRepositoryStore dStore = RepositoryManager.getInstance()
                    .getRepositoryStore(DiagramRepositoryStore.class);
            result.add(dStore.getDiagram(diagram.getName(), diagram.getVersion()));
        }

        return result;
    }

    private static List<IBOSArchiveFileStoreProvider> getFileStoreProviders() {
        if (providers == null) {
            providers = new ArrayList<>();
            final IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance()
                    .getConfigurationElements(
                            BOS_ARCHIVE_PROVIDERS_EXTENSION_POINT);
            final ExtensionContextInjectionFactory extensionContextInjectionFactory = new ExtensionContextInjectionFactory();
            for (final IConfigurationElement extension : extensions) {
                try {
                    providers.add(extensionContextInjectionFactory.make(extension, "providerClass",
                            IBOSArchiveFileStoreProvider.class));
                } catch (final Exception ex) {
                    BonitaStudioLog.error(ex);
                }
            }
        }
        return providers;
    }

    protected MainProcess getDiagramInEditor() {
        if (PlatformUI.getWorkbench().getWorkbenchWindows() == null
                || PlatformUI.getWorkbench().getWorkbenchWindows().length == 0) {
            return null;
        }
        final IEditorPart editor = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor();
        final boolean isADiagram = editor != null && editor instanceof DiagramEditor;
        if (isADiagram) {
            final EObject root = ((DiagramEditor) editor).getDiagramEditPart().resolveSemanticElement();
            final MainProcess mainProc = ModelHelper.getMainProcess(root);
            return mainProc;
        }

        return null;
    }

    public static Configuration getConfiguration(final AbstractProcess process, String configurationId) {
        Configuration configuration = null;
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        if (configurationId == null) {
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore()
                    .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        }
        if (configurationId.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)) {
            final String id = ModelHelper.getEObjectID(process);
            IRepositoryFileStore file = processConfStore.getChild(id + ".conf", true);
            if (file == null) {
                file = processConfStore.createRepositoryFileStore(id + ".conf");
                final Configuration conf = ConfigurationFactory.eINSTANCE.createConfiguration();
                conf.setVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
                file.save(conf);
            }
            try {
                configuration = (Configuration) file.getContent();
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to retrieve configuration content", e);
            }
        } else {
            for (final Configuration conf : process.getConfigurations()) {
                if (configurationId.equals(conf.getName())) {
                    configuration = conf;
                }
            }
        }
        if (configuration == null) {
            configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
            configuration.setName(configurationId);
            configuration.setVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
        }
        //Synchronize configuration with definition
        new ConfigurationSynchronizer(process, configuration).synchronize();
        return configuration;
    }

}
