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
package org.bonitasoft.studio.properties.sections.general;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.common.widgets.GTKStyleHandler;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier This contribution is designed to have the text of the
 *         Text annotation.
 */
public class TextAnnotationTextPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    private Text text;
    protected TextAnnotation textAnnotation;
    protected TransactionalEditingDomain editingDomain;
    private EMFDataBindingContext context;

    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
	    ExtensibleGridPropertySection extensibleGridPropertySection) {
	composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
	text = widgetFactory.createText(composite, "", GTKStyleHandler.removeBorderFlag(SWT.BORDER | SWT.MULTI | SWT.WRAP)); //$NON-NLS-1$
	text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
	updateBindings();
    }

    public String getLabel() {
	return Messages.GeneralSection_TextAnnotation;
    }

    public boolean isRelevantFor(EObject eObject) {
	return eObject instanceof TextAnnotation;
    }

    public void refresh() {

    }

    public void updateBindings() {
	if (text != null && !text.isDisposed()) {
	    if (context != null) {
		context.dispose();
	    }
	    context = new EMFDataBindingContext();
	    context.bindValue(SWTObservables.observeText(text, SWT.Modify), EMFEditObservables
		    .observeValue(editingDomain, textAnnotation, ProcessPackage.Literals.TEXT_ANNOTATION__TEXT));
	}
    }

    public void setEObject(EObject object) {
	this.textAnnotation = (TextAnnotation) object;
	updateBindings();
    }

    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
	this.editingDomain = editingDomain;
    }

    public void setSelection(ISelection selection) {
	// Nothing
    }

    @Override
    public void dispose() {

    }

}
