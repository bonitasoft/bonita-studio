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

import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotWizardDialog;
import org.bonitasoft.studio.swtbot.framework.application.BotDeployDialog;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

public class BotImportBOSDialog extends BotWizardDialog {

    public BotImportBOSDialog(final SWTGefBot bot) {
        super(bot, Messages.importBosArchiveTitle);
    }

    public BotImportBOSDialog setArchive(URL bosURLInClasspath) throws IOException {
        final SWTBotText pathText = bot.textWithLabel(Messages.selectLocation);
        Display.getDefault().syncExec(() -> {
            try {
                pathText.widget.setText(toAbsoluteFilePath(bosURLInClasspath));
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        });
        return this;
    }
    
    
    @Override
    public BotImportBOSDialog next() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.buttonWithId(String.valueOf(IDialogConstants.NEXT_ID))), 5000);
        bot.buttonWithId(String.valueOf(IDialogConstants.NEXT_ID)).click();
        return this;
    }
    
    @Override
    public BotImportBOSDialog back() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.buttonWithId(String.valueOf(IDialogConstants.BACK_ID))), 5000);
        bot.buttonWithId(String.valueOf(IDialogConstants.BACK_ID)).click();
        return this;
    }

    public SWTBotTree tree() {
        final SWTBotTree tree = bot.tree();
        bot.waitUntil(Conditions.widgetIsEnabled(tree), 10000);
        return tree;
    }

    public void keepAll() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.keepAll)), 5000);
        bot.button(Messages.keepAll).click();
    }

    public void overwriteAll() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.overwriteAll)), 5000);
        bot.button(Messages.overwriteAll).click();
    }

    public void importArchive() {
        // Wait extension resolution
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.importButtonLabel)), 50000);
        bot.button(Messages.importButtonLabel).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.importer.i18n.Messages.importResultTitle), 120000);
        bot.shell(org.bonitasoft.studio.importer.i18n.Messages.importResultTitle).activate();
        final SWTBotShell activeShell = bot.activeShell();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
    }

    public BotDeployDialog importAndDeploy() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(Messages.importButtonLabel)), 10000);
        bot.button(Messages.importButtonLabel).click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.importer.i18n.Messages.importResultTitle), 120000);
        bot.shell(org.bonitasoft.studio.importer.i18n.Messages.importResultTitle).activate();
        final SWTBotShell activeShell = bot.activeShell();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
        bot.button(org.bonitasoft.studio.importer.i18n.Messages.deploy).click();
        bot.waitUntil(Conditions.shellCloses(activeShell));
        return new BotDeployDialog(bot);
    }

    private String toAbsoluteFilePath(URL bosURLInClasspath) throws IOException {
        return new File(FileLocator.toFileURL(bosURLInClasspath).getFile()).getAbsolutePath();
    }

    public BotImportBOSDialog newProject(String repoName) {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.radio(org.bonitasoft.studio.importer.i18n.Messages.aNewRepository)));
        bot.radio(org.bonitasoft.studio.importer.i18n.Messages.aNewRepository).click();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButtonWithId(SWTBOT_ID_TRANSACTIONAL_TEXT_EDIT_BUTTON)));
        bot.toolbarButtonWithId(SWTBOT_ID_TRANSACTIONAL_TEXT_EDIT_BUTTON).click();
        bot.textWithId(SWTBOT_ID_NEW_PROJECT_NAME_TEXT_ID).setText(repoName);
        bot.toolbarButtonWithId(SWTBOT_ID_TRANSACTIONAL_TEXT_OK_BUTTON).click();
        return this;
    }

    public BotImportBOSDialog existingRepository(String repoName, Boolean current) {
        String name = current ? repoName + " " + Messages.currentRepoinfo : repoName;
        bot.waitUntil(
                Conditions.widgetIsEnabled(bot.radio(org.bonitasoft.studio.importer.i18n.Messages.anExistingRepository)));
        SWTBotShell activeShell = bot.activeShell();
        bot.radio(org.bonitasoft.studio.importer.i18n.Messages.anExistingRepository).click();
        activeShell.setFocus();
        bot.shell(Messages.importBosArchiveTitle).activate();
        bot.waitUntil(Conditions.shellIsActive(Messages.importBosArchiveTitle));
        bot.ccomboBoxWithId(SWTBOT_ID_BOS_IMPORT_PROJECT_COMBO).setSelection(name);
        return this;
    }

    @Override
    public void finish() {
        importArchive();
    }

    public BotImportBOSDialog existingRepository() {
        SWTBotShell activeShell = bot.activeShell();
        bot.radio(org.bonitasoft.studio.importer.i18n.Messages.anExistingRepository).click();
        activeShell.setFocus();
        bot.shell(Messages.importBosArchiveTitle).activate();
        return this;
    }
}
