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
package org.bonitasoft.studio.la.i18n;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    public static String url;
    public static String applicationStoreName;
    public static String create;
    public static String newApplicationDescriptorTitle;
    public static String newApplicationDescription;
    public static String applicationTokenMessage;
    public static String alphaNumericOnly;
    public static String deployingLivingApplication;
    public static String deletingApplication;
    public static String open;
    public static String openExistingApplication;
    public static String openExistingApplicationDescription;
    public static String deleteExistingApplication;
    public static String deleteExistingApplicationDescription;
    public static String deleteSingleDoneMessage;
    public static String delete;
    public static String export;
    public static String exportApplicationDescriptor;
    public static String exportDoneMessage;
    public static String importApplicationDescriptorLabel;
    public static String browse;
    public static String importApplicationDescriptor;
    public static String importConflictWarning;
    public static String importLabel;
    public static String notAnApplicationError;
    public static String importApplicationDescriptorDesc;
    public static String exportOperation;
    public static String exporting;
    public static String deleteDescriptor;
    public static String deleteDescriptorDone;
    public static String deleteContainer;
    public static String applicationInfo;
    public static String applicationDetails;
    public static String exportApplicationDescriptorTitle;
    public static String exportApplicationDescriptorMessage;
    public static String menuLevelWarning;
    public static String applicationDescriptorDeployed;
    public static String deployExistingApplication;
    public static String selectApplicationToDeploy;
    public static String deployDoneTitle;
    public static String deployDoneMessage;
    public static String deployFailedTitle;
    public static String deploy;
    public static String nothingToDeploy;
    public static String applicationWithoutHomepage;
    public static String applicationWithUnknownHomepage;

    static {
        NLS.initializeMessages("messages", Messages.class);
    }
}
