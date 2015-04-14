/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.pagedesigner.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 */
public class Messages extends NLS {

    public static String invalidURLTitle;
    public static String invalidURLMsg;
    public static String formRepository;
    public static String fragmentRepository;
    public static String widgetRepository;
    public static String pageDesigner;
    public static String externalURL;
    public static String targetForm;
    public static String url;
    public static String pageDoesntExists;
    public static String caseStartFormMappingDescription;
    public static String entryFormMappingDescription;
    public static String caseOverviewFormMappingDescription;
    public static String newFormTooltip;
    public static String pool;
    public static String task;
    public static String legacyForm;
    public static String openUIDesigner;
    public static String uiDesignerLabel;
    public static String creatingNewForm;

    static {
        NLS.initializeMessages("messages", Messages.class);
    }

}
