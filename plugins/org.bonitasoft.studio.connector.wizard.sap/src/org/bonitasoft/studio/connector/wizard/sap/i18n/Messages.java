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
 
package org.bonitasoft.studio.connector.wizard.sap.i18n;


import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 *
 */
public class Messages extends NLS {

	public static String columnNameLabel;
	public static String addRow;
	public static String removeRow;
	public static String columnTypeLabel;
	public static String columnTableLabel;
	public static String columnParamNameLabel;
	public static String columnParamValueLabel;
	public static String columnXPathLabel;
	public static String columnVariableName;
	public static String existingConfigurationLabel;
	public static String newConfigurationLabel;
	public static String useExistingConfigurationLabel;
	public static String groupNameLabel;
	public static String sysIdLabel;
	public static String sysNumberLabel;
	public static String hostNameLabel;
	public static String languageLabel;
	public static String userIdLabel;
	public static String passwordLabel;
	public static String clientLabel;
	public static String connectionPageTitle;
	public static String connectionPageDescription;
	public static String functionDefinitionPageTitle;
	public static String functionDefinitionPageDescription;
	public static String rollbackOnFailureLabel;
	public static String commitOnSuccessLabel;
	public static String functionNameLabel;
	public static String repositoryLabel;
	public static String releaseClientLabel;
	public static String inputParametersPageTitle;
	public static String inputParametersPageDescription;
	public static String outputParametersPageTitle;
	public static String outputParametersPageDescription;
	public static String htmlFileLabel;
	public static String writeToHtmlFileLabel;
	public static String down;
	public static String up;
	public static String serverTypeLabel;
	public static String rawOutputLabel;

	static {
		NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
	}
	
}
