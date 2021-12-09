/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.properties;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.widgets.GTKStyleHandler;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class DescriptionPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    protected Element element;
    protected TransactionalEditingDomain editingDomain;
    private EMFDataBindingContext context;
    private int vSpan;

    public DescriptionPropertySectionContribution(int vSpan) {
	this.vSpan = vSpan;
    }

    @Override
    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory,
	    final ExtensibleGridPropertySection page) {
	composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(1, vSpan).create());
	final Text text = widgetFactory.createText(composite, "", GTKStyleHandler.removeBorderFlag(SWT.BORDER | SWT.MULTI | SWT.WRAP));
	text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 120).create());

	context = new EMFDataBindingContext();
	final UpdateValueStrategy strategy = new UpdateValueStrategy();
	strategy.setBeforeSetValidator(new InputLengthValidator(Messages.GeneralSection_Description, 254));
	ControlDecorationSupport.create(context.bindValue(
		SWTObservables.observeDelayedValue(400, SWTObservables.observeText(text, SWT.Modify)),
		EMFEditObservables.observeValue(editingDomain, element, ProcessPackage.Literals.ELEMENT__DOCUMENTATION),
		strategy, null), SWT.LEFT | SWT.TOP);
    }

    @Override
    public String getLabel() {
	return Messages.GeneralSection_Description;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void setEObject(final EObject object) {
	element = (Element) object;
    }

    @Override
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
	this.editingDomain = editingDomain;
    }

    @Override
    public boolean isRelevantFor(final EObject eObject) {
	return eObject instanceof Element && !(eObject instanceof TextAnnotation);
    }

    @Override
    public void setSelection(final ISelection selection) {
	// NOTHING
    }

    @Override
    public void dispose() {
	if (context != null) {
	    context.dispose();
	}
    }

}
