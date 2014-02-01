/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.handler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ImportConnectorArchiveOperation;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Mickael Istria
 *
 */
public class ImportConnectorHandler extends AbstractHandler {

    /* (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            FileDialog fileDialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
            fileDialog.setFilterExtensions(new String[] {"*.zip"});
            fileDialog.setText(Messages.importConnectorArchive);
            final String fileName = fileDialog.open();
            IProgressService service = PlatformUI.getWorkbench().getProgressService() ;
            service.run(true, false,new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException,
                InterruptedException {
                    try{
                        if (fileName != null) {
                            monitor.beginTask(Messages.importingConnectorArchive,IProgressMonitor.UNKNOWN) ;
                            File zipFile = new File(fileName);
                            ImportConnectorArchiveOperation importOp = new ImportConnectorArchiveOperation() ;
                            importOp.setFile(zipFile) ;
                            final IStatus status = importOp.run(monitor) ;
                            if(IStatus.OK  == status.getSeverity()){
                                Display.getDefault().syncExec(new Runnable() {
                                    @Override
                                    public void run() {
                                        MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.importSuccessfulTitle, Messages.importSuccessfulMsg) ;
                                    }
                                }) ;
                            }else{
                                Display.getDefault().syncExec(new Runnable() {
                                    @Override
                                    public void run() {
                                        MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.importFailedTitle,Messages.bind(Messages.importFailedMsg,status.getMessage())) ;
                                    }
                                }) ;
                            }
                        }
                    }catch (Exception e) {
                        throw new InvocationTargetException(e);
                    }

                }
            });

            return null;
        } catch (Exception ex) {
            throw new ExecutionException(ex.getMessage(), ex);
        }
    }

}
