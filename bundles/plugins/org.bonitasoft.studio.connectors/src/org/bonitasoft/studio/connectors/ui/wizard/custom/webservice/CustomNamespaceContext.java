/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.ui.wizard.custom.webservice;

import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

/**
 * Custom implementation of {@link NamespaceContext} to use
 * in the XPath evaluations.
 * 
 * Respond to namespace alias 'tns' with the value passed in constructor.
 * 
 * @author zubairov
 */
public class CustomNamespaceContext implements NamespaceContext {

	private Map<String, String> nsMap ;

	public CustomNamespaceContext(Map<String, String> nsMap) {
		this.nsMap = nsMap;
	}
	
	public String getNamespaceURI(String prefix) {
		if (nsMap.get(prefix) != null) {
			return nsMap.get(prefix) ;
		}
		throw new UnsupportedOperationException("Prefix " + prefix + " is not supported by this NamespaceContext");
	}

	public String getPrefix(String namespaceURI) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Iterator getPrefixes(String namespaceURI) {
		return null;
	}

}
