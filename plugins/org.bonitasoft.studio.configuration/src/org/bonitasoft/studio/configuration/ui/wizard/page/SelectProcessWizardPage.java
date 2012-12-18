/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Romain Bioteau
 *
 */
public class SelectProcessWizardPage extends WizardPage {

	private MainProcess diagram;
	private Object selection;
	private DataBindingContext databindingContext;

	public SelectProcessWizardPage(MainProcess diagram) {
		super(SelectProcessWizardPage.class.getName());
		setTitle(Messages.selectProcessToConfigureTitle) ;
		setDescription(Messages.selectProcessToConfigureDesc) ;
		this.diagram = diagram ; 
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;
		
		databindingContext = new DataBindingContext() ;
		
		final FilteredTree tree = new FilteredTree(mainComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE,new PatternFilter(), false) ;
		tree.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		TreeViewer viewer = tree.getViewer() ;
		viewer.setContentProvider(new ProcessContentProvider()) ;
		viewer.setLabelProvider(new ProcessTreeLabelProvider()) ;

		UpdateValueStrategy startegy = new UpdateValueStrategy() ;
		startegy.setBeforeSetValidator(new IValidator() {
			
			@Override
			public IStatus validate(Object value) {
				if(value  instanceof AbstractProcess && !(value instanceof MainProcess)){
					return Status.OK_STATUS ;
				}
				return ValidationStatus.error(Messages.mustSelectProcessError);
			}
		}) ;
	
		if(diagram == null){
			viewer.setInput(new Object[]{ProcessContentProvider.OTHER_PROCESSES}) ;
		}else{
			viewer.setInput(new Object[]{diagram,ProcessContentProvider.OTHER_PROCESSES}) ;
		}

		if(diagram != null){
			if(((ITreeContentProvider) viewer.getContentProvider()).hasChildren(diagram)){
				viewer.expandToLevel(diagram, 1) ;
			}
		}else{
			if(((ITreeContentProvider) viewer.getContentProvider()).hasChildren(ProcessContentProvider.OTHER_PROCESSES)){
				viewer.expandToLevel(ProcessContentProvider.OTHER_PROCESSES, 1) ;
			}
		}
		
		WizardPageSupport.create(this,databindingContext) ;
		databindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), PojoObservables.observeValue(this, "selection"),startegy,null) ;


		
		setControl(mainComposite) ;
	}

	public Object getSelection() {
		return selection;
	}

	public void setSelection(Object selection) {
		this.selection = selection;
	}

	@Override
	public void dispose() {
		super.dispose();
		if(databindingContext != null){
			databindingContext.dispose() ;
		}
	}
}
