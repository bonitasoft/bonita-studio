/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.configuration.ui.handler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.environment.EnvironmentFactory;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.repository.EnvironmentFileStore;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.bonitasoft.studio.configuration.ui.dialog.NewEnvironmentDialog;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.ui.editors.DirtyEditorChecker;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class CreateEnvironmentHandler extends AbstractHandler {

    private static final int MAX_NAME_LENGTH = 80;
    private DirtyEditorChecker dirtyEditorChecker;
    private RepositoryAccessor repositoryAccessor;

    public CreateEnvironmentHandler() {
        this.dirtyEditorChecker = new DirtyEditorChecker();
        this.repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    }

    @Inject
    public CreateEnvironmentHandler(DirtyEditorChecker dirtyEditorChecker, RepositoryAccessor repositoryAccessor) {
        this.dirtyEditorChecker = dirtyEditorChecker;
        this.repositoryAccessor = repositoryAccessor;
    }

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, IProgressService progressService) {
        var store = repositoryAccessor
                .getRepositoryStore(EnvironmentRepositoryStore.class);

        var dialog = new NewEnvironmentDialog(activeShell,
                Messages.createEnvironmentTitle,
                Messages.name,
                "",
                input -> {
                    if (input == null || input.isEmpty()) {
                        return Messages.emptyName;
                    }

                    if (input.length() > MAX_NAME_LENGTH) {
                        return String.format(Messages.maxNameLength, MAX_NAME_LENGTH);
                    }

                    if (store.getChildren().stream().map(IDisplayable::toDisplayName).filter(Optional::isPresent)
                            .map(Optional::get).anyMatch(input::equalsIgnoreCase)) {
                        return Messages.alreadyExists;
                    }
                    return null;
                });
        if (dialog.open() == Window.OK) {
            final String name = dialog.getValue();
            String selectedEnv = dialog.getSelectedEnv();
            var file = store.createRepositoryFileStore(name + "." + EnvironmentRepositoryStore.ENV_EXT);
            Environment env = EnvironmentFactory.eINSTANCE.createEnvironment();
            if (dialog.isDuplicateEnv()) {
                if (dirtyEditorChecker.checkDirtyState(progressService, false)) {
                    // Duplicate the env selected with this new name
                    duplicateEnv(progressService, repositoryAccessor, selectedEnv, name);
                    saveEnv(env, file, name);
                }
            } else {
                saveEnv(env, file, name);
            }
        }
    }

    private void saveEnv(Environment env, EnvironmentFileStore file, String name) {
        env.setName(name);
        file.save(env);
    }

    private void duplicateEnv(IProgressService progressService, RepositoryAccessor repositoryAccessor,
            String envNameToDuplicate, String envName) {
        var diagStore = repositoryAccessor
                .getRepositoryStore(DiagramRepositoryStore.class);
        try {
            progressService.run(true, false, diagStore::computeProcesses);
            progressService.run(true, false, monitor -> {
                var allProcesses = diagStore.getComputedProcesses();
                monitor.beginTask(String.format(Messages.copyingProcessConfigurations, envNameToDuplicate),
                        allProcesses.size());
                allProcesses
                        .forEach(process -> copyConfiguration(envNameToDuplicate, envName, process, monitor));
            });
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        } finally {
            diagStore.resetComputedProcesses();
        }
    }

    private void copyConfiguration(String sourceEnv, String newEnv,
            AbstractProcess process,
            IProgressMonitor monitor) {
        monitor.subTask(String.format("%s (%s)", process.getName(), process.getVersion()));
        try {
            var sourceConfiguration = findSourceConfiguration(sourceEnv, process);
            if (sourceConfiguration != null) {
                copyAndSaveResource(newEnv, sourceConfiguration, process);
            }
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
        }

        monitor.worked(1);
    }

    private Configuration findSourceConfiguration(String sourceEnv, AbstractProcess process)
            throws ReadFileStoreException {
        if (Objects.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURATION, sourceEnv)) {
            var confStore = repositoryAccessor
                    .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
            var confFileStore = confStore.getChild(
                    ModelHelper.getEObjectID(process) + "." + ProcessConfigurationRepositoryStore.CONF_EXT,
                    false);
            if (confFileStore != null) {
                return confFileStore.getContent();
            }
        } else {
            return process.getConfigurations().stream()
                    .filter(c -> Objects.equals(c.getName(), sourceEnv))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    void copyAndSaveResource(String newEnv, Configuration srcConfig, AbstractProcess process) {
        var configCopied = EcoreUtil.copy(srcConfig);
        configCopied.setName(newEnv);
        var editingDomain = TransactionUtil.getEditingDomain(process);
        editingDomain.getCommandStack()
                .execute(AddCommand.create(editingDomain, process,
                        ProcessPackage.Literals.ABSTRACT_PROCESS__CONFIGURATIONS,
                        configCopied));
        try {
            process.eResource().save(Map.of(XMLResource.OPTION_ENCODING, "UTF-8"));
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.hasActiveRepository();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        execute(Display.getDefault().getActiveShell(), PlatformUI.getWorkbench().getProgressService());
        return null;
    }

    @Override
    public boolean isEnabled() {
        return canExecute(repositoryAccessor);
    }

}
