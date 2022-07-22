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
 
package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gmf.runtime.diagram.ui.menus.PopupMenu;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * @author Romain Bioteau
 *
 */
public class CustomPopupMenu extends PopupMenu {

	public CustomPopupMenu(List<?> aContent, ILabelProvider aLabelProvider) {
		super(aContent, aLabelProvider);
	}
	

	
	@Override
	public boolean show(Control parent) {
		Menu menu = new Menu(parent);
	
		Assert.isNotNull(getContent());
		Assert.isNotNull(getLabelProvider());
		final ArrayList<Object> resultThusFar = new ArrayList<Object>();
		for (Iterator<?> iter = getContent().iterator(); iter.hasNext();) {		
			Object contentObject = iter.next();
			createItem(menu, resultThusFar, contentObject);
		}
		menu.setLocation(Display.getCurrent().getCursorLocation().x + 20,Display.getCurrent().getCursorLocation().y  + 20 );
		menu.setVisible(true);

	
		Display display = menu.getDisplay();
		while (!menu.isDisposed() && menu.isVisible()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		if (!menu.isDisposed()) {
			menu.dispose();

			if (getResult() != null) {
				return true;
			}
		}
	
		return false;
	}



	private void createItem(Menu menu, final ArrayList<Object> resultThusFar,
			Object contentObject) {
		
		for(MenuItem item : menu.getItems()){
			if(item.getText().equals(getLabelProvider().getText(contentObject))){
				item.dispose();
			}
		}
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);

		final Object fContentObject = contentObject;
		menuItem.setText(getLabelProvider().getText(contentObject));
		menuItem.setImage(getLabelProvider().getImage(contentObject));
		menuItem.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				resultThusFar.add(fContentObject);
				setResult(resultThusFar);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				resultThusFar.add(fContentObject);
				setResult(resultThusFar);
			}
		});
		
		
		
	}
}
