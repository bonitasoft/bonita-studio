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
package org.bonitasoft.studio.simulation.properties.contributions;

import org.bonitasoft.studio.common.jface.databinding.BonitaNumberFormat;
import org.bonitasoft.studio.common.jface.databinding.validator.WrappingValidator;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.internal.databinding.validation.StringToDoubleValidator;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 *
 */
public class EstimatedTimeContribution extends AbstractPropertySectionContribution {


	private EMFDataBindingContext context;

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof SimulationActivity;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#refresh()
	 */
	public void refresh() {
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#getLabel()
	 */
	public String getLabel() {
		return Messages.EstimatedTime;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
	 */
	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		composite.setLayout(new FillLayout());
		context = new EMFDataBindingContext();
		
		final Composite probaComposite = widgetFactory.createComposite(composite);
		GridLayout layout = new GridLayout(2,false);
		layout.horizontalSpacing = 10;
		probaComposite.setLayout(layout);
		
		widgetFactory.createLabel(probaComposite, Messages.ExecutionTimePlus);//$NON-NLS-1$
		Text probaText = widgetFactory.createText(probaComposite, "",SWT.BORDER);
		probaText.setLayoutData(GridDataFactory.swtDefaults().hint(80, SWT.DEFAULT).create());
		ControlDecoration controlDecoration = new ControlDecoration(probaText, SWT.LEFT|SWT.TOP);
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
		.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
		controlDecoration.setImage(fieldDecoration.getImage());
		controlDecoration.setDescriptionText(Messages.mustBeAPercentage);
		
		ControlDecoration hint = new ControlDecoration(probaText, SWT.RIGHT | SWT.TOP);
		hint.setImage(Pics.getImage(PicsConstants.hint));
		hint.setDescriptionText(Messages.EstimatedTime_hint);
		
		context.bindValue(SWTObservables.observeText(probaText, SWT.Modify),EMFEditObservables.observeValue(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_ACTIVITY__ESTIMATED_TIME),
				new UpdateValueStrategy().setConverter(StringToNumberConverter.toDouble(BonitaNumberFormat.getPercentInstance(),false))
				.setAfterGetValidator(new WrappingValidator(controlDecoration,new StringToDoubleValidator(StringToNumberConverter.toDouble(BonitaNumberFormat.getPercentInstance(),false))))
				,new UpdateValueStrategy().setConverter(NumberToStringConverter.fromDouble(BonitaNumberFormat.getPercentInstance(),false)));
				
		
		
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
	 */
	public void dispose() {
		if(context != null){
			context.dispose();
		}
	}

}
