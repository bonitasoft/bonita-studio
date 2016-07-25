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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.WrappingValidator;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.model.process.CatchSignalEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SignalEvent;
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
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 * 
 */
public class SignalEventEventSelectionContribution extends AbstractPropertySectionContribution {

	private Combo combo;
	private DataBindingContext context;
	private ControlDecoration controlDecoration;
	private ControlDecoration hint;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution
	 * #createControl(org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory,
	 * org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
	 */
	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL));
		combo = new Combo(composite, SWT.NONE);
		GridData gd = new GridData(GridData.FILL);
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.FILL;
		gd.widthHint = 200;
		combo.setLayoutData(gd);
		
		controlDecoration = new ControlDecoration(combo, SWT.RIGHT | SWT.TOP);
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
		controlDecoration.setImage(fieldDecoration.getImage());
		controlDecoration.setDescriptionText(Messages.mustBeSet);
		controlDecoration.hide();
		hint = new ControlDecoration(combo, SWT.LEFT | SWT.TOP);
		hint.setImage(Pics.getImage(PicsConstants.hint));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution#dispose()
	 */
	public void dispose() {
		if (context != null) {
			context.dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution#getLabel()
	 */
	public String getLabel() {
		return Messages.selectSignalEventLabel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution
	 * #isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof SignalEvent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution#refresh()
	 */
	public void refresh() {
		
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution
	 * #setSelection(org.eclipse.jface.viewers.ISelection)
	 */
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

			List<Element> elements = ModelHelper.getAllItemsOfType(ModelHelper.getMainProcess(eObject),ProcessPackage.Literals.THROW_SIGNAL_EVENT);
			List<String> codes = new ArrayList<String>();
			for (Element element : elements) {
				if(((SignalEvent) element).getSignalCode() != null && ((SignalEvent) element).getSignalCode().length()>0 && !codes.contains(((SignalEvent) element).getSignalCode()) ) {
					codes.add(((SignalEvent) element).getSignalCode());
				}
			}
			Collections.sort(codes);
			combo.removeAll();
			for (String code : codes) {
				combo.add(code);
			}
			context.bindValue(SWTObservables.observeText(combo), EMFEditObservables.observeValue(editingDomain, eObject,
					ProcessPackage.Literals.SIGNAL_EVENT__SIGNAL_CODE), new UpdateValueStrategy().setAfterGetValidator(new WrappingValidator(
							controlDecoration, new IValidator() {
								
								public IStatus validate(Object value) {
									if (value instanceof String && ((String) value).length() > 0) {
										return Status.OK_STATUS;
									} else {
										return Status.CANCEL_STATUS;
									}
								}
							},true)), null);
			
			if (eObject instanceof CatchSignalEvent) {
				hint.setDescriptionText(Messages.signalEvent_catchHint);
			} else {
				hint.setDescriptionText(Messages.signalEvent_throwHint);
			}
		}
	}

}
