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
package org.bonitasoft.studio.common.gmf;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;

/**
 * @author Aurelien Pupier
 * Used to customize the BorderItemLocator:
 * - set affixed children on the border
 * - TODO organize order if multiple boundary event
 */
public class ActivityBorderItemLocator extends BorderItemLocator {

	/**
	 * Use to "middle overlap" the affixed child
	 */
	private static final int halfSizeOfAfffixedChild = 8;

	public ActivityBorderItemLocator(IFigure parentFigure) {
		super(parentFigure);
		setBorderItemOffset(new Dimension(halfSizeOfAfffixedChild, halfSizeOfAfffixedChild));
	}

	public ActivityBorderItemLocator(IFigure mainFigure, int preferredSide) {
		super(mainFigure, preferredSide);
		setBorderItemOffset(new Dimension(halfSizeOfAfffixedChild, halfSizeOfAfffixedChild));
	}
	
	/**
	 * Get an initial location based on the side. ( choose first sixth part of the side for SOUTH or call super)
	 * 
	 * @param side
	 *            the preferred side of the parent figure on which to place this
	 *            border item as defined in {@link PositionConstants}
	 * @return point
	 */
	protected Point getPreferredLocation(int side, IFigure borderItem) {
		if (side == PositionConstants.SOUTH) {
			Rectangle bounds = getParentBorder();
			int parentFigureWidth = bounds.width;
			int parentFigureHeight = bounds.height;
			int parentFigureX = bounds.x;
			int parentFigureY = bounds.y;
			int x = parentFigureX;
			int y = parentFigureY;
			/*Play with this for modify the horizontal position on the activity Figure*/
			x += parentFigureWidth / 6;
			y = parentFigureY + parentFigureHeight - getBorderItemOffset().height;
			return new Point(x, y);
		} else {
			return super.getPreferredLocation(side, borderItem);
		}
	}
}
