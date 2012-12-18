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
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.groovy.ui.providers.ITextOrDataFactory;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData.Type;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

/**
 * @author Mickael Istria
 *
 */
public class TextOrDataTableEditingSupport extends EditingSupport {

	
	private int column;
	private Element modelElement;
	private Collection<ModifyListener> listeners;
	private TextOrDataCellEditor textCellEditor;
	protected ITextOrDataFactory textOrDataFactory;
	private final Type ioType;
	protected boolean showGroovyExpression = true ;

	/**
	 * @param viewer
	 */
	public TextOrDataTableEditingSupport(ColumnViewer viewer, int index, Element modelElement,ITextOrDataFactory textOrDataFactory) {
		this(viewer, index, modelElement, textOrDataFactory, TextOrData.Type.READ);
	}

	/**
	 * @param viewer
	 * @param index
	 * @param modelElement2
	 * @param textOrDataType
	 * @param ioType
	 */
	public TextOrDataTableEditingSupport(ColumnViewer viewer, int index, Element modelElement, ITextOrDataFactory textOrDataType, Type ioType) {
		super(viewer);
		this.column = index;
		this.modelElement = modelElement;
		this.ioType = ioType;
		listeners = new ArrayList<ModifyListener>();
		this.textOrDataFactory = textOrDataType ;
	}

	public TextOrDataTableEditingSupport(ColumnViewer viewer, int index,
			Element modelElement, ITextOrDataFactory textOrDataType,
			Type ioType, Boolean showGroovyExpression) {
		this(viewer,index,modelElement,textOrDataType,ioType) ;
		this.showGroovyExpression  = showGroovyExpression ;
	}

	@Override
	protected void setValue(Object element, Object value) {
		List<String> line = (List<String>)element;
		line.set(column, (String) value);
		for (ModifyListener listener : listeners) {
			Event e = new Event();
			e.widget = textCellEditor.getControl();
			ModifyEvent modifyEvent = new ModifyEvent(e);
			listener.modifyText(modifyEvent);
		}
		getViewer().refresh();
		getViewer().getControl().notifyListeners(SWT.Selection,new Event()) ;
	}
	
	@Override
	protected Object getValue(Object element) {
		return ((List<String>)element).get(column);
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		textCellEditor = new TextOrDataCellEditor((Composite) getViewer().getControl(), modelElement,textOrDataFactory,ioType,showGroovyExpression);
		return textCellEditor;
	}

	
	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	/**
	 * @param modifyListener
	 */
	public void addModifyListener(ModifyListener modifyListener) {
		this.listeners.add(modifyListener);
	}
}
