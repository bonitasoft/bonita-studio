/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.dependencies.handler;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class AddJarsHandler extends AbstractHandler {

    private String[] filenames = null;

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        final FileDialog fd = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN | SWT.MULTI);
        fd.setFilterExtensions(new String[] { "*.jar;*.zip" });
        if (filenames != null) {
            fd.setFilterNames(filenames);
        }
        if (fd.open() != null) {
            final DependencyRepositoryStore libStore = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
            final String[] jars = fd.getFileNames();
            final IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
            final IRunnableWithProgress runnable = new ImportLibsOperation(libStore, jars, fd.getFilterPath());

            try {

                progressManager.run(true, false, runnable);
                progressManager.run(true, false, new IRunnableWithProgress() {

                    @Override
                    public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                            InterruptedException {
                        RepositoryManager.getInstance().getCurrentRepository().build(monitor);
                    }
                });
            } catch (final InvocationTargetException e1) {
                BonitaStudioLog.error(e1);
                if (e1.getCause() != null && e1.getCause().getMessage() != null) {
                    MessageDialog.openError(Display.getDefault().getActiveShell(), org.bonitasoft.studio.dependencies.i18n.Messages.importJar, e1.getCause()
                            .getMessage());
                }
            } catch (final InterruptedException e2) {
                BonitaStudioLog.error(e2);
            }

        }
        return fd.getFileNames();
    }

    public void setFileNameFilter(final String[] fileNames) {
        filenames = fileNames;
    }

}
