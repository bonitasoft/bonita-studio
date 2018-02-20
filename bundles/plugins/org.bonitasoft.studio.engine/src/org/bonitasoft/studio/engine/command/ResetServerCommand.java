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

import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @author Aurelien Pupier
 */
public class ResetServerCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        new Job(Messages.restartingWebServer) {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                BOSWebServerManager.getInstance().resetServer(monitor);
                return Status.OK_STATUS;
            }
        }.schedule();
        return null;
    }

}
