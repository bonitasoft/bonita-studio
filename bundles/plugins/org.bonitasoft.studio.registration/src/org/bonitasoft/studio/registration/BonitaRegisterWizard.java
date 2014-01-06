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
package org.bonitasoft.studio.registration;

import org.bonitasoft.studio.application.BonitaRegistration;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;

/**
 * the wizard used to gather info from user
 * 
 * @author Baptiste Mesta
 * 
 */
public class BonitaRegisterWizard extends Wizard {

	private BonitaRegisterWizardPage mainPage;

	@Override
	public boolean performFinish() {


		IPreferenceStore prefStore = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
		//infos registered
		prefStore.setValue(BonitaRegistration.BONITA_USER_REGISTERED, 1);
		prefStore.setValue(BonitaRegistration.BONITA_USER_MAIL, mainPage.getEmail());
		prefStore.setValue(BonitaRegistration.BONITA_USER_COUNTRY, mainPage.getCountry());
		prefStore.setValue(BonitaRegistration.BONITA_USER_FIRST_NAME, mainPage.getFirstName());
		prefStore.setValue(BonitaRegistration.BONITA_USER_LAST_NAME, mainPage.getLastName());
		prefStore.setValue(BonitaRegistration.BONITA_USER_PHONE, mainPage.getPhone());
		BonitaRegistration.sendUserInfo(prefStore);

		return true;
	}


	@Override
	public void addPages() {
		mainPage = new BonitaRegisterWizardPage("register");
		addPage(mainPage);
		
		IProduct product = Platform.getProduct();
		setWindowTitle(product.getName());
		setDefaultPageImageDescriptor(Pics.getImageDescriptor("editIcons/welcome.png"));
	}

	@Override
	public boolean canFinish() {
		return mainPage.isPageComplete();
	}

}
