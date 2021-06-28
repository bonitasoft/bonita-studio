/**
 * Copyright (C) 2011 BonitaSoft S.A.
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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.LineAttributes;

/**
 * @author Romain Bioteau
 */
public class CollapsableEventSubprocessFigure extends RoundedRectangle {

    private Color gradientColor = FigureColorProvider.getGradientColor();
    private boolean useGradient = true;

    @Override
    protected void outlineShape(Graphics graphics) {
        graphics.setAdvanced(true);
        this.setLineAttributes(new LineAttributes(2, SWT.CAP_FLAT, SWT.JOIN_ROUND));
        setLineStyle(SWT.LINE_DOT);
        super.outlineShape(graphics);
    }

    @Override
    protected void fillShape(Graphics graphics) {
        if (!useGradient) {
            super.fillShape(graphics);
        } else {
            paintGradient(graphics);
        }
    }

    private void paintGradient(Graphics graphics) {
        Rectangle rect = getClientArea();
        if (rect == null)
            return;

        graphics.pushState();

        Color from = gradientColor;
        Color to = getBackgroundColor();

        graphics.setForegroundColor(from);
        graphics.setBackgroundColor(to);

        graphics.fillGradient(rect.getCopy().shrink(new Insets(2, 2, 2, 2)), false);
        graphics.popState();
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
