/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.profiles.i18n;


import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 *
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
	public static String ProfilePreferencePage_descriptionMessage;
	public static String activateTooltip;
	public static String addTooltip;
	public static String editTooltip;
	public static String deleteTooltip;
	public static String actors_management_label;
	public static String appearance_label;
	public static String bam_label;
	public static String connectors_label;
	public static String contexts_label;
	public static String data_management_label;
	public static String dependencies_management_label;
	public static String execution_label;
	public static String forms_modeling_label;
	public static String forms_templates_label;
	public static String language_support_label;
	public static String look_n_feel_label;
	public static String process_modeling_label;
	public static String simulation_label;
	public static String validation_label;
	public static String validators_label;
	public static String initializingProfileRepository;
	public static String profileRepository;
	public static String modeling;
	public static String extension;
	public static String application;
	public static String createProfileNameWizardTitle;
	public static String createProfileNameWizardDesc;
	public static String name;
	public static String duplicateFrom;
	public static String editProfileNameWizardTitle;
	public static String editProfileNameWizardDesc;
	public static String deleteProfileTitle;
	public static String deleteProfileMsg;
	public static String selectProfileWizardTitle;
	public static String selectProfileWizardDesc;
	public static String changeProfileHint;
	public static String dontDisplayNexStartup ;
	public static String defaultProfileDesc;
	public static String extensionProfileDesc;
	public static String applicationProfileDesc;
	public static String bpaProfileDesc;
	public static String description;
	public static String userProfile;
	public static String processDesignerDesc;
	public static String creationError;
	public static String creationErrorMsg;
	public static String askForProfileOnStartup;
	public static String applyingProfiles;
	public static String BusinessAnalyst_Label;
	public static String ProcessEngineer_Label;
	public static String ApplicationDeveloper_Label;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
