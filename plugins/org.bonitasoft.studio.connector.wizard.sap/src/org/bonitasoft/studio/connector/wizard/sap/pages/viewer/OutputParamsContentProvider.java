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
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.model.process.OutputParameterMapping;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class OutputParamsContentProvider implements IStructuredContentProvider  {

	private List<List<String>> matrix ;
	private int nbRows;
	private List<OutputParameterMapping> outputs ;

	public OutputParamsContentProvider(){
		nbRows = 0;
		matrix = new ArrayList<List<String>>();
		outputs = new ArrayList<OutputParameterMapping>();
	}

	public Object[] getElements(Object inputElement) {
		return matrix.toArray();
	}

	public void dispose() {

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/**
	 * Add an empty row.
	 */
	public void addRow() {
		ArrayList<String> row = new ArrayList<String>();
		OutputParameterMapping out = ProcessFactory.eINSTANCE.createOutputParameterMapping() ;
		outputs.add(out);
		matrix.add(row);
		populateRow(row);
		nbRows++;
	}

	private void populateRow(List<String> row) {
		for (int c = 0; c < getNbColumns(); c++) {
			if (c == 0) {
				row.add(OutputParamsEditingSupport.OUTPUT_SINGLE);
			}else if(row.size() <= c) {
				row.add(""); //$NON-NLS-1$
			}
		}
	}

	public List<List<String>> getMatrix() {
		return matrix;
	}

	public List<OutputParameterMapping> getOutputParameterMapping() {
		return outputs;
	}
	
	public void removeRow(List<Object> list) {
		if(outputs.indexOf(matrix.indexOf(list)) != -1){
			outputs.remove(matrix.indexOf(list));
		}
		
		matrix.remove(list);
	}

	/**
	 * @param value
	 */
	public void setMatrix(List<List<String>> value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		this.matrix = value;
		nbRows = value.size();
	}
	
	public void setOutputParameterMapping(List<OutputParameterMapping> outputs) {
		if (outputs == null) {
			throw new IllegalArgumentException();
		}
		this.outputs = outputs;
	}

	public int getNbColumns() {
		return 4;
	}

	public List<List<String>> getInputParams() {
 		return matrix;
	}
	
	public void addColumn() {
		for (int r = 0; r < nbRows; r++) {
			matrix.get(r).add(""); //$NON-NLS-1$
		}
	}

	public void up(List<String> list) {
		int listIndex =  matrix.indexOf(list) ;
		if(listIndex > 0){
			Collections.swap(matrix, listIndex, listIndex-1);
			
			if(outputs.get(listIndex).getValueExpression() != null){
				outputs.get(listIndex).setValueExpression("results.get("+(listIndex-1)+")"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if(outputs.get(listIndex-1).getValueExpression() != null){
				outputs.get(listIndex-1).setValueExpression("results.get("+listIndex+")");  //$NON-NLS-1$//$NON-NLS-2$
			}
			
			Collections.swap(outputs, listIndex, listIndex-1);
		}
	}
	
	public void down(List<String> list) {
		int listIndex =  matrix.indexOf(list) ;
		if(listIndex < matrix.size()-1){
			Collections.swap(matrix, listIndex, listIndex+1);
			
			if(outputs.get(listIndex).getValueExpression() != null){
				outputs.get(listIndex).setValueExpression("results.get("+(listIndex+1)+")"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if(outputs.get(listIndex+1).getValueExpression() != null){
				outputs.get(listIndex+1).setValueExpression("results.get("+listIndex+")");  //$NON-NLS-1$//$NON-NLS-2$
			}
			
			Collections.swap(outputs, listIndex, listIndex+1);
		}
	}	
}
