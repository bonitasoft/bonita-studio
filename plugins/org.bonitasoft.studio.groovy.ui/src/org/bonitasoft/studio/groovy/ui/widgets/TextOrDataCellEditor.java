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
package org.bonitasoft.studio.groovy.ui.widgets;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ui.providers.ITextOrDataFactory;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData.Type;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier - manage focus to avoid that widget lost focus when opening a dialog
 * 
 */
public class TextOrDataCellEditor extends CellEditor {

	protected Element modelElement;
	protected TextOrData widget;
	
	/**
	 * Listens for 'focusLost' events and  fires the 'apply' event as long
	 * as the focus wasn't lost because the dialog was opened.
	 */
	private FocusListener focusListener = null;
	private ITextOrDataFactory textOrDataFactory;
	private boolean isPassword;
	private boolean usePassword;
	private final Type ioType;
	private boolean showGroovyExpression = true ;

	

	public TextOrDataCellEditor(Composite composite, Element modelElement, ITextOrDataFactory textOrDataFactory) {
		super();
		this.modelElement = modelElement;
		this.textOrDataFactory = textOrDataFactory ;
		this.ioType = TextOrData.Type.READ;
		this.usePassword = true;
		this.isPassword = false;
		create(composite);
	}

	public TextOrDataCellEditor(Composite composite, Element modelElement, ITextOrDataFactory textOrDataFactory, boolean isPassword, boolean usePassword) {
		super();
		this.modelElement = modelElement;
		this.textOrDataFactory = textOrDataFactory ;
		this.usePassword = usePassword;
		this.isPassword = isPassword;
		this.ioType = TextOrData.Type.READ;
		create(composite);
		
	}
	
	/**
	 * @param control
	 * @param modelElement2
	 * @param textOrDataFactory2
	 * @param ioType
	 */
	public TextOrDataCellEditor(Composite control, Element modelElement, ITextOrDataFactory textOrDataFactory, Type ioType, boolean showGroovyExpression) {
		super();
		this.ioType = ioType;
		this.modelElement = modelElement;
		this.textOrDataFactory = textOrDataFactory ;
		this.showGroovyExpression  = showGroovyExpression;
		this.usePassword = true;
		this.isPassword = false;
		create(control);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createControl(Composite parent) {
		widget = textOrDataFactory.createTextOrData(parent, modelElement, getFocusListener(),isPassword,usePassword);
		widget.setIOType(ioType);
		widget.setExepectedIsGroovy(showGroovyExpression) ;
		widget.setSize(parent.getSize());
		if(ioType.equals(Type.WRITE)){
			widget.getControl().setData("XMLComboProvider.AdditionalDataListener", new ModifyListener() {
 
				public void modifyText(ModifyEvent e) {
					String res = widget.getText();
					if (res.toString().endsWith("...")) {
						res = res.substring(0, res.length() - "...".length());
					}
					if (GroovyUtil.isGroovyExpression(res)) {
						res = GroovyUtil.toSimpleExpression(res);
					}
					StringBuilder lvalue = new StringBuilder();
					lvalue.append(res);
					if (e.data != null && ((String)e.data).trim().length() > 0) {
						lvalue.append(BonitaConstants.XPATH_VAR_SEPARATOR);
						lvalue.append(e.data);
					}
					widget.setText(lvalue.toString());
				}
			});
		}
		
		
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
		if (value instanceof String && ((String)value).length() > 0) {
			List<String> firstValue = new ArrayList<String>();
			firstValue.add((String)value);
			this.widget.addAll(firstValue);
			this.widget.setText(value.toString()) ;
		} 
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
					TextOrDataCellEditor.this.focusLost();
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
    
    public TextOrData getTextOrData(){
    	return widget ;
    }
}
