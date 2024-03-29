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
package org.bonitasoft.studio.application.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.studio.common.diagram.Identifier;
import org.bonitasoft.studio.common.diagram.dialog.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.operation.RenameDiagramOperation;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;


public class RenameDiagramCommandHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        String diagramToRename = event.getParameter("diagram");
        final MainProcess diagram = diagramToRename != null
                ? getMainProcess(diagramToRename)
                : getMainProcess();
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        final OpenNameAndVersionForDiagramDialog nameDialog = new OpenNameAndVersionForDiagramDialog(
                Display.getDefault().getActiveShell(), diagram,
                diagramStore);
        if (nameDialog.open() == Dialog.OK) {
            getDiagramEditor().filter(IEditorPart::isDirty)
                    .ifPresent(editor -> editor.doSave(AbstractRepository.NULL_PROGRESS_MONITOR));
            RenameDiagramOperation renameDiagramOperation = new RenameDiagramOperation();
            renameDiagramOperation.setDiagramToDuplicate(diagram);
            Identifier identifier = nameDialog.getIdentifier();
            renameDiagramOperation.setNewDiagramName(identifier.getName());
            renameDiagramOperation.setNewDiagramVersion(nameDialog.getIdentifier().getVersion());
            renameDiagramOperation.setPoolsRenamed(nameDialog.getPools());
            final IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            try {
                progressService.run(false, false, renameDiagramOperation);
            } catch (final InvocationTargetException e1) {
                throw new ExecutionException(e1.getMessage(), e1);
            } catch (final InterruptedException e1) {
                throw new ExecutionException(e1.getMessage(), e1);
            }
        }
        return null;
    }

    @Override
    public boolean isEnabled() {
        if (RepositoryManager.getInstance().hasActiveRepository()) {
            boolean diagramSelected = new FileStoreFinder()
                    .findSelectedFileStore(RepositoryManager.getInstance().getCurrentRepository().orElseThrow())
                    .filter(DiagramFileStore.class::isInstance).isPresent();
            return diagramSelected || getMainProcess() != null;
        }
        return false;
    }

    protected MainProcess getMainProcess() {
        if (PlatformUI.isWorkbenchRunning()) {
            Optional<ProcessDiagramEditor> diagramEditor = getDiagramEditor();
            if (diagramEditor.isPresent()) {
                ProcessDiagramEditor processDiagramEditor = diagramEditor.get();
                final EObject mainElement = processDiagramEditor.getDiagramEditPart().resolveSemanticElement();
                return ModelHelper.getMainProcess(mainElement);
            }
            return new FileStoreFinder()
                    .findSelectedFileStore(RepositoryManager.getInstance().getCurrentRepository().orElseThrow())
                    .filter(DiagramFileStore.class::isInstance)
                    .map(DiagramFileStore.class::cast)
                    .map(t -> {
                        try {
                            return t.getContent();
                        } catch (ReadFileStoreException e) {
                           return null;
                        }
                    })
                    .orElse(null);
        }
        return null;
    }

    private Optional<ProcessDiagramEditor> getDiagramEditor() {
        IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        return editor instanceof ProcessDiagramEditor
                ? Optional.of((ProcessDiagramEditor) editor)
                : Optional.empty();
    }

    private MainProcess getMainProcess(String diagramToRename) {
        DiagramFileStore fileStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class)
                .getChild(diagramToRename, true);
        try {
            return fileStore != null
                    ? fileStore.getContent()
                    : null;
        } catch (ReadFileStoreException e) {
            return null;
        }
    }
}
