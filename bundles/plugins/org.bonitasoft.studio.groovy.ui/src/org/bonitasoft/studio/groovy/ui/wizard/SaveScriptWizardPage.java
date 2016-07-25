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

import org.bonitasoft.studio.groovy.library.IFunction;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class SaveScriptWizardPage extends WizardPage {

	private Text nameText;
	private String defaultName;

	protected SaveScriptWizardPage(List<IFunction> methods, String defaultName,List<IFunction> selectedFunctions) {
		super(Messages.saveWizardPageTitle);
		this.setTitle(Messages.saveWizardPageTitle);
		this.setDescription(Messages.saveWizardPageDescription);
		this.setMessage(Messages.saveWizardPageMessage);
		setImageDescriptor(Pics.getWizban());
		this.defaultName = defaultName ;
	}


	public void createControl(Composite parent) {
		Listener validator = new Listener() {
			public void handleEvent(Event e) {
				List<String> errors = getErrors();
				if (errors.isEmpty()) {
					setErrorMessage(null);
					setPageComplete(true);
				} else {
					setErrorMessage(SaveScriptWizardPage.this.toString(errors));
					setPageComplete(false);
				}
			}

		};
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		GridData gd ;

		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText(Messages.nameLabel);

		nameText = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL,SWT.NONE,true,false);
		nameText.setLayoutData(gd);
		nameText.addListener(SWT.Modify, validator);

		if(defaultName != null){
			nameText.setText(defaultName);
		}

		this.setControl(composite);
		validator.handleEvent(null);
	}


	/**
	 * @return
	 */
	private List<String> getErrors() {
		List<String> res = new ArrayList<String>();
		if (nameText == null || nameText.getText() == null) {
			res.add(Messages.wrongName);
		} else {
			final String scriptName = nameText.getText();
			if (scriptName.length() <= 3) {
				res.add(Messages.wrongName);
			} else {
				IStatus validJavaIdentifierName = JavaConventions.validateJavaTypeName(scriptName, JavaCore.VERSION_1_5, JavaCore.VERSION_1_5);
				if (validJavaIdentifierName.getSeverity() == IStatus.ERROR) {
					res.add(Messages.scriptNameMustBeValid);
				}
			}
		}
		
		return res;
	}
	
	private String toString(List<String> errors) {
		StringBuilder sb = new StringBuilder();
		for (String string : errors) {
			sb.append(string);
			sb.append("\n");
		}
		return sb.toString();
	}


	/**
	 * @return the script name
	 */
	public String getName() {
		return nameText.getText();
	}

}
