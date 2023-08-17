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

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.ui.dialog.DetailsEnvironmentDialog;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.ui.editors.DirtyEditorChecker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

public class EnvironmentFileStore extends EMFFileStore<Environment> {
	
	private static final String ENVIRONMENT_CONTENT_TYPE = "Environment";

    public EnvironmentFileStore(String fileName, IRepositoryStore<EnvironmentFileStore> store) {
        super(fileName, store);
    }
    
    @Override
    protected Resource doCreateEMFResource() {
        final URI uri = getResourceURI();
        try {
            final EditingDomain editingDomain = getParentStore().getEditingDomain(uri);
            final ResourceSet resourceSet = editingDomain.getResourceSet();
            var emfResource = resourceSet.createResource(uri, ENVIRONMENT_CONTENT_TYPE);
            if (getResource().exists()) {
            	emfResource.load(Map.of());
            } 
            return emfResource;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
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
            var envFile = store.getChild(getContent().getName() + "." + EnvironmentRepositoryStore.ENV_EXT, true);
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
        var dirtyEditorChecker = new DirtyEditorChecker();
        var newEnvFile = store.getChild(dialog.getNameEnv() + "." + EnvironmentRepositoryStore.ENV_EXT, true);
        if (newEnvFile == null && dirtyEditorChecker.checkDirtyState(getProgressService(), false)) {
            // Create and save new env file, delete the old one
            newEnvFile = store
                    .createRepositoryFileStore(dialog.getNameEnv() + "." + EnvironmentRepositoryStore.ENV_EXT);
            var env = envFile.getContent();
            var oldEnvName = env.getName();
            env.setName(dialog.getNameEnv());
            env.setDescription(dialog.getDescEnv());
            newEnvFile.save(env);
            envFile.delete();
            // Updating existing same name Configuration for each Process 
            var diagStore = getRepositoryAccessor()
                    .getRepositoryStore(DiagramRepositoryStore.class);
            var progressService = getProgressService();
            try {
                progressService.run(true, false, diagStore::computeProcesses);
                progressService.run(true, false, monitor -> {
                    var allProcesses = diagStore.getComputedProcesses();
                    monitor.beginTask(String.format(Messages.renamingProcessConfigurations, oldEnvName, env.getName()),
                            allProcesses.size());
                    allProcesses
                            .forEach(process -> updateConfiguration(process, env.getName(), oldEnvName, monitor));
                });
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            } finally {
                diagStore.resetComputedProcesses();
            }

            // If the former env file was active, we need to put active the modified one
            if (ConfigurationPlugin.getDefault().getPreferenceStore()
                    .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION).equals(oldEnvName)) {
                ConfigurationPlugin.getDefault().getPreferenceStore().setValue(
                        ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION,
                        IDisplayable.toDisplayName(newEnvFile).orElse(""));
                AbstractFileStore.refreshExplorerView();
            }
        } else {
            var env = envFile.getContent();
            env.setDescription(dialog.getDescEnv());
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
                .execute(SetCommand.create(editingDomain, srcConfig, ConfigurationPackage.Literals.CONFIGURATION__NAME,
                        newEnv));
    }

}
