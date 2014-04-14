/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 * @author Aurelien Pupier - allow to create a data from here
 */
public class MaxItemsSectionContribution implements IExtensibleGridPropertySectionContribution {

	protected Widget element;
	protected TransactionalEditingDomain editingDomain;
	protected EMFDataBindingContext dataBinding;
	protected Composite composite;
	private Spinner spinner;
	private Button useMaxItemsButton;
	private ControlDecoration controlDecoration;

	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		composite.setLayout(new GridLayout(2, false));
		useMaxItemsButton = widgetFactory.createButton(composite, "", SWT.CHECK);
		spinner = new Spinner(composite, SWT.WRAP | SWT.BORDER);
		spinner.setMinimum(1);
		spinner.setMaximum(Integer.MAX_VALUE);
		spinner.setBackground(composite.getBackground());

		controlDecoration = new ControlDecoration(useMaxItemsButton, SWT.TOP);
		controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
		controlDecoration.setDescriptionText(Messages.maxItems_hint);

		bindWidgets();
	}

	protected void bindWidgets() {
		if (dataBinding != null) {
			dataBinding.dispose();
		}
		if (spinner != null && ! spinner.isDisposed()) {
			dataBinding = new EMFDataBindingContext();
			ISWTObservableValue spinnerSelection = WidgetProperties.selection().observe(spinner);
			ISWTObservableValue buttonSelection = WidgetProperties.selection().observe(useMaxItemsButton);
			ISWTObservableValue spinnerEnablement = WidgetProperties.enabled().observe(spinner);
			IEMFEditValueProperty maxItems = EMFEditProperties.value(editingDomain, FormPackage.Literals.SUGGEST_BOX__MAX_ITEMS);
			IEMFEditValueProperty useMaxItems = EMFEditProperties.value(editingDomain, FormPackage.Literals.SUGGEST_BOX__USE_MAX_ITEMS);

			dataBinding.bindValue(spinnerSelection, maxItems.observe(element));
			dataBinding.bindValue(buttonSelection, useMaxItems.observe(element));
			dataBinding.bindValue(spinnerEnablement, useMaxItems.observe(element));
		}
	}

	public void dispose() {
		if (dataBinding != null) {
			dataBinding.dispose();
		}
	}

	public String getLabel() {
		return Messages.maxItems_title;
	}

	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof SuggestBox;
	}

	public void refresh() {
	}

	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	public void setSelection(ISelection selection) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution
	 * #setEObject(org.eclipse.emf.ecore.EObject)
	 */
	public void setEObject(EObject object) {
		this.element = (Widget) object;
		bindWidgets();
	}
}
