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

package org.bonitasoft.studio.properties.sections.general.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.bpm.model.process.CatchSignalEvent;
import org.bonitasoft.bpm.model.process.Element;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.SignalEvent;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.ui.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.ui.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class SignalEventEventSelectionContribution extends AbstractPropertySectionContribution {

    private Combo combo;
    private DataBindingContext context;
    private ControlDecoration controlDecoration;
    private ControlDecoration hint;

    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
	    ExtensibleGridPropertySection extensibleGridPropertySection) {
	combo = new Combo(composite, SWT.NONE);
	combo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

	controlDecoration = new ControlDecoration(combo, SWT.RIGHT | SWT.TOP);
	FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
		.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
	controlDecoration.setImage(fieldDecoration.getImage());
	controlDecoration.setDescriptionText(Messages.mustBeSet);
	controlDecoration.hide();
	hint = new ControlDecoration(combo, SWT.LEFT | SWT.TOP);
	hint.setImage(Pics.getImage(PicsConstants.hint));
    }

    public void dispose() {
	if (context != null) {
	    context.dispose();
	}
    }

    public String getLabel() {
	return Messages.selectSignalEventLabel;
    }

    public boolean isRelevantFor(EObject eObject) {
	return eObject instanceof SignalEvent;
    }

    public void refresh() {

    }

    public void setSelection(ISelection selection) {
	super.setSelection(selection);
	bindEObject();
    }

    private void bindEObject() {
	if (combo != null && !combo.isDisposed()) {
	    if (context != null) {
		context.dispose();
	    }
	    context = new EMFDataBindingContext();

	    List<Element> elements = ModelHelper.getAllItemsOfType(ModelHelper.getMainProcess(eObject),
		    ProcessPackage.Literals.THROW_SIGNAL_EVENT);
	    List<String> codes = new ArrayList<String>();
	    for (Element element : elements) {
		if (((SignalEvent) element).getSignalCode() != null
			&& ((SignalEvent) element).getSignalCode().length() > 0
			&& !codes.contains(((SignalEvent) element).getSignalCode())) {
		    codes.add(((SignalEvent) element).getSignalCode());
		}
	    }
	    Collections.sort(codes);
	    combo.removeAll();
	    for (String code : codes) {
		combo.add(code);
	    }
	    context.bindValue(WidgetProperties.text().observe(combo),
		    EMFEditObservables.observeValue(editingDomain, eObject,
			    ProcessPackage.Literals.SIGNAL_EVENT__SIGNAL_CODE),
		    new UpdateValueStrategy()
			    .setAfterGetValidator(new WrappingValidator(controlDecoration, new IValidator() {

				public IStatus validate(Object value) {
				    if (value instanceof String && ((String) value).length() > 0) {
					return Status.OK_STATUS;
				    } else {
					return Status.CANCEL_STATUS;
				    }
				}
			    }, true)),
		    null);

	    if (eObject instanceof CatchSignalEvent) {
		hint.setDescriptionText(Messages.signalEvent_catchHint);
	    } else {
		hint.setDescriptionText(Messages.signalEvent_throwHint);
	    }
	}
    }

}
