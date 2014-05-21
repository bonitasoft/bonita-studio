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
package org.bonitasoft.studio.configuration.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.ui.wizard.page.ExportConfigurationWizardPage;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class ExportWizard extends Wizard {

	private Configuration configuration;
	private ExportConfigurationWizardPage page;
	private AbstractProcess process;

	public ExportWizard(Configuration configuration,AbstractProcess process){
		setDefaultPageImageDescriptor(Pics.getWizban()) ;
		setForcePreviousAndNextButtons(false) ;
		setNeedsProgressMonitor(true) ;
		this.configuration = configuration ;
		this.process = process ;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			getContainer().run(true, false, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
					monitor.beginTask(Messages.exporting, IProgressMonitor.UNKNOWN) ;
					Display.getDefault().asyncExec(new Runnable() {
						
						@Override
						public void run() {
							IAction action = page.getAction() ;
							action.run() ;	
						}
					}) ;
				}
			});
		} catch (Exception e){
			BonitaStudioLog.error(e) ;
		}
		
		
		return true;
	}

	@Override
	public void addPages() {
		page = new ExportConfigurationWizardPage(configuration,process) ;
		addPage(page) ;
	}
}
