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
package org.bonitasoft.studio.examples.wizard;

import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.examples.i18n.Messages;
import org.bonitasoft.studio.examples.store.ExampleFileStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Mickael Istria
 *
 */
public class OpenExampleWizardPage extends WizardPage implements IWizardPage {

	protected FilteredTree ifileTree;

	/**
	 * @param openProcessWizard 
	 * @param example 
	 * 
	 */
	public OpenExampleWizardPage() {
		super(OpenExampleWizardPage.class.getName());
		setImageDescriptor(Pics.getWizban());
		setTitle(Messages.openExampleProcessWizardPage_title);
		setDescription(Messages.openExampleProcessWizardPage_desc);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, true));
		//Composite repositoryComposite = new Composite(composite, SWT.NONE);
		ifileTree = new FilteredTree(composite, SWT.MULTI | SWT.BORDER, new PatternFilter(), false);
		
		GridData processesListLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false,2,1);
		processesListLayoutData.heightHint = 250;
		ifileTree.setLayoutData(processesListLayoutData);
		ifileTree.getViewer().setContentProvider(new ExampleContentProvider()) ;
		ifileTree.getViewer().setInput(new Object());
		ifileTree.getViewer().setLabelProvider(new FileStoreLabelProvider()) ;
		ifileTree.getViewer().addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent arg0) {
				if (getWizard().canFinish()) {
					if(getWizard().performFinish()){
						((WizardDialog)getContainer()).close();
					}
				}

			}
		}) ;
		ifileTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				setPageComplete(isPageComplete());
			}

		});
		
		Link link = new Link(composite, SWT.NONE);
		link.setText(Messages.openContributionForMoreExamples);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class) ;
				Command cmd = service.getCommand("org.bonitasoft.studio.application.openContributions") ;
				try {
					cmd.executeWithChecks(new ExecutionEvent()) ;
					getWizard().getContainer().getShell().close();
				} catch (Exception ex) {
					BonitaStudioLog.error(ex);
				}
			}
		});
		
		

		// Separator
		Composite blank = new Composite(composite, SWT.NONE);
		blank.setLayoutData(new GridData(SWT.DEFAULT, 40));

		setWorkspaceThingsEnabled(true);
		setControl(composite);
	}


	private void setWorkspaceThingsEnabled(boolean enabled) {
		ifileTree.getFilterControl().setEnabled(enabled);
		ifileTree.getViewer().getTree().setEnabled(enabled);
	}


	@Override
	public boolean isPageComplete() {
		return getExamples() != null;
	}

	public List<ExampleFileStore> getExamples() {
		if (!ifileTree.getViewer().getSelection().isEmpty()) {
			return ((ITreeSelection)ifileTree.getViewer().getSelection()).toList();
		}
		return null;
	}

}
