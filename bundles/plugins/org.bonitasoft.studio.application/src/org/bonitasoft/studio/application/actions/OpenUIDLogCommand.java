/**
 * Copyright (C) 2018 BonitaSoft S.A.
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

import java.io.File;
import java.util.Optional;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
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

public class OpenUIDLogCommand extends AbstractHandler implements IHandler {

    private static final long MAX_FILE_SIZE = 1000000 * 40; //40Mo

    @Override
    public Boolean execute(ExecutionEvent event) throws ExecutionException {
        final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        final Optional<File> logFile = UIDesignerServerManager.getInstance().getLogFile();
        if (logFile.isPresent() && logFile.get().exists()) {
            try {
                IFileStore fileStore = EFS.getLocalFileSystem().getStore(logFile.get().toURI());
                final File localFile = fileStore.toLocalFile(EFS.NONE, AbstractRepository.NULL_PROGRESS_MONITOR);
                final long fileSize = localFile.length();
                if (fileSize < MAX_FILE_SIZE) {
                    IDE.openEditorOnFileStore(page, fileStore);
                } else {
                    Program textEditor = Program.findProgram("log");
                    if (textEditor == null) {
                        textEditor = Program.findProgram("txt");
                    }
                    if (textEditor == null || !textEditor.execute(localFile.getAbsolutePath())) {
                        showWarningMessage(localFile);
                    }
                }
                return true;
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
                return false;
            }
        }
        MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                Messages.unableTofindLogTitle, Messages.unableTofindLogMessage);
        return false;
    }

    protected void showWarningMessage(File localFile) {
        MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.failedToOpenLogTitle,
                Messages.bind(Messages.failedToOpenLogMessage, localFile.getAbsolutePath(),
                        org.bonitasoft.studio.common.Messages.bonitaStudioModuleName));
    }

}
