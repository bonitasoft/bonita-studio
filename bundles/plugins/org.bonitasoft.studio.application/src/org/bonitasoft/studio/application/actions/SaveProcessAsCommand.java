/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
import java.util.List;

import org.bonitasoft.studio.common.diagram.Identifier;
import org.bonitasoft.studio.common.diagram.dialog.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.diagram.dialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.operation.DuplicateDiagramOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Mickael Istria
 */
public class SaveProcessAsCommand extends AbstractHandler {

    private String newProcessLabel;
    private String newProcessVersion;
    private List<ProcessesNameVersion> pools;
    private DiagramRepositoryStore diagramStore;

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        diagramStore = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class);
        final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (!(editor instanceof ProcessDiagramEditor)) {
            MessageDialog.openWarning(Display.getDefault().getActiveShell(), "This is not a process diagram!",
                    "Cannot perform \"Duplicate\" on something that is not a process diagram");
            return null;
        }

        final ProcessDiagramEditor processEditor = (ProcessDiagramEditor) editor;

        final MainProcess process = (MainProcess) ((IGraphicalEditPart) processEditor.getDiagramEditPart()).resolveSemanticElement();
        newProcessLabel = process.getName();
        newProcessVersion = process.getVersion();
        final OpenNameAndVersionForDiagramDialog dialog = new OpenNameAndVersionForDiagramDialog(Display.getDefault().getActiveShell(),
                ModelHelper.getMainProcess(process), diagramStore);
        dialog.forceNameUpdate();
        if (dialog.open() == Dialog.OK) {
            final Identifier identifier = dialog.getIdentifier();
            newProcessLabel = identifier.getName();
            newProcessVersion = dialog.getIdentifier().getVersion();
            pools = dialog.getPools();
            final DuplicateDiagramOperation op = new DuplicateDiagramOperation();
            op.setDiagramToDuplicate(process);
            op.setNewDiagramName(newProcessLabel);
            op.setNewDiagramVersion(newProcessVersion);
            op.setPoolsRenamed(pools);
            final IProgressService service = PlatformUI.getWorkbench().getProgressService();
            try {
                service.run(true, false, op);
            } catch (final InvocationTargetException e) {
                throw new ExecutionException(e.getMessage(), e);
            } catch (final InterruptedException e) {
                throw new ExecutionException(e.getMessage(), e);
            }
            final DiagramFileStore store = diagramStore.getDiagram(newProcessLabel, newProcessVersion);
            store.open();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
            final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            return editor != null && editor instanceof ProcessDiagramEditor;
        }
        return false;
    }

}
