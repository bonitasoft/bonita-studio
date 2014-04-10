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

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.model.simulation.SimulationAbstractProcess;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;
import org.bonitasoft.studio.simulation.wizards.EditSimulationLoadProfileWizard;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 *
 */
public class LoadProfileContribution extends AbstractPropertySectionContribution {

	private CCombo cCombo;
	private EMFDataBindingContext context;

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof SimulationAbstractProcess;
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
		return "Load profile:";
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
	 */
	public void createControl(final Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		composite.setLayout(new GridLayout(3,false));
		
		cCombo = widgetFactory.createCCombo(composite, SWT.BORDER);
		cCombo.setLayoutData(GridDataFactory.swtDefaults().hint(200, SWT.DEFAULT).create());
		final SimulationLoadProfileRepositoryStore profileStore = RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class) ;
		for ( IRepositoryFileStore artifact: profileStore.getChildren()) {
			cCombo.add(artifact.getDisplayName());
		}
		cCombo.setEditable(false);
		Button editLoadProfile = new Button(composite, SWT.FLAT);
		editLoadProfile.setText(Messages.edit);
		editLoadProfile.addSelectionListener(new SelectionAdapter() {
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				EditSimulationLoadProfileWizard wizard = new EditSimulationLoadProfileWizard(profileStore.getChild(cCombo.getText()+"."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT));
				new CustomWizardDialog(Display.getCurrent().getActiveShell(), wizard).open();
				
			}
		});
		Button createLoadProfile = new Button(composite, SWT.FLAT);
		createLoadProfile.setText(Messages.create);
		createLoadProfile.addSelectionListener(new SelectionAdapter() {
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				EditSimulationLoadProfileWizard wizard = new EditSimulationLoadProfileWizard();
				if(new CustomWizardDialog(Display.getCurrent().getActiveShell(), wizard).open() == IDialogConstants.OK_ID){
					String id = wizard.getArtifact().getDisplayName();
					cCombo.add(id);
					cCombo.setText(id);
				}
				
			}
		});
		
		
		context = new EMFDataBindingContext();
		context.bindValue(SWTObservables.observeText(cCombo), EMFEditObservables.observeValue(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_ABSTRACT_PROCESS__LOAD_PROFILE_ID));
		context.bindValue(SWTObservables.observeEnabled(editLoadProfile), SWTObservables.observeText(cCombo),new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),new UpdateValueStrategy().setConverter(new Converter(String.class,Boolean.class) {
			
			public Object convert(Object fromObject) {
				return ((String)fromObject).length()>0;
			}
		}));
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
