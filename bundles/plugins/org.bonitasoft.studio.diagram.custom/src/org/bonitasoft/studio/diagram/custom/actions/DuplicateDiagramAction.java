/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.diagram.custom.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.diagram.Identifier;
import org.bonitasoft.studio.common.diagram.dialog.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.diagram.dialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.operation.DuplicateDiagramOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class DuplicateDiagramAction {

    private RepositoryAccessor repositoryAccessor;

    public DuplicateDiagramAction(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    public void duplicate(MainProcess diagram) {
        String newProcessLabel = diagram.getName();
        String newProcessVersion = diagram.getVersion();
        DiagramRepositoryStore diagramRepositoryStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        final OpenNameAndVersionForDiagramDialog dialog = new OpenNameAndVersionForDiagramDialog(
                Display.getDefault().getActiveShell(),
                diagram, diagramRepositoryStore);
        dialog.forceNameUpdate();
        if (dialog.open() == Dialog.OK) {
            final Identifier identifier = dialog.getIdentifier();
            newProcessLabel = identifier.getName();
            newProcessVersion = dialog.getIdentifier().getVersion();
            List<ProcessesNameVersion> pools = dialog.getPools();
            final DuplicateDiagramOperation op = new DuplicateDiagramOperation();
            op.setDiagramToDuplicate(diagram);
            op.setNewDiagramName(newProcessLabel);
            op.setNewDiagramVersion(newProcessVersion);
            op.setPoolsRenamed(pools);
            final IProgressService service = PlatformUI.getWorkbench().getProgressService();
            try {
                service.run(true, false, op);
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            DiagramFileStore store = diagramRepositoryStore.getDiagram(newProcessLabel, newProcessVersion);
            store.open();
        }
    }

}
