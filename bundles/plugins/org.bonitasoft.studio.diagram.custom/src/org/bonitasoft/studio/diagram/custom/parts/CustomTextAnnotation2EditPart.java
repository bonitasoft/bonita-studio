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

import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotation2EditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @author Aurelien Pupier
 * Custom the EditPart in order to allow wrap text and a gradient on the figure.
 */
public class CustomTextAnnotation2EditPart extends TextAnnotation2EditPart {

	public CustomTextAnnotation2EditPart(View view) {
		super(view);
	}
	
	/**
	 * @author Aurelien Pupier
	 * Use a custom NoteFigureDescriptor in order to set a gradient
	 */
	public class CustomNoteFigureDescriptor extends NoteFigureDescriptor{
		public void paintGradient(Graphics graphics) {                     
			 Rectangle rect = getClientArea();
			 if (rect == null)
				 return;

			 graphics.pushState();
			 Color from = ColorConstants.white;
			 Color to = getBackgroundColor();

			 graphics.setForegroundColor(from);
			 graphics.setBackgroundColor(to);

			 graphics.fillGradient(rect, false);

			 graphics.popState();
			 //now drawing the outline shape
			 super.outlineShape(graphics);
		 }

		 protected void paintClientArea(Graphics graphics) {
			 paintGradient(graphics);
			 super.paintClientArea(graphics);
		 }
	}
	
	/** 
	 * Use a CustomFigureDescriptor in order to have gradient.
	 * @see org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotation2EditPart#createNodeShape()
	 */
	protected IFigure createNodeShape() {
		NoteFigureDescriptor figure = new CustomNoteFigureDescriptor();
		return primaryShape = figure;
	}
	
	/**
	 * Allow to reduce size (avoid GMF bug) but there is no feedback.
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart#refreshBounds()
	 */
	@Override
	protected void refreshBounds() {
		int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
		int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
		Dimension size = new Dimension(width, height);
		getFigure().setMinimumSize(size);
		int x = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
		int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
		

		((GraphicalEditPart) getParent()).setLayoutConstraint(
			this,
			getFigure(),
			new Rectangle(new Point(x,y), size));
	}
	
	
	
	/**
	 * Provide The figure in order to be used in feedback
	 * @return
	 */
	public IFigure createCustomNoteFigureDescriptor() {
		return new CustomNoteFigureDescriptor();
	}
	
}
