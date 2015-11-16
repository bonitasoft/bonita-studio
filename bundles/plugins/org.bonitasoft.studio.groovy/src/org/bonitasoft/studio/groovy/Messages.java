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
package org.bonitasoft.studio.groovy;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "messages";//$NON-NLS-1$

	public static String userDefinedCatLabel;
	public static String stringCatLabel;
	public static String numberCatLabel;
	public static String collectionCatLabel;
	public static String otherCatLabel;
	public static String groovyScriptRepository;
	public static String groovyProvidedScriptRepository;
	public static String noDocumentationAvailable;
    public static String errorBuildingJarForGroovyScriptsForProcess;
    public static String errorBuildingJarForProvidedGroovyScriptsForProcess;
    public static String errorBuildingJarForGroovyScriptsFor6xApplication;

	private Messages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}