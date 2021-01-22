/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
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

package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.gmf.tools.GMFTools.ElementTypeResolver;
import org.bonitasoft.studio.diagram.custom.BonitaNodesElementTypeResolver;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;


/**
 * @author Romain Bioteau
 * @author Aurelien Pupier improve resource management (free handle of image)
 */
public class BoundaryEventSwitchEditPolicy extends ActivitySwitchEditPolicy {




	@Override
	protected List<Pair<IFigure, MouseListener>> getItems(
			IGraphicalEditPart host2) {
		final List<Pair<IFigure, MouseListener>> clickableItems = new ArrayList<Pair<IFigure, MouseListener>>();
		if(host2.getAdapter(NonInterruptingBoundaryTimerEvent.class) != null ){
			 clickableItems.add(createClickableItem(new Point(0, 0),host2, ProcessElementTypes.BoundaryTimerEvent_3043));
		}else  if(host2.getAdapter(BoundaryTimerEvent.class) != null ){
			 clickableItems.add(createClickableItem(new Point(0, 0),host2, ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3064));
		}
		return clickableItems;
	}

	protected ElementTypeResolver getElementTypeResolver() {
		return new BoundaryElementTypeResolver();
	}

	protected Point getIconLocation(Rectangle bounds) {
		return new Point(bounds.getLeft().x, bounds.getBottomRight().y);
	}



}
