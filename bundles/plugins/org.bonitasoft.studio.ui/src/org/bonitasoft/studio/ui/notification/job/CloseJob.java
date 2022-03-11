/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.notification.job;

import org.bonitasoft.studio.ui.i18n.Messages;
import org.bonitasoft.studio.ui.notification.BonitaNotificationPopup;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Shell;

public class CloseJob extends Job {

    private BonitaNotificationPopup notificationPopup;

    public CloseJob(BonitaNotificationPopup notificationPopup) {
        super(Messages.closeNotificationJobTitle);
        this.notificationPopup = notificationPopup;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        if (!notificationPopup.getDisplay().isDisposed()) {
            notificationPopup.getDisplay().asyncExec(new Runnable() {

                @Override
                public void run() {
                    Shell shell = notificationPopup.getShell();
                    if (shell == null || shell.isDisposed()) {
                        return;
                    }

                    if (notificationPopup.isMouseOver(shell)) {
                        scheduleAutoClose();
                        return;
                    }

                    notificationPopup.closeFade();
                }

            });
        }
        if (monitor.isCanceled()) {
            return Status.CANCEL_STATUS;
        }

        return Status.OK_STATUS;
    }

    public void scheduleAutoClose() {
        if (notificationPopup.getDelayClose() > 0) {
            schedule(notificationPopup.getDelayClose());
        }
    }

}
