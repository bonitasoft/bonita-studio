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

package org.bonitasoft.studio.groovy.ui.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class ProcessVariableContentProvider implements IStructuredContentProvider {

	public static Object SELECT_ENTRY = new Object();

	TreeMap<String, List<Object>> map;

	@Override
	@SuppressWarnings("unchecked")
	public Object[] getElements(Object inputElement) {
		List<Object> res = new ArrayList<Object>();
		res.addAll((Collection<? extends Object>) inputElement);
		map = getClassifiedVariables(res);
		List<Object> inputElements = new ArrayList<Object>();
		//int i=map.keySet().size()-1;
		for (String key:map.keySet()){
			if (!key.equals(Messages.SelectProcessVariableLabel) && !key.isEmpty()){
				inputElements.add(key);
			}
			List<Object> scriptVariables = map.get(key);
			inputElements.addAll(scriptVariables);
		}
		return inputElements.toArray();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	private TreeMap getClassifiedVariables(List<Object> res){
		TreeMap<String,List<Object>> map = new TreeMap<String,List<Object>>();
		List<Object> select_entry_list = new ArrayList<Object>(1);
		select_entry_list.add(SELECT_ENTRY);
		map.put(Messages.SelectProcessVariableLabel, select_entry_list);
		for (Object object:res){
			if (object instanceof ScriptVariable){
				ScriptVariable scriptVariable = (ScriptVariable)object;
				String key=scriptVariable.getCategory();
				if (key==null){ 
					key="";
				}
				if (map.containsKey(key)){
					List<Object> scriptVariables = map.get(key);
					scriptVariables.add(scriptVariable);
				} else {
					List<Object> scriptVariables = new ArrayList<Object>();
					scriptVariables.add(scriptVariable);
					map.put(key, scriptVariables);
				}
			}

		}
		return map;
	}

}
