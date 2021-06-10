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
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.swtbot.framework.application.editor.project.BotEditProjectMetadataWizard;
import org.bonitasoft.studio.swtbot.framework.application.editor.project.BotExtensionCard;
import org.bonitasoft.studio.swtbot.framework.application.editor.project.BotExtensionWizard;
import org.bonitasoft.studio.swtbot.framework.application.editor.project.BotMarketplaceWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

public class BotProjectDetailsEditor {

    private SWTGefBot bot;

    public BotProjectDetailsEditor(SWTGefBot bot) {
        this.bot = bot;
    }

    public BotEditProjectMetadataWizard editProjectMetadata() {
        bot.labelWithId(SWTBotConstants.SWTBOT_ID_EDIT_PROJECT_METADATA).click();
        return new BotEditProjectMetadataWizard(bot);
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

}
