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
package org.bonitasoft.studio.connector.wizard.sap.pages.viewer;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.groovy.ui.providers.ITextOrDataFactory;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrDataCellEditor;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

/**
 * @author Mickael Istria
 *
 */
public class InputParamsEditingSupport extends EditingSupport {


	private int column;
	private IWizardContainer container;
	private Element modelElement;
	private ITextOrDataFactory textOrDataFactory;
	
	public static final String TABLE_INPUT = "table_input"; //$NON-NLS-1$
	public static final String INPUT_TABLE = "input_table"; //$NON-NLS-1$
	public static final String INPUT_STRUCTURE = "input_structure"; //$NON-NLS-1$
	public static final String INPUT_SINGLE = "input_single"; //$NON-NLS-1$

	private static List<String> SAP_INPUT_TYPES = new ArrayList<String>();
	static{
		SAP_INPUT_TYPES.add(INPUT_TABLE);
		SAP_INPUT_TYPES.add(INPUT_STRUCTURE);
		SAP_INPUT_TYPES.add(INPUT_SINGLE);
		SAP_INPUT_TYPES.add(TABLE_INPUT);
	};


	/**
	 * @param viewer
	 */
	public InputParamsEditingSupport(ColumnViewer viewer, int index,IWizardContainer container,Element modelElement,ITextOrDataFactory textOrDataFactory) {
		super(viewer);
		this.column = index ;
		this.container = container ;
		this.modelElement= modelElement ;
		this.textOrDataFactory = textOrDataFactory ;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setValue(Object element, Object value) {
		List<Object> line = (List<Object>)element;
		line.set(column, value);
		getViewer().refresh();
		container.updateButtons() ;
		getViewer().getControl().notifyListeners(SWT.Selection, new Event()) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object getValue(Object element) {
		return ((List<Object>)element).get(column);
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		CellEditor cellEditor = null ;
		if(column == 0){
			cellEditor = new InputParamsCellEditor((Composite) getViewer().getControl(),getTypes(element));
		}else{
			cellEditor = new TextOrDataCellEditor((Composite) getViewer().getControl(),modelElement,textOrDataFactory);
		}
		return cellEditor;
	}

	private String[] getTypes(Object element) {
		
		List<String> resultList = new ArrayList<String>(SAP_INPUT_TYPES);
		if(element != null && SAP_INPUT_TYPES.contains(((List<Object>) element).get(column))){
			resultList.remove(((List<Object>) element).get(column));
		}
		
	
		return resultList.toArray(new String[]{}) ;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}
}
