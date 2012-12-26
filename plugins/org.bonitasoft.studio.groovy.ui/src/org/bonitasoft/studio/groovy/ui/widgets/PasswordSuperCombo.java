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

import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public class PasswordSuperCombo implements  SuperCombo {

	private Composite passwordComposite;
	private PasswordCombo passwordCombo;

	public PasswordSuperCombo(Composite parent) {
		super();
		passwordComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		passwordComposite.setLayout(layout);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		passwordCombo = new PasswordCombo(passwordComposite, SWT.BORDER );
		passwordCombo.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		final Button checkbox = new Button(passwordComposite, SWT.CHECK);
		checkbox.setLayoutData(new GridData(SWT.LEFT, SWT.DEFAULT, false, false));
		checkbox.setSelection(false);
		passwordCombo.setEchoChar('*');
		checkbox.setText(Messages.showPassword);
		checkbox.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (checkbox.getSelection()) {
					passwordCombo.setEchoChar('\0');
				} else {
					passwordCombo.setEchoChar('*');
				}
			}
		});
	}

	public String getText() {
		return passwordCombo.getText();
	}

	public int getItemCount() {
		return passwordCombo.getItemCount();
	}

	public String getItem(int i) {
		return passwordCombo.getItem(i);
	}

	public void select(int i) {
		passwordCombo.select(i);
	}

	public void setText(String text) {
		passwordCombo.setText(text) ;
	}

	public Control getControl() {
		return passwordComposite;
	}

	public void add(String entry, int i) {
		passwordCombo.add(entry, i) ;
	}

	public void removeAll() {
		passwordCombo.removeAll();
	}

	public void add(String string) {
		passwordCombo.add(string) ;
	}

	public void addSelectionListener(SelectionListener listener) {
		passwordCombo.addSelectionListener(listener) ;
		
	}

	public void removeSelectionListener(SelectionListener listener) {
		passwordCombo.removeSelectionListener(listener) ;
	}

	public void setSize(Point size) {
		passwordCombo.setSize(size) ;
	}

	public Shell getShell() {
		return passwordComposite.getShell();
	}

	public void addListener(int event, Listener listener) {
		passwordCombo.addListener(event, listener);
	}

	public void removeListener(int event, Listener listener) {
		passwordCombo.removeListener(event, listener);
	}

	public void remove(String item) {
		passwordCombo.remove(item) ;
	}
}
