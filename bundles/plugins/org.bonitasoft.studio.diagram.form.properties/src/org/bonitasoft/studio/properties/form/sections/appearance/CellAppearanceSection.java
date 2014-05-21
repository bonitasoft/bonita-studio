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
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.FormPackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Baptiste Mesta
 * 
 */
public class CellAppearanceSection extends AppearancePropertySection {

	protected Button normalButton;
	protected Button labelButton;
	protected SelectionListener buttonStyleListener = new SelectionListener() {

		public void widgetSelected(SelectionEvent e) {
			// update labelBehavior
			boolean labelBehavior = labelButton.getSelection();
			getEditingDomain().getCommandStack().execute(
					new SetCommand(getEditingDomain(), getEObject(), FormPackage.Literals.FORM_BUTTON__LABEL_BEHAVIOR, labelBehavior));

		}

		public void widgetDefaultSelected(SelectionEvent e) {
		}
	};
	private Group group;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.properties.form.sections.appearance.
	 * AppearancePropertySection
	 * #createControls(org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		tabWidgetContents = getWidgetFactory().createComposite(parent, SWT.NONE);
		tabWidgetContents.setLayout(new GridLayout(2, true));
		addSizeGroup(tabWidgetContents, ExporterTools.PREFIX_WIDGET, 1, 1);
		addCustomStyleGroup(tabWidgetContents, ExporterTools.PREFIX_WIDGET, 1, 2);
		labelButton = null;
		normalButton = null;
		addButtonStyle(tabWidgetContents, 1, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.properties.form.sections.appearance.
	 * AppearancePropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
	 * org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if (group != null) {
			group.setVisible(needButtonStyleGroup());
			group.getParent().layout();
			group.layout();
		}
	}

	protected boolean needButtonStyleGroup() {
		return (getEObject() != null && getEObject() instanceof FormButton);
	}

	/**
	 * 
	 * group that contains button style configuration
	 * 
	 */
	protected void addButtonStyle(Composite mainComposite2, int hs, int vs) {
		group = getWidgetFactory().createGroup(mainComposite2, "");

		GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
		group.setLayoutData(gd);
		gd.horizontalSpan = hs;
		gd.verticalSpan = vs;
		gd.widthHint = RIGHT_WIDTH;
		group.setLayout(new GridLayout(3, false));

		Label label = getWidgetFactory().createLabel(group, Messages.AppearancePropertySection_buttonLabelBehavior);
		gd = new GridData(SWT.FILL, SWT.CENTER, false, true);
		label.setLayoutData(gd);

		normalButton = getWidgetFactory().createButton(group, Messages.AppearancePropertySection_buttonNormalStyle, SWT.RADIO);
		normalButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));

		labelButton = getWidgetFactory().createButton(group, Messages.AppearancePropertySection_buttonLabelStyle, SWT.RADIO);
		labelButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));

		normalButton.addSelectionListener(buttonStyleListener);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.properties.form.sections.appearance.
	 * AppearancePropertySection#updateFields()
	 */
	@Override
	protected void updateFields() {
		if (labelButton != null && normalButton != null) {
			boolean label = getEObject() instanceof FormButton && ((FormButton) getEObject()).getLabelBehavior() != null && ((FormButton) getEObject()).getLabelBehavior();
				labelButton.setSelection(label);
				normalButton.setSelection(!label);

		}
		super.updateFields();
	}

}
