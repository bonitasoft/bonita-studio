/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
 
package org.bonitasoft.studio.data.ui.wizard;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.DataAwareElementViewerFilter;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Romain Bioteau
 *
 */
public class MoveDataWizardPage extends WizardPage implements
		IWizardPage {


	private AbstractProcess sourceProcess;
	private DataAware sourceContainer;
	private FilteredTree modelTree;
	private ComposedAdapterFactory adapterFactory;
	private DataAware selectedDataAwareElement;
	private DataBindingContext context;

	public MoveDataWizardPage(DataAware sourceContainer) {
		super(Messages.moveDataWizardMessage, Messages.moveDataWizardTitle, Pics.getWizban());
		setDescription(Messages.moveDataWizardMessage);
		this.sourceContainer = sourceContainer ;
		this.sourceProcess= ModelHelper.getMainProcess(sourceContainer);
		this.adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	}

	public void createControl(Composite parent) {
		context = new DataBindingContext();
		Composite mainComposite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(1, false);
		mainComposite.setLayout(gl);
		
		new Label(mainComposite, SWT.NONE).setText(Messages.chooseTargetStepOrProcess);
		
		
		modelTree = new FilteredTree(mainComposite, SWT.BORDER | SWT.SINGLE, new PatternFilter(), true);
		modelTree.getViewer().setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		modelTree.getViewer().setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		DataAwareElementViewerFilter filter = new DataAwareElementViewerFilter(sourceContainer) ;
		modelTree.getViewer().setFilters(new ViewerFilter[]{filter,modelTree.getViewer().getFilters()[0]});
		modelTree.getViewer().setInput(sourceProcess);
		modelTree.getViewer().expandAll() ;
	
		UpdateValueStrategy selectionStrategy = new UpdateValueStrategy();
		selectionStrategy.setAfterGetValidator(new IValidator() {
			
			@Override
			public IStatus validate(Object value) {
			
				
				if(value==null){
					return ValidationStatus.error(Messages.dataMoveErrorTargetSelected);
				}
				if(!(value instanceof DataAware)){
					return ValidationStatus.error(Messages.datamoveErrorWrongTarget);
				}
				if(value.equals(sourceContainer)){
					return ValidationStatus.error(Messages.dataMoveErrorTargetCantBeSource);
				}
				return ValidationStatus.ok();
			}
		});
		context.bindValue(ViewersObservables.observeSingleSelection(modelTree.getViewer()), PojoProperties.value(MoveDataWizardPage.class, "selectedDataAwareElement").observe(this),selectionStrategy,null);
		
		WizardPageSupport.create(this, context);
		setControl(mainComposite);
	}
	
	@Override
	public void dispose() {
		if(context != null){
			context.dispose();
		}
		super.dispose();
	}
	
	public DataAware getSelectedDataAwareElement() {
		return selectedDataAwareElement;
	}
	
	public void setSelectedDataAwareElement(DataAware selectedDataAwareElement) {
		this.selectedDataAwareElement = selectedDataAwareElement;
	}

}
