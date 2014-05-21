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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class FontHelper {

private static String[] FONT_NAMES;
	
	/**
	 * array of font sizes
	 */
	protected static final String[] FONT_SIZES = { "8", //$NON-NLS-1$
		"9", //$NON-NLS-1$
		"10", //$NON-NLS-1$
		"11", //$NON-NLS-1$
		"12", //$NON-NLS-1$
		"14", //$NON-NLS-1$
		"16", //$NON-NLS-1$
		"18", //$NON-NLS-1$
		"20", //$NON-NLS-1$
		"22", //$NON-NLS-1$
		"24", //$NON-NLS-1$
		"26", //$NON-NLS-1$
		"28", //$NON-NLS-1$
		"36", //$NON-NLS-1$
		"48", //$NON-NLS-1$
		"72" }; //$NON-NLS-1$
	
	/**
	 * Return the font names for the default display.
	 *  
	 * @return String array of font names as String objects for the default
	 * display. 
	 */
	static public String[] getFontNames() {
		if (FONT_NAMES != null)
			return FONT_NAMES;
		
		//add the names into a set to get a set of unique names
		Set<String> stringItems = new HashSet<String>();
		stringItems.add("serif") ;
		stringItems.add("sans-serif") ;
		stringItems.add("cursive") ;
		stringItems.add("fantasy") ;
		stringItems.add("monospace") ;
		FontData[] fontDatas = Display.getDefault().getFontList(null, true);
		for (int i = 0; i < fontDatas.length; i++) {
			if (fontDatas[i].getName() != null) {
				stringItems.add(fontDatas[i].getName());
			}
		}

		//add strings into the array
		String strings[] = new String[stringItems.size()];
		int i = 0;
		for (String item : stringItems) {
			strings[i++] = item;
		}
		
		//sort the array
		Arrays.sort(strings);

		return FONT_NAMES = strings;
	}
	
	/**
	 * @return - array of fomt sizes
	 */
	public static final String[] getFontSizes(){
		return FONT_SIZES;
	}
	
}
