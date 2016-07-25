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

package org.bonitasoft.studio.groovy.ui.wizard;

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.groovy.library.IFunction;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 *
 */
public class SaveScriptWizard extends Wizard implements IWizard {

	private List<IFunction> methods ;
	private List<IFunction> chosenFunctions ;
	private SaveScriptWizardPage page ;
	private String name;
	private boolean overwrite = true ;
	private String defaultName;
	private List<IFunction> selectedMethods;
	private IRepositoryStore<?> groovyStore;
	private IDocument document;
	private IRepositoryFileStore fragment;

	public SaveScriptWizard() {
		super();
	}

	public SaveScriptWizard(String defaultName,IDocument document) {
		this();
		this.defaultName = defaultName ;
		this.document = document ;
		this.groovyStore = RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class) ;
	}


	@Override
	public void addPages() {
		page = new SaveScriptWizardPage(methods,defaultName,selectedMethods);
		this.addPage(page);
	}

	@Override
	public boolean performFinish() {
		name = page.getName();
		fragment = groovyStore.createRepositoryFileStore(name+"."+GroovyRepositoryStore.GROOVY_EXT);
		if(fragment.getResource().exists()){
			if(!MessageDialog.openQuestion(getShell(), Messages.overwriteQuestionTitle, Messages.bind(Messages.overwriteQuestionMessage,name))){
				overwrite = false ;
				return false ;
			}
		}
		fragment.save(document.get().trim());
		MessageDialog.openInformation(getShell(), Messages.saveSuccesfullTitle,Messages.bind(Messages.saveSuccesfullMessage,fragment.getName()));

		return true;
	}

	public IRepositoryFileStore getFile(){
		return fragment ;
	}
	
	public String getName(){
		return name ;
	}

}
