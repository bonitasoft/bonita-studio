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
    private final List<IFunction> functionList ;
    private String documentation;


    public FunctionCategory(final String catName){
        name = catName ;
        functionList = new ArrayList<IFunction>();
    }

    public FunctionCategory(final String catName,final String doc){
        this(catName);
        documentation = doc ;
    }

    @Override
    public void addFunction(final IFunction f) {
        if(!functionList.contains(f)){
            functionList.add(f);
        }else{
            functionList.remove(f);
            functionList.add(f);
        }
    }

    @Override
    public String getDocumentation() {
        return documentation;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return functionList.size();
    }

    @Override
    public void removeFunction(final IFunction f) {
        if(functionList.contains(f)){
            functionList.remove(f);
        }
    }

    @Override
    public void setName(final String newName) {
        name = newName ;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (documentation == null ? 0 : documentation.hashCode());
        result = prime * result + (functionList == null ? 0 : functionList.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof IFunctionCategory) {
            return getName().equals(((IFunctionCategory) obj).getName());
        }

        return super.equals(obj);
    }

    @Override
    public List<IFunction> getFunctions() {
        return functionList;
    }



    @Override
    public void removeAllFunctions() {
        functionList.clear();

    }



}
