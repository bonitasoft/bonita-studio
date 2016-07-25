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

import java.util.LinkedHashMap;

import org.bonitasoft.studio.repository.themes.model.css.ParserFactory;
import org.bonitasoft.studio.repository.themes.model.css.property.ParseException;
import org.bonitasoft.studio.repository.themes.model.css.property.PropertyParser;

/**
 * @author Romain Bioteau
 *
 */
public class BorderCSSValue extends CSSValue{

	private String width ;
	private String style ;
	private String color ;

	public BorderCSSValue(String value) {
		super(value);
		parseValue(value) ;
	}

	public BorderCSSValue(String width, String color, String style) {
		super("");
		setWidth(width) ;
		setColor(color) ;
		setStyle(style) ;
	}

	private void parseValue(String value) {
		if(value.isEmpty()){
			return ;
		}
		if(value.equals("0")){
			setWidth("0") ;
		}else{
			PropertyParser parser = ParserFactory.createPropertyParser(value) ;
			try {
				parser.parseBorder();
				LinkedHashMap<String,String> result =  parser.getCssProperties() ;
				setWidth(result.get(ATTR_BORDER_TOP_WIDTH)) ;
				setStyle(result.get(ATTR_BORDER_TOP_STYLE));
				setColor(result.get(ATTR_BORDER_TOP_COLOR));
			} catch (ParseException e) {
				setWidth(null) ;
				setStyle(null);
				setColor(null);
			}
		}
	}


	public String getWidth() {
		return width;
	}

	public String getStyle() {
		return style;
	}

	public String getColor() {
		return color;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String getRawValue(){
		StringBuilder sb = new StringBuilder();
		if(width != null && !width.isEmpty()){
			sb.append(width) ;
			sb.append(" ") ;
		}
		if(style != null  && !style.isEmpty()){
			sb.append(style) ;
			sb.append(" ") ;
		}
		if(color != null  && !color.isEmpty()){
			sb.append(color) ;
		}
		return sb.toString().trim();
	}
	
	public static boolean isValidWidth(String value){
		PropertyParser parser = ParserFactory.createPropertyParser(value) ;
		try {
			parser.parseBorderWidth();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isValidColor(String value) {
		PropertyParser parser = ParserFactory.createPropertyParser(value) ;
		try {
			parser.parseBorderColor();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
