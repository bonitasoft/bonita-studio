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
public class BackgroundCSSValue extends CSSValue {

	private String color ;
	private String image ;
	private String attachment ;
	private String repeat ;
	private String position ;

	public BackgroundCSSValue(String value) {
		super(value);
		parseValue(value) ;
	}

	@SuppressWarnings("restriction")
	private void parseValue(String value) {
		if(value.isEmpty())
			return ;
		value = value.replaceAll("\n", " ") ;
		value = value.replaceAll("\t", " ") ;
		value = value.replaceAll("  ", " ") ;
		value = value.replaceAll("  ", " ") ;
		
		if(value.contains("transparent")){//NOT SUPPORTED BY THE PARSER
			setColor("transparent") ;
			value = value.replace("transparent", "") ;
		}
		
		if(value.isEmpty())
			return ;
		
		PropertyParser parser = ParserFactory.createPropertyParser(value) ;
		try {
			parser.parseBackground();
			LinkedHashMap<String,String> result =  parser.getCssProperties() ;
			
			if(color == null){
				setColor(result.get(ATTR_BACKGROUND_COLOR)) ;
			}
			setAttachment(result.get(ATTR_BACKGROUND_ATTACHEMNT));
			setImage(result.get(ATTR_BACKGROUND_IMAGE));
			setRepeat(result.get(ATTR_BACKGROUND_REPEAT));
			setPosition(result.get(ATTR_BACKGROUND_POSITION)) ;
		} catch (ParseException e) {
			setColor(null) ;
			setAttachment(null);
			setImage(null);
			setRepeat(null);
			setPosition(null) ;
		}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	

	
}
