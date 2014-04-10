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
package org.bonitasoft.studio.actors.ui.wizard;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.ui.wizard.page.AddActorWizardPage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Maxence Raoux
 * 
 */
public class AddActorWizard extends Wizard {

	private AddActorWizardPage page;
	private AbstractProcess process;
	private TransactionalEditingDomain editingDomain;
	private Actor newActor;

	public AddActorWizard(EObject eObject, TransactionalEditingDomain editingDomain) {
		super();
		this.setWindowTitle(Messages.newActor);
		setDefaultPageImageDescriptor(Pics.getWizban());
		while(!(eObject instanceof AbstractProcess)){
			eObject = eObject.eContainer();
		}
		this.process = (AbstractProcess)eObject;
		this.editingDomain = editingDomain;
		newActor = null;
	}
	
	public Actor getNewActor(){
		return newActor;
	}

	@Override
	public void addPages() {
		page = new AddActorWizardPage(Messages.actorWizardPageTitle, process.getActors());
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		newActor = ProcessFactory.eINSTANCE.createActor();	
		newActor.setName(page.getActorName());
		newActor.setDocumentation(page.getActorDocumentation());
		if(page.isSetAsInitiator()){
			for (Actor a : process.getActors()) {
				if(a.isInitiator()){
					editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, a, ProcessPackage.Literals.ACTOR__INITIATOR, false));
					break;
				}
			}
			newActor.setInitiator(true);
		} else {
			newActor.setInitiator(false);
		}
		editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, process, ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS, newActor));
		return true;
	}

}
