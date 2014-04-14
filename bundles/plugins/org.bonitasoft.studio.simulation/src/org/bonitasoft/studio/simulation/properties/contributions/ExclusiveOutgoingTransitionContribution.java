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

import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.model.process.XORGateway;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 *
 */
public class ExclusiveOutgoingTransitionContribution extends AbstractPropertySectionContribution{

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
		return Messages.exclusiveOutgoingContributionLabel;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
	 */
	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		composite.setLayout(new FillLayout());

		Button button = widgetFactory.createButton(composite, "", SWT.CHECK);
		if(eObject instanceof XORGateway){
			button.setEnabled(false) ;
		}

		ControlDecoration hint = new ControlDecoration(button, SWT.RIGHT | SWT.TOP);
		hint.setImage(Pics.getImage(PicsConstants.hint));
		hint.setDescriptionText(Messages.exclusiveOutgoingContributionHint);

		context = new EMFDataBindingContext();
		context.bindValue(SWTObservables.observeSelection(button), EMFEditObservables.observeValue(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_ACTIVITY__EXCLUSIVE_OUTGOING_TRANSITION));
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
