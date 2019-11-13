/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.designer.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 */
public class Messages extends NLS {

    public static String invalidURLTitle;
    public static String invalidURLMsg;
    public static String formRepository;
    public static String fragmentRepository;
    public static String widgetRepository;
    public static String externalURL;
    public static String targetForm;
    public static String url;
    public static String pageDoesntExist;
    public static String caseStartFormMappingDescription;
    public static String entryFormMappingDescription;
    public static String caseOverviewFormMappingDescription;
    public static String newFormTooltipForPool;
    public static String legacyForm;
    public static String openUIDesigner;
    public static String uiDesignerLabel;
    public static String creatingNewForm;
    public static String switchTypeOfFormQuestionTitle;
    public static String switchTypeOfFormQuestion;
    public static String internalFormMappingUndefined;
    public static String formDoesntExistAnymoreMessage;
    public static String formDoesntExistAnymoreTitle;
    public static String stepUIDesignerInfo;
    public static String processUIDesignerInfo;
    public static String overviewUIDesignerInfo;
    public static String overviewURLInfo;
    public static String stepURLInfo;
    public static String processURLInfo;
    public static String overviewLegacyInfo;
    public static String stepLegacyInfo;
    public static String processLegacyInfo;
    public static String openUiDesignerInformationWindowTitle;
    public static String openUiDesignerInformationMessage;
    public static String openUiDesignerInformationToggleMessage;
    public static String hideEmptyContractDialogTitle;
    public static String hideEmptyContractDialogMessage;
    public static String hideEmptyContractDialogToggleMessage;

    public static String newFormTooltipForTask;
    public static String noForm;
    public static String noFormMessageOnTask;
    public static String noFormMessageOnProcess;
    public static String urlNotDefined;
    public static String formDoesntExist;
    public static String overviewnoFormMessage;
    public static String waitingForUIDesigner;
    public static String startingUIDesigner;
    public static String migratingUID;
    public static String creatingNewPage;
    public static String createPageFailed;
    public static String creatingNewWidget;
    public static String createWidgetFailed;
    public static String creatingNewLayout;
    public static String createLayoutFailed;
    public static String buildingWebPage;
    public static String startingDataRepositoryService;

    static {
        NLS.initializeMessages("messages", Messages.class);
    }

}
