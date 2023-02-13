/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.sqlbuilder.ex;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "messages";//$NON-NLS-1$

	private Messages() {

		// Do not instantiate
	}

	public static String addTableLabel;
	public static String designerTabLabel;
	public static String queryTabLabel;
	public static String useGraphicalQueryBuilder;
	public static String useSimpleQuery;
	public static String promptHelp;
	public static String connectionSuccessMsg;
	public static String connectionFailedMsg;
	public static String databaseConnection;
	public static String connectionExplanation;
	public static String connect;
	public static String disconnect;
	public static String connecting;
	public static String connectDBWizardWizardPageTitle;
	public static String connectDBWizardWizardPageDescription;
	public static String sqlBuilderExtendedWizardPageTitle;
	public static String sqlBuilderExtendedWizardPageDescription;
	public static String className;
	public static String JDBCUrl;
	public static String username;
	public static String password;
	public static String noActiveDriver;
	public static String unparsableQueryTitle;
	public static String unparsableQueryMsg;
	public static String updateConfigurationTitle;
	public static String updateConfigurationMsg;
    public static String connectionInfoDialogTitle;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}