/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.diagram.palette;

import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.internal.ui.palette.editparts.SliderPaletteEditPart;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteEditPartFactory;
import org.eclipse.gef.ui.palette.editparts.PaletteEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;

/**
 * @author Romain Bioteau
 */
public class CustomPaletteEditPartFactory extends PaletteEditPartFactory {

    @Override
    protected EditPart createToolbarEditPart(EditPart parentEditPart,
            Object model) {
        PaletteEditPart ep = (PaletteEditPart) super.createToolbarEditPart(parentEditPart, model);
        ep.getFigure().setBackgroundColor(
                isDarkTheme() ? FigureUtilities.lighter(ColorConstants.darkGray)
                        : FigureUtilities.lighter(ColorConstants.lightGray));
        ep.getFigure().setOpaque(true);
        return ep;
    }

    @Override
    protected EditPart createDrawerEditPart(EditPart parentEditPart,
            Object model) {
        return new CustomDrawerEditPart((PaletteDrawer) model);
    }

    @Override
    protected EditPart createMainPaletteEditPart(EditPart parentEditPart,
            Object model) {
        return new SliderPaletteEditPart((PaletteRoot) model) {

            @Override
            public IFigure createFigure() {
                Figure figure = new Figure();
                figure.setOpaque(true);
                figure.setForegroundColor(ColorConstants.listForeground);
                figure.setBackgroundColor(isDarkTheme() ? ColorConstants.darkGray : ColorConstants.white);
                return figure;
            }
        };
    }

    private boolean isDarkTheme() {
        return PreferenceUtil.isDarkTheme();
    }

}
