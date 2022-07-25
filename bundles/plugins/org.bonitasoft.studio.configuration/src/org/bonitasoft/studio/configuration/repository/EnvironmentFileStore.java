/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration.repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.ui.dialog.DetailsEnvironmentDialog;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.editors.DirtyEditorChecker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

public class EnvironmentFileStore extends EMFFileStore<Environment> {

    private DefaultConfigurationStyler defaultConfigurationStyler;

    public EnvironmentFileStore(String fileName, IRepositoryStore<EnvironmentFileStore> store) {
        super(fileName, store);
        defaultConfigurationStyler = new DefaultConfigurationStyler();
    }

    @Override
    public String getDisplayName() {
        try {
            return getContent().getName();
        } catch (ReadFileStoreException e) {
            return getName();
        }
    }

    @Override
    protected void doSave(Object content) {
        if (content instanceof Environment) {
            Resource emfResource = getEMFResource();
            emfResource.getContents().clear();
            emfResource.getContents().add(EcoreUtil.copy((EObject) content));
            Map<String, String> options = new HashMap<>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            try {
                emfResource.save(options);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    protected IWorkbenchPart doOpen() {
        try {
            var envFile = store.getChild(getDisplayName() + "." + EnvironmentRepositoryStore.ENV_EXT, true);
            var dialog = new DetailsEnvironmentDialog(Display.getDefault().getActiveShell(), envFile.getContent());
            if (dialog.open() == Window.OK) {
                updateEnv(dialog, envFile);
            }
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }

        return null;
    }

    private void updateEnv(DetailsEnvironmentDialog dialog, IRepositoryFileStore<Environment> envFile)
            throws ReadFileStoreException {
        var env = envFile.getContent();
        var dirtyEditorChecker = new DirtyEditorChecker();
        var newEnvFile = store.getChild(dialog.getNameEnv() + "." + EnvironmentRepositoryStore.ENV_EXT, true);
        env.setDescription(dialog.getDescEnv());
        if (newEnvFile == null && dirtyEditorChecker.checkDirtyState(getProgressService(), false)) {
            // Create and save new env file, delete the old one
            newEnvFile = store
                    .createRepositoryFileStore(dialog.getNameEnv() + "." + EnvironmentRepositoryStore.ENV_EXT);
            var oldEnvName = env.getName();
            env.setName(dialog.getNameEnv());
            newEnvFile.save(env);
            envFile.delete();
            // Updating existing same name Configuration for each Process 
            var diagStore = getRepositoryAccessor()
                    .getRepositoryStore(DiagramRepositoryStore.class);
            var progressService = getProgressService();
            try {
                progressService.run(true, false, diagStore::computeProcesses);
                progressService.run(true, false, monitor -> {
                    var allProcesses = diagStore.getAllProcesses();
                    monitor.beginTask(String.format(Messages.renamingProcessConfigurations, oldEnvName, env.getName()),
                            allProcesses.size());
                    diagStore.getAllProcesses()
                            .forEach(process -> updateConfiguration(process, env.getName(),  oldEnvName,monitor));
                });
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            } finally {
                diagStore.resetComputedProcesses();
            }
            
            // If the former env file was active, we need to put active the modified one
            if (ConfigurationPlugin.getDefault().getPreferenceStore()
                    .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION).equals(oldEnvName)) {
                ConfigurationPlugin.getDefault().getPreferenceStore()
                        .setValue(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION, newEnvFile.getDisplayName());
                AbstractFileStore.refreshExplorerView();
            }

        } else {
            envFile.save(env);
        }
    }

    private void updateConfiguration(AbstractProcess process, String newConfigName, String oldConfigName,
            IProgressMonitor monitor) {
        // Find configuration
        var config = process.getConfigurations().stream()
                .filter(c -> Objects.equals(c.getName(), oldConfigName))
                .findFirst()
                .orElse(null);

        // Update name
        if (config != null) {
            monitor.subTask(String.format("%s (%s)", process.getName(), process.getVersion()));
            updateConfigurationName(newConfigName, config, process);
            // Save the process
            try {
                process.eResource().save(Map.of(XMLResource.OPTION_ENCODING, "UTF-8"));
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private void updateConfigurationName(String newEnv, Configuration srcConfig, AbstractProcess process) {
        var editingDomain = TransactionUtil.getEditingDomain(process);
        editingDomain.getCommandStack()
                .execute(new RecordingCommand(editingDomain) {

                    @Override
                    protected void doExecute() {
                        srcConfig.setName(newEnv);
                    }
                });
    }

    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.environment);
    }

    private boolean isDefault() {
        String defaultConfiguration = ConfigurationPlugin.getDefault().getPreferenceStore()
                .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        return Objects.equals(defaultConfiguration, getDisplayName());
    }

    @Override
    public StyledString getStyledString() {
        StyledString styledString = super.getStyledString();
        if (isDefault()) {
            styledString.append(String.format("  (%s)", Messages.activeConfiguration), defaultConfigurationStyler);
        }
        return styledString;
    }

}
