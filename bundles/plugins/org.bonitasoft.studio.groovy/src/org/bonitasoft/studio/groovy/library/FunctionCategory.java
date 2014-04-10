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
 
package org.bonitasoft.studio.groovy.library;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Romain Bioteau
 *
 */
public class FunctionCategory implements IFunctionCategory{

	private String name ;
	private List<IFunction> functionList ;
	private String documentation;
	
	
	public FunctionCategory(String catName){
		name = catName ;
		functionList = new ArrayList<IFunction>();
	}
	
	public FunctionCategory(String catName,String doc){
		this(catName);
		documentation = doc ;
	}
	
	public void addFunction(IFunction f) {
		if(!functionList.contains(f)){
			functionList.add(f);
		}else{
			functionList.remove(f);
			functionList.add(f);
		}
	}

	public String getDocumentation() {
		return documentation;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return functionList.size();
	}

	public void removeFunction(IFunction f) {
		if(functionList.contains(f)){
			functionList.remove(f);
		}
	}

	public void setName(String newName) {
		name = newName ;
	}

	public List<IFunction> getFunctions() {
		return functionList;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof IFunctionCategory)
			return getName().equals(((IFunctionCategory) obj).getName());
		
		return super.equals(obj);
	}

	public void removeAllFunctions() {
		functionList.clear();
		
	}
	
	

}
