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
 
package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.common.ui.dialogs.PopupDialog;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateOrSelectElementCommand;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public class CustomCreateOrSelectElementCommand extends
		CreateOrSelectElementCommand {

	public static final int NONE = 0;
	private IFigure layer;

	public CustomCreateOrSelectElementCommand(String createCommandLabel,
			Shell activeShell, List<?> validTypes) {
		super(createCommandLabel, activeShell, validTypes) ;
	}
	
	public CustomCreateOrSelectElementCommand(String label, Shell parentShell,
			List<?> content, int style) {
		super(label, parentShell,content,style);
		if (style == POPUP_DIALOG) {
			setPopupDialog(new PopupDialog(parentShell, content,
				getLabelProvider()));
		} else if(style == NONE) {
		
		}else{
			setPopupMenu(new CustomPopupMenu(content, getLabelProvider()));
		}
	}

	public CustomCreateOrSelectElementCommand(String createCommandLabel,Shell activeShell, List<?> validTypes, IFigure layer) {
		super(createCommandLabel, activeShell,validTypes);
		setPopupMenu(null);
		setLayerFigure(layer);
	}
	
	public void setLayerFigure(IFigure layer){
		this.layer = layer ;
	}
	
	public IFigure getLayerFigure(){
		return this.layer ;
	}
	
}
