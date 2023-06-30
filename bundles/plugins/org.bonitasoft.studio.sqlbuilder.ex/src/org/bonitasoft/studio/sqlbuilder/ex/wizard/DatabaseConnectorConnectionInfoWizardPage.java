/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.sqlbuilder.ex.wizard;

import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.wizard.GeneratedConnectorWizardPage;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public class DatabaseConnectorConnectionInfoWizardPage extends GeneratedConnectorWizardPage {

	private static final String CONNECTION_PAGE = "db";

	public DatabaseConnectorConnectionInfoWizardPage(){
		super(DatabaseConnectorConnectionInfoWizardPage.class.getName());
	}
	
	@Override
	public Control doCreateControl(Composite parent,
			EMFDataBindingContext context) {
		for(Page p : getDefinition().getPage()){
			if(CONNECTION_PAGE.equals(p.getId())){
				setPage(p);
			}
		}
		return super.doCreateControl(parent, context);
	}
	
	@Override
	public IWizardPage getNextPage() {
		AbstractConnectDBWizardWizardPage page = (AbstractConnectDBWizardWizardPage) getWizard().getPage(AbstractConnectDBWizardWizardPage.class.getName());
		if( page.useQueryBuilder() && page.isConnected()){
			return getWizard().getPage(SQLBuilderExtendedWizardPage.class.getName());
		}else{
			return getWizard().getPage(DatabaseConnectorQueryWizardPage.class.getName());
		}
	}

}
