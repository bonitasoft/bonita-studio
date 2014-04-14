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
package org.bonitasoft.studio.actors.ui.wizard.page;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class NewOrganizationWizardPage extends WizardPage {

	private Organization organization ;
	
	public NewOrganizationWizardPage(Organization organization){
		super(NewOrganizationWizardPage.class.getName()) ;
		setTitle(Messages.newOrganizationTitle) ;
		setDescription(Messages.newOrganizationMsg) ;
		this.organization = organization ;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		
		final EMFDataBindingContext emfDatabindingContext = new EMFDataBindingContext() ;
		
		Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 20).spacing(5, 15).equalWidth(false).create()) ;
		
		Label nameLabel = new Label(mainComposite, SWT.NONE) ;
		nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create()) ;
		nameLabel.setText(Messages.name) ;
		
		Text nameText = new Text(mainComposite, SWT.BORDER) ;
		nameText.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).hint(300, SWT.DEFAULT).create()) ;
		emfDatabindingContext.bindValue(SWTObservables.observeText(nameText, SWT.Modify), EMFObservables.observeValue(organization, OrganizationPackage.Literals.ORGANIZATION__NAME)) ;
		
		Label descriptionLabel = new Label(mainComposite, SWT.NONE) ;
		descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create()) ;
		descriptionLabel.setText(Messages.description) ;
		
		Text descriptionText = new Text(mainComposite, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL) ;
		descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).hint(SWT.DEFAULT, 100).create()) ;
		emfDatabindingContext.bindValue(SWTObservables.observeText(descriptionText, SWT.Modify), EMFObservables.observeValue(organization, OrganizationPackage.Literals.ORGANIZATION__DESCRIPTION)) ;
		
		
		setControl(mainComposite) ;
	}

}
