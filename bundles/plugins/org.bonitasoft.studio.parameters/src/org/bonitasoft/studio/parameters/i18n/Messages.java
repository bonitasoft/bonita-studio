/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
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
	public static String invalidIntegerForParameter;
	public static String invalidDoulbeForParameter;
	public static String missingParameterValue;
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
