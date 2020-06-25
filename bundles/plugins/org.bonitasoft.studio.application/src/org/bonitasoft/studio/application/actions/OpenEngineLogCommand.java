/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

import java.io.File;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * Command to open the log file of the bonita engine.
 *
 * @author Romain Bioteau
 */
public class OpenEngineLogCommand extends AbstractHandler implements IHandler {

    private static final long MAX_FILE_SIZE = 1000000 * 40; //40Mo

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Boolean execute(final ExecutionEvent event) throws ExecutionException {
        final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        final File logFile = BOSWebServerManager.getInstance().getBonitaLogFile();
        if (logFile != null && logFile.exists()) {
            IFileStore fileStore;
            try {
                fileStore = EFS.getLocalFileSystem().getStore(logFile.toURI());
                final File localFile = fileStore.toLocalFile(EFS.NONE, Repository.NULL_PROGRESS_MONITOR);
                final long fileSize = localFile.length();
                if (fileSize < MAX_FILE_SIZE) {
                    IDE.openEditorOnFileStore(page, fileStore);
                } else {
                    Program textEditor = Program.findProgram("log");
                    if (textEditor == null) {
                        textEditor = Program.findProgram("txt");
                    }
                    if (textEditor != null) {
                        final boolean success = textEditor.execute(localFile.getAbsolutePath());
                        if (!success) {
                            showWarningMessage(localFile);
                        }
                    } else {
                        showWarningMessage(localFile);
                    }
                }

                return Boolean.TRUE;
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
                return Boolean.FALSE;
            }
        } else {
            MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                    Messages.unableTofindLogTitle, Messages.unableTofindLogMessage);
            return Boolean.FALSE;
        }
    }

    protected void showWarningMessage(final File localFile) {
        MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.failedToOpenLogTitle,
                Messages.bind(Messages.failedToOpenLogMessage, localFile.getAbsolutePath(),
                        org.bonitasoft.studio.common.Messages.bonitaStudioModuleName));
    }

}
