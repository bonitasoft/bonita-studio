/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.application.editor;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withId;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withStyle;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.swtbot.framework.ConditionBuilder;
import org.bonitasoft.studio.swtbot.framework.application.editor.project.BotEditProjectMetadataWizard;
import org.bonitasoft.studio.swtbot.framework.application.editor.project.BotExtensionCard;
import org.bonitasoft.studio.swtbot.framework.application.editor.project.BotExtensionWizard;
import org.bonitasoft.studio.swtbot.framework.application.editor.project.BotMarketplaceWizard;
import org.bonitasoft.studio.swtbot.framework.bdm.BotBdmEditor;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.la.BotApplicationEditor;
import org.bonitasoft.studio.swtbot.framework.organization.BotOrganizationEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

public class BotProjectDetailsEditor {

    protected SWTGefBot bot;

    public BotProjectDetailsEditor(SWTGefBot bot) {
        this.bot = bot;
    }

    public BotEditProjectMetadataWizard editProjectMetadata() {
        bot.labelWithId(SWTBotConstants.SWTBOT_ID_EDIT_PROJECT_METADATA).click();
        return new BotEditProjectMetadataWizard(bot);
    }

    public BotProjectDetailsEditor toExtensionView() {
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_OPEN_EXTENSIONS_VIEW).click();
        return this;
    }

    public BotProjectDetailsEditor toDashboardView() {
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_OPEN_ELEMENT_VIEW).click();
        return this;
    }

    public BotMarketplaceWizard openMarketplace() {
        var smallButton = allOf(widgetOfType(ToolItem.class),
                withId(SWTBotConstants.SWTBOT_ID_OPEN_MARKETPLACE_BIG_TOOLITEM), withStyle(SWT.PUSH, "SWT.PUSH"));
        if (!bot.getFinder().findControls(smallButton).isEmpty()) {
            bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_OPEN_MARKETPLACE_BIG_TOOLITEM).click();
        } else {
            bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_OPEN_MARKETPLACE_TOOLITEM).click();
        }
        return new BotMarketplaceWizard(bot);
    }

    public BotExtensionWizard addConnectorExtension() {
        bot.toolbarDropDownButtonWithId(SWTBotConstants.SWTBOT_ID_ADD_EXTENSION_DROPDOWN)
                .menuItem(Messages.addConnector)
                .click();
        return new BotExtensionWizard(bot, String.format(Messages.importExtensionTitle,
                org.bonitasoft.studio.common.repository.Messages.connector));
    }

    public BotExtensionCard findExtensionCardByArtifactId(String artifactId) {
        bot.clabelWithId(SWTBotConstants.extensionCardId(artifactId));
        return new BotExtensionCard(bot, artifactId);
    }

    public BotProcessDiagramPerspective createDiagram() {
        int nbEditorBefore = bot.editors().size();
        bot.toolbarButtonWithId(SWTBotConstants
                .createArtifactButtonId(org.bonitasoft.studio.diagram.custom.i18n.Messages.dashboardDiagramName)).click();
        waitForEditor(nbEditorBefore, 30000, 100);
        return new BotProcessDiagramPerspective(bot);
    }

    public BotProcessDiagramPerspective openDiagram(String diagramFileName) {
        bot.toolbarButtonWithId(
                SWTBotConstants.extensionCardId(org.bonitasoft.studio.diagram.custom.i18n.Messages.dashboardDiagramName))
                .click();
        waitForToolbarButton(SWTBotConstants.openArtifactButtonId(diagramFileName));
        int nbEditorBefore = bot.editors().size();
        bot.toolbarButtonWithId(SWTBotConstants.openArtifactButtonId(diagramFileName)).click();
        waitForEditor(nbEditorBefore, 30000, 100);
        return new BotProcessDiagramPerspective(bot);
    }

    public BotBdmEditor createBdm() {
        int nbEditorBefore = bot.editors().size();
        bot.toolbarButtonWithId(SWTBotConstants
                .createArtifactButtonId(org.bonitasoft.studio.businessobject.i18n.Messages.businessDataModel)).click();
        waitForEditor(nbEditorBefore, 10000, 100);
        return new BotBdmEditor(bot);
    }

    public BotBdmEditor openBdm() {
        int nbEditorBefore = bot.editors().size();
        bot.toolbarButtonWithId(SWTBotConstants.openArtifactButtonId(BusinessObjectModelFileStore.BOM_FILENAME)).click();
        waitForEditor(nbEditorBefore, 10000, 100);
        return new BotBdmEditor(bot);
    }

    public BotOrganizationEditor openActiveOrga() {
        var fileName = new ActiveOrganizationProvider().getActiveOrganizationFileName();
        int nbEditorBefore = bot.editors().size();
        bot.toolbarButtonWithId(SWTBotConstants.openArtifactButtonId(fileName)).click();
        waitForEditor(nbEditorBefore, 10000, 100);
        return new BotOrganizationEditor(bot, fileName);
    }

    public BotApplicationEditor createApplicationFile() {
        int nbEditorBefore = bot.editors().size();
        bot.toolbarButtonWithId(SWTBotConstants
                .createArtifactButtonId(org.bonitasoft.studio.la.i18n.Messages.application)).click();
        waitForEditor(nbEditorBefore, 10000, 100);
        return new BotApplicationEditor(bot, bot.activeEditor());
    }

    public BotApplicationEditor openApplicationFile(String applicationFileName) {
        bot.toolbarButtonWithId(
                SWTBotConstants.extensionCardId(org.bonitasoft.studio.la.i18n.Messages.application))
                .click();
        waitForToolbarButton(SWTBotConstants.openArtifactButtonId(applicationFileName));
        int nbEditorBefore = bot.editors().size();
        bot.toolbarButtonWithId(SWTBotConstants.openArtifactButtonId(applicationFileName)).click();
        waitForEditor(nbEditorBefore, 30000, 100);
        return new BotApplicationEditor(bot, bot.activeEditor());
    }

    protected void waitForEditor(int nbEditorsBefore, long timeout, long interval) {
        bot.waitUntil(new ConditionBuilder()
                .withTest(() -> nbEditorsBefore + 1 == bot.editors().size())
                .withFailureMessage(() -> "New editor has not been opened.")
                .create(), timeout, interval);
    }

    protected void waitForToolbarButton(String buttonId) {
        bot.waitUntil(new ConditionBuilder()
                .withTest(() -> {
                    try {
                        bot.toolbarButtonWithId(buttonId);
                        return true;
                    } catch (WidgetNotFoundException e) {
                        return false;
                    }
                })
                .withFailureMessage(() -> String.format("Buton '%s' has not been found.", buttonId))
                .create());
    }

}
