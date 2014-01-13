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
package org.bonitasoft.studio.common.properties;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Baptiste Mesta
 *
 */
public abstract class PropertySectionWithTabs extends AbstractModelerPropertySection {

	private ArrayList<AbstractBonitaDescriptionSection> sections;
	private ArrayList<String> titles;
	private CTabFolder folder;

	/**
	 * 
	 */
	public PropertySectionWithTabs() {
		super();
		this.sections = new ArrayList<AbstractBonitaDescriptionSection>();
		this.titles = new ArrayList<String>();
		addSections();
	}
	
	/**
	 * 
	 */
	protected abstract void addSections();
	
	/**
	 * @param section 
	 * 
	 */
	protected void addSection(AbstractBonitaDescriptionSection section, String title) {
		sections.add(section);
		titles.add(title);
	}
	
	public void createControls(org.eclipse.swt.widgets.Composite parent, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		folder = getWidgetFactory().createTabFolder(parent, SWT.MULTI | SWT.TOP);
		Iterator<String> titlesIterator = titles.iterator();
		for (AbstractModelerPropertySection section : sections) {
			CTabItem item = getWidgetFactory().createTabItem(folder, SWT.NONE);
			item.setText(titlesIterator.next());
			section.createControls(folder, aTabbedPropertySheetPage);
			Control[] children = folder.getChildren();
			item.setControl(children[children.length-1]);
		}
		if(folder.getItemCount()>0) {
			folder.setSelection(folder.getItems()[0]);
		}
	}
	
	public void setSelectedTab(int tabIndex){
		folder.setSelection(tabIndex) ;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		for (AbstractModelerPropertySection section : sections) {
			section.setInput(part, selection);
		}
	}
	
	
	
}
