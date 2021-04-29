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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.definition.DependencyUpdate;
import org.bonitasoft.studio.application.operation.extension.participant.preview.PreviewResultImpl;
import org.bonitasoft.studio.application.operation.extension.participant.preview.Previewable;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.UpdateDependencyVersionOperation;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;

public class UpdateExtensionOperationDecorator {

    private List<DependencyUpdate> dependenciesUpdates;
    private IRepository currentRepository;
    private ExtensionUpdateParticipantFactory updateParticipantFactory;
    private List<UpdateExtensionOperationParticipant> participants = new ArrayList<>();

    public UpdateExtensionOperationDecorator(ExtensionUpdateParticipantFactory definitionUpdateOperationFactory,
            List<DependencyUpdate> dependenciesUpdates,
            IRepository currentRepository) {
        this.updateParticipantFactory = definitionUpdateOperationFactory;
        this.dependenciesUpdates = dependenciesUpdates;
        this.currentRepository = currentRepository;
    }

    public void preUpdate(IRunnableContext context) throws InvocationTargetException, InterruptedException {
        if (!dependenciesUpdates.isEmpty()) {
            participants.add(updateParticipantFactory
                    .createDefinitionUpdateParticipant(dependenciesUpdates));
            participants.add(updateParticipantFactory
                    .createProcessConfigurationUpdateParticipant(dependenciesUpdates));
            DiagramRepositoryStore diagramRepositoryStore = currentRepository
                    .getRepositoryStore(DiagramRepositoryStore.class);
            context.run(true, false, diagramRepositoryStore::computeProcesses);
            for (UpdateExtensionOperationParticipant participant : participants) {
                context.run(true, false, participant::preUpdate);
            }
        }
    }

    public boolean postUpdate(Shell shell, IRunnableContext context)
            throws InvocationTargetException, InterruptedException {
        DiagramRepositoryStore diagramRepositoryStore = currentRepository
                .getRepositoryStore(DiagramRepositoryStore.class);
        try {
            for (var p : participants) {
                context.run(true, false, p::runPreview);
            }
            
            var previewResult = new PreviewResultImpl();
            participants.stream()
                    .map(Previewable::getPreviewResult)
                    .flatMap(r -> r.getChanges().stream())
                    .forEach(previewResult::addChange);
            if (previewResult.hasChanges()) {
                int buttonId = previewResult.open(shell);
                if (buttonId == IDialogConstants.PROCEED_ID) {
                    for (var p : participants) {
                        context.run(true, false, p::run);
                    }
                } else if (buttonId == IDialogConstants.ABORT_ID) {
                    context.run(true, false, this::abortDependenciesUpdate);
                    return false;
                }
            }
        } finally {
            diagramRepositoryStore.resetComputedProcesses();
        }
        return true;
    }

    private void abortDependenciesUpdate(IProgressMonitor monitor) throws InvocationTargetException {
        monitor.beginTask(Messages.abortingUpdate, IProgressMonitor.UNKNOWN);
        List<Dependency> originalDependencies = dependenciesUpdates
                .stream()
                .map(DependencyUpdate::getCurrentDependency)
                .collect(Collectors.toList());
        try {
            new UpdateDependencyVersionOperation(originalDependencies)
                    .run(AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (CoreException e) {
            throw new InvocationTargetException(e);
        }
        dependenciesUpdates
                .stream()
                .map(DependencyUpdate::getUpdatedDependency)
                .forEach(dep -> {
                    try {
                        currentRepository.getLocalDependencyStore().remove(dep);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                });
    }

}
