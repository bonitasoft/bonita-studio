/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework.diagram.export;

import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

public class BotExportBOSDialog extends BotWizardDialog {

    public BotExportBOSDialog(final SWTGefBot bot) {
        super(bot, org.bonitasoft.studio.exporter.Messages.ExportButtonLabel);
    }

    public BotExportBOSDialog selectAll() {
        bot.button(Messages.selectAll).click();
        return this;
    }

    public BotExportBOSDialog setDestinationPath(String path) {
        bot.comboBoxWithLabel(Messages.destinationPath + " *").setText(path);
        return this;
    }

    @Override
    public void finish() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)), 5000);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.exportLabel), 15000);
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

}
