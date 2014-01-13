/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.application;

import java.util.Map;

import org.bonitasoft.studio.application.advisor.RecoverWorkspaceAdvisor;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class RecoverWorkspaceApplication implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) throws Exception {
		Map argument = context.getArguments() ;
		Object args = argument.get(IApplicationContext.APPLICATION_ARGS) ;
		String newWorkspaceLocation = null ;
		if(args != null && args instanceof String[]){
			String[] arguments =(String[]) args ;
			for(int i = 0; i < ((String[]) arguments).length ; i++  ){
				if(arguments[i].startsWith("-newWorkspaceLocation")){
					newWorkspaceLocation = arguments[i+1] ;
				}
			}
		}

		BonitaStudioApplication.preStartupStudio();
		PlatformUI.createAndRunWorkbench(Display.getDefault(), new RecoverWorkspaceAdvisor(newWorkspaceLocation)) ;
		return IApplication.EXIT_OK;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	@Override
	public void stop() {
		// NOTHING TO DO
	}

	

}
