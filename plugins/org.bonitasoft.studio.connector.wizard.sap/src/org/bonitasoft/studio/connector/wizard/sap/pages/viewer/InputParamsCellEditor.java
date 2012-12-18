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

package org.bonitasoft.studio.connector.wizard.sap.pages.viewer;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public class InputParamsCellEditor extends CellEditor {


	protected TextOrData widget;
	
	/**
	 * Listens for 'focusLost' events and  fires the 'apply' event as long
	 * as the focus wasn't lost because the dialog was opened.
	 */
	private FocusListener focusListener = null;
	private String[] initialValue;

	private boolean enable = true;
	

	public InputParamsCellEditor(Composite composite,String[] initialValue) {
		super();
		this.initialValue = initialValue ;
		create(composite);
	}
	
	public InputParamsCellEditor(Composite control, String[] variableNames,
			boolean enable) {
		super();
		this.initialValue = variableNames ;
		this.enable = enable ;
		create(control);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createControl(Composite parent) {
		widget = new TextOrDataForInputParamsCellEditor(parent, getFocusListener(),initialValue);
		widget.setSize(parent.getSize());
		widget.getControl().addFocusListener(getFocusListener());
		widget.getControl().setEnabled(enable);
		
		return widget.getControl();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		return widget.getText();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#doSetFocus()
	 */
	@Override
	protected void doSetFocus() {
		widget.getControl().setFocus();
		widget.getControl().addFocusListener(getFocusListener());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object value) {
		if (value != null && value instanceof String && ((String)value).length() > 0) {
			List<String> firstValue = new ArrayList<String>();
			firstValue.add((String)value);
			this.widget.addAll(firstValue);
		}
		this.widget.setText((String)value);
	}
	

	@Override
    public LayoutData getLayoutData() {
        return new LayoutData();
    }
	
	/**
     * Return a listener for widget focus.
     * @return FocusListener
     */
    protected FocusListener getFocusListener() {
    	if (focusListener == null) {
    		focusListener = new FocusListener() {

				/* (non-Javadoc)
				 * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
				 */
				public void focusGained(FocusEvent e) {
					// Do nothing
				}

				/* (non-Javadoc)
				 * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
				 */
				public void focusLost(FocusEvent e) {
					InputParamsCellEditor.this.focusLost();
				}
    		};
    	}
    	
    	return focusListener;
	}
    
    @Override
    public void deactivate() {
    	if(widget != null){
    		widget.getControl().removeFocusListener(getFocusListener());
    	}
    	super.deactivate();
    }

}
