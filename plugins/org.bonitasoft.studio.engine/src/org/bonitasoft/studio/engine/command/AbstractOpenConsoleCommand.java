/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.command;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.preferences.BonitaUserXpPreferencePage;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Mickael Istria
 *
 */
public abstract class AbstractOpenConsoleCommand extends AbstractHandler {

    public static final String REFRESH_THEME_PARAMETER = "refreshTheme";
    public static final String CONSOLE_LOCALE = "locale=";
    private URL url;
    protected boolean runSynchronously;
    protected boolean refreshTheme = true ;

    /**
     * @return
     */
    private static IPreferenceStore getPreferenceStore() {
        return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try{
            refreshTheme = (Boolean) (event != null &&  event.getParameters() != null &&  event.getParameters().get(REFRESH_THEME_PARAMETER) != null  ? event.getParameters().get(REFRESH_THEME_PARAMETER) : true) ;
            //close intro
            executeJob();


        }catch (Exception e) {
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

            final IRunnableWithProgress runnable = new IRunnableWithProgress(){
                @Override
                public void run(IProgressMonitor monitor)
                        throws InvocationTargetException, InterruptedException {
                    try{
                        if (!BOSWebServerManager.getInstance().serverIsStarted()) {
                            BOSWebServerManager.getInstance().startServer(monitor);
                        }
                        monitor.beginTask(Messages.initializingUserXP, IProgressMonitor.UNKNOWN);
                        setURL(buildUrl(monitor));
                        if(refreshTheme){
                            String currentTheme = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.DEFAULT_USERXP_THEME) ;
                            String installedTheme = BonitaUserXpPreferencePage.getInstalledThemeId()  ;
                            if(installedTheme != null && !installedTheme.equals(currentTheme)){
                                BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.DEFAULT_USERXP_THEME, currentTheme) ;
                                BonitaUserXpPreferencePage.updateBonitaHome() ;
                            }
                        }
                        new OpenBrowserCommand(url,BonitaPreferenceConstants.CONSOLE_BROWSER_ID,"Bonita User Experience").execute(null); //$NON-NLS-1$
                    }catch(Exception e){
                        BonitaStudioLog.error(e);
                    }finally{
                        monitor.done();
                    }
                }
            };

            if (runSynchronously) {
                runnable.run(new NullProgressMonitor());
            } else {
                final IProgressService progressManager = PlatformUI.getWorkbench().getProgressService() ;
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        try{
                            progressManager.run(true, false, runnable) ;
                        }  catch (Exception e) {
                            BonitaStudioLog.error(e);
                        }

                    }
                });

            }

        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }

    }

    private String buildUrl(IProgressMonitor monitor) throws UnsupportedEncodingException {
        final IPreferenceStore store = getPreferenceStore();
        final String port = store.getString(BonitaPreferenceConstants.CONSOLE_PORT);
        final String host = store.getString(BonitaPreferenceConstants.CONSOLE_HOST);
        final String userName =  store.getString(BonitaPreferenceConstants.USER_NAME);
        final String password = store.getString(BonitaPreferenceConstants.USER_PASSWORD);
        final String locale = store.getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE);
        final String loginUrl = BOSWebServerManager.getInstance().generateLoginURL(userName, password) ;
        final String consoleURl = "http://"+host+":"+ port + getURLRelativePath() +"locale="+locale;
        return loginUrl+"&redirectUrl="+URLEncoder.encode(consoleURl, "UTF-8");
    }

    /**
     * @param store
     * @return
     * @throws MalformedURLException
     */
    public URL getURL() throws MalformedURLException {
        return url;
    }

    /**
     * @param store
     * @return
     * @throws MalformedURLException
     */
    public void setURL(String path) throws MalformedURLException {
        url = new URL(path);
    }

    /**
     * 
     */
    public AbstractOpenConsoleCommand() {
        super();
    }

    /**
     * @return
     */
    protected abstract String getURLRelativePath();

}