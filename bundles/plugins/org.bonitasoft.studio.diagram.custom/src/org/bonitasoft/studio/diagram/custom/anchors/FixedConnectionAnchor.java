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
 
package org.bonitasoft.studio.diagram.custom.anchors;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Romain Bioteau
 *
 */
public class FixedConnectionAnchor extends AbstractConnectionAnchor {
	 
	private double xOffset;
	private double yOffset;
 
	public FixedConnectionAnchor(IFigure owner, PrecisionPoint offset) {
		this(owner, offset.preciseX, offset.preciseY);
	}
 
	public FixedConnectionAnchor(IFigure owner, double xOffset, double yOffset) {
		super(owner);
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
 
	public Point getLocation() {
		Rectangle r = getOwner().getBounds();
		Point p = new PrecisionPoint(r.x + r.width*xOffset, r.y + r.height*yOffset);
		getOwner().translateToAbsolute(p);
		return p;
	}
	

	public Point getLocation(Point reference) {
		return getLocation();
	}
 
}
