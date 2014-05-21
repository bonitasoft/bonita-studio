/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class ImageKindContribution implements IExtensibleGridPropertySectionContribution {

	private ImageWidget img;
	private TransactionalEditingDomain editingDomain;
	private EMFDataBindingContext context = null;

	private Button isUrlButton;
	private Button isADocumentButton;


	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		isADocumentButton = widgetFactory.createButton(composite, Messages.imageIsAnAttachment, SWT.RADIO);
		isUrlButton = widgetFactory.createButton(composite, Messages.imageIsAURL, SWT.RADIO);
		bindFields();
	}



	protected void bindFields() {
		if(context != null){
			context.dispose();
		}
		context = new EMFDataBindingContext();
		UpdateValueStrategy updateValue = new UpdateValueStrategy(){
			@Override
			public Object convert(Object value) {
				if(value instanceof Boolean){
					return !Boolean.valueOf((Boolean) value) ;
				}
				return super.convert(value);
			}
		};
				
		context.bindValue(
				WidgetProperties.selection().observe(isUrlButton),
				EMFEditObservables.observeValue(editingDomain,img, FormPackage.Literals.IMAGE_WIDGET__IS_ADOCUMENT),updateValue,updateValue);
		context.bindValue(
				WidgetProperties.selection().observe(isADocumentButton),
				EMFEditObservables.observeValue(editingDomain,img, FormPackage.Literals.IMAGE_WIDGET__IS_ADOCUMENT));
		
		if(ModelHelper.isInEntryPageFlowOnAPool(img)){
			isUrlButton.setSelection(true);
			isUrlButton.setEnabled(false);
			isADocumentButton.setEnabled(false);
		}
	}

	public void dispose() {
		if(context != null){
			context.dispose();
		}
	}

	public String getLabel() {
		return " ";
	}

	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof ImageWidget;
	}

	public void refresh() {
	}

	public void setEObject(EObject object) {
		img = (ImageWidget) object;

	}

	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	public void setSelection(ISelection selection) {

	}

}
