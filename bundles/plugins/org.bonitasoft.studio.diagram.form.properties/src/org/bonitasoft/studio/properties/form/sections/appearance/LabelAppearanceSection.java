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
package org.bonitasoft.studio.properties.form.sections.appearance;

import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Baptiste Mesta
 *
 */
public class LabelAppearanceSection extends AppearancePropertySection {

	private Button left;
	private Button right;
	private Button up;
	private Button down;
	Group group;


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.form.sections.appearance.AppearancePropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		
			super.createControls(parent, aTabbedPropertySheetPage);
			tabLabelContents = getWidgetFactory().createComposite(parent, SWT.NONE);

			tabLabelContents.setLayout(new GridLayout(2, true));

			addSizeGroup(tabLabelContents, ExporterTools.PREFIX_LABEL, 1, 1);
			addCustomStyleGroup(tabLabelContents, ExporterTools.PREFIX_LABEL, 1, 2);
			addFontGroup(tabLabelContents, ExporterTools.PREFIX_LABEL, 1, 1);
			addTextGroup(tabLabelContents, ExporterTools.PREFIX_LABEL, 1, 1);
			addLabelPositionGroup(tabLabelContents, 1, 1);
			
		
	}


	/**
	 * @param tabInputContents2
	 * @param i
	 * @param j
	 */
	protected void addLabelPositionGroup(Composite tabInputContents2, int nbCols, int nbRows) {
		group = getWidgetFactory().createGroup(tabInputContents2, Messages.AppearancePropertySection_LabelPosition);
		group.setLayoutData(GridDataFactory.fillDefaults().span(nbCols, nbRows).create());
		group.setLayout(new RowLayout());
		left = getWidgetFactory().createButton(group, Messages.AppearancePropertySection_Left, SWT.RADIO);
		right = getWidgetFactory().createButton(group, Messages.AppearancePropertySection_Right, SWT.RADIO);
		up = getWidgetFactory().createButton(group, Messages.AppearancePropertySection_Up, SWT.RADIO);
		down = getWidgetFactory().createButton(group,Messages.AppearancePropertySection_Down, SWT.RADIO);

	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.form.sections.appearance.AppearancePropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		bindLabelPositionGroup(left, right, up, down);
	}
	
	@Override
	public void refresh() {
		Widget widget = getWidget();
		if ((widget!=null) && widget instanceof org.bonitasoft.studio.model.form.Group) {
			group.setVisible(false);
		} else {
			group.setVisible(true);
		}
		super.refresh();
	}

}
