/**
 * Copyright (C) 2016 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.ui.i18n;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    static {
        NLS.initializeMessages("messages", Messages.class);
    }

    public static String errorOccuredDuringFinish;
    public static String errorTitle;
    public static String revertEdit;
    public static String applyEdit;
    public static String edit;
    public static String editor;
    public static String source;
    public static String parseError;
    public static String rename;
    public static String renameFile;
    public static String required;
    public static String invalidFileName;
    public static String invalidCharFileName;
    public static String export;
    public static String exportTooltips;
    public static String saveBeforeTitle;
    public static String saveBeforeMessage;
    public static String exportFailedTitle;
    public static String exportDoneTitle;
    public static String doNotShowMeAgain;
    public static String importLabel;
    public static String browse;
    public static String filePathNotEmpty;
    public static String fileDoesntExist;
    public static String unparsableFile;
    public static String delete;
    public static String deleteDoneTitle;
    public static String deleteDoneMessage;
    public static String deleteConfirmation;
    public static String deleteConfirmationMessage;
    public static String saveAndDeploy;
    public static String deployCurrent;
    public static String seeDetails;
    public static String skipValidation;
    public static String exportCancel;
    public static String exportCancelTitle;
    public static String closeNotificationJobTitle;

}
