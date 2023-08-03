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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Romain Bioteau
 *
 */
public class DecoratorSVGFigure extends CustomSVGFigure {

	boolean foundBgelements = false;
	private Element border;
	private Element border2;
	private Element border3;
	private Element border_style;
	private Element border_style2;
	private Element style;
	private Element style2;
	private Element style3;
	private Element style4;
	private Element fill;
	private Element fill2;
	private Node style1;

	@Override
	protected void paintFigure(Graphics graphics) {
		Document document = getDocument();

		if(document != null) {

			if(foregroundColor != null && backgroundColor != null) {

				if(!foundBgelements) {

					border = document.getElementById("BORDER");
					border2 = document.getElementById("BORDER2");
					border3 = document.getElementById("BORDER3");
					border_style = document.getElementById("BORDER_STYLE");
					border_style2 = document.getElementById("BORDER_STYLE2");
					style = document.getElementById("STYLE");
					style2 = document.getElementById("STYLE2");
					style3 = document.getElementById("STYLE3");
					style4 = document.getElementById("STYLE4");
					fill = document.getElementById("FILL");
					fill2 = document.getElementById("FILL2");
					final Element svgid_1_ = document.getElementById("SVGID_1_");
					if (svgid_1_ != null) {
						final NodeList stops = svgid_1_.getElementsByTagName("stop");
						style1 = stops.item(0).getAttributes().getNamedItem("style");
					}
				}

				if (border != null) {
					border.setAttribute("stroke", foregroundColor);
				}

				if (border2 != null) {
					border2.setAttribute("stroke", foregroundColor);
				}

				if (border3 != null) {
					border3.setAttribute("stroke", foregroundColor);
				}

				if (border_style != null) {
					border_style.setAttribute("stroke", foregroundColor);
					border_style.setAttribute("style", "fill:" + foregroundColor);
				}

				if (border_style2 != null) {
					border_style2.setAttribute("stroke", foregroundColor);
					border_style2.setAttribute("style", "fill:" + foregroundColor);
				}


				if (style != null) {
					style.setAttribute("style", "fill:" + foregroundColor);
				}

				if (style2 != null) {
					style2.setAttribute("style", "fill:" + foregroundColor);
				}

				if (style3 != null) {
					style3.setAttribute("style", "fill:" + foregroundColor);
				}

				if (style4 != null) {
					style4.setAttribute("style", "fill:" + foregroundColor);
				}

				if (fill != null) {
					fill.setAttribute("style", "fill:" + backgroundColor + ";fill-opacity:0.6");
				}


				if (fill2 != null) {
					fill2.setAttribute("style", "fill:" + backgroundColor + ";fill-opacity:0.3");
				}

				if(style1 !=null) {
					style1.setNodeValue("stop-color:" + backgroundColor);
					//stops.item(1).getAttributes().getNamedItem("style").setNodeValue("stop-color:#FFFFFF");
				}
			}

			Image image = null;

			try {
				final Rectangle r = this.getClientArea();
				this.transcoder.setCanvasSize(this.specifyCanvasWidth?r.width:-1, this.specifyCanvasHeight?r.height:-1);
				this.updateRenderingHints(graphics);
				final BufferedImage awtImage = this.transcoder.getBufferedImage();
				if(awtImage != null) {
					image = toSWT(Display.getCurrent(), awtImage);
					graphics.drawImage(image, r.x, r.y);
				}
			} finally {
				if(image != null) {
					image.dispose();
				}

				document = null;
			}

		}
	}

}
