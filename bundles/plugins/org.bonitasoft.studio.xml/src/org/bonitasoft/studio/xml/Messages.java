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
package org.bonitasoft.studio.xml;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 *
 */
public class Messages extends NLS {

	static {
		initializeMessages("messages", Messages.class);
	}

	public static String AddXSD;
	public static String selectElementLabel;
	public static String wholeXML;
	public static String selectNamespaceAndElement;
	public static String namespace;
	public static String element;
	public static String selectXSDToImport;
	public static String appendChild;
	public static String xpathExpressionType;
	public static String name;
	public static String returnType;
	public static String xsdRepositoryName;
	public static String missingATargetNamespace;

}
