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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.repository.themes.model.css.ParserFactory;
import org.bonitasoft.studio.repository.themes.model.css.property.ParseException;
import org.bonitasoft.studio.repository.themes.model.css.property.PropertyParser;

/**
 * @author Romain Bioteau
 *
 */
public class FontCSSValue extends CSSValue {

	private String fontFamily ;
	private String fontSize ;
	private String fontVariant ;
	private String fontWeight ;
	private String fontStyle ;
	private String lineHeight ;
	
	

	public FontCSSValue(String value) {
		super(value);
		parseValue(value) ;
	}

	@SuppressWarnings("restriction")
	private void parseValue(String value) {
		PropertyParser parser = ParserFactory.createPropertyParser(value) ;
		try {
			parser.parseFont();
			LinkedHashMap<String,String> result =  parser.getCssProperties() ;
			setFontFamily(result.get(ATTR_FONT_FAMILY)) ;
			setFontSize(result.get(ATTR_FONT_SIZE));
			setFontStyle(result.get(ATTR_FONT_STYLE));
			setFontVariant(result.get(ATTR_FONT_VARIANT));
			setFontWeight(result.get(ATTR_FONT_WEIGTH)) ;
			setLineHeight(result.get(ATTR_LINE_HEIGHT)) ;
		} catch (ParseException e) {
			setFontFamily(null) ;
			setFontSize(null);
			setFontStyle(null);
			setFontVariant(null);
			setFontWeight(null) ;
			setLineHeight(null) ;
		}
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontVariant() {
		return fontVariant;
	}

	public void setFontVariant(String fontVariant) {
		this.fontVariant = fontVariant;
	}

	public String getFontWeight() {
		return fontWeight;
	}

	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}

	public String getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}


	public String getLineHeight() {
		return lineHeight;
	}

	public void setLineHeight(String lineHeight) {
		this.lineHeight = lineHeight;
	}
	

	
}
