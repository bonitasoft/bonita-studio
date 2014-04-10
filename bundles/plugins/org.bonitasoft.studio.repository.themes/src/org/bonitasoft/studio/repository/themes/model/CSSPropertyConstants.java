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
public interface CSSPropertyConstants {

	/**
	 * Defines constants used for external Css files.
	 */

	// font

	public static final String ATTR_FONT_FAMILY = "font-family"; //$NON-NLS-1$
	public static final String ATTR_FONT_SIZE = "font-size"; //$NON-NLS-1$
	public static final String ATTR_FONT_STYLE = "font-style"; //$NON-NLS-1$
	public static final String ATTR_FONT_VARIANT = "font-variant"; //$NON-NLS-1$
	public static final String ATTR_FONT_WEIGTH = "font-weight"; //$NON-NLS-1$

	// text

	public static final String ATTR_TEXT_ALIGN = "text-align"; //$NON-NLS-1$
	public static final String ATTR_TEXT_INDENT = "text-indent"; //$NON-NLS-1$
	public static final String ATTR_TEXT_DECORATION = "text-decoration"; //$NON-NLS-1$
	public static final String ATTR_LETTER_SPACING = "letter-spacing"; //$NON-NLS-1$
	public static final String ATTR_WORD_SPACING = "word-spacing"; //$NON-NLS-1$
	public static final String ATTR_TEXT_TRANSFORM = "text-transform"; //$NON-NLS-1$
	public static final String ATTR_WHITE_SPACE = "white-space"; //$NON-NLS-1$

	// margin

	public static final String ATTR_MARGIN_TOP = "margin-top"; //$NON-NLS-1$
	public static final String ATTR_MARGIN_RIGHT = "margin-right"; //$NON-NLS-1$
	public static final String ATTR_MARGIN_BOTTOM = "margin-bottom"; //$NON-NLS-1$
	public static final String ATTR_MARGIN_LEFT = "margin-left"; //$NON-NLS-1$

	// padding

	public static final String ATTR_PADDING_TOP = "padding-top"; //$NON-NLS-1$
	public static final String ATTR_PADDING_RIGHT = "padding-right"; //$NON-NLS-1$
	public static final String ATTR_PADDING_BOTTOM = "padding-bottom"; //$NON-NLS-1$
	public static final String ATTR_PADDING_LEFT = "padding-left"; //$NON-NLS-1$

	// background

	public static final String ATTR_COLOR = "color"; //$NON-NLS-1$    
	public static final String ATTR_BACKGROUND_COLOR = "background-color"; //$NON-NLS-1$
	public static final String ATTR_BACKGROUND_IMAGE = "background-image"; //$NON-NLS-1$
	public static final String ATTR_BACKGROUND_REPEAT = "background-repeat"; //$NON-NLS-1$
	public static final String ATTR_BACKGROUND_ATTACHEMNT = "background-attachment"; //$NON-NLS-1$
	public static final String ATTR_BACKGROUND_POSITION = "background-position"; //$NON-NLS-1$
	public static final String ATTR_BACKGROUND_SIZE = "background-size"; //$NON-NLS-1$

	// page media

	public static final String ATTR_ORPHANS = "orphans"; //$NON-NLS-1$
	public static final String ATTR_WIDOWS = "widows"; //$NON-NLS-1$
	public static final String ATTR_DISPLAY = "display"; //$NON-NLS-1$
	public static final String ATTR_PAGE_BREAK_BEFORE = "page-break-before"; //$NON-NLS-1$
	public static final String ATTR_PAGE_BREAK_AFTER = "page-break-after"; //$NON-NLS-1$
	public static final String ATTR_PAGE_BREAK_INSIDE = "page-break-inside"; //$NON-NLS-1$

	// visual

	public static final String ATTR_VERTICAL_ALIGN = "vertical-align"; //$NON-NLS-1$
	public static final String ATTR_LINE_HEIGHT = "line-height"; //$NON-NLS-1$

	// border

	public static final String ATTR_BORDER_BOTTOM_COLOR = "border-bottom-color"; //$NON-NLS-1$    
	public static final String ATTR_BORDER_BOTTOM_STYLE = "border-bottom-style"; //$NON-NLS-1$
	public static final String ATTR_BORDER_BOTTOM_WIDTH = "border-bottom-width"; //$NON-NLS-1$
	public static final String ATTR_BORDER_LEFT_COLOR = "border-left-color"; //$NON-NLS-1$
	public static final String ATTR_BORDER_LEFT_STYLE = "border-left-style"; //$NON-NLS-1$
	public static final String ATTR_BORDER_LEFT_WIDTH = "border-left-width"; //$NON-NLS-1$
	public static final String ATTR_BORDER_RIGHT_COLOR = "border-right-color"; //$NON-NLS-1$
	public static final String ATTR_BORDER_RIGHT_STYLE = "border-right-style"; //$NON-NLS-1$
	public static final String ATTR_BORDER_RIGHT_WIDTH = "border-right-width"; //$NON-NLS-1$
	public static final String ATTR_BORDER_TOP_COLOR = "border-top-color"; //$NON-NLS-1$
	public static final String ATTR_BORDER_TOP_STYLE = "border-top-style"; //$NON-NLS-1$
	public static final String ATTR_BORDER_TOP_WIDTH = "border-top-width"; //$NON-NLS-1$

	// border for short hand

	public static final String ATTR_BORDER_BOTTOM = "border-bottom"; //$NON-NLS-1$
	public static final String ATTR_BORDER_LEFT = "border-left"; //$NON-NLS-1$
	public static final String ATTR_BORDER_RIGHT = "border-right"; //$NON-NLS-1$
	public static final String ATTR_BORDER_TOP = "border-top"; //$NON-NLS-1$
	public static final String ATTR_BORDER = "border"; //$NON-NLS-1$
	public static final String ATTR_BORDER_WIDTH = "border-width"; //$NON-NLS-1$
	public static final String ATTR_BORDER_COLOR = "border-color"; //$NON-NLS-1$
	public static final String ATTR_BORDER_STYLE = "border-style"; //$NON-NLS-1$

	// other short hand

	public static final String ATTR_FONT = "font"; //$NON-NLS-1$
	public static final String ATTR_BACKGROUND = "background"; //$NON-NLS-1$
	public static final String ATTR_MARGIN = "margin"; //$NON-NLS-1$
	public static final String ATTR_PADDING = "padding"; //$NON-NLS-1$

	// text-decoration choice

	public static final String TEXT_DECORATION_UNDERLINE = "underline"; //$NON-NLS-1$
	public static final String TEXT_DECORATION_OVERLINE = "overline"; //$NON-NLS-1$
	public static final String TEXT_DECORATION_LINE_THROUGH = "line-through"; //$NON-NLS-1$

	// bidi_hcg: direction

	public static final String DIRECTION = "direction"; //$NON-NLS-1$
	
	public static final String ATTR_WIDTH = "width"; //$NON-NLS-1$
	public static final String ATTR_HEIGHT = "height"; //$NON-NLS-1$
	public static final String ATTR_FLOAT = "float"; //$NON-NLS-1$
	public static final String ATTR_CLEAR = "clear"; //$NON-NLS-1$
	
	public static final String EM_UNIT = "em"  ;
	public static final String EX_UNIT = "ex"  ;
	public static final String PX_UNIT = "px"  ;
	public static final String IN_UNIT = "in"  ;
	public static final String CM_UNIT = "cm"  ;
	public static final String MM_UNIT = "mm"  ;
	public static final String PC_UNIT = "pc"  ;
	public static final String PT_UNIT = "pt"  ;
	public static final String PERCENT_UNIT = "%"  ;
	

}
