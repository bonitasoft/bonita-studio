/**
 * Copyright (C) 2017 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.swtbot.framework.la;

import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;

public class BotApplicationEditor {

    private SWTBotEditor editor;
    private SWTGefBot bot;

    public BotApplicationEditor(SWTGefBot bot, SWTBotEditor editor) {
        this.editor = editor;
        this.bot = bot;
    }

    public void close() {
        editor.close();
    }

    public String getTitle() {
        return editor.getTitle();
    }

    /**
     * save before deploy, else a pop up may appear
     */
    public void deploy() {
        editor.save();
        bot.toolbarButton(Messages.deploy).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deployDoneTitle));
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    public void delete() {
        bot.toolbarButton(Messages.delete).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteConfirmation));
        bot.button(IDialogConstants.YES_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.deleteDoneTitle));
    }
}
