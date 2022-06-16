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

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.DirectoryAppURLBuilder;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PlatformUI;


public abstract class AbstractOpenConsoleCommand extends AbstractHandler {

    public static final String REFRESH_THEME_PARAMETER = "refreshTheme";
    public static final String CONSOLE_LOCALE = "locale=";
    private URL url;
    protected boolean runSynchronously;
    protected boolean refreshTheme = true;

    public AbstractOpenConsoleCommand() {
        super();
    }

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        try {
            executeJob();
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                    "error",
                    "error starting server",
                    Status.OK_STATUS);
        }

        return null;
    }

    private void executeJob() {
        if (runSynchronously) {
            doOpen(AbstractRepository.NULL_PROGRESS_MONITOR);
        } else {
            Job job = new Job(Messages.waitingForEngineToStart) {

                @Override
                public IStatus run(IProgressMonitor monitor) {
                    return doOpen(monitor);
                }
            };
            job.setUser(true);
            job.schedule();
        }
    }

    private IStatus doOpen(IProgressMonitor monitor) {
        monitor.beginTask(Messages.waitingForEngineToStart, IProgressMonitor.UNKNOWN);
        BOSEngineManager.getInstance().start();
        try {
            setURL(getURLBuilder().toURL(monitor));
        } catch (MalformedURLException | UnsupportedEncodingException | URISyntaxException e) {
            return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
        }
        if (!runSynchronously) {
            new OpenBrowserOperation(url).execute(); //$NON-NLS-1$
        }
        return Status.OK_STATUS;
    }

    protected DirectoryAppURLBuilder getURLBuilder() {
        return new DirectoryAppURLBuilder();
    }

    public URL getURL() {
        return url;
    }

    public void setURL(final URL url) {
        this.url = url;
    }

}
