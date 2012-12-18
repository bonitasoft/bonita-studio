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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public class SimplePasswordSuperCombo implements  SuperCombo {

	private CCombo combo;

	public SimplePasswordSuperCombo(Composite parent) {
		super();

		combo = new CCombo(parent, SWT.BORDER );
		combo.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
	
	}

	public String getText() {
		return combo.getText();
	}

	public int getItemCount() {
		return combo.getItemCount();
	}

	public String getItem(int i) {
		return combo.getItem(i);
	}

	public void select(int i) {
		combo.select(i);
	}

	public void setText(String text) {
		combo.setText(text) ;
	}

	public Control getControl() {
		return combo;
	}

	public void add(String entry, int i) {
		combo.add(entry, i) ;
	}

	public void removeAll() {
		combo.removeAll();
	}

	public void add(String string) {
		combo.add(string) ;
	}

	public void addSelectionListener(SelectionListener listener) {
		combo.addSelectionListener(listener) ;
		
	}

	public void removeSelectionListener(SelectionListener listener) {
		combo.removeSelectionListener(listener) ;
	}

	public void setSize(Point size) {
		combo.setSize(size) ;
	}

	public Shell getShell() {
		return combo.getShell();
	}

	public void addListener(int event, Listener listener) {
		combo.addListener(event, listener);
	}

	public void removeListener(int event, Listener listener) {
		combo.removeListener(event, listener);
	}

	public void remove(String item) {
		combo.remove(item) ;
	}

}
