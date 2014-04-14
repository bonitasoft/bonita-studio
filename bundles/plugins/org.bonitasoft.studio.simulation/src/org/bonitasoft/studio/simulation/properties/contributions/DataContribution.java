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

import java.util.List;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.simulation.SimulationAbstractProcess;
import org.bonitasoft.studio.model.simulation.SimulationBoolean;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationDataContainer;
import org.bonitasoft.studio.model.simulation.SimulationLiteralData;
import org.bonitasoft.studio.model.simulation.SimulationNumberData;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.properties.sections.PropertySectionUtil;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.wizards.AddSimulationDataWizard;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 *
 */
public class DataContribution extends AbstractPropertySectionContribution {

	private FilteredTree filteredTree;
	private Button addDataButton;
	private Button updateDataButton;
	private Button removeDataButton;
	private TabbedPropertySheetWidgetFactory widgetFactory;

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof SimulationAbstractProcess && !(eObject instanceof MainProcess) ;
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
		return "";//$NON-NLS-N$
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
	 */
	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		this.widgetFactory = widgetFactory;
		composite.setLayout(new GridLayout(2, false));
		filteredTree = new FilteredTree(composite, SWT.BORDER | SWT.MULTI |SWT.NO_FOCUS, new PatternFilter(), true);
		filteredTree.setLayoutData(new GridData(300, PropertySectionUtil.LIST_HEIGHT));
		filteredTree.getViewer().setLabelProvider(new LabelProvider(){
			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
			 */
			@Override
			public String getText(Object element) {
				return ((SimulationData)element).getName() + " -- " + getTypeLabel((SimulationData) element); //$NON-NLS-1$
			}
		});
		filteredTree.getViewer().setContentProvider(new ITreeContentProvider() {

			public Object[] getChildren(Object parentElement) {
				return null;
			}

			public Object getParent(Object element) {
				return null;
			}

			public boolean hasChildren(Object element) {
				return false;
			}

			public Object[] getElements(Object inputElement) {
				return ((SimulationAbstractProcess)inputElement).getSimulationData().toArray();
			}

			public void dispose() {

			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
		});
		filteredTree.getViewer().addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				updateDataAction();
			}
		});
		filteredTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		filteredTree.getViewer().setInput(eObject);

		Composite buttonsComposite = widgetFactory.createPlainComposite(composite, SWT.NONE);
		buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));

		addDataButton = createAddDataButton(buttonsComposite);
		addDataButton.setFocus();
		updateDataButton = createUpdateDataButton(buttonsComposite); 
		removeDataButton = createRemoveDataButton(buttonsComposite);
		updateButtons() ;
	}

	/**
	 * @return
	 */
	protected String getTypeLabel(SimulationData data) {
		if(data instanceof SimulationBoolean){
			return Messages.SimulationDataType_boolean;
		} else if(data instanceof SimulationNumberData) {
			return Messages.SimulationDataType_number;
		} else  if(data instanceof SimulationLiteralData) {
			return Messages.SimulationDataType_literal;
		} else {
			return "";
		}
	}

	/**
	 * @param buttonsComposite
	 * @return
	 */
	private Button createRemoveDataButton(final Composite buttonsComposite) {
		final Button addData = widgetFactory.createButton(buttonsComposite, Messages.remove, SWT.FLAT );
		addData.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Are you shure", "Selected data will be removed")){
					List<?> list = ((StructuredSelection) filteredTree.getViewer().getSelection()).toList();
					RemoveCommand removeCommand = new RemoveCommand(editingDomain, eObject,SimulationPackage.Literals.SIMULATION_DATA_CONTAINER__SIMULATION_DATA, list);
					editingDomain.getCommandStack().execute(removeCommand);
					filteredTree.getViewer().setInput(getSimulationProcess());
				}
				
			}
		
		});		
		return addData;
	}

	/**
	 * @param buttonsComposite
	 * @return
	 */
	private Button createUpdateDataButton(final Composite buttonsComposite) {
		final Button addData = widgetFactory.createButton(buttonsComposite, Messages.edit, SWT.FLAT  );
		addData.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddSimulationDataWizard wizard = new AddSimulationDataWizard((SimulationData)((StructuredSelection) filteredTree.getViewer().getSelection()).getFirstElement(),editingDomain);
				CustomWizardDialog wizardDialog = new CustomWizardDialog(Display.getCurrent().getActiveShell(), 
						wizard);
				if(wizardDialog.open() == IDialogConstants.OK_ID){
					filteredTree.getViewer().setInput(getSimulationProcess());
					filteredTree.getViewer().setSelection(new StructuredSelection(wizard.getCreatedData()));
				}
			}
		
		});		
		return addData;
	}

	/**
	 * @param buttonsComposite
	 * @return
	 */
	private Button createAddDataButton(final Composite buttonsComposite) {
		final Button addData = widgetFactory.createButton(buttonsComposite, Messages.add, SWT.FLAT  );
		addData.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddSimulationDataWizard wizard = new AddSimulationDataWizard((SimulationDataContainer) eObject,editingDomain);
				CustomWizardDialog wizardDialog = new CustomWizardDialog(Display.getCurrent().getActiveShell(), 
						wizard);
				if(wizardDialog.open() == IDialogConstants.OK_ID){
					filteredTree.getViewer().setInput(getSimulationProcess());
				}
			}
		
		});		
		return addData;
	}

	/**
	 * @return
	 */
	protected SimulationAbstractProcess getSimulationProcess() {
		return (SimulationAbstractProcess) eObject;
	}

	/**
	 * 
	 */
	protected void updateButtons() {
		if(filteredTree!=null && filteredTree.getViewer()!= null && filteredTree.getViewer().getSelection()!=null) {
			if(!removeDataButton.isDisposed() && !updateDataButton.isDisposed()){
				removeDataButton.setEnabled(!filteredTree.getViewer().getSelection().isEmpty());
				updateDataButton.setEnabled(!filteredTree.getViewer().getSelection().isEmpty());
			}
		}
	}

	/**
	 * 
	 */
	protected void updateDataAction() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
	 */
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void setEObject(EObject object) {
		
		super.setEObject(object);
		updateButtons();
	}
}
