/**
 * Copyright (C) 2009-2014 BonitaSoft S.A.
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

package org.bonitasoft.studio.engine.command;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.UndeployProcessOperation;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;


public class ResetEngineCommand extends AbstractHandler {

    private IStatus status;

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IProgressService progressManager = PlatformUI.getWorkbench().getProgressService() ;
        final IRunnableWithProgress runnable = new IRunnableWithProgress(){

            @Override
            public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                monitor.beginTask(Messages.resetingEngine, IProgressMonitor.UNKNOWN);
                status = new UndeployProcessOperation(BOSEngineManager.getInstance()).undeployAll().run(monitor);
                monitor.done();

            }
        };

        try {
            progressManager.run(true, false, runnable);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
        }
        if (!status.isOK()) {
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.resetEngine, Messages.resetingEngineFailed, status, IStatus.ERROR).open();
        } else {
            MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.resetEngine, Messages.resetEngineSuccess);
        }
        return status;
    }


}
