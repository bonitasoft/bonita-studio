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
package org.bonitasoft.studio.engine.command;

import java.io.IOException;

import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.EngineNotificationSemaphore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class RestartServerCommand extends AbstractHandler {

    private static final String DROP_DB_KEY = "DROP_ENGINE_DB";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final IPreferenceStore store = EnginePlugin.getDefault().getPreferenceStore();
        MessageDialogWithPrompt.open(MessageDialog.INFORMATION,
                Display.getDefault().getActiveShell(),
                Messages.restartServer,
                Messages.restartServerMsg,
                null,
                Messages.dropEngineDatabase,
                false,
                store,
                DROP_DB_KEY,
                SWT.NONE);
        new Job(Messages.restartingWebServer) {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                boolean notifying = notifyRestartServer();
                BOSEngineManager.getInstance().stop();
                if (store.getBoolean(DROP_DB_KEY)) {
                    DatabaseHandler databaseHandler = RepositoryManager.getInstance().getCurrentRepository()
                            .getDatabaseHandler();
                    try {
                        databaseHandler.removeEngineDatabase();
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
                BOSEngineManager.getInstance().start();
                if (notifying) {
                    BonitaNotificator.openNotification(Messages.restartServerCompletedNotificationTitle,
                            Messages.serverRunningNotificationMessage);
                    EngineNotificationSemaphore.getInstance().release();
                }
                return Status.OK_STATUS;
            }

            private boolean notifyRestartServer() {
                if (EngineNotificationSemaphore.getInstance().tryAcquire()) {
                    BonitaNotificator.openNotification(Messages.restartServerNotificationTitle,
                            Messages.restartServerNotificationMessage);
                    return true;
                }
                return false;
            }
        }.schedule();
        return null;
    }

}
