/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.widgets;

import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Aurelien Pupier A TextOrData with a management of focus on the
 *         passorcombo for cellEditor purpose. Related to these bugs:
 *         https://bugs.eclipse.org/bugs/show_bug.cgi?id=262016
 *         http://www.bonitasoft.org/bugs/view.php?id=1167
 */
public class TextOrDataForCellEditor extends TextOrData {

	protected FocusListener focusListener;

	public TextOrDataForCellEditor(Composite parent, Element modelElement, final FocusListener focusListener, boolean isPassword, boolean usePassword) {
		super(parent, modelElement, isPassword, usePassword);
		this.focusListener = focusListener;
		comboSelectionListener = null;
	}

	public TextOrDataForCellEditor(Composite parent, Element modelElement, final FocusListener focusListener) {
		this(parent, modelElement, focusListener, false, true);
	}

	@Override
	protected void addListeners() {
		addSelectionListener(getComboSelectionListner());
	}

	@Override
	protected SelectionListener getComboSelectionListner() {
		if(comboSelectionListener == null){
			comboSelectionListener = new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					/*Remove all listener in order to avoid to lost focus on the passwordCombo in the cellEditor*/
					getControl().removeFocusListener(focusListener);
					for(Listener l : getControl().getListeners(SWT.FocusOut)){
						getControl().removeListener(SWT.FocusOut, l);
					}
					String value = combo.getText();
					boolean hasAListener = false;
					for(ComboElementsProvider cep : getProviders()){
						if(cep.appliesTo(TextOrDataForCellEditor.this)){
							String newValue = cep.proceed(TextOrDataForCellEditor.this);
							if(newValue != null){
								hasAListener = true;
								combo.setText(newValue);
								previousEntry = combo.getText();
							}
						}
					}
					if (!hasAListener) {
						
						if(value.indexOf(DEFAULT_VALUE_START) > 0 ) {
							value = value.substring(0, value.indexOf(DEFAULT_VALUE_START));
						}
						if (!groovyExpected && value.startsWith(GroovyUtil.GROOVY_PREFIX) && value.endsWith(GroovyUtil.GROOVY_SUFFIX)) {
							value = value.substring(2, value.length() - 1);
						}

						combo.setText(value);
						previousEntry = combo.getText();
					}
					//reset focus since the dialog is closed
					getControl().addFocusListener(focusListener);
				}
			};
		}
		return comboSelectionListener;
	}
}
