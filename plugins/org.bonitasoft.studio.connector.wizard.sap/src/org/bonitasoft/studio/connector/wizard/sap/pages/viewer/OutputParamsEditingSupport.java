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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.groovy.ui.providers.ITextOrDataFactory;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrDataCellEditor;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.OutputParameterMapping;
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
public class OutputParamsEditingSupport extends EditingSupport {


	private int column;
	private IWizardContainer container;
	private Element modelElement;
	private boolean enable = true;
	private ITextOrDataFactory textOrDataFactory;

	public static final String TABLE_OUTPUT = "table_output"; //$NON-NLS-1$
	public static final String OUTPUT_TABLE = "output_table"; //$NON-NLS-1$
	public static final String OUTPUT_STRUCTURE = "output_structure"; //$NON-NLS-1$
	public static final String OUTPUT_SINGLE = "output_single"; //$NON-NLS-1$

	private static List<String> SAP_OUTPUT_TYPES = new ArrayList<String>();
	static{
		SAP_OUTPUT_TYPES.add(OUTPUT_TABLE);
		SAP_OUTPUT_TYPES.add(OUTPUT_STRUCTURE);
		SAP_OUTPUT_TYPES.add(OUTPUT_SINGLE);
		SAP_OUTPUT_TYPES.add(TABLE_OUTPUT);
	};


	/**
	 * @param viewer
	 */
	public OutputParamsEditingSupport(ColumnViewer viewer, int index,IWizardContainer container,Element modelElement,ITextOrDataFactory textOrDataFactory) {
		super(viewer);
		this.column = index ;
		this.container = container ;
		this.modelElement= modelElement ;
		this.textOrDataFactory = textOrDataFactory ;
	}
	
	public OutputParamsEditingSupport(ColumnViewer viewer, int index,IWizardContainer container,Element modelElement,boolean enable,ITextOrDataFactory textOrDataFactory) {
		this(viewer,index,container,modelElement,textOrDataFactory);
		this.enable  = enable ;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setValue(Object element, Object value) {
		List<Object> line = (List<Object>)element;
		line.set(column, value);
		getViewer().refresh();
		if(column == 3){
			if(getViewer().getContentProvider() instanceof OutputParamsContentProvider){
				int row = getIndexOf(element,((OutputParamsContentProvider)getViewer().getContentProvider()).getMatrix());
				if(row>-1 && row < ((OutputParamsContentProvider)getViewer().getContentProvider()).getOutputParameterMapping().size()){
					OutputParameterMapping out = ((OutputParamsContentProvider)getViewer().getContentProvider()).getOutputParameterMapping().get(row);
					Data targetData = getDataByName(value.toString());
					if(targetData != null){
						out.setTargetData(targetData);
						out.setValueExpression("results.get("+row+")"); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}
		}
		container.updateButtons() ;
		getViewer().getControl().notifyListeners(SWT.Selection,new Event()) ;
	}

	private int getIndexOf(Object element, List<List<String>> matrix) {
		for(int i = 0 ; i< matrix.size(); i++){
			if(matrix.get(i) == element){
				return i ;
			}
		}
		return -1;
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
		}else if(column == 3 && enable){
			cellEditor = new InputParamsCellEditor((Composite) getViewer().getControl(),getVariableNames());
		}else if(column == 3 && !enable){
			cellEditor = new InputParamsCellEditor((Composite) getViewer().getControl(),getVariableNames(),enable);
		}else{
			cellEditor = new TextOrDataCellEditor((Composite) getViewer().getControl(),modelElement,textOrDataFactory);
		}
		return cellEditor;
	}

	private String[] getVariableNames() {
		List<String> data = new ArrayList<String>();
		for(Data d : ModelHelper.getAccessibleData(modelElement)){
			data.add(d.getName());
		}
		return data.toArray(new String[]{});
	}

	private Data getDataByName(String name) {
		for(Data d : ModelHelper.getAccessibleData(modelElement)){
			if(d.getName().equals(name)){
				return d ;
			}
		}
		return null;
	}

	private String[] getTypes(Object element) {
		
		List<String> resultList = new ArrayList<String>(SAP_OUTPUT_TYPES);
		if(element != null && SAP_OUTPUT_TYPES.contains(((List<Object>) element).get(column))){
			resultList.remove(((List<Object>) element).get(column));
		}
		
	
		return resultList.toArray(new String[]{}) ;
	}



	@Override
	protected boolean canEdit(Object element) {
		return true;
	}
}
