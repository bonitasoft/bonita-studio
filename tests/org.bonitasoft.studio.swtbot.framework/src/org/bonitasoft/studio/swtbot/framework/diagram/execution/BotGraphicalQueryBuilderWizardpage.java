/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.diagram.execution;

import org.bonitasoft.studio.sqlbuilder.ex.Messages;
import org.bonitasoft.studio.swtbot.framework.AbstractBotWizardPage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;


public class BotGraphicalQueryBuilderWizardpage extends AbstractBotWizardPage {

    public BotGraphicalQueryBuilderWizardpage(final SWTGefBot bot) {
        super(bot);
    }

    public BotGraphicalQueryBuilderWizardpage addTable(final String databaseName, final String tableName) {
        final SWTBotShell wizardShell = bot.activeShell();
        bot.tabItem(Messages.designerTabLabel).activate();
        bot.button(Messages.addTableLabel).click();
        bot.waitUntil(Conditions.shellIsActive(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_DIALOG_ADD_TABLE_TITLE));
        bot.tree().getTreeItem(databaseName).expand().select(tableName);
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(wizardShell.getText()));
        wizardShell.setFocus();
        return this;
    }

    public void writeQuery(final String query) {
        bot.tabItem(Messages.queryTabLabel).activate();
        bot.styledText().setText(query);
    }

    public String getQuery() {
        bot.tabItem(Messages.queryTabLabel).activate();
        return bot.styledText().getText();
    }

}
