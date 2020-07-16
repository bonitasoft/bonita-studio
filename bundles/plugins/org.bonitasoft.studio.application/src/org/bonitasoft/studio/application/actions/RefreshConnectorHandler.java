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
package org.bonitasoft.studio.application.actions;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Aurelien Pupier
 *
 */
public class RefreshConnectorHandler extends AbstractHandler implements IHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IProgressService service = PlatformUI.getWorkbench().getProgressService() ;
		try {
			service.run(false, false, new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor) throws InvocationTargetException,
				InterruptedException {
					monitor.beginTask(Messages.resfreshConnectors,IProgressMonitor.UNKNOWN) ;
					RepositoryManager.getInstance().getCurrentRepository().build(monitor) ;
				}
			});
		} catch (InvocationTargetException e) {
			BonitaStudioLog.error(e);
		} catch (InterruptedException e) {
			BonitaStudioLog.error(e);
		}
		
		return null;
	}

	
}
