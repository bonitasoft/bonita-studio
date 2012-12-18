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
package org.bonitasoft.studio.userguidance.command;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.userguidance.i18n.Messages;
import org.bonitasoft.studio.userguidance.wizards.UserGuideWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.common.ui.util.WindowUtil;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class OpenUserGuidanceCommandHandler extends AbstractHandler {

	private static CustomWizardDialog dialog ;

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if(dialog == null){
			try{
				dialog = new CustomWizardDialog( Display.getDefault().getActiveShell(), new UserGuideWizard(), Messages.close,false) ;
				dialog.setAskWhenShellCloses(false) ;
				dialog.create() ;
				WindowUtil.centerDialog(dialog.getShell(), Display.getDefault().getActiveShell()) ;
				dialog.open();
			}finally{
				dialog = null ; 
			}
		}
		return null;
	}

}
