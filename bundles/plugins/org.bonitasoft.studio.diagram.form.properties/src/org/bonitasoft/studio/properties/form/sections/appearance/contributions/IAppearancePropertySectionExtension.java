/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.appearance.contributions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 *
 */
public interface IAppearancePropertySectionExtension {
	
	public String TAB_ID_INPUT = "input";
	public String TAB_ID_LABEL = "label";
	public String TAB_ID_WIDGET = "widget";
	public String TAB_ID_TABLE_CELLS = "tableCells";
	public String TAB_ID_TABLE_HEADERS = "tableHeaders";
	
	/**
	 * Choose among TAB_ID* attributes.
	 * @return the sectionId
	 */
	public String getTabId();


	/**
	 * @param composite
	 * @param tabbedPropertySheetWidgetFactory 
	 * @return
	 */
	public Map<Widget,String> createWidgets(Composite composite, TabbedPropertySheetWidgetFactory tabbedPropertySheetWidgetFactory, EObject target);
	
	/**
	 * 
	 * @return the group name
	 */
	public String getGroupName();
	
	
	/**
	 * 
	 * @param target
	 * @return if the contribution is relevant for the target and should appear
	 */
	public boolean isRelevantFor(EObject target);
	
}
