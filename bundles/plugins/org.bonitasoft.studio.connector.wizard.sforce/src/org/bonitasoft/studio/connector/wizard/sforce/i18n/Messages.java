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
package org.bonitasoft.studio.connector.wizard.sforce.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Maxence Raoux
 * 
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	public static String add;
	public static String connect;
	public static String connecting;
	public static String disconnect;
	public static String error;
	public static String errorExplanation;
	public static String errorConnectingMessage;
	public static String filterAutoCompletionTitle;
	public static String filterAutoCompletionExplanation;
	public static String helperLink;
	public static String login;
	public static String password;
	public static String queryObjectConditions;
	public static String queryObjectFields;
	public static String queryObjectType;
	public static String remove;
	public static String successConnectingMessage;
	public static String testConnectExplanation;
	public static String testConnectTitle;
	public static String token;
    public static String authEndpointURL;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
