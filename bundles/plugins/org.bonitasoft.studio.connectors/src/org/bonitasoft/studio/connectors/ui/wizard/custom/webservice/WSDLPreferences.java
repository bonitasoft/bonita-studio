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

import java.net.URL;

import org.bonitasoft.studio.connectors.ConnectorPlugin;

/**
 * @author Mickael Istria
 *
 */
public class WSDLPreferences {

	private static final String KNOWN_WSDL_PREF = "knowsWsdl";
	private static String SEPARATOR = ",##,";
	
	public static String[] getKnownsWsdls() {
		String res = ConnectorPlugin.getDefault().getPreferenceStore().getString(KNOWN_WSDL_PREF);
		if (res == null) {
			return new String[0];
		} else {
			return res.split(SEPARATOR);
		}
	}

	/**
	 * @param url
	 */
	public static void addKnownWSDL(URL url) {
		String res = ConnectorPlugin.getDefault().getPreferenceStore().getString(KNOWN_WSDL_PREF);
		if (res == null) {
			res = url.toString();
		} else if (!res.contains(url.toString())) {
			res += (SEPARATOR + url.toString());
		}
		ConnectorPlugin.getDefault().getPreferenceStore().setValue(KNOWN_WSDL_PREF, res);
	}

}
