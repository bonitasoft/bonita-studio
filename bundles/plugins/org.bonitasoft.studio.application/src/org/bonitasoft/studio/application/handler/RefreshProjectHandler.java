/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.handler;

import java.util.Optional;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.ui.actions.RefreshAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class RefreshProjectHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        getCurrentViewPart().ifPresent(vp -> {
            RefreshAction refreshAction = new RefreshAction(vp.getSite());
            ISelection selection = vp.getViewSite().getSelectionProvider().getSelection();
            if (selection instanceof IStructuredSelection) {
                refreshAction.run((IStructuredSelection) selection);
            }
        });
        return null;
    }

    private Optional<IViewPart> getCurrentViewPart() {
        IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart();
        return Optional.ofNullable(activePart.getAdapter(IViewPart.class));
    }

}
