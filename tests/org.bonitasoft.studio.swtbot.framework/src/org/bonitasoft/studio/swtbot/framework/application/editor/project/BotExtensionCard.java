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
package org.bonitasoft.studio.swtbot.framework.application.editor.project;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellIsActive;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

public class BotExtensionCard {

    private SWTGefBot bot;
    private String artifactId;

    public BotExtensionCard(SWTGefBot bot, String artifactId) {
        this.bot = bot;
        this.artifactId = artifactId;
    }

    public void remove() {
        bot.activeEditor().setFocus();
        bot.waitUntil(new WidgetEnabled(SWTBotConstants.removeExtensionFromCard(artifactId)));
        bot.toolbarButtonWithId(SWTBotConstants.removeExtensionFromCard(artifactId)).click();
        bot.waitUntil(shellIsActive(Messages.removeExtensionConfirmationTitle));
        bot.button(IDialogConstants.YES_LABEL).click();
    }

    public void updateToLatest() {
        bot.activeEditor().setFocus();
        bot.waitUntil(new WidgetEnabled(SWTBotConstants.updateToLatestExtensionFromCard(artifactId)));
        bot.toolbarButtonWithId(SWTBotConstants.updateToLatestExtensionFromCard(artifactId)).click();
        bot.waitUntil(shellIsActive(Messages.updateExtensionConfirmationTitle));
        bot.button(IDialogConstants.YES_LABEL).click();
    }

    public BotMaximizedExtensionCard maximize() {
        Display.getDefault().syncExec(() -> {
            var clabel = bot.clabel().widget;
            var e = new Event();
            e.widget = clabel;
            clabel.notifyListeners(SWT.MouseUp, e);
        });
        return new BotMaximizedExtensionCard(bot);
    }

    /**
     * The default condition in {@link Conditions#widgetIsEnabled(org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot)} catch an out
     * of date widget that is disposed when the extension view composite is refreshed.
     * Using this condition, we re capture the widget at each try.
     */
    static class WidgetEnabled extends DefaultCondition {
        
        
        private String widgetId;

        public WidgetEnabled(String widgetId) {
            this.widgetId = widgetId;
        }

        public boolean test() throws Exception {
            return bot.toolbarButtonWithId(widgetId).isEnabled();
        }

        public String getFailureMessage() {
            return String.format( "Remove button with id %s is not enabled.", widgetId);
        }
        
    }
}
