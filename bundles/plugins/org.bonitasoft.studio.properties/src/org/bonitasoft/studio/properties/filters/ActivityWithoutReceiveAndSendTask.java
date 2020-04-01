/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.filters;

import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.ScriptTask;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.ServiceTask;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

/**
 * @author Aurelien Pupier
 *
 */
public class ActivityWithoutReceiveAndSendTask implements IFilter {

	public boolean select(Object object) {
		if (object instanceof IGraphicalEditPart) {
			IGraphicalEditPart editPart = (IGraphicalEditPart) object;
			Object model = editPart.resolveSemanticElement();
			return !(model instanceof ReceiveTask)
			|| model instanceof ScriptTask
			|| !(model instanceof SendTask)
			|| model instanceof ServiceTask
			|| model instanceof CallActivity
			|| model instanceof Task
			;
		}
		return false;
	}

}
