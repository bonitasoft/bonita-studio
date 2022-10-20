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
package org.bonitasoft.studio.identity.actors.ui.wizard.page;

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.process.Actor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Maxence Raoux
 * 
 */
public class AddActorWizardPage extends WizardPage {

	private Text nameText;
	private Text descriptionText;
	private Button initatorCheckbox;
	private EList<Actor> actors;
	
	public AddActorWizardPage(String pageName, EList<Actor> actors) {
		super(pageName);
		setTitle(pageName);
		setDescription(pageName);
		this.actors = actors;
		this.setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		container.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(7, 7).create());

		final Label nameLabel = new Label(container, SWT.NONE);
		nameLabel.setText(Messages.name + " *");
		nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
		nameText = new Text(container, SWT.BORDER);
		nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		nameText.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
			}
			@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
				nameListener();
			}
		});
		
		final Label descriptionLabel = new Label(container, SWT.NONE);
		descriptionLabel.setText(Messages.description);
		descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());
		descriptionText = new Text(container, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 70).create());

		final Label initiatorLabel = new Label(container, SWT.NONE);
		initiatorLabel.setText(Messages.initiator);
		initiatorLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
		initatorCheckbox = new Button(container, SWT.CHECK);
		initatorCheckbox.setText(Messages.setAsProcessInitiator);
		initatorCheckbox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

		setControl(container);
	}
	
	public String getActorName(){
		return nameText.getText();
	}
	
	public String getActorDocumentation(){
		return descriptionText.getText();
	}
	
	public Boolean isSetAsInitiator(){
		return initatorCheckbox.getSelection();
	}
	
	private void nameListener(){
		if(getActorName().isEmpty()){
			this.setPageComplete(false);
			this.setErrorMessage(Messages.nameIsEmpty);
		} else if(actorNameExist()){
			this.setPageComplete(false);
			this.setErrorMessage(Messages.nameAlreadyExists);
		} else if(getActorName().length()>50) {
			this.setPageComplete(false);
			this.setErrorMessage(Messages.nameTooLong);
		} else {
			this.setPageComplete(true);
			this.setErrorMessage(null);
		}
	}
	
	private Boolean actorNameExist(){
		for(Actor a : actors){
	        if(a.getName().equals(getActorName())){
	        	return true;
	        }
	    }
		return false;
	}
}
