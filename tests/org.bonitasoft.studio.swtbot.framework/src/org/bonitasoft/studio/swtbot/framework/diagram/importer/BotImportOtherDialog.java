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
package org.bonitasoft.studio.swtbot.framework.diagram.importer;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;

public class BotImportOtherDialog extends BotWizardDialog {

    public BotImportOtherDialog(final SWTGefBot bot) {
        super(bot, Messages.importTitle);
    }

    public BotImportOtherDialog setArchive(URL bosURLInClasspath) throws IOException {
        final SWTBotText pathText = bot.textWithLabel(Messages.selectFileToImport);
        pathText.setText(toAbsoluteFilePath(bosURLInClasspath));
        return this;
    }

    public BotImportOtherDialog selectBARImporter() throws IOException {
        bot.table().select("Bonita BAR 5.9/5.10");
        return this;
    }

    public BotImportOtherDialog selectBPMN2Importer() throws IOException {
        bot.table().select("BPMN 2.0");
        return this;
    }

    private String toAbsoluteFilePath(URL bosURLInClasspath) throws IOException {
        return new File(FileLocator.toFileURL(bosURLInClasspath).getFile()).getAbsolutePath();
    }

    @Override
    public void finish() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.importButtonLabel)), 10000);
        bot.button(Messages.importButtonLabel).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.importer.i18n.Messages.importResultTitle), 60000);
        final SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

}
