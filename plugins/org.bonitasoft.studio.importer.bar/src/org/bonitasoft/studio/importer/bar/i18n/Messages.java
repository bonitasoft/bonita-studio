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
package org.bonitasoft.studio.importer.bar.i18n;

import org.eclipse.osgi.util.NLS;


/**
 * @author Romain Bioteau
 *
 */
public class Messages extends NLS {

    public static String migrationWarningTitle;
    public static String migrationWizardTitle;
    public static String migrationWizardDescription;
    public static String importWarningMessageContent;
	public static String callActivityTargetNameMigrationDescription;
	public static String callActivityTargetNameProperty;
	public static String callActivityTargetVersionMigrationDescription;
	public static String callActivityTargetVersionProperty;
	public static String attachmentDataRemovedDescription;
	public static String dataProperty;
	public static String document;
	public static String documentProperty;
	public static String documentCreationDescription;

    static {
        initializeMessages("messages", Messages.class); //$NON-NLS-1$
    }

}
