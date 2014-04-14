/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.repository.themes.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Romain Bioteau
 *
 */
public class CSSRuleImpl {

	private String name;
	private Map<String, String> cssProperties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getCssProperties() {
		return Collections.unmodifiableMap(cssProperties);
	}

	public void setCssProperties(Map<String, String> cssProperties) {
		this.cssProperties = cssProperties;
	}

	public CSSRuleImpl(String ruleName){
		this.name = ruleName ;
		this.cssProperties = new HashMap<String, String>() ;
	}
	
	public CSSRuleImpl(String ruleName,Map<String, String> cssProperties){
		this(ruleName) ;
		this.cssProperties = cssProperties ;
	}

	public void put(String propertyName, String propertieValue) {
		this.cssProperties.put(propertyName, propertieValue) ;
	}
	
	public void remove(String propertyName) {
		this.cssProperties.remove(propertyName) ;
	}

	public String get(String propertyName) {
		return this.cssProperties.get(propertyName) ;
	}
	
}
