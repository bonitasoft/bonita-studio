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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.extension.IConfigurationImportAction;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class ImportConfigurationWizardPage extends WizardPage {

	private static final String IMPORT_CONFIGURATION_ACTION_ID = "org.bonitasoft.studio.configuration.importConfigurationAction";
	private static final String CLASS = "class";
	
	private Configuration configuration;
	private DataBindingContext databindingContext;
	private IAction action;
	private AbstractProcess process;

	public ImportConfigurationWizardPage(Configuration configuration,AbstractProcess process) {
		super(ImportConfigurationWizardPage.class.getName());
		setTitle(Messages.importWizardPageTitle) ;
		setDescription(Messages.importWizardPageDesc) ;
		this.configuration = configuration ; 
		this.process = process ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(20,20).create()) ;
		
		databindingContext = new DataBindingContext() ;
		
		final TableViewer viewer = new TableViewer(mainComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE ) ;
		viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		viewer.setContentProvider(new ArrayContentProvider()) ;
		viewer.setLabelProvider(new ActionLabelProvider()) ;
		viewer.setInput(getConfigurationImporterContributions()) ;
		
		UpdateValueStrategy startegy = new UpdateValueStrategy() ;
		startegy.setBeforeSetValidator(new IValidator() {
			
			@Override
			public IStatus validate(Object value) {
				if(value  instanceof IAction){
					return Status.OK_STATUS ;
				}
				return ValidationStatus.error(Messages.mustSelectExportError);
			}
		}) ;
	
		
		WizardPageSupport.create(this,databindingContext) ;
		databindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), PojoObservables.observeValue(this, "action"),startegy,null) ;

		
		setControl(mainComposite) ;
	}

	private List<IConfigurationImportAction>  getConfigurationImporterContributions() {
		List<IConfigurationImportAction> result = new ArrayList<IConfigurationImportAction>() ;
		for(IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(IMPORT_CONFIGURATION_ACTION_ID)){
			try {
				IConfigurationImportAction action = (IConfigurationImportAction) element.createExecutableExtension(CLASS) ;
				action.setProcess(process) ;
				action.setConfiguration(configuration) ;
				result.add(action) ;
			} catch (CoreException e) {
				BonitaStudioLog.error(e) ;
			}
		}
		return result;
	}

	@Override
	public void dispose() {
		super.dispose();
		if(databindingContext != null){
			databindingContext.dispose() ;
		}
	}

	public IAction getAction() {
		return action ;
	}
	
	public void setAction(IAction action) {
		this.action = action;
	}
}
