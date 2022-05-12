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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.plugin.analyze.report.model.Issue;
import org.bonitasoft.plugin.analyze.report.model.Issue.Severity;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.preview.PreviewResultImpl;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.UpdateDependencyVersionOperation;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipant;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipantFactoryRegistry;
import org.bonitasoft.studio.common.repository.extension.update.preview.PreviewMessageProvider;
import org.bonitasoft.studio.common.repository.extension.update.preview.Previewable;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.ui.dialog.ProblemsDialog;
import org.bonitasoft.studio.ui.provider.TypedLabelProvider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class UpdateExtensionOperationDecorator {

    private static final String BATCH_VALIDATION_COMMAND_ID = "org.bonitasoft.studio.validation.batchValidation";
    private List<DependencyUpdate> dependenciesUpdates;
    private IRepository currentRepository;
    private List<ExtensionUpdateParticipant> participants = List.of();
    private CommandExecutor commandExecutor;
    private boolean shouldValidateProject;

    public UpdateExtensionOperationDecorator(List<DependencyUpdate> dependenciesUpdates,
            IRepository currentRepository, CommandExecutor commandExecutor) {
        this.dependenciesUpdates = dependenciesUpdates;
        this.currentRepository = currentRepository;
        this.commandExecutor = commandExecutor;
    }

    public void preUpdate(IProgressMonitor monitor) {
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

    /**
     * Check
     * 
     * @param dependency the added/updated dependency
     * @param monitor a progress monitor
     * @return true if the extension issues should be ignored and false if the update should be aborted.
     * @throws InvocationTargetException 
     */
    public boolean checkIssues(Dependency dependency, IProgressMonitor monitor) throws InvocationTargetException {
        var localDependencyStore = currentRepository.getLocalDependencyStore();
        var projectDependenciesStore = currentRepository.getProjectDependenciesStore();
        List<Issue> issues = projectDependenciesStore.findIssues(dependency);
        if (!issues.isEmpty()) {
            String artifactId = issues.get(0).getContext().get(0);
            var dialogResult = new AtomicInteger(0);
            Display.getDefault().syncExec(() -> dialogResult
                    .set(new ProblemsDialog<Issue>(Display.getDefault().getActiveShell(),
                            Messages.problemsFound,
                            String.format(Messages.problemsFoundMessage, artifactId),
                            MessageDialog.WARNING,
                            new String[] { Messages.undo, IDialogConstants.IGNORE_LABEL}) {

                        @Override
                        protected TypedLabelProvider<Issue> getTypedLabelProvider() {
                            return new TypedLabelProvider<Issue>() {

                                @Override
                                public String getText(Issue element) {
                                    return element.getMessage();
                                }

                                @Override
                                public Image getImage(Issue element) {
                                    return Severity.valueOf(element.getSeverity()) == Severity.ERROR
                                            ? JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR) : JFaceResources
                                                    .getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
                                }
                            };
                        }

                        @Override
                        protected Collection<Issue> getInput() {
                            return issues;
                        }

                    }.open()));
            if(dialogResult.get() == 0) {
                if(dependenciesUpdates.isEmpty()) {
                    abortDependencyImport(dependency, localDependencyStore);
                }else {
                    abortDependenciesUpdate(localDependencyStore);
                }
                currentRepository.getProjectDependenciesStore().analyze(monitor);
                return false;
            }
        }
        return true;
    }

    public boolean postUpdate(IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        var localDependencyStore = currentRepository.getLocalDependencyStore();
        monitor.setTaskName(Messages.computingPreview);

        for (var p : participants) {
            try {
                p.runPreview(new NullProgressMonitor());
            } catch (CoreException e) {
                throw new InvocationTargetException(e.getCause());
            }
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
        // No need to trigger project validation when updating Themes or Rest API Extensions
        shouldValidateProject = dependenciesUpdates.stream()
                .map(DependencyUpdate::getCurrentDependency)
                .map(Dependency::getType)
                .anyMatch(type -> type == null || Objects.equals(type, "jar"));
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
                        if (du.getUpdatedDependency() != null
                                && (du.isRename() || !Objects.equals(du.getCurrentDependency().getVersion(),
                                        du.getUpdatedDependency().getVersion()))) {
                            // Nothing to remove if version is the same (updating a snapshot)
                            localDependencyStore.remove(du.getCurrentDependency());
                        }
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }

                });
    }
    
    
    private void abortDependencyImport(Dependency depedency, LocalDependenciesStore localDependencyStore)
            throws InvocationTargetException {
        try {
            new RemoveDependencyOperation(depedency)
                    .run(AbstractRepository.NULL_PROGRESS_MONITOR);
            localDependencyStore.remove(depedency);
        } catch (CoreException e) {
            throw new InvocationTargetException(e);
        }
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

    public boolean shouldValidateProject() {
        return shouldValidateProject;
    }

    public void validateDependenciesConstraints() {
        // Run a minimal process validation that only checks dependency consistency
        Display.getDefault().asyncExec(() -> commandExecutor.executeCommand(BATCH_VALIDATION_COMMAND_ID, Map.of(
                "dependencyConstraintsOnly", "true",
                "checkAllModelVersion", "true",
                "showReport", "false")));
    }

}
