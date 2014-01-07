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
package org.bonitasoft.studio.common.figures;

import java.awt.image.BufferedImage;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.lite.svg.SVGFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;

/**
 * @author Romain Bioteau
 *
 */
public class CustomSVGFigure extends SVGFigure {

	protected String foregroundColor ;
	protected String backgroundColor ;
	private float strokeWidth;

	public Document getSVGDocument(){
		return super.getDocument() ;
	}
	
	public void setColor(Color foreground,Color background) {
		setColor(foreground, background, -1) ;
	}
	public void setColor(Color foreground,Color background,float strokeWidth) {
		String red = Integer.toHexString(foreground.getRed()) ;
		if(red.length() == 1) {
			red = "0"+red ;
		}

		String green = Integer.toHexString(foreground.getGreen()) ;
		if(green.length() == 1) {
			green = "0"+green ;
		}

		String blue = Integer.toHexString(foreground.getBlue()) ;
		if(blue.length() == 1) {
			blue = "0"+blue ;
		}

		String hexaColor = ("#"+red+green+blue).toUpperCase() ;
		this.foregroundColor = hexaColor ;
		
		red = Integer.toHexString(background.getRed()) ;
		if(red.length() == 1) {
			red = "0"+red ;
		}

		green = Integer.toHexString(background.getGreen()) ;
		if(green.length() == 1) {
			green = "0"+green ;
		}

		blue = Integer.toHexString(background.getBlue()) ;
		if(blue.length() == 1) {
			blue = "0"+blue ;
		}

		hexaColor = ("#"+red+green+blue).toUpperCase() ;
		this.backgroundColor = hexaColor ;
		this.strokeWidth = strokeWidth ;
		repaint() ;
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		Document document = getDocument();
		if (document == null) {
			return;
		}

		if(foregroundColor != null && backgroundColor != null){
			
			if(document.getElementById("BORDER") != null){
				document.getElementById("BORDER").setAttribute("stroke", foregroundColor) ;
				if(strokeWidth > 0){
					document.getElementById("BORDER").setAttribute("stroke-width", String.valueOf(strokeWidth)) ;
				}
			}
			
			if(document.getElementById("SVGID_1_") != null){
				document.getElementById("SVGID_1_").getElementsByTagName("stop").item(0).getAttributes().getNamedItem("style").setNodeValue("stop-color:"+backgroundColor) ;
				document.getElementById("SVGID_1_").getElementsByTagName("stop").item(1).getAttributes().getNamedItem("style").setNodeValue("stop-color:#FFFFFF") ;
			}
		}
		Image image = null;
		try {
			Rectangle r = getClientArea();
			transcoder.setCanvasSize(specifyCanvasWidth ? r.width : -1, specifyCanvasHeight ? r.height : -1);
			updateRenderingHints(graphics);
			BufferedImage awtImage = transcoder.getBufferedImage();
			if (awtImage != null) {
				image = toSWT(Display.getCurrent(), awtImage);
				graphics.drawImage(image, r.x, r.y);
			}
		} finally {
			if (image != null) {
				image.dispose();
			}

			document = null ;
		}
	}

}
