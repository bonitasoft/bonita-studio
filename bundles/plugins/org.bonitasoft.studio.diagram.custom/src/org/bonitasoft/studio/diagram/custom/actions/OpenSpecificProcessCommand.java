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
package org.bonitasoft.studio.diagram.custom.actions;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * @author Mickael Istria
 *
 */
public class OpenSpecificProcessCommand extends AbstractHandler {

	public static final String ID = "org.bonitasoft.studio.diagram.custom.openProcessComman";

	public static final String PARAMETER_PROCESS_NAME = "processName";
	public static final String PARAMETER_PROCESS_VERSION = "processVersion";

	private DiagramRepositoryStore diagramSotre;

	public OpenSpecificProcessCommand() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IRepository repository = RepositoryManager.getInstance().getCurrentRepository() ;
		diagramSotre = (DiagramRepositoryStore) repository.getRepositoryStore(DiagramRepositoryStore.class) ;
		try {
			if (event.getParameter(PARAMETER_PROCESS_NAME) != null &&
					event.getParameter(PARAMETER_PROCESS_VERSION) != null) {
				for(IRepositoryFileStore file : diagramSotre.getChildren()){
					for(AbstractProcess p : ((DiagramFileStore)file).getProcesses(true)){
						if(p.getName().equals(event.getParameter(PARAMETER_PROCESS_NAME))
								&& p.getVersion().equals(event.getParameter(PARAMETER_PROCESS_VERSION))){
							file.open();
							return null ;
						}
					}
				}
			} else {
				return null;
			}
		} catch (Exception ex) {
			throw new ExecutionException("Could not open process", ex);
		}
		return null;
	}

}
