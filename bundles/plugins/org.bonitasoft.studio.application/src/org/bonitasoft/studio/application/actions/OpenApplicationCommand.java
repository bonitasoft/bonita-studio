/*
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
package org.bonitasoft.studio.application.actions;

import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

public class OpenApplicationCommand extends AbstractHandler implements IHandler {

	
	private String path ;

	public OpenApplicationCommand(){
		super();
	}

	public OpenApplicationCommand(String path){
		this();
		this.path = path ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {


		try{
			
			URL url = getURL();
			
			IWebBrowser browser;
			browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(IWorkbenchBrowserSupport.AS_EDITOR,BonitaPreferenceConstants.APPLICATION_BROWSER_ID,"Bonita Application","");
			//ConsoleManagement.startServer(monitor);
			browser.openURL(url);
			

		}catch (Exception e) {
			BonitaStudioLog.error(e);
			ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					"error",
					"error starting server",
					Status.OK_STATUS);
		}


		return null;
	}

	/**
	 * @param store
	 * @return
	 * @throws MalformedURLException
	 */
	public URL getURL() throws MalformedURLException {
		IPreferenceStore store = getPreferenceStore();
		String port = store.getString(BonitaPreferenceConstants.CONSOLE_PORT);
		URL url = new URL("http://localhost:" + port + path);
		return url;
	}
	
	/**
	 * @return
	 */
	private static IPreferenceStore getPreferenceStore() {
		return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
	}


}