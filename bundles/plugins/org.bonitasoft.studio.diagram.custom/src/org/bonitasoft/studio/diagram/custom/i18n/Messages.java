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
package org.bonitasoft.studio.diagram.custom.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Mickael Istria
 */
public class Messages extends NLS {

    static {
        NLS.initializeMessages("messages", Messages.class);
    }

    public static String deleteDialogTitle;
    public static String deleteDialogMessage;
    public static String more;
    public static String extractSubprocessError;
    public static String noNodeInSelectionForExtract;
    public static String cannotCopyElementBecauseOfType;
    public static String selectedItemsNotInSameProcess;
    public static String severalSubprocessEntryPoints;
    public static String cannotExtractSubprocessMessage;
    public static String boundaryTool;
    public static String switchTool;
    public static String copyOfLabel;
    public static String diagrams;
    public static String newFilePrefix;
    public static String newProcessPrefix;
    public static String newDiagram;
    public static String OpenProcessButtonLabel;
    public static String openProcessWizardPage_title;
    public static String openProcessWizardPage_desc;
    public static String removeProcessLabel;
    public static String confirmProcessOverrideMessage;
    public static String confirmProcessOverrideTitle;
    public static String openingDiagramProgressText;
    public static String confirmProcessDeleteMessage;
    public static String confirmProcessDeleteTitle;
    public static String configurations;
    public static String applicationResources;
    public static String invalidResourceFileName_message;
    public static String defaultLaneName;
    public static String initiatorDescription;
    public static String configuration;
    public static String Application_Resources;
    public static String localConfigurationFor;
    public static String applicationResourcesFor;
    public static String migrationOngoing;
    public static String deleteMessageFlow;
    public static String readOnlyFileTitle;
    public static String readOnlyFileWarning;
    public static String invalidResourceFileName_title;
    public static String incompatibleVersionTitle;
    public static String incompatibleVersionMsg;
    public static String errorSubprocessDoesNotExist;
    public static String errorSubprocessNotFound;
    public static String updatingReferences;
    public static String updateReferencesTitle;
    public static String updateReferencesMsg;
    public static String GridRulerPreferencePage_showGrid_label;
    public static String GridRulerPreferencePage_gridGroup_label;
    public static String GridRulerPreferencePage_snapToGrid_label;
    public static String GridRulerPreferencePage_snapToGeometry_label;
    public static String GridRulerPreferencePage_gridSpacing_label_cm;
    public static String extractAsSubprocessNewPoolName;
    public static String warningDuplicateDialogTitle;
    public static String poolAlreadyExistWarningMessage;
    public static String duplicatingDiagram;
    public static String DeleteDiagramWizardPage_title;
    public static String DeleteDiagramWizardPage_desc;
    public static String noDiagramSelected;
    public static String buildingDiagram;
    public static String validatingDiagram;
    public static String buildingProcess;
    public static String containsLegacyFormsWarning;
    public static String loadingAllProcesses;
    public static String dashboardDiagramDescription;
    public static String dashboardDiagramName;
}
