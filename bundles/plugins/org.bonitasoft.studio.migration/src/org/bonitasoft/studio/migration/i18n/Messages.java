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
package org.bonitasoft.studio.migration.i18n;

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
	    public static String importWarningMessageContentAfterPart;
	
    public static String find;
	public static String exportAsPdf;
	public static String completeImport;
	public static String unknown;
	public static String name;
	public static String property;
	public static String status;
	public static String reviewed;
	public static String information;
	public static String currentVersion;
	public static String migrationReportOf;
	public static String hideValidStatusAction;
	public static String hideReviewedAction;
	public static String description;
	public static String elementType;
	public static String okStatusTooltip;
	public static String warningStatusTooltip;
	public static String errorStatusTooltip;
	public static String no;
	public static String yes;
	public static String noActionRequired;
	public static String reviewRequired;
	public static String actionRequired;
	public static String completeImportMessage;
	public static String completeImportToggleMessage;
	public static String noActionRequiredHelp;
	public static String reviewRequiredHelp;
	public static String actionRequiredHelp;
	public static String doNotDisplayAtNextImport;
	public static String migrating;

    static {
        initializeMessages("messages", Messages.class); //$NON-NLS-1$
    }

}
