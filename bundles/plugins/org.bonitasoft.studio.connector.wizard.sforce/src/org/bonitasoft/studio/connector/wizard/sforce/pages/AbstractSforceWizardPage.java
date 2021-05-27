/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.wizard.sforce.pages;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.wizard.GeneratedConnectorWizardPage;
import org.bonitasoft.studio.connector.wizard.sforce.tooling.SalesforceTool;

import com.sforce.ws.ConnectionException;

/**
 * @author Maxence Raoux
 * 
 */
public abstract class AbstractSforceWizardPage extends
		GeneratedConnectorWizardPage {

	protected SalesforceTool sfTool;

	public AbstractSforceWizardPage() {
		super(AbstractSforceWizardPage.class.getName());
		sfTool = SalesforceTool.getInstance();
	}

	public SalesforceTool getSfTool() {
		return sfTool;
	}

	@Override
	public void dispose() {
		super.dispose();
		try {
			if (sfTool != null && sfTool.isLogged()) {
				sfTool.logout();
			}
		} catch (ConnectionException e) {
			BonitaStudioLog.log(e.getMessage());
		}
		super.dispose();
	}

}
