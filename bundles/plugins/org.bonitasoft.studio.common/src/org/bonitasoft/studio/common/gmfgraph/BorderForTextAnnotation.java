/** Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmfgraph;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Insets;

/**
 * @author Aurelien Pupier
 * Custom Border in order to respect BPMN Text annotation convention.
 */
public class BorderForTextAnnotation extends MarginBorder {
	
	public BorderForTextAnnotation() {
		/*WARNING : don't set an inset different from 0, it will indeed bugs :
		 * - the figure will enlarge each time you type a letter
		 * - if you resize with a corner and that you go off the screen, the application crash (loop)*/
		super(new Insets(0));
	}

	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		tempRect = getPaintRectangle(figure, insets);

		graphics.setForegroundColor(ColorConstants.black);
		graphics.setLineWidth(5/*insets.left*/);
		//paint the left line
		graphics.drawLine(tempRect.getTopLeft().translate(0, 0), tempRect.getBottomLeft().translate(0, 0));

		
		//paint the top and bottom line
		graphics.drawLine(tempRect.getTopLeft().translate(0, 0),tempRect.getTopLeft().translate(20, 0) );
		graphics.drawLine(tempRect.getBottomLeft().translate(0, -1), tempRect.getBottomLeft().translate(20, -1));
	}

}
