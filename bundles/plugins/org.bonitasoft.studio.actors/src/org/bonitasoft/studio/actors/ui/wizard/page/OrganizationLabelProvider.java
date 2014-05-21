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
package org.bonitasoft.studio.actors.ui.wizard.page;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.preference.ActorsPreferenceConstants;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

final class OrganizationLabelProvider extends
StyledCellLabelProvider implements ILabelProvider {

	private static Styler boldgreen = new StyledString.Styler(){

		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.font = BonitaStudioFontRegistry.getActiveFont();
			textStyle.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
		}
	};

	@Override
	public String getText(Object element) {
		if(element instanceof OrganizationFileStore){
			return ((OrganizationFileStore)element).getDisplayName();
		}else if(element instanceof Organization){
			return ((Organization)element).getName();
		}
		return null;
	}

	@Override
	public void update(ViewerCell cell) {
		String orgaName = getText(cell.getElement());
		String activeOrganization = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(ActorsPreferenceConstants.DEFAULT_ORGANIZATION) ;
		StyledString styledString = new StyledString();
		styledString.append(orgaName, null);
		if(orgaName.equals(activeOrganization)){
			styledString.append("  ("+Messages.active+")",boldgreen);
		}
		cell.setText(styledString.getString());
		cell.setImage(null) ;
		cell.setStyleRanges(styledString.getStyleRanges());
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}
}