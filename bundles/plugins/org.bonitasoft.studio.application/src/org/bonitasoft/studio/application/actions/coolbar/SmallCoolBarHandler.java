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
package org.bonitasoft.studio.application.actions.coolbar;

import java.lang.reflect.Field;

import org.bonitasoft.studio.application.BonitaStudioWorkbenchWindowAdvisor;
import org.bonitasoft.studio.common.jface.BonitaSashForm;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

/**
 * @author Romain Bioteau
 *
 */
public class SmallCoolBarHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow() ;
		try {
			Field f = WorkbenchWindow.class.getDeclaredField("windowAdvisor") ;
			f.setAccessible(true) ;
			BonitaStudioWorkbenchWindowAdvisor windowAdvisor = (BonitaStudioWorkbenchWindowAdvisor) f.get(window) ;
			windowAdvisor.minimizeCoolbar() ;
		} catch (Exception e) {
			
		}
		
		return null;
	}

}
