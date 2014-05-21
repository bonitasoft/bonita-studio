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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.repository.themes.model.BackgroundCSSValue;
import org.bonitasoft.studio.repository.themes.model.BorderCSSValue;
import org.bonitasoft.studio.repository.themes.model.CSSPropertyConstants;
import org.bonitasoft.studio.repository.themes.model.FontCSSValue;
import org.bonitasoft.studio.repository.themes.model.MarginCSSValue;
import org.bonitasoft.studio.repository.themes.model.PaddingCSSValue;
import org.bonitasoft.theme.css.CSSProperties;
import org.eclipse.swt.graphics.RGB;

/**
 * 
 * @author Romain Bioteau
 *
 */
public class CSSUtil implements CSSPropertyConstants {




	private static final String[] REPEAT_VALUES = new String[]{
		"repeat",
		"repeat-x",
		"repeat-y",
		"no-repeat"
	} ;

	private static final String[] MARGIN_VALUES = new String[]{
		"auto"
	} ;

	private static final String[] DISPLAY_VALUES = new String[]{
		"none",
		"block",
		"inline",
		"list-item"
	} ;

	private static final String[] WHITESPACE_VALUES = new String[]{
		"normal",
		"pre",
		"no-wrap",
		"pre-wrap",
		"pre-line "
	} ;

	private static final String[] BORDER_WIDTH_VALUES = new String[]{
		"thin",
		"medium",
		"thick"
	} ;


	private static final String[] CLEAR_VALUES =  new String[]{
		"none","left","right","both" 
	} ;


	private static final String[] FLOAT_VALUES =  new String[]{
		"left","right","none" 
	} ;

	private static final String[] ATTACHMENT_VALUES =  new String[]{
		"scroll","fixed" 
	} ;

	private static String[] FONT_STYLES = new String[]{
		"normal",
		"italic"
	} ;

	private static String[] FONT_VARIANT = new String[]{
		"normal",
		"small-caps"
	} ;

	private static String[] FONT_WEIGHT = new String[]{
		"normal",
		"bold"
	} ;

	private static String[] FONT_SIZE = new String[]{
		"xx-small",
		"x-small",
		"small",
		"medium",
		"large",
		"x-large",
		"xx-large"
	} ;

	private static String[] BORDER_STYLE = new String[]{
		"none", 
		"hidden",	
		"dotted", 	
		"dashed",
		"solid",	
		"double",	
		"groove",
		"ridge",
		"inset",
		"outset"

	} ;

	private static String[] POSITION_VALUES = new String[]{
		"top", 
		"right",	
		"bottom", 	
		"left",
		"center"
	} ;


	private static SortedMap<String, RGB> cssColorMap;

	public static Map<String, Object> getPropertyMap(String rule,CSSProperties cssPropertyFile){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			result.put(ATTR_FONT_FAMILY,cssPropertyFile.get(rule, ATTR_FONT_FAMILY));
			result.put(ATTR_FONT_SIZE,cssPropertyFile.get(rule, ATTR_FONT_SIZE));
			result.put(ATTR_FONT_STYLE,cssPropertyFile.get(rule, ATTR_FONT_STYLE));
			result.put(ATTR_FONT_VARIANT,cssPropertyFile.get(rule, ATTR_FONT_VARIANT));
			result.put(ATTR_FONT_WEIGTH,cssPropertyFile.get(rule, ATTR_FONT_WEIGTH));
			result.put(ATTR_TEXT_ALIGN,cssPropertyFile.get(rule, ATTR_TEXT_ALIGN));
			result.put(ATTR_TEXT_INDENT,cssPropertyFile.get(rule, ATTR_TEXT_INDENT));
			result.put(ATTR_TEXT_DECORATION,cssPropertyFile.get(rule, ATTR_TEXT_DECORATION));
			result.put(ATTR_LETTER_SPACING,cssPropertyFile.get(rule, ATTR_LETTER_SPACING));
			result.put(ATTR_WORD_SPACING,cssPropertyFile.get(rule, ATTR_WORD_SPACING));
			result.put(ATTR_TEXT_TRANSFORM,cssPropertyFile.get(rule, ATTR_TEXT_TRANSFORM));
			result.put(ATTR_WHITE_SPACE,cssPropertyFile.get(rule, ATTR_WHITE_SPACE));

			MarginCSSValue marginValue = null ; 
			String raw = cssPropertyFile.get(rule, ATTR_MARGIN_BOTTOM) ;
			if(raw != null){
				marginValue = new MarginCSSValue(raw) ;
			}
			result.put(ATTR_PADDING_BOTTOM,marginValue);

			raw = cssPropertyFile.get(rule, ATTR_MARGIN_LEFT) ;
			marginValue = null ; 
			if(raw != null){
				marginValue = new MarginCSSValue(raw) ;
			}
			result.put(ATTR_PADDING_LEFT,marginValue);

			raw = cssPropertyFile.get(rule, ATTR_MARGIN_RIGHT) ;
			marginValue = null ; 
			if(raw != null){
				marginValue = new MarginCSSValue(raw) ;
			}
			result.put(ATTR_PADDING_RIGHT,marginValue);

			raw = cssPropertyFile.get(rule, ATTR_MARGIN_TOP) ;
			marginValue = null ; 
			if(raw != null){
				marginValue = new MarginCSSValue(raw) ;
			}
			result.put(ATTR_MARGIN_TOP,marginValue);


			raw = cssPropertyFile.get(rule, ATTR_MARGIN) ;
			marginValue = null ; 
			if(raw != null){
				marginValue = new MarginCSSValue(raw) ;
			}
			result.put(ATTR_MARGIN,marginValue);



			PaddingCSSValue value = null ; 
			raw = cssPropertyFile.get(rule, ATTR_PADDING_BOTTOM) ;
			if(raw != null){
				value = new PaddingCSSValue(raw) ;
			}
			result.put(ATTR_PADDING_BOTTOM,value);

			raw = cssPropertyFile.get(rule, ATTR_PADDING_LEFT) ;
			value = null ; 
			if(raw != null){
				value = new PaddingCSSValue(raw) ;
			}
			result.put(ATTR_PADDING_LEFT,value);

			raw = cssPropertyFile.get(rule, ATTR_PADDING_RIGHT) ;
			value = null ; 
			if(raw != null){
				value = new PaddingCSSValue(raw) ;
			}
			result.put(ATTR_PADDING_RIGHT,value);

			raw = cssPropertyFile.get(rule, ATTR_PADDING_TOP) ;
			value = null ; 
			if(raw != null){
				value = new PaddingCSSValue(raw) ;
			}
			result.put(ATTR_PADDING_TOP,value);


			raw = cssPropertyFile.get(rule, ATTR_PADDING) ;
			value = null ; 
			if(raw != null){
				value = new PaddingCSSValue(raw) ;
			}
			result.put(ATTR_PADDING,value);

			result.put(ATTR_COLOR,cssPropertyFile.get(rule, ATTR_COLOR));
			result.put(ATTR_BACKGROUND_COLOR,cssPropertyFile.get(rule, ATTR_BACKGROUND_COLOR));
			result.put(ATTR_BACKGROUND_IMAGE,cssPropertyFile.get(rule, ATTR_BACKGROUND_IMAGE));
			result.put(ATTR_BACKGROUND_REPEAT,cssPropertyFile.get(rule, ATTR_BACKGROUND_REPEAT));
			result.put(ATTR_BACKGROUND_ATTACHEMNT,cssPropertyFile.get(rule, ATTR_BACKGROUND_ATTACHEMNT));
			result.put(ATTR_BACKGROUND_POSITION,cssPropertyFile.get(rule, ATTR_BACKGROUND_POSITION));
			result.put(ATTR_BACKGROUND_SIZE,cssPropertyFile.get(rule, ATTR_BACKGROUND_SIZE));

			result.put(ATTR_ORPHANS,cssPropertyFile.get(rule, ATTR_ORPHANS));
			result.put(ATTR_WIDOWS,cssPropertyFile.get(rule, ATTR_WIDOWS));
			result.put(ATTR_DISPLAY,cssPropertyFile.get(rule, ATTR_DISPLAY));
			result.put(ATTR_PAGE_BREAK_BEFORE,cssPropertyFile.get(rule, ATTR_PAGE_BREAK_BEFORE));
			result.put(ATTR_PAGE_BREAK_AFTER,cssPropertyFile.get(rule, ATTR_PAGE_BREAK_AFTER));
			result.put(ATTR_PAGE_BREAK_INSIDE,cssPropertyFile.get(rule, ATTR_PAGE_BREAK_INSIDE));
			result.put(ATTR_VERTICAL_ALIGN,cssPropertyFile.get(rule, ATTR_VERTICAL_ALIGN));
			result.put(ATTR_LINE_HEIGHT,cssPropertyFile.get(rule, ATTR_LINE_HEIGHT));

			String color = null ;
			String width = null ;
			String style = null ;

			BorderCSSValue borderValue = null ; 
			raw = cssPropertyFile.get(rule, ATTR_BORDER_BOTTOM) ;
			if(raw != null){
				borderValue = new BorderCSSValue(raw) ;
				if(borderValue.getColor() != null){
					result.put(ATTR_BORDER_BOTTOM_COLOR,borderValue.getColor() );
				}
				if(borderValue.getWidth() != null){
					result.put(ATTR_BORDER_BOTTOM_WIDTH,borderValue.getWidth() );
				}
				if(borderValue.getStyle() != null){
					result.put(ATTR_BORDER_BOTTOM_STYLE,borderValue.getStyle() );
				}
			}
			result.put(ATTR_BORDER_BOTTOM,null);

			raw = cssPropertyFile.get(rule, ATTR_BORDER_LEFT) ;
	
			if(raw != null){
				borderValue = new BorderCSSValue(raw) ;
				if(borderValue.getColor() != null){
					result.put(ATTR_BORDER_LEFT_COLOR,borderValue.getColor() );
				}
				if(borderValue.getWidth() != null){
					result.put(ATTR_BORDER_LEFT_WIDTH,borderValue.getWidth() );
				}
				if(borderValue.getStyle() != null){
					result.put(ATTR_BORDER_LEFT_STYLE,borderValue.getStyle() );
				}
			}
			result.put(ATTR_BORDER_LEFT,null);

			raw = cssPropertyFile.get(rule, ATTR_BORDER_RIGHT) ;

			if(raw != null){
				borderValue = new BorderCSSValue(raw) ;
				if(borderValue.getColor() != null){
					result.put(ATTR_BORDER_RIGHT_COLOR,borderValue.getColor() );
				}
				if(borderValue.getWidth() != null){
					result.put(ATTR_BORDER_RIGHT_WIDTH,borderValue.getWidth() );
				}
				if(borderValue.getStyle() != null){
					result.put(ATTR_BORDER_RIGHT_STYLE,borderValue.getStyle() );
				}
			}
			result.put(ATTR_BORDER_RIGHT,null);

			raw = cssPropertyFile.get(rule, ATTR_BORDER_TOP) ;
	
			if(raw != null){
				borderValue = new BorderCSSValue(raw) ;
				if(borderValue.getColor() != null){
					result.put(ATTR_BORDER_TOP_COLOR,borderValue.getColor() );
				}
				if(borderValue.getWidth() != null){
					result.put(ATTR_BORDER_TOP_WIDTH,borderValue.getWidth() );
				}
				if(borderValue.getStyle() != null){
					result.put(ATTR_BORDER_TOP_STYLE,borderValue.getStyle() );
				}
			}
			result.put(ATTR_BORDER_TOP,null);


			raw = cssPropertyFile.get(rule, ATTR_BORDER) ;

			if(raw != null){
				borderValue = new BorderCSSValue(raw) ;
				if(borderValue.getColor() != null){
					result.put(ATTR_BORDER_COLOR,borderValue.getColor() );
				}
				if(borderValue.getWidth() != null){
					result.put(ATTR_BORDER_WIDTH,borderValue.getWidth() );
				}
				if(borderValue.getStyle() != null){
					result.put(ATTR_BORDER_STYLE,borderValue.getStyle() );
				}
			}
			result.put(ATTR_BORDER,null);

			color = cssPropertyFile.get(rule, ATTR_BORDER_BOTTOM_COLOR) ;
			if(color != null){
				result.put(ATTR_BORDER_BOTTOM_COLOR,color);
			}
			style = cssPropertyFile.get(rule, ATTR_BORDER_BOTTOM_STYLE) ;
			if(style != null){
				result.put(ATTR_BORDER_BOTTOM_STYLE,style);
			}
			width = cssPropertyFile.get(rule, ATTR_BORDER_BOTTOM_WIDTH) ;
			if(width != null){
				result.put(ATTR_BORDER_BOTTOM_WIDTH,width);
			}

			borderValue = new BorderCSSValue((String)result.get(ATTR_BORDER_BOTTOM_WIDTH),(String)result.get(ATTR_BORDER_BOTTOM_COLOR),(String)result.get(ATTR_BORDER_BOTTOM_STYLE)) ;
			if(!borderValue.toString().isEmpty()){
				result.put(ATTR_BORDER_BOTTOM,borderValue) ;
			}

			color = cssPropertyFile.get(rule, ATTR_BORDER_LEFT_COLOR) ;
			if(color != null){
				result.put(ATTR_BORDER_LEFT_COLOR,color);
			}
			style = cssPropertyFile.get(rule, ATTR_BORDER_LEFT_STYLE) ;
			if(style != null){
				result.put(ATTR_BORDER_LEFT_STYLE,style);
			}
			width = cssPropertyFile.get(rule, ATTR_BORDER_LEFT_WIDTH) ;
			if(width != null){
				result.put(ATTR_BORDER_LEFT_WIDTH,width);
			}

			borderValue = new BorderCSSValue((String)result.get(ATTR_BORDER_LEFT_WIDTH),(String)result.get(ATTR_BORDER_LEFT_COLOR),(String)result.get(ATTR_BORDER_LEFT_STYLE)) ;
			if(!borderValue.toString().isEmpty()){
				result.put(ATTR_BORDER_LEFT,borderValue) ;
			}

			color = cssPropertyFile.get(rule, ATTR_BORDER_RIGHT_COLOR) ;
			if(color != null){
				result.put(ATTR_BORDER_RIGHT_COLOR,color);
			}
			style = cssPropertyFile.get(rule, ATTR_BORDER_RIGHT_STYLE) ;
			if(style != null){
				result.put(ATTR_BORDER_RIGHT_STYLE,style);
			}
			width = cssPropertyFile.get(rule, ATTR_BORDER_RIGHT_WIDTH) ;
			if(width != null){
				result.put(ATTR_BORDER_RIGHT_WIDTH,width);
			}
			borderValue = new BorderCSSValue((String)result.get(ATTR_BORDER_RIGHT_WIDTH),(String)result.get(ATTR_BORDER_RIGHT_COLOR),(String)result.get(ATTR_BORDER_RIGHT_STYLE)) ;
			if(!borderValue.toString().isEmpty()){
				result.put(ATTR_BORDER_RIGHT,borderValue) ;
			}

			color = cssPropertyFile.get(rule, ATTR_BORDER_TOP_COLOR) ;
			if(color != null){
				result.put(ATTR_BORDER_TOP_COLOR,color);
			}
			style = cssPropertyFile.get(rule, ATTR_BORDER_TOP_STYLE) ;
			if(style != null){
				result.put(ATTR_BORDER_TOP_STYLE,style);
			}
			width = cssPropertyFile.get(rule, ATTR_BORDER_TOP_WIDTH) ;
			if(width != null){
				result.put(ATTR_BORDER_TOP_WIDTH,width);
			}
			borderValue = new BorderCSSValue((String)result.get(ATTR_BORDER_TOP_WIDTH),(String)result.get(ATTR_BORDER_TOP_COLOR),(String)result.get(ATTR_BORDER_TOP_STYLE)) ;
			if(!borderValue.toString().isEmpty()){
				result.put(ATTR_BORDER_TOP,borderValue) ;
			}

			color = cssPropertyFile.get(rule, ATTR_BORDER_COLOR) ;
			if(color != null){
				result.put(ATTR_BORDER_COLOR,color);
			}
			style = cssPropertyFile.get(rule, ATTR_BORDER_STYLE) ;
			if(style != null){
				result.put(ATTR_BORDER_STYLE,style);
			}
			width = cssPropertyFile.get(rule, ATTR_BORDER_WIDTH) ;
			if(width != null){
				result.put(ATTR_BORDER_WIDTH,width);
			}
			borderValue = new BorderCSSValue((String)result.get(ATTR_BORDER_WIDTH),(String)result.get(ATTR_BORDER_COLOR),(String)result.get(ATTR_BORDER_STYLE)) ;
			if(!borderValue.toString().isEmpty()){
				result.put(ATTR_BORDER,borderValue) ;
			}


			FontCSSValue fontValue = null ; 
			raw = cssPropertyFile.get(rule, ATTR_FONT) ;

			if(raw != null){
				fontValue = new FontCSSValue(raw) ;
			}
			result.put(ATTR_FONT,fontValue);

			BackgroundCSSValue backgroundValue = null ; 
			raw = cssPropertyFile.get(rule, ATTR_BACKGROUND) ;

			if(raw != null){
				backgroundValue = new BackgroundCSSValue(raw) ;
			}
			result.put(ATTR_BACKGROUND,backgroundValue);

			result.put(ATTR_WIDTH,cssPropertyFile.get(rule, ATTR_WIDTH));
			result.put(ATTR_HEIGHT,cssPropertyFile.get(rule, ATTR_HEIGHT));
			result.put(ATTR_FLOAT,cssPropertyFile.get(rule, ATTR_FLOAT));
			result.put(ATTR_CLEAR,cssPropertyFile.get(rule, ATTR_CLEAR));

		}catch (Exception e) {
			BonitaStudioLog.error(e) ;
		}

		return result;
	}

	public static String[] getAvailableValuesFor(String attribute) {

		if(attribute.equals(ATTR_FONT_FAMILY)){
			return FontHelper.getFontNames(); 
		}else if(attribute.equals(ATTR_FONT_WEIGTH)){
			return FONT_WEIGHT ;
		}else if(attribute.equals(ATTR_FONT_STYLE)){
			return FONT_STYLES ;
		}else if(attribute.equals(ATTR_BACKGROUND_REPEAT)){
			return REPEAT_VALUES ;
		}else if(attribute.equals(ATTR_DISPLAY)){
			return DISPLAY_VALUES ;
		}else if(attribute.equals(ATTR_WHITE_SPACE)){
			return WHITESPACE_VALUES ;
		}else if(attribute.equals(ATTR_CLEAR)){
			return CLEAR_VALUES ;
		}else if(attribute.equals(ATTR_FLOAT)){
			return FLOAT_VALUES ;
		}else if(attribute.equals(ATTR_BORDER_STYLE)
				|| attribute.equals(ATTR_BORDER_TOP_STYLE)
				|| attribute.equals(ATTR_BORDER_LEFT_STYLE)
				|| attribute.equals(ATTR_BORDER_RIGHT_STYLE)
				|| attribute.equals(ATTR_BORDER_BOTTOM_STYLE)){
			return BORDER_STYLE ;
		}else if(attribute.equals(ATTR_COLOR)
				|| attribute.equals(ATTR_BORDER_TOP_COLOR)
				|| attribute.equals(ATTR_BORDER_LEFT_COLOR)
				|| attribute.equals(ATTR_BORDER_RIGHT_COLOR)
				|| attribute.equals(ATTR_BORDER_BOTTOM_COLOR)){
			return getCSSColors().keySet().toArray(new String[]{}) ;
		}else if(attribute.equals(ATTR_BORDER_WIDTH)
				|| attribute.equals(ATTR_BORDER_TOP_WIDTH)
				|| attribute.equals(ATTR_BORDER_LEFT_WIDTH)
				|| attribute.equals(ATTR_BORDER_RIGHT_WIDTH)
				|| attribute.equals(ATTR_BORDER_BOTTOM_WIDTH)){
			return BORDER_WIDTH_VALUES ;
		}else if(attribute.equals(ATTR_MARGIN)
				|| attribute.equals(ATTR_MARGIN_BOTTOM)
				|| attribute.equals(ATTR_MARGIN_LEFT)
				|| attribute.equals(ATTR_MARGIN_RIGHT)
				|| attribute.equals(ATTR_MARGIN_TOP)){
			return MARGIN_VALUES ;
		}else if(attribute.equals(ATTR_BACKGROUND_ATTACHEMNT)){
			return ATTACHMENT_VALUES ;
		}else if(attribute.equals(ATTR_BACKGROUND_POSITION)){
			return POSITION_VALUES ;
		}else if(attribute.equals(ATTR_FONT_SIZE)){
			return FONT_SIZE ;
		}

		return null;
	}

	public static Map<String, RGB> getCSSColors(){
		if(cssColorMap == null){
			cssColorMap = new TreeMap<String, RGB>() ;
			InputStream steam = CSSUtil.class.getResourceAsStream("csscolors.properties") ;
			Properties colors = new Properties() ;
			try {
				colors.load(steam) ;
			} catch (IOException e) {
				BonitaStudioLog.error(e) ;
			}
			Set<Entry<Object,Object>> entrySet = colors.entrySet();
			for (Entry<Object, Object> entry : entrySet) {
				cssColorMap.put(entry.getKey().toString().toLowerCase(), toRGBColor(entry.getValue().toString().trim())) ;
			}
		}
		return cssColorMap;
	}

	public static String toHexa(RGB color) {
		String red = Integer.toHexString(color.red) ;
		if(red.length() == 1) {
			red = "0"+red ;
		}

		String green = Integer.toHexString(color.green) ;
		if(green.length() == 1) {
			green = "0"+green ;
		}

		String blue = Integer.toHexString(color.blue) ;
		if(blue.length() == 1) {
			blue = "0"+blue ;
		}

		return  ("#"+red+green+blue).toUpperCase();
	}

	public static RGB toRGBColor(String text) {
		if(text.isEmpty()){
			return null ;
		}else{
			if(text.startsWith("#") &&  text.length() == 7){//Hexa
				text = text.substring(1) ;
				String red = text.substring(0, 2) ;
				String green = text.substring(2, 4) ;
				String blue = text.substring(4, 6) ;
				return new RGB(Integer.parseInt(red, 16),Integer.parseInt(green, 16),Integer.parseInt(blue, 16)) ;

			}else if(text.startsWith("rgb(") ){
				String rgbString = text.substring(4, text.lastIndexOf(')')) ;
				String[] rgb = rgbString.split(",") ;
				if(rgb.length == 3){
					String red = rgb[0];
					String green = rgb[1] ;
					String blue = rgb[2];
					return new RGB(Integer.parseInt(red),Integer.parseInt(green),Integer.parseInt(blue)) ;
				}else{
					return null ;
				}
			}else{
				if(getCSSColors().keySet().contains(text)){
					return getCSSColors().get(text) ;
				}

			}
		}
		return null;
	}

	public static Map<String, Object> formatPropertiesForOutput(Map<String, Object> cssProperties) {
		Map<String, Object> result = new HashMap<String, Object>() ;

		for (Entry<String, Object> entry : cssProperties.entrySet()) {
			result.put(entry.getKey(), entry.getValue()) ;
		}
		
		BorderCSSValue border = (BorderCSSValue) result.get(ATTR_BORDER) ;
		if(border != null){
			result.put(ATTR_BORDER_COLOR, border.getColor()) ;
			result.put(ATTR_BORDER_STYLE, border.getStyle()) ;
			result.put(ATTR_BORDER_WIDTH, border.getWidth()) ;
			result.put(ATTR_BORDER, null) ;
		}else{
			result.put(ATTR_BORDER_COLOR, null) ;
			result.put(ATTR_BORDER_STYLE, null) ;
			result.put(ATTR_BORDER_WIDTH, null) ;
			result.put(ATTR_BORDER, null) ;
		}
		
		border = (BorderCSSValue) result.get(ATTR_BORDER_TOP) ;
		if(border != null){
			result.put(ATTR_BORDER_TOP_COLOR, border.getColor()) ;
			result.put(ATTR_BORDER_TOP_STYLE, border.getStyle()) ;
			result.put(ATTR_BORDER_TOP_WIDTH, border.getWidth()) ;
			result.put(ATTR_BORDER_TOP, null) ;
		}else{
			result.put(ATTR_BORDER_TOP_COLOR, null) ;
			result.put(ATTR_BORDER_TOP_STYLE, null) ;
			result.put(ATTR_BORDER_TOP_WIDTH, null) ;
			result.put(ATTR_BORDER_TOP, null) ;
		}
		
		border = (BorderCSSValue) result.get(ATTR_BORDER_RIGHT) ;
		if(border != null){
			result.put(ATTR_BORDER_RIGHT_COLOR, border.getColor()) ;
			result.put(ATTR_BORDER_RIGHT_STYLE, border.getStyle()) ;
			result.put(ATTR_BORDER_RIGHT_WIDTH, border.getWidth()) ;
			result.put(ATTR_BORDER_RIGHT, null) ;
		}else{
			result.put(ATTR_BORDER_RIGHT_COLOR, null) ;
			result.put(ATTR_BORDER_RIGHT_STYLE, null) ;
			result.put(ATTR_BORDER_RIGHT_WIDTH, null) ;
			result.put(ATTR_BORDER_RIGHT, null) ;
		}
		
		border = (BorderCSSValue) result.get(ATTR_BORDER_BOTTOM) ;
		if(border != null){
			result.put(ATTR_BORDER_BOTTOM_COLOR, border.getColor()) ;
			result.put(ATTR_BORDER_BOTTOM_STYLE, border.getStyle()) ;
			result.put(ATTR_BORDER_BOTTOM_WIDTH, border.getWidth()) ;
			result.put(ATTR_BORDER_BOTTOM, null) ;
		}else{
			result.put(ATTR_BORDER_BOTTOM_COLOR, null) ;
			result.put(ATTR_BORDER_BOTTOM_STYLE, null) ;
			result.put(ATTR_BORDER_BOTTOM_WIDTH, null) ;
		}
		
		border = (BorderCSSValue) result.get(ATTR_BORDER_LEFT) ;
		if(border != null){
			result.put(ATTR_BORDER_LEFT_COLOR, border.getColor()) ;
			result.put(ATTR_BORDER_LEFT_STYLE, border.getStyle()) ;
			result.put(ATTR_BORDER_LEFT_WIDTH, border.getWidth()) ;
			result.put(ATTR_BORDER_LEFT, null) ;
		}else{
			result.put(ATTR_BORDER_LEFT_COLOR, null) ;
			result.put(ATTR_BORDER_LEFT_STYLE, null) ;
			result.put(ATTR_BORDER_LEFT_WIDTH, null) ;
		}
 		return result;
	}
	
	public static Map<String,Object> copyMap(Map<String,Object> cssProperties){
		Map<String, Object> result = new HashMap<String, Object>() ;
		for (Entry<String, Object> entry : cssProperties.entrySet()) {
			Object propertyValue = entry.getValue();
			String key = entry.getKey();
			if(propertyValue instanceof String){
				result.put(key,propertyValue) ;
			}else if(propertyValue instanceof BorderCSSValue){
				BorderCSSValue value = (BorderCSSValue)propertyValue ;
				result.put(key,new BorderCSSValue(value.toString())) ;
			}else if(propertyValue instanceof MarginCSSValue){
				MarginCSSValue value = (MarginCSSValue)propertyValue ;
				result.put(key,new MarginCSSValue(value.toString())) ;
			}else if(propertyValue instanceof PaddingCSSValue){
				PaddingCSSValue value = (PaddingCSSValue)propertyValue ;
				result.put(key,new PaddingCSSValue(value.toString())) ;
			}else if(propertyValue instanceof BackgroundCSSValue){
				BackgroundCSSValue value = (BackgroundCSSValue)propertyValue ;
				result.put(key,new BackgroundCSSValue(value.toString())) ;
			}else if(propertyValue instanceof FontCSSValue){
				FontCSSValue value = (FontCSSValue)propertyValue ;
				result.put(key,new FontCSSValue(value.toString())) ;
			}else{
				result.put(key,null) ;
			}
		}
		return result ;
	}
 

}
