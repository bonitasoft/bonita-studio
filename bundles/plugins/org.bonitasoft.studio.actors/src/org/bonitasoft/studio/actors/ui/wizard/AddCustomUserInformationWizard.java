/**
 * 
 */
package org.bonitasoft.studio.actors.ui.wizard;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author florine
 *
 */
public class AddCustomUserInformationWizard extends Wizard {

	
	private AddCustomUserInformationWizardPage page;
	
	
	/**
	 * 
	 */
	public AddCustomUserInformationWizard() {
		// TODO Auto-generated constructor stub
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public void addPages() {
		page=new AddCustomUserInformationWizardPage("Add Custom Info");
		addPage(page);
	};
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
