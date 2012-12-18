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

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class InputParamsContentProvider implements IStructuredContentProvider  {

	private List<List<Object>> matrix ;
	private int nbRows;

	public InputParamsContentProvider(){
		nbRows = 0;
		matrix = new ArrayList<List<Object>>();
	}

	public Object[] getElements(Object inputElement) {
		this.matrix = (List<List<Object>>) inputElement;
		nbRows = ((List<List<Object>>) inputElement).size();
		return matrix.toArray();
	}

	public void dispose() {
		matrix = null;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void addRow() {
		ArrayList<Object> row = new ArrayList<Object>();
		matrix.add(row);
		populateRow(row);
		nbRows++;
	}

	private void populateRow(List<Object> row) {
		for (int c = 0; c < getNbColumns(); c++) {
			if (c == 0) {
				row.add(InputParamsEditingSupport.INPUT_SINGLE);
			}else if(row.size() <= c) {
				row.add(""); //$NON-NLS-1$
			}
		}
	}

	public List<List<Object>> getMatrix() {
		return matrix;
	}

	public void removeRow(List<Object> list) {
		matrix.remove(list);
	}

	/**
	 * @param value
	 */
//	public void setMatrix(List<List<Object>> value) {
//		if (value == null) {
//			throw new IllegalArgumentException();
//		}
//		this.matrix = value;
//		nbRows = value.size();
//	}

	public int getNbColumns() {
		return 4;
	}

	public List<List<Object>> getInputParams() {
 		return matrix;
	}
	
	public void addColumn() {
		for (int r = 0; r < nbRows; r++) {
			matrix.get(r).add(""); //$NON-NLS-1$
		}
	}

	public void up(List<Object> list) {
		int listIndex =  matrix.indexOf(list) ;
		if(listIndex > 0){
			Collections.swap(matrix, listIndex, listIndex-1);
		}
	}
	
	public void down(List<Object> list) {
		int listIndex =  matrix.indexOf(list) ;
		if(listIndex < matrix.size()-1){
			Collections.swap(matrix, listIndex, listIndex+1);
		}
	}
	

}
