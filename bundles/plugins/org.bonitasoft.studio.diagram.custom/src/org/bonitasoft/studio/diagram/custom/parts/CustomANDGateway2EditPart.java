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

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGateway2EditPart;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class CustomANDGateway2EditPart extends ANDGateway2EditPart {


	public CustomANDGateway2EditPart(final View view) {
		super(view);
	}
	
	@Override
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(FiguresHelper.GATEWAY_WIDTH, FiguresHelper.GATEWAY_WIDTH) {
			public PointList getPolygonPoints() {
				PointList points = new PointList(5);
				Rectangle anchRect = getHandleBounds();
				points.addPoint(anchRect.x+anchRect.width/2,anchRect.y);                         
				points.addPoint(anchRect.x + anchRect.width, anchRect.y+anchRect.height/2);                        
				points.addPoint(anchRect.x+anchRect.width/2,anchRect.y+anchRect.height); 
				points.addPoint(anchRect.x, anchRect.y+anchRect.height/2);       
				points.addPoint(anchRect.x+ anchRect.width/2,anchRect.y);
				return points;
			}
		};
		return result;
	}


}
