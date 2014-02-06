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

package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.provider.StyledConnectorLabelProvider;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorContainerSwitchWizardPage extends WizardPage implements IWizardPage {


	private final AbstractProcess sourceProcess;
	private FilteredTree connectorsTree;
	private FilteredTree modelTree;
	private final ComposedAdapterFactory adapterFactory;
	private ConnectableElement selectedConnectableElement;
	private List<EObject> input;
	private boolean copy = false;
	private Connector selectedConnector;

	public ConnectorContainerSwitchWizardPage(AbstractProcess sourceProcess) {
		super(Messages.switchContainerConnectorMessage, Messages.switchContainerConnectorTitle, Pics.getWizban());
		setDescription(Messages.switchContainerConnectorMessage);
		this.sourceProcess = sourceProcess ;
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	}

	public ConnectorContainerSwitchWizardPage(AbstractProcess sourceProcess, Connector selectedConnector) {
		super(Messages.switchContainerConnectorMessage, Messages.switchContainerConnectorTitle, Pics.getWizban());
		setDescription(Messages.switchContainerConnectorMessage);
		this.sourceProcess = sourceProcess ;
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		this.selectedConnector = selectedConnector;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(2, false);
		mainComposite.setLayout(gl);

		new Label(mainComposite, SWT.NONE).setText(Messages.chooseConnectorToMove);
		new Label(mainComposite, SWT.NONE).setText(Messages.chooseTargetStepOrProcess);
		connectorsTree = new FilteredTree(mainComposite, SWT.BORDER | SWT.SINGLE, new PatternFilter(), true);
		connectorsTree.getViewer().setLabelProvider(new StyledConnectorLabelProvider());
		connectorsTree.getViewer().setContentProvider(new ITreeContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

			}

			@Override
			public void dispose() {

			}

			@Override
			public Object[] getElements(Object inputElement) {
				return ((List<?>) inputElement).toArray();
			}

			@Override
			public boolean hasChildren(Object element) {
				return false;
			}

			@Override
			public Object getParent(Object element) {
				return null;
			}

			@Override
			public Object[] getChildren(Object parentElement) {
				return null;
			}
		});

		input = new ArrayList<EObject>();
		ModelHelper.findAllConnectors(sourceProcess, input);
		connectorsTree.getViewer().setInput(input);
		connectorsTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				connectorsTreeSelectionChanged();
			}
		});
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 300 ;
		connectorsTree.setLayoutData(gd);

		modelTree = new FilteredTree(mainComposite, SWT.BORDER | SWT.SINGLE, new PatternFilter(), true);
		modelTree.getViewer().setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		modelTree.getViewer().setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		ConnectableElementViewerFilter filter = new ConnectableElementViewerFilter(sourceProcess) ;
		modelTree.getViewer().setFilters(new ViewerFilter[]{filter,modelTree.getViewer().getFilters()[0]});
		modelTree.getViewer().setInput(sourceProcess.eContainer());
		modelTree.getViewer().expandAll() ;
		modelTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object selection = ((StructuredSelection) modelTree.getViewer().getSelection()).getFirstElement() ;
				if(selection instanceof ConnectableElement) {
					setSelectedConnectableElement((ConnectableElement) selection);
				} else {
					setSelectedConnectableElement(null);
				}
				getContainer().updateButtons();

			}
		});

		final Button copyButton = new Button(mainComposite,SWT.CHECK);
		copyButton.setText(Messages.copyConnectorCheckBoxLabel);
		copyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCopy(copyButton.getSelection());
			}
		});

		if(selectedConnector != null){
			StructuredSelection s = new StructuredSelection(selectedConnector);
			connectorsTree.getViewer().setSelection(s, true);
		}
		getContainer().updateButtons();       
		setControl(mainComposite);
	}

	private void connectorsTreeSelectionChanged() {
		if(((StructuredSelection) connectorsTree.getViewer().getSelection()).getFirstElement() instanceof Connector) {
			setSelectedConnector((Connector) ((StructuredSelection) connectorsTree.getViewer().getSelection()).getFirstElement());
		}

		if(getSelectedConnector() != null){
			ConnectableElement container = (ConnectableElement) getSelectedConnector().eContainer() ;

			if(!(container instanceof AbstractProcess)){
				modelTree.getViewer().setInput(sourceProcess.eContainer());
				modelTree.getViewer().expandAll() ;
				modelTree.getViewer().remove(container);

			}else{
				modelTree.getViewer().setInput(sourceProcess);
				modelTree.getViewer().expandAll() ;
			}
			//modelTree.getViewer().expandAll() ;

		}
		getContainer().updateButtons();
	}

	@Override
	public boolean isPageComplete() {
		if(getSelectedConnector() != null){
			ConnectableElement source = (ConnectableElement) getSelectedConnector().eContainer() ;
			if(!(source instanceof AbstractProcess)){
				setMessage(Messages.warningLocalVariableinConnector,IMessageProvider.WARNING);
			}else{
				setMessage(null);
			}
		}

		return !modelTree.getViewer().getSelection().isEmpty() && !connectorsTree.getViewer().getSelection().isEmpty() && getSelectedConnectableElement() != null;
	}

	public Connector getSelectedConnector() {
		return selectedConnector;
	}

	public void setSelectedConnector(Connector selectedConnector) {
		this.selectedConnector = selectedConnector;
	}

	public ConnectableElement getSelectedConnectableElement() {
		return selectedConnectableElement;
	}

	public void setSelectedConnectableElement(ConnectableElement selectedConnectableElement) {
		this.selectedConnectableElement = selectedConnectableElement;
	}

	public boolean getCopy() {
		return copy ;
	}

	public void setCopy(boolean copy) {
		this.copy = copy;
	}


}
