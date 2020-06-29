/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.engine.server;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @author Romain Bioteau
 */
public class StartEngineJob extends Job {

    public static final Object FAMILY = StartEngineJob.class.getName();

    public StartEngineJob(String name) {
        super(name);
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        try {
            BOSEngineManager.getInstance(monitor).start();
            BonitaNotificator.openNotification(Messages.startServerCompletedNotificationTitle,
                    Messages.serverRunningNotificationMessage);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
            return Status.CANCEL_STATUS;
        }
        return Status.OK_STATUS;
    }

    public static Object getFamily() {
        return FAMILY;
    }

}
