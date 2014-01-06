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
package org.bonitasoft.studio.repository.themes.helper;

import java.util.Map;
import java.util.TreeSet;

import org.bonitasoft.theme.model.Binding;

/**
 * 
 * @author Romain Bioteau
 *
 */
public class BindingWrapper implements Comparable<BindingWrapper>{

	private Binding binding ;
	private Map<String,Object> cssProperties ;

	public BindingWrapper(Binding binding, Map<String,Object> cssProperties){
		this.binding  = binding ;
		this.cssProperties = cssProperties ;
	}

	public Binding getBinding() {
		return binding;
	}

	public void setBinding(Binding binding) {
		this.binding = binding;
	}

	public Map<String, Object> getCssProperties() {
		return cssProperties;
	}

	public void setCssProperties(Map<String, Object> cssProperties) {
		this.cssProperties = cssProperties;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder() ;
		if(cssProperties != null){
			TreeSet<String> keys = new TreeSet<String>(cssProperties.keySet()) ;
			for(String key : keys){
				if(cssProperties.get(key) != null){
					sb.append(key +":"+cssProperties.get(key).toString()) ;
				}else{
					sb.append(key +":"+cssProperties.get(key)) ;
				}
			}
		}
		return binding.toString()+","+sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BindingWrapper){
			return toString().equals(obj.toString()) ;
		}
		return super.equals(obj);
	}

	@Override
	public int compareTo(BindingWrapper o) {
		return this.getBinding().getName().compareTo(o.getBinding().getName());
	}

}