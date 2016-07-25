/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.common;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 *
 */
public class DataTypeLabels extends NLS {

    public static String booleanDataType;
    public static String dateDataType;
    public static String integerDataType;
    public static String floatDataType;
    public static String stringDataType;
    public static String attachedDocDataType;
    public static String javaDataType;
    public static String xmlDataType;
    public static String longDataType;
    public static String doubleDataType;
	public static String businessObjectType;

    static {
        initializeMessages("dataTypes", DataTypeLabels.class);
    }
}