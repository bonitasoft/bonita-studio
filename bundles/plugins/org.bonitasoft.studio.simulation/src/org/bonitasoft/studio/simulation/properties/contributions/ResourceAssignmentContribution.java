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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.model.simulation.ResourceUsage;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.properties.sections.PropertySectionUtil;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.wizards.AddResourceAssignmentWizard;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 * 
 */
public class ResourceAssignmentContribution extends AbstractPropertySectionContribution {

	private FilteredTree filteredTree;
	private Button addDataButton;
	private Button updateDataButton;
	private Button removeDataButton;
	//	private ObservableListTreeContentProvider provider;
	//	private IObservableList observeList;
	private ObservablesManager mgr;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution
	 * #isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof SimulationActivity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution#refresh()
	 */
	public void refresh() {
		if (filteredTree != null && filteredTree.getViewer() != null && filteredTree.getViewer().getContentProvider() != null && eObject != null) {
			filteredTree.getViewer().setInput(eObject);
			updateButtons() ;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution#getLabel()
	 */
	public String getLabel() {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution
	 * #createControl(org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory,
	 * org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
	 */
	public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory,
			final ExtensibleGridPropertySection extensibleGridPropertySection) {

		//		mgr = new ObservablesManager();
		//		mgr.runAndCollect(new Runnable() {
		//
		//			public void run() {
		composite.setLayout(new GridLayout(2, false));
		filteredTree = new FilteredTree(composite, SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS, new PatternFilter(), true);

		filteredTree.setLayoutData(new GridData(300, PropertySectionUtil.LIST_HEIGHT));
		//				provider = new ObservableListTreeContentProvider(new IObservableFactory() {
		//
		//					public IObservable createObservable(Object target) {
		//						if (target instanceof IObservableList) {
		//							return (IObservable) target;
		//						} else if (target instanceof SimulationActivity) {
		//							return EMFProperties.list(SimulationPackage.Literals.SIMULATION_ACTIVITY__RESOURCES_USAGES).observe(target);
		//						}
		//
		//						return null;
		//					}
		//				}, new TreeStructureAdvisor() {
		//					@Override
		//					public Boolean hasChildren(Object element) {
		//						return false;
		//					}
		//					@Override
		//					public Object getParent(Object element) {
		//						return null;
		//					}
		//
		//				});
		//				filteredTree.getViewer().setContentProvider(provider);
		filteredTree.getViewer().addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				updateResourceAction();
			}
		});
		filteredTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		filteredTree.getViewer().setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				ResourceUsage ru = (ResourceUsage) element;
				if(ru.isUseActivityDuration()){
					return NLS.bind(Messages.resourceAssignmentLabel, new Object[]{ru.getQuantity(),ru.getResourceID(),Messages.AddResourceAssignmentWizardPage_useActivity});							
				}else{
					return NLS.bind(Messages.resourceAssignmentLabel, new Object[]{ru.getQuantity(),ru.getResourceID(),DateUtil.getDisplayDuration(ru.getDuration())});
				}
			}
		});
		filteredTree.getViewer().setContentProvider(new ITreeContentProvider() {

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			public void dispose() {
			}

			public boolean hasChildren(Object element) {
				return false;
			}

			public Object getParent(Object element) {
				return null;
			}

			public Object[] getElements(Object inputElement) {
				List<ResourceUsage> list = new ArrayList<ResourceUsage>(((SimulationActivity) inputElement).getResourcesUsages()) ;
				Collections.sort(list, new Comparator<ResourceUsage>() {
					public int compare(ResourceUsage o1,ResourceUsage o2) {
						return o1.getResourceID().compareTo(o2.getResourceID());
					}
				}) ;
				return list.toArray();
			}

			public Object[] getChildren(Object parentElement) {
				return null;
			}
		});
		Composite buttonsComposite = widgetFactory.createPlainComposite(composite, SWT.NONE);
		buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));

		addDataButton = createAddResourceButton(buttonsComposite);
		addDataButton.setFocus();
		updateDataButton = createUpdateResourceButton(buttonsComposite);
		removeDataButton = createRemoveResourceButton(buttonsComposite);
		updateButtons() ;
		//			}
	//		});

	}

	/**
	 * @param buttonsComposite
	 * @return
	 */
	private Button createRemoveResourceButton(Composite buttonsComposite) {
		final Button removeResource = new Button(buttonsComposite, SWT.FLAT);
		removeResource.setText(Messages.remove);
		removeResource.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (filteredTree != null && ((ITreeSelection) filteredTree.getViewer().getSelection()).size() > 0) {
					if(MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.removeResourceTitle, Messages.removeResourceMessage)){
						Object[] selection = ((ITreeSelection) filteredTree.getViewer().getSelection()).toArray();
						List<Object> toRemove = new ArrayList<Object>();
						for (Object object : selection) {
							toRemove.add(object);
						}
						editingDomain.getCommandStack().execute(
								new RemoveCommand(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_ACTIVITY__RESOURCES_USAGES, toRemove));
						refresh();
					}
				}
			}
		});
		return removeResource;
	}

	/**
	 * @param buttonsComposite
	 * @return
	 */
	private Button createUpdateResourceButton(Composite buttonsComposite) {
		final Button updateResource = new Button(buttonsComposite, SWT.FLAT);
		updateResource.setText(Messages.edit);
		updateResource.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomWizardDialog wizardDialog = new CustomWizardDialog(Display.getCurrent().getActiveShell(), new AddResourceAssignmentWizard(
						(ResourceUsage) ((IStructuredSelection) filteredTree.getViewer().getSelection()).getFirstElement(), editingDomain));
				wizardDialog.open();
				refresh();
			}
		});
		return updateResource;
	}

	/**
	 * @param buttonsComposite
	 * @return
	 */
	private Button createAddResourceButton(Composite buttonsComposite) {
		final Button addResource = new Button(buttonsComposite, SWT.FLAT);
		addResource.setText(Messages.add);
		addResource.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CustomWizardDialog wizardDialog = new CustomWizardDialog(Display.getCurrent().getActiveShell(), new AddResourceAssignmentWizard((SimulationActivity) eObject,
						editingDomain));
				wizardDialog.open();
				refresh();
			}
		});
		return addResource;
	}

	/**
	 * 
	 */
	protected void updateButtons() {
		if (filteredTree != null && filteredTree.getViewer() != null) {
			ITreeSelection selection = (ITreeSelection) filteredTree.getViewer().getSelection();
			if (!removeDataButton.isDisposed()) {
				removeDataButton.setEnabled(selection.size() > 0);
			}
			if (!updateDataButton.isDisposed()) {
				updateDataButton.setEnabled(selection.size() == 1);
			}
		}
	}

	/**
	 * 
	 */
	protected void updateResourceAction() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution#dispose()
	 */
	public void dispose() {
		if(mgr != null){
			mgr.dispose();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution
	 * #setEObject(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void setEObject(EObject object) {
		super.setEObject(object);
		refresh();
		//		observeList.dispose();
		//		mgr.runAndCollect(new Runnable() {
		//			public void run() {
		//				observeList = EMFEditObservables.observeList(editingDomain, eObject, SimulationPackage.Literals.SIMULATION_ACTIVITY__RESOURCES_USAGES);
		//				filteredTree.getViewer().setInput(observeList);				
		//			}
		//		});
	}


}
