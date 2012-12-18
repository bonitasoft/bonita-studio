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
 
package org.bonitasoft.studio.application.actions.wizards;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 *
 */
public class ChooseProcessesWizardPage extends WizardPage {
	
	private List<AbstractProcess> selectedProcesses;

	protected ChooseProcessesWizardPage() {
		super(ChooseProcessesWizardPage.class.getName());
		this.setTitle(Messages.deployWarWizardTitle);
		this.setDescription(Messages.deployWarWizardDescription);
		this.setMessage(Messages.deployWarWizardMessage);
		setImageDescriptor(Pics.getWizban());
		selectedProcesses = new ArrayList<AbstractProcess>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData gd ;
		
		Label warLabel = new Label(composite, SWT.NONE);
		gd = new GridData(GridData.FILL,SWT.NONE,true,false);
		gd.horizontalSpan = 2 ;
		warLabel.setLayoutData(gd);
		warLabel.setText(Messages.deployApplicationHelpLabel);
		
		// Create the tree viewer to display the file tree
		final CheckboxTreeViewer tv = new CheckboxTreeViewer(composite,SWT.V_SCROLL|SWT.BORDER);
		gd = new GridData(GridData.FILL,SWT.FILL,true,true);
		gd.horizontalSpan = 2 ;
		gd.heightHint = 250 ;
		tv.getTree().setLayoutData(gd);
		tv.setContentProvider(new ITreeContentProvider(){

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
				return((DiagramRepositoryStore)inputElement).getAllProcesses().toArray();
				
			}

			public void dispose() {		
			}

			public void inputChanged(Viewer viewer, Object oldInput,Object newInput) {
				
			}
			
		});
		tv.setLabelProvider(new FileStoreLabelProvider());
		tv.setInput(RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class));


		// When user checks a checkbox in the tree, check all its children
		tv.addCheckStateListener(new ICheckStateListener() {


			public void checkStateChanged(CheckStateChangedEvent event) {
				// If the item is checked . . .
				if (event.getChecked()) {
					selectedProcesses.add((AbstractProcess)event.getElement());
				}else{
					selectedProcesses.remove((AbstractProcess)event.getElement());
				}
				getContainer().updateButtons();
			}
		});

		this.setControl(composite);
		getContainer().updateButtons() ;
	}

	@Override
	public boolean isPageComplete() {
		return !selectedProcesses.isEmpty();
	}
	
	public List<AbstractProcess> getProcesses() {
		return selectedProcesses;
	}
}
