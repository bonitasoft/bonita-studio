/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.parameters.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 *
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	public static String name;
	public static String textType;
	public static String integerType;
	public static String booleanType;
	public static String doubleType;
	public static String value;
	public static String type;
	public static String add;
	public static String addData;
	public static String remove;
	public static String paramDefaultName;
	public static String parameters;
	public static String createOrRemoveParameterDetail;
	public static String parameterDescription;
	public static String invalidName;
	public static String returnType;
	public static String invalidInteger;
	public static String invalidDouble;
	public static String parameterWizardDesc;
	public static String newParameterWizardDescription;
	public static String exportParameterFile;
	public static String importParameterFile;
	public static String areYouSureMessage;
	public static String areYouSureTitle;
	public static String newParameter;
	public static String newParameterWizardTitle;
	public static String createAndNewButton;
	public static String updateParameter;
	public static String selectOnlyOneElementTitle;
	public static String selectOnlyOneElementMessage;
	public static String editParameterWizardTitle;
	public static String editParameterWizardDescription;
	public static String additionalInformationText;
	public static String removeParameters;
	public static String updatingParameterReferences;

}
