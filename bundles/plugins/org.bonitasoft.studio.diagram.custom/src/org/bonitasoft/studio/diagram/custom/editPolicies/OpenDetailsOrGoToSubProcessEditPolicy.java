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
package org.bonitasoft.studio.diagram.custom.editPolicies;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.actions.OpenLatestSubprocessCommand;
import org.bonitasoft.studio.model.process.CallActivity;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier: begin to clean code and add evenemential subprocess support
 *
 */
public class OpenDetailsOrGoToSubProcessEditPolicy extends OpenEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy#getOpenCommand(org.eclipse.gef.Request)
	 */
	@Override
	protected Command getOpenCommand(Request request) {
		return new Command() {
			@Override
			public void execute() {
				try {
					EditPart host = getHost();
					if(host instanceof IGraphicalEditPart){
						EObject semanticElement = ((IGraphicalEditPart) host).resolveSemanticElement();
						if(semanticElement instanceof CallActivity){
							/*select it if in the same diagram or open a the diagram in editor that contains it*/
							new OpenLatestSubprocessCommand().execute(new ExecutionEvent());
						}
					}
				} catch (Exception ex) {
					BonitaStudioLog.error(ex);
				}
			}
		};
	}

}
