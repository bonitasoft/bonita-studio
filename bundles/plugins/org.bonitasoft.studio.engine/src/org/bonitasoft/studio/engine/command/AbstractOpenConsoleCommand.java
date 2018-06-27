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

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.PortalURLBuilder;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Mickael Istria
 */
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
            //close intro
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
        try {

            final IRunnableWithProgress runnable = new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor)
                        throws InvocationTargetException, InterruptedException {
                    try {
                        monitor.beginTask(Messages.initializingUserXP, IProgressMonitor.UNKNOWN);
                        BOSEngineManager.getInstance().start();
                        setURL(getURLBuilder().toURL(monitor));
                        if (!runSynchronously) {
                            new OpenBrowserOperation(url).execute(); //$NON-NLS-1$
                        }
                    } catch (final Exception e) {
                        BonitaStudioLog.error(e);
                    } finally {
                        monitor.done();
                    }
                }
            };

            if (runSynchronously) {
                runnable.run(new NullProgressMonitor());
            } else {
                final IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            progressManager.run(true, false, runnable);
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e);
                        }

                    }
                });

            }

        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }

    }

    protected PortalURLBuilder getURLBuilder() {
        return new PortalURLBuilder();
    }

    public URL getURL() throws MalformedURLException {
        return url;
    }

    public void setURL(final URL url) throws MalformedURLException {
        this.url = url;
    }

}
