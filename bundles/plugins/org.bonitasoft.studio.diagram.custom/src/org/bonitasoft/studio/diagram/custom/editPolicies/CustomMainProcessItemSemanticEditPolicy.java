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

import org.bonitasoft.studio.model.process.diagram.edit.commands.PoolCreateCommand;
import org.bonitasoft.studio.model.process.diagram.edit.policies.MainProcessItemSemanticEditPolicy;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @author Romain Bioteau
 *
 */
public class CustomMainProcessItemSemanticEditPolicy extends
		MainProcessItemSemanticEditPolicy {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.process.diagram.edit.policies.MainProcessItemSemanticEditPolicy#getCreateCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest)
	 */
	@Override
	protected Command getCreateCommand(CreateElementRequest req) {
		/*
		 * Allow to create only Pool and EventSubProcessPool on the "background"
		 * */
		IElementType elementType = req.getElementType();
		if (ProcessElementTypes.Pool_2007 == elementType) {
			return getGEFWrapper(new PoolCreateCommand(req));
		}

		return UnexecutableCommand.INSTANCE;
	}
	
	

}
