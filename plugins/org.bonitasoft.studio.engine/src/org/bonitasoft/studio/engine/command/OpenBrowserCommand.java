/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * @author Romain Bioteau
 *
 */
public class OpenBrowserCommand extends AbstractHandler {

    public static final String APPLI_PATH = "/application/BonitaApplication.html?"; //$NON-NLS-1$
    private URL url ;
    private String typeId ;
    private String title;
    public OpenBrowserCommand(){
        super();
    }

    public OpenBrowserCommand(URL url,String typeId,String title){
        this.url = url ;
        this.typeId = typeId ;
        this.title = title ;
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                try{
                    IWebBrowser browser;
                    browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(IWorkbenchBrowserSupport.AS_EDITOR,typeId,title,""); //$NON-NLS-1$
                    browser.openURL(url);
                }catch (Exception e) {
                    BonitaStudioLog.error(e);
                    ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                            "error", //$NON-NLS-1$
                            "error starting server", //$NON-NLS-1$
                            Status.OK_STATUS);
                }

            }
        });
        return null;
    }


    /**
     * @param url2
     */
    public void setURL(URL url) {
        this.url = url;
    }
}
