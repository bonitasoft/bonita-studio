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

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public interface SuperCombo {


	public String getText() ;

	public int getItemCount() ;


	public String getItem(int i) ;

	public void select(int i) ;

	public void setText(String text) ;

	public Control getControl() ;

	public void add(String entry, int i) ;

	public void removeAll() ;

	public void add(String string)  ;

	public void addSelectionListener(SelectionListener editorSelection) ;

	public void removeSelectionListener(SelectionListener editorSelection) ;

	public void setSize(Point size) ;

	public Shell getShell() ;
	
	public void addListener(int modify, Listener listener) ;

	public void removeListener(int modify, Listener listener);

	public void remove(String item);
	
	
}
