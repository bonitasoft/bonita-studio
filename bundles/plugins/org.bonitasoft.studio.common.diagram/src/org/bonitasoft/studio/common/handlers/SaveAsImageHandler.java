/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.handlers;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.render.actions.CopyToImageAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class SaveAsImageHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        //Remove Selection
        final DiagramEditor editor = (DiagramEditor) activePage.getActiveEditor();
        editor.getDiagramGraphicalViewer().setSelection(new StructuredSelection());
        final CopyToImageAction act = new CopyToImageAction(activePage) {

            @Override
            protected List createOperationSet() {
                if (getWorkbenchPart() instanceof DiagramEditor) {
                    return Collections.singletonList(((DiagramEditor) getWorkbenchPart()).getDiagramEditPart());
                }
                return Collections.emptyList();
            }

            @Override
            protected void setWorkbenchPart(final IWorkbenchPart workbenchPart) {
                super.setWorkbenchPart(getWorkbenchPage().getActiveEditor());
            }

        };
        act.init();
        act.run();
        return null;
    }

    @Override
    public boolean isEnabled() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof DiagramEditor;
    }
}
