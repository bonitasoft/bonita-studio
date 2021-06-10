/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.operation.extension;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.preview.PreviewResultImpl;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.UpdateDependencyVersionOperation;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipant;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipantFactoryRegistry;
import org.bonitasoft.studio.common.repository.extension.update.preview.PreviewMessageProvider;
import org.bonitasoft.studio.common.repository.extension.update.preview.Previewable;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;

public class UpdateExtensionOperationDecorator {

    private static final String BATCH_VALIDATION_COMMAND_ID = "org.bonitasoft.studio.validation.batchValidation";
    private List<DependencyUpdate> dependenciesUpdates;
    private IRepository currentRepository;
    private List<ExtensionUpdateParticipant> participants = List.of();
    private CommandExecutor commandExecutor;

    public UpdateExtensionOperationDecorator(List<DependencyUpdate> dependenciesUpdates,
            IRepository currentRepository, CommandExecutor commandExecutor) {
        this.dependenciesUpdates = dependenciesUpdates;
        this.currentRepository = currentRepository;
        this.commandExecutor = commandExecutor;
    }

    public void preUpdate(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        if (!dependenciesUpdates.isEmpty()) {
            participants = ExtensionUpdateParticipantFactoryRegistry.getInstance()
                    .createParticipants(dependenciesUpdates);
            DiagramRepositoryStore diagramRepositoryStore = currentRepository
                    .getRepositoryStore(DiagramRepositoryStore.class);
            diagramRepositoryStore.resetComputedProcesses();
            monitor.setTaskName(Messages.preparingUpdate);
            diagramRepositoryStore.computeProcesses(new NullProgressMonitor());
            for (ExtensionUpdateParticipant participant : participants) {
                participant.preUpdate(new NullProgressMonitor());
            }
        }
    }

    public boolean postUpdate(IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        DiagramRepositoryStore diagramRepositoryStore = currentRepository
                .getRepositoryStore(DiagramRepositoryStore.class);
        var localDependencyStore = currentRepository.getLocalDependencyStore();
        try {
            monitor.setTaskName(Messages.computingPreview);
            for (var p : participants) {
                p.runPreview(new NullProgressMonitor());
            }
            var previewResult = new PreviewResultImpl();
            participants.stream()
                    .map(Previewable::getPreviewResult)
                    .flatMap(r -> r.getChanges().stream())
                    .forEach(previewResult::addChange);
            PreviewMessageProvider messageProvider = participants.stream()
                    .filter(participant -> !participant.getPreviewResult().getChanges().isEmpty())
                    .findFirst()
                    .map(ExtensionUpdateParticipant::getPreviewMessageProvider)
                    .orElse(null);
            if (previewResult.shouldOpenPreviewDialog() && messageProvider != null) {
                var dialogResult = new AtomicInteger();
                Display.getDefault().syncExec(() -> dialogResult
                        .set(previewResult.open(Display.getDefault().getActiveShell(), messageProvider)));
                int buttonId = dialogResult.get();
                if (buttonId == IDialogConstants.PROCEED_ID) {
                    applyChanges(localDependencyStore, monitor);
                } else if (buttonId == IDialogConstants.ABORT_ID) {
                    monitor.beginTask(Messages.abortingUpdate, IProgressMonitor.UNKNOWN);
                    abortDependenciesUpdate(localDependencyStore);
                    currentRepository.getProjectDependenciesStore().analyze(monitor);
                    return false;
                }
            } else {
                applyChanges(localDependencyStore, monitor);
            }
            // Run a minimal process validation that only checks dependency consistency
            Display.getDefault().asyncExec(() -> commandExecutor.executeCommand(BATCH_VALIDATION_COMMAND_ID, Map.of(
                    "dependencyConstraintsOnly", "true",
                    "checkAllModelVersion", "true",
                    "showReport", "false")));
        } finally {
            diagramRepositoryStore.resetComputedProcesses();
        }
        return true;
    }

    private void applyChanges(LocalDependenciesStore localDependencyStore, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.applyingChanges, IProgressMonitor.UNKNOWN);
        Set<Resource> modifiedResources = new HashSet<>();
        for (var p : participants) {
            p.run(new NullProgressMonitor());
            if (p.getModifiedResources() != null) {
                modifiedResources.addAll(p.getModifiedResources());
            }
        }
        for (var resource : modifiedResources) {
            try {
                resource.save(Collections.emptyMap());
            } catch (IOException e) {
                throw new InvocationTargetException(e);
            }
        }
        updateLocalStore(localDependencyStore);
    }

    private void updateLocalStore(LocalDependenciesStore localDependencyStore) {
        dependenciesUpdates.stream()
                .forEach(du -> {
                    try {
                        localDependencyStore.deleteBackup(du.getCurrentDependency());
                        if (du.getUpdatedDependency() != null && !Objects.equals(du.getCurrentDependency().getVersion(),
                                du.getUpdatedDependency().getVersion())) {
                            // Nothing to remove if version is the same (updating a snapshot)
                            localDependencyStore.remove(du.getCurrentDependency());
                        }
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }

                });
    }

    private void abortDependenciesUpdate(LocalDependenciesStore localDependencyStore)
            throws InvocationTargetException {
        revertUpdatedDependencies();
        revertRemovedDependencies();
        revertLocalStore(localDependencyStore);
    }

    private void revertRemovedDependencies() throws InvocationTargetException {
        List<Dependency> removedDependencies = dependenciesUpdates
                .stream()
                .filter(d -> d.getUpdatedDependency() == null)
                .map(DependencyUpdate::getCurrentDependency)
                .collect(Collectors.toList());
        if (!removedDependencies.isEmpty()) {
            try {
                new AddDependencyOperation(removedDependencies)
                        .run(AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                throw new InvocationTargetException(e);
            }
        }
    }

    private void revertUpdatedDependencies() throws InvocationTargetException {
        List<Dependency> originalDependencies = dependenciesUpdates
                .stream()
                .filter(d -> d.getUpdatedDependency() != null)
                .map(DependencyUpdate::getCurrentDependency)
                .collect(Collectors.toList());
        if (!originalDependencies.isEmpty()) {
            try {
                new UpdateDependencyVersionOperation(originalDependencies)
                        .run(AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                throw new InvocationTargetException(e);
            }
        }
    }

    private void revertLocalStore(LocalDependenciesStore localDependencyStore) {
        //remove installed local dependencies if any
        dependenciesUpdates
                .stream()
                .map(DependencyUpdate::getUpdatedDependency)
                .filter(Objects::nonNull)
                .forEach(dep -> {
                    try {
                        localDependencyStore.remove(dep);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                });

        //THEN Restore jar in local store if any
        dependenciesUpdates.stream()
                .map(DependencyUpdate::getCurrentDependency)
                .forEach(dep -> {
                    try {
                        localDependencyStore.revert(dep);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                });
    }

}
