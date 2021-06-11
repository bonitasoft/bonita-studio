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
package org.bonitasoft.studio.exporter;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 *
 */
public class Messages extends NLS {

    static {
        NLS.initializeMessages("messages", Messages.class);
    }

    public static String overwriteBPMNFile_title;
    public static String overwriteBPMNFile_message;
    public static String exportingTo;
    public static String exportSuccessfulTitle;
    public static String exportSuccessfulMessage;
    public static String exportFailedTitle;
    public static String exportFailedMessage;
    public static String ExportButtonLabel;
	public static String autoLoginMessageMigration;
	public static String autoLoginNameMigration;
    public static String resolvingDiagramDependencies;

}
