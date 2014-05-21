/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validators.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 *
 */
public class Messages extends NLS {

    static{
        NLS.initializeMessages("messages", Messages.class) ;
    }

    public static String validators;
    public static String createValidatorWizardPage_displayNameLabel;
    public static String createValidatorWizardPage_packageLabel;
    public static String createValidatorWizardPage_browsePackages;
    public static String createValidatorWizardPage_classNameLabel;
    public static String createValidator;
    public static String createValidatorWizardPage_generateValidator;

    public static String ValidatorDefaultName;
    public static String Validator_ValidatorClass;
    public static String Validator_HtmlClass;
    public static String Validator_ErrorMessage;
    public static String Validator_Parameter;
    public static String Validator_defaultValidator;

    public static String Validator_isBelow;
    public static String Validator_Above;
    public static String Validator_Below;
    public static String GeneralSection_Name;
    public static String Remove;
    public static String Add;
    public static String createButton;
    public static String editValidator;
    public static String dependencies;
    public static String missingClassname;
    public static String nameIsEmpty;
    public static String validatorType;
    public static String fieldValidator;
    public static String pageValidator;
    public static String ValidatorWizardPage_title;
    public static String ValidatorWizardPage_description;
    public static String selectAValidatorWarning;
    public static String selectValidatorTitle;
    public static String selectValidatorDesc;
    public static String validatorSource;
	public static String missingPackageName;
	public static String exportFailedTitle;
}
