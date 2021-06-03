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

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.stream.Stream;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaMarketplace;
import org.bonitasoft.studio.application.views.extension.ProjectExtensionEditorInput;
import org.bonitasoft.studio.application.views.extension.ProjectExtensionEditorPart;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class OpenExtensionViewHandler {

    @Execute
    public void execute() {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (Stream.of(activePage.getEditorReferences())
                .noneMatch(er -> Objects.equals( er.getId(), ProjectExtensionEditorPart.ID))) {
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
                    monitor.beginTask(Messages.fetchingExtensions, IProgressMonitor.UNKNOWN);
                    BonitaMarketplace marketplace = BonitaMarketplace.getInstance(monitor);
                    marketplace.loadDependencies();
                    monitor.done();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(Messages.extensionLoadingErrorTitle, e);
                MessageDialog.openError(Display.getDefault().getActiveShell(),
                        Messages.extensionLoadingErrorTitle, Messages.extensionLoadingError);
            }
        }
        try {
            activePage.openEditor(ProjectExtensionEditorInput.getInstance(), ProjectExtensionEditorPart.ID);
        } catch (PartInitException e) {
            BonitaStudioLog.error(e);
        }
    }

    @CanExecute
    public boolean canExecute() {
        return PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null;
    }

}
