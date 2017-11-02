/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.decision.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 */
public class Messages extends NLS {

    public static String errorMessageNoConditionForLineInDecisionTable;
    public static String wizardPageTitle;
    public static String wizardPageDesc;
    public static String addCondition;
    public static String addRow;
    public static String defaultLine;
    public static String table;
    public static String deleteLineTitle;
    public static String deleteLineMessage;
    public static String editRow;
    public static String and;
    public static String takeTransition;
    public static String updateLine;
    public static String dontTakeTransition;
    public static String tableDescriptionLabel;
    public static String selectALine;
    public static String conditions;
    public static String decision;
    public static String noConditionDefined;
    public static String editDecisionTable;

    static {
        NLS.initializeMessages("messages", Messages.class);
    }
}
