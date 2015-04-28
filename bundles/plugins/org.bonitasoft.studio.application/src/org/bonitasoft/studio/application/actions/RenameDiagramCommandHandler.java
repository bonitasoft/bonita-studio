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

import org.bonitasoft.studio.common.diagram.Identifier;
import org.bonitasoft.studio.common.diagram.dialog.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.operation.RenameDiagramOperation;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class RenameDiagramCommandHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final MainProcess diagram = getMainProcess();
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final OpenNameAndVersionForDiagramDialog nameDialog = new OpenNameAndVersionForDiagramDialog(Display.getDefault().getActiveShell(), diagram,
                diagramStore);
        if (nameDialog.open() == Dialog.OK) {
            final DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

            EObject currentObject = editor.getDiagramEditPart().resolveSemanticElement();
            while (!(currentObject instanceof MainProcess)) {
                currentObject = currentObject.eContainer();
            }
            final MainProcess newProcess = (MainProcess) currentObject;
            final RenameDiagramOperation renameDiagramOperation = new RenameDiagramOperation();
            renameDiagramOperation.setEditor(editor);
            renameDiagramOperation.setDiagramToDuplicate(newProcess);
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
        return getMainProcess() != null;
    }

    protected MainProcess getMainProcess() {
        if (PlatformUI.isWorkbenchRunning()) {
            final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if (editor instanceof ProcessDiagramEditor || editor instanceof FormDiagramEditor) {
                final EObject mainElement = ((DiagramEditor) editor).getDiagramEditPart().resolveSemanticElement();
                final MainProcess diagram = ModelHelper.getMainProcess(mainElement);
                return diagram;
            }
        }
        return null;
    }
}
