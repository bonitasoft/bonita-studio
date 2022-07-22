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

import org.eclipse.gef.editparts.SimpleRootEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.ToolEntryEditPart;
import org.eclipse.gef.palette.ToolEntry;

/**
 * @author Romain Bioteau
 *
 */
public class CustomToolPaletteViewer extends CustomMainPaletteViewer {

	private CustomMainPaletteViewer mainPalette;

	public CustomToolPaletteViewer() {
		super();
	}
	
	protected void createDefaultRoot() {
		setRootEditPart(new SimpleRootEditPart());
	}
	
	public void setActiveTool(ToolEntry newMode) {
		if (newMode == null)
			newMode = getPaletteRoot().getDefaultEntry();

		if (activeEntry != null){
			final ToolEntryEditPart toolEntryEditPart = getToolEntryEditPart(activeEntry);
			if(toolEntryEditPart != null){
				toolEntryEditPart.setToolSelected(false);
			}
		}
		activeEntry = newMode;
		if (activeEntry != null) {
			ToolEntryEditPart editpart = getToolEntryEditPart(activeEntry);
			if (editpart != null) {
				editpart.setToolSelected(true);
			}
		}
		fireModeChanged();
		if(mainPalette != null){
			mainPalette.setActiveTool(newMode);	
		}
	}


	public void setMainPaletteViewer(CustomMainPaletteViewer mainPalette) {
		this.mainPalette = mainPalette;
	}

}
