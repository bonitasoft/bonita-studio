/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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


import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.examples.i18n.Messages;
import org.bonitasoft.studio.examples.store.ExampleFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Mickael Istria
 *
 */
public class OpenExampleWizard extends Wizard implements IWizard {

	private OpenExampleWizardPage page;

	/**
	 * Default constructor to open process and not example.
	 */
	public OpenExampleWizard(){
		setNeedsProgressMonitor(true) ;
		setWindowTitle(Messages.openProcessWizardPage_title);
	}


	@Override
	public void addPages() {
		page = new OpenExampleWizardPage();
		this.addPage(page);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		
		try {
			getContainer().run(false, false, new IRunnableWithProgress(){

				public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Opening example...", IProgressMonitor.UNKNOWN) ; //$NON-NLS-1$
					for(ExampleFileStore file : page.getExamples()){
						file.open() ;
					}
					monitor.done();
				}

			});
		} catch (Exception e) {
			BonitaStudioLog.error(e) ;
		} 

		
		return true;
	}
	


}
