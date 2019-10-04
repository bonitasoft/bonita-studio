/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.commands;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.wizard.OpenDiagramWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.PlatformUI;

public class OpenDiagramCommandHandler extends AbstractHandler implements IHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		final OpenDiagramWizard wizard = new OpenDiagramWizard();
		new CustomWizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				wizard,false, Messages.OpenProcessButtonLabel).open();

		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
        if (RepositoryManager.getInstance().hasActiveRepository()) {
            final DiagramRepositoryStore diagramSotre = (DiagramRepositoryStore) RepositoryManager.getInstance()
                    .getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class);
            return !diagramSotre.isEmpty();
        }
        return false;
	}

}
