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
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
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
public class TableHeadersAppearanceSection extends AppearancePropertySection {

	private Button leftHeaderButton;
	private Button topHeaderButton;
	private Button rightHeaderButton;
	private Button bottomHeaderButton;


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.form.sections.appearance.AppearancePropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		tabInputContents = getWidgetFactory().createComposite(parent, SWT.NONE);

		tabInputContents.setLayout(new GridLayout(2, true));

		addSizeGroup(tabInputContents, ExporterTools.PREFIX_TABLE_HEADERS, 1, 1);
		addCustomStyleGroup(tabInputContents, ExporterTools.PREFIX_TABLE_HEADERS, 1, 2);
		addFontGroup(tabInputContents, ExporterTools.PREFIX_TABLE_HEADERS, 1, 1);
		addTextGroup(tabInputContents, ExporterTools.PREFIX_TABLE_HEADERS, 1, 1);
		addHeaderGroup(tabInputContents, 1, 1);

	}
	

	/**
	 * @param tabInputContents2
	 * @param i
	 * @param j
	 */
	protected void addHeaderGroup(Composite tabInputContents2, int nbCols, int nbRows) {
		Group group = getWidgetFactory().createGroup(tabInputContents2, Messages.headers);
		GridData gd = new GridData(SWT.DEFAULT, SWT.FILL, false, false);
		group.setLayoutData(gd);
		
		group.setLayout(new RowLayout());
		leftHeaderButton = getWidgetFactory().createButton(group, "", SWT.TOGGLE);
		leftHeaderButton.setImage(Pics.getImage(PicsConstants.headingLeft));
		topHeaderButton = getWidgetFactory().createButton(group, "", SWT.TOGGLE);
		topHeaderButton.setImage(Pics.getImage(PicsConstants.headingTop));
		rightHeaderButton = getWidgetFactory().createButton(group, "", SWT.TOGGLE);
		rightHeaderButton.setImage(Pics.getImage(PicsConstants.headingRight));
		bottomHeaderButton = getWidgetFactory().createButton(group, "", SWT.TOGGLE);
		bottomHeaderButton.setImage(Pics.getImage(PicsConstants.headingBottom));
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.form.sections.appearance.AppearancePropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		bindHeaderButtons();
	}
	

	/**
	 * @param leftHeaderButton
	 * @param topHeaderButton
	 * @param rightHeaderButton
	 * @param bottomHeaderButton
	 */
	protected void bindHeaderButtons() {
		databindingContext.bindValue(SWTObservables.observeSelection(topHeaderButton), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__FIRST_ROW_IS_HEADER));
		databindingContext.bindValue(SWTObservables.observeSelection(leftHeaderButton), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__LEFT_COLUMN_IS_HEADER));
		databindingContext.bindValue(SWTObservables.observeSelection(rightHeaderButton), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__RIGHT_COLUMN_IS_HEADER));
		databindingContext.bindValue(SWTObservables.observeSelection(bottomHeaderButton), EMFEditObservables.observeValue(getEditingDomain(), getEObject(), FormPackage.Literals.ABSTRACT_TABLE__LAST_ROW_IS_HEADER));
	}
	
	
}
