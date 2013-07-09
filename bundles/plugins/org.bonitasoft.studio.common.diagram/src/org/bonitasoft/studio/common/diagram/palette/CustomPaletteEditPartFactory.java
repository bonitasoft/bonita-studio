/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.diagram.palette;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.internal.ui.palette.editparts.ToolbarEditPart;
import org.eclipse.gef.ui.palette.PaletteEditPartFactory;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;

/**
 * @author Romain Bioteau
 *
 */
public class CustomPaletteEditPartFactory extends PaletteEditPartFactory{


	@Override
	protected EditPart createToolbarEditPart(EditPart parentEditPart,
			Object model) {
		ToolbarEditPart ep = (ToolbarEditPart) super.createToolbarEditPart(parentEditPart, model);
		ep.getFigure().setBackgroundColor(FigureUtilities.lighter(ColorConstants.lightGray));
		ep.getFigure().setOpaque(true);
		return ep;
	}
	
}
