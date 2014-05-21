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

import org.bonitasoft.studio.model.simulation.Resource;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.simulation.i18n.Messages;

/**
 * @author Romain Bioteau
 */
public class EditSimulationResourceCalendarWizardPage extends EditCalendarWizardPage {

	

	/**
	 * @param artifact
	 */
	protected EditSimulationResourceCalendarWizardPage(Resource resource) {
		super(resource.getCalendar());//$NON-NLS-1$
		setTitle(Messages.EditSimulationResourceCalendarWizardPage_Title);
		setMessage(Messages.EditSimulationResourceCalendarWizardPage_Message);
		this.setImageDescriptor(Pics.getWizban()) ;
	}
	
	

}
