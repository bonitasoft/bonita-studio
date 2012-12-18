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
package org.bonitasoft.studio.groovy.ui.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class TextAreaSuperCombo implements SuperCombo  {

		private Combo theCombo;
		private final Text textArea;

		/**
		 * @param parent
		 * 
		 */
		public TextAreaSuperCombo (Composite parent, Text textArea) {
			this.textArea = textArea;
			this.theCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY );
			//theCombo.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));

		}
		
		public void removeTextOnCombo(String text) {
			theCombo.remove(text) ;
			theCombo.setText("");
		}

		public void setTextOnCombo(String text){
			theCombo.add(text) ;
			theCombo.setText(text);
		}

		public String getTextOnTextArea(){
			return textArea.getText();
		}
		
		public String getText() {
			return theCombo.getText();
		}

		public int getItemCount() {
			return theCombo.getItemCount();
		}

		public String getItem(int i) {
			return theCombo.getItem(i);
		}

		public void select(int i) {
			theCombo.select(i);
		}

		public void setText(String text) {
			theCombo.setText("");
			textArea.setText(text);
		}

		public Control getControl() {
			return textArea;
		}
		
		public Control getComboControl() {
			return theCombo;
		}

		public void add(String entry, int i) {
			theCombo.add(entry, i);
		}

		public void removeAll() {
			theCombo.removeAll();
		}

		public void add(String string) {
			theCombo.add(string);
		}

		public void addSelectionListener(SelectionListener listener) {
			theCombo.addSelectionListener(listener);

		}

		public void removeSelectionListener(SelectionListener listener) {
			theCombo.removeSelectionListener(listener);
		}

		public void setSize(Point size) {
			theCombo.setSize(size);
		}

		public Shell getShell() {
			return theCombo.getShell();
		}

		public void addListener(int event, Listener listener) {
			theCombo.addListener(event, listener);
		}

		public void removeListener(int event, Listener listener) {
			theCombo.removeListener(event, listener);
		}

		public void remove(String item) {
			theCombo.remove(item);
		}
	
}
