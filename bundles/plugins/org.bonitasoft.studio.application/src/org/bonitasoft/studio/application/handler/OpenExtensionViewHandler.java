/**
 * Copyright (C) 2021 Bonitasoft S.A.
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

import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorInput;
import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorPart;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class OpenExtensionViewHandler {

    @Execute
    public void execute() {
        Optional.ofNullable(PlatformUI.getWorkbench())
                .map(IWorkbench::getActiveWorkbenchWindow)
                .map(IWorkbenchWindow::getActivePage)
                .ifPresent(activePage -> {
                    try {
                        IEditorPart editor = activePage.openEditor(ProjectOverviewEditorInput.getInstance(),
                                ProjectOverviewEditorPart.ID);
                        if (editor instanceof ProjectOverviewEditorPart) {
                            ((ProjectOverviewEditorPart) editor).openExtensionsView();
                        }
                    } catch (PartInitException e) {
                        BonitaStudioLog.error(e);
                    }
                });
    }

    @CanExecute
    public boolean canExecute() {
        return PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null;
    }

}
