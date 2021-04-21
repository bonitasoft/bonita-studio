/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 */
public class Messages extends NLS {

    static {
        NLS.initializeMessages("messages", Messages.class);
    }

    public static String browsePackages;

    public static String testConnector;
    public static String testConnectorTitle;
    public static String switchContainerConnectorTitle;
    public static String switchContainerConnectorMessage;
    public static String chooseConnectorToMove;
    public static String chooseTargetStepOrProcess;
    public static String warningLocalVariableinConnector;
    public static String removeData;
    public static String outputMapping;
    public static String selectActivityEvent;
    public static String connectorCrashLabel;
    public static String connectorFails_crash;
    public static String connectorFails_ignore;
    public static String connectorFails_namedError;
    public static String connectorFails_throwEvent;
    public static String connectorFails_throwException;
    public static String connectorImplRepositoryName;
    public static String connectorDefRepositoryName;
    public static String up;
    public static String down;
    public static String selectOnlyOneElementTitle;
    public static String selectOnlyOneElementMessage;
    public static String update;
    public static String copyMove;
    public static String move;
    public static String add;
    public static String deleteDialogConfirmMessage;
    public static String deleteDialogTitle;
    public static String connectorConfRepositoryName;
    public static String selectAConnectorDefWarning;
    public static String connectorOutpuTypeLabel;
    public static String name;
    public static String returnType;
    public static String remove;
    public static String selectConnectorDefinitionTitle;
    public static String selectConnectorDefinitionDesc;
    public static String search;
    public static String newConnectorDefinition;
    public static String editConnectorDefinition;
    public static String outputMappingDesc;
    public static String connectorDefinitionNotFound;
    public static String newConnectorImplementation;
    public static String editConnectorImplementation;
    public static String connectorImplementationTitle;
    public static String connectorImplementationDesc;
    public static String selectAConnectorImplWarning;
    public static String selectConnectorImplementationTitle;
    public static String selectConnectorImplementationDesc;
    public static String targetPathIsInvalid;
    public static String selectConnectorImplementationToExportTitle;
    public static String selectConnectorImplementationToExportDesc;
    public static String destinationLabel;
    public static String inculeSourcesLabel;
    public static String addDependencies;
    public static String connectorSourceRepositoryName;
    public static String addingDefinitionDependencies;
    public static String exporting;
    public static String importConnectorArchive;
    public static String importingConnectorArchive;
    public static String exportSuccessfulMsg;
    public static String connectors;
    public static String connectorsConfigurationDescription;
    public static String definition;
    public static String implementation;
    public static String selectImplementation;
    public static String invalidImplementationFor;
    public static String implementationNotFound;
    public static String clear;
    public static String addingImplementationDependencies;
    public static String implementationDepNotFound;
    public static String exportStatusTitle;
    public static String noImplementationFoundTitle;
    public static String noImplementationFoundMsg;
    public static String importSuccessfulTitle;
    public static String importSuccessfulMsg;
    public static String importFailedMsg;
    public static String importFailedTitle;
    public static String selectAValidConnectorImplWarning;
    public static String exportConnectorTitle;
    public static String connectorSectionDescription;
    public static String Edit;
    public static String activate;
    public static String automaticallyAddDriver;
    public static String BonitaPreferenceDialog_DBConnectors;
    public static String BonitaPreferencePage_DBConnectors_Description;
    public static String databaseConnectorsProperties;
    public static String databaseConnectorDriversWizardPageTitle;
    public static String databaseConnectorDriversWizardPageDescription;
    public static String selectConnectorConfigurationWizardPageTitle;
    public static String resultTitleLabel;
    public static String testConnectorPOJOWarning;
    public static String exceptionFound;
    public static String successMessage;
    public static String connectorExpressionViewerLabel;
    public static String connectorExpressionViewerMessage;
    public static String connectorTypeValidationMessage;
    public static String unserializableOutputWarning;
    public static String selectConnectorConfigurationWizardPageDescription;
    public static String connectorAdditionalDependencyTitle;
    public static String connectorAdditionalDependencyMessage;
    public static String selectConnectorDefinitionForImplDesc;
    public static String outputOperationsDefinitionTitle;
    public static String outputOperationsDefinitionDesc;
    public static String selectConnectorOutputMode;
    public static String graphicalMode;
    public static String graphicalModeDescription;
    public static String singleValue;
    public static String oneRowNCol;
    public static String nRowOneCol;
    public static String nRowsNcolumns;
    public static String oneRowHint;
    public static String oneColHint;
    public static String tableHint;
    public static String scriptMode;
    public static String scriptModeDescription;
    public static String alwaysUseScriptingMode;

    public static String singleDatabaseOutputDescription;
    public static String oneRowDatabaseOutputDescription;
    public static String oneColDatabaseOutputDescription;
    public static String nRowsNColsDatabaseOutputDescription;

    public static String takeValueOf;
    public static String nRowsOneColOutputHint;
    public static String nRowsNColsOutputHint;

    public static String alwaysUseScriptingModeOutputPref;
    public static String unsuportedExpressionTypeForTesting;

    public static String unresolvedPatternOrScriptExpression;
    public static String unresolvedExpression;
    public static String deleteConnectorDefinition;
    public static String destFileNameLabel;
    public static String notAZipFile;
    public static String connectorOutput;

    public static String configurationChangedTitle;
    public static String configurationChangedMsg;

    public static String noImplementationFoundErrorTitle;
    public static String noImplementationFoundErrorMessage;

    public static String moveFailed;
    public static String moveFailedMsg;

    public static String invalidTargetLocationMessages;

    public static String connectorInChoice;
    public static String connectorOutChoice;
    public static String connectorEventLabel;

    public static String selectMoveOrCopyAction;
    public static String copy;

    public static String providedDefinitionAlreadyExists;
    public static String selectConnectorTitle;
    public static String selectConnectorMessage;
    public static String selectAll;
    public static String unSelectAll;
    public static String DebugProcessButtonLabel;
    public static String debugProcessWizardtitle;

    public static String wsdlLocation;
    public static String readWSDL;
    public static String operation;
    public static String service;
    public static String port;
    public static String parameters;
    public static String parameter;
    public static String value;
    public static String configureXMLWebServiceDescription;
    public static String configureXMLWebServiceTitle;
    public static String errorIntrospectTitle;
    public static String errorIntrospectMessage;

    public static String charactersLeft;
    public static String searchUserButton;
    public static String searchUserEmptyField;
    public static String searchUserTitle;
    public static String searchUserLastTweetsTitle;
    public static String testValideInformationButtonLabel;
    public static String testValideInformationError;
    public static String testValideInformationSuccess;
    public static String testValideInformationExplanation;
    public static String testValideInformationTitle;

    public static String configureDatabaseDriverTitle;
    public static String configureDatabaseDriverDesc;
    public static String configureDatabaseDrivers;

    public static String openMarketplace;
    public static String openMarketplaceTooltip;
    public static String connectorType;

    public static String connectorDefinitionUpdateRequired;
    public static String selectTargetDefinitionVersionTitle;
    public static String selectTargetDefinitionVersionMessage;
    public static String version;

    public static String getValue(final String event) {
        try {
            return (String) Messages.class.getField(event).get(null);
        } catch (final Exception ex) {
            return "";
        }
    }

}
