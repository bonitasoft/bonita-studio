/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.operation;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class GetApiSessionOperation implements IRunnableWithProgress {

    private APISession session;

    protected IProgressService progressService() {
        return PlatformUI.getWorkbench().getProgressService();
    }

    public APISession execute() {
        try {
            if (!BOSEngineManager.getInstance().isRunning()) {
                progressService().run(true, false, monitor -> {
                    monitor.beginTask(Messages.waitingForEngineToStart, IProgressMonitor.UNKNOWN);
                    BOSEngineManager.getInstance().start();
                    monitor.done();
                });
            }
            run(new NullProgressMonitor());
            return session;
        } catch (InvocationTargetException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        try {
            session = BOSEngineManager.getInstance().loginDefaultTenant(monitor);
        } catch (LoginException | BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
            throw new InvocationTargetException(e, "Failed to login");
        } finally {
            monitor.done();
        }
    }

    public APISession getSession() {
        return session;
    }

    public void logout() {
        if (session != null) {
            BOSEngineManager.getInstance().logoutDefaultTenant(session);
        }
    }

}
