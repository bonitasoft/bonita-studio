/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.xml.api;

import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDElementDeclaration;
import org.w3c.dom.NodeList;

/**
 *@Author Aurélie Zara
 *
 *
 */
public class XPathReturnType {
	
	public static final String XPATH_STRING = String.class.getName();
	public static final String XPATH_BOOLEAN =Boolean.class.getName();
	public static final String XPATH_INTEGER = Integer.class.getName();
	public static final String XPATH_FLOAT = Float.class.getName();
	public static final String XPATH_LONG = Long.class.getName();
	public static final String XPATH_DOUBLE = Double.class.getName();
	public static final String XPATH_NODE = org.w3c.dom.Node.class.getName();
	public static final String XPATH_LIST_OF_NODES = NodeList.class.getName();
	
	public static String[] getAvailableReturnTypes(){
		String[] types = {XPATH_BOOLEAN,XPATH_INTEGER,XPATH_FLOAT,XPATH_LONG,XPATH_DOUBLE,XPATH_LIST_OF_NODES,XPATH_NODE,XPATH_STRING};
		return types;
	}
	
	public static String getType(Object element){
		if (element instanceof XSDElementDeclaration){
			return convertEcoreTypeToXPATHReturnType("", true);
		} else {
			if (element instanceof XSDAttributeDeclaration){
				return convertEcoreTypeToXPATHReturnType(((XSDAttributeDeclaration)element).getType().getName(), false);
			}
		}
		return XPATH_STRING;
	}
	
	public static String convertEcoreTypeToXPATHReturnType(String type,boolean isNode){
		if (type.equals("EDouble")){
			return XPATH_DOUBLE;
		}
		if (type.equals("EFloat")){
			return XPATH_FLOAT;
		}
		if (type.equals("ELONG")){
			return XPATH_LONG;
		}
		if (type.equals("EInt")){
			return XPATH_INTEGER;
		}
		if (type.equals("EBoolean")){
			return XPATH_BOOLEAN;
		}
		if (type.equals("EString") || type.equals("EDate")){
			return XPATH_STRING;
		}
		if (isNode){
			return XPATH_NODE;
		}
		return XPATH_STRING;
	}
}
