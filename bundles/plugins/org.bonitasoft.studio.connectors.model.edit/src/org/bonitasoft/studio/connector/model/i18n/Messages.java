/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 *
 */
public class Messages extends NLS {

	static {
        NLS.initializeMessages("messages", Messages.class);
    }
    public static String definitionIdLabel;
    public static String classNameLabel;
    public static String categoryLabel;
    public static String browsePackages;
    public static String type;
    public static String widget;
    public static String mandatory;
    public static String optional;
    public static String pageDescLabel;
    public static String iconLabel;
    public static String saveConfigurationWizardPageTitle;
    public static String saveConfigurationWizardPageDesc;
    public static String selectConfigurationPageName;
    public static String selectConfigurationPageDesc;
    public static String removeData;
    public static String connectorOutputTitle;
    public static String Add;
    public static String dataNameLabel;
    public static String dataDescriptionLabel;
    public static String specifyName_wizardTitle;
    public static String nameAlreadyExists;
    public static String up;
    public static String down;
    public static String update;
    public static String add;
    public static String nameIsEmpty;
    public static String selectAConnectorConfDefWarning;
    public static String loadConfiguration;
    public static String saveConfiguration;
    public static String testConfiguration;
    public static String name;
    public static String categoriesLabel;
    public static String newCategory;
    public static String remove;
    public static String categoryId;
    public static String idIsEmpty;
    public static String idAlreadyExists;
    public static String selectConnectorDefinitionTitle;
    public static String connectorPageDefinitionTitle;
    public static String connectorPageDefinitionDesc;
    public static String pageId;
    public static String connectorInputTitle;
    public static String connectorInputDesc;
    public static String typeIsEmpty;
    public static String cantSetDefaultValueForType;
    public static String defaultValue;
    public static String defaultInputName;
    public static String connectorOutputDesc;
    public static String defaultOutputName;
    public static String widgets;
    public static String inputIsEmpty;
    public static String displayName;
    public static String definitionAlreadyExists;
    public static String whitespaceInDefinitionIDNotAllowed;
    public static String connectorI18NTitle;
    public static String connectorI18NDesc;
    public static String countryName;
    public static String locale;
    public static String search;
    public static String input;
    public static String widgetType;
    public static String widgetId;
    public static String textWidgetLabel;
    public static String groupWidgetLabel;
    public static String radioGroupWidgetLabel;
    public static String arrayWidgetLabel;
    public static String listWidgetLabel;
    public static String selectWidgetLabel;
    public static String checkboxWidgetLabel;
    public static String passwordWidgetLabel;
    public static String items;
    public static String item;
    public static String widgetPageTitle;
    public static String widgetPageDesc;
    public static String orientation;
    public static String newConnectorDefinition;
    public static String isCollapsed;
    public static String nbColumn;
    public static String fixedColumn;
    public static String columnHeaders;
    public static String header;
    public static String apply;
    public static String implementationAlreadyExists;
    public static String connectorDefinition;
    public static String dependenciesLabel;
    public static String missingImplemenationClass;
    public static String implementationId;
    public static String versionLabel;
    public static String packageName;
    public static String definitionTitle;
    public static String definitionDescription;
    public static String specifyName_wizardDesc;
    public static String invalidFileName;
    public static String browse;
    public static String selectAImplWarning;
    public static String description;
    public static String alreadyExistsForAnotherImplementation;
    public static String scriptEditorWidgetLabel;
    public static String interpreter;
    public static String textAreaEditor;
    public static String addWidget;
    public static String Edit;
	public static String dependenciesInfo;
	public static String showDocuments;
	public static String idIsInvalid;
	public static String noWhiteSpaceInPageID;
	public static String parentCategoryLabel;
	public static String definitionVersion;
	public static String onlyCustomConnector;
	public static String all;
	public static String inputMandatoryError;
	public static String inputMandatoryErrorTitle;
	public static String inputAlreadyUseInAnotherWidget;
	public static String confirmConnectorDefEditionTitle;
	public static String confirmConnectorDefEditionMsg;
	public static String doNotDisplayAgain;
    public static String loadConfigurationWarningMsg;
    public static String loadConfigurationWarningTitle;
    public static String loadConfigurationSuccessMsg;
    public static String missingDependency;

    /**
     * @param event
     * @return
     */
    public static String getValue(final String event) {
        try {
            return (String) Messages.class.getField(event).get(null);
        } catch (final Exception ex) {
            return "";
        }
    }

}
