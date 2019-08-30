/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 */
public class Messages extends NLS {

    static {
        initializeMessages("messages", Messages.class); //$NON-NLS-1$
    }

    public static String importFromXPDL;
    public static String importFromBPMN;
    public static String importTitle;
    public static String importFileTitle;
    public static String importFileDescription;
    public static String fileFormat;
    public static String importDescriptionLabel;
    public static String selectFileToImport;
    public static String browseButton_label;
    public static String importProcessTitle;
    public static String importProcessProgressDialog;
    public static String errorWhileImporting_message;
    public static String errorWhileImporting_title;
    public static String ImportProcessButtonLabel;
    public static String importButtonLabel;
    public static String importSucessfulMessage;
    public static String importResultTitle;
    public static String seeDetails;
    public static String skipValidation;
    public static String importStatusMsg;
    public static String copyToClipboard;
    public static String invalidFilePath;
    public static String deploy;

}
