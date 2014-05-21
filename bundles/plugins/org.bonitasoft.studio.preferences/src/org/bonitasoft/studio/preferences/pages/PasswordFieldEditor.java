/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences.pages;

import org.bonitasoft.studio.preferences.i18n.Messages;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @author Aurelien Pupier
 *
 */
public class PasswordFieldEditor extends StringFieldEditor {

    public PasswordFieldEditor(String userPassword, String userPasswordLabel,
			Composite fieldEditorParent) {
		super(userPassword, userPasswordLabel, fieldEditorParent);
	}

	/**
     * Returns this field editor's text control.
     * <p>
     * The control is created if it does not yet exist
     * </p>
     *
     * @param parent the parent
     * @return the text control
     */
    public Text getTextControl(Composite parent) {
    	/*Create a composite to contain the TextBox and the checkbox*/
    	final Composite textAndCheckboxcomposite = new Composite(parent, SWT.NONE);
    	textAndCheckboxcomposite.setLayout(GridLayoutFactory.fillDefaults().create());
    	textAndCheckboxcomposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
    	
    	/*Create Text and Checkbox*/
    	final Text textControl = super.getTextControl(textAndCheckboxcomposite);
    	textControl.setEchoChar('*');
    	final Button checkboxToShowPassword = new Button(textAndCheckboxcomposite, SWT.CHECK);
		checkboxToShowPassword.setText(Messages.showPassword);
		
		/*Add a listener to hide/show password when Checkbox is checked/unchecked*/
		checkboxToShowPassword.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				if(checkboxToShowPassword.getSelection()){
					textControl.setEchoChar('\0');//show password
				} else {
					textControl.setEchoChar('*');//hide password, replace all characters by *
				}
			}
		});
		
		return textControl;
    }
}
