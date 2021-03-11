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

package org.bonitasoft.studio.connectors.ui.provider;

import java.util.Objects;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;


public class DatabaseDriversLabelProvider extends  StyledCellLabelProvider implements ILabelProvider{

	private String defaultDriver;
	private Styler boldgreen;

	public DatabaseDriversLabelProvider(){
		super();
		boldgreen = new StyledString.Styler(){

			@Override
			public void applyStyles(TextStyle textStyle) {
				textStyle.font = BonitaStudioFontRegistry.getActiveFont();
				textStyle.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
			}
		};
	}
	
	public void setDefaultDriver(String driver){
		defaultDriver = driver;
	}

	@Override
	public String getText(Object object){
		return (String) object;
	}


	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public void update(ViewerCell cell) {
		if (cell.getElement() instanceof String) {
			String jarName = (String)cell.getElement();
			StyledString styledString = new StyledString();
			styledString.append(jarName, null);
			if (isActive(jarName)){
				styledString.append(" (Active)", boldgreen);
			}
			cell.setText(styledString.getString());
			cell.setStyleRanges(styledString.getStyleRanges());
		}
	}

    private boolean isActive(String jarName) {
        return Objects.equals(jarName, defaultDriver);
    }
}
