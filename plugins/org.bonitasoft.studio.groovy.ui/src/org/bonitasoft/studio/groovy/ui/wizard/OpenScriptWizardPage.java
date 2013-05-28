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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.groovy.GroovyPlugin;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.dialog.GroovyScriptNameDialog;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Mickael Istria
 *
 */
public class OpenScriptWizardPage extends WizardPage implements IWizardPage {

	private FilteredTree ifileTree;
	private Button removeScriptButton;
	private boolean withCreationButton;
	private Button createScriptButton;
	private IRepositoryStore<?> groovyStore;

	/**
	 * @param openScriptWizard 
	 * @param withCreationButton 
	 * @param example 
	 * 
	 */
	public OpenScriptWizardPage(OpenScriptWizard openScriptWizard, boolean withCreationButton) {
		super(Messages.openScriptWizardPage_title);
		this.setTitle(Messages.openScriptWizardPage_title);
		this.setDescription(Messages.openScriptWizardPage_desc);
		this.setWizard(openScriptWizard);
		this.withCreationButton = withCreationButton;
		setImageDescriptor(Pics.getWizban());
		this.groovyStore = RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class) ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		//Composite repositoryComposite = new Composite(composite, SWT.NONE);
		ifileTree = new FilteredTree(composite, SWT.SINGLE | SWT.BORDER, new PatternFilter(), true);
		GridData processesListLayoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
		processesListLayoutData.heightHint = 250;
		ifileTree.setLayoutData(processesListLayoutData);
		ifileTree.getViewer().setContentProvider(new ScriptTreeContentProvider()); 
	
		ifileTree.getViewer().setLabelProvider(new FileStoreLabelProvider());
		ifileTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				updateRemoveButton();
				setPageComplete(isPageComplete());
			}

		});
		ifileTree.getViewer().getTree().addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event event) {
				if (isPageComplete()) {
					if (canFlipToNextPage()) {
						getContainer().showPage(getNextPage());
					} else if (getWizard().canFinish()) {
						if (getWizard().performFinish()) {
							((WizardDialog)getContainer()).close();
						}
					}
				}
			}
		});
		
		createButtons(composite);
		
		updateRemoveButton();

		// Separator
		Composite blank = new Composite(composite, SWT.NONE);
		blank.setLayoutData(new GridData(SWT.DEFAULT, 40));

		ifileTree.getViewer().setInput(groovyStore.getChildren());

		setWorkspaceThingsEnabled(true);
		setControl(composite);
	}

	private void createButtons(final Composite composite) {
		Composite buttonComposite = new Composite(composite, SWT.NONE);
		buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		
		createDeleteButton(composite, buttonComposite);
		
		if(withCreationButton){
			createScriptButton(buttonComposite);
		}
	}

	private void createDeleteButton(final Composite composite,
			Composite buttonComposite) {
		removeScriptButton = new Button(buttonComposite,SWT.FLAT);
		GridData gd = new GridData();
		gd.widthHint = 90 ;
		removeScriptButton.setLayoutData(gd);
		removeScriptButton.setText(Messages.removeScriptLabel);
		removeScriptButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (ifileTree.isEnabled() && !ifileTree.getViewer().getSelection().isEmpty()) {
					try {
						IRepositoryFileStore selected = (IRepositoryFileStore) ((ITreeSelection)ifileTree.getViewer().getSelection()).getFirstElement();
						if( MessageDialog.openQuestion(composite.getShell(), Messages.confirmScriptDeleteTitle, NLS.bind(Messages.confirmScriptDeleteMessage,selected.getName()))){
							selected.delete();
							ifileTree.getViewer().setInput(groovyStore.getChildren());
						}
					} catch (Exception e1) {
						GroovyPlugin.logError(e1);
					}
				}
			}

		});
	}

	private void createScriptButton(Composite buttonComposite) {
		createScriptButton = new Button(buttonComposite,SWT.FLAT);
		createScriptButton.setLayoutData(GridDataFactory.fillDefaults().hint(90, 0).create());
		createScriptButton.setText(Messages.createScript);
		final IInputValidator validator = new IInputValidator() {
			
			public String isValid(String newText) {
				List<String> errors = getErrors(newText);
				if (errors.isEmpty()) {
					return null;
				} else {
					return toStringError(errors);
				}
			}
		};
		
		createScriptButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				InputDialog inDialog = new GroovyScriptNameDialog(getShell(),
						Messages.scriptNameDialogTitle,
						Messages.scriptNameDialogMessage,
						"",
						validator);
				if(inDialog.open() == Dialog.OK){
					String newGroovyScriptName = inDialog.getValue();
					groovyStore.createRepositoryFileStore(newGroovyScriptName+"."+GroovyRepositoryStore.GROOVY_EXT).save("");
				
					ifileTree.getViewer().setInput(groovyStore.getChildren());
				}
			}
			
		});
	}


	private void updateRemoveButton() {
		if (ifileTree.isEnabled()
				&& !ifileTree.getViewer().getSelection().isEmpty()) {
			IRepositoryFileStore currentlyOpenScript = ((OpenScriptWizard) getWizard()).getCurrentlyOpenScript();
			if(currentlyOpenScript != null){
				IRepositoryFileStore selected = (IRepositoryFileStore) ((ITreeSelection)ifileTree.getViewer().getSelection()).getFirstElement();
				if(currentlyOpenScript.equals(selected)){
					removeScriptButton.setEnabled(false);
				} else {
					removeScriptButton.setEnabled(true);
				}
			} else {	
				removeScriptButton.setEnabled(true);
			}
		}else{
			removeScriptButton.setEnabled(false);
		}
	}

	private void setWorkspaceThingsEnabled(boolean enabled) {
		ifileTree.getFilterControl().setEnabled(enabled);
		ifileTree.getViewer().getTree().setEnabled(enabled);
	}



	@Override
	public boolean isPageComplete() {
		return getFile() != null;
	}

	public IRepositoryFileStore getFile() {
		if (ifileTree.isEnabled() && !ifileTree.getViewer().getSelection().isEmpty()) {
			return (IRepositoryFileStore)((ITreeSelection)ifileTree.getViewer().getSelection()).getFirstElement();
		}
		return null;
	}
	
	private String toStringError(List<String> errors) {
		StringBuilder sb = new StringBuilder();
		for (String string : errors) {
			sb.append(string);
			sb.append("\n");
		}
		return sb.toString();
	}
	
	private List<String> getErrors(String newText) {
		List<String> res = new ArrayList<String>();
		if (newText == null) {
			res.add(Messages.wrongName);
		} else {
			final String scriptName = newText;
			String firstChar = scriptName.isEmpty() ? "" : scriptName.substring(0, 1);
			if(!scriptName.isEmpty() && firstChar.equals(firstChar.toLowerCase())){
				res.add(Messages.wrongNameLowCase);
			}else if (scriptName.length() <= 3) {
				res.add(Messages.wrongName);
			}else {
				IStatus validJavaIdentifierName = JavaConventions.validateJavaTypeName(scriptName, JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
				if (validJavaIdentifierName.getSeverity() == IStatus.ERROR) {
					res.add(Messages.scriptNameMustBeValid);
				}
				GroovyRepositoryStore store = (GroovyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class);
				if(store.getChild(scriptName+"."+GroovyRepositoryStore.GROOVY_EXT) != null){
					res.add(Messages.scriptNameAlreadyExists);
				}
			}
		}
		return res;
	}

}
