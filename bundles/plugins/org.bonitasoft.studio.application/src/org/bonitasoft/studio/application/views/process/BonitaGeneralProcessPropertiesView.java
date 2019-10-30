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
package org.bonitasoft.studio.application.views.process;

import org.bonitasoft.studio.application.views.BonitaPropertiesView;
import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.bonitasoft.studio.diagram.custom.part.BonitaGeneralProcessPropertiesBrowserPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * @author Aurelien Pupier
 */
public class BonitaGeneralProcessPropertiesView extends BonitaPropertiesView {
	
	public static final String VIEW_ID = "org.bonitasoft.studio.views.properties.process.general";
	
	protected String getViewId() {
		return VIEW_ID;
	}
	
	@Override
	protected BonitaPropertiesBrowserPage getBonitaPropertiesBrowserPage(
			ITabbedPropertySheetPageContributor part) {
		return new BonitaGeneralProcessPropertiesBrowserPage(part);
	}
	
	@Override
	protected void doDestroyPage(IWorkbenchPart part, PageRec rec) {
	    if(rec.page instanceof PropertySheetPage) {
	        PropertySheetPage propertySheetPage = (PropertySheetPage) rec.page;
	        if(propertySheetPage.getSite() == null || propertySheetPage.getSite().getWorkbenchWindow() == null || propertySheetPage.getSite().getWorkbenchWindow().getSelectionService() == null){
	            return;
	        }
	    }
	    super.doDestroyPage(part, rec);
	}

}
