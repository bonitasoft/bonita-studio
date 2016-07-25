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
package org.bonitasoft.studio.simulation.wizards;

import java.util.Iterator;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.model.simulation.Resource;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationResourceRepositoryStore;
import org.eclipse.gmf.runtime.common.ui.util.WindowUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * @author Baptiste Mesta
 * @author Romain Bioteau
 */
public class ManageResourcesWizardPage extends WizardPage {
	private Composite mainComposite;
	Resource currentResource;
	private TreeViewer treeViewer;
	SimulationResourceComposite resourceComposite = new SimulationResourceComposite();
	private Button btnRemove;
	private Button btnEdit;
	private Button btnAdd;
	private IRepositoryStore resourceStore;

	/**
	 * @param pageName
	 */
	protected ManageResourcesWizardPage() {
		super("manage resources");
		setTitle(Messages.ManageResourcesWizardPage_Title);
		setMessage(Messages.ManageResourcesWizardPage_Message);
		this.setImageDescriptor(Pics.getWizban());
		this.resourceStore = RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class) ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createControl(Composite parent) {
		
		mainComposite = new Composite(parent, SWT.NULL);

		setControl(mainComposite);
		mainComposite.setLayout(new GridLayout(2, false));

		createTree(mainComposite);
		updateButtons();
	}

	/**
	 * @param container
	 */
	private void createTree(Composite container) {

		SashForm sashForm = new SashForm(mainComposite, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setSashWidth(3);
		treeViewer = new TreeViewer(sashForm, SWT.BORDER);

		createButtons(sashForm);



		resourceComposite.createResourcePanel(this, sashForm);
		treeViewer.setContentProvider(new TreeArrayContentProvider());
		treeViewer.setInput(resourceStore.getChildren());
		treeViewer.setLabelProvider(new FileStoreLabelProvider());
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				resourceComposite.changeBinding(((IStructuredSelection) event.getSelection()).getFirstElement());
				updateButtons()  ;
			}
		});
		sashForm.setWeights(new int[] { 254,90, 254 });
	}

	private void updateButtons() {
		if(treeViewer != null && treeViewer.getSelection() != null && !treeViewer.getSelection().isEmpty()){
			btnEdit.setEnabled(true) ;
			btnRemove.setEnabled(true) ;
		}else{
			btnEdit.setEnabled(false) ;
			btnRemove.setEnabled(false) ;
		}
	}

	/**
	 * @param container
	 */
	private void createButtons(Composite container) {
		Composite buttonComposite = new Composite(container, SWT.NONE);
		buttonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		RowLayout rl_buttonComposite = new RowLayout(SWT.VERTICAL);
		rl_buttonComposite.fill = true;
		buttonComposite.setLayout(rl_buttonComposite);

		btnAdd = new Button(buttonComposite, SWT.FLAT);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Wizard editWizard = new EditSimulationResourceWizard();
				CustomWizardDialog wizard = new CustomWizardDialog(Display.getCurrent().getActiveShell(), editWizard, IDialogConstants.FINISH_LABEL);
				wizard.create() ;
				WindowUtil.centerDialog(wizard.getShell(), Display.getCurrent().getActiveShell())  ;
				if (wizard.open() == IDialogConstants.OK_ID) {
					treeViewer.setInput(resourceStore.getChildren());
					if (!treeViewer.getSelection().isEmpty()) {
						resourceComposite.changeBinding(((IStructuredSelection) treeViewer.getSelection()).getFirstElement());
					} else {
						resourceComposite.changeBinding(null);
					}
				}
			}
		});
		btnAdd.setText(Messages.add);

		btnEdit = new Button(buttonComposite, SWT.FLAT);
		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!treeViewer.getSelection().isEmpty()) {
					IRepositoryFileStore artifact = (IRepositoryFileStore) ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
					Wizard editWizard = new EditSimulationResourceWizard(artifact);
					CustomWizardDialog wizard = new CustomWizardDialog(Display.getCurrent().getActiveShell(), editWizard, IDialogConstants.FINISH_LABEL);
					wizard.create() ;
					WindowUtil.centerDialog(wizard.getShell(), Display.getCurrent().getActiveShell())  ;
					if (wizard.open() == IDialogConstants.OK_ID) {
						treeViewer.setInput(resourceStore.getChildren());
						if (!treeViewer.getSelection().isEmpty()) {
							resourceComposite.changeBinding(((IStructuredSelection) treeViewer.getSelection()).getFirstElement());
						} else {
							resourceComposite.changeBinding(null);
						}
					}
				}
			}
		});
		btnEdit.setText(Messages.edit);

		btnRemove = new Button(buttonComposite, SWT.FLAT);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!treeViewer.getSelection().isEmpty()) {
					if(MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteQuestionTitle, Messages.deleteQuestionMessage)){
						IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
						for (Iterator<?> iterator = selection.iterator(); iterator.hasNext();) {
							Object item = (Object) iterator.next();
							if (item instanceof IRepositoryFileStore) {
								IRepositoryFileStore artifact = (IRepositoryFileStore) item;
								artifact.delete() ;
							}
						}
						treeViewer.setInput(resourceStore.getChildren());
					}
				}
			}
		});
		btnRemove.setText(Messages.remove);
	}
}
