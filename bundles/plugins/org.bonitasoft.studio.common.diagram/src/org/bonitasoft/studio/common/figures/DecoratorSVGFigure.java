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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;

/**
 * @author Romain Bioteau
 *
 */
public class DecoratorSVGFigure extends CustomSVGFigure {

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
			}
			if(document.getElementById("BORDER2") != null){
				document.getElementById("BORDER2").setAttribute("stroke", foregroundColor) ;
			}
			if(document.getElementById("BORDER3") != null){
				document.getElementById("BORDER3").setAttribute("stroke", foregroundColor) ;
			}
			if(document.getElementById("BORDER_STYLE") != null){
				document.getElementById("BORDER_STYLE").setAttribute("stroke", foregroundColor) ;
				document.getElementById("BORDER_STYLE").setAttribute("style", "fill:"+foregroundColor) ;
			}
			if(document.getElementById("BORDER_STYLE2") != null){
				document.getElementById("BORDER_STYLE2").setAttribute("stroke", foregroundColor) ;
				document.getElementById("BORDER_STYLE2").setAttribute("style", "fill:"+foregroundColor) ;
			}
	
			if(document.getElementById("STYLE") != null){
				document.getElementById("STYLE").setAttribute("style", "fill:"+foregroundColor) ;
			}
			if(document.getElementById("STYLE2") != null){
				document.getElementById("STYLE2").setAttribute("style", "fill:"+foregroundColor) ;
			}
			if(document.getElementById("STYLE3") != null){
				document.getElementById("STYLE3").setAttribute("style", "fill:"+foregroundColor) ;
			}
			if(document.getElementById("STYLE4") != null){
				document.getElementById("STYLE4").setAttribute("style", "fill:"+foregroundColor) ;
			}
			if(document.getElementById("FILL") != null){
				document.getElementById("FILL").setAttribute("style", "fill:"+backgroundColor+";fill-opacity:0.6") ;
			}
			
			if(document.getElementById("FILL2") != null){
				document.getElementById("FILL2").setAttribute("style", "fill:"+backgroundColor+";fill-opacity:0.3") ;
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
