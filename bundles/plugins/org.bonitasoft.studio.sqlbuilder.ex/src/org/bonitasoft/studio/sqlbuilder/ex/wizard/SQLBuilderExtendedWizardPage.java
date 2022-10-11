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

import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.AbstractConnectorOutputWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectDatabaseOutputTypeWizardPage;
import org.bonitasoft.studio.sqlbuilder.ex.Messages;
import org.bonitasoft.studio.sqlbuilder.ex.builder.BonitaSQLBuilder;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public class SQLBuilderExtendedWizardPage extends AbstractConnectorConfigurationWizardPage {


	private final static int WIDTH = 630;
	private final static int HEIGHT = 600;


	public SQLBuilderExtendedWizardPage(){
		super(SQLBuilderExtendedWizardPage.class.getName());
		setTitle(Messages.sqlBuilderExtendedWizardPageTitle);
		setDescription(Messages.sqlBuilderExtendedWizardPageDescription);

	}

	@Override
	protected Control doCreateControl(Composite parent,EMFDataBindingContext context) {
		final Composite mainComposite = new Composite(parent, SWT.NONE);
		final GridLayout mainCompLayout = new GridLayout(1, true);
		mainComposite.setLayout(mainCompLayout);
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		final AbstractConnectDBWizardWizardPage connectionPage =(AbstractConnectDBWizardWizardPage) getWizard().getPage(AbstractConnectDBWizardWizardPage.class.getName());
		if(connectionPage!= null){
			final BonitaSQLBuilder _sqlBuilder = connectionPage.getSqlBuilder();
			if(_sqlBuilder.getGraphViewer() == null && _sqlBuilder.getSourceViewer() == null && _sqlBuilder.getDesignViewer() == null){
				_sqlBuilder.createClient(mainComposite);

			}
		}
		getContainer().getShell().setSize(WIDTH, HEIGHT);
		return mainComposite;
	}



	@Override
	public IWizardPage getNextPage() {
		final IWizardPage page = getWizard().getPage(SelectDatabaseOutputTypeWizardPage.class.getName());
		if(page == null){
			return getWizard().getPage(AbstractConnectorOutputWizardPage.class.getName());
		}
		return page;
	}

	@Override
	public IWizardPage getPreviousPage() {
		return getWizard().getPage(DatabaseConnectorConnectionInfoWizardPage.class.getName());
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(boolean isOverviewContext) {
	}
}
