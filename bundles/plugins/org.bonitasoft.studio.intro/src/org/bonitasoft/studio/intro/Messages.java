/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.intro;

import java.lang.reflect.Field;

import org.eclipse.osgi.util.NLS;


/**
 * @author Mickael Istria
 *
 */
public class Messages extends NLS {

	static {
		initializeMessages("messages", Messages.class);
	}
	
	public static String openExampleTitle;
	public static String openExampleMessage;
	public static String openProcessTitle;
	public static String openProcessMessage;
	public static String importProcessTitle;
	public static String importProcessMessage;
	public static String createProcessMessage;
	public static String createProcessTitle;
	public static String welcome;
	
	public static String newProcess;
	public static String openAProcess;
	public static String importAProcess;
	public static String recentlyModified;
	public static String run2;
	public static String examples;
	public static String resources2;
	public static String migrationOngoing;
	
	/**
	 * @param id
	 * @return
	 */
	public static String getMessage(final String id) {
		try {
			final Field field = Messages.class.getField(id);
			return (String)field.get(null);
		} catch (final NoSuchFieldException ex) {
			return "Field [" + id + "] does not exist";
		} catch (final IllegalAccessException ex1) {
			return "Could not easily access to field [" + ex1 + "]\n" + ex1.getMessage();
		}
	}
}
