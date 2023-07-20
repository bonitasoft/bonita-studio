/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Romain Bioteau
 */
public class CustomSVGFigure extends SVGFigure {

    protected String foregroundColor;
    protected String backgroundColor;
    private float strokeWidth;
    private Element border;
    private Node bgStyleNode0;
    private Node bgStyleNode1;
    private boolean foundBgElement = false;

    public Document getSVGDocument() {
        return super.getDocument();
    }

    public void setColor(Color foreground, Color background) {
        setColor(foreground, background, -1);
    }

    public void setColor(Color foreground, Color background, float strokeWidth) {
        String red = Integer.toHexString(foreground.getRed());
        if (red.length() == 1) {
            red = "0" + red;
        }

        String green = Integer.toHexString(foreground.getGreen());
        if (green.length() == 1) {
            green = "0" + green;
        }

        String blue = Integer.toHexString(foreground.getBlue());
        if (blue.length() == 1) {
            blue = "0" + blue;
        }

        String hexaColor = ("#" + red + green + blue).toUpperCase();
        this.foregroundColor = hexaColor;

        red = Integer.toHexString(background.getRed());
        if (red.length() == 1) {
            red = "0" + red;
        }

        green = Integer.toHexString(background.getGreen());
        if (green.length() == 1) {
            green = "0" + green;
        }

        blue = Integer.toHexString(background.getBlue());
        if (blue.length() == 1) {
            blue = "0" + blue;
        }

        hexaColor = ("#" + red + green + blue).toUpperCase();
        this.backgroundColor = hexaColor;
        this.strokeWidth = strokeWidth;

        repaint();
    }

    @Override
    protected void paintFigure(Graphics graphics) {
        double stroke = getPreferredSize().width * (strokeWidth > 0 ? strokeWidth : 2.0) / getClientArea().getSize().width;

        Document document = getDocument();
        if (document != null && foregroundColor != null && backgroundColor != null) {

            if (!foundBgElement) {

                border = document.getElementById("BORDER");
                Element svgid_1_ = document.getElementById("SVGID_1_");
                NodeList stop = svgid_1_.getElementsByTagName("stop");
                bgStyleNode0 = stop.item(0).getAttributes().getNamedItem("style");
                bgStyleNode1 = stop.item(1).getAttributes().getNamedItem("style");
                foundBgElement = true;
            }

            if (border != null) {
                border.setAttribute("stroke", foregroundColor);
                if (stroke > 0) {
                    border.setAttribute("stroke-width", String.valueOf(stroke));
                }
            }

            if (bgStyleNode0 != null) {
                bgStyleNode0.setNodeValue("stop-color:" + backgroundColor);
            }
            if (bgStyleNode1 != null) {
                bgStyleNode1.setNodeValue("stop-color:" + FigureColorProvider.getGradientHexaColor());
            }
        }

        if (document != null) {
            Image image = null;

            try {
                Rectangle r = this.getClientArea();
                this.transcoder.setCanvasSize(this.specifyCanvasWidth ? r.width : -1,
                        this.specifyCanvasHeight ? r.height : -1);
                this.updateRenderingHints(graphics);
                BufferedImage awtImage = this.transcoder.getBufferedImage();
                if (awtImage != null) {
                    image = toSWT(Display.getCurrent(), awtImage);
                    graphics.drawImage(image, r.x, r.y);
                }
            } finally {
                if (image != null) {
                    image.dispose();
                }

                document = null;
            }

        }
    }
}
