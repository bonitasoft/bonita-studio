/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy;


/**
 * @author Romain Bioteau
 *
 */
public class ScriptVariable {

    private String name;
    private String type;
    private String defaultValue;
    private String category;
    private String description;

    public String getCategory() {
		return category;
	}
    
    public String getDescription() {
        return description;
    }

	public void setCategory(String category) {
		this.category = category;
	}
	
	public ScriptVariable(String name,String type){
        this.name = name;
        this.type = type ;
    }

	public ScriptVariable(String name,String type, String defaultValue, String description){
        this.name = name;
        this.type = type ;
        this.description = description;
        setDefaultValue(defaultValue);
    }

    public ScriptVariable(String name,String type,String defaultValue){
        this(name,type);
        setDefaultValue(defaultValue);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}
