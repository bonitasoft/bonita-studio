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

package org.bonitasoft.studio.diagram.custom.parts;

import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEvent2EditPart;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class CustomCatchLinkEvent2EditPart extends CatchLinkEvent2EditPart {


	public CustomCatchLinkEvent2EditPart(final View view) {
		super(view);
	}

	
	@Override
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(FiguresHelper.BIG_EVENT_WIDTH, FiguresHelper.BIG_EVENT_WIDTH){
				public PointList getPolygonPoints() {
					Rectangle anchRect = getHandleBounds();
					return FiguresHelper.CirclePointList(anchRect);
				}
		};
		return result;
	}
	
	@Override
	protected void handleNotificationEvent(Notification notification) {
		super.handleNotificationEvent(notification);
		Object feature = notification.getFeature();
		//if name changed
		if(notification.getEventType() == Notification.SET
				&& feature.equals(ProcessPackage.Literals.ELEMENT__NAME)){
			final IGraphicalEditPart topGraphicEditPart = (IGraphicalEditPart) getRoot().getChildren().get(0);
			for(ThrowLinkEvent tle : ((CatchLinkEvent)resolveSemanticElement()).getFrom()){
				/*Get the corresponding ThrowLinkEditpart*/
				final EditPart findEditPart = topGraphicEditPart.findEditPart(topGraphicEditPart, tle);
				if(findEditPart != null){
					/*refresh the label edit part*/
					for(EditPart childPart : (List<EditPart>)findEditPart.getChildren()){
						childPart.refresh();
					}
				}
			}
		}
	}
}
