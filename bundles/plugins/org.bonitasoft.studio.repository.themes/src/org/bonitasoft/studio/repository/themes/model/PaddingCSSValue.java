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

/**
 * @author Romain Bioteau
 *
 */
public class PaddingCSSValue extends CSSValue {

	private String top ;
	private String right ;
	private String bottom ;
	private String left ;
	
	public PaddingCSSValue(String value) {
		super(value);
		parseValue(value) ;
	}

	private void parseValue(String value) {
		
		String[] values = value.split(" ") ;
		if(values.length == 4){
			top = values[0] ;
			right = values[1] ;
			bottom = values[2] ;
			left = values[3] ;
		}else if(values.length == 3){
			top = values[0] ;
			right = values[1] ;
			bottom = values[2] ;
		}else if(values.length == 2){
			top = values[0] ;
			right = values[1]  ;
		}else if(values.length == 1){
			top = values[0] ;
		}
	}


	
	@Override
	public String getRawValue(){
		StringBuilder sb = new StringBuilder();
		if(top != null && !top.isEmpty()){
			sb.append(top) ;
			sb.append(" ") ;
		}
		if(right != null && !right.isEmpty()){
			sb.append(right) ;
			sb.append(" ") ;
		}
		if(bottom != null && !bottom.isEmpty()){
			sb.append(bottom) ;
			sb.append(" ") ;
		}
		
		if(left != null && !left.isEmpty()){
			sb.append(left) ;
		}
		return sb.toString().trim();
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	
}
