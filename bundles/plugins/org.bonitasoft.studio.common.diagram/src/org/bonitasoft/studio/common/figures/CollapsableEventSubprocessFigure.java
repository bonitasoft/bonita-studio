/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class CollapsableEventSubprocessFigure extends RoundedRectangle implements IFigure{

	private Color gradientColor = ColorConstants.white ;
	private boolean useGradient = true ;

	@Override
	protected void outlineShape(Graphics graphics) {
		graphics.setAdvanced(false);
		super.outlineShape(graphics);
	}

	@Override
	protected void fillShape(Graphics graphics) {
		if(useGradient){
			Rectangle r = getBounds().getCopy();
			Point topLeft = r.getTopLeft();
			Point bottomRight = r.getBottomRight();
			Pattern pattern = new Pattern(Display.getCurrent(), topLeft.x +2,
					topLeft.y+2 , bottomRight.x-2, bottomRight.y-2,gradientColor,255,getBackgroundColor(),90);
			graphics.setBackgroundPattern(pattern);
			graphics.fillRectangle(r.crop(new Insets(2,2,2,2)));
			graphics.setBackgroundPattern(null);
			pattern.dispose();
		}else{
			super.fillShape(graphics) ;
		}

	}

	public void setGradientColor(Color gradientColor) {
		this.gradientColor = gradientColor;
	}

	public Color getGradientColor() {
		return gradientColor;
	}

	public void setUseGradient(boolean useGradient) {
		this.useGradient = useGradient;
	}

}
