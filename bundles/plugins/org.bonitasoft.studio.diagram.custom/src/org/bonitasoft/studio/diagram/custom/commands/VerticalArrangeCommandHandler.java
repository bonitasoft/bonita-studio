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
package org.bonitasoft.studio.diagram.custom.commands;

import org.bonitasoft.studio.diagram.custom.actions.VerticalArrangeAction;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class VerticalArrangeCommandHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		VerticalArrangeAction action = new VerticalArrangeAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), true) ;
		action.init();
		action.refresh();
		action.run();
		action.dispose();
		return null;
	}

	@Override
	public boolean isEnabled() {
		VerticalArrangeAction action = new VerticalArrangeAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), true) ;
		action.init();
		action.refresh();
		return action.isEnabled();
	}
	
}
