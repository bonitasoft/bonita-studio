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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Baptiste Mesta
 * 
 */
public class ItemsAppearanceSection extends FieldAppearanceSection {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.form.sections.appearance.FieldAppearanceSection#doCreateControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void doCreateControls(Composite parent) {
		tabInputContents = getWidgetFactory().createComposite(parent, SWT.NONE);

		tabInputContents.setLayout(new GridLayout(2, true));

		addSizeGroup(tabInputContents, ExporterTools.PREFIX_ITEMS, 1, 1);
		addCustomStyleGroup(tabInputContents, ExporterTools.PREFIX_ITEMS, 1, 2);
		addFontGroup(tabInputContents, ExporterTools.PREFIX_ITEMS, 1, 1);
		addTextGroup(tabInputContents, ExporterTools.PREFIX_ITEMS, 1, 1);
	}

	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.form.sections.appearance.AppearancePropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
	}

	protected boolean haveHeightAttr() {
		return false;
	}

	protected boolean haveLengthAttr() {
		return false;
	}
	
	
	

}
